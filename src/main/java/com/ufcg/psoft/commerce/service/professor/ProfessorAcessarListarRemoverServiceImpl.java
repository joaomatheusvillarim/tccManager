package com.ufcg.psoft.commerce.service.professor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.professor.ProfessorResponseDTO;
import com.ufcg.psoft.commerce.exception.ProfessorNaoExisteException;
import com.ufcg.psoft.commerce.model.Professor;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;

@Service
public class ProfessorAcessarListarRemoverServiceImpl implements ProfessorAcessarListarRemoverService {

  @Autowired
  ProfessorRepository professorRepository;

  @Override
  public ProfessorResponseDTO acessarProfessor(Long id) {
    Professor professor = professorRepository.findById(id).orElseThrow(ProfessorNaoExisteException::new);
    return new ProfessorResponseDTO(professor);
  }

  @Override
  public List<ProfessorResponseDTO> acessarTodosProfessores() {
    List<Professor> professores = professorRepository.findAll();
    return professores.stream()
      .map(ProfessorResponseDTO::new)
      .collect(Collectors.toList());
  }

  @Override
  public void removerProfessor(Long id) {
    Professor professor = professorRepository.findById(id).orElseThrow(ProfessorNaoExisteException::new);
    professorRepository.delete(professor);
  }
  
}
