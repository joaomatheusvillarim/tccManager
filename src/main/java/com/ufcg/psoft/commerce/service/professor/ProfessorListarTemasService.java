package com.ufcg.psoft.commerce.service.professor;

import java.util.List;

import com.ufcg.psoft.commerce.dto.tema.TemaResponseDTO;

public interface ProfessorListarTemasService {
  
  List<TemaResponseDTO> listarTemasPorProfessor(Long id);

  List<TemaResponseDTO> listarTemasDeAlunos();

}
