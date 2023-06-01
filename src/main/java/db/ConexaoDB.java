package db;

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
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Properties props = getProps();
                conexao = DriverManager.getConnection(props.getProperty("url"), props.getProperty("usuario"),
                        props.getProperty("senha"));
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DbException(e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
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
        try (InputStream inputStream = ConexaoDB.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new FileNotFoundException("Arquivo config.properties não encontrado no classpath");
            }
            props.load(inputStream);
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
        return props;
    }
}
