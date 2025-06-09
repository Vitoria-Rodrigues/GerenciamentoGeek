
package telas;

public class telaSucesso extends javax.swing.JDialog {

 
    public telaSucesso(java.awt.Frame parent, boolean modal, String titulo, String msg) {
        super(parent, modal);
        initComponents();
        setTitle(titulo);
        labelSucesso.setText(msg);
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonSucess = new customs.botaoBorda();
        labelSucesso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonSucess.setBackground(new java.awt.Color(63, 144, 4));
        buttonSucess.setForeground(new java.awt.Color(255, 255, 255));
        buttonSucess.setText("OK");
        buttonSucess.setBorderPainted(false);
        buttonSucess.setCor(new java.awt.Color(63, 144, 4));
        buttonSucess.setCorBorda(new java.awt.Color(63, 144, 4));
        buttonSucess.setCorClick(new java.awt.Color(77, 177, 5));
        buttonSucess.setCorHover(new java.awt.Color(77, 177, 5));
        buttonSucess.setFocusable(false);
        buttonSucess.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonSucess.setRadius(32);
        buttonSucess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSucessActionPerformed(evt);
            }
        });

        labelSucesso.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        labelSucesso.setForeground(new java.awt.Color(0, 0, 0));
        labelSucesso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSucesso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(buttonSucess, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSucesso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(labelSucesso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(buttonSucess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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

    private void buttonSucessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSucessActionPerformed
        dispose();
    }//GEN-LAST:event_buttonSucessActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customs.botaoBorda buttonSucess;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelSucesso;
    // End of variables declaration//GEN-END:variables
}
