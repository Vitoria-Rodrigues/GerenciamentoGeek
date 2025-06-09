package telas;

import classes.Cargo;
import classes.Categoria;
import classes.Cliente;
import classes.Funcionario;
import classes.ItemCarrinho;
import classes.Login;
import classes.Produto;
import classes.Venda;
import classesDAO.ClienteDAO;
import classesDAO.FuncionarioDAO;
import classesDAO.ProdutoDAO;
import classesDAO.VendaDAO;
import customs.botaoBorda;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import utilitarios.criptografia;
import validacao.Alerta;

public class Cadastros extends javax.swing.JFrame {

    private List<Cargo> listaCargos = FuncionarioDAO.pegarCargos();
    private List<Categoria> listaCategorias = ProdutoDAO.pegarCategoria();
    private Login login;

    public Cadastros(String menuP, Login login) {
        initComponents();
        this.login = login;
        definirRestricoes(login);
        montarComboboxCargos();
        montarComboboxCategorias();
        viewChange(menuP);
    }

    public void definirRestricoes(Login login) {
        if (login.getFuncionario() == null) {
            Alerta.Sucesso("Bem Vindo!", "Seja Bem Vindo Administrador!");
        } else if (login.getFuncionario().getCargo().getFuncao().equals("Atendente")) {
            buttonCadastrarF.setVisible(false);
            buttonExibirF.setVisible(false);
            buttonExibirCExcluir.setEnabled(false);
            buttonExibirVExcluir.setEnabled(false);
            buttonExibirVExcluir.setVisible(false);
            buttonExibirVExcluir.setEnabled(false);
        } else if (login.getFuncionario().getCargo().getFuncao().equals("Caixa")) {
            buttonCadastrarF.setVisible(false);
            buttonExibirF.setVisible(false);
            buttonExibirCExcluir.setEnabled(false);
            buttonExibirVExcluir.setEnabled(false);
            buttonExibirVExcluir.setVisible(false);
            buttonExibirVExcluir.setEnabled(false);
        } else if(login.getFuncionario().getCargo().getFuncao().equals("Gerente")){
            buttonExibirVExcluir.setVisible(false);
            buttonExibirVExcluir.setEnabled(false);
        }
    }

    public void viewChange(String cardName) {  //Método para mudar os cardLayout de acordo com os botões correspondentes da sidebar
        CardLayout layout = (CardLayout) panelTelas.getLayout();
        layout.show(panelTelas, cardName);
        resetarBotoes(buttonCadastrarC, buttonCadastrarP, buttonCadastrarF, buttonCadastrarV,
                buttonExibirC, buttonExibirP, buttonExibirF, buttonExibirV);

        switch (cardName) {
            case "cadastroCli" -> {
                buttonCadastrarC.setCor(new Color(27, 91, 155));
            }
            case "cadastroProd" -> {
                buttonCadastrarP.setCor(new Color(27, 91, 155));
            }
            case "cadastroVenda" -> {
                buttonCadastrarV.setCor(new Color(27, 91, 155));
                tfVendaData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
            }
            case "cadastroFunc" -> {
                buttonCadastrarF.setCor(new Color(27, 91, 155));
            }
            case "exibirCli" -> {
                buttonExibirC.setCor(new Color(27, 91, 155));
            }
            case "exibirProd" -> {
                buttonExibirP.setCor(new Color(27, 91, 155));
            }
            case "exibirVendas" -> {
                buttonExibirV.setCor(new Color(27, 91, 155));
            }
            case "exibirFunc" -> {
                listaFuncionarios();
                buttonExibirF.setCor(new Color(27, 91, 155));
            }
        }
    }

    public void limparCampos(JTextField... campos) { //Método para limpar os textfield
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    public void resetarBotoes(botaoBorda... botoes) { //Método para resetar a cor dos botões da sidebar
        for (botaoBorda botao : botoes) {
            botao.setCor(new Color(0, 51, 102));
        }
    }

    public void montarComboboxCargos() { //Método para montar um combobox com uma lista de Cargos que vem do banco de dados
        DefaultComboBoxModel modelo = (DefaultComboBoxModel) cbFCargos.getModel();
        modelo.removeAllElements();
        for (Cargo cargo : listaCargos) {
            modelo.addElement(cargo.getFuncao());
        }
        cbFCargos.setModel(modelo);
    }

    public void montarComboboxCategorias() { //Método para montar um combobox com uma lista de Categorias que vem do banco de dados
        DefaultComboBoxModel modelo = (DefaultComboBoxModel) cbPCategoria.getModel();
        modelo.removeAllElements();
        for (Categoria categoria : listaCategorias) {
            modelo.addElement(categoria.getNomeCateg());
        }
        cbPCategoria.setModel(modelo);
    }
    private Cliente cliente = new Cliente();
    private Produto produto = new Produto();
    private List<ItemCarrinho> carrinho = new ArrayList<>();

    public void listagemProdutos() {
        produto = VendaDAO.listarProdutos(tfVendaCodigo.getText());
        tfVendaProduto.setText(produto.getNomeProd());
        tfVendaPreco.setText(String.valueOf(produto.getPreco()));
    }

    public void listarCPF() {
        cliente = VendaDAO.listarCPF(tfVendaCPFC.getText());       
        Alerta.Cliente("Info", cliente.getCpfC(), cliente.getTelefoneC());
    }
    
    public void limparTabela(){
        DefaultTableModel modelo = (DefaultTableModel) tableVenda.getModel();
        modelo.setRowCount(0);
        tableVenda.setModel(modelo);
    }

    public void tabelaCarrinho() {
        DefaultTableModel modelo = (DefaultTableModel) tableVenda.getModel();
        modelo.setRowCount(0);

        for (ItemCarrinho item : carrinho) {
            Double subtotal = item.getQtd() * item.getProduto().getPreco();
            String[] linha = {String.valueOf(item.getProduto().getCodigoProd()), item.getProduto().getNomeProd(),
                String.valueOf(item.getQtd()), String.valueOf(item.getProduto().getPreco()),
                "R$" + subtotal.toString()};
            modelo.addRow(linha);
        }
        tableVenda.setModel(modelo);
    }

    public void adcCarrinho() {
        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);
        item.setQtd(Integer.parseInt(tfVendaQtd.getText()));

        carrinho.add(item);
        limparCampos(tfVendaProduto, tfVendaCodigo, tfVendaPreco, tfVendaQtd);

        tabelaCarrinho();
    }

    public void cadastroCliente() { //Método para cadastrar o cliente no banco de dados e verificar os campos se estao vazios
        if (tfNomeC.getText().isBlank() && tfCpfC.getText().isBlank()
                && (cbSexoC.getSelectedItem() == null || cbSexoC.getSelectedItem().equals(" "))) {
            Alerta.Erro("Campo vazio", "Por favor, inserir os dados");
        } else if (tfNomeC.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o nome");
        } else if (tfCpfC.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o CPF");
        } else if (cbSexoC.getSelectedItem() == null || cbSexoC.getSelectedItem().equals(" ")) {
            Alerta.Erro("Campo vazio", "Por favor, selecionar o sexo");
        } else {
            Cliente cli = new Cliente();
            cli.setNomeC(tfNomeC.getText());
            cli.setCpfC(tfCpfC.getText());
            cli.setSexoC(cbSexoC.getSelectedItem().toString());
            cli.setTelefoneC(tfTelefoneC.getText());

            ClienteDAO.cadastrarCliente(cli);

            limparCampos(tfNomeC, tfCpfC, tfTelefoneC);
            cbSexoC.setSelectedIndex(-1);
        }
    }

    public void cadastroFuncionario() { //Método para cadastrar o funcionário no banco de dados
        String passwordFunc = tfFSenha.getText();

        if (tfFNome.getText().isBlank() && tfFCpf.getText().isBlank()
                && tfFCep.getText().isBlank() && tfFLogradouro.getText().isBlank()
                && tfFNum.getText().isBlank() && tfFComplemento.getText().isBlank()
                && tfFLogin.getText().isBlank()
                && passwordFunc.isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir os dados");
        } else if (tfFNome.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o nome");
        } else if (tfFCpf.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o CPF");
        } else if (tfFCep.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o CEP");
        } else if (tfFLogradouro.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o logradouro");
        } else if (tfFNum.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o numero");
        } else if (tfFComplemento.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o complemento");
        } else if (tfFLogin.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o login");
        } else if (passwordFunc.isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir a senha");
        } else {

            Login log = new Login();
            log.setLogin(tfFLogin.getText());
            log.setSenha(criptografia.toMD5(passwordFunc));

            Cargo cargoselecionado = (Cargo) listaCargos.stream()
                    .filter(cargo -> cargo.getFuncao().equals(cbFCargos.getSelectedItem().toString()))
                    .findFirst()
                    .orElse(null);

            Funcionario func = new Funcionario();
            func.setNomeF(tfFNome.getText());
            func.setCpfF(tfFCpf.getText());
            func.setCep(tfFCep.getText());
            func.setLogradouro(tfFLogradouro.getText());
            func.setNumero(tfFNum.getText());
            func.setComplemento(tfFComplemento.getText());
            func.setTelefoneF(tfFTelefone.getText());
            func.setLogin(log);
            log.setFuncionario(func);
            func.setCargo(cargoselecionado);

            FuncionarioDAO.cadastrarFuncionario(func);

            limparCampos(tfFNome, tfFCpf, tfFCep, tfFLogradouro,
                    tfFNum, tfFComplemento, tfFTelefone, tfFLogin, tfFSenha);
            cbFCargos.setSelectedIndex(-1);

        }

    }

    public void cadastroProduto() { //Método para cadastrar o produto no banco de dados
        if (tfCadastroPNome.getText().isBlank() && tfCadastroPPreco.getText().isBlank()
                && tfCadastroPCod.getText().isBlank() && tfCadastroPEstoque.getText().isBlank()
                && taCadastroPDesc.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir as informações");
        } else if (tfCadastroPNome.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o Nome");
        } else if (tfCadastroPPreco.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o Preço");
        } else if (tfCadastroPCod.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir o Código");
        } else if (tfCadastroPEstoque.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir a quantidade em Estoque");
        } else if (cbPCategoria.getSelectedItem() == null || cbPCategoria.getSelectedItem().equals(" ")) {
            Alerta.Erro("Campo vazio", "Por favor, selecionar uma Categoria");
        } else if (taCadastroPDesc.getText().isBlank()) {
            Alerta.Erro("Campo vazio", "Por favor, inserir uma Descrição");
        } else {

            Categoria categselecionado = (Categoria) listaCategorias.stream()
                    .filter(categoria -> categoria.getNomeCateg().equals(cbPCategoria.getSelectedItem().toString()))
                    .findFirst()
                    .orElse(null);

            Produto prod = new Produto();
            prod.setNomeProd(tfCadastroPNome.getText());
            prod.setPreco(Double.parseDouble(tfCadastroPPreco.getText()));
            prod.setDescProd(taCadastroPDesc.getText());
            prod.setCodigoProd(Integer.parseInt(tfCadastroPCod.getText()));
            prod.setQtdEstoque(Integer.parseInt(tfCadastroPEstoque.getText()));
            prod.setCategoria(categselecionado);

            ProdutoDAO.cadastrarProduto(prod);

            limparCampos(tfCadastroPNome, tfCadastroPPreco, tfCadastroPCod, tfCadastroPEstoque);
            cbPCategoria.setSelectedIndex(-1);
            taCadastroPDesc.setText("");

        }
    }
    
    public void cadastroVenda() { //Método para cadastrar o produto no banco de dados       
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setFuncionario(login.getFuncionario());
        List<Produto> produtos = new ArrayList<>();
        Double valorTotal = 0.0;
        int qtdprod = 0;
        
        for(ItemCarrinho item : carrinho){
            produtos.add(item.getProduto());
            valorTotal = valorTotal + (item.getProduto().getPreco() * item.getQtd());
            qtdprod = qtdprod + item.getQtd();
            int qtdEstoque = item.getProduto().getQtdEstoque();
            int qtdEstoqueAtz = qtdEstoque - item.getQtd();
            item.getProduto().setQtdEstoque(qtdEstoqueAtz);
        }
        venda.setProd(produtos);
        venda.setDataVenda(LocalDate.now());
        venda.setTotalVenda(valorTotal);
        venda.setQtdVenda(qtdprod);
        
        new Pagamento(this, rootPaneCheckingEnabled, venda).setVisible(true);
    }

    public void listaClientes() { //Método para listar os clientes que foram cadastrados no banco de dados
        DefaultTableModel modelo = (DefaultTableModel) tableClientes.getModel();
        modelo.setRowCount(0);
        List<Cliente> lista = ClienteDAO.listarClientes(tfBuscarClientes.getText());

        for (Cliente cli : lista) {
            String[] linha = {cli.getId().toString(), cli.getNomeC(),
                cli.getCpfC(), cli.getSexoC(), cli.getTelefoneC()};
            modelo.addRow(linha);
        }
        tableClientes.setModel(modelo);
    }

    public void listaFuncionarios() { //Método para listar os funcionarios que foram cadastrados no banco de dados
        DefaultTableModel modelo = (DefaultTableModel) tableFuncionarios.getModel();
        modelo.setRowCount(0);
        List<Funcionario> lista = FuncionarioDAO.listarFuncionarios(tfBuscarFuncionarios.getText());

        for (Funcionario funcionario : lista) {
            String[] linha = {funcionario.getId().toString(), funcionario.getNomeF(),
                funcionario.getCpfF(), funcionario.getCargo().getFuncao(),
                funcionario.getTelefoneF(), funcionario.getCep(),
                funcionario.getLogradouro(), funcionario.getNumero(), funcionario.getComplemento()};
            modelo.addRow(linha);
        }
        tableFuncionarios.setModel(modelo);
    }

    public void listaProdutos() {  //Método para listar os produtos que foram cadastrados no banco de dados
        DefaultTableModel modelo = (DefaultTableModel) tableProdutos.getModel();
        modelo.setRowCount(0);
        List<Produto> lista = ProdutoDAO.listarProdutos(tfBuscarProdutos.getText());

        for (Produto prod : lista) {
            String[] linha = {prod.getId().toString(), String.valueOf(prod.getCodigoProd()), produto.getNomeProd(),
                prod.getCategoria().getNomeCateg(), String.valueOf(prod.getQtdEstoque()),
                String.valueOf(prod.getPreco()), prod.getDescProd()};
            modelo.addRow(linha);
        }
        tableProdutos.setModel(modelo);
    }
    
    public void listaVendas() {  //Método para listar as vendas que foram cadastrados no banco de dados
        DefaultTableModel modelo = (DefaultTableModel) tableVendas.getModel();
        modelo.setRowCount(0);
        List<Venda> lista = VendaDAO.listarVendas(tfBuscarVendas.getText());

        for (Venda venda : lista) {
            String[] linha = {venda.getId().toString(), venda.getCliente().getNomeC(), 
                String.valueOf(venda.getDataVenda()), String.valueOf(venda.getQtdVenda()),
            String.valueOf(venda.getTotalVenda())};
            modelo.addRow(linha);
        }
        tableVendas.setModel(modelo);
    }
    

    public void editarClientes() {  //Método para editar os clientes que foram cadastrados no banco de dados
        int linhaSelecionada = tableClientes.getSelectedRow();

        if (linhaSelecionada == -1) {
            Alerta.Erro("Erro na linha", "Nenhuma linha selecionada");
        } else {
            String idCliente = (String) tableClientes.getValueAt(linhaSelecionada, 0);

            new EditarCadastroCliente(this,
                    rootPaneCheckingEnabled,
                    ClienteDAO.listarCliente(idCliente)).setVisible(true);
        }
        listaClientes();
    }

    public void editarFuncionarios() {  //Método para editar os funcionarios que foram cadastrados no banco de dados
        int linhaSelecionada = tableFuncionarios.getSelectedRow();

        if (linhaSelecionada == -1) {
            Alerta.Erro("Erro na linha", "Nenhuma linha selecionada");
        } else {
            String idFuncionario = (String) tableFuncionarios.getValueAt(linhaSelecionada, 0);

            new EditarCadastroFuncionario(this,
                    rootPaneCheckingEnabled,
                    FuncionarioDAO.listarFuncionario(idFuncionario)).setVisible(true);
        }
        listaFuncionarios();
    }

    public void editarProdutos() {  //Método para editar os produtos que foram cadastrados no banco de dados
        int linhaSelecionada = tableProdutos.getSelectedRow();

        if (linhaSelecionada == -1) {
            Alerta.Erro("Erro na linha", "Nenhuma linha selecionada");
        } else {
            String idProduto = (String) tableProdutos.getValueAt(linhaSelecionada, 0);

            new EditarCadastroProduto(this,
                    rootPaneCheckingEnabled,
                    ProdutoDAO.listarProduto(idProduto)).setVisible(true);
        }
        listaProdutos();
    }

    public void excluirClientes() { ////Método para excluir os clientes que foram cadastrados no banco de dados
        int linhaSelecionada = tableClientes.getSelectedRow();

        if (linhaSelecionada == -1) {
            Alerta.Erro("Erro na linha", "Nenhuma linha selecionada");
        } else {
            String idCliente = (String) tableClientes.getValueAt(linhaSelecionada, 0);

            ClienteDAO.excluirClientes(idCliente);
        }
        listaClientes();
    }

    public void excluirFuncionarios() { ////Método para excluir os funcionarios que foram cadastrados no banco de dados
        int linhaSelecionada = tableFuncionarios.getSelectedRow();

        if (linhaSelecionada == -1) {
            Alerta.Erro("Erro na linha", "Nenhuma linha selecionada");
        } else {
            String idFuncionario = (String) tableFuncionarios.getValueAt(linhaSelecionada, 0);

            FuncionarioDAO.excluirFuncionario(idFuncionario);
        }
        listaFuncionarios();
    }

    public void excluirProdutos() { ////Método para excluir os produtos que foram cadastrados no banco de dados
        int linhaSelecionada = tableProdutos.getSelectedRow();

        if (linhaSelecionada == -1) {
            Alerta.Erro("Erro na linha", "Nenhuma linha selecionada");
        } else {
            String idProduto = (String) tableProdutos.getValueAt(linhaSelecionada, 0);

            ProdutoDAO.excluirProdutos(idProduto);
        }
        listaProdutos();
    }
    
    public void excluirVendas() { ////Método para excluir as vendas que foram cadastrados no banco de dados
        int linhaSelecionada = tableVendas.getSelectedRow();

        if (linhaSelecionada == -1) {
            Alerta.Erro("Erro na linha", "Nenhuma linha selecionada");
        } else {
            String idVenda = (String) tableVendas.getValueAt(linhaSelecionada, 0);

            VendaDAO.excluirVendas(idVenda);
        }
        listaVendas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sidebar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buttonCadastrarC = new customs.botaoBorda();
        buttonCadastrarP = new customs.botaoBorda();
        buttonExibirC = new customs.botaoBorda();
        buttonCadastrarV = new customs.botaoBorda();
        buttonExibirV = new customs.botaoBorda();
        buttonExibirP = new customs.botaoBorda();
        buttonSair = new customs.botaoBorda();
        buttonExibirF = new customs.botaoBorda();
        buttonCadastrarF = new customs.botaoBorda();
        panelTelas = new javax.swing.JPanel();
        panelFuncionario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tfFCep = new javax.swing.JTextField();
        tfFNome = new javax.swing.JTextField();
        cbFCargos = new javax.swing.JComboBox<>();
        tfFCpf = new javax.swing.JTextField();
        tfFNum = new javax.swing.JTextField();
        tfFComplemento = new javax.swing.JTextField();
        tfFLogradouro = new javax.swing.JTextField();
        tfFTelefone = new javax.swing.JTextField();
        buttonCadastrarFunc = new customs.botaoBorda();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        tfFSenha = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        tfFLogin = new javax.swing.JTextField();
        buttonLimparF = new customs.botaoBorda();
        panelCliente = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonCadastrarCliente = new customs.botaoBorda();
        jLabel5 = new javax.swing.JLabel();
        tfNomeC = new javax.swing.JTextField();
        tfCpfC = new javax.swing.JTextField();
        tfTelefoneC = new javax.swing.JTextField();
        buttonLimparC = new customs.botaoBorda();
        cbSexoC = new javax.swing.JComboBox<>();
        panelProduto = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        buttonCadastrarProduto = new customs.botaoBorda();
        buttonLimparP = new customs.botaoBorda();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfCadastroPEstoque = new javax.swing.JTextField();
        tfCadastroPNome = new javax.swing.JTextField();
        tfCadastroPPreco = new javax.swing.JTextField();
        tfCadastroPCod = new javax.swing.JTextField();
        cbPCategoria = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        taCadastroPDesc = new javax.swing.JTextArea();
        panelVenda = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tfVendaPreco = new javax.swing.JTextField();
        tfVendaCodigo = new javax.swing.JTextField();
        tfVendaQtd = new javax.swing.JTextField();
        buttonVPesquisarCPF = new customs.botaoBorda();
        buttonVAdicionar = new customs.botaoBorda();
        buttonVPesquisarCod = new customs.botaoBorda();
        tfVendaData = new javax.swing.JTextField();
        tfVendaCPFC = new javax.swing.JTextField();
        tfVendaProduto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableVenda = new javax.swing.JTable();
        buttonVPagar = new customs.botaoBorda();
        buttonVLimpar = new customs.botaoBorda();
        panelExibirClientes = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        tfBuscarClientes = new javax.swing.JTextField();
        buttonVPesquisarAdc = new customs.botaoBorda();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        buttonExibirCEditar = new customs.botaoBorda();
        buttonExibirCExcluir = new customs.botaoBorda();
        buttonExibirCCad = new customs.botaoBorda();
        panelExibirProdutos = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        tfBuscarProdutos = new javax.swing.JTextField();
        buttonPPesquisar = new customs.botaoBorda();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();
        buttonExibirPEditar = new customs.botaoBorda();
        buttonExibirPExcluir = new customs.botaoBorda();
        buttonExibirPCad = new customs.botaoBorda();
        panelExibirVendas = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        tfBuscarVendas = new javax.swing.JTextField();
        buttonVPesquisar = new customs.botaoBorda();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableVendas = new javax.swing.JTable();
        buttonExibirVCad = new customs.botaoBorda();
        buttonExibirVExcluir = new customs.botaoBorda();
        panelExibirFuncionario = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        tfBuscarFuncionarios = new javax.swing.JTextField();
        buttonFPesquisar = new customs.botaoBorda();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableFuncionarios = new javax.swing.JTable();
        buttonExibirFEditar = new customs.botaoBorda();
        buttonExibirFExcluir = new customs.botaoBorda();
        buttonExibirFCad = new customs.botaoBorda();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        sidebar.setBackground(new java.awt.Color(226, 226, 226));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/LogoGG (110).png"))); // NOI18N

        buttonCadastrarC.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Cliente(20).png"))); // NOI18N
        buttonCadastrarC.setText("  Cadastrar Cliente");
        buttonCadastrarC.setBorderPainted(false);
        buttonCadastrarC.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarC.setCorBorda(new java.awt.Color(226, 226, 226));
        buttonCadastrarC.setCorClick(new java.awt.Color(27, 98, 165));
        buttonCadastrarC.setCorHover(new java.awt.Color(27, 98, 165));
        buttonCadastrarC.setFocusPainted(false);
        buttonCadastrarC.setFocusable(false);
        buttonCadastrarC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        buttonCadastrarC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonCadastrarC.setRadius(20);
        buttonCadastrarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarCActionPerformed(evt);
            }
        });

        buttonCadastrarP.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Produto(20).png"))); // NOI18N
        buttonCadastrarP.setText("  Cadastrar Produto");
        buttonCadastrarP.setBorderPainted(false);
        buttonCadastrarP.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarP.setCorBorda(new java.awt.Color(226, 226, 226));
        buttonCadastrarP.setFocusable(false);
        buttonCadastrarP.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        buttonCadastrarP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonCadastrarP.setRadius(20);
        buttonCadastrarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarPActionPerformed(evt);
            }
        });

        buttonExibirC.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Clientes(20).png"))); // NOI18N
        buttonExibirC.setText("  Exibir Clientes");
        buttonExibirC.setBorderPainted(false);
        buttonExibirC.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirC.setCorBorda(new java.awt.Color(226, 226, 226));
        buttonExibirC.setFocusable(false);
        buttonExibirC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        buttonExibirC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonExibirC.setRadius(20);
        buttonExibirC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirCActionPerformed(evt);
            }
        });

        buttonCadastrarV.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Venda(20).png"))); // NOI18N
        buttonCadastrarV.setText("  Cadastrar Venda");
        buttonCadastrarV.setBorderPainted(false);
        buttonCadastrarV.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarV.setCorBorda(new java.awt.Color(226, 226, 226));
        buttonCadastrarV.setFocusable(false);
        buttonCadastrarV.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        buttonCadastrarV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonCadastrarV.setRadius(20);
        buttonCadastrarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarVActionPerformed(evt);
            }
        });

        buttonExibirV.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Vendas(20).png"))); // NOI18N
        buttonExibirV.setText("  Exibir Vendas");
        buttonExibirV.setBorderPainted(false);
        buttonExibirV.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirV.setCorBorda(new java.awt.Color(226, 226, 226));
        buttonExibirV.setFocusable(false);
        buttonExibirV.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        buttonExibirV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonExibirV.setRadius(20);
        buttonExibirV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirVActionPerformed(evt);
            }
        });

        buttonExibirP.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Produtos(20).png"))); // NOI18N
        buttonExibirP.setText("  Exibir Produtos");
        buttonExibirP.setBorderPainted(false);
        buttonExibirP.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirP.setCorBorda(new java.awt.Color(226, 226, 226));
        buttonExibirP.setFocusable(false);
        buttonExibirP.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        buttonExibirP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonExibirP.setRadius(20);
        buttonExibirP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirPActionPerformed(evt);
            }
        });

        buttonSair.setForeground(new java.awt.Color(255, 255, 255));
        buttonSair.setText("Sair");
        buttonSair.setBorderPainted(false);
        buttonSair.setCor(new java.awt.Color(0, 51, 102));
        buttonSair.setCorBorda(new java.awt.Color(226, 226, 226));
        buttonSair.setFocusable(false);
        buttonSair.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonSair.setRadius(32);
        buttonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSairActionPerformed(evt);
            }
        });

        buttonExibirF.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/funcionarios(20).png"))); // NOI18N
        buttonExibirF.setText("  Exibir Funcionarios");
        buttonExibirF.setBorderPainted(false);
        buttonExibirF.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirF.setCorBorda(new java.awt.Color(226, 226, 226));
        buttonExibirF.setFocusable(false);
        buttonExibirF.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        buttonExibirF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonExibirF.setRadius(20);
        buttonExibirF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirFActionPerformed(evt);
            }
        });

        buttonCadastrarF.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/funcionario(20).png"))); // NOI18N
        buttonCadastrarF.setText("  Cadastrar Funcionário");
        buttonCadastrarF.setBorderPainted(false);
        buttonCadastrarF.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarF.setCorBorda(new java.awt.Color(226, 226, 226));
        buttonCadastrarF.setCorClick(new java.awt.Color(27, 98, 165));
        buttonCadastrarF.setCorHover(new java.awt.Color(27, 98, 165));
        buttonCadastrarF.setFocusPainted(false);
        buttonCadastrarF.setFocusable(false);
        buttonCadastrarF.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        buttonCadastrarF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonCadastrarF.setRadius(20);
        buttonCadastrarF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sidebarLayout = new javax.swing.GroupLayout(sidebar);
        sidebar.setLayout(sidebarLayout);
        sidebarLayout.setHorizontalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonCadastrarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonCadastrarP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonCadastrarV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonExibirC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonExibirV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonExibirF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonExibirP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGroup(sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidebarLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel1))
                    .addGroup(sidebarLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(buttonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(buttonCadastrarF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidebarLayout.setVerticalGroup(
            sidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonCadastrarF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCadastrarC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCadastrarP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCadastrarV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExibirC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExibirP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExibirV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExibirF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        panelTelas.setBackground(new java.awt.Color(245, 245, 245));
        panelTelas.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("CEP:");

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Nome:");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("CPF:");

        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Cargo:");

        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Logradouro:");

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Numero:");

        jLabel28.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Complemento:");

        tfFCep.setBackground(new java.awt.Color(255, 255, 255));
        tfFCep.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfFCep.setForeground(new java.awt.Color(0, 0, 0));

        tfFNome.setBackground(new java.awt.Color(255, 255, 255));
        tfFNome.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfFNome.setForeground(new java.awt.Color(0, 0, 0));

        cbFCargos.setBackground(new java.awt.Color(255, 255, 255));
        cbFCargos.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        cbFCargos.setForeground(new java.awt.Color(0, 0, 0));

        tfFCpf.setBackground(new java.awt.Color(255, 255, 255));
        tfFCpf.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfFCpf.setForeground(new java.awt.Color(0, 0, 0));

        tfFNum.setBackground(new java.awt.Color(255, 255, 255));
        tfFNum.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfFNum.setForeground(new java.awt.Color(0, 0, 0));

        tfFComplemento.setBackground(new java.awt.Color(255, 255, 255));
        tfFComplemento.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfFComplemento.setForeground(new java.awt.Color(0, 0, 0));

        tfFLogradouro.setBackground(new java.awt.Color(255, 255, 255));
        tfFLogradouro.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfFLogradouro.setForeground(new java.awt.Color(0, 0, 0));

        tfFTelefone.setBackground(new java.awt.Color(255, 255, 255));
        tfFTelefone.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfFTelefone.setForeground(new java.awt.Color(0, 0, 0));

        buttonCadastrarFunc.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarFunc.setText("Cadastrar");
        buttonCadastrarFunc.setBorderPainted(false);
        buttonCadastrarFunc.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarFunc.setFocusable(false);
        buttonCadastrarFunc.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        buttonCadastrarFunc.setRadius(32);
        buttonCadastrarFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarFuncActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Senha:");

        jLabel31.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Login:");

        tfFSenha.setBackground(new java.awt.Color(255, 255, 255));
        tfFSenha.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfFSenha.setForeground(new java.awt.Color(0, 0, 0));

        jLabel32.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Tel:");

        tfFLogin.setBackground(new java.awt.Color(255, 255, 255));
        tfFLogin.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfFLogin.setForeground(new java.awt.Color(0, 0, 0));

        buttonLimparF.setForeground(new java.awt.Color(255, 255, 255));
        buttonLimparF.setText("Limpar");
        buttonLimparF.setBorderPainted(false);
        buttonLimparF.setCor(new java.awt.Color(0, 51, 102));
        buttonLimparF.setCorBorda(new java.awt.Color(245, 245, 245));
        buttonLimparF.setFocusable(false);
        buttonLimparF.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonLimparF.setRadius(26);
        buttonLimparF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimparFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(tfFLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel31)
                                    .addComponent(buttonLimparF, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfFNome, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfFCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfFCep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(121, 121, 121)
                                                .addComponent(tfFComplemento))
                                            .addComponent(tfFLogin))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel26)
                                                    .addComponent(jLabel24))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbFCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tfFNum, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel30)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(tfFSenha))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel32)
                                                        .addGap(60, 60, 60)
                                                        .addComponent(tfFTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(buttonCadastrarFunc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel25)
                            .addComponent(jLabel28))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(tfFNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(cbFCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(tfFCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(tfFNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(tfFLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(tfFComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30)
                    .addComponent(tfFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCadastrarFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLimparF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout panelFuncionarioLayout = new javax.swing.GroupLayout(panelFuncionario);
        panelFuncionario.setLayout(panelFuncionarioLayout);
        panelFuncionarioLayout.setHorizontalGroup(
            panelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelFuncionarioLayout.setVerticalGroup(
            panelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTelas.add(panelFuncionario, "cadastroFunc");

        panelCliente.setBackground(new java.awt.Color(245, 245, 245));

        jPanel6.setBackground(new java.awt.Color(245, 245, 245));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nome:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("CPF:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Telefone:");

        buttonCadastrarCliente.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarCliente.setText("Cadastrar");
        buttonCadastrarCliente.setBorderPainted(false);
        buttonCadastrarCliente.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarCliente.setCorBorda(new java.awt.Color(245, 245, 245));
        buttonCadastrarCliente.setFocusable(false);
        buttonCadastrarCliente.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonCadastrarCliente.setRadius(26);
        buttonCadastrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarClienteActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Sexo:");

        tfNomeC.setBackground(new java.awt.Color(255, 255, 255));
        tfNomeC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfNomeC.setForeground(new java.awt.Color(0, 0, 0));

        tfCpfC.setBackground(new java.awt.Color(255, 255, 255));
        tfCpfC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfCpfC.setForeground(new java.awt.Color(0, 0, 0));

        tfTelefoneC.setBackground(new java.awt.Color(255, 255, 255));
        tfTelefoneC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfTelefoneC.setForeground(new java.awt.Color(0, 0, 0));

        buttonLimparC.setForeground(new java.awt.Color(255, 255, 255));
        buttonLimparC.setText("Limpar");
        buttonLimparC.setBorderPainted(false);
        buttonLimparC.setCor(new java.awt.Color(0, 51, 102));
        buttonLimparC.setCorBorda(new java.awt.Color(245, 245, 245));
        buttonLimparC.setFocusable(false);
        buttonLimparC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonLimparC.setRadius(26);
        buttonLimparC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimparCActionPerformed(evt);
            }
        });

        cbSexoC.setBackground(new java.awt.Color(255, 255, 255));
        cbSexoC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        cbSexoC.setForeground(new java.awt.Color(0, 0, 0));
        cbSexoC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Feminino", "Masculino", "Outros" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(buttonLimparC, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(tfCpfC, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cbSexoC, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfNomeC)
                            .addComponent(tfTelefoneC))))
                .addGap(66, 66, 66))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNomeC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(tfCpfC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(cbSexoC, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTelefoneC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLimparC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout panelClienteLayout = new javax.swing.GroupLayout(panelCliente);
        panelCliente.setLayout(panelClienteLayout);
        panelClienteLayout.setHorizontalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelClienteLayout.setVerticalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTelas.add(panelCliente, "cadastroCli");

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));

        buttonCadastrarProduto.setForeground(new java.awt.Color(255, 255, 255));
        buttonCadastrarProduto.setText("Cadastrar");
        buttonCadastrarProduto.setBorderPainted(false);
        buttonCadastrarProduto.setCor(new java.awt.Color(0, 51, 102));
        buttonCadastrarProduto.setCorBorda(new java.awt.Color(245, 245, 245));
        buttonCadastrarProduto.setFocusable(false);
        buttonCadastrarProduto.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonCadastrarProduto.setRadius(26);
        buttonCadastrarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarProdutoActionPerformed(evt);
            }
        });

        buttonLimparP.setForeground(new java.awt.Color(255, 255, 255));
        buttonLimparP.setText("Limpar");
        buttonLimparP.setBorderPainted(false);
        buttonLimparP.setCor(new java.awt.Color(0, 51, 102));
        buttonLimparP.setCorBorda(new java.awt.Color(245, 245, 245));
        buttonLimparP.setFocusable(false);
        buttonLimparP.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonLimparP.setRadius(26);
        buttonLimparP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimparPActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Código:");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Nome:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Descrição:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Preço:");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Categoria:");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Estoque:");

        tfCadastroPEstoque.setBackground(new java.awt.Color(255, 255, 255));
        tfCadastroPEstoque.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfCadastroPEstoque.setForeground(new java.awt.Color(0, 0, 0));

        tfCadastroPNome.setBackground(new java.awt.Color(255, 255, 255));
        tfCadastroPNome.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfCadastroPNome.setForeground(new java.awt.Color(0, 0, 0));

        tfCadastroPPreco.setBackground(new java.awt.Color(255, 255, 255));
        tfCadastroPPreco.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfCadastroPPreco.setForeground(new java.awt.Color(0, 0, 0));

        tfCadastroPCod.setBackground(new java.awt.Color(255, 255, 255));
        tfCadastroPCod.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfCadastroPCod.setForeground(new java.awt.Color(0, 0, 0));
        tfCadastroPCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCadastroPCodActionPerformed(evt);
            }
        });

        cbPCategoria.setBackground(new java.awt.Color(255, 255, 255));
        cbPCategoria.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        cbPCategoria.setForeground(new java.awt.Color(0, 0, 0));
        cbPCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));

        taCadastroPDesc.setBackground(new java.awt.Color(255, 255, 255));
        taCadastroPDesc.setColumns(20);
        taCadastroPDesc.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        taCadastroPDesc.setForeground(new java.awt.Color(0, 0, 0));
        taCadastroPDesc.setRows(5);
        jScrollPane1.setViewportView(taCadastroPDesc);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfCadastroPPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfCadastroPEstoque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbPCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfCadastroPCod, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(tfCadastroPNome, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(482, 482, 482)
                                .addComponent(jLabel6))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(buttonLimparP, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonCadastrarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfCadastroPNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(tfCadastroPPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCadastroPCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(tfCadastroPEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCadastrarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLimparP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout panelProdutoLayout = new javax.swing.GroupLayout(panelProduto);
        panelProduto.setLayout(panelProdutoLayout);
        panelProdutoLayout.setHorizontalGroup(
            panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelProdutoLayout.setVerticalGroup(
            panelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTelas.add(panelProduto, "cadastroProd");

        jPanel8.setBackground(new java.awt.Color(245, 245, 245));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("CPF:");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Produto:");

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Código:");

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Data:");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Preço:");

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Qtd:");

        tfVendaPreco.setBackground(new java.awt.Color(255, 255, 255));
        tfVendaPreco.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfVendaPreco.setForeground(new java.awt.Color(0, 0, 0));

        tfVendaCodigo.setBackground(new java.awt.Color(255, 255, 255));
        tfVendaCodigo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfVendaCodigo.setForeground(new java.awt.Color(0, 0, 0));

        tfVendaQtd.setBackground(new java.awt.Color(255, 255, 255));
        tfVendaQtd.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfVendaQtd.setForeground(new java.awt.Color(0, 0, 0));

        buttonVPesquisarCPF.setForeground(new java.awt.Color(255, 255, 255));
        buttonVPesquisarCPF.setText("Pesquisar");
        buttonVPesquisarCPF.setBorderPainted(false);
        buttonVPesquisarCPF.setCor(new java.awt.Color(0, 51, 102));
        buttonVPesquisarCPF.setFocusPainted(false);
        buttonVPesquisarCPF.setFocusable(false);
        buttonVPesquisarCPF.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonVPesquisarCPF.setRadius(32);
        buttonVPesquisarCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVPesquisarCPFActionPerformed(evt);
            }
        });

        buttonVAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        buttonVAdicionar.setText("Adicionar");
        buttonVAdicionar.setBorderPainted(false);
        buttonVAdicionar.setCor(new java.awt.Color(0, 51, 102));
        buttonVAdicionar.setFocusPainted(false);
        buttonVAdicionar.setFocusable(false);
        buttonVAdicionar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonVAdicionar.setRadius(32);
        buttonVAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVAdicionarActionPerformed(evt);
            }
        });

        buttonVPesquisarCod.setForeground(new java.awt.Color(255, 255, 255));
        buttonVPesquisarCod.setText("Pesquisar");
        buttonVPesquisarCod.setBorderPainted(false);
        buttonVPesquisarCod.setCor(new java.awt.Color(0, 51, 102));
        buttonVPesquisarCod.setFocusPainted(false);
        buttonVPesquisarCod.setFocusable(false);
        buttonVPesquisarCod.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonVPesquisarCod.setRadius(32);
        buttonVPesquisarCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVPesquisarCodActionPerformed(evt);
            }
        });

        tfVendaData.setBackground(new java.awt.Color(255, 255, 255));
        tfVendaData.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfVendaData.setForeground(new java.awt.Color(0, 0, 0));
        tfVendaData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfVendaDataActionPerformed(evt);
            }
        });

        tfVendaCPFC.setBackground(new java.awt.Color(255, 255, 255));
        tfVendaCPFC.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfVendaCPFC.setForeground(new java.awt.Color(0, 0, 0));

        tfVendaProduto.setBackground(new java.awt.Color(255, 255, 255));
        tfVendaProduto.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfVendaProduto.setForeground(new java.awt.Color(0, 0, 0));

        tableVenda.setBackground(new java.awt.Color(255, 255, 255));
        tableVenda.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        tableVenda.setForeground(new java.awt.Color(0, 0, 0));
        tableVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Produto", "Qtd", "Preço", "SubTotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVenda.setRowHeight(30);
        tableVenda.setSelectionBackground(new java.awt.Color(223, 231, 238));
        tableVenda.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(tableVenda);
        if (tableVenda.getColumnModel().getColumnCount() > 0) {
            tableVenda.getColumnModel().getColumn(0).setMinWidth(90);
            tableVenda.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableVenda.getColumnModel().getColumn(0).setMaxWidth(110);
            tableVenda.getColumnModel().getColumn(2).setMinWidth(40);
            tableVenda.getColumnModel().getColumn(2).setPreferredWidth(50);
            tableVenda.getColumnModel().getColumn(2).setMaxWidth(60);
            tableVenda.getColumnModel().getColumn(3).setMinWidth(140);
            tableVenda.getColumnModel().getColumn(3).setPreferredWidth(150);
            tableVenda.getColumnModel().getColumn(3).setMaxWidth(160);
            tableVenda.getColumnModel().getColumn(4).setMinWidth(190);
            tableVenda.getColumnModel().getColumn(4).setPreferredWidth(200);
            tableVenda.getColumnModel().getColumn(4).setMaxWidth(210);
        }
        DefaultTableCellRenderer centralizarTable = new DefaultTableCellRenderer();
        centralizarTable.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tableVenda.getColumnCount(); i++) {
            tableVenda.getColumnModel().getColumn(i).setCellRenderer(centralizarTable);
        }

        TableCellRenderer baseRender4 = tableVenda.getTableHeader().getDefaultRenderer();
        tableVenda.getTableHeader().setDefaultRenderer((tbl, value, isSelected, hasFocus, row, column) -> {
            JComponent comp = (JComponent) baseRender4.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
            comp.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            return comp;
        });
        tableVenda.setIntercellSpacing(new Dimension(0, 0));
        tableVenda.setForeground(new java.awt.Color(0, 0, 0));
        tableVenda.getTableHeader().setBackground(new Color(0, 64, 121));
        tableVenda.getTableHeader().setForeground(new Color(255, 255,255));
        tableVenda.getTableHeader().setSize(50, 50);
        tableVenda.setShowVerticalLines(false);
        tableVenda.getTableHeader().setFont( new Font("Segoe UI Semibold",Font.BOLD, 18));

        buttonVPagar.setForeground(new java.awt.Color(255, 255, 255));
        buttonVPagar.setText("Pagar");
        buttonVPagar.setBorderPainted(false);
        buttonVPagar.setCor(new java.awt.Color(0, 51, 102));
        buttonVPagar.setFocusPainted(false);
        buttonVPagar.setFocusable(false);
        buttonVPagar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonVPagar.setRadius(32);
        buttonVPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVPagarActionPerformed(evt);
            }
        });

        buttonVLimpar.setForeground(new java.awt.Color(255, 255, 255));
        buttonVLimpar.setText("Limpar");
        buttonVLimpar.setBorderPainted(false);
        buttonVLimpar.setCor(new java.awt.Color(0, 51, 102));
        buttonVLimpar.setFocusPainted(false);
        buttonVLimpar.setFocusable(false);
        buttonVLimpar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonVLimpar.setRadius(32);
        buttonVLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(buttonVLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonVPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(tfVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfVendaData, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfVendaCPFC)
                            .addComponent(tfVendaCodigo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(tfVendaPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfVendaQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonVPesquisarCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonVPesquisarCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonVAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tfVendaCPFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonVPesquisarCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfVendaData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(tfVendaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfVendaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonVPesquisarCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfVendaPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(tfVendaQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonVAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonVPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonVLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelVendaLayout = new javax.swing.GroupLayout(panelVenda);
        panelVenda.setLayout(panelVendaLayout);
        panelVendaLayout.setHorizontalGroup(
            panelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelVendaLayout.setVerticalGroup(
            panelVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTelas.add(panelVenda, "cadastroVenda");

        jPanel9.setBackground(new java.awt.Color(245, 245, 245));

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("CPF:");

        tfBuscarClientes.setBackground(new java.awt.Color(255, 255, 255));
        tfBuscarClientes.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfBuscarClientes.setForeground(new java.awt.Color(0, 0, 0));

        buttonVPesquisarAdc.setForeground(new java.awt.Color(255, 255, 255));
        buttonVPesquisarAdc.setText("Pesquisar");
        buttonVPesquisarAdc.setBorderPainted(false);
        buttonVPesquisarAdc.setCor(new java.awt.Color(0, 51, 102));
        buttonVPesquisarAdc.setFocusPainted(false);
        buttonVPesquisarAdc.setFocusable(false);
        buttonVPesquisarAdc.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonVPesquisarAdc.setRadius(32);
        buttonVPesquisarAdc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVPesquisarAdcActionPerformed(evt);
            }
        });

        tableClientes.setBackground(new java.awt.Color(255, 255, 255));
        tableClientes.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        tableClientes.setForeground(new java.awt.Color(0, 0, 0));
        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Sexo", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClientes.setFocusable(false);
        tableClientes.setRowHeight(30);
        tableClientes.setSelectionBackground(new java.awt.Color(223, 231, 238));
        tableClientes.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(tableClientes);
        if (tableClientes.getColumnModel().getColumnCount() > 0) {
            tableClientes.getColumnModel().getColumn(0).setMinWidth(80);
            tableClientes.getColumnModel().getColumn(0).setPreferredWidth(90);
            tableClientes.getColumnModel().getColumn(0).setMaxWidth(100);
            tableClientes.getColumnModel().getColumn(1).setMinWidth(290);
            tableClientes.getColumnModel().getColumn(1).setPreferredWidth(300);
            tableClientes.getColumnModel().getColumn(1).setMaxWidth(310);
            tableClientes.getColumnModel().getColumn(2).setMinWidth(290);
            tableClientes.getColumnModel().getColumn(2).setPreferredWidth(300);
            tableClientes.getColumnModel().getColumn(2).setMaxWidth(310);
            tableClientes.getColumnModel().getColumn(3).setMinWidth(190);
            tableClientes.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableClientes.getColumnModel().getColumn(3).setMaxWidth(210);
        }
        DefaultTableCellRenderer centralizarTabela = new DefaultTableCellRenderer();
        centralizarTabela.setHorizontalAlignment(SwingConstants.CENTER);

        tableClientes.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < tableClientes.getColumnCount(); i++) {
            tableClientes.getColumnModel().getColumn(i).setCellRenderer(centralizarTabela);
        }

        TableCellRenderer baseRender = tableClientes.getTableHeader().getDefaultRenderer();
        tableClientes.getTableHeader().setDefaultRenderer((tbl, value, isSelected, hasFocus, row, column) -> {
            JComponent comp = (JComponent) baseRender.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
            comp.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            return comp;
        });
        tableClientes.setIntercellSpacing(new Dimension(0, 0));
        tableClientes.setForeground(new java.awt.Color(0, 0, 0));
        tableClientes.getTableHeader().setBackground(new Color(0, 64, 121));
        tableClientes.getTableHeader().setForeground(new Color(255, 255,255));
        tableClientes.getTableHeader().setSize(50, 50);
        tableClientes.setShowVerticalLines(false);
        tableClientes.getTableHeader().setFont( new Font("Segoe UI Semibold",Font.BOLD, 18));

        buttonExibirCEditar.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirCEditar.setText("Editar");
        buttonExibirCEditar.setBorderPainted(false);
        buttonExibirCEditar.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirCEditar.setFocusPainted(false);
        buttonExibirCEditar.setFocusable(false);
        buttonExibirCEditar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirCEditar.setRadius(32);
        buttonExibirCEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirCEditarActionPerformed(evt);
            }
        });

        buttonExibirCExcluir.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirCExcluir.setText("Excluir");
        buttonExibirCExcluir.setBorderPainted(false);
        buttonExibirCExcluir.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirCExcluir.setFocusPainted(false);
        buttonExibirCExcluir.setFocusable(false);
        buttonExibirCExcluir.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirCExcluir.setRadius(32);
        buttonExibirCExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirCExcluirActionPerformed(evt);
            }
        });

        buttonExibirCCad.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirCCad.setText("Cadastrar");
        buttonExibirCCad.setBorderPainted(false);
        buttonExibirCCad.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirCCad.setFocusPainted(false);
        buttonExibirCCad.setFocusable(false);
        buttonExibirCCad.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirCCad.setRadius(32);
        buttonExibirCCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirCCadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(buttonExibirCCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonExibirCEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(364, 364, 364)
                        .addComponent(buttonExibirCExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(tfBuscarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(buttonVPesquisarAdc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(tfBuscarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonVPesquisarAdc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonExibirCCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExibirCEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExibirCExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout panelExibirClientesLayout = new javax.swing.GroupLayout(panelExibirClientes);
        panelExibirClientes.setLayout(panelExibirClientesLayout);
        panelExibirClientesLayout.setHorizontalGroup(
            panelExibirClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelExibirClientesLayout.setVerticalGroup(
            panelExibirClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTelas.add(panelExibirClientes, "exibirCli");

        jPanel10.setBackground(new java.awt.Color(245, 245, 245));

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Código:");

        tfBuscarProdutos.setBackground(new java.awt.Color(255, 255, 255));
        tfBuscarProdutos.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfBuscarProdutos.setForeground(new java.awt.Color(0, 0, 0));

        buttonPPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        buttonPPesquisar.setText("Pesquisar");
        buttonPPesquisar.setBorderPainted(false);
        buttonPPesquisar.setCor(new java.awt.Color(0, 51, 102));
        buttonPPesquisar.setFocusPainted(false);
        buttonPPesquisar.setFocusable(false);
        buttonPPesquisar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonPPesquisar.setRadius(32);
        buttonPPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPPesquisarActionPerformed(evt);
            }
        });

        tableProdutos.setBackground(new java.awt.Color(255, 255, 255));
        tableProdutos.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        tableProdutos.setForeground(new java.awt.Color(0, 0, 0));
        tableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Código", "Produto", "Categoria", "Qtd Est.", "Preço", "Descrição"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProdutos.setFocusable(false);
        tableProdutos.setRowHeight(30);
        tableProdutos.setSelectionBackground(new java.awt.Color(223, 231, 238));
        tableProdutos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane4.setViewportView(tableProdutos);
        if (tableProdutos.getColumnModel().getColumnCount() > 0) {
            tableProdutos.getColumnModel().getColumn(0).setMinWidth(40);
            tableProdutos.getColumnModel().getColumn(0).setPreferredWidth(70);
            tableProdutos.getColumnModel().getColumn(0).setMaxWidth(80);
            tableProdutos.getColumnModel().getColumn(1).setMinWidth(50);
            tableProdutos.getColumnModel().getColumn(1).setPreferredWidth(120);
            tableProdutos.getColumnModel().getColumn(1).setMaxWidth(130);
            tableProdutos.getColumnModel().getColumn(2).setMinWidth(100);
            tableProdutos.getColumnModel().getColumn(2).setPreferredWidth(250);
            tableProdutos.getColumnModel().getColumn(2).setMaxWidth(260);
            tableProdutos.getColumnModel().getColumn(3).setMinWidth(80);
            tableProdutos.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableProdutos.getColumnModel().getColumn(3).setMaxWidth(210);
            tableProdutos.getColumnModel().getColumn(4).setMinWidth(40);
            tableProdutos.getColumnModel().getColumn(4).setPreferredWidth(90);
            tableProdutos.getColumnModel().getColumn(4).setMaxWidth(90);
            tableProdutos.getColumnModel().getColumn(5).setMinWidth(80);
            tableProdutos.getColumnModel().getColumn(5).setPreferredWidth(150);
            tableProdutos.getColumnModel().getColumn(5).setMaxWidth(160);
            tableProdutos.getColumnModel().getColumn(6).setMinWidth(100);
            tableProdutos.getColumnModel().getColumn(6).setPreferredWidth(300);
            tableProdutos.getColumnModel().getColumn(6).setMaxWidth(310);
        }
        tableProdutos.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < tableProdutos.getColumnCount(); i++) {
            tableProdutos.getColumnModel().getColumn(i).setCellRenderer(centralizarTabela);
        }

        TableCellRenderer baseRender1 = tableProdutos.getTableHeader().getDefaultRenderer();
        tableProdutos.getTableHeader().setDefaultRenderer((tbl, value, isSelected, hasFocus, row, column) -> {
            JComponent comp = (JComponent) baseRender1.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
            comp.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            return comp;
        });
        tableProdutos.setIntercellSpacing(new Dimension(0, 0));
        tableProdutos.setForeground(new java.awt.Color(0, 0, 0));
        tableProdutos.getTableHeader().setBackground(new Color(0, 64, 121));
        tableProdutos.getTableHeader().setForeground(new Color(255, 255,255));
        tableProdutos.getTableHeader().setSize(50, 50);
        tableProdutos.setShowVerticalLines(false);
        tableProdutos.getTableHeader().setFont( new Font("Segoe UI Semibold",Font.BOLD, 18));

        buttonExibirPEditar.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirPEditar.setText("Editar");
        buttonExibirPEditar.setBorderPainted(false);
        buttonExibirPEditar.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirPEditar.setFocusPainted(false);
        buttonExibirPEditar.setFocusable(false);
        buttonExibirPEditar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirPEditar.setRadius(32);
        buttonExibirPEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirPEditarActionPerformed(evt);
            }
        });

        buttonExibirPExcluir.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirPExcluir.setText("Excluir");
        buttonExibirPExcluir.setBorderPainted(false);
        buttonExibirPExcluir.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirPExcluir.setFocusPainted(false);
        buttonExibirPExcluir.setFocusable(false);
        buttonExibirPExcluir.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirPExcluir.setRadius(32);
        buttonExibirPExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirPExcluirActionPerformed(evt);
            }
        });

        buttonExibirPCad.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirPCad.setText("Cadastrar");
        buttonExibirPCad.setBorderPainted(false);
        buttonExibirPCad.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirPCad.setFocusPainted(false);
        buttonExibirPCad.setFocusable(false);
        buttonExibirPCad.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirPCad.setRadius(32);
        buttonExibirPCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirPCadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(buttonExibirPCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonExibirPEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(359, 359, 359)
                        .addComponent(buttonExibirPExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(tfBuscarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(buttonPPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tfBuscarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonPPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonExibirPCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExibirPEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExibirPExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout panelExibirProdutosLayout = new javax.swing.GroupLayout(panelExibirProdutos);
        panelExibirProdutos.setLayout(panelExibirProdutosLayout);
        panelExibirProdutosLayout.setHorizontalGroup(
            panelExibirProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelExibirProdutosLayout.setVerticalGroup(
            panelExibirProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTelas.add(panelExibirProdutos, "exibirProd");

        jPanel11.setBackground(new java.awt.Color(245, 245, 245));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Cliente:");

        tfBuscarVendas.setBackground(new java.awt.Color(255, 255, 255));
        tfBuscarVendas.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfBuscarVendas.setForeground(new java.awt.Color(0, 0, 0));

        buttonVPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        buttonVPesquisar.setText("Pesquisar");
        buttonVPesquisar.setBorderPainted(false);
        buttonVPesquisar.setCor(new java.awt.Color(0, 51, 102));
        buttonVPesquisar.setFocusPainted(false);
        buttonVPesquisar.setFocusable(false);
        buttonVPesquisar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonVPesquisar.setRadius(32);
        buttonVPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVPesquisarActionPerformed(evt);
            }
        });

        tableVendas.setBackground(new java.awt.Color(255, 255, 255));
        tableVendas.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        tableVendas.setForeground(new java.awt.Color(0, 0, 0));
        tableVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Cliente", "Data da Venda", "Qtd. Prod", "Total da Venda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVendas.setFocusable(false);
        tableVendas.setRowHeight(30);
        tableVendas.setSelectionBackground(new java.awt.Color(223, 231, 238));
        tableVendas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane5.setViewportView(tableVendas);
        if (tableVendas.getColumnModel().getColumnCount() > 0) {
            tableVendas.getColumnModel().getColumn(0).setMinWidth(70);
            tableVendas.getColumnModel().getColumn(0).setPreferredWidth(80);
            tableVendas.getColumnModel().getColumn(0).setMaxWidth(90);
            tableVendas.getColumnModel().getColumn(1).setMinWidth(440);
            tableVendas.getColumnModel().getColumn(1).setPreferredWidth(450);
            tableVendas.getColumnModel().getColumn(1).setMaxWidth(460);
            tableVendas.getColumnModel().getColumn(2).setMinWidth(190);
            tableVendas.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableVendas.getColumnModel().getColumn(2).setMaxWidth(210);
            tableVendas.getColumnModel().getColumn(3).setMinWidth(140);
            tableVendas.getColumnModel().getColumn(3).setPreferredWidth(150);
            tableVendas.getColumnModel().getColumn(3).setMaxWidth(160);
            tableVendas.getColumnModel().getColumn(4).setMinWidth(210);
            tableVendas.getColumnModel().getColumn(4).setPreferredWidth(220);
            tableVendas.getColumnModel().getColumn(4).setMaxWidth(230);
        }
        tableVendas.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < tableVendas.getColumnCount(); i++) {
            tableVendas.getColumnModel().getColumn(i).setCellRenderer(centralizarTabela);
        }

        TableCellRenderer baseRender2 = tableVendas.getTableHeader().getDefaultRenderer();
        tableVendas.getTableHeader().setDefaultRenderer((tbl, value, isSelected, hasFocus, row, column) -> {
            JComponent comp = (JComponent) baseRender2.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
            comp.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            return comp;
        });
        tableVendas.setIntercellSpacing(new Dimension(0, 0));
        tableVendas.setForeground(new java.awt.Color(0, 0, 0));
        tableVendas.getTableHeader().setBackground(new Color(0, 64, 121));
        tableVendas.getTableHeader().setForeground(new Color(255, 255,255));
        tableVendas.getTableHeader().setSize(50, 50);
        tableVendas.setShowVerticalLines(false);
        tableVendas.getTableHeader().setFont( new Font("Segoe UI Semibold",Font.BOLD, 18));

        buttonExibirVCad.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirVCad.setText("Cadastrar");
        buttonExibirVCad.setBorderPainted(false);
        buttonExibirVCad.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirVCad.setFocusPainted(false);
        buttonExibirVCad.setFocusable(false);
        buttonExibirVCad.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirVCad.setRadius(32);
        buttonExibirVCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirVCadActionPerformed(evt);
            }
        });

        buttonExibirVExcluir.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirVExcluir.setText("Excluir");
        buttonExibirVExcluir.setBorderPainted(false);
        buttonExibirVExcluir.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirVExcluir.setFocusPainted(false);
        buttonExibirVExcluir.setFocusable(false);
        buttonExibirVExcluir.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirVExcluir.setRadius(32);
        buttonExibirVExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirVExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(buttonExibirVCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonExibirVExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(26, 26, 26)
                        .addComponent(tfBuscarVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(buttonVPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(tfBuscarVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonVPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonExibirVCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExibirVExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout panelExibirVendasLayout = new javax.swing.GroupLayout(panelExibirVendas);
        panelExibirVendas.setLayout(panelExibirVendasLayout);
        panelExibirVendasLayout.setHorizontalGroup(
            panelExibirVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelExibirVendasLayout.setVerticalGroup(
            panelExibirVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTelas.add(panelExibirVendas, "exibirVendas");

        jPanel12.setBackground(new java.awt.Color(245, 245, 245));

        jLabel29.setFont(new java.awt.Font("Segoe UI Semibold", 1, 35)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("CPF:");

        tfBuscarFuncionarios.setBackground(new java.awt.Color(255, 255, 255));
        tfBuscarFuncionarios.setFont(new java.awt.Font("Segoe UI Semibold", 1, 30)); // NOI18N
        tfBuscarFuncionarios.setForeground(new java.awt.Color(0, 0, 0));

        buttonFPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        buttonFPesquisar.setText("Pesquisar");
        buttonFPesquisar.setBorderPainted(false);
        buttonFPesquisar.setCor(new java.awt.Color(0, 51, 102));
        buttonFPesquisar.setFocusPainted(false);
        buttonFPesquisar.setFocusable(false);
        buttonFPesquisar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonFPesquisar.setRadius(32);

        tableFuncionarios.setBackground(new java.awt.Color(255, 255, 255));
        tableFuncionarios.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        tableFuncionarios.setForeground(new java.awt.Color(0, 0, 0));
        tableFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Cargo", "Telefone", "CEP", "Logradouro", "N°"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableFuncionarios.setFocusable(false);
        tableFuncionarios.setRowHeight(30);
        tableFuncionarios.setSelectionBackground(new java.awt.Color(223, 231, 238));
        tableFuncionarios.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane6.setViewportView(tableFuncionarios);
        if (tableFuncionarios.getColumnModel().getColumnCount() > 0) {
            tableFuncionarios.getColumnModel().getColumn(0).setMinWidth(30);
            tableFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableFuncionarios.getColumnModel().getColumn(0).setMaxWidth(50);
            tableFuncionarios.getColumnModel().getColumn(1).setMinWidth(240);
            tableFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(250);
            tableFuncionarios.getColumnModel().getColumn(1).setMaxWidth(260);
            tableFuncionarios.getColumnModel().getColumn(2).setMinWidth(140);
            tableFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(150);
            tableFuncionarios.getColumnModel().getColumn(2).setMaxWidth(160);
            tableFuncionarios.getColumnModel().getColumn(3).setMinWidth(140);
            tableFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(150);
            tableFuncionarios.getColumnModel().getColumn(3).setMaxWidth(160);
            tableFuncionarios.getColumnModel().getColumn(4).setMinWidth(140);
            tableFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(150);
            tableFuncionarios.getColumnModel().getColumn(4).setMaxWidth(160);
            tableFuncionarios.getColumnModel().getColumn(5).setMinWidth(110);
            tableFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(120);
            tableFuncionarios.getColumnModel().getColumn(5).setMaxWidth(130);
            tableFuncionarios.getColumnModel().getColumn(6).setMinWidth(170);
            tableFuncionarios.getColumnModel().getColumn(6).setPreferredWidth(180);
            tableFuncionarios.getColumnModel().getColumn(6).setMaxWidth(160);
            tableFuncionarios.getColumnModel().getColumn(7).setMinWidth(70);
            tableFuncionarios.getColumnModel().getColumn(7).setPreferredWidth(80);
            tableFuncionarios.getColumnModel().getColumn(7).setMaxWidth(90);
        }
        tableFuncionarios.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < tableFuncionarios.getColumnCount(); i++) {
            tableFuncionarios.getColumnModel().getColumn(i).setCellRenderer(centralizarTabela);
        }

        TableCellRenderer baseRender3 = tableFuncionarios.getTableHeader().getDefaultRenderer();
        tableFuncionarios.getTableHeader().setDefaultRenderer((tbl, value, isSelected, hasFocus, row, column) -> {
            JComponent comp = (JComponent) baseRender3.getTableCellRendererComponent(tbl, value, isSelected, hasFocus, row, column);
            comp.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            return comp;
        });
        tableFuncionarios.setIntercellSpacing(new Dimension(0, 0));
        tableFuncionarios.setForeground(new java.awt.Color(0, 0, 0));
        tableFuncionarios.getTableHeader().setBackground(new Color(0, 64, 121));
        tableFuncionarios.getTableHeader().setForeground(new Color(255, 255,255));
        tableFuncionarios.getTableHeader().setSize(50, 50);
        tableFuncionarios.setShowVerticalLines(false);
        tableFuncionarios.getTableHeader().setFont( new Font("Segoe UI Semibold",Font.BOLD, 18));

        buttonExibirFEditar.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirFEditar.setText("Editar");
        buttonExibirFEditar.setBorderPainted(false);
        buttonExibirFEditar.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirFEditar.setFocusPainted(false);
        buttonExibirFEditar.setFocusable(false);
        buttonExibirFEditar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirFEditar.setRadius(32);
        buttonExibirFEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirFEditarActionPerformed(evt);
            }
        });

        buttonExibirFExcluir.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirFExcluir.setText("Excluir");
        buttonExibirFExcluir.setBorderPainted(false);
        buttonExibirFExcluir.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirFExcluir.setFocusPainted(false);
        buttonExibirFExcluir.setFocusable(false);
        buttonExibirFExcluir.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirFExcluir.setRadius(32);
        buttonExibirFExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirFExcluirActionPerformed(evt);
            }
        });

        buttonExibirFCad.setForeground(new java.awt.Color(255, 255, 255));
        buttonExibirFCad.setText("Cadastrar");
        buttonExibirFCad.setBorderPainted(false);
        buttonExibirFCad.setCor(new java.awt.Color(0, 51, 102));
        buttonExibirFCad.setFocusPainted(false);
        buttonExibirFCad.setFocusable(false);
        buttonExibirFCad.setFont(new java.awt.Font("Segoe UI Semibold", 1, 25)); // NOI18N
        buttonExibirFCad.setRadius(32);
        buttonExibirFCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExibirFCadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(buttonExibirFCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(359, 359, 359)
                        .addComponent(buttonExibirFEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonExibirFExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfBuscarFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(buttonFPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(tfBuscarFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonFPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonExibirFCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExibirFEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExibirFExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout panelExibirFuncionarioLayout = new javax.swing.GroupLayout(panelExibirFuncionario);
        panelExibirFuncionario.setLayout(panelExibirFuncionarioLayout);
        panelExibirFuncionarioLayout.setHorizontalGroup(
            panelExibirFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelExibirFuncionarioLayout.setVerticalGroup(
            panelExibirFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTelas.add(panelExibirFuncionario, "exibirFunc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTelas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void buttonCadastrarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarCActionPerformed
        viewChange("cadastroCli");
    }//GEN-LAST:event_buttonCadastrarCActionPerformed

    private void buttonCadastrarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarPActionPerformed
        viewChange("cadastroProd");
    }//GEN-LAST:event_buttonCadastrarPActionPerformed

    private void buttonExibirCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirCActionPerformed
        viewChange("exibirCli");
        listaClientes();
    }//GEN-LAST:event_buttonExibirCActionPerformed

    private void buttonCadastrarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarVActionPerformed
        viewChange("cadastroVenda");
    }//GEN-LAST:event_buttonCadastrarVActionPerformed

    private void buttonExibirVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirVActionPerformed
        viewChange("exibirVendas");
        listaVendas();
    }//GEN-LAST:event_buttonExibirVActionPerformed

    private void buttonExibirPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirPActionPerformed
        viewChange("exibirProd");
        listaProdutos();
    }//GEN-LAST:event_buttonExibirPActionPerformed

    private void buttonCadastrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarClienteActionPerformed
        cadastroCliente();
    }//GEN-LAST:event_buttonCadastrarClienteActionPerformed

    private void buttonLimparCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparCActionPerformed
        limparCampos(tfNomeC, tfCpfC, tfTelefoneC);
        cbSexoC.setSelectedIndex(-1);
    }//GEN-LAST:event_buttonLimparCActionPerformed

    private void buttonCadastrarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarProdutoActionPerformed
        cadastroProduto();
    }//GEN-LAST:event_buttonCadastrarProdutoActionPerformed

    private void buttonLimparPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparPActionPerformed
        limparCampos(tfCadastroPNome, tfCadastroPPreco, tfCadastroPCod, tfCadastroPEstoque);
        cbPCategoria.setSelectedIndex(-1);
        taCadastroPDesc.setText("");
    }//GEN-LAST:event_buttonLimparPActionPerformed

    private void tfCadastroPCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCadastroPCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCadastroPCodActionPerformed

    private void buttonVPesquisarCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVPesquisarCPFActionPerformed
        if (tfVendaCPFC.getText().isBlank()) {
            Alerta.Erro("Campo Vazio", "Digite o CPF do cliente");
        } else {
            listarCPF();
        }
    }//GEN-LAST:event_buttonVPesquisarCPFActionPerformed

    private void buttonExibirFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirFActionPerformed
        viewChange("exibirFunc");
    }//GEN-LAST:event_buttonExibirFActionPerformed

    private void buttonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSairActionPerformed
        new TelaInicial().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonSairActionPerformed

    private void buttonCadastrarFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarFuncActionPerformed
        cadastroFuncionario();
    }//GEN-LAST:event_buttonCadastrarFuncActionPerformed

    private void buttonLimparFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparFActionPerformed
        limparCampos(tfFNome, tfFCpf, tfFCep, tfFLogradouro,
                tfFNum, tfFComplemento, tfFTelefone, tfFLogin, tfFSenha);
        cbFCargos.setSelectedIndex(-1);
    }//GEN-LAST:event_buttonLimparFActionPerformed

    private void buttonCadastrarFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarFActionPerformed
        viewChange("cadastroFunc");
    }//GEN-LAST:event_buttonCadastrarFActionPerformed

    private void buttonVPesquisarAdcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVPesquisarAdcActionPerformed
        listaClientes();
    }//GEN-LAST:event_buttonVPesquisarAdcActionPerformed

    private void buttonExibirCExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirCExcluirActionPerformed
        excluirClientes();
    }//GEN-LAST:event_buttonExibirCExcluirActionPerformed

    private void buttonExibirCCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirCCadActionPerformed
        viewChange("cadastroCli");
    }//GEN-LAST:event_buttonExibirCCadActionPerformed

    private void buttonExibirCEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirCEditarActionPerformed
        editarClientes();
    }//GEN-LAST:event_buttonExibirCEditarActionPerformed

    private void buttonExibirFEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirFEditarActionPerformed
        editarFuncionarios();
    }//GEN-LAST:event_buttonExibirFEditarActionPerformed

    private void buttonExibirFCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirFCadActionPerformed
        viewChange("cadastroFunc");
    }//GEN-LAST:event_buttonExibirFCadActionPerformed

    private void buttonExibirFExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirFExcluirActionPerformed
        excluirFuncionarios();
    }//GEN-LAST:event_buttonExibirFExcluirActionPerformed

    private void buttonExibirPCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirPCadActionPerformed
        viewChange("cadastroProd");
    }//GEN-LAST:event_buttonExibirPCadActionPerformed

    private void buttonExibirPEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirPEditarActionPerformed
        editarProdutos();
    }//GEN-LAST:event_buttonExibirPEditarActionPerformed

    private void buttonPPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPPesquisarActionPerformed
        listaProdutos();
    }//GEN-LAST:event_buttonPPesquisarActionPerformed

    private void buttonExibirPExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirPExcluirActionPerformed
        excluirProdutos();
    }//GEN-LAST:event_buttonExibirPExcluirActionPerformed

    private void buttonExibirVExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirVExcluirActionPerformed
        excluirVendas();
    }//GEN-LAST:event_buttonExibirVExcluirActionPerformed

    private void buttonVPesquisarCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVPesquisarCodActionPerformed
        if (tfVendaCodigo.getText().isBlank()) {
            Alerta.Erro("Campo Vazio", "Digite o ´Código do produto");
        } else {
            listagemProdutos();
        }
    }//GEN-LAST:event_buttonVPesquisarCodActionPerformed

    private void tfVendaDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfVendaDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfVendaDataActionPerformed

    private void buttonVAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVAdicionarActionPerformed
        adcCarrinho();
    }//GEN-LAST:event_buttonVAdicionarActionPerformed

    private void buttonVPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVPagarActionPerformed
        cadastroVenda();
        limparTabela();
    }//GEN-LAST:event_buttonVPagarActionPerformed

    private void buttonVPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVPesquisarActionPerformed
        listaVendas();
    }//GEN-LAST:event_buttonVPesquisarActionPerformed

    private void buttonExibirVCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExibirVCadActionPerformed
        viewChange("cadastroVenda");
    }//GEN-LAST:event_buttonExibirVCadActionPerformed

    private void buttonVLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVLimparActionPerformed
        limparCampos(tfVendaCPFC, tfVendaProduto, tfVendaCodigo, tfVendaPreco, tfVendaQtd);
        limparTabela();
    }//GEN-LAST:event_buttonVLimparActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customs.botaoBorda buttonCadastrarC;
    private customs.botaoBorda buttonCadastrarCliente;
    private customs.botaoBorda buttonCadastrarF;
    private customs.botaoBorda buttonCadastrarFunc;
    private customs.botaoBorda buttonCadastrarP;
    private customs.botaoBorda buttonCadastrarProduto;
    private customs.botaoBorda buttonCadastrarV;
    private customs.botaoBorda buttonExibirC;
    private customs.botaoBorda buttonExibirCCad;
    private customs.botaoBorda buttonExibirCEditar;
    private customs.botaoBorda buttonExibirCExcluir;
    private customs.botaoBorda buttonExibirF;
    private customs.botaoBorda buttonExibirFCad;
    private customs.botaoBorda buttonExibirFEditar;
    private customs.botaoBorda buttonExibirFExcluir;
    private customs.botaoBorda buttonExibirP;
    private customs.botaoBorda buttonExibirPCad;
    private customs.botaoBorda buttonExibirPEditar;
    private customs.botaoBorda buttonExibirPExcluir;
    private customs.botaoBorda buttonExibirV;
    private customs.botaoBorda buttonExibirVCad;
    private customs.botaoBorda buttonExibirVExcluir;
    private customs.botaoBorda buttonFPesquisar;
    private customs.botaoBorda buttonLimparC;
    private customs.botaoBorda buttonLimparF;
    private customs.botaoBorda buttonLimparP;
    private customs.botaoBorda buttonPPesquisar;
    private customs.botaoBorda buttonSair;
    private customs.botaoBorda buttonVAdicionar;
    private customs.botaoBorda buttonVLimpar;
    private customs.botaoBorda buttonVPagar;
    private customs.botaoBorda buttonVPesquisar;
    private customs.botaoBorda buttonVPesquisarAdc;
    private customs.botaoBorda buttonVPesquisarCPF;
    private customs.botaoBorda buttonVPesquisarCod;
    private javax.swing.JComboBox<String> cbFCargos;
    private javax.swing.JComboBox<String> cbPCategoria;
    private javax.swing.JComboBox<String> cbSexoC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelExibirClientes;
    private javax.swing.JPanel panelExibirFuncionario;
    private javax.swing.JPanel panelExibirProdutos;
    private javax.swing.JPanel panelExibirVendas;
    private javax.swing.JPanel panelFuncionario;
    private javax.swing.JPanel panelProduto;
    private javax.swing.JPanel panelTelas;
    private javax.swing.JPanel panelVenda;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTextArea taCadastroPDesc;
    private javax.swing.JTable tableClientes;
    private javax.swing.JTable tableFuncionarios;
    private javax.swing.JTable tableProdutos;
    private javax.swing.JTable tableVenda;
    private javax.swing.JTable tableVendas;
    private javax.swing.JTextField tfBuscarClientes;
    private javax.swing.JTextField tfBuscarFuncionarios;
    private javax.swing.JTextField tfBuscarProdutos;
    private javax.swing.JTextField tfBuscarVendas;
    private javax.swing.JTextField tfCadastroPCod;
    private javax.swing.JTextField tfCadastroPEstoque;
    private javax.swing.JTextField tfCadastroPNome;
    private javax.swing.JTextField tfCadastroPPreco;
    private javax.swing.JTextField tfCpfC;
    private javax.swing.JTextField tfFCep;
    private javax.swing.JTextField tfFComplemento;
    private javax.swing.JTextField tfFCpf;
    private javax.swing.JTextField tfFLogin;
    private javax.swing.JTextField tfFLogradouro;
    private javax.swing.JTextField tfFNome;
    private javax.swing.JTextField tfFNum;
    private javax.swing.JTextField tfFSenha;
    private javax.swing.JTextField tfFTelefone;
    private javax.swing.JTextField tfNomeC;
    private javax.swing.JTextField tfTelefoneC;
    private javax.swing.JTextField tfVendaCPFC;
    private javax.swing.JTextField tfVendaCodigo;
    private javax.swing.JTextField tfVendaData;
    private javax.swing.JTextField tfVendaPreco;
    private javax.swing.JTextField tfVendaProduto;
    private javax.swing.JTextField tfVendaQtd;
    // End of variables declaration//GEN-END:variables
}
