package com.ufcg.psoft.commerce.repository;

import com.ufcg.psoft.commerce.model.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

  Boolean existsByEmail(String email);

}
