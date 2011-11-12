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
public class EmployeeTaxDB {
    
    
    public EmployeeTaxDB()
    {
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    
    private PreparedStatement ps;
    private ResultSet rs;
    private UserInfo userBean;
    public float getMonthlyInstallment(String empCode) {
        float amount=0;
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select tr_amount from tax_rep_master  where tr_emp_code=? and tr_month_code=?");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentMonth());
            rs=ps.executeQuery();
            if(rs.next())
            {
                amount = rs.getInt(1);
            }
            rs.close();
            ps.close();
            c.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return amount;
    }
    public void saveMonthlyInstallment(String empCode,float amount) {
        System.err.println("Updating TDS Installment : "+amount);
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from tds_installment_master where ti_emp_id=? and ti_year=?");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into tds_installment_master values(?,?,?)");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            ps.setFloat(3, amount);
            ps.executeUpdate();
            ps.close();
            c.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }    
    public boolean updateMarkedMonths(ArrayList<ExtendedMonth> months, String empCode)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from tax_rep_master where tr_emp_code=?");
            ps.setString(1, empCode);
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into tax_rep_master(tr_month_code,tr_emp_code,tr_amount) values(?,?,?)");
            for(ExtendedMonth em : months)
            {
                if(em.getAmount()==0)
                {
                    continue;
                }
                ps.setInt(1, em.getCode());
                ps.setString(2, empCode);
                ps.setInt(3, (int) em.getAmount());
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
    public ArrayList<ExtendedMonth> getMarkedMonths(String empCode) {
        ArrayList<ExtendedMonth> data = new ArrayList<ExtendedMonth>();
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select tr_month_code,tr_amount "
                    + " from tax_rep_master where tr_emp_code=?");
            ps.setString(1, empCode);
            rs = ps.executeQuery();
            System.out.println("Code "+empCode);
            while(rs.next())
            {
                ExtendedMonth em= new ExtendedMonth();
                em.setCode(rs.getInt(1));
                em.setAmount(rs.getInt(2));
                data.add(em);
            }
            rs.close();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        for(ExtendedMonth k : data)
        {
            System.out.println(k.getCode());
        }
        
        System.out.println("************************** "+data.size());
            return data;
    }    
    public float getTaxAmount(String empCode) {
        try
        {
            System.out.println("Code ET : "+empCode);
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select et_amount from emp_tax_master where "
                    + "et_emp_id=? and et_year=? ");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            rs = ps.executeQuery();
            float amount=0;
            if(rs.next())
            {
                amount = rs.getFloat(1);                        
            }
            rs.close();
            ps.close();
            c.close();
            return amount;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }    
    public boolean save(EmployeeTax et)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from emp_tax_master where et_emp_id=? and et_year=?");
            ps.setString(1, et.getName());
            ps.setInt(2, userBean.getCurrentYear());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into emp_tax_master(et_emp_id,et_year,"
                    + "et_amount) values(?,?,?)");            
            ps.setString(1, et.getName());
            ps.setInt(2, userBean.getCurrentYear());
            ps.setFloat(3, et.getAmount());
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
