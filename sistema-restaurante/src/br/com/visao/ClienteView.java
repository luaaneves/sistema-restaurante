package br.com.visao;

import br.com.controle.Cliente;
import br.com.entidade.ManterCliente;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ClienteView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ClienteView.class.getName());
    private final ManterCliente dao = new ManterCliente();
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    
    public ClienteView() {
        initComponents();
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Gerenciamento de Clientes");
        jTtabelaClientes.setDefaultEditor(Object.class, null);
        jTtabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        preencherTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLpesquisa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTcpf = new javax.swing.JTextField();
        jTcpf.putClientProperty("JTextField.placeholderText", "Digite o CPF do cliente...");
        jLabel4 = new javax.swing.JLabel();
        jTemail = new javax.swing.JTextField();
        jTemail.putClientProperty("JTextField.placeholderText", "Digite o email do cliente...");
        jLabel5 = new javax.swing.JLabel();
        jTtelefone = new javax.swing.JTextField();
        jTtelefone.putClientProperty("JTextField.placeholderText", "Digite o telefone do cliente... Ex.: (61) 98112-3456");
        jBsalvar = new javax.swing.JButton();
        jBalterar = new javax.swing.JButton();
        jBexcluir = new javax.swing.JButton();
        jBpesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTtabelaClientes = new javax.swing.JTable();
        jBlimpar = new javax.swing.JButton();
        jTconsulta = new javax.swing.JTextField();
        jTconsulta.putClientProperty("JTextField.placeholderText", "Digite o CPF do cliente...");
        jTnome = new javax.swing.JTextField();
        jTnome.putClientProperty("JTextField.placeholderText", "Digite o nome do cliente...");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLpesquisa.setText("Consultar:");

        jLabel2.setText("Nome Completo:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Email:");

        jLabel5.setText("Telefone:");

        jBsalvar.setText("Cadastrar");
        jBsalvar.addActionListener(this::jBsalvarActionPerformed);

        jBalterar.setText("Alterar");
        jBalterar.addActionListener(this::jBalterarActionPerformed);

        jBexcluir.setText("Deletar");
        jBexcluir.addActionListener(this::jBexcluirActionPerformed);

        jBpesquisar.setText("Consultar");
        jBpesquisar.addActionListener(this::jBpesquisarActionPerformed);

        jTtabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Email", "Telefone"
            }
        ));
        jTtabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTtabelaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTtabelaClientes);

        jBlimpar.setText("Limpar");
        jBlimpar.addActionListener(this::jBlimparActionPerformed);

        jTconsulta.setBackground(java.awt.SystemColor.controlHighlight);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLpesquisa)
                        .addGap(15, 15, 15)
                        .addComponent(jTconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBpesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(jBlimpar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTemail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(jTcpf, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTnome, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTtelefone)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jBsalvar)
                                .addGap(80, 80, 80)
                                .addComponent(jBalterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(jBexcluir)))
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLpesquisa)
                            .addComponent(jTconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBlimpar)
                            .addComponent(jBpesquisar))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTtelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBsalvar)
                            .addComponent(jBalterar)
                            .addComponent(jBexcluir)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atualizarTabela(ArrayList<Cliente> lista) {
        this.listaClientes = lista;
        DefaultTableModel modelo = (DefaultTableModel) jTtabelaClientes.getModel();
        modelo.setNumRows(0);

        for (Cliente c : listaClientes) {
            modelo.addRow(new Object[]{
                c.getIdCliente(),
                c.getNome(),
                c.getCpf(),
                c.getEmail(),
                c.getTelefone()
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

    private Cliente lerCampos() {
        Cliente c = new Cliente();
        c.setNome(jTnome.getText().trim());
        c.setCpf(jTcpf.getText().trim());
        c.setEmail(jTemail.getText().trim());
        c.setTelefone(jTtelefone.getText().trim());
        return c;
    }

    private void limparCampos() {
        jTconsulta.setText("");
        jTnome.setText("");
        jTcpf.setText("");
        jTemail.setText("");
        jTtelefone.setText("");
        jTtabelaClientes.clearSelection();
        jTnome.requestFocus();
    }

    private void mostrarErro(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
    
    private void jBsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalvarActionPerformed
        try {
            Cliente c = lerCampos();
            c.setDataCadastro(java.time.LocalDateTime.now()); 
            dao.inserir(c);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            limparCampos();
            preencherTabela();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBsalvarActionPerformed

    private void jBalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBalterarActionPerformed
        try {
            int linhaSelecionada = jTtabelaClientes.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um cliente na tabela antes de alterar.");
            }

            Cliente c = lerCampos();
            c.setIdCliente(listaClientes.get(linhaSelecionada).getIdCliente());
            dao.alterarCliente(c);

            JOptionPane.showMessageDialog(this, "Cliente alterado com sucesso!");
            limparCampos();
            preencherTabela();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBalterarActionPerformed

    private void jBpesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBpesquisarActionPerformed
        try {
            String busca = jTconsulta.getText().trim();
            if (busca.isEmpty()) {
                preencherTabela();
                return;
            }

            ArrayList<Cliente> encontrados = dao.pesquisarPorFiltro(busca);
            if (encontrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado para: " + busca);
                preencherTabela();
            } else {
                atualizarTabela(encontrados);
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBpesquisarActionPerformed

    private void jBexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBexcluirActionPerformed
        try {
            int linhaSelecionada = jTtabelaClientes.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um cliente na tabela antes de excluir.");
            }

            Cliente c = listaClientes.get(linhaSelecionada);
            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir o cliente \"" + c.getNome() + "\"?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                dao.deletarCliente(c);
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                limparCampos();
                preencherTabela();
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBexcluirActionPerformed

    private void jBlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimparActionPerformed
        limparCampos();
        preencherTabela();
    }//GEN-LAST:event_jBlimparActionPerformed

    private void jTtabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTtabelaClientesMouseClicked
        int linhaSelecionada = jTtabelaClientes.getSelectedRow();
        if (linhaSelecionada != -1) {
            Cliente c = listaClientes.get(linhaSelecionada);
            jTnome.setText(c.getNome());
            jTcpf.setText(c.getCpf());
            jTemail.setText(c.getEmail() != null ? c.getEmail() : "");
            jTtelefone.setText(c.getTelefone() != null ? c.getTelefone() : "");
        }
    }//GEN-LAST:event_jTtabelaClientesMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new ClienteView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBalterar;
    private javax.swing.JButton jBexcluir;
    private javax.swing.JButton jBlimpar;
    private javax.swing.JButton jBpesquisar;
    private javax.swing.JButton jBsalvar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLpesquisa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTconsulta;
    private javax.swing.JTextField jTcpf;
    private javax.swing.JTextField jTemail;
    private javax.swing.JTextField jTnome;
    private javax.swing.JTable jTtabelaClientes;
    private javax.swing.JTextField jTtelefone;
    // End of variables declaration//GEN-END:variables

}
