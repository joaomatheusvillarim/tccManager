package com.ufcg.psoft.commerce.service.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.aluno.AlunoResponseDTO;
import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.AlunoNaoExisteException;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.repository.AlunoRepository;
import com.ufcg.psoft.commerce.service.areaDeEstudo.AreaDeEstudoValidarService;

@Service
public class AlunoAtualizarAreasDeInteresseServiceImpl implements AlunoAtualizarAreasDeInteresseService {

  @Autowired
  AlunoRepository alunoRepository;

  @Autowired
  AreaDeEstudoValidarService areaDeEstudoValidarService;

  @Override
  public AlunoResponseDTO atualizarAreasDeInteresse(Long id, List<AreaDeEstudoPostPutRequestDTO> areasDeEstudoDto) {
    Aluno aluno = alunoRepository.findById(id).orElseThrow(AlunoNaoExisteException::new);
    
    List<AreaDeEstudo> areasValidas = areaDeEstudoValidarService.validarAreasDeEstudo(areasDeEstudoDto);

    aluno.setAreasDeInteresse(areasValidas);
    alunoRepository.save(aluno);
    
    return new AlunoResponseDTO(aluno);
  }
  
}
