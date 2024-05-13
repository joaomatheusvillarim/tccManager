package com.ufcg.psoft.commerce.dto.tema;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.dto.areaDeEstudo.AreaDeEstudoPostPutRequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemaPostPutRequestDTO {

  @JsonProperty("titulo")
  @NotBlank(message = "Titulo obrigatorio")
  private String titulo;

  @JsonProperty("descricao")
  @NotBlank(message = "Descricao obrigatorio")
  private String descricao;

  @JsonProperty("areasRelacionadas")
  @NotNull(message = "Areas relacionadas obrigatorio")
  private List<AreaDeEstudoPostPutRequestDTO> areasRelacionadas;

}