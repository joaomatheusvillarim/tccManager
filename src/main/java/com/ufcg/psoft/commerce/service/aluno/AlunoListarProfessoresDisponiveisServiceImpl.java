package com.ufcg.psoft.commerce.service.aluno;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.professor.ProfessorResponseDTO;
import com.ufcg.psoft.commerce.exception.AlunoNaoExisteException;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.repository.AlunoRepository;
import com.ufcg.psoft.commerce.service.professor.ProfessorAcessarListarRemoverService;

@Service
public class AlunoListarProfessoresDisponiveisServiceImpl implements AlunoListarProfessoresDisponiveisService {

  @Autowired
  AlunoRepository alunoRepository;

  @Autowired
  ProfessorAcessarListarRemoverService professorAcessarListarRemoverService;

  @Override
  public List<ProfessorResponseDTO> listarProfessoresDisponiveisPorAluno(Long id) {
    Aluno aluno = alunoRepository.findById(id).orElseThrow(AlunoNaoExisteException::new);
    List<AreaDeEstudo> areasDoAluno = aluno.getAreasDeInteresse();
    List<ProfessorResponseDTO> todosProfessores = professorAcessarListarRemoverService.acessarTodosProfessores();
    List<ProfessorResponseDTO> resp = new ArrayList<ProfessorResponseDTO>();

    for (ProfessorResponseDTO professor : todosProfessores) {

      if (professor.getQuota() > 0) {
        List<AreaDeEstudo> areasDoProfessor = professor.getAreasDeOrientacao();

        for (AreaDeEstudo area : areasDoProfessor) {
          
          if (areasDoAluno.contains(area)) {
            resp.add(professor);
          }
        }
      }
    }

    return resp;
  }
  
}
