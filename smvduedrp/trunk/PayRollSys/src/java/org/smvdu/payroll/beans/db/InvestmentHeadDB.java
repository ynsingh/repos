/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.InvestmentHead;

/**
 *
 * @author Algox
 */
public class InvestmentHeadDB {

    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<InvestmentHead> loadHeads()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from investment_heads");
            rs=ps.executeQuery();
            ArrayList<InvestmentHead> data = new ArrayList<InvestmentHead>();
            while(rs.next())
            {
                InvestmentHead dept = new InvestmentHead();
                dept.setNumber(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setBenefit(rs.getBoolean(3));
                dept.setDetails(rs.getString(4));
                System.err.println("Detail  "+dept.getDetails());
                data.add(dept);
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
    public Exception save(String dptName,boolean stat,String detail)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into investment_heads(ih_name,ih_benefit,ih_details) values(?,?,?)",1);
            ps.setString(1, dptName);
            ps.setBoolean(2, stat);
            ps.setString(3, detail);
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
            int code = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return e;
        }

    }

}
