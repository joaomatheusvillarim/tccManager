package com.ufcg.psoft.commerce.service.aluno;

import java.util.List;

import com.ufcg.psoft.commerce.dto.aluno.AlunoResponseDTO;

public interface AlunoAcessarListarRemoverService {

  AlunoResponseDTO acessarAluno(Long id);

  List<AlunoResponseDTO> acessarTodosAlunos();

  void removerAluno(Long id);
  
}
