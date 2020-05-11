package control;

import java.util.ArrayList;
import java.util.Date;
import model.MConsulta;

public class Consulta extends Pessoa {

	private int idPaciente;
	private int idMedico;
	private Date data;
	private String especialidade;
	private String endereco;
	private String nomePaciente;
	private String nomeMedico;

	public ArrayList<Medico> medicoPorEspecialidade(String especialidade) {

		return new MConsulta().medicosPorEspecialidade(especialidade);

	}

	public int idPaciente(String cpf) {

		return new MConsulta().idPaciente(cpf);

	}

	public int idMedico(String crm) {

		return new MConsulta().idMedico(crm);

	}

	public String agendarConsulta(String especialidade, int idPaciente, int idMedico, Date data) {

		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		this.especialidade = especialidade;
		this.data = data;

		if (validarConsulta(this)) {

			MConsulta mc = new MConsulta();

			if (mc.agendarConsultaModel(this)) {
				return "Consulta agendada com sucesso!";
			} else {
				return "Não foi possível efetuar o cadastro!\n" + mc.msg;
			}

		} else {
			return "Formato invalido nos seguintes itens : \n" + msg;
		}
	}

	public ArrayList<Consulta> consultasAgendadas(String cpf) {

		setIdPaciente(new MConsulta().idPaciente(cpf));

		return new MConsulta().consultasAgendadasModel(getIdPaciente());

	}

	public String editarConsulta(String coluna, String novo, int id) {

		String linha = null;

		switch (coluna) {

		case "1": {
			if (dataConsulta(novo)) {
				linha = new MConsulta().editarData(novo, id);
			} else {
				linha = "Formato de data digitado invalido";
			}
			break;
		}
		case "2": {
			if (horaConsulta(novo)) {
				linha = new MConsulta().editarHora(novo, id);
			} else {
				linha = "Formato de hora digitado invalido";
			}
			break;
		}

		}
		return linha;
	}

	public String excluirConsulta(int id) {

		return new MConsulta().excluirConsultaModel(id);

	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

}
