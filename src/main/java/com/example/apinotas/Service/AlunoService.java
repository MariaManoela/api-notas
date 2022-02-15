package com.example.apinotas.Service;

import com.example.apinotas.Model.Aluno;
import com.example.apinotas.Repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public List findAll() {
        return alunoRepository.findAll();
    }

    public Optional findById(Long id) {
        return alunoRepository.findById(id);
    }

    public void save(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }
}
