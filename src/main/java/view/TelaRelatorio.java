package view;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import controller.OrdemServicoController;
import model.OrdemServico;
import util.Conversor;

public class TelaRelatorio extends javax.swing.JFrame {

    private OrdemServicoController controller = new OrdemServicoController();

    public TelaRelatorio() {
        initComponents();
        setSize(980, 480);
        setLocationRelativeTo(null);

        txtInicio.setText(Conversor.formatarData(LocalDate.now().withDayOfMonth(1)));
        txtFim.setText(Conversor.formatarData(LocalDate.now()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtInicio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFim = new javax.swing.JTextField();
        btnGerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        lblResumo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de ordens de serviço por período");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("De (dd/mm/aaaa):");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(15, 15, 120, 28);

        getContentPane().add(txtInicio);
        txtInicio.setBounds(135, 15, 100, 28);

        jLabel2.setText("até:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(245, 15, 30, 28);

        getContentPane().add(txtFim);
        txtFim.setBounds(280, 15, 100, 28);

        btnGerar.setText("Gerar relatório");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGerar);
        btnGerar.setBounds(390, 15, 140, 28);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Abertura", "Cliente", "Bateria", "Tipo", "Técnico", "Status", "Conclusão", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(15, 60, 935, 340);

        lblResumo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblResumo.setText("");
        getContentPane().add(lblResumo);
        lblResumo.setBounds(15, 408, 935, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        try {
            LocalDate inicio = Conversor.lerData(txtInicio.getText(), "De");
            LocalDate fim = Conversor.lerData(txtFim.getText(), "até");
            List<OrdemServico> ordens = controller.listarPorPeriodo(inicio, fim);
            TabelaOrdens.preencher(tabela, ordens);
            lblResumo.setText("Ordens no período: " + ordens.size() + "      Valor total: "
                    + Conversor.formatarValor(controller.somarValores(ordens)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGerarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblResumo;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtFim;
    private javax.swing.JTextField txtInicio;
    // End of variables declaration//GEN-END:variables
}
