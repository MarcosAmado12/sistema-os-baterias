package view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.OrdemServicoController;
import model.OrdemServico;
import model.PecaUtilizada;
import util.Conversor;

public class TelaPecas extends javax.swing.JDialog {

    private OrdemServicoController controller = new OrdemServicoController();
    private OrdemServico os;

    public TelaPecas(java.awt.Frame parent, OrdemServico os) {
        super(parent, true);
        this.os = os;
        initComponents();
        setSize(720, 430);
        setLocationRelativeTo(parent);

        lblDados.setText("OS nº " + os.getNumero() + "  |  " + os.getCliente().getNome() + "  |  Bateria "
                + os.getBateria().getNumeroSerie() + "  |  " + os.getTipo() + "  |  " + os.getStatus());
        atualizar();
    }

    private void atualizar() {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        for (PecaUtilizada peca : os.getPecas()) {
            modelo.addRow(new Object[] { peca.getDescricao(), peca.getQuantidade(),
                    Conversor.formatarValor(peca.getValorUnitario()),
                    Conversor.formatarValor(peca.calcularSubtotal()) });
        }
        lblTotal.setText("Valor total da OS: " + Conversor.formatarValor(os.getValorTotal()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDados = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Peças utilizadas");
        setResizable(false);
        getContentPane().setLayout(null);

        lblDados.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDados.setText("OS");
        getContentPane().add(lblDados);
        lblDados.setBounds(15, 10, 680, 25);

        jLabel2.setText("Descrição:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(15, 45, 70, 28);

        getContentPane().add(txtDescricao);
        txtDescricao.setBounds(90, 45, 220, 28);

        jLabel3.setText("Qtd.:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(320, 45, 35, 28);

        getContentPane().add(txtQuantidade);
        txtQuantidade.setBounds(358, 45, 50, 28);

        jLabel4.setText("Valor unitário:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(418, 45, 90, 28);

        getContentPane().add(txtValor);
        txtValor.setBounds(510, 45, 75, 28);

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(595, 45, 100, 28);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Qtd.", "Valor unitário", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(15, 85, 680, 260);

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTotal.setText("Valor total da OS:");
        getContentPane().add(lblTotal);
        lblTotal.setBounds(15, 355, 680, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        try {
            controller.adicionarPeca(os, txtDescricao.getText(), txtQuantidade.getText(), txtValor.getText());
            txtDescricao.setText("");
            txtQuantidade.setText("");
            txtValor.setText("");
            atualizar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDados;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
