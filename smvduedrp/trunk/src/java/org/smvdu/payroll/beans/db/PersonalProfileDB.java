/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.smvdu.payroll.beans.PersonalProfileForm;

/**
 *
 * @author Algox
 */
public class PersonalProfileDB {
    private PreparedStatement ps;
    private ResultSet rs;

    public Exception save(PersonalProfileForm pf)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into employee_profile_master(emp_org_code,"
                    + "emp_name,emp_dept_code,emp_desig_code,emp_dob,emp_doj,emp_dol,"
                    + "emp_bank_accno,emp_pf_accno,emp_pan_no,emp_address,emp_phone,emp_email"
                    + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, pf.getEmpcode());
            ps.setString(2, pf.getName());
            ps.setInt(3, pf.getDepartment());
            ps.setInt(4, pf.getDesignation());
            ps.setString(5, pf.getDob());
            ps.setString(6, pf.getDoj());
            ps.setString(7, pf.getDol());
            ps.setString(8, pf.getBankaccNo());
            ps.setString(9, pf.getPfaccNo());
            ps.setString(10, pf.getPanNo());
            ps.setString(11, pf.getAddress());
            ps.setString(12, pf.getPhone());
            ps.setString(13, pf.getEmail());
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
