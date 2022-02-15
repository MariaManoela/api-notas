package com.example.apinotas.Service;

import com.example.apinotas.Repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public static List findAll() {
        return alunoRepository.findAll();
    }
}
