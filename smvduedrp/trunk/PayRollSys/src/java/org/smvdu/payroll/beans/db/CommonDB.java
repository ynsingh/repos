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


    private Properties dbProps = null;
    private String root;
    public CommonDB()    {
    try
    {
        root = System.getProperty("user.dir");
        FileInputStream fin = new FileInputStream(root+File.separator+"database"+File.separator+"dbconf_admin");
        dbProps = new Properties();
        dbProps.load(fin);
        fin.close();

    }
    catch(Exception e)
    {
        System.err.println("root path : "+root);
        e.printStackTrace();
    }
    }

    private static String timeStamp;
    private static String username;
    private static String password;
    private static String hostName;
    private static String portNo;
    private static String dbName;

    public boolean testConnection(DBConfig fig)    {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://"+fig.getHost()+":"+fig.getPort()+"/"+fig.getDbName()+"", fig.getUsername(),fig.getPassword());
            CommonDB.hostName =fig.getHost();
            CommonDB.portNo = fig.getPort();
            CommonDB.username = fig.getUsername();
            CommonDB.password = fig.getPassword();
            CommonDB.dbName = fig.getDbName();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
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


    public int getDateDiff(String d1,String d2)
    {
        try
        {
            Connection c = getConnection();
            PreparedStatement ps=c.prepareStatement("select date_diff(d2,d1)");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int x = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return x;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
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
    


     public int getYear()   {
        try
        {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("select year(date(now()))");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int date = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return date;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

     public int getMonth()    {
        try
        {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("select month(date(now()))");
            ResultSet rs = ps.executeQuery();
            rs.next();
            int date = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return date;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

     public String getDescriptiveDate()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            PreparedStatement ps = c.prepareStatement("select DATE_FORMAT(date(now()),'%M-%Y')");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String tag = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return tag;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private boolean connected = false;

    public boolean isConnected() {
        if(getConnection()!=null)
        return true;
        else
            return false;
    }
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    public String getDate()    {
        try
        {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("select date(now())");
            ResultSet rs = ps.executeQuery();
            rs.next();
            String date = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return date;
        }
        catch(Exception e)
        {
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
    private  void loadTimeStamp()   {
        try
        {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("select now()");
            ResultSet rs = ps.executeQuery();
            rs.next();
            timeStamp = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {

        }
    }
    public String eval(String exp)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            PreparedStatement ps=c.prepareStatement(exp);
            ResultSet rs = ps.executeQuery();
            rs.next();
            float val = rs.getFloat(1);
            rs.close();
            ps.close();
            c.close();
            return String.valueOf(val);
        }
        catch(Exception e)
        {
            System.out.println(e);
            return e.getMessage();
        }
    }
    public Connection getConnection()   {
        try
        {
            //System.out.println("Port No : "+portNo);
             Class.forName("com.mysql.jdbc.Driver");
             Connection c = DriverManager.getConnection("jdbc:mysql://"+dbProps.getProperty("payroll_db_host")+":"+dbProps.getProperty("payroll_db_port")
                    +"/"+dbProps.getProperty("payroll_db_name")+"", dbProps.getProperty("payroll_db_user"), dbProps.getProperty("payroll_db_password"));
            return c;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
