package com.example.trabalho02.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;


@NamedQueries({
    @NamedQuery(name = "alunoPorId", query = "select aluno from Aluno aluno where aluno.id = :id"),
    @NamedQuery(name = "alunoPorMatricula", query = "select aluno from Aluno aluno where aluno.matricula = :matricula"),
    @NamedQuery(name = "alunoPorEmail", query = "select aluno from Aluno aluno where aluno.email = :email"),
    @NamedQuery(name = "alunoPorDatanascimento", query = "select aluno from Aluno aluno where aluno.datanascimento >= :data")
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
    @NonNull @Getter @Setter private Date datanascimento;

}
