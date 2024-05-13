package com.ufcg.psoft.commerce.repository;

import com.ufcg.psoft.commerce.model.Solicitacao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
  
  List<Solicitacao> findByProfessor(Long professor);

  Optional<Solicitacao> findByAceitoAndAluno(Boolean aceito, Long aluno);

}