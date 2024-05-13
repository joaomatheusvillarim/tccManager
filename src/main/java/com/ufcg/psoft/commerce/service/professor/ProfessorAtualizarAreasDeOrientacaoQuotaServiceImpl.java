package com.ufcg.psoft.commerce.service.professor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.professor.ProfessorResponseDTO;
import com.ufcg.psoft.commerce.exception.ProfessorNaoExisteException;
import com.ufcg.psoft.commerce.exception.ProfessorDadosInvalidosException;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.model.Professor;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;
import com.ufcg.psoft.commerce.service.areaDeEstudo.AreaDeEstudoValidarService;

@Service
public class ProfessorAtualizarAreasDeOrientacaoQuotaServiceImpl implements ProfessorAtualizarAreasDeOrientacaoQuotaService {

  @Autowired
  ProfessorRepository professorRepository;

  @Autowired
  AreaDeEstudoValidarService areaDeEstudoValidarService;

  @Override
  public ProfessorResponseDTO atualizarAreasDeOrientacao(Long id, List<AreaDeEstudoPostPutRequestDTO> areasDeEstudoDto) {
    Professor professor = professorRepository.findById(id).orElseThrow(ProfessorNaoExisteException::new);
    
    //validar areas de estudo
    List<AreaDeEstudo> areasValidas = areaDeEstudoValidarService.validarAreasDeEstudo(areasDeEstudoDto);

    professor.setAreasDeOrientacao(areasValidas);
    professorRepository.save(professor);
    return new ProfessorResponseDTO(professor);
  }

  @Override
  public ProfessorResponseDTO atualizarQuota(Long id, Integer quota) {
    Professor professor = professorRepository.findById(id).orElseThrow(ProfessorNaoExisteException::new);
    
    //validar quota
    validarQuota(quota);
    
    professor.setQuota(quota);
    professorRepository.save(professor);
    return new ProfessorResponseDTO(professor);
  }

  private void validarQuota(Integer quota) {
    if (quota < 0) {
      throw new ProfessorDadosInvalidosException();
    }
  }
  
}
