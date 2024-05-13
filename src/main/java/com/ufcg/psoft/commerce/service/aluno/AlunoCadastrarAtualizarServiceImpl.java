package com.ufcg.psoft.commerce.service.aluno;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.commerce.dto.aluno.AlunoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.aluno.AlunoResponseDTO;
import com.ufcg.psoft.commerce.exception.AlunoDadosInvalidosException;
import com.ufcg.psoft.commerce.exception.AlunoNaoExisteException;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.repository.AlunoRepository;

@Service
public class AlunoCadastrarAtualizarServiceImpl implements AlunoCadastrarAtualizarService {

  @Autowired
  AlunoRepository alunoRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public AlunoResponseDTO cadastrarAluno(AlunoPostPutRequestDTO alunoDto) {
    validarDadosAluno(alunoDto);
    Aluno aluno = modelMapper.map(alunoDto, Aluno.class);
    
    aluno.setAreasDeInteresse(new ArrayList<AreaDeEstudo>());
    aluno.setAlocado(false);

    alunoRepository.save(aluno);
    return modelMapper.map(aluno, AlunoResponseDTO.class);
  }

  @Override
  public AlunoResponseDTO atualizarAluno(Long id, AlunoPostPutRequestDTO alunoDto) {
    Aluno aluno = alunoRepository.findById(id).orElseThrow(AlunoNaoExisteException::new);
    
    alunoRepository.deleteById(id);
    validarDadosAluno(alunoDto);
    
    modelMapper.map(alunoDto, aluno);
    alunoRepository.save(aluno);
    return modelMapper.map(aluno, AlunoResponseDTO.class);
  }

  private void validarDadosAluno(AlunoPostPutRequestDTO aluno) {
    if (alunoRepository.existsByMatricula(aluno.getMatricula())
      || alunoRepository.existsByEmail(aluno.getEmail())) {
      throw new AlunoDadosInvalidosException();
    }
  }
  
}
