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
import persistenciaBD.GastoDAO;

@SuppressWarnings("serial")
public class TelaAlterarDescricao extends JFrame{
	
	private static final int DEFAULT_WIDTH=1000;
	private static final int DEFAULT_HEIGHT=1000;
	
	public TelaAlterarDescricao(int idGasto, Usuario usuario) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		JLabel titulo = new JLabel("ALTERAÇÃO DA DESCRIÇÃO DO GASTO");
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		JLabel descricaoAlt = new JLabel("Descricao:");
		descricaoAlt.setMaximumSize(new Dimension(200, 20));
		descricaoAlt.setFont(new Font("Arial", Font.PLAIN, 18));

		JTextField descricaoAltText = new JTextField();
		descricaoAltText.setMaximumSize(new Dimension(200, 20));
		
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

		JPanel descricaoAltJ = new JPanel();
		descricaoAltJ.setLayout(new BoxLayout(descricaoAltJ,BoxLayout.X_AXIS));
		descricaoAltJ.add(descricaoAlt);
		descricaoAltJ.add(descricaoAltText);
		JPanel botoes = new JPanel();
		botoes.setAlignmentX(CENTER_ALIGNMENT);
		botoes.add(botao1);
		botoes.add(botao2);
		
		JPanel juncao = new JPanel();
		juncao.setBorder(new EmptyBorder(350,50,200,50));
		juncao.setLayout(new BoxLayout(juncao,BoxLayout.Y_AXIS));
		juncao.add(titulo);
		juncao.add(Box.createRigidArea(new Dimension(0, 10)));
		juncao.add(descricaoAltJ);
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
		
					String descricaoV = descricaoAltText.getText();
					
					if (!verificarVazio(descricaoV)) {
						String tipo = "Por favor, preencha todos os campos.";
						telaErro(tipo);
						return;
					}

					GastoDAO gastoBD = new GastoDAO();
					
					boolean check = gastoBD.alterarGastoParcialmente(usuario, idGasto, 3, descricaoV);
					
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
	
	public boolean verificarVazio(String descricao) {
		
		if (descricao.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}
		
	private void telaErro(String tipo) {
		JOptionPane.showMessageDialog(null,tipo,"Erro na alteração",JOptionPane.ERROR_MESSAGE);
	}

}
