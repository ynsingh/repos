/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.controller;

import java.util.ArrayList;
import org.smvdu.payroll.api.MatrixTransformer;
import org.smvdu.payroll.api.report.IndividualGrossDB;

/**
 *
 * @author Algox
 */
public class MonthlyGrossSalaryController {

    private String dateFrom;
    private String dateTo;
    private String empCode;

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

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    private ArrayList<GrossData> salaryMatrix;

    public ArrayList<GrossData> getSalaryMatrix() {
        salaryMatrix = MatrixTransformer.transform(new IndividualGrossDB().fetchSalaryMatrix(empCode),empCode);
        return salaryMatrix;
    }

    public void setSalaryMatrix(ArrayList<GrossData> salaryMatrix) {
        this.salaryMatrix = salaryMatrix;
    }

    

    
    

}
