package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.BateriaController;
import controller.ClienteController;
import model.Bateria;
import model.Cliente;
import util.Conversor;

public class TelaBaterias extends javax.swing.JFrame {

    private BateriaController controller = new BateriaController();
    private ClienteController clienteController = new ClienteController();
    private List<Bateria> baterias = new ArrayList<>();
    private Bateria selecionada;

    public TelaBaterias() {
        initComponents();
        setSize(820, 500);
        setLocationRelativeTo(null);
        carregar();
    }

    private void carregar() {
        try {
            cbCliente.removeAllItems();
            for (Cliente cliente : clienteController.listar()) {
                cbCliente.addItem(cliente);
            }
            cbCliente.setSelectedItem(null);

            baterias = controller.listar();
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            modelo.setRowCount(0);
            for (Bateria bateria : baterias) {
                modelo.addRow(new Object[] { bateria.getNumeroSerie(), bateria.getMarca(), bateria.getModelo(),
                        bateria.getTensao(), bateria.getCapacidadeAh(), bateria.getCliente().getNome() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        selecionada = null;
        tabela.clearSelection();
        cbCliente.setSelectedItem(null);
        txtNumeroSerie.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtTensao.setText("");
        txtCapacidade.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroSerie = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTensao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCapacidade = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Baterias");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("Cliente:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(15, 15, 110, 28);

        getContentPane().add(cbCliente);
        cbCliente.setBounds(125, 15, 250, 28);

        jLabel2.setText("Nº de série:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(410, 15, 110, 28);

        getContentPane().add(txtNumeroSerie);
        txtNumeroSerie.setBounds(520, 15, 270, 28);

        jLabel3.setText("Marca:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(15, 50, 110, 28);

        getContentPane().add(txtMarca);
        txtMarca.setBounds(125, 50, 250, 28);

        jLabel4.setText("Modelo:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(410, 50, 110, 28);

        getContentPane().add(txtModelo);
        txtModelo.setBounds(520, 50, 270, 28);

        jLabel5.setText("Tensão (V):");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(15, 85, 110, 28);

        getContentPane().add(txtTensao);
        txtTensao.setBounds(125, 85, 250, 28);

        jLabel6.setText("Capacidade (Ah):");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(410, 85, 110, 28);

        getContentPane().add(txtCapacidade);
        txtCapacidade.setBounds(520, 85, 270, 28);

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
                "Nº de série", "Marca", "Modelo", "Tensão (V)", "Capacidade (Ah)", "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            Bateria bateria = new Bateria();
            if (selecionada != null) {
                bateria.setIdBateria(selecionada.getIdBateria());
            }
            bateria.setCliente((Cliente) cbCliente.getSelectedItem());
            bateria.setNumeroSerie(txtNumeroSerie.getText().trim());
            bateria.setMarca(txtMarca.getText().trim());
            bateria.setModelo(txtModelo.getText().trim());
            bateria.setTensao(Conversor.lerDecimal(txtTensao.getText(), "Tensão"));
            bateria.setCapacidadeAh(Conversor.lerDecimal(txtCapacidade.getText(), "Capacidade"));

            controller.salvar(bateria);
            JOptionPane.showMessageDialog(this, "Bateria " + bateria.getNumeroSerie() + " salva com sucesso!");
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
        if (selecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma bateria na tabela.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(this,
                "Excluir a bateria " + selecionada.getNumeroSerie() + "?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            controller.excluir(selecionada);
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
        selecionada = baterias.get(linha);
        for (int i = 0; i < cbCliente.getItemCount(); i++) {
            if (cbCliente.getItemAt(i).getIdCliente() == selecionada.getCliente().getIdCliente()) {
                cbCliente.setSelectedIndex(i);
            }
        }
        txtNumeroSerie.setText(selecionada.getNumeroSerie());
        txtMarca.setText(selecionada.getMarca());
        txtModelo.setText(selecionada.getModelo());
        txtTensao.setText(String.valueOf(selecionada.getTensao()));
        txtCapacidade.setText(String.valueOf(selecionada.getCapacidadeAh()));
    }//GEN-LAST:event_tabelaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<model.Cliente> cbCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtCapacidade;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNumeroSerie;
    private javax.swing.JTextField txtTensao;
    // End of variables declaration//GEN-END:variables
}
