package com.ufcg.psoft.commerce.service.areaDeEstudo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoResponseDTO;
import com.ufcg.psoft.commerce.exception.AreaDeEstudoDadosInvalidosException;
import com.ufcg.psoft.commerce.exception.AreaDeEstudoNaoExisteException;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.repository.AreaDeEstudoRepository;

@Service
public class AreaDeEstudoCadastrarAtualizarServiceImpl implements AreaDeEstudoCadastrarAtualizarService {

  @Autowired
  AreaDeEstudoRepository areaDeEstudoRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public AreaDeEstudoResponseDTO cadastrarAreaDeEstudo(AreaDeEstudoPostPutRequestDTO areaDeEstudoDto) {
    validarAreaDeEstudo(areaDeEstudoDto);
    AreaDeEstudo areaDeEstudo = modelMapper.map(areaDeEstudoDto, AreaDeEstudo.class);
    areaDeEstudoRepository.save(areaDeEstudo);
    return modelMapper.map(areaDeEstudo, AreaDeEstudoResponseDTO.class);
  }

  @Override
  public AreaDeEstudoResponseDTO atualizarAreaDeEstudo(Long id, AreaDeEstudoPostPutRequestDTO areaDeEstudoDto) {
    AreaDeEstudo areaDeEstudo = areaDeEstudoRepository.findById(id).orElseThrow(AreaDeEstudoNaoExisteException::new);
    
    areaDeEstudoRepository.deleteById(id);
    validarAreaDeEstudo(areaDeEstudoDto);
    
    modelMapper.map(areaDeEstudoDto, areaDeEstudo);
    areaDeEstudoRepository.save(areaDeEstudo);
    return modelMapper.map(areaDeEstudo, AreaDeEstudoResponseDTO.class);
  }

  private void validarAreaDeEstudo(AreaDeEstudoPostPutRequestDTO areaDeEstudoDto) {
    if (areaDeEstudoRepository.existsByNome(areaDeEstudoDto.getNome())) {
      throw new AreaDeEstudoDadosInvalidosException();
    }
  }
  
}
