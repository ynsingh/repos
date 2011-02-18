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
            ps=c.prepareStatement("select ih_id,ih_name,ih_benefit,ih_details,ic_id,ic_name "
                    + " from investment_heads left join investment_category_master on ic_id = ih_under");
            rs=ps.executeQuery();
            ArrayList<InvestmentHead> data = new ArrayList<InvestmentHead>();
            while(rs.next())
            {
                InvestmentHead dept = new InvestmentHead();
                dept.setNumber(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setBenefit(rs.getBoolean(3));
                dept.setDetails(rs.getString(4));
                dept.setUnderGroupCode(rs.getInt(5));
                dept.setUnderGroupName(rs.getString(6));
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
    public Exception save(String dptName,boolean stat,String detail,int group)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into investment_heads(ih_name,ih_benefit,"
                    + "ih_details,ih_under) values(?,?,?,?)",1);
            ps.setString(1, dptName.toUpperCase());
            ps.setBoolean(2, stat);
            ps.setString(3, detail);
            ps.setInt(4, group);
            ps.executeUpdate();
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
