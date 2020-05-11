package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import control.Consulta;
import control.Medico;

public class VConsulta {

	public String opcao;

	public VConsulta(Scanner read) {

		menu(read);

	}

	public void menu(Scanner read) {

		do {

			System.out.println("1-Agendar nova consulta\n" + "2-Localizar consulta\n" + "3-Editar consulta\n"
					+ "4-Excluir consulta\n" + "s-Voltar ao menu principal");

			opcao = read.nextLine();

			opcao(read);

		} while (!opcao.equals("s") && !opcao.equals("S"));

	}

	public void opcao(Scanner read) {

		switch (opcao) {

		case "1": {

			agendar(read);

			break;
		}
		case "2": {

			consultasAgendadas(read);

			break;
		}
		case "3": {

			if (consultasAgendadas(read)) {

				editarConsulta(read);

			}

			break;
		}
		case "4": {

			if (consultasAgendadas(read)) {

				excluirConsulta(read);

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

	public boolean agendar(Scanner read) {

		System.out.println(
				"Escolha especialidade médica : \n1-Pediatria\n2-Ortopedia\n3-Ginecologia\n4-Oftamologia\n5-Geriatria");
		String op = read.nextLine();

		String especialidade = especialidade(op);

		if (especialidade != null) {

			ArrayList<Medico> lista = new Consulta().medicoPorEspecialidade(especialidade);

			if (!lista.isEmpty()) {

				System.out.println("ESPECIALIDADE : " + especialidade.toUpperCase() + "\n");
				System.out.printf("%-20.16s%-10.6s", "Nome", "Crm");
				System.out.println();

				for (int i = 0; i < lista.size(); i++) {
					System.out.println(String.format("%-20.16s%-10.6s", lista.get(i).getNome(), lista.get(i).getCrm()));
				}
				System.out.println();

				System.out.println("Digite crm do médico : ");
				String crm = read.nextLine();

				System.out.println("Digite cpf do paciente : ");
				String cpf = read.nextLine();

				int idMedico = new Consulta().idMedico(crm);
				int idPaciente = new Consulta().idPaciente(cpf);

				if (idMedico > 0 && idPaciente > 0) {

					agendar(read, especialidade, idPaciente, idMedico);

					return true;

				} else {

					System.out.println(
							"Cadastro não encontrado!\nCertifique-se que o paciente possui cadastro , e número correto do crm!");

					return false;
				}

			} else {
				System.out.println("Não existem medicos cadastrados na especialidade buscada!");
				return false;
			}

		} else {
			System.out.println("Opção invalida!");
			return false;
		}
	}

	public void agendar(Scanner read, String especialidade, int idPaciente, int idMedico) {

		System.out.println("Digite data da consulta : \nFormato dd/mm/aaaa");
		String dt = read.nextLine();

		System.out.println("Digite horário  : \nFormato hh:mm");
		String hora = read.nextLine();

		if (!dt.isEmpty() && !hora.isEmpty()) {

			dt = dt.substring(3, 5) + "/" + dt.substring(0, 2) + "/" + dt.substring(6);

			Date data = new Date(dt + " " + hora);

			System.out.println(new Consulta().agendarConsulta(especialidade, idPaciente, idMedico, data));

		} else {
			System.out.println("Entrada invalida!");
		}
	}

	public String especialidade(String op) {

		switch (op) {
		case "1": {
			return "pediatria";
		}
		case "2": {
			return "ortopedia";
		}
		case "3": {
			return "ginecologia";
		}
		case "4": {
			return "oftamologia";
		}
		case "5": {
			return "geriatria";
		}
		default: {
			return "";
		}
		}

	}

	public boolean consultasAgendadas(Scanner read) {

		System.out.println("Digite cpf do paciente : ");
		String cpf = read.nextLine();

		Consulta c = new Consulta();

		ArrayList<Consulta> lista = c.consultasAgendadas(cpf);

		if (!lista.isEmpty()) {

			System.out.printf("%-3.2s %-20.16s %-20.16s %-20.16s %-20.16s %-12.10s %-6.5s", "Id", "Nome paciente",
					"Nome médico", "Especialidade", "Endereço", "Data", "Hora");
			System.out.println();

			for (int i = 0; i < lista.size(); i++) {

				System.out.printf("%-3.2s ", lista.get(i).getId());
				System.out.printf("%-20.16s ", lista.get(i).getNomePaciente());
				System.out.printf("%-20.16s ", lista.get(i).getNomeMedico());
				System.out.printf("%-20.16s ", lista.get(i).getEspecialidade());
				System.out.printf("%-20.16s ", lista.get(i).getEndereco());

				SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
				System.out.printf("%-12.10s ", data.format(lista.get(i).getData()));

				SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
				System.out.printf("%-6.5s \n", hora.format(lista.get(i).getData()));

			}
			System.out.println();

			return true;
		} else {
			System.out.println("Não existem consultas agendadas para este cpf\n" + c.getMsg());
			return false;
		}

	}

	public void editarConsulta(Scanner read) {

		System.out.println("Digite o id da consulta que deseja alterar : ");
		int id = Integer.parseInt(read.nextLine());

		System.out.println("Qual item gostaria de editar?\n1-Data\n2-Hora\n");
		String coluna = read.nextLine();

		System.out.println("Digite nova informação : \ndd/mm/aaa\nhh:mm");
		String novo = read.nextLine();

		if (coluna.equals("1") || coluna.equals("2")) {

			Consulta c = new Consulta();

			System.out.println(c.editarConsulta(coluna, novo, id));

		} else {
			System.out.println("Não foi possível alterar o cadastro!");
		}
	}

	public void excluirConsulta(Scanner read) {

		System.out.println("Digite o id da consulta que deseja excluir : ");
		int id = Integer.parseInt(read.nextLine());

		System.out.println(new Consulta().excluirConsulta(id));

	}

}
