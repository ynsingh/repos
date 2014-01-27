/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *  This class acts as the central resource for database connection.
 *  The general working principal is Connect, Execute and Close.
 *  Database connection is managed by DBCP and is pooled. COnfiguration details
 *  can be found and edited in context.xml file.
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITK
*  Modified Date: 26 Dec 2013, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*
*/
public class CommonDB {


    
    public CommonDB()    {
   
    }

    private static String timeStamp;
    

   
    /**
     * @param date Returns the First day of the month / year corresponding to the given Day.
     * e.g it will return 1-1-2010 for the given date 5-1-2010
     * @return : returns the first day of the month of the corresponding date
     */

    public String getFirstDay(String date)
    {
        String[] d = date.split("-");
        return d[0]+"-"+d[1]+"-1";
    }

    /**
     * @param date Returns the Last day of the month / year corresponding to the given Day.
     * e.g it will return 31-1-2010 for the given date 5-1-2010
     * @return : returns the last day of the month of the corresponding date
     */


    public String getLastDate(String date) {
        try
        {
            Connection c = new CommonDB().getConnection();
            PreparedStatement ps=c.prepareStatement("select last_day(?)");
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String d = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return d;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * Returns the difference in days of the two given days d2 and d1.
     * @param d1 date which is to be subtracted in yyyy-mm-dd format
     * @param d2 date from where is to be subtracted in yyyy-mm-dd format
     * @return : returns the number of day of between the given two days
     */


    public int getDateDiff(String d1,String d2)
    {
        try
        {
            Connection c = getConnection();
            PreparedStatement ps=c.prepareStatement("select datediff(?,?)");
            ps.setString(1, d2);
            ps.setString(2, d1);
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

    /**
     * Evaluates the current year of the system date
     *
     * @return : current year in yyyy format
     */

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

      /**
     * Evaluates the current month of the system date
     *
     * @return : current year in mm format
     */

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

      /**
     * Evaluates the current Month Name - year of the system date
     *
     * @return : current Month-year in MM-yyyy format
     */

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

      /**
     * Evaluates the current Date
     *
     * @return : current year in yyyy-mm-dd format
     */
    
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
     /**
     * Evaluates the current time of the system
     *
     * @return : current Tim in Date-Time format
     */
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
    
  /**
     * Pulls a connection object from Connection pool
     *
     * @return : instance of Database Connection
     */
    
    
    public Connection getConnection()   {
       try
        {
            Connection conn;
           Class.forName("com.mysql.jdbc.Driver");
           conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/pl","etrg","brihaspatigroup");
            return conn;
        }
        catch(Exception e)
        {
            //ErrorManager.manageError(e);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Connection for Brihaspati General Accounting System (BGAS) Database
     * @return : instance of Database Connection
     */
    public Connection getwebzashConnection()   {
       try
        {
            Connection conn;
           Class.forName("com.mysql.jdbc.Driver");
           conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/webzash","etrg","brihaspatigroup");
            return conn;
        }
        catch(Exception e)
        {
            //ErrorManager.manageError(e);
            e.printStackTrace();
            return null;
        }
    }
    
}
