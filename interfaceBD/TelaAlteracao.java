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

import dadosBD.Categoria;
import dadosBD.Gasto;
import dadosBD.Usuario;
import negocioBD.Principal;
import persistenciaBD.GastoDAO;

@SuppressWarnings("serial")
public class TelaAlteracao extends JFrame{
		
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
		
	public TelaAlteracao(int escolha, Usuario usuario) {
			
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JLabel titulo = new JLabel("ALTERAÇÃO DO GASTO");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		JLabel nomeAlt = new JLabel("Nome:");
		nomeAlt.setMaximumSize(new Dimension(200, 20));
		nomeAlt.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel dataAlt = new JLabel("Data (dd/mm/aaaa):");
		dataAlt.setMaximumSize(new Dimension(200, 20));
		dataAlt.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel descricaoAlt = new JLabel("Descricao:");
		descricaoAlt.setMaximumSize(new Dimension(200, 20));
		descricaoAlt.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel valorAlt = new JLabel("Valor:");
		valorAlt.setMaximumSize(new Dimension(200, 20));
		valorAlt.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel categoriaAlt = new JLabel("Categoria:");
		categoriaAlt.setMaximumSize(new Dimension(200, 20));
		categoriaAlt.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JTextField nomeAltText = new JTextField();
		nomeAltText.setMaximumSize(new Dimension(200, 20));
		JTextField dataAltText = new JTextField();
		dataAltText.setMaximumSize(new Dimension(200, 20));
		JTextField descricaoAltText = new JTextField();
		descricaoAltText.setMaximumSize(new Dimension(200, 20));
		JTextField valorAltText = new JTextField();
		valorAltText.setMaximumSize(new Dimension(200, 20));
		String [] opcoesAlt = {"Escolha uma opção","COMIDA","LAZER","EDUCACAO","SAUDE","TRANSPORTE","OUTROS"};
		JComboBox<String> listaAlt = new JComboBox<>(opcoesAlt);
		listaAlt.setMaximumSize(new Dimension(200, 20));
		
		JLabel voltar = new JLabel("Voltar");
		voltar.setAlignmentX(CENTER_ALIGNMENT);
		voltar.setFont(new Font("Arial", Font.PLAIN,14));
		JLabel cadastrar = new JLabel("Cadastrar");
		cadastrar.setAlignmentX(CENTER_ALIGNMENT);
		cadastrar.setFont(new Font("Arial", Font.PLAIN,14));
		
		JButton botao1 = new JButton();
		botao1.setPreferredSize(new Dimension(100,30));
		botao1.add(voltar);
		JButton botao2 = new JButton();
		botao2.setPreferredSize(new Dimension(100,30));
		botao2.add(cadastrar);
		
		JPanel nomeAltJ = new JPanel();
		nomeAltJ.setLayout(new BoxLayout(nomeAltJ,BoxLayout.X_AXIS));
		nomeAltJ.add(nomeAlt);
		nomeAltJ.add(nomeAltText);
		JPanel dataAltJ = new JPanel();
		dataAltJ.setLayout(new BoxLayout(dataAltJ,BoxLayout.X_AXIS));
		dataAltJ.add(dataAlt);
		dataAltJ.add(dataAltText);
		JPanel descricaoAltJ = new JPanel();
		descricaoAltJ.setLayout(new BoxLayout(descricaoAltJ,BoxLayout.X_AXIS));
		descricaoAltJ.add(descricaoAlt);
		descricaoAltJ.add(descricaoAltText);
		JPanel valorAltJ = new JPanel();
		valorAltJ.setLayout(new BoxLayout(valorAltJ,BoxLayout.X_AXIS));
		valorAltJ.add(valorAlt);
		valorAltJ.add(valorAltText);
		JPanel categoriaAltJ = new JPanel();
		categoriaAltJ.setLayout(new BoxLayout(categoriaAltJ,BoxLayout.X_AXIS));
		categoriaAltJ.add(categoriaAlt);
		categoriaAltJ.add(listaAlt);
		JPanel botoes = new JPanel();
		botoes.setAlignmentX(CENTER_ALIGNMENT);
		botoes.add(botao1);
		botoes.add(botao2);
		
		JPanel juncao = new JPanel();
		juncao.setBorder(new EmptyBorder(350,50,200,50));
		juncao.setLayout(new BoxLayout(juncao,BoxLayout.Y_AXIS));
		juncao.add(titulo);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(nomeAltJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(dataAltJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(descricaoAltJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(valorAltJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(categoriaAltJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(botoes);
		
		botao1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
							
					dispose();
					new TelaMenu(usuario);

			}
			
		});
		
		botao2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
					String nomeV = nomeAltText.getText();
					String dataV = dataAltText.getText();		
					String descricaoV = descricaoAltText.getText();
					String valorV = valorAltText.getText();
					String categoriaV = (String) listaAlt.getSelectedItem();
					
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
					
					Gasto gastoAlterado = new Gasto(nomeV,dataV,descricaoV,valorReal,tipo);
					
					gastoAlterado.setIdGasto(escolha);
					
					GastoDAO gasto = new GastoDAO();
					boolean check = gasto.alterarGasto(usuario, gastoAlterado);
					
					if(check) {
						String[] bottons = {"Menu","Vizualizar","Sair"};
						int escolha = JOptionPane.showOptionDialog(null, "Sua alteração foi efetuada, como deseja prosseguir?",
						"Alteração efetuada!",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null,
						bottons,
						bottons[0]);
						
						if (escolha==0) {
							dispose();
							new TelaMenu(usuario);
						}
						
						else if (escolha==1) {
							dispose();
							new TelaVisualizarGastos(usuario);
						}
						
						else if (escolha==2) {
							System.exit(0);
						}
					} else {
						String mensagem = "Ocorreu um erro na alteração, por favor, tente novamente.";
						telaErro(mensagem);
						return;
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
		JOptionPane.showMessageDialog(null,tipo,"Erro na alteração",JOptionPane.ERROR_MESSAGE);
	}
		
}
