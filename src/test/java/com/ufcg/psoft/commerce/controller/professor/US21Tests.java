package com.ufcg.psoft.commerce.controller.professor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufcg.psoft.commerce.model.*;
import com.ufcg.psoft.commerce.repository.AlunoRepository;
import com.ufcg.psoft.commerce.repository.AreaDeEstudoRepository;
import com.ufcg.psoft.commerce.repository.ProfessorRepository;
import com.ufcg.psoft.commerce.repository.SolicitacaoRepository;
import com.ufcg.psoft.commerce.repository.TemaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes da US21")
public class US21Tests {

    final String URI_PROFESSORES = "/professor";

    @Autowired
    MockMvc driver;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    AreaDeEstudoRepository areaDeEstudoRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    SolicitacaoRepository solicitacaoRepository;

    @Autowired
    TemaRepository temaRepository;

    Professor professor;

    Aluno aluno;

    Tema temaProfessor;

    Solicitacao solicitacao1;

    @BeforeEach
    void setup(){

        //criacao de uma area de estudo
        AreaDeEstudo areaDeEstudo = areaDeEstudoRepository.save(
                AreaDeEstudo.builder()
                        .nome("backend")
                        .build()
        );

        ArrayList<AreaDeEstudo> areas = new ArrayList<AreaDeEstudo>();
        areas.add(areaDeEstudo);

        //criacao de um aluno
        aluno = alunoRepository.save(
                Aluno.builder()
                        .nomeCompleto("Joao Matheus")
                        .matricula("121110386")
                        .email("joao@gmail.com")
                        .periodoConclusao("2026.2")
                        .areasDeInteresse(areas)
                        .alocado(false)
                        .build()
        );

        //criacao de um professor
        professor = professorRepository.save(
                Professor.builder()
                        .nomeCompleto("Adalberto")
                        .email("adalberto@gmail.com")
                        .laboratorios(new ArrayList<String>())
                        .quota(1)
                        .areasDeOrientacao(areas)
                        .build()
        );

        //criacao de um tema de aluno
        temaProfessor = temaRepository.save(
                Tema.builder()
                        .titulo("TCC de Joao Matheus")
                        .descricao("Descricao")
                        .areasRelacionadas(areas)
                        .autor(professor.getId())
                        .tipoAutor("Professor")
                        .status(Status.NOVO)
                        .build()
        );

        //criacao de uma solicitacao com tema de Professor
        solicitacao1 = solicitacaoRepository.save(
                Solicitacao.builder()
                        .aluno(aluno.getId())
                        .professor(professor.getId())
                        .tema(temaProfessor.getId())
                        .aceito(false)
                        .build()
        );

    }

    @AfterEach
    void tearDown(){
        professorRepository.deleteAll();
        alunoRepository.deleteAll();
        temaRepository.deleteAll();
        solicitacaoRepository.deleteAll();
        areaDeEstudoRepository.deleteAll();
    }

    @Nested
    @DisplayName("Teste de Solicitacao")
    class SolicitacaoVerificaStatus{

        @Test
        @DisplayName("Quando rejeitamos uma solicitacao")
        void quandoRejeitaUmaSolicitacao() throws Exception {
            //Arrange

            //Act
            String responseJsonString = driver.perform(put(URI_PROFESSORES + "/" + professor.getId() + "/rejeitarSolicitacao")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("id", professor.getId().toString())
                            .param("solicitacao", solicitacao1.getId().toString()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Solicitacao resultado = objectMapper.readValue(responseJsonString, Solicitacao.SolicitacaoBuilder.class).build();

            //Assert
            assertEquals(resultado.getAceito(), false);
        }
    }
}
