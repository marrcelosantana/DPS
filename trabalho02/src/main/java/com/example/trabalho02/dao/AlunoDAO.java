package com.example.trabalho02.dao;

import com.example.trabalho02.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoDAO extends JpaRepository<Aluno, Integer> {

    public Aluno findFirstByMatricula(String matricula);
    @Query("select aluno from Aluno aluno where aluno.id = :id")
    public Aluno buscaPorid(int id);

    @Query("select aluno from Aluno aluno where aluno.nome like :nome%")
    public List<Aluno> buscaPorNome(String nome);
}
