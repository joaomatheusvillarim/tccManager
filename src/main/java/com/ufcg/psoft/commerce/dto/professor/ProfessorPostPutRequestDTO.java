package com.ufcg.psoft.commerce.dto.professor;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorPostPutRequestDTO {

  @JsonProperty("nomeCompleto")
  @NotBlank(message = "Nome completo obrigatorio")
  private String nomeCompleto;

  @JsonProperty("email")
  @NotBlank(message = "Email obrigatorio")
  private String email;

  @JsonProperty("laboratorios")
  @NotNull(message = "Laboratorios obrigatorio")
  private List<String> laboratorios;

}
