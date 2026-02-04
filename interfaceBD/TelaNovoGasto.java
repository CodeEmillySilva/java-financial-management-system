package interfaceBD;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dadosBD.Usuario;
import dadosBD.Categoria;
import dadosBD.Gasto;
import negocioBD.Principal;
import persistenciaBD.GastoDAO;

@SuppressWarnings("serial")
public class TelaNovoGasto extends JFrame{

	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	
	public TelaNovoGasto(Usuario usuarioMenu) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//Nomes
		JLabel titulo = new JLabel("CADASTRO NOVO GASTO");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		JLabel nomeGasto = new JLabel("Nome:");
		nomeGasto.setMaximumSize(new Dimension(200, 20));
		nomeGasto.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel dataGasto = new JLabel("Data (dd/mm/aaaa):");
		dataGasto.setMaximumSize(new Dimension(200, 20));
		dataGasto.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel descricaoGasto = new JLabel("Descricao:");
		descricaoGasto.setMaximumSize(new Dimension(200, 20));
		descricaoGasto.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel valorGasto = new JLabel("Valor:");
		valorGasto.setMaximumSize(new Dimension(200, 20));
		valorGasto.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel categoriaGasto = new JLabel("Categoria:");
		categoriaGasto.setMaximumSize(new Dimension(200, 20));
		categoriaGasto.setFont(new Font("Arial", Font.PLAIN, 18));
		
		
		//Campo para escrever
		JTextField nomeGastoText = new JTextField();
		nomeGastoText.setMaximumSize(new Dimension(200, 20));
		JTextField dataGastoText = new JTextField();
		dataGastoText.setMaximumSize(new Dimension(200, 20));
		JTextField descricaoGastoText = new JTextField();
		descricaoGastoText.setMaximumSize(new Dimension(200, 20));
		JTextField valorGastoText = new JTextField();
		valorGastoText.setMaximumSize(new Dimension(200, 20));
		String [] opcoes = {"Escolha uma opção","COMIDA","LAZER","EDUCACAO","SAUDE","TRANSPORTE","OUTROS"};
		JComboBox<String> lista = new JComboBox<>(opcoes);
		lista.setMaximumSize(new Dimension(200, 20));
		
		//Nomes do botões
		JLabel voltar = new JLabel("Voltar");
		voltar.setAlignmentX(CENTER_ALIGNMENT);
		voltar.setFont(new Font("Arial", Font.PLAIN,14));
		JLabel cadastrar = new JLabel("Cadastrar");
		cadastrar.setAlignmentX(CENTER_ALIGNMENT);
		cadastrar.setFont(new Font("Arial", Font.PLAIN,14));
		
		//Criação dos botões
		JButton botao1 = new JButton();
		botao1.setPreferredSize(new Dimension(100,30));
		botao1.add(voltar);
		JButton botao2 = new JButton();
		botao2.setPreferredSize(new Dimension(100,30));
		botao2.add(cadastrar);
		
		//Paineis para nomes, campos e botões
		JPanel nomeGastoJ = new JPanel();
		nomeGastoJ.setLayout(new BoxLayout(nomeGastoJ,BoxLayout.X_AXIS));
		nomeGastoJ.add(nomeGasto);
		nomeGastoJ.add(nomeGastoText);
		JPanel dataGastoJ = new JPanel();
		dataGastoJ.setLayout(new BoxLayout(dataGastoJ,BoxLayout.X_AXIS));
		dataGastoJ.add(dataGasto);
		dataGastoJ.add(dataGastoText);
		JPanel descricaoGastoJ = new JPanel();
		descricaoGastoJ.setLayout(new BoxLayout(descricaoGastoJ,BoxLayout.X_AXIS));
		descricaoGastoJ.add(descricaoGasto);
		descricaoGastoJ.add(descricaoGastoText);
		JPanel valorGastoJ = new JPanel();
		valorGastoJ.setLayout(new BoxLayout(valorGastoJ,BoxLayout.X_AXIS));
		valorGastoJ.add(valorGasto);
		valorGastoJ.add(valorGastoText);
		JPanel categoriaGastoJ = new JPanel();
		categoriaGastoJ.setLayout(new BoxLayout(categoriaGastoJ,BoxLayout.X_AXIS));
		categoriaGastoJ.add(categoriaGasto);
		categoriaGastoJ.add(lista);
		JPanel botoes = new JPanel();
		botoes.setAlignmentX(CENTER_ALIGNMENT);
		botoes.add(botao1);
		botoes.add(botao2);
		
		JPanel juncao = new JPanel();
		juncao.setBorder(new EmptyBorder(350,50,200,50));
		juncao.setLayout(new BoxLayout(juncao,BoxLayout.Y_AXIS));
		juncao.add(titulo);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(nomeGastoJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(dataGastoJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(descricaoGastoJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(valorGastoJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(categoriaGastoJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(botoes);
		
		botao1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
							
					dispose();
					new TelaMenu(usuarioMenu);

			}
			
		});
		
		botao2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
					String nomeV = nomeGastoText.getText();
					String dataV = dataGastoText.getText();		
					String descricaoV = descricaoGastoText.getText();
					String valorV = valorGastoText.getText();
					String categoriaV = (String) lista.getSelectedItem();
					
					if (!verificarVazio(nomeV,dataV,descricaoV,valorV,categoriaV)) {
						String tipo = "Por favor, preencha todos os campos.";
						telaErro(tipo);
						return;
					}
					
					if(!verificarData(dataV)) {
						String tipo = "Por favor, digite uma data válida.\n"
						+ "No padrão dd/mm/aaaa";
						telaErro(tipo);
						return;
					}
					
					if(!verificarValor(valorV)) {
						String tipo = "Por favor, digite apenas número.\n"
						+ "Caso queira indicar centavos, use um ponto antes deles.";
						telaErro(tipo);
						return;
					}
					
					if(!verificarCategoria(categoriaV)) {
						String tipo = "Por favor, escolha um tipo de categoria.";
						telaErro(tipo);
						return;
					}
					
					Double valorReal = Double.parseDouble(valorV);
					Categoria tipo = Categoria.valueOf(categoriaV);
					
					Gasto novoGasto = new Gasto(nomeV,dataV,descricaoV,valorReal,tipo);
					
					usuarioMenu.cadastrarNovoGasto(novoGasto);
					
					GastoDAO gastoBD = new GastoDAO();
					gastoBD.cadastrarNovoGasto(usuarioMenu, novoGasto);
					
					String[] bottons = {"Menu","Continuar","Sair"};
					int escolha = JOptionPane.showOptionDialog(null, "Seu cadastro foi efetuado, como deseja prosseguir?",
					"Cadastro efetuado!",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					bottons,
					bottons[0]);
					
					if (escolha==0) {
						dispose();
						new TelaMenu(usuarioMenu);
					}
					
					else if (escolha==1) {
						dispose();
						new TelaNovoGasto(usuarioMenu);
					}
					
					else if (escolha==2) {
						System.exit(0);
					}
					
			}
			
		});
		
		this.add(juncao);
		this.setVisible(true);
		
	}
	
	public boolean verificarVazio(String nome,String data, String descricao, String valor, String categoria) {
		
		if (nome.isEmpty() || data.isEmpty() || descricao.isEmpty() || valor.isEmpty() || categoria.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public boolean verificarData(String data) {
		if(!Principal.isData(data)) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean verificarValor(String valor) {
		if(!valor.matches("\\d+(\\.\\d+)?")) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean verificarCategoria(String categoria) {
		if(categoria.equals("Escolha uma opção")) {
			return false;
		} else {
			return true;
		}
	}
	
	private void telaErro(String tipo) {
		JOptionPane.showMessageDialog(null,tipo,"Erro no cadastro",JOptionPane.ERROR_MESSAGE);
	}
	
}
