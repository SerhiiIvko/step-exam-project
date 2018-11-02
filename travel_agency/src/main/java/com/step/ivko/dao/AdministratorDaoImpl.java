package com.step.ivko.dao;

import com.step.ivko.exception.ApplicationException;
import com.step.ivko.model.Administrator;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDaoImpl implements AdministratorDao {
    private static final String ADMIN_BY_ID_QUERY = "select * from admin where id = '%s'";
    private static final String ADMIN_BY_EMAIL_QUERY = "select * from admin where email = '%s'";
    private static final String ALL_ADMINS_QUERY = "select * from admin";
    private static final String ERROR_MESSAGE_PATTERN = "Administrator not found by %s: %s";

    @Override
    public Administrator getById(Integer id) {
        return getAdminByQuery(String.format(ADMIN_BY_ID_QUERY, id),
                String.format(ERROR_MESSAGE_PATTERN, "id", id));
    }

    @Override
    public Administrator getByEmail(String email) {
        return getAdminByQuery(String.format(ADMIN_BY_EMAIL_QUERY, email),
                String.format(ERROR_MESSAGE_PATTERN, "email", email));
    }

    private Administrator getAdminByQuery(String query, String errorMessage) {
        Administrator administrator = null;
        try (Connection connection = retrieveConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                administrator = new Administrator();
                administrator.setId(resultSet.getInt("id"));
                administrator.setName(resultSet.getString("name"));
                administrator.setSurname(resultSet.getString("surname"));
                administrator.setAge(resultSet.getInt("age"));
                administrator.setEmail(resultSet.getString("email"));
                administrator.setPassword(resultSet.getString("password"));
                administrator.setAdmin(resultSet.getBoolean("isAdmin"));
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to load administrator from DB", e);
        }
        if (administrator == null) {
            throw new ApplicationException(errorMessage);
        }
        return administrator;
    }

    @Override
    public List<Administrator> getAll() {
        List<Administrator> admins = new ArrayList<>();
        try (Connection connection = retrieveConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ALL_ADMINS_QUERY);
            while (resultSet.next()) {
                Administrator admin = new Administrator();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setSurname(resultSet.getString("surname"));
                admin.setAge(resultSet.getInt("age"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setAdmin(resultSet.getBoolean("isAdmin"));
                admins.add(admin);
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to load administrator from DB", e);
        }
        return admins;
    }

    private static final String INSERT_ADMIN_QUERY = "insert into admin (" +
            "name, " +
            "surname, " +
            "age, " +
            "email, " +
            "password, " +
            "isAdmin) " +
            "values (?, ?, ?, ?, ?, ?)";

    @Override
    public Administrator create(Administrator administrator) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, administrator.getName());
            statement.setString(2, administrator.getSurname());
            statement.setInt(3, administrator.getAge());
            statement.setString(4, administrator.getEmail());
            statement.setString(5, administrator.getPassword());
            statement.setBoolean(6, administrator.isAdmin());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                administrator.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to insert administrator into DB", e);
        }
        return administrator;
    }

    private static final String UPDATE_ADMIN_QUERY = "update admin set " +
            "name=?, " +
            "surname=?, " +
            "age=?, " +
            "email=?, " +
            "isAdmin=?%s " +
            "where id=?";
    private static final String UPDATE_PASSWORD_PART = ", password=?";

    @Override
    public Administrator update(Administrator administrator) {
        boolean needUpdatePassword = StringUtils.isNotEmpty(administrator.getPassword());
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(UPDATE_ADMIN_QUERY,
                     needUpdatePassword ? UPDATE_PASSWORD_PART : ""))) {
            int parameterCounter = 1;
            statement.setString(parameterCounter++, administrator.getName());
            statement.setString(parameterCounter++, administrator.getSurname());
            statement.setInt(parameterCounter++, administrator.getAge());
            statement.setString(parameterCounter++, administrator.getEmail());
            statement.setBoolean(parameterCounter++, administrator.isAdmin());
            if (needUpdatePassword) {
                statement.setString(parameterCounter++, administrator.getPassword());
            }
            statement.setInt(parameterCounter, administrator.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationException("Failed to update admin", e);
        }
        return administrator;
    }

    @Override
    public void update(List<Administrator> administrators) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(UPDATE_ADMIN_QUERY, ""))) {
            for (Administrator administrator : administrators) {
                statement.setString(1, administrator.getName());
                statement.setString(2, administrator.getSurname());
                statement.setInt(3, administrator.getAge());
                statement.setString(4, administrator.getEmail());
                statement.setBoolean(5, administrator.isAdmin());
                statement.setInt(6, administrator.getId());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            throw new ApplicationException("Failed to update admin", e);
        }
    }

    private static final String DELETE_ADMIN_QUERY = "delete from admin where id=?";

    @Override
    public void deleteAdministrator(Administrator administrator) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, administrator.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationException("Failed to delete admin", e);
        }
    }

    private Connection retrieveConnection() throws SQLException {
        return DBManager.getInstance().getConnection();
    }
}