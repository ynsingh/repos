/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.composite;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.application.FacesMessage;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.EmpSalaryLiabilityDB;

/**
 *
 * Copyright (c) 2010 - 2011 SMVDU, Katra.
 * Copyright (c) 2014 - 2017 ETRG, IITK.
 * All Rights Reserved.
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  Contributors: Members of IITK Team @ IIT Kanpur
 *  Manorama Pal  7 AUG 2016, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
 */

public class EmpSalaryLiability implements Serializable{
    private String code;
    private UIData dataGrid;
    private UIData dataGrid1;
    private int month;
    private int year;
    private int liabiltyamount;
    private String type;
    private String tdate;
    private String status;
    private String deptName;
    private String desigName;
    private String typeName;
    private int srNo;
    private boolean checked;
    private UserInfo user;
    private String message;
    private boolean btndisabled;
        
    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }
    
         
    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    
    public UIData getDataGrid1() {
        return dataGrid1;
    }

    public void setDataGrid1(UIData dataGrid1) {
        this.dataGrid1 = dataGrid1;
    }
    
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public int getLiabiltyAmt() {
          return liabiltyamount;
    }

    public void setLiabiltyAmt(int liabiltyamount) {
        this.liabiltyamount = liabiltyamount;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getTransactionDate() {
        return tdate;
    }

    public void setTransactionDate(String tdate) {
        this.tdate = tdate;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDesigName() {
        return desigName;
    }

    public void setDesigName(String desigName) {
        this.desigName = desigName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
          
    private Employee employee;
    
    public Employee getEmployee() {

        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
                               
    }
    
    
    public boolean isBtnDisabled() {
        return btndisabled;
    }
    
    public void setBtnDisabled(boolean btndisabled) {
        this.btndisabled = btndisabled;
    }                          
    
    private String realDate;
    private String date;


    public String toString()
    {
        return date;
    }
    

    public String getDate() {
        return date;
    }
    
     public void setDate(String date) {
        this.date = date;
    }
     public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
   
   
   private ArrayList<EmpSalaryLiability> monthData;
  
    private SelectItem[] copyProvider;

    public SelectItem[] getCopyProvider() {
        monthData = new EmpSalaryLiabilityDB().loadDates(2010);
        copyProvider= new SelectItem[monthData.size()];
        EmpSalaryLiability sc = null;
        for(int i=0;i<monthData.size();i++)
        {
            sc= monthData.get(i);
            SelectItem si = new SelectItem(sc.getTransactionDate(), sc.getDate());
            copyProvider[i] = si;
            
        }
        return copyProvider;
    }

    public void setCopyProvider(SelectItem[] copyProvider) {
        this.copyProvider = copyProvider;
    }



    private String current;

    public String getCurrent() {
        
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

      
    private ArrayList<EmpSalaryLiability> alldata;
    
    
    public void loadliablityDetail() {
        try{
            getEmpliabrecord();
       
        }    
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<EmpSalaryLiability>getEmpliabrecord(){
        user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        if(current == null){
            String monthyear=Integer.toString(user.getCurrentMonth())+"-"+Integer.toString(user.getCurrentYear());
            alldata = new EmpSalaryLiabilityDB().loadEmpLiability(monthyear);
           
           
        }   
        else{
            
            alldata = new EmpSalaryLiabilityDB().loadEmpLiability(current);
           
        }
        this.setCurrent(current);
        dataGrid.setValue(alldata);
        return alldata;
       
   }
   
   public void setEmpliabrecord(ArrayList<EmpSalaryLiability> alldata) {
        this.alldata = alldata;
        
    }
  
   
   public void callpage()
    {
        try
        {
            //int Amountexp= new EmpSalaryLiabilityDB().getALLEmpTotalExpenses();
            //System.out.println("in callpage method==1==="+Amountexp);
            //int amount= new EmpSalaryLiabilityDB().getEmpTotalLiabilityType("credit");
            //System.out.println("in callpage method====="+amount);
            FacesContext.getCurrentInstance().getExternalContext().redirect("AllEmpLiability.jsf");
            return;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
           
        }
    }
   
    public String getCurrentMonthYear() {
        
        user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
              
        if(current== null){
           //this.setCurrent(user.getCurrentMonthName()); 
           current=user.getCurrentMonthName();
          
        }
        else
        {
                      
            String[] months = {"", "JAN", "FEB", "MARCH", "APRIL", "MAY",
                              "JUNE", "JULY", "AUG", "SEPT", "OCT", "NOV", "DEC"};
            if(current.contains("-")){
                String[] bits = current.split("-");
            
                current=months[Integer.parseInt(bits[0])]+","+ bits[1];
            }
          
        }
                
        return current;
    }

    public void setCurrentMonthYear(String current) {
        this.current = current;
    }
    
    
     public ArrayList<EmpSalaryLiability>getSalaryPayableRecord(){
        user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        if(current == null){
            String monthyear=Integer.toString(user.getCurrentMonth())+"-"+Integer.toString(user.getCurrentYear());
            alldata = new EmpSalaryLiabilityDB().LoadSalaryPaidData(monthyear);
           
        }   
        else{
            
            alldata = new EmpSalaryLiabilityDB().LoadSalaryPaidData(current);
            
        }
        this.setCurrent(current);
        dataGrid1.setValue(alldata);
        return alldata;
       
   }
   
   public void setSalaryPayableRecord(ArrayList<EmpSalaryLiability> alldata) {
        this.alldata = alldata;
        
    }
    
    public void Updatesalaryprocess()
    {
        try
        {
            FacesMessage message = new FacesMessage();
            ArrayList<EmpSalaryLiability> datacopy = new ArrayList<EmpSalaryLiability>();
            ArrayList<EmpSalaryLiability> empsliab = (ArrayList<EmpSalaryLiability>)dataGrid1.getValue();
            
            for(EmpSalaryLiability esl : empsliab)
            {   
                if(esl.isChecked())
                {
                    datacopy.add(esl);
                                       
                } 
            }
            boolean b=new EmpSalaryLiabilityDB().checkBudget(datacopy);
            if(b== true){
                Exception ee= new EmpSalaryLiabilityDB().EmployeeSalaryPaid(datacopy);
                if (ee == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary paid sucessfully ",""));
                }
                else {
                throw ee;
                }
            }
            else{
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Allocated Budget is not sufficient to process salary", ""));
            }
                
                       
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            message = "  Please Try Again";
        }
    }
   
}
