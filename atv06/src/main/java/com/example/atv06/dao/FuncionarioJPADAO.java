package com.example.atv06.dao;

import com.example.atv06.model.Funcionario;
import com.example.atv06.utils.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioJPADAO implements FuncionarioDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public void inserir(Funcionario funcionario) throws SQLException {
        entityManager = JPAUtil.getEntityManager();
        JPAUtil.beginTransaction();
        entityManager.persist(funcionario);
        JPAUtil.commit();
    }

    @Override
    public void deletar(int id) throws SQLException {
        entityManager = JPAUtil.getEntityManager();
        Funcionario funcionario = buscar(id);
        entityManager.remove(funcionario);
        JPAUtil.commit();
    }

    @Override
    public List<Funcionario> selecionar() throws SQLException {
        entityManager = JPAUtil.getEntityManager();
        List<Funcionario> funcionarios = entityManager.createQuery("select f from Funcionario as f", Funcionario.class).getResultList();
        return funcionarios;
    }

    @Override
    public Funcionario buscar(int id) throws SQLException {
        entityManager = JPAUtil.getEntityManager();
        return entityManager.find(Funcionario.class, id);
    }

    @Override
    public void editar(Funcionario funcionario) throws SQLException {
        entityManager = JPAUtil.getEntityManager();
        entityManager.merge(funcionario);
        JPAUtil.commit();
    }
}
