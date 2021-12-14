package com.example.atv07.ui;

import com.example.atv07.dao.FuncionarioDAO;
import com.example.atv07.entity.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.example.atv07")
@EntityScan("com.example.atv07.entity")
@EnableJpaRepositories("com.example.atv07.dao")
public class Main implements CommandLineRunner {

	@Autowired
	FuncionarioDAO templateFuncionario;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		builder.headless(false).run(args);
	}

	public void mostrar(List<Funcionario> funcionarios) {
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
		Funcionario funcionario;
		int id;
		String cpf;

		while(escolha != 'x'){
			escolha = JOptionPane.showInputDialog(opcoes).charAt(0);
			if(escolha == '1'){ //Inserir um funcionário.
				funcionario = new Funcionario();
				adicionarDados(funcionario);
				templateFuncionario.save(funcionario);
			}
			if(escolha == '2'){ //Deletar um funcionário.
				id = Integer.parseInt(JOptionPane.showInputDialog("id"));
				funcionario = templateFuncionario.findFirstByid(id);
				if(funcionario != null){
					templateFuncionario.deleteById(funcionario.getId());
				}
			}
			if(escolha == '3'){ //Editar um funcionário.
				funcionario = templateFuncionario.findFirstByCpf("id");
				id = Integer.parseInt(JOptionPane.showInputDialog("id"));
				mostrar(templateFuncionario.findAll());
				templateFuncionario.save(funcionario);
			}
			if(escolha == '4'){ //Buscar apenas um funcionário.
				id = Integer.parseInt(JOptionPane.showInputDialog("id"));
				funcionario = templateFuncionario.findById(id).orElse(null);
				mostrarUmFuncionario(funcionario);
			}
			if(escolha == '5') { //Listar todos.
				mostrar(templateFuncionario.findAll());
			}
		}
	}
}
