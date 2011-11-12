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
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
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
