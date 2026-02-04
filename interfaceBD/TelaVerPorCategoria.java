package interfaceBD;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.util.List;

import dadosBD.Categoria;
import dadosBD.Gasto;
import dadosBD.Usuario;
import persistenciaBD.GastoDAO;

@SuppressWarnings("serial")
public class TelaVerPorCategoria extends JFrame {
	
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	private GastoDAO gastoBD = new GastoDAO();
	
	public TelaVerPorCategoria(Categoria categoriaEscolhida, Usuario usuario) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		List<Gasto> gastoCategoria = gastoBD.buscarCategoria(usuario, categoriaEscolhida);
		
		JLabel titulo = new JLabel("GASTOS");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		
		JButton botao1 = new JButton("Voltar");
		botao1.setAlignmentX(CENTER_ALIGNMENT);
		botao1.setFont(new Font("Arial", Font.PLAIN, 18));
		botao1.setPreferredSize(new Dimension(200,30));
		JButton botao2 = new JButton("Sair");
		botao2.setAlignmentX(CENTER_ALIGNMENT);
		botao2.setFont(new Font("Arial", Font.PLAIN, 18));
		botao2.setPreferredSize(new Dimension(200,30));
		
		String [] tituloColunas = {"ID Gasto","Data","Nome","Valor","Descrição","Categoria"};
		Object[][] dados = new Object [gastoCategoria.size()][6];
		
		if (!gastoCategoria.isEmpty()) {
			
			for (int i=0; i<gastoCategoria.size(); i++) {
				Gasto parametro = gastoCategoria.get(i);
				dados[i][0] = parametro.getIdGasto();
				dados[i][1] = parametro.getDataGasto();
				dados[i][2] = parametro.getNomeGasto();
				dados[i][3] = "R$ "+Double.toString(parametro.getValorGasto());
				dados[i][4] = parametro.getDescricaoGasto();
				dados[i][5] = parametro.getCategoria();
			}
			
		}
		
		if (gastoCategoria.isEmpty()) {
				
			JOptionPane.showMessageDialog(null,"A lista está vazia porque\n"
			+ "nenhum gasto dessa Categoria foi cadastrado.","Planilha vazia",JOptionPane.WARNING_MESSAGE);
			
		}
		
		botao1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TelaFiltrarCategoria(usuario);
			}
			
		});
		
		botao2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		JTable tabela = new JTable(dados,tituloColunas);
		JScrollPane rolagem = new JScrollPane(tabela);
		
		JPanel botoes = new JPanel();
		botoes.setAlignmentX(CENTER_ALIGNMENT);
		botoes.add(botao1);
		botoes.add(botao2);
		
		JPanel localizacao = new JPanel();
		localizacao.setAlignmentX(CENTER_ALIGNMENT);
		localizacao.setLayout(new BoxLayout(localizacao,BoxLayout.Y_AXIS));
		localizacao.setBorder(new EmptyBorder(100,100,100,100));
		localizacao.add(titulo);
		localizacao.add(Box.createRigidArea(new Dimension(0,20)));
		localizacao.add(rolagem);
		localizacao.add(Box.createRigidArea(new Dimension(0,10)));
		localizacao.add(botoes);
		
		this.add(localizacao);
		this.setVisible(true);
		
	}
}
