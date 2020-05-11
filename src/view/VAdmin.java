package view;

import java.util.ArrayList;
import java.util.Scanner;
import control.Admin;

public class VAdmin {

	private String opcao = null;

	public VAdmin(Scanner read) {

		System.out.println("Modo Admin");

		menu(read);

	}

	public void menu(Scanner read) {

		do {

			System.out.println("1-Consultar tabelas\n" + "2-Adcionar nova tabela\n" + "3-Deletar tabela\n"
					+ "4-Excluir banco de dados\n" + "s-Voltar ao menu principal");

			opcao = read.nextLine();

			opcao(read);

		} while (!opcao.equals("s") && !opcao.equals("S"));

	}

	public void opcao(Scanner read) {

		switch (opcao) {

		case "1": {

			showTables();

			break;
		}
		case "2": {

			System.out.println("Qual nome da nova tabela ?");
			String table = read.nextLine();

			Admin a = new Admin();

			System.out.println(a.criarTabela(table));

			break;
		}
		case "3": {

			if (showTables()) {

				System.out.println("Qual tabela deseja excluir?");
				String table = read.nextLine();

				System.out.println(new Admin().deletartabela(table));

			} else {
				System.out.println("Não existem tabelas cadastradas!");
			}

			break;
		}
		case "4": {

			System.out.println("Tem certeza que deseja excluir permanentemente o banco de dados?\ns-Sim\nn-Não");
			String op = read.nextLine();

			if (op.equals("s") || op.equals("S")) {

				System.out.println(new Admin().deletarDatabase());

				opcao = "s";

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

	public boolean showTables() {

		ArrayList<String> lista = new Admin().showTables();

		int aux = 0;
		if (!lista.isEmpty()) {

			System.out.println("Tabelas : ");
			for (String i : lista) {

				System.out.println(i);
				aux++;
			}
			System.out.println();

			if (aux > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println("Não existem tabelas cadastradas!");
			return false;
		}
	}

}
