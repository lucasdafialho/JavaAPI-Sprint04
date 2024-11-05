package org.mecanica.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login extends _EntidadeBase{
    private String senha;
    private String email;

    public Login(int id, String senha, String email) {
        super(id);
        this.senha = senha;
        this.email = email;
    }
}