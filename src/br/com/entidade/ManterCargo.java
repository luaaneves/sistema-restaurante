package br.com.entidade;

import br.com.controle.Cargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterCargo extends DAO {

    public void inserir(Cargo c) throws Exception {
        validar(c);
        try{
            abrirBanco();
            if (nomeExiste(c.getNomeCargo(), 0)){
                throw new Exception("Já existe um cargo cadastrado com o nome \"" + c.getNomeCargo() + "\".");
            }
            String query = "INSERT INTO cargo (nome_cargo, salario, carga_horaria, descricao, ativo) VALUES (?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, c.getNomeCargo());
            pst.setDouble(2, c.getSalario());
            pst.setInt(3, c.getCargaHoraria());
            pst.setString(4, c.getDescricao());
            pst.setInt(5, c.getAtivo());
            pst.execute();
        }finally{
            fechar();
        }
    }

    public void alterarCargo(Cargo c) throws Exception {
        if(c.getIdCargo() <= 0){
            throw new Exception("Selecione um cargo na tabela antes de alterar.");
        }
        validar(c);
        try{
            abrirBanco();
            if (nomeExiste(c.getNomeCargo(), c.getIdCargo())){
                throw new Exception("Já existe outro cargo cadastrado com o nome\"" + c.getNomeCargo() + "\".");
            }
            String query = "UPDATE cargo SET nome_cargo=?, salario=?, carga_horaria=?, descricao=?, ativo=? WHERE id_cargo=?";
            pst = con.prepareStatement(query);
            pst.setString(1, c.getNomeCargo());
            pst.setDouble(2, c.getSalario());
            pst.setInt(3, c.getCargaHoraria());
            pst.setString(4, c.getDescricao());
            pst.setInt(5, c.getAtivo());
            pst.setInt(6, c.getIdCargo());
            pst.execute();
        } finally{
            fechar();
        }
    }

    public void deletarCargo(Cargo c) throws Exception {
        if(c.getIdCargo() <= 0){
            throw new Exception("Selecione um cargo na tabela antes de excluir.");
        }
        try{
            abrirBanco();
            String verifica = "SELECT COUNT(*) FROM funcionario WHERE id_cargo=?";
            try (PreparedStatement pstLocal = con.prepareStatement(verifica)){
                pstLocal.setInt(1, c.getIdCargo());
                try (ResultSet tr = pstLocal.executeQuery()){
                    if (tr.next() && tr.getInt(1) > 0) {
                        throw new Exception("Não é possível excluir. Existem Funcionários com este cargo.");
                }
            }
        }
            String query = "DELETE FROM cargo WHERE id_cargo=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, c.getIdCargo());
            pst.execute();
        }finally{
            fechar();   
        }
    }

    public ArrayList<Cargo> PesquisarTudo() throws Exception {
        ArrayList<Cargo> cargos = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM cargo ORDER BY nome_cargo";
            pst = con.prepareStatement(query);
            try (ResultSet tr = pst.executeQuery()){
                while (tr.next()){
                    Cargo c = new Cargo();
                    preencher(c, tr);
                    cargos.add(c);
                }
            } 
        }finally{
            fechar();
        }
        return cargos;
    }
    
    public ArrayList<Cargo> pesquisarPorFiltro(String busca) throws Exception {
        ArrayList<Cargo> cargos = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM cargo WHERE CAST(id_cargo AS CHAR) = ? OR LOWER(nome_cargo) LIKE ? ORDER BY nome_cargo";
            pst = con.prepareStatement(query);
            pst.setString(1, busca.trim());
            pst.setString(2, "%" + busca.trim().toLowerCase() + "%");
            try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    Cargo c = new Cargo();
                    preencher(c, tr);
                    cargos.add(c);
                }
            }
        } finally {
            fechar();
        }
        return cargos;
    }
    
    private void validar(Cargo c) throws Exception {
        if (c.getNomeCargo() == null || c.getNomeCargo().trim().isEmpty()) {
            throw new Exception("Informe o nome do cargo.");
        }
        if (c.getSalario() <= 0) {
            throw new Exception("O salário deve ser maior que zero.");
        }
        if (c.getCargaHoraria() <= 0) {
            throw new Exception("A carga horária deve ser maior que zero.");
        }
    }
    
    private boolean nomeExiste(String nome, int idIgnorar) throws Exception {
        String query = "SELECT id_cargo FROM cargo WHERE LOWER(nome_cargo)=? AND id_cargo<>?";
        try (PreparedStatement pstLocal = con.prepareStatement(query)) {
            pstLocal.setString(1, nome.trim().toLowerCase());
            pstLocal.setInt(2, idIgnorar);
            try (ResultSet tr = pstLocal.executeQuery()) {
                return tr.next();
            }
        }
    }
    
    private void preencher(Cargo c, ResultSet tr) throws Exception {
        c.setIdCargo(tr.getInt("id_cargo"));
        c.setNomeCargo(tr.getString("nome_cargo"));
        c.setSalario(tr.getDouble("salario"));
        c.setCargaHoraria(tr.getInt("carga_horaria"));
        c.setDescricao(tr.getString("descricao"));
        c.setAtivo(tr.getInt("ativo"));
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