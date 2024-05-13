package com.ufcg.psoft.commerce.dto.areaDeEstudo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaDeEstudoResponseDTO {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @JsonProperty("nome")
  @NotBlank(message = "Nome obrigatorio")
  private String nome;

  public AreaDeEstudoResponseDTO(AreaDeEstudo areaDeEstudo) {
    this.id = areaDeEstudo.getId();
    this.nome = areaDeEstudo.getNome();
  }
}
