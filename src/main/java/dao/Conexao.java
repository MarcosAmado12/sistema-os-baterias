package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static String driverJDBC = "org.postgresql.Driver";
    public static String url = "jdbc:postgresql://localhost:5432/os_baterias";
    public static String user = "postgres";
    public static String senha = "1234";

    public static Connection getConexao() {
        try {
            Class.forName(driverJDBC);
            return DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do PostgreSQL não encontrado.");
        } catch (SQLException e) {
            throw new RuntimeException("Falha na conexão com o banco de dados: " + e.getMessage());
        }
    }
}
