package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Titular;

public class TitularDAO {
	private String cpf;
	//Metodo para inserir um registro na tabela titular do SQL
	public void inserir(Titular t) {
		ConectaBD conexao = new ConectaBD();
		String sql = "INSERT INTO titular (nome, documento, numero, validade, limite, saldo, fatura) values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
			pst.setString(1, t.getNome());
			pst.setString(2, t.getDocumento());
			pst.setString(3, t.getNumero());
			pst.setString(4, t.getValidade());
			pst.setDouble(5, t.getLimite());
			pst.setDouble(6, t.getSaldo());
			pst.setDouble(7, t.getFatura());
			pst.execute();
			System.out.println(t.getNome() + " inserido");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	//Metodo que seleciona um determinado registro da tabela titular
	public Titular consultar(String cpf) {
		this.cpf = cpf;
		ConectaBD conexao = new ConectaBD();
		String sql = "Select * from titular WHERE documento = ?";
		Titular t = null;
		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
			pst.setString(1, cpf);
			pst.executeQuery();
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int idTitular = rs.getInt("id");
				String nome = rs.getString("nome");
				String documento = rs.getString("documento");
				String numero = rs.getString("numero");
				String validade = rs.getString("validade");
				double limite = rs.getDouble("limite");
				double saldo = rs.getDouble("saldo");
				double fatura = rs.getDouble("fatura");
				t = new Titular(nome, documento, numero, validade, limite);
				t.setId(idTitular);
				t.setSaldo(saldo);
				t.setFatura(fatura);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return t;
	}
	//Metodo que permite atualizar um determinado registro da tabela titular
	public boolean atualizar(Titular t) {
		ConectaBD conexao = new ConectaBD();
		String sql = "UPDATE titular SET saldo = ?, fatura = ? WHERE documento = ?";
		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
			pst.setDouble(1, t.getSaldo());
			pst.setDouble(2, t.getFatura());
			pst.setString(3, this.cpf);
			int linhas = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	//Metodo que consulta o saldo de um determinado registro na tabela titular
	public Titular consultarSaldo(String cpf) {
		this.cpf = cpf;
		ConectaBD conexao = new ConectaBD();
		String sql = "Select saldo from titular WHERE documento = ?";
		Titular t = null;
		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				
			
				
				double saldo = rs.getDouble("saldo");
				t = new Titular();
				t.setSaldo(saldo);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return t;
	}
	
	public void inserirCompra(double valor, String local, String cpf) {
		ConectaBD conexao = new ConectaBD();
		String sql = "INSERT INTO venda (valor, local, cpf_cliente) values (?,?,?)";
		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
			pst.setDouble(1, valor);
			pst.setString(2, local);
			pst.setString(3, cpf);
			pst.execute();
			System.out.println(" venda inserida");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	} 
	public List<String> retornaHistorico(String cpf) {
		List<String> vendas = new ArrayList<>();
		TitularDAO dao = new TitularDAO();
		ConectaBD conexao = new ConectaBD();
		String sql = "SELECT valor, local FROM venda WHERE cpf_cliente = ?";
		try {
			PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
			
				
				double valor = rs.getDouble("valor");
				String local = rs.getString("local");
				vendas.add(valor + " " + local + "\n");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return vendas;
	}
	

}
