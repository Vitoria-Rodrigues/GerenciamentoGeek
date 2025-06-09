
package telas;


public class telaErro extends javax.swing.JDialog {

    
    public telaErro(java.awt.Frame parent, boolean modal, String titulo, String msg) {
        super(parent, modal);
        initComponents();
        setTitle(titulo);
        labelAviso.setText(msg);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonErro = new customs.botaoBorda();
        labelAviso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonErro.setForeground(new java.awt.Color(255, 255, 255));
        buttonErro.setText("OK");
        buttonErro.setBorderPainted(false);
        buttonErro.setCor(new java.awt.Color(188, 0, 0));
        buttonErro.setCorBorda(new java.awt.Color(190, 0, 0));
        buttonErro.setCorClick(new java.awt.Color(168, 0, 0));
        buttonErro.setCorHover(new java.awt.Color(168, 0, 0));
        buttonErro.setFocusable(false);
        buttonErro.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonErro.setRadius(32);
        buttonErro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonErroActionPerformed(evt);
            }
        });

        labelAviso.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        labelAviso.setForeground(new java.awt.Color(0, 0, 0));
        labelAviso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAviso.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAviso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(buttonErro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(262, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(labelAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(buttonErro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonErroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonErroActionPerformed
        dispose();
    }//GEN-LAST:event_buttonErroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customs.botaoBorda buttonErro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelAviso;
    // End of variables declaration//GEN-END:variables
}
