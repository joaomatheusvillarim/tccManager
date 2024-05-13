package com.ufcg.psoft.commerce.dto.professor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.model.Professor;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class ProfessorResponseDTO {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @JsonProperty("nomeCompleto")
  @NotBlank(message = "Nome completo obrigatorio")
  private String nomeCompleto;

  @JsonProperty("email")
  @NotBlank(message = "Email obrigatorio")
  private String email;

  @JsonProperty("laboratorios")
  @NotNull(message = "Laboratorios obrigatorio")
  private List<String> laboratorios;

  @JsonProperty("areasDeOrientacao")
  @NotNull(message = "Areas de orientacao obrigatorio")
  private List<AreaDeEstudo> areasDeOrientacao;

  @JsonProperty("quota")
  @NotNull(message = "Quota obrigatorio")
  private Integer quota;

  public ProfessorResponseDTO(Professor professor) {
    this.id = professor.getId();
    this.nomeCompleto = professor.getNomeCompleto();
    this.email = professor.getEmail();
    this.laboratorios = professor.getLaboratorios();
    this.areasDeOrientacao = professor.getAreasDeOrientacao();
    this.quota = professor.getQuota();
  }
}
