package data_access;

import connection.ConnectionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 * Provides common data access functionality for DAO classes.
 *
 * @param &lt;T&gt; The type of entity managed by the DAO.
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    /**
     * Constructs a new AbstractDAO instance.
     */
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Retrieves a list of all entities from the database.
     *
     * @return A list of entities.
     */
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        Connection connection = ConnectionFactory.getConnection();;
        String tableName = type.getSimpleName().toLowerCase();
        if(tableName.equals("bill")){
            tableName="log";
        }
        String query = "SELECT * FROM " + tableName;

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                T entity = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(entity, resultSet.getObject(field.getName()));
                }
                entities.add(entity);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            closeResources(connection,null,null);
        }
        return entities;
    }

    /**
     * Inserts an entity into the database.
     *
     * @param entity The entity to be inserted.
     * @return true if the insertion was successful, false otherwise.
     */
    public boolean insert(T entity) {
        Connection connection = ConnectionFactory.getConnection();
        String tableName = entity.getClass().getSimpleName().toLowerCase(); // Assumes table name is the same as class name
        if(tableName.equals("bill")){
            tableName="log";
        }
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
        queryBuilder.append(tableName).append(" (");

        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                queryBuilder.append(field.getName()).append(",");
            }
            queryBuilder.deleteCharAt(queryBuilder.length() - 1); // Remove the last comma
            queryBuilder.append(") VALUES (");
            for (Field field : fields) {
                queryBuilder.append("?,");
            }
            queryBuilder.deleteCharAt(queryBuilder.length() - 1); // Remove the last comma
            queryBuilder.append(")");

            try (PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())) {
                int parameterIndex = 1;
                for (Field field : fields) {
                    field.setAccessible(true);
                    statement.setObject(parameterIndex++, field.get(entity));
                }
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection,null,null);
        }
        return false;
    }

    /**
     * Updates an entity in the database.
     *
     * @param entity The entity to be updated.
     * @return true if the update was successful, false otherwise.
     */
    public boolean update(T entity) {
        Connection connection = ConnectionFactory.getConnection();
        String tableName = entity.getClass().getSimpleName().toLowerCase(); // Assumes table name is the same as class name
        StringBuilder queryBuilder = new StringBuilder("UPDATE ");
        queryBuilder.append(tableName).append(" SET ");

        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value != null && !field.getName().equalsIgnoreCase("id")) { // Exclude the ID field from update
                    queryBuilder.append(field.getName()).append(" = ?,");
                }
            }
            queryBuilder.deleteCharAt(queryBuilder.length() - 1); // Remove the last comma
            queryBuilder.append(" WHERE id = ?");

            try (PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())) {
                int parameterIndex = 1;
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    if (value != null && !field.getName().equalsIgnoreCase("id")) {
                        statement.setObject(parameterIndex++, value);
                    }
                }
                // Set the ID parameter
                statement.setObject(parameterIndex, getEntityId(entity));

                return statement.executeUpdate() > 0;
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, null, null);
        }
        return false;
    }

    /**
     * Deletes an entity from the database based on its ID.
     *
     * @param id The ID of the entity to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean delete(int id) {
        Connection connection = ConnectionFactory.getConnection();
        String tableName = type.getSimpleName().toLowerCase(); // Assumes table name is the same as class name
        String idColumnName = "id"; // Assuming the primary key column name is "id"
        String query = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, null, null);
        }
        return false;
    }

    /**
     * Closes the database connection and associated resources.
     *
     * @param connection The database connection to be closed.
     * @param statement  The prepared statement to be closed.
     * @param resultSet  The result set to be closed.
     */
    private void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the ID of an entity.
     *
     * @param entity The entity from which to retrieve the ID.
     * @return The ID of the entity.
     * @throws IllegalAccessException If the ID field cannot be accessed.
     */
    private Object getEntityId(T entity) throws IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equalsIgnoreCase("id")) {
                return field.get(entity);
            }
        }
        throw new IllegalArgumentException("Entity does not have an ID field");
    }
}
