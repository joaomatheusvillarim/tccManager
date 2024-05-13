package com.ufcg.psoft.commerce.service.aluno;

import com.ufcg.psoft.commerce.dto.tema.TemaPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.tema.TemaResponseDTO;

public interface AlunoCadastrarTemaService {
  
  TemaResponseDTO cadastrarTemaTcc(Long id, TemaPostPutRequestDTO temaDto);
  
}
