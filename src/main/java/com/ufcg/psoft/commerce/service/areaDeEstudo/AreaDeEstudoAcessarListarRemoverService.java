package com.ufcg.psoft.commerce.service.areaDeEstudo;

import java.util.List;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoResponseDTO;

public interface AreaDeEstudoAcessarListarRemoverService {

  AreaDeEstudoResponseDTO acessarAreaDeEstudo(Long id);

  List<AreaDeEstudoResponseDTO> acessarTodasAreaDeEstudo();

  void removerAreaDeEstudo(Long id);
  
}
