package com.ufcg.psoft.commerce.service.professor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoResponseDTO;
import com.ufcg.psoft.commerce.exception.ProfessorNaoExisteException;
import com.ufcg.psoft.commerce.model.Solicitacao;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;
import com.ufcg.psoft.commerce.repository.SolicitacaoRepository;

@Service
public class ProfessorListarSolicitacoesServiceImpl implements ProfessorListarSolicitacoesService {

  @Autowired
  SolicitacaoRepository solicitacaoRepository;

  @Autowired
  ProfessorRepository professorRepository;

  @Override
  public List<SolicitacaoResponseDTO> listarSolicitacoesPorProfessor(Long id) {
    validarProfessor(id);

    List<Solicitacao> solicitacoes = solicitacaoRepository.findByProfessor(id);
    return solicitacoes.stream()
      .map(SolicitacaoResponseDTO::new)
      .collect(Collectors.toList());
  }

  private void validarProfessor(Long id) {
    if (! professorRepository.existsById(id)) {
      throw new ProfessorNaoExisteException();
    }
  }

}
