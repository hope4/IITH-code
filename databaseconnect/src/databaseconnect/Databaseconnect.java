package databaseconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Databaseconnect {

    Connection connection;
    String url = "jdbc:postgresql://192.168.134.5:5432/alertdatabase";
    String user = "postgres";
    String password = "ds123";

    public Connection dbConnection() {

        try {

            Class.forName("org.postgresql.Driver.Driver");

        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        try {
            DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null, "Connected");

        } catch (SQLException ex) {
            Logger.getLogger(Databaseconnect.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Failed to connect")
        }

        return connection;
    }

    public static void main(String[] args) {

    }

}
