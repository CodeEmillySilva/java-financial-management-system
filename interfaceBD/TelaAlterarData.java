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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dadosBD.Usuario;
import negocioBD.Principal;
import persistenciaBD.GastoDAO;

@SuppressWarnings("serial")
public class TelaAlterarData extends JFrame{

	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	
	public TelaAlterarData(int idGasto, Usuario usuario) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	
		JLabel titulo = new JLabel("ALTERAÇÃO DA DATA DO GASTO");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		JLabel dataAlt = new JLabel("Data (dd/mm/aaaa):");
		dataAlt.setMaximumSize(new Dimension(200, 20));
		dataAlt.setFont(new Font("Arial", Font.PLAIN, 18));

		JTextField dataAltText = new JTextField();
		dataAltText.setMaximumSize(new Dimension(200, 20));
		
		JLabel voltar = new JLabel("Voltar");
		voltar.setAlignmentX(CENTER_ALIGNMENT);
		voltar.setFont(new Font("Arial", Font.PLAIN,14));
		JLabel cadastrar = new JLabel("Alterar");
		cadastrar.setAlignmentX(CENTER_ALIGNMENT);
		cadastrar.setFont(new Font("Arial", Font.PLAIN,14));
		
		JButton botao1 = new JButton();
		botao1.setPreferredSize(new Dimension(100,30));
		botao1.add(voltar);
		JButton botao2 = new JButton();
		botao2.setPreferredSize(new Dimension(100,30));
		botao2.add(cadastrar);

		JPanel dataAltJ = new JPanel();
		dataAltJ.setLayout(new BoxLayout(dataAltJ,BoxLayout.X_AXIS));
		dataAltJ.add(dataAlt);
		dataAltJ.add(dataAltText);

		JPanel botoes = new JPanel();
		botoes.setAlignmentX(CENTER_ALIGNMENT);
		botoes.add(botao1);
		botoes.add(botao2);
		
		JPanel juncao = new JPanel();
		juncao.setBorder(new EmptyBorder(350,50,200,50));
		juncao.setLayout(new BoxLayout(juncao,BoxLayout.Y_AXIS));
		juncao.add(titulo);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(dataAltJ);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(botoes);
		
		botao1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
							
					dispose();
					new TelaTipoAlteracao(idGasto,usuario);

			}
			
		});
		
		botao2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
					String dataV = dataAltText.getText();		
					
					if (!verificarVazio(dataV)) {
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
					
					GastoDAO gastoAlterado = new GastoDAO();
					
					boolean check = gastoAlterado.alterarGastoParcialmente(usuario, idGasto, 2, dataV);
					
					if (check) {
						
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
						
						String tipo = "Ocorreu um erro na alteração, tente novamente";
						telaErro(tipo);
						return;
						
					}
					
			}
			
		});
		
		this.add(juncao);
		this.setVisible(true);
		
	}
	
	public boolean verificarVazio(String data) {
		
		if (data.isEmpty()) {
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
	
	private void telaErro(String tipo) {
		JOptionPane.showMessageDialog(null,tipo,"Erro na alteração",JOptionPane.ERROR_MESSAGE);
	}

}
