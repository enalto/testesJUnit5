package utfpr.enalto.exercicio3.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utfpr.enalto.exercicio3.Funcionario;
import utfpr.enalto.exercicio3.enums.Cargo;
import utfpr.enalto.exercicio3.exception.SalarioFuncionarioInvalidoException;
import utfpr.enalto.exercicio3.strategy.CalculaSalarioDBA;
import utfpr.enalto.exercicio3.strategy.CalculaSalarioDesenvolvedor;
import utfpr.enalto.exercicio3.strategy.CalculaSalarioGerente;
import utfpr.enalto.exercicio3.strategy.CalculaSalarioTestador;
import utfpr.enalto.exercicio3.strategy.CalculadoraSalario;

class CalculoSalarioTest {

	private Funcionario funcionario;
	private CalculadoraSalario calculadoraSalario;

	@BeforeEach
	void setup() {
		funcionario = new Funcionario();
		calculadoraSalario = new CalculadoraSalario(funcionario);
	}

	@Test
	void naoDeveAceitarSalarioIgualAZero() {

		assertThrows(SalarioFuncionarioInvalidoException.class, () -> {
			funcionario.setSalario(new BigDecimal(0));
		});
	}

	@Test
	void naoDeveAceitarSalarioMenorQueZero() {

		assertThrows(SalarioFuncionarioInvalidoException.class, () -> {
			funcionario.setSalario(new BigDecimal(-10000));
		});
	}

	@Test
	void deveDescontar10PorCentoParaSalarioDesenvolvedorMenorQueTresMil() {

		funcionario.setSalario(BigDecimal.valueOf(2999));
		funcionario.setCargo(Cargo.DESENVOLVEDOR);

		calculadoraSalario.setCalculadoraStrategy(new CalculaSalarioDesenvolvedor());
		calculadoraSalario.getCalculadoraStrategy().calcula(funcionario);

		assertThat(calculadoraSalario.getSalarioLiquido(),
				is(equalTo(funcionario.getSalario().multiply(BigDecimal.valueOf(.90)))));
	}

	@Test
	void deveDescontar20PorCentoParaSalarioDesenvolvedorMaiorIgualTresMil() {

		funcionario.setSalario(BigDecimal.valueOf(3000));
		funcionario.setCargo(Cargo.DESENVOLVEDOR);

		calculadoraSalario.setCalculadoraStrategy(new CalculaSalarioDesenvolvedor());
		calculadoraSalario.getCalculadoraStrategy().calcula(funcionario);

		assertThat(calculadoraSalario.getSalarioLiquido(),
				is(equalTo(funcionario.getSalario().multiply(BigDecimal.valueOf(0.80)))));

		funcionario.setSalario(BigDecimal.valueOf(5000));
		assertThat(calculadoraSalario.getSalarioLiquido(), is(equalTo(BigDecimal.valueOf(4000.00))));

	}

	@Test
	void deveDescontar15PorCentoParaSalarioDBAMenorQueDoisMil() {

		funcionario.setSalario(BigDecimal.valueOf(1000));
		funcionario.setCargo(Cargo.DBA);

		calculadoraSalario.setCalculadoraStrategy(new CalculaSalarioDBA());
		calculadoraSalario.getCalculadoraStrategy().calcula(funcionario);

		assertThat(calculadoraSalario.getSalarioLiquido(),
				is(equalTo(funcionario.getSalario().multiply(BigDecimal.valueOf(0.85)))));

		funcionario.setSalario(BigDecimal.valueOf(1000));

		assertTrue(calculadoraSalario.getSalarioLiquido().compareTo(BigDecimal.valueOf(850)) == 0);

	}

	@Test
	void deveDescontar25PorCentoParaSalarioDBAMaiorQueDoisMil() {

		funcionario.setSalario(BigDecimal.valueOf(2800));
		funcionario.setCargo(Cargo.DBA);

		calculadoraSalario.setCalculadoraStrategy(new CalculaSalarioDBA());
		calculadoraSalario.getCalculadoraStrategy().calcula(funcionario);

		assertThat(calculadoraSalario.getSalarioLiquido(),
				is(equalTo(funcionario.getSalario().multiply(BigDecimal.valueOf(0.75)))));

		funcionario.setSalario(BigDecimal.valueOf(2000));

		assertTrue(calculadoraSalario.getSalarioLiquido().compareTo(BigDecimal.valueOf(1500)) == 0);

	}

	@Test
	void deveDescontar15PorCentoParaSalarioTestadorMenorQueDoisMil() {

		funcionario.setSalario(BigDecimal.valueOf(1000));
		funcionario.setCargo(Cargo.TESTADOR);

		calculadoraSalario.setCalculadoraStrategy(new CalculaSalarioTestador());
		calculadoraSalario.getCalculadoraStrategy().calcula(funcionario);

		assertThat(calculadoraSalario.getSalarioLiquido(),
				is(equalTo(funcionario.getSalario().multiply(BigDecimal.valueOf(0.85)))));

		funcionario.setSalario(BigDecimal.valueOf(550));

		assertTrue(calculadoraSalario.getSalarioLiquido().compareTo(BigDecimal.valueOf(467.50)) == 0);

	}

	@Test
	void deveDescontar25PorCentoParaSalarioTestadorMaiorQueDoisMil() {

		funcionario.setSalario(BigDecimal.valueOf(2800));
		funcionario.setCargo(Cargo.DBA);

		calculadoraSalario.setCalculadoraStrategy(new CalculaSalarioTestador());
		calculadoraSalario.getCalculadoraStrategy().calcula(funcionario);

		assertThat(calculadoraSalario.getSalarioLiquido(),
				is(equalTo(funcionario.getSalario().multiply(BigDecimal.valueOf(0.75)))));

		funcionario.setSalario(BigDecimal.valueOf(2000));

		assertTrue(calculadoraSalario.getSalarioLiquido().compareTo(BigDecimal.valueOf(1500)) == 0);

	}

	@Test
	void deveDescontar20PorCentoParaSalarioGerenteMenorQueCincoMil() {

		funcionario.setSalario(BigDecimal.valueOf(4999));
		funcionario.setCargo(Cargo.GERENTE);

		calculadoraSalario.setCalculadoraStrategy(new CalculaSalarioGerente());
		calculadoraSalario.getCalculadoraStrategy().calcula(funcionario);

		assertThat(calculadoraSalario.getSalarioLiquido(),
				is(equalTo(funcionario.getSalario().multiply(BigDecimal.valueOf(0.80)))));

		funcionario.setSalario(BigDecimal.valueOf(2500));

		assertTrue(calculadoraSalario.getSalarioLiquido().compareTo(BigDecimal.valueOf(2000)) == 0);

	}

	@Test
	void deveDescontar30PorCentoParaSalarioGerenteMaiorOuIgualQueCincoMil() {

		funcionario.setSalario(BigDecimal.valueOf(5000));
		funcionario.setCargo(Cargo.GERENTE);

		calculadoraSalario.setCalculadoraStrategy(new CalculaSalarioGerente());
		calculadoraSalario.getCalculadoraStrategy().calcula(funcionario);

		assertThat(calculadoraSalario.getSalarioLiquido(),
				is(equalTo(funcionario.getSalario().multiply(BigDecimal.valueOf(0.70)))));

		funcionario.setSalario(BigDecimal.valueOf(7850));

		assertTrue(calculadoraSalario.getSalarioLiquido().compareTo(BigDecimal.valueOf(5495)) == 0);

	}

}
