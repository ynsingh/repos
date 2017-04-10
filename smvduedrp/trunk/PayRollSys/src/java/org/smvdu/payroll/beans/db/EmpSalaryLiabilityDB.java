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
import org.smvdu.payroll.api.tools.FormulaProcessor;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.EmpSalaryLiability;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.SalaryData;
import org.smvdu.payroll.beans.composite.NewSalaryProcessing;
import org.smvdu.payroll.beans.setup.SalaryHead;


/**
*  *Copyright (c) 2010 - 2011 SMVDU, Katra.
*   Copyright (c) 2014 - 2017 ETRG, IITK.
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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  Modified Date: 7 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
*  Last Modification :(Salary Processing with Budgets), January 2017, Manorama Pal (palseema30@gmail.com).
*/


public class EmpSalaryLiabilityDB {
    
    private int orgCode = 0;
    private UserInfo uf;
    //private Employee empval;
   
    public EmpSalaryLiabilityDB() {
        
        uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = uf.getUserOrgCode();
    
    }
    private PreparedStatement ps;
    private ResultSet rs;
    
    
    public ArrayList<EmpSalaryLiability> loadEmpLiability(String current) {
        try {
            
            Connection c = new CommonDB().getConnection();
            
            int month = uf.getCurrentMonth();
            int year = uf.getCurrentYear();
            
            String q ="select * from employee_salary_liability"
                     +" where esl_month ='"+month+"' and esl_year ='"+year+"' and esl_org_id='" +orgCode+ "'";
                               
            
            ps = c.prepareStatement(q);
            rs = ps.executeQuery();
            ArrayList<EmpSalaryLiability> data = new ArrayList<EmpSalaryLiability>();
            
            int k = 1;
            while (rs.next()) {
                
                EmpSalaryLiability empliab = new EmpSalaryLiability();
                
                empliab.setCode(rs.getString(1).trim());
                Employee employee = new EmployeeDB().loadProfile(empliab.getCode(),orgCode);
                empliab.setEmployee(employee);
                empliab.setMonth(rs.getInt(2));
                empliab.setYear(rs.getInt(3));
                empliab.setLiabiltyAmt(rs.getInt(4));
                if(rs.getString(5).equals("credit")){
                    //String sh="<font color='red'>salary on hold</font>";
                    empliab.setStatus("salary processed");
                    empliab.setBtnDisabled(false);
                }
                else{
                   // String sp="<font color='green'>salary proccessed</font>";
                    //empliab.setStatus("<html><font color='green'>salary proccessed</font></html>");
                    empliab.setStatus("salary paid");
                    empliab.setChecked(true);
                    empliab.setBtnDisabled(true);
                    
                    
                }
                empliab.setSrNo(k);
                data.add(empliab);
                k++;
                                   
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }
     }
   
   
   public ArrayList<EmpSalaryLiability> loadDates(int year)  {
        try
        {
            
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select distinct date_format(esl_transaction_date,'%M-%y')"
                    + ",month(esl_transaction_date),year(esl_transaction_date)from employee_salary_liability");
            rs=ps.executeQuery();
            ArrayList<EmpSalaryLiability> data = new ArrayList<EmpSalaryLiability>();
            while(rs.next())
            {
                EmpSalaryLiability sc = new EmpSalaryLiability();
                sc.setDate(rs.getString(1));
                sc.setTransactionDate(rs.getInt(2)+"-"+rs.getInt(3));
                data.add(sc);
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
    public int getAllEmptotalliability(int month, int year){
     try{
            int total=0;
            ArrayList<Employee> data = new SalaryDataDB().loadAllEmpCode();
            for(Employee empval : data)
            {
                
                int emptotal=getEmpliability(month,year,empval.getCode());
                total+=emptotal;
            }   
            return total;    
     }
     catch(Exception e) {
            e.printStackTrace();
            return -1;
     }
 }
   
    public int getEmpliability(int month, int year,String empcode){
     try{
            int total=0;
            String type="credit";
            Connection c = new CommonDB().getConnection();
            String q ="select esl_totalsalary_amount, esl_type from employee_salary_liability"
                     +" where esl_month ='"+month+"' and esl_year ='"+year+"' and esl_emp_code='"+empcode+"' and esl_type='"+type+"' and esl_org_id='" +orgCode+ "'";
                               
            //System.out.println("QUARY : " + q);
            ps = c.prepareStatement(q);
            rs=ps.executeQuery();
            rs.next();
            if(rs.getRow()!=0){
                int estypeAmt=rs.getInt(1);
                total=estypeAmt;
            }   
            rs.close();
            ps.close();
            c.close();
            return total; 
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    

    public int getEmpTotalLiabilityType(String type){
        try{
            int total=0;
            Connection c = new CommonDB().getConnection();
            String q ="select esl_totalsalary_amount from employee_salary_liability"
                     +" where esl_type='"+type+"' and esl_org_id='" +orgCode+ "'";
             ps = c.prepareStatement(q);                  
            rs=ps.executeQuery();
            while(rs.next())
            {
                int estypeAmt=rs.getInt(1);
                total+=estypeAmt;
                    //System.out.println("total in EmpTotalLiabilityType====="+total);
               
            }
            rs.close();
            ps.close();
            c.close();
            return total; 
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }  
  
    public int getEmpTotalLiabilityType(String type,int month,int year){
        try{
            int total=0;
            Connection c = new CommonDB().getConnection();
            String q ="select esl_totalsalary_amount from employee_salary_liability"
                     +" where esl_type='"+type+"' and  esl_month ='"+month+"' and esl_year ='"+year+"' and esl_org_id='" +orgCode+ "'";
            ps = c.prepareStatement(q);                  
            rs=ps.executeQuery();
            while(rs.next())
            {
                int estypeAmt=rs.getInt(1);
                    total+=estypeAmt;
                    //System.out.println("total====="+total);
               
            }
            rs.close();
            ps.close();
            c.close();
            return total; 
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
   
    public int getEmpTIncome(Employee empval){
        try{
            int totalIncome=0;
            ArrayList<SalaryData> Data = new SalaryDataDB().loadCurrentSalaryData(empval);
            new FormulaProcessor().processFormula(Data);
            for (SalaryData sd : Data) {
                SalaryHead sh = new SalaryHead();
                sh.setDefaultValue(sd.getHeadValue());
                if (sd.isCatagory()) {
                        totalIncome+=sh.getDefaultValue();
                }    
            }
            return totalIncome;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int getEmpTDeduction(Employee empval){
        try{
            int totalDed=0;
            ArrayList<SalaryData> Data = new SalaryDataDB().loadCurrentSalaryData(empval);
            new FormulaProcessor().processFormula(Data);
            for (SalaryData sd : Data) {
                SalaryHead sh = new SalaryHead();
                sh.setDefaultValue(sd.getHeadValue());
                if (!sd.isCatagory()) {
                        totalDed+=sh.getDefaultValue();
                        //System.out.println("totaldeduction==="+totalDed+"empcode==="+empval.getCode());
                }    
            }
            return totalDed;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
  
    public int getEmpTotalExpenses(Employee empval){
        try{
                int totalexp=0;
                int ded=getEmpTDeduction(empval);
                int income=getEmpTIncome(empval);
                totalexp=income-ded;
                return totalexp;
        }
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int getALLEmpTotalExpenses(){
        try{
            int totalexp=0;
            ArrayList<Employee> data = new SalaryDataDB().loadAllEmpCode();
            //System.out.println("emptotal====="+data.size());
            for(Employee empval : data){
                
                int total=getEmpTotalExpenses(empval);
                totalexp+=total;
                     
            }
            return totalexp;
        }        
        catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public Exception EmployeeSalaryPaid(ArrayList<EmpSalaryLiability> empsl){
        try{
            
                      
            NewSalaryProcessing nsp=new NewSalaryProcessing();
                   
            //inset entry into BGAS=(entries)======== //
                
            int salaryPayableAmt=getTotalAmountSalaryPayable(empsl);
                
            int TotalAmoutofsalary=getTotalAmountSalary(empsl);
                
            nsp.insertinEntries(TotalAmoutofsalary, TotalAmoutofsalary);
            //System.out.println("total of sum==specialcheck="+salaryPayableAmt+"TotalAmoutofsalary==="+TotalAmoutofsalary);
                
            // calculating  head wise total amount 
            
            ArrayList<EmpSalaryLiability> empdata = new ArrayList<EmpSalaryLiability>(empsl);
            ArrayList<SalaryData> data=getTotalHeadValue(empsl);
            for(SalaryData sd : data){
                    
                //System.out.println(sd.getLedgerCode()+"headvaue=====:"+sd.getHeadValue()+"sdcheck===codehead===="+sd.getNumber()+"size===="+data.size());   
                if(sd.isUnder()){
                    
                    // insert data into BGAS entry_items======(emp incomes)==========// 
                    //ledgercode start with 1 (Debit)
                    if(sd.getLedgerCode().startsWith("1")){
                        nsp.PaymentEntry(sd.getLedgerCode(),sd.getHeadValue(),"D");
                    }
                    //ledgercode start with 2 (Credit)
                    else if(sd.getLedgerCode().startsWith("2"))
                    {   
                        nsp.PaymentEntry(sd.getLedgerCode(),sd.getHeadValue(),"C");
                    } 
                    else{
                    nsp.PaymentEntry(sd.getLedgerCode(),sd.getHeadValue(),"D");
                    }
                                                            
                }
                else{
                    // insert data into BGAS entry_items======(emp deduction)==========// 
                    //ledgercode start with 2 (Credit)
                    
                    if(sd.getLedgerCode().startsWith("1")){
                        nsp.PaymentEntry(sd.getLedgerCode(),sd.getHeadValue(),"C");
                    }
                    //ledgercode start with 2 (Debit)
                    else if(sd.getLedgerCode().startsWith("2"))
                    {   
                        nsp.PaymentEntry(sd.getLedgerCode(),sd.getHeadValue(),"D");
                    } 
                    else{
                        
                        nsp.PaymentEntry(sd.getLedgerCode(),sd.getHeadValue(),"C");
                    }
                }
                
            } 
            //income total
            int ledgercodeofexpence=nsp.getcashbudgetcode();
            //System.out.println("ledgercodeofexpence=== ="+ledgercodeofexpence);
            nsp.PaymentEntry(Integer.toString(ledgercodeofexpence),salaryPayableAmt,"C");
                
            //update budgets--------------------------
                    
            nsp.UpdateBudgets(TotalAmoutofsalary);
                
            for(EmpSalaryLiability empval : empdata){
                    
                //update liability----done-----------
                nsp.updateEmpLiability(empval.getCode());
                    
                //update employee salary summary for reports--------------
                                                          
                int income=getEmpTIncome(empval.getEmployee());
                                   
                int deduction=getEmpTDeduction(empval.getEmployee());
                //System.out.println("deduction of amount==esp==="+deduction);   
                int grossTotal=income-deduction;
                //System.out.println("gross=esp===="+grossTotal); 
                SalaryDataDB sdb = new SalaryDataDB();
                sdb.saveSummary(income, deduction, grossTotal,empval.getCode());  
            }//for empval
              
         
            return null;   
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return ex;
        } 
    }  
    
   public ArrayList<EmpSalaryLiability>SalaryPaidemployee() {
        try {
            
            Connection c = new CommonDB().getConnection();
            int month = uf.getCurrentMonth();
            int year = uf.getCurrentYear();
            
            ps=c.prepareStatement("select * from employee_salary_liability"
                     +" where esl_month=? and esl_year=? and esl_type=? and esl_org_id=?");
                               
            
            ps.setInt(1, month);
            ps.setInt(2, year);
            ps.setString(3, "debit");
            ps.setInt(4, orgCode);
           
            rs = ps.executeQuery();
            ArrayList<EmpSalaryLiability> data = new ArrayList<EmpSalaryLiability>();
            while (rs.next()) {
                EmpSalaryLiability empliab = new EmpSalaryLiability();
                empliab.setCode(rs.getString(1).trim());
                Employee employee = new EmployeeDB().loadProfile(empliab.getCode(),orgCode);
                empliab.setEmployee(employee);
                empliab.setMonth(rs.getInt(2));
                empliab.setYear(rs.getInt(3));
                empliab.setLiabiltyAmt(rs.getInt(4));
                empliab.setStatus(rs.getString(5));
                data.add(empliab);
                                       
            }
            
            rs.close();
            ps.close();
            c.close();
            return data;
        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }
      }
     
    public ResultSet getTotalExpensesHeadwise(String empcode){
        try{
           
            int totalexp=0;
            int month = uf.getCurrentMonth();
            int year = uf.getCurrentYear();
            Connection c = new CommonDB().getConnection();
           
            ps=c.prepareStatement("select sh_type, sh_ledger_code, sd_head_code, SUM(sd_amount) as total from salary_data"
                + " left join salary_head_master on sh_id = sd_head_code where month(sd_date)=? and year(sd_date)=? and  sd_emp_code=? and org_code=? GROUP BY sd_head_code");
            
            
            ps.setInt(1, month);
            ps.setInt(2, year);
            ps.setString(3,empcode);
            ps.setInt(4,orgCode);
            rs=ps.executeQuery();
            
            return rs;
        }        
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
      
    
    public ArrayList<SalaryData> getTotalHeadValue(ArrayList<EmpSalaryLiability> empsl){
        try{
            
            int finaltotal=0;
            ArrayList<EmpSalaryLiability> empdata = new ArrayList<EmpSalaryLiability>(empsl);
            //System.out.println("emptotal====="+empdata.size());
            ArrayList<SalaryData> data = new ArrayList<SalaryData>();
            int i=0;
            for(EmpSalaryLiability empval : empdata){
                //System.out.println("empval======"+empval.getCode());
                rs=getTotalExpensesHeadwise(empval.getCode());
                //System.out.println("final totalexp======"+rs);
                if(i==0){
                    while(rs.next())
                    {
                       
                        SalaryData sd = new SalaryData();
                        sd.setUnder(rs.getBoolean(1));
                        sd.setLedgerCode(rs.getString(2));
                        sd.setNumber(rs.getInt(3));
                        sd.setHeadValue(rs.getInt(4));
                        data.add(sd);
                        //System.out.println("=======:"+sd.getLedgerCode()+":"+sd.getHeadValue());
                    }
                    i++;
                }
                else{
                    ArrayList<SalaryData> clone = (ArrayList<SalaryData>) data.clone();
                    data = new ArrayList<SalaryData>();
                    while(rs.next()){
                        for(SalaryData firstsd : clone)
                        {
                            if(rs.getInt(3)== firstsd.getNumber()){
                            //data.get(0);
                                finaltotal= rs.getInt(4)+firstsd.getHeadValue();
                                  
                                SalaryData sd = new SalaryData();
                                sd.setUnder(rs.getBoolean(1));
                                sd.setLedgerCode(rs.getString(2));
                                sd.setNumber(rs.getInt(3));
                                sd.setHeadValue(finaltotal);
                                data.add(sd);
                                //System.out.println("elsepart==":"+sd.getLedgerCode()+":"+sd.getHeadValue());
                               
                            }
                            // clone.remove(firstsd);
                        } 
                       
                        
                           
                    }
                    i++;
                       
                } 
            }
            return data;
        
        }        
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    } 
    
    //head wise  distribution
    public ArrayList<SalaryData> loadRawData() {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("select DISTINCT sd_head_code from salary_data");
            rs=ps.executeQuery();
            ArrayList<SalaryData> data = new ArrayList<SalaryData>();
            while(rs.next())
            {
                SalaryData sd = new SalaryData();
                sd.setNumber(rs.getInt(1));
                data.add(sd);
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
    
    public ArrayList<EmpSalaryLiability> LoadSalaryPaidData(String current) {
        try {
            
            
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            int month = uf.getCurrentMonth();
            int year = uf.getCurrentYear();
           // System.out.println();
            String type="credit";
            Connection c = new CommonDB().getConnection();
            String q ="select * from employee_salary_liability "
                +"where esl_month ='"+month+"' and esl_year ='"+year+"'and esl_type='"+type+"' and esl_org_id='" +orgCode+ "'";
                               
            ps = c.prepareStatement(q.trim());
            rs = ps.executeQuery();
            ArrayList<EmpSalaryLiability> data = new ArrayList<EmpSalaryLiability>();
            
            int k = 1;
            while (rs.next()) {
                
                EmpSalaryLiability empliab = new EmpSalaryLiability();
                
                empliab.setCode(rs.getString(1).trim());
                Employee employee = new EmployeeDB().loadProfile(empliab.getCode(),orgCode);
                empliab.setEmployee(employee);
                empliab.setMonth(rs.getInt(2));
                empliab.setYear(rs.getInt(3));
                int emptotalded= getEmpTDeduction(employee);
                int netAmt=rs.getInt(4)-emptotalded;
                
                empliab.setLiabiltyAmt(netAmt);
                empliab.setType(rs.getString(5));
                if(rs.getString(5).equals("credit")){
                    empliab.setStatus("salary processed");
                    empliab.setBtnDisabled(false);
                }
                /*else{
                   
                    empliab.setStatus("salary paid");
                    empliab.setChecked(true);
                    empliab.setBtnDisabled(true);
                    
                    
                }*/
                
                empliab.setSrNo(k);
                data.add(empliab);
                k++;
                                   
            }
            rs.close();
            ps.close();
            c.close();
            return data;
        }catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
    public int getTotalAmountSalaryPayable(ArrayList<EmpSalaryLiability> empsl){
        try{
            ArrayList<EmpSalaryLiability> diff = new ArrayList<EmpSalaryLiability>(empsl);
            int sum=0;
            for(EmpSalaryLiability esl : diff)
            {
                //EmpSalaryLiability dataesl = new EmpSalaryLiability();
                //System.out.println("empcode--in liabilityDBClass    --"+esl.getEmployee().getCode()+"liabilityamt==="+esl.getLiabiltyAmt());
                sum=sum+esl.getLiabiltyAmt();
                                    
            }
            return sum;
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;

        }
    } 
    
    
     public int getTotalAmountSalary(ArrayList<EmpSalaryLiability> empsl){
        try{
            int month = uf.getCurrentMonth();
            int year = uf.getCurrentYear();
            ArrayList<EmpSalaryLiability> diff = new ArrayList<EmpSalaryLiability>(empsl);
            //System.out.println("diff======="+diff.size());
            int sum=0;
            for(EmpSalaryLiability esl : diff)
            {
                //EmpSalaryLiability dataesl = new EmpSalaryLiability();
                //System.out.println("empcode--in liabilityDBClass--"+esl.getEmployee().getCode()+"liabilityamt==="+esl.getLiabiltyAmt());
                sum=sum+getEmpliability(month,year,esl.getEmployee().getCode());
               
            }
            return sum;
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;

        }
    } 
    /* this method check the available budget amount for salary */ 
     
    public boolean checkBudget(ArrayList<EmpSalaryLiability> empsl){
        try{
         
            boolean booleanFlag= false; 
            int salaryPayableAmt=getTotalAmountSalaryPayable(empsl);
            NewSalaryProcessing nsp=new NewSalaryProcessing();
            int salcode = nsp.getsalarybudgetcode();
            int avalbud= nsp.getAvailableBudget(salcode);
            //System.out.println("available bug======"+avalbud);
            if(avalbud > salaryPayableAmt){
                booleanFlag=true;
            
            }
            
            return booleanFlag;
        
     }
     catch(Exception e) {
        e.printStackTrace();
        return false;
     }
    }//mothod close
    
    
    /**
     * This method check the total income and Total deduction of employee(total income should be positive)
     * @param empsl
     * @return boolean
     */ 
       
    public boolean checkIncomeAndDeduction(EmpSalaryLiability empsl){
        try{
            boolean flag=false;
            int Tincome=getEmpTIncome(empsl.getEmployee());
            int Tdeduction=getEmpTDeduction(empsl.getEmployee());
            if(Tincome > Tdeduction){
              //System.out.println("empcode===="+empsl.getEmployee().getCode());
                flag= true;
            }     
            return flag;
        }
        catch(Exception e) {
        e.printStackTrace();
        return false;
        }
       
    }//mothod close
       
}
