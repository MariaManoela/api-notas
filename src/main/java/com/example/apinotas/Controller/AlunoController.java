package com.example.apinotas.Controller;

import com.example.apinotas.Service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public List findAll() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional findById (@PathVariable("id")Long id) {
        return alunoService.findById(id);
    }
}
