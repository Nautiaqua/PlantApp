package plantapp;

import java.sql.*;
import javax.swing.*;

public class CatalogueManagerConnection {
    public Connection conn;
    public Statement stmt;

    public void activateConn() {
        try {
            String url = "jdbc:sqlite:database/plantDB.db";
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String sql = "PRAGMA foreign_keys = ON;"; // DO NOT REMOVE THIS, THIS IS NECESSARY FOR FOREIGN KEYS
            stmt.execute(sql);

            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            System.out.println("Connected to SQLite database successfully.");

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Database connection error:\n" + err.getMessage());
        }
    }
    
    

    public void closeConnection() {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}