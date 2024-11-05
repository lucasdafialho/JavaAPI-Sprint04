package org.mecanica.repositorios;

import lombok.var;
import org.mecanica.entidades.Veiculo;
import org.mecanica.infraestrutura.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class VeiculoRepositorio implements _RepositorioBase<Veiculo> {

    @Override
    public void cadastrar(Veiculo entidade) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "INSERT INTO T_CARRO (PLACA, MARCA, MODELO, ANO, QUILOMETRAGEM) VALUES (?,?,?,?,?)";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getPlaca());
            stmt.setString(2, entidade.getMarca());
            stmt.setString(3, entidade.getModelo());
            stmt.setInt(4, entidade.getAno());
            stmt.setDouble(5, entidade.getQuilometragem());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Veiculo entidade, int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "UPDATE T_CARRO SET PLACA = ?, MARCA = ?, MODELO = ?, ANO = ?, QUILOMETRAGEM = ? WHERE ID_CARRO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getPlaca());
            stmt.setString(2, entidade.getMarca());
            stmt.setString(3, entidade.getModelo());
            stmt.setInt(4, entidade.getAno());
            stmt.setDouble(5, entidade.getQuilometragem());
            stmt.setInt(6, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "DELETE FROM T_CARRO WHERE ID_CARRO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Veiculo> buscarPorId(int id) {
        Optional<Veiculo> carro = Optional.empty();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_CARRO WHERE ID_CARRO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var _id = rs.getInt("ID_CARRO");
                var placa = rs.getString("PLACA");
                var marca = rs.getString("MARCA");
                var modelo = rs.getString("MODELO");
                var ano = rs.getInt("ANO");
                var quilometragem = rs.getDouble("QUILOMETRAGEM");
                carro = Optional.of(new Veiculo(_id, placa, marca, modelo, ano, quilometragem));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return carro;
    }

    @Override
    public List<Veiculo> listar() {
        var carros = new ArrayList<Veiculo>();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_CARRO";
            var stmt = conn.prepareStatement(query);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var _id = rs.getInt("ID_CARRO");
                var placa = rs.getString("PLACA");
                var marca = rs.getString("MARCA");
                var modelo = rs.getString("MODELO");
                var ano = rs.getInt("ANO");
                var quilometragem = rs.getDouble("QUILOMETRAGEM");
                carros.add(new Veiculo(_id, placa, marca, modelo, ano, quilometragem));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return carros;
    }
}

