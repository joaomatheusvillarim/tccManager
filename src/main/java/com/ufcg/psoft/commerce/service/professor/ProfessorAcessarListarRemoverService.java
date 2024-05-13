package com.ufcg.psoft.commerce.service.professor;

import java.util.List;

import com.ufcg.psoft.commerce.dto.professor.ProfessorResponseDTO;

public interface ProfessorAcessarListarRemoverService {

  ProfessorResponseDTO acessarProfessor(Long id);

  List<ProfessorResponseDTO> acessarTodosProfessores();

  void removerProfessor(Long id);
  
}
