package com.ufcg.psoft.commerce.dto.areaDeEstudo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaDeEstudoPostPutRequestDTO {

  @JsonProperty("nome")
  @NotBlank(message = "Nome obrigatorio")
  private String nome;

}
