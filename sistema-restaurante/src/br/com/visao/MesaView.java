package br.com.visao;

import br.com.controle.Mesa;
import br.com.entidade.ManterMesa;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MesaView extends JFrame {

    private JTextField txtId = new JTextField();
    private JTextField txtNumero = new JTextField();
    private JTextField txtCapacidade = new JTextField();
    private JComboBox<String> cbLocal = new JComboBox<>(new String[]{"Salão Principal", "Varanda", "Área VIP", "Mezanino"});
    private JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Disponível", "Indisponível"});

    private JButton btnCadastrar = new JButton("Cadastrar");
    private JButton btnAlterar = new JButton("Alterar");
    private JButton btnExcluir = new JButton("Excluir");
    private JButton btnPesquisar = new JButton("Consultar ID");
    private JButton btnLimpar = new JButton("Limpar");

    private DefaultTableModel modeloTabela = new DefaultTableModel(
            new Object[]{"ID", "Número", "Capacidade", "Local", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JTable tabela = new JTable(modeloTabela);

    private ManterMesa dao = new ManterMesa();

    public MesaView() {
        setTitle("Gerenciamento de Mesas");
        setSize(640, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        montarTela();
        configurarEventos();
        carregarTabela();
    }

    private void montarTela() {
        setLayout(null);

        JLabel titulo = new JLabel("Mesas");
        titulo.setFont(titulo.getFont().deriveFont(20f));
        titulo.setBounds(20, 10, 200, 30);
        add(titulo);

        adicionarCampo("ID:", txtId, 20, 55, 80);
        adicionarCampo("Número:", txtNumero, 20, 90, 80);
        adicionarCampo("Capacidade:", txtCapacidade, 20, 125, 80);
        adicionarCampo("Local:", cbLocal, 300, 55, 160);
        adicionarCampo("Status:", cbStatus, 300, 90, 160);

        txtId.setEditable(false); 
        cbLocal.setEditable(true); 

        btnCadastrar.setBounds(20, 170, 105, 30);
        btnAlterar.setBounds(135, 170, 105, 30);
        btnExcluir.setBounds(250, 170, 105, 30);
        btnPesquisar.setBounds(365, 170, 120, 30);
        btnLimpar.setBounds(495, 170, 105, 30);
        add(btnCadastrar);
        add(btnAlterar);
        add(btnExcluir);
        add(btnPesquisar);
        add(btnLimpar);

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBounds(20, 215, 580, 245);
        add(rolagem);
    }

    private void adicionarCampo(String texto, java.awt.Component campo, int x, int y, int largura) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setBounds(x, y, 90, 25);
        add(rotulo);
        campo.setBounds(x + 95, y, largura, 25);
        add(campo);
    }

    private void configurarEventos() {
        btnCadastrar.addActionListener(e -> cadastrar());
        btnAlterar.addActionListener(e -> alterar());
        btnExcluir.addActionListener(e -> excluir());
        btnPesquisar.addActionListener(e -> pesquisar());
        btnLimpar.addActionListener(e -> limparCampos());

        tabela.getSelectionModel().addListSelectionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (!e.getValueIsAdjusting() && linha >= 0) {
                txtId.setText(modeloTabela.getValueAt(linha, 0).toString());
                txtNumero.setText(modeloTabela.getValueAt(linha, 1).toString());
                txtCapacidade.setText(modeloTabela.getValueAt(linha, 2).toString());
                cbLocal.setSelectedItem(modeloTabela.getValueAt(linha, 3).toString());
                cbStatus.setSelectedItem(modeloTabela.getValueAt(linha, 4).toString());
            }
        });
    }

    private Mesa lerCampos() throws Exception {
        Mesa m = new Mesa();
        try {
            m.setIdMesa(txtId.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtId.getText().trim()));
            m.setNumero(Integer.parseInt(txtNumero.getText().trim()));
            m.setCapacidade(Integer.parseInt(txtCapacidade.getText().trim()));
        } catch (NumberFormatException ex) {
            throw new Exception("Número e capacidade devem ser números inteiros.");
        }
        Object local = cbLocal.getSelectedItem();
        m.setLocal(local == null ? "" : local.toString().trim());
        m.setStatus(cbStatus.getSelectedIndex() == 0 ? 1 : 0); 
        return m;
    }

    private void cadastrar() {
        try {
            Mesa m = lerCampos();
            dao.inserir(m);
            JOptionPane.showMessageDialog(this, "Mesa cadastrada com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void alterar() {
        try {
            Mesa m = lerCampos();
            dao.alterarMesa(m);
            JOptionPane.showMessageDialog(this, "Mesa alterada com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void excluir() {
        try {
            if (txtId.getText().trim().isEmpty()) {
                throw new Exception("Selecione uma mesa na tabela antes de excluir.");
            }
            int resposta = JOptionPane.showConfirmDialog(this,
                    "Deseja realmente excluir a mesa nº " + txtNumero.getText() + "?",
                    "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
            if (resposta != JOptionPane.YES_OPTION) {
                return;
            }
            Mesa m = new Mesa();
            m.setIdMesa(Integer.parseInt(txtId.getText().trim()));
            dao.deletarMesa(m);
            JOptionPane.showMessageDialog(this, "Mesa excluída com sucesso!");
            limparCampos();
            carregarTabela();
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void pesquisar() {
        try {
            String id = JOptionPane.showInputDialog(this, "Digite o ID da mesa:", txtId.getText());
            if (id == null || id.trim().isEmpty()) {
                return;
            }
            Mesa m = new Mesa();
            m.setIdMesa(Integer.parseInt(id.trim()));
            if (dao.PesquisarRegistro(m)) {
                txtId.setText(String.valueOf(m.getIdMesa()));
                txtNumero.setText(String.valueOf(m.getNumero()));
                txtCapacidade.setText(String.valueOf(m.getCapacidade()));
                cbLocal.setSelectedItem(m.getLocal());
                cbStatus.setSelectedIndex(m.getStatus() == 1 ? 0 : 1);
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma mesa encontrada com esse ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O ID deve ser um número.");
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        try {
            ArrayList<Mesa> mesas = dao.PesquisarTudo();
            for (Mesa m : mesas) {
                modeloTabela.addRow(new Object[]{
                    m.getIdMesa(), m.getNumero(), m.getCapacidade(), m.getLocal(), m.getStatusTexto()
                });
            }
        } catch (Exception ex) {
            mostrarErro(ex);
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNumero.setText("");
        txtCapacidade.setText("");
        cbLocal.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
        tabela.clearSelection();
        txtNumero.requestFocus();
    }

    private void mostrarErro(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        JOptionPane.showMessageDialog(this, msg, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MesaView().setVisible(true));
    }
}
