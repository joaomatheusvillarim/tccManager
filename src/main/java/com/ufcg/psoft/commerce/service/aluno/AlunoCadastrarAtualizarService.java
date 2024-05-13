package com.ufcg.psoft.commerce.service.aluno;

import com.ufcg.psoft.commerce.dto.aluno.AlunoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.aluno.AlunoResponseDTO;

public interface AlunoCadastrarAtualizarService {

  AlunoResponseDTO cadastrarAluno(AlunoPostPutRequestDTO alunoDto);

  AlunoResponseDTO atualizarAluno(Long id, AlunoPostPutRequestDTO alunoDto);
  
}
