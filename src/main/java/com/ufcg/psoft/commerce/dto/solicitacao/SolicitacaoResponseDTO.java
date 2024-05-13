package com.ufcg.psoft.commerce.dto.solicitacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.Solicitacao;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoResponseDTO {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @JsonProperty("aluno")
  @NotNull(message = "ID Aluno obrigatorio")
  private Long aluno;

  @JsonProperty("professor")
  @NotNull(message = "ID Professor obrigatorio")
  private Long professor;

  @JsonProperty("tema")
  @NotNull(message = "ID Tema obrigatorio")
  private Long tema;

  @JsonProperty("aceito")
  @NotNull(message = "Aceito obrigatorio")
  private Boolean aceito;

  public SolicitacaoResponseDTO(Solicitacao solicitacao) {
    this.id = solicitacao.getId();
    this.aluno = solicitacao.getAluno();
    this.professor = solicitacao.getProfessor();
    this.tema = solicitacao.getTema();
    this.aceito = solicitacao.getAceito();
  }
}