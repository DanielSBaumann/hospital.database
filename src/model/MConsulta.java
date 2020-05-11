package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import control.Consulta;
import control.Medico;

public class MConsulta extends Conexao {

	public ArrayList<Medico> medicosPorEspecialidade(String especialidade) {

		ArrayList<Medico> lista = new ArrayList<Medico>();
		try {

			if (!conectar()) {
				msg = "Não foi possivel conectar ao banco de dados";
				return lista;
			} else {

				String sql = "SELECT nome,crm FROM medico WHERE especialidade = \'" + especialidade + "\' ;";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (!resultado.next()) {
					msg = "Cadastro não localizado!";
					return lista;
				} else {

					resultado.beforeFirst();
					while (resultado.next()) {

						Medico m = new Medico();

						m.setNome(resultado.getString(1));
						m.setCrm(resultado.getString(2));

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

	public int idMedico(String crm) {

		try {

			if (!conectar()) {
				msg = "Não foi possível conectar ao banco de dados : \n";
				return 0;
			} else {

				String sql = "SELECT id FROM medico WHERE crm = \'" + crm + "\';";

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

	public int idPaciente(String cpf) {

		try {

			if (!conectar()) {
				msg = "Não foi possível conectar ao banco de dados : \n";
				return 0;
			} else {

				String sql = "SELECT id FROM paciente WHERE cpf = \'" + cpf + "\';";

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

	public boolean agendarConsultaModel(Consulta c) {

		try {

			if (!conectar()) {
				msg = "Não foi possível conectar ao banco de dados : \n";
				return false;
			} else {

				String sql = "INSERT INTO consulta(idPaciente,idMedico,especialidade,data,hora) VALUES(?,?,?,?,?)";
				PreparedStatement stm = conn.prepareStatement(sql);

				stm.setInt(1, c.getIdPaciente());
				stm.setInt(2, c.getIdMedico());
				stm.setString(3, c.getEspecialidade());

				SimpleDateFormat data = new SimpleDateFormat("yyyy/MM/dd");
				stm.setString(4, data.format(c.getData()));

				SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
				stm.setString(5, hora.format(c.getData()));

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

	public ArrayList<Consulta> consultasAgendadasModel(int i) {

		ArrayList<Consulta> lista = new ArrayList<Consulta>();
		try {

			if (!conectar()) {
				msg = "Não foi possivel conectar ao banco de dados";
				return lista;
			} else {

				String sql = "SELECT * FROM consulta WHERE idPaciente = " + i + ";";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (!resultado.next()) {
					msg = "Cadastro não localizado!";
					return lista;
				} else {

					resultado.beforeFirst();

					while (resultado.next()) {

						Consulta c = new Consulta();

						c.setId(resultado.getInt(1));
						c.setNomePaciente(nomePaciente(resultado.getInt(2)));
						c.setNomeMedico(nomeMedico(resultado.getInt(3)));
						c.setEspecialidade(resultado.getString(4));
						c.setEndereco(resultado.getString(5));

						String dt = resultado.getString(6);
						dt = dt.substring(5, 7) + "/" + dt.substring(8) + "/" + dt.substring(0, 4);

						String hr = resultado.getString(7);

						Date data = new Date(dt + " " + hr);

						c.setData(data);

						lista.add(c);
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

	public String nomePaciente(int id) {
		try {

			if (!conectar()) {
				return "Não foi possivel conectar ao banco de dados";
			} else {

				String sql = "SELECT nome FROM paciente WHERE id = " + id + ";";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (!resultado.next()) {
					return "Cadastro não localizado!";
				} else {

					resultado.beforeFirst();

					String linha = null;
					while (resultado.next()) {
						linha = resultado.getString(1);
					}
					return linha;
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

	public String nomeMedico(int id) {
		try {

			if (!conectar()) {
				return "Não foi possivel conectar ao banco de dados";
			} else {

				String sql = "SELECT nome FROM medico WHERE id = " + id + ";";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet resultado = stmt.executeQuery();

				if (!resultado.next()) {
					return "Cadastro não localizado!";
				} else {

					resultado.beforeFirst();

					String linha = null;
					while (resultado.next()) {
						linha = resultado.getString(1);
					}
					return linha;
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

	public String editarData(String novo, int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				novo = novo.substring(3, 5) + "/" + novo.substring(0, 2) + "/" + novo.substring(6);

				SimpleDateFormat data = new SimpleDateFormat("yyyy/MM/dd");
				Date dt = new Date(novo);

				String sql = "UPDATE consulta SET data = \'" + data.format(dt) + "\' WHERE id = " + Integer.toString(id)
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

	public String editarHora(String novo, int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "UPDATE consulta SET hora = \'" + novo + "\' WHERE id = "
						+ Integer.toString(id) + ";";

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

	public String excluirConsultaModel(int id) {

		try {

			if (!conectar()) {
				return "Não foi possível conectar ao banco de dados";
			} else {

				String sql = "DELETE FROM consulta WHERE id = " + Integer.toString(id) + ";";
				PreparedStatement stm = conn.prepareStatement(sql);

				stm.execute();

				return "Consulta excluida !";
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
