
package telas;

import classes.Cargo;
import classes.Funcionario;
import classesDAO.FuncionarioDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import utilitarios.criptografia;
import validacao.Alerta;


public class EditarCadastroFuncionario extends javax.swing.JDialog {

    private Funcionario funcionario;
    private List<Cargo> listaCargos = FuncionarioDAO.pegarCargos();
    
    public EditarCadastroFuncionario(java.awt.Frame parent, boolean modal, Funcionario funcionario) {
        super(parent, modal);
        initComponents();
        this.funcionario = funcionario;
        montarCampos();
        montarComboboxCargos();
    }
    public void montarCampos() {
        tfEditarFNome.setText(funcionario.getNomeF());
        tfEditarFCPF.setText(funcionario.getCpfF());
        tfEditarFTel.setText(funcionario.getTelefoneF());
        tfEditarFCEP.setText(funcionario.getCep());
        tfEditarFLogradouro.setText(funcionario.getLogradouro());
        tfEditarFNum.setText(funcionario.getNumero());
        tfEditarFComplemento.setText(funcionario.getComplemento());
        cbEditarFCargo.setSelectedItem(funcionario.getCargo());
        tfEditarFLogin.setText(funcionario.getLogin().getLogin());
    }
    public void montarComboboxCargos() { //Método para montar um combobox com uma lista de Cargos que vem do banco de dados
        DefaultComboBoxModel modelo = (DefaultComboBoxModel) cbEditarFCargo.getModel();
        modelo.removeAllElements();
        for (Cargo cargo : listaCargos) {
            modelo.addElement(cargo.getFuncao());
        }
        cbEditarFCargo.setModel(modelo);
    }
    
    public void salvarEdicao(){
        String passwordFunc = tfEditarFSenha.getText();
        
        if (tfEditarFNome.getText().isBlank() && tfEditarFCPF.getText().isBlank()
                && tfEditarFCEP.getText().isBlank() && tfEditarFLogradouro.getText().isBlank()
                && tfEditarFNum.getText().isBlank() && tfEditarFComplemento.getText().isBlank() 
                && tfEditarFLogin.getText().isBlank()
                && passwordFunc.isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir os dados");
        } else if (tfEditarFNome.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o nome");
        } else if (tfEditarFCPF.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o CPF");
        } else if (tfEditarFCEP.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o CEP");
        } else if (tfEditarFLogradouro.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o logradouro" );
        } else if (tfEditarFNum.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o numero");
        } else if (tfEditarFComplemento.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o complemento");
        } else if (tfEditarFLogin.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o login");
        } else if (passwordFunc.isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir a senha");
        } else {
            
            Cargo cargoselecionado = (Cargo) listaCargos.stream()
                    .filter(cargo -> cargo.getFuncao().equals(cbEditarFCargo.getSelectedItem().toString()))
                    .findFirst()
                    .orElse(null);
            
            funcionario.setNomeF(tfEditarFNome.getText());
            funcionario.setCep(tfEditarFCEP.getText());
            funcionario.setComplemento(tfEditarFComplemento.getText());
            funcionario.setCpfF(tfEditarFCPF.getText());
            funcionario.setLogradouro(tfEditarFLogradouro.getText());
            funcionario.setNumero(tfEditarFNum.getText());
            funcionario.setTelefoneF(tfEditarFTel.getText());
            funcionario.setCargo(cargoselecionado);
            funcionario.getLogin().setLogin(tfEditarFLogin.getText());
            funcionario.getLogin().setSenha(criptografia.toMD5(passwordFunc));
            
            FuncionarioDAO.editarFuncionario(funcionario);
        }

    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        tfEditarFCPF = new javax.swing.JTextField();
        tfEditarFNome = new javax.swing.JTextField();
        tfEditarFLogradouro = new javax.swing.JTextField();
        tfEditarFNum = new javax.swing.JTextField();
        tfEditarFCEP = new javax.swing.JTextField();
        tfEditarFComplemento = new javax.swing.JTextField();
        tfEditarFTel = new javax.swing.JTextField();
        cbEditarFCargo = new javax.swing.JComboBox<>();
        buttonEditarSalvar = new customs.botaoBorda();
        jLabel29 = new javax.swing.JLabel();
        tfEditarFLogin = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        tfEditarFSenha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Editar Cadastro do Funcionário");

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Nome:");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("CPF:");

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("CEP:");

        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Logradouro:");

        jLabel28.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Login:");

        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Tel:");

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Numero:");

        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Cargo:");

        tfEditarFCPF.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarFCPF.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        tfEditarFCPF.setForeground(new java.awt.Color(0, 0, 0));

        tfEditarFNome.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarFNome.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        tfEditarFNome.setForeground(new java.awt.Color(0, 0, 0));

        tfEditarFLogradouro.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarFLogradouro.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        tfEditarFLogradouro.setForeground(new java.awt.Color(0, 0, 0));

        tfEditarFNum.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarFNum.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        tfEditarFNum.setForeground(new java.awt.Color(0, 0, 0));

        tfEditarFCEP.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarFCEP.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        tfEditarFCEP.setForeground(new java.awt.Color(0, 0, 0));

        tfEditarFComplemento.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarFComplemento.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        tfEditarFComplemento.setForeground(new java.awt.Color(0, 0, 0));

        tfEditarFTel.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarFTel.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        tfEditarFTel.setForeground(new java.awt.Color(0, 0, 0));

        cbEditarFCargo.setBackground(new java.awt.Color(255, 255, 255));
        cbEditarFCargo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        cbEditarFCargo.setForeground(new java.awt.Color(0, 0, 0));
        cbEditarFCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEditarFCargoActionPerformed(evt);
            }
        });

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

        jLabel29.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Complemento:");

        tfEditarFLogin.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarFLogin.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        tfEditarFLogin.setForeground(new java.awt.Color(0, 0, 0));

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Senha:");

        tfEditarFSenha.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarFSenha.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        tfEditarFSenha.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(431, 431, 431)
                        .addComponent(buttonEditarSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(tfEditarFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEditarFLogradouro))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel21))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfEditarFCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(tfEditarFCEP)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addGap(29, 29, 29)
                                    .addComponent(tfEditarFLogin))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfEditarFComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbEditarFCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfEditarFNum)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfEditarFTel)
                                    .addComponent(tfEditarFSenha))))))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(tfEditarFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(tfEditarFCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEditarFCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel26)
                    .addComponent(tfEditarFCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEditarFNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(tfEditarFLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(tfEditarFComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEditarFTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(tfEditarFLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(tfEditarFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(buttonEditarSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbEditarFCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEditarFCargoActionPerformed
        
    }//GEN-LAST:event_cbEditarFCargoActionPerformed

    private void buttonEditarSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarSalvarActionPerformed
        salvarEdicao();
        this.dispose();
    }//GEN-LAST:event_buttonEditarSalvarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customs.botaoBorda buttonEditarSalvar;
    private javax.swing.JComboBox<String> cbEditarFCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfEditarFCEP;
    private javax.swing.JTextField tfEditarFCPF;
    private javax.swing.JTextField tfEditarFComplemento;
    private javax.swing.JTextField tfEditarFLogin;
    private javax.swing.JTextField tfEditarFLogradouro;
    private javax.swing.JTextField tfEditarFNome;
    private javax.swing.JTextField tfEditarFNum;
    private javax.swing.JTextField tfEditarFSenha;
    private javax.swing.JTextField tfEditarFTel;
    // End of variables declaration//GEN-END:variables
}
