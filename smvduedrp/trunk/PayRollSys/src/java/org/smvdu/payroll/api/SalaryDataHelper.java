/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class SalaryDataHelper {

    private PreparedStatement ps;
    private ResultSet rs;


    public ArrayList<FormattedDate> getCopyDate(String empCode)
    {
        System.out.println("Loading existing salary dates for "+empCode);
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select distinct(date_format(sd_date,'%M-%Y')),sd_date "
                    + "from salary_data where sd_emp_code=?");
            ps.setString(1, empCode);
            rs=ps.executeQuery();
            ArrayList<FormattedDate> data = new ArrayList<FormattedDate>();
            while(rs.next())
            {
                FormattedDate fd =new FormattedDate();
                fd.setDisplayString(rs.getString(1));
                fd.setValueString(rs.getString(2));
                data.add(fd);
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

}
