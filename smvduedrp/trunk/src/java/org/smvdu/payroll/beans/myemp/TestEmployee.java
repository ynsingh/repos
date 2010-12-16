/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.myemp;


import org.smvdu.payroll.beans.Department;

/**
 *
 * @author Algox
 */
public class TestEmployee {
    private String code;
    private String name;
    private Department department;


    public TestEmployee()
    {
        System.err.println("Emp Initialized ...");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.err.println("Name Set : "+name);
    }


    public void processXYZ()
    {
        try
        {
        System.err.println("Code : "+code+" , Name : "+name+" , Department : "+department.getName());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
