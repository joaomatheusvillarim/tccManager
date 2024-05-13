package com.ufcg.psoft.commerce.service.areaDeEstudo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.AreaDeEstudoNaoExisteException;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.repository.AreaDeEstudoRepository;

@Service
public class AreaDeEstudoValidarServiceImpl implements AreaDeEstudoValidarService {

  @Autowired
  AreaDeEstudoRepository areaDeEstudoRepository;

  @Override
  public List<AreaDeEstudo> validarAreasDeEstudo(List<AreaDeEstudoPostPutRequestDTO> areasDeEstudoDto) {
    List<AreaDeEstudo> resp = new ArrayList<AreaDeEstudo>();

    for (AreaDeEstudoPostPutRequestDTO areaDto : areasDeEstudoDto) {
      AreaDeEstudo areaDeEstudo = areaDeEstudoRepository.findByNome(areaDto.getNome()).orElseThrow(AreaDeEstudoNaoExisteException::new);

      resp.add(areaDeEstudo);
    }
    
    return resp;
  }
  
}
