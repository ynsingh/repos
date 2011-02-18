/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.EmployeeDB;

/**
 *
 * @author Algox
 */
public class Employee {
    private String code;


    private int currentBasic;    
    private SelectItem[] codedEmp;
    public SelectItem[] getCodedEmp() {
        ArrayList<Employee> emps = new EmployeeDB().loadProfiles("");
        codedEmp = new SelectItem[emps.size()];
        int k=0;
        for(Employee e : emps)
        {
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
        System.err.println(" Name : "+emp.getName());
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
    private int dept ;
    private int desig;
    private String name;
    private String deptName;
    private String desigName;
    private String typeName;
    private String bandName;
    private boolean male;
    private int grade;
    private String address;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        System.out.println("Setting department : "+department.getName()+" Code : "+department.getCode());
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
    public void updateProfile()   {
         boolean b =new EmployeeDB().update(this);
         if(b)
         {
             FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Details Updated",""));
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
    public Employee getDefault()    {
       Employee emp = new Employee();
       emp.name="Default";
       emp.code="EMP000";
       
       return emp;
   }


    public Employee getProfileData(String code)    {
       //System.err.println("Loading Profile for code :"+id);
       Employee xemp = new EmployeeDB().loadProfile(code);
       if(xemp==null)
       {
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
    public String getProfile()    {
       //System.err.println("Loading Profile for code :"+id);
       Employee xemp = new EmployeeDB().loadProfile(code);
       if(xemp==null)
       {
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



    public void fetchProfile()
    {
        Employee emp = new EmployeeDB().loadProfile(code);
       if(emp==null)
       {
           emp = getDefault();
       }
       System.err.println("Name : "+emp.name);
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


    public String loadProfile()    {
       
       System.err.println("Loading Profile for code :"+code);
       Employee emp = new EmployeeDB().loadProfile(code);
       if(emp==null)
       {
           emp = getDefault();
       }
       System.err.println("Name : "+emp.name);
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
       return "EditEmployeeProfile";
       
   }


    private SelectItem[] empIdentity;

    public SelectItem[] getEmpIdentity() {
        ArrayList<Employee> loadProfiles = new EmployeeDB().loadProfiles("");
        Employee em= null;
        empIdentity = new SelectItem[loadProfiles.size()];
        for(int i=0;i<loadProfiles.size();i++)
        {
            em = loadProfiles.get(i);
            SelectItem si = new SelectItem(em.getEmpId(),em.getName());
            empIdentity[i] = si;
        }
        
        return empIdentity;
    }

    public void setEmpIdentity(SelectItem[] empIdentity) {
        this.empIdentity = empIdentity;
    }
    

    public ArrayList<Employee> getAll()   {
       return new EmployeeDB().loadProfiles("");
   }
    public void save()    {
        try
        {
            if(new EmployeeDB().codeExist(code))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Code already exist("+code+")", "("+code+")"));
                return;
            }
            Exception ee=new EmployeeDB().save(this);
            if(ee==null)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Data Saved ("+code+")", "Employee Data Saved ("+code+")"));
            }
            else
                throw ee;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            message = " Profile Not Saved :"+code +" Try Again";
        }
   }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
        System.out.println(">> Date Of Birth : "+this.dob);
        
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
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }


    @Override
    public String toString()
    {
        return name;
    }
}
