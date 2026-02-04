package persistenciaBD;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class ConexaoBD {
	
	public static final String URL = "jdbc:postgresql://localhost:5432/nome_do_seu_banco";
	public static final String user = "seu_usuario";
	public static final String password = "senha_do_seu_banco";

	public static Connection getConexao() {
		
		try {
			
			Connection conexao = DriverManager.getConnection(URL,user,password);
			return conexao;
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
