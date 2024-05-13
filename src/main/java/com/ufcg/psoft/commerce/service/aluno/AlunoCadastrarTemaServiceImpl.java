package com.ufcg.psoft.commerce.service.aluno;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.tema.TemaPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.tema.TemaResponseDTO;
import com.ufcg.psoft.commerce.exception.AlunoNaoExisteException;
import com.ufcg.psoft.commerce.exception.TemaDadosInvalidosException;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.model.Status;
import com.ufcg.psoft.commerce.model.Tema;
import com.ufcg.psoft.commerce.repository.AlunoRepository;
import com.ufcg.psoft.commerce.repository.TemaRepository;
import com.ufcg.psoft.commerce.service.areaDeEstudo.AreaDeEstudoValidarService;

import java.util.List;

@Service
public class AlunoCadastrarTemaServiceImpl implements AlunoCadastrarTemaService {

  @Autowired
  AlunoRepository alunoRepository;

  @Autowired
  TemaRepository temaRepository;

  @Autowired
  AreaDeEstudoValidarService areaDeEstudoValidarService;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public TemaResponseDTO cadastrarTemaTcc(Long id, TemaPostPutRequestDTO temaDto) {
    validarAluno(id);
    validarDadosTema(temaDto);

    List<AreaDeEstudo> areasValidas = areaDeEstudoValidarService.validarAreasDeEstudo(temaDto.getAreasRelacionadas());

    Tema tema = Tema.builder()
      .titulo(temaDto.getTitulo())
      .descricao(temaDto.getDescricao())
      .areasRelacionadas(areasValidas)
      .autor(id)
      .tipoAutor("Aluno")
      .status(Status.NOVO)
      .build(); 
    temaRepository.save(tema);
    return new TemaResponseDTO(tema);
  }

  private void validarDadosTema(TemaPostPutRequestDTO temaDto) {
    if (temaRepository.existsByTitulo(temaDto.getTitulo())
      || temaDto.getAreasRelacionadas().size() == 0) {
      throw new TemaDadosInvalidosException();
    }
  }

  private void validarAluno(Long id) {
    if (! alunoRepository.existsById(id)) {
      throw new AlunoNaoExisteException();
    }
  }
  
}
