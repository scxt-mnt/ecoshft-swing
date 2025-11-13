/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication13;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author borag
 */
public class SalesTrackerSystem extends javax.swing.JFrame {

    /**
     * Creates new form addSales
     */
    
    
    
    public void updateProdDetails() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    "");

            String query = "SELECT * FROM productstable WHERE `product name` = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(comboBox.getSelectedItem()));
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                labelPrice.setText("Price: " + res.getString("price"));
                labelQuantity.setText("Quantity: " + res.getString("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
     public void addListenerToFocus(JTextComponent textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textChanged();
            }
        });}
    
    
     private void textChanged() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );

            // select data 
            String query = "SELECT * FROM `productsales` WHERE `product name` = ? OR `quantity` LIKE ?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, "%" + searchInput.getText() + "%");
            statement.setString(2, "%" + searchInput.getText() + "%");
     

            ResultSet res = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("Quantity");


            while (res.next()) {
                String name = res.getString("product name");
                String quantity = res.getString("quantity");
                
                model.addRow(new Object[]{name, quantity});
            }

            myTable.setModel(model);

            if (searchInput.getText().isEmpty()) {
                refreshTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void refreshTable() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );

            String query = "SELECT b.`product name`, b.quantity, a.price "
                    + "FROM productstable a "
                    + "JOIN productsales b ON a.`product name` = b.`product name`";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet res = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Product Name");
            model.addColumn("Quantity");

            int sales = 0;
            while (res.next()) {
                String name = res.getString("product name");
                int quantity = res.getInt("quantity");
                int price = res.getInt("price");

                sales += price * quantity;

                model.addRow(new Object[]{name, quantity});
            }

            totalSales.setText("P " + sales);
            myTable.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    String credential;

    public SalesTrackerSystem(String credential) {
        initComponents();
        this.credential = credential;
        setLocationRelativeTo(null);

        try {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );
            String selectQuery = "SELECT * FROM `productstable` ";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            ResultSet selectRes = selectStatement.executeQuery();
            comboBox.removeAllItems();
            while (selectRes.next()) {
                String name = selectRes.getString("product name");
                System.out.print(name);
                comboBox.addItem(selectRes.getString("product name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        refreshTable();
        addListenerToFocus(searchInput);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        totalSales = new javax.swing.JLabel();
        labelQuantity = new javax.swing.JLabel();
        labelPrice = new javax.swing.JLabel();
        searchInput = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        myTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        productAmount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jTextField2.setText("jTextField2");

        jButton1.setText("jButton1");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("total sales:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, -1, -1));

        totalSales.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalSales.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(totalSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, 90, 20));

        labelQuantity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelQuantity.setForeground(new java.awt.Color(255, 255, 255));
        labelQuantity.setDoubleBuffered(true);
        getContentPane().add(labelQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 190, 20));

        labelPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelPrice.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(labelPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 160, 20));

        searchInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchInputFocusLost(evt);
            }
        });
        searchInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInputActionPerformed(evt);
            }
        });
        getContentPane().add(searchInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 260, 30));

        myTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(myTable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 400, 280));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/icons8-back-48.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, 36));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/icons8-exit-50 (1).png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 40, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Dashboard");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 70, -1));

        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(comboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 160, 30));

        jToggleButton1.setText("submit");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, -1, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("quantity");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("product lists");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Add Sales");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 190, 20));

        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 220, 470));

        productAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productAmountActionPerformed(evt);
            }
        });
        getContentPane().add(productAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 170, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/resizecom_background ecoShift (1) (1).png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:5050/mydatabase",
                "root",
                ""
        )) {
            String selectedProduct = comboBox.getSelectedItem().toString();
            int requestedQuantity = Integer.parseInt(productAmount.getText());

            // 1️⃣ Kunin ang product details
            String selectQuery = "SELECT * FROM productstable WHERE `product name` = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setString(1, selectedProduct);
                try (ResultSet res = selectStmt.executeQuery()) {

                    if (!res.next()) {
                        JOptionPane.showMessageDialog(this, "No product " + selectedProduct + " found");
                        return;
                    }

                    int initialQuantity = res.getInt("quantity");
                    int totalQuantity = initialQuantity - requestedQuantity;

                    if (totalQuantity < 0) {
                        JOptionPane.showMessageDialog(this, "Out of stocks");
                        return;
                    }

                    String insertQuery = "INSERT INTO productsales(`quantity`, `product name`) VALUES (?, ?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, requestedQuantity);
                        insertStmt.setString(2, selectedProduct);
                        insertStmt.executeUpdate();
                    }

                    String updateQuery = "UPDATE productstable SET quantity = ? WHERE `product name` = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, totalQuantity);
                        updateStmt.setString(2, selectedProduct);
                        updateStmt.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(this, "Successfully sold");
                    refreshTable();

                }
            }

            updateProdDetails();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Invalid quantity input");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed on selection");
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void productAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productAmountActionPerformed
        // TODO add your handling code ehre:
    }//GEN-LAST:event_productAmountActionPerformed

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        updateProdDetails();
    }//GEN-LAST:event_comboBoxActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        new dashboardAdmin(credential).setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        try {
            String query = "SELECT status FROM `userdetails` WHERE `username` = ?";
            PreparedStatement statement = productsFrames.conn().prepareStatement(query);
            statement.setString(1, credential);
            ResultSet res = statement.executeQuery();

            if (res.next() && res.getString("status").equals("admin")) {
                new dashboardAdmin(credential).setVisible(true);
            } else {
                new dashboardEmployee(credential).setVisible(true);
            }

            this.dispose();
        } catch (SQLException e) {
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void searchInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchInputFocusGained
    }//GEN-LAST:event_searchInputFocusGained

    private void searchInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchInputFocusLost
    }//GEN-LAST:event_searchInputFocusLost

    private void searchInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInputActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalesTrackerSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesTrackerSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesTrackerSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesTrackerSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesTrackerSystem("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel labelPrice;
    private javax.swing.JLabel labelQuantity;
    private javax.swing.JTable myTable;
    private javax.swing.JTextField productAmount;
    private javax.swing.JTextField searchInput;
    private javax.swing.JLabel totalSales;
    // End of variables declaration//GEN-END:variables
}
