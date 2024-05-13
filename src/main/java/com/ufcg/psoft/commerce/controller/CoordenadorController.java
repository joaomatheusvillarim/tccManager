package com.ufcg.psoft.commerce.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufcg.psoft.commerce.dto.aluno.AlunoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.professor.ProfessorPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.aluno.AlunoAcessarListarRemoverService;
import com.ufcg.psoft.commerce.service.aluno.AlunoCadastrarAtualizarService;
import com.ufcg.psoft.commerce.service.areaDeEstudo.AreaDeEstudoAcessarListarRemoverService;
import com.ufcg.psoft.commerce.service.areaDeEstudo.AreaDeEstudoCadastrarAtualizarService;
import com.ufcg.psoft.commerce.service.coordenador.CoordenadorGerarRelatorioService;
import com.ufcg.psoft.commerce.service.professor.ProfessorAcessarListarRemoverService;
import com.ufcg.psoft.commerce.service.professor.ProfessorCadastrarAtualizarService;

@RestController
@RequestMapping(
  value = "/coordenador",
  produces = MediaType.APPLICATION_JSON_VALUE
)
public class CoordenadorController {

  @Autowired
  AlunoCadastrarAtualizarService alunoCadastrarAtualizarService;

  @Autowired
  AlunoAcessarListarRemoverService alunoAcessarListarRemoverService;

  @Autowired
  ProfessorCadastrarAtualizarService professorCadastrarAtualizarService;

  @Autowired
  ProfessorAcessarListarRemoverService professorAcessarListarRemoverService;

  @Autowired
  AreaDeEstudoCadastrarAtualizarService areaDeEstudoCadastrarAtualizarService;

  @Autowired
  AreaDeEstudoAcessarListarRemoverService areaDeEstudoAcessarListarRemoverService;

  @Autowired
  CoordenadorGerarRelatorioService coordenadorGerarRelatorioService;

  @GetMapping("/aluno/{id}")
  public ResponseEntity<?> acessarAluno(
    @PathVariable Long id) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(alunoAcessarListarRemoverService.acessarAluno(id));
  }

  @GetMapping("/aluno")
  public ResponseEntity<?> acessarTodosAlunos() {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(alunoAcessarListarRemoverService.acessarTodosAlunos());
  }

  @PostMapping("/aluno")
  public ResponseEntity<?> criarAluno(
    @RequestBody @Valid AlunoPostPutRequestDTO alunoPostPutRequestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(alunoCadastrarAtualizarService.cadastrarAluno(alunoPostPutRequestDto));
  }

  @PutMapping("/aluno/{id}")
  public ResponseEntity<?> atualizarAluno(
    @PathVariable Long id,
    @RequestBody @Valid AlunoPostPutRequestDTO alunoPostPutRequestDto) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(alunoCadastrarAtualizarService.atualizarAluno(id, alunoPostPutRequestDto));
  }

  @DeleteMapping("/aluno/{id}")
  public ResponseEntity<?> excluirAluno(@PathVariable Long id) {
    alunoAcessarListarRemoverService.removerAluno(id);
    return ResponseEntity
      .status(HttpStatus.NO_CONTENT)
      .body("");
  }

  @GetMapping("/professor/{id}")
  public ResponseEntity<?> acessarProfessor(
    @PathVariable Long id) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(professorAcessarListarRemoverService.acessarProfessor(id));
  }

  @GetMapping("/professor")
  public ResponseEntity<?> acessarTodosProfessores() {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(professorAcessarListarRemoverService.acessarTodosProfessores());
  }

  @PostMapping("/professor")
  public ResponseEntity<?> criarProfessor(
    @RequestBody @Valid ProfessorPostPutRequestDTO professorPostPutRequestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(professorCadastrarAtualizarService.cadastrarProfessor(professorPostPutRequestDto));
  }

  @PutMapping("/professor/{id}")
  public ResponseEntity<?> atualizarProfessor(
    @PathVariable Long id,
    @RequestBody @Valid ProfessorPostPutRequestDTO professorPostPutRequestDto) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(professorCadastrarAtualizarService.atualizarProfessor(id, professorPostPutRequestDto));
  }

  @DeleteMapping("/professor/{id}")
  public ResponseEntity<?> excluirProfessor(@PathVariable Long id) {
    professorAcessarListarRemoverService.removerProfessor(id);
    return ResponseEntity
      .status(HttpStatus.NO_CONTENT)
      .body("");
  }

  @GetMapping("/area-de-estudo/{id}")
  public ResponseEntity<?> acessarAreaDeEstudo(
    @PathVariable Long id) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(areaDeEstudoAcessarListarRemoverService.acessarAreaDeEstudo(id));
  }

  @GetMapping("/area-de-estudo")
  public ResponseEntity<?> acessarTodasAreasDeEstudo() {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(areaDeEstudoAcessarListarRemoverService.acessarTodasAreaDeEstudo());
  }

  @PostMapping("/area-de-estudo")
  public ResponseEntity<?> criarAreaDeEstudo(
    @RequestBody @Valid AreaDeEstudoPostPutRequestDTO areaDeEstudoPostPutRequestDto) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(areaDeEstudoCadastrarAtualizarService.cadastrarAreaDeEstudo(areaDeEstudoPostPutRequestDto));
  }

  @PutMapping("/area-de-estudo/{id}")
  public ResponseEntity<?> atualizarAreaDeEstudo(
    @PathVariable Long id,
    @RequestBody @Valid AreaDeEstudoPostPutRequestDTO areaDeEstudoPostPutRequestDto) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(areaDeEstudoCadastrarAtualizarService.atualizarAreaDeEstudo(id, areaDeEstudoPostPutRequestDto));
  }

  @DeleteMapping("/area-de-estudo/{id}")
  public ResponseEntity<?> excluirAreaDeEstudo(@PathVariable Long id) {
    areaDeEstudoAcessarListarRemoverService.removerAreaDeEstudo(id);
    return ResponseEntity
      .status(HttpStatus.NO_CONTENT)
      .body("");
  }

  @GetMapping("/relatorio")
  public ResponseEntity<?> gerarRelatorioDeAlocacoes() {
    coordenadorGerarRelatorioService.gerarRelatorioAlocacoes();
    return ResponseEntity
      .status(HttpStatus.NO_CONTENT)
      .body("");
  }
  
}
