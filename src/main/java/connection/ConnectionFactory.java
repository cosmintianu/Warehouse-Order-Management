package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides methods for establishing and managing database connections.
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/warehousedb";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Constructs a new ConnectionFactory instance.
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error loading database driver", e);
        }
    }

    /**
     * Creates a new database connection.
     *
     * @return A Connection object representing the database connection.
     * @throws SQLException If a database access error occurs.
     */
    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DBURL, USER, PASS);
    }

    /**
     * Retrieves a database connection.
     *
     * @return A Connection object representing the database connection.
     */
    public static Connection getConnection() {
        try {
            return singleInstance.createConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error establishing database connection", e);
            return null;
        }
    }

    /**
     * Closes a database connection.
     *
     * @param connection The Connection object to be closed.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error closing connection", e);
            }
        }
    }

    /**
     * Closes a SQL statement.
     *
     * @param statement The Statement object to be closed.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error closing statement", e);
            }
        }
    }

    /**
     * Closes a ResultSet.
     *
     * @param resultSet The ResultSet object to be closed.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error closing result set", e);
            }
        }
    }
}
