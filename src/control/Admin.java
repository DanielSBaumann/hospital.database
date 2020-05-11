package control;

import java.util.ArrayList;
import model.Conexao;
import model.MAdmin;

public class Admin {

	public boolean rotina() {
		
		return new Conexao().rotina();
		
	}
	
	public ArrayList<String> showTables() {

		return new MAdmin().showTablesModel();

	}

	public String criarTabela(String table) {

		if (!new Validar().validarTabela(showTables(), table)) {

			return new MAdmin().criarTabela(table);

		} else {
			return "Nome de tabela digitada ja existente!";
		}
	}

	public String deletartabela(String table) {

		if (new Validar().validarTabela(showTables(), table)) {

			return new MAdmin().deletarTabela(table);

		} else {
			return "Nome de tabela digitada inexistente!";
		}
	}

	public String deletarDatabase() {

		return new MAdmin().deletarDatabase();

	}

}
