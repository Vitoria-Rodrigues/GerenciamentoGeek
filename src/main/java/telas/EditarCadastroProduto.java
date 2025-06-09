
package telas;

import classes.Categoria;
import classes.Produto;
import classesDAO.ProdutoDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import validacao.Alerta;


public class EditarCadastroProduto extends javax.swing.JDialog {
    private Produto produto;
    private List<Categoria> listaCategorias = ProdutoDAO.pegarCategoria();;
    public EditarCadastroProduto(java.awt.Frame parent, boolean modal, Produto produto) {
        super(parent, modal);
        initComponents();
        this.produto = produto;
        montarCampos();
        montarComboboxCategorias();
    }
    public void montarCampos() {
        tfEditarNome.setText(produto.getNomeProd());
        tfEditarPreco.setText(produto.getPreco().toString());
        tfEditarCod.setText(String.valueOf(produto.getCodigoProd()));
        tfEditarEstoque.setText(String.valueOf(produto.getQtdEstoque()));
        taEditarDesc.setText(produto.getDescProd());  
        cbEPCateg.setSelectedItem(produto.getCategoria());
    }
    
    public void montarComboboxCategorias() { //Método para montar um combobox com uma lista de Categorias que vem do banco de dados
        DefaultComboBoxModel modelo = (DefaultComboBoxModel) cbEPCateg.getModel();
        modelo.removeAllElements();
        for (Categoria categoria : listaCategorias) {
            modelo.addElement(categoria.getNomeCateg());
        }
        cbEPCateg.setModel(modelo);
    }
    
    public void salvarEdicao(){
        if(tfEditarNome.getText().isBlank() && tfEditarPreco.getText().isBlank()
            && tfEditarCod.getText().isBlank() && tfEditarEstoque.getText().isBlank()
            && taEditarDesc.getText().isBlank()){
            Alerta.Erro("Campo vazio", "Por favor, inserir as informações");
        } else if(tfEditarNome.getText().isBlank()){
            Alerta.Erro("Campo vazio", "Por favor, inserir o nome");
        } else if(tfEditarPreco.getText().isBlank()){
            Alerta.Erro("Campo vazio", "Por favor, inserir o preço");
        } else if(tfEditarCod.getText().isBlank()){
            Alerta.Erro("Campo vazio", "Por favor, inserir o código");
        } else if(tfEditarEstoque.getText().isBlank()){
            Alerta.Erro("Campo vazio", "Por favor, inserir a quantidade no estoque");
        } else if(taEditarDesc.getText().isBlank()){
            Alerta.Erro("Campo vazio", "Por favor, inserir a descrição");
        } else {
            
            Categoria categselecionado = (Categoria) listaCategorias.stream()
                    .filter(categoria -> categoria.getNomeCateg().equals(cbEPCateg.getSelectedItem().toString()))
                    .findFirst()
                    .orElse(null);
            
            produto.setNomeProd(tfEditarNome.getText());
            produto.setPreco(Double.parseDouble(tfEditarPreco.getText()));
            produto.setQtdEstoque(Integer.parseInt(tfEditarEstoque.getText()));
            produto.setCodigoProd(Integer.parseInt(tfEditarCod.getText()));
            produto.setDescProd(taEditarDesc.getText());
            produto.setCategoria(categselecionado);
            
            ProdutoDAO.editarProduto(produto);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbEPCateg = new javax.swing.JComboBox<>();
        tfEditarCod = new javax.swing.JTextField();
        tfEditarEstoque = new javax.swing.JTextField();
        tfEditarPreco = new javax.swing.JTextField();
        tfEditarNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taEditarDesc = new javax.swing.JTextArea();
        buttonEditarSalvar = new customs.botaoBorda();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Editar Cadastro de Produto");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nome:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Preço:");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Estoque:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Descrição:");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Categoria:");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Código:");

        cbEPCateg.setBackground(new java.awt.Color(255, 255, 255));
        cbEPCateg.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        cbEPCateg.setForeground(new java.awt.Color(0, 0, 0));

        tfEditarCod.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarCod.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfEditarCod.setForeground(new java.awt.Color(0, 0, 0));
        tfEditarCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEditarCodActionPerformed(evt);
            }
        });

        tfEditarEstoque.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarEstoque.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfEditarEstoque.setForeground(new java.awt.Color(0, 0, 0));
        tfEditarEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEditarEstoqueActionPerformed(evt);
            }
        });

        tfEditarPreco.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarPreco.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfEditarPreco.setForeground(new java.awt.Color(0, 0, 0));
        tfEditarPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEditarPrecoActionPerformed(evt);
            }
        });

        tfEditarNome.setBackground(new java.awt.Color(255, 255, 255));
        tfEditarNome.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfEditarNome.setForeground(new java.awt.Color(0, 0, 0));
        tfEditarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEditarNomeActionPerformed(evt);
            }
        });

        taEditarDesc.setBackground(new java.awt.Color(255, 255, 255));
        taEditarDesc.setColumns(20);
        taEditarDesc.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        taEditarDesc.setForeground(new java.awt.Color(0, 0, 0));
        taEditarDesc.setRows(5);
        jScrollPane1.setViewportView(taEditarDesc);

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(tfEditarCod, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(195, 195, 195)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tfEditarPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfEditarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(223, 223, 223)
                                            .addComponent(cbEPCateg, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(tfEditarNome)))))))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonEditarSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(374, 374, 374))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfEditarNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(tfEditarCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEditarPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(cbEPCateg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfEditarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(buttonEditarSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
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

    private void tfEditarCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEditarCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEditarCodActionPerformed

    private void tfEditarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEditarEstoqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEditarEstoqueActionPerformed

    private void tfEditarPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEditarPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEditarPrecoActionPerformed

    private void tfEditarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEditarNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEditarNomeActionPerformed

    private void buttonEditarSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarSalvarActionPerformed
        salvarEdicao();
        this.dispose();
    }//GEN-LAST:event_buttonEditarSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customs.botaoBorda buttonEditarSalvar;
    private javax.swing.JComboBox<String> cbEPCateg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taEditarDesc;
    private javax.swing.JTextField tfEditarCod;
    private javax.swing.JTextField tfEditarEstoque;
    private javax.swing.JTextField tfEditarNome;
    private javax.swing.JTextField tfEditarPreco;
    // End of variables declaration//GEN-END:variables
}
