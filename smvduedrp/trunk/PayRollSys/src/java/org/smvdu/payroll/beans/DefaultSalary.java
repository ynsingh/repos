/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans;

import java.beans.*;
import java.io.Serializable;
import org.smvdu.payroll.beans.setup.SalaryGrade;
import org.smvdu.payroll.beans.setup.SalaryHead;

/**
 *
 * @author yuvraj
 */
public class DefaultSalary implements Serializable {
    
    private int id;
    private SalaryGrade gradeType;
    private SalaryHead salaryHead;
    private int amount;
    
    public DefaultSalary(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SalaryGrade getGradeType() {
        return gradeType;
    }

    public void setGradeType(SalaryGrade gradeType) {
        this.gradeType = gradeType;
    }

    public SalaryHead getSalaryHead() {
        return salaryHead;
    }

    public void setSalaryHead(SalaryHead salaryHead) {
        this.salaryHead = salaryHead;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
   
    
}
