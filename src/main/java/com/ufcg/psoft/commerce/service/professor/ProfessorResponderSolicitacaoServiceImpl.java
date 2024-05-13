package com.ufcg.psoft.commerce.service.professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoResponseDTO;
import com.ufcg.psoft.commerce.exception.AlunoNaoExisteException;
import com.ufcg.psoft.commerce.exception.ProfessorNaoExisteException;
import com.ufcg.psoft.commerce.exception.ProfessorSemQuotaException;
import com.ufcg.psoft.commerce.exception.SolicitacaoDadosInvalidosException;
import com.ufcg.psoft.commerce.exception.SolicitacaoNaoExisteException;
import com.ufcg.psoft.commerce.exception.TemaNaoExisteException;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.Professor;
import com.ufcg.psoft.commerce.model.Solicitacao;
import com.ufcg.psoft.commerce.model.Tema;
import com.ufcg.psoft.commerce.repository.AlunoRepository;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;
import com.ufcg.psoft.commerce.repository.SolicitacaoRepository;
import com.ufcg.psoft.commerce.repository.TemaRepository;
import com.ufcg.psoft.commerce.service.aluno.AlunoNotificarNovoTemaRespostaService;

@Service
public class ProfessorResponderSolicitacaoServiceImpl implements ProfessorResponderSolicitacaoService {

  @Autowired
  ProfessorRepository professorRepository;

  @Autowired
  SolicitacaoRepository solicitacaoRepository;

  @Autowired
  AlunoRepository alunoRepository;

  @Autowired
  TemaRepository temaRepository;

  @Autowired
  AlunoNotificarNovoTemaRespostaService alunoNotificarNovoTemaRespostaService;

  @Override
  public SolicitacaoResponseDTO aceitarSolicitacao(Long idProfessor, Long idSolicitacao, String mensagem) {
    Solicitacao solicitacao = solicitacaoRepository.findById(idSolicitacao).orElseThrow(SolicitacaoNaoExisteException::new);
    Professor professor = professorRepository.findById(idProfessor).orElseThrow(ProfessorNaoExisteException::new);
    Aluno aluno = alunoRepository.findById(solicitacao.getAluno()).orElseThrow(AlunoNaoExisteException::new);
    Tema tema = temaRepository.findById(solicitacao.getTema()).orElseThrow(TemaNaoExisteException::new);
    
    validarSolicitacao(professor, solicitacao);
    validarProfessor(professor);

    solicitacao.setAceito(true);
    aluno.setAlocado(true);
    professor.setQuota(professor.getQuota() - 1);
    tema.getStatus().aceitar(tema);

    alunoNotificarNovoTemaRespostaService.notificarRespostaSolicitacao(idSolicitacao, mensagem);

    solicitacaoRepository.save(solicitacao);
    alunoRepository.save(aluno);
    professorRepository.save(professor);
    temaRepository.save(tema);
    return new SolicitacaoResponseDTO(solicitacao);
  }

  @Override
  public SolicitacaoResponseDTO rejeitarSolicitacao(Long idProfessor, Long idSolicitacao, String mensagem) {
    Solicitacao solicitacao = solicitacaoRepository.findById(idSolicitacao).orElseThrow(SolicitacaoNaoExisteException::new);
    Professor professor = professorRepository.findById(idProfessor).orElseThrow(ProfessorNaoExisteException::new);
    Tema tema = temaRepository.findById(solicitacao.getTema()).orElseThrow(TemaNaoExisteException::new);
    
    validarSolicitacao(professor, solicitacao);

    tema.getStatus().rejeitar(tema);

    alunoNotificarNovoTemaRespostaService.notificarRespostaSolicitacao(idSolicitacao, mensagem);

    solicitacaoRepository.save(solicitacao);
    temaRepository.save(tema);
    return new SolicitacaoResponseDTO(solicitacao);
  }

  private void validarSolicitacao(Professor professor, Solicitacao solicitacao) {
    if (professor.getId() != solicitacao.getProfessor()) {
      throw new SolicitacaoDadosInvalidosException();
    }
  }
  
  private void validarProfessor(Professor professor) {
    if (professor.getQuota() == 0) {
      throw new ProfessorSemQuotaException();
    }
  }
  
}
