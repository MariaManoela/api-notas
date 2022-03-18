package com.example.apinotas.Controller;

import com.example.apinotas.Model.Aluno;
import com.example.apinotas.Service.AlunoService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlunoController.class)
class AlunoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlunoService alunoService;

    public static Aluno buidAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Maria");
        aluno.setAnoDoAluno(9);
        aluno.setNotaPrimeiroTrimestre(9f);
        aluno.setNotaSegundoTrimestre(8.7f);
        aluno.setNotaTerceiroTrimestre(8.9f);
        return aluno;
    }

   @Test
    void retornarSucessoNoCadastroDeAluno() {
       Aluno aluno = buidAluno();
       Aluno alunoSalvo = new Aluno(1L, "Maria", 9, 9f, 8.7f, 8.9f, 8.86f, true);
       when(alunoService.cadastraAluno(aluno)).thenReturn(alunoSalvo);

       Aluno alunoRetornado = alunoService.cadastraAluno(aluno);

       assertEquals(alunoSalvo, alunoRetornado);
       verify(alunoService).cadastraAluno(aluno);
   }
}
