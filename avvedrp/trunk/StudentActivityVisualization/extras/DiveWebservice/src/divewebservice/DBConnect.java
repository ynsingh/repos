/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.sql.*;

public class DBConnect{
  public static void main(String[] args) {
  System.out.println("MySQL Connect Example.");
  Connection conn = null;
  String url = "jdbc:mysql://localhost:3306/";
  String dbName = "test";
  String driver = "com.mysql.jdbc.Driver";
  String userName = "root"; 
  String password = "amma";
  try {
  Class.forName(driver).newInstance();
  conn = DriverManager.getConnection(url+dbName,userName,password);
  System.out.println("Connected to the database");
  Statement stmt = conn.createStatement();
  stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
  conn.close();
  System.out.println("Disconnected from database");
  } catch (Exception e) {
  e.printStackTrace();
  }
  }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.sql.*;

public class DBConnect{
  public static void main(String[] args) {
  System.out.println("MySQL Connect Example.");
  Connection conn = null;
  String url = "jdbc:mysql://localhost:3306/";
  String dbName = "test";
  String driver = "com.mysql.jdbc.Driver";
  String userName = "root"; 
  String password = "amma";
  try {
  Class.forName(driver).newInstance();
  conn = DriverManager.getConnection(url+dbName,userName,password);
  System.out.println("Connected to the database");
  Statement stmt = conn.createStatement();
  stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
  conn.close();
  System.out.println("Disconnected from database");
  } catch (Exception e) {
  e.printStackTrace();
  }
  }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.sql.*;

public class DBConnect{
  public static void main(String[] args) {
  System.out.println("MySQL Connect Example.");
  Connection conn = null;
  String url = "jdbc:mysql://localhost:3306/";
  String dbName = "test";
  String driver = "com.mysql.jdbc.Driver";
  String userName = "root"; 
  String password = "amma";
  try {
  Class.forName(driver).newInstance();
  conn = DriverManager.getConnection(url+dbName,userName,password);
  System.out.println("Connected to the database");
  Statement stmt = conn.createStatement();
  stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
  conn.close();
  System.out.println("Disconnected from database");
  } catch (Exception e) {
  e.printStackTrace();
  }
  }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.sql.*;

public class DBConnect{
  public static void main(String[] args) {
  System.out.println("MySQL Connect Example.");
  Connection conn = null;
  String url = "jdbc:mysql://localhost:3306/";
  String dbName = "test";
  String driver = "com.mysql.jdbc.Driver";
  String userName = "root"; 
  String password = "amma";
  try {
  Class.forName(driver).newInstance();
  conn = DriverManager.getConnection(url+dbName,userName,password);
  System.out.println("Connected to the database");
  Statement stmt = conn.createStatement();
  stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
  conn.close();
  System.out.println("Disconnected from database");
  } catch (Exception e) {
  e.printStackTrace();
  }
  }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package divewebservice;

import java.sql.*;

public class DBConnect{
  public static void main(String[] args) {
  System.out.println("MySQL Connect Example.");
  Connection conn = null;
  String url = "jdbc:mysql://localhost:3306/";
  String dbName = "test";
  String driver = "com.mysql.jdbc.Driver";
  String userName = "root"; 
  String password = "amma";
  try {
  Class.forName(driver).newInstance();
  conn = DriverManager.getConnection(url+dbName,userName,password);
  System.out.println("Connected to the database");
  Statement stmt = conn.createStatement();
  stmt.executeUpdate("insert into master(module_name,user_name) values('ffff','fffffff')");
  conn.close();
  System.out.println("Disconnected from database");
  } catch (Exception e) {
  e.printStackTrace();
  }
  }
}