package App;

import java.util.List;
import java.util.Scanner;

import dao.DependenteDAO;
import dao.TitularDAO;
import entidades.Dependente;
import entidades.Titular;


//Os metodos daqui com a denominação Dep são a interface para o DependenteDAO

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Opcao selecionada: Emitir cartao");
                    emiteTitular();
                    break;
                case 2:
                    System.out.println("Opcao selecionada: Emitir cartao adicional"); 
                    emiteDep();
                    break;
                case 3:
                    System.out.println("Opcao selecionada: Realizar transacao");
                    menuCartao();
                    break;
                case 4:
                    System.out.println("Opcao selecionada: Consultar saldo");
                    verificaSaldo();
                    break;
                case 5:
                	verificaHistorico();
                	break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                case 6:
                	verificaSaldoDep();
                	break;
                default:
                    System.out.println("Opcao invalida. Por favor, tente novamente.");
                    break;
            }

            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }
	/*Definição de variáveis que receberão,
	respectivamente, os registro das tabelas titular e dependente,
	para que possam ser manipuladas pelo Java*/
	private static Titular titular;
	private static Dependente dep;
	
	private static String historicoTitular = "\n";
	private static String historicoDep = "\n";
    public static void exibirMenu() {
        System.out.println(" ____MENU____");
        System.out.println("1. Emitir cartao");
        System.out.println("2. Emitir cartao adicional");
        System.out.println("3. Realizar transacao/Pagar fatura");
        System.out.println("4. Consultar saldo titular");
        System.out.println("5. Consultar histórico");
        System.out.println("6. Consultar saldo dependente");
        System.out.println("0. Sair");
        System.out.print("Digite o numero da opcao desejada: ");
    }
    /*Metodo que pergunta os dados para emissão do cartão titular,
     * em seguida esses dados são usados para se criar um objeto
     * do tipo Titular, e então esse objeto é inserido
     * no banco de dados pelo metodo inserir da classe TitularDAO*/
    public static void emiteTitular() {
    	Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.println("Informe o seu documento: ");
        String documento = scanner.nextLine();
        System.out.println("Digite o numero do seu cartão: ");
        String numero = scanner.nextLine();
        System.out.println("Digite a validade do cartão: ");
        String validade = scanner.nextLine();
        System.out.println("Digite o limite do cartão: ");
        String limite = scanner.nextLine();
        double limiteDouble = Double.parseDouble(limite);
        titular = new Titular(nome, documento, numero, validade, limiteDouble);
        TitularDAO dao = new TitularDAO();
        dao.inserir(titular);
        System.out.println("Cartão emitido:");
        System.out.println("Nome:" + titular.getNome());
        System.out.println("Documento:" + titular.getDocumento());
        System.out.println("Numero do cartão:" + titular.getNumero());
        System.out.println("validade:" + titular.getValidade());
        System.out.println("Limite:" + titular.getLimite());
	}
    
    public static void emiteDep() {
    	Scanner scanner = new Scanner(System.in);
    	
    	

	        System.out.println("Digite o nome: ");
	        String nome = scanner.nextLine();
	        System.out.println("Informe o seu documento: ");
	        String documento = scanner.nextLine();
	        System.out.println("Digite o numero do seu cartão: ");
	        String numero = scanner.nextLine();
	        System.out.println("Digite a validade do cartão: ");
	        String validade = scanner.nextLine();
	        System.out.println("Digite o cpf do titular:");
	        String cpf = scanner.nextLine();
	    	TitularDAO dao1 = new TitularDAO();
	    	titular = dao1.consultar(cpf);
	        dep = new Dependente(nome, documento, numero, validade, titular.getLimite(), titular);
	        dep.setDependente(dep);
	        DependenteDAO dao = new DependenteDAO();
	        
	        dao.inserir(dep);
	        System.out.println("Cartão emitido:");
	        System.out.println("Nome:" + dep.getNome());
	        System.out.println("Documento:" + dep.getDocumento());
	        System.out.println("Numero do cartão:" + dep.getNumero());
	        System.out.println("Validade:" + dep.getValidade());
	        System.out.println("Titular:" + dep.getTitular().getNome());
	        System.out.println("Limite :" + dep.getLimite());
		
	}
    /*Metodo que resgata o registro da tabela titular do SQL,
     * pelo numero do documento, em seguida é feita uma compra
     * pelo objeto que recebeu o registro. Em seguida o registro
     * é atualizado para os novos valores de saldo e fatura*/
    public static void compraTitular() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Digite o cpf:");
    	String cpf = scanner.nextLine();
    	TitularDAO dao = new TitularDAO();
    	titular = dao.consultar(cpf);
    	System.out.println("Insira o valor:");
    	double valor = scanner.nextDouble();
    	System.out.println("Insira o local da compra:");
    	String local = scanner.next();
    	titular.Compra(valor);
    	dao.atualizar(titular);
    	dao.inserirCompra(valor, local, cpf);
		historicoTitular += "Valor:" + valor + " Local:" + local + "\n";
    	
    	dao.atualizar(titular);
    	
    	
		
	}
    public static void compraDep() {
    	Scanner scanner = new Scanner(System.in);
    	    	System.out.println("Digite o cpf:");
    	    	String cpf = scanner.nextLine();
    	    	DependenteDAO dao = new DependenteDAO();
    	    	dep = dao.consultar(cpf);
    	    	System.out.println(dep.getDocumento());
    	    	System.out.println("Insira o valor:");
    	    	double valor = scanner.nextDouble();
    	    	System.out.println("Insira o local da compra:");
    	    	String local = scanner.next();
    	    	dep.Compra(valor);
    	    	dao.atualizar(dep);
    	    	dao.inserirCompra(valor, local, cpf);
    			//historicoTitular += "Valor:" + valor + " Local:" + local + "\n";
    	    	
    	    	
    	
	}
    public static void menuCartao() {
		System.out.println("Escolha o cartão e a ação:");
        System.out.println("1. Titular(comprar)");
        System.out.println("2. Depedente(comprar)");
        System.out.println("3. Titular(pagar)");
        System.out.println("4. Depedente(pagar)");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        
        switch(opcao) {
        case 1:
        	compraTitular();
        	break;
        
        case 2:
        	compraDep();
        	break;
        case 3:
        	FaturaTitular();
        	break;
        case 4:
        	FaturaDep();
        	break;
        }
        
        
        
	}
    public static void verificaSaldo() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Digite o cpf:");
    	String cpf = scanner.nextLine();
    	TitularDAO dao = new TitularDAO();
    	titular = dao.consultarSaldo(cpf);
    	System.out.println("Saldo:" + titular.getSaldo());
	}
    
    public static void verificaSaldoDep() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Digite o cpf:");
    	String cpf = scanner.nextLine();
    	DependenteDAO dao = new DependenteDAO();
    	dep = dao.consultarSaldo(cpf);
    	System.out.println("Saldo:" + dep.getSaldo());
	}
    public static void verificaHistorico() {
    	TitularDAO dao = new TitularDAO();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Digite o cpf:");
    	String cpf = scanner.nextLine();
    	List<String> vendas= dao.retornaHistorico(cpf);
    	System.out.println(vendas);
	}
    /*Metodo que resgata o registro da tabela titular do SQL,
     * pelo numero do documento, em seguida é feita o pagamento da fatura 
     * pelo objeto que recebeu o registro. Em seguida o registro
     * é atualizado para os novos valores de saldo e fatura*/
    public static void FaturaTitular() {
    	TitularDAO dao = new TitularDAO();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Digite o cpf:");
    	String cpf = scanner.nextLine();
    	titular = dao.consultar(cpf);
    	System.out.println("Valor a ser pago:" + titular.getFatura());
    	System.out.println("Digite o valor que irá pagar:");
    	double valor = scanner.nextDouble();
		titular.pagarFatura(valor);
		dao.atualizar(titular);
	}
    
    public static void FaturaDep() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Digite o cpf:");
    	String cpf = scanner.nextLine();
    	DependenteDAO dao = new DependenteDAO();
    	dep = dao.consultar(cpf);
    	
    	System.out.println("Valor a ser pago:" + dep.getFatura());
    	System.out.println("Digite o valor que irá pagar:");
    	double valor = scanner.nextDouble();
		dep.pagarFatura(valor);
		dao.atualizar(dep);
	}

	}





