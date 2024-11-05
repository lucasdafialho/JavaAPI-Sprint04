package org.mecanica.repositorios;

import lombok.var;
import org.mecanica.entidades.Diagnostico;
import org.mecanica.infraestrutura.DatabaseConfig;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiagnosticoRepositorio implements _RepositorioBase<Diagnostico> {
    @Override
    public void cadastrar(Diagnostico entidade) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "INSERT INTO T_DIAGNOSTICO (PROBLEMA, ORCAMENTO, DT_EMISSAO) VALUES (?, ?, ?)";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getProblema());
            stmt.setDouble(2, entidade.getOrcamento());
            stmt.setDate(3, Date.valueOf(entidade.getDataEmissao()));
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Diagnostico entidade, int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "UPDATE T_DIAGNOSTICO SET PROBLEMA = ?, ORCAMENTO = ?, DT_EMISSAO = ? WHERE ID_DIAGNOSTICO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setString(1, entidade.getProblema());
            stmt.setDouble(2, entidade.getOrcamento());
            stmt.setDate(3, Date.valueOf(entidade.getDataEmissao()));
            stmt.setInt(4, id);
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
            var query = "DELETE FROM T_DIAGNOSTICO WHERE ID_DIAGNOSTICO = ?";
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
    public Optional<Diagnostico> buscarPorId(int id) {
        Optional<Diagnostico> diagnostico = Optional.empty();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_DIAGNOSTICO WHERE ID_DIAGNOSTICO = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var _id = rs.getInt("ID_DIAGNOSTICO");
                var problema = rs.getString("PROBLEMA");
                var orcamento = rs.getDouble("ORCAMENTO");
                var dataSql = rs.getDate("DT_EMISSAO");
                var dataEmissao = dataSql.toLocalDate();
                diagnostico = Optional.of(new Diagnostico(_id, problema, orcamento, dataEmissao));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnostico;
    }

    @Override
    public List<Diagnostico> listar() {
        var diagnosticos = new ArrayList<Diagnostico>();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_DIAGNOSTICO";
            var stmt = conn.prepareStatement(query);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var _id = rs.getInt("ID_DIAGNOSTICO");
                var problema = rs.getString("PROBLEMA");
                var orcamento = rs.getDouble("ORCAMENTO");
                var dataSql = rs.getDate("DT_EMISSAO");
                var dataEmissao = dataSql.toLocalDate();
                diagnosticos.add(new Diagnostico(_id, problema, orcamento, dataEmissao));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosticos;
    }
}
