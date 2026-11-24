package br.com.visao;

import br.com.controle.Pedido;
import br.com.entidade.ManterPedido;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class RelatorioPedidos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RelatorioPedidos.class.getName());
    private final ManterPedido dao = new ManterPedido();
    
    public RelatorioPedidos() {
        initComponents();
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Relatório de Pedidos");
        jTpedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        preencherTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBfiltrar = new javax.swing.JButton();
        jTdataInicial = new javax.swing.JTextField();
        jTdataInicial.putClientProperty("JTextField.placeholderText", "DD/MM/YYYY");
        jTdataFinal = new javax.swing.JTextField();
        jTdataFinal.putClientProperty("JTextField.placeholderText", "DD/MM/YYYY");
        jTnomeCliente = new javax.swing.JTextField();
        jTnomeCliente.putClientProperty("JTextField.placeholderText", "Digite o nome do cliente...");
        JScrollPane1 = new javax.swing.JScrollPane();
        jTpedidos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jBlimpar = new javax.swing.JButton();
        jCstatus = new javax.swing.JComboBox<>();
        jCconsumo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome do Cliente:");

        jLabel2.setText("Data Inicial:");

        jLabel3.setText("Data Final:");

        jBfiltrar.setBackground(new java.awt.Color(153, 153, 255));
        jBfiltrar.setForeground(new java.awt.Color(255, 255, 255));
        jBfiltrar.setText("Filtrar");
        jBfiltrar.setPreferredSize(new java.awt.Dimension(80, 25));
        jBfiltrar.addActionListener(this::jBfiltrarActionPerformed);

        jTdataInicial.addActionListener(this::jTdataInicialActionPerformed);

        jTdataFinal.addActionListener(this::jTdataFinalActionPerformed);

        jTpedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Pedido", "Cliente", "Funcionário", "Tipo", "Status", "Data", "Observação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JScrollPane1.setViewportView(jTpedidos);
        if (jTpedidos.getColumnModel().getColumnCount() > 0) {
            jTpedidos.getColumnModel().getColumn(0).setResizable(false);
            jTpedidos.getColumnModel().getColumn(1).setResizable(false);
            jTpedidos.getColumnModel().getColumn(2).setResizable(false);
            jTpedidos.getColumnModel().getColumn(3).setResizable(false);
            jTpedidos.getColumnModel().getColumn(4).setResizable(false);
            jTpedidos.getColumnModel().getColumn(5).setResizable(false);
            jTpedidos.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel4.setText("Filtros:");

        jLabel5.setText("Status:");

        jLabel6.setText("Consumo:");

        jBlimpar.setText("Limpar");
        jBlimpar.setPreferredSize(new java.awt.Dimension(80, 25));
        jBlimpar.addActionListener(this::jBlimparActionPerformed);

        jCstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Em andamento", "Finalizado" }));

        jCconsumo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Local", "Retirada" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jBfiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTnomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTdataFinal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTdataInicial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCconsumo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jBlimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTnomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTdataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jCstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTdataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jCconsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBfiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBlimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void atualizarTabela(ArrayList<Pedido> lista) {
        DefaultTableModel modelo = (DefaultTableModel) jTpedidos.getModel();
        modelo.setNumRows(0);

        for (Pedido p : lista) {
            modelo.addRow(new Object[]{
                p.getIdPedido(),
                p.getNomeCliente(),
                p.getNomeFuncionario(),
                p.getTipoConsumo(),
                p.getStatus() == 1 ? "Em andamento" : "Finalizado",
                p.getData(),
                p.getObservacao()
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

    private void mostrarErro(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
    
    private void jBfiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBfiltrarActionPerformed
        try {
        String nome = jTnomeCliente.getText().trim();
        String dataInicial = jTdataInicial.getText().trim();
        String dataFinal = jTdataFinal.getText().trim();
        Integer status = null;
        if (jCstatus.getSelectedIndex() == 1) {
            status = 1;
        } else if (jCstatus.getSelectedIndex() == 2) {
            status = 0;
        }

        String consumo = jCconsumo.getSelectedIndex() > 0
                ? jCconsumo.getSelectedItem().toString()
                : null;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate inicio = null;
        LocalDate fim = null;

        if (!dataInicial.isEmpty()) {
            inicio = LocalDate.parse(dataInicial, formato);
        }

        if (!dataFinal.isEmpty()) {
            fim = LocalDate.parse(dataFinal, formato);
        }

        ManterPedido mp = new ManterPedido();
        ArrayList<Pedido> lista = mp.pesquisarPedidosPorCliente(nome, inicio, fim, status, consumo);

        DefaultTableModel modelo = (DefaultTableModel) jTpedidos.getModel();
        modelo.setRowCount(0);

        for (Pedido p : lista) {

            modelo.addRow(new Object[]{
                p.getIdPedido(),
                p.getNomeCliente(),
                p.getNomeFuncionario(),
                p.getTipoConsumo(),
                p.getStatus() == 1 ? "Em andamento" : "Finalizado",
                p.getData(),
                p.getObservacao()
            });
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(
                null, "Nenhum pedido encontrado para os filtros informados."
            );
        }

    } catch (DateTimeParseException e) {

        JOptionPane.showMessageDialog(
            null, "Informe as datas no formato DD/MM/AAAA."
        );

    } catch (IllegalArgumentException e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            null,
            "Erro ao pesquisar pedidos: " + e.getMessage()
        );
    }
    }//GEN-LAST:event_jBfiltrarActionPerformed

    private void jTdataFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTdataFinalActionPerformed
       
    }//GEN-LAST:event_jTdataFinalActionPerformed

    private void jBlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimparActionPerformed
       jTnomeCliente.setText("");
        jTdataInicial.setText("");
        jTdataFinal.setText("");
        jCstatus.setSelectedIndex(0);
        jCconsumo.setSelectedIndex(0);
        preencherTabela();
    }//GEN-LAST:event_jBlimparActionPerformed

    private void jTdataInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTdataInicialActionPerformed

    }//GEN-LAST:event_jTdataInicialActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new RelatorioPedidos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane1;
    private javax.swing.JButton jBfiltrar;
    private javax.swing.JButton jBlimpar;
    private javax.swing.JComboBox<String> jCconsumo;
    private javax.swing.JComboBox<String> jCstatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTdataFinal;
    private javax.swing.JTextField jTdataInicial;
    private javax.swing.JTextField jTnomeCliente;
    private javax.swing.JTable jTpedidos;
    // End of variables declaration//GEN-END:variables
}
