package model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Conexao {

	public Connection conn;
	public String msg;
	public String id;
	
	public boolean rotina() {

		try {

			String url = "jdbc:mysql://localhost:3306/";
			String user = "root";
			String password = "";

			conn = (Connection) DriverManager.getConnection(url, user, password);

			String sql = "create database if not exists bdhospital;";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.execute();

			sql = "use bdhospital;";
			stm = conn.prepareStatement(sql);
			stm.execute();

			sql = "CREATE TABLE IF NOT EXISTS medico(\r\n" + "    id INT PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "    nome VARCHAR(50) NOT NULL,\r\n" + "    cpf VARCHAR(11) UNIQUE NOT NULL,\r\n"
					+ "    dt_nascimento DATE NOT NULL,\r\n" + "    num_sus VARCHAR(15) UNIQUE,\r\n"
					+ "    uf VARCHAR(2) NOT NULL,\r\n" + "    cidade VARCHAR(30) NOT NULL,\r\n"
					+ "    cep VARCHAR(8),\r\n" + "    bairro VARCHAR(50),\r\n"
					+ "    endereco VARCHAR(50) NOT NULL,\r\n" + "    tipo_sang VARCHAR(2),\r\n"
					+ "    telefone VARCHAR(12),\r\n" + "    nume_clt VARCHAR(10),\r\n" + "    crm VARCHAR(10),\r\n"
					+ "    especialidade VARCHAR(30),\r\n" + "    salario FLOAT NOT NULL CHECK\r\n"
					+ "        (salario > 0)\r\n" + ");";

			stm = conn.prepareStatement(sql);
			stm.execute();
			
			sql="CREATE TABLE IF NOT EXISTS paciente(\r\n" + 
					"    id INT PRIMARY KEY AUTO_INCREMENT,\r\n" + 
					"    nome VARCHAR(50) NOT NULL,\r\n" + 
					"    cpf VARCHAR(11) UNIQUE NOT NULL,\r\n" + 
					"    dt_nascimento DATE NOT NULL,\r\n" + 
					"    num_sus VARCHAR(15) UNIQUE,\r\n" + 
					"    uf VARCHAR(2) NOT NULL,\r\n" + 
					"    cidade VARCHAR(30) NOT NULL,\r\n" + 
					"    cep VARCHAR(8),\r\n" + 
					"    bairro VARCHAR(50),\r\n" + 
					"    endereco VARCHAR(50) NOT NULL,\r\n" + 
					"    tipo_sang VARCHAR(2)\r\n" + 
					");";
			
			stm = conn.prepareStatement(sql);
			stm.execute();
			
			sql="CREATE TABLE IF NOT EXISTS consulta(\r\n" + 
					"    id INT PRIMARY KEY AUTO_INCREMENT,\r\n" + 
					"    idPaciente INT,\r\n" + 
					"        FOREIGN KEY(idPaciente) REFERENCES paciente(id),\r\n" + 
					"    idMedico INT,\r\n" + 
					"        FOREIGN KEY(idMedico) REFERENCES medico(id),\r\n" + 
					"    especialidade VARCHAR(30) NOT NULL,\r\n" + 
					"    endereco VARCHAR(30) DEFAULT 'av. das americas 4500',\r\n" + 
					"    data DATE NOT NULL,\r\n" + 
					"    hora TIME NOT NULL\r\n" + 
					");";
			
			stm = conn.prepareStatement(sql);
			stm.execute();

			conn.close();
			return true;
		} catch (SQLException e) {
			msg = e.toString();
			return false;
		} catch (NullPointerException e) {
			msg = e.toString();
			return false;
		} finally {
			encerrar();
		}

	}

	public boolean conectar() {

		try {

			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);

			String url = "jdbc:mysql://localhost:3306/bdhospital";
			String user = "root";
			String password = "";

			conn = (Connection) DriverManager.getConnection(url, user, password);
			return true;
		} catch (SQLException e) {
			msg = e.toString();
			return false;
		} catch (ClassNotFoundException e) {
			msg = e.toString();
			return false;
		} catch (NullPointerException e) {
			msg = e.toString();
			return false;
		}
	}

	public boolean encerrar() {

		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			msg = e.toString();
			return false;
		} catch (NullPointerException e) {
			msg = e.toString();
			return false;
		}
	}
	
}
