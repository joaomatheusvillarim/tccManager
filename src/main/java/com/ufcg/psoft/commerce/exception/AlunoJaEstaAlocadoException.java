package com.ufcg.psoft.commerce.exception;

public class AlunoJaEstaAlocadoException extends InvalidDataException {
  public AlunoJaEstaAlocadoException() {
    super("O aluno ja esta alocado!");
  }  
}
