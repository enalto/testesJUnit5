package br.edu.utfpr.enalto.testedesoftware.exercicio3.strategy;

import java.math.BigDecimal;

import br.edu.utfpr.enalto.testedesoftware.exercicio3.Funcionario;

public class CalculadoraSalario {

	private Funcionario funcionario;
	private CalculadoraStrategy calculadoraStrategy;

	public CalculadoraSalario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setCalculadoraStrategy(CalculadoraStrategy calculadoraStrategy) {
		this.calculadoraStrategy = calculadoraStrategy;
	}

	public CalculadoraStrategy getCalculadoraStrategy() {
		return calculadoraStrategy;
	}

	public BigDecimal getSalarioLiquido() {
		return calculadoraStrategy.calcula(funcionario);
	}

}
