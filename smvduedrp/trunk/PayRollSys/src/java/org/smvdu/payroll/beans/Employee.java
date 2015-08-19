/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
//import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.smvdu.payroll.beans.setup.Department;
import org.smvdu.payroll.beans.setup.Designation;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.smvdu.payroll.api.BankDetails.BankDetailsSearch;
import org.smvdu.payroll.api.BankDetails.BankProfileDetails;
import org.smvdu.payroll.api.report.LeavingDate;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.EmployeeDB;
import org.smvdu.payroll.beans.upload.UploadFile;
import org.smvdu.payroll.beans.validator.DateValidation;
import org.smvdu.payroll.beans.validator.EmployeeNotification;
import org.smvdu.payroll.module.attendance.LoggedEmployee;
import org.smvdu.payroll.user.ActiveProfile;

/**
 * Manages the record of employee including all of its attribute.
 * for database operations, it uses EmployeeDB and is accessible as
 * SearchBean and EmployeeController as Managed Beans in UI calls.
 * Employee Record include Date of birth, Department, Designation etc all.
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
 *  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
 *  Modified Date: 4 AUG 2014, 17 Jan 2015 IITK  IITK (palseema30@gmail.com, kishore.shuklak@gmail.com)
 *
 */
public class Employee implements Serializable {

    /*public Employee() {
    }*/
    
    private int gradePay;

    public int getGradePay() {
        return gradePay;
    }

    public void setGradePay(int gradePay) {
        this.gradePay = gradePay;
    }
    private String password;
    private String rePassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
    private int orgCode;
    private String code;
    private String genderName;
    private boolean selected;
    private String title="Mr";
    boolean ststus;
    private String qualification="";
    private int experience=0;
    private String address="";
    private int yearOfPassing=0;
    private boolean bankStatus;
    private boolean userNameStatus;
    private String notification = new String();
    private String salaryMessage = new String();

    private String genDetails;

    public String getGenDetails() {
        //System.out.println("employeebean==getdetails=="+ genDetails);
        return genDetails;
    }

    public void setGenDetails(String genDetails) {
         //System.out.println("employeebean==setdetails=="+ genDetails);
        this.genDetails = genDetails;
    }
     private int genDetailCode;

    public int getGenDetailCode() {
        //System.out.println("Code : "+genDetailCode);
        return genDetailCode;
    }

    public void setGenDetailCode(int genDetailCode) {
        //System.out.println("Code : "+genDetailCode);
        this.genDetailCode = genDetailCode;
    }
    public String getSalaryMessage() {
        //System.out.println("DAta Should Be Write Here :dsd "+salaryMessage);
        return salaryMessage;
    }

    public void setSalaryMessage(String salaryMessage) {
        //System.out.println("DAta Should Be Write Here seema: "+salaryMessage);
        this.salaryMessage = salaryMessage;
    }
    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public boolean isUserNameStatus() {
        return userNameStatus;
    }

    public void setUserNameStatus(boolean userNameStatus) {
        this.userNameStatus = userNameStatus;
    }

    public boolean isBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(boolean bankStatus) {
        this.bankStatus = bankStatus;
    }

    public boolean getStstus() {
        return ststus;
    }

    public void setStstus(boolean ststus) {
        this.ststus = ststus;
    }

    public int getExperience() {
        return experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(int yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }
    private String previousEmployer="";

    public String getPreviousEmployer() {
        return previousEmployer;
    }

    public void setPreviousEmployer(String previousEmployer) {
        this.previousEmployer = previousEmployer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    private String fatherName="";

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGenderName() {
        //System.out.println("employeebean==get=="+ genderName);
        return genderName;
    }

    public void setGenderName(String genderName) {
        //System.out.println("employeebean==set=="+ genderName);
        this.genderName = genderName;
    }
    private int currentBasic;
    private SelectItem[] codedEmp;

    public SelectItem[] getCodedEmp() {
        ArrayList<Employee> emps = new EmployeeDB().loadProfiles("");
        codedEmp = new SelectItem[emps.size()];
        int k = 0;
        for (Employee e : emps) {
            codedEmp[k] = new SelectItem(e.getCode(), e.getName());
        }
        return codedEmp;
    }

    public int getCurrentBasic() {

        return currentBasic;
    }

    public void setCurrentBasic(int currentBasic) {
        this.currentBasic = currentBasic;
    }

    public void setCodedEmp(SelectItem[] codedEmp) {
        this.codedEmp = codedEmp;
    }
    private Employee emp;

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
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

    public boolean isMale() {
        //System.out.println("male==="+   male);
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }
    private String phone;
    private String email;
    private int dept;
    private int desig;
    private String name;
    private String deptName;
    private String desigName;
    private String typeName;
    private String bandName;
    private boolean male;
    private int grade;
    private int srNo;
    private int type;
    private String dob;
    private String doj;
    private int empId;
    private String bankAccNo="";
    private String pfAccNo="";
    private String panNo="";
    private String message;
    private Department department;
    private Designation designation;
    private String statusI;
    private boolean event;
    private String buttonValue;
    private String bankName = new String("");
    private String bankIFSCcode = new String();
    private String bankBranchName = new String();
    private String dateOfResig = new String();
    private String empLeaDate = new String();
    private int empNotDay ;
    private String empLeavingDate = new String();
    private boolean seniorCitizen;

    public boolean isSeniorCitizen() {
        return seniorCitizen;
    }

    public void setSeniorCitizen(boolean seniorCitizen) {
        this.seniorCitizen = seniorCitizen;
    }
    public String getEmpLeavingDate() {
        return empLeavingDate;
    }

    public void setEmpLeavingDate(String empLeavingDate) {
        this.empLeavingDate = empLeavingDate;
    }
    public String getEmpLeaDate() {
        return empLeaDate;
    }

    public void setEmpLeaDate(String empLeaDate) {
        this.empLeaDate = empLeaDate;
    }

    public int getEmpNotDay() {
        return empNotDay;
    }

    public void setEmpNotDay(int empNotDay) {
        this.empNotDay = empNotDay;
    }
    public String getDateOfResig() {
        return dateOfResig;
    }

    public void setDateOfResig(String dateOfResig) {
        this.dateOfResig = dateOfResig;
    }

    public boolean isEvent() {
        return event;
    }

    public void setEvent(boolean event) {
        this.event = event;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmpId() {
        return empId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void loadBankDeails() {
        try {
            Connection cn;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Employee bankDetails(Employee emp) {
        try {
            Connection cn;
            cn = new CommonDB().getConnection();
            PreparedStatement pst;
            ResultSet rst;
            pst = cn.prepareStatement("select bank_name,branch_name,bank_ifsc_code from bankprofile where bank_ifsc_code = '" + emp.getBankIFSCcode() + "'");
            rst = pst.executeQuery();
            //Employee employee = new Employee();
            if (rst.next()) {
                emp.setBankName(rst.getString(1));
                emp.setBankBranchName(rst.getString(2));
                emp.setBankIFSCcode(rst.getString(3));
            }
            pst.close();
            cn.close();
            return emp;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateProfile(){
        try{
            //new EmployeeDB().bankDetails(this);
            FacesContext fc=FacesContext.getCurrentInstance();
            if(this.getEmail().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter EmailID In Correct Format ");
                this.setStatusI("/img/InActive.png");
                fc.addMessage("", message);
                return;
            }
            if(this.getName().matches("^[a-zA-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid First Name");
                fc.addMessage("", message);
                return;
            }
            if(this.getFatherName().matches("^[a-zA-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Father Name");
                fc.addMessage("", message);
                return;
            }
            if(this.getPhone().matches(".*[0-9]{10}.*") == false || this.getPhone().length()!=10)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Phone Number");
                fc.addMessage("", message);
                return;
            }
            if(this.getBankAccNo().trim().matches(".*[0-9].*") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Bank Acc. Number");
                fc.addMessage("", message);
                return;
            }
        
            if((this.getPanNo().matches("[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}") == false)&&(!(this.getPanNo().equals(""))))
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid pan number");
                fc.addMessage("", message);
                return;
            }
            if(this.getGpfNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid GPF Number");
                fc.addMessage("", message);
                return;
            }
            if(this.getDpsNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid DPS Number");
                fc.addMessage("", message);
                return;
            }
            if(this.getEcrNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid ECR Number");
                fc.addMessage("", message);
                return;
            }
            
            if(this.getEcrPageNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid Page No of ECR Book");
                fc.addMessage("", message);
                return;
            }
            if(this.getPostingId().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid Posting Id");
                fc.addMessage("", message);
                return;
            }
            if(this.getHouseType().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid House Type");
                fc.addMessage("", message);
                return;
            }

            if(this.getHouseNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid House Number");
                fc.addMessage("", message);
                return;
            }

            if(this.getPolicyNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid House Number");
                fc.addMessage("", message);
                return;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1=new java.util.Date();
            java.util.Date date2=new java.util.Date();

            date1=dateFormat.parse(this.getDoj());
            date2=dateFormat.parse(this.getDob());

            if (date1.compareTo(date2) <=0)
            {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Date of Joing should be graeter than Date of Birth.");
                fc.addMessage("", message);
                return;
            }
            //System.out.println("doa==="+this.getDoAcceptance()+"dom=="+this.getDoMaturity());
            if((!(this.getDoMaturity().equals("")))||(!(this.getDoAcceptance().equals(""))))
            {
                //System.out.println("doa=222=="+this.getDoAcceptance()+"dom=="+this.getDoMaturity());
                date1=dateFormat.parse(this.getDoMaturity());
                date2=dateFormat.parse(this.getDoAcceptance());

                if(date1.compareTo(date2) <= 0)
                {
                    FacesMessage message = new FacesMessage(); 
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Date of Maturity should be greater than date of Acceptance.");
                    fc.addMessage("", message);
                    return;
                }
            }
            if((!(this.getDoNextIncrement().equals("")))&&(!(this.getDoj().equals(""))))
            {
                date1=dateFormat.parse(this.getDoNextIncrement());
                date2=dateFormat.parse(this.getDoj());

                if (date1.compareTo(date2)<=0)
                {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Date of Next Increment should be greater than date of Joining.");
                    fc.addMessage("", message);
                    return;
                }
            }
            if((!(this.getConfirmationDate().equals("")))||(!(this.getProbationDate().equals("")))){
                date1=dateFormat.parse(this.getConfirmationDate());
                date2=dateFormat.parse(this.getProbationDate());
                if (date1.compareTo(date2) <=0)
                {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Confirmation date  is greater than Probation date.");
                    fc.addMessage("", message);
                    return;
                }
            }
            if((!(this.getExtentionDate().equals("")))&&(!(this.getDoj().equals("")))){
                date1=dateFormat.parse(this.getExtentionDate());
                date2=dateFormat.parse(this.getDoj());
                if (date1.compareTo(date2) <=0)
                {
                    FacesMessage message = new FacesMessage();
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Extention Date  is greater than  Date of joining.");
                    fc.addMessage("", message);
                    return;
                }
            }
           
            boolean dateVali = new DateValidation().dateOfBirthValidation(this.getDob(), this.getDoj(), this.getDateOfResig());
            if (dateVali == true) {
                boolean b = new EmployeeDB().update(this);
                boolean c= new EmployeeDB().updateEmpSupport(this);
                //System.out.println("update====b="+b+"\nc======"+c);
                if (b || c) {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Details Updated sucessfully", ""));
                }
                else
                {
                    FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Details not Updated", ""));
                }    
            }
            else {
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "(Check: DateOfBirth,DateOfJoining,DateOfResignation and Diffrence Between DateOfBirth And DateOfJoining Should Be 23 Year ) ", ""));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }     
    
    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getPfAccNo() {
        return pfAccNo;
    }

    public void setPfAccNo(String pfAccNo) {
        this.pfAccNo = pfAccNo;
    }

    public Employee getDefault() {
        Employee empx = new Employee();
        empx.name = "Default";
        empx.code = "EMP000";
        return empx;
    }

    public String getStatusI() {
        return statusI;
    }

    public void setStatusI(String statusI) {
        this.statusI = statusI;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    public Employee getProfileData(String code) {
        //System.err.println("Loading Profile for code :"+id);
        Employee xemp = new EmployeeDB().loadProfile(code, orgCode);
        if (xemp == null) {
            xemp = getDefault();
        }
        //System.err.println("Name : "+emp.name);
        type = xemp.type;
        name = xemp.name;
        code = xemp.code;
        dob = xemp.dob;
        doj = xemp.doj;
        email = xemp.email;

        dept = xemp.dept;
        desig = xemp.desig;
        bankAccNo = xemp.bankAccNo;
        pfAccNo = xemp.pfAccNo;
        panNo = xemp.panNo;
        return xemp;
    }

    public String getProfile() {


        ActiveProfile le = (ActiveProfile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ActiveProfile");

        //System.err.println("Loading Profile for code :"+id);
        Employee xemp = new EmployeeDB().loadProfile(code, orgCode);
        if (xemp == null) {
            xemp = getDefault();
        }
        //System.err.println("Name : "+emp.name);
        type = xemp.type;
        name = xemp.name;
        code = xemp.code;
        dob = xemp.dob;
        doj = xemp.doj;
        email = xemp.email;

        dept = xemp.dept;
        desig = xemp.desig;
        bankAccNo = xemp.bankAccNo;
        pfAccNo = xemp.pfAccNo;
        panNo = xemp.panNo;
        return "EditEmployeeProfile.jsf";
    }

    public void fetchProfile() {
        LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if (le == null) {
            UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            orgCode = uf.getUserOrgCode();
        } else {
            orgCode = le.getUserOrgCode();
        }
        Employee emp = new EmployeeDB().loadProfile(code, orgCode);
        if (emp == null) {
            emp = getDefault();
        }
        //System.err.println("Name : " + emp.name);
        type = emp.type;
        name = emp.name;
        setCode(code);
        dept = emp.dept;
        desig = emp.desig;
        dob = emp.dob;
        doj = emp.doj;
        phone = emp.phone;
        bankAccNo = emp.bankAccNo;
        pfAccNo = emp.pfAccNo;
        email = emp.email;
        panNo = emp.panNo;
    }
    boolean gender;
    private String genName;

    public String getGenName() {
         //System.out.println("employeebean==getname=="+ genName);
        return genName;
    }

    public void setGenName(String genName) {
        //System.out.println("employeebean==setname=="+ genName);
        this.genName = genName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(int gen) {
        if (gen == 1) {
            this.gender = false;
            this.setMale(true);
        } else {
            this.gender = false;
            this.setMale(false);
        }
    }
    
    /**
     * This method load the employee data
     * @param empcode
     * @param orgcode
     * @see EmployeeDB()
     * @return String 
     */
     public String loadProfile(){
        Employee empz = new EmployeeDB().loadProfile(code.trim(), orgCode);
        Employee empsupp = new EmployeeDB().loadEmpsupportProfile(code.trim());
        if (empz == null) {
            addEmpMess();
        }
        else{
            //System.err.println("Load Profile for code empz===:" +empz);
            if (empz.getStstus() == false) {
                this.setStstus(false);
                this.setStatusI("/img/InActive.png");
            }
            else {
                this.setStstus(true);
                //System.out.println("DAta Should Be Write Herekjhkgh : " + empz.getStstus());
                this.setStatusI("/img/Active.png");
            }
            bankName = new String();
            bankBranchName = new String();
            bankIFSCcode = new String();
            type = empz.type;
            name = empz.name;
            setCode(code);
            dept = empz.dept;
            desig = empz.desig;
            dob = empz.dob;
            doj = empz.doj;
            phone = empz.phone;
            bankAccNo = empz.bankAccNo;
            pfAccNo = empz.pfAccNo;
            email = empz.email;
            currentBasic = empz.currentBasic;
            panNo = empz.panNo;
            fatherName = empz.fatherName;
            male = empz.male;
            qualification = empz.qualification;
            experience = empz.experience;
            address = empz.address;
            previousEmployer = empz.previousEmployer;
            yearOfPassing = empz.yearOfPassing;
            ststus = empz.ststus;
            empNotDay = empz.empNotDay;
            notification = new EmployeeNotification().resignationNotification(empz.doj, empz.dateOfResig);
            //System.out.println("Noti "+notification);
            empLeaDate = new LeavingDate().leavingDate(empz.dateOfResig,empz.empNotDay);
            bankIFSCcode = empz.bankIFSCcode.trim();
            dateOfResig = empz.dateOfResig;
            genDetails = empz.genDetails;
            /* employee (support table data) support data */       
            entitledCategory = empsupp.entitledCategory.trim();
            employeeStatus = empsupp.employeeStatus;
            saldept = empsupp.saldept;
            gpfNo= empsupp.gpfNo;
            dpsNo=empsupp.dpsNo;
            npsNo=empsupp.npsNo;
            workingType = empsupp.workingType;
            houseType = empsupp.houseType;
            houseNo = empsupp.houseNo;
            ecrNo = empsupp.ecrNo;
            ecrPageNo = empsupp.ecrPageNo;
            joindept = empsupp.joindept;
            postingId = empsupp.postingId;
            policyNo = empsupp.policyNo;
            doAcceptance = empsupp.doAcceptance;
            doMaturity = empsupp.doMaturity;
            doNextIncrement = empsupp.doNextIncrement;
            probationDate = empsupp.probationDate;
            confirmationDate = empsupp.confirmationDate;
            extentionDate = empsupp.extentionDate;
            joindesig = empsupp.joindesig;
            //seniorCitizen = empz.seniorCitizen;
            //empLeaDate = empz.empLeaDate;
            //System.out.println("Bank l : "+empz.bankIFSCcode);
        }  
        return "EditEmployeeProfile";
        
    }
     
    private SelectItem[] empIdentity;
    
    public SelectItem[] getEmpIdentity() {
        ArrayList<Employee> loadProfiles = new EmployeeDB().loadProfiles("");
        Employee em = null;
        empIdentity = new SelectItem[loadProfiles.size()];
        for (int i = 0; i < loadProfiles.size(); i++) {
            em = loadProfiles.get(i);
            SelectItem si = new SelectItem(em.getEmpId(), em.getName());
            empIdentity[i] = si;
        }

        return empIdentity;
    }

    public void setEmpIdentity(SelectItem[] empIdentity) {
        this.empIdentity = empIdentity;
    }

    public ArrayList<Employee> getAll() {
        return new EmployeeDB().loadProfiles("");
    }

    public void save() {
        try {
            String statusMessage = null;
            Severity s = null;
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            if (new EmployeeDB().codeExist(code)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Code already exist(" + code + ")", "(" + code + ")"));
                return;
            }
            if (this.getEmail().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") == false) {

                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter EmailID In Correct Format ");
                fc.addMessage("", message);
                return;
            }
            if(this.getName().matches("^[a-zA-Z\\s]*$") == false) {
                
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid First Name");
                fc.addMessage("", message);
                return;
            }
            if(this.getFatherName().matches("^[a-zA-Z\\s]*$") == false) {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Father Name");
                fc.addMessage("", message);
                return;
            }
            if(this.getPhone().matches(".*[0-9]{10}.*") == false || this.getPhone().length() != 10) {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Phone Number");
                fc.addMessage("", message);
                return;
            }
            String emc = ""+this.getCode();
            if (emc.matches("^[a-z0-9A-Z\\s]*$") == false){
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Employee Code");
                fc.addMessage("", message);
                return;
            }
             //------------------------checks for new Add fields---------------------------------
            if((this.getPanNo().matches("[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}") == false)&&(!(this.getPanNo().equals(""))))
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid pan number");
                fc.addMessage("", message);
               return;
            }
            if(this.getGpfNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid GPF Number");
                fc.addMessage("", message);
               return;
            }
            if(this.getDpsNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid DPS Number");
                fc.addMessage("", message);
               return;
            }
            if(this.getEcrNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid ECR Number");
                fc.addMessage("", message);
               return;
            }
            
            if(this.getEcrPageNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid Page No of ECR Book");
                fc.addMessage("", message);
               return;
            }
            if(this.getPostingId().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid Posting Id");
                fc.addMessage("", message);
               return;
            }
            if(this.getHouseType().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid House Type");
                fc.addMessage("", message);
               return;
            }

            if(this.getHouseNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid House Number");
                fc.addMessage("", message);
               return;
            }

            if(this.getPolicyNo().matches("^[a-z0-9A-Z\\s]*$") == false)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter valid House Number");
                fc.addMessage("", message);
               return;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1=new java.util.Date();
            java.util.Date date2=new java.util.Date();

            date1=dateFormat.parse(this.getDoj());
            date2=dateFormat.parse(this.getDob());

            if (date1.compareTo(date2) <=0)
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Date of Joing should be graeter than Date of Birth.");
                fc.addMessage("", message);
                return;
            }
            //System.out.println("doa==="+this.getDoAcceptance()+"dom=="+this.getDoMaturity());
            if((!(this.getDoMaturity().equals("")))||(!(this.getDoAcceptance().equals(""))))
            {
                //System.out.println("doa=222=="+this.getDoAcceptance()+"dom=="+this.getDoMaturity());
                date1=dateFormat.parse(this.getDoMaturity());
                date2=dateFormat.parse(this.getDoAcceptance());

                if(date1.compareTo(date2) <= 0)
                {
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Date of Maturity should be greater than date of Acceptance.");
                    fc.addMessage("", message);
                    return;
                }
            }
            if((!(this.getDoNextIncrement().equals("")))&&(!(this.getDoj().equals(""))))
            {
                date1=dateFormat.parse(this.getDoNextIncrement());
                date2=dateFormat.parse(this.getDoj());

                if (date1.compareTo(date2)<=0)
                {
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Date of Next Increment should be greater than date of Joining.");
                    fc.addMessage("", message);
                    return;
                }
            }
            if((!(this.getConfirmationDate().equals("")))||(!(this.getProbationDate().equals("")))){
                date1=dateFormat.parse(this.getConfirmationDate());
                date2=dateFormat.parse(this.getProbationDate());
                if (date1.compareTo(date2) <=0)
                {
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Confirmation date  is greater than Probation date.");
                    fc.addMessage("", message);
                    return;
                }
            }
            if((!(this.getExtentionDate().equals("")))&&(!(this.getDoj().equals("")))){
                date1=dateFormat.parse(this.getExtentionDate());
                date2=dateFormat.parse(this.getDoj());
                if (date1.compareTo(date2) <=0)
                {
                    message.setSeverity(FacesMessage.SEVERITY_ERROR);
                    message.setSummary("Extention Date  is greater than  Date of joining.");
                    fc.addMessage("", message);
                    return;
                }
            }
            //------------------End------checks for new Add fields---------------------------------
            //Exception ee = new EmployeeDB().save(this);
            boolean b=new EmployeeDB().InsertAllEmpData(this);
            if (b == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Data Saved (" + code + ")", "Employee Data Saved (" + code + ")"));
            } else {
                //throw ee;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profile Not Saved (" + code + ")",""));
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = " Profile Not Saved :" + code + " Try Again";
        }
    }

    public String getDob() {
        //System.out.println(">> Date Of Birth : " +dob);
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
        //System.out.println(">> Date Of Birth : " + this.dob);

    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {

        this.dept = dept;
    }

    public int getDesig() {
        return desig;
    }

    public void setDesig(int desig) {
        this.desig = desig;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void delete() {
        String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("delid");
        int pid = Integer.parseInt(s);
        //System.err.println("CUT Command " + pid);
        new EmployeeDB().delete(pid);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected Employee Record Deleted", ""));
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public ArrayList<BankProfileDetails> getBankSuggestion(Object obj) {
        try {
            return new BankDetailsSearch().bankSearch(obj);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }
    public String bifsc = new String();

    public String getBifsc() {
        //System.out.println("DAta Should Be Write Here klop cv: " + bifsc);
        return bifsc;
    }

    public void setBifsc(String bifsc) {
        //System.out.println("DAta Should Be Write Here klop : " + bifsc);
        this.bifsc = bifsc;
    }

    public String getBankName() {

        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankIFSCcode() {
        return bankIFSCcode.trim();
    }

    public void setBankIFSCcode(String bankIFSCcode) {
        this.bankIFSCcode = bankIFSCcode.trim();
    }
    private boolean emailIdValid;
    private boolean userVali;
    private boolean dateValid;

    public boolean isDateValid() {
        return dateValid;
    }

    public void setDateValid(boolean dateValid) {
        this.dateValid = dateValid;
    }

    public boolean isEmailIdValid() {
        return emailIdValid;
    }

    public void setEmailIdValid(boolean emailIdValid) {
        this.emailIdValid = emailIdValid;
    }

    public boolean isUserVali() {
        return userVali;
    }

    public void setUserVali(boolean userVali) {
        this.userVali = userVali;
    }
    // Bank Details.............
    
    //family record--------------------
    private UIData dataGrid;
    private String membername;
    private String relation;
    private String dependent;
    private String whetheremployed;
    private boolean checked;
       
    
    public String getMemberName() {
        return membername;
    }

    public void setMemberName(String membername) {
        this.membername = membername;
    }
    
    
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
    
    public String getDependent() {
        return dependent;
    }

    public void setDependent(String dependent) {
        this.dependent = dependent;
    }
    
    public String getWhetherEmployed() {
        return whetheremployed;
    }

    public void setWhetherEmployed(String whetheremployed) {
        this.whetheremployed = whetheremployed;
    }
   
    public boolean isCheck() {
        //System.out.println("is chdecked====="+checked);
        return checked;
    }

    public void setCheck(boolean checked) {
        this.checked = checked;
    }
    
    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
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
    
    // family record-----------------
    public void saveFamilyRecord() {
        try {
            //System.out.println("method====save family record=");
            String statusMessage = null;
            Severity s = null;
            FacesContext fc = FacesContext.getCurrentInstance();
            if(this.getMemberName().matches("^[a-zA-Z\\s]*$") == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid First Name");
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
            Exception ee = new EmployeeDB().saveFamilyRecord(this);
            if (ee == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Family Record  Saved (" + code + ")", "Employee Data Saved (" + code + ")"));
            } else {
                throw ee;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = " Family Record Not Saved :" + code + " Try Again";
        }
    }
    
    private ArrayList<Employee> allfamilyrecord;
        
    public void loadfamilyDetail() {
        try{
            
            allfamilyrecord = new EmployeeDB().loadfamilyrecord(code);
            //System.err.println("Loading Profile for code :" + code+"\nallfamilyrecord==="+allfamilyrecord);
            if((allfamilyrecord.isEmpty()) && (!code.equals("null"))){
         
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Family detial of employee is not exist", ""));
          
            }
       
        }    
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
       
    public ArrayList<Employee>getAllFamilyRecord()   {
       allfamilyrecord = new EmployeeDB().loadfamilyrecord(code);
       //System.out.println("\n inget family record in line1071==code==="+code+"\nallfamilyrecord=size==="+allfamilyrecord.size());
       dataGrid.setValue(allfamilyrecord);  
       return allfamilyrecord;
       
   }
   
    
    public void setAllFamilyRecord(ArrayList<Employee> allfamilyrecord) {
        this.allfamilyrecord = allfamilyrecord;
        
    }
  
    //Employee service history
    
    private UIData dataGrid1;
    private String transactiontype;
    private String tooffice;
    private String towhichpost;
    private String empservclass;
    private int ordernum;
    private String orderdate;
    private String dateofincrement;
    private String payscale;
    private String deputationdept;
    private String areatype;
    
    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
    }
    
     public String getTooffice() {
        return tooffice;
    }

    public void setTooffice(String tooffice) {
        this.tooffice = tooffice;
    }
    
     public String getTowhichpost() {
        return towhichpost;
    }

    public void setTowhichpost(String towhichpost) {
        this.towhichpost = towhichpost;
    }
    
     public String getEmpservclass() {
        return empservclass;
    }

    public void setEmpservclass(String empservclass) {
        this.empservclass = empservclass;
    }
    
     public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }
    
     public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }
    
     public String getDateofincrement() {
        return dateofincrement;
    }

    public void setDateofincrement(String dateofincrement) {
        this.dateofincrement = dateofincrement;
    }
    
     public String getPayscale() {
        return payscale;
    }

    public void setPayscale(String payscale) {
        this.payscale = payscale;
    }
    
     public String getDeputationdept() {
        return deputationdept;
    }

    public void setDeputationdept(String deputationdept) {
        this.deputationdept = deputationdept;
    }
    
     public String getAreatype() {
        return areatype;
    }

    public void setAreatype(String areatype) {
        this.areatype = areatype;
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
    
    Employee editedRecord;
    public Employee getEditedRecord() {
        
        return editedRecord;
    }
 
    public void setEditedRecord(Employee editedRecord) {
        this.editedRecord = editedRecord;
    }
    
    public void saveServiceHistory() {
        try {
            
                Severity s = null;
                FacesContext fc = FacesContext.getCurrentInstance();
                String emc = ""+this.getCode();
                if (emc.matches("^[a-z0-9A-Z\\s]*$") == false){
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Enter Valid Employee Code");
                fc.addMessage("", message);
                return;
            }
            Exception ee = new EmployeeDB().saveEmpHistory(this);
            if (ee == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Service Record  Saved (" + code + ")", "Employee Data Saved (" + code + ")"));
            }
            else {
                throw ee;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            message = " Service Record Not Saved :" + code + " Try Again";
        }
    }
    
       
    private ArrayList<Employee> allservicerecord;
    
    public void loadserviceHistory(){
        try{
        
            allservicerecord = new EmployeeDB().loadEmpHitory(code);
           //  System.out.println("\n===loadserviceHistory==code=== "+code+"\nallsrvcsHistory===="+allservicerecord);
            if((allservicerecord.isEmpty()) && (!code.equals("null"))){
         
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Service History of employee is not exist", ""));
          
            }
      
        }    
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Employee>getAllServiceRecord(){
        
        allservicerecord = new EmployeeDB().loadEmpHitory(code);
        dataGrid.setValue(allservicerecord);  
        //System.out.println("\n inget  record in line1300=code=dataGrid1=="+dataGrid);
        return allservicerecord;
       
    }
        
    public void setAllServiceRecord(ArrayList<Employee> allservicerecord) {
        this.allservicerecord = allservicerecord;
        
    }
  
    public void deleteRecord(){
        try
        {
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            
            ArrayList<Employee> familyrecord = (ArrayList<Employee>) dataGrid.getValue();
            Employee emp=familyrecord.get(currentRecordindex);
            int currentIndex=emp.getRecordId();
           // System.out.println("\n in line 1103==getFamilyRecord=="+familyrecord);
            Exception ex = new EmployeeDB().DeleteFamilyRecord(currentIndex, familyrecord);
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
        try{
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            //System.out.println("\n in line 1139==getFamilyRecord=="+editedRecord);
            Exception ex = new EmployeeDB().UpdateFamilyRecord(editedRecord); 
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
        
    public void deleteServiceHistory(){
        try{
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            ArrayList<Employee> servicerecord = (ArrayList<Employee>) dataGrid.getValue();
            Employee emp=servicerecord.get(currentRecordindex);
            int currentIndex=emp.getRecordId();
            //System.out.println("\nindex-----===="+editedRecord.getRecordId());
            Exception ex = new EmployeeDB().DeleteServicehistoryRecord(currentIndex, servicerecord); 
            if(ex == null )
            {
                    message.setSeverity(FacesMessage.SEVERITY_INFO);
                    message.setSummary("  Service Record deleted Successfully");
                    fc.addMessage("", message);
            }
                       
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
      
    public void updateServiceRecord(){
        try{
            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            Exception ex = new EmployeeDB().UpdateServicehistoryRecord(editedRecord); 
            if(ex == null )
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary(" Sevice Record Updated Successfully");
                fc.addMessage("", message);
            }
     
        }//try
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
       
    //==================start== Employee support data (add some new field according to nysa).

    private UIData dataGrid2;
    private String entitledCategory;
    private String employeeStatus;
    private String gpfNo="";
    private String dpsNo="";
    private String npsNo="";
    private String workingType;
    private String houseType="";
    private String houseNo="";
    private String ecrNo="";
    private String ecrPageNo="";
    private String postingId="";
    private String policyNo="";
    private String doAcceptance="";
    private String doMaturity="";
    private String doNextIncrement="";
    private String probationDate="";
    private String confirmationDate="";
    private String extentionDate="";
    
    public UIData getDataGrid2() {
        return dataGrid2;
    }

    public void setDataGrid2(UIData dataGrid2) {
        this.dataGrid = dataGrid2;
    }

     public String getEntitledCategory() {
        return entitledCategory;
    }

    public void setEntitledCategory(String entitledCategory) {
        this.entitledCategory = entitledCategory;
    }
     public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
     public String getGpfNo() {
        return gpfNo;
    }

    public void setGpfNo(String gpfNo) {
        this.gpfNo = gpfNo;
    }

    public String getDpsNo() {
        return dpsNo;
    }

    public void setDpsNo(String dpsNo) {
        this.dpsNo = dpsNo;
    }

    public String getNpsNo() {
        return npsNo;
    }
    public void setNpsNo(String npsNo) {
        this.npsNo = npsNo;
    }

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getEcrNo() {
        return ecrNo;
    }

    public void setEcrNo(String ecrNo) {
        this.ecrNo = ecrNo;
    }

    public String getEcrPageNo() {
        return ecrPageNo;
    }
 public void setEcrPageNo(String ecrPageNo) {
        this.ecrPageNo = ecrPageNo;
    }

    public String getPostingId() {
        return postingId;
    }

    public void setPostingId(String postingId) {
        this.postingId = postingId;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getDoAcceptance() {
        return doAcceptance;
    }

    public void setDoAcceptance(String doAcceptance) {
        this.doAcceptance = doAcceptance;
    }

    public String getDoMaturity() {
        return doMaturity;
    }

    public void setDoMaturity(String doMaturity) {
        this.doMaturity = doMaturity;
    }

    public String getDoNextIncrement() {
        return doNextIncrement;
    }
    public void setDoNextIncrement(String doNextIncrement) {
        this.doNextIncrement = doNextIncrement;
    }

    public String getProbationDate() {
        return probationDate;
    }

    public void setProbationDate(String probationDate) {
        this.probationDate = probationDate;
    }

    public String getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getExtentionDate() {
        return extentionDate;
    }

    public void setExtentionDate(String extentionDate) {
        this.extentionDate = extentionDate;
    }
    private int saldept;
    public int getSaldept() {
        return saldept;
    }
    public void setSaldept(int saldept) {

        this.saldept = saldept;
    }
    private int joindept;
    public int getJoindept() {
        return joindept;
    }
    public void setJoindept(int joindept) {

        this.joindept = joindept;
    }
    
    private int joindesig;
    public int getJoindesig() {
        return joindesig;
    }
     
    public void setJoindesig(int joindesig) {
       this.joindesig = joindesig;
    }
    
    private String saldeptdcode;
    public String getSaldeptdcode() {
        return saldeptdcode;
    }
    public void setSaldeptdcode(String saldeptdcode) {

        this.saldeptdcode = saldeptdcode;
    }
    private String joindeptdcode;
    public String getJoindeptdcode() {
        return joindeptdcode;
    }
    public void setJoindeptdcode(String joindeptdcode) {

        this.joindeptdcode = joindeptdcode;
    }
    
    private String joindesigdcode;
    public String getJoindesigdcode() {
        return joindesigdcode;
    }

    public void setJoindesigdcode(String joindesigdcode) {

        this.joindesigdcode = joindesigdcode;
    }
    
    
    private String dcode;
    public String getDeptdcode(){
        return dcode;
    }

    public void setDeptdcode(String dcode) {

        this.dcode = dcode;
    }
    
    private String ddcode;
    public String getDesigdcode(){
        return ddcode;
    }

    public void setDesigdcode(String ddcode) {

        this.ddcode = ddcode;
    }

    private String tcode;
    public String getEmptcode(){
        return tcode;
    }

    public void setEmptcode(String tcode) {

        this.tcode = tcode;
    }
    
    private String sgcode;
    public String getSGcode(){
        return sgcode;
    }

    public void setSGcode(String sgcode) {

        this.sgcode = sgcode;
    }
    
    //==================end=== Employee support data (add some new field according to nysa).

    //upload file --------------------//
    
    private UploadFile files=null ;
    public void listener(UploadEvent event) throws Exception{
        UploadItem item = event.getUploadItem();
        UploadFile file= new UploadFile();
        file.setLength(item.getData().length);
        file.setName(item.getFileName());
        file.setData(item.getData());
        this.files=file;
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/tmp");
        //System.out.println("path=in employee==="+path);
        File ff=new File(path);
        if(!ff.exists())
            ff.mkdirs();
        ff=new File(path+"/"+file.getName());
        FileOutputStream stream=new FileOutputStream(ff,true);
        stream.write(file.getData());
        stream.close();
        saveFile();
        ff.delete();

    }
    
    public void saveFile(){
        try{
            
            FacesContext fc = FacesContext.getCurrentInstance();
                   
            boolean b=new EmployeeDB().loadData(files);
            // System.out.println("\n boolean b in save file===="+b);
            if(b==true)
            {
                //System.out.println("\n boolean b in save file==true case=="+b);
           
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee registration file uploaded successfully ", ""));
                //System.out.println("\n boolean b in save below message file==true case=="+b);
            }
            else
            {
                //System.out.println("\n boolean b in save file=false case==="+b);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Code already exist",""));
            }   

        }   
        catch(Exception ex)
        {
            ex.printStackTrace();

        }
        
    }
    
    public void addEmpMess(){
        try{
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Code does not exist",""));
            return;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();

        }
    }
          
}   
