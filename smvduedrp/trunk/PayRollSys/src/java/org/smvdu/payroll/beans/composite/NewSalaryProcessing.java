/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.composite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.smvdu.payroll.beans.setup.SalaryHead;
import org.smvdu.payroll.beans.*;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.api.FormattedDate;
import org.smvdu.payroll.api.SalaryDataHelper;
import org.smvdu.payroll.api.security.SalaryLockDB;
import org.smvdu.payroll.api.tools.FormulaProcessor;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.EmployeeDB;
import org.smvdu.payroll.beans.db.SalaryDataDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;
import org.smvdu.payroll.user.ActiveProfile;
import javax.faces.model.SelectItem;
import java.util.Date;



/**
*
*  Copyright (c) 2010 - 2011 SMVDU, Katra, IITK.
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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IIT Kanpur.
* 
*  Modified Date: 26 Dec 2013, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com).
*/

public class NewSalaryProcessing {


    private int orgCode;
    
    public NewSalaryProcessing()  {
        pendingList = new EmployeeDB().loadPendingEmployee();
        ActiveProfile le = (ActiveProfile)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");
        orgCode = le.getOrgId();
    }


    private int numberOfdays;
  //  private int numberOfdays1=20;

    private int typeCode = -1;
    private String empCode;
    private UIData incomeGrid;
    private UIData deductGrid;
    private Employee employee;
    private boolean currentData;
    private String errorClass = new String();
    private String me = new String();
    private UIData dataGridValue;
    //01147772100
    // 9873124287
    public UIData getDataGridValue() {
        return dataGridValue;
    }

    public void setDataGridValue(UIData dataGridValue) {
        this.dataGridValue = dataGridValue;
    }
    public String getMe() {
        System.out.println("DAta Should Be Write Here : "+me);
        return me;
    }

    public void setMe(String me) {
        System.out.println("DAta Should Be Write Here set : "+me);
        this.me = me;
    }
    public String getErrorClass() {
        return errorClass;
    }

    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }
    private String copyDate;

    private boolean copyAllowed;

    public boolean isCurrentData() {
        return currentData;
    }

    public void setCurrentData(boolean currentData) {
        this.currentData = currentData;
    }



    private ArrayList<SimpleEmployee> pendingList;

    public ArrayList<SimpleEmployee> getPendingList() {
        return pendingList;
    }

    public void setPendingList(ArrayList<SimpleEmployee> pendingList) {
        this.pendingList = pendingList;
    }
    public void doUnLock()
    {
        new SalaryLockDB().doUnLock();
    }



    public void doLock()
    {
        new SalaryLockDB().doLock();
    }
    


    public void autoLoad()
    {
        new SalaryDataDB().autoLoad();
    }

    public boolean isCopyAllowed() {
        if(empCode==null)
        {
            copyAllowed=false;
        }
        if(locked)
        {
            copyAllowed = false;
        }
        
        return copyAllowed;
    }

    public void setCopyAllowed(boolean copyAllowed) {
        this.copyAllowed = copyAllowed;
    }



    public String getCopyDate() {
        return copyDate;
    }

    public void setCopyDate(String copyDate) {
        this.copyDate = copyDate;
    }

    public void copyData()
    {
        //System.err.println("Copying Salary Data ..."+empCode+","+copyDate);
        new SalaryDataDB().copyLastMonthData(empCode,copyDate);
        loadProfile();
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary Data Copied.", ""));
    }

    private SelectItem[] copyDates;

    private void loadCopyDates()
    {
        ArrayList<FormattedDate> fds = new SalaryDataHelper().getCopyDate(empCode);
        copyDates = new SelectItem[fds.size()];
        for(int i=0;i<fds.size();i++)
        {
            SelectItem si = new SelectItem(fds.get(i).getValueString(), fds.get(i).getDisplayString());
            copyDates[i] = si;
        }
    }
    public SelectItem[] getCopyDates() {
        loadCopyDates();
        return copyDates;
    }

    public void setCopyDates(SelectItem[] copyDates) {
        this.copyDates = copyDates;
    }
    
    private ArrayList<SalaryHead> incomeHeads;
    private ArrayList<SalaryHead> deductHeads;
    private boolean locked;

    public boolean isLocked() {
        return new SalaryLockDB().isLocked();
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    


    private ArrayList<SalaryData> combinedData ;
    private int totalIncome=0;
    private int totalDeduct=0;
    private int gorssTotal;

    public int getGorssTotal() {
        return gorssTotal;
    }

    public void setGorssTotal(int gorssTotal) {
        this.gorssTotal = gorssTotal;
    }
    

    public int getTotalDeduct() {
        return totalDeduct;
    }

    public void setTotalDeduct(int totalDeduct) {
        this.totalDeduct = totalDeduct;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    
    public int getNumberOfdays() {
        System.out.println("Number Of Days..."+numberOfdays);
        return numberOfdays;
    }

    public void setNumberOfdays(int numberOfdays) {
        System.out.println("Number Of Days...set ..."+numberOfdays);
        this.numberOfdays = numberOfdays;
    }
    public ArrayList<SalaryHead> getDeductHeads() {
        return deductHeads;
    }
    public void setDeductHeads(ArrayList<SalaryHead> deductHeads) {
        this.deductHeads = deductHeads;
    }
    public ArrayList<SalaryHead> getIncomeHeads() {        
        return incomeHeads;
    }
    public void setIncomeHeads(ArrayList<SalaryHead> incomeHeads) {

        this.incomeHeads = incomeHeads;
    }
    public String getEmpCode() {
        
        return empCode;
    }
   
    public void setEmpCode(String empCode) {
        this.empCode = empCode.toUpperCase();
    }
    
        public void scale()   {
        System.err.println("Scaling by "+numberOfdays);
        if(combinedData!=null)
        {
            combinedData.clear();
        }
        combinedData = new ArrayList<SalaryData>();
        ArrayList<SalaryHead> incomes = (ArrayList<SalaryHead>) incomeGrid.getValue();
        ArrayList<SalaryHead> deds = (ArrayList<SalaryHead>) deductGrid.getValue();
        for(SalaryHead sh : incomes)
        {
            SalaryData sd = new SalaryData();
            sd.setHeadCode(sh.getNumber());
            sd.setHeadName(sh.getName());
            sd.setFormula(sh.getFormula());
            sd.setHeadValue(sh.getDefaultValue());
            sd.setAlias(sh.getAlias());
            sd.setScalable(sh.isScalable());
            sd.setCatagory(sh.isUnder());
            sd.setUnder(sh.isUnder());
            sd.setDisplay(sh.isDisplay());
            combinedData.add(sd);
        }
        for(SalaryHead sh : deds)
        {
            SalaryData sd = new SalaryData();
            sd.setHeadCode(sh.getNumber());
            sd.setHeadValue(sh.getDefaultValue());
            sd.setAlias(sh.getAlias());
            sd.setFormula(sh.getFormula());
            sd.setScalable(sh.isScalable());
            sd.setCatagory(sh.isUnder());
            sd.setHeadName(sh.getName());
            sd.setUnder(sh.isUnder());
            sd.setDisplay(sh.isDisplay());
            combinedData.add(sd);
        }
        for(SalaryData sd : combinedData)
        {
            if(sd.isScalable())
            {
                System.err.println("Scalable field name : "+sd.getHeadName());
                System.out.println("NUmber Of Days : "+this.getNumberOfdays());
                sd.setHeadValue((sd.getHeadValue()*this.getNumberOfdays())/30);
                System.err.println("Scalable field Value : "+sd.getHeadValue());
            }
        }
        new FormulaProcessor().processFormula(combinedData);


//        incomeHeads.clear();
//        deductHeads.clear();
        incomeHeads = new ArrayList<SalaryHead>();
        deductHeads = new ArrayList<SalaryHead>();
       for (SalaryData sd : combinedData) {
                SalaryHead sh = new SalaryHead();
                sh.setName(sd.getHeadName());
                sh.setUnder(sd.isCatagory());
                sh.setNumber(sd.getHeadCode());
                sh.setDefaultValue(sd.getHeadValue());
                sh.setFormula(sd.getFormula());
                sh.setAlias(sd.getAlias());
                sh.setScalable(sd.isScalable());
                if(!sh.isDisplay())
                {
                    continue;
                }
                if (sd.isCatagory()) {
                    incomeHeads.add(sh);
                    totalIncome+=sh.getDefaultValue();
                } else {
                    deductHeads.add(sh);
                    totalDeduct+=sh.getDefaultValue();
                }

                gorssTotal = totalIncome-totalDeduct;
            }
                
                deductGrid.setValue(deductHeads);
                incomeGrid.setValue(incomeHeads);
    }
    
    private void refreshModel()   {
            totalDeduct=0;
            totalIncome=0;
            incomeHeads = new ArrayList<SalaryHead>();
            deductHeads=new ArrayList<SalaryHead>();
            new FormulaProcessor().processFormula(combinedData);
            
            for (SalaryData sd : combinedData) {
                SalaryHead sh = new SalaryHead();
                sh.setName(sd.getHeadName());
                sh.setUnder(sd.isCatagory());
                sh.setNumber(sd.getHeadCode());
                sh.setDefaultValue(sd.getHeadValue());
                sh.setFormula(sd.getFormula());
                sh.setAlias(sd.getAlias());
                sh.setScalable(sd.isScalable());
                sh.setDisplay(sd.isDisplay());
                if (sd.isCatagory()) {
                    incomeHeads.add(sh);
                    totalIncome+=sh.getDefaultValue();
                } else {
                    deductHeads.add(sh);
                    
                    totalDeduct+=sh.getDefaultValue();
                    System.err.println("Deduct >> "+sh.getName());
                }

                gorssTotal = totalIncome-totalDeduct;
            }
    }
    public void loadProfile() {
        if(deductHeads!=null)
        deductHeads.clear();
        if(incomeHeads!=null)
        incomeHeads.clear();
        if(combinedData!=null)
        combinedData.clear();
        employee = new EmployeeDB().loadProfile(empCode,orgCode);
        if (employee != null) {
         //   loadCopyDates();
            //currentBasic = employee.getCurrentBasic();
            combinedData = new SalaryDataDB().loadCurrentSalaryData(employee);
            if (combinedData != null&&combinedData.size()>0) {
                System.out.println("Data exist. Loading ...");
                refreshModel();
                
            }
                deductGrid.setValue(deductHeads);
                incomeGrid.setValue(incomeHeads);

        }
    }
    public Employee getEmployee() {

        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public int getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
    public void setDeductGrid(UIData deductGrid) {

        this.deductGrid = deductGrid;
    }
    public void setIncomeGrid(UIData incomeGrid) {
        this.incomeGrid = incomeGrid;
    }
    public UIData getDeductGrid() {        
        return deductGrid;
    }
    public UIData getIncomeGrid() {
        return incomeGrid;
    }
    public void save(ArrayList<SalaryHead> heads) {
        new SalaryHeadDB().updateDefault(heads, typeCode);
    }


    // New Method For Updating Employee Salary

    
    public void updatingEmployeeSalary()
    {
        try
        {
            ArrayList<SimpleEmployee> simpleEmployee = (ArrayList<SimpleEmployee>) dataGridValue.getValue();
            boolean b = new EmployeeDB().updateEmployeeSalaryStatus(simpleEmployee);
            if(b == true)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Activated",""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void updateData() throws SQLException {

        
        //FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary data Updated", ""));        
        PreparedStatement ps;
        ResultSet rs;

        ArrayList<SalaryHead> incomes = (ArrayList<SalaryHead>) incomeGrid.getValue();
        
        //processFormula(combinedData);
        ArrayList<SalaryData> datas = new ArrayList<SalaryData>();
        Employee emp = new Employee();
        emp.setCode(empCode.toUpperCase());
        totalDeduct=0;
        totalIncome=0;
        for (SalaryHead sh : incomes) {
            SalaryData sd = new SalaryData();
            System.err.println("Code : "+sh.getNumber()+", "+sh.getDefaultValue());
            sd.setEmployee(emp);
            sd.setHeadCode(sh.getNumber());
            sd.setHeadValue(sh.getDefaultValue());
            datas.add(sd);
            totalIncome+=sh.getDefaultValue();
        }
        //new SalaryDataDB().save(datas);
        incomes = (ArrayList<SalaryHead>) deductGrid.getValue();
        System.out.println("Deduct Size : " + incomes.size());
        for (SalaryHead sh : incomes) {
            SalaryData sd = new SalaryData();
            System.err.println("Code : "+sh.getNumber()+", "+sh.getDefaultValue());
            sd.setEmployee(emp);
            sd.setHeadCode(sh.getNumber());
            sd.setHeadValue(sh.getDefaultValue());
            datas.add(sd);
            totalDeduct+=sh.getDefaultValue();
        }
         ps = new CommonDB().getConnection().prepareStatement("select emp_active from employee_master where emp_code = '"+empCode+"'");
         rs = ps.executeQuery();
         if(rs.next())
         {
            if(rs.getInt(1) == 0)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Is Not Active", "errorMarker"));
                return;
            }
         }
        gorssTotal =totalIncome-totalDeduct;
        SalaryDataDB sdb = new SalaryDataDB();
        sdb.save(datas,empCode);
	sdb.saveSummary(totalIncome, totalDeduct, gorssTotal, empCode);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary data Updated", ""));
        loadProfile();
        
    }
   //---------------------------------------Salary with budgets==============================================

	public void updateDatawithbudgets() throws SQLException {

        PreparedStatement ps;
        ResultSet rs;

        ArrayList<SalaryHead> incomes = (ArrayList<SalaryHead>) incomeGrid.getValue();

        //processFormula(combinedData);
        ArrayList<SalaryData> datas = new ArrayList<SalaryData>();
        Employee emp = new Employee();
        emp.setCode(empCode.toUpperCase());
        totalDeduct=0;
        totalIncome=0;
        for (SalaryHead sh : incomes) {
            SalaryData sd = new SalaryData();
            System.err.println("Code : "+sh.getNumber()+", "+sh.getDefaultValue());
            sd.setEmployee(emp);
            sd.setHeadCode(sh.getNumber());
            sd.setHeadValue(sh.getDefaultValue());
            datas.add(sd);
            totalIncome+=sh.getDefaultValue();
        }
        //new SalaryDataDB().save(datas);
        incomes = (ArrayList<SalaryHead>) deductGrid.getValue();
        System.out.println("Deduct Size : " + incomes.size());
        for (SalaryHead sh : incomes) {
            SalaryData sd = new SalaryData();
            System.err.println("Code : "+sh.getNumber()+", "+sh.getDefaultValue());
            sd.setEmployee(emp);
            sd.setHeadCode(sh.getNumber());
            sd.setHeadValue(sh.getDefaultValue());
            datas.add(sd);
            totalDeduct+=sh.getDefaultValue();
        }
         ps = new CommonDB().getConnection().prepareStatement("select emp_active from employee_master where emp_code = '"+empCode+"'");
         rs = ps.executeQuery();
         if(rs.next())
         {
            if(rs.getInt(1) == 0)
            {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Is Not Active", "errorMarker"));
                return;
            }
         }
        gorssTotal =totalIncome-totalDeduct;
        SalaryDataDB sdb = new SalaryDataDB();
        //sdb.save(datas,empCode);
        //---------start----check available budget for process  Employee Salary------------
        boolean c =checkAlreadyExsistEmpSalary(empCode);
        if(c==true)
        {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Salary already processed for this month", ""));
            return;
        }
        else{
                
                boolean b= checkbudgetwithTotalSalary();
                if(b==true){
                    sdb.save(datas,empCode);
                    System.out.println("  check=== budget +========= budget : "+b);
                    saveEmpLiability();
                    PaymentEntry();
                    updateEmpLiability(empCode);
                    sdb.saveSummary(totalIncome, totalDeduct, gorssTotal, empCode);
                    UpdateBudgets();
                }
                else
                {
                    System.out.println(" check====in else========== budget : "+b);
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Allocated Budget is not sufficient to process salary", ""));
                    return;
                }
        }       
        //---------------------------------------- end check budget part------------------------------------------ 
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary data Updated", ""));
        loadProfile();

    }
 
    public int getAvailableBudget(int code){
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            Connection c = new CommonDB().getwebzashConnection();
            int salcode = getsalarybudgetcode();
            ps = c.prepareStatement("select bd_balance,consume_amount from budgets "
                + "where code='"+salcode+"'");
           
            rs=ps.executeQuery();
            rs.next();
            int allocationamt = rs.getInt(1);
            int consumedamt=rs.getInt(2);
            int availableamt=allocationamt-consumedamt;
            //System.out.println(" getAvailableBudget=== + : "+allocationamt);
            //System.out.println(" getAvailableBudget==== + : "+consumedamt);
            //System.out.println(" getAvailableBudget===== + : "+availableamt);
            rs.close();
            ps.close();
            c.close();
            return availableamt;
        }
       catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    } 
    public int getEmptotalsalary(){
     try{
            PreparedStatement ps;
            ResultSet rs;
            int total=0;
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            
            Connection c = new CommonDB().getConnection();
            int Pmonth = user.getCurrentMonth()-1;
            int year = user.getCurrentYear();
            if(Pmonth==0)
            {
                Pmonth=12;
                year=year-1;
                //System.out.println("Emptotalsalary======"+Pmonth+"\nyear====="+year);
                
            } 
            
            ps=c.prepareStatement("select es_total_income from employee_salary_summery "
            + "left join employee_master on emp_code = es_code "
            +"where es_org_id ='"+orgCode+"' and es_month= '"+Pmonth+"' and es_year='"+year+"' ");
            
            rs=ps.executeQuery();   
            while(rs.next())
            {
                //System.out.println("Emptotalsalary======"+rs.getInt(1));
                total=total+rs.getInt(1);
            }
            //System.out.println("Emptotalsalarytotal======"+total);
            return total;    
     }
     catch(Exception e) {
            e.printStackTrace();
            return -1;
     }
 }
    
    public boolean checkbudgetwithTotalSalary(){
     try{
         
         int emptotalsalary=getEmptotalsalary();
         //System.out.println("Emptotalsalarytotal======"+emptotalsalary);
         int salcode = getsalarybudgetcode();
         //System.out.println("Emptotalsalarytotal======"+salcode);
         int avalbud= getAvailableBudget(salcode);
         //System.out.println("available bug======"+avalbud);
         if(avalbud > emptotalsalary){
             //System.out.println("Emptotalsalarytotal======"+emptotalsalary);
             //System.out.println("avalbudget======"+avalbud);
             
             return true;
                         
         }
         else{
             //System.out.println("Emptotalsalarytotal======"+emptotalsalary);
             //System.out.println("availablebuget======"+avalbud);
             return false;
         }
        
     }
     catch(Exception e) {
        e.printStackTrace();
        return false;
     }
    }//mothod cloase
    
    
    public int getParentcode(){ 
          
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            Connection c = new CommonDB().getwebzashConnection();
            int salcode = getsalarybudgetcode();
            //System.out.println("getParentcode()======"+salcode);
            ps = c.prepareStatement("select groups.code,groups.id,groups.parent_id from groups "
            + "left join ledgers on  ledgers.group_id = groups.id"
	    +" where ledgers.code='"+salcode+"' ");
            
            rs=ps.executeQuery();
            rs.next();
            int Pcode = rs.getInt(1);
            //int id = rs.getInt(2);
	    //int Pid = rs.getInt(3);
	    //System.out.println(" getParentlist+ : =Pcode==rs "+Pcode);
            //System.out.println("getParentlist== + : ==id=rs "+id);
            //System.out.println("getParentlist + : parentid===rs "+Pid);
            return Pcode;
           
        }
        catch(Exception e) {
        e.printStackTrace();
        return -1;
     }
    }
    public void PaymentEntry(){ 
        PreparedStatement ps;
              
        try{
                                
                UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                Connection c = new CommonDB().getwebzashConnection();
                Date date = new Date();
                java.sql.Date newdate = new java.sql.Date(date.getTime());
                int totalincome=getTotalIncome();
                //get ledgers id==============
                int ledgerid=getledgers_salId();
                //Insert enrty in BGAS entries table
                insertinEntries();
                // Insert entry in BGAS entry_items table and update the left over entry in entries
                ps = c.prepareStatement("insert into entry_items values(?,?,?,?,?,?,?)");
                ArrayList<SalaryData> data= EntryIDforPayment();
                ArrayList<SalaryData> ldata= getAllledgersId();
                for(SalaryData Ed: data){
                    for(SalaryData ld : ldata){
                        //if(ld.getledgersId()== 773){
                        if(ld.getledgersId()== ledgerid){
                            //System.out.println("line723=="+Ed.getEntId()+"\n secomd========="+Ed.getEntryId());
                            ps.setInt(1,Ed.getEntId());
                            ps.setInt(2,Ed.getEntryId());
                            ps.setInt(3,ld.getledgersId() );
                            ps.setInt(4, totalincome);
                            ps.setString(5, "D");
                            ps.setDate(6, null );
                            ps.setDate(7, newdate);
                        }      
                        else{
                            int edid=Ed.getEntId()+1;
                            ps.setInt(1, edid);
                            ps.setInt(2, Ed.getEntryId());
                            ps.setInt(3, ld.getledgersId());
                            ps.setInt(4, totalincome);
                            ps.setString(5, "C");  
                            ps.setDate(6, null );
                            ps.setDate(7, newdate);
                        }   
                        ps.executeUpdate(); 
                        ps.clearParameters();
                        //ps.close();                      
                       
                    } 
                    ps = c.prepareStatement("update entries set dr_total=?, cr_total=? "
                    +" where entries.number='"+Ed.getEntryId()+"' ");
                    ps.setInt(1, totalincome);
                    ps.setInt(2, totalincome);
                    ps.executeUpdate(); 
                    ps.clearParameters();
                    //ps.close();
                }
                ps.close();
                ps=c.prepareStatement("commit");
                ps.executeUpdate();
                ps.close();
                c.close();
           
        }
        catch(Exception e) {
        e.printStackTrace();
        
        }
    }
      
    public ArrayList<SalaryData> getAllledgersId(){
        //int code;
        PreparedStatement ps;
        ResultSet rs;
        try{
                Connection c = new CommonDB().getwebzashConnection();
                //System.out.println(" getAllledgersId====+ :connection====="+c);
                int salcode = getsalarybudgetcode();
                //System.out.println(" getAllledgersId====+ :connection====="+salcode);
                int cashbudgetcode = getcashbudgetcode();
                //System.out.println(" getAllledgersId====+ :connection====="+cashbudgetcode);
                ps = c.prepareStatement("select ledgers.id from ledgers "
                + "where code IN ('"+salcode+"','"+cashbudgetcode+"' )");
                
                rs=ps.executeQuery();
                ArrayList<SalaryData> data = new ArrayList<SalaryData>();
                while(rs.next())
                {
                                      
                    //System.out.println("getAllledgersId====from ledgers====="+rs.getInt(1));
                    
                    SalaryData sd = new SalaryData();
                    //EntryData Ed = new EntryData();
                    sd.setledgersId(rs.getInt(1));
                    data.add(sd);
                    
                }
                rs.close();
                ps.close();
                c.close();
                return data;
          
        }
        catch(Exception e) {
        e.printStackTrace();
        return null;
        
        }
    }
   
    public ArrayList<SalaryData> EntryIDforPayment(){ 
        PreparedStatement ps;
        ResultSet rs;
        try{
                Connection c = new CommonDB().getwebzashConnection();
                //System.out.println("EntryIDforPayment===== + :connection====="+c);
                ps = c.prepareStatement("select Max(id) , Max(entry_id) from entry_items");
                rs=ps.executeQuery();
                ArrayList<SalaryData> data = new ArrayList<SalaryData>();
                while(rs.next())
                {
                    SalaryData sd = new SalaryData();
                    if(rs.getInt(1)==0 && rs.getInt(2)==0){
                        sd.setEntId(1);
                        sd.setEntryId(1);
                        data.add(sd);
                    }
                    else{
                        sd.setEntId(rs.getInt(1)+1);
                        sd.setEntryId(rs.getInt(2)+1);
                        data.add(sd);
                    }
                    //System.out.println("entry id from entry_items====="+data);
                    
                }
                //System.out.println("entry id from  outer part entry_items====="+data);
                rs.close();
                ps.close();
                c.close();
                return data;  
                
        }
        catch(Exception e) {
        e.printStackTrace();
        return null;
        
        }
    }
    // save employee salary liability data into the database 
    public void saveEmpLiability() throws SQLException  {
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            int totalincome=getTotalIncome();
            //System.out.println("totlaincome in save emp liability ====="+totalincome);
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            //System.out.println("totlaincome in save emp liability ====="+empCode);
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("insert into employee_salary_liability (esl_emp_code,"
                    + "esl_month,esl_year,esl_totalsalary_amount,esl_debit,esl_credit,esl_org_id) values(?,?,?,?,?,?,?)");
            
            ps.setString(1,empCode);
            //System.out.println("liability====line890==="+user.getCurrentMonth());
            //System.out.println("liability==line891======="+user.getCurrentYear());
            ps.setInt(2,user.getCurrentMonth() );
            ps.setInt(3, user.getCurrentYear());
            ps.setInt(4, totalincome);
            ps.setInt(5, 0);
            ps.setInt(6, totalincome);
            ps.setInt(7, user.getUserOrgCode());
            ps.executeUpdate();
            ps.close();
            ps=c.prepareStatement("commit");
            ps.executeUpdate();
            ps.close();
            c.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
    }
    
    public void updateEmpLiability(String empcode) throws SQLException  {
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            int totalincome=getTotalIncome();
            //System.out.println("totlaincome in update emp liability ====="+totalincome);
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            //System.out.println("totlaincome in save emp liability ====="+empCode);
            Connection c = new CommonDB().getConnection();
            ps=c.prepareStatement("update employee_salary_liability set esl_debit=?"
                + " where esl_emp_code=? and esl_org_id=? ");
            //System.out.println("totlaincome in update emp liability ====="+ps);
            //System.out.println("totlaincome in update emp liability ====="+empcode);
            ps.setInt(1, totalincome);
            ps.setString(2,empcode);
            ps.setInt(3, user.getUserOrgCode());
            ps.executeUpdate();
            ps.close();
            c.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
    }
    
    
     public void UpdateBudgets(){ 
        
        PreparedStatement ps;
        ResultSet rs;
        try{
                UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                Connection c = new CommonDB().getwebzashConnection();
                //System.out.println("updatebudgets connection==="+c);
                int salcode = getsalarybudgetcode();
                //System.out.println(" getAllledgersId====+ :connection====="+salcode);
                int parentbudgetcode = getParentcode();
                //System.out.println(" getAllledgersId====+ :connection====="+parentbudgetcode);
                int mainbudgetcode = getmainbudgetcode();
                //System.out.println(" getAllledgersId====+ :connection====="+mainbudgetcode);
                int consumedamt=getconsumedBudget(salcode);
                //System.out.println("updatebudgets======="+consumedamt);
                int empsalary=getTotalIncome();
                //System.out.println("updatebudgets=====totalincome====="+empsalary);
                int finalconsumedamt=consumedamt+empsalary;
                //System.out.println("updatebudgets====finalconsumedamt====="+finalconsumedamt);
                ps=c.prepareStatement(" update budgets set consume_amount=? "
                    + " where code IN ('"+salcode+"', '"+parentbudgetcode+"', '"+mainbudgetcode+"' ) ");
                ps.setInt(1,finalconsumedamt);
                ps.executeUpdate();
                ps.clearParameters();
                ps.close();
                ps=c.prepareStatement("commit");
                ps.executeUpdate();
                ps.close();
                c.close();
               
        }
        catch(Exception e) {
        e.printStackTrace();
        
        }
    }
     public int getconsumedBudget(int code){
        
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            Connection c = new CommonDB().getwebzashConnection();
            //System.out.println("condumed amount + :connection====="+c);
            ps = c.prepareStatement("select consume_amount from budgets "
            + "where code='"+code+"'");
            //System.out.println("consumed + :ps "+ps);
            rs=ps.executeQuery();
            //System.out.println("consumed + :rs "+rs);
            rs.next();
            int consumedamt=rs.getInt(1);
            //System.out.println("consumed + :======line====934=== "+consumedamt);
            rs.close();
            ps.close();
            c.close();
           return consumedamt;
        }
       catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    } 
     
     public void insertinEntries() throws SQLException  {
        PreparedStatement ps;
        try
        {
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            Connection c = new CommonDB().getwebzashConnection();
            int totalincome=getTotalIncome();
            Date date = new Date();
            //System.out.println("entries + :connection=== date=="+date);
            java.sql.Date newdate = new java.sql.Date(date.getTime());
            //System.out.println("entries + :connection===new newdate=="+newdate);
            ArrayList<SalaryData> entries=NumberInEntyriesTable();
            ps = c.prepareStatement("insert into entries values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            for(SalaryData entnum : entries){
                //System.out.println("entries :connection====="+entnum.getEntId());
                //System.out.println("entries :connection====="+entnum.getEntryId());
                ps.setInt(1,entnum.getEntId());
                ps.setString(2, null);
                ps.setInt(3, 2);
                ps.setInt(4, entnum.getEntryId());
                ps.setDate(5, newdate );
                ps.setInt(6, 0);
                ps.setInt(7, 0);
                ps.setString(8, "salary entry from payroll");
                ps.setDate(9, newdate);
                ps.setString(10, user.getUserName());
                ps.setString(11, "");
                ps.setInt(12, 0);
                ps.setString(13, null);
                ps.executeUpdate();
                ps.clearParameters();
           
            }
            ps.close();
            ps=c.prepareStatement("commit");
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
    }
     
     public ArrayList<SalaryData> NumberInEntyriesTable(){ 
        PreparedStatement ps;
        ResultSet rs;
        try{
                Connection c = new CommonDB().getwebzashConnection();
                //System.out.println(" + :connection====="+c);
                ps = c.prepareStatement("select Max(id), Max(number) from entries");
                rs=ps.executeQuery();
                ArrayList<SalaryData> data = new ArrayList<SalaryData>();
                while(rs.next())
                {
                    SalaryData sd = new SalaryData();
                    if(rs.getInt(1)==0 && rs.getInt(2)==0){
                        sd.setEntId(1);
                        sd.setEntryId(1);
                        data.add(sd);
                    }
                    else{
                        sd.setEntId(rs.getInt(1)+1);
                        sd.setEntryId(rs.getInt(2)+1);
                        data.add(sd);
                    }
                    //System.out.println("number====="+data);
                    
                }
                rs.close();
                ps.close();
                c.close();
                return data;  
                
        }
        catch(Exception e) {
        e.printStackTrace();
        return null;
        
        }
    }
     
     public ArrayList<SalaryData> getpayrollsetupDetail(){ 
        PreparedStatement ps;
        ResultSet rs;
        try{
                Connection c = new CommonDB().getwebzashConnection();
                //System.out.println(" + :connection====="+c);
                ps = c.prepareStatement(" select * from payrollsetup");
                rs=ps.executeQuery();
                ArrayList<SalaryData> data = new ArrayList<SalaryData>();
                while(rs.next())
                {
                    SalaryData sd = new SalaryData();
                    sd.setbudgetcode(rs.getString(2));
                    sd.setbudgetname(rs.getString(3));
                    sd.setbudgettype(rs.getInt(4));
                    data.add(sd);
                    /*System.out.println("=getpayrollsetupDetailcode===="+rs.getString(2));
                    System.out.println("==getpayrollsetupDetailname==="+rs.getString(3));
                    System.out.println("number==getpayrollsetupdetialtype==="+rs.getInt(4));
                    System.out.println("number==getpayrollsetupDetail======data=="+data);*/
                    
                }
                rs.close();
                ps.close();
                c.close();
                return data;  
                
        }
        catch(Exception e) {
        e.printStackTrace();
        return null;
        
        }
    }
     public int getsalarybudgetcode(){
        
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            Connection c = new CommonDB().getwebzashConnection();
            //System.out.println("condumed amount + :connection====="+c);
            ps = c.prepareStatement("select payrollsetup.code from payrollsetup "
            + "where type='"+2+"'");
            //System.out.println("consumed + :ps "+ps);
            rs=ps.executeQuery();
            //System.out.println("consumed + :rs "+rs);
            rs.next();
            int salarycode=rs.getInt(1);
            //System.out.println("consumed + :======line====934=== "+salarycode);
            rs.close();
            ps.close();
            c.close();
           return salarycode;
        }
       catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    } 
    public int getcashbudgetcode(){
        
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            Connection c = new CommonDB().getwebzashConnection();
            //System.out.println("condumed amount + :connection====="+c);
            ps = c.prepareStatement("select payrollsetup.code from payrollsetup "
            + "where type='"+1+"'");
            //System.out.println("consumed + :ps "+ps);
            rs=ps.executeQuery();
            //System.out.println("consumed + :rs "+rs);
            rs.next();
            int cashbudgetcode=rs.getInt(1);
            //System.out.println("consumed + :======line====934=== "+cashbudgetcode);
            rs.close();
            ps.close();
            c.close();
           return cashbudgetcode;
       }
       catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }  
    
    public int getmainbudgetcode(){
        
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            
            Connection c = new CommonDB().getwebzashConnection();
            //System.out.println("condumed amount + :connection====="+c);
            ps = c.prepareStatement("select payrollsetup.code from payrollsetup "
            + "where type= '"+0+"' ");
            //System.out.println("consumed + :ps "+ps);
            rs=ps.executeQuery();
            //System.out.println("consumed + :rs "+rs);
            rs.next();
            int mainbudgetcode=rs.getInt(1);
            //System.out.println("consumed + :======line====934=== "+mainbudgetcode);
            rs.close();
            ps.close();
            c.close();
           return mainbudgetcode;
       }
       catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }  
    
    public int getledgers_salId(){
        
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            Connection c = new CommonDB().getwebzashConnection();
            //System.out.println("condumed amount + :connection====="+c);
            int salcode = getsalarybudgetcode();
            ps = c.prepareStatement("select ledgers.id from ledgers "
            + "where code='"+salcode+"'");
            //System.out.println("consumed + :ps "+ps);
            rs=ps.executeQuery();
            //System.out.println("consumed + :rs "+rs);
            rs.next();
            int ledgerid=rs.getInt(1);
            //System.out.println("consumed + :======line====934=== "+ledgerid);
            rs.close();
            ps.close();
            c.close();
           return ledgerid;
       }
       catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
     public boolean checkAlreadyExsistEmpSalary(String empcode){
        try{
                PreparedStatement ps;
                ResultSet rs;
                UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
                Connection c = new CommonDB().getConnection();
                int month = user.getCurrentMonth();
                int year = user.getCurrentYear();
                ps=c.prepareStatement("select * from employee_salary_summery"
                       +" where es_code=? and es_month=? and es_year=? and es_org_id=? ");
                ps.setString(1, empcode);
                ps.setInt(2, month);
                ps.setInt(3, year);
                ps.setInt(4, orgCode);
                rs=ps.executeQuery();
                rs.next();
                String escode=rs.getString(1);
                //System.out.println("existsempsalary====line====1252=== "+escode);
                rs.close();
                ps.close();
                c.close();
                return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }//mothod cloase
     
}


