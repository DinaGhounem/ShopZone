package jtech.shopzone.model.dal;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection;

    static {
        try {
            DriverManager.registerDriver(new OracleDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Factory method for getting the same db connection instance
     *
     * @return
     */
    public static Connection getConnection() {
        if (connection == null) {

            try {
                connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "shopping", "shopping");
            } catch (SQLException ex) {
                try {
                    connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "shopping", "shopping");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }

}
