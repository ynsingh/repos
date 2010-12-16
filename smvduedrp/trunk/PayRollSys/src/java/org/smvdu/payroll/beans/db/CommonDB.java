/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.db;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import org.smvdu.payroll.beans.DBConfig;

/**
 *
 * @author Algox
 */
public class CommonDB {

    private boolean connected = false;

    public boolean isConnected() {
        if (getConnection() != null) {
            return true;
        } else {
            return false;
        }
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    private static Properties dbProps;
    String root = System.getProperty("user.dir");

    public CommonDB() {
    }
    private static String timeStamp;
    private static String username;
    private static String password;
    private static String hostName;
    private static String portNo;
    private static String dbName;

    public static String getDbName() {
        return dbName;
    }

    public static void setDbName(String dbName) {
        CommonDB.dbName = dbName;
    }

    public String testConnection(DBConfig conf) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://" + conf.getHost() + ":" + conf.getPort() + "/" + conf.getDbName(), conf.getUsername(), conf.getPassword());

            CommonDB.dbName = conf.getDbName();
            CommonDB.hostName = conf.getHost();
            CommonDB.password = conf.getPassword();
            CommonDB.portNo = conf.getPort();
            CommonDB.username = conf.getUsername();
            return "Connected . ";

        } catch (Exception e) {
            e.printStackTrace();
            return "Not Connected " + e.getMessage();
        }

    }

    public static String getHostName() {
        return hostName;
    }

    public static void setHostName(String hostName) {
        CommonDB.hostName = hostName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CommonDB.password = password;
    }

    public static String getPortNo() {
        return portNo;
    }

    public static void setPortNo(String portNo) {
        CommonDB.portNo = portNo;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CommonDB.username = username;
    }

    public int getYear() {
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("select year(date(now()))");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int date = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getMonth() {
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("select month(date(now()))");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int date = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getDescriptiveDate() {
        try {
            Connection c = new CommonDB().getConnection();
            PreparedStatement ps = c.prepareStatement("select DATE_FORMAT(date(now()),'%M-%Y')");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String tag = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return tag;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDate() {
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("select date(now())");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String date = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTimeStamp() {
        new CommonDB().loadTimeStamp();
        return timeStamp;
    }

    public static void setTimeStamp(String timeStamp) {
        CommonDB.timeStamp = timeStamp;
    }

    private void loadTimeStamp() {
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("select now()");
            ResultSet rs = ps.executeQuery();
            rs.next();
            timeStamp = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
        } catch (Exception e) {
        }
    }

    public String eval(String exp) {
        try {
            Connection c = new CommonDB().getConnection();
            PreparedStatement ps = c.prepareStatement(exp);
            ResultSet rs = ps.executeQuery();
            rs.next();
            float val = rs.getFloat(1);
            rs.close();
            ps.close();
            c.close();
            return String.valueOf(val);
        } catch (Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }

    public Connection getConnection() {
        root = System.getProperty("user.dir");
        System.out.println("Start Connection . Loading conf from >>> " + root);
        try {
            FileInputStream fin = new FileInputStream(root + File.separator + "database" + File.separator + "dbconf_admin");
            dbProps = new Properties();
            dbProps.load(fin);
            fin.close();
            System.out.println("Host :"+dbProps.getProperty("payroll_db_host"));
            System.out.println("Port :"+dbProps.getProperty("payroll_db_port"));
            System.out.println("Name :"+dbProps.getProperty("payroll_db_user"));
            System.out.println("User :"+dbProps.getProperty("payroll_db_password"));
            System.out.println("Password :"+dbProps.getProperty("payroll_db_name"));
            System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>  Database Connected <<<<<<<<<<<<<<<<<<<<<<<<<");
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://" + dbProps.getProperty("payroll_db_host") + ":" + dbProps.getProperty("payroll_db_port")
                    + "/" + dbProps.getProperty("payroll_db_name") + "", dbProps.getProperty("payroll_db_user"), dbProps.getProperty("payroll_db_password"));
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("-------------> " + e.getMessage());
            return null;
        }
    }
}
