package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MAdmin extends Conexao {

	public MAdmin() {

		if (!rotina()) {
			msg = "Erro ao criar database";
		}

	}

	public ArrayList<String> showTablesModel() {

		ArrayList<String> lista = new ArrayList<String>();

		try {

			if (!conectar()) {
				lista.add("Não foi possível conectar ao banco de dados : \n");
			} else {

				String sql = "SHOW TABLES ;";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (!resultado.next()) {
					lista.add("Cadastro não localizado");
				} else {

					resultado.beforeFirst();
					while (resultado.next()) {

						lista.add(resultado.getString(1));

					}

				}

			}
		} catch (SQLException e) {
			lista.add(e.toString());
		} catch (NullPointerException e) {
			lista.add(e.toString());
		} finally {
			encerrar();
		}
		return lista;
	}

	public String criarTabela(String table) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados!";
			} else {

				String sql = "CREATE TABLE IF NOT EXISTS " + table + "(\r\n"
						+ "    id INT PRIMARY KEY AUTO_INCREMENT,\r\n" + "    nome VARCHAR(50) NOT NULL,\r\n"
						+ "    cpf VARCHAR(11) UNIQUE NOT NULL,\r\n" + "    dt_nascimento DATE NOT NULL,\r\n"
						+ "    num_sus VARCHAR(15) UNIQUE,\r\n" + "    uf VARCHAR(2) NOT NULL,\r\n"
						+ "    cidade VARCHAR(30) NOT NULL,\r\n" + "    cep VARCHAR(8),\r\n"
						+ "    bairro VARCHAR(50),\r\n" + "    endereco VARCHAR(50) NOT NULL,\r\n"
						+ "    tipo_sang VARCHAR(2)\r\n" + ");";
				;

				PreparedStatement stm = conn.prepareStatement(sql);
				stm.execute();

				return "Tabela criada com sucesso!";
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}
	}

	public String deletarTabela(String table) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados : \n";
			} else {

				String sql = "DROP TABLE IF EXISTS " + table + ";";

				PreparedStatement stm = conn.prepareStatement(sql);
				stm.execute();

				return "Tabela deletada com sucesso!";
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}
	}

	public String deletarDatabase() {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados!";
			} else {

				String sql = "DROP DATABASE IF EXISTS bdhospital ";

				PreparedStatement stm = conn.prepareStatement(sql);
				stm.execute();

				return "Banco de dados deletado com sucesso!\nO programa retornará ao menu principal";
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}

	}

}
