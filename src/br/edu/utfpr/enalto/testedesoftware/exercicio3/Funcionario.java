package br.edu.utfpr.enalto.testedesoftware.exercicio3;

import java.math.BigDecimal;

import br.edu.utfpr.enalto.testedesoftware.exercicio3.enums.Cargo;
import br.edu.utfpr.enalto.testedesoftware.exercicio3.exception.SalarioFuncionarioInvalidoException;

public class Funcionario {

	private String nome;
	private String email;
	private BigDecimal salario;
	private Cargo cargo;

	public void setSalario(BigDecimal salario) {
		validarSalario(salario);
		this.salario = salario;
	}

	private void validarSalario(BigDecimal salario) {
		if (salario.compareTo(BigDecimal.ZERO) == 0) {
			throw new SalarioFuncionarioInvalidoException("Salário deve ser maior que zero!.");
		}
		if (salario.compareTo(BigDecimal.ZERO) < 0) {
			throw new SalarioFuncionarioInvalidoException("Salário não pode ser menor que zero!.");

		}
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setCargo(Cargo cargo) {
		this.cargo=cargo;
	}

}
