package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.OrdemServicoController;
import model.OrdemServico;

public class TelaOrdensServico extends javax.swing.JFrame {

    private OrdemServicoController controller = new OrdemServicoController();
    private List<OrdemServico> ordens = new ArrayList<>();

    public TelaOrdensServico() {
        initComponents();
        setSize(980, 480);
        setLocationRelativeTo(null);
        carregar();
    }

    private void carregar() {
        try {
            ordens = controller.listar();
            TabelaOrdens.preencher(tabela, ordens);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPecas = new javax.swing.JButton();
        btnStatus = new javax.swing.JButton();
        btnRecarregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordens de serviço");
        setResizable(false);
        getContentPane().setLayout(null);

        btnPecas.setText("Registrar peças");
        btnPecas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPecasActionPerformed(evt);
            }
        });
        getContentPane().add(btnPecas);
        btnPecas.setBounds(15, 15, 140, 30);

        btnStatus.setText("Atualizar status");
        btnStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusActionPerformed(evt);
            }
        });
        getContentPane().add(btnStatus);
        btnStatus.setBounds(165, 15, 140, 30);

        btnRecarregar.setText("Recarregar lista");
        btnRecarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecarregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRecarregar);
        btnRecarregar.setBounds(315, 15, 140, 30);

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
        jScrollPane1.setBounds(15, 60, 935, 370);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPecasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPecasActionPerformed
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma ordem de serviço na tabela.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            OrdemServico os = controller.buscarPorNumero(ordens.get(linha).getNumero());
            if (os.isConcluida()) {
                JOptionPane.showMessageDialog(this, "A OS já está concluída e não aceita novas peças.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            new TelaPecas(this, os).setVisible(true);
            carregar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPecasActionPerformed

    private void btnStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusActionPerformed
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma ordem de serviço na tabela.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            OrdemServico os = controller.buscarPorNumero(ordens.get(linha).getNumero());
            if (os.isConcluida()) {
                JOptionPane.showMessageDialog(this, "OS já concluída.");
                return;
            }

            String[] opcoes = { OrdemServico.EM_EXECUCAO, OrdemServico.CONCLUIDA };
            String novoStatus = (String) JOptionPane.showInputDialog(this,
                    "OS nº " + os.getNumero() + " - status atual: " + os.getStatus() + "\nNovo status:",
                    "Atualizar status", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
            if (novoStatus == null) {
                return;
            }

            controller.alterarStatus(os, novoStatus);
            JOptionPane.showMessageDialog(this, "Status alterado para \"" + os.getStatus() + "\".");
            carregar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnStatusActionPerformed

    private void btnRecarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecarregarActionPerformed
        carregar();
    }//GEN-LAST:event_btnRecarregarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPecas;
    private javax.swing.JButton btnRecarregar;
    private javax.swing.JButton btnStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
