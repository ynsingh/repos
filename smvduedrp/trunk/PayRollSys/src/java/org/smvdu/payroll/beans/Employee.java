/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans;

import java.io.Serializable;
import org.smvdu.payroll.beans.setup.Department;
import org.smvdu.payroll.beans.setup.Designation;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.EmployeeDB;
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class Employee implements Serializable {


    public Employee()
    {
        
    }
    
    
    private int gradePay;

    public int getGradePay() {
        return gradePay;
    }

    public void setGradePay(int gradePay) {
        this.gradePay = gradePay;
    }
    
    



    private int orgCode;

    private String code;
    private String genderName;
    private boolean selected;
    private String title;
    boolean ststus;

    private String qualification;
    private int experience;
    private String address;
    private int yearOfPassing;


    public boolean getStstus() {
        return ststus;
    }

    public void setStstus(boolean  ststus) {
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
    private String previousEmployer;

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
    private String fatherName;

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
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
        System.err.println(" Name : " + emp.getName());
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
    private String bankAccNo;
    private String pfAccNo;
    private String panNo;
    private String message;
    private Department department;
    private Designation designation;
    private String statusI;
    private boolean event;
    private String buttonValue;
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
        System.out.println("Setting department : " + department.getName() + " Code : " + department.getCode());
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

    public void updateProfile() {
        boolean b = new EmployeeDB().update(this);
        if (b) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Details Updated", ""));
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
        Employee xemp = new EmployeeDB().loadProfile(code,orgCode);
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
        Employee xemp = new EmployeeDB().loadProfile(code,orgCode);
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
        if(le==null)
        {
            UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            orgCode = uf.getUserOrgCode();
        }
 else
        {
            orgCode = le.getUserOrgCode();
 }
        Employee emp = new EmployeeDB().loadProfile(code,orgCode);
        if (emp == null) {
            emp = getDefault();
        }
        System.err.println("Name : " + emp.name);
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
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }
    public boolean isGender() {
        return gender;
    }

    public void setGender(int gen) {
        if(gen == 1)
        {
            this.gender = false;
            this.setMale(true);
        }
        else
        {
            this.gender = false;
            this.setMale(false);
        }
    }
    public String loadProfile() {

        System.err.println("Loading Profile for code :" + code);
        Employee empz = new EmployeeDB().loadProfile(code,orgCode);
        if(empz.getStstus() == false)
        {
            this.setStstus(false);
            this.setStatusI("/img/InActive.png");
        }
        else
        {
            this.setStstus(true);
            this.setStatusI("/img/Active.png");
        }
        if (empz == null) {
            empz = getDefault();
        }
        System.err.println("Name : " + empz.name);
        type = empz.type;
        System.err.println("Type : " + type);
        name = empz.name;
        setCode(code);
        dept = empz.dept;
        System.err.println("Department : " + dept);


        desig = empz.desig;
        dob = empz.dob;
        doj = empz.doj;
        phone = empz.phone;
        bankAccNo = empz.bankAccNo;
        pfAccNo = empz.pfAccNo;
        email = empz.email;
        currentBasic = empz.currentBasic;
        panNo = empz.panNo;
        fatherName= empz.fatherName;
        male = empz.male;
        qualification = empz.qualification;
        experience = empz.experience;
        address = empz.address;
        previousEmployer = empz.previousEmployer;
        yearOfPassing = empz.yearOfPassing;
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
            if (new EmployeeDB().codeExist(code)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Code already exist(" + code + ")", "(" + code + ")"));
                return;
            }
            Exception ee = new EmployeeDB().save(this);
            if (ee == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Data Saved (" + code + ")", "Employee Data Saved (" + code + ")"));
            } else {
                throw ee;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = " Profile Not Saved :" + code + " Try Again";
        }
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
        System.out.println(">> Date Of Birth : " + this.dob);

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



    public void delete()
    {
        String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("delid");
        int pid = Integer.parseInt(s);
        System.err.println("CUT Command "+pid);
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
}
