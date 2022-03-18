package com.example.apinotas.service;

import com.example.apinotas.model.Aluno;
import com.example.apinotas.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno cadastraAluno(Aluno aluno) {
        aluno.finalizarAvaliacao(aluno.getNotaPrimeiroTrimestre(), aluno.getNotaSegundoTrimestre(), aluno.getNotaTerceiroTrimestre());
        return alunoRepository.save(aluno);
    }

    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }
}
