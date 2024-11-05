package org.mecanica.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Data
@NoArgsConstructor
public class Usuario extends _EntidadeBase {
    private String nome;
    private String cpf;
    private String sexo;
    private LocalDate dataNascimento;
    private String email;
    private String senha;

    public Usuario(String cpf, String nome, String sexo, LocalDate dataNascimento, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int id, String cpf, String nome, String sexo, LocalDate dataNascimento, String email, String senha) {
        super(id);
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
    }

    public static LocalDate parseDataNascimento(String dataNascimento) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dataNascimento, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data de nascimento inválido. Use o formato dd/MM/yyyy.");
        }
    }
}
