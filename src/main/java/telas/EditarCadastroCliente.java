package telas;

import classes.Cliente;
import classesDAO.ClienteDAO;
import validacao.Alerta;

public class EditarCadastroCliente extends javax.swing.JDialog {

    private Cliente cliente;

    public EditarCadastroCliente(java.awt.Frame parent, boolean modal, Cliente cliente) {
        super(parent, modal);
        initComponents();
        this.cliente = cliente;
        montarCampos();
    }

    public void montarCampos() {
        tfNomeEC.setText(cliente.getNomeC());
        tfCpfEC.setText(cliente.getCpfC());
        cbSexoEC.setSelectedItem(cliente.getSexoC());
        tfTelefoneEC.setText(cliente.getTelefoneC());
    }

    public void SalvarEdicao() {
        if (tfNomeEC.getText().isBlank()) {
            Alerta.Erro("Por favor, inserir o nome", "Campo vazio");
        } else if (tfCpfEC.getText().isBlank()) {
            Alerta.Erro("Por favor, inserir o CPF", "Campo vazio");
        } else if (cbSexoEC.getSelectedItem() == null || cbSexoEC.getSelectedItem().equals(" ")) {
            Alerta.Erro("Por favor, selecionar o sexo", "Campo vazio");
        } else if (tfNomeEC.getText().isBlank() && tfCpfEC.getText().isBlank()
                && (cbSexoEC.getSelectedItem() == null || cbSexoEC.getSelectedItem().equals(" "))) {
            Alerta.Erro("Por favor, inserir os dados nos campos vazio", "Campo vazio");
        } else {
            cliente.setNomeC(tfNomeEC.getText());
            cliente.setCpfC(tfCpfEC.getText());
            cliente.setSexoC(cbSexoEC.getSelectedItem().toString());
            cliente.setTelefoneC(tfTelefoneEC.getText());

            ClienteDAO.editarCliente(cliente);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbSexoEC = new javax.swing.JComboBox<>();
        tfCpfEC = new javax.swing.JTextField();
        tfNomeEC = new javax.swing.JTextField();
        tfTelefoneEC = new javax.swing.JTextField();
        buttonEditarSalvar = new customs.botaoBorda();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Editar Cadastro de Cliente");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tel:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Sexo:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("CPF:");

        cbSexoEC.setBackground(new java.awt.Color(255, 255, 255));
        cbSexoEC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        cbSexoEC.setForeground(new java.awt.Color(0, 0, 0));
        cbSexoEC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Feminino", "Masculino", "Outro" }));

        tfCpfEC.setBackground(new java.awt.Color(255, 255, 255));
        tfCpfEC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfCpfEC.setForeground(new java.awt.Color(0, 0, 0));

        tfNomeEC.setBackground(new java.awt.Color(255, 255, 255));
        tfNomeEC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfNomeEC.setForeground(new java.awt.Color(0, 0, 0));

        tfTelefoneEC.setBackground(new java.awt.Color(255, 255, 255));
        tfTelefoneEC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfTelefoneEC.setForeground(new java.awt.Color(0, 0, 0));

        buttonEditarSalvar.setForeground(new java.awt.Color(255, 255, 255));
        buttonEditarSalvar.setText("Salvar");
        buttonEditarSalvar.setBorderPainted(false);
        buttonEditarSalvar.setCor(new java.awt.Color(0, 51, 102));
        buttonEditarSalvar.setFocusPainted(false);
        buttonEditarSalvar.setFocusable(false);
        buttonEditarSalvar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonEditarSalvar.setRadius(32);
        buttonEditarSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfCpfEC, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSexoEC, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfNomeEC)
                            .addComponent(tfTelefoneEC)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(buttonEditarSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jLabel1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNomeEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfCpfEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbSexoEC, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfTelefoneEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(buttonEditarSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditarSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarSalvarActionPerformed
        SalvarEdicao();
        this.dispose();
    }//GEN-LAST:event_buttonEditarSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customs.botaoBorda buttonEditarSalvar;
    private javax.swing.JComboBox<String> cbSexoEC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfCpfEC;
    private javax.swing.JTextField tfNomeEC;
    private javax.swing.JTextField tfTelefoneEC;
    // End of variables declaration//GEN-END:variables
}
