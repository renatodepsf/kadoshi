package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDB {

    private static Connection conexao = null;

    public static Connection getConectar() {

        if (conexao == null) {
//            String url = "jdbc:sqlserver://;serverName=RENATO;databaseName=Kadoshi;integratedSecurity=true";
            try {
                conexao = DriverManager.getConnection(getProps().getProperty("url"));
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

    private static Properties getProps() {
        Properties props = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("config.properties");
        } catch (FileNotFoundException e) {
            throw new DbException(e.getMessage());
        }
        try {
            props.load(inputStream);
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
        return props;
    }
}
