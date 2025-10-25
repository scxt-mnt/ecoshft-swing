/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication13;

import java.awt.Color;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author borag
 */
public class productsFrames extends javax.swing.JFrame {

    /**
     * Creates new form productsFrames
     */
    Connection conn;

    public void callData() {

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );

            // select data 
            String query = "SELECT * FROM productstable";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet res = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("product name");
            model.addColumn("product type");
            model.addColumn("price");
            model.addColumn("quantity");
            model.addColumn("created");

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("product name");
                String type = res.getString("product type");
                String price = res.getString("price");
                String quantity = res.getString("quantity");
                String created = res.getString("product_created");

                model.addRow(new Object[]{id, name, type, "P" + price, quantity, created});
            }

            myTable.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        };
    }

    private void textChanged() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );

            // select data 
            String query = "SELECT * FROM `productstable` WHERE `product name` = ? ";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, searchInput.getText());
            ResultSet res = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("product name");
            model.addColumn("product type");
            model.addColumn("price");
            model.addColumn("quantity");

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("product name");
                String type = res.getString("product type");
                String price = res.getString("price");
                String quantity = res.getString("quantity");

                model.addRow(new Object[]{id, name, type, price, quantity});
            }

            myTable.setModel(model);

            if (searchInput.getText().isEmpty()) {
                callData();
            }
        } catch (Exception e) {
        }

    }

    private void idChange() {
        try {

            if (editId.getText().isEmpty()) {
                editName.setText("");
                editType.setText("");
                editPrice.setText("");
                editQuantity.setText("");
            } else {
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:5050/mydatabase",
                        "root",
                        ""
                );

                String query = "SELECT * FROM `productstable` WHERE id = ?";
                PreparedStatement statement = conn.prepareStatement(query);

                statement.setInt(1, Integer.parseInt(editId.getText()));

                ResultSet res = statement.executeQuery();

                if (res.next()) {
                    editName.setText(res.getString("product name"));
                    editType.setText(res.getString("product type"));
                    editPrice.setText(res.getString("price"));
                    editQuantity.setText(res.getString("quantity"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public productsFrames() {
        initComponents();
        viewLabel.setOpaque(true);
        viewLabel.setBackground(Color.white);
        addPanel.setVisible(false);
        editPanel.setVisible(false);

        searchInput.getDocument().addDocumentListener(new DocumentListener() {
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
        });

        editId.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                idChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                idChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                idChange();
            }
        });

        deletePanel.setVisible(false);

        setLocationRelativeTo(null);

        callData();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        myPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        viewLabel = new javax.swing.JLabel();
        addLabel = new javax.swing.JLabel();
        editLabel = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        deleteLabel = new javax.swing.JLabel();
        viewPanel = new javax.swing.JPanel();
        deletePanel = new javax.swing.JPanel();
        idDeleteInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        editPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        editId = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        editName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        editType = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        editPrice = new javax.swing.JTextField();
        editQuantity = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        addPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        productList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myTable = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        searchLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        searchInput = new javax.swing.JTextField();

        jLabel10.setText("jLabel10");

        jLabel11.setText("jLabel11");

        jLabel12.setText("jLabel12");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        myPanel.setBackground(new java.awt.Color(0, 153, 0));
        myPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Inventory System");
        myPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 30, 190, 20));

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setBackground(new java.awt.Color(204, 0, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("delete product");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 110, 30));

        jButton3.setBackground(new java.awt.Color(0, 102, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 255, 0));
        jButton3.setText("edit");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 100, 30));

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 255, 51));
        jButton1.setText("view");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 90, 30));

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("←dashboard");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/resizecom_ecoshift-removebg-preview (1).png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 36));

        jButton8.setBackground(new java.awt.Color(0, 102, 0));
        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 255, 0));
        jButton8.setText("add");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 100, 30));
        jPanel1.add(viewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 290, 50));
        jPanel1.add(addLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 290, 50));
        jPanel1.add(editLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 290, 50));

        jButton6.setBackground(new java.awt.Color(0, 102, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 255, 0));
        jButton6.setText("delete");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, 30));
        jPanel1.add(deleteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 290, 50));

        myPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 290, 860));

        viewPanel.setBackground(new java.awt.Color(0, 153, 0));
        viewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        deletePanel.setBackground(new java.awt.Color(0, 153, 0));
        deletePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idDeleteInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idDeleteInputActionPerformed(evt);
            }
        });
        deletePanel.add(idDeleteInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 170, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("delete products");
        deletePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("enter product id");
        deletePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, 70));

        deleteButton.setBackground(new java.awt.Color(102, 0, 0));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        deletePanel.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        viewPanel.add(deletePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 410));

        editPanel.setBackground(new java.awt.Color(0, 153, 0));
        editPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Edit Products");
        editPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("product id");
        editPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        editId.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                editIdComponentAdded(evt);
            }
        });
        editId.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                editIdInputMethodTextChanged(evt);
            }
        });
        editId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editIdActionPerformed(evt);
            }
        });
        editPanel.add(editId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 160, 40));

        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("product name");
        editPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        editName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNameActionPerformed(evt);
            }
        });
        editPanel.add(editName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 160, 40));

        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("product type");
        editPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        editType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTypeActionPerformed(evt);
            }
        });
        editPanel.add(editType, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 160, 40));

        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("product quantity");
        editPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        editPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPriceActionPerformed(evt);
            }
        });
        editPanel.add(editPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 180, 40));

        editQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editQuantityActionPerformed(evt);
            }
        });
        editPanel.add(editQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 180, 40));

        jButton10.setBackground(new java.awt.Color(0, 153, 0));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("submit changes");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        editPanel.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, -1));

        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("product price");
        editPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        viewPanel.add(editPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 460));

        addPanel.setBackground(new java.awt.Color(0, 153, 0));
        addPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Add Products");
        addPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 170, 30));

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("product name");
        addPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        addPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 230, 40));

        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("product type");
        addPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 60));
        addPanel.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 230, 40));

        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("price");
        addPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 60));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        addPanel.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 230, 40));

        jButton7.setBackground(new java.awt.Color(0, 153, 0));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("submit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        addPanel.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 80, 30));

        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("quantity");
        addPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, 60));

        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });
        addPanel.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 230, 40));

        viewPanel.add(addPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 450));

        productList.setBackground(new java.awt.Color(0, 204, 0));
        productList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        myTable.setBackground(new java.awt.Color(0, 102, 0));
        myTable.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        myTable.setForeground(new java.awt.Color(255, 255, 255));
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
        jScrollPane1.setViewportView(myTable);

        productList.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, -1));

        viewPanel.add(productList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 450));

        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("product quantity");
        viewPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        myPanel.add(viewPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 650, 450));

        searchLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(204, 204, 204));
        searchLabel.setText("search by product name");
        myPanel.add(searchLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 180, -1));
        myPanel.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, -1));

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
        myPanel.add(searchInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 280, 40));

        getContentPane().add(myPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deletePanel.setVisible(false);
        deleteLabel.setVisible(false);
        searchInput.setVisible(true);
        searchLabel.setVisible(true);
        editPanel.setVisible(false);
        viewLabel.setOpaque(true);
        viewLabel.setVisible(true);
        productList.setVisible(true);
        addPanel.setVisible(false);
        viewLabel.setBackground(Color.white);
        addLabel.setVisible(false);
        editLabel.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new dashboard("").setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        searchInput.setVisible(false);
        searchLabel.setVisible(false);
        deletePanel.setVisible(false);
        deleteLabel.setVisible(false);
        productList.setVisible(false);
        addPanel.setVisible(false);
        editPanel.setVisible(true);
        viewLabel.setVisible(false);
        addLabel.setVisible(false);
        editLabel.setVisible(true);
        editLabel.setBackground(Color.white);
        editLabel.setOpaque(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        deletePanel.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void idDeleteInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idDeleteInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idDeleteInputActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
            String query = "DELETE FROM `productstable` WHERE id = ?";
            PreparedStatement queries = this.conn.prepareStatement(query);
            queries.setInt(1, Integer.parseInt(idDeleteInput.getText()));

            int result = JOptionPane.showConfirmDialog(
                    null,
                    "are you sure you want to continue?",
                    "confimation",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {

                int res = queries.executeUpdate();

                if (res > 0) {
                    JOptionPane.showMessageDialog(this, "product id: " + idDeleteInput.getText() + " deleted");
                } else {
                    JOptionPane.showMessageDialog(this, "no id: " + idDeleteInput.getText() + " exist");
                }

                callData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_deleteButtonActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );

            // select data
            String query = "INSERT INTO `productstable`( `product name`, `product type`, `price`, `quantity`) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, jTextField1.getText());
            statement.setString(2, jTextField2.getText());
            statement.setInt(3, Integer.parseInt(jTextField3.getText()));
            statement.setInt(4, Integer.parseInt(quantity.getText()));
            JOptionPane.showMessageDialog(this, "sucessfully added!");

            statement.execute();
            
            callData();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "wrong value types");
            e.printStackTrace();
        };
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        deletePanel.setVisible(false);
        deleteLabel.setVisible(false);
        searchInput.setVisible(false);
        searchLabel.setVisible(false);
        editPanel.setVisible(false);
        addPanel.setVisible(true);
        productList.setVisible(false);
        addLabel.setOpaque(true);
        addLabel.setBackground(Color.white);
        viewLabel.setVisible(false);
        addLabel.setVisible(true);
        editLabel.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void editIdComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_editIdComponentAdded

    }//GEN-LAST:event_editIdComponentAdded

    private void editIdInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_editIdInputMethodTextChanged

    }//GEN-LAST:event_editIdInputMethodTextChanged

    private void editIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editIdActionPerformed

    }//GEN-LAST:event_editIdActionPerformed

    private void editNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editNameActionPerformed

    private void editTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editTypeActionPerformed

    private void editPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editPriceActionPerformed

    private void editQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editQuantityActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );

            // select data
            String query = "UPDATE `productstable` SET `product name`=?,`product type`=?,`price`=?,`quantity`=? WHERE `id` = ?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, editName.getText());
            statement.setString(2, editType.getText());
            statement.setInt(3, Integer.parseInt(editPrice.getText()));
            statement.setInt(4, Integer.parseInt(editQuantity.getText()));
            statement.setInt(5, Integer.parseInt(editId.getText()));

            int res = statement.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(this, "successfully edited");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:5050/mydatabase",
                        "root",
                        ""
                );

                // select data 
                String selectQuery = "SELECT * FROM productstable";
                PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
                ResultSet selectRes = selectStatement.executeQuery();

                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("ID");
                model.addColumn("product name");
                model.addColumn("product type");
                model.addColumn("price");
                model.addColumn("quantity");
                model.addColumn("created");

                while (selectRes.next()) {
                    int id = selectRes.getInt("id");
                    String name = selectRes.getString("product name");
                    String type = selectRes.getString("product type");
                    String price = selectRes.getString("price");
                    String quantity = selectRes.getString("quantity");
                    String created = selectRes.getString("product_created");

                    model.addRow(new Object[]{id, name, type, "P" + price, quantity, created});
                }

                myTable.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "can't find id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void searchInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInputActionPerformed

    private void searchInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchInputFocusGained
        searchLabel.setVisible(false);
    }//GEN-LAST:event_searchInputFocusGained

    private void searchInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchInputFocusLost
        searchLabel.setVisible(true);
    }//GEN-LAST:event_searchInputFocusLost

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        addPanel.setVisible(false);
        addLabel.setVisible(false);
        editPanel.setVisible(false);
        editLabel.setVisible(false);
        productList.setVisible(false);
        productList.setVisible(false);
        searchInput.setVisible(false);
        searchLabel.setVisible(false);
        viewLabel.setVisible(false);

        deletePanel.setVisible(true);
        deleteLabel.setVisible(true);
        deleteLabel.setOpaque(true);
        deleteLabel.setBackground(Color.white);

    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(productsFrames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(productsFrames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(productsFrames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(productsFrames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new productsFrames().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addLabel;
    private javax.swing.JPanel addPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel deleteLabel;
    private javax.swing.JPanel deletePanel;
    private javax.swing.JTextField editId;
    private javax.swing.JLabel editLabel;
    private javax.swing.JTextField editName;
    private javax.swing.JPanel editPanel;
    private javax.swing.JTextField editPrice;
    private javax.swing.JTextField editQuantity;
    private javax.swing.JTextField editType;
    private javax.swing.JTextField idDeleteInput;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel myPanel;
    private javax.swing.JTable myTable;
    private javax.swing.JPanel productList;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField searchInput;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JLabel viewLabel;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
}
