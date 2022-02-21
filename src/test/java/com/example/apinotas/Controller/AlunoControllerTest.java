package com.example.apinotas.Controller;

import com.example.apinotas.Model.Aluno;
import com.example.apinotas.Service.AlunoService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@WebMvcTest(controllers = AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlunoService alunoService;

    @Test
    public void retornarListaDeAlunos() throws Exception {
        Aluno aluno1 = new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f,false);
        Aluno aluno2 = new Aluno(2L, "Pedro", 9, 8.4f, 9.2f, 8.7f, 8.76f,true);

        List<Aluno> listaAlunos = new ArrayList<Aluno>();
        listaAlunos.add(aluno1);
        listaAlunos.add(aluno2);

        Mockito.when(alunoService.findAll()).thenReturn(listaAlunos);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

//        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
//
    }

    @Test
    public void buscarAlunoPeloId() throws Exception {
        Aluno aluno1 = new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f,false);
        Aluno aluno2 = new Aluno(2L, "Pedro", 9, 8.4f, 9.2f, 8.7f, 8.76f,true);

        List<Aluno> listaAlunos = new ArrayList<Aluno>();
        listaAlunos.add(aluno1);
        listaAlunos.add(aluno2);

        Mockito.when(alunoService.findById(1L)).thenReturn(aluno1);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos/{id}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void cadastrarAluno() throws Exception{
        Aluno aluno1 = new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f,false);

        String paraJson = new Gson().toJson(aluno1);

        Mockito.when(alunoService.save(aluno1)).thenReturn(aluno1);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deletarAluno() throws Exception {
        Aluno aluno1 = new Aluno(1L, "Maria", 7, 6.4f, 7.2f, 6.7f, 6.76f,false);
        Aluno aluno2 = new Aluno(2L, "Pedro", 9, 8.4f, 9.2f, 8.7f, 8.76f,true);

        List<Aluno> listaAlunos = new ArrayList<Aluno>();
        listaAlunos.add(aluno1);
        listaAlunos.add(aluno2);

        Mockito.when(alunoService.deleteById(1L)).thenReturn(null);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos/{id}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
}
