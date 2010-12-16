/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.EmpTypeHead;
import org.smvdu.payroll.beans.SalaryMap;

/**
 *
 * @author Algox
 */
public class EmpTypeHeadDB {

    private PreparedStatement ps;
    private ResultSet rs;
    public ArrayList<Integer> getHeadCodes(int code)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id from emp_salary_head_master "
                    + "left join salary_head_master on sh_id = st_sal_code where "
                    + " st_code=?");
            ps.setInt(1, code);
            rs=ps.executeQuery();
            ArrayList<Integer> data = new ArrayList<Integer>();
            while(rs.next())
            {
                data.add(rs.getInt(1));
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

    public ArrayList<SelectItem> getHeads(int code)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name from emp_salary_head_master "
                    + "left join salary_head_master on sh_id = st_sal_code where "
                    + " st_code=?");
            ps.setInt(1, code);
            rs=ps.executeQuery();
            ArrayList<SelectItem> data = new ArrayList<SelectItem>();
            while(rs.next())
            {
                SelectItem si = new SelectItem(rs.getInt(1), rs.getString(2));
                data.add(si);
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
    public boolean save(SalaryMap th)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from emp_salary_head_master where st_code=?");
            ps.setInt(1, th.getTypeCode());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into emp_salary_head_master values(?,?)");
            for(int x: th.getSalaryCode())
            {
                ps.setInt(1,th.getTypeCode());
                ps.setInt(2,(Integer) x);
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
            return true;
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
