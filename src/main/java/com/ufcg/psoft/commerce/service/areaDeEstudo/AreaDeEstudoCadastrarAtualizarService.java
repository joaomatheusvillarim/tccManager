package com.ufcg.psoft.commerce.service.areaDeEstudo;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoResponseDTO;

public interface AreaDeEstudoCadastrarAtualizarService {

  AreaDeEstudoResponseDTO cadastrarAreaDeEstudo(AreaDeEstudoPostPutRequestDTO areaDeEstudoDto);

  AreaDeEstudoResponseDTO atualizarAreaDeEstudo(Long id, AreaDeEstudoPostPutRequestDTO areaDeEstudoDto);
  
}
