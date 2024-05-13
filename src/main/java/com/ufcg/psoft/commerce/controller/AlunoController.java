package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.tema.TemaPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.aluno.AlunoAtualizarAreasDeInteresseService;
import com.ufcg.psoft.commerce.service.aluno.AlunoCadastrarTemaService;
import com.ufcg.psoft.commerce.service.aluno.AlunoListarProfessoresDisponiveisService;
import com.ufcg.psoft.commerce.service.aluno.AlunoSolicitarOrientacaoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
  value = "/aluno",
  produces = MediaType.APPLICATION_JSON_VALUE
)
public class AlunoController {

  @Autowired
  AlunoAtualizarAreasDeInteresseService alunoAtualizarAreasDeInteresseService;

  @Autowired
  AlunoListarProfessoresDisponiveisService alunoListarProfessoresDisponiveisService;

  @Autowired
  AlunoCadastrarTemaService alunoCadastrarTemaService;

  @Autowired
  AlunoSolicitarOrientacaoService alunoSolicitarOrientacaoService;

  @PutMapping("/{id}/atualizarAreasDeInteresse")
  public ResponseEntity<?> atualizarAreasDeInteresseAluno(
    @PathVariable Long id,
    @RequestBody List<AreaDeEstudoPostPutRequestDTO> areasDeEstudo) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(alunoAtualizarAreasDeInteresseService.atualizarAreasDeInteresse(id, areasDeEstudo));
  }

  @GetMapping("/{id}/professoresDisponiveis")
  public ResponseEntity<?> acessarProfessoresDisponiveisPorAluno(Long id) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(alunoListarProfessoresDisponiveisService.listarProfessoresDisponiveisPorAluno(id));
  }

  @PostMapping("/{id}/cadastrarTema")
  public ResponseEntity<?> cadastrarTemaTcc(
    @PathVariable Long id,
    @RequestBody @Valid TemaPostPutRequestDTO temaPostPutRequestDTO) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(alunoCadastrarTemaService.cadastrarTemaTcc(id, temaPostPutRequestDTO));
  }

  @PostMapping("/{id}/solicitarOrientacao")
  public ResponseEntity<?> solicitarOrientacao(SolicitacaoPostPutRequestDTO solicitacaoDTO) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(alunoSolicitarOrientacaoService.solicitarOrientacao(solicitacaoDTO));
   }
    
}
