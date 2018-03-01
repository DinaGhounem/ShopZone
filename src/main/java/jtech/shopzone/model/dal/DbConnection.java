package jtech.shopzone.model.dal;

import oracle.jdbc.OracleDriver;

import java.sql.*;

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

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return preparedStatement;
    }

    public static Statement getStatement() throws SQLException {
        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return statement;
    }

    public static void closeStatementAndResultSet(Statement statement, ResultSet resultSet) throws SQLException {
        statement.close();
        resultSet.close();
    }

}
