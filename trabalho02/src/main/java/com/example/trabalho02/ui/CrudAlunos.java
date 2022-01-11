package com.example.trabalho02.ui;

import com.example.trabalho02.dao.AlunoDAO;
import com.example.trabalho02.entity.Aluno;
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
@ComponentScan("com.example.trabalho02")
@EntityScan("com.example.trabalho02.entity")
@EnableJpaRepositories("com.example.trabalho02.dao")

public class CrudAlunos implements CommandLineRunner {
    @Autowired
    public AlunoDAO templateAluno;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CrudAlunos.class);
        builder.headless(false).run(args);
    }

    public static void adicionarAluno(Aluno aluno){
        String nome = JOptionPane.showInputDialog("Nome", aluno.getNome());
        String email = JOptionPane.showInputDialog("Email", aluno.getEmail());
        String matricula = JOptionPane.showInputDialog("Matrícula", aluno.getMatricula());
        String cpf = JOptionPane.showInputDialog("CPF", aluno.getCpf());

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setMatricula(matricula);
        aluno.setCpf(cpf);
    }

    public void mostrarUmAluno(Aluno aluno){
        JOptionPane.showMessageDialog(null, aluno == null ? "Nenhum aluno encontrado" : aluno);
    }

    public void mostrarUmAlunoPorMatricula(Aluno aluno){
        JOptionPane.showMessageDialog(null, aluno == null ? "Nenhum aluno encontrado"
        : "Nome: " + aluno.getNome() + "\n" + "Email: " + aluno.getEmail() + "\n");
    }

    public void listarAlunos(List<Aluno> alunos){
        StringBuilder listagem = new StringBuilder();
        for(Aluno aluno : alunos) {
            listagem.append(aluno).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum aluno encontrado" : listagem);
    }

    @Override
    public void run(String... args) throws Exception {
        String opcoes = "Escolha uma das seguintes opções:\n" +
                "1: Adicionar um aluno\n" +
                "2: Deletar um aluno\n" +
                "3: Editar um aluno\n" +
                "4: Buscar um único aluno\n" +
                "5: Listar todos os alunos\n" +
                "6: Buscar aluno por matrícula\n" +
                "7: Buscar aluno por data de nascimento\n" +
                "x: Sair do sistema\n";
        char escolha = '0';
        Aluno aluno;
        int id;

        while(escolha != 'x'){
            escolha = JOptionPane.showInputDialog(opcoes).charAt(0);
            if(escolha == '1'){ //Inserir um aluno.
                aluno = new Aluno();
                adicionarAluno(aluno);
                templateAluno.save(aluno);
            }
            if(escolha == '2'){ //Deletar um aluno.
                id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do aluno que será deletado:"));
                aluno = templateAluno.findFirstById(id);
                if(aluno != null){
                    templateAluno.deleteById(aluno.getId());
                } else {
                    JOptionPane.showMessageDialog(null, "Aluno não encontrado");
                }
            }
            if(escolha == '3'){ //Editar um aluno.
                id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do aluno que será editado:"));
                aluno = templateAluno.findFirstById(id);
                if(aluno != null) {
                    adicionarAluno(aluno);
                    templateAluno.save(aluno);
                } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado");
                }
            }
            if(escolha == '4'){ //Buscar apenas um aluno.
                id = Integer.parseInt(JOptionPane.showInputDialog("Id:"));
                aluno = templateAluno.findFirstById(id);
                mostrarUmAluno(aluno);
            }
            if(escolha == '5') { //Listar todos.
                listarAlunos(templateAluno.findAll());
            }
            if(escolha == '6') { //Buscar aluno por matrícula.
                String matricula = JOptionPane.showInputDialog("Digite a matrícula:");
                aluno = templateAluno.findFirstByMatricula(matricula);
                mostrarUmAlunoPorMatricula(aluno);
            }
        }
    }
}
