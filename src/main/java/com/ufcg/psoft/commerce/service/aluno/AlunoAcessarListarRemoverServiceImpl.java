package com.ufcg.psoft.commerce.service.aluno;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.aluno.AlunoResponseDTO;
import com.ufcg.psoft.commerce.exception.AlunoNaoExisteException;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.repository.AlunoRepository;

@Service
public class AlunoAcessarListarRemoverServiceImpl implements AlunoAcessarListarRemoverService {

  @Autowired
  AlunoRepository alunoRepository;

  @Override
  public AlunoResponseDTO acessarAluno(Long id) {
    Aluno aluno = alunoRepository.findById(id).orElseThrow(AlunoNaoExisteException::new);
    return new AlunoResponseDTO(aluno);
  }

  @Override
  public List<AlunoResponseDTO> acessarTodosAlunos() {
    List<Aluno> alunos = alunoRepository.findAll();
    return alunos.stream()
      .map(AlunoResponseDTO::new)
      .collect(Collectors.toList());
  }

  @Override
  public void removerAluno(Long id) {
    Aluno aluno = alunoRepository.findById(id).orElseThrow(AlunoNaoExisteException::new);
    alunoRepository.delete(aluno);
  }

}
