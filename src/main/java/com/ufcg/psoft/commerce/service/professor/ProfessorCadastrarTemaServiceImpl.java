package com.ufcg.psoft.commerce.service.professor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.tema.TemaPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.tema.TemaResponseDTO;
import com.ufcg.psoft.commerce.exception.ProfessorNaoExisteException;
import com.ufcg.psoft.commerce.exception.TemaDadosInvalidosException;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.model.Status;
import com.ufcg.psoft.commerce.model.Tema;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;
import com.ufcg.psoft.commerce.repository.TemaRepository;
import com.ufcg.psoft.commerce.service.aluno.AlunoNotificarNovoTemaRespostaService;
import com.ufcg.psoft.commerce.service.areaDeEstudo.AreaDeEstudoValidarService;

@Service
public class ProfessorCadastrarTemaServiceImpl implements ProfessorCadastrarTemaService {

  @Autowired
  AlunoNotificarNovoTemaRespostaService alunoNotificarNovoTemaService;

  @Autowired
  ProfessorRepository professorRepository;

  @Autowired
  TemaRepository temaRepository;

  @Autowired
  AreaDeEstudoValidarService areaDeEstudoValidarService;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public TemaResponseDTO cadastrarTemaTcc(Long id, TemaPostPutRequestDTO temaDto) {
    validarProfessor(id);
    validarDadosTema(temaDto);

    List<AreaDeEstudo> areasValidas = areaDeEstudoValidarService.validarAreasDeEstudo(temaDto.getAreasRelacionadas());
    alunoNotificarNovoTemaService.notificarNovoTema(areasValidas);

    Tema tema = Tema.builder()
      .titulo(temaDto.getTitulo())
      .descricao(temaDto.getDescricao())
      .areasRelacionadas(areasValidas)
      .autor(id)
      .tipoAutor("Professor")
      .status(Status.NOVO)
      .build(); 
    temaRepository.save(tema);
    return new TemaResponseDTO(tema);
  }

  private void validarProfessor(Long id) {
    if (! professorRepository.existsById(id)) {
      throw new ProfessorNaoExisteException();
    }
  }

  private void validarDadosTema(TemaPostPutRequestDTO temaDto) {
    if (temaRepository.existsByTitulo(temaDto.getTitulo())) {
      throw new TemaDadosInvalidosException();
    }
  }
  
}
