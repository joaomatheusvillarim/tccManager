package com.ufcg.psoft.commerce.dto.tema;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.model.Tema;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class TemaResponseDTO {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @JsonProperty("titulo")
  @NotBlank(message = "Titulo obrigatorio")
  private String titulo;

  @JsonProperty("descricao")
  @NotBlank(message = "Descricao obrigatorio")
  private String descricao;

  @JsonProperty("areasRelacionadas")
  @NotNull(message = "Areas relacionadas obrigatorio")
  private List<AreaDeEstudo> areasRelacionadas;

  @JsonProperty("autor")
  @NotBlank(message = "Autor obrigatorio")
  private Long autor;

  @JsonProperty("tipoAutor")
  @NotBlank(message = "Tipo autor obrigatorio")
  private String tipoAutor;

  @JsonProperty("status")
  @NotBlank(message = "Status obrigatorio")
  private String status;
  
  public TemaResponseDTO(Tema tema) {
    this.id = tema.getId();
    this.titulo = tema.getTitulo();
    this.descricao = tema.getDescricao();
    this.areasRelacionadas = tema.getAreasRelacionadas();
    this.autor = tema.getAutor();
    this.tipoAutor = tema.getTipoAutor();
    this.status = tema.getStatus().getValue();
  }
}