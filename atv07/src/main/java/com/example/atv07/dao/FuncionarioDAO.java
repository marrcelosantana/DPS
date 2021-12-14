package com.example.atv07.dao;

import com.example.atv07.entity.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer> {

    public Funcionario findFirstByCpf(String cpf);
    @Query("select funcionario from Funcionario funcionario where funcionario.id = :id")
    public Funcionario findFirstByid(int id);
    public List<Funcionario> findByNomeStartingWith(String string);
}
