/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GIS.database;

import static GIS.GIS.gis;
import GIS.drawing.Content;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author keneuoe
 */
public class DB_connection extends javax.swing.JFrame {

    // Database object to store connection information permanently
    Database db;
    // Content received from GIS.java
    Content c;
    // Content received from DB import
    Content cDB;
    
    /**
     * Creates new form db_connection
     */
    public DB_connection() {
        // get the content of GIS.java
        this.c = gis.c;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        saveToDb_btn = new javax.swing.JButton();
        saveToCsv_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        file_viewer = new javax.swing.JTable();
        LoadInGis_DB_btn = new javax.swing.JButton();
        label1 = new java.awt.Label();
        open_csv_btn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        host_textfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        user_textfield = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DBMSName = new javax.swing.JTextField();
        password_textfield = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        port_textfield = new javax.swing.JTextField();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        dbname_textfield = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Connect = new javax.swing.JButton();
        connected_label = new javax.swing.JLabel();
        label2 = new java.awt.Label();
        name_txt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        path_txt = new javax.swing.JTextField();
        import_db_btn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        table_txt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        import_csv_btn = new javax.swing.JButton();
        clear_viewer_btn = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jTextField1.setText("jTextField1");

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setPreferredSize(new java.awt.Dimension(810, 515));

        saveToDb_btn.setText("Save into database");
        saveToDb_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToDb_btnActionPerformed(evt);
            }
        });

        saveToCsv_btn.setText("Save to CSV");
        saveToCsv_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToCsv_btnActionPerformed(evt);
            }
        });

        file_viewer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "gid", "type", "geom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(file_viewer);

        LoadInGis_DB_btn.setText("Load into GIS");
        LoadInGis_DB_btn.setToolTipText("");
        LoadInGis_DB_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadInGis_DB_btnActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Elephant", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 0, 153));
        label1.setText("Connection with MySQL");

        open_csv_btn.setText("Open CSV");
        open_csv_btn.setToolTipText("");
        open_csv_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                open_csv_btnActionPerformed(evt);
            }
        });

        host_textfield.setText("localhost");
        host_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                host_textfieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Host");

        jLabel5.setText("Password");

        jLabel4.setText("Username");

        user_textfield.setText("root");
        user_textfield.setMinimumSize(new java.awt.Dimension(10, 109));
        user_textfield.setPreferredSize(new java.awt.Dimension(109, 26));
        user_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_textfieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Port");

        DBMSName.setText("mysql");
        DBMSName.setPreferredSize(new java.awt.Dimension(109, 26));

        password_textfield.setPreferredSize(new java.awt.Dimension(109, 26));

        jLabel1.setText("DBMS Name");

        port_textfield.setText("3306");
        port_textfield.setPreferredSize(new java.awt.Dimension(109, 26));

        jLayeredPane1.setLayer(host_textfield, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(user_textfield, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(DBMSName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(password_textfield, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(port_textfield, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(port_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(user_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(37, 37, 37)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DBMSName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(host_textfield))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(password_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DBMSName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(host_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(port_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(user_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(password_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dbname_textfield.setText("shapes");
        dbname_textfield.setPreferredSize(new java.awt.Dimension(109, 26));

        jLabel7.setText("Database Name");

        Connect.setText("Connect");
        Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectActionPerformed(evt);
            }
        });

        connected_label.setText("status show");

        label2.setFont(new java.awt.Font("Elephant", 1, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(0, 0, 153));
        label2.setText("Connection with CSV");

        jLabel8.setText("File");

        jLabel9.setText("Path");

        import_db_btn.setText("Import from Database");
        import_db_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                import_db_btnActionPerformed(evt);
            }
        });

        table_txt.setText("shapes");
        table_txt.setPreferredSize(new java.awt.Dimension(109, 26));
        table_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table_txtActionPerformed(evt);
            }
        });

        jLabel10.setText("Database Table");

        jLayeredPane2.setLayer(dbname_textfield, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(Connect, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(connected_label, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(label2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(name_txt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(path_txt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(import_db_btn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jSeparator2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(table_txt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(dbname_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(import_db_btn)
                        .addGroup(jLayeredPane2Layout.createSequentialGroup()
                            .addComponent(connected_label, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Connect)))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(table_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(path_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(dbname_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(table_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connected_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Connect))
                .addGap(50, 50, 50)
                .addComponent(import_db_btn)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(path_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        import_csv_btn.setText("Import from CSV");
        import_csv_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                import_csv_btnActionPerformed(evt);
            }
        });

        clear_viewer_btn.setText("Clear Viewer");
        clear_viewer_btn.setToolTipText("");
        clear_viewer_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_viewer_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(saveToDb_btn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(clear_viewer_btn)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(saveToCsv_btn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(LoadInGis_DB_btn))))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(open_csv_btn)
                        .addGap(18, 18, 18)
                        .addComponent(import_csv_btn)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveToDb_btn)
                            .addComponent(saveToCsv_btn)
                            .addComponent(LoadInGis_DB_btn))
                        .addGap(18, 18, 18)
                        .addComponent(clear_viewer_btn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(open_csv_btn)
                    .addComponent(import_csv_btn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void user_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_textfieldActionPerformed

    private void host_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_host_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_host_textfieldActionPerformed

    private void ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectActionPerformed
         try {
            getConnection();
            connected_label.setVisible(true);
            connected_label.setText("connected");
            connected_label.setForeground(Color.green);

        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            connected_label.setVisible(true);
            connected_label.setText("connection error");
            connected_label.setForeground(Color.red);
        }
    }//GEN-LAST:event_ConnectActionPerformed

    private void saveToDb_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToDb_btnActionPerformed
        try {
            String tableName = table_txt.getText();
            String dbname = dbname_textfield.getText();
            createTable();
            deleteAllEntries();
            db.insertContent(c);
            JOptionPane.showMessageDialog(null, "Saved to \ndatabase: " +dbname +" \ntable: " +tableName);
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_saveToDb_btnActionPerformed
    
    private void saveToCsv_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToCsv_btnActionPerformed
        try {
            String filename = name_txt.getText();
            String filepath = path_txt.getText();
            
            if (!"".equals(filename) && filepath !=""){
                saveToCsv();
            }
            else {
                fileChooser();
                saveToCsv();
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Select file first");
        }
    }//GEN-LAST:event_saveToCsv_btnActionPerformed

    private void import_db_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_import_db_btnActionPerformed
        try {
            displayDbData();
        }  
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_import_db_btnActionPerformed

    private void LoadInGis_DB_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadInGis_DB_btnActionPerformed
        try {
            // load content from the DB and store it in the Content object cDB
            cDB = db.loadContent();
            // and overwrite the actual content of the GIS 
            gis.setContent(cDB);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_LoadInGis_DB_btnActionPerformed

    private void open_csv_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_open_csv_btnActionPerformed
        // 
        try {
            fileChooser();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_open_csv_btnActionPerformed

    private void import_csv_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_import_csv_btnActionPerformed
        // displays the contents of the chosen CSV file into the JTable
    }//GEN-LAST:event_import_csv_btnActionPerformed

    private void clear_viewer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_viewer_btnActionPerformed
        // clears the file_viewer
        ((DefaultTableModel)file_viewer.getModel()).setNumRows(0);
    }//GEN-LAST:event_clear_viewer_btnActionPerformed

    private void table_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table_txtActionPerformed
        // TODO add your handling code here:
        table_txt.setEditable(false);
    }//GEN-LAST:event_table_txtActionPerformed

    // called when import from Import from Database botton is pressed
    //this methods displays the contents of a database table into the JTable
    public void displayDbData() throws SQLException {
            try {
                String table = table_txt.getText();
                Connection con = getConnection();
                java.sql.ResultSet resultSet;
                PreparedStatement display;
               
                display = con.prepareStatement("SELECT * FROM " +table);
                
                resultSet = display.executeQuery();

                file_viewer.setModel(DbUtils.resultSetToTableModel(resultSet));
                
                resultSet.close();
                display.close();
            }
            
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    
    // called when Import from CSV botton is pressed
    //this methods displays the contents of a CSV file into the JTable
    public void displayCsvData() throws Exception {
        
    }
    
    // called when connect button is pressed
    // TO-DO: check if Table name exists,
    // if not, ask the user if table should be generated automaticly
    public Connection getConnection () throws SQLException {
        db = new Database();
        db.dbHost = host_textfield.getText();
        db.dbPort = port_textfield.getText();
        db.dbName = dbname_textfield.getText();
        db.dbUser = user_textfield.getText();
        db.dbPassword = password_textfield.getText();
        db.DBMS = (String) DBMSName.getText();
        
        return db.dbConnect();
    }  
    
    //this methods creates a table if none exists
    public void createTable() throws Exception {
        Connection con = getConnection();
        PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS shapes (gid int NOT NULL AUTO_INCREMENT, "
                + "type varchar(10), "
                + "geom longtext, "
                + "PRIMARY KEY(gid))");
        create.executeUpdate();
    }
   /* 
    public void deleteEntries() throws Exception {
        Connection con = getConnection();
        PreparedStatement delete = con.prepareStatement("DELETE FROM shapes WHERE id = ?"); 
        delete.executeUpdate();
    }
*/
    public void fileChooser() throws Exception {
            JFileChooser chooser = new JFileChooser();
            int fc = chooser.showOpenDialog(null);
            while(fc ==JFileChooser.APPROVE_OPTION && !chooser.getSelectedFile().getName().endsWith(".csv")){
                      JOptionPane.showMessageDialog(null, "The file "+ chooser.getSelectedFile() + 
                              " is not a csv file.","Open Error", JOptionPane.ERROR_MESSAGE);
            fc = chooser.showOpenDialog(this);
            }
            
            if (fc ==JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                String filepath = f.getAbsolutePath();
                path_txt.setText(filepath);
                String filename = f.getName();
                name_txt.setText(filename);
            }
            
            else {
                JOptionPane.showMessageDialog(null, "No Selection");
            }
    }
    
    public void saveToCsv() throws Exception {
        String filepath  = path_txt.getText();
        String filename = name_txt.getText();
        createfile g = new createfile();
        g.openFile();
        g.addRecords(file_viewer, filepath);
        g.closeFile();
        JOptionPane.showMessageDialog(null,"Saved to file: " +filename);
    }
    
    public void deleteAllEntries() throws Exception {
        Connection con = getConnection();
        PreparedStatement deleteAll = con.prepareStatement("TRUNCATE shapes"); 
        deleteAll.executeUpdate();
}
      
    public class createfile {
       private Formatter file;

       public void openFile() {
           try {
               String filepath = path_txt.getText();
               file = new Formatter(filepath);
           }
           catch(FileNotFoundException e) {
               System.out.println("you have an error");
           }
        }
        
        public boolean addRecords(JTable table, String path) {
            try {
                TableModel model = table.getModel();
                FileWriter csv = new FileWriter(new File(path));

                for (int i = 0; i < model.getColumnCount(); i++) {
                    csv.write(model.getColumnName(i) + ",");
                }

                csv.write("\n");

                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        csv.write(model.getValueAt(i, j).toString() + ",");
                    }
                    csv.write("\n");
                }
                
                csv.close();
                return true;
            }
                
        catch (IOException e) {
        }
        return false;
        }
    
        public void closeFile() {
            file.close();
        }
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Connect;
    private javax.swing.JTextField DBMSName;
    private javax.swing.JButton LoadInGis_DB_btn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clear_viewer_btn;
    private javax.swing.JLabel connected_label;
    private javax.swing.JTextField dbname_textfield;
    private javax.swing.JTable file_viewer;
    private javax.swing.JTextField host_textfield;
    private javax.swing.JButton import_csv_btn;
    private javax.swing.JButton import_db_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private javax.swing.JTextField name_txt;
    private javax.swing.JButton open_csv_btn;
    private javax.swing.JPasswordField password_textfield;
    private javax.swing.JTextField path_txt;
    private javax.swing.JTextField port_textfield;
    private javax.swing.JButton saveToCsv_btn;
    private javax.swing.JButton saveToDb_btn;
    private javax.swing.JTextField table_txt;
    private javax.swing.JTextField user_textfield;
    // End of variables declaration//GEN-END:variables
}
