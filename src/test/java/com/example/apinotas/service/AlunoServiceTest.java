package com.example.apinotas.service;

import com.example.apinotas.model.Aluno;
import com.example.apinotas.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void retornarAlunoPeloId() {
        Aluno alunoSalvo = new Aluno(1L, "Maria", 9, 9f, 8.7f, 8.9f, 8.86f, true);
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(alunoSalvo));

        Optional<Aluno> alunoRetornado = alunoService.findById(1L);

        assertEquals(Optional.of(alunoSalvo), alunoRetornado);
        verify(alunoRepository).findById(1L);
    }

    @Test
    void retornarListaDeAlunos() {
        Aluno alunoSalvo1 = new Aluno(1L, "Maria", 9, 9f, 8.7f, 8.9f, 8.86f, true);
        Aluno alunoSalvo2 = new Aluno(1L, "Maria", 9, 9f, 8.7f, 8.9f, 8.86f, true);
        List<Aluno> listaEsperada = List.of(alunoSalvo1, alunoSalvo2);
        when(alunoRepository.findAll()).thenReturn(listaEsperada);

        List<Aluno> listaRetornada = alunoService.findAll();

        assertEquals(listaEsperada, listaRetornada);
        verify(alunoRepository).findAll();
    }
}
