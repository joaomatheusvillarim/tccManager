package com.ufcg.psoft.commerce.exception;

public class SolicitacaoDadosInvalidosException extends InvalidDataException {
  public SolicitacaoDadosInvalidosException() {
    super("OS dados da solicitacao fornecida sao invalidos!");
  }
}
