/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.pf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.SessionController;
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
public class PFOpeningBalanceDB {

    private PreparedStatement ps;
    private ResultSet rs, rs1;    //Added rs1 here
    private SessionController sessionController;
    private final UserInfo userBean;


    public PFOpeningAccount getAccount(String code) {
        try
        {
            SessionController session = new SessionController();
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_code,emp_name,pf_op_balance,pf_op_balance_empl,sum(pf_amount) from "
                    + "employee_master left join pf_data_master  as pdm on pf_emp_id = emp_id and pdm.pf_org_id = emp_org_code"
                    + " left join pf_account_master as pam on pf_ac_id = emp_id and pam.pf_org_id = emp_org_code where pf_sess = '"+session.getCurrentSession()+"' "
                    + "and emp_code='"+code+"' and emp_org_code = '"+userBean.getUserOrgCode()+"' group by pf_emp_id");
            System.err.println("SCODE : "+session.getCurrentSession());
            rs = ps.executeQuery();
            rs.next();
            PFOpeningAccount pac = new PFOpeningAccount();
            pac.setEmpCode(rs.getString(1));
            pac.setEmpName(rs.getString(2));
            System.out.println("Opening Balence : "+rs.getInt(3)); 
            pac.setOpBal(rs.getInt(3));
            pac.setEmployerBalance(rs.getInt(4));
            pac.setTotalWithdrawal(rs.getInt(5));
            System.out.println(pac.getEmpCode()+" : "+pac.getEmpName()+" : "+pac.getOpBal()+" : "+pac.getEmployerBalance()+" : "+pac.getTotalWithdrawal());
            rs.close();
            ps.close();
            c.close();
            return pac;
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private HashMap<String,Integer> loadRecovery(String code)
    {
        try
        {
            HashMap<String,Integer> data = new HashMap<String, Integer>();
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("(select monthname(mm_month),sd_amount from salary_data left join month_master_2 on "
                    + " month(sd_date) = month(mm_month) where sd_emp_code=? and sd_head_code=36 )");
            ps.setString(1, code);
            rs = ps.executeQuery();
            while(rs.next())
            {
                data.put(rs.getString(1), rs.getInt(2));
                System.out.println("Enter String Here : "+rs.getString(1)+" : "+rs.getInt(2));
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new HashMap<String, Integer>();
        }

    }



    public ArrayList<PFData> getData(String code)   {
        try
        {
            SessionController session = new SessionController();
            HashMap<String,Integer> map= loadRecovery(code);
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select monthname(mm_month),sd_amount,factor,pf_amount"
                    + " from month_master_2 left join salary_data on month(sd_date) = "
                    + "month(mm_month) left join pf_account_master on pf_ac_id = "
                    + "(select emp_id from employee_master  where emp_code=sd_emp_code)"
                    + " and month(pf_ac_date) = month(sd_date) AND pf_sess_id='"+session.getCurrentSession()+"' and pf_org_id='"+userBean.getUserOrgCode()+"' where sd_emp_code='"+code+"'"
                    + " and sd_head_code=12 and sd_sess_id='"+session.getCurrentSession()+"' and org_code='"+userBean.getUserOrgCode()+"'");
           /* ps.setString(1, code);
            ps.setInt(2, sessionController.getCurrentSession());*/
            System.out.println("Session : "+session.getCurrentSession());
            rs = ps.executeQuery();
            ArrayList<PFData> data = new ArrayList<PFData>();
            int cl = 0;
            int ccl=0;
            while(rs.next())
            {
                PFData d = new PFData();
                d.setMonth(rs.getString(1));
                d.setAmount(rs.getInt(2));
                d.setEmployerAmount(rs.getInt(2));
                d.setFactor(rs.getInt(3));
                d.setWithdrawal(rs.getInt(4));
                System.out.println(d.getMonth()+" : "+d.getAmount()+" : "+d.getEmployerAmount()+" : "+d.getFactor()+" : "+d.getWithdrawal());
                if(map.containsKey(d.getMonth()) == true)
                {
                    d.setRecovery(map.get(d.getMonth()));
                }
                cl+=d.getAmount()+d.getRecovery()-d.getWithdrawal();
                d.setCumulative(cl);
                ccl+=d.getAmount();
                d.setEmplCum(ccl);
                data.add(d);
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

    public PFOpeningBalanceDB() {
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        sessionController = (SessionController) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SessionController");
    }


    public String refreshReport(int sessionId) {
        //SessionController session = new SessionController();
        String s = "report/APF.jsf";
        System.err.println("Current Session : " + sessionId);
        try {
            
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("delete from annual_pf_master where apf_sess_id=? and apf_org_id=?");
            ps.setInt(1, sessionId);
            ps.setInt(2, userBean.getUserOrgCode());
            ps.executeUpdate();
            ps.close();



            //   Query For Reading From Pf_data_master and insert into apf_annual_pf_master for opening balence


            openingToOpening(sessionId);
 
            ps=c.prepareStatement("commit");  //Additional added
            ps.executeQuery();
            ps.close();



            System.err.println("------->  Loading Total Contribution   ------------ ");
            ps = c.prepareStatement("update annual_pf_master left join employee_master on "
                    + "emp_id = apf_emp_id set apf_emp_contribution = (select sum(sd_amount) "
                    + "from salary_data  where sd_head_code =12 and sd_sess_id=? and sd_emp_code = emp_code group "
                    + "by sd_emp_code  ) where apf_sess_id=? and apf_org_id = '"+userBean.getUserOrgCode()+"'");
            ps.setInt(1, sessionId);
            ps.setInt(2, sessionId);
            ps.executeUpdate();
            ps.close();
            ps = c.prepareStatement("update annual_pf_master set apf_empl_contribution =apf_emp_contribution where apf_org_id = '"+userBean.getUserOrgCode()+"'");
            ps.executeUpdate();
            ps.close();
            
            System.err.println("------->  Loading Cumulative Contribution   ------------ ");
            ps = c.prepareStatement("update annual_pf_master left join employee_master on "
                    + "emp_id = apf_emp_id set apf_cum_amount_emp = (select sum(sd_amount*factor) "
                    + "from salary_data  left join month_master on month(sd_date) = month(mm_month) "
                    + "and sd_head_code =12 and sd_sess_id=? where sd_emp_code = emp_code group "
                    + "by sd_emp_code  ) where apf_sess_id=? and apf_org_id = '"+userBean.getUserOrgCode()+"'");
            ps.setInt(1, sessionId);
            ps.setInt(2, sessionId);
            ps.executeUpdate();
            ps.close();
            ps = c.prepareStatement("update annual_pf_master set apf_cum_amount_empl = apf_cum_amount_emp "
                    + "where apf_sess_id=? and apf_org_id = '"+userBean.getUserOrgCode()+"'");
            ps.setInt(1, sessionId);
            ps.executeUpdate();
            ps.close();
             System.err.println("------->  Loading Cumulative Contribution   ------------ ");
            ps = c.prepareStatement("update annual_pf_master left join employee_master on "
                    + "emp_id = apf_emp_id set apf_cum_amount_emp = apf_cum_amount_emp+(select sum(sd_amount*factor) "
                    + "from salary_data  left join month_master on month(sd_date) = month(mm_month) "
                    + "and sd_head_code =36 and sd_sess_id=? where sd_emp_code = emp_code and org_code = '"+userBean.getUserOrgCode()+"' " 
                    + "group by sd_emp_code) where apf_sess_id=? and apf_org_id = '"+userBean.getUserOrgCode()+"'");
            ps.setInt(1, sessionId);
            ps.setInt(2, sessionId);
            
            ps.executeUpdate();
            ps.close();



// cummulative amount getting updated after subtracting withdrawals




            ps = c.prepareStatement("update annual_pf_master left join pf_account_master "
                    + "on pf_ac_id = apf_emp_id set apf_cum_amount_emp ="
                    + "(apf_cum_amount_emp-(datediff(now(),pf_ac_date)/30)*pf_amount) where apf_org_id = '"+userBean.getUserOrgCode()+"' and pf_org_id = '"+userBean.getUserOrgCode()+"'");
            ps.executeUpdate();
            ps.close();

            // Updating With Drawal
            ps = c.prepareStatement("select emp_id, sum(pf_amount) "
                    + "from employee_master,pf_account_master where emp_id = pf_ac_id and pf_sess_id = '"+sessionId+"' and emp_org_code = '"+userBean.getUserOrgCode()+"' and pf_org_id = '"+userBean.getUserOrgCode()+"'"
                    + "group by emp_id");
            rs = ps.executeQuery();
            while(rs.next())
            {
                ps = c.prepareStatement("update annual_pf_master set apf_withdrawal = '"+rs.getInt(2)+"' where apf_emp_id = '"+rs.getInt(1)+"' and apf_sess_id = '"+sessionId+"' and apf_org_id='"+userBean.getUserOrgCode()+"'");
                ps.executeUpdate();
            }
            //ps = c.prepareStatement("select emp_id , sum(pf_ampont) from ")
           /* ps = c.prepareStatement("update annual_pf_master left join pf_account_master on "
                    + "pf_ac_id = apf_emp_id set apf_withdrawal = (select sum(pf_amount) from "
                    + "pf_account_master where pf_ac_id = apf_emp_id group by pf_ac_id) where apf_sess_id=? "); //ffrom
            ps.setInt(1, sessionController.getCurrentSession());
            ps.executeUpdate(); Sushant Sir Coading For With Drawal*/
            ps.close();



            //Updating PFReocvery



            System.err.println("------->  Loading Anual PF Recovery   ------------ ");
            ps = c.prepareStatement("select emp_id, sum(sd_amount) from employee_master,salary_data where emp_code = sd_emp_code and "
                    + "sd_head_code = 36 and sd_sess_id = '"+sessionId+"' and org_code = '"+userBean.getUserOrgCode()+"' and emp_org_code='"+userBean.getUserOrgCode()+"' group by emp_id");

            rs = ps.executeQuery();
            while(rs.next())
            {
                ps = c.prepareStatement("update annual_pf_master set apf_recovery = '"+rs.getInt(2)+"' where apf_emp_id = '"+rs.getInt(1)+"' and apf_sess_id = '"+sessionId+"' and apf_org_code = '"+userBean.getUserOrgCode()+"'");
                ps.executeUpdate();
            }
            ps.close();




            System.err.println("------->  Loading Cumulative Contribution   ------------ ");
          //  ps = c.prepareStatement("update annual_pf_master set apf_int_dep_emp = (apf_cum_amount_emp)/600");
          //  ps.executeUpdate();
          //  ps.close();
          //  ps = c.prepareStatement("update annual_pf_master set apf_int_dep_empr =apf_int_dep_emp ");
          //  ps.executeUpdate();
          //  ps.close();


            ps = c.prepareStatement("update annual_pf_master set apf_int_opbal_emp = (2*apf_op_bal)/25 where apf_org_id = '"+userBean.getUserOrgCode()+"'"); // CHANGED THE FORMULA 2* APF_OP_BAL / 25
            ps.executeUpdate();
            ps.close();
             ps = c.prepareStatement("update annual_pf_master set apf_int_opbal_empr =0 where apf_org_id='"+userBean.getUserOrgCode()+"'");//EMPLOYER'S OPENING BAL SET TO 0
            ps.executeUpdate();
            ps.close();
           
            //Making all kinds of changes in this section to calculate actual interest//

            ps= c.prepareStatement("select emp_code,apf_emp_id from employee_master,annual_pf_master where emp_id=apf_emp_id and apf_sess_id=? and emp_org_code='"+userBean.getUserOrgCode()+"' and apf_org_id='"+userBean.getUserOrgCode()+"'");
            ps.setInt(1, sessionId);
            rs1= ps.executeQuery();
            while (rs1.next())
            {  ps=c.prepareStatement("select month(mm_month),sd_amount,factor,pf_amount,sd_emp_code"
                    + " from month_master left join salary_data on month(sd_date) = "// We Have Change From Month_Master_2 to Month_Master
                    + "month(mm_month) left join pf_account_master on pf_ac_id = "
                    + "(select emp_id from employee_master  where emp_code=sd_emp_code and emp_org_code='"+userBean.getUserOrgCode()+"')"
                    + " and month(pf_ac_date) = month(sd_date) AND pf_sess_id=sd_sess_id and pf_org_id = org_code where sd_emp_code=? and org_code = '"+userBean.getUserOrgCode()+"'"
                    + " and sd_head_code=12 and sd_sess_id=?");
              ps.setString(1, rs1.getString(1));
              ps.setInt(2, sessionId);
              rs = ps.executeQuery();
              float pfamtcum=(float) 0.0, pfamtsplcum= (float)0.0, pfamtcume=(float) 0.0, pfamtsplcume= (float) 0.0;
              while (rs.next())
                     { pfamtcum = pfamtcum + rs.getInt(2);
                       pfamtsplcum = pfamtsplcum + pfamtcum;
                       if(this.getRecoveryOfEmployees(sessionId,rs1.getInt(1)).containsKey(rs.getInt(1)) == true)
                       {
                            pfamtcume=pfamtcume + rs.getInt(2) - rs.getInt(4)+this.getRecoveryOfEmployees(sessionId,rs1.getInt(1)).get(rs.getInt(1));
                       }
                       pfamtsplcume = pfamtsplcume + pfamtcume;
                     }
              pfamtsplcum= (pfamtsplcum)/150;
              pfamtsplcume=(pfamtsplcume)/150;
              System.out.println("Pfamtcume : "+pfamtsplcume+"Code : "+rs1.getInt(1));
              ps.close();

              // Rounding Off..done by Sumit Kes...

              ps = c.prepareStatement("update annual_pf_master set apf_int_dep_emp =" + (int)(pfamtsplcum+0.51d) +" where apf_emp_id=? and apf_sess_id=? and apf_org_id = '"+userBean.getUserOrgCode()+"'");
              ps.setInt(1, rs1.getInt(2));
              ps.setInt(2, sessionId);
              ps.executeUpdate();
              ps.close();

              ps = c.prepareStatement("update annual_pf_master set apf_int_dep_empr =" + (int)(pfamtsplcume+0.51d) + " where apf_emp_id=? and apf_sess_id=? and apf_org_id='"+userBean.getUserOrgCode()+"'");
              ps.setInt(1, rs1.getInt(2));
              ps.setInt(2, sessionId);
              ps.executeUpdate();
              ps.close();
              
            }


            //Actual program continues here

            
            System.err.println("------->  Refresh Closing Balance   ------------ ");
            ps = c.prepareStatement("update annual_pf_master set apf_closing_bal = "
                    + "((apf_op_bal)+apf_emp_contribution+apf_empl_contribution+"
                    + "apf_int_opbal_emp+apf_int_dep_emp+apf_int_dep_empr+apf_recovery-apf_withdrawal) where apf_org_id = '"+userBean.getUserOrgCode()+"'");// CHANGED THE FORMULA BY EXCLUDING apf_int_opbal_empr
            ps.executeUpdate();
            ps.close();



            // Employee becomes opening balence of current session ,,, This opening balence came from closing balence of last session

            closingToOpening(sessionId);
            c.close();
            System.err.println("------->  Report Refreshed");
        } catch (Exception e) {
               e.printStackTrace();
        }
        return s;
    }



     public void closingToOpening(int session)
    {
        try
        {
          PreparedStatement pre,pst,pst1,pst2; 
          Connection cn;
          cn = new CommonDB().getConnection();
          ResultSet rest,rst1;
          int i1 = session+1;
          pre = cn.prepareStatement("select apf_closing_bal, apf_sess_id,apf_emp_id from annual_pf_master , `employee_master` where apf_emp_id = emp_id and emp_org_code = '"+userBean.getUserOrgCode()+"' and apf_org_id='"+userBean.getUserOrgCode()+"' and apf_sess_id = '"+session+"'");
          rest = pre.executeQuery();
          while(rest.next())
          {
              pst = cn.prepareStatement("select pf_emp_id,pf_sess from pf_data_master where pf_emp_id = '"+rest.getInt(3)+"' and pf_org_id = '"+userBean.getUserOrgCode()+"' and pf_sess = '"+i1+"'");
              rst1 = pst.executeQuery();
              if(rst1.next())
              {
                  System.out.println("Enter String Here updation..........");
                  pst1 = cn.prepareStatement("update pf_data_master set pf_op_balance = '"+rest.getInt(1)+"' where pf_emp_id = '"+rest.getInt(3)+"' and pf_sess = '"+i1+"' and pf_org_id = '"+userBean.getUserOrgCode()+"'");
                  pst1.executeUpdate();
                  pst1.close();
              }
              else
              {
                  System.out.println("Enter String Here inserting...............");
                  pst2 = cn.prepareStatement("insert into pf_data_master values('"+rest.getInt(3)+"','"+rest.getInt(1)+"','"+0+"','"+i1+"','"+userBean.getUserOrgCode()+"')");
                  pst2.executeUpdate();
                  pst2.close();
              }
              pst.close();
          }
          pre.close();
          cn.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : "+ex.getMessage());
            ex.printStackTrace();
        }
    }



     // Data Transfer From Pf_data_master from apf_annual_pf_master


     public void openingToOpening(int session)
    {
        try
        {
            int i = session+1;
            Connection cn;
            ResultSet res1;
            PreparedStatement pt,pt1;
            ResultSet rt,rt1;
            cn = new CommonDB().getConnection();
            PreparedStatement p = cn.prepareStatement("select pf_emp_id,pf_op_balance,pf_sess from pf_data_master where pf_sess = '"+session+"' and pf_org_id='"+userBean.getUserOrgCode()+"'");
            res1= p.executeQuery();
            while(res1.next())
            {
                pt=cn.prepareStatement("select apf_emp_id,apf_op_bal,apf_sess_id,apf_closing_bal from annual_pf_master where apf_emp_id = '"+res1.getInt(1)+"' and apf_sess_id = '"+session+"' and apf_org_id='"+userBean.getUserOrgCode()+"'");
                rt = pt.executeQuery();
                if(rt.next())
                {
                    pt1 = cn.prepareStatement("select pf_op_balance,pf_sess from pf_data_master where pf_emp_id = '"+rt.getInt(1)+"'"+ " and pf_sess = '"+session+"' and pg_org_id='"+userBean.getUserOrgCode()+"'");
                    rt1 = pt1.executeQuery();
                    if(rt1.next())
                    { 
                        System.out.println("Enter String Here.......");
                        pt = cn.prepareStatement("update annual_pf_master set apf_op_bal ="
                            + "'"+rt1.getInt(1)+"', apf_closing_bal = '"+rt.getInt(4)+"'  where apf_sess_id = '"+rt1.getInt(2)+"' and apf_emp_id = '"+session+"' and apf_org_id='"+userBean.getUserOrgCode()+"'");
                        pt.executeUpdate();
                        pt.close();
                    }
                    pt1.close();
                }
                else
                {
                    System.out.println("Inserting Records.........................");
                    pt1 = cn.prepareStatement("insert into annual_pf_master (apf_emp_id,apf_op_bal,apf_sess_id,apf_org_id) values('"+res1.getInt(1)+"','"+res1.getInt(2)+"','"+session+"','"+userBean.getUserOrgCode()+"')");
                    pt1.executeUpdate();
                    pt1.close();
                }
           }
        }
        catch(Exception ex)
        {
            System.out.println("Error : "+ex.getMessage());
        }
    }
    public void update(ArrayList<PFOpeningAccount> pas) {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("delete from pf_data_master where pf_org_id='"+userBean.getUserOrgCode()+"'");
            ps.executeUpdate();
            ps.close();
            ps = c.prepareStatement("insert into pf_data_master values(?,?,?,?,?)");   // only 1 opbal empr + emp combined
            for (PFOpeningAccount pa : pas) {
                ps.setInt(1, pa.getEmpId());
                ps.setInt(2, pa.getOpBal());
                ps.setInt(3, 0);
                ps.setInt(4, sessionController.getCurrentSession());
                ps.setInt(5,userBean.getUserOrgCode());
                ps.executeUpdate();
                ps.clearParameters();
            }
            ps.close();
            c.close();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Updated", ""));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not Updated", ""));
        }
    }



    //Getting PF Recovery For Employer's Sharing



     public HashMap<Integer,Integer> getRecoveryOfEmployees(int se,int code)
    {
        try
        {
            PreparedStatement prep;
            Connection c;
            ResultSet rst;
            ArrayList<Integer> a = new ArrayList<Integer>();
           HashMap<Integer,Integer> value = new HashMap<Integer, Integer>();
            c = new CommonDB().getConnection();
            prep = c.prepareStatement("select sd_amount,emp_code,month(mm_month) from salary_data,employee_master,month_master where sd_emp_code = '"+code+"' and sd_head_code = 36 and month(mm_month) = month(sd_date) and sd_sess_id = '"+se+"' and emp_org_code='"+userBean.getUserOrgCode()+"' and org_code='"+userBean.getUserOrgCode()+"'");
            rst = prep.executeQuery();
            while(rst.next())
            {
                value.put(rst.getInt(3),rst.getInt(1));
            }
            rst.close();
            prep.close();
            c.close();
            return value;
        }
        catch(Exception ex)
        {
            System.out.println("Enter String Here : "+ex.getMessage());
            return null;
        }
    }







    public ArrayList<PFOpeningAccount> getAccount() {
        try {
            Connection c = new CommonDB().getConnection();
            ps = c.prepareStatement("select emp_code,emp_name,pf_op_balance,pf_op_balance_empl,emp_id from "
                    + "employee_master left join pf_data_master on pf_emp_id = emp_id and pf_org_id=emp_org_code where emp_org_code='"+userBean.getUserOrgCode()+"'");
            rs = ps.executeQuery();
            ArrayList<PFOpeningAccount> data = new ArrayList<PFOpeningAccount>();
            while (rs.next()) {
                PFOpeningAccount po = new PFOpeningAccount();
                po.setEmpCode(rs.getString(1));
                po.setEmpName(rs.getString(2));
                po.setOpBal(rs.getInt(3));
                po.setEmployerBalance(rs.getInt(4));
                po.setEmpId(rs.getInt(5));
                data.add(po);
            }
            rs.close();
            ps.close();
            c.close();
            return data;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
