package com.example.apinotas.controller;

import com.example.apinotas.model.Aluno;
import com.example.apinotas.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public List<Aluno> findAll() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional findById (@PathVariable("id")Long id) {
        return alunoService.findById(id);
    }

    @PostMapping
    public void cadastraAluno(@RequestBody Aluno aluno) {
        alunoService.cadastraAluno(aluno);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        alunoService.deleteById(id);
    }
}
