package br.com.visao;

import br.com.controle.Mesa;
import br.com.controle.MesaReserva;
import br.com.controle.Reserva;
import br.com.entidade.ManterMesa;
import br.com.entidade.ManterMesaReserva;
import br.com.entidade.ManterReserva;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MesaReservaView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MesaReservaView.class.getName());
    
    private final ManterMesaReserva dao = new ManterMesaReserva();
    private final ManterMesa daoMesa = new ManterMesa();
    private final ManterReserva daoReserva = new ManterReserva();

    private ArrayList<MesaReserva> listaMesaReservas = new ArrayList<>();
    private ArrayList<Mesa> listaMesasCombo = new ArrayList<>();
    private ArrayList<Reserva> listaReservasCombo = new ArrayList<>();
    
    public MesaReservaView() {
        initComponents();
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Relação Mesa x Reserva");
        jTreservadas.setDefaultEditor(Object.class, null);
        jTreservadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        carregarCombos();
        listarMesaReservas();
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCreserva = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreservadas = new javax.swing.JTable();
        jBalterar = new javax.swing.JButton();
        jBdeletar = new javax.swing.JButton();
        jBsalvar = new javax.swing.JButton();
        jLmesa = new javax.swing.JLabel();
        jLreserva = new javax.swing.JLabel();
        jLchegada = new javax.swing.JLabel();
        jLsaida = new javax.swing.JLabel();
        jLobservacao = new javax.swing.JLabel();
        jBpesquisar = new javax.swing.JButton();
        jTpesquisa = new javax.swing.JTextField();
        jTpesquisa.putClientProperty("JTextField.placeholderText", "Digite o número da mesa ou nome do cliente...");
        jLabel1 = new javax.swing.JLabel();
        jBlimpar = new javax.swing.JButton();
        jTsaida = new javax.swing.JTextField();
        jTsaida.putClientProperty("JTextField.placeholderText", "Ex.: 22:05");
        jCmesa = new javax.swing.JComboBox<>();
        jTchegada = new javax.swing.JTextField();
        jTchegada.putClientProperty("JTextField.placeholderText", "Ex.: 18:35");
        jTobservacao = new javax.swing.JTextField();
        jTobservacao.putClientProperty("JTextField.placeholderText", "Ex.: Aniversário...");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCreserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTreservadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mesa", "Reserva", "Chegada", "Saída", "Observação"
            }
        ));
        jTreservadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreservadasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTreservadas);

        jBalterar.setText("Alterar");
        jBalterar.addActionListener(this::jBalterarActionPerformed);

        jBdeletar.setText("Deletar");
        jBdeletar.addActionListener(this::jBdeletarActionPerformed);

        jBsalvar.setText("Cadastrar");
        jBsalvar.addActionListener(this::jBsalvarActionPerformed);

        jLmesa.setText("Mesa");

        jLreserva.setText("Reserva");

        jLchegada.setText("Horário de chegada:");

        jLsaida.setText("Horário de saída:");

        jLobservacao.setText("Observação:");

        jBpesquisar.setText("Consultar");
        jBpesquisar.addActionListener(this::jBpesquisarActionPerformed);

        jTpesquisa.setBackground(java.awt.SystemColor.controlHighlight);

        jLabel1.setText("Consultar:");

        jBlimpar.setText("Limpar");
        jBlimpar.addActionListener(this::jBlimparActionPerformed);

        jCmesa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jTpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBpesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(jBlimpar)
                        .addGap(639, 639, 639))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLobservacao)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jBsalvar)
                                        .addGap(52, 52, 52)
                                        .addComponent(jBalterar)
                                        .addGap(52, 52, 52)
                                        .addComponent(jBdeletar))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLchegada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLmesa, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLreserva, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLsaida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCmesa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCreserva, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTchegada)
                                    .addComponent(jTsaida)
                                    .addComponent(jTobservacao))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTpesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBpesquisar)
                    .addComponent(jBlimpar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLmesa)
                            .addComponent(jCmesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLreserva)
                            .addComponent(jCreserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLchegada)
                            .addComponent(jTchegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLsaida)
                            .addComponent(jTsaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTobservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLobservacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBsalvar)
                            .addComponent(jBalterar)
                            .addComponent(jBdeletar))
                        .addContainerGap(125, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
            for (Reserva r : listaReservasCombo) {
                jCreserva.addItem(r.getIdReserva() + " - " + r.getNomeCliente()
                        + " (" + ManterReserva.dataParaTela(r.getDataReserva()) + ")");
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }
    
    private void atualizarTabela(ArrayList<MesaReserva> lista) {
        this.listaMesaReservas = lista;
        DefaultTableModel modelo = (DefaultTableModel) jTreservadas.getModel();
        modelo.setNumRows(0);

        for (MesaReserva mr : listaMesaReservas) {
            modelo.addRow(new Object[]{
                "Mesa " + mr.getNumeroMesa(),
                mr.getNomeCliente() + " (" + ManterReserva.dataParaTela(mr.getDataReserva()) + ")",
                mr.getHoraChegada(),
                mr.getHoraSaida(),
                mr.getObservacao()
            });
        }
    }
    
    public void listarMesaReservas() {
        try {
            atualizarTabela(dao.PesquisarTudo());
        } catch (Exception e) {
            mostrarErro(e);
        }
    }

    private MesaReserva lerCampos() throws Exception {
        int idxMesa = jCmesa.getSelectedIndex();
        int idxReserva = jCreserva.getSelectedIndex();

        if (idxMesa < 0 || idxMesa >= listaMesasCombo.size()) {
            throw new Exception("Selecione uma mesa válida.");
        }
        if (idxReserva < 0 || idxReserva >= listaReservasCombo.size()) {
            throw new Exception("Selecione uma reserva válida.");
        }

        MesaReserva mr = new MesaReserva();
        mr.setIdMesa(listaMesasCombo.get(idxMesa).getIdMesa());
        mr.setIdReserva(listaReservasCombo.get(idxReserva).getIdReserva());
        mr.setHoraChegada(jTchegada.getText().trim());
        mr.setHoraSaida(jTsaida.getText().trim());
        mr.setObservacao(jTobservacao.getText().trim());
        return mr;
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
        for (int i = 0; i < listaReservasCombo.size(); i++) {
            if (listaReservasCombo.get(i).getIdReserva() == idReserva) {
                jCreserva.setSelectedIndex(i);
                return;
            }
        }
    }

    private void limparCampos() {
        jTpesquisa.setText("");
        jTchegada.setText("");
        jTsaida.setText("");
        jTobservacao.setText("");
        if (jCmesa.getItemCount() > 0) {
            jCmesa.setSelectedIndex(0);
        }
        if (jCreserva.getItemCount() > 0) {
            jCreserva.setSelectedIndex(0);
        }
        jTreservadas.clearSelection();
    }
    
    private boolean confirmarHorario(MesaReserva mr) {
        boolean semChegada = mr.getHoraChegada() == null || mr.getHoraChegada().isEmpty();
        boolean semSaida = mr.getHoraSaida() == null || mr.getHoraSaida().isEmpty();

        if (!semChegada && !semSaida) {
            return true;
        }

        String aviso;
        if (semChegada && semSaida) {
            aviso = "Nenhum horário foi informado. Se não selecionar o horário, a mesa será reservada para o dia todo.\n\nDeseja continuar mesmo assim?";
        } else if (semChegada) {
            aviso = "O horário de chegada não foi informado (será considerado desde o início do dia).\n\nDeseja continuar mesmo assim?";
        } else {
            aviso = "O horário de saída não foi informado (será considerado até o fim do dia).\n\nDeseja continuar mesmo assim?";
        }

        int opcao = JOptionPane.showConfirmDialog(
                this,
                aviso,
                "Aviso de Horário",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        return opcao == JOptionPane.YES_OPTION;
    }

    private void mostrarErro(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
    
    private void jBsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalvarActionPerformed
        try {
            MesaReserva mr = lerCampos();
            if(!confirmarHorario(mr)){
                return;
            }
            dao.inserir(mr);

            JOptionPane.showMessageDialog(this, "Mesa vinculada à reserva com sucesso!");
            limparCampos();
            listarMesaReservas();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBsalvarActionPerformed

    private void jTreservadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreservadasMouseClicked
        int linhaSelecionada = jTreservadas.getSelectedRow();
        if (linhaSelecionada != -1) {
            MesaReserva mr = listaMesaReservas.get(linhaSelecionada);
            selecionarMesa(mr.getIdMesa());
            selecionarReserva(mr.getIdReserva());
            jTchegada.setText(mr.getHoraChegada() != null ? mr.getHoraChegada() : "");
            jTsaida.setText(mr.getHoraSaida() != null ? mr.getHoraSaida() : "");
            jTobservacao.setText(mr.getObservacao() != null ? mr.getObservacao() : "");
        }
    }//GEN-LAST:event_jTreservadasMouseClicked

    private void jBalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBalterarActionPerformed
        try {
            int linhaSelecionada = jTreservadas.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um vínculo na tabela antes de alterar.");
            }

            MesaReserva mrAntigo = listaMesaReservas.get(linhaSelecionada);
            MesaReserva mrNovo = lerCampos();
            if(!confirmarHorario(mrNovo)){
                return;
            }
            dao.alterarMesaReserva(mrNovo, mrAntigo.getIdMesa(), mrAntigo.getIdReserva());

            JOptionPane.showMessageDialog(this, "Vínculo alterado com sucesso!");
            limparCampos();
            listarMesaReservas();
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBalterarActionPerformed

    private void jBdeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdeletarActionPerformed
        try {
            int linhaSelecionada = jTreservadas.getSelectedRow();
            if (linhaSelecionada == -1) {
                throw new Exception("Selecione um vínculo na tabela antes de excluir.");
            }

            MesaReserva mr = listaMesaReservas.get(linhaSelecionada);
            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja remover a Mesa " + mr.getNumeroMesa() + " da reserva de \"" + mr.getNomeCliente() + "\"?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                dao.deletarMesaReserva(mr);
                JOptionPane.showMessageDialog(this, "Vínculo excluído com sucesso!");
                limparCampos();
                listarMesaReservas();
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBdeletarActionPerformed

    private void jBpesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBpesquisarActionPerformed
        try {
            String busca = jTpesquisa.getText().trim();
            if (busca.isEmpty()) {
                listarMesaReservas();
                return;
            }

            ArrayList<MesaReserva> encontrados = dao.pesquisarPorFiltro(busca);
            if (encontrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum vínculo encontrado para: " + busca);
                listarMesaReservas();
            } else {
                atualizarTabela(encontrados);
            }
        } catch (Exception e) {
            mostrarErro(e);
        }
    }//GEN-LAST:event_jBpesquisarActionPerformed

    private void jBlimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlimparActionPerformed
        limparCampos();
        listarMesaReservas();
    }//GEN-LAST:event_jBlimparActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(() -> new MesaReservaView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBalterar;
    private javax.swing.JButton jBdeletar;
    private javax.swing.JButton jBlimpar;
    private javax.swing.JButton jBpesquisar;
    private javax.swing.JButton jBsalvar;
    private javax.swing.JComboBox<String> jCmesa;
    private javax.swing.JComboBox<String> jCreserva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLchegada;
    private javax.swing.JLabel jLmesa;
    private javax.swing.JLabel jLobservacao;
    private javax.swing.JLabel jLreserva;
    private javax.swing.JLabel jLsaida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTchegada;
    private javax.swing.JTextField jTobservacao;
    private javax.swing.JTextField jTpesquisa;
    private javax.swing.JTable jTreservadas;
    private javax.swing.JTextField jTsaida;
    // End of variables declaration//GEN-END:variables
}
