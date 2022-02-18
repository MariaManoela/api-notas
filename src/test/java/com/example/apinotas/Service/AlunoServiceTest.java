package com.example.apinotas.Service;

import com.example.apinotas.Model.Aluno;
import com.example.apinotas.Repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void retornarListaDeTodosAlunos() {
        List<Aluno> lista = new ArrayList<>();
        lista.add(new Aluno(1L, "Manoela", 9, 8.4f, 9.2f, 8.7f, 8.76f,true));
        lista.add(new Aluno(2L, "Camila", 8, 6.8f, 7.5f, 6.9f, 7.06f,true));

        when(alunoRepository.findAll()).thenReturn(lista);
        assertEquals(alunoService.findAll(), lista);
        verify(alunoRepository).findAll();
    }
}
