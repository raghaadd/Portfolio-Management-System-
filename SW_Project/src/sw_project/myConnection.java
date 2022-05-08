/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sw_project;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sara
 */
public class myConnection {
    public static Connection getConnection()throws Exception{
    String dbRoot = "jdbc:mysql://";
    String hostName ="localhost:3306/";
    String dbName = "users";
    String dbUrl = dbRoot +hostName+dbName;
    String hostUsername="root";
    String hostPassword = "";
    Class.forName("com.mysql.jdbc.Driver");
    Connection myConn = (Connection) DriverManager.getConnection(dbUrl, hostUsername, hostPassword);
    return myConn;
    }
}
