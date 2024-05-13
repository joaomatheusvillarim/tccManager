package com.ufcg.psoft.commerce.model;

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
@Table(name = "solicitacoes")
public class Solicitacao {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @JsonProperty("aluno")
  @Column(nullable = false)
  private Long aluno;

  @JsonProperty("professor")
  @Column(nullable = false)
  private Long professor;

  @JsonProperty("tema")
  @Column(nullable = false)
  private Long tema;

  @JsonProperty("aceito")
  @Column(nullable = false)
  private Boolean aceito;
  
}