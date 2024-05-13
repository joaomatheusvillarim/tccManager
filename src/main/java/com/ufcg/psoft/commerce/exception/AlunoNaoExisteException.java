package com.ufcg.psoft.commerce.exception;

public class AlunoNaoExisteException extends NotFoundException {
  public AlunoNaoExisteException() {
      super("O aluno consultado nao existe!");
  }
}