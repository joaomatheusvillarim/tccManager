package com.ufcg.psoft.commerce.exception;

public class AreaDeEstudoNaoExisteException extends NotFoundException {
  public AreaDeEstudoNaoExisteException() {
      super("A area de estudo consultada nao existe!");
  }
}