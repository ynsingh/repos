/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;
import java.util.ArrayList;

import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import org.smvdu.payroll.beans.db.DepartmentDB;

import org.smvdu.payroll.beans.db.EmployeeQualificationDB;
import org.smvdu.payroll.beans.setup.Department;

/**
 * Manages the record of employee including all of its attribute.
 * for database operations, it uses EmployeeDB and is accessible as
 * SearchBean and EmployeeController as Managed Beans in UI calls.
 * Employee Record include Date of birth, Department, Designation etc all.
 * Copyright (c) 2010 - 2011 SMVDU, Katra.
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
 * Modified Date: 7 AUG 2014, IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
 */
public class EmployeeQualification implements Serializable {
  
   
    private int orgCode;
    private String code;
    private String examPassed;
    private int marksObtained;
      
    private String boardUniversity;
    private String board;
    private String divGrade;
    private int yearOfPassing;
    private String trainingType;
    private String topicName;
    private String instituteName;
    private String sponsoredBy;
    private String dateFrom;
    private String dateTo;
    private String message;
    private int srNo;
    
    
   public int getYearOfPassing() {
       //System.out.println("\nyearOfPassing===="+yearOfPassing);
        return yearOfPassing;
    }

    public void setYearOfPassing(int yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }
    
   private SelectItem[] codedEmp;

    public SelectItem[] getCodedEmp() {
        ArrayList<EmployeeQualification> emps = new EmployeeQualificationDB().loadEmpQualification("");
        codedEmp = new SelectItem[emps.size()];
        int k = 0;
        for (EmployeeQualification e : emps) {
            codedEmp[k] = new SelectItem(e.getCode(), e.getExamPassed());
        }
        return codedEmp;
    }

   public void setCodedEmp(SelectItem[] codedEmp) {
        this.codedEmp = codedEmp;
    }
     
    public String getExamPassed() {
        return examPassed;
    }

    public void setExamPassed(String examPassed) {
        this.examPassed = examPassed;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
    
    public String getBoardName() {
        return boardUniversity;
    }

    public void setBoardName(String boardUniversity) {
        this.boardUniversity = boardUniversity;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }

   
   // private int srNo;
    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getDivGrade() {
        return divGrade;
    }

    public void setDivGrade(String divGrade) {
        this.divGrade = divGrade;
    }
    
    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }
    
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
    
    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }
    
    public String getSponsoredBy() {
        return sponsoredBy;
    }

    public void setSponsoredBy(String sponsoredBy) {
        this. sponsoredBy = sponsoredBy;
    }
    
    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }
    
    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
    
    
     public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private UIData dataGrid;
   
        
    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    
    private UIData dataGrid1;
    
    public UIData getDataGrid1() {
        return dataGrid1;
    }

    public void setDataGrid1(UIData dataGrid1) {
        this.dataGrid1 = dataGrid1;
    }
    
    private int recordId;

    public int getRecordId() {
        //System.out.println("recordId===== : "+recordId);
        return recordId;
    }

    public void setRecordId(int recordId) {
        //System.out.println("recordId===== : "+recordId);
        this.recordId = recordId;
    }
    private EmployeeQualification empqual;

    public EmployeeQualification getEmpQual() {
        return empqual;
    }

    public void setEmpQual(EmployeeQualification empqual) {
        this.empqual = empqual;
    }
    
    
    

   private SelectItem[] empIdentity;

    public SelectItem[] getEmpIdentity() {
        ArrayList<EmployeeQualification> loadProfiles = new EmployeeQualificationDB().loadEmpQualification("");
        EmployeeQualification em = null;
        empIdentity = new SelectItem[loadProfiles.size()];
        for (int i = 0; i < loadProfiles.size(); i++) {
            em = loadProfiles.get(i);
            SelectItem si = new SelectItem(em.getRecordId(), em.getCode());
            empIdentity[i] = si;
        }

        return empIdentity;
    }

    public void setEmpIdentity(SelectItem[] empIdentity) {
        this.empIdentity = empIdentity;
    }
  
    
    public void saveQualification() {
        try {
            
            String statusMessage = null;
            Severity s = null;
            FacesContext fc = FacesContext.getCurrentInstance();
            /*if(this.getBoardName().matches("^[a-zA-Z\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Board/University");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }*/
            String emc = ""+this.getCode();
            if (emc.matches("^[a-z0-9A-Z\\s]*$") == false){
               FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Employee Code");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }
            Exception ee = new EmployeeQualificationDB().saveEmpQualification(this);
            if (ee == null) {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Data Saved (" + code + ")",""));
            } else {
                throw ee;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = " Record not saved :" + code + " Try Again";
        }
    }
    private ArrayList<EmployeeQualification> allqualdata;
    
    
    public void loadQualDetail() {
        try{
        
            allqualdata = new EmployeeQualificationDB().loadEmpQualification(code);
            //System.err.println("Loading Profile for code :" + code+"\nallqualdata==="+allqualdata);
            if((allqualdata.isEmpty()) && (!code.equals("null"))){
         
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Education and training detial of employee is not exist", ""));
          
            }
       
        }    
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
      
    public ArrayList<EmployeeQualification>getAllQualRecord(){
        
       allqualdata = new EmployeeQualificationDB().loadEmpQualification(code);
       //System.out.println("\n inget family record in line1071==code==="+code+"\nallfamilyrecord=size==="+allqualdata.size());
       dataGrid.setValue(allqualdata);  
       return allqualdata;
       
   }
   
    
    public void setAllQualRecord(ArrayList<EmployeeQualification> allqualdata) {
        this.allqualdata = allqualdata;
        
    }
 
    int currentRecordindex;
    public int getCurrentRecordindex() {
        //System.out.println("current index====="+currentRecordindex);
        return currentRecordindex;
    }
 
    public void setCurrentRecordindex(int currentRecordindex) {
        //System.out.println("current index==inset==="+currentRecordindex);
        this.currentRecordindex = currentRecordindex;
    }
    
    EmployeeQualification editedRecord;
    public EmployeeQualification getEditedRecord() {
       
        return editedRecord;
    }
 
    public void setEditedRecord(EmployeeQualification editedRecord) {
        this.editedRecord = editedRecord;
    }
       
    public void deleteRecord(){
        
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            
            ArrayList<EmployeeQualification> qualrecord = (ArrayList<EmployeeQualification>) dataGrid.getValue();
            EmployeeQualification emp=qualrecord.get(currentRecordindex);
            int currentIndex=emp.getRecordId();
           // System.out.println("\n in line 1103==getFamilyRecord=="+familyrecord);
            Exception ex = new EmployeeQualificationDB().DeleteEmpQualificationRecord(currentIndex, qualrecord);
            if(ex == null )
            {
                    message.setSeverity(FacesMessage.SEVERITY_INFO);
                    message.setSummary(" Record deleted Successfully");
                    fc.addMessage("", message);
            }
           
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
     
    public void updateRecord(){
        
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            //System.out.println("\n in line 1139==getFamilyRecord=="+editedRecord);
            Exception ex = new EmployeeQualificationDB().UpdateEmpQualificationRecord(editedRecord); 
            if(ex == null )
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary(" Record Updated Successfully");
                fc.addMessage("", message);
            }
               
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
   
    /*
    int  year;
    
    public int getYear(){
        
        return year;
    }
    
  
    
    
    public void setYear(int year) {
        
        this.year = year;
        
    }
     */   
     public void saveTrainingDetail() {
        try {
            
            String statusMessage = null;
            Severity s = null;
            FacesContext fc = FacesContext.getCurrentInstance();
            if(this.getTopicName().matches("^[a-zA-Z\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Topic Name");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }
            if(this.getSponsoredBy().matches("^[a-zA-Z\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Sponsored name");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }
            
            String emc = ""+this.getCode();
            if (emc.matches("^[a-z0-9A-Z\\s]*$") == false){
               FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Employee Code");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }
            Exception ee = new EmployeeQualificationDB().saveTrainingData(this);
            if (ee == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Qualification Record  Saved (" + code + ")", "Employee Data Saved (" + code + ")"));
            } else {
                throw ee;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = " Qualification Record Not Saved :" + code + " Try Again";
        }
    }
    private ArrayList<EmployeeQualification> alltrainingdata;
    
    
    public void loadTrainingDetail() {
        try{
        
            alltrainingdata = new EmployeeQualificationDB().loadTrainingData(code);
            //System.err.println("Loading Profile for code :" + code+"\nallqualdata==="+alltrainingdata);
            if((alltrainingdata.isEmpty()) && (!code.equals("null"))){
         
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Education and training detial of employee is not exist", ""));
          
            }
       
        }    
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
      
    public ArrayList<EmployeeQualification>getAllTrainingRecord()   {
       alltrainingdata = new EmployeeQualificationDB().loadTrainingData(code);
       //System.out.println("\n inget family record in line1071==code==="+code+"\nallfamilyrecord=size==="+alltrainingdata.size());
       dataGrid1.setValue(alltrainingdata);  
       return alltrainingdata;
       
   }
   
    
    public void setAllTrainingRecord(ArrayList<EmployeeQualification> alltrainingdata) {
        this.alltrainingdata = alltrainingdata;
        
    }
 
          
    public void deleteTrainingRecord(){
        
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            
            ArrayList<EmployeeQualification> trainingrecord = (ArrayList<EmployeeQualification>) dataGrid1.getValue();
            EmployeeQualification emp=trainingrecord.get(currentRecordindex);
            int currentIndex=emp.getRecordId();
           // System.out.println("\n in line 1103==getFamilyRecord=="+familyrecord);
            Exception ex = new EmployeeQualificationDB().DeleteTrainingRecord(currentIndex, trainingrecord);
            if(ex == null )
            {
                    message.setSeverity(FacesMessage.SEVERITY_INFO);
                    message.setSummary(" Record deleted Successfully");
                    fc.addMessage("", message);
            }
           
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
     
    public void updateTrainingRecord(){
        
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            Exception ex = new EmployeeQualificationDB().UpdateTrainingRecord(editedRecord); 
            if(ex == null )
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary(" Record Updated Successfully");
                fc.addMessage("", message);
            }
               
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
         
}
