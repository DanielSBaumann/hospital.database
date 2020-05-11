package control;

import java.util.ArrayList;
import model.MMedico;

public class Medico extends Pessoa {

	private String telefone;
	private String clt;
	private String crm;
	private String especialidade;
	private double salario;

	public String cadastrar(String nome, String cpf, String data_nascimento, String num_sus, String uf, String cidade,
			String cep, String bairro, String endereco, String tipo_sang, String telefone, String clt, String crm,
			String especialidade, double salario) {

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
		setTelefone(telefone);
		setClt(clt);
		setCrm(crm);
		setEspecialidade(especialidade);
		setSalario(salario);

//		if (validarMedico(this)) {

			MMedico mp = new MMedico();

			if (mp.cadastrarModel(this)) {
				return "Cadastro efetuado com sucesso";
			} else {
				return "Não foi possível efetuar o cadastro!\n" + mp.msg;
			}

//		} else {
//			return "Formato invalido nos seguintes itens : \n" + msg;
//		}
	}

	public ArrayList<Medico> consultar(String query) {

		MMedico m = new MMedico();

		setId(m.consultarNome(query));

		return m.consultarModel(getId());

	}

	public String editar(String coluna, String novo, int id) {

		String linha = null;

		switch (coluna) {

		case "1": {
			linha = new MMedico().editarUf(novo, id);
			break;
		}
		case "2": {
			linha = new MMedico().editarCidade(novo, id);
			break;
		}
		case "3": {
			linha = new MMedico().editarCep(novo, id);
			break;
		}
		case "4": {
			linha = new MMedico().editarBairro(novo, id);
			break;
		}
		case "5": {
			linha = new MMedico().editarEndereco(novo, id);
			break;
		}
		case "6": {
			linha = new MMedico().editarTelefone(novo, id);
			break;
		}
		case "7": {
			linha = new MMedico().editarEspecialidade(novo, id);
			break;
		}
		case "8": {

			if (Double.parseDouble(novo) > 0) {
				linha = new MMedico().editarSalario(novo, id);
			} else {
				linha = "Formato invalido!";
			}

			break;
		}

		}
		return linha;
	}

	public String excluir(int id) {

		return new MMedico().excluirModel(id);

	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getClt() {
		return clt;
	}

	public void setClt(String clt) {
		this.clt = clt;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

}
