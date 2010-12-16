/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

/**
 *
 * @author Algox
 */
public class MyTempBean {


    private Employee emp;

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    private String code;

    public String getCode() {
        return emp.getCode();
    }

    public void setCode(String code) {
        this.code = code;
    }
    

}
