package negocioBD;

import dadosBD.Categoria;
import dadosBD.Usuario;
import dadosBD.Gasto;
import persistenciaBD.UsuarioDAO;
import persistenciaBD.GastoDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

	//private static Sistema sistema = new Sistema();
    private static Scanner scanner = new Scanner(System.in);
    private static Usuario usuarioLogado = null;
    private static UsuarioDAO usuarioBD = new UsuarioDAO();
    private static GastoDAO gastoBD = new GastoDAO();
    
    public static void main(String[] args) {
    	
        Main();
        
    }

    public static void Main() {
    	
        int escolha;
        int parada=0;
        
        while (parada==0) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro");
            System.out.println("0 - Sair");
            
            escolha = scanner.nextInt();
            scanner.nextLine();
            
           switch(escolha) {
           		case 1:
           			loginMain();
           			break;
           		case 2:
           			cadastroNovoUsuarioMain();
           			break;
           		case 0:
           			System.out.println("Saindo...");
           			parada++;
           			return;
           		default:
           			System.out.println("Digite apenas números de 0 a 2");
           			break;
           }
            
        }
    }



    public static void loginMain() {
    	
        int escolha = 1;

        while (escolha != 0) {
            System.out.println("Digite seu ID (CPF, sem pontos e traços, apenas números):");
            String cpfTemp = scanner.nextLine();

            while(!isCpf(cpfTemp)) {
                System.out.println("Não será possível efetuar o login, pois o CPF digitado é inválido. Caso não queira continuar, digite zero. Do contrário, digite um CPF válido.");
                cpfTemp = scanner.nextLine();
                
                if(cpfTemp.equals("0")) {
                    return;
                }
            }

            System.out.println("Digite sua senha:");
            String senha = scanner.nextLine();

            if (usuarioBD.loginBanco(cpfTemp, senha)) {
            	usuarioLogado = usuarioBD.usuarioLog(cpfTemp, senha);
                MenuMain();
                break;
            } else {
                System.out.println("Deseja tentar novamente?");
                System.out.println("0 - Não (Voltará ao menu principal, você poderá sair ou se cadastrar)");
                System.out.println("1 a 9 - Você colocará o CPF e a senha novamente");

                int verificacao = 0;

                while (verificacao==0) {
                    
                    escolha = scanner.nextInt();
                    scanner.nextLine();

                    if (escolha < 0 || escolha > 9) {
                        System.out.println("Erro, por favor digite um número dentro do intervalo, 1-9:");
                    } else {
                        verificacao++;
                    }

                    
                }

                if (escolha == 0) {
                    return;
                }
            }
        }
    }

    public static void MenuMain() {
    	
        int escolhaMenu = 9;
        
        while (escolhaMenu != 0) {
            System.out.println("-------MENU-------");
            System.out.println("1 - Cadastrar novo gasto");
            System.out.println("2 - Filtrar gasto por categoria");
            System.out.println("3 - Filtrar gasto por mês");
            System.out.println("4 - Remover gasto");
            System.out.println("5 - Alterar gasto");
            System.out.println("0 - Sair do menu");
            System.out.println("Digite um número:");
            escolhaMenu = scanner.nextInt();
            scanner.nextLine();
            
            switch (escolhaMenu) {
                case 1:
                    cadastroNovoGastoMain();
                    break;
                case 2:
                    filtrarPorCategoriaMain();
                    break;
                case 3:
                    filtrarPorMesMain();
                    break;
                case 4:
                    removerGastoMain();
                    break;
                case 5:
                    alterarGastoMain();
                    break;
                case 0:
                    System.out.println("Saindo do MENU...");
                    return;
                default:
                    System.out.println("Digite apenas números entre 0 - 5");
                    break;
            }
        }
        
    }

    public static void cadastroNovoUsuarioMain() {
        
        System.out.println("Digite seu nome completo:");
        String nomeMain = scanner.nextLine();
        System.out.println("Digite sua idade:");
        int idadeMain = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite seu CPF (ele será seu ID, use apenas números):");
        String cpfCad = scanner.nextLine();
        
        while(!isCpf(cpfCad)) {
        	
        	System.out.println("Não será possível efetuar o cadastro, pois o cpf digitado é inválido, caso não queira continuar digite zero, do contrário digite um cpf válido");
        	cpfCad = scanner.nextLine();
        	
        	if(cpfCad.equals("0")) {
            	return;
            }
        	
        }
        
        System.out.println("Digite uma senha para sua conta:");
        String senhaMain = scanner.nextLine();
        
        if (idadeMain < 18) {
            System.out.println("Sistema disponível apenas para maiores de idade");
            return;
        } 
		
        Usuario novoUsuarioMain = new Usuario(nomeMain, senhaMain, cpfCad, idadeMain, new ArrayList<>());
		           
		 if (usuarioBD.verificarUsuario(novoUsuarioMain)) {
			 System.out.println("Usuário já existe.");
			 return;
		 }
		 
		 usuarioBD.cadastrarUsuario(novoUsuarioMain);
         
    }

    public static void cadastroNovoGastoMain() {

        System.out.println("Digite o nome do gasto:");
        String nomeGastoMain = scanner.nextLine();
        System.out.println("Digite a data que o gasto foi feito:");
        String dataGastoMain = scanner.nextLine();
        while (!isData(dataGastoMain)) {
        	System.out.println("Por favor, digite uma data válida");
        	dataGastoMain = scanner.nextLine();
        }
        System.out.println("Digite a descrição do gasto:");
        String descricaoGastoMain = scanner.nextLine();
        System.out.println("Digite o valor do gasto:");
        String verificarGasto = scanner.nextLine();
        while(!verificarGasto.matches("\\d+(\\.\\d+)?")) {
			System.out.println("Por favor, digite o valor apenas com números e acrescente ponto apenas se quiser colocar os centavos");
			verificarGasto = scanner.nextLine();
		}
        double valorGastoMain = Double.parseDouble(verificarGasto);
        Categoria categoriaGastoMain = escolherCategoria();

        Gasto gastoNovo = new Gasto(nomeGastoMain,dataGastoMain,descricaoGastoMain,valorGastoMain,categoriaGastoMain);
        
        gastoBD.cadastrarNovoGasto(usuarioLogado, gastoNovo);
        
    }

    public static void filtrarPorCategoriaMain() {

        Categoria categoriaCategoriaMain = escolherCategoria();
        
        List<Gasto> gastos = gastoBD.buscarCategoria(usuarioLogado, categoriaCategoriaMain);
        
        if (gastos.isEmpty()) {
        	System.out.println("Gastos por categoria vazio");
        } else {
	        for(Gasto gasto:gastos) {
	        	System.out.println(gasto.toString());
	        }
        }
        
    }

    public static void filtrarPorMesMain() {

        System.out.println("Digite o mês que você deseja verificar:");
        int mes = scanner.nextInt();
        scanner.nextLine();
        while(mes<=0||mes>12) {
        	System.out.println("Por favor, digite um mês válido (1 a 12):");
        	mes=scanner.nextInt();
        	scanner.nextLine();
        }
        
        String mesMain;
        
        if (mes<10) {
        	mesMain="0"+mes;
        } else {
        	mesMain = String.valueOf(mes);
        }
        
        List<Gasto> gastos = gastoBD.buscarMes(usuarioLogado, mesMain);
        
        if (gastos.isEmpty()) {
        	System.out.println("Gastos por mês vazio");
        } else {
	        for(Gasto gasto:gastos) {
	        	System.out.println(gasto.toString());
	        }
        }
        
    }

    public static void removerGastoMain() {

    	if (!gastoBD.buscarGastos(usuarioLogado).isEmpty()) {
    		
    		List<Gasto> gastos = gastoBD.buscarGastos(usuarioLogado);
    		
    		for(Gasto gasto:gastos) {
    			System.out.println(gasto.toString());
    		}
    		
    		System.out.println("Digite o id do Gasto:");
            int removerMain = scanner.nextInt();
            scanner.nextLine();
            
            if(gastoBD.verificarGastoBD(usuarioLogado, removerMain)) {
            	
            	if(gastoBD.removerGasto(usuarioLogado, removerMain)) {
            		System.out.println("Gasto removido");
            	} else {
            		System.out.println("Gasto não foi removido");
            	}
            	
            } else {
            	System.out.println("Gasto não foi achado");
            }
    		
    	} else {
    		
    		System.out.println("Quando gastos forem efetuados, caso necessário, poderão ser removidos");
    	
    	}

    }

    public static void alterarGastoMain() {
    	
    	if (!gastoBD.buscarGastos(usuarioLogado).isEmpty()) {
    		
    		List<Gasto> gastos = gastoBD.buscarGastos(usuarioLogado);
    		
    		for(Gasto gasto:gastos) {
	        	System.out.println(gasto.toString());
	        }
    		
            System.out.println("Digite o id do Gasto que você deseja alterar:");
            int alteraMain = scanner.nextInt();
            scanner.nextLine();
            
            if (gastoBD.verificarGastoBD(usuarioLogado, alteraMain)) {
            	
                int altMain = tipoDeAlteracao();

                switch (altMain) {
                
                	case 1:
                		System.out.println("Altere o nome do gasto:");
                		String nomeAlt = scanner.nextLine();
                		gastoBD.alterarGastoParcialmente(usuarioLogado, alteraMain, altMain, nomeAlt);
                		break;
                	case 2:
                		System.out.println("Altere a data do gasto:");
                		String dataAlt = scanner.nextLine();
                        while (!isData(dataAlt)) {
                        	System.out.println("Por favor, digite uma data válida");
                        	dataAlt = scanner.nextLine();
                        }
                        gastoBD.alterarGastoParcialmente(usuarioLogado, alteraMain, altMain, dataAlt);
                		break;
                	case 3:
                		System.out.println("Altere a descrição do gasto:");
                		String descricaoAlt = scanner.nextLine();
                		gastoBD.alterarGastoParcialmente(usuarioLogado, alteraMain, altMain, descricaoAlt);
                		break;
                	case 4:
                		System.out.println("Altere o valor do gasto:");
                		String valorAlt = scanner.nextLine();
                		while(!valorAlt.matches("\\d+(\\.\\d+)?")) {
                			System.out.println("Por favor, digite o valor apenas com números e acrescente ponto apenas se quiser colocar os centavos");
                			valorAlt = scanner.nextLine();
                		}
                		double valorAltDouble = Double.parseDouble(valorAlt);
                		gastoBD.alterarGastoParcialmente(usuarioLogado, alteraMain, altMain, valorAltDouble);
                		break;
                	case 5:
                		System.out.println("Altere a categoria do gasto:");
                		Categoria catAlt = escolherCategoria();
                		gastoBD.alterarGastoParcialmente(usuarioLogado, alteraMain, altMain, catAlt);
                		break;
                	case 6:
		                System.out.println("Digite o nome do novo gasto:");
		                String nomeAltMain = scanner.nextLine();
		                System.out.println("Digite a data que o novo gasto foi feito:");
		                String dataAltMain = scanner.nextLine();
		                System.out.println("Digite a descrição do novo gasto:");
		                String descricaoAltMain = scanner.nextLine();
		                System.out.println("Digite o valor do novo gasto:");
		                double valorAltMain = scanner.nextDouble();
		                scanner.nextLine();
		                System.out.println("Escolha a categoria a qual seu novo gasto pertence:");
		                Categoria categoriaAltMain = escolherCategoria();
		
		                Gasto NovoAltGasto = new Gasto(nomeAltMain, dataAltMain, descricaoAltMain, valorAltMain, categoriaAltMain);
		            	
		                gastoBD.alterarGasto(usuarioLogado, NovoAltGasto);
		                break;
		                
                }
                
            } else {
            	
            	System.out.println("Gasto não encontrado! Verifique os dados e tente novamente.");
            	
            }
    		
    	} else {
    		
    		System.out.println("Quando gastos forem efetuados, caso necessário, poderão ser alterados");
    	
    	}
    	
    }
    
    public static int tipoDeAlteracao() {
    	
    	System.out.println("Digite o número do que você deseja alterar:");
    	System.out.println("1 - Nome");
    	System.out.println("2 - Data");
    	System.out.println("3 - Descrição");
    	System.out.println("4 - Valor");
    	System.out.println("5 - Categoria");
    	System.out.println("6 - Alterar o gasto por completo");
        int altMain = scanner.nextInt();
        scanner.nextLine();
        
        if (altMain<1 || altMain>6) {
        	System.out.println("Por favor, digite um número de 1 a 6.");
        	System.out.println("1 - Nome");
        	System.out.println("2 - Data");
        	System.out.println("3 - Descrição");
        	System.out.println("4 - Valor");
        	System.out.println("5 - Categoria");
        	System.out.println("6 - Alterar o gasto por completo");
            altMain = scanner.nextInt();
            scanner.nextLine();
        }
        
        return altMain;
        
    }
    
    public static Categoria escolherCategoria() {
    	
    	int escolha = -3;
    	Categoria escolhaCat = Categoria.OUTROS;
    	
    	while (escolha<1 || escolha>6) {
	    	System.out.println("Escolha a categoria a qual seu gasto pertence:");
	        System.out.println("1 - Comida");
	        System.out.println("2 - Lazer");
	        System.out.println("3 - Educação");
	        System.out.println("4 - Saúde");
	        System.out.println("5 - Transporte");
	        System.out.println("6 - Outros");
	        escolha = scanner.nextInt();
	        scanner.nextLine();
    	}   
	        
        switch(escolha){
        	case 1:
        		escolhaCat=Categoria.COMIDA;
        		break;
        	case 2:
        		escolhaCat=Categoria.LAZER;
        		break;
        	case 3:
        		escolhaCat=Categoria.EDUCACAO;
        		break;
        	case 4:
        		escolhaCat=Categoria.SAUDE;
        		break;
        	case 5:
        		escolhaCat=Categoria.TRANSPORTE;
        		break;
        	case 6:
        		escolhaCat=Categoria.OUTROS;
        		break;
        	default:
        		System.out.println("Escolha inválida, digite um número de 1 a 6:");
        		break;
        }
    	
        return escolhaCat;
        
    }
    
    public static boolean isData(String data) {
    	
    	try {
    		
    		SimpleDateFormat mudar = new SimpleDateFormat("dd/MM/yyyy");
    		mudar.setLenient(false);
    		mudar.parse(data);
    		
    		return true;
    		
    	}catch(ParseException ex) {
    		
    		return false;
    	
    	}
    	 
    }
    
    public static boolean isCpf(String cpf) {

        if (!cpf.matches("\\d{11}")) {
            System.out.println("Formato incorreto! O CPF deve ter 11 dígitos numéricos.");
            return false;
        }

        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999")) {

            return false;
        }

        char d10, d11;
        
        int soma1=0, soma2=0, resto1=0, resto2=0, peso11=11, peso10=10, num1, num2;
        
        for(int i=0; i<9; i++) {
        	num1=(int)(cpf.charAt(i)-'0');
        	soma1=soma1+(num1*peso10);
        	peso10--;
        }
        
        resto1=11-(soma1%11);
        
        if (resto1==10 || resto1==11) {
        	d10= '0';
        } else {
        	d10=(char)(resto1+48);
        }
        
        for(int i=0; i<10; i++) {
        	num2=(int)(cpf.charAt(i)-'0');
        	soma2=soma2+(num2*peso11);
        	peso11--;
        }
        
        resto2=11-(soma2%11);
        
        if (resto2==10 || resto2==11) {
        	d11= '0';
        } else {
        	d11=(char)(resto2+48);
        }
        
        if(d11 == cpf.charAt(10) && d10 == cpf.charAt(9)) {
        	return true;
        } else {
        	return false;
        }
        
    }
    
}
