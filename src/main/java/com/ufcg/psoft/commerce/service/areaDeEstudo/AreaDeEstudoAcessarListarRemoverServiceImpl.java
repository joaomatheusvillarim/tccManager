package com.ufcg.psoft.commerce.service.areaDeEstudo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoResponseDTO;
import com.ufcg.psoft.commerce.exception.AreaDeEstudoNaoExisteException;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.repository.AreaDeEstudoRepository;

@Service
public class AreaDeEstudoAcessarListarRemoverServiceImpl implements AreaDeEstudoAcessarListarRemoverService {

  @Autowired
  AreaDeEstudoRepository areaDeEstudoRepository;

  @Override
  public AreaDeEstudoResponseDTO acessarAreaDeEstudo(Long id) {
    AreaDeEstudo areaDeEstudo = areaDeEstudoRepository.findById(id).orElseThrow(AreaDeEstudoNaoExisteException::new);
    return new AreaDeEstudoResponseDTO(areaDeEstudo);
  }

  @Override
  public List<AreaDeEstudoResponseDTO> acessarTodasAreaDeEstudo() {
    List<AreaDeEstudo> areasDeEstudo = areaDeEstudoRepository.findAll();
    return areasDeEstudo.stream()
      .map(AreaDeEstudoResponseDTO::new)
      .collect(Collectors.toList());
  }

  @Override
  public void removerAreaDeEstudo(Long id) {
    AreaDeEstudo areaDeEstudo = areaDeEstudoRepository.findById(id).orElseThrow(AreaDeEstudoNaoExisteException::new);
    areaDeEstudoRepository.delete(areaDeEstudo);
  }
  
}