/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.SalaryFormula;

/**
 *
 * @author Algox
 */
public class SalaryFormulaDB {
    private PreparedStatement ps;
    private ResultSet rs;


    public String loadFormula(int code)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sf_sal_formula from salary_formula "
                    + "left join salary_head_master on sh_id = sf_sal_id where sf_sal_id=?");
            ps.setInt(1, code);
            rs=ps.executeQuery();
            rs.next();
            String formula = rs.getString(1);
            rs.close();
            ps.close();
            c.close();
            return formula;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
    public ArrayList<SalaryFormula> loadFormula()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select sh_name,sf_sal_formula from salary_formula "
                    + "left join salary_head_master on sh_id = sf_sal_id");
            rs=ps.executeQuery();
            ArrayList<SalaryFormula> data = new ArrayList<SalaryFormula>();
            while(rs.next())
            {
                SalaryFormula sf = new SalaryFormula();
                sf.setName(rs.getString(1));
                sf.setFormula(rs.getString(2));
                data.add(sf);
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
    public boolean save(SalaryFormula sf)    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from salary_formula where sf_sal_id=?");
            ps.setInt(1, sf.getSalCode());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("INSERT INTO SALARY_FORMULA VALUES(?,?)");
            ps.setInt(1, sf.getSalCode());
            ps.setString(2, sf.getFormula());
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

}
