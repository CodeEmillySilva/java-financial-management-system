package interfaceBD;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistenciaBD.UsuarioDAO;
import negocioBD.Principal;
import dadosBD.Usuario;

@SuppressWarnings("serial")
public class TelaCadastro extends JFrame{

	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	private UsuarioDAO usuarioBD = new UsuarioDAO();
	
	public TelaCadastro () {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JLabel titulo = new JLabel("CADASTRO");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		JLabel nome = new JLabel("Nome completo:");
		nome.setMaximumSize(new Dimension(200, 20));
		nome.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel cpf = new JLabel("CPF (apenas número):");
		cpf.setMaximumSize(new Dimension(200, 20));
		cpf.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel idade = new JLabel("Idade:");
		idade.setMaximumSize(new Dimension(200, 20));
		idade.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel senha = new JLabel("Senha:");
		senha.setMaximumSize(new Dimension(200, 20));
		senha.setFont(new Font("Arial", Font.PLAIN, 18));
		JLabel senhaConf = new JLabel("Senha (confirmação):");
		senhaConf.setMaximumSize(new Dimension(200, 20));
		senhaConf.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JTextField nomeText = new JTextField();
		nomeText.setMaximumSize(new Dimension(200,30));
		JTextField cpfText = new JTextField();
		cpfText.setMaximumSize(new Dimension(200,30));
		JTextField idadeText = new JTextField();
		idadeText.setMaximumSize(new Dimension(200,30));
		JPasswordField senhaText = new JPasswordField();
		senhaText.setMaximumSize(new Dimension(200,30));
		JPasswordField senhaTextConf = new JPasswordField();
		senhaTextConf.setMaximumSize(new Dimension(200,30));
		
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
		
		JPanel nomeJ = new JPanel();
		nomeJ.setLayout(new BoxLayout(nomeJ,BoxLayout.X_AXIS));
		nomeJ.add(nome);
		nomeJ.add(nomeText);
		JPanel cpfJ = new JPanel();
		cpfJ.setLayout(new BoxLayout(cpfJ,BoxLayout.X_AXIS));
		cpfJ.add(cpf);
		cpfJ.add(cpfText);
		JPanel idadeJ = new JPanel();
		idadeJ.setLayout(new BoxLayout(idadeJ,BoxLayout.X_AXIS));
		idadeJ.add(idade);
		idadeJ.add(idadeText);
		JPanel senhaJ = new JPanel();
		senhaJ.setLayout(new BoxLayout(senhaJ,BoxLayout.X_AXIS));
		senhaJ.add(senha);
		senhaJ.add(senhaText);
		JPanel senhaConJ = new JPanel();
		senhaConJ.setLayout(new BoxLayout(senhaConJ,BoxLayout.X_AXIS));
		senhaConJ.add(senhaConf);
		senhaConJ.add(senhaTextConf);
		JPanel botoes = new JPanel();
		botoes.setAlignmentX(CENTER_ALIGNMENT);
		botoes.add(botao1);
		botoes.add(botao2);
		
		JPanel juncao = new JPanel();
		juncao.setBorder(new EmptyBorder(350,50,200,50));
		juncao.setLayout(new BoxLayout(juncao,BoxLayout.Y_AXIS));
		juncao.add(titulo);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(nomeJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(cpfJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(idadeJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(senhaJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(senhaConJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(botoes);
		
		botao1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
							
					dispose();
					new TelaEntrada();

			}
			
		});
		
		botao2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
							
					String nomeV = nomeText.getText();
					String cpfV = cpfText.getText();
					String idadeV = idadeText.getText();
					String senhaV = new String(senhaText.getPassword());
					String senha2V = new String(senhaTextConf.getPassword());
					
					if (!verificarVazio(nomeV,cpfV,idadeV,senhaV,senha2V)) {
						String tipo = "Há campos vazios\nPor vaor, preencha todos.";
						telaErro(tipo);
						return;
					}
					
					if (!validarCpf(cpfV)) {
						String tipo = "O cpf não está no padrão estabelecido.\n"
						+ "Por favor, digite um cpf valido, sem pontos e traços.";
						telaErro(tipo);
						return;
					}
					
					if(duploCpf(cpfV)) {
						String tipo = "O cpf digitado já existe no sistema.\n"
						+ "Por favor, caso queira acessar o sistema se dirija ao Login.";
						telaAlerta(tipo);
						return;
					}
					
					if(!validarIdade(idadeV)) {
						String tipo = "Por favor, digite uma idade válida.";
						telaErro(tipo);
						return;
					}
					
					if(!validarIgualdade(senhaV,senha2V)) {
						String tipo = "Por favor, digite senhas iguais.";
						telaErro(tipo);
						return;
					}
					
					int idadeUsuario = Integer.parseInt(idadeV);
					
					Usuario usuario = new Usuario(nomeV,senhaV,cpfV,idadeUsuario,null);
					
					usuarioBD.cadastrarUsuario(usuario);
					
					String[] bottons = {"Menu","Inicio","Sair"};
					int escolha = JOptionPane.showOptionDialog(null, "Seu cadastro foi efetuado, como deseja prosseguir?",
					"Cadastro efetuado!",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					null,
					bottons,
					bottons[0]);
					
					if (escolha==0) {
						dispose();
						Usuario pessoa = usuarioBD.retornaUsuario(usuario);
						new TelaMenu(pessoa);
					}
					
					else if (escolha==1) {
						dispose();
						new TelaEntrada();
					}
					
					else if (escolha==2) {
						System.exit(0);
					}
					
			}
			
		});
		
		this.add(juncao);
		this.setVisible(true);
		
	}
	
	private boolean verificarVazio(String nome, String cpf,
	String idade, String senha1, String senha2) {
		if (nome.isEmpty() || cpf.isEmpty() || idade.isEmpty() ||
		senha1.isEmpty() || senha2.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validarCpf(String cpf) {
		if (Principal.isCpf(cpf)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean duploCpf(String cpf) {
		if (usuarioBD.buscarCpf(cpf)!=true) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validarIdade(String idade) {
		if(Integer.parseInt(idade)<0 ||  Integer.parseInt(idade)>120) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validarIgualdade(String senha1, String senha2) {
		
		if (senha1.equals(senha2)) {
			return true;
		} else {
			return false;
		}
	}
	
	private void telaErro(String tipo) {
		JOptionPane.showMessageDialog(null,tipo,"Erro no cadastro",JOptionPane.ERROR_MESSAGE);
	}
	
	private void telaAlerta(String tipo) {
		JOptionPane.showMessageDialog(null,tipo,"Erro no cadastro",JOptionPane.WARNING_MESSAGE);
	}

}
