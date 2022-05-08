/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw_project;

import java.sql.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Sara
 */
public class Operations {

    public static boolean isLogin(String username, String password, String usertype, JFrame frame) {
        try {
            // Connection myConn= myConnection.getConnection();
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String mySqlQuery
                    = "SELECT Usertype FROM appuser WHERE Username = '"
                    + username
                    + "' AND Password = '"
                    + password
                    + "' AND Usertype = '"
                    + usertype
                    + "'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // loginSession.UID =resultSet.getInt("UID");
                loginSession.UserType = resultSet.getString("Usertype");
                return true;
            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(frame, "Database error" + ex.getMessage());
        }
        return false;
    }

    public static boolean isRegistered(String Rfullname, String Rgender, String Remail, String Rpath, JFrame frame) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String Query = "INSERT INTO `person`(`Fullname`,`Gender`, `Email`,`Image`) VALUES ("
                    + "\"" + Rfullname + "\", "
                    + "\"" + Rgender + "\","
                    + "\"" + Remail + "\","
                    + "\"" + Rpath + "," + "\" )";

            Statement st = myConn.createStatement();
            st.executeUpdate(Query);
            //insertUser(Remail);

            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "error" + ex.getMessage());
            return false;
        }

    }

    public static boolean isTaken(String username, JFrame frame) {
        boolean UserTaken = false;
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String mySqlQuery
                    = "SELECT username FROM appuser WHERE username = '"
                    + username
                    + "'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserTaken = true;
                JOptionPane.showMessageDialog(frame, "Username already Taken");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Database error" + ex.getMessage());
        }
        return UserTaken;
    }

    public static void insertUser(String RemailStr, String username, String password, JFrame frame) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String mySqlQuery
                    = "SELECT email FROM person WHERE email = '"
                    + RemailStr
                    + "'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                //JOptionPane.showMessageDialog(frame, "it's"+username+" "+password+" " +email+"  done");

                String Query = "INSERT INTO `appuser`(`username`,`Password`, `email`,`Usertype`) VALUES ("
                        + "\"" + username + "\", "
                        + "\"" + password + "\","
                        + "\"" + email + "\","
                        + "\"" + "User" + "\" )";
                Statement st = myConn.createStatement();
                st.executeUpdate(Query);
                myConn.close();
            }

        } catch (Exception ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void resetPassword(String newpass, String name, JFrame frame) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String Query = "UPDATE appuser SET Password='" + newpass + "' where username='" + name + "'";
            Statement st = myConn.createStatement();
            st.executeUpdate(Query);
            myConn.close();
        } catch (Exception ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean checkEmail(String Email, JFrame frame) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String mySqlQuery
                    = "SELECT email FROM appuser WHERE email = '"
                    + Email
                    + "'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void contact(String name, String email, String message, JFrame frame) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "123456");
            String Query = "INSERT INTO `contact`(`email`,`name`, `message`) VALUES ("
                    + "\"" + email + "\", "
                    + "\"" + name + "\","
                    + "\"" + message + "," + "\" )";

            Statement st = myConn.createStatement();
            st.executeUpdate(Query);

        } catch (Exception ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
