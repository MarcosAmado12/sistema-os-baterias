package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.TecnicoController;
import model.Tecnico;

public class TelaTecnicos extends javax.swing.JFrame {

    private TecnicoController controller = new TecnicoController();
    private List<Tecnico> tecnicos = new ArrayList<>();
    private Tecnico selecionado;

    public TelaTecnicos() {
        initComponents();
        setSize(820, 500);
        setLocationRelativeTo(null);
        carregar();
    }

    private void carregar() {
        try {
            tecnicos = controller.listar();
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            modelo.setRowCount(0);
            for (Tecnico tecnico : tecnicos) {
                modelo.addRow(new Object[] { tecnico.getNome(), tecnico.getEspecialidade(),
                        tecnico.getTelefone(), tecnico.getEmail(), tecnico.getLogin() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        selecionado = null;
        tabela.clearSelection();
        txtNome.setText("");
        txtEspecialidade.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEspecialidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Técnicos");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("Nome:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(15, 15, 110, 28);

        getContentPane().add(txtNome);
        txtNome.setBounds(125, 15, 250, 28);

        jLabel2.setText("Especialidade:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(410, 15, 110, 28);

        getContentPane().add(txtEspecialidade);
        txtEspecialidade.setBounds(520, 15, 270, 28);

        jLabel3.setText("Telefone:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(15, 50, 110, 28);

        getContentPane().add(txtTelefone);
        txtTelefone.setBounds(125, 50, 250, 28);

        jLabel4.setText("E-mail:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(410, 50, 110, 28);

        getContentPane().add(txtEmail);
        txtEmail.setBounds(520, 50, 270, 28);

        jLabel5.setText("Login:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(15, 85, 110, 28);

        getContentPane().add(txtLogin);
        txtLogin.setBounds(125, 85, 250, 28);

        jLabel6.setText("Senha:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(410, 85, 110, 28);

        getContentPane().add(txtSenha);
        txtSenha.setBounds(520, 85, 270, 28);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(15, 125, 90, 30);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovo);
        btnNovo.setBounds(115, 125, 90, 30);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir);
        btnExcluir.setBounds(215, 125, 90, 30);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Especialidade", "Telefone", "E-mail", "Login"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(15, 170, 775, 280);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            Tecnico tecnico = new Tecnico();
            if (selecionado != null) {
                tecnico.setIdFuncionario(selecionado.getIdFuncionario());
            }
            tecnico.setNome(txtNome.getText().trim());
            tecnico.setEspecialidade(txtEspecialidade.getText().trim());
            tecnico.setTelefone(txtTelefone.getText().trim());
            tecnico.setEmail(txtEmail.getText().trim());
            tecnico.setLogin(txtLogin.getText().trim());
            tecnico.setSenha(new String(txtSenha.getPassword()));

            controller.salvar(tecnico);
            JOptionPane.showMessageDialog(this, "Técnico " + tecnico.getNome() + " salvo com sucesso!");
            limpar();
            carregar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limpar();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (selecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um técnico na tabela.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(this, "Excluir o técnico " + selecionado.getNome() + "?",
                "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            controller.excluir(selecionado);
            limpar();
            carregar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            return;
        }
        selecionado = tecnicos.get(linha);
        txtNome.setText(selecionado.getNome());
        txtEspecialidade.setText(selecionado.getEspecialidade());
        txtTelefone.setText(selecionado.getTelefone());
        txtEmail.setText(selecionado.getEmail());
        txtLogin.setText(selecionado.getLogin());
        txtSenha.setText(selecionado.getSenha());
    }//GEN-LAST:event_tabelaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEspecialidade;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
