CREATE DATABASE sistema_cartao



CREATE TABLE titular(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50),
	documento CHAR(11),
	numero INT,
	validade DATE,
	limite DOUBLE,
	saldo DOUBLE,
	fatura DOUBLE
);

CREATE TABLE dependente(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50),
	documento CHAR(11),
	numero INT,
	validade DATE,
	limite DOUBLE,
	saldo DOUBLE,
	fatura DOUBLE,
	titular VARCHAR(50)
);
 CREATE TABLE venda(
	id INT PRIMARY KEY AUTO_INCREMENT,
	valor DOUBLE,
	local VARCHAR(50),
	cpf_cliente CHAR(11)
	
);