package br.com.visao;

import br.com.controle.Cliente;
import br.com.controle.Reserva;
import br.com.entidade.ManterCliente;
import br.com.entidade.ManterReserva;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class ReservaView extends JFrame {

    private JTextField txtId = new JTextField();
    private JComboBox<Cliente> cbCliente = new JComboBox<>();
    private JFormattedTextField txtData = criarCampoComMascara("##/##/####");
    private JFormattedTextField txtHorario = criarCampoComMascara("##:##");
    private JTextField txtQntPessoas = new JTextField();
    private JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Ativa", "Inativa"});

    private JButton btnAtualizarClientes = new JButton("↻");
    private JButton btnCadastrar = new JButton("Cadastrar");
    private JButton btnAlterar = new JButton("Alterar");
    private JButton btnExcluir = new JButton("Excluir");
    private JButton btnPesquisar = new JButton("Consultar ID");
    private JButton btnLimpar = new JButton("Limpar");

    private DefaultTableModel modeloTabela = new DefaultTableModel(
            new Object[]{"ID", "Cliente", "Data", "Horário", "Pessoas", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    
    private JTable tabela = new JTable(modeloTabela);

    private ArrayList<Reserva> listaReservas = new ArrayList<>();

    private ManterReserva dao = new ManterReserva();
    private ManterCliente daoCliente = new ManterCliente();

    public ReservaView() {
        setTitle("Cadastro de Reservas");
        setSize(700, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        montarTela();
        configurarEventos();
        carregarClientes();
        carregarTabela();
    }

    private void montarTela() {
        setLayout(null);

        JLabel titulo = new JLabel("Reservas");
        titulo.setFont(titulo.getFont().deriveFont(20f));
        titulo.setBounds(20, 10, 200, 30);
        add(titulo);

        adicionarCampo("ID:", txtId, 20, 55, 80);
        adicionarCampo("Cliente:", cbCliente, 20, 90, 200);
        adicionarCampo("Pessoas:", txtQntPessoas, 20, 125, 80);
        adicionarCampo("Data:", txtData, 360, 55, 110);
        adicionarCampo("Horário:", txtHorario, 360, 90, 70);
        adicionarCampo("Status:", cbStatus, 360, 125, 110);

        txtId.setEditable(false);
        btnAtualizarClientes.setBounds(320, 90, 30, 25);
        btnAtualizarClientes.setToolTipText("Recarregar a lista de clientes");
        btnAtualizarClientes.setMargin(new java.awt.Insets(0, 0, 0, 0));
        add(btnAtualizarClientes);

        JLabel dica = new JLabel("Data: dd/mm/aaaa    Horário: hh:mm");
        dica.setBounds(455, 20, 220, 20);
        dica.setForeground(java.awt.Color.GRAY);
        add(dica);

        btnCadastrar.setBounds(20, 170, 115, 30);
        btnAlterar.setBounds(145, 170, 115, 30);
        btnExcluir.setBounds(270, 170, 115, 30);
        btnPesquisar.setBounds(395, 170, 130, 30);
        btnLimpar.setBounds(535, 170, 125, 30);
        add(btnCadastrar);
        add(btnAlterar);
        add(btnExcluir);
        add(btnPesquisar);
        add(btnLimpar);

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200);
        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBounds(20, 215, 640, 265);
        add(rolagem);
    }

    private void adicionarCampo(String texto, java.awt.Component campo, int x, int y, int largura) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setBounds(x, y, 90, 25);
        add(rotulo);
        campo.setBounds(x + 95, y, largura, 25);
        add(campo);
    }

    private static JFormattedTextField criarCampoComMascara(String mascara) {
        try {
            MaskFormatter mf = new MaskFormatter(mascara);
            mf.setPlaceholderCharacter('_');
            JFormattedTextField campo = new JFormattedTextField(mf);
            campo.setFocusLostBehavior(JFormattedTextField.PERSIST);
            return campo;
        } catch (java.text.ParseException e) {
            return new JFormattedTextField();
        }
    }

    private void configurarEventos() {
        btnCadastrar.addActionListener(e -> cadastrar());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnPesquisar.addActionListener(e -> pesquisar());
        btnLimpar.addActionListener(e -> limparCampos());
        btnAtualizarClientes.addActionListener(e -> carregarClientes());

        tabela.getSelectionModel().addListSelectionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (!e.getValueIsAdjusting() && linha >= 0) {
                mostrarNaTela(listaReservas.get(linha));
            }
        });
    }

    private Reserva lerCampos() throws Exception {
        Reserva r = new Reserva();
        r.setIdReserva(txtId.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtId.getText().trim()));

        Cliente c = (Cliente) cbCliente.getSelectedItem();
        r.setIdCliente(c == null ? 0 : c.getIdCliente());

        r.setDataReserva(ManterReserva.dataParaBanco(txtData.getText()));
        r.setHorario(ManterReserva.horaParaBanco(txtHorario.getText()));
        try {
            r.setQntPessoas(Integer.parseInt(txtQntPessoas.getText().trim()));
        } catch (NumberFormatException ex) {
            throw new Exception("A quantidade de pessoas deve ser um número inteiro.");
        }
        r.setStatus(cbStatus.getSelectedIndex() == 0 ? 1 : 0); 
        return r;
    }

    private void mostrarNaTela(Reserva r) {
        txtId.setText(String.valueOf(r.getIdReserva()));
        selecionarCliente(r.getIdCliente());
        txtData.setText(ManterReserva.dataParaTela(r.getDataReserva()));
        txtHorario.setText(ManterReserva.horaParaTela(r.getHorario()));
        txtQntPessoas.setText(String.valueOf(r.getQntPessoas()));
        cbStatus.setSelectedIndex(r.getStatus() == 1 ? 0 : 1);
    }

    private void selecionarCliente(int idCliente) {
        for (int i = 0; i < cbCliente.getItemCount(); i++) {
            if (cbCliente.getItemAt(i).getIdCliente() == idCliente) {
                cbCliente.setSelectedIndex(i);
                return;
            }
        }
    }

    private void cadastrar() {
        try {
            Reserva r = lerCampos();
            dao.inserir(r);
            JOptionPane.showMessageDialog(this, "Reserva cadastrada com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void alterar() {
        try {
            Reserva r = lerCampos();
            dao.alterarReserva(r);
            JOptionPane.showMessageDialog(this, "Reserva alterada com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void excluir() {
        try {
            if (txtId.getText().trim().isEmpty()) {
                throw new Exception("Selecione uma reserva na tabela antes de excluir.");
            }
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Deseja realmente excluir a reserva " + txtId.getText() + "?",
                    "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta != JOptionPane.YES_OPTION) {
                return;
            }
            Reserva r = new Reserva();
            r.setIdReserva(Integer.parseInt(txtId.getText().trim()));
            dao.deletarReserva(r);
            JOptionPane.showMessageDialog(this, "Reserva excluída com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void pesquisar() {
        try {
            String id = JOptionPane.showInputDialog(this, "Digite o ID da reserva:", txtId.getText());
            if (id == null || id.trim().isEmpty()) {
                return;
            }
            Reserva r = new Reserva();
            r.setIdReserva(Integer.parseInt(id.trim()));
            if (dao.PesquisarRegistro(r)) {
                mostrarNaTela(r);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada com esse ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O ID deve ser um número.");
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void carregarClientes() {
        cbCliente.removeAllItems();
        try {
            for (Cliente c : daoCliente.PesquisarTudo()) {
                cbCliente.addItem(c);
            }
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        try {
            listaReservas = dao.PesquisarTudo();
            for (Reserva r : listaReservas) {
                modeloTabela.addRow(new Object[]{
                    r.getIdReserva(),
                    r.getNomeCliente(),
                    ManterReserva.dataParaTela(r.getDataReserva()),
                    ManterReserva.horaParaTela(r.getHorario()),
                    r.getQntPessoas(),
                    r.getStatusTexto()
                });
            }
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtData.setValue(null);
        txtHorario.setValue(null);
        txtQntPessoas.setText("");
        cbStatus.setSelectedIndex(0);
        if (cbCliente.getItemCount() > 0) {
            cbCliente.setSelectedIndex(0);
        }
        tabela.clearSelection();
    }

    private void mostrarErro(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ReservaView().setVisible(true));
    }
}
