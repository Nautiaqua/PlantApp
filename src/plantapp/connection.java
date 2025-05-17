
package plantapp;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// import javax.swing.table.DefaultTableModel;

public class connection extends javax.swing.JFrame {
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
            String sql = "SELECT * FROM ACCOUNTS";  // make sure this table exists
            rs = stmt.executeQuery(sql);
            
            System.out.println(rs);

        System.out.println("Connected to SQLite database successfully.");
        }catch (SQLException err) {
            JOptionPane.showMessageDialog(connection.this, err.getMessage());
        }
    }
    public void RefreshRS(){
        try{
            stmt.close();
            rs.close();
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM USERS";
            rs = stmt.executeQuery(sql);
        }catch(SQLException ex){
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}