package utfpr.enalto.exercicio3.strategy;

import java.math.BigDecimal;

import utfpr.enalto.exercicio3.Funcionario;

public class CalculaSalarioGerente implements CalculadoraStrategy {

	@Override
	public BigDecimal calcula(Funcionario funcionario) {

		if (funcionario.getSalario().compareTo(BigDecimal.valueOf(5000)) < 0) {
			return funcionario.getSalario().multiply(BigDecimal.valueOf(0.80));
		}
		return funcionario.getSalario().multiply(BigDecimal.valueOf(0.70));
	}

}
