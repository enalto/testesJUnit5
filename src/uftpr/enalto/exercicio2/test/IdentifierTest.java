package uftpr.enalto.exercicio2.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uftpr.enalto.exercicio2.exception.IdenfierComTamanhoInvalidoException;
import uftpr.enalto.exercicio2.exception.IdentifierDeveComecarComLetraException;
import uftpr.enalto.exercicio2.exception.IdentifierNuloException;
import uftpr.enalto.exercicio2.exception.IdentifierSoPodeConterLetrasENumerosException;
import uftpr.enalto.exercicio2.identifier.Identifier;

class IdentifierTest {

	private Identifier identifier;

	@BeforeEach
	public void setup() {
		identifier = new Identifier();
	}

	@Test
	void tamanhoDoIdentificadorDeveSerMenorQue7() {

		assertThrows(IdenfierComTamanhoInvalidoException.class, () -> {
			identifier.setIdentifier("abc454545defg");
		});

		assertThrows(IdenfierComTamanhoInvalidoException.class, () -> {
			identifier.setIdentifier("A1b2C3d");
		});

	}

	@Test
	void identifierNaoPodeSerNulo() {
		assertThrows(IdentifierNuloException.class, () -> {
			identifier.setIdentifier("");
		});
	}

	@Test
	void identifierComTamanho6EValido() {
		identifier.setIdentifier("abcdef");
		assertThat("esperado que o identificador tenha tamanho 6", identifier.getIdentifier().length(), is(equalTo(6)));
	}

	@Test
	void identifierComTamanho1EValido() {
		identifier.setIdentifier("a");
		assertThat(identifier.getIdentifier().length(), is(equalTo(1)));
	}

	@Test
	void identifierDeveComecarComLetra() {
		assertThrows(IdentifierDeveComecarComLetraException.class, () -> {
			identifier.setIdentifier("1abcd");
		});

		assertThrows(IdentifierDeveComecarComLetraException.class, () -> {
			identifier.setIdentifier("_1abcd");
		});

		assertThrows(IdentifierDeveComecarComLetraException.class, () -> {
			identifier.setIdentifier("*1abcd");
		});

		assertThrows(IdentifierDeveComecarComLetraException.class, () -> {
			identifier.setIdentifier("2B3");
		});

	}

	@Test
	void identifierIniciaComLetra() {
		identifier.setIdentifier("a12345");
		assertThat(identifier.getIdentifier(), is(equalTo("a12345")));

		identifier.setIdentifier("AABCC9");
		assertThat(identifier.getIdentifier(), is(equalTo("AABCC9")));
	}

	@Test
	void identifierSoPodeConterLetrasENumeros() {
		assertThrows(IdentifierSoPodeConterLetrasENumerosException.class, () -> {
			identifier.setIdentifier("a1a*bc");
		});

		assertThrows(IdentifierSoPodeConterLetrasENumerosException.class, () -> {
			identifier.setIdentifier("Z-12");
		});

		assertThat(new Identifier("abc12").getIdentifier(), is(equalTo("abc12")));

		assertThat(new Identifier("a1").getIdentifier(), is(equalTo("a1")));

	}

}
