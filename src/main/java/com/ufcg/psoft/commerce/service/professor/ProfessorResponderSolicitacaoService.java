package com.ufcg.psoft.commerce.service.professor;

import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoResponseDTO;

public interface ProfessorResponderSolicitacaoService {
  
  SolicitacaoResponseDTO aceitarSolicitacao(Long idProfessor, Long idSolicitacao, String mensagem);

  SolicitacaoResponseDTO rejeitarSolicitacao(Long idProfessor, Long idSolicitacao, String mensagem);
  
}
