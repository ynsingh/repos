/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.SalaryGrade;

/**
 *
 * @author Algox
 */
public class SalaryGradeDB {

    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<SalaryGrade> load()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select * from salary_grade_master");
            rs=ps.executeQuery();
            ArrayList<SalaryGrade> grades = new ArrayList<SalaryGrade>();
            while(rs.next())
            {
                SalaryGrade sg = new SalaryGrade();
                sg.setCode(rs.getInt(1));
                sg.setName(rs.getString(2));
                sg.setMaxValue(rs.getInt(3));
                sg.setMinValue(rs.getInt(4));
                grades.add(sg);
            }
            rs.close();
            ps.close();
            c.close();
            return grades;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    public int save(SalaryGrade sg)   {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into salary_grade_master(grd_name,"
                    + "grd_max,grd_min) values(?,?,?)");
            ps.setString(1, sg.getName());
            ps.setInt(2, sg.getMaxValue());
            ps.setInt(3, sg.getMinValue());
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            rs.next();
            int code = rs.getInt(1);
            rs.close();
            ps.close();
            c.close();
            return code;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

}
