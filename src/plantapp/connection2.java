
package plantapp;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// import javax.swing.table.DefaultTableModel;

public class connection2 extends javax.swing.JFrame {
    Connection conn;
    Statement stmt;
    ResultSet rs, cloners;
    // DefaultTableModel testtable = new DefaultTableModel();
    
    // (All things we need to add to the db will be added here)
    // String
    // int
    // Date

    public void ActivateConn(){
        try{
            String url = "jdbc:sqlite:database/plantDB.db";
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);

            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String sql = "PRAGMA foreign_keys = ON;"; // DO NOT REMOVE THIS, THIS IS NECESSARY FOR FOREIGN KEYS
            stmt.execute(sql);

            System.out.println("Connected to SQLite database successfully.");
        }catch (SQLException err) {
            JOptionPane.showMessageDialog(connection2.this, err.getMessage());
        }
    }
    
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
    
    public void RefreshRS(){
        try{
            stmt.close();
            rs.close();
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String sql = "PRAGMA foreign_keys = ON;";
            rs = stmt.executeQuery(sql);
        }catch(SQLException ex){
            Logger.getLogger(connection2.class.getName()).log(Level.SEVERE,null,ex);
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