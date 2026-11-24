package br.com.entidade;

import br.com.controle.Cliente;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class ManterCliente extends DAO {
    
    public void inserir(Cliente c) throws Exception {
        validar(c);
        try {
            abrirBanco();
            if(cpfExiste(c.getCpf(), 0)){
                throw new Exception("Já existe um cliente cadastrado com este CPF.");
            }
    
            String query = "INSERT INTO cliente (nome, cpf, email, telefone, data_cadastro) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getCpf());  
            pst.setString(3, c.getEmail());
            pst.setString(4, c.getTelefone());
            pst.setObject(5, c.getDataCadastro() != null ? c.getDataCadastro() : LocalDateTime.now()); 
            pst.execute(); 
            
        } finally {
            fechar();
        }
    }
    
    public void alterarCliente(Cliente c) throws Exception {
        if (c.getIdCliente() <= 0){
            throw new Exception("Selecione um cliente na tabela antes de alterar.");
        }
        validar(c);
        try {
            abrirBanco();
            if(cpfExiste(c.getCpf(), c.getIdCliente())){
                throw new Exception("Já existe outro cliente cadastrado com este CPF.");
            }
            
            String query = "UPDATE cliente SET nome=?, cpf=?, email=?, telefone=? WHERE id_cliente=?";
            pst = con.prepareStatement(query);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getCpf());
            pst.setString(3, c.getEmail());
            pst.setString(4, c.getTelefone());
            pst.setInt(5, c.getIdCliente());
            pst.execute();

        } finally {
            fechar();
        }
    }
    
    public void deletarCliente(Cliente c) throws Exception {
        if (c.getIdCliente() <= 0) {
            throw new Exception("Selecione um cliente na tabela antes de excluir.");
        }
        try {
            abrirBanco();
            if (contar("SELECT COUNT(*) FROM reserva WHERE id_cliente=?", c.getIdCliente()) > 0) {
                throw new Exception("Não é possível excluir. Este cliente possui reservas cadastradas.");
            }
            if (contar("SELECT COUNT(*) FROM pedido WHERE id_cliente=?", c.getIdCliente()) > 0) {
                throw new Exception("Não é possível excluir. Este cliente possui pedidos registrados.");
            }

            String query = "DELETE FROM cliente WHERE id_cliente = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, c.getIdCliente());
            pst.execute();
        } finally {
            fechar();
        }
    }
    
    public ArrayList<Cliente> PesquisarTudo() throws Exception {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM cliente ORDER BY nome";
            pst = con.prepareStatement(query);
            try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    Cliente c = new Cliente();
                    preencher(c, tr);
                    listaClientes.add(c);
                }
            }
        } finally {
            fechar();
        }
        return listaClientes;
    }
    
    public ArrayList<Cliente> pesquisarPorFiltro(String busca) throws Exception {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            abrirBanco();
            String buscaLimpa = busca.replaceAll("[^0-9]", "");
            String query = "SELECT * FROM cliente WHERE CAST(id_cliente AS CHAR) = ? "
                    + "OR (? <> '' AND REPLACE(REPLACE(cpf, '.', ''), '-', '') LIKE ?) "
                    + "ORDER BY nome";
            pst = con.prepareStatement(query);
            pst.setString(1, busca.trim());
            pst.setString(2, buscaLimpa);
            pst.setString(3, "%" + buscaLimpa + "%");
            try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    Cliente c = new Cliente();
                    preencher(c, tr);
                    listaClientes.add(c);
                }
            }
        } finally {
            fechar();
        }
        return listaClientes;
    }
    
    private void validar(Cliente c) throws Exception {
        if (c.getNome() == null || c.getNome().trim().isEmpty()) {
            throw new Exception("Informe o nome completo do cliente.");
        }
        if (c.getCpf() == null || c.getCpf().trim().isEmpty()) {
            throw new Exception("Informe o CPF do cliente.");
        }
    }

    private boolean cpfExiste(String cpf, int idIgnorar) throws Exception {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        String query = "SELECT id_cliente FROM cliente WHERE REPLACE(REPLACE(cpf, '.', ''), '-', '') = ? AND id_cliente <> ?";
        try (PreparedStatement pstLocal = con.prepareStatement(query)) {
            pstLocal.setString(1, cpfLimpo);
            pstLocal.setInt(2, idIgnorar);
            try (ResultSet tr = pstLocal.executeQuery()) {
                return tr.next();
            }
        }
    }

    private int contar(String query, int id) throws Exception {
        try (PreparedStatement pstLocal = con.prepareStatement(query)) {
            pstLocal.setInt(1, id);
            try (ResultSet tr = pstLocal.executeQuery()) {
                tr.next();
                return tr.getInt(1);
            }
        }
    }

    private void preencher(Cliente c, ResultSet tr) throws Exception {
        c.setIdCliente(tr.getInt("id_cliente"));
        c.setNome(tr.getString("nome"));
        c.setCpf(tr.getString("cpf"));
        c.setEmail(tr.getString("email"));
        c.setTelefone(tr.getString("telefone"));
        if (tr.getTimestamp("data_cadastro") != null) {
            c.setDataCadastro(tr.getTimestamp("data_cadastro").toLocalDateTime());
        }
    }

    private void fechar() {
        try {
            fecharBanco();
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}