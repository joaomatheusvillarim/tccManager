package com.ufcg.psoft.commerce.repository;

import com.ufcg.psoft.commerce.model.Aluno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

  Boolean existsByMatricula(String matricula);

  Boolean existsByEmail(String email);

  List<Aluno> findByAlocado(Boolean alocado);

}
