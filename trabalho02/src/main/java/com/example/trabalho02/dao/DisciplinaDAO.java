package com.example.trabalho02.dao;

import com.example.trabalho02.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DisciplinaDAO extends JpaRepository<Disciplina, Integer> {

    @Query(name = "disciplinaPorId")
    Disciplina findFirstById(int id);

    @Query(name = "disciplinaPorCodigo")
    Disciplina findFirstByCodigo(String codigo);
}
