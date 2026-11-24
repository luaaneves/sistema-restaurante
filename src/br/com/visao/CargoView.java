package br.com.visao;

import br.com.controle.Cargo;
import br.com.entidade.ManterCargo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CargoView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CargoView.class.getName());
    private final ManterCargo dao = new ManterCargo();
    private ArrayList<Cargo> listaCargos = new ArrayList<>();

    public CargoView() {
        initComponents();
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Gerenciamento de Cargos");
        jTcargos.setDefaultEditor(Object.class, null);
        jTcargos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarCargos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTcarga = new javax.swing.JTextField();
        jTcarga.putClientProperty("JTextField.placeholderText", "Ex.: 44");
        jCstatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTcargos = new javax.swing.JTable();
        jBsalvar = new javax.swing.JButton();
        jBexcluir = new javax.swing.JButton();
        jBeditar = new javax.swing.JButton();
        jLnome = new javax.swing.JLabel();
        jLsalario = new javax.swing.JLabel();
        jLcarga = new javax.swing.JLabel();
        jLdescricao = new javax.swing.JLabel();
        jLpesquisa = new javax.swing.JLabel();
        jTpesquisa = new javax.swing.JTextField();
        jTpesquisa.putClientProperty("JTextField.placeholderText", "Digite o ID ou nome do cargo...");
        jBpesquisa = new javax.swing.JButton();
        jBlimpar = new javax.swing.JButton();
        jTdescricao = new javax.swing.JTextField();
        jTdescricao.putClientProperty("JTextField.placeholderText", "Ex.: Atendimento no salão principal.");
        jTsalario = new javax.swing.JTextField();
        jTsalario.putClientProperty("JTextField.placeholderText", "Ex.: 2500.00");
        jTnome = new javax.swing.JTextField();
        jTnome.putClientProperty("JTextField.placeholderText", "Digite um nome para o cargo... ex.: Garçom.");
        jLstatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        jCstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        jCstatus.addActionListener(this::jCstatusActionPerformed);

        jTcargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cargo", "Salário", "Carga Horária", "Descrição", "Atividade"
            }
        ));
        jTcargos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTcargosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTcargos);

        jBsalvar.setText("Cadastrar");
        jBsalvar.addActionListener(this::jBsalvarActionPerformed);

        jBexcluir.setText("Deletar");
        jBexcluir.addActionListener(this::jBexcluirActionPerformed);

        jBeditar.setText("Alterar");
        jBeditar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBeditar.addActionListener(this::jBeditarActionPerformed);

        jLnome.setText("Nome:");

        jLsalario.setText("Salário:");

        jLcarga.setText("Carga Horária:");

        jLdescricao.setText("Descrição:");

        jLpesquisa.setText("Consultar:");

        jTpesquisa.setBackground(java.awt.SystemColor.controlHighlight);

        jBpesquisa.setText("Consultar");
        jBpesquisa.addActionListener(this::jBpesquisaActionPerformed);

        jBlimpar.setText("Limpar");
        jBlimpar.addActionListener(this::jBlimparActionPerformed);

        jLstatus.setText("Status:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLpesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jTpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLsalario, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addComponent(jLnome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLcarga, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTsalario)
                            .addComponent(jTnome)
                            .addComponent(jTcarga)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLstatus)
                            .addComponent(jBsalvar))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTdescricao)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jBeditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBexcluir)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBpesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBlimpar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLpesquisa)
                    .addComponent(jTpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBpesquisa)
                    .addComponent(jBlimpar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLnome)
                            .addComponent(jTnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLsalario)
                            .addComponent(jTsalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLcarga)
                            .addComponent(jTcarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLdescricao)
                            .addComponent(jTdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLstatus))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBsalvar)
                            .addComponent(jBexcluir)
                            .addComponent(jBeditar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void atualizarTabela(ArrayList<Cargo> Lista){
        this.listaCargos = Lista;
        DefaultTableModel modelo = (DefaultTableModel) jTcargos.getModel();
        modelo.setNumRows(0);
        for(Cargo c : listaCargos){
            modelo.addRow(new Object[]{
            c.getIdCargo(),
            c.getNomeCargo(),
            c.getSalario(),
            c.getCargaHoraria(),
            c.getDescricao(),
            c.getAtivoTexto()
        });
    }
    }
    public void listarCargos() {
        try {
            atualizarTabela(dao.PesquisarTudo());
        } catch (Exception e) {
            mostrarErro(e);
        }
    }
    
    public Cargo lerCampos() throws Exception {
        Cargo c = new Cargo();
        c.setNomeCargo(jTnome.getText().trim());

        try {
            String salarioTexto = jTsalario.getText().trim().replace(",", ".");
            c.setSalario(Double.parseDouble(salarioTexto));
        } catch (NumberFormatException ex) {
            throw new Exception("Informe um valor numérico válido para o salário.");
        }

        try {
            c.setCargaHoraria(Integer.parseInt(jTcarga.getText().trim()));
        } catch (NumberFormatException ex) {
            throw new Exception("A carga horária deve ser um número inteiro.");
        }

        c.setDescricao(jTdescricao.getText().trim());
        c.setAtivo(jCstatus.getSelectedIndex() == 0 ? 1 : 0); // 0 = Ativo (1), 1 = Inativo (0)
        return c;
    }
    
    private void limparCampos() {
        jTpesquisa.setText("");
        jTnome.setText("");
        jTsalario.setText("");
        jTcarga.setText("");
        jTdescricao.setText("");
        jCstatus.setSelectedIndex(0);
        jTcargos.clearSelection();
        jTnome.requestFocus();
    }
    
    private void mostrarErro(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
    
    private void jBsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalvarActionPerformed
        try {
            Cargo c = lerCampos();
            dao.inserir(c);
            JOptionPane.showMessageDialog(this, "Cargo cadastrado com sucesso!");
            limparCampos();
            listarCargos();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBsalvarActionPerformed

    private void jBexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBexcluirActionPerformed
        try {
            int linhaSelecionada = jTcargos.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um cargo na tabela antes de excluir.");
            }

            Cargo c = listaCargos.get(linhaSelecionada);
            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir o cargo \"" + c.getNomeCargo() + "\"?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                dao.deletarCargo(c);
                JOptionPane.showMessageDialog(this, "Cargo excluído com sucesso!");
                limparCampos();
                listarCargos();
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBexcluirActionPerformed

    private void jBeditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBeditarActionPerformed
        try {
            int linhaSelecionada = jTcargos.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um cargo na tabela antes de alterar.");
            }

            Cargo c = lerCampos();
            c.setIdCargo(listaCargos.get(linhaSelecionada).getIdCargo());
            dao.alterarCargo(c);

            JOptionPane.showMessageDialog(this, "Cargo alterado com sucesso!");
            limparCampos();
            listarCargos();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBeditarActionPerformed

    private void jCstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCstatusActionPerformed
    }//GEN-LAST:event_jCstatusActionPerformed

    private void jTcargosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTcargosMouseClicked
    int linhaSelecionada = jTcargos.getSelectedRow();
        if (linhaSelecionada != -1) {
            Cargo c = listaCargos.get(linhaSelecionada);
            jTnome.setText(c.getNomeCargo());
            jTsalario.setText(String.valueOf(c.getSalario()));
            jTcarga.setText(String.valueOf(c.getCargaHoraria()));
            jTdescricao.setText(c.getDescricao() != null ? c.getDescricao() : "");
            jCstatus.setSelectedItem(c.getAtivoTexto());
        }
    }//GEN-LAST:event_jTcargosMouseClicked

    private void jBpesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBpesquisaActionPerformed
        try {
            String busca = jTpesquisa.getText().trim();
            if (busca.isEmpty()) {
                listarCargos();
                return;
            }

            ArrayList<Cargo> encontrados = dao.pesquisarPorFiltro(busca);
            if (encontrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum cargo encontrado para: " + busca);
                listarCargos();
            } else {
                atualizarTabela(encontrados);
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBpesquisaActionPerformed

    private void jBlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimparActionPerformed
        limparCampos();
        listarCargos();
    }//GEN-LAST:event_jBlimparActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new CargoView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBeditar;
    private javax.swing.JButton jBexcluir;
    private javax.swing.JButton jBlimpar;
    private javax.swing.JButton jBpesquisa;
    private javax.swing.JButton jBsalvar;
    private javax.swing.JComboBox<String> jCstatus;
    private javax.swing.JLabel jLcarga;
    private javax.swing.JLabel jLdescricao;
    private javax.swing.JLabel jLnome;
    private javax.swing.JLabel jLpesquisa;
    private javax.swing.JLabel jLsalario;
    private javax.swing.JLabel jLstatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTcarga;
    private javax.swing.JTable jTcargos;
    private javax.swing.JTextField jTdescricao;
    private javax.swing.JTextField jTnome;
    private javax.swing.JTextField jTpesquisa;
    private javax.swing.JTextField jTsalario;
    // End of variables declaration//GEN-END:variables
}
