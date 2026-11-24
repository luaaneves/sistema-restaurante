package br.com.entidade;

import br.com.controle.Pedido;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.Timestamp;
import java.time.LocalDate;

    public class ManterPedido extends DAO {

    public void inserir(Pedido p) throws Exception {
        validar(p);
        try {
            abrirBanco();
            int idCliente = resolverIdCliente(p);
            int idFuncionario = resolverIdFuncionario(p);

            if (p.getStatus() == 1 && pedidoAberto(p.getIdMesa(), 0)) {
                throw new Exception("Já existe um pedido em aberto para esta mesa!");
            }

            String query = "INSERT INTO pedido (id_cliente, id_funcionario, id_mesa, id_reserva, tipo_consumo, status, data, observacao) "
                    + "VALUES (?,?,?,?,?,?,NOW(),?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, idCliente);
            pst.setInt(2, idFuncionario);
            pst.setInt(3, p.getIdMesa());
            if (p.getIdReserva() <= 0) {
                pst.setNull(4, Types.INTEGER);
            } else {
                pst.setInt(4, p.getIdReserva());
            }
            pst.setString(5, p.getTipoConsumo());
            pst.setInt(6, p.getStatus());
            pst.setString(7, p.getObservacao());
            pst.execute();
        } finally {
            fechar();
        }
    }
    
    public void alterarPedido(Pedido p) throws Exception {
   if (p.getIdPedido() <= 0) {
            throw new Exception("Selecione um pedido na tabela antes de alterar.");
        }
        validar(p);
        try {
            abrirBanco();
            int idCliente = resolverIdCliente(p);
            int idFuncionario = resolverIdFuncionario(p);

            if (p.getStatus() == 1 && pedidoAberto(p.getIdMesa(), p.getIdPedido())) {
                throw new Exception("Já existe outro pedido em aberto para esta mesa!");
            }

            String query = "UPDATE pedido SET id_cliente=?, id_funcionario=?, id_mesa=?, id_reserva=?, tipo_consumo=?, status=?, observacao=? "
                    + "WHERE id_pedido=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, idCliente);
            pst.setInt(2, idFuncionario);
            pst.setInt(3, p.getIdMesa());
            if (p.getIdReserva() <= 0) {
                pst.setNull(4, Types.INTEGER);
            } else {
                pst.setInt(4, p.getIdReserva());
            }
            pst.setString(5, p.getTipoConsumo());
            pst.setInt(6, p.getStatus());
            pst.setString(7, p.getObservacao());
            pst.setInt(8, p.getIdPedido());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public void deletarPedido(Pedido p) throws Exception {
        if (p.getIdPedido() <= 0) {
            throw new Exception("Selecione um pedido na tabela antes de excluir.");
        }
        try {
            abrirBanco();
            String verifica = "SELECT status FROM pedido WHERE id_pedido=?";
            try (PreparedStatement pstLocal = con.prepareStatement(verifica)) {
                pstLocal.setInt(1, p.getIdPedido());
                try (ResultSet tr = pstLocal.executeQuery()) {
                    if (tr.next() && tr.getInt("status") == 1) {
                        throw new Exception("Não é possível apagar um pedido que ainda está em aberto!");
                    }
                }
            }

            String query = "DELETE FROM pedido WHERE id_pedido=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, p.getIdPedido());
            pst.execute();
        } finally {
            fechar();
        }
    }

    public ArrayList<Pedido> PesquisarTudo() throws Exception {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT p.*, c.nome AS nome_cliente, f.nome AS nome_funcionario FROM pedido p "
                    + "INNER JOIN cliente c ON p.id_cliente = c.id_cliente "
                    + "INNER JOIN funcionario f ON p.id_funcionario = f.id_funcionario "
                    + "ORDER BY p.id_pedido DESC";
            pst = con.prepareStatement(query);
            try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    Pedido p = new Pedido();
                    preencher(p, tr);
                    pedidos.add(p);
                }
            }
        } finally {
            fechar();
        }
        return pedidos;
    }

    public ArrayList<Pedido> pesquisarPorFiltro(String busca) throws Exception {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            abrirBanco();
            String termo = busca.trim();
            String query = "SELECT p.*, c.nome AS nome_cliente, f.nome AS nome_funcionario FROM pedido p "
                    + "INNER JOIN cliente c ON p.id_cliente = c.id_cliente "
                    + "INNER JOIN funcionario f ON p.id_funcionario = f.id_funcionario "
                    + "WHERE CONCAT(p.id_pedido, '') = ? OR LOWER(c.nome) LIKE ? "
                    + "ORDER BY p.id_pedido DESC";
            pst = con.prepareStatement(query);
            pst.setString(1, termo);
            pst.setString(2, "%" + termo.toLowerCase() + "%");
            try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    Pedido p = new Pedido();
                    preencher(p, tr);
                    pedidos.add(p);
                }
            }
        } finally {
            fechar();
        }
        return pedidos;
    }     
    
    public ArrayList<Pedido> pesquisarPedidosPorCliente(String nomeCliente, LocalDate dataInicial,
    LocalDate dataFinal, Integer status, String consumo) throws Exception {
        if (dataInicial != null && dataFinal != null && dataInicial.isAfter(dataFinal)) {
            throw new IllegalArgumentException("A data inicial não pode ser posterior à data final.");
        }
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            abrirBanco();
        
            StringBuilder query = new StringBuilder(
            "SELECT p.*, c.nome AS nome_cliente, " +
            "f.nome AS nome_funcionario " +
            "FROM pedido p " +
            "INNER JOIN cliente c ON p.id_cliente = c.id_cliente " +
            "INNER JOIN funcionario f ON p.id_funcionario = f.id_funcionario " +
            "WHERE 1=1 "
        );

        ArrayList<Object> parametros = new ArrayList<>();

        if (nomeCliente != null && !nomeCliente.trim().isEmpty()) {
            query.append("AND c.nome LIKE ? ");
            parametros.add("%" + nomeCliente.trim() + "%");
        }

        if (dataInicial != null) {
            query.append("AND p.data >= ? ");
            parametros.add(Timestamp.valueOf(dataInicial.atStartOfDay()));
        }

        if (dataFinal != null) {
            query.append("AND p.data < ? ");
            parametros.add(Timestamp.valueOf(dataFinal.plusDays(1).atStartOfDay()));
        }

        if (status != null) {
            query.append("AND p.status = ? ");
            parametros.add(status);
            }

            if (consumo != null && !consumo.trim().isEmpty()) {
            query.append("AND p.tipo_consumo = ? ");
            parametros.add(consumo.trim());
            }

        query.append("ORDER BY p.data ASC");

        pst = con.prepareStatement(query.toString());

        for (int i = 0; i < parametros.size(); i++) {
            pst.setObject(i + 1, parametros.get(i));
        }

        try (ResultSet tr = pst.executeQuery()) {
                while (tr.next()) {
                    Pedido p = new Pedido();
                    preencher(p, tr);
                    pedidos.add(p);
                }
            }
        } finally {
            fechar();
        }

        return pedidos;
    }
    
    private void validar(Pedido p) throws Exception {
        if (p.getIdCliente() <= 0 && (p.getNomeCliente() == null || p.getNomeCliente().trim().isEmpty())) {
            throw new Exception("Informe o nome do cliente.");
        }
        if (p.getIdFuncionario() <= 0 && (p.getNomeFuncionario() == null || p.getNomeFuncionario().trim().isEmpty())) {
            throw new Exception("Informe o nome do funcionário.");
        }
        if (p.getIdMesa() <= 0) {
            throw new Exception("Selecione uma mesa válida.");
        }
        if (p.getTipoConsumo() == null || p.getTipoConsumo().trim().isEmpty()) {
            throw new Exception("Informe o tipo de consumo.");
        }
        if (p.getStatus() != 0 && p.getStatus() != 1) {
            throw new Exception("Status inválido. Informe 1 para aberto ou 0 para fechado.");
        }
    }

    private int resolverIdCliente(Pedido p) throws Exception {
        if (p.getIdCliente() > 0) {
            return p.getIdCliente();
        }
        String nome = p.getNomeCliente().trim().toLowerCase();
        int id = buscarIdPorNome("cliente", "id_cliente", nome, true);
        if (id == 0) {
            id = buscarIdPorNome("cliente", "id_cliente", nome, false);
        }
        if (id == 0) {
            throw new Exception("Cliente não encontrado! Verifique o nome digitado.");
        }
        return id;
    }

    private int resolverIdFuncionario(Pedido p) throws Exception {
        if (p.getIdFuncionario() > 0) {
            return p.getIdFuncionario();
        }
        String nome = p.getNomeFuncionario().trim().toLowerCase();
        int id = buscarIdPorNome("funcionario", "id_funcionario", nome, true);
        if (id == 0) {
            id = buscarIdPorNome("funcionario", "id_funcionario", nome, false);
        }
        if (id == 0) {
            throw new Exception("Funcionário não encontrado! Verifique o nome digitado.");
        }
        return id;
    }

    private int buscarIdPorNome(String tabela, String colunaId, String nomeLower, boolean exato) throws Exception {
        String sql = exato
                ? "SELECT " + colunaId + " FROM " + tabela + " WHERE LOWER(nome) = ?"
                : "SELECT " + colunaId + " FROM " + tabela + " WHERE LOWER(nome) LIKE ?";
        try (PreparedStatement pstLocal = con.prepareStatement(sql)) {
            pstLocal.setString(1, exato ? nomeLower : "%" + nomeLower + "%");
            try (ResultSet tr = pstLocal.executeQuery()) {
                return tr.next() ? tr.getInt(1) : 0;
            }
        }
    }

    private boolean pedidoAberto(int idMesa, int idPedidoIgnorar) throws Exception {
        String query = "SELECT id_pedido FROM pedido WHERE id_mesa=? AND status=1 AND id_pedido<>?";
        try (PreparedStatement pstLocal = con.prepareStatement(query)) {
            pstLocal.setInt(1, idMesa);
            pstLocal.setInt(2, idPedidoIgnorar);
            try (ResultSet tr = pstLocal.executeQuery()) {
                return tr.next();
            }
        }
    }

    private void preencher(Pedido p, ResultSet tr) throws Exception {
        p.setIdPedido(tr.getInt("id_pedido"));
        p.setIdCliente(tr.getInt("id_cliente"));
        p.setIdFuncionario(tr.getInt("id_funcionario"));
        p.setIdMesa(tr.getInt("id_mesa"));
        p.setIdReserva(tr.getInt("id_reserva"));
        p.setTipoConsumo(tr.getString("tipo_consumo"));
        p.setStatus(tr.getInt("status"));
        p.setData(tr.getString("data"));
        p.setObservacao(tr.getString("observacao"));
        p.setNomeCliente(tr.getString("nome_cliente"));
        p.setNomeFuncionario(tr.getString("nome_funcionario"));
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