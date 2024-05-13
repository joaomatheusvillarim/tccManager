package com.ufcg.psoft.commerce.exception;

public class ProfessorDadosInvalidosException extends InvalidDataException {
  public ProfessorDadosInvalidosException() {
      super("os dados do professor fornecido sao invalidos!");
  }
}