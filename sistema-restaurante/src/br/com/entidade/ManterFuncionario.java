package br.com.entidade;

import br.com.controle.Funcionario;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class ManterFuncionario extends DAO {
    
    public void inserir(Funcionario f) throws Exception {
        validar(f);
        try {
            abrirBanco();
            if (cpfExiste(f.getCpf(), 0)) {
                throw new Exception("Já existe um funcionário cadastrado com este CPF.");
            }
            String query = "INSERT INTO funcionario (nome, cpf, email, telefone, data_admissao, id_cargo) VALUES (?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, f.getNome());
            pst.setString(2, f.getCpf());
            pst.setString(3, f.getEmail());
            pst.setString(4, f.getTelefone());
            pst.setObject(5, f.getDataAdmissao() != null ? f.getDataAdmissao() : LocalDateTime.now());
            pst.setInt(6, f.getIdCargo());
            pst.execute();
        } finally {
            fechar();
        }
    }
    
    public void alterarFuncionario(Funcionario f) throws Exception {
        if (f.getIdFuncionario() <= 0) {
            throw new Exception("Selecione um funcionário na tabela antes de alterar.");
        }
        validar(f);
        try {
            abrirBanco();
            if (cpfExiste(f.getCpf(), f.getIdFuncionario())) {
                throw new Exception("Já existe outro funcionário cadastrado com este CPF!");
            }
            String query = "UPDATE funcionario SET nome=?, cpf=?, email=?, telefone=?, id_cargo=? WHERE id_funcionario=?";
            pst = con.prepareStatement(query);
            pst.setString(1, f.getNome());
            pst.setString(2, f.getCpf());
            pst.setString(3, f.getEmail());
            pst.setString(4, f.getTelefone());
            pst.setInt(5, f.getIdCargo());
            pst.setInt(6, f.getIdFuncionario());
            pst.execute();
        } finally {
            fechar();
        }
    }
    
    public ArrayList<Funcionario> PesquisarTudo() throws Exception {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT f.*, c.nome_cargo FROM funcionario f "
                    + "LEFT JOIN cargo c ON f.id_cargo = c.id_cargo "
                    + "ORDER BY f.nome";
            pst = con.prepareStatement(query);
            try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    Funcionario f = new Funcionario();
                    preencher(f, tr);
                    listaFuncionarios.add(f);
                }
            }
        } finally {
            fechar();
        }
        return listaFuncionarios;
    }
    
    public ArrayList<Funcionario> pesquisarPorFiltro(String busca) throws Exception {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
        try {
            abrirBanco();
            String buscaLimpa = busca.replaceAll("[^0-9]", "");
            String query = "SELECT f.*, c.nome_cargo FROM funcionario f "
                    + "LEFT JOIN cargo c ON f.id_cargo = c.id_cargo "
                    + "WHERE CAST(f.id_funcionario AS CHAR) = ? "
                    + "OR (? <> '' AND REPLACE(REPLACE(f.cpf, '.', ''), '-', '') LIKE ?) "
                    + "ORDER BY f.nome";
            pst = con.prepareStatement(query);
            pst.setString(1, busca.trim());
            pst.setString(2, buscaLimpa);
            pst.setString(3, "%" + buscaLimpa + "%");
            try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    Funcionario f = new Funcionario();
                    preencher(f, tr);
                    listaFuncionarios.add(f);
                }
            }
        } finally {
            fechar();
        }
        return listaFuncionarios;
    }
  
    public void deletarFuncionario(Funcionario f) throws Exception {
    if (f.getIdFuncionario() <= 0) {
            throw new Exception("Selecione um funcionário na tabela antes de excluir.");
        }
        try {
            abrirBanco();
            if (contar("SELECT COUNT(*) FROM pedido WHERE id_funcionario=?", f.getIdFuncionario()) > 0) {
                throw new Exception("Não é possível excluir: este funcionário possui pedidos registrados!");
            }

            String query = "DELETE FROM funcionario WHERE id_funcionario = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, f.getIdFuncionario());
            pst.execute();
        } finally {
            fechar();
        }
}

    private void validar(Funcionario f) throws Exception {
        if (f.getNome() == null || f.getNome().trim().isEmpty()) {
            throw new Exception("Informe o nome do funcionário.");
        }
        if (f.getCpf() == null || f.getCpf().trim().isEmpty()) {
            throw new Exception("Informe o CPF do funcionário.");
        }
        if (f.getIdCargo() <= 0) {
            throw new Exception("Selecione um cargo válido.");
        }
    }

    private boolean cpfExiste(String cpf, int idIgnorar) throws Exception {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        String query = "SELECT id_funcionario FROM funcionario WHERE REPLACE(REPLACE(cpf, '.', ''), '-', '') = ? AND id_funcionario <> ?";
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

    private void preencher(Funcionario f, ResultSet tr) throws Exception {
        f.setIdFuncionario(tr.getInt("id_funcionario"));
        f.setIdCargo(tr.getInt("id_cargo"));
        f.setNomeCargo(tr.getString("nome_cargo"));
        f.setNome(tr.getString("nome"));
        f.setCpf(tr.getString("cpf"));
        f.setEmail(tr.getString("email"));
        f.setTelefone(tr.getString("telefone"));
        if (tr.getTimestamp("data_admissao") != null) {
            f.setDataAdmissao(tr.getTimestamp("data_admissao").toLocalDateTime());
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