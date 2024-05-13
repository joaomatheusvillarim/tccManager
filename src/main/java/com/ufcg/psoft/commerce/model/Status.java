package com.ufcg.psoft.commerce.model;

public enum Status {
  
  NOVO {
    
    @Override
    public String getValue() {
      return "NOVO";
    }

    @Override
    public void anexar(Tema tema) {
      if (tema.getTipoAutor().equals("Aluno")) {
        tema.setStatus(PENDENTE);
      }
    }

    @Override
    public void aceitar(Tema tema) {
      if (tema.getTipoAutor().equals("Professor")) {
        tema.setStatus(ALOCADO);
      }
    }

    @Override
    public void rejeitar(Tema tema) {
      //pass
    }

  },
  PENDENTE {

    @Override
    public String getValue() {
      return "PENDENTE";
    }

    @Override
    public void anexar(Tema tema) {
      //pass
    }

    @Override
    public void aceitar(Tema tema) {
      tema.setStatus(ALOCADO);
    }

    @Override
    public void rejeitar(Tema tema) {
      tema.setStatus(NOVO);
    }

  },
  ALOCADO {

    @Override
    public String getValue() {
      return "ALOCADO";
    }

    @Override
    public void anexar(Tema tema) {
      //pass
    }

    @Override
    public void aceitar(Tema tema) {
      //pass
    }

    @Override
    public void rejeitar(Tema tema) {
      //pass
    }

  };

  public abstract String getValue();
  public abstract void anexar(Tema tema);
  public abstract void aceitar(Tema tema);
  public abstract void rejeitar(Tema tema);
}
