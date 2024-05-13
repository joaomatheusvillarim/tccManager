package com.ufcg.psoft.commerce.repository;

import com.ufcg.psoft.commerce.model.Tema;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {

  List<Tema> findByTipoAutor(String tipoAutor);

  List<Tema> findByTipoAutorAndAutor(String tipoAutor, Long autor);

  Boolean existsByTitulo(String titulo);

}
