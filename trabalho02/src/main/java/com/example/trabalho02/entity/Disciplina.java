package com.example.trabalho02.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
    @NamedQuery(name = "disciplinaPorId", query = "select disciplina from Disciplina disciplina where disciplina.id = :id"),
    @NamedQuery(name = "disciplinaPorCodigo", query = "select disciplina from Disciplina disciplina where disciplina.codigo = :codigo"),
})

@Entity
@Table(name = "disciplinas")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;
    @NonNull @Getter @Setter private String nome;
    @NonNull @Getter @Setter private String codigo;

    @ToString.Exclude
    @OneToMany(mappedBy = "disciplina")
    @Getter @Setter private List<Associacao> associacoes;
}
