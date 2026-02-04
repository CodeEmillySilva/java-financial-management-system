package persistenciaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dadosBD.Usuario;

public class UsuarioDAO {
		
	public void cadastrarUsuario(Usuario usuario) {
		
		try {
			
			String sql = "INSERT INTO usuario (id_cpf,nome_usuario,senha_usuario,idade_usuario) VALUES (?,?,?,?)";
			
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sql);
			
			permanecer.setString(1, usuario.getIdCpfUsuario());
			permanecer.setString(2, usuario.getNomeUsuario());
			permanecer.setString(3, usuario.getSenhaUsuario());
			permanecer.setInt(4, usuario.getIdadeUsuario());
			
			permanecer.execute();
			
			permanecer.close();
			conexao.close();
			
			System.out.println("Usuário está cadastrado no sistema");
			
		} catch (SQLException e) {
			System.out.println("Erro! Não foi possível adicionar usuário");
			e.printStackTrace();
		}
		
	}
	
	public boolean verificarUsuario(Usuario usuario) {
		
		try {	
			
			String sql = "SELECT * FROM usuario WHERE id_cpf = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sql);
			permanecer.setString(1, usuario.getIdCpfUsuario());
			
			ResultSet resultado = permanecer.executeQuery();
			
			boolean achado=resultado.next();
			
			resultado.close();
			permanecer.close();
			conexao.close();
			
			return achado;
			
		} catch (SQLException e) {
			System.out.println("Erro!");
			e.printStackTrace();
			return false;
		}
	}
	
	public Usuario retornaUsuario(Usuario usuario) {
		
		try {	
			
			String sql = "SELECT * FROM usuario WHERE id_cpf = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sql);
			permanecer.setString(1, usuario.getIdCpfUsuario());
			
			ResultSet resultado = permanecer.executeQuery();
			
			if (resultado.next()) {
				
				String nome = resultado.getString("nome_usuario");
				String cpf = resultado.getString("id_cpf");
		        String senha = resultado.getString("senha_usuario");
		        int idade = resultado.getInt("ideade_usuario");
		        
		       Usuario usuarioBD = new Usuario(nome, senha, cpf, idade, new ArrayList<>());
		        
				resultado.close();
				permanecer.close();
				conexao.close();
				
				return usuarioBD;
			
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro!");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean buscarCpf(String cpf) {
		
		try {	
		
			String sql = "SELECT * FROM usuario WHERE id_cpf = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sql);
			permanecer.setString(1, cpf);
			
			ResultSet resultado = permanecer.executeQuery();
			
			boolean achado=resultado.next();
			
			resultado.close();
			permanecer.close();
			conexao.close();
			
			return achado;
			
		} catch (SQLException e) {
			System.out.println("Erro!");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Usuario usuarioLog(String idUsuario, String senha) {
		
		try {
			
			String sqlLogin = "SELECT * FROM usuario WHERE id_cpf = ? AND senha_usuario = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlLogin);
			permanecer.setString(1, idUsuario);
			permanecer.setString(2, senha);
			
			ResultSet resultado = permanecer.executeQuery();
			
			if (resultado.next()) {
				
				Usuario usuario = new Usuario("","","",0,new ArrayList<>());
				
				usuario.setNomeUsuario(resultado.getString("nome_usuario"));
				usuario.setIdCpfUsuario(resultado.getString("id_cpf"));
				usuario.setSenhaUsuario(resultado.getString("senha_usuario"));
				usuario.setIdadeUsuario(resultado.getInt("idade_usuario"));

				resultado.close();
				permanecer.close();
				conexao.close();
				
				return usuario;
				
			}
			
			resultado.close();
			permanecer.close();
			conexao.close();
			
			return null;
			
		} catch(SQLException e) {
			System.out.println("Senha ou usuario estão errados");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean loginBanco(String idUsuario, String senha) {
		
		try {
			
			String sqlLogin = "SELECT * FROM usuario WHERE id_cpf = ? AND senha_usuario = ?";
			Connection conexao = ConexaoBD.getConexao();
			PreparedStatement permanecer = conexao.prepareStatement(sqlLogin);
			permanecer.setString(1, idUsuario);
			permanecer.setString(2, senha);
			
			ResultSet resultado = permanecer.executeQuery();
			
			boolean login = resultado.next();
			
			resultado.close();
			permanecer.close();
			conexao.close();
			
			return login;
			
		} catch(SQLException e) {
			System.out.println("Senha ou usuario estão errados");
			e.printStackTrace();
			return false;
		}
	}
	
}
