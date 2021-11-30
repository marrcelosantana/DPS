package com.example.atv06.ui;

import com.example.atv06.dao.FuncionarioDAO;
import com.example.atv06.dao.FuncionarioJPADAO;
import com.example.atv06.model.Funcionario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

	FuncionarioDAO funcionarioDAO = new FuncionarioJPADAO();

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		builder.headless(false).run(args);
	}

	public void mostrar() throws SQLException{
		List<Funcionario> funcionarios = funcionarioDAO.selecionar();

		StringBuilder listagem = new StringBuilder();
		for(Funcionario funcionario : funcionarios) {
			listagem.append(funcionario).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum funcionario encontrado" : listagem);

	}

	public void mostrarUmFuncionario(Funcionario funcionario){
		JOptionPane.showMessageDialog(null, funcionario == null ? "Nenhum funcionario encontrado" : funcionario);
	}

	public void adicionarDados(Funcionario funcionario){
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

	@Override
	public void run(String... args) throws Exception {
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
				int id = Integer.parseInt(JOptionPane.showInputDialog("id"));
				Funcionario funcionario = funcionarioDAO.buscar(id);
				mostrarUmFuncionario(funcionario);
			}
			if(escolha == '5') { //Listar todos.
				mostrar();
			}
		}
	}
}

