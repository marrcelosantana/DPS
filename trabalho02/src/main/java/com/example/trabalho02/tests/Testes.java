package com.example.trabalho02.tests;

import com.example.trabalho02.entity.Aluno;

public class Testes {

    public void cadastrarAluno(){
        Aluno aluno = new Aluno();

        aluno.setNome("Marcelo");
        aluno.setEmail("marcelo@email.com");
        aluno.setMatricula("112233");
        aluno.setDisciplinas(new String[]{ "111", "222" });
        aluno.setDataNascimento("13-06-2000");

        System.out.println(aluno);
    }
}
