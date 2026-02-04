package negocioBD;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import dadosBD.Usuario;

public class Sistema {

	private List<Usuario> listaUsuarios = new ArrayList<>();
	private Map<String,Usuario> mapaUsuarios = new HashMap<>();

	public boolean loginUsuario(String cpf, String senha) {
		
		Usuario verificacao = mapaUsuarios.get(cpf);
		
		if (verificacao != null) {
	        if (verificacao.getIdCpfUsuario().equals(cpf) && verificacao.getSenhaUsuario().equals(senha)) {
	            System.out.println("Bem-vindo, " + verificacao.getNomeUsuario() + "!");
	            return true;
	        } else {
	            System.out.println("CPF ou senha foram digitados errados");
	            return false;
	        }
	    } else {
	        System.out.println("Usuário não existe no sistema");
	        System.out.println("Por favor, cadastre-se");
	        return false;
	    }
		
	}
	
	public boolean cadastrarNovoUsuario(Usuario usuario) {
		
		if (mapaUsuarios.containsKey(usuario.getIdCpfUsuario())) {
			System.out.println("Usuário já existe.");
			return false;
		} else {
			mapaUsuarios.put(usuario.getIdCpfUsuario(),usuario);
			listaUsuarios.add(usuario);
			System.out.println("Usuário cadastrado com sucesso!");
			return true;
		}
		
	}
	
	public Usuario buscarUsuario(String cpf) {

		return mapaUsuarios.get(cpf);
		
	}
	
}
