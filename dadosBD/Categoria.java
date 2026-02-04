package dadosBD;

public enum Categoria {
	
	COMIDA(1,"Comida"), LAZER(2,"Lazer"), EDUCACAO(3,"Educação"), SAUDE(4,"Saúde"), TRANSPORTE(5,"Transporte"), OUTROS(6,"Outros");

	private final String valor;
	private final int id_cat;
	
	private Categoria ( int id_cat,String valor) {
		this.valor=valor;
		this.id_cat=id_cat;
	}
	
	public String getCategoria() {
		return valor;
	}
	
	public int getIdcat() {
		return id_cat;
	}
	
}
