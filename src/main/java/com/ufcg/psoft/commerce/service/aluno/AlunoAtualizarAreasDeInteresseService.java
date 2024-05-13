package com.ufcg.psoft.commerce.service.aluno;

import java.util.List;

import com.ufcg.psoft.commerce.dto.aluno.AlunoResponseDTO;
import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;

public interface AlunoAtualizarAreasDeInteresseService {

  AlunoResponseDTO atualizarAreasDeInteresse(Long id, List<AreaDeEstudoPostPutRequestDTO> areasDeEstudoDto);
  
}
