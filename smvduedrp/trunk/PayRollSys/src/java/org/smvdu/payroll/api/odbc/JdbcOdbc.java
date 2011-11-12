/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.odbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class JdbcOdbc {


    ArrayList<Data> data = new ArrayList<Data>();


    class Data
    {
        private String empCode;
        private String date;
        private int amount;
        
    }


    public void saveToJDBC()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pl", "root", "admin");
            PreparedStatement ps = c.prepareStatement("insert into salary_data values(?,?,?,?,?)");
            for(Data d : data)
            {
                try
                {
                ps.setString(1, d.empCode);
                ps.setInt(2, 3);
                ps.setString(3, d.date);
                ps.setInt(4, d.amount);
                ps.setInt(5, 1);
                ps.executeUpdate();
                ps.clearParameters();
                }
                catch(Exception e)
                {
                    
                }
            }
            ps.close();
            c.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {

        JdbcOdbc jo = new JdbcOdbc();
        jo.loadFromODBC();
        jo.saveToJDBC();
    }

    

    public void loadFromODBC()
    {
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection c = DriverManager.getConnection("jdbc:odbc:plo", "admin", "pastonji");
            System.out.println("Connected");
            PreparedStatement ps= c.prepareStatement("select emp_code,format(date_print,'yyyy-MM-dd'),HKCharges from salary_master");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Data dt = new Data();
                dt.empCode = rs.getString(1);
                dt.date = rs.getString(2);
                dt.amount = rs.getInt(3);
                data.add(dt);
            }
            rs.close();
            ps.close();
            c.close();
            System.err.println("Jdbc data count : "+data.size());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
