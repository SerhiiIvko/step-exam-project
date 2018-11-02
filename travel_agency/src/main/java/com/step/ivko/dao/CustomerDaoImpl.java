package com.step.ivko.dao;

import com.step.ivko.exception.ApplicationException;
import com.step.ivko.model.Customer;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private static final String CUSTOMER_BY_ID_QUERY = "select * from customers where id = '%s'";
    private static final String CUSTOMER_BY_EMAIL_QUERY = "select * from customers where email = '%s'";
    private static final String ALL_CUSTOMERS_QUERY = "select * from customers";
    private static final String ERROR_MESSAGE_PATTERN = "Customer not found by %s: %s";

    @Override
    public Customer getById(Integer id) {
        return getCustomerByQuery(String.format(CUSTOMER_BY_ID_QUERY, id),
                String.format(ERROR_MESSAGE_PATTERN, "id", id));
    }

    @Override
    public Customer getByEmail(String email) {
        return getCustomerByQuery(String.format(CUSTOMER_BY_EMAIL_QUERY, email),
                String.format(ERROR_MESSAGE_PATTERN, "email", email));
    }

    private Customer getCustomerByQuery(String query, String errorMessage) {
        Customer customer = null;
        try (Connection connection = retrieveConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setAge(resultSet.getInt("age"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setBlocked(resultSet.getBoolean("isBlocked"));
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to load customer from DB", e);
        }
        if (customer == null) {
            throw new ApplicationException(errorMessage);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = retrieveConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ALL_CUSTOMERS_QUERY);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setAge(resultSet.getInt("age"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setBlocked(resultSet.getBoolean("isBlocked"));
                customers.add(customer);
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to load customer from DB", e);
        }
        return customers;
    }

    private static final String INSERT_CUSTOMER_QUERY = "insert into customers (" +
            "name, " +
            "surname, " +
            "age, " +
            "email, " +
            "password, " +
            "isBlocked) " +
            "values (?, ?, ?, ?, ?, ?)";

    @Override
    public Customer create(Customer customer) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getSurname());
            statement.setInt(3, customer.getAge());
            statement.setString(4, customer.getEmail());
            statement.setString(5, customer.getPassword());
            statement.setBoolean(6, customer.isBlocked());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                customer.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to insert customer into DB", e);
        }
        return customer;
    }

    private static final String UPDATE_CUSTOMER_QUERY = "update customers set " +
            "name=?, " +
            "surname=?, " +
            "age=?, " +
            "email=?, " +
            "isBlocked=?%s " +
            "where id=?";
    private static final String UPDATE_PASSWORD_PART = ", password=?";

    @Override
    public Customer update(Customer customer) {
        boolean needUpdatePassword = StringUtils.isNotEmpty(customer.getPassword());
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(UPDATE_CUSTOMER_QUERY,
                     needUpdatePassword ? UPDATE_PASSWORD_PART : ""))) {
            int parameterCounter = 1;
            statement.setString(parameterCounter++, customer.getName());
            statement.setString(parameterCounter++, customer.getSurname());
            statement.setInt(parameterCounter++, customer.getAge());
            statement.setString(parameterCounter++, customer.getEmail());
            statement.setBoolean(parameterCounter++, customer.isBlocked());
            if (needUpdatePassword) {
                statement.setString(parameterCounter++, customer.getPassword());
            }
            statement.setInt(parameterCounter, customer.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationException("Failed to update customer", e);
        }
        return customer;
    }

    @Override
    public void update(List<Customer> customers) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(String.format(UPDATE_CUSTOMER_QUERY, ""))) {
            for (Customer customer : customers) {
                statement.setString(1, customer.getName());
                statement.setString(2, customer.getSurname());
                statement.setInt(3, customer.getAge());
                statement.setString(4, customer.getEmail());
                statement.setBoolean(5, customer.isBlocked());
                statement.setInt(6, customer.getId());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (Exception e) {
            throw new ApplicationException("Failed to update customers", e);
        }
    }

    private static final String DELETE_CUSTOMER_QUERY = "delete from customers where id=?";

    @Override
    public void deleteCustomer(Customer customer) {
        try (Connection connection = retrieveConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_QUERY)) {
            statement.setInt(1, customer.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationException("Failed to delete customer", e);
        }
    }

    private Connection retrieveConnection() throws SQLException {
        return DBManager.getInstance().getConnection();
    }
}