package dadosBD;

public class Gasto {
	
	private int idGasto;
	private String nomeGasto;
	private String dataGasto;
	private String descricaoGasto;
	private double valorGasto;
	private Categoria categoria;
	
	public Gasto (String nomeGasto, String dataGasto, String descricaoGasto, double valorGasto, Categoria categoria) {
		this.nomeGasto=nomeGasto;
		this.dataGasto=dataGasto;
		this.descricaoGasto=descricaoGasto;
		this.valorGasto=valorGasto;
		this.categoria=categoria;
	}

	public String getNomeGasto() {
		return nomeGasto;
	}

	public void setNomeGasto(String nomeGasto) {
		this.nomeGasto = nomeGasto;
	}

	public String getDataGasto() {
		return dataGasto;
	}

	public void setDataGasto(String dataGasto) {
		this.dataGasto = dataGasto;
	}

	public String getDescricaoGasto() {
		return descricaoGasto;
	}

	public void setDescricaoGasto(String descricaoGasto) {
		this.descricaoGasto = descricaoGasto;
	}

	public double getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(double valorGasto) {
		this.valorGasto = valorGasto;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public int getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(int idGasto) {
		this.idGasto = idGasto;
	}
	
	public String toString() {
		return "Gasto:\n" + 
				"ID Gasto: " + String.format("%d\n", idGasto) +
				"Nome: " + nomeGasto + "\n" +
				"Data: " + dataGasto + "\n" +
				"Valor: " + String.format("%.2f", valorGasto) + "\n" +
				"Descrição: " + descricaoGasto + "\n" +
				"Categoria: " + categoria.getCategoria() + "\n";
	}

}
