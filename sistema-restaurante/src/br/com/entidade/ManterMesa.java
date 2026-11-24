package br.com.entidade;

import br.com.controle.Mesa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterMesa extends DAO {

    public void inserir(Mesa m) throws Exception {
        validar(m);
        try {
            abrirBanco();
            if (numeroJaExiste(m.getNumero(), 0)) {
                throw new Exception("Já existe uma mesa com o número " + m.getNumero() + "!");
            }
            String query = "INSERT INTO MESA (CAPACIDADE, LOCAL, NUMERO, STATUS) VALUES (?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, m.getCapacidade());
            pst.setString(2, m.getLocal());
            pst.setInt(3, m.getNumero());
            pst.setInt(4, m.getStatus());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public void alterarMesa(Mesa m) throws Exception {
        if (m.getIdMesa() <= 0) {
            throw new Exception("Selecione uma mesa na tabela (ou pesquise pelo ID) antes de alterar.");
        }
        validar(m);
        try {
            abrirBanco();
            if (numeroJaExiste(m.getNumero(), m.getIdMesa())) {
                throw new Exception("Já existe outra mesa com o número " + m.getNumero() + "!");
            }
            String query = "UPDATE MESA SET CAPACIDADE=?, LOCAL=?, NUMERO=?, STATUS=? WHERE ID_MESA=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, m.getCapacidade());
            pst.setString(2, m.getLocal());
            pst.setInt(3, m.getNumero());
            pst.setInt(4, m.getStatus());
            pst.setInt(5, m.getIdMesa());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public void deletarMesa(Mesa m) throws Exception {
        if (m.getIdMesa() <= 0) {
            throw new Exception("Selecione uma mesa na tabela (ou pesquise pelo ID) antes de excluir.");
        }
        try {
            abrirBanco();

            if (contar("SELECT COUNT(*) FROM MESA_RESERVA WHERE ID_MESA=?", m.getIdMesa()) > 0) {
                throw new Exception("Não é possível excluir: esta mesa está vinculada a uma reserva.");
            }
            if (contar("SELECT COUNT(*) FROM PEDIDO WHERE ID_MESA=?", m.getIdMesa()) > 0) {
                throw new Exception("Não é possível excluir: esta mesa possui pedidos registrados.");
            }
            String query = "DELETE FROM MESA WHERE ID_MESA=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, m.getIdMesa());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public boolean PesquisarRegistro(Mesa m) throws Exception {
        try {
            abrirBanco();
            String query = "SELECT * FROM MESA WHERE ID_MESA=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, m.getIdMesa());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                preencher(m, tr);
                return true;
            }
            return false;
        } finally {
            fechar();
        }
    }

    public ArrayList<Mesa> PesquisarTudo() throws Exception {
        ArrayList<Mesa> mesas = new ArrayList<Mesa>();
        try {
            abrirBanco();
            String query = "SELECT * FROM MESA ORDER BY NUMERO";
            pst = (PreparedStatement) con.prepareStatement(query);
            ResultSet tr = pst.executeQuery();
            while (tr.next()) {
                Mesa m = new Mesa();
                preencher(m, tr);
                mesas.add(m);
            }
        } finally {
            fechar();
        }
        return mesas;
    }

    private void validar(Mesa m) throws Exception {
        if (m.getNumero() <= 0) {
            throw new Exception("Informe um número de mesa maior que zero.");
        }
        if (m.getCapacidade() <= 0) {
            throw new Exception("A capacidade deve ser maior que zero.");
        }
        if (m.getLocal() == null || m.getLocal().trim().isEmpty()) {
            throw new Exception("Informe o local da mesa.");
        }
    }

    private boolean numeroJaExiste(int numero, int idIgnorar) throws Exception {
        String query = "SELECT ID_MESA FROM MESA WHERE NUMERO=? AND ID_MESA<>?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, numero);
        pst.setInt(2, idIgnorar);
        ResultSet tr = pst.executeQuery();
        return tr.next();
    }

    private int contar(String query, int id) throws Exception {
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet tr = pst.executeQuery();
        tr.next();
        return tr.getInt(1);
    }

    private void preencher(Mesa m, ResultSet tr) throws Exception {
        m.setIdMesa(tr.getInt("ID_MESA"));
        m.setCapacidade(tr.getInt("CAPACIDADE"));
        m.setLocal(tr.getString("LOCAL"));
        m.setNumero(tr.getInt("NUMERO"));
        m.setStatus(tr.getInt("STATUS"));
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
