/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
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

/**
 *
 * @author WINDOWS
 */
public class ADMIN_accountrecords extends javax.swing.JFrame {

    private final connection2 db = new connection2();
    private final DefaultTableModel AccountModels = new DefaultTableModel();

    /**
     * Creates new form USER_login
     */
    public ADMIN_accountrecords() {
        initComponents();
        tbl_records.setModel(AccountModels);
        Select();
    }

    private void setTableDesign() {
        tbl_records.getTableHeader().setBackground(new Color(255, 255, 255));
        tbl_records.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tbl_records.getTableHeader().setForeground(new Color(72, 96, 51));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tbl_records.getTableHeader().setDefaultRenderer(headerRenderer);

        tbl_records.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tbl_records.setRowHeight(30);
        tbl_records.setSelectionBackground(new Color(72, 96, 51));
        tbl_records.setSelectionForeground(Color.WHITE);
    }

    public void refreshTable() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                AccountModels.setRowCount(0);
                Select();
                return null;
            }

            @Override
            protected void done() {
                tbl_records.repaint();
            }
        };
        worker.execute();
    }

    private void deleteSelectedAccount() {
        int selectedRow = tbl_records.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to remove.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbl_records.getModel();
        String employeeEmail = (String) model.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Permanently delete employee with email: " + employeeEmail + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            db.activateConn();
            String deleteSQL = "DELETE FROM ACCOUNTS WHERE EMAIL = ?";

            try (PreparedStatement pstmt = db.conn.prepareStatement(deleteSQL)) {
                pstmt.setString(1, employeeEmail);
                int rowsDeleted = pstmt.executeUpdate();

                if (rowsDeleted > 0) {
                    db.conn.commit();
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(this, "Employee permanently deleted.");
                } else {
                    db.conn.rollback();
                    JOptionPane.showMessageDialog(this, "No employee found with email: " + employeeEmail);
                }
            }
        } catch (SQLException ex) {
            try {
                if (db.conn != null) {
                    db.conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Error deleting employee: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            db.closeConnection();
        }
        refreshTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search_records = new javax.swing.JTextField();
        email_lbl1 = new javax.swing.JLabel();
        email_lbl2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_records = new javax.swing.JTable();
        close_btn = new javax.swing.JButton();
        remove_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(72, 96, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/LOGO_main.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 120));

        search_records.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_recordsActionPerformed(evt);
            }
        });
        jPanel1.add(search_records, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 660, 20));

        email_lbl1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        email_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        email_lbl1.setText("Search by last name:");
        jPanel1.add(email_lbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, -1, -1));

        email_lbl2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        email_lbl2.setForeground(new java.awt.Color(255, 255, 255));
        email_lbl2.setText("Employee Account Records");
        jPanel1.add(email_lbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 290, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, 0, 1260, 90));

        jPanel3.setBackground(new java.awt.Color(238, 235, 235));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_records.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_records);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 930, 500));

        close_btn.setBackground(new java.awt.Color(72, 96, 51));
        close_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        close_btn.setForeground(new java.awt.Color(255, 255, 255));
        close_btn.setText("Close");
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });
        jPanel3.add(close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, 100, 30));

        remove_btn.setBackground(new java.awt.Color(72, 96, 51));
        remove_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        remove_btn.setForeground(new java.awt.Color(255, 255, 255));
        remove_btn.setText("Remove");
        remove_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_btnActionPerformed(evt);
            }
        });
        jPanel3.add(remove_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 570, 100, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1120, 670));

        setSize(new java.awt.Dimension(1063, 766));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void search_recordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_recordsActionPerformed

        String searchQuery = search_records.getText().trim().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tbl_records.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tbl_records.setRowSorter(sorter);

        if (searchQuery.isEmpty()) {
            sorter.setRowFilter(null);
        } else {

            RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
                @Override
                public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                    String firstname = entry.getStringValue(2).toLowerCase();
                    String lastname = entry.getStringValue(3).toLowerCase();

                    return firstname.contains(searchQuery) || lastname.contains(searchQuery);
                }
            };
            sorter.setRowFilter(filter);
        }

    }//GEN-LAST:event_search_recordsActionPerformed

    private void remove_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_btnActionPerformed
        deleteSelectedAccount();
    }//GEN-LAST:event_remove_btnActionPerformed

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
        this.dispose();

    }//GEN-LAST:event_close_btnActionPerformed

    private void Select() {
        String[] columnNames = {"Email Account", "First Name", "Last Name", "Birth Date", "Address", "Contact #", "Account Type"};
        AccountModels.setColumnIdentifiers(columnNames);
        AccountModels.setRowCount(0);

        try {
            db.activateConn();

            ResultSet rs = db.stmt.executeQuery("SELECT * FROM ACCOUNTS");

            while (rs.next()) {
                String e = rs.getString("EMAIL");
                String n = rs.getString("FIRSTNAME");
                String l = rs.getString("LASTNAME");
                String bd = rs.getDate("BIRTHDATE").toString();
                String ad = rs.getString("ADDRESS");
                int cn = rs.getInt("CONTACTNUM");
                String acc = rs.getString("ACC_TYPE");

                tbl_records.setVisible(true);
                AccountModels.addRow(new Object[]{e, n, l, bd, ad, cn, acc});
            }

            db.closeConnection();
            setTableDesign();

        } catch (SQLException err) {
            JOptionPane.showMessageDialog(ADMIN_accountrecords.this, "Error loading account records:\n" + err.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ADMIN_accountrecords.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ADMIN_accountrecords().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close_btn;
    private javax.swing.JLabel email_lbl1;
    private javax.swing.JLabel email_lbl2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton remove_btn;
    private javax.swing.JTextField search_records;
    private javax.swing.JTable tbl_records;
    // End of variables declaration//GEN-END:variables
}
