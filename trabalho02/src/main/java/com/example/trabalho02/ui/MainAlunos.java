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
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.example.trabalho02")
@EntityScan("com.example.trabalho02.entity")
@EnableJpaRepositories("com.example.trabalho02.dao")

public class MainAlunos implements CommandLineRunner {
    @Autowired
    public AlunoDAO templateAluno;

    @Autowired
    public AssociacaoDAO templateAssociacao;

    @Autowired
    public DisciplinaDAO templateDisciplina;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MainAlunos.class);
        builder.headless(false).run(args);
    }

    public void adicionarAluno(Aluno aluno) throws ParseException {
        String nome = JOptionPane.showInputDialog("Nome", aluno.getNome());
        String email = JOptionPane.showInputDialog("Email", aluno.getEmail());
        String matricula = JOptionPane.showInputDialog("Matrícula", aluno.getMatricula());
        String cpf = JOptionPane.showInputDialog("CPF", aluno.getCpf());
        String dataDeNascimento = JOptionPane.showInputDialog("Data de nascimento", aluno.getDatanascimento());
        java.util.Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataDeNascimento);

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setMatricula(matricula);
        aluno.setCpf(cpf);
        aluno.setDatanascimento(alterarTipoDeData(data));
    }


    public void mostrarUmAluno(Aluno aluno){
        JOptionPane.showMessageDialog(null, aluno == null ? "Nenhum aluno encontrado" : aluno);
    }

    public void mostrarUmAlunoPorMatricula(){
        String matricula = JOptionPane.showInputDialog("Digite a matrícula:");
        Aluno aluno = templateAluno.findFirstByMatricula(matricula);
        JOptionPane.showMessageDialog(null, aluno == null ? "Nenhum aluno encontrado"
        : "Nome: " + aluno.getNome() + "\n" + "Email: " + aluno.getEmail() + "\n");
    }

    public void mostrarUmAlunoPorNome(){
        listarAlunos(templateAluno.findByNomeStartingWith(JOptionPane.showInputDialog("Digito nome do aluno para busca")));
    }

    public void listarAlunos(List<Aluno> alunos){
        StringBuilder listagem = new StringBuilder();
        for(Aluno aluno : alunos) {
            listagem.append(aluno).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum aluno encontrado" : listagem);
    }

    public void deletarAluno(){
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do aluno que será deletado:"));
        Aluno aluno = templateAluno.findFirstById(id);
        if(aluno != null){
            templateAluno.deleteById(aluno.getId());
        } else {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado");
        }
    }

    public void editarAluno() throws ParseException {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do aluno que será editado:"));
        Aluno aluno = templateAluno.findFirstById(id);
        if(aluno != null) {
            adicionarAluno(aluno);
            templateAluno.save(aluno);
        } else {
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
        }
    }

    public java.sql.Date alterarTipoDeData(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateSql = simpleDateFormat.format(date);
        return java.sql.Date.valueOf(dateSql);
    }

    public void buscaPorData() throws ParseException {
        String data = JOptionPane.showInputDialog("Digite a data de nascimento do aluno:");
        java.util.Date dataUtil = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        java.sql.Date dataSql = alterarTipoDeData(dataUtil);
        listarAlunos(templateAluno.findAlunosByDatanascimento(dataSql));
    }

    public void atribuirDisciplina(){
        int alunoId = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do Aluno:"));
        String disciplinaCodigo = JOptionPane.showInputDialog("Digite o codigo da Disciplina:");

        Aluno aluno = templateAluno.findFirstById(alunoId);
        Disciplina disciplina = templateDisciplina.findFirstByCodigo(disciplinaCodigo);

        if(aluno != null && disciplina != null){
            Associacao associacao = new Associacao();
            associacao.setAluno(aluno);
            associacao.setDisciplina(disciplina);
            templateAssociacao.save(associacao);
        }else{
            JOptionPane.showMessageDialog(null, "Dados não encontrados.");
        }
    }

    @Override
    public void run(String... args) throws Exception {
        char escolha = '0';
        Aluno aluno;
        int id;
        String opcoes = "Escolha uma das seguintes opções:\n" +
                "1: Adicionar um aluno\n" +
                "2: Deletar um aluno por id\n" +
                "3: Editar um aluno por id\n" +
                "4: Buscar um aluno por id\n" +
                "5: Listar todos os alunos\n" +
                "6: Buscar aluno por matrícula\n" +
                "7: Lista por data de nascimento\n" +
                "8: Buscar aluno por nome\n" +
                "9: Atribuir disciplina a aluno\n" +
                "x: Sair do sistema\n";

        while(escolha != 'x'){
            escolha = JOptionPane.showInputDialog(opcoes).charAt(0);
            if(escolha == '1'){ //Inserir um aluno.
                aluno = new Aluno();
                adicionarAluno(aluno);
                templateAluno.save(aluno);
            }
            if(escolha == '2'){ //Deletar um aluno.
                deletarAluno();
            }
            if(escolha == '3'){ //Editar um aluno.
                editarAluno();
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
                mostrarUmAlunoPorMatricula();
            }
            if(escolha == '7') { //Buscar por data.
                buscaPorData();
            }
            if(escolha == '8'){ //Buscar aluno por nome.
                mostrarUmAlunoPorNome();
            }
            if(escolha == '9'){ //Atribuir disciplina para um aluno.
                atribuirDisciplina();
            }
        }
    }
}
