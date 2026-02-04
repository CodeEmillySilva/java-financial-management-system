package interfaceBD;

import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dadosBD.Categoria;
import dadosBD.Usuario;

@SuppressWarnings("serial")
public class TelaFiltrarCategoria extends JFrame{
	
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	
	public TelaFiltrarCategoria(Usuario usuarioMenu) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JLabel titulo = new JLabel("ESCOLHA UMA CATEGORIA:");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo.setFont(new Font("Arial", Font.BOLD,24));
		
		String [] opcoes = {"Escolha uma opção","COMIDA","LAZER","EDUCACAO","SAUDE","TRANSPORTE","OUTROS"};
		JComboBox<String>escolha = new JComboBox<>(opcoes);
		escolha.setAlignmentX(CENTER_ALIGNMENT);
		escolha.setPreferredSize(new Dimension(200,20));
		
		JButton botao1 = new JButton("Voltar");
		botao1.setPreferredSize(new Dimension(100,30));
		botao1.setFont(new Font ("Arial",Font.PLAIN,18));
		JButton botao2 = new JButton("Ver");
		botao2.setPreferredSize(new Dimension(100,30));
		botao2.setFont(new Font ("Arial",Font.PLAIN,18));
		
		JPanel botoes = new JPanel();
		botoes.setAlignmentX(CENTER_ALIGNMENT);
		botoes.add(botao1);
		botoes.add(botao2);
		
		JPanel juncao = new JPanel();
		juncao.setLayout(new BoxLayout(juncao,BoxLayout.Y_AXIS));
		juncao.setBorder(new EmptyBorder(450,300,450,300));
		juncao.add(titulo);
		juncao.add(Box.createRigidArea(new Dimension(0,20)));
		juncao.add(escolha);
		juncao.add(Box.createRigidArea(new Dimension(0,20)));
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
				
				String categoria = (String) escolha.getSelectedItem();
				
				if(!verificarCategoria(categoria)) {
					JOptionPane.showMessageDialog(null,"Por favor, escolha uma categoria para seguir.","Alerta",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Categoria tipo = Categoria.valueOf(categoria);
				
				dispose();
				new TelaVerPorCategoria(tipo,usuarioMenu);

			}
			
		});
		
		this.add(juncao);
		this.setVisible(true);
		
	}
	
	public boolean verificarCategoria(String categoria) {
		if(categoria.equals("Escolha uma opção")) {
			return false;
		} else {
			return true;
		}
	}
}
