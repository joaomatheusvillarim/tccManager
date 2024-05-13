package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professores")
public class Professor {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @JsonProperty("nomeCompleto")
  @Column(nullable = false)
  private String nomeCompleto;

  @JsonProperty("email")
  @Column(nullable = false)
  private String email;

  @JsonProperty("laboratorios")
  @Column(nullable = false)
  private List<String> laboratorios;

  @JsonProperty("areasDeOrientacao")
  @Column(nullable = false)
  @ManyToMany
  @JoinTable(
    name = "professor_areaDeEstudo",
    joinColumns = @JoinColumn(name = "professor_id"),
    inverseJoinColumns = @JoinColumn(name = "areaDeEstudo_id")
  )
  private List<AreaDeEstudo> areasDeOrientacao;

  @JsonProperty("quota")
  @Column(nullable = false)
  @PositiveOrZero
  private Integer quota;
  
}
