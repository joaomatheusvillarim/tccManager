package com.ufcg.psoft.commerce.service.areaDeEstudo;

import java.util.List;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;

public interface AreaDeEstudoValidarService {

  List<AreaDeEstudo> validarAreasDeEstudo(List<AreaDeEstudoPostPutRequestDTO> areasDeEstudoDto);
  
}
