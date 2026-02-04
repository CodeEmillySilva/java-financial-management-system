package persistenciaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dadosBD.Usuario;
import dadosBD.Categoria;
import dadosBD.Gasto;

public class GastoDAO {
	
	public boolean verificarGastoBD(Usuario usuario, int id_gasto) {
		
		try {
			
			String sqlBusca = "SELECT * FROM gasto WHERE id_cpf = ? AND id_gasto = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlBusca);
			
			permanecer.setString(1, usuario.getIdCpfUsuario());
			permanecer.setInt(2, id_gasto);
			
			ResultSet resultado = permanecer.executeQuery();
			boolean verificacao = resultado.next();
			
			resultado.close();
			permanecer.close();
			conexao.close();
			
			if(verificacao) {
				System.out.println("Gasto achado");
				return true;
			} else {
				System.out.println("Gasto não encontrado");
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um problema no banco");
			return false;
		}
		
	}

	public void cadastrarNovoGasto (Usuario usuario, Gasto gasto) {
				
		try {
			
			String sqlINSERT = "INSERT INTO gasto (nome_gasto,data_gasto,descricao_gasto,valor_gasto,id_cpf,id_categoria) VALUES (?,?,?,?,?,?)";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlINSERT);
			
			permanecer.setString(1, gasto.getNomeGasto());
			permanecer.setString(2, gasto.getDataGasto());
			permanecer.setString(3, gasto.getDescricaoGasto());
			permanecer.setDouble(4, gasto.getValorGasto());
			permanecer.setString(5, usuario.getIdCpfUsuario());
			permanecer.setInt(6, gasto.getCategoria().getIdcat());
			
			permanecer.execute();
			
			permanecer.close();
			conexao.close();
			
			System.out.println("Gasto está cadastrado no sistema");
			
		} catch (SQLException e) {
			System.out.println("Erro! Não foi possível adicionar gasto");
			e.printStackTrace();
		}

	}
	
	public boolean removerGasto (Usuario usuario, int idGasto) {
				
		try {
			
			String sqlDELETE = "DELETE FROM gasto WHERE id_gasto = ? AND id_cpf = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlDELETE);
			permanecer.setInt(1, idGasto);
			permanecer.setString(2, usuario.getIdCpfUsuario());
			
			int verificacao = permanecer.executeUpdate();
			
			permanecer.close();
			conexao.close();
			
			if(verificacao==1) {
				System.out.println("Gasto deletado com sucesso");
				return true;
			} else {
				System.out.println("Ocorreu um erro e não foi possível deletar o gasto.");
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro! Não foi possível remover gasto");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean alterarGastoParcialmente(Usuario usuario, int id_gasto, int tipo, Object alteracao) {
		
		String alt = "";
		
		switch(tipo) {
			case 1:
				alt = "nome_gasto";
				break;
			case 2:
				alt = "data_gasto";
				break;
			case 3:
				alt = "descricao_gasto";
				break;
			case 4:
				alt = "valor_gasto";
				break;
			case 5:
				alt = "id_categoria";
				break;
			default:
				System.out.println("Um problema ocorreu na troca de nome");
				return false;
		}
				
		try {
			
			String sqlE = "UPDATE gasto SET " + alt + " = ? WHERE id_gasto = ? AND id_cpf = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlE);
			
			switch(tipo) {
			
				case 1:
					permanecer.setString(1, (String)alteracao);
					break;
				
				case 2:
					permanecer.setString(1, (String)alteracao);
					break;
				case 3:
					permanecer.setString(1, (String)alteracao);
					break;
				case 4:
					permanecer.setDouble(1, (double)alteracao);
					break;
				case 5:
					Categoria cat = (Categoria) alteracao;
					permanecer.setInt(1, cat.getIdcat());
					break;
				default:
					System.out.println("Erro na execução de alterar uma parte do gasto");
					break;
					
			}
		
			permanecer.setInt(2,id_gasto);
			permanecer.setString(3, usuario.getIdCpfUsuario());
			
			int verificacao = permanecer.executeUpdate();
			
			permanecer.close();
			conexao.close();
			
			if(verificacao==1) {
				System.out.println("Gasto alterado parcialmente com sucesso");
				return true;
			} else {
				System.out.println("Ocorreu um erro e não foi possível alterar parcialmente o gasto.");
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro! Não foi possível alterar gasto");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean alterarGasto(Usuario usuario,Gasto gasto) {
				
		try {
			
			String sqlALT = "UPDATE gasto SET nome_gasto = ?,data_gasto = ?,descricao_gasto = ?,valor_gasto = ?,id_categoria = ? WHERE id_gasto = ? AND id_cpf = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlALT);
			
			permanecer.setString(1, gasto.getNomeGasto()); 
			permanecer.setString(2, gasto.getDataGasto());
			permanecer.setString(3, gasto.getDescricaoGasto());
			permanecer.setDouble(4, gasto.getValorGasto());
			permanecer.setInt(5, gasto.getCategoria().getIdcat());
			permanecer.setInt(6, gasto.getIdGasto());
			permanecer.setString(7, usuario.getIdCpfUsuario());

			int verificacao = permanecer.executeUpdate();
			
			permanecer.close();
			conexao.close();
			
			if(verificacao==1) {
				System.out.println("Gasto alterado com sucesso");
				return true;
			} else {
				System.out.println("Ocorreu um erro e não foi possível alterar o gasto.");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Erro! Não foi possível alterar gasto");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Gasto> buscarGastos(Usuario usuario) {
		
		List<Gasto> lista= new ArrayList<>();
				
		try {
			
			String sqlGastos = "SELECT * FROM gasto WHERE id_cpf = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlGastos);
			permanecer.setString(1, usuario.getIdCpfUsuario());
			
			ResultSet resultado = permanecer.executeQuery();
			
			while(resultado.next()) {
				
				Gasto gasto = new Gasto("","","",0,null);
				gasto.setIdGasto(resultado.getInt("id_gasto"));
				gasto.setNomeGasto(resultado.getString("nome_gasto"));
				gasto.setDataGasto(resultado.getString("data_gasto"));
				gasto.setDescricaoGasto(resultado.getString("descricao_gasto"));
				gasto.setValorGasto(resultado.getDouble("valor_gasto"));
				int tipo = resultado.getInt("id_categoria");
				
				switch(tipo) {
					case 1:
						gasto.setCategoria(Categoria.COMIDA);
						break;
					case 2:
						gasto.setCategoria(Categoria.LAZER);
						break;
					case 3:
						gasto.setCategoria(Categoria.EDUCACAO);
						break;
					case 4:
						gasto.setCategoria(Categoria.SAUDE);
						break;
					case 5:
						gasto.setCategoria(Categoria.TRANSPORTE);
						break;
					case 6:
						gasto.setCategoria(Categoria.OUTROS);
						break;
					default:
						System.out.println("Ocorreu um problema para alocar a categoria");
						break;
				}
				
				lista.add(gasto);
				
			}
			
			resultado.close();
			permanecer.close();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro para imprimir a lista");
		}
		
		return lista;
	}
	
	public List<Gasto> buscarCategoria(Usuario usuario, Categoria categoria) {
		
		List<Gasto> listaCat= new ArrayList<>();
		
		try {
			
			String sqlGastos = "SELECT * FROM gasto WHERE id_cpf = ? AND id_categoria = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlGastos);
			permanecer.setString(1, usuario.getIdCpfUsuario());
			permanecer.setInt(2, categoria.getIdcat());
			
			ResultSet resultado = permanecer.executeQuery();
			
			while(resultado.next()) {
				
				Gasto gasto = new Gasto("","","",0,categoria);
				gasto.setIdGasto(resultado.getInt("id_gasto"));
				gasto.setNomeGasto(resultado.getString("nome_gasto"));
				gasto.setDataGasto(resultado.getString("data_gasto"));
				gasto.setDescricaoGasto(resultado.getString("descricao_gasto"));
				gasto.setValorGasto(resultado.getDouble("valor_gasto"));
				
				listaCat.add(gasto);

			}
			
			resultado.close();
			permanecer.close();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro para imprimir a lista filtrada por categoria");
		}
		
		return listaCat;
		
	}
	
	public List<Gasto> buscarMes(Usuario usuario, String dataGasto){
	
		List<Gasto> listaMes= new ArrayList<>();
			
		try {
			
			String sqlGastos = "SELECT * FROM gasto WHERE id_cpf = ? AND data_gasto LIKE ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlGastos);
			permanecer.setString(1, usuario.getIdCpfUsuario());
			permanecer.setString(2, "%/" + dataGasto + "/%");
			
			ResultSet resultado = permanecer.executeQuery();
			
			while(resultado.next()) {
				
				Gasto gasto = new Gasto("","","",0,null);
				gasto.setIdGasto(resultado.getInt("id_gasto"));
				gasto.setNomeGasto(resultado.getString("nome_gasto"));
				gasto.setDataGasto(resultado.getString("data_gasto"));
				gasto.setDescricaoGasto(resultado.getString("descricao_gasto"));
				gasto.setValorGasto(resultado.getDouble("valor_gasto"));
				
				int cat = resultado.getInt("id_categoria");
				
				switch(cat) {
					case 1:
						gasto.setCategoria(Categoria.COMIDA);
						break;
					case 2:
						gasto.setCategoria(Categoria.LAZER);
						break;
					case 3:
						gasto.setCategoria(Categoria.EDUCACAO);
						break;
					case 4:
						gasto.setCategoria(Categoria.SAUDE);
						break;
					case 5:
						gasto.setCategoria(Categoria.TRANSPORTE);
						break;
					case 6:
						gasto.setCategoria(Categoria.OUTROS);
						break;
					default:
						System.out.println("Ocorreu um problema na alocação de categoria");
						
				}
				
				listaMes.add(gasto);

			}
			
			resultado.close();
			permanecer.close();
			conexao.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorre um problema na listagem por mês.");
		}
		
		return listaMes;
		
	}

}
