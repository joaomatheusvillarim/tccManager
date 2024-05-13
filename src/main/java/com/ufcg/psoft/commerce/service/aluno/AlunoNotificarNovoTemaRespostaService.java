package com.ufcg.psoft.commerce.service.aluno;

import com.ufcg.psoft.commerce.model.AreaDeEstudo;

import java.util.List;

public interface AlunoNotificarNovoTemaRespostaService {
  
  void notificarNovoTema(List<AreaDeEstudo> areasDeEstudo);

  void notificarRespostaSolicitacao(Long id, String mensagem);

}
