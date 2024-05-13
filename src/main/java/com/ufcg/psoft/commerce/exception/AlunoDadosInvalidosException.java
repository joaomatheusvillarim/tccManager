package com.ufcg.psoft.commerce.exception;

public class AlunoDadosInvalidosException extends InvalidDataException {
  public AlunoDadosInvalidosException() {
      super("Os dados do aluno fornecido sao invalidos!");
  }
}