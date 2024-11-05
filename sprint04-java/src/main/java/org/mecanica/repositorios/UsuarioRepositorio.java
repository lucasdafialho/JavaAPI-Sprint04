package org.mecanica.repositorios;

import org.mecanica.entidades.Usuario;
import org.mecanica.infraestrutura.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositorio {

    public void cadastrar(Usuario usuario) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "INSERT INTO T_USUARIO (CPF, NM_USUARIO, SEXO, DT_NASCIMENTO, EMAIL, SENHA) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSexo());
            stmt.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void atualizar(Usuario usuario, int id) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "UPDATE T_USUARIO SET CPF = ?, NM_USUARIO = ?, SEXO = ?, DT_NASCIMENTO = ?, EMAIL = ?, SENHA = ? WHERE ID_USUARIO = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSexo());
            stmt.setDate(4, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());
            stmt.setInt(7, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "DELETE FROM T_USUARIO WHERE ID_USUARIO = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Usuario> buscarPorId(int id) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "SELECT * FROM T_USUARIO WHERE ID_USUARIO = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("CPF"),
                        rs.getString("NM_USUARIO"),
                        rs.getString("SEXO"),
                        rs.getDate("DT_NASCIMENTO").toLocalDate(),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")
                );
                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "SELECT * FROM T_USUARIO WHERE EMAIL = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("CPF"),
                        rs.getString("NM_USUARIO"),
                        rs.getString("SEXO"),
                        rs.getDate("DT_NASCIMENTO").toLocalDate(),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")
                );
                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection()) {
            String query = "SELECT * FROM T_USUARIO";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("CPF"),
                        rs.getString("NM_USUARIO"),
                        rs.getString("SEXO"),
                        rs.getDate("DT_NASCIMENTO").toLocalDate(),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
