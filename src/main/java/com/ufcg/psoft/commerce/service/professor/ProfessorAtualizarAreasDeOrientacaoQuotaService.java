package com.ufcg.psoft.commerce.service.professor;

import java.util.List;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.professor.ProfessorResponseDTO;

public interface ProfessorAtualizarAreasDeOrientacaoQuotaService {

  ProfessorResponseDTO atualizarAreasDeOrientacao(Long id, List<AreaDeEstudoPostPutRequestDTO> areasDeEstudoDto);

  ProfessorResponseDTO atualizarQuota(Long id, Integer quota);
  
}