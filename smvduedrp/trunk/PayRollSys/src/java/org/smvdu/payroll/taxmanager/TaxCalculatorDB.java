/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
public class TaxCalculatorDB {

    private PreparedStatement ps;
    private ResultSet rs;

    public float getTaxPercent() {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement(" select it_percent from system_private_data");
            rs=ps.executeQuery();
            float f= 0;
            if(rs.next())
            {
                f = rs.getFloat(1);
            }
            rs.close();
            ps.close();
            c.close();
            return f;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public float getNetIncome(String empCode) {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement(" select sum(es_gross) from employee_salary_summery where es_code=? "
                    + "group by es_code");
            ps.setString(1, empCode);
            rs=ps.executeQuery();
            float f= 0;
            if(rs.next())
            {
                f = rs.getFloat(1);
            }
            rs.close();
            ps.close();
            c.close();
            return f;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
    public ArrayList<TaxCalculatorBean> loadAnnualStat(String empId){
        TaxCalculatorBean totalBean = new TaxCalculatorBean();
        int netTotal=0;
        int netEffective=0;
        float netSaving=0;
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select ic_id,ic_name,sum(ip_amount),ic_max_limit,"
                    + "ic_deduction from "
                    + "investment_category_master left join investment_heads "
                    + "on ih_under = ic_id left join investment_plan_master on  "
                    + "ip_ins_id = ih_id  where ip_emp_id = ? group by ic_id");
            ps.setString(1, empId);
            rs=ps.executeQuery();
            ArrayList<TaxCalculatorBean> data = new ArrayList<TaxCalculatorBean>();
            while(rs.next())
            {
                TaxCalculatorBean tcb = new TaxCalculatorBean();
                tcb.setHeadCode(rs.getInt(1));
                tcb.setInvestmentHead(rs.getString(2));
                tcb.setActualAmount(rs.getInt(3));
                tcb.setMaxLimitAmount(rs.getInt(4));
                if(tcb.getActualAmount()>tcb.getMaxLimitAmount())
                {
                    tcb.setEffectiveAmount(tcb.getMaxLimitAmount());
                }
                else
                {
                    tcb.setEffectiveAmount(tcb.getActualAmount());
                }
                tcb.setPercentDeduction(rs.getFloat(5));
                tcb.setPercentDeduction((tcb.getEffectiveAmount()*tcb.getPercentDeduction())/100);
                netEffective+=tcb.getEffectiveAmount();
                netTotal+=tcb.getActualAmount();
                netSaving+=tcb.getPercentDeduction();
                System.out.println("Total = "+netTotal+", Effective : "+netEffective);
                data.add(tcb);
            }
            totalBean.setInvestmentHead("Net Total");
            totalBean.setActualAmount(netTotal);
            totalBean.setTotalInvestment(netTotal);
            totalBean.setEffectiveAmount(netEffective);
            totalBean.setMaxLimitAmount(netEffective);
            totalBean.setPercentDeduction(netSaving);
            data.add(totalBean);
            System.out.println("Total = "+totalBean.getActualAmount()+", Effective : "+totalBean.getEffectiveAmount());
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
