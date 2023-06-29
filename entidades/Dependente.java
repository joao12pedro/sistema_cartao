package entidades;

public class Dependente extends CartaodeCredito{
	private Titular titular;
	private Dependente dependente;
	private double limite;
	private double saldo;
	private double fatura;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Dependente(String nome, String documento, String numero, String validade, double limite, Titular titular) {
		super(nome,documento, numero, validade);
		this.titular = titular;
		this.limite = limite;
		saldo = limite;
		
	}
	
	public Dependente() {
		// TODO Auto-generated constructor stub
	}
	public Titular getTitular() {
		return titular;
	}
	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	public Dependente getDependente() {
		return dependente;
	}
	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public void Compra(double valor) {
		if (saldo < valor) {
			System.out.println("Saldo insuficiente");
		}
		else if (saldo > 0) {
			this.fatura += valor;
			this.saldo -= valor;
		}
		else {
			System.out.println("Limite excedido");
		}
		
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
	
	


}
