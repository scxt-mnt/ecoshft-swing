/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication13;

import java.awt.Color;
import java.awt.event.FocusListener;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author borag
 */
public class productsFrames extends javax.swing.JFrame {

    /**
     * Creates new form productsFrames
     *
     */
    public static Connection conn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    String credential;

    public productsFrames(String credential) {
        initComponents();
        this.credential = credential;
        viewLabel.setOpaque(true);
        viewLabel.setBackground(Color.white);
        addPanel.setVisible(false);
        editPanel.setVisible(false);
        addListenerToFocus(searchInput);
        addListenerToFocus(searchInput2);

        setLocationRelativeTo(null);

        callData();

        try {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );
            String selectQuery = "SELECT * FROM `productstable`";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            ResultSet selectRes = selectStatement.executeQuery();
            editId.removeAllItems();
            while (selectRes.next()) {
                String name = selectRes.getString("product name");
                editId.addItem(selectRes.getString("product name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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

            myTable1.setModel(model);

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
            String query = "SELECT * FROM `productstable` WHERE `product name` LIKE ? OR `product type` LIKE ? OR `price` LIKE ? OR `quantity` LIKE ? OR `product_created` LIKE ?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, "%" + searchInput.getText() + "%");
            statement.setString(2, "%" + searchInput.getText() + "%");
            statement.setString(3, "%" + searchInput.getText() + "%");
            statement.setString(4, "%" + searchInput.getText() + "%");
            statement.setString(5, "%" + searchInput.getText() + "%");

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

            myTable1.setModel(model);

            if (searchInput.getText().isEmpty()) {
                callData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void textChangedEdit() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );

            // select data 
            String query = "SELECT * FROM `productstable` WHERE `product name` LIKE ? OR `product type` LIKE ? OR `price` LIKE ? OR `quantity` LIKE ? OR `product_created` LIKE ?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, "%" + searchInput2.getText() + "%");
            statement.setString(2, "%" + searchInput2.getText() + "%");
            statement.setString(3, "%" + searchInput2.getText() + "%");
            statement.setString(4, "%" + searchInput2.getText() + "%");
            statement.setString(5, "%" + searchInput2.getText() + "%");

            ResultSet res = statement.executeQuery();

            editId.removeAllItems();
            while (res.next()) {
                editId.addItem(res.getString("product name"));
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
                textChangedEdit();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textChanged();
                textChangedEdit();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textChanged();
                textChangedEdit();
            }
        });

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
        jLabel12 = new javax.swing.JLabel();
        exitBtn = new javax.swing.JLabel();
        myPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        viewBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        viewLabel = new javax.swing.JLabel();
        addLabel = new javax.swing.JLabel();
        editLabel = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        deleteLabel = new javax.swing.JLabel();
        exitBtn1 = new javax.swing.JLabel();
        viewPanel = new javax.swing.JPanel();
        editPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        editName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        editType = new javax.swing.JTextField();
        NumberFormat format1 = NumberFormat.getNumberInstance();
        NumberFormatter formatter1 = new NumberFormatter(format1);
        formatter1.setAllowsInvalid(true);
        formatter1.setMinimum(0.0);
        formatter1.setCommitsOnValidEdit(true);
        editPrice = new javax.swing.JFormattedTextField(formatter1);
        jLabel17 = new javax.swing.JLabel();
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0);
        editQuantity = new javax.swing.JFormattedTextField(formatter);
        jButton10 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        editId = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        addPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NumberFormat format2 = NumberFormat.getNumberInstance();
        NumberFormatter formatter2 = new NumberFormatter(format2);
        formatter2.setAllowsInvalid(true);
        formatter2.setMinimum(0.0);
        formatter2.setCommitsOnValidEdit(true);
        jTextField3 = new javax.swing.JFormattedTextField(formatter2);
        jTextField2 = new javax.swing.JTextField();
        NumberFormat format4 = NumberFormat.getIntegerInstance();
        NumberFormatter formatter4 = new NumberFormatter(format4);
        formatter4.setAllowsInvalid(false);
        formatter4.setMinimum(0);
        quantity = new javax.swing.JFormattedTextField(formatter4);
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        productList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myTable1 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        searchInput2 = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        searchInput = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();

        jLabel10.setText("jLabel10");

        jLabel12.setText("jLabel12");

        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/icons8-exit-50 (1).png"))); // NOI18N
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitBtnMouseEntered(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        myPanel.setBackground(new java.awt.Color(0, 153, 0));
        myPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        editBtn.setBackground(new java.awt.Color(0, 102, 0));
        editBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(0, 255, 0));
        editBtn.setText("edit");
        editBtn.setBorder(null);
        editBtn.setBorderPainted(false);
        editBtn.setContentAreaFilled(false);
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editBtnMouseExited(evt);
            }
        });
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 370, 320, 50));

        viewBtn.setBackground(new java.awt.Color(0, 102, 0));
        viewBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        viewBtn.setForeground(new java.awt.Color(51, 255, 51));
        viewBtn.setText("view");
        viewBtn.setBorder(null);
        viewBtn.setBorderPainted(false);
        viewBtn.setContentAreaFilled(false);
        viewBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                viewBtnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                viewBtnFocusLost(evt);
            }
        });
        viewBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewBtnMouseExited(evt);
            }
        });
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });
        jPanel1.add(viewBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 270, 320, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dashboard");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 70, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/icons8-back-48.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, 36));

        addBtn.setBackground(new java.awt.Color(0, 102, 0));
        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 255, 0));
        addBtn.setText("add");
        addBtn.setBorder(null);
        addBtn.setBorderPainted(false);
        addBtn.setContentAreaFilled(false);
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addBtnMouseExited(evt);
            }
        });
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 320, 320, 50));

        viewLabel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                viewLabelFocusGained(evt);
            }
        });
        jPanel1.add(viewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 290, 50));
        jPanel1.add(addLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 290, 50));

        editLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editLabelMouseExited(evt);
            }
        });
        jPanel1.add(editLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 290, 50));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/resizecom_background ecoShift (1).png"))); // NOI18N
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 860));

        myPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 290, 860));

        deleteLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/resizecom_icons8-delete-48.png"))); // NOI18N
        deleteLabel.setText("delete product");
        deleteLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteLabelMouseClicked(evt);
            }
        });
        myPanel.add(deleteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 220, 110, 30));

        exitBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/icons8-exit-50 (1).png"))); // NOI18N
        exitBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitBtn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitBtn1MouseEntered(evt);
            }
        });
        myPanel.add(exitBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 10, 40, 40));

        viewPanel.setBackground(new java.awt.Color(0, 153, 0));
        viewPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editPanel.setBackground(new java.awt.Color(0, 153, 0));
        editPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Edit Products");
        editPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("product id");
        editPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

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

        editPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPriceActionPerformed(evt);
            }
        });
        editPanel.add(editPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 190, 40));

        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("product quantity");
        editPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        editQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editQuantityActionPerformed(evt);
            }
        });
        editPanel.add(editQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 190, 40));

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

        editId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editIdActionPerformed(evt);
            }
        });
        editPanel.add(editId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 160, 30));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/resizecom_background ecoShift (1).png"))); // NOI18N
        editPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 740, 430));

        viewPanel.add(editPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 620, 410));

        addPanel.setBackground(new java.awt.Color(0, 153, 0));
        addPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Add Products");
        addPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 170, 30));

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("product name");
        addPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        addPanel.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 230, 40));
        addPanel.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 230, 40));

        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });
        addPanel.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 220, 40));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        addPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 230, 40));

        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("product type");
        addPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 60));

        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("price");
        addPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 60));

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

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/resizecom_background ecoShift (1).png"))); // NOI18N
        addPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 430));

        viewPanel.add(addPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 410));

        productList.setBackground(new java.awt.Color(0, 204, 0));
        productList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        myTable1.setBackground(new java.awt.Color(0, 102, 0));
        myTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        myTable1.setForeground(new java.awt.Color(255, 255, 255));
        myTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(myTable1);

        productList.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 420));

        viewPanel.add(productList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 420));

        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("product quantity");
        viewPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/resizecom_icons8-delete-48.png"))); // NOI18N
        jLabel11.setText("delete history");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        viewPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 110, 30));

        searchInput2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchInput2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchInput2FocusLost(evt);
            }
        });
        searchInput2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInput2ActionPerformed(evt);
            }
        });
        viewPanel.add(searchInput2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 280, 40));

        myPanel.add(viewPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 640, 410));

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

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication13/resizecom_background ecoShift (1) (1).png"))); // NOI18N
        myPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1160, 900));

        getContentPane().add(myPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
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

        searchInput2.setVisible(false);
        deleteLabel.setVisible(true);
    }//GEN-LAST:event_viewBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        searchInput.setVisible(false);
        searchLabel.setVisible(false);
        productList.setVisible(false);
        addPanel.setVisible(false);
        editPanel.setVisible(true);
        viewLabel.setVisible(false);
        addLabel.setVisible(false);
        editLabel.setVisible(true);
        editLabel.setBackground(Color.white);
        editLabel.setOpaque(true);
        searchInput2.setVisible(true);
        deleteLabel.setVisible(false);
    }//GEN-LAST:event_editBtnActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            statement.setDouble(3, Double.parseDouble(jTextField3.getText()));
            statement.setInt(4, Integer.parseInt(quantity.getText().replace(",", "")));
            JOptionPane.showMessageDialog(this, "sucessfully added!");

            statement.execute();

            callData();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "wrong value types");
            e.printStackTrace();
        };
    }//GEN-LAST:event_jButton7ActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
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
        searchInput2.setVisible(false);
        deleteLabel.setVisible(false);
    }//GEN-LAST:event_addBtnActionPerformed

    private void editNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editNameActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    ""
            );

            // select data
            String query = "UPDATE `productstable` SET `product name`= ?,`product type`= ?,`price`= ?,`quantity`= ? WHERE `product name` = ?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, editName.getText());
            statement.setString(2, editType.getText());
            statement.setDouble(3, Double.parseDouble(editPrice.getText().replace(",", "")));
            statement.setInt(4, Integer.parseInt(editQuantity.getText().replace(",", "")));
            statement.setString(5, String.valueOf(editId.getSelectedItem()));

            int res = statement.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(this, "successfully edited");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:5050/mydatabase",
                        "root",
                        ""
                );
                editId.removeAllItems();
                String comboQuery = "SELECT `product name` FROM productstable";
                PreparedStatement comboStatement = conn.prepareStatement(comboQuery);
                ResultSet comboRes = comboStatement.executeQuery();

                while (comboRes.next()) {
                    editId.addItem(comboRes.getString("product name"));
                }

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

                myTable1.setModel(model);
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

    private void editIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editIdActionPerformed
        try {

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:5050/mydatabase",
                    "root",
                    "");

            String query = "SELECT * FROM productstable WHERE `product name` = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(editId.getSelectedItem()));
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                editName.setText(res.getString("product name"));
                editQuantity.setText(res.getString("product type"));
                editPrice.setText(res.getString("price"));
                editQuantity.setText(res.getString("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_editIdActionPerformed

    private void searchInput2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchInput2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInput2FocusGained

    private void searchInput2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchInput2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInput2FocusLost

    private void searchInput2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInput2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInput2ActionPerformed

    private void viewLabelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_viewLabelFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_viewLabelFocusGained

    private void viewBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_viewBtnFocusGained
        viewLabel.setVisible(true);
    }//GEN-LAST:event_viewBtnFocusGained

    private void viewBtnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_viewBtnFocusLost
        viewLabel.setVisible(false);
    }//GEN-LAST:event_viewBtnFocusLost

    private void viewBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBtnMouseEntered
        viewBtn.setOpaque(true);
        viewBtn.setBackground(Color.white);
    }//GEN-LAST:event_viewBtnMouseEntered

    private void viewBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBtnMouseExited
        viewBtn.setOpaque(false);
        viewBtn.setBackground(null);
    }//GEN-LAST:event_viewBtnMouseExited

    private void addBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseEntered
        addBtn.setOpaque(true);
        addBtn.setBackground(Color.white);
    }//GEN-LAST:event_addBtnMouseEntered

    private void addBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseExited
        addBtn.setOpaque(false);
        addBtn.setBackground(null);
    }//GEN-LAST:event_addBtnMouseExited

    private void editBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseEntered
        editBtn.setOpaque(true);
        editBtn.setBackground(Color.white);
    }//GEN-LAST:event_editBtnMouseEntered

    private void editLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editLabelMouseExited
        editLabel.setVisible(false);
    }//GEN-LAST:event_editLabelMouseExited

    private void editBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseExited
        editBtn.setOpaque(false);
        editBtn.setBackground(null);
    }//GEN-LAST:event_editBtnMouseExited

    private void exitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMouseClicked
        dispose();
    }//GEN-LAST:event_exitBtnMouseClicked

    private void exitBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtnMouseEntered
        viewLabel.setOpaque(true);
        exitBtn.setBackground(Color.red);
    }//GEN-LAST:event_exitBtnMouseEntered

    private void exitBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtn1MouseClicked
        dispose();
    }//GEN-LAST:event_exitBtn1MouseClicked

    private void exitBtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBtn1MouseEntered
        exitBtn.setBackground(Color.red);
    }//GEN-LAST:event_exitBtn1MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        try {
            String query = "SELECT status FROM `userdetails` WHERE `username` = ?";
            PreparedStatement statement = conn().prepareStatement(query);
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

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        DefaultTableModel model = (DefaultTableModel) myTable1.getModel();
        int SelectedRow = myTable1.getSelectedRow();
        int SelectedColumn = myTable1.getSelectedColumn();

        int confirmation = JOptionPane.showConfirmDialog(this, "are you sure?");

        if (confirmation == 0) {
            int value = (Integer) model.getValueAt(SelectedRow, 2);
            try {
                String query = "DELETE FROM `loginhistory` WHERE `historyId` = ?";
                PreparedStatement statement = productsFrames.conn().prepareStatement(query);
                statement.setInt(1, value);
                statement.executeUpdate();

                callData();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void deleteLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabelMouseClicked
        DefaultTableModel model = (DefaultTableModel) myTable1.getModel();
        int SelectedRow = myTable1.getSelectedRow();
        int SelectedColumn = myTable1.getSelectedColumn();

        int confirmation = JOptionPane.showConfirmDialog(this, "are you sure?");

        if (confirmation == 0) {
            int value = (Integer) model.getValueAt(SelectedRow, 0);
            try {
                String query = "DELETE FROM `productstable` WHERE `id` = ?";
                PreparedStatement statement = productsFrames.conn().prepareStatement(query);
                statement.setInt(1, value);
                statement.executeUpdate();

                callData();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
    }//GEN-LAST:event_deleteLabelMouseClicked

    private void editQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editQuantityActionPerformed

    private void editTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editTypeActionPerformed

    private void editPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editPriceActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new productsFrames("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel addLabel;
    private javax.swing.JPanel addPanel;
    private javax.swing.JLabel deleteLabel;
    private javax.swing.JButton editBtn;
    private javax.swing.JComboBox<String> editId;
    private javax.swing.JLabel editLabel;
    private javax.swing.JTextField editName;
    private javax.swing.JPanel editPanel;
    private javax.swing.JFormattedTextField editPrice;
    private javax.swing.JFormattedTextField editQuantity;
    private javax.swing.JTextField editType;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JLabel exitBtn1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JFormattedTextField jTextField3;
    private javax.swing.JPanel myPanel;
    private javax.swing.JTable myTable1;
    private javax.swing.JPanel productList;
    private javax.swing.JFormattedTextField quantity;
    private javax.swing.JTextField searchInput;
    private javax.swing.JTextField searchInput2;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JButton viewBtn;
    private javax.swing.JLabel viewLabel;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
}
