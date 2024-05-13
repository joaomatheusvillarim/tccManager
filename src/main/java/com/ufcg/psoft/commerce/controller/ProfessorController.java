package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.professor.ProfessorPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.tema.TemaPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.professor.ProfessorAtualizarAreasDeOrientacaoQuotaService;
import com.ufcg.psoft.commerce.service.professor.ProfessorCadastrarTemaService;
import com.ufcg.psoft.commerce.service.professor.ProfessorListarSolicitacoesService;
import com.ufcg.psoft.commerce.service.professor.ProfessorListarTemasService;
import com.ufcg.psoft.commerce.service.professor.ProfessorResponderSolicitacaoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
  value = "/professor",
  produces = MediaType.APPLICATION_JSON_VALUE
)
@SuppressWarnings("unused")
public class ProfessorController {

  @Autowired
  ProfessorAtualizarAreasDeOrientacaoQuotaService professorAtualizarAreasDeOrientacaoService;

  @Autowired
  ProfessorCadastrarTemaService professorCadastrarTemaService;

  @Autowired
  ProfessorListarTemasService professorListarTemasService;

  @Autowired
  ProfessorListarSolicitacoesService professorListarSolicitacoesService;

  @Autowired
  ProfessorResponderSolicitacaoService professorResponderSolicitacaoService;

  @PutMapping("/{id}/atualizarAreasDeOrientacao")
  public ResponseEntity<?> atualizarAreasDeOrientacaoProfessor(
    @PathVariable Long id,
    @RequestBody List<AreaDeEstudoPostPutRequestDTO> areasDeEstudo) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(professorAtualizarAreasDeOrientacaoService.atualizarAreasDeOrientacao(id, areasDeEstudo));
  }

  @PutMapping("/{id}/atualizarQuota")
  public ResponseEntity<?> atualizarQuota(
    @PathVariable Long id,
    Integer quota) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(professorAtualizarAreasDeOrientacaoService.atualizarQuota(id, quota));
  }

  @PostMapping("/{id}/cadastrarTema")
  public ResponseEntity<?> cadastrarTemaTcc(
    @PathVariable Long id,
    @RequestBody @Valid TemaPostPutRequestDTO temaPostPutRequestDTO) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(professorCadastrarTemaService.cadastrarTemaTcc(id, temaPostPutRequestDTO));
  }

  @GetMapping("/temasAlunos")
  public ResponseEntity<?> listarTemasTccDeAlunos() {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(professorListarTemasService.listarTemasDeAlunos());
   }
    
  @GetMapping("/{id}/temas")
  public ResponseEntity<?> listarTemasTccPorProfessor(
    Long id) {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(professorListarTemasService.listarTemasPorProfessor(id));
  }

  @GetMapping("/{id}/solicitacoes")
  public ResponseEntity<?> listarSolicitacoesDeOrientacao(
    Long id) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(professorListarSolicitacoesService.listarSolicitacoesPorProfessor(id));
  }

  @PutMapping("/{id}/aceitarSolicitacao")
  public ResponseEntity<?> aceitarSolicitacao(
    Long id,
    Long solicitacao,
    String mensagem) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(professorResponderSolicitacaoService.aceitarSolicitacao(id, solicitacao, mensagem));
  }

  @PutMapping("/{id}/rejeitarSolicitacao")
  public ResponseEntity<?> rejeitarSolicitacao(
    Long id,
    Long solicitacao,
    String mensagem) {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(professorResponderSolicitacaoService.rejeitarSolicitacao(id, solicitacao, mensagem));
  }

}
