/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentcompanion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author anmol singh sethi
 */
public class addcourses extends javax.swing.JFrame {

    /**
     * Creates new form addcourses
     */
    public addcourses() {
        initComponents();
        cbdeptcombo.removeAllItems();
        cbdeptcombo.addItem("----Select----");
        setSize(600, 600);
        setVisible(true);
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("Driver Loading done");

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student_companion", "root", "system");
//            System.out.println("Connection Created");

            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
//            System.out.println("Statement Created");

            ResultSet rs = stmt.executeQuery("select * from departments");
            while (rs.next()) {

                cbdeptcombo.addItem(rs.getString("department"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btaddcourse = new javax.swing.JButton();
        tfcoursename = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tacoursedesc = new javax.swing.JTextArea();
        cbdeptcombo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("Add New Course");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(240, 30, 135, 29);

        jLabel2.setText("Enter Course Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 110, 180, 40);

        jLabel3.setText("Enter Description");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 240, 140, 40);

        jLabel4.setText("Select Department");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 170, 160, 50);

        btaddcourse.setText("Add Course");
        btaddcourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btaddcourseActionPerformed(evt);
            }
        });
        getContentPane().add(btaddcourse);
        btaddcourse.setBounds(220, 430, 170, 50);
        getContentPane().add(tfcoursename);
        tfcoursename.setBounds(260, 90, 310, 60);

        tacoursedesc.setColumns(20);
        tacoursedesc.setRows(5);
        jScrollPane1.setViewportView(tacoursedesc);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(260, 230, 310, 180);

        cbdeptcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbdeptcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbdeptcomboActionPerformed(evt);
            }
        });
        getContentPane().add(cbdeptcombo);
        cbdeptcombo.setBounds(260, 170, 310, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbdeptcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbdeptcomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbdeptcomboActionPerformed

    private void btaddcourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btaddcourseActionPerformed
        // TODO add your handling code here:
        String courseName = tfcoursename.getText();
        String courseDescription = tacoursedesc.getText();
        String department = cbdeptcombo.getSelectedItem().toString();
        
        if(courseName.isEmpty() || courseDescription.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"The fields are empty");
        }
        else if(department.equals("----Select----"))
        {
             JOptionPane.showMessageDialog(this,"The fields are not correct");
        }
        else
        {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("Driver Loading done");

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student_companion", "root", "system");
//            System.out.println("Connection Created");

            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
//            System.out.println("Statement Created");

            ResultSet rs = stmt.executeQuery("select * from courses where coursename='"+courseName+"'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(rootPane, "Department already exists \nEnter new Department");
                tfcoursename.setText("");
                tacoursedesc.setText("");
            } else {
                rs.moveToInsertRow();

                rs.updateString("coursename", courseName);

                rs.updateString("department", department);

                rs.updateString("courseDescription", courseDescription);
                rs.insertRow();
                JOptionPane.showMessageDialog(rootPane, "Department added successfully");
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        }

    }//GEN-LAST:event_btaddcourseActionPerformed

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
            java.util.logging.Logger.getLogger(addcourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addcourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addcourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addcourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addcourses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btaddcourse;
    private javax.swing.JComboBox<String> cbdeptcombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea tacoursedesc;
    private javax.swing.JTextField tfcoursename;
    // End of variables declaration//GEN-END:variables
}
