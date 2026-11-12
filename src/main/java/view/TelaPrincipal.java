package view;

import model.Funcionario;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal(Funcionario usuario) {
        initComponents();
        setSize(460, 470);
        setLocationRelativeTo(null);

        lblUsuario.setText("Usuário: " + usuario.getNome() + " (" + usuario.getCargo() + ")");
        liberarBotoes(usuario.getCargo());
    }

    private void liberarBotoes(String cargo) {
        boolean gerente = cargo.equals("Gerente");
        boolean atendente = cargo.equals("Atendente");
        boolean tecnico = cargo.equals("Tecnico");

        btnClientes.setEnabled(atendente || gerente);
        btnBaterias.setEnabled(atendente || gerente);
        btnAbrirOS.setEnabled(atendente || gerente);
        btnOrdens.setEnabled(tecnico || gerente);
        btnHistorico.setEnabled(atendente || gerente);
        btnTecnicos.setEnabled(gerente);
        btnRelatorio.setEnabled(gerente);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        btnClientes = new javax.swing.JButton();
        btnBaterias = new javax.swing.JButton();
        btnAbrirOS = new javax.swing.JButton();
        btnOrdens = new javax.swing.JButton();
        btnHistorico = new javax.swing.JButton();
        btnTecnicos = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu principal");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sistema de Ordens de Serviço");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(12, 15, 420, 30);

        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setText("Usuário:");
        getContentPane().add(lblUsuario);
        lblUsuario.setBounds(12, 45, 420, 20);

        btnClientes.setText("Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes);
        btnClientes.setBounds(102, 85, 240, 30);

        btnBaterias.setText("Baterias");
        btnBaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBateriasActionPerformed(evt);
            }
        });
        getContentPane().add(btnBaterias);
        btnBaterias.setBounds(102, 120, 240, 30);

        btnAbrirOS.setText("Abrir ordem de serviço");
        btnAbrirOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirOSActionPerformed(evt);
            }
        });
        getContentPane().add(btnAbrirOS);
        btnAbrirOS.setBounds(102, 155, 240, 30);

        btnOrdens.setText("Ordens de serviço");
        btnOrdens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdensActionPerformed(evt);
            }
        });
        getContentPane().add(btnOrdens);
        btnOrdens.setBounds(102, 190, 240, 30);

        btnHistorico.setText("Histórico do cliente");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });
        getContentPane().add(btnHistorico);
        btnHistorico.setBounds(102, 225, 240, 30);

        btnTecnicos.setText("Técnicos");
        btnTecnicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTecnicosActionPerformed(evt);
            }
        });
        getContentPane().add(btnTecnicos);
        btnTecnicos.setBounds(102, 260, 240, 30);

        btnRelatorio.setText("Relatório por período");
        btnRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorio);
        btnRelatorio.setBounds(102, 295, 240, 30);

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair);
        btnSair.setBounds(102, 345, 240, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new TelaClientes().setVisible(true);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnBateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBateriasActionPerformed
        new TelaBaterias().setVisible(true);
    }//GEN-LAST:event_btnBateriasActionPerformed

    private void btnAbrirOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirOSActionPerformed
        new TelaAbrirOS().setVisible(true);
    }//GEN-LAST:event_btnAbrirOSActionPerformed

    private void btnOrdensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdensActionPerformed
        new TelaOrdensServico().setVisible(true);
    }//GEN-LAST:event_btnOrdensActionPerformed

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed
        new TelaHistorico().setVisible(true);
    }//GEN-LAST:event_btnHistoricoActionPerformed

    private void btnTecnicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTecnicosActionPerformed
        new TelaTecnicos().setVisible(true);
    }//GEN-LAST:event_btnTecnicosActionPerformed

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioActionPerformed
        new TelaRelatorio().setVisible(true);
    }//GEN-LAST:event_btnRelatorioActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        new TelaLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirOS;
    private javax.swing.JButton btnBaterias;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnOrdens;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnTecnicos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
