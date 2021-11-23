package com.example.atv05.dao;

import com.example.atv05.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public interface FuncionarioDAO {
    public void inserir(Funcionario funcionario) throws SQLException;
    public void deletar(int id) throws SQLException;
    public List<Funcionario> selecionar() throws SQLException;
    public Funcionario buscar(int id) throws SQLException;
    public void editar(Funcionario funcionario) throws SQLException;
}
