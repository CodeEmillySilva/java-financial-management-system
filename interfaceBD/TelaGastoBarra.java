package interfaceBD;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import dadosBD.Usuario;
import dadosBD.Gasto;
import persistenciaBD.GastoDAO;

@SuppressWarnings("serial")
public class TelaGastoBarra extends JFrame{
	
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;

	public TelaGastoBarra(Usuario usuario) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		DefaultCategoryDataset dados = DadosArmazenados(usuario);
		
		JFreeChart grafico = ChartFactory.createBarChart("GASTOS POR CATEGORIA EM BARRA",
				"CATEGORIA", "VALOR EM R$", dados);
		
		ChartPanel painel = new ChartPanel(grafico);
		painel.setPreferredSize(new Dimension(800,800));
		
		JPanel painelGrafico = new JPanel();
        painelGrafico.setPreferredSize(new Dimension(900, 900));
        painelGrafico.add(painel);
		
		JButton botao1 = new JButton("Voltar");
		botao1.setPreferredSize(new Dimension(100,30));
		botao1.setFont(new Font ("Arial",Font.PLAIN,18));
		JButton botao2 = new JButton("Sair");
		botao2.setPreferredSize(new Dimension(100,30));
		botao2.setFont(new Font ("Arial",Font.PLAIN,18));
		
		JPanel botoes = new JPanel();
		botoes.setAlignmentX(CENTER_ALIGNMENT);
		botoes.add(botao1);
		botoes.add(botao2);
		
		JPanel juncao = new JPanel();
		juncao.setLayout(new BoxLayout(juncao,BoxLayout.Y_AXIS));
		juncao.setBorder(new EmptyBorder(50,50,50,50));
		juncao.add(painelGrafico);
		juncao.add(Box.createRigidArea(new Dimension(0,20)));
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
			
				System.exit(0);

			}
			
		});
		
		this.add(juncao);
		this.setLocationRelativeTo(null);
        this.setVisible(true);
		
	}
	
	private DefaultCategoryDataset DadosArmazenados(Usuario usuario) {
		
		DefaultCategoryDataset dados = new DefaultCategoryDataset();
		
		GastoDAO gastos = new GastoDAO();
		
		List<Gasto> total = gastos.buscarGastos(usuario);
		
		double comidaValor = 0, lazerValor = 0, educacaoValor = 0, 
				saudeValor = 0, transporteValor = 0, outrosValor = 0;
		
		for (Gasto gasto:total) {
			
			switch(gasto.getCategoria()) {
			
				case COMIDA:
					comidaValor+=gasto.getValorGasto();
					break;
				case LAZER:
					lazerValor+=gasto.getValorGasto();
					break;
				case EDUCACAO:
					educacaoValor+=gasto.getValorGasto();
					break;
				case SAUDE:
					saudeValor+=gasto.getValorGasto();
					break;
				case TRANSPORTE:
					transporteValor+=gasto.getValorGasto();
					break;
				case OUTROS:
					outrosValor+=gasto.getValorGasto();
					break;
			
			}
			
		}
		
		dados.addValue(comidaValor, "Valor", "Comida");
		dados.addValue(lazerValor, "Valor", "Lazer");
		dados.addValue(educacaoValor, "Valor", "Educação");
		dados.addValue(saudeValor, "Valor", "Saúde");
		dados.addValue(transporteValor, "Valor", "Transporte");
		dados.addValue(outrosValor, "Valor", "Outros");
		
		return dados;
		
	}
	
}
