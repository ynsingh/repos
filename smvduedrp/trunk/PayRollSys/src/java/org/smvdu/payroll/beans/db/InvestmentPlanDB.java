/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.smvdu.payroll.beans.InvestmentPlan;
import org.smvdu.payroll.beans.setup.InvestmentHead;

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
public class InvestmentPlanDB {


    private PreparedStatement ps;
    private ResultSet rs;



    public void delete(int id)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("delete from investment_plan_master where ip_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void savePlan(InvestmentPlan plan)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into investment_plan_master(ip_emp_id,"
                    + "ip_ins_id,ip_amount) values(?,?,?)");
            ps.setInt(1, plan.getEmpId());
            ps.setInt(2, plan.getPlanCode());
            ps.setInt(3, plan.getAmount());
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<InvestmentPlan> loadPlans(int empId)  {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_code,emp_name,ih_name,ip_amount,ip_id from "
                    + "investment_plan_master left join investment_heads on "
                    + "ih_id = ip_ins_id left join employee_master on "
                    + "emp_id = ip_emp_id");
            rs = ps.executeQuery();
            ArrayList<InvestmentPlan> data = new ArrayList<InvestmentPlan>();
            while(rs.next())
            {
                InvestmentPlan ip = new InvestmentPlan();
                ip.setEmpCode(rs.getString(1));
                ip.setEmpname(rs.getString(2));
                InvestmentHead ih = new InvestmentHead();
                ih.setName(rs.getString(3));
                ip.setHeadId(ih);
                ip.setAmount(rs.getInt(4));
                ip.setPlanId(rs.getInt(5));
                data.add(ip);
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
