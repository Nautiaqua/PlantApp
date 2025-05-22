package plantapp;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.time.Instant;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class ADMIN_cataloguemanager extends javax.swing.JFrame {
    connection2 dbConn;
    Statement stmt;

    private final CatalogueManagerConnection db = new CatalogueManagerConnection();    
    DefaultTableModel PlantModels = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
        return true; // Allow editing all cells
    }
};

    

    public ADMIN_cataloguemanager() {
        initComponents();
        
        dbConn = new connection2();
        dbConn.ActivateConn();
        
        System.out.println("Session email: " + ALL_login.sessionEmail);
        table_cm.setModel(PlantModels);
        Select();
        
        btn_save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        
    }

    private void setTableDesign() {
        table_cm.getTableHeader().setBackground(new Color(255, 255, 255));
        table_cm.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        table_cm.getTableHeader().setForeground(new Color(72, 96, 51));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table_cm.getTableHeader().setDefaultRenderer(headerRenderer);

        table_cm.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table_cm.setRowHeight(30);
        table_cm.setSelectionBackground(new Color(72, 96, 51));
        table_cm.setSelectionForeground(Color.WHITE);
    }

    public void refreshTable() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                PlantModels.setRowCount(0);
                Select();
                return null;
            }

            @Override
            protected void done() {
                table_cm.repaint();
            }
        };
        worker.execute();
    }

   

        // Optional: commit if your DB requires it
        // db.conn.commit();


        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        search_catalogue = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_cm = new javax.swing.JTable();
        btn_add = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_close = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        add_stock = new javax.swing.JButton();
        stockadd = new javax.swing.JSpinner();
        email_lbl4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(238, 235, 235));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(72, 96, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_catalogue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_catalogueActionPerformed(evt);
            }
        });
        search_catalogue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_catalogueKeyReleased(evt);
            }
        });
        jPanel3.add(search_catalogue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 620, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Search by Plant Name / Scientific Name:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Catalogue Manager");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 6, -1, 27));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 674, 93));

        table_cm.setModel(PlantModels);
        jScrollPane1.setViewportView(table_cm);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 128, 990, 510));

        btn_add.setBackground(new java.awt.Color(72, 96, 51));
        btn_add.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        jPanel2.add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 650, 80, -1));

        btn_delete.setBackground(new java.awt.Color(72, 96, 51));
        btn_delete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel2.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 650, 80, -1));

        btn_close.setBackground(new java.awt.Color(72, 96, 51));
        btn_close.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_close.setForeground(new java.awt.Color(255, 255, 255));
        btn_close.setText("Close");
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });
        jPanel2.add(btn_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, 110, -1));

        btn_save.setBackground(new java.awt.Color(72, 96, 51));
        btn_save.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_save.setForeground(new java.awt.Color(255, 255, 255));
        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });
        jPanel2.add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 650, 90, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/LOGO_main.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 330, 60));

        add_stock.setBackground(new java.awt.Color(72, 96, 51));
        add_stock.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add_stock.setForeground(new java.awt.Color(255, 255, 255));
        add_stock.setText("Add Stock");
        add_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_stockActionPerformed(evt);
            }
        });
        jPanel2.add(add_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 650, 100, -1));

        stockadd.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jPanel2.add(stockadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 650, 90, -1));

        email_lbl4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        email_lbl4.setForeground(new java.awt.Color(72, 96, 51));
        email_lbl4.setText("Amount of stock to add:");
        jPanel2.add(email_lbl4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 650, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 770));

        setSize(new java.awt.Dimension(1063, 769));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
    int selectedRow = table_cm.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a catalogue entry to delete.");
        return;
    }

    DefaultTableModel model = (DefaultTableModel) table_cm.getModel();
    String catalogueId = model.getValueAt(selectedRow, 0).toString().trim(); // Ensure it's trimmed

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Permanently delete plant with Catalogue ID: " + catalogueId + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        db.activateConn();

        String deleteSQL = "DELETE FROM PLANT_CATALOGUE WHERE CATALOGUE_ID = ?";
        try (PreparedStatement pstmt = db.conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, catalogueId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                // Force commit to ensure deletion is written to DB
                db.conn.commit();
                JOptionPane.showMessageDialog(this, "Plant entry deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No plant entry found with Catalogue ID: " + catalogueId);
            }
        }
    } catch (SQLException ex) {
        try {
            if (db.conn != null) {
                db.conn.rollback(); // rollback if something goes wrong
            }
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Error deleting plant entry:\n" + ex.getMessage());
        ex.printStackTrace();
    } finally {
        db.closeConnection();
    }

    Select(); 
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed

        ADMIN_dashboard dashboard = new ADMIN_dashboard();
        this.dispose();
        dashboard.setVisible(true);
    }//GEN-LAST:event_btn_closeActionPerformed

    private void search_catalogueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_catalogueActionPerformed
    String searchQuery = search_catalogue.getText().trim().toLowerCase();
    DefaultTableModel model = (DefaultTableModel) table_cm.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    table_cm.setRowSorter(sorter);

    if (searchQuery.isEmpty()) {
        sorter.setRowFilter(null); 
    } else {
        RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String plantName = entry.getStringValue(1).toLowerCase(); 
                String scientificName = entry.getStringValue(3).toLowerCase();
                
                return plantName.contains(searchQuery) || scientificName.contains(searchQuery);
            }
        };
        sorter.setRowFilter(filter);
    }
    }//GEN-LAST:event_search_catalogueActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        PlantModels.addRow(new Object[]{"", "", "", "", 0, 0, 0});
        int newRowIndex = PlantModels.getRowCount() - 1;
        table_cm.changeSelection(newRowIndex, 0, false, false);
    
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
    int selectedRow = table_cm.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a row to save.");
        return;
    }

    try {
        String cid = table_cm.getValueAt(selectedRow, 0).toString();
        String pn = table_cm.getValueAt(selectedRow, 1).toString();
        String c = table_cm.getValueAt(selectedRow, 2).toString();
        String sn = table_cm.getValueAt(selectedRow, 3).toString();

        int p = Integer.parseInt(table_cm.getValueAt(selectedRow, 4).toString());
        int sq = Integer.parseInt(table_cm.getValueAt(selectedRow, 5).toString());
        int ts = Integer.parseInt(table_cm.getValueAt(selectedRow, 6).toString());

        db.activateConn();

        // Optional: turn off auto-commit (if not already)
        db.conn.setAutoCommit(false);

        String sql = "INSERT INTO PLANT_CATALOGUE (CATALOGUE_ID, PLANT_NAME, CATEGORY, SCIENTIFIC_NAME, PRICE, STOCK_QUANTITY, TOTAL_STOCK) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = db.conn.prepareStatement(sql);

        pstmt.setString(1, cid);
        pstmt.setString(2, pn);
        pstmt.setString(3, c);
        pstmt.setString(4, sn);
        pstmt.setInt(5, p);
        pstmt.setInt(6, sq);
        pstmt.setInt(7, ts);

        int rowsInserted = pstmt.executeUpdate();

        if (rowsInserted > 0) {
            db.conn.commit();  // Commit transaction

            JOptionPane.showMessageDialog(this, "Record saved successfully.");
            Select();  // Refresh the table
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save record.");
        }

        db.closeConnection();

    } catch (NumberFormatException nfe) {
        JOptionPane.showMessageDialog(this, "Price and Stock Quantity must be valid numbers.");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving record:\n" + ex.getMessage());
    }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void add_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_stockActionPerformed
        // TODO add your handling code here:
        int selectedRow = table_cm.getSelectedRow();
        if (selectedRow > -1) {
            int stockamount = (int) stockadd.getValue();
            int STOCK_QUANTITY = Integer.parseInt(table_cm.getValueAt(selectedRow, 5).toString());
            int TOTAL_STOCK = Integer.parseInt(table_cm.getValueAt(selectedRow, 6).toString());
            String CATALOGUE_ID = table_cm.getValueAt(selectedRow, 0).toString();

            int newSTOCK_QUANTITY = STOCK_QUANTITY + stockamount;
            int newTOTAL_STOCK = TOTAL_STOCK + stockamount;

            try {
                String stmt = "UPDATE PLANT_CATALOGUE SET STOCK_QUANTITY = ?, TOTAL_STOCK = ? WHERE CATALOGUE_ID = ?";
                PreparedStatement pstmt = dbConn.conn.prepareStatement(stmt);
                pstmt.setString(1, Integer.toString(newSTOCK_QUANTITY));
                pstmt.setString(2, Integer.toString(newTOTAL_STOCK));
                pstmt.setString(3, CATALOGUE_ID);

                pstmt.executeUpdate();
                dbConn.conn.commit();
                Select();
            } catch (SQLException ex) {

            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
            
        
    }//GEN-LAST:event_add_stockActionPerformed

    private void search_catalogueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_catalogueKeyReleased
        // TODO add your handling code here:
        String searchQuery = search_catalogue.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) table_cm.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table_cm.setRowSorter(sorter);

        if (searchQuery.isEmpty()) {
            sorter.setRowFilter(null); 
        } else {
            RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
                @Override
                public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
                    String plantName = entry.getStringValue(1).toLowerCase(); 
                    String scientificName = entry.getStringValue(3).toLowerCase();

                    return plantName.contains(searchQuery) || scientificName.contains(searchQuery);
                }
            };
            sorter.setRowFilter(filter);
        }
    }//GEN-LAST:event_search_catalogueKeyReleased

    private void Select() {
        String[] columnNames = {"CATALOGUE_ID", "PLANT_NAME", "CATEGORY", "SCIENTIFIC_NAME", "PRICE", "STOCK_QUANTITY", "TOTAL_STOCK"};
        PlantModels.setColumnIdentifiers(columnNames);
        PlantModels.setRowCount(0);

        try {
            Statement stmt = dbConn.conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLANT_CATALOGUE");

            while (rs.next()) {
                String cid = rs.getString("CATALOGUE_ID");
                String pn = rs.getString("PLANT_NAME");
                String c = rs.getString("CATEGORY");
                String sn = rs.getString("SCIENTIFIC_NAME");
                int p = rs.getInt("PRICE");
                int sq = rs.getInt("STOCK_QUANTITY"); 
                int ts = rs.getInt("TOTAL_STOCK");
                
                table_cm.setVisible(true);
                PlantModels.addRow(new Object[]{cid, pn, c, sn, p, sq, ts});
            }

            setTableDesign();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(ADMIN_cataloguemanager.this, "Error loading account records:\n" + err.getMessage());
        }
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
            * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
            */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(ADMIN_cataloguemanager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(ADMIN_cataloguemanager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(ADMIN_cataloguemanager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(ADMIN_cataloguemanager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
            
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new ADMIN_cataloguemanager().setVisible(true);
                }
            });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ADMIN_cataloguemanager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ADMIN_cataloguemanager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ADMIN_cataloguemanager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ADMIN_cataloguemanager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_stock;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel email_lbl4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search_catalogue;
    private javax.swing.JSpinner stockadd;
    private javax.swing.JTable table_cm;
    // End of variables declaration//GEN-END:variables
}
