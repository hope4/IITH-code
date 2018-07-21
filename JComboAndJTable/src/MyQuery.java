package javaapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1bestcsharp.blogspot.com
 */
public class MyQuery {
    
   public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root","");
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public ArrayList<Product> getData(int catID){

   ArrayList<Product> list = new ArrayList<Product>();
   Connection con = getConnection();
   Statement st;
   ResultSet rs;
   
   try {
   st = con.createStatement();
   rs = st.executeQuery("SELECT `ID_PRO`, `PRO_NAME`, `QTE_IN_STOCK`, `PRICE`, `ID_CAT` FROM `products` WHERE `ID_CAT` = "+ catID);
   
   Product p;
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
