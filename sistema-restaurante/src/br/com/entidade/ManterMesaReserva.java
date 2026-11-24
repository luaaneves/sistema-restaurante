package br.com.entidade;

import br.com.controle.MesaReserva;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManterMesaReserva extends DAO {

    public void inserir(MesaReserva mr) throws Exception {
        validar(mr);
        try {
            abrirBanco();
            if (vinculoExiste(mr.getIdReserva(), mr.getIdMesa())) {
                throw new Exception("Esta mesa já está vinculada a esta reserva!");
            }
            if (mesaOcupada(mr, 0)) {
                throw new Exception("Conflito de horário: esta mesa já está reservada nesse dia e horário.");
            }
            
            String query = "INSERT INTO mesa_reserva (id_reserva, id_mesa, hora_chegada, hora_saida, observacao) VALUES (?,?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, mr.getIdReserva());
            pst.setInt(2, mr.getIdMesa());
            pst.setString(3, horario(mr.getHoraChegada()));
            pst.setString(4, horario(mr.getHoraSaida()));
            pst.setString(5, mr.getObservacao());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public void alterarMesaReserva(br.com.controle.MesaReserva mrNovo, int idMesaAntigo, int idReservaAntigo) throws Exception {
        if (idMesaAntigo <= 0 || idReservaAntigo <= 0) {
            throw new Exception("Selecione um vínculo na tabela antes de alterar.");
        }
        validar(mrNovo);
        try {
            abrirBanco();
            boolean mudouChave = (mrNovo.getIdReserva() != idReservaAntigo) || (mrNovo.getIdMesa() != idMesaAntigo);
            if (mudouChave && vinculoExiste(mrNovo.getIdReserva(), mrNovo.getIdMesa())) {
                throw new Exception("Esta mesa já está vinculada à reserva selecionada!");
            }

            String query = "UPDATE mesa_reserva SET id_mesa = ?, id_reserva = ?, hora_chegada = ?, hora_saida = ?, observacao = ? "
                    + "WHERE id_mesa = ? AND id_reserva = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, mrNovo.getIdMesa());
            pst.setInt(2, mrNovo.getIdReserva());
            pst.setString(3, horario(mrNovo.getHoraChegada()));
            pst.setString(4, horario(mrNovo.getHoraSaida()));
            pst.setString(5, mrNovo.getObservacao());
            pst.setInt(6, idMesaAntigo);
            pst.setInt(7, idReservaAntigo);
            pst.execute();
        } finally {
            fechar();
        }
    }

    public void deletarMesaReserva(MesaReserva mr) throws Exception {
        if (mr.getIdReserva() <= 0 || mr.getIdMesa() <= 0) {
            throw new Exception("Selecione um vínculo na tabela antes de excluir.");
        }
        try {
            abrirBanco();
            String query = "DELETE FROM mesa_reserva WHERE id_reserva=? AND id_mesa=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, mr.getIdReserva());
            pst.setInt(2, mr.getIdMesa());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public ArrayList<MesaReserva> PesquisarTudo() throws Exception {
        ArrayList<MesaReserva> mesasReservadas = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT mr.*, m.numero AS numero_mesa, c.nome AS nome_cliente, r.data_reserva "
                    + "FROM mesa_reserva mr " + "INNER JOIN mesa m ON mr.id_mesa = m.id_mesa " + "INNER JOIN reserva r ON mr.id_reserva = r.id_reserva " 
                    + "INNER JOIN cliente c ON r.id_cliente = c.id_cliente " + "ORDER BY r.data_reserva DESC, m.numero";
            pst = con.prepareStatement(query);
            try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    MesaReserva mr = new MesaReserva();
                    preencher(mr, tr);
                    mesasReservadas.add(mr);
                }
            }
        } finally {
            fechar();
        }
        return mesasReservadas;
    }
    
    public ArrayList<MesaReserva> pesquisarPorFiltro(String busca) throws Exception {
        ArrayList<MesaReserva> mesasReservadas = new ArrayList<>();
        try {
            abrirBanco();
            String termo = busca.trim().toLowerCase();
            String numeroLimpo = termo.replace("mesa", "").trim();

            String query = "SELECT mr.*, m.numero AS numero_mesa, c.nome AS nome_cliente, r.data_reserva "
                    + "FROM mesa_reserva mr "
                    + "INNER JOIN mesa m ON mr.id_mesa = m.id_mesa "
                    + "INNER JOIN reserva r ON mr.id_reserva = r.id_reserva "
                    + "INNER JOIN cliente c ON r.id_cliente = c.id_cliente "
                    + "WHERE (? <> '' AND CAST(m.numero AS CHAR) = ?) "
                    + "OR LOWER(c.nome) LIKE ? "
                    + "ORDER BY r.data_reserva DESC, m.numero";

            pst = con.prepareStatement(query);
            pst.setString(1, numeroLimpo);
            pst.setString(2, numeroLimpo);
            pst.setString(3, "%" + termo + "%");

            try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    MesaReserva mr = new MesaReserva();
                    preencher(mr, tr);
                    mesasReservadas.add(mr);
                }
            }
        } finally {
            fechar();
        }
        return mesasReservadas;
    }
    
    private void validar(MesaReserva mr) throws Exception {
        if (mr.getIdMesa() <= 0) {
            throw new Exception("Selecione uma mesa válida.");
        }
        if (mr.getIdReserva() <= 0) {
            throw new Exception("Selecione uma reserva válida.");
        }
    }

    private boolean vinculoExiste(int idReserva, int idMesa) throws Exception {
        String query = "SELECT id_mesa FROM mesa_reserva WHERE id_reserva=? AND id_mesa=?";
        try (PreparedStatement pstLocal = con.prepareStatement(query)) {
            pstLocal.setInt(1, idReserva);
            pstLocal.setInt(2, idMesa);
            try (ResultSet tr = pstLocal.executeQuery()) {
                return tr.next();
            }
        }
    }
    
    private boolean mesaOcupada(MesaReserva mr, int idReservaIgnorar) throws Exception {
        String query = "SELECT mr.id_reserva "
                + "FROM mesa_reserva mr "
                + "INNER JOIN reserva r_existente ON mr.id_reserva = r_existente.id_reserva "
                + "INNER JOIN reserva r_nova ON r_nova.id_reserva = ? "
                + "WHERE mr.id_mesa = ? "
                + "AND mr.id_reserva <> ? "
                + "AND mr.id_reserva <> ? "
                + "AND DATE(r_existente.data_reserva) = DATE(r_nova.data_reserva) "
                + "AND TIME(COALESCE(mr.hora_chegada, '00:00:00')) < TIME(COALESCE(?, '23:59:59')) "
                + "AND TIME(COALESCE(mr.hora_saida, '23:59:59')) > TIME(COALESCE(?, '00:00:00'))";

        try (PreparedStatement pstLocal = con.prepareStatement(query)) {
            pstLocal.setInt(1, mr.getIdReserva());
            pstLocal.setInt(2, mr.getIdMesa());
            pstLocal.setInt(3, mr.getIdReserva());
            pstLocal.setInt(4, idReservaIgnorar);
            pstLocal.setString(5, horario(mr.getHoraSaida()));
            pstLocal.setString(6, horario(mr.getHoraChegada()));
            try (ResultSet tr = pstLocal.executeQuery()) {
                return tr.next();
            }
        }
    }

    private String horario(String texto) {
        return (texto != null && !texto.trim().isEmpty()) ? texto.trim() : null;
    }

    private void preencher(MesaReserva mr, ResultSet tr) throws Exception {
        mr.setIdReserva(tr.getInt("id_reserva"));
        mr.setIdMesa(tr.getInt("id_mesa"));
        mr.setHoraChegada(tr.getString("hora_chegada"));
        mr.setHoraSaida(tr.getString("hora_saida"));
        mr.setObservacao(tr.getString("observacao"));
        mr.setNumeroMesa(tr.getInt("numero_mesa"));
        mr.setNomeCliente(tr.getString("nome_cliente"));
        mr.setDataReserva(tr.getString("data_reserva"));
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
