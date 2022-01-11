package com.example.trabalho02.ui;

import com.example.trabalho02.dao.DisciplinaDAO;
import com.example.trabalho02.entity.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.example.trabalho02")

public class CrudDisciplinas implements CommandLineRunner {
    @Autowired
    public DisciplinaDAO templateDisciplina;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CrudDisciplinas.class);
        builder.headless(false).run(args);
    }

    public void adicionarDisciplina(Disciplina disciplina){
        String nome = JOptionPane.showInputDialog("Disciplina", disciplina.getNome());
        String codigo = JOptionPane.showInputDialog("Código", disciplina.getCodigo());
        disciplina.setNome(nome);
        disciplina.setCodigo(codigo);
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

    public void run(String... args) throws Exception{
        String opcoes = "Escolha uma das seguintes opções:\n" +
                "1: Adicionar uma disciplina\n" +
                "2: Deletar uma disciplina\n" +
                "3: Editar uma disciplina\n" +
                "4: Buscar uma única disciplina\n" +
                "5: Listar todas as disciplinas\n" +
                "6: Buscar disciplina por código\n" +
                "x: Sair do sistema\n";
        char escolha = '0';
        Disciplina disciplina;
        int id;

        while(escolha != 'x'){
            escolha = JOptionPane.showInputDialog(opcoes).charAt(0);
            if(escolha == '1'){ //Inserir uma disciplina.
                disciplina = new Disciplina();
                adicionarDisciplina(disciplina);
                templateDisciplina.save(disciplina);
            }
            if(escolha == '2'){ //Deletar uma disciplina.
                id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da disciplina que será deletada:"));
                disciplina = templateDisciplina.findFirstById(id);
                if(disciplina != null){
                    templateDisciplina.deleteById(disciplina.getId());
                } else {
                    JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
                }
            }
            if(escolha == '3'){ //Editar uma disciplina.
                id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da disciplina que será editada:"));
                disciplina = templateDisciplina.findFirstById(id);
                if(disciplina != null) {
                    adicionarDisciplina(disciplina);
                    templateDisciplina.save(disciplina);
                } else {
                    JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
                }
            }
            if(escolha == '4'){ //Buscar apenas uma disciplina.
                id = Integer.parseInt(JOptionPane.showInputDialog("id"));
                disciplina = templateDisciplina.findFirstById(id);
                mostrarUmaDisciplina(disciplina);
            }
            if(escolha == '5') { //Listar todos.
                listarDisciplinas(templateDisciplina.findAll());
            }

            if(escolha == '6'){ //Buscar Disciplina por Código.
                String codigo = JOptionPane.showInputDialog("Digite o código da disciplina:");
                disciplina = templateDisciplina.findFirstByCode(codigo);
                mostrarUmaDisciplina(disciplina);
            }
        }
    }
}
