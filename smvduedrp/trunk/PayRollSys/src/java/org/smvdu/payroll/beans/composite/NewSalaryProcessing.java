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
import javax.faces.model.SelectItem;
import org.smvdu.payroll.api.FormattedDate;
import org.smvdu.payroll.api.SalaryDataHelper;
import org.smvdu.payroll.api.security.SalaryLockDB;
import org.smvdu.payroll.api.tools.FormulaProcessor;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.EmployeeDB;
import org.smvdu.payroll.beans.db.SalaryDataDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;
import org.smvdu.payroll.user.ActiveProfile;

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
        System.err.println("Copying Salary Data ..."+empCode+","+copyDate);
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
}
