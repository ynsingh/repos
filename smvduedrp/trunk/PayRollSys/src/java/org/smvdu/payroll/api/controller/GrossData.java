/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.controller;

/**
 *
 * @author Algox
 */
public class GrossData {

    private  String salaryHead;
    private Integer[] salaryData;

    public  String getSalaryHead() {
        return salaryHead;
    }

    public  void setSalaryHead(String salaryHead) {
        this.salaryHead = salaryHead;
    }

    public Integer[] getSalaryData() {
        return salaryData;
    }

    public void setSalaryData(Integer[] salarydata) {
        this.salaryData = salarydata;
    }
    

    
    

}
