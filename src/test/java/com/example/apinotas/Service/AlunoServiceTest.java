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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
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

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

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
    void retornarSucessoAoSalvarAlunoNoBanco() {
        Aluno aluno = buidAluno();
        Aluno alunoSalvo = new Aluno(1L, "Maria", 9, 9f, 8.7f, 8.9f, 8.86f, true);
        when(alunoRepository.save(aluno)).thenReturn(alunoSalvo);

        Aluno alunoRetornado = alunoService.cadastraAluno(aluno);

        assertEquals(alunoSalvo, alunoRetornado);
        verify(alunoRepository).save(aluno);
    }
}
