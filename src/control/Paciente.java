package control;

import java.util.ArrayList;
import model.MPaciente;

public class Paciente extends Pessoa {

	public String cadastrar(String nome, String cpf, String data_nascimento, String num_sus, String uf, String cidade,
			String cep, String bairro, String endereco, String tipo_sang) {

		setNome(nome);
		setCpf(cpf);
		setData_nascimento(data_nascimento);
		setNumero_sus(num_sus);
		setUf(uf);
		setCidade(cidade);
		setCep(cep);
		setBairro(bairro);
		setEndereco(endereco);
		setTipo_sanguineo(tipo_sang);

		if (validarPaciente(this)) {

			MPaciente mp = new MPaciente();

			if (mp.cadastrarModel(this)) {
				return "Cadastro efetuado com sucesso";
			} else {
				return "Não foi possível efetuar o cadastro!\n" + mp.msg;
			}

		} else {
			return "Formato invalido nos seguintes itens : \n" + msg;
		}

	}

	public ArrayList<Paciente> consultar(String query) {

		MPaciente mp = new MPaciente();

		setId(mp.consultarNome(query));

		return mp.consultarModel(getId());

	}

	public String editar(String coluna, String novo, int id) {

		String linha = null;

		switch (coluna) {

		case "1": {
			linha = new MPaciente().editarUf(novo, id);
			break;
		}
		case "2": {
			linha = new MPaciente().editarCidade(novo, id);
			break;
		}
		case "3": {
			linha = new MPaciente().editarCep(novo, id);
			break;
		}
		case "4": {
			linha = new MPaciente().editarBairro(novo, id);
			break;
		}
		case "5": {
			linha = new MPaciente().editarEndereco(novo, id);
			break;
		}

		}
		return linha;
	}

	public String excluir(int id) {

		return new MPaciente().excluirModel(id);

	}

	public String contarPacientes() {

		return new MPaciente().contarPacientesModel();

	}

	public ArrayList<String> pacientesPorBairro() {

		return new MPaciente().pacientesPorBairroModel();

	}

	public ArrayList<String> listarPacientes() {

		return new MPaciente().listarPacientesModel();

	}

}
