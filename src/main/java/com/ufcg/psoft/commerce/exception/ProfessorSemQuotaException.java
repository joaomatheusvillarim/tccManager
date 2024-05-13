package com.ufcg.psoft.commerce.exception;

public class ProfessorSemQuotaException extends InvalidDataException {
  public ProfessorSemQuotaException() {
    super("O professor consultado está sem quota!");
  }
  
}
