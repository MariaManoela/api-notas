package com.example.apinotas.Model;

import lombok.*;

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
    @Setter(AccessLevel.NONE)
    private float notaFinal;
    @Setter(AccessLevel.NONE)
    private boolean aprovado;

    public void finalizarAvaliacao(float notaPrimeiroTrimestre, float notaSegundoTrimestre, float notaTerceiroTrimestre) {
        notaFinal = (notaPrimeiroTrimestre + notaSegundoTrimestre + notaTerceiroTrimestre)/3;
        setAprovado(notaFinal);
    }

    private void setAprovado(float notaFinal) {
        if(notaFinal >= 7) {
            this.aprovado = true;
        } else {
            this.aprovado = false;
        }
    }
}
