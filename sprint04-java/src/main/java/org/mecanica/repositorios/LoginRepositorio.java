package org.mecanica.repositorios;

import lombok.var;
import org.mecanica.entidades.Login;
import org.mecanica.infraestrutura.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class LoginRepositorio implements _RepositorioBase<Login> {
    @Override
    public void cadastrar(Login entidade) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "INSERT INTO T_LOGIN (SENHA, EMAIL) VALUES (?, ?)";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getSenha());
            stmt.setString(2, entidade.getEmail());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Login entidade, int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "UPDATE T_LOGIN SET SENHA = ?, EMAIL = ? WHERE ID_LOGIN = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getSenha());
            stmt.setString(2, entidade.getEmail());
            stmt.setInt(3, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "DELETE FROM T_LOGIN WHERE ID_LOGIN = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Login> buscarPorId(int id) {
        Optional<Login> login = Optional.empty();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_LOGIN WHERE ID_LOGIN = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var _id = rs.getInt("ID_LOGIN");
                var senha = rs.getString("SENHA");
                var email = rs.getString("EMAIL");
                login = Optional.of(new Login(_id, senha, email));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }

    @Override
    public ArrayList<Login> listar() {
        var logins = new ArrayList<Login>();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_LOGIN";
            var stmt = conn.prepareStatement(query);
            var rs = stmt.executeQuery();

            while (rs.next()) {
                var id = rs.getInt("ID_LOGIN");
                var senha = rs.getString("SENHA");
                var email = rs.getString("EMAIL");
                logins.add(new Login(id, senha, email));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logins;
    }
}
