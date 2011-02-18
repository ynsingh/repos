/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.SalaryHead;

/**
 *
 * @author Algox
 */
public class SalaryHeadDB {

    private PreparedStatement ps;
    private ResultSet rs;




    public ArrayList<String> getApplicableHeads(String empCode)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_name from salary_head_master "
                + "where sh_id in (select st_sal_code from emp_salary_head_master "
                + "where st_code =  (select emp_type_code from employee_master "
                + "where emp_code=?) ) order by sh_id ");
            ps.setString(1, empCode);
            rs=ps.executeQuery();
            ArrayList<String> data = new ArrayList<String>();
            while(rs.next())
            {
                data.add(rs.getString(1));
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
    public void updateDefaultvalue(ArrayList<SalaryHead> heads,int emptype)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from default_salary_master where ds_emp_type=?");
            ps.setInt(1, emptype);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("update default_salary_master set ds_amount=? "
                    + "where ds_sal_head=? and ds_emp_type=?");
            for(SalaryHead sh : heads)
            {
                ps.setInt(1, sh.getDefaultValue());
                ps.setInt(2, sh.getNumber());
                ps.setInt(3, emptype);
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void update(ArrayList<SalaryHead> heads)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update salary_head_master set sh_name=? where sh_id=?");
            for(SalaryHead sh : heads)
            {
                ps.setString(1, sh.getName().toUpperCase());
                ps.setInt(2, sh.getNumber());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void updateDefault(ArrayList<SalaryHead> heads,int code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from default_salary_master where ds_emp_type=?");
            ps.setInt(1, code);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into default_salary_master values(?,?,?)");
            for(SalaryHead sh : heads)
            {
                ps.setInt(1, code);
                ps.setInt(2, sh.getNumber());
                ps.setInt(3, sh.getDefaultValue());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public ArrayList<SalaryHead> loadAppliedHeads(int empType)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,sh_calc_type,sh_type "
                    + "from emp_salary_head_master left join salary_head_master"
                    + " on sh_id = st_sal_code where st_code=? order by sh_type");
            ps.setInt(1, empType);
            //System.err.println(">>> Type Code : "+empType);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead sal = new SalaryHead();
                sal.setNumber(rs.getInt(1));
                sal.setName(rs.getString(2));
                sal.setCalculationType(rs.getBoolean(3));
                sal.setUnder(!rs.getBoolean(4));
                data.add(sal);
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



    public ArrayList<SalaryHead> loadSelectedHeads(int empType)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_id,sh_name,ds_amount,sh_type from "
                    + "salary_head_master left join default_salary_master "
                    + "on ds_sal_head = sh_id and ds_emp_type=? where sh_calc_type=0 ");
            ps.setInt(1, empType);
            //System.err.println(">>> Type Code : "+empType);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead sal = new SalaryHead();
                sal.setNumber(rs.getInt(1));
                sal.setName(rs.getString(2));
                sal.setDefaultValue(rs.getInt(3));
                sal.setCalculationType(rs.getBoolean(4));
                data.add(sal);
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
    public ArrayList<SalaryHead> loadSelectedDeductionHeads(int empType)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select st_sal_code,sh_name,ds_amount,sh_type from "
                    + "emp_salary_head_master left join salary_head_master "
                    + "on sh_id = st_sal_code left join default_salary_master "
                    + "on ds_emp_type = st_code and ds_sal_head=sh_id where st_code=? and "
                    + "sh_type=0 ");
            ps.setInt(1, empType);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setDefaultValue(rs.getInt(3));
                dept.setCalculationType(rs.getBoolean(4));
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
    public ArrayList<SalaryHead> loadSelectedIncomeHeads(int empType)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select st_sal_code,sh_name,ds_amount,sh_type from "
                    + "emp_salary_head_master left join salary_head_master "
                    + "on sh_id = st_sal_code left join default_salary_master "
                    + "on ds_emp_type = st_code and ds_sal_head=sh_id where st_code=? "
                    + " and sh_type=1 ");
            ps.setInt(1, empType);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setDefaultValue(rs.getInt(3));
                dept.setCalculationType(rs.getBoolean(4));
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
    public boolean updateFormula(int code,String formula)    {
        try
        {
              Connection c = new CommonDB().getConnection();
              ps=c.prepareStatement("update salary_head_master set sh_formula=? where sh_id=?");
              ps.setString(1, formula);
              ps.setInt(2, code);
              ps.executeUpdate();
              ps.close();
              c.close();
              return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public ArrayList<SalaryHead> loadConsolidatedHeads()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from salary_head_master where sh_calc_type=0");
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setUnder(rs.getBoolean(3));
                dept.setAlias(rs.getString(4));
                dept.setCalculationType(rs.getBoolean(5));
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
    public ArrayList<SalaryHead> loadHeads(boolean type)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from salary_head_master where sh_type=?");
            ps.setBoolean(1, type);
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setUnder(rs.getBoolean(3));
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
    public ArrayList<SalaryHead> loadAllHeads()   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select * from salary_head_master order by sh_id");
            rs=ps.executeQuery();
            ArrayList<SalaryHead> data = new ArrayList<SalaryHead>();
            while(rs.next())
            {
                SalaryHead dept = new SalaryHead();
                dept.setNumber(rs.getInt(1));
                dept.setName(rs.getString(2));
                dept.setUnder(rs.getBoolean(3));
                dept.setAlias(rs.getString(4));
                dept.setCalculationType(rs.getBoolean(5));
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
    public Exception save(SalaryHead form)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into salary_head_master(sh_name,sh_type,sh_alias,sh_calc_type) values(?,?,?,?)");
            ps.setString(1, form.getName().toUpperCase());
            ps.setBoolean(2, form.isUnder());
            ps.setString(3, form.getAlias());
            ps.setBoolean(4, form.isCalculationType());
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
