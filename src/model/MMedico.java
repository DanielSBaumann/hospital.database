package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import control.Medico;

public class MMedico extends Conexao {

	public MMedico() {

		if (!rotina()) {
			msg = "Erro ao criar database";
		}

	}

	public boolean cadastrarModel(Medico m) {

		try {

			if (!conectar()) {
				msg = "Não foi possível conectar ao banco de dados : \n";
				return false;
			} else {

				String sql = "INSERT INTO medico(nome,cpf,dt_nascimento,num_sus,uf,cidade,cep,bairro,endereco,tipo_sang,telefone,nume_clt,crm,especialidade,salario) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stm = conn.prepareStatement(sql);

				stm.setString(1, m.getNome());
				stm.setString(2, m.getCpf());
				stm.setString(3, m.getData_nascimento());
				stm.setString(4, m.getNumero_sus());
				stm.setString(5, m.getUf());
				stm.setString(6, m.getCidade());
				stm.setString(7, m.getCep());
				stm.setString(8, m.getBairro());
				stm.setString(9, m.getEndereco());
				stm.setString(10, m.getTipo_sanguineo());
				stm.setString(11, m.getTelefone());
				stm.setString(12, m.getClt());
				stm.setString(13, m.getCrm());
				stm.setString(14, m.getEspecialidade());
				stm.setDouble(15, m.getSalario());

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

	public ArrayList<Medico> consultarModel(int id) {

		ArrayList<Medico> lista = new ArrayList<Medico>();

		try {

			if (!conectar()) {
				msg = "Não foi possivel conectar ao banco de dados";
				return lista;
			} else {

				String sql = "SELECT * FROM medico WHERE id = " + Integer.toString(id) + ";";

				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (!resultado.next()) {
					msg = "Cadastro não localizado!";
					return lista;
				} else {

					resultado.beforeFirst();
					while (resultado.next()) {

						Medico m = new Medico();

						m.setNome(resultado.getString(2));
						m.setCpf(resultado.getString(3));
						// acertar data nascimento antes de passar para lista
						m.setData_nascimento(resultado.getString(4));
						m.setNumero_sus(resultado.getString(5));
						m.setUf(resultado.getString(6));
						m.setCidade(resultado.getString(7));
						m.setCep(resultado.getString(8));
						m.setBairro(resultado.getString(9));
						m.setEndereco(resultado.getString(10));
						m.setTipo_sanguineo(resultado.getString(11));
						m.setTelefone(resultado.getString(12));
						m.setClt(resultado.getString(13));
						m.setCrm(resultado.getString(14));
						m.setEspecialidade(resultado.getString(15));
//						lista.add(String.format("Salário : %.2f", resultado.getDouble(16)));
						m.setSalario(resultado.getInt(16));

						lista.add(m);
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

				String sql = "SELECT id FROM medico WHERE nome = \'" + query + "\';";

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

				String sql = "SELECT id FROM medico WHERE cpf = \'" + query + "\';";

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

				String sql = "UPDATE medico SET uf = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

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

				String sql = "UPDATE medico SET cidade = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

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

				String sql = "UPDATE medico SET cep = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

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

				String sql = "UPDATE medico SET bairro = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

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

				String sql = "UPDATE medico SET endereco = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

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

	public String editarTelefone(String novo, int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "UPDATE medico SET telefone = \'" + novo + "\' WHERE id = " + Integer.toString(id) + ";";

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

	public String editarEspecialidade(String novo, int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "UPDATE medico SET especialidade = \'" + novo + "\' WHERE id = " + Integer.toString(id)
						+ ";";

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

	public String editarSalario(String novo, int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "UPDATE medico SET salario = " + novo + " WHERE id = " + Integer.toString(id) + ";";

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

				String sql = "DELETE FROM medico WHERE id = " + Integer.toString(id) + ";";
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

}
