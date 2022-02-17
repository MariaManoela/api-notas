package com.example.apinotas.Controller;

import com.example.apinotas.Model.Aluno;
import com.example.apinotas.Service.AlunoService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = AlunoController.class)
public class AlunoControllerTest {

    List<Aluno> lista = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlunoService alunoService;


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        Aluno aluno1 = new Aluno(1L, "Manoela", 9, 8.4f, 9.2f, 8.7f, 8.76f,true );
        Aluno aluno2 = new Aluno(2L, "Pedro", 7, 6.4f, 7.1f, 7.3f, 6.93f, false);
        Aluno aluno3 = new Aluno(3L, "Camila", 5, 8.8f, 8.2f, 8.5f, 8.5f, true);

        lista.add(aluno1);
        lista.add(aluno2);
        lista.add(aluno3);
    }

    @Test
    public void deveriaMostrarTodosAlunosComSucesso() {
        Mockito.when(alunoService.findAll()).thenReturn(lista);
        Assert.assertEquals(lista, alunoService.findAll());
        System.out.println(alunoService.findAll());
    }
}
