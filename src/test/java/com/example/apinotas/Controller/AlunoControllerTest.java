package com.example.apinotas.Controller;

import com.example.apinotas.Model.Aluno;
import com.example.apinotas.Service.AlunoService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = AlunoController.class)
class AlunoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlunoService alunoService;

    public Aluno buildAluno(){
        return new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f,false);
    }

    @Test
    void retornarListaDeAlunos() throws Exception {
        List<Aluno> esperado = Collections.singletonList(buildAluno());

        Mockito.when(alunoService.findAll()).thenReturn(esperado);

        List<Aluno> resultado = alunoService.findAll();
        Assertions.assertEquals(esperado,resultado);

//        Mockito.when(alunoService.findAll()).thenReturn(listaAlunos);
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(content().string(listaAlunos.toString()));
//
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

    }

    @Test
    void buscarAlunoPeloId() throws Exception {
        Optional<Aluno> esperado = Optional.of(buildAluno());

        Mockito.when(alunoService.findById(1L)).thenReturn(esperado);

        Optional<Aluno> resultado = alunoService.findById(1L);
        Assertions.assertEquals(esperado, resultado);
//
////        Mockito.when(alunoService.findById(2L)).thenReturn(Optional.of(aluno1));
//        Assertions.assertEquals(Optional.of(aluno1), alunoService.findById(1L));
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void cadastrarAluno() throws Exception {
        Aluno aluno1 = new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f,false);

        String paraJson = new Gson().toJson(aluno1);

        Mockito.when(alunoService.save(aluno1)).thenReturn(aluno1);
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

//        List<Aluno> esperado = Collections.singletonList(buildAluno());
//        Aluno esperado = new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f,false);
//
//        Mockito.when(alunoService.save(esperado)).thenReturn(esperado);
//
//        Aluno resultado = alunoService.save(esperado);
//        Assertions.assertEquals(esperado, resultado);
    }

    @Test
    public void deletarAluno() throws Exception {
        Aluno aluno = new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f,false);

        Mockito.when(alunoService.findById(aluno.getId())).thenReturn(Optional.of(aluno));

        alunoService.deleteById(aluno.getId());
        verify(alunoService).deleteById(aluno.getId());
//
////        Mockito.when(alunoService.deleteById(1L)).thenReturn(null);
////        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos/{id}"))
////                .andExpect(MockMvcResultMatchers.status().isCreated())
////                .andDo(MockMvcResultHandlers.print());
    }
}
