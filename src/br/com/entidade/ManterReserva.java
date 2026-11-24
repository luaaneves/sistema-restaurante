package br.com.entidade;

import br.com.controle.Reserva;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

public class ManterReserva extends DAO {

    private static final DateTimeFormatter DATA_TELA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter DATA_BANCO = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private static final DateTimeFormatter HORA_TELA = DateTimeFormatter.ofPattern("HH:mm");

    private static final String SELECT_JOIN =
            "SELECT r.*, c.NOME AS NOME_CLIENTE FROM RESERVA r "
            + "INNER JOIN CLIENTE c ON r.ID_CLIENTE = c.ID_CLIENTE ";

    public void inserir(Reserva r) throws Exception {
        validar(r);

        if (LocalDate.parse(r.getDataReserva()).isBefore(LocalDate.now())) {
            throw new Exception("A data da reserva não pode ser anterior a hoje.");
        }
        try {
            abrirBanco();
            String query = "INSERT INTO RESERVA (ID_CLIENTE, DATA_RESERVA, HORARIO, QNT_PESSOAS, STATUS) VALUES (?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, r.getIdCliente());
            pst.setString(2, r.getDataReserva());
            pst.setString(3, r.getHorario());
            pst.setInt(4, r.getQntPessoas());
            pst.setInt(5, r.getStatus());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public void alterarReserva(Reserva r) throws Exception {
        if (r.getIdReserva() <= 0) {
            throw new Exception("Selecione uma reserva na tabela (ou pesquise pelo ID) antes de alterar.");
        }
        validar(r);
        try {
            abrirBanco();
            String query = "UPDATE RESERVA SET ID_CLIENTE=?, DATA_RESERVA=?, HORARIO=?, QNT_PESSOAS=?, STATUS=? WHERE ID_RESERVA=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, r.getIdCliente());
            pst.setString(2, r.getDataReserva());
            pst.setString(3, r.getHorario());
            pst.setInt(4, r.getQntPessoas());
            pst.setInt(5, r.getStatus());
            pst.setInt(6, r.getIdReserva());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public void deletarReserva(Reserva r) throws Exception {
        if (r.getIdReserva() <= 0) {
            throw new Exception("Selecione uma reserva na tabela (ou pesquise pelo ID) antes de excluir.");
        }
        try {
            abrirBanco();

            if (contar("SELECT COUNT(*) FROM MESA_RESERVA WHERE ID_RESERVA=?", r.getIdReserva()) > 0) {
                throw new Exception("Não é possível excluir: esta reserva já tem mesa vinculada.\n"
                        + "Remova o vínculo em Mesa/Reserva ou altere o status para Cancelada.");
            }
            if (contar("SELECT COUNT(*) FROM PEDIDO WHERE ID_RESERVA=?", r.getIdReserva()) > 0) {
                throw new Exception("Não é possível excluir: esta reserva possui pedidos.\n"
                        + "Altere o status para Cancelada.");
            }
            String query = "DELETE FROM RESERVA WHERE ID_RESERVA=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, r.getIdReserva());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public boolean PesquisarRegistro(Reserva r) throws Exception {
        try {
            abrirBanco();
            pst = (PreparedStatement) con.prepareStatement(SELECT_JOIN + "WHERE r.ID_RESERVA=?");
            pst.setInt(1, r.getIdReserva());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                preencher(r, tr);
                return true;
            }
            return false;
        } finally {
            fechar();
        }
    }

    public ArrayList<Reserva> PesquisarTudo() throws Exception {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        try {
            abrirBanco();
            pst = (PreparedStatement) con.prepareStatement(SELECT_JOIN + "ORDER BY r.DATA_RESERVA, r.HORARIO");
            ResultSet tr = pst.executeQuery();
            while (tr.next()) {
                Reserva r = new Reserva();
                preencher(r, tr);
                reservas.add(r);
            }
        } finally {
            fechar();
        }
        return reservas;
    }

    public static String dataParaBanco(String dataTela) throws Exception {
        try {
            return LocalDate.parse(dataTela, DATA_TELA).format(DATA_BANCO);
        } catch (Exception e) {
            throw new Exception("Data inválida. Use o formato dd/mm/aaaa.");
        }
    }

    public static String dataParaTela(String dataBanco) {
        return LocalDate.parse(dataBanco, DATA_BANCO).format(DATA_TELA);
    }

    public static String horaParaBanco(String horaTela) throws Exception {
        try {
            return LocalTime.parse(horaTela, HORA_TELA).toString() + ":00";
        } catch (Exception e) {
            throw new Exception("Horário inválido. Use o formato hh:mm (ex.: 19:30).");
        }
    }

    public static String horaParaTela(String horaBanco) {
        return horaBanco.substring(0, 5);
    }

    private void validar(Reserva r) throws Exception {
        if (r.getIdCliente() <= 0) {
            throw new Exception("Selecione o cliente da reserva.");
        }
        if (r.getQntPessoas() <= 0) {
            throw new Exception("A quantidade de pessoas deve ser maior que zero.");
        }
        if (r.getDataReserva() == null || r.getHorario() == null) {
            throw new Exception("Informe a data e o horário da reserva.");
        }
    }

    private int contar(String query, int id) throws Exception {
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet tr = pst.executeQuery();
        tr.next();
        return tr.getInt(1);
    }

    private void preencher(Reserva r, ResultSet tr) throws Exception {
        r.setIdReserva(tr.getInt("ID_RESERVA"));
        r.setIdCliente(tr.getInt("ID_CLIENTE"));
        r.setDataReserva(tr.getString("DATA_RESERVA"));
        r.setHorario(tr.getString("HORARIO"));
        r.setQntPessoas(tr.getInt("QNT_PESSOAS"));
        r.setStatus(tr.getInt("STATUS"));
        r.setNomeCliente(tr.getString("NOME_CLIENTE"));
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
