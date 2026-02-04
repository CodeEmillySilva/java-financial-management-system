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
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import dadosBD.Gasto;
import dadosBD.Usuario;
import persistenciaBD.GastoDAO;

@SuppressWarnings("serial")
public class TelaGastoScatter extends JFrame{
	
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	
	public TelaGastoScatter(Usuario usuario) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		double[] gastos = dadosMes(usuario);
		
		XYSeries sequencia = new XYSeries("Gastos por mês");
		
		for(int j=0; j<gastos.length; j++) {
			sequencia.add(j+1,gastos[j]);
		}
		
		XYSeriesCollection dados = new XYSeriesCollection();
		dados.addSeries(sequencia);
		
		JFreeChart grafico = ChartFactory.createScatterPlot("SCATTER - GASTO POR MÊS", "MÊS", "VALOR EM R$", dados);
		
		ChartPanel painel = new ChartPanel(grafico);
		painel.setPreferredSize(new Dimension(800, 800));
		
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
	
	private double[] dadosMes(Usuario usuario) {
		
		GastoDAO gastosBD = new GastoDAO();
		
		List<Gasto> gastos = gastosBD.buscarGastos(usuario);
		
		double[] gastosMes = new double[12];
		
		for(Gasto gasto:gastos) {
			
			switch(gasto.getDataGasto().substring(3, 5)){
			
				case "01":
					gastosMes[0]+=gasto.getValorGasto();
					break;
				case "02":
					gastosMes[1]+=gasto.getValorGasto();
					break;					
				case "03":
					gastosMes[2]+=gasto.getValorGasto();
					break;					
				case "04":
					gastosMes[3]+=gasto.getValorGasto();
					break;
				case "05":
					gastosMes[4]+=gasto.getValorGasto();
					break;
				case "06":
					gastosMes[5]+=gasto.getValorGasto();
					break;
				case "07":
					gastosMes[6]+=gasto.getValorGasto();
					break;
				case "08":
					gastosMes[7]+=gasto.getValorGasto();
					break;
				case "09":
					gastosMes[8]+=gasto.getValorGasto();
					break;
				case "10":
					gastosMes[9]+=gasto.getValorGasto();
					break;
				case "11":
					gastosMes[10]+=gasto.getValorGasto();
					break;
				case "12":
					gastosMes[11]+=gasto.getValorGasto();
					break;
					
			}
			
		}
		
		return gastosMes;
		
	}
	
}
