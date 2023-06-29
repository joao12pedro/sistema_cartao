package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*Como instalar o driver JDBC:
 * 1-Clique com o botão direito no projeto em uso
 * 2-Clique em Properties
 * 3-Clique em Java Build Path
 * 4-Clique em ClassPath
 * 5-Clique em Add external JARs
 * 6-Escolha o Driver a ser instalado
 * 7-Clique em Apply and Choose*/
public class ConectaBD {
	private Connection conexao;
	public ConectaBD() {
		String url = "jdbc:mariadb://localhost:3306/sistema_cartao";
		String user = "root";
		String pwd = "123";
		try {
			conexao = DriverManager.getConnection(url, user, pwd);
			//System.out.println("Conexão realizada");
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * Metodo que retorna a conexão
	 * @return
	 */
	public Connection getConexao() {
		return conexao;
	}
	
}
