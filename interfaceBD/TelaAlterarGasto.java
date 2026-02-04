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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.util.List;

import dadosBD.Gasto;
import dadosBD.Usuario;
import persistenciaBD.GastoDAO;

@SuppressWarnings("serial")
public class TelaAlterarGasto extends JFrame{

	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	private GastoDAO gastoBD = new GastoDAO();
	
	public TelaAlterarGasto(Usuario usuarioMenu) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		List<Gasto> gastosExistentes = gastoBD.buscarGastos(usuarioMenu);
		
		JLabel titulo = new JLabel("ALTERAR GASTO");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		
		JButton botao1 = new JButton("Voltar");
		botao1.setAlignmentX(CENTER_ALIGNMENT);
		botao1.setFont(new Font("Arial", Font.PLAIN, 18));
		botao1.setPreferredSize(new Dimension(200,30));
		JButton botao2 = new JButton("Alterar");
		botao2.setAlignmentX(CENTER_ALIGNMENT);
		botao2.setFont(new Font("Arial", Font.PLAIN, 18));
		botao2.setPreferredSize(new Dimension(200,30));
		JButton botao3 = new JButton("Sair");
		botao3.setAlignmentX(CENTER_ALIGNMENT);
		botao3.setFont(new Font("Arial", Font.PLAIN, 18));
		botao3.setPreferredSize(new Dimension(200,30));
		
		String [] tituloColunas = {"ID Gasto","Data","Nome","Valor","Descrição","Categoria"};
		Object[][] dados = new Object [gastosExistentes.size()][6];
		
		if (!gastosExistentes.isEmpty()) {
			
			for (int i=0; i<gastosExistentes.size(); i++) {
				Gasto parametro = gastosExistentes.get(i);
				dados[i][0] = parametro.getIdGasto();
				dados[i][1] = parametro.getDataGasto();
				dados[i][2] = parametro.getNomeGasto();
				dados[i][3] = "R$ "+Double.toString(parametro.getValorGasto());
				dados[i][4] = parametro.getDescricaoGasto();
				dados[i][5] = parametro.getCategoria();
			}
			
		}
		
		if (gastosExistentes.isEmpty()) {
				
			JOptionPane.showMessageDialog(null,"A lista está vazia, logo\n"
			+ "não será possível alterar nenhum item","Planilha vazia",JOptionPane.WARNING_MESSAGE);
			dispose();
			new TelaMenu(usuarioMenu);
			
		}
		
		Integer [] numeros = new Integer [gastosExistentes.size()];
				
		for(int i=0; i<gastosExistentes.size();i++) {
			numeros[i]=gastosExistentes.get(i).getIdGasto();
		}
		
		JComboBox<Integer> lista = new JComboBox<>(numeros);
		lista.setAlignmentX(CENTER_ALIGNMENT);
		lista.setMaximumSize(new Dimension(70,30));
		
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
				
				int gastoSelecionado = (int) lista.getSelectedItem();
				
				dispose();
				new TelaTipoAlteracao(gastoSelecionado,usuarioMenu);

			}
			
		});
		
		botao3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		
		JTable tabela = new JTable(dados,tituloColunas);
		JScrollPane rolagem = new JScrollPane(tabela);
		
		JLabel orientacao = new JLabel("Escolha o elemento que deseja alterar:");
		orientacao.setFont(new Font ("Arial",Font.PLAIN,18));
		
		JPanel elegir = new JPanel();
		elegir.setAlignmentX(CENTER_ALIGNMENT);
		elegir.add(orientacao);
		elegir.setPreferredSize(new Dimension(200,30));
		elegir.add(lista);
		
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
		localizacao.add(Box.createRigidArea(new Dimension(0,20)));
		localizacao.add(elegir);
		localizacao.add(Box.createRigidArea(new Dimension(0,20)));
		localizacao.add(botoes);
		
		this.add(localizacao);
		this.setVisible(true);
		
	}
	
}
