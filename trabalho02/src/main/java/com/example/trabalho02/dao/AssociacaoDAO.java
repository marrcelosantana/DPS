package com.example.trabalho02.dao;

import com.example.trabalho02.entity.Associacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssociacaoDAO extends JpaRepository<Associacao, Integer> {

    @Query(name="associacaoPorId")
    List<Associacao> findForDisciplina_id(int id);

}
