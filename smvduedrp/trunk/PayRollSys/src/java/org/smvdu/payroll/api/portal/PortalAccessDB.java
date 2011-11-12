/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.portal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.CommonDB;

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
public class PortalAccessDB {
    
    
    private PreparedStatement ps;
    private ResultSet rs;
    private UserInfo userBean;
    
    public ArrayList<PortalAccess> loadACL() {
        userBean = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_code,emp_name,dept_name,el_login_name,el_password from "
                    + "employee_master left join department_master on dept_code = emp_dept_code "
                    + "left join employee_login_master on el_id = emp_code where emp_org_code=?");
            ps.setInt(1, userBean.getUserOrgCode());
            rs = ps.executeQuery();
            ArrayList<PortalAccess> data = new ArrayList<PortalAccess>();
            while(rs.next())
            {
                PortalAccess pa = new PortalAccess();
                pa.setCode(rs.getString(1));
                pa.setName(rs.getString(2));
                pa.setDepartment(rs.getString(3));
                pa.setLoginId(rs.getString(4));
                pa.setPassword(rs.getString(5));
                data.add(pa);
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
