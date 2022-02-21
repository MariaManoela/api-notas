package com.example.apinotas.Controller;

import com.example.apinotas.Model.Aluno;
import com.example.apinotas.Service.AlunoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

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

        //        ---------------se encaixa em teste de controller?---------------
//        List<Aluno> listaAlunos = new ArrayList<>();
//        listaAlunos.add(new Aluno(1L, "Manoela", 9, 8.4f, 9.2f, 8.7f, 8.76f,true));
//
//
    }
}
