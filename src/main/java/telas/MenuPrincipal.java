
package telas;

import classes.Login;
import validacao.Alerta;


public class MenuPrincipal extends javax.swing.JFrame {

        private Login login;
        
    public MenuPrincipal(Login login) {
        initComponents();
        this.login = login;
        definirRestricoes(login);
    }
    
    public void definirRestricoes(Login login) {
        if(login.getFuncionario() == null) {
            
        } else if (login.getFuncionario().getCargo().getFuncao().equals("Atendente")) {
            buttonCadastrarF.setVisible(false);
            buttonExibirF.setVisible(false);
        } else if (login.getFuncionario().getCargo().getFuncao().equals("Caixa")) {
            buttonCadastrarF.setVisible(false);
            buttonExibirF.setVisible(false);
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
        buttonCadastrarC = new customs.botaoBorda();
        buttonCadastrarP = new customs.botaoBorda();
        buttonCadastrarV = new customs.botaoBorda();
        buttonCadastrarF = new customs.botaoBorda();
        buttonExibirF = new customs.botaoBorda();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logoG-1(200).png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 1, 55)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(176, 103, 1));
        jLabel2.setText("erencimento");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logoG-2(200).png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 1, 55)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(176, 103, 1));
        jLabel4.setText("eek");

        buttonCadastrarC.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarC.setText("Cadastrar Cliente");
        buttonCadastrarC.setBorderPainted(false);
        buttonCadastrarC.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarC.setCorBorda(new java.awt.Color(0, 51, 102));
        buttonCadastrarC.setFocusPainted(false);
        buttonCadastrarC.setFocusable(false);
        buttonCadastrarC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        buttonCadastrarC.setRadius(28);
        buttonCadastrarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarCActionPerformed(evt);
            }
        });

        buttonCadastrarP.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarP.setText("Cadastrar Produto");
        buttonCadastrarP.setBorderPainted(false);
        buttonCadastrarP.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarP.setCorBorda(new java.awt.Color(0, 51, 102));
        buttonCadastrarP.setFocusPainted(false);
        buttonCadastrarP.setFocusable(false);
        buttonCadastrarP.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        buttonCadastrarP.setRadius(28);
        buttonCadastrarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarPActionPerformed(evt);
            }
        });

        buttonCadastrarV.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarV.setText("Cadastrar Venda");
        buttonCadastrarV.setBorderPainted(false);
        buttonCadastrarV.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarV.setCorBorda(new java.awt.Color(0, 51, 102));
        buttonCadastrarV.setFocusPainted(false);
        buttonCadastrarV.setFocusable(false);
        buttonCadastrarV.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        buttonCadastrarV.setRadius(28);
        buttonCadastrarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarVActionPerformed(evt);
            }
        });

        buttonCadastrarF.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarF.setText("Cadastrar Funcionário");
        buttonCadastrarF.setBorderPainted(false);
        buttonCadastrarF.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarF.setCorBorda(new java.awt.Color(0, 51, 102));
        buttonCadastrarF.setFocusPainted(false);
        buttonCadastrarF.setFocusable(false);
        buttonCadastrarF.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        buttonCadastrarF.setRadius(28);
        buttonCadastrarF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarFActionPerformed(evt);
            }
        });

        buttonExibirF.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirF.setText("Exibir Funcionário");
        buttonExibirF.setBorderPainted(false);
        buttonExibirF.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirF.setCorBorda(new java.awt.Color(0, 51, 102));
        buttonExibirF.setFocusPainted(false);
        buttonExibirF.setFocusable(false);
        buttonExibirF.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        buttonExibirF.setRadius(28);
        buttonExibirF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(buttonCadastrarC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(buttonCadastrarP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(buttonCadastrarV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(buttonCadastrarF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(buttonExibirF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(194, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCadastrarC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCadastrarP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCadastrarV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonExibirF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCadastrarF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87))
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

    private void buttonCadastrarFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarFActionPerformed
        new Cadastros("cadastroFunc", login).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonCadastrarFActionPerformed

    private void buttonCadastrarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarCActionPerformed
        new Cadastros("cadastroCli", login).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonCadastrarCActionPerformed

    private void buttonCadastrarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarPActionPerformed
        new Cadastros("cadastroProd", login).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonCadastrarPActionPerformed

    private void buttonCadastrarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarVActionPerformed
        new Cadastros("cadastroVenda", login).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonCadastrarVActionPerformed

    private void buttonExibirFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirFActionPerformed
        new Cadastros("exibirFunc", login).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonExibirFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customs.botaoBorda buttonCadastrarC;
    private customs.botaoBorda buttonCadastrarF;
    private customs.botaoBorda buttonCadastrarP;
    private customs.botaoBorda buttonCadastrarV;
    private customs.botaoBorda buttonExibirF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
