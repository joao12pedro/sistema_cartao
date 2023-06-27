package entidades;

public class CartaodeCredito {
	//Atributos que serão herdados pelas classes, 
	
	private String nome;
	private String documento;
	private String numero;
	private String validade;
	
	public CartaodeCredito() {
		
	}
	
	
	public CartaodeCredito(String nome, String documento, String numero, String validade) {
		this.nome = nome;
		this.documento = documento;
		this.numero = numero;
		this.validade = validade;
		

	}
	
	

	



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}







	
	
	
	

}
