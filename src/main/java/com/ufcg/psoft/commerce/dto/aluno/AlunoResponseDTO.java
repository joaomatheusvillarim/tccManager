package com.ufcg.psoft.commerce.dto.aluno;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class AlunoResponseDTO {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

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

  @JsonProperty("areasDeInteresse")
  @NotNull(message = "Areas de interesse obrigatorio")
  private List<AreaDeEstudo> areasDeInteresse;

  @JsonProperty
  @NotNull(message = "Alocado obrigatorio")
  private Boolean alocado;

  public AlunoResponseDTO(Aluno aluno) {
    this.id = aluno.getId();
    this.nomeCompleto = aluno.getNomeCompleto();
    this.matricula = aluno.getMatricula();
    this.email = aluno.getEmail();
    this.periodoConclusao = aluno.getPeriodoConclusao();
    this.areasDeInteresse = aluno.getAreasDeInteresse();
    this.alocado = aluno.getAlocado();
  }
}
