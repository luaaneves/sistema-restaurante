package br.com.visao;

public class ViewPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewPrincipal.class.getName());

    public ViewPrincipal() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Sistema Restaurante");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBnovopedido = new javax.swing.JButton();
        jBconsultarreservas = new javax.swing.JButton();
        jBrelatorio = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMcargos = new javax.swing.JMenuItem();
        jMfuncionarios = new javax.swing.JMenuItem();
        jMclientes = new javax.swing.JMenuItem();
        jMmesas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMreservas = new javax.swing.JMenuItem();
        jMpedidos = new javax.swing.JMenuItem();
        jMmesareserva = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBnovopedido.setBackground(new java.awt.Color(102, 102, 255));
        jBnovopedido.setForeground(new java.awt.Color(255, 255, 255));
        jBnovopedido.setText("Novo Pedido");
        jBnovopedido.addActionListener(this::jBnovopedidoActionPerformed);

        jBconsultarreservas.setText("Consultar Reservas");
        jBconsultarreservas.addActionListener(this::jBconsultarreservasActionPerformed);

        jBrelatorio.setText("Relatório");
        jBrelatorio.addActionListener(this::jBrelatorioActionPerformed);

        jMenu1.setBackground(new java.awt.Color(153, 153, 255));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Cadastros");

        jMcargos.setText("Gerenciar Cargos");
        jMcargos.addActionListener(this::jMcargosActionPerformed);
        jMenu1.add(jMcargos);

        jMfuncionarios.setText("Gerenciar Funcionários");
        jMfuncionarios.addActionListener(this::jMfuncionariosActionPerformed);
        jMenu1.add(jMfuncionarios);

        jMclientes.setText("Gerenciar Clientes");
        jMclientes.addActionListener(this::jMclientesActionPerformed);
        jMenu1.add(jMclientes);

        jMmesas.setText("Gerenciar Mesas");
        jMmesas.addActionListener(this::jMmesasActionPerformed);
        jMenu1.add(jMmesas);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(255, 51, 51));
        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Operacional");

        jMreservas.setText("Gerenciar Reservas");
        jMreservas.addActionListener(this::jMreservasActionPerformed);
        jMenu2.add(jMreservas);

        jMpedidos.setText("Gerenciar Pedidos");
        jMpedidos.addActionListener(this::jMpedidosActionPerformed);
        jMenu2.add(jMpedidos);

        jMmesareserva.setText("Mesa x Reserva");
        jMmesareserva.addActionListener(this::jMmesareservaActionPerformed);
        jMenu2.add(jMmesareserva);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(534, 534, 534)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBconsultarreservas, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jBrelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBnovopedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(529, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jBnovopedido, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jBrelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jBconsultarreservas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBnovopedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnovopedidoActionPerformed

        try {
            new PedidoView().setVisible(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir tela de pedidos: " + e.getMessage());
        }
    }//GEN-LAST:event_jBnovopedidoActionPerformed

    private void jMfuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMfuncionariosActionPerformed

        try {
            new FuncionarioView().setVisible(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir tela de funcionários: " + e.getMessage());
        }
    }//GEN-LAST:event_jMfuncionariosActionPerformed

    private void jMclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMclientesActionPerformed

        try {
            new ClienteView().setVisible(true); 
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir tela de clientes: " + e.getMessage());
        }
    }//GEN-LAST:event_jMclientesActionPerformed

    private void jMcargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMcargosActionPerformed

        try {
            new CargoView().setVisible(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir tela de cargos: " + e.getMessage());
        }
    }//GEN-LAST:event_jMcargosActionPerformed

    private void jMmesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMmesasActionPerformed
        try {
            new MesaView().setVisible(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir tela de mesas: " + e.getMessage());
        }
    }//GEN-LAST:event_jMmesasActionPerformed

    private void jBconsultarreservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBconsultarreservasActionPerformed

        try {
            new ReservaView().setVisible(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir a tela de reservas: " + e.getMessage());
        }
    }//GEN-LAST:event_jBconsultarreservasActionPerformed

    private void jMpedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMpedidosActionPerformed
        try {
        new PedidoView().setVisible(true);
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir tela de pedidos: " + e.getMessage());
    }
    }//GEN-LAST:event_jMpedidosActionPerformed

    private void jMreservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMreservasActionPerformed
        try {
            new ReservaView().setVisible(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir tela de reservas: " + e.getMessage());
        }
    }//GEN-LAST:event_jMreservasActionPerformed

    private void jMmesareservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMmesareservaActionPerformed
        try {
            new MesaReservaView().setVisible(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir tela de Mesa x Reserva: " + e.getMessage());
        }
    }//GEN-LAST:event_jMmesareservaActionPerformed

    private void jBrelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBrelatorioActionPerformed
        try {
            new RelatorioPedidos().setVisible(true);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao abrir tela de relatório: " + e.getMessage());
        }
    }//GEN-LAST:event_jBrelatorioActionPerformed

    public static void main(String args[]) {
        com.formdev.flatlaf.FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> new ViewPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBconsultarreservas;
    private javax.swing.JButton jBnovopedido;
    private javax.swing.JButton jBrelatorio;
    private javax.swing.JMenuItem jMcargos;
    private javax.swing.JMenuItem jMclientes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMfuncionarios;
    private javax.swing.JMenuItem jMmesareserva;
    private javax.swing.JMenuItem jMmesas;
    private javax.swing.JMenuItem jMpedidos;
    private javax.swing.JMenuItem jMreservas;
    // End of variables declaration//GEN-END:variables
}
