package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import entidades.Dependente;
import entidades.Titular;

public class DependenteDAO {
	/*Metodos que serão implementados aqui:
	 * 1-Emitir cartão do dependente
	 * 2-Efetuar compra
	 * 3-Efetuar pagamento da fatura
	 * 4-Consultar saldo*/
	
		private String cpf;
		//Metodo para inserir um registro na tabela titular do SQL
		public void inserir(Dependente d) {
			ConectaBD conexao = new ConectaBD();
			String sql = "INSERT INTO dependente (nome, documento, numero, validade, limite, saldo, fatura, titular) values (?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
				pst.setString(1, d.getNome());
				pst.setString(2, d.getDocumento());
				pst.setString(3, d.getNumero());
				pst.setString(4, d.getValidade());
				pst.setDouble(5, d.getLimite());
				pst.setDouble(6, d.getSaldo());
				pst.setDouble(7, d.getFatura());
				pst.setString(8, d.getTitular().getNome());
				pst.execute();
				System.out.println(d.getNome() + " inserido");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			
		}
		public Dependente consultar(String cpf) {
			this.cpf = cpf;
			ConectaBD conexao = new ConectaBD();
			String sql = "Select * from dependente WHERE documento = ?";
			Dependente d = null;
			try {
				PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
				pst.setString(1, cpf);
				pst.executeQuery();
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					int idDependente = rs.getInt("id");
					String nome = rs.getString("nome");
					String documento = rs.getString("documento");
					String numero = rs.getString("numero");
					String validade = rs.getString("validade");
					double limite = rs.getDouble("limite");
					double saldo = rs.getDouble("saldo");
					double fatura = rs.getDouble("fatura");
					TitularDAO dao = new TitularDAO();
					Scanner scanner = new Scanner(System.in);
					System.out.println("Digite o cpf do titular:");
			    	cpf = scanner.nextLine();
			    	Titular titular = dao.consultar(cpf);
					d = new Dependente(nome, documento, numero, validade, limite, titular);
					d.setId(idDependente);
					d.setSaldo(saldo);
					d.setFatura(fatura);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return d;
		}
		public boolean atualizar(Dependente d) {
			ConectaBD conexao = new ConectaBD();
			String sql = "UPDATE dependente SET saldo = ?, fatura = ? WHERE documento = ?";
			try {
				PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
				pst.setDouble(1, d.getSaldo());
				pst.setDouble(2, d.getFatura());
				pst.setString(3, this.cpf);
				int linhas = pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
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
		
		public Dependente consultarSaldo(String cpf) {
			this.cpf = cpf;
			ConectaBD conexao = new ConectaBD();
			String sql = "Select saldo from dependente WHERE documento = ?";
			Dependente d = null;
			try {
				PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
				pst.setString(1, cpf);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					
				
					
					double saldo = rs.getDouble("saldo");
					d = new Dependente();
					d.setSaldo(saldo);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return d;
		}

}
