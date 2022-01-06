package com.example.trabalho02.ui;

import com.example.trabalho02.tests.Testes;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		Testes teste = new Testes();
		teste.cadastrarAluno();
	}
}
