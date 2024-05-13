package com.ufcg.psoft.commerce.exception;

public class TemaDadosInvalidosException extends InvalidDataException {
  public TemaDadosInvalidosException() {
    super("Os dados do tema de TCC fornecido sao invalidos!");
  }
}
