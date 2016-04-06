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
import org.smvdu.payroll.beans.composite.SessionController;
import org.smvdu.payroll.beans.db.CommonDB;
import javax.faces.application.FacesMessage;

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
    
   SessionController sessionId = new SessionController();

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
                    + "ip_emp_id=? and ip_year=? and ip_sess_id= '"+sessionId.getCurrentSession()+"' and ip_org_id= '"+userBean.getUserOrgCode()+"'");
            ps.setString(1, empCode);
            ps.setInt(2, userBean.getCurrentYear());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("insert into investment_plan_master(ip_emp_id,"
                    + "ip_ins_id,ip_amount,ip_year,ip_sess_id,ip_org_id) values(?,?,?,?,?,?)");
            for(EmployeeInvestment ei : data)
            {
		if(ei.getHeadCode()!=0){
                	ps.setString(1, empCode);
			ps.setInt(2, ei.getHeadCode());
                	ps.setInt(3,ei.getActualInvestment());
                	//ps.setInt(2, ei.getCode());
                	//ps.setFloat(3, ei.getAmount());
                	ps.setInt(4, userBean.getCurrentYear());
                	ps.setInt(5, sessionId.getCurrentSession());
                	ps.setInt(6, userBean.getUserOrgCode());
                	ps.executeUpdate();
                	ps.clearParameters();
		}
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
    
    /*public ArrayList<EmployeeInvestment> loadInvestments(String empCode)   {
        ArrayList<EmployeeInvestment> data = new ArrayList<EmployeeInvestment>();
        try
        {
            Connection c = new CommonDB().getConnection();
            String q = "select ih_id,ih_name,ip_amount,ic_name  from investment_heads "
                    + " left join investment_category_master on ic_id = ih_under "
                    + "left join investment_plan_master on ip_ins_id = ih_id and ip_emp_id=? "
                    + "and ip_year=? and ip_sess_id = '"+sessionId.getCurrentSession()+"' where ih_org_id='"+userBean.getUserOrgCode()+"'";
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
    }*/
    
      public boolean checkIfEmployeeExists(String empId){
    	boolean exist=false;
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select exists(select * from employee_master where emp_code=?)");
            ps.setString(1, empId);
            rs=ps.executeQuery();
            if(rs.next())
            {
             exist=rs.getBoolean(1);
            }
            ps.close();
            rs.close();
            c.close();
            return exist;
        }
       catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void checkIfEmployeeDataExists(String empId){
      try
        {

            Connection c = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            int row=0,i=0;
            int rows[];
            ps=c.prepareStatement("select exists(select * from investment_plan_master where ip_emp_id=? and ip_sess_id="+sessionId.getCurrentSession()+")");
            ps.setString(1, empId);
            rs=ps.executeQuery();
            if(rs.next())
            {
                if(!rs.getBoolean(1)){
                pst=c.prepareStatement("select count(*)  from investment_heads ");
                rst=pst.executeQuery();
                if(rst.next()){
                row=rst.getInt(1);}
                rows=new int[row];
                i=0;
                pst.close();
                rst.close();
                pst=c.prepareStatement("select ih_id from investment_heads ");
                rst=pst.executeQuery();
                while(rst.next()){
                 rows[i++]=rst.getInt(1);
                }
                pst.close();
                rst.close();
                pst=c.prepareStatement("insert into investment_plan_master(ip_emp_id,"
                    + "ip_ins_id,ip_amount,ip_year,ip_sess_id,ip_org_id) values(?,?,?,?,?,?)");
                for(i=0;i<row;i++){
                pst.setString(1, empId);
                pst.setInt(2, rows[i]);
                pst.setFloat(3, 0);
                pst.setInt(4, userBean.getCurrentYear());
                pst.setInt(5, sessionId.getCurrentSession());
                pst.setInt(6, userBean.getUserOrgCode());
                pst.executeUpdate();
                pst.clearParameters();
                }
                pst.close();
		ps.close();
                rs.close();
                c.close();

                }


           }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
 /*
     public ArrayList<EmployeeInvestment> loadInvestments(String empId){
         ArrayList<EmployeeInvestment> data=null;
        EmployeeInvestment totalBean = new EmployeeInvestment();
        int netTotal=0;
        int total=0;
        int saving=0;
        int netSaving=0;
        int effectiveInv=0;
        int limit=0;
        float percent=0;
        int maxLimit=0;
        String prev="";
        try
        {
           if(this.checkIfEmployeeExists(empId)){
                this.checkIfEmployeeDataExists(empId);
                Connection c = new CommonDB().getConnection();

                ps=c.prepareStatement("select ih_name,ic_name,ip_amount,ic_max_limit,ic_deduction,ih_id  from investment_heads "
                    + " left join investment_category_master on ic_id = ih_under "
                    + "left join investment_plan_master on ip_ins_id = ih_id and ip_emp_id=? "
                    + "and ip_year=? and ip_sess_id = '"+sessionId.getCurrentSession()+"' where ip_org_id='"+userBean.getUserOrgCode()+"'"+" order by ic_id");
                ps.setString(1, empId);
                ps.setInt(2, userBean.getCurrentYear());
                rs=ps.executeQuery();
                data = new ArrayList<EmployeeInvestment>();
                EmployeeInvestment ei = new EmployeeInvestment();
                if(rs.next()){
                    prev=rs.getString(2);
                    ei.setInvestmentHead(rs.getString(1));
                    ei.setInvestmentUnder(rs.getString(2));
                    ei.setActualInvestment(rs.getInt(3));
                    ei.setMaxLimitAmount(rs.getInt(4));
                    if(ei.getActualInvestment()>ei.getMaxLimitAmount())
                    {
                        effectiveInv=ei.getMaxLimitAmount();
                    }
                    else
                    {
                        effectiveInv=ei.getActualInvestment();
                    }
                    percent=rs.getFloat(5);
                    ei.setNetSavings(percent);
                    ei.setNetSavings((effectiveInv*ei.getNetSavings())/100);
                    ei.setHeadCode(rs.getInt(6));
                    total+=ei.getActualInvestment();
                    saving+=ei.getNetSavings();
                    limit=ei.getMaxLimitAmount();
                    data.add(ei);
                }//if
                while(rs.next())
                {
                    if(!(prev.equals(rs.getString(2))))
                    {
                        EmployeeInvestment eibb = new EmployeeInvestment();
                        eibb.setInvestmentHead("Total Investment");
                        eibb.setInvestmentUnder(prev);
                        eibb.setActualInvestment(total);
                        eibb.setMaxLimitAmount(limit);
                        if(eibb.getActualInvestment()>eibb.getMaxLimitAmount())
                        {
                            effectiveInv=eibb.getMaxLimitAmount();
                        }
                        else
                        {
                            effectiveInv=eibb.getActualInvestment();
                        }

                        eibb.setNetSavings(effectiveInv*percent/100);
                        eibb.setHeadCode(0);
                        data.add(eibb);

                        netTotal+=total;
                        netSaving+=eibb.getNetSavings();
                        maxLimit+=limit;
                        total=0;
                        saving=0;
                        limit=0;
                        prev=rs.getString(2);


                    }//if
                    EmployeeInvestment eib = new EmployeeInvestment();
                    eib.setInvestmentHead(rs.getString(1));
                    eib.setInvestmentUnder(rs.getString(2));
                    eib.setActualInvestment(rs.getInt(3));
                    eib.setMaxLimitAmount(rs.getInt(4));
                    if(eib.getActualInvestment()>eib.getMaxLimitAmount())
                    {
                        effectiveInv=eib.getMaxLimitAmount();
                    }
                    else
                    {
                        effectiveInv=eib.getActualInvestment();
                    }
                    percent=rs.getFloat(5);
                    eib.setNetSavings(percent);
                    eib.setNetSavings((effectiveInv*eib.getNetSavings())/100);
                    eib.setHeadCode(rs.getInt(6));
                    total+=eib.getActualInvestment();
                    saving+=eib.getNetSavings();
                    limit=eib.getMaxLimitAmount();
                    data.add(eib);
                }//while
                
                EmployeeInvestment eib = new EmployeeInvestment();
                eib.setInvestmentHead("Total Investment");
                eib.setInvestmentUnder(prev);
                eib.setActualInvestment(total);
                eib.setMaxLimitAmount(limit);
		data.add(eib);
           // }//if
            EmployeeInvestment eib = new EmployeeInvestment();
            eib.setInvestmentHead("Total Investment");
            eib.setInvestmentUnder(prev);
            eib.setActualInvestment(total);
            eib.setMaxLimitAmount(limit);
            if(eib.getActualInvestment()>eib.getMaxLimitAmount())
            {
             effectiveInv=eib.getMaxLimitAmount();
            }
            else
            {
              effectiveInv=eib.getActualInvestment();
            }
            eib.setNetSavings(effectiveInv*percent/100);
            eib.setHeadCode(0);
            netTotal+=total;
            netSaving+=eib.getNetSavings();
            maxLimit+=limit;
            data.add(eib);

            totalBean.setInvestmentHead("Net Total");
            totalBean.setInvestmentUnder("All CAtegory");
            totalBean.setActualInvestment(netTotal);
            totalBean.setMaxLimitAmount(maxLimit);
            totalBean.setNetSavings(netSaving);
            totalBean.setHeadCode(0);
            data.add(totalBean);
            rs.close();
            ps.close();
            c.close();
           }
           else{
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee doesnot exists", ""));
           }
	   return data;


        }

        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

       }*/
     public ArrayList<EmployeeInvestment> loadInvestments(String empId){
         ArrayList<EmployeeInvestment> data=null;
        EmployeeInvestment totalBean = new EmployeeInvestment();
        int netTotal=0;
        int total=0;
        int saving=0;
        int netSaving=0;
        int effectiveInv=0;
        int limit=0;
        float percent=0;
        int maxLimit=0;
        String prev="";
        try
        {
           if(this.checkIfEmployeeExists(empId)){
           this.checkIfEmployeeDataExists(empId);
            Connection c = new CommonDB().getConnection();

            ps=c.prepareStatement("select ih_name,ic_name,ip_amount,ic_max_limit,ic_deduction,ih_id  from investment_heads "
                    + " left join investment_category_master on ic_id = ih_under "
                    + "left join investment_plan_master on ip_ins_id = ih_id and ip_emp_id=? "
                    + "and ip_year=? and ip_sess_id = '"+sessionId.getCurrentSession()+"' where ip_org_id='"+userBean.getUserOrgCode()+"'"+" order by ic_id");
            ps.setString(1, empId);
            ps.setInt(2, userBean.getCurrentYear());
            rs=ps.executeQuery();
            data = new ArrayList<EmployeeInvestment>();
            EmployeeInvestment ei = new EmployeeInvestment();
            if(rs.next()){
            prev=rs.getString(2);
                ei.setInvestmentHead(rs.getString(1));
                ei.setInvestmentUnder(rs.getString(2));
                ei.setActualInvestment(rs.getInt(3));
                ei.setMaxLimitAmount(rs.getInt(4));
                if(ei.getActualInvestment()>ei.getMaxLimitAmount())
                {
                    effectiveInv=ei.getMaxLimitAmount();
                }
                else
                {
                    effectiveInv=ei.getActualInvestment();
                }
                percent=rs.getFloat(5);
                ei.setNetSavings(percent);
                ei.setNetSavings((effectiveInv*ei.getNetSavings())/100);
                ei.setHeadCode(rs.getInt(6));
                total+=ei.getActualInvestment();
                saving+=ei.getNetSavings();
                 limit=ei.getMaxLimitAmount();
                data.add(ei);
            }
            while(rs.next())
            {
                if(!(prev.equals(rs.getString(2))))
                {
                 EmployeeInvestment eibb = new EmployeeInvestment();
                 eibb.setInvestmentHead("Total Investment");
                 eibb.setInvestmentUnder(prev);
                 eibb.setActualInvestment(total);
                 eibb.setMaxLimitAmount(limit);
                 if(eibb.getActualInvestment()>eibb.getMaxLimitAmount())
                 {
                     effectiveInv=eibb.getMaxLimitAmount();
                 }
                 else
                 {
                     effectiveInv=eibb.getActualInvestment();
                 }
                 eibb.setNetSavings(effectiveInv*percent/100);
                 eibb.setHeadCode(0);
                 data.add(eibb);

                 netTotal+=total;
                 netSaving+=eibb.getNetSavings();
                 maxLimit+=limit;
                 total=0;
                 saving=0;
                 limit=0;
                 prev=rs.getString(2);


                }
                EmployeeInvestment eib = new EmployeeInvestment();
                eib.setInvestmentHead(rs.getString(1));
                eib.setInvestmentUnder(rs.getString(2));
                eib.setActualInvestment(rs.getInt(3));
                 eib.setMaxLimitAmount(rs.getInt(4));
                if(eib.getActualInvestment()>eib.getMaxLimitAmount())
                {
                    effectiveInv=eib.getMaxLimitAmount();
                }
                else
                {
                    effectiveInv=eib.getActualInvestment();
                }
                percent=rs.getFloat(5);
                eib.setNetSavings(percent);
                eib.setNetSavings((effectiveInv*eib.getNetSavings())/100);
                eib.setHeadCode(rs.getInt(6));
                total+=eib.getActualInvestment();
                saving+=eib.getNetSavings();
                 limit=eib.getMaxLimitAmount();
                data.add(eib);
            }
            EmployeeInvestment eib = new EmployeeInvestment();
            eib.setInvestmentHead("Total Investment");
            eib.setInvestmentUnder(prev);
            eib.setActualInvestment(total);
            eib.setMaxLimitAmount(limit);
            if(eib.getActualInvestment()>eib.getMaxLimitAmount())
            {
             effectiveInv=eib.getMaxLimitAmount();
            }
            else
            {
              effectiveInv=eib.getActualInvestment();
            }
            eib.setNetSavings(effectiveInv*percent/100);
            eib.setHeadCode(0);
            netTotal+=total;
            netSaving+=eib.getNetSavings();
            maxLimit+=limit;
            data.add(eib);

            totalBean.setInvestmentHead("Net Total");
            totalBean.setInvestmentUnder("All CAtegory");
            totalBean.setActualInvestment(netTotal);

	    totalBean.setMaxLimitAmount(maxLimit);
            totalBean.setNetSavings(netSaving);
            totalBean.setHeadCode(0);
            data.add(totalBean);
            rs.close();
            ps.close();
            c.close();
           }
           else{
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee doesnot exists", ""));
           }
            return data;


        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

       }

			
}
