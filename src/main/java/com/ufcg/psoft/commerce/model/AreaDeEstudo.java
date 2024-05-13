package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
@Table(name = "areas-de-estudo")
public class AreaDeEstudo {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @JsonProperty("nome")
  @Column(nullable = false)
  private String nome;

  @JsonIgnore
  @ManyToMany(mappedBy = "areasDeInteresse")
  private List<Aluno> alunos;

  @JsonIgnore
  @ManyToMany(mappedBy = "areasDeOrientacao")
  private List<Professor> professores;

  @JsonIgnore
  @ManyToMany(mappedBy = "areasRelacionadas")
  private List<Tema> temas;
  
}
