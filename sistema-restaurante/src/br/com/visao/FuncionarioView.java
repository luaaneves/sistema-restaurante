package br.com.visao;

import br.com.controle.Cargo;
import br.com.controle.Funcionario;
import br.com.entidade.ManterCargo;
import br.com.entidade.ManterFuncionario;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class FuncionarioView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FuncionarioView.class.getName());
    private final ManterFuncionario dao = new ManterFuncionario();
    private final ManterCargo daoCargo = new ManterCargo();
    private ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    private ArrayList<Cargo> listaCargosCombo = new ArrayList<>();
    
    public FuncionarioView() {
        initComponents();
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Gerenciamento de Funcionários");
        jTfuncionarios.setDefaultEditor(Object.class, null);
        jTfuncionarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        preencherCargos();
        preencherTabela();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTnome = new javax.swing.JTextField();
        jTnome.putClientProperty("JTextField.placeholderText", "Digite o nome do funcionário...");
        jTcpf = new javax.swing.JTextField();
        jTcpf.putClientProperty("JTextField.placeholderText", "Digite o CPF do funcionário...Ex.: 222.333.444-98");
        jTemail = new javax.swing.JTextField();
        jTemail.putClientProperty("JTextField.placeholderText", "Digite o email do funcionario...");
        jCcargo = new javax.swing.JComboBox<>();
        jTconsultar = new javax.swing.JTextField();
        jTconsultar.putClientProperty("JTextField.placeholderText", "Digite o CPF do funcionário...");
        jBconsultar = new javax.swing.JButton();
        jBlimpar = new javax.swing.JButton();
        jBalterar = new javax.swing.JButton();
        jBcadastrar = new javax.swing.JButton();
        jBdeletar = new javax.swing.JButton();
        jTtelefone = new javax.swing.JTextField();
        jTtelefone.putClientProperty("JTextField.placeholderText", "Digite o telefone do funcionário... Ex.: (61)98765-3456");
        jScrollPane1 = new javax.swing.JScrollPane();
        jTfuncionarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Consultar:");

        jLabel.setText("Nome:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Email:");

        jLabel2.setText("Telefone:");

        jLabel5.setText("Cargo:");

        jCcargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTconsultar.setBackground(java.awt.SystemColor.controlHighlight);

        jBconsultar.setText("Consultar");
        jBconsultar.addActionListener(this::jBconsultarActionPerformed);

        jBlimpar.setText("Limpar");
        jBlimpar.addActionListener(this::jBlimparActionPerformed);

        jBalterar.setText("Alterar");
        jBalterar.addActionListener(this::jBalterarActionPerformed);

        jBcadastrar.setText("Cadastrar");
        jBcadastrar.addActionListener(this::jBcadastrarActionPerformed);

        jBdeletar.setText("Deletar");
        jBdeletar.addActionListener(this::jBdeletarActionPerformed);

        jTfuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Email", "Telefone", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTfuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTfuncionariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTfuncionarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(2, 2, 2)
                        .addComponent(jTconsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBconsultar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBlimpar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBcadastrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBalterar)
                                .addGap(61, 61, 61)
                                .addComponent(jBdeletar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTtelefone)
                                    .addComponent(jCcargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTemail, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTcpf)
                                    .addComponent(jTnome))))
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTconsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBlimpar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBconsultar))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel)
                            .addComponent(jTnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTtelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jCcargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBalterar)
                            .addComponent(jBdeletar)
                            .addComponent(jBcadastrar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atualizarTabela(ArrayList<Funcionario> lista) {
        this.listaFuncionarios = lista;
        DefaultTableModel modelo = (DefaultTableModel) jTfuncionarios.getModel();
        modelo.setNumRows(0);

        for (Funcionario f : listaFuncionarios) {
            modelo.addRow(new Object[]{
                f.getIdFuncionario(),
                f.getNome(),
                f.getCpf(),
                f.getEmail(),
                f.getTelefone(),
                f.getNomeCargo() != null ? f.getNomeCargo() : "Sem Cargo"
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

    public void preencherCargos() {
        try {
            listaCargosCombo = daoCargo.PesquisarTudo();
            jCcargo.removeAllItems();
            for (Cargo c : listaCargosCombo) {
                jCcargo.addItem(c.getNomeCargo());
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }

    private Funcionario lerCampos() throws Exception {
        int idxCargo = jCcargo.getSelectedIndex();
        if (idxCargo < 0 || idxCargo >= listaCargosCombo.size()) {
            throw new Exception("Selecione um cargo válido.");
        }

        Funcionario f = new Funcionario();
        f.setNome(jTnome.getText().trim());
        f.setCpf(jTcpf.getText().trim());
        f.setEmail(jTemail.getText().trim());
        f.setTelefone(jTtelefone.getText().trim());
        f.setIdCargo(listaCargosCombo.get(idxCargo).getIdCargo());
        return f;
    }

    private void limparCampos() {
        jTconsultar.setText("");
        jTnome.setText("");
        jTcpf.setText("");
        jTemail.setText("");
        jTtelefone.setText("");
        if (jCcargo.getItemCount() > 0) {
            jCcargo.setSelectedIndex(0);
        }
        jTfuncionarios.clearSelection();
        jTnome.requestFocus();
    }

    private void mostrarErro(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
    
    private void jBcadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcadastrarActionPerformed
       try {
            Funcionario f = lerCampos();
            f.setDataAdmissao(LocalDateTime.now());
            dao.inserir(f);

            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
            limparCampos();
            preencherTabela();
        } catch (Exception e) {
            mostrarErro(e);
        } 
    }//GEN-LAST:event_jBcadastrarActionPerformed

    private void jBalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBalterarActionPerformed
        try {
            int linhaSelecionada = jTfuncionarios.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um funcionário na tabela antes de alterar.");
            }

            Funcionario f = lerCampos();
            f.setIdFuncionario(listaFuncionarios.get(linhaSelecionada).getIdFuncionario());
            dao.alterarFuncionario(f);

            JOptionPane.showMessageDialog(this, "Funcionário alterado com sucesso!");
            limparCampos();
            preencherTabela();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBalterarActionPerformed

    private void jBdeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdeletarActionPerformed
        try {
            int linhaSelecionada = jTfuncionarios.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um funcionário na tabela antes de excluir.");
            }

            Funcionario f = listaFuncionarios.get(linhaSelecionada);
            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir o funcionário \"" + f.getNome() + "\"?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                dao.deletarFuncionario(f);
                JOptionPane.showMessageDialog(this, "Funcionário excluído com sucesso!");
                limparCampos();
                preencherTabela();
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBdeletarActionPerformed

    private void jBconsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBconsultarActionPerformed
        try {
            String busca = jTconsultar.getText().trim();
            if (busca.isEmpty()) {
                preencherTabela();
                return;
            }

            ArrayList<Funcionario> encontrados = dao.pesquisarPorFiltro(busca);
            if (encontrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum funcionário encontrado para: " + busca);
                preencherTabela();
            } else {
                atualizarTabela(encontrados);
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBconsultarActionPerformed

    private void jBlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimparActionPerformed
        limparCampos();
        preencherTabela();
    }//GEN-LAST:event_jBlimparActionPerformed

    private void jTfuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTfuncionariosMouseClicked
        int linhaSelecionada = jTfuncionarios.getSelectedRow();
        if (linhaSelecionada != -1) {
            Funcionario f = listaFuncionarios.get(linhaSelecionada);
            jTnome.setText(f.getNome());
            jTcpf.setText(f.getCpf());
            jTemail.setText(f.getEmail() != null ? f.getEmail() : "");
            jTtelefone.setText(f.getTelefone() != null ? f.getTelefone() : "");
            if (f.getNomeCargo() != null) {
                jCcargo.setSelectedItem(f.getNomeCargo());
            }
        }
    }//GEN-LAST:event_jTfuncionariosMouseClicked
   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FuncionarioView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBalterar;
    private javax.swing.JButton jBcadastrar;
    private javax.swing.JButton jBconsultar;
    private javax.swing.JButton jBdeletar;
    private javax.swing.JButton jBlimpar;
    private javax.swing.JComboBox<String> jCcargo;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTconsultar;
    private javax.swing.JTextField jTcpf;
    private javax.swing.JTextField jTemail;
    private javax.swing.JTable jTfuncionarios;
    private javax.swing.JTextField jTnome;
    private javax.swing.JTextField jTtelefone;
    // End of variables declaration//GEN-END:variables
}
