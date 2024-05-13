package com.ufcg.psoft.commerce.service.aluno;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoResponseDTO;
import com.ufcg.psoft.commerce.exception.AlunoJaEstaAlocadoException;
import com.ufcg.psoft.commerce.exception.AlunoNaoExisteException;
import com.ufcg.psoft.commerce.exception.ProfessorNaoExisteException;
import com.ufcg.psoft.commerce.exception.SolicitacaoDadosInvalidosException;
import com.ufcg.psoft.commerce.exception.TemaNaoExisteException;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.Professor;
import com.ufcg.psoft.commerce.model.Solicitacao;
import com.ufcg.psoft.commerce.model.Tema;
import com.ufcg.psoft.commerce.repository.AlunoRepository;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;
import com.ufcg.psoft.commerce.repository.SolicitacaoRepository;
import com.ufcg.psoft.commerce.repository.TemaRepository;
import com.ufcg.psoft.commerce.service.professor.ProfessorNotificarNovaSolicitacaoService;

@Service
public class AlunoSolicitarOrientacaoServiceImpl implements AlunoSolicitarOrientacaoService {

  @Autowired
  SolicitacaoRepository solicitacaoRepository;

  @Autowired
  TemaRepository temaRepository;

  @Autowired
  ProfessorRepository professorRepository;

  @Autowired
  AlunoRepository alunoRepository;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  ProfessorNotificarNovaSolicitacaoService professorNotificarNovaSolicitacaoService;

  @Override
  public SolicitacaoResponseDTO solicitarOrientacao(SolicitacaoPostPutRequestDTO solicitacaoDto) {
    validarDadosSolicitacao(solicitacaoDto);
    
    Solicitacao solicitacao = modelMapper.map(solicitacaoDto, Solicitacao.class);
    solicitacao.setAceito(false);
    professorNotificarNovaSolicitacaoService.notificarProfessorNovaSolicitacao(solicitacao);

    solicitacaoRepository.save(solicitacao);
    return new SolicitacaoResponseDTO(solicitacao);
  }

  private void validarDadosSolicitacao(SolicitacaoPostPutRequestDTO solicitacaoDto) {
    Tema tema = temaRepository.findById(solicitacaoDto.getTema()).orElseThrow(TemaNaoExisteException::new);
    Professor professor = professorRepository.findById(solicitacaoDto.getProfessor()).orElseThrow(ProfessorNaoExisteException::new);
    Aluno aluno = alunoRepository.findById(solicitacaoDto.getAluno()).orElseThrow(AlunoNaoExisteException::new);

    if (aluno.getAlocado()) {
      throw new AlunoJaEstaAlocadoException();
     }

    if (tema.getTipoAutor().equals("Aluno")) {
      if (tema.getAutor() != aluno.getId()) {
        throw new SolicitacaoDadosInvalidosException();
      }
    } else if (tema.getTipoAutor().equals("Professor")) {
      if (tema.getAutor() != professor.getId()) {
        throw new SolicitacaoDadosInvalidosException();
      }
    }

  }
  
}
