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
 * @author Algox
 */
public class TaxCalculatorDB {

    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<TaxCalculatorBean> loadAnnualStat(String empId)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select ic_id,ic_name,sum(ip_amount),ic_max_limit from "
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
                data.add(tcb);
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
