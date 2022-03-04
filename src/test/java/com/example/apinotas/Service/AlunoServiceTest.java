package com.example.apinotas.Service;

import com.example.apinotas.Model.Aluno;
import com.example.apinotas.Repository.AlunoRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    public Aluno buildAluno() {
        return new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f, false);
    }

    @Test
    void retornarListaDeTodosAlunos() {
        List<Aluno> esperado = Collections.singletonList(buildAluno());

        Mockito.when(alunoRepository.findAll()).thenReturn(esperado);

        List<Aluno> resultado = alunoRepository.findAll();
        Assertions.assertEquals(esperado, resultado);
//        List<Aluno> lista = new ArrayList<>();
//        lista.add(new Aluno(1L, "Manoela", 9, 8.4f, 9.2f, 8.7f, 8.76f,true));
//        lista.add(new Aluno(2L, "Camila", 8, 6.8f, 7.5f, 6.9f, 7.06f,true));
//
//        when(alunoRepository.findAll()).thenReturn(lista);
//        assertEquals(alunoService.findAll(), lista);
//        verify(alunoRepository).findAll();
    }

    @Test
    void retornarAlunoBuscandoPeloId() {
        Optional<Aluno> esperado = Optional.of(buildAluno());

        Mockito.when(alunoRepository.findById(1L)).thenReturn(esperado);

        Optional<Aluno> resultado = alunoRepository.findById(1L);
        Assertions.assertEquals(esperado, resultado);

//        List<Aluno> lista = new ArrayList<>();
//        Aluno aluno1 = new Aluno(1L, "Manoela", 9, 8.4f, 9.2f, 8.7f, 8.76f,true);
//
//        lista.add(aluno1);
//        lista.add(new Aluno(2L, "Camila", 8, 6.8f, 7.5f, 6.9f, 7.06f,true));
//
//        when(alunoRepository.findById(2L)).thenReturn(java.util.Optional.of(aluno1));
//        assertEquals(alunoService.findById(2L), aluno1);
//        verify(alunoRepository).findById(2L);
    }

    @Test
    void cadastrarAluno() throws Exception {
        Aluno aluno1 = new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f, false);

        String paraJson = new Gson().toJson(aluno1);

        Mockito.when(alunoRepository.save(aluno1)).thenReturn(aluno1);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(paraJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.id", is(1L)))
                .andExpect((ResultMatcher) jsonPath("$.nome", is("Maria")))
                .andExpect((ResultMatcher) jsonPath("$.anoDoAluno", is(7)))
                .andExpect((ResultMatcher) jsonPath("$.notaPrimeiroTrimestre", is(6.4f)))
                .andExpect((ResultMatcher) jsonPath("$.notaSegundoTrimestre", is(7.2f)))
                .andExpect((ResultMatcher) jsonPath("$.notaTerceiroTrimestre", is(6.7f)))
                .andExpect((ResultMatcher) jsonPath("$.notaFinal", is(6.76f)))
                .andExpect((ResultMatcher) jsonPath("$.aprovado", is(false)))
                .andDo(MockMvcResultHandlers.print());

        verify(alunoService).save(aluno1);
    }

//    @Test
//    public void deletarAluno() {
//        Aluno aluno = new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f, false);
//
//        Mockito.when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
//
//        alunoRepository.deleteById(aluno.getId());
//        verify(alunoRepository).deleteById(aluno.getId());
//    }
}
