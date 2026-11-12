package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ClienteController;
import model.Cliente;

public class TelaClientes extends javax.swing.JFrame {

    private ClienteController controller = new ClienteController();
    private List<Cliente> clientes = new ArrayList<>();
    private Cliente selecionado;

    public TelaClientes() {
        initComponents();
        setSize(820, 500);
        setLocationRelativeTo(null);
        carregar();
    }

    private void carregar() {
        try {
            clientes = controller.listar();
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            modelo.setRowCount(0);
            for (Cliente cliente : clientes) {
                modelo.addRow(new Object[] { cliente.getNome(), cliente.getCpfCnpj(), cliente.getTelefone(),
                        cliente.getEmail(), cliente.getEndereco() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        selecionado = null;
        tabela.clearSelection();
        txtNome.setText("");
        txtCpfCnpj.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCpfCnpj = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("Nome:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(15, 15, 110, 28);

        getContentPane().add(txtNome);
        txtNome.setBounds(125, 15, 250, 28);

        jLabel2.setText("CPF/CNPJ:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(410, 15, 110, 28);

        getContentPane().add(txtCpfCnpj);
        txtCpfCnpj.setBounds(520, 15, 270, 28);

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

        jLabel5.setText("Endereço:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(15, 85, 110, 28);

        getContentPane().add(txtEndereco);
        txtEndereco.setBounds(125, 85, 250, 28);

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
                "Nome", "CPF/CNPJ", "Telefone", "E-mail", "Endereço"
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
            Cliente cliente = new Cliente();
            if (selecionado != null) {
                cliente.setIdCliente(selecionado.getIdCliente());
            }
            cliente.setNome(txtNome.getText().trim());
            cliente.setCpfCnpj(txtCpfCnpj.getText().trim());
            cliente.setTelefone(txtTelefone.getText().trim());
            cliente.setEmail(txtEmail.getText().trim());
            cliente.setEndereco(txtEndereco.getText().trim());

            controller.salvar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente " + cliente.getNome() + " salvo com sucesso!");
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
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(this, "Excluir o cliente " + selecionado.getNome() + "?",
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
        selecionado = clientes.get(linha);
        txtNome.setText(selecionado.getNome());
        txtCpfCnpj.setText(selecionado.getCpfCnpj());
        txtTelefone.setText(selecionado.getTelefone());
        txtEmail.setText(selecionado.getEmail());
        txtEndereco.setText(selecionado.getEndereco());
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtCpfCnpj;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
