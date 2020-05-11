package model;

import java.sql.SQLException;
import java.util.ArrayList;
import control.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MPaciente extends Conexao {

	public boolean cadastrarModel(Paciente p) {

		try {

			if (!conectar()) {
				msg = "Não foi possível conectar ao banco de dados : \n";
				return false;
			} else {

				String sql = "INSERT INTO paciente(nome,cpf,dt_nascimento,num_sus,uf,cidade,cep,bairro,endereco,tipo_sang) VALUES(?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stm = conn.prepareStatement(sql);

				stm.setString(1, p.getNome());
				stm.setString(2, p.getCpf());
				stm.setString(3, p.getData_nascimento());
				stm.setString(4, p.getNumero_sus());
				stm.setString(5, p.getUf());
				stm.setString(6, p.getCidade());
				stm.setString(7, p.getCep());
				stm.setString(8, p.getBairro());
				stm.setString(9, p.getEndereco());
				stm.setString(10, p.getTipo_sanguineo());

				stm.executeUpdate();

				return true;
			}
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

	public ArrayList<Paciente> consultarModel(int i) {

		ArrayList<Paciente> lista = new ArrayList<Paciente>();
		try {

			if (!conectar()) {
				msg = "Não foi possivel conectar ao banco de dados";
				return lista;
			} else {

				String sql = "SELECT * FROM paciente WHERE id = " + i + ";";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (!resultado.next()) {
					msg = "Cadastro não localizado!";
					return lista;
				} else {

					resultado.beforeFirst();
					while (resultado.next()) {

						Paciente p = new Paciente();

						p.setNome(resultado.getString(2));
						p.setCpf(resultado.getString(3));
						// acertar data nascimento antes de passar para lista
						p.setData_nascimento(resultado.getString(4));
						p.setNumero_sus(resultado.getString(5));
						p.setUf(resultado.getString(6));
						p.setCidade(resultado.getString(7));
						p.setCep(resultado.getString(8));
						p.setBairro(resultado.getString(9));
						p.setEndereco(resultado.getString(10));
						p.setTipo_sanguineo(resultado.getString(11));

						lista.add(p);
					}
					return lista;
				}
			}
		} catch (SQLException e) {
			msg = e.toString();
			return lista;
		} catch (NullPointerException e) {
			msg = e.toString();
			return lista;
		} finally {
			encerrar();
		}
	}

	public int consultarNome(String query) {

		try {

			if (!conectar()) {
				msg = "Não foi possível conectar ao banco de dados : \n";
				return 0;
			} else {

				String sql = "SELECT id FROM paciente WHERE nome = \'" + query + "\';";

				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (resultado.next()) {

					resultado.beforeFirst();

					int aux = 0;

					while (resultado.next()) {
						aux = resultado.getInt(1);
					}

					return aux;
				} else {
					return consultarCpf(query);

				}
			}

		} catch (SQLException e) {

			msg = e.toString();
			return 0;

		} catch (NullPointerException e) {
			msg = e.toString();
			return 0;
		} finally {
			encerrar();
		}
	}

	public int consultarCpf(String query) {

		try {

			if (!conectar()) {
				msg = "Não foi possível conectar ao banco de dados : \n";
				return 0;
			} else {

				String sql = "SELECT id FROM paciente WHERE cpf = \'" + query + "\';";

				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (resultado.next()) {

					resultado.beforeFirst();

					int aux = 0;

					while (resultado.next()) {
						aux = resultado.getInt(1);
					}

					return aux;
				} else {
					return 0;

				}
			}

		} catch (SQLException e) {

			msg = e.toString();
			return 0;

		} catch (NullPointerException e) {
			msg = e.toString();
			return 0;
		} finally {
			encerrar();
		}
	}

	public String editarUf(String novo, int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "UPDATE paciente SET uf = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

				PreparedStatement stm = conn.prepareStatement(sql);
				stm.execute();

				return "Cadastro alterado com sucesso!";
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}
	}

	public String editarCidade(String novo, int id) {
		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "UPDATE paciente SET cidade = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

				PreparedStatement stm = conn.prepareStatement(sql);
				stm.execute();

				return "Cadastro alterado com sucesso!";
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}
	}

	public String editarCep(String novo, int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "UPDATE paciente SET cep = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

				PreparedStatement stm = conn.prepareStatement(sql);
				stm.execute();

				return "Cadastro alterado com sucesso!";
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}
	}

	public String editarBairro(String novo, int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "UPDATE paciente SET bairro = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

				PreparedStatement stm = conn.prepareStatement(sql);
				stm.execute();

				return "Cadastro alterado com sucesso!";
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}
	}

	public String editarEndereco(String novo, int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "UPDATE paciente SET endereco = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

				PreparedStatement stm = conn.prepareStatement(sql);
				stm.execute();

				return "Cadastro alterado com sucesso!";
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}
	}

	public String excluirModel(int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "DELETE FROM paciente WHERE id = " + Integer.toString(id) + ";";
				PreparedStatement stm = conn.prepareStatement(sql);

				stm.execute();

				return "Cadastro excluido com sucesso!";
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}
	}

	public String contarPacientesModel() {

		int n = 0;

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "SELECT COUNT(nome) FROM paciente";

				PreparedStatement stm = conn.prepareStatement(sql);
				ResultSet resultado = stm.executeQuery();

				while (resultado.next()) {
					n = resultado.getInt(1);
				}
				if (n == 0) {
					return "Não existem pacientes cadastrados!";
				} else {
					return Integer.toString(n);
				}
			}
		} catch (SQLException e) {
			return e.toString();
		} catch (NullPointerException e) {
			return e.toString();
		} finally {
			encerrar();
		}
	}

	

	public ArrayList<String> pacientesPorBairroModel() {

		ArrayList<String> lista = new ArrayList<String>();

		try {

			if (!conectar()) {
				lista.add("Não foi possivel conectar ao banco de dados");
			} else {

				String sql = "SELECT bairro, COUNT(nome) FROM paciente GROUP BY bairro;";

				PreparedStatement stm = conn.prepareStatement(sql);
				ResultSet resultado = stm.executeQuery();

				while (resultado.next()) {

					lista.add(resultado.getString(1) + " : " + resultado.getString(2));

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

	public ArrayList<String> listarPacientesModel() {

		ArrayList<String> lista = new ArrayList<String>();

		try {

			if (!conectar()) {
				lista.add("Não foi possível conectar ao banco de dados : \n");
			} else {

				String sql = "SELECT nome FROM paciente ORDER BY nome ASC;";

				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (!resultado.next()) {
					lista.add("Não existem pacientes cadastrados");
				} else {

					resultado.beforeFirst();

					lista.add("Pacientes cadastrados por ordem alfabetica : ");

					while (resultado.next()) {
						lista.add(resultado.getString(1));
					}

					lista.add("Quantidade de pacientes cadastrados : " + contarPacientesModel());

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

}
