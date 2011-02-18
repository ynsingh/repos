/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.InvestmentType;

/**
 *
 * @author Algox
 */
public class InvestmentTypeDB {

     private PreparedStatement ps;
     private ResultSet rs;


     public ArrayList<InvestmentType> load() {
         try
         {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from investment_type_master");
            rs=ps.executeQuery();
            ArrayList<InvestmentType> data = new ArrayList<InvestmentType>();
            while(rs.next())
            {
                InvestmentType it = new InvestmentType();
                it.setCode(rs.getInt(1));
                it.setName(rs.getString(2));
                data.add(it);
            }
            rs.close();
            ps.close();
            c.close();
            return data;
         }
         catch(Exception e)
         {
             e.printStackTrace();
             return null;
         }
     }
     public Exception save(String name)  {
         try
         {
             Connection c = new CommonDB().getConnection();
             ps=c.prepareStatement("insert into investment_type_master(ic_name) values(?)");
             ps.setString(1, name.toUpperCase());
             ps.executeUpdate();
             ps.close();
             c.close();
             return null;
         }
         catch(Exception e)
         {
             return e;
         }
     }

}
