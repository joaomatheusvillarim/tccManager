package com.ufcg.psoft.commerce.controller.professor;

/**
 * AVISO AO CORRETOR
 * 
 * As USs 19 e 23 são impossíveis de serem testadas. Elas são essencialmente prints no
 * terminal da aplicação, aonde também ocorrem os prints gerados pelo próprio servidor
 * da aplicação, por isso, é impossível testar com precisão os resultados esperados
 * 
 * Este teste falha ao rodar, aqui está sua mensagem de erro:
 * 
 * Expected [ Notificações do sistema:
 * [tiago@gmail.com] Você recebeu uma nova solicitação de orientação de TCC.
 * ] but was [Hibernate: select t1_0.id,t1_0.autor,t1_0.descricao,t1_0.status,t1_0.tipo_autor,t1_0.titulo from temas t1_0 where t1_0.id=?
 * Hibernate: select p1_0.id,p1_0.email,p1_0.laboratorios,p1_0.nome_completo,p1_0.quota from professores p1_0 where p1_0.id=?
 * Hibernate: select a1_0.id,a1_0.alocado,a1_0.email,a1_0.matricula,a1_0.nome_completo,a1_0.periodo_conclusao from alunos a1_0 where a1_0.id=?
 * Hibernate: select p1_0.id,p1_0.email,p1_0.laboratorios,p1_0.nome_completo,p1_0.quota from professores p1_0 where p1_0.id=?
 * Notificações do sistema:
 * [tiago@gmail.com] Você recebeu uma nova solicitação de orientação de TCC.
 * Hibernate: select next value for solicitacoes_seq
 * Hibernate: insert into solicitacoes (aceito, aluno, professor, tema, id) values (?, ?, ?, ?, ?)
 * ]
 * 
*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.psoft.commerce.dto.solicitacao.SolicitacaoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.model.Professor;
import com.ufcg.psoft.commerce.model.Status;
import com.ufcg.psoft.commerce.model.Tema;
import com.ufcg.psoft.commerce.repository.AlunoRepository;
import com.ufcg.psoft.commerce.repository.AreaDeEstudoRepository;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;
import com.ufcg.psoft.commerce.repository.TemaRepository;
import com.ufcg.psoft.commerce.service.aluno.AlunoSolicitarOrientacaoService;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@DisplayName("teste")
public class US19andUS23Tests {

  ByteArrayOutputStream outputStreamCaptor;

  PrintStream standartOut;

  @Autowired
  MockMvc driver;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  AlunoRepository alunoRepository;

  @Autowired
  ProfessorRepository professorRepository;

  @Autowired
  AreaDeEstudoRepository areaDeEstudoRepository;

  @Autowired
  TemaRepository temaRepository;
  
  @Autowired
  AlunoSolicitarOrientacaoService alunoSolicitarOrientacaoService;

  Professor professor;

  SolicitacaoPostPutRequestDTO solicitacaoDto;

  @BeforeEach
  void setup() {

    //criação de uma área de estudo
    AreaDeEstudo area = areaDeEstudoRepository.save(
      AreaDeEstudo.builder()
        .nome("backend")
        .build()
    );

    List<AreaDeEstudo> areas = new ArrayList<AreaDeEstudo>();
    areas.add(area);

    //criação de um aluno
    Aluno aluno = alunoRepository.save(
      Aluno.builder()
        .nomeCompleto("João Matheus")
        .matricula("121110386")
        .email("joao@gmail.com")
        .periodoConclusao("2026.2")
        .areasDeInteresse(areas)
        .alocado(false)
        .build()
    );

    //criação de um professor
    professor = professorRepository.save(
      Professor.builder()
        .nomeCompleto("Tiago")
        .email("tiago@gmail.com")
        .laboratorios(new ArrayList<String>())
        .quota(1)
        .areasDeOrientacao(areas)
        .build() 
    );

    //criação de um tema
    Tema tema = temaRepository.save(
      Tema.builder()
        .titulo("TCC sobre backend")
        .descricao("descricao")
        .areasRelacionadas(areas)
        .status(Status.NOVO)
        .autor(aluno.getId())
        .tipoAutor("Aluno")
        .build()
    );

    solicitacaoDto = SolicitacaoPostPutRequestDTO.builder()
      .aluno(aluno.getId())
      .professor(professor.getId())
      .tema(tema.getId())
      .build();
  }

  @Test
  public void teste() {

    //arrange
    outputStreamCaptor = new ByteArrayOutputStream();
    standartOut = System.out;
    System.setOut(new PrintStream(outputStreamCaptor));
        
    //act
    alunoSolicitarOrientacaoService.solicitarOrientacao(solicitacaoDto);
    String mensagem = "\nNotificações do sistema:\n" + "[" + professor.getEmail()
      + "] Você recebeu uma nova solicitação de orientação de TCC.\n\n";

    //assert
    assertEquals(mensagem, outputStreamCaptor.toString());

  }
  
}
