/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.myemp;


import java.util.ArrayList;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import org.smvdu.payroll.beans.Department;
import org.smvdu.payroll.beans.Designation;
import org.smvdu.payroll.beans.Employee;
import org.smvdu.payroll.beans.db.DepartmentDB;
import org.smvdu.payroll.beans.db.EmployeeDB;

/**
 *
 * @author Algox
 */
public class TestEmployee {
    private static String code;
    private Department department;
    private static Employee employee;
    private Designation desig;
    private String name;
    private HtmlSelectOneMenu departmentCombo;

    public HtmlSelectOneMenu getDepartmentCombo() {
        departmentCombo= new HtmlSelectOneMenu();
        for(Department d : new DepartmentDB().loadDepartments())
        departmentCombo.setValue(d);
        return departmentCombo;
    }

    public void setDepartmentCombo(HtmlSelectOneMenu departmentCombo) {
        this.departmentCombo = departmentCombo;

    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Designation getDesig() {
        return desig;
    }

    public void setDesig(Designation desig) {
        this.desig = desig;
    }

    public Employee getEmployee() {
        return employee;
    }

    private HtmlInputText input;

    public HtmlInputText getInput() {
        return input;
    }

    public void setInput(HtmlInputText input) {
        this.input = input;
    }
    

    public void setEmployee(Employee employee) {
        TestEmployee.employee = employee;
    }
    private ArrayList<Employee> results = null;

    public ArrayList<Employee> getResults(Object s) {
        String sd = (String)s;
        return new EmployeeDB().loadProfiles("where emp_code like '"+sd+"%'");
    }

    public void setResults(ArrayList<Employee> results) {
        this.results = results;
    }


    public void print()
    {
        Department dept = (Department)departmentCombo.getSubmittedValue();
        System.out.println(dept.getName());

    } 


    public TestEmployee()
    {
        System.err.println("Emp Initialized ...");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        TestEmployee.code = code;
        if(code.length()>6)
        {
            TestEmployee.employee = new EmployeeDB().loadProfile(code);
            System.out.println("Employee Profile Loaded... "+TestEmployee.employee.getName());
        }    

    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

   
   
  

  

}
