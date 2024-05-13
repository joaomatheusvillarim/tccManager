package com.ufcg.psoft.commerce.dto.solicitacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoPostPutRequestDTO {
  
  @JsonProperty("aluno")
  @NotNull(message = "ID Aluno obrigatorio")
  private Long aluno;

  @JsonProperty("professor")
  @NotNull(message = "ID Professor obrigatorio")
  private Long professor;

  @JsonProperty("tema")
  @NotNull(message = "ID Tema obrigatorio")
  private Long tema;

}