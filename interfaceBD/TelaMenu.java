package interfaceBD;

import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dadosBD.Usuario;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class TelaMenu extends JFrame{
	
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	
	public TelaMenu (Usuario usuarioMenu) {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JButton botao1 = new JButton("Vizualizar gastos");
		botao1.setAlignmentX(CENTER_ALIGNMENT);
		botao1.setMaximumSize(new Dimension(500,40));
		botao1.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao2 = new JButton("Filtrar gastos por categoria");
		botao2.setAlignmentX(CENTER_ALIGNMENT);
		botao2.setMaximumSize(new Dimension(500,40));
		botao2.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao3 = new JButton("Filtrar gastos por mês");
		botao3.setAlignmentX(CENTER_ALIGNMENT);
		botao3.setMaximumSize(new Dimension(500,40));
		botao3.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao4 = new JButton("Alterar gasto");
		botao4.setAlignmentX(CENTER_ALIGNMENT);
		botao4.setMaximumSize(new Dimension(500,40));
		botao4.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao5 = new JButton("Remover gasto");
		botao5.setAlignmentX(CENTER_ALIGNMENT);
		botao5.setMaximumSize(new Dimension(500,40));
		botao5.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao6 = new JButton("Cadastrar Novo Gasto");
		botao6.setAlignmentX(CENTER_ALIGNMENT);
		botao6.setMaximumSize(new Dimension(500,40));
		botao6.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao7 = new JButton("Visualizar gráfico em barras");
		botao7.setAlignmentX(CENTER_ALIGNMENT);
		botao7.setMaximumSize(new Dimension(500,40));
		botao7.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao8 = new JButton("Visualizar Scatter");
		botao8.setAlignmentX(CENTER_ALIGNMENT);
		botao8.setMaximumSize(new Dimension(500,40));
		botao8.setFont(new Font("Arial", Font.PLAIN, 18));
		JButton botao9 = new JButton("Sair");
		botao9.setAlignmentX(CENTER_ALIGNMENT);
		botao9.setMaximumSize(new Dimension(500,40));
		botao9.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JLabel texto = new JLabel("MENU");
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
		botoes.add(botao4);
		botoes.add(Box.createRigidArea(new Dimension(0,10)));
		botoes.add(botao5);
		botoes.add(Box.createRigidArea(new Dimension(0,10)));
		botoes.add(botao6);
		botoes.add(Box.createRigidArea(new Dimension(0,10)));
		botoes.add(botao7);
		botoes.add(Box.createRigidArea(new Dimension(0,10)));
		botoes.add(botao8);
		botoes.add(Box.createRigidArea(new Dimension(0,10)));
		botoes.add(botao9);
		botoes.add(Box.createRigidArea(new Dimension(0,10)));
		
		JPanel painel = new JPanel();
		painel.setLayout(new BoxLayout(painel,BoxLayout.Y_AXIS));
		painel.setBorder(new EmptyBorder(200,150,200,150));
		painel.add(botoes);
		
		botao1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaVisualizarGastos(usuarioMenu);
			}
			
		});
		
		botao2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaFiltrarCategoria(usuarioMenu);
			}
			
		});
		
		botao3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaFiltrarMes(usuarioMenu);
			}
			
		});
		
		botao4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaAlterarGasto(usuarioMenu);
			}
			
		});
		
		botao5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaRemoverGasto(usuarioMenu);
			}
			
		});
		
		botao6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaNovoGasto(usuarioMenu);
			}
			
		});
		
		botao7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaGastoBarra(usuarioMenu);
			}
			
		});
		
		botao8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaGastoScatter(usuarioMenu);
			}
			
		});
		
		botao9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		this.add(painel);
		this.setVisible(true);
	}

}
