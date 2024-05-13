package com.ufcg.psoft.commerce.service.professor;

import com.ufcg.psoft.commerce.dto.tema.TemaPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.tema.TemaResponseDTO;

public interface ProfessorCadastrarTemaService {
  
  TemaResponseDTO cadastrarTemaTcc(Long id, TemaPostPutRequestDTO temaDto);

}
