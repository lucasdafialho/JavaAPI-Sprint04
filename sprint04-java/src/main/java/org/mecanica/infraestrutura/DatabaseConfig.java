package org.mecanica.infraestrutura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConfig {
    private static String USER = "RM557884";
    private static String PASSWORD = "210506";
    private static String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCl";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}