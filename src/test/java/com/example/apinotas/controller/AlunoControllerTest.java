package com.example.apinotas.controller;

import com.example.apinotas.model.Aluno;
import com.example.apinotas.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
