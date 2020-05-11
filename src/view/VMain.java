package view;

import java.util.Scanner;
import control.Admin;

public class VMain {

	public static void main(String[] args) {

		if (new Admin().rotina()) {

			Scanner read = new Scanner(System.in);
			String opcao;

			do {
				
				menu();
				opcao = read.nextLine();

				switch (opcao) {

				case "1": {

					new VPaciente(read);

					break;
				}
				case "2": {

					new VMedico(read);

					break;
				}
				case "3": {

					new VConsulta(read);

					break;
				}
				case "admin": {

					new VAdmin(read);

					break;
				}
				case "s":
				case "S": {
					System.out.println("Encerrando o programa");
					break;
				}
				default: {
					System.out.println("Opção invalida!");
					break;
				}

				}

			} while (!opcao.equals("s"));

			read.close();

		} else {

			System.out.println("Não foi possível iniciar o programa!\nVerifique conexão com banco de dados");

		}
	}

	public static void menu() {
		System.out.println(
				"1-Acessar menu pacientes\n" + "2-Acessar menu médico\n" + "3-Consultas\n" + "s-Sair do programa");
	}
}
