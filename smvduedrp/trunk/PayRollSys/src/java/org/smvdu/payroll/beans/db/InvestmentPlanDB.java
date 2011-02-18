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
import org.smvdu.payroll.beans.InvestmentHead;

/**
 *
 * @author Algox
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
