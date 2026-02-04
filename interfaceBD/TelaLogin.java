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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistenciaBD.UsuarioDAO;
import dadosBD.Usuario;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame{
	
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;

	public TelaLogin() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JLabel login = new JLabel("LOGIN");
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.setFont(new Font ("Arial",Font.BOLD,24));
		JLabel cpf = new JLabel("CPF:");
		cpf.setMaximumSize(new Dimension(75,30));
		cpf.setFont(new Font ("Arial",Font.PLAIN,20));
		JLabel senha = new JLabel("Senha:");
		senha.setMaximumSize(new Dimension(75,30));
		senha.setFont(new Font ("Arial",Font.PLAIN,20));
		
		JTextField cpfText = new JTextField();
		cpfText.setMaximumSize(new Dimension(200,30));
		JPasswordField senhaText = new JPasswordField();
		senhaText.setMaximumSize(new Dimension(200,30));
		
		JButton botao1 = new JButton("Voltar");
		botao1.setPreferredSize(new Dimension(100,30));
		botao1.setFont(new Font ("Arial",Font.PLAIN,18));
		JButton botao2 = new JButton("Entrar");
		botao2.setPreferredSize(new Dimension(100,30));
		botao2.setFont(new Font ("Arial",Font.PLAIN,18));
		
		JPanel painel = new JPanel();
		painel.setLayout(new BoxLayout(painel, BoxLayout.X_AXIS));
		painel.add(cpf);
		painel.add(cpfText);
		
		JPanel painel2 = new JPanel();
		painel2.setLayout(new BoxLayout(painel2, BoxLayout.X_AXIS));
		painel2.add(senha);
		painel2.add(senhaText);
		
		JPanel painel3 = new JPanel();
		painel3.setPreferredSize(new Dimension(0,1));
		
		JPanel botoes = new JPanel();
		botoes.add(botao1);
		botoes.add(painel3);
		botoes.add(botao2);
		
		JPanel titulo =  new JPanel();
		titulo.setLayout(new BoxLayout(titulo,BoxLayout.Y_AXIS));
		titulo.setBorder(new EmptyBorder(400,350,400,350));
		titulo.add(login);
		titulo.add(Box.createRigidArea(new Dimension(0,15)));
		titulo.add(painel);
		titulo.add(Box.createRigidArea(new Dimension(0,15)));
		titulo.add(painel2);
		titulo.add(Box.createRigidArea(new Dimension(0,15)));
		titulo.add(botoes);
		
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
				
				String usuario = cpfText.getText();
				String senha = new String(senhaText.getPassword());
				
				UsuarioDAO usuarios= new UsuarioDAO();
				Usuario usuarioBD = usuarios.usuarioLog(usuario, senha);
				
				if (usuarioBD != null) {
					dispose();
					new TelaMenu(usuarioBD);
				} else {
					
					Object[] bottons = {"Continuar","Sair","Cadastrar"};
					int escolha = JOptionPane.showOptionDialog(null, "CPF e/ou senha inválidos\nDeseja continuar, sair \nou se cadastrar?",
					"Erro de Login",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE,
					null,
					bottons,
					bottons[0]);
					
					if (escolha==0) {
						
					}
					
					else if (escolha==1) {
						System.exit(0);
					}
					
					else if (escolha==2) {
						dispose();
						new TelaCadastro();
					}
					
				}
				
			}
			
		});
		
		this.add(titulo);
		this.setVisible(true);
		
	}

}
