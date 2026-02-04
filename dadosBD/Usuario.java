package dadosBD;

import java.util.List;
import java.util.ArrayList;

public class Usuario {

	private String nomeUsuario;
	private String senhaUsuario;
	private String idCpfUsuario;
	private int idadeUsuario;
	private List<Gasto> gastosUsuario;
	private int idGastoUsuario = 1;
	
	public Usuario (String nomeUsuario, String senhaUsuario, String idCpfUsuario, int idadeUsuario, List<Gasto> gastosUsuario) {
		this.nomeUsuario=nomeUsuario;
		this.senhaUsuario=senhaUsuario;
		this.idCpfUsuario=idCpfUsuario;
		this.idadeUsuario=idadeUsuario;
		this.gastosUsuario=new ArrayList<>();
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getIdCpfUsuario() {
		return idCpfUsuario;
	}

	public void setIdCpfUsuario(String idCpfUsuario) {
		this.idCpfUsuario = idCpfUsuario;
	}

	public int getIdadeUsuario() {
		return idadeUsuario;
	}

	public void setIdadeUsuario(int idadeUsuario) {
		this.idadeUsuario = idadeUsuario;
	}

	public List<Gasto> getGastosUsuario() {
		return gastosUsuario;
	}
	
	public void cadastrarNovoGasto(Gasto gasto) {
		gastosUsuario.add(gasto);
		gasto.setIdGasto(idGastoUsuario++);
	}
	
	public boolean removerGasto(int idUsado) {

		int check = 0;
		
		if (gastosUsuario!=null) {
			for (int i=0; i<gastosUsuario.size(); i++) {
				Gasto temp = gastosUsuario.get(i);
				if (temp.getIdGasto()==idUsado) {
					gastosUsuario.remove(i);
					check++;
				}
			}
		}
		
		if (check==0) {
			System.out.println("Gasto não foi achado.");
			return false;
		} else {
			System.out.println("Gasto removido com sucesso!");
			return true;
		}
		
	}
	
	public List<Gasto> filtrarPorCategoria(Categoria categoriaGasto) {
		
		if (gastosUsuario == null) {
	        System.out.println("Nenhum gasto foi realizado nesses CPF.");
	        return new ArrayList<>();
	    }
		
		List<Gasto> filtradoCategoria = new ArrayList<>();
		
		for(Gasto gasto:gastosUsuario) {
			if (gasto.getCategoria().equals(categoriaGasto)) {
				filtradoCategoria.add(gasto);
			}
		}
		
		if (filtradoCategoria.isEmpty()) {
			System.out.println("Não foram feitos gastos nessa categoria ainda.");
		} else {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Gastos por categoria:\n");
			
			for (Gasto gasto:filtradoCategoria) {
				sb.append("- ").append(gasto).append("\n");
			}
			
			System.out.println(sb.toString());
		}
		
		return filtradoCategoria;
		
	}
	
	public List<Gasto> filtrarPorMes(String dataGasto) {
		
		if (gastosUsuario == null) {
	        System.out.println("Nenhum gasto foi realizado nesses CPF.");
	        return new ArrayList<>();
	    }
		
		List<Gasto> filtradoMes = new ArrayList<>();
		
		for(Gasto gasto:gastosUsuario) {
			if (gasto.getDataGasto().substring(3, 5).equals(dataGasto)) {
				filtradoMes.add(gasto);
			}
		}
		
		if (filtradoMes.isEmpty()) {
			System.out.println("Não foram feitos gastos nesse mês.");
		} else {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Gastos por mês:\n");
			
			for (Gasto gasto:filtradoMes) {
				sb.append("- ").append(gasto).append("\n");
			}
			
			System.out.println(sb.toString());
		}
		
		return filtradoMes;
	}
	
	public boolean alterarGasto(int idUsado, Gasto gastoNovo) {

		int check = 0;
		
		if(gastosUsuario!=null) {
			for (int i=0; i<gastosUsuario.size(); i++) {
				Gasto temp = gastosUsuario.get(i);
				if (temp.getIdGasto()==idUsado) {
					temp.setCategoria(gastoNovo.getCategoria());
					temp.setDataGasto(gastoNovo.getDataGasto());
					temp.setDescricaoGasto(gastoNovo.getDescricaoGasto());
					temp.setNomeGasto(gastoNovo.getNomeGasto());
					temp.setValorGasto(gastoNovo.getValorGasto());
					check++;
				}
			}
		}
		
		if (check==0) {
			System.out.println("Gasto não foi achado.");
			return false;
		} else {
			System.out.println("Gasto alterado com sucesso!");
			return true;
		}
		
	}
	
	public boolean alteracaoEspecifica(int idGasto, int tipoAlteracao, Object alteracaoValor) {
		
		Gasto altGasto=null;
		
		for (Gasto gasto:gastosUsuario) {
			if(idGasto==gasto.getIdGasto()) {
				altGasto = gasto;
				break;
			}
		}
		
		if (altGasto==null) {
			System.out.println("Gasto não encontrado");
			return false;
		}
		
		switch (tipoAlteracao) {
		
			case 1:
				String nome = (String) (alteracaoValor);
				altGasto.setNomeGasto(nome);
				return true;
			case 2:
				String data = (String) (alteracaoValor);
				altGasto.setDataGasto(data);
				return true;
			case 3:
				String descricao = (String) (alteracaoValor);
				altGasto.setDescricaoGasto(descricao);
				return true;
			case 4:
				double valor = (Double)(alteracaoValor);
				altGasto.setValorGasto(valor);
				return true;
			case 5:
				Categoria cat = (Categoria) (alteracaoValor);
				altGasto.setCategoria(cat);
				return true;
			default:
			    System.out.println("Tipo de alteração inválido.");
			    return false;
	
		}
	}
	
	public boolean existeGasto(int idUtilizado) {
		
		if (gastosUsuario!=null) {
			for (int i=0; i<gastosUsuario.size(); i++) {
				Gasto temp = gastosUsuario.get(i);
				if (temp.getIdGasto()==idUtilizado) {
					System.out.println("Gasto existe, prossiga!");
					return true;
				}
			}
		}

			System.out.println("Gasto não foi achado.");
			return false;
	}
	
	public boolean visualizarGastos() {
		
		if(gastosUsuario==null || gastosUsuario.isEmpty()) {
			System.out.println("Não há gastos.");
			return false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Gastos:\n");
		for(Gasto gasto:gastosUsuario) {
			sb.append(gasto.getIdGasto()).append(" - ").append(gasto).append("\n");
		}
		
		System.out.println(sb.toString());
		return true;
		
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Usuário:\n");
		sb.append("Nome: ").append(nomeUsuario).append("\n");
		sb.append("Idade: ").append(idadeUsuario).append("\n");
		sb.append("ID/CPF: ").append(idCpfUsuario).append("\n");
		sb.append("Gastos:\n");
		
		for (Gasto gasto:gastosUsuario) {
			sb.append(gasto.getIdGasto()).append(" - ").append(gasto).append("\n");
		}
		
		return sb.toString();
		
	}
	
}
