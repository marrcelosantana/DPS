package com.example.trabalho02.ui;

import com.example.trabalho02.dao.AlunoDAO;
import com.example.trabalho02.dao.AssociacaoDAO;
import com.example.trabalho02.dao.DisciplinaDAO;
import com.example.trabalho02.entity.Aluno;
import com.example.trabalho02.entity.Associacao;
import com.example.trabalho02.entity.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.example.trabalho02")

public class MainDisciplinas implements CommandLineRunner {
    @Autowired
    public DisciplinaDAO templateDisciplina;

    @Autowired
    public AssociacaoDAO templateAssociacao;

    @Autowired
    public AlunoDAO templateAluno;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MainDisciplinas.class);
        builder.headless(false).run(args);
    }

    public void adicionarDisciplina(Disciplina disciplina){
        String nome = JOptionPane.showInputDialog("Disciplina", disciplina.getNome());
        String codigo = JOptionPane.showInputDialog("Código", disciplina.getCodigo());
        disciplina.setNome(nome);
        disciplina.setCodigo(codigo);
    }

    public void deletarDisciplina(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da disciplina que será deletada:"));
        Disciplina disciplina = templateDisciplina.findFirstById(id);
        if(disciplina != null){
            templateDisciplina.deleteById(disciplina.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
        }
    }

    public void editarDisciplina(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da disciplina que será editada:"));
        Disciplina disciplina = templateDisciplina.findFirstById(id);
        if(disciplina != null) {
            adicionarDisciplina(disciplina);
            templateDisciplina.save(disciplina);
        } else {
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
        }
    }

    public void mostrarUmaDisciplina(Disciplina disciplina){
        JOptionPane.showMessageDialog(null, disciplina == null ? "Nenhuma disciplina encontrada" : disciplina);
    }

    public void listarDisciplinas(List<Disciplina> disciplinas){
        StringBuilder listagem = new StringBuilder();
        for(Disciplina disciplina : disciplinas) {
            listagem.append(disciplina).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma disciplina encontrada!" : listagem);
    }

    public void listarAlunos(List<Aluno> alunos){
        StringBuilder listagem = new StringBuilder();
        for(Aluno aluno : alunos) {
            listagem.append(aluno).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum aluno encontrado" : listagem);
    }

    public void buscarAlunoPorDisciplina(){
        String codigoDisciplina = JOptionPane.showInputDialog("Digite o código da disciplina: ");
        Disciplina disciplina = templateDisciplina.findFirstByCodigo(codigoDisciplina);
        if(disciplina != null){
            List<Aluno> alunos = new ArrayList<>();
            List<Associacao> associacoes = templateAssociacao.findAll();
            for(Associacao associacao : associacoes){
                if(associacao.getDisciplina().getId() == disciplina.getId()){
                    Aluno aluno = templateAluno.findFirstById(associacao.getAluno().getId());
                    alunos.add(aluno);
                }
            }
            listarAlunos(alunos);
        }
    }

    public void run(String... args){
        char escolha = '0';
        Disciplina disciplina;
        int id;

        String opcoes = "Escolha uma das seguintes opções:\n" +
                "1: Adicionar uma disciplina\n" +
                "2: Deletar uma disciplina\n" +
                "3: Editar uma disciplina\n" +
                "4: Buscar uma única disciplina\n" +
                "5: Listar todas as disciplinas\n" +
                "6: Buscar disciplina por código\n" +
                "7: Buscar alunos por disciplina\n" +
                "x: Sair do sistema\n";

        while(escolha != 'x'){
            escolha = JOptionPane.showInputDialog(opcoes).charAt(0);
            if(escolha == '1'){ //Inserir uma disciplina.
                disciplina = new Disciplina();
                adicionarDisciplina(disciplina);
                templateDisciplina.save(disciplina);
            }
            if(escolha == '2'){ //Deletar uma disciplina.
                deletarDisciplina();
            }
            if(escolha == '3'){ //Editar uma disciplina.
                editarDisciplina();
            }
            if(escolha == '4'){ //Buscar apenas uma disciplina.
                id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID:"));
                disciplina = templateDisciplina.findFirstById(id);
                mostrarUmaDisciplina(disciplina);
            }
            if(escolha == '5') { //Listar todos.
                listarDisciplinas(templateDisciplina.findAll());
            }

            if(escolha == '6'){ //Buscar Disciplina por Código.
                String codigo = JOptionPane.showInputDialog("Digite o código da disciplina:");
                disciplina = templateDisciplina.findFirstByCodigo(codigo);
                mostrarUmaDisciplina(disciplina);
            }
            if(escolha == '7'){ //Buscar Alunos por Disciplina.
                buscarAlunoPorDisciplina();
            }
        }
    }
}
