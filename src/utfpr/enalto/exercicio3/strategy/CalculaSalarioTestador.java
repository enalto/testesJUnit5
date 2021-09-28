package utfpr.enalto.exercicio3.strategy;

import java.math.BigDecimal;

import utfpr.enalto.exercicio3.Funcionario;

public class CalculaSalarioTestador implements CalculadoraStrategy {

	@Override
	public BigDecimal calcula(Funcionario funcionario) {
		if(funcionario.getSalario().compareTo(BigDecimal.valueOf(2000))<0) {
			return funcionario.getSalario().multiply(BigDecimal.valueOf(0.85));
		}
		return funcionario.getSalario().multiply(BigDecimal.valueOf(0.75));
	}

}
