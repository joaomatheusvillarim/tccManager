package com.ufcg.psoft.commerce.service.professor;

import java.util.List;

import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoResponseDTO;

public interface ProfessorListarSolicitacoesService {

  List<SolicitacaoResponseDTO> listarSolicitacoesPorProfessor(Long id);

}
