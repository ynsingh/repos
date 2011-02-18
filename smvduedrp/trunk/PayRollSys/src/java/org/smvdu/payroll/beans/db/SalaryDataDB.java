/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.SalaryData;
import org.smvdu.payroll.beans.UserInfo;

/**
 *
 * @author Algox
 */
public class SalaryDataDB {

    private PreparedStatement ps;
    private ResultSet rs;
    private UserInfo user;

    public SalaryDataDB()  {
        user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }

    public void saveSummary(int income,int deduct,int net,String empCode)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from employee_salary_summery where es_code=?");
            ps.setString(1, empCode);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into employee_salary_summery(es_code,es_month,es_year,"
                    + "es_total_income,es_total_deduct,es_gross,es_last_update_date) values(?,?,?,?,?,?,date(now()))");
            String date = user.getCurrentDate();
            String[] ds = date.split("-");
            int year = Integer.parseInt(ds[0]);
            int month = Integer.parseInt(ds[1]);
            ps.setString(1, empCode);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ps.setInt(4, income);
            ps.setInt(5, deduct);
            ps.setInt(6, net);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean copyLastMonthData(String empCode,String date)   {
        String[] ds = date.split("-");
        int month = Integer.parseInt(ds[1]);
        int year=  Integer.parseInt(ds[0]);
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into salary_data (select sd_emp_code,sd_head_code,"
                    + "?,sd_amount from salary_data where sd_emp_code=? and month(sd_date)=? and year(sd_date)=? )");
            ps.setString(1, user.getCurrentDate());
            ps.setString(2, empCode);
            ps.setInt(3, month);
            ps.setInt(4, year);
            ps.executeUpdate();
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
    public ArrayList<SalaryData> loadCurrentSalaryData(String empCode)    {
        try
        {
            CommonDB cdb = new CommonDB();
            int month = user.getCurrentMonth();
            int year = user.getCurrentYear();
            Connection c = cdb.getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,sh_type,sd_amount,sf_sal_formula,"
                    + "sh_alias,sh_scalable from emp_salary_head_master left join salary_head_master "
                    + "on sh_id = st_sal_code left join salary_data  on sh_id = sd_head_code"
                    + " and sd_emp_code=? and month(sd_date)=? and year(sd_date)=?"
                    + " left join salary_formula  on sf_sal_id = sh_id "
                    + "where st_code = (select emp_type_code from employee_master where emp_code=?)");
            ps.setString(1,empCode);
            ps.setInt(2, month);
            ps.setInt(3, year);
            ps.setString(4,empCode);
            rs=ps.executeQuery();
            ArrayList<SalaryData> data = new ArrayList<SalaryData>();
            while(rs.next())
            {
                SalaryData sd = new SalaryData();
                sd.setHeadCode(rs.getInt(1));
                sd.setHeadName(rs.getString(2));
                sd.setCatagory(rs.getBoolean(3));
                sd.setHeadValue(rs.getInt(4));
                sd.setFormula(rs.getString(5));
                sd.setAlias(rs.getString(6));
                sd.setScalable(rs.getBoolean(7));
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
            String date = user.getCurrentDate();
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from salary_data where sd_emp_code=? and "
                    + "month(sd_date)=? and year(sd_date)=?");
            ps.setString(1, data.get(0).getEmployee().getCode());
            ps.setInt(2, user.getCurrentMonth());
            ps.setInt(3, user.getCurrentYear());
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
