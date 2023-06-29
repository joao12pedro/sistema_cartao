package entidades;

public class Titular extends CartaodeCredito{
	private int id;
	private double limite;
	private double saldo;
	private double fatura;
	
	public Titular() {
	}
	//Definição dos atributos herdados
	public Titular(String nome, String documento, String numero, String validade, double limite) {
		super( nome,documento, numero, validade);
		this.limite = limite;
		saldo = limite;
		
		
		
	}
	
	
	public void Compra(double valor) {
		//Se o saldo for menor que o valor da compra, a compra não será realizada
		if (saldo < valor) {
			System.out.println("Saldo insuficiente");
		}
		else if (saldo > 0) {
			this.saldo -= valor;
			fatura += valor;
		}
		else {
			System.out.println("Limite excedido");
		}
		
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	


	

	
	
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
	public void pagarFatura(double valor) {
		fatura -= valor;
		saldo += valor;
		if (fatura <= 0) {
			System.out.println("Fatura paga");
			
		}
		else {
			System.out.println("Fatura pendente: " + fatura);
		}
	 
	}
	public double getFatura() {
		return fatura;
	}
	public void setFatura(double fatura) {
		this.fatura = fatura;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
