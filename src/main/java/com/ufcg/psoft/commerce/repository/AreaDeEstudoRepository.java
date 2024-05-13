package com.ufcg.psoft.commerce.repository;

import com.ufcg.psoft.commerce.model.AreaDeEstudo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaDeEstudoRepository extends JpaRepository<AreaDeEstudo, Long> {

  Optional<AreaDeEstudo> findByNome(String nome);

  Boolean existsByNome(String nome);
  
}
