package com.ufcg.psoft.commerce.exception;

public class TemaNaoExisteException extends NotFoundException {
  public TemaNaoExisteException() {
    super("O tema consultado nao existe");
  }
}
