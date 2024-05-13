package com.ufcg.psoft.commerce.service.professor;

import com.ufcg.psoft.commerce.dto.professor.ProfessorPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.professor.ProfessorResponseDTO;

public interface ProfessorCadastrarAtualizarService {

  ProfessorResponseDTO cadastrarProfessor(ProfessorPostPutRequestDTO professorDto);

  ProfessorResponseDTO atualizarProfessor(Long id, ProfessorPostPutRequestDTO professorDto);
  
}