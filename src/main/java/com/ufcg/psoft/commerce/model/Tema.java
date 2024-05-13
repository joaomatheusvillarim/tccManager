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
@Table(name = "temas")
public class Tema {

  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @JsonProperty("titulo")
  @Column(nullable = false)
  private String titulo;

  @JsonProperty("descricao")
  @Column(nullable = false)
  private String descricao;

  @JsonProperty("autor")
  @Column(nullable = false)
  private Long autor;

  @JsonProperty("tipoAutor")
  @Column(nullable = false)
  private String tipoAutor;

  @JsonProperty("status")
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Basic(fetch = FetchType.EAGER)
  private Status status;

  @JsonProperty("areasRelacionadas")
  @Column(nullable = false)
  @ManyToMany
  @JoinTable(
    name = "tema_areaDeEstudo",
    joinColumns = @JoinColumn(name = "tema_id"),
    inverseJoinColumns = @JoinColumn(name = "areaDeEstudo_id")
  )
  private List<AreaDeEstudo> areasRelacionadas;
  
}
