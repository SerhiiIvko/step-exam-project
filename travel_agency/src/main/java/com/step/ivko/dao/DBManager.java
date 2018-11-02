package com.step.ivko.dao;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static DBManager Instance;
    private DataSource dataSource;

    public static DBManager getInstance() {
        if (Instance == null) {
            Instance = new DBManager();
        }
        return Instance;
    }

    private DBManager() {
    }

    public void initialize(Properties properties) {
        try {
            dataSource = createMySQLDataSource(properties);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private DataSource createMySQLDataSource(Properties properties) throws NullPointerException, SQLException {
        MysqlDataSource mysqlDS;
        mysqlDS = new MysqlDataSource();
        mysqlDS.setUrl(properties.getProperty("MYSQL_URL"));
        mysqlDS.setUser(properties.getProperty("MYSQL_USERNAME"));
        mysqlDS.setPassword(properties.getProperty("MYSQL_PASSWORD"));
        mysqlDS.setAutoReconnect(true);
        mysqlDS.setUseSSL(false);
        mysqlDS.setServerTimezone("UTC");
        return mysqlDS;
    }

    public void stopDb() {
        if (dataSource != null) {
            try {
                dataSource.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}