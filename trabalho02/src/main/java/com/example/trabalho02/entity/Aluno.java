package com.example.trabalho02.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@NamedQueries({
    @NamedQuery(name = "alunoPorId", query = "select aluno from Aluno aluno where aluno.id = :id"),
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

    @ToString.Exclude
    @OneToMany(mappedBy = "aluno")
    @Getter @Setter private List<Associacao> associacoes;
}
