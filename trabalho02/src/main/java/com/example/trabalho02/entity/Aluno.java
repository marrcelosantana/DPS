package com.example.trabalho02.entity;

import lombok.*;

import javax.persistence.*;


@NamedQueries({
    @NamedQuery(name = "alunoPorId", query = "select aluno from Aluno aluno where aluno.id = :id")
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
    @NonNull @Getter @Setter private String dataNascimento;
    @NonNull @Getter @Setter private String[] disciplinas;
}
