package utfpr.enalto.exercicio3.strategy;

import java.math.BigDecimal;

import utfpr.enalto.exercicio3.Funcionario;

public interface CalculadoraStrategy {
	
	BigDecimal calcula(Funcionario funcionario);

}
