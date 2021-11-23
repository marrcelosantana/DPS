package com.example.atv05.dao;

import com.example.atv05.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioJDBCDAO implements FuncionarioDAO{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void inserir(Funcionario funcionario)  {
        String consulta = "insert into funcionarios (cpf, matricula, nome, email, telefone) values (:cpf, :matricula, :nome, :email, :telefone)";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("cpf", funcionario.getCpf())
                .addValue("matricula", funcionario.getMatricula())
                .addValue("nome", funcionario.getNome())
                .addValue("email", funcionario.getEmail())
                .addValue("telefone", funcionario.getTelefone());

        jdbcTemplate.update(consulta, params);
    }

    @Override
    public void deletar(int id)  {
        String consulta = "delete from funcionarios where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(consulta, params);
    }

    private Funcionario map(ResultSet resultSet) throws SQLException{
        Funcionario funcionario = new Funcionario();
        funcionario.setId(resultSet.getInt("id"));
        funcionario.setCpf(resultSet.getString("cpf"));
        funcionario.setMatricula(resultSet.getString("matricula"));
        funcionario.setNome(resultSet.getString("nome"));
        funcionario.setEmail(resultSet.getString("email"));
        funcionario.setTelefone(resultSet.getString("telefone"));

        return funcionario;
    }

    @Override
    public List<Funcionario> selecionar() {
        String consulta = "select * from funcionarios";
        return jdbcTemplate.query(consulta, (rs, rowNum) -> map(rs));
    }

    @Override
    public Funcionario buscar(int id) {
        String consulta = "select * from funcionarios where id = :id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

        return jdbcTemplate.queryForObject(consulta, params, (rs, rowNum) -> map(rs));
    }

    @Override
    public void editar(Funcionario funcionario)  {
        String consulta = "update funcionarios set cpf = :cpf, matricula = :matricula, nome = :nome, email = :email, telefone = :telefone where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("cpf", funcionario.getCpf())
                .addValue("matricula", funcionario.getMatricula())
                .addValue("nome", funcionario.getNome())
                .addValue("email", funcionario.getEmail())
                .addValue("telefone", funcionario.getTelefone());

        params.addValue("id",  funcionario.getId());
        jdbcTemplate.update(consulta, params);

    }
}
