package com.ufcg.psoft.commerce.service.coordenador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.exception.ManagerException;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.Professor;
import com.ufcg.psoft.commerce.model.Solicitacao;
import com.ufcg.psoft.commerce.model.Tema;
import com.ufcg.psoft.commerce.repository.AlunoRepository;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;
import com.ufcg.psoft.commerce.repository.SolicitacaoRepository;
import com.ufcg.psoft.commerce.repository.TemaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoordenadorGerarRelatorioServiceImpl implements CoordenadorGerarRelatorioService {

  @Autowired
  AlunoRepository alunoRepository;
   
  @Autowired
  SolicitacaoRepository solicitacaoRepository;

  @Autowired
  ProfessorRepository professorRepository;
   
  @Autowired
  TemaRepository temaRepository;
   
  @Override
  public void gerarRelatorioAlocacoes() {
    System.out.println("\nRELATORIO DE ALOCACOES ORIENTANDO-ORIENTADOR:\n");
    System.out.println("----------");
    printAlunosAlocados();
    System.out.println("----------");
    printAlunosNaoAlocados();
    System.out.println("----------\n");
  }
    
  private void printAlunosNaoAlocados() {
    List<Aluno> alunosNaoAlocados = alunoRepository.findByAlocado(false);
    System.out.println("\nLista de alunos nao-alocados:\n");

    if (alunosNaoAlocados.isEmpty()) {
      System.out.println("Nenhum aluno encontrado.");

    }

    for (Aluno aluno : alunosNaoAlocados) {
      System.out.println(aluno.getMatricula() + " - " + aluno.getNomeCompleto());  
    }
  }
  
  private void printAlunosAlocados() {
    List<Aluno> alunosAlocados = alunoRepository.findByAlocado(true);
    System.out.println("\nListas de alunos alocados:\n");

    if (alunosAlocados.isEmpty()) {
      System.out.println("Nenhuma alocacao encontrada.");
    }

    for (Aluno aluno : alunosAlocados) {
      Solicitacao solicitacao = solicitacaoRepository.findByAceitoAndAluno(true, aluno.getId()).orElseThrow(ManagerException::new);
      Professor professor = professorRepository.findById(solicitacao.getProfessor()).orElseThrow(ManagerException::new);
      Tema tema = temaRepository.findById(solicitacao.getTema()).orElseThrow(ManagerException::new);
      String[] areasDeEstudo = tema.getAreasRelacionadas().stream()
        .map(area -> area.getNome())
        .collect(Collectors.toList()).toArray(new String[0]);
      
      System.out.println("----------");
      System.out.println("Orientando: " + aluno.getNomeCompleto() + " - " + aluno.getMatricula());
      System.out.println("Orientador: " + professor.getNomeCompleto());
      System.out.println("Tema: " + tema.getTitulo());
      System.out.println("Areas:" + Arrays.toString(areasDeEstudo));
    }
  }
    
}
