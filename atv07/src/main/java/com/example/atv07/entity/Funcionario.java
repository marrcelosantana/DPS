package com.example.atv07.entity;

import lombok.*;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "funcionarioPorCpf", query = "select funcionario from Funcionario funcionario where funcionario.cpf = :cpf")
})

@Entity
@Table(name = "Funcionarios")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString

public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;
    @NonNull @Getter @Setter private String cpf;
    @NonNull @Getter @Setter private String matricula;
    @Getter @Setter private String nome;
    @Getter @Setter private String email;
    @Getter @Setter private String telefone;
}
