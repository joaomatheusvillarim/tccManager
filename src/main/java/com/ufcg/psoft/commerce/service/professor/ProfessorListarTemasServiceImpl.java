package com.ufcg.psoft.commerce.service.professor;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.tema.TemaResponseDTO;
import com.ufcg.psoft.commerce.exception.ProfessorNaoExisteException;
import com.ufcg.psoft.commerce.model.Tema;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;
import com.ufcg.psoft.commerce.repository.TemaRepository;

@Service
public class ProfessorListarTemasServiceImpl implements ProfessorListarTemasService {

  @Autowired
  TemaRepository temaRepository;

  @Autowired
  ProfessorRepository professorRepository;

  @Override
  public List<TemaResponseDTO> listarTemasPorProfessor(Long id) {
    validarProfessor(id);
    List<Tema> temasProfessor = temaRepository.findByTipoAutorAndAutor("Professor", id);
    return temasProfessor.stream()
      .map(TemaResponseDTO::new)
      .collect(Collectors.toList());
  }

  @Override
  public List<TemaResponseDTO> listarTemasDeAlunos() {
    List<Tema> temasAlunos = temaRepository.findByTipoAutor("Aluno");
    List<TemaResponseDTO> resp = new ArrayList<TemaResponseDTO>();
    for (Tema tema : temasAlunos) {
      resp.add(new TemaResponseDTO(tema));
    }
    return resp;
  }

  private void validarProfessor(Long id) {
    if (! professorRepository.existsById(id)) {
      throw new ProfessorNaoExisteException();
    }
  }
  
}
