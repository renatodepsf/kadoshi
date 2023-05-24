import db.ConexaoDB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Aplicacao {

    public static void main(String[] args) {

        Connection conexao = ConexaoDB.getConectar();
        ConexaoDB.getDesconectar();

    }
}
