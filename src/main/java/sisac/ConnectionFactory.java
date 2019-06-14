package sisac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public ConnectionFactory() {

    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_sisac_v1", "root", "secret");
        } catch(SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
