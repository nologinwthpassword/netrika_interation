package ru.rtlabs.DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBWorker2 {
    private  String URL;
    private  String USERNAME;
    private  String PASSWORD;

    private Connection connection;

    public DBWorker2(){
        System.out.println("PostgreSQL "+ "JDBC Connection Testing ");
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("config.properties");
            property.load(fis);
            this.URL = property.getProperty("db2.host");
            this.USERNAME = property.getProperty("db2.login");
            this.PASSWORD = property.getProperty("db2.password");
        } catch (FileNotFoundException e1) {
            System.err.println("ОШИБКА: Файл properties отсуствует!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e) {

            System.out.println("Не удалось подключить драйвер JDBC Driver");
            return;
        }
        System.out.println("PostgreSQL JDBC Driver!");

        this.connection = null;
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {

            System.out.println("Не удалось подключиться. Проверьте URL, USERNAME, PASSWORD");

        }

        if (connection != null) {
            System.out.println("Подключение удалось");
            System.out.println(URL);
        } else {
            System.out.println("Не удалось подключиться.");
        }
    }


    public Connection getConnection() {
        return connection;
    }
}
