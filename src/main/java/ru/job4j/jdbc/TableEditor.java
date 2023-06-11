package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable{

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, IOException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, IOException, ClassNotFoundException {
        connection = getConnection();
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement  = connection.createStatement()) {
            String sql = String.format("create table if not exists %s();", tableName);
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("drop table %s;", tableName);
            statement.execute(sql);
        }
    }

    public void addColum(String tableName, String columName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s add %s %s;", tableName, columName, type);
            statement.execute(sql);
        }

    }

    public void dropColum(String tableName, String columName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s drop column %s;", tableName, columName);
            statement.execute(sql);
        }
    }

    public void renameColum(String tableName, String columName, String newColumName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("alter table %s rename column %s to %s;", tableName, columName, newColumName);
            statement.execute(sql);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        return DriverManager.getConnection(url, login, password);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("tableTest");
        System.out.println(tableEditor.getTableScheme("tableTest"));
        tableEditor.addColum("tableTest", "first_name", "text");
        System.out.println(tableEditor.getTableScheme("tableTest"));
        tableEditor.renameColum("tableTest", "first_name", "name");
        System.out.println(tableEditor.getTableScheme("tableTest"));
        tableEditor.dropColum("tableTest", "name");
        System.out.println(tableEditor.getTableScheme("tableTest"));
        tableEditor.dropTable("tableTest");
    }
}
