package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static Connection conexao = null;
    public static Connection getConectar() {
        if (conexao == null) {
            String url = "jdbc:sqlserver://;serverName=RENATO;databaseName=master";
            try {
                conexao = DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conexao;
    }

    public static void getDesconectar() {
        if(conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
