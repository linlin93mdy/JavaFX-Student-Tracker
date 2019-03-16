/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LinnLinn
 */
public class Database {

    private String url = "jdbc:mysql://localhost:3306/studentapp";
    private String name = "root";
    private String password = "";

    private static Database db;

    private Connection conn;

    private Database() throws SQLException {
        connect();
    }

    public static Database getDatabaseObject() throws SQLException {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    public void connect() throws SQLException {

        conn = DriverManager.getConnection(url, name, password);
    }

    public void disconnect() throws SQLException {
        if (conn != null) {
            conn.close();

        }
    }

    public Connection getConnection() {
        return conn;
    }
}
