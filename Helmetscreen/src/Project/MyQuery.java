package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 1bestcsharp.blogspot.com
 */
public class MyQuery {
     String url = "jdbc:postgresql://192.168.134.5:5432/alertdatabase";
             String user1 = "postgres";
             String password = "ds123";
   public Connection getConnection(){
          Connection con1 = null;
 
       try {          
            Class.forName("org.postgresql.Driver");
                     con1 = DriverManager.getConnection(url,user1,password);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return con1;
    }
    
    public ArrayList<Product> getData(int cameraID){

   ArrayList<Product> list = new ArrayList<Product>();
   Connection con = getConnection();
   Statement st;
   ResultSet rs;
   
   try {
   st = con.createStatement();
   rs = st.executeQuery("SELECT `alert_id`, `timestamp`, `horizontal`, `vertical`, `height` , `width`  FROM `alerts` WHERE `camera_id` = "+ cameraID);
   
   User p;
   while(rs.next()){
   p = new Product(
   rs.getString("ID_PRO"),
   rs.getString("PRO_NAME"),
   rs.getInt("QTE_IN_STOCK"),
   rs.getString("PRICE"),
   rs.getInt("ID_CAT")
   );
   list.add(p);
   }
   
   } catch (SQLException ex) {
   Logger.getLogger(MyQuery.class.getName()).log(Level.SEVERE, null, ex);
   }
   return list;
   }
}