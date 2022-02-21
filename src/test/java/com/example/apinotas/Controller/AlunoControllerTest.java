package com.example.apinotas.Controller;

import com.example.apinotas.Service.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlunoService alunoService;

    @Test
    public void retornarListaDeAlunos() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        //        ---------------se encaixa em teste de controller?---------------
//        List<Aluno> listaAlunos = new ArrayList<>();
//        listaAlunos.add(new Aluno(1L, "Manoela", 9, 8.4f, 9.2f, 8.7f, 8.76f,true));
//
//        Mockito.when(alunoService.findAll()).thenReturn(listaAlunos);
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/alunos"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//

    }
}
