package view;

import java.util.List;

import javax.swing.JOptionPane;

import controller.ClienteController;
import controller.OrdemServicoController;
import model.Cliente;
import model.OrdemServico;

public class TelaHistorico extends javax.swing.JFrame {

    private OrdemServicoController controller = new OrdemServicoController();
    private ClienteController clienteController = new ClienteController();

    public TelaHistorico() {
        initComponents();
        setSize(980, 460);
        setLocationRelativeTo(null);
        carregarClientes();
    }

    private void carregarClientes() {
        try {
            for (Cliente cliente : clienteController.listar()) {
                cbCliente.addItem(cliente);
            }
            cbCliente.setSelectedItem(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        btnConsultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Histórico de ordens de serviço do cliente");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("Cliente:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(15, 15, 60, 28);

        getContentPane().add(cbCliente);
        cbCliente.setBounds(80, 15, 300, 28);

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConsultar);
        btnConsultar.setBounds(390, 15, 110, 28);

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
        jScrollPane1.setBounds(15, 60, 935, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            List<OrdemServico> ordens = controller.listarPorCliente((Cliente) cbCliente.getSelectedItem());
            TabelaOrdens.preencher(tabela, ordens);
            if (ordens.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Este cliente ainda não possui ordens de serviço.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox<model.Cliente> cbCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
