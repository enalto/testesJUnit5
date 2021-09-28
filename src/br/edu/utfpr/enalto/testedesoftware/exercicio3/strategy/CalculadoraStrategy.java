package br.edu.utfpr.enalto.testedesoftware.exercicio3.strategy;

import java.math.BigDecimal;

import br.edu.utfpr.enalto.testedesoftware.exercicio3.Funcionario;

public interface CalculadoraStrategy {
	
	BigDecimal calcula(Funcionario funcionario);

}
