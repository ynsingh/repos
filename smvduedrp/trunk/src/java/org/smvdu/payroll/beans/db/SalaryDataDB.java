/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.SalaryData;

/**
 *
 * @author Algox
 */
public class SalaryDataDB {

    private PreparedStatement ps;
    private ResultSet rs;


    public ArrayList<SalaryData> loadSalaryData(String date)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name from salary_head_master ");
            rs=ps.executeQuery();
            ArrayList<SalaryData> data = new ArrayList<SalaryData>();
            while(rs.next())
            {
                SalaryData sd = new SalaryData();
                sd.setHeadCode(rs.getInt(1));
                sd.setHeadName(rs.getString(2));
                sd.setHeadValue(1000);
                data.add(sd);
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
    public ArrayList<SalaryData> loadRawData()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name from salary_head_master ");
            rs=ps.executeQuery();
            ArrayList<SalaryData> data = new ArrayList<SalaryData>();
            while(rs.next())
            {
                SalaryData sd = new SalaryData();
                sd.setHeadCode(rs.getInt(1));
                sd.setHeadName(rs.getString(2));
                sd.setHeadValue(1000);
                data.add(sd);
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
    public boolean save(ArrayList<SalaryData> data) {
        try
        {
            String date = new CommonDB().getDate();
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from salary_data where sd_emp_code=? and "
                    + "month(sd_date)=? and year(sd_date)=?");
            ps.setString(1, data.get(0).getEmployee().getCode());
            ps.setInt(2, new CommonDB().getMonth());
            ps.setInt(3, new CommonDB().getYear());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into salary_data values(?,?,?,?)");
            for(SalaryData sd : data)
            {
                ps.setString(1, sd.getEmployee().getCode());
                ps.setInt(2, sd.getHeadCode());
                ps.setString(3,date);
                ps.setInt(4, sd.getHeadValue());
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
