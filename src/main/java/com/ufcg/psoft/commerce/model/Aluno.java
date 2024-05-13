package com.ufcg.psoft.commerce.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alunos")
public class Aluno {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @JsonProperty("nomeCompleto")
  @Column(nullable = false)
  private String nomeCompleto;
  
  @JsonProperty("matricula")
  @Column(nullable = false)
  private String matricula;

  @JsonProperty("email")
  @Column(nullable = false)
  private String email;

  @JsonProperty("periodoConclusao")
  @Column(nullable = false)
  private String periodoConclusao;

  @JsonProperty("alocado")
  @Column(nullable = false)
  private Boolean alocado;

  @JsonProperty("areasDeInteresse")
  @Column(nullable = false)
  @ManyToMany
  @JoinTable(
    name = "aluno_areaDeEstudo",
    joinColumns = @JoinColumn(name = "aluno_id"),
    inverseJoinColumns = @JoinColumn(name = "areaDeEstudo_id")
  )
  private List<AreaDeEstudo> areasDeInteresse;
  
}
