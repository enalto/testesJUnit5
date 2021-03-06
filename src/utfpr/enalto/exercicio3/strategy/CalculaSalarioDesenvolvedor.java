package utfpr.enalto.exercicio3.strategy;

import java.math.BigDecimal;

import utfpr.enalto.exercicio3.Funcionario;

public class CalculaSalarioDesenvolvedor implements CalculadoraStrategy{

	@Override
	public BigDecimal calcula(Funcionario funcionario) {
		if(funcionario.getSalario().compareTo(BigDecimal.valueOf(3000))<0) {
			return funcionario.getSalario().multiply(BigDecimal.valueOf(0.90));
		}
		return funcionario.getSalario().multiply(BigDecimal.valueOf(0.80));
	}

}
