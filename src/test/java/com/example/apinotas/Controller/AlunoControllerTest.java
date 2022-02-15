package com.example.apinotas.Controller;

import com.example.apinotas.Model.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = AlunoController.class)
public class AlunoControllerTest {

    private AlunoController alunoController;

    @Mock
    private Aluno aluno;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }
}
