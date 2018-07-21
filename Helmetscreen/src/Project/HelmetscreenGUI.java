/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author naveen
 */
public class HelmetscreenGUI extends javax.swing.JFrame {
      String url; 
      String user1;
      String password;
      Connection con;
      
      
    /**
     * Creates new form HelmetscreenGUI
     */
    public HelmetscreenGUI() {
        initComponents();
    }
      public ArrayList<User> userList(){
        ArrayList<User> usersList = new ArrayList<>();
   
      try {

            
           
            String cameraid = String.valueOf(cameralist.getSelectedItem());
            System.out.println(cameraid);
            
             System.out.println(cameraid);
             if(cameraid!= null){
             String query1 = "SELECT * FROM alerts where camera_id = "+cameraid;
             System.out.println(query1);
             Statement st = (Statement) con.createStatement();
             ResultSet rs = st.executeQuery(query1);
             User user;
             while(rs.next()){
                 user = new User(rs.getInt("alert_id"),rs.getString("timestamp"),rs.getInt("horizontal"),rs.getInt("vertical"),rs.getInt("height"),rs.getInt("width"),rs.getInt("camera_id"));
                 usersList.add(user);
             }
}
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return usersList;
    }
    
    public void show_user(ArrayList <User> list){
        DefaultTableModel model = (DefaultTableModel) jtable_display_alerts.getModel();
        Object[] row = new Object[7];
        for(int i=0;i<list.size();i++){
            row[0] = list.get(i).getalert_id();
            row[1] = list.get(i).gettimestamp();
            row[2] = list.get(i).gethorizontal();
            row[3] = list.get(i).getvertical();
            row[4] = list.get(i).getheight();
            row[5] = list.get(i).getwidth();
            row[6] = list.get(i).getcamera_id();
            model.addRow(row);
        }
    
    }
   
    
    private  void Fillcombo(){
        
        try {

            Class.forName("org.postgresql.Driver"); 
            Connection con;
            con = DriverManager.getConnection(url,user1,password);
             String query1 = "SELECT camera_id FROM camera";
             String query2 = "select table_name from information_schema.tables where table_schema = 'public'";
             Statement st = (Statement) con.createStatement();
             ResultSet rs = st.executeQuery(query1);
             Statement st2 = (Statement) con.createStatement();

             ResultSet rs2 = st2.executeQuery(query2);
             
      
             while(rs.next()){
                 String number;
                number = rs.getString("camera_id");
             
                cameralist.addItem(number);
                
             }
             
              //while(rs2.next()){
                  
                  //int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 }; 
                 String []tablenames = new String[]{"New Alerts","Accepted Alerts","Rejected Alerts"};
          
               // tablenames = rs2.getString("table_name");
              
                System.out.println(tablenames);
                tablelist.addItem(tablenames[0]);
                tablelist.addItem(tablenames[1]);
                tablelist.addItem(tablenames[2]);
             
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        IPlabel = new javax.swing.JLabel();
        iptextfield = new javax.swing.JTextField();
        portlabel = new javax.swing.JLabel();
        porttextfield = new javax.swing.JTextField();
        usernamelabel = new javax.swing.JLabel();
        usernametextfield = new javax.swing.JTextField();
        passwordlabel = new javax.swing.JLabel();
        databaselabel = new javax.swing.JLabel();
        databasetextfield = new javax.swing.JTextField();
        connectbutton = new javax.swing.JButton();
        cameralist = new javax.swing.JComboBox<>();
        tablelist = new javax.swing.JComboBox<>();
        acceptbutton = new javax.swing.JButton();
        revokebutton = new javax.swing.JButton();
        rejectbutton = new javax.swing.JButton();
        cameralabel = new javax.swing.JLabel();
        tablelistlabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_display_alerts = new javax.swing.JTable();
        passwordtextfield = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        IPlabel.setText("IP");

        iptextfield.setText("192.168.134.5");
        iptextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iptextfieldActionPerformed(evt);
            }
        });

        portlabel.setText("Port");

        porttextfield.setText("5432");

        usernamelabel.setText("Username");

        usernametextfield.setText("postgres");
        usernametextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernametextfieldActionPerformed(evt);
            }
        });

        passwordlabel.setText("Password");

        databaselabel.setText("Database");

        databasetextfield.setText("alertdatabase");
        databasetextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                databasetextfieldActionPerformed(evt);
            }
        });

        connectbutton.setText("Connect");
        connectbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectbuttonActionPerformed(evt);
            }
        });

        cameralist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cameralistActionPerformed(evt);
            }
        });

        tablelist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "New_Alerts", "Challans", "Rejected_Alerts" }));
        tablelist.setToolTipText("");
        tablelist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablelistActionPerformed(evt);
            }
        });

        acceptbutton.setText("accept");
        acceptbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptbuttonActionPerformed(evt);
            }
        });

        revokebutton.setText("revoke");
        revokebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revokebuttonActionPerformed(evt);
            }
        });

        rejectbutton.setText("reject");

        cameralabel.setText("Camera");

        tablelistlabel.setText("Tablelist");

        jtable_display_alerts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "alert_id", "timestamp", "horizontal", "vertical", "height", "width"
            }
        ));
        jtable_display_alerts.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtable_display_alertsFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jtable_display_alerts);

        jScrollPane2.setViewportView(jScrollPane1);

        passwordtextfield.setText("ds123");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernamelabel)
                                    .addComponent(IPlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(porttextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(iptextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(usernametextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(passwordtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(acceptbutton)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(rejectbutton))
                                    .addComponent(passwordlabel))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(revokebutton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(databaselabel)
                                            .addComponent(databasetextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(portlabel)
                                .addGap(255, 255, 255)
                                .addComponent(cameralabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addComponent(connectbutton)
                                .addGap(110, 110, 110)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tablelistlabel)
                                    .addComponent(cameralist, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tablelist, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IPlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(portlabel)
                            .addComponent(databaselabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cameralabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iptextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(porttextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(databasetextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cameralist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(passwordlabel)
                                    .addComponent(usernamelabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(usernametextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(connectbutton, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(tablelistlabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tablelist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acceptbutton)
                            .addComponent(revokebutton)
                            .addComponent(rejectbutton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iptextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iptextfieldActionPerformed
                // TODO add your handling code here:
         
    }//GEN-LAST:event_iptextfieldActionPerformed

    private void acceptbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptbuttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acceptbuttonActionPerformed

    private void usernametextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernametextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernametextfieldActionPerformed

    private void revokebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_revokebuttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_revokebuttonActionPerformed

    private void connectbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectbuttonActionPerformed
        // TODO add your handling code here:
        try{
            url = "jdbc:postgresql://"+iptextfield.getText()+":"+porttextfield.getText()+"/"+databasetextfield.getText();
            user1 = usernametextfield.getText();
            password = passwordtextfield.getText();
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url,user1,password);
            String query1 = "SELECT camera_id FROM camera";
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            while(rs.next()){
                String number;
                number = rs.getString("camera_id");
                cameralist.addItem(number);
            }
            connectbutton.setEnabled(false);
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_connectbuttonActionPerformed

    private void cameralistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cameralistActionPerformed
        try{
            String query1 = "select alerts.alert_id,alerts.timestamp,alerts.horizontal,alerts.vertical,alerts.height,alerts.width from alerts inner join "+tablelist.getSelectedItem()+" on alerts.alert_id = "+tablelist.getSelectedItem()+".alert_id where camera_id = "+cameralist.getSelectedItem();
            Statement st1 = (Statement) con.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);
            DefaultTableModel model = (DefaultTableModel) jtable_display_alerts.getModel();
            model.setRowCount(0);
            Object []row=new Object[6];
            while(rs1.next()){
                row[0] = (Object)rs1.getString("alert_id");
                row[1] = (Object)rs1.getString("timestamp");
                row[2] = (Object)rs1.getString("horizontal");
                row[3] = (Object)rs1.getString("vertical");
                row[4] = (Object)rs1.getString("height");
                row[5] = (Object)rs1.getString("width");
                model.addRow(row);
            }
            connectbutton.setEnabled(false);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } 
          
    }//GEN-LAST:event_cameralistActionPerformed
  
    private void tablelistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablelistActionPerformed
        try{
            String query1 = "select alerts.alert_id,alerts.timestamp,alerts.horizontal,alerts.vertical,alerts.height,alerts.width from alerts inner join "+tablelist.getSelectedItem()+" on alerts.alert_id = "+tablelist.getSelectedItem()+".alert_id where camera_id = "+cameralist.getSelectedItem();
            Statement st1 = (Statement) con.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);
            DefaultTableModel model = (DefaultTableModel) jtable_display_alerts.getModel();
            model.setRowCount(0);
            Object []row=new Object[6];
            while(rs1.next()){
                row[0] = (Object)rs1.getString("alert_id");
                row[1] = (Object)rs1.getString("timestamp");
                row[2] = (Object)rs1.getString("horizontal");
                row[3] = (Object)rs1.getString("vertical");
                row[4] = (Object)rs1.getString("height");
                row[5] = (Object)rs1.getString("width");
                model.addRow(row);
            }
            connectbutton.setEnabled(false);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } 
    }//GEN-LAST:event_tablelistActionPerformed

    private void databasetextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_databasetextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_databasetextfieldActionPerformed

    private void jtable_display_alertsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtable_display_alertsFocusGained
        try {
            URL url = new URL("file:///run/user/1001/gvfs/sftp:host=192.168.134.5,user=dinesh%253Ads123/home/dinesh/django-apps/webapp/htp/static/htp/Images/1.jpg");
            jLabel1.setIcon(new javax.swing.ImageIcon(url));
          } catch (MalformedURLException ex) {
              Logger.getLogger(HelmetscreenGUI.class.getName()).log(Level.SEVERE, null, ex);
          }
        
        
    }//GEN-LAST:event_jtable_display_alertsFocusGained

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
            java.util.logging.Logger.getLogger(HelmetscreenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HelmetscreenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HelmetscreenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HelmetscreenGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HelmetscreenGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IPlabel;
    private javax.swing.JButton acceptbutton;
    private javax.swing.JLabel cameralabel;
    private javax.swing.JComboBox<String> cameralist;
    private javax.swing.JButton connectbutton;
    private javax.swing.JLabel databaselabel;
    private javax.swing.JTextField databasetextfield;
    private javax.swing.JTextField iptextfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtable_display_alerts;
    private javax.swing.JLabel passwordlabel;
    private javax.swing.JPasswordField passwordtextfield;
    private javax.swing.JLabel portlabel;
    private javax.swing.JTextField porttextfield;
    private javax.swing.JButton rejectbutton;
    private javax.swing.JButton revokebutton;
    private javax.swing.JComboBox<String> tablelist;
    private javax.swing.JLabel tablelistlabel;
    private javax.swing.JLabel usernamelabel;
    private javax.swing.JTextField usernametextfield;
    // End of variables declaration//GEN-END:variables
}
