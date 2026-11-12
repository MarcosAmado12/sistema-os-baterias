package view;

import javax.swing.JOptionPane;

import controller.LoginController;
import model.Funcionario;

public class TelaLogin extends javax.swing.JFrame {

    private LoginController controller = new LoginController();

    public TelaLogin() {
        initComponents();
        setSize(380, 230);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnEntrar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ordens de Serviço - Baterias");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(12, 15, 340, 30);

        jLabel2.setText("Login:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 65, 60, 28);

        getContentPane().add(txtLogin);
        txtLogin.setBounds(120, 65, 200, 28);

        jLabel3.setText("Senha:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 100, 60, 28);

        getContentPane().add(txtSenha);
        txtSenha.setBounds(120, 100, 200, 28);

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrar);
        btnEntrar.setBounds(132, 145, 100, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        try {
            Funcionario funcionario = controller.entrar(txtLogin.getText(),
                    new String(txtSenha.getPassword()));
            new TelaPrincipal(funcionario).setVisible(true);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
