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
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.setup.EmployeeTypeSalaryHead;
import org.smvdu.payroll.beans.setup.SalaryHead;

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
public class EmployeeTypeSalaryHeadDB {

    private PreparedStatement ps;
    private ResultSet rs;
    private UserInfo userBean;

    public EmployeeTypeSalaryHeadDB() {
        userBean =(UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    
    public void save(ArrayList<EmployeeTypeSalaryHead> data,int type)
    {
       try
       {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from emp_salary_head_master where st_code=?");
            ps.setInt(1, type);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into emp_salary_head_master values(?,?,?)");
            for(EmployeeTypeSalaryHead sh : data)
            {
                ps.setInt(1, type);
                ps.setInt(2, sh.getNumber());
                ps.setInt(3, userBean.getUserOrgCode());
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

   public ArrayList<EmployeeTypeSalaryHead> loadHeads(int type) {
       ArrayList<EmployeeTypeSalaryHead> allheads = new ArrayList<EmployeeTypeSalaryHead>();
       ArrayList<SalaryHead> selected = new SalaryHeadDB().loadAppliedHeads(type);
       for(SalaryHead sh : new SalaryHeadDB().loadAllHeads())
       {
           EmployeeTypeSalaryHead esh = new EmployeeTypeSalaryHead();
           esh.setNumber(sh.getNumber());
           esh.setCalculationType(sh.isCalculationType());
           esh.setUnder(sh.isUnder());
           esh.setName(sh.getName());
           if(selected.contains(sh))
           {
               System.out.println("Hello World");
               esh.setSelected(true);
           }
           allheads.add(esh);
       }
       return allheads;
       
    }

}
