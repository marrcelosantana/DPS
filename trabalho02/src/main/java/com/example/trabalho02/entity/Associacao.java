package com.example.trabalho02.entity;

import lombok.*;

import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name = "associacaoPorId", query = "select associacao from Associacao associacao where associacao.id = :id"),
})

@Entity
@Table(name = "associacao")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Associacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Integer id;

    @ManyToOne
    @Getter @Setter private Aluno aluno;

    @ManyToOne
    @Getter @Setter private Disciplina disciplina;
}
