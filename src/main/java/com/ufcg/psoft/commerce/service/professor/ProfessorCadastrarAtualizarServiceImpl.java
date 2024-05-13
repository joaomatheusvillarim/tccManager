package com.ufcg.psoft.commerce.service.professor;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.professor.ProfessorPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.professor.ProfessorResponseDTO;
import com.ufcg.psoft.commerce.exception.ProfessorDadosInvalidosException;
import com.ufcg.psoft.commerce.exception.ProfessorNaoExisteException;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.model.Professor;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;

@Service
public class ProfessorCadastrarAtualizarServiceImpl implements ProfessorCadastrarAtualizarService {

  @Autowired
  ProfessorRepository professorRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public ProfessorResponseDTO cadastrarProfessor(ProfessorPostPutRequestDTO professorDto) {
    validarProfessor(professorDto);
    Professor professor = modelMapper.map(professorDto, Professor.class);

    professor.setAreasDeOrientacao(new ArrayList<AreaDeEstudo>());
    professor.setQuota(0);

    professorRepository.save(professor);
    return modelMapper.map(professor, ProfessorResponseDTO.class);
  }

  @Override
  public ProfessorResponseDTO atualizarProfessor(Long id, ProfessorPostPutRequestDTO professorDto) {
    Professor professor = professorRepository.findById(id).orElseThrow(ProfessorNaoExisteException::new);
  
    professorRepository.deleteById(id);
    validarProfessor(professorDto);

    modelMapper.map(professorDto, professor);
    professorRepository.save(professor);
    return modelMapper.map(professor, ProfessorResponseDTO.class);
  }

  private void validarProfessor(ProfessorPostPutRequestDTO professorDto) {
    if (professorRepository.existsByEmail(professorDto.getEmail())) {
      throw new ProfessorDadosInvalidosException();
    }
  }
  
}
