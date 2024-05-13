package com.ufcg.psoft.commerce.exception;

public class SolicitacaoNaoExisteException extends NotFoundException {
  public SolicitacaoNaoExisteException() {
    super("A solicitacao consultada nao existe");
  }
}
