package com.ufcg.psoft.commerce.service.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.exception.ProfessorNaoExisteException;
import com.ufcg.psoft.commerce.model.Professor;
import com.ufcg.psoft.commerce.model.Solicitacao;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;

@Service
public class ProfessorNotificarNovaSolicitacaoServiceImpl implements ProfessorNotificarNovaSolicitacaoService {

  @Autowired
  ProfessorRepository professorRepository;

  @Override
  public void notificarProfessorNovaSolicitacao(Solicitacao solicitacao) {
    Professor professor = professorRepository.findById(solicitacao.getProfessor()).orElseThrow(ProfessorNaoExisteException::new);
    notificarProfessor(professor.getEmail());
  }

  private void notificarProfessor(String email) {
    System.out.println("\nNotificações do sistema:\n"
      + "[" + email + "] Você recebeu uma nova solicitação de orientação de TCC.\n");
  }
  
}
