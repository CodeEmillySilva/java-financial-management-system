package interfaceBD;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class TelaEntrada extends JFrame{
	
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	
	public TelaEntrada() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JButton botao1 = new JButton("Login");
		botao1.setAlignmentX(CENTER_ALIGNMENT);
		botao1.setMaximumSize(new Dimension(500,60));
		botao1.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao2 = new JButton("Cadastro");
		botao2.setAlignmentX(CENTER_ALIGNMENT);
		botao2.setMaximumSize(new Dimension(500,60));
		botao2.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao3 = new JButton("Sair");
		botao3.setAlignmentX(CENTER_ALIGNMENT);
		botao3.setMaximumSize(new Dimension(500,60));
		botao3.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JLabel texto = new JLabel("BEM - VINDO AO SISTEMA FINANCEIRO !");
		texto.setAlignmentX(CENTER_ALIGNMENT);
		texto.setFont(new Font ("Arial",Font.BOLD,24));
		
		JPanel botoes = new JPanel();
		botoes.setAlignmentX(CENTER_ALIGNMENT);
		botoes.setLayout(new BoxLayout(botoes,BoxLayout.Y_AXIS));
		botoes.add(texto);
		botoes.add(Box.createRigidArea(new Dimension(0,20)));
		botoes.add(botao1);
		botoes.add(Box.createRigidArea(new Dimension(0,10)));
		botoes.add(botao2);
		botoes.add(Box.createRigidArea(new Dimension(0,10)));
		botoes.add(botao3);
		botoes.add(Box.createRigidArea(new Dimension(0,10)));
		
		JPanel painel = new JPanel();
		painel.setLayout(new BoxLayout(painel,BoxLayout.Y_AXIS));
		painel.setBorder(new EmptyBorder(300,150,250,150));
		painel.add(botoes);

		botao1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaLogin();
			}
			
		});
		
		botao2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();			
				new TelaCadastro();
			}
			
		});
		
		botao3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
	
		this.add(painel);
		this.setVisible(true);
		
	}

}
