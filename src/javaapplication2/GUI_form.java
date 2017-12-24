package javaapplication2;
import java.sql.*;
import java.awt.Color;

public class GUI_form extends javax.swing.JFrame {
    
    public GUI_form() {
        initComponents();
        results_label.setVisible(false);
        connected_label.setVisible(false);
        inserted_label.setVisible(false);
    }

    // Code generated automaticly by Design-Panel
    // !!!! initComponents() behind here
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TestConnectionButton = new javax.swing.JButton();
        GetButton = new javax.swing.JButton();
        DBNameLabel = new javax.swing.JLabel();
        host_textfield = new javax.swing.JTextField();
        InsertButton = new javax.swing.JButton();
        FirstNameLabel = new javax.swing.JLabel();
        firstname_textfield = new javax.swing.JTextField();
        LastNameLabel = new javax.swing.JLabel();
        lastname_textfield = new javax.swing.JTextField();
        EmailLabel = new javax.swing.JLabel();
        grade_textfield = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        port_textfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        password_textfield = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        dbname_textfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        table_textfield = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        results_label = new javax.swing.JLabel();
        connected_label = new javax.swing.JLabel();
        inserted_label = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        id_textfield = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        user_textfield = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        DBNameLabel1 = new javax.swing.JLabel();
        DBMSName = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        TestConnectionButton.setText("Test connection");
        TestConnectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestConnectionButtonActionPerformed(evt);
            }
        });

        GetButton.setText("Get all entries");
        GetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetButtonActionPerformed(evt);
            }
        });

        DBNameLabel.setText("Host");

        host_textfield.setText("127.0.0.1");

        InsertButton.setText("Insert into Database");
        InsertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertButtonActionPerformed(evt);
            }
        });

        FirstNameLabel.setText("First Name");

        firstname_textfield.setText("insert first name");
        firstname_textfield.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        LastNameLabel.setText("Last Name");

        lastname_textfield.setText("insert last name");
        lastname_textfield.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        EmailLabel.setText("Grade");

        grade_textfield.setText("insert Grade");

        jLabel1.setText("Port");

        port_textfield.setText("9999");

        jLabel2.setText("Password");

        password_textfield.setText("blas");

        jLabel3.setText("Database name");

        dbname_textfield.setText("Database name");

        jLabel4.setText("Table name");

        table_textfield.setText("Table name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel5.setText("Connection");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("Insert");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("Select");

        results_label.setBackground(new java.awt.Color(204, 204, 255));
        results_label.setText("results here");
        results_label.setOpaque(true);

        connected_label.setForeground(new java.awt.Color(0, 255, 0));
        connected_label.setText("connected");

        inserted_label.setForeground(new java.awt.Color(0, 255, 0));
        inserted_label.setText("inserted");

        jLabel9.setText("ID");

        id_textfield.setText("insert ID");

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel10.setText("Into");

        jLabel11.setText("User");

        user_textfield.setText("User");

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel12.setText("Attributes");

        DBNameLabel1.setText("DBMS Name");

        DBMSName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "postgresql", "mysql", "db2 (X)", "oracle:thin (X)" }));

        jMenu1.setText("File");

        jMenuItem1.setText("close");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(password_textfield)
                            .addComponent(port_textfield)
                            .addComponent(DBNameLabel)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(host_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11)
                            .addComponent(user_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(dbname_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(connected_label))
                            .addComponent(DBNameLabel1)
                            .addComponent(DBMSName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(EmailLabel)
                            .addComponent(LastNameLabel)
                            .addComponent(FirstNameLabel)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(grade_textfield, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lastname_textfield, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(firstname_textfield, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(id_textfield, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(table_textfield, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(inserted_label)))
                        .addGap(79, 79, 79))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TestConnectionButton)
                        .addGap(120, 120, 120)
                        .addComponent(InsertButton)
                        .addGap(111, 111, 111)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GetButton)
                    .addComponent(jLabel7)
                    .addComponent(results_label, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(table_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(16, 16, 16)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel12))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(id_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(FirstNameLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(firstname_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(LastNameLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lastname_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(EmailLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(grade_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(results_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(DBNameLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DBMSName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(DBNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(host_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(4, 4, 4)
                                .addComponent(port_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dbname_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(user_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(password_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TestConnectionButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(InsertButton)
                        .addComponent(GetButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connected_label, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inserted_label, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    public Connection getConnection () throws SQLException {
        String host = host_textfield.getText();
        String port = port_textfield.getText();
        String dbname = dbname_textfield.getText();
        String user = user_textfield.getText();
        String password = password_textfield.getText();
        String DBMS = (String) DBMSName.getSelectedItem();
       
        String connection = "jdbc:"+ DBMS +"://" + host + ":" + port + "/" + dbname + "?autoReconnect=true&useSSL=false";
        
        Connection dbmsconn = DriverManager.getConnection(connection, user, password);
        return dbmsconn;
    }
    
    private void TestConnectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestConnectionButtonActionPerformed
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
    }//GEN-LAST:event_TestConnectionButtonActionPerformed

    private void InsertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertButtonActionPerformed
        try {
            String dbname = dbname_textfield.getText();
            String table = table_textfield.getText();
            String id = id_textfield.getText();
            String firstname = firstname_textfield.getText();
            String lastname = lastname_textfield.getText();
            String grade = grade_textfield.getText();
            
            Connection dbmsconn = getConnection();
            Statement sta = dbmsconn.createStatement();
            //Creating the SQL Statement for insert data into table
            String db_table = dbname + "." + table;
            String values = id + ", '" + firstname + "', '" + lastname + "'," + grade;
            //change the field name according to your table field name.
            sta.executeUpdate("INSERT INTO " + db_table + " (id, namefirst, namelast, grade) VALUES (" + values + ")"); 
            //
            inserted_label.setVisible(true);
            inserted_label.setText("inserted");
            inserted_label.setForeground(Color.green);
            
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
            inserted_label.setVisible(true);
            inserted_label.setText("insert error");
            inserted_label.setForeground(Color.red);
        }
    }//GEN-LAST:event_InsertButtonActionPerformed

    private void GetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetButtonActionPerformed
        try{
            String dbname = dbname_textfield.getText();
            String table = table_textfield.getText();
            String db_table = dbname + "." + table;
            
            Connection dbmsconn = getConnection();
            Statement sta = dbmsconn.createStatement();
            //calling all data from the table
            ResultSet myres = sta.executeQuery("select * from "+ db_table);
            //Displaying the data
            String result = "<b>ID -- lastname -- firstname -- grade</b><br>";
            while(myres.next()){
                //change the field name accordig to your field name
		result = result + myres.getInt("id")+" -- "+myres.getString("namelast")+" -- "+myres.getString("namefirst")+" -- "+myres.getDouble("grade") + "<br>";
               
            }
            //html to support text breaks
            result = "<html><body>" + result + "</body></html>";
            results_label.setVisible(true);
            results_label.setText(result);
        }
        catch (SQLException ex) {
            System.out.println(ex.getSQLState());
        }
        
    }//GEN-LAST:event_GetButtonActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(GUI_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DBMSName;
    private javax.swing.JLabel DBNameLabel;
    private javax.swing.JLabel DBNameLabel1;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JLabel FirstNameLabel;
    private javax.swing.JButton GetButton;
    private javax.swing.JButton InsertButton;
    private javax.swing.JLabel LastNameLabel;
    private javax.swing.JButton TestConnectionButton;
    private javax.swing.JLabel connected_label;
    private javax.swing.JTextField dbname_textfield;
    private javax.swing.JTextField firstname_textfield;
    private javax.swing.JTextField grade_textfield;
    private javax.swing.JTextField host_textfield;
    private javax.swing.JTextField id_textfield;
    private javax.swing.JLabel inserted_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JTextField lastname_textfield;
    private javax.swing.JPasswordField password_textfield;
    private javax.swing.JTextField port_textfield;
    private javax.swing.JLabel results_label;
    private javax.swing.JTextField table_textfield;
    private javax.swing.JTextField user_textfield;
    // End of variables declaration//GEN-END:variables
}
