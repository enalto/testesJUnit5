package uftpr.enalto.exercicio2.identifier;

import uftpr.enalto.exercicio2.exception.IdenfierComTamanhoInvalidoException;
import uftpr.enalto.exercicio2.exception.IdentifierDeveComecarComLetraException;
import uftpr.enalto.exercicio2.exception.IdentifierNuloException;
import uftpr.enalto.exercicio2.exception.IdentifierSoPodeConterLetrasENumerosException;

public class Identifier {
	private String s;
	private String pattern = "[a-zA-Z0-9]+";

	public Identifier() {
	}

	public Identifier(String s) {
		setIdentifier(s);
	}

	public void setIdentifier(String s) {
		validarIdentifier(s);
		this.s = s;
	}

	private void validarIdentifier(String s) {
		if (s == null || s.isEmpty()) {
			throw new IdentifierNuloException("Identificador não pode ser nulo");
		}

		if (s.length() < 1 || s.length() > 6) {
			throw new IdenfierComTamanhoInvalidoException(
					"Tamanho do identificador não pode ser menor que 1 ou maior que 6");
		}

		char ch = s.charAt(0);
		if (!Character.isAlphabetic(ch)) {
			throw new IdentifierDeveComecarComLetraException("Identificador deve começar com letra");
		}

		if (!s.matches(pattern)) {
			throw new IdentifierSoPodeConterLetrasENumerosException("Identificador só pode contem letras ou digitos");
		}
	}

	public String getIdentifier() {
		return s;
	}
}
