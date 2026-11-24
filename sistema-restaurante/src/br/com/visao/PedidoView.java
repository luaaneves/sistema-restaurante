package br.com.visao;

import br.com.controle.Mesa;
import br.com.controle.Pedido;
import br.com.controle.Reserva;
import br.com.entidade.ManterMesa;
import br.com.entidade.ManterPedido;
import br.com.entidade.ManterReserva;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PedidoView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PedidoView.class.getName());
    
    private final ManterPedido dao = new ManterPedido();
    private final ManterMesa daoMesa = new ManterMesa();
    private final ManterReserva daoReserva = new ManterReserva();

    private ArrayList<Pedido> listaPedidos = new ArrayList<>();
    private ArrayList<Mesa> listaMesasCombo = new ArrayList<>();
    private ArrayList<Reserva> listaReservasCombo = new ArrayList<>();
    
    public PedidoView() {
        initComponents();
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Gerenciamento de Pedidos");
        jTtabelaPedidos.setDefaultEditor(Object.class, null);
        jTtabelaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carregarCombos();
        preencherTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLconsultar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTconsultar = new javax.swing.JTextField();
        jTconsultar.putClientProperty("JTextField.placeholderText", "Digite o Id do pedido ou nome do cliente...");
        jTcliente = new javax.swing.JTextField();
        jTcliente.putClientProperty("JTextField.placeholderText", "Digite o nome do cliente...");
        jTfuncionario = new javax.swing.JTextField();
        jTfuncionario.putClientProperty("JTextField.placeholderText", "Digite o nome do funcionário...");
        jTobservacao = new javax.swing.JTextField();
        jTobservacao.putClientProperty("JTextField.placeholderText", "Ex.: Sem cebola.");
        jBlimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTtabelaPedidos = new javax.swing.JTable();
        jBsalvar = new javax.swing.JButton();
        jBalterar = new javax.swing.JButton();
        jBexcluir = new javax.swing.JButton();
        jBpesquisar = new javax.swing.JButton();
        jCmesa = new javax.swing.JComboBox<>();
        jCreserva = new javax.swing.JComboBox<>();
        jCtipoconsumo = new javax.swing.JComboBox<>();
        jCstatus = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLconsultar.setText("Consultar:");

        jLabel2.setText("Nome do Cliente:");

        jLabel3.setText("Nome do Funcionário:");

        jLabel4.setText("Mesa:");

        jLabel5.setText("Reserva:");

        jLabel6.setText("Tipo de Consumo:");

        jLabel7.setText("Status :");

        jLabel8.setText("Observação:");

        jTconsultar.setBackground(java.awt.SystemColor.controlHighlight);

        jBlimpar.setText("Limpar");
        jBlimpar.addActionListener(this::jBlimparActionPerformed);

        jTtabelaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mesa", "Cliente", "Funcionário", "Consumo", "Status", "Data"
            }
        ));
        jTtabelaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTtabelaPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTtabelaPedidos);

        jBsalvar.setText("Cadastrar");
        jBsalvar.addActionListener(this::jBsalvarActionPerformed);

        jBalterar.setText("Alterar");
        jBalterar.addActionListener(this::jBalterarActionPerformed);

        jBexcluir.setText("Deletar");
        jBexcluir.addActionListener(this::jBexcluirActionPerformed);

        jBpesquisar.setText("Consultar");
        jBpesquisar.addActionListener(this::jBpesquisarActionPerformed);

        jCmesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCreserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCtipoconsumo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBsalvar)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLconsultar)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBalterar)
                                        .addGap(78, 78, 78)
                                        .addComponent(jBexcluir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTobservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jCstatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jCtipoconsumo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jCreserva, javax.swing.GroupLayout.Alignment.LEADING, 0, 231, Short.MAX_VALUE)
                                                .addComponent(jCmesa, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTfuncionario, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTcliente, javax.swing.GroupLayout.Alignment.LEADING)))
                                        .addGap(19, 19, 19)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTconsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBpesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBlimpar)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLconsultar)
                    .addComponent(jTconsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBpesquisar)
                    .addComponent(jBlimpar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTfuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jCmesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCreserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jCtipoconsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jCstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTobservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBsalvar)
                            .addComponent(jBalterar)
                            .addComponent(jBexcluir)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void carregarCombos() {
        try {
            listaMesasCombo = daoMesa.PesquisarTudo();
            jCmesa.removeAllItems();
            for (Mesa m : listaMesasCombo) {
                jCmesa.addItem(m.getIdMesa() + " - Mesa " + m.getNumero());
            }

            listaReservasCombo = daoReserva.PesquisarTudo();
            jCreserva.removeAllItems();
            jCreserva.addItem("0 - Sem reserva");
            for (Reserva r : listaReservasCombo) {
                jCreserva.addItem(r.getIdReserva() + " - " + r.getNomeCliente()
                        + " (" + ManterReserva.dataParaTela(r.getDataReserva()) + ")");
            }
            
            jCtipoconsumo.removeAllItems();
            jCtipoconsumo.addItem("Local");
            jCtipoconsumo.addItem("Retirada");
            
            jCstatus.removeAllItems();
            jCstatus.addItem("Em andamento");
            jCstatus.addItem("Finalizado");  
        } catch (Exception e) {
            mostrarErro(e);
        }
    }

    private void atualizarTabela(ArrayList<Pedido> lista) {
        this.listaPedidos = lista;
        DefaultTableModel modelo = (DefaultTableModel) jTtabelaPedidos.getModel();
        modelo.setNumRows(0);

        for (Pedido p : listaPedidos) {
            modelo.addRow(new Object[]{
                p.getIdPedido(),
                p.getIdMesa(),
                p.getNomeCliente(),
                p.getNomeFuncionario(),
                p.getTipoConsumo(),
                p.getStatusTexto(),
                p.getData()
            });
        }
    }

    public void preencherTabela() {
        try {
            atualizarTabela(dao.PesquisarTudo());
        } catch (Exception e) {
            mostrarErro(e);
        }
    }

    private Pedido lerCampos() throws Exception {
        int idxMesa = jCmesa.getSelectedIndex();
        if (idxMesa < 0 || idxMesa >= listaMesasCombo.size()) {
            throw new Exception("Selecione uma mesa válida.");
        }

        int idxReserva = jCreserva.getSelectedIndex();
        int idReserva = (idxReserva > 0 && idxReserva <= listaReservasCombo.size())
                ? listaReservasCombo.get(idxReserva - 1).getIdReserva()
                : 0;

        if (jCtipoconsumo.getSelectedItem() == null) {
            throw new Exception("Selecione o tipo de consumo.");
        }
        
        int status = (jCstatus.getSelectedIndex() == 0) ? 1 : 0;

        Pedido p = new Pedido();
        p.setNomeCliente(jTcliente.getText().trim());
        p.setNomeFuncionario(jTfuncionario.getText().trim());
        p.setIdMesa(listaMesasCombo.get(idxMesa).getIdMesa());
        p.setIdReserva(idReserva);
        p.setTipoConsumo(jCtipoconsumo.getSelectedItem().toString());
        p.setStatus(status);
        p.setObservacao(jTobservacao.getText().trim());
        return p;
    }

    private void selecionarMesa(int idMesa) {
        for (int i = 0; i < listaMesasCombo.size(); i++) {
            if (listaMesasCombo.get(i).getIdMesa() == idMesa) {
                jCmesa.setSelectedIndex(i);
                return;
            }
        }
    }

    private void selecionarReserva(int idReserva) {
        if (idReserva <= 0) {
            jCreserva.setSelectedIndex(0);
            return;
        }
        for (int i = 0; i < listaReservasCombo.size(); i++) {
            if (listaReservasCombo.get(i).getIdReserva() == idReserva) {
                jCreserva.setSelectedIndex(i + 1);
                return;
            }
        }
    }

    private void limparCampos() {
        jTconsultar.setText("");
        jTcliente.setText("");
        jTfuncionario.setText("");
        if (jCmesa.getItemCount() > 0) {
            jCmesa.setSelectedIndex(0);
        }
        if (jCreserva.getItemCount() > 0) {
            jCreserva.setSelectedIndex(0);
        }
        if (jCtipoconsumo.getItemCount() > 0) {
            jCtipoconsumo.setSelectedIndex(0);
        }
        if (jCstatus.getItemCount() > 0) {
            jCstatus.setSelectedIndex(0);
        }
        jTobservacao.setText("");
        jTtabelaPedidos.clearSelection();
        jTcliente.requestFocus();
    }

    private void mostrarErro(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
    
    private void jBsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalvarActionPerformed
        try {
            Pedido p = lerCampos();
            dao.inserir(p);

            JOptionPane.showMessageDialog(this, "Pedido cadastrado com sucesso!");
            limparCampos();
            preencherTabela();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBsalvarActionPerformed

    private void jBexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBexcluirActionPerformed
       try {
            int linhaSelecionada = jTtabelaPedidos.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um pedido na tabela antes de excluir.");
            }

            Pedido p = listaPedidos.get(linhaSelecionada);
            if (p.getStatus() == 1) {
                throw new Exception("Não é possível apagar um pedido que ainda está em andamento.");
            }

            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja excluir o pedido nº " + p.getIdPedido() + "?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                dao.deletarPedido(p);
                JOptionPane.showMessageDialog(this, "Pedido excluído com sucesso!");
                limparCampos();
                preencherTabela();
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBexcluirActionPerformed

    private void jBalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBalterarActionPerformed
try {
            int linhaSelecionada = jTtabelaPedidos.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um pedido na tabela antes de alterar.");
            }

            Pedido p = lerCampos();
            p.setIdPedido(listaPedidos.get(linhaSelecionada).getIdPedido());
            dao.alterarPedido(p);

            JOptionPane.showMessageDialog(this, "Pedido alterado com sucesso!");
            limparCampos();
            preencherTabela();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBalterarActionPerformed

    private void jBpesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBpesquisarActionPerformed
        try {
            String busca = jTconsultar.getText().trim();
            if (busca.isEmpty()) {
                preencherTabela();
                return;
            }

            ArrayList<Pedido> encontrados = dao.pesquisarPorFiltro(busca);
            if (encontrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum pedido encontrado para: " + busca);
                preencherTabela();
            } else {
                atualizarTabela(encontrados);
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBpesquisarActionPerformed

    private void jBlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimparActionPerformed
        limparCampos();
        preencherTabela();
    }//GEN-LAST:event_jBlimparActionPerformed

    private void jTtabelaPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtabelaPedidosMouseClicked
        int linhaSelecionada = jTtabelaPedidos.getSelectedRow();
        if (linhaSelecionada != -1) {
            Pedido p = listaPedidos.get(linhaSelecionada);
            jTcliente.setText(p.getNomeCliente() != null ? p.getNomeCliente() : "");
            jTfuncionario.setText(p.getNomeFuncionario() != null ? p.getNomeFuncionario() : "");
            selecionarMesa(p.getIdMesa());
            selecionarReserva(p.getIdReserva());
            if (p.getTipoConsumo() != null) {
            jCtipoconsumo.setSelectedItem(p.getTipoConsumo());
            }
            jCstatus.setSelectedIndex(p.getStatus() == 1 ? 0 : 1);
            jTobservacao.setText(p.getObservacao() != null ? p.getObservacao() : "");
        }
    }//GEN-LAST:event_jTtabelaPedidosMouseClicked
  
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new PedidoView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBalterar;
    private javax.swing.JButton jBexcluir;
    private javax.swing.JButton jBlimpar;
    private javax.swing.JButton jBpesquisar;
    private javax.swing.JButton jBsalvar;
    private javax.swing.JComboBox<String> jCmesa;
    private javax.swing.JComboBox<String> jCreserva;
    private javax.swing.JComboBox<String> jCstatus;
    private javax.swing.JComboBox<String> jCtipoconsumo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLconsultar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTcliente;
    private javax.swing.JTextField jTconsultar;
    private javax.swing.JTextField jTfuncionario;
    private javax.swing.JTextField jTobservacao;
    private javax.swing.JTable jTtabelaPedidos;
    // End of variables declaration//GEN-END:variables
}
