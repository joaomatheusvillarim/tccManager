package com.ufcg.psoft.commerce.service.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.exception.AlunoNaoExisteException;
import com.ufcg.psoft.commerce.exception.SolicitacaoNaoExisteException;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.model.Solicitacao;
import com.ufcg.psoft.commerce.repository.AlunoRepository;
import com.ufcg.psoft.commerce.repository.SolicitacaoRepository;

@Service
public class AlunoNotificarNovoTemaRespostaServiceImpl implements AlunoNotificarNovoTemaRespostaService {

  @Autowired
  AlunoRepository alunoRepository;

  @Autowired
  SolicitacaoRepository solicitacaoRepository;

  @Override
  public void notificarNovoTema(List<AreaDeEstudo> areasDeEstudo) {
    List<Aluno> todosAlunos = alunoRepository.findAll();

    for (Aluno aluno : todosAlunos) {
      List<AreaDeEstudo> areasDoAluno = aluno.getAreasDeInteresse();

      for (AreaDeEstudo area : areasDoAluno) {
        if (areasDeEstudo.contains(area)) {
          notificarAluno(aluno.getEmail());
          break;
        }
      }

    }
  }

  private void notificarAluno(String email) {
    System.out.println("\nNotificações do sistema:\n"
      + "[" + email + "] Um novo tema de TCC das suas áreas de interesse foi cadastrado "
      + "por um professor.\n");
  }

  @Override
  public void notificarRespostaSolicitacao(Long id, String mensagem) {
    Solicitacao solicitacao = solicitacaoRepository.findById(id).orElseThrow(SolicitacaoNaoExisteException::new);
    Aluno aluno = alunoRepository.findById(solicitacao.getAluno()).orElseThrow(AlunoNaoExisteException::new);
    String email = aluno.getEmail();
    String resposta = "REJEITADA";
    if (solicitacao.getAceito()) {
      resposta = "ACEITA";
    }
    System.out.println("\nNotificações do sistema:\n" +
      "[" + email + "] Sua solicitação de orientação de TCC de id " + solicitacao.getId()
      + " foi " + resposta + ". Mensagem do orientador:\n" + mensagem);
  }
  
}
