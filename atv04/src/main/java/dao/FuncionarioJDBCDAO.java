package dao;

import model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioJDBCDAO implements FuncionarioDAO{

    @Override
    public void inserir(Funcionario funcionario) throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        String consulta = "insert into funcionarios (cpf, matricula, nome, email, telefone) values (?, ?, ?, ?, ?)";
        PreparedStatement ps = conexao.prepareStatement(consulta);
        ps.setString(1, funcionario.getCpf());
        ps.setString(2, funcionario.getMatricula());
        ps.setString(3, funcionario.getNome());
        ps.setString(4, funcionario.getEmail());
        ps.setString(5, funcionario.getTelefone());

        ps.executeUpdate();
        conexao.close();
    }

    public void deletar(int id) throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        String consulta = "delete from funcionarios where id = ?";
        PreparedStatement ps = conexao.prepareStatement(consulta);

        ps.setInt(1, id);
        ps.executeUpdate();
        conexao.close();
    }

    public void editar(Funcionario funcionario) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        String consulta = "update funcionarios set cpf= ?, matricula= ?, nome= ?, email= ?, telefone= ? where id = ?";
        PreparedStatement ps = conexao.prepareStatement(consulta);
        ps.setString(1, funcionario.getCpf());
        ps.setString(2, funcionario.getMatricula());
        ps.setString(3, funcionario.getNome());
        ps.setString(4, funcionario.getEmail());
        ps.setString(5, funcionario.getTelefone());
        ps.setInt(6, funcionario.getId());
        ps.executeUpdate();
        conexao.close();
    }

    private Funcionario map(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(rs.getInt("id"));
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setMatricula(rs.getString("matricula"));
        funcionario.setEmail(rs.getString("email"));
        funcionario.setTelefone(rs.getString("telefone"));
        return funcionario;
    }

    public List<Funcionario> selecionar() throws SQLException { //Lista todos os funcionários.
        List<Funcionario> funcionarios = new ArrayList<>();
        Connection conexao = ConnectionFactory.getConnection();
        String consulta = "select * from funcionarios";
        PreparedStatement ps = conexao.prepareStatement(consulta);

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Funcionario funcionario = map(rs);
            funcionarios.add(funcionario);
        }
        conexao.close();
        return funcionarios;
    }

    public Funcionario buscar(int id) throws SQLException { //Busca apenas um funcionário.
        Funcionario funcionario = new Funcionario();
        Connection conexao = ConnectionFactory.getConnection();
        String consulta = "select * from funcionarios where id = ?";
        PreparedStatement ps = conexao.prepareStatement(consulta);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            funcionario = map(rs);
        }
        conexao.close();
        return funcionario;
    }

}
