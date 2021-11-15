package ui;

import dao.FuncionarioDAO;
import dao.FuncionarioJDBCDAO;
import model.Funcionario;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void mostrar() throws SQLException{
        FuncionarioDAO funcionarioDAO = new FuncionarioJDBCDAO();
        List<Funcionario> funcionarios = funcionarioDAO.selecionar();

        StringBuilder listagem = new StringBuilder();
        for(Funcionario funcionario : funcionarios) {
            listagem.append(funcionario).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum funcionario encontrado" : listagem);

    }

    public static void mostrarUmFuncionario(Funcionario funcionario) throws SQLException{
        JOptionPane.showMessageDialog(null, funcionario == null ? "Nenhum funcionario encontrado" : funcionario);
    }

    public static void adicionarDados(Funcionario funcionario) throws SQLException{
        String cpf = JOptionPane.showInputDialog("Cpf");
        String matricula = JOptionPane.showInputDialog("Matricula");
        String nome = JOptionPane.showInputDialog("Nome");
        String email = JOptionPane.showInputDialog("Email");
        String telefone = JOptionPane.showInputDialog("Telefone");

        funcionario.setCpf(cpf);
        funcionario.setMatricula(matricula);
        funcionario.setNome(nome);
        funcionario.setEmail(email);
        funcionario.setTelefone(telefone);
    }

    public static void main(String[] args) throws SQLException {
        FuncionarioDAO funcionarioDAO = new FuncionarioJDBCDAO();

        String opcoes = "Escolha uma das seguintes opções:\n" +
                "1: Adicione um funcionário\n" +
                "2: Deletar um funcionário\n" +
                "3: Editar um funcionário\n" +
                "4: Buscar um único funcionário\n" +
                "5: Listar todos os funcionários\n" +
                "x: Sair do sistema\n";
        char escolha = '0';

        while(escolha != 'x'){
            escolha = JOptionPane.showInputDialog(opcoes).charAt(0);
            if(escolha == '1'){ //Inserir um funcionário.
                Funcionario funcionario = new Funcionario();
                adicionarDados(funcionario);
                funcionarioDAO.inserir(funcionario);
            }
            if(escolha == '2'){ //Deletar um funcionário.
                int id = Integer.parseInt(JOptionPane.showInputDialog("id"));
                funcionarioDAO.deletar(id);
            }
            if(escolha == '3'){ //Editar um funcionário.
                Funcionario funcionario = new Funcionario();
                int id = Integer.parseInt(JOptionPane.showInputDialog("id"));
                funcionario.setId(id);
                adicionarDados(funcionario);
                funcionarioDAO.editar(funcionario);
            }
            if(escolha == '4'){ //Buscar apenas um funcionário.
                Funcionario funcionario = new Funcionario();
                int id = Integer.parseInt(JOptionPane.showInputDialog("id"));
                funcionario = funcionarioDAO.buscar(id);
                mostrarUmFuncionario(funcionario);
            }
            if(escolha == '5') { //Listar todos.
                mostrar();
            }
        }
    }
}
