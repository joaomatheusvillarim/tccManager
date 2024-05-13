package com.ufcg.psoft.commerce.service.aluno;

import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoResponseDTO;

public interface AlunoSolicitarOrientacaoService {

  SolicitacaoResponseDTO solicitarOrientacao(SolicitacaoPostPutRequestDTO solicitacaoDto);
  
}
