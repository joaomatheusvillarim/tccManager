package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.aluno.AlunoPostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.aluno.AlunoResponseDTO;
import com.ufcg.psoft.commerce.exception.CustomErrorType;
import com.ufcg.psoft.commerce.model.Aluno;
import com.ufcg.psoft.commerce.model.AreaDeEstudo;
import com.ufcg.psoft.commerce.repository.AlunoRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@DisplayName("Testes do controlador de Alunos")
public class AlunoControllerTests {

  final String URI_ALUNOS = "/coordenador/aluno";

  @Autowired
  MockMvc driver;

  @Autowired
  AlunoRepository alunoRepository;

  ObjectMapper objectMapper = new ObjectMapper();

  Aluno aluno;

  AlunoPostPutRequestDTO alunoPostPutRequestDTO;

  @BeforeEach
  void setup() {
    objectMapper.registerModule(new JavaTimeModule());

    aluno = alunoRepository.save(Aluno.builder()
      .nomeCompleto("Gabriel Barbosa Almeida")
      .matricula("121110123")
      .email("gabriel.barbosa@ccc.ufcg.edu.br")
      .periodoConclusao("2025.2")
      .alocado(false)
      .areasDeInteresse(new ArrayList<AreaDeEstudo>())
      .build()
    );

    alunoPostPutRequestDTO = AlunoPostPutRequestDTO.builder()
      .nomeCompleto(aluno.getNomeCompleto())
      .matricula(aluno.getMatricula())
      .email(aluno.getEmail())
      .periodoConclusao(aluno.getPeriodoConclusao())
      .build();
  }

  @AfterEach
  void tearDown() {
    alunoRepository.deleteAll();
  }

  @Nested
  @DisplayName("Conjunto de casos de verificação de nome")
  class AlunoVerificacaoNome {

    @Test
    @DisplayName("Quando alteramos o nome do aluno com dados válidos")
    void quandoAlteramosNomeDoAlunoValido() throws Exception {
      // Arrange
      alunoPostPutRequestDTO.setNomeCompleto("Gabigol Barbosa Almeida");

      // Act
      String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .param("matricula", "121110123")
          .param("email", "gabriel.barbosa@ccc.ufcg.edu.br")
          .param("periodoConclusao", "2025.2")
          .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      Aluno resultado = objectMapper.readValue(responseJsonString, Aluno.AlunoBuilder.class).build();

      // Assert
      assertEquals("Gabigol Barbosa Almeida", resultado.getNomeCompleto());
    }

    @Test
    @DisplayName("Quando alteramos o nome do aluno nulo")
    void quandoAlteramosNomeDoAlunoNulo() throws Exception {
      // Arrange
      alunoPostPutRequestDTO.setNomeCompleto(null);

      // Act
      String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

      // Assert
      assertAll(
        () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
        () -> assertEquals("Nome completo obrigatorio", resultado.getErrors().get(0))
      );
    }

    @Test
    @DisplayName("Quando alteramos o nome do aluno vazio")
    void quandoAlteramosNomeDoAlunoVazio() throws Exception {
      // Arrange
      alunoPostPutRequestDTO.setNomeCompleto("");

      // Act
      String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
        .andExpect(status().isBadRequest())
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

      // Assert
      assertAll(
        () -> assertEquals("Erros de validacao encontrados", resultado.getMessage()),
        () -> assertEquals("Nome completo obrigatorio", resultado.getErrors().get(0))
      );
    }

  }

  @Nested
  @DisplayName("Conjunto de casos de verificação dos fluxos básicos API Rest")
  class AlunoVerificacaoFluxosBasicosApiRest {

    @Test
    @DisplayName("Quando buscamos por todos alunos salvos")
    void quandoBuscamosPorTodosAlunosSalvos() throws Exception {
      // Arrange
      // Vamos ter 3 alunos no banco
      Aluno aluno1 = Aluno.builder()
        .nomeCompleto("Bruno Henrique Pinto")
        .matricula("120110666")
        .email("bruno.henrique.pinto@ccc.ufcg.edu.br")
        .periodoConclusao("2024.2")
        .alocado(false)
        .areasDeInteresse(new ArrayList<AreaDeEstudo>())
        .build();
      Aluno aluno2 = Aluno.builder()
        .nomeCompleto("Giorgian Daniel De Arrascaeta Benedetti")
        .matricula("122110123")
        .email("giorgian.de.arrascaeta@ccc.ufcg.edu.br")
        .periodoConclusao("2026.2")
        .alocado(false)
        .areasDeInteresse(new ArrayList<AreaDeEstudo>())
        .build();
      alunoRepository.saveAll(Arrays.asList(aluno1, aluno2));

      // Act
      String responseJsonString = driver.perform(get(URI_ALUNOS)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
        .andExpect(status().isOk()) // Codigo 200
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      List<Aluno> resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {
      });

      // Assert
      assertAll(
        () -> assertEquals(3, resultado.size())
      );
    }

    @Test
    @DisplayName("Quando buscamos um aluno salvo pelo id")
    void quandoBuscamosPorUmAlunoSalvo() throws Exception {
      // Arrange
      // nenhuma necessidade além do setup()

      // Act
      String responseJsonString = driver.perform(get(URI_ALUNOS + "/" + aluno.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
        .andExpect(status().isOk()) // Codigo 200
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      AlunoResponseDTO resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {});

      // Assert
      assertAll(
        () -> assertEquals(aluno.getId().longValue(), resultado.getId().longValue()),
        () -> assertEquals(aluno.getNomeCompleto(), resultado.getNomeCompleto())
      );
    }

    @Test
    @DisplayName("Quando buscamos um aluno inexistente")
    void quandoBuscamosPorUmAlunoInexistente() throws Exception {
      // Arrange
      // nenhuma necessidade além do setup()

      // Act
      String responseJsonString = driver.perform(get(URI_ALUNOS + "/" + 999999999)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
        .andExpect(status().isBadRequest()) // Codigo 400
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

      // Assert
      assertAll(
        () -> assertEquals("O aluno consultado nao existe!", resultado.getMessage())
      );
    }

    @Test
    @DisplayName("Quando criamos um novo aluno com dados válidos")
    void quandoCriarAlunoValido() throws Exception {
      // Arrange
      // nenhuma necessidade além do setup()

      // Act
      String responseJsonString = driver.perform(post(URI_ALUNOS)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
        .andExpect(status().isCreated()) // Codigo 201
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      Aluno resultado = objectMapper.readValue(responseJsonString, Aluno.AlunoBuilder.class).build();

      // Assert
      assertAll(
        () -> assertNotNull(resultado.getId()),
        () -> assertEquals(alunoPostPutRequestDTO.getNomeCompleto(), resultado.getNomeCompleto())
      );

    }

    @Test
    @DisplayName("Quando alteramos o aluno com dados válidos")
    void quandoAlteramosAlunoValido() throws Exception {
      // Arrange
      Long alunoId = aluno.getId();

      // Act
      String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + aluno.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
        .andExpect(status().isOk()) // Codigo 200
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

        Aluno resultado = objectMapper.readValue(responseJsonString, Aluno.AlunoBuilder.class).build();

        // Assert
        assertAll(
          () -> assertEquals(resultado.getId().longValue(), alunoId),
          () -> assertEquals(alunoPostPutRequestDTO.getNomeCompleto(), resultado.getNomeCompleto())
        );
    }

    @Test
    @DisplayName("Quando alteramos o aluno inexistente")
    void quandoAlteramosAlunoInexistente() throws Exception {
      // Arrange
      // nenhuma necessidade além do setup()

      // Act
      String responseJsonString = driver.perform(put(URI_ALUNOS + "/" + 99999L)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(alunoPostPutRequestDTO)))
        .andExpect(status().isBadRequest()) // Codigo 400
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

      // Assert
      assertAll(
        () -> assertEquals("O aluno consultado nao existe!", resultado.getMessage())
      );
    }

    @Test
    @DisplayName("Quando excluímos um aluno salvo")
    void quandoExcluimosAlunoValido() throws Exception {
      // Arrange
      // nenhuma necessidade além do setup()

      // Act
      String responseJsonString = driver.perform(delete(URI_ALUNOS + "/" + aluno.getId())
          .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent()) // Codigo 204
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      // Assert
      assertTrue(responseJsonString.isBlank());
    }

    @Test
    @DisplayName("Quando excluímos um aluno inexistente")
    void quandoExcluimosAlunoInexistente() throws Exception {
      // Arrange
      // nenhuma necessidade além do setup()

      // Act
      String responseJsonString = driver.perform(delete(URI_ALUNOS + "/" + 999999)
          .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest()) // Codigo 400
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

      CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

      // Assert
      assertAll(
        () -> assertEquals("O aluno consultado nao existe!", resultado.getMessage())
      );
    }

  }

}
