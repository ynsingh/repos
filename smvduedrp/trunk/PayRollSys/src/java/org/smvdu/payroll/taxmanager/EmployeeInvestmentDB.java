/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxmanager;

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
public class EmployeeInvestmentDB {
    
    private UserInfo userBean;
    
    public EmployeeInvestmentDB()
    {
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    
    private PreparedStatement ps;
    private ResultSet rs;
    
    
    public boolean update(ArrayList<EmployeeInvestment> data,String empCode)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from investment_plan_master where "
                    + "ip_emp_id=? and ip_year=?");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into investment_plan_master(ip_emp_id,"
                    + "ip_ins_id,ip_amount,ip_year) values(?,?,?,?)");
            for(EmployeeInvestment ei : data)
            {
                ps.setString(1, empCode);
                ps.setInt(2, ei.getCode());
                ps.setFloat(3, ei.getAmount());
                ps.setInt(4, userBean.getCurrentYear());
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
    
    public ArrayList<EmployeeInvestment> loadInvestments(String empCode)   {
        ArrayList<EmployeeInvestment> data = new ArrayList<EmployeeInvestment>();
        try
        {
            Connection c = new CommonDB().getConnection();
            String q = "select ih_id,ih_name,ip_amount,ic_name  from investment_heads "
                    + " left join investment_category_master on ic_id = ih_under "
                    + "left join investment_plan_master on ip_ins_id = ih_id and ip_emp_id=? "
                    + "and ip_year=?";
            //System.err.println(empCode+","+userBean.getCurrentYear());
            ps=c.prepareStatement(q);
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            rs = ps.executeQuery();
            while(rs.next())
            {
                EmployeeInvestment ei = new EmployeeInvestment();
                ei.setCode(rs.getInt(1));
                ei.setName(rs.getString(2));
                ei.setAmount(rs.getFloat(3));
                ei.setUnder(rs.getString(4));
                data.add(ei);
            }
            rs.close();
            ps.close();
            c.close();           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }
    
    
    
    

}
