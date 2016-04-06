/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.taxmanager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class TaxCalculatorDB {

    private PreparedStatement ps;
    private ResultSet rs;
    private Statement stmt;	

    
    private UserInfo userBean;
    
   SessionController sessionId = new SessionController();

   public TaxCalculatorDB()
    {
        userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }
    
    
     
   private float taxValue;

    public float getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(float taxValue) {
        this.taxValue = taxValue;
    }
   /* public float getTaxPercent(int amount)
    {
        try
        {
            if(amount < 200000)
            {
                return (float)0.0;
            }
            int a = 0;
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            float percentf = (float) 0.0;
            float percent = (float) 0.0;
            int numberOfRows=0;
            pst = cn.prepareStatement("select count(*) as rownumber from slab_head where sl_orgCode='"+userBean.getUserOrgCode()+"'");
            rst = pst.executeQuery();
            while(rst.next())
            {
                numberOfRows = rst.getInt("rownumber");
            }
            pst.close();
            rst.close();
            //cn.close();
            System.out.println("Total Value : "+numberOfRows);
            pst = cn.prepareStatement("select sl_start_value,sl_end_value,sl_percent from slab_head where sl_orgCode='"+userBean.getUserOrgCode()+"'");
            rst = pst.executeQuery();
            int i=0;
            int start = 0;
            int end = 0 ;
            Integer[] slabValue = new Integer[numberOfRows] ;
            float amountValue = (float) 0.0;
            while(rst.next())
            {
                start = Integer.parseInt(rst.getString(1));
                end = Integer.parseInt(rst.getString(2));
                if (amount >= start && amount <= end )
                {
                    percentf = (float) rst.getDouble(3);
                    slabValue[i] = (end - start);
                    i++;
                    break;
                }
                else
                {
                    slabValue[i] = (end - start);
                    i++;
                }
            }

            for(int i1 = 0;i1<i-1;i1++)
            {
                if(i1 < slabValue.length)
                {
                    a = amount;
                    amount = amount - slabValue[i1];
                    amountValue = amountValue + ((slabValue[i1]*new TaxCalculatorDB().fPercent(slabValue[i1]))/100);
                    if(amount < 0)
                    {
                        break;
                    }
                    percent = percent + new TaxCalculatorDB().fPercent(slabValue[i1]);
                    //amountValue1
                }
            }
            amountValue = amountValue + ((amount*percentf)/100);
            percent = percent+percentf;
            this.setTaxValue(amountValue); 
            pst.close();
            cn.close();
            return percent;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return (float)0.0;
        }
    }


    public float fPercent(int amount)
    {
        try
        {
            float percent = (float) 0.0;
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            float percentf = (float) 0.0;
            pst = cn.prepareStatement("select sl_start_value,sl_end_value,sl_percent from slab_head where sl_orgCode='"+userBean.getUserOrgCode()+"'");
            rst = pst.executeQuery();
            while(rst.next())
            {
                if (amount >= Integer.parseInt(rst.getString(1)) && amount <= Integer.parseInt(rst.getString(2)))
                {
                    percentf = (float) rst.getDouble(3);
                    break;
                }
            }
            pst.close();
            cn.close();
            return percentf;
        } catch (Exception ex) {
            ex.printStackTrace();
            return (float) 0.0;
        }
    }
	
    public float getActualCalc(String empCode)
    {
        try
        {
            Connection c = new CommonDB().getConnection();
            SessionController session = new SessionController();
            int sdAmount = 0;
            ps = c.prepareStatement("select sum(sd_amount) as amount from `salary_data` where sd_head_code = 13 and sd_emp_code = '"+empCode+"' and sd_sess_id = '"+sessionId.getCurrentSession()+"' and org_code = '"+userBean.getUserOrgCode()+"' ");
            rs = ps.executeQuery();
            rs.next();
            sdAmount = rs.getInt("amount");
            rs.close();
            ps.close();
           return (float)sdAmount; 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public Date getStartingDate(int session)    // add method here;
    {
        try
        {
            Connection cn = new CommonDB().getConnection();
            PreparedStatement pst;
            Date startingDate = null;
            ResultSet rst;
            pst = cn.prepareStatement("select ss_start_from from session_master where ss_id = '"+session+"'");
            rst = pst.executeQuery();
            if(rst.next())
            {
                startingDate = rst.getDate(1);
            }
            return startingDate;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


    public float getNetIncome(String empCode) {
        try
        {
            
            Connection c = new CommonDB().getConnection();
            SessionController session = new SessionController();
            int currentSession = (sessionId.getCurrentSession()-1);
            Date d = new TaxCalculatorDB().getStartingDate(session.getCurrentSession());
            System.out.println(d.toString());
            String[] dateSplit = d.toString().split("-");
            System.out.println(Integer.parseInt(dateSplit[1])); 
            System.out.println("Current Date : "+userBean.getCurrentDate());
            int dd;
            dd = (Integer.parseInt(dateSplit[1])-1);
            System.out.println("Value Of Gross : "+new TaxCalculatorDB().getCurrentSessionGross(empCode)); 
            System.out.println("Date : "+new TaxCalculatorDB().getStartingDate(session.getCurrentSession()));
            //ps = c.prepareStatement("select (es_gross * factor) from employee_salary_summery,`month_master` where es_sess_id = '"+currentSession+"' and es_code = '"+empCode+"' and es_month = month('"+new TaxCalculatorDB().getStartingDate(session.getCurrentSession())+"')  group by es_code ");
            ps = c.prepareStatement("select (es_gross*factor) as va from employee_salary_summery,`month_master` where es_sess_id = '"+currentSession+"' and es_code = '"+empCode+"' and es_month = '"+dd+"' group by es_code");
            System.out.println("SQL VALUE : "+"select (es_gross * factor) as va from employee_salary_summery,`month_master` where es_sess_id = '"+currentSession+"' and es_code = '"+empCode+"' and es_month = '"+dd+"' group by es_code");
            //   ps.setString(1, empCode);
            rs = ps.executeQuery();
            
            //    ps= c.prepareStatement("select es_gross from employee_salary_summery where es_code = '"+empCode+"' and es_month= month('"+new TaxCalculatorDB().getStartingDate(session.getCurrentSession())+"') ");
            float f = 0;
            //float f1 = 0;
            if (rs.next())
            {
                f = rs.getInt("va");
                //System.out.println("Value Of F : "+f);
            }
            rs.close();
            ps.close();
            String cd = userBean.getCurrentDate();
            String[] cda = cd.split("-");
            boolean flag = false;
            int noOfM = 0;
            int j = 12;
            int j1 = 0;
            int idate = Integer.parseInt(cda[1]);
            int i = 0;
            int gross = 0;
            if(idate>4 && session.getCurrentSession()>currentSession)
            {
                  PreparedStatement pst;
                  pst = c.prepareStatement("select es_gross as gross from employee_salary_summery where es_code='"+empCode+"' and es_sess_id='"+session.getCurrentSession()+"' and es_org_id='"+userBean.getUserOrgCode()+"' and es_month < '"+idate+"'");
                  ResultSet rst;
                  rst = pst.executeQuery();
                  while(rst.next())
                  {
                     gross = gross+rst.getInt("gross");
                      System.out.println("Grssss : "+gross);
                  }
                  pst.close();
                  rst.close();
                while(idate < 13)
                {
                    idate++;
                    i++;
                }
                for(int o = 1;o<4;o++)
                {
                    i++;
                }
                System.out.println("Gross Value : "+gross+" : "+i);
                f = gross*i;
            }
            if((idate < 4) && (session.getCurrentSession()==currentSession+1))
            {
                    gross = 0;
                  PreparedStatement pst;
                  pst = c.prepareStatement("select es_gross as gross from employee_salary_summery where es_code='"+empCode+"' and es_sess_id='"+session.getCurrentSession()+"' and es_org_id='"+userBean.getUserOrgCode()+"' and es_month < '"+idate+"'");
                  ResultSet rst;
                  rst = pst.executeQuery();
                  while(rst.next())
                  {
                     gross = gross+rst.getInt("gross");
                  }
                  pst.close();
                  rst.close();
                i = 0;
                while(idate < 4)
                {
                    idate++;
                    i++;
                }
                i = i-1;
                f = gross*i;
                System.out.println("Month Value A : "+i);
            }*/
            /*ps=c.prepareStatement("select es_gross from employee_salary_summery where es_code = '"+empCode+"' and es_sess_id = '"+session.getCurrentSession()+"' and es_month = month('"+new TaxCalculatorDB().getStartingDate(session.getCurrentSession())+"') and es_year = year('"+new TaxCalculatorDB().getStartingDate(session.getCurrentSession())+"')");
            rs=ps.executeQuery();
           if(rs.next())
          {
                f1= rs.getFloat(1);
                System.out.println("Value Of F1 : "+f1);
           }*/
           /*c.close();
           return f;//new TaxCalculatorDB().getCurrentSessionGross(empCode); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }*/
    
    /*public int getCurrentSessionGross(String empCode)
    {
        int grossSalary = 0;
        try
        {
            String date = userBean.getCurrentDate();
            System.out.println("current : "+date);
            String[] dateSplit = date.split("-");
            int idate = Integer.parseInt(dateSplit[1]);
            Connection c = new CommonDB().getConnection();
            SessionController session = new SessionController();
            PreparedStatement pst;
            pst = c.prepareStatement("select es_gross as gross from employee_salary_summery where es_code='"+empCode+"' and es_sess_id='"+session.getCurrentSession()+"' and es_org_id='"+userBean.getUserOrgCode()+"' and es_month < '"+idate+"'");
            ResultSet rst;
            rst = pst.executeQuery();
            while(rst.next())
            {
                grossSalary = grossSalary+rst.getInt("gross");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return grossSalary;
    }
    */
    
    /*public ArrayList<TaxCalculatorBean> loadAnnualStat(String empId){
        TaxCalculatorBean totalBean = new TaxCalculatorBean();
        int netTotal=0;
        int netEffective=0;
        int netSaving=0;
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select ic_id,ic_name,ip_amount,ic_max_limit,"
                    + "ic_deduction from "
                    + "investment_category_master left join investment_heads "
                    + "on ih_under = ic_id left join investment_plan_master on  "
                    + "ip_ins_id = ih_id  where ip_emp_id = ? and ip_org_id='"+userBean.getUserOrgCode()+"' and ip_sess_id='"+sessionId.getCurrentSession()+"' group by ic_id");
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
   
    
        
    }*/

  public int getMonth()
    {
            String date = userBean.getCurrentDate();
            String[] dateSplit = date.split("-");
            int month=Integer.parseInt(dateSplit[1]);
            return month;
    }


    public int getRequiredQuater(){
           int quater=0,month=0;
           month=this.getMonth();
           if(month>=4 && month<=6)
             quater=1;
            if(month>=7 && month<=9)
             quater=2;
            if(month>=10 && month<=12)
             quater=3;
            if(month>=1 && month<=3)
            quater=4;
            return quater;
    }

    public int getRequiredSession(){
           int session=0,quater=0;
           quater=this.getRequiredQuater();
           session=sessionId.getCurrentSession();

           if(quater==1){
               session-=1;}
            return session;
    }


public boolean checkIfEmployeeInvestmentExists(String empId){
      boolean check=true;
      int session=0,month=0;
        try
        {
            session=sessionId.getCurrentSession();
            month=this.getMonth();
             if(month==4)
            {
             session-=1;
            }
            Connection c = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            int row=0,i=0;
            int rows[];
            ps=c.prepareStatement("select exists(select * from investment_plan_master where ip_emp_id=? and ip_sess_id= "+session+")");
            ps.setString(1, empId);
            rs=ps.executeQuery();
            if(rs.next())
            {
                if(!rs.getBoolean(1)){
                check=false;
                }
            }
            return check;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

     }
 public String CalculationType(int orgCode,int session){
        String calctype="QUATERLY";
           try{
           Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select ct_calctype from org_tax_calc_type where ct_org_code= "+orgCode+" and ct_sess_id= "+session);
            rs=ps.executeQuery();
            try{
            if(rs.next()){
            calctype=rs.getString(1);
            }
            }
            catch(SQLException ex)
            {
            ex.printStackTrace();
            }
            finally{
            ps.close();
            rs.close();
            }
           return calctype;
           }
           catch(Exception e)
           {
            e.printStackTrace();
            return null;
           }
    }
public float InvestmentCalculation(String empId){
       float effectiveAmount=0,netSavings=0;
       int session=0,month=0;
        try
        {
            session=sessionId.getCurrentSession();
            month=this.getMonth();
             if(month==4)
            {
             session-=1;
            }
            if(checkIfEmployeeInvestmentExists(empId)){
            ArrayList<EmployeeInvestment> ei = (new EmployeeInvestmentDB()).loadInvestments(empId);
            EmployeeInvestment e=ei.get(ei.size()-1);
            netSavings=e.getNetSavings();
            }
            else{
             netSavings=0;
            }
            return netSavings;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }

    }

    public int SalaryCalculation(String empCode){
       try
       {
            int totalsalary=0,orgCode=0,session=0;
            String calctype="QUATERLY";
            orgCode=userBean.getUserOrgCode();
            session=sessionId.getCurrentSession();
            calctype=this.CalculationType(orgCode,session);
           // System.out.println("calactype======563==="+calctype);
            if(calctype.equalsIgnoreCase("MONTHLY")){
            totalsalary=MonthlyTypeCalc(empCode);
            //System.out.println("month===line 565=="+totalsalary);
            }
            if(calctype.equalsIgnoreCase("QUATERLY")){
            totalsalary=QuaterlyTypeCalc(empCode);
            //System.out.println("month===line 567=="+totalsalary);
            }
            return totalsalary;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }

      }
    public int findAgeDetails(String empCode){
        int gender=0,start_year=0,end_year=0,dob_year=0,age=0,dob_month=0;
        String g="";
        String[] dob=null;
        try
        {
         Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select emp_gender,emp_dob from employee_master where emp_code = ? and emp_org_code='"+userBean.getUserOrgCode()+"'");
            ps.setString(1, empCode);
            rs=ps.executeQuery();
            if(rs.next())
            {
            gender=rs.getInt(1);
            Date d=rs.getDate(2);
            String db=d.toString();
            dob=db.split("-");
            //System.out.println("gender====="+gender+"\ndob===="+dob+"\ndb==="+db);
            }
            ps.close();
            rs.close();

            ps=c.prepareStatement("select ss_start_from,ss_end_to from session_master where ss_id='"+sessionId.getCurrentSession()+"' and ss_org_id='"+userBean.getUserOrgCode()+"'");
            rs=ps.executeQuery();
            if(rs.next()){
                String[] start=(rs.getDate(1)).toString().split("-");
                String[] end=(rs.getDate(2)).toString().split("-");
                start_year=Integer.parseInt(start[0]);
                end_year=Integer.parseInt(end[0]);
            }
            dob_year=Integer.parseInt(dob[0]);
            dob_month=Integer.parseInt(dob[1]);
            if(dob_month>=4 && dob_month<=12)
                age=start_year-dob_year;
            else
                age=end_year-dob_year;
            if(gender==0)
            {
                g="female";
            }
            else{
                if(age>=80)
                    g="Super Senior";
                if(age<80 && age>=60)
                    g="senior citizen";
                if(age<60)
                    g="male";
            }
            ps.close();
            rs.close();
            ps=c.prepareStatement("select ts_seq from ts_gender where gender_name=? and orgCode='"+userBean.getUserOrgCode()+"'");
            ps.setString(1, g);
            rs=ps.executeQuery();
            int ts_seq=0;
            if(rs.next()){
            ts_seq=rs.getInt(1);}
            //System.out.println("ts_seq====="+ts_seq);
            ps.close();
            rs.close();
            c.close();
            return ts_seq;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
     }


 public int MonthlyTypeCalc(String empCode){
     int session=0,totalSalary=0,month=0,tempmonth=0;
     float salary;
     boolean check=false;
         try{
             session=sessionId.getCurrentSession();
            month=this.getMonth();
             if(month==4)
            {
             session-=1;
             check=true;
            }
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select es_total_income,es_month from employee_salary_summery where es_code = ? and  es_org_id='"
                                  +userBean.getUserOrgCode()
                                  +"' and es_sess_id='"+session+"'");
            ps.setString(1, empCode);
            rs=ps.executeQuery();

            while(rs.next()){
                salary=rs.getInt(1);
                tempmonth=rs.getInt(2);
             if(check==true)
             totalSalary+=salary;
             else
             {
              if(month>=1 && month<=3)
              {
               if(tempmonth>=4 && tempmonth<=12)
               totalSalary+=salary;
               if(month==2 && tempmonth==1)
                 totalSalary+=salary;
               if(month==3 && (tempmonth==1 || tempmonth==2))
                totalSalary+=salary;
              }
              else
              {
               if(tempmonth>=4 && tempmonth<month)
               totalSalary+=salary;
              }
             }
            }
          rs.close();
  	  ps.close();
          c.close();
          return totalSalary;
        }
      catch(Exception e)
        {
            e.printStackTrace();
           return 0;
        }

     }

    public int QuaterlyTypeCalc(String empCode){
        int session=0,month=0,quater=0,salarymonth=0,salary=0,totalSalary=0;

        try{
            session=sessionId.getCurrentSession();
            month=this.getMonth();
            //System.out.println("month===line704=="+month);
            if(month>=4 && month<=6)
            {
             session-=1;
             //   session=1;
             //System.out.println("month===line705=="+session);
             
            }
            quater=this.getRequiredQuater();
            //System.out.println("month===line 6=="+quater);
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select es_month,es_total_income from employee_salary_summery where es_code = ? and  es_org_id='"+userBean.getUserOrgCode()+"' and es_sess_id='"+session+"'");
            ps.setString(1, empCode);
            rs=ps.executeQuery();
            while(rs.next()){
                salarymonth=rs.getInt(1);
                //System.out.println("month===line 725=="+salarymonth);
                salary=rs.getInt(2);
                //System.out.println("month===line 726=="+salary);
                if(quater==1){
                    totalSalary+=salary;
                    //System.out.println("month===line 7=="+totalSalary);
                }
                else
                {
                if(salarymonth<=(quater*3) && salarymonth>=4)
                    totalSalary+=salary;
                    //System.out.println("month===line 6=="+quater);
                }
            }
            rs.close();
            ps.close();
            c.close();
            //System.out.println("month===line 742=="+totalSalary);
            return totalSalary;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }

     }
public float getTax(String empCode,float amount)
    {
        try
        {   int ts_seq=findAgeDetails(empCode);
            //System.out.println("month===line 756=="+ts_seq);
            if(ts_seq!=0)
            {
                Connection cn = new CommonDB().getConnection();
                PreparedStatement pst;
                ResultSet rst;
                int i=0;
                int rows[]= new int[10];
                 float tax=0;
                //pst = cn.prepareStatement("select emp_slab_code from emp_slab_head_master where emp_gen_code=? and emp_gen_code=? and emp_slab_orgCode='"+userBean.getUserOrgCode()+"'");
                pst = cn.prepareStatement("select emp_slab_code from emp_slab_head_master where emp_gen_code=? and emp_slab_orgCode='"+userBean.getUserOrgCode()+"'");
                //pst.setString(1, empCode);
                pst.setInt(1, ts_seq);
                rst = pst.executeQuery();
                while(rst.next())
                {
                    rows[i++]=rst.getInt(1);
                    //System.out.println("slab==i=773=="+i);
                }
                pst.close();
                rst.close();
                int slabs=i;
                //System.out.println("slab==fghfghfgf=779=="+slabs+"\nslabs i===="+i);
                i=0;
                while(i!=slabs){
                    pst = cn.prepareStatement("select sl_start_value,sl_end_value,sl_percent from slab_head where sl_orgCode='"+userBean.getUserOrgCode()+"' and sl_head_code='"+rows[i]+"'");
                    rst = pst.executeQuery();
                    int start = 0;
                    int end = 0 ;
                    float percent=0;
                    while(rst.next())
                    {
                        start = rst.getInt(1);
                        end = rst.getInt(2);
                        percent = rst.getFloat(3);
                        // System.out.println("start===792=="+start+"\nend===="+end+"\npercent===="+percent+"\n amount====="+amount);
                        if (amount >= start && amount <= end )
                        {
                            tax+=(amount-start)*(percent/100);
                            /*System.out.println("month===line 796=1loop======="+tax);
                            System.out.println("month===line 796=amount===1loop======="+amount);
                            System.out.println("month===line 796=start===1loop======="+start);
                            System.out.println("month===line 796 percent==firstloop===="+percent);*/
                        }
                        if(amount>end)
                        {
                            tax+= (end - start)*(percent/100);
                            /*System.out.println("month===line 805=="+tax);
                            System.out.println("month===line 806=end===="+end);
                            System.out.println("month===line 807=start===="+start);
                            System.out.println("month===line 808 percent======"+percent);*/
                        }
                    }
                i++;
                //System.out.println("month===line 813======"+tax);
                
                }
                pst.close();
                cn.close();
                //System.out.println("month===line 815======"+tax);
                return tax;
            }
            else
            return 0;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return (float)0.0;
        }
    }
 public float getPaidTax(String empCode){
    try{
        int orgCode=userBean.getUserOrgCode();
        int session=sessionId.getCurrentSession();
        String calctype=this.CalculationType(orgCode,session);
         int month=0,quater=0;
         float  preTax=0;
            if(calctype.equalsIgnoreCase("MONTHLY"))
            {
             session=sessionId.getCurrentSession();
            month=this.getMonth();
            if(month>=8 && month<=10)
            {
             quater=1;
            }
            if((month>=11 && month<=12) || month==1 )
            {
             quater=2;
            }
            if(month>=2 && month<=4)
            quater=3;
            if(month==4)
            {
             session-=1;
            }
            if(month>=5 && month<=7)
            {
             preTax=0;
            }
             else
             preTax=this.paidTaxCalculation(empCode,quater,session);
            }
            if(calctype.equalsIgnoreCase("QUATERLY"))
            {
            session=sessionId.getCurrentSession();
            month=this.getMonth();
            if(month>=4 && month<=6)
            {
             session-=1;
             quater=3;
            }
 if(month>=10 && month<=12)
            {
             quater=1;
            }
            if(month>=1 && month<=3)
            {
             quater=2;
            }
            if(month>=7 && month<=9)
            preTax=0;
            else
            preTax=this.paidTaxCalculation(empCode,quater,session);
            }
            return preTax;
         }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return (float)0.0;
        }

    }
 public float paidTaxCalculation(String empCode,int quater,int session){
        float preTax=0;
        try
        {
            Connection cn = new CommonDB().getConnection();
            ps = cn.prepareStatement("select et_quater,et_amount from emp_tax_master where et_emp_id='"+empCode+"' and et_org_Code="+userBean.getUserOrgCode()+" and et_sess_id="+session);
            rs = ps.executeQuery();
            while(rs.next())
            {
             if(rs.getInt(1)<=quater)
             preTax+=rs.getInt(2);
            }
            ps.close();
            rs.close();
            cn.close();
            return preTax;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return (float)0.0;
        }
    }

	    
}
