package com.ufcg.psoft.commerce.exception;

public class ProfessorNaoExisteException extends NotFoundException {
  public ProfessorNaoExisteException() {
      super("O professor consultado nao existe!");
  }
}