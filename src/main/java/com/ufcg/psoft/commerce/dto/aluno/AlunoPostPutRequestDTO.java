package com.ufcg.psoft.commerce.dto.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoPostPutRequestDTO {

  @JsonProperty("nomeCompleto")
  @NotBlank(message = "Nome completo obrigatorio")
  private String nomeCompleto;

  @JsonProperty("matricula")
  @NotNull(message = "Matricula obrigatorio")
  @Pattern(regexp = "^\\d{9}$", message = "Matricula deve ter exatamente 9 digitos numericos")
  private String matricula;

  @JsonProperty("email")
  @NotBlank(message = "Email obrigatorio")
  private String email;

  @JsonProperty("periodoConclusao")
  @NotBlank(message = "Periodo de conclusao obrigatorio")
  private String periodoConclusao;

}
