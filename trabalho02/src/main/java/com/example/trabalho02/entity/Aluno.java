package com.example.trabalho02.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@NamedQueries({
    @NamedQuery(name = "alunoPorId", query = "select aluno from Aluno aluno where aluno.id = :id"),
    @NamedQuery(name = "alunoPorCpf", query = "select aluno from Aluno aluno where aluno.cpf = :cpf"),
    @NamedQuery(name = "alunoPorMatricula", query = "select aluno from Aluno aluno where aluno.matricula = :matricula"),
    @NamedQuery(name = "alunoPorEmail", query = "select aluno from Aluno aluno where aluno.email = :email")
})

@Entity
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;
    @NonNull @Getter @Setter private String nome;
    @NonNull @Getter @Setter private String matricula;
    @NonNull @Getter @Setter private String email;
    @NonNull @Getter @Setter private String cpf;
    @NonNull @Getter @Setter private Date dataDeNascimento;

    @Transient
    @NonNull @Getter @Setter private String[] disciplinasCursadas;
}
