package com.example.trabalho02.dao;

import com.example.trabalho02.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface AlunoDAO extends JpaRepository<Aluno, Integer> {

    @Query(name = "alunoPorId")
    Aluno findFirstById(int id);

    @Query(name = "alunoPorMatricula")
    Aluno findFirstByMatricula(String matricula);

    @Query(name = "alunoPorDatanascimento")
    List<Aluno> findAlunosByDatanascimento(Date data);

    List<Aluno> findByNomeStartingWith(String nome);

}
