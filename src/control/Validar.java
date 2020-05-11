package control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Validar {

	public String msg = "";

	public boolean validarPaciente(Paciente p) {

		boolean nome = validarNome(p.getNome());
		boolean cpf = validarCPF(p.getCpf());
		boolean data = validarData(p.getData_nascimento());
		boolean sus = validarSUS(p.getNumero_sus());
		boolean uf = validarUF(p.getUf());
		boolean cidade = validarCidade(p.getCidade());
		boolean cep = validarCEP(p.getCep());
		boolean bairro = validarBairro(p.getBairro());
		boolean endereco = validarEndereco(p.getEndereco());
		boolean sangue = validarTipoSanquineo(p.getTipo_sanguineo());

		if (nome && cpf && data && sus && uf && cidade && cep && bairro && endereco && sangue) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validarMedico(Medico m) {

		boolean nome = validarNome(m.getNome());
		boolean cpf = validarCPF(m.getCpf());
		boolean data = validarData(m.getData_nascimento());
		boolean sus = validarSUS(m.getNumero_sus());
		boolean uf = validarUF(m.getUf());
		boolean cidade = validarCidade(m.getCidade());
		boolean cep = validarCEP(m.getCep());
		boolean bairro = validarBairro(m.getBairro());
		boolean endereco = validarEndereco(m.getEndereco());
		boolean sangue = validarTipoSanquineo(m.getTipo_sanguineo());
		boolean telefone = validarTelefone(m.getTelefone());
		boolean clt = validarCLT(m.getClt());
		boolean crm = validarCRM(m.getCrm());
		boolean especialidade = validarEspecialidade(m.getEspecialidade());
		boolean salario = validarSalario(m.getSalario());

		if (nome && cpf && data && sus && uf && cidade && cep && bairro && endereco && sangue && telefone && clt && crm
				&& especialidade && salario) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validarConsulta(Consulta c) {

		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		boolean dataConsulta = dataConsulta(data.format(c.getData()));

		SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
		boolean horaConsulta = horaConsulta(hora.format(c.getData()));

		if (dataConsulta && horaConsulta) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validarNome(String nome) {
		char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j',
				'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
				'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'ç', 'Ç', ' ' };
		int auxLetras = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < nome.length(); j++) {
				if (letras[i] == nome.charAt(j)) {
					auxLetras++;
				}
			}
		}
		if (auxLetras != nome.length() || nome.isEmpty() || !nome.contains(" ")) {
			msg += "Nome\n";
			return false;
		} else {
			return true;
		}
	}

	public String limparEspaços(String nome) {

		while (nome.contains("  ")) {
			nome = nome.replace("  ", " ");
		}
		nome = nome.trim();
		return nome;
	}

	public boolean validarCPF(String cpf) {

		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < cpf.length(); j++) {
				if (numero[i] == cpf.charAt(j)) {
					auxNumero++;
				}
			}
		}
		if (auxNumero != cpf.length() || cpf.isEmpty() || cpf.length() != 11) {
			msg += "cpf\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarSUS(String sus) {

		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < sus.length(); j++) {
				if (numero[i] == sus.charAt(j)) {
					auxNumero++;
				}
			}
		}
		if (auxNumero != sus.length() || sus.isEmpty()) {
			msg += "Numero sus\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarData(String data) {

		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < data.length(); j++) {
				if (numero[i] == data.charAt(j)) {
					auxNumero++;
				}
			}
		}
		if (auxNumero != data.length() || data.isEmpty() || data.length() < 8) {
			msg += "Data de nascimento\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarCEP(String cep) {

		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < cep.length(); j++) {
				if (numero[i] == cep.charAt(j)) {
					auxNumero++;
				}
			}
		}
		if (auxNumero != cep.length() || cep.isEmpty()) {
			msg += "CEP\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarUF(String nome) {

		char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j',
				'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
				'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'ç', 'Ç', ' ' };
		int auxLetras = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < nome.length(); j++) {
				if (letras[i] == nome.charAt(j)) {
					auxLetras++;
				}
			}
		}
		if (auxLetras != nome.length() || nome.isEmpty() || nome.length() != 2) {
			msg += "UF\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarBairro(String nome) {

		char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j',
				'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
				'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'ç', 'Ç', ' ' };
		int auxLetras = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < nome.length(); j++) {
				if (letras[i] == nome.charAt(j)) {
					auxLetras++;
				}
			}
		}
		if (auxLetras != nome.length() || nome.isEmpty()) {
			msg += "Bairro\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarCidade(String nome) {

		char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j',
				'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
				'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'ç', 'Ç', ' ' };
		int auxLetras = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < nome.length(); j++) {
				if (letras[i] == nome.charAt(j)) {
					auxLetras++;
				}
			}
		}
		if (auxLetras != nome.length() || nome.isEmpty()) {
			msg += "Cidade\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarEndereco(String nome) {

		char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j',
				'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
				'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'ç', 'Ç', ' ' };
		int auxLetras = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < nome.length(); j++) {
				if (letras[i] == nome.charAt(j)) {
					auxLetras++;
				}
			}
		}
		if (auxLetras != nome.length() || nome.isEmpty()) {
			msg += "Endereço\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarTipoSanquineo(String tipo_sanguineo) {

		char numero[] = { 'a', 'b', 'o', '+', '-' };
		int aux = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < tipo_sanguineo.length(); j++) {
				if (numero[i] == tipo_sanguineo.charAt(j)) {
					aux++;
				}
			}
		}
		if (aux != tipo_sanguineo.length() || tipo_sanguineo.isEmpty() || tipo_sanguineo.length() > 3) {
			msg += "Tipo sanguineo\n";
			return false;
		} else {
			return true;
		}
	}

	public boolean validarTelefone(String telefone) {

		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < telefone.length(); j++) {
				if (numero[i] == telefone.charAt(j)) {
					auxNumero++;
				}
			}
		}
		if (auxNumero != telefone.length() || telefone.isEmpty() || telefone.length() > 11) {
			msg += "Telefone\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarCLT(String clt) {

		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < clt.length(); j++) {
				if (numero[i] == clt.charAt(j)) {
					auxNumero++;
				}
			}
		}
		if (auxNumero != clt.length() || clt.isEmpty() || clt.length() != 11) {
			msg += "clt\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarCRM(String crm) {

		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < crm.length(); j++) {
				if (numero[i] == crm.charAt(j)) {
					auxNumero++;
				}
			}
		}
		if (auxNumero != crm.length() || crm.isEmpty() || crm.length() != 11) {
			msg += "crm\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarEspecialidade(String especialidade) {

		char letras[] = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j',
				'J', 'k', 'K', 'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T',
				'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z', 'Z', 'ç', 'Ç', ' ' };
		int auxLetras = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < especialidade.length(); j++) {
				if (letras[i] == especialidade.charAt(j)) {
					auxLetras++;
				}
			}
		}
		if (auxLetras != especialidade.length() || especialidade.isEmpty()) {
			msg += "Especialidade\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean validarSalario(double salario) {

		if (salario > 0) {
			return true;
		} else {
			msg+="salario";
			return false;
		}

	}

	public boolean validarTabela(ArrayList<String> lista, String table) {

		int aux = 0;

		for (String i : lista) {

			if (i.equals(table)) {
				aux++;
			}

		}

		if (aux > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean dataConsulta(String data) {

		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < data.length(); j++) {
				if (numero[i] == data.charAt(j)) {
					auxNumero++;
				}
			}
		}
		char letras[] = { '/', '-' };
		int auxChar = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < data.length(); j++) {
				if (letras[i] == data.charAt(j)) {
					auxChar++;
				}
			}
		}
		if (auxNumero + auxChar != data.length() || data.isEmpty() || data.length() != 10 || auxChar != 2) {
			msg += "data consulta\n";
			return false;
		} else {
			return true;
		}

	}

	public boolean horaConsulta(String hora) {

		char numero[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int auxNumero = 0;
		for (int i = 0; i < numero.length; i++) {
			for (int j = 0; j < hora.length(); j++) {
				if (numero[i] == hora.charAt(j)) {
					auxNumero++;
				}
			}
		}
		char letras[] = { ':' };
		int auxChar = 0;
		for (int i = 0; i < letras.length; i++) {
			for (int j = 0; j < hora.length(); j++) {
				if (letras[i] == hora.charAt(j)) {
					auxChar++;
				}
			}
		}
		if (auxNumero + auxChar != hora.length() || hora.isEmpty() || hora.length() != 5 || auxChar != 1) {
			msg += "hora consulta\n";
			return false;
		} else {
			return true;
		}

	}

}
