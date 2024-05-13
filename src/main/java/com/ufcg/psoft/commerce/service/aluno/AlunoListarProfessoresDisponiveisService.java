package com.ufcg.psoft.commerce.service.aluno;

import java.util.List;

import com.ufcg.psoft.commerce.dto.professor.ProfessorResponseDTO;

public interface AlunoListarProfessoresDisponiveisService {
  
  List<ProfessorResponseDTO> listarProfessoresDisponiveisPorAluno(Long id);

}
