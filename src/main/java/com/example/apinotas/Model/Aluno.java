package com.example.apinotas.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private int anoDoAluno;
    private float notaPrimeiroTrimestre;
    private float notaSegundoTrimestre;
    private float notaTerceiroTrimestre;
    private float notaFinal;
    private boolean aprovado;

    public void setNotaFinal(float notaFinal) {
        this.notaFinal = (notaPrimeiroTrimestre + notaSegundoTrimestre + notaTerceiroTrimestre)/3;
    }

    public void setAprovado(boolean aprovado) {
        if(notaFinal >= 7) {
            this.aprovado = true;
        } else {
            this.aprovado = false;
        }
    }
}
