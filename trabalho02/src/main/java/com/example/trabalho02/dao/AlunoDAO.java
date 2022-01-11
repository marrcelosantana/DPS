package com.example.trabalho02.dao;

import com.example.trabalho02.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoDAO extends JpaRepository<Aluno, Integer> {

    @Query(name = "alunoPorId")
    public Aluno findFirstById(int id);

    @Query(name = "alunoPorMatricula")
    public Aluno findFirstByMatricula(String matricula);
}
