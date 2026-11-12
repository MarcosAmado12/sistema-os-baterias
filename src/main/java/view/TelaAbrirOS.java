package view;

import javax.swing.JOptionPane;

import controller.BateriaController;
import controller.ClienteController;
import controller.OrdemServicoController;
import controller.TecnicoController;
import model.Bateria;
import model.Cliente;
import model.OrdemServico;
import model.Tecnico;

public class TelaAbrirOS extends javax.swing.JFrame {

    private OrdemServicoController controller = new OrdemServicoController();
    private ClienteController clienteController = new ClienteController();
    private BateriaController bateriaController = new BateriaController();
    private TecnicoController tecnicoController = new TecnicoController();
    private Cliente cliente;

    public TelaAbrirOS() {
        initComponents();
        setSize(640, 470);
        setLocationRelativeTo(null);
        carregarTecnicos();
    }

    private void carregarTecnicos() {
        try {
            for (Tecnico tecnico : tecnicoController.listar()) {
                cbTecnico.addItem(tecnico);
            }
            cbTecnico.setSelectedItem(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        cliente = null;
        txtCpfCnpj.setText("");
        lblCliente.setText("(nenhum cliente selecionado)");
        cbBateria.removeAllItems();
        cbTecnico.setSelectedItem(null);
        txtComplemento.setText("");
        txtDescricao.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCpfCnpj = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbBateria = new javax.swing.JComboBox<>();
        btnNovaBateria = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbTecnico = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        lblComplemento = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        btnAbrir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Abrir ordem de serviço");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("CPF/CNPJ do cliente:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(15, 15, 170, 28);

        getContentPane().add(txtCpfCnpj);
        txtCpfCnpj.setBounds(190, 15, 250, 28);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(450, 15, 90, 28);

        jLabel2.setText("Cliente:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(15, 50, 170, 28);

        lblCliente.setText("(nenhum cliente selecionado)");
        getContentPane().add(lblCliente);
        lblCliente.setBounds(190, 50, 400, 28);

        jLabel4.setText("Bateria:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(15, 85, 170, 28);

        getContentPane().add(cbBateria);
        cbBateria.setBounds(190, 85, 250, 28);

        btnNovaBateria.setText("Cadastrar bateria");
        btnNovaBateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaBateriaActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovaBateria);
        btnNovaBateria.setBounds(450, 85, 150, 28);

        jLabel5.setText("Técnico responsável:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(15, 120, 170, 28);

        getContentPane().add(cbTecnico);
        cbTecnico.setBounds(190, 120, 250, 28);

        jLabel6.setText("Tipo de manutenção:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(15, 155, 170, 28);

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Preventiva", "Corretiva", "Garantia" }));
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        getContentPane().add(cbTipo);
        cbTipo.setBounds(190, 155, 150, 28);

        lblComplemento.setText("Valor da revisão (R$):");
        getContentPane().add(lblComplemento);
        lblComplemento.setBounds(15, 190, 170, 28);

        getContentPane().add(txtComplemento);
        txtComplemento.setBounds(190, 190, 150, 28);

        jLabel8.setText("Descrição do problema:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(15, 225, 170, 28);

        txtDescricao.setColumns(20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setRows(5);
        txtDescricao.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDescricao);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(190, 225, 410, 120);

        btnAbrir.setText("Abrir OS");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });
        getContentPane().add(btnAbrir);
        btnAbrir.setBounds(190, 360, 120, 32);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            cliente = clienteController.buscarPorCpfCnpj(txtCpfCnpj.getText());
            cbBateria.removeAllItems();
            if (cliente == null) {
                lblCliente.setText("(nenhum cliente selecionado)");
                JOptionPane.showMessageDialog(this,
                        "Cliente não cadastrado. Faça o cadastro e busque novamente.");
                new TelaClientes().setVisible(true);
                return;
            }
            lblCliente.setText(cliente.getNome());
            for (Bateria bateria : bateriaController.listarPorCliente(cliente.getIdCliente())) {
                cbBateria.addItem(bateria);
            }
            if (cbBateria.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "Este cliente não possui bateria cadastrada. "
                        + "Cadastre a bateria e busque o cliente novamente.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNovaBateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaBateriaActionPerformed
        new TelaBaterias().setVisible(true);
    }//GEN-LAST:event_btnNovaBateriaActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        txtComplemento.setText("");
        switch ((String) cbTipo.getSelectedItem()) {
            case "Preventiva":
                lblComplemento.setText("Valor da revisão (R$):");
                break;
            case "Corretiva":
                lblComplemento.setText("Valor da mão de obra (R$):");
                break;
            default:
                lblComplemento.setText("Número da garantia:");
                break;
        }
    }//GEN-LAST:event_cbTipoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        try {
            OrdemServico os = controller.abrir(cliente, (Bateria) cbBateria.getSelectedItem(),
                    (Tecnico) cbTecnico.getSelectedItem(), (String) cbTipo.getSelectedItem(),
                    txtComplemento.getText(), txtDescricao.getText());
            JOptionPane.showMessageDialog(this,
                    "Ordem de serviço nº " + os.getNumero() + " registrada com status Aberta.");
            limpar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNovaBateria;
    private javax.swing.JComboBox<model.Bateria> cbBateria;
    private javax.swing.JComboBox<model.Tecnico> cbTecnico;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblComplemento;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtCpfCnpj;
    private javax.swing.JTextArea txtDescricao;
    // End of variables declaration//GEN-END:variables
}
