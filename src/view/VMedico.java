package view;

import java.util.ArrayList;
import java.util.Scanner;
import control.Medico;

public class VMedico {

	public String opcao;
	public String msg;
	public int id;

	public VMedico(Scanner read) {

		menu(read);

	}

	public void menu(Scanner read) {

		do {

			System.out.println("1-Cadastrar médico\n" + "2-Consultar dados médico\n" + "3-Editar dados médico\n"
					+ "4-Excluir cadastro médico\n" + "s-Voltar ao menu principal");

			opcao = read.nextLine();

			opcao(read);

		} while (!opcao.equals("s") && !opcao.equals("S"));

	}

	public void opcao(Scanner read) {

		switch (opcao) {

		case "1": {

			cadastrar(read);

			break;
		}
		case "2": {

			consultar(read);

			break;
		}
		case "3": {

			if (consultar(read)) {

				editar(read);

			}

			break;
		}
		case "4": {

			if (consultar(read)) {

				excluir(read);

			}

			break;
		}
		case "s":
		case "S": {
			System.out.println("Retornando ao menu");
			break;
		}
		default: {
			System.out.println("Opção invalida!");
			break;
		}

		}

	}

	public void cadastrar(Scanner read) {

		System.out.println("*Digite nome completo : ");
		String nome = read.nextLine();

		System.out.println("*Cpf : ");
		String cpf = read.nextLine();

		System.out.println("*Data de nascimento  (Formato dd/mm/aaaa) : ");
		String data_nascimento = read.nextLine();

		if (data_nascimento.contains("/")) {
			data_nascimento = data_nascimento.substring(6) + data_nascimento.substring(3, 5)
					+ data_nascimento.substring(0, 2);
		} else {
			data_nascimento = data_nascimento.substring(4) + data_nascimento.substring(2, 4)
					+ data_nascimento.substring(0, 2);
		}

		System.out.println("Número cartão SUS : ");
		String num_sus = read.nextLine();

		System.out.println("*UF : ");
		String uf = read.nextLine();

		System.out.println("*Cidade : ");
		String cidade = read.nextLine();

		System.out.println("CEP : ");
		String cep = read.nextLine();

		System.out.println("Bairro : ");
		String bairro = read.nextLine();

		System.out.println("*Endereço : ");
		String endereco = read.nextLine();

		System.out.println("Tipo sanguineo : ");
		String tipo_sang = read.nextLine();

		System.out.println("Telefone : ");
		String telefone = read.nextLine();

		System.out.println("Carteira de trabalho : ");
		String clt = read.nextLine();

		System.out.println("CRM : ");
		String crm = read.nextLine();

		System.out.println("Especialidade : ");
		String especialidade = read.nextLine();

		System.out.println("Salário : ");
		String cash = read.nextLine();

		double salario = 0;
		if (Double.parseDouble(cash) > 0) {
			salario = Double.parseDouble(cash);
		} else {
			System.out.println("Formato invalido");
		}

		if (checarDados(nome, cpf, data_nascimento, num_sus, uf, cidade, cep, bairro, endereco, tipo_sang, telefone,
				clt, crm, especialidade, salario)) {

			System.out.println(new Medico().cadastrar(nome, cpf, data_nascimento, num_sus, uf, cidade, cep, bairro,
					endereco, tipo_sang, telefone, clt, crm, especialidade, salario));

		} else {

			System.out.println("Formato Invalido de informações!\nPreencha o cadastro corretamente!");

		}

	}

	public boolean consultar(Scanner read) {

		int aux = 0;

		System.out.println("Digite nome/cpf do paciente : ");
		String query = read.nextLine();

		Medico m = new Medico();

		ArrayList<Medico> lista = m.consultar(query);

		id = m.getId();

		System.out.println();

		if (!lista.isEmpty()) {

			System.out.println("Nome : " + lista.get(0).getNome());
			System.out.println("Cpf : " + lista.get(0).getCpf());
			System.out.println("Data nascimento : " + lista.get(0).getData_nascimento());
			System.out.println("Sus : " + lista.get(0).getNumero_sus());
			System.out.println("Uf : " + lista.get(0).getUf());
			System.out.println("Cidade : " + lista.get(0).getCidade());
			System.out.println("Cep : " + lista.get(0).getCep());
			System.out.println("Bairro : " + lista.get(0).getBairro());
			System.out.println("Endereço : " + lista.get(0).getEndereco());
			System.out.println("Tipo sanguineo : " + lista.get(0).getTipo_sanguineo());
			System.out.println("Telefone : " + lista.get(0).getTelefone());
			System.out.println("Carteira de trabalho : " + lista.get(0).getClt());
			System.out.println("Crm : " + lista.get(0).getCrm());
			System.out.println("Especialidade : " + lista.get(0).getEspecialidade());
			System.out.println(String.format("Salario : %.2f", lista.get(0).getSalario()));

			System.out.println();

			if (aux == 1) {
				return false;
			} else {
				return true;
			}

		} else {

			System.out.println("Cadastro não encontrado!\n" + m.getMsg());
			return false;
		}

	}

	public void editar(Scanner read) {

		System.out.println(
				"Qual item gostaria de editar?\n1-UF\n2-Cidade\n3-CEP\n4-Bairro\n5-Endereço\n6-Telefone\n7-Especialidade\n8-Salário");
		String coluna = read.nextLine();

		System.out.println("Digite nova informação : ");
		String novo = read.nextLine();

		if (coluna.equals("1") || coluna.equals("2") || coluna.equals("3") || coluna.equals("4") || coluna.equals("5")
				|| coluna.equals("6") || coluna.equals("7") || coluna.equals("8")) {

			Medico m = new Medico();

			System.out.println(m.editar(coluna, novo, id));

		} else {
			System.out.println("Não foi possível alterar o cadastro!");
		}
	}

	public void excluir(Scanner read) {

		System.out.println("Tem certeza que deseja excluir o cadastro?\ns-Sim\nn-Não");
		String op = read.nextLine();

		if (op.equals("s") || op.equals("S")) {
			System.out.println(new Medico().excluir(id));
		} else {
			System.out.println("Retornando ao menu");
		}

	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public boolean checarDados(String nome, String cpf, String data_nascimento, String num_sus, String uf,
			String cidade, String cep, String bairro, String endereco, String tipo_sang, String telefone, String clt,
			String crm, String especialidade, double salario) {

		if (nome != null && cpf != null && data_nascimento != null && num_sus != null && uf != null && cidade != null
				&& cep != null && bairro != null && endereco != null && tipo_sang != null && telefone != null
				&& clt != null && crm != null && especialidade != null && salario > 0) {
			return true;
		} else {
			return false;
		}

	}

}
