package com.example.trabalho02.dao;

import com.example.trabalho02.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaDAO extends JpaRepository<Disciplina, Integer> {
}
