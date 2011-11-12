/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.pf;

import java.sql.*;
import java.io.Serializable;
import java.util.ArrayList;
import org.smvdu.payroll.beans.composite.SessionController;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
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
public class PFOpeningAccount implements Serializable {

    private String empCode;
    private String empName;
    private int opBal = 0;
    private int cumulativeTotal = 0;
    private int empId;
    private int employerBalance;
    private float interestOnOpbal = 0;
    private int totalWithdrawal = 0;
    private float interestOnDeposite;
    private float interestOnEmplBal = 0;
    private int annualDeposite = 0;
    private float totalInterest;
    private int closingBalance;
    private int totalPfRecovery;
    private int totalWithDrawal;


    private ArrayList<PFData> pfData;
    private PFOpeningAccount openingAccount;
    private SessionController sessionConttroler;
    private int emplCum=0;

    public int getAnnualDeposite() {
        return annualDeposite;
    }

    public void setAnnualDeposite(int annualDeposite) {
        this.annualDeposite = annualDeposite;
    }

    public float getInterestOnDeposite() {
        interestOnDeposite = (cumulativeTotal+emplCum) / 150;
        return interestOnDeposite;
    }

    public void setInterestOnDeposite(int interestOnDeposite) {
        this.interestOnDeposite = interestOnDeposite;
    }

    public int getCumulativeTotal() {
        return cumulativeTotal;
    }

    public void setCumulativeTotal(int cumulativeTotal) {
        this.cumulativeTotal = cumulativeTotal;
    }

    public PFOpeningAccount getOpeningAccount() {
        return openingAccount;
    }

    public void setOpeningAccount(PFOpeningAccount openingAccount) {
        this.openingAccount = openingAccount;
    }

    public ArrayList<PFData> getPfData() {
        loadData();
        return pfData;
    }

    public void setPfData(ArrayList<PFData> pfData) {
        this.pfData = pfData;
    }

    public void loadData() {
        pfData = new PFOpeningBalanceDB().getData(empCode);
        totalWithDrawal = 0;
        for (PFData pd : pfData) {
            annualDeposite += pd.getAmount();
            cumulativeTotal +=  pd.getCumulative();
            emplCum+=pd.getEmplCum();
            totalPfRecovery+=pd.getRecovery();
            totalWithDrawal+=pd.getWithdrawal();
            System.err.println(emplCum);
        }
        PFData d = new PFData();
        d.setMonth("Total");
        d.setAmount(annualDeposite);
        d.setCumulative(cumulativeTotal);
        d.setEmplCum(emplCum);
        d.setRecovery(totalPfRecovery);
        d.setWithdrawal(totalWithDrawal);
        pfData.add(d);
        openingAccount = new PFOpeningBalanceDB().getAccount(empCode);
    }

    public float getClosingBalance()
    {
       closingBalance = (int) (opBal + totalInterest + employerBalance+ 2*annualDeposite +totalPfRecovery - totalWithDrawal);
        return closingBalance;
    }

    public void setClosingBalance(int closingBalance) {
        this.closingBalance = closingBalance;
    }

    public float getTotalInterest() {
        totalInterest = interestOnOpbal + interestOnEmplBal + interestOnDeposite;
        return (int)(totalInterest+0.51d);  //RoundOff
    }

    public void setTotalInterest(int totalInterest) {
        this.totalInterest = totalInterest;
    }

   public float getInterestOnEmplBal() {
       interestOnEmplBal = (float) ((employerBalance * 8.0) / 100);
        return interestOnEmplBal;

    }

    public void setInterestOnEmplBal(int interestOnEmplBal) {
        this.interestOnEmplBal = interestOnEmplBal;
    }

    public int getEmployerBalance() {
        return employerBalance;
    }

    public void setEmployerBalance(int employerBalance) {
       this.employerBalance = employerBalance;
    }

    public int getTotalWithdrawal() {
        System.out.println("With Drawal Data : "+totalWithdrawal);
        return totalWithdrawal;
    }

    public void setTotalWithdrawal(int totalWithdrawal) {
        this.totalWithdrawal = totalWithdrawal;
    }

    public float getInterestOnOpbal() {
        interestOnOpbal = (float) ((opBal * 8.0) / 100);
        return interestOnOpbal;
    }

    public void setInterestOnOpbal(int interestOnOpbal) {
        this.interestOnOpbal = interestOnOpbal;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getOpBal() {
        return opBal;
    }

    public void setOpBal(int opBal) {
        this.opBal = opBal;
    }

    public int getTotalPfRecovery() {
        return totalPfRecovery;
    }

    public void setTotalPfRecovery(int totalPfRecovery) {
        this.totalPfRecovery = totalPfRecovery;
    }

    public int getTotalWithDrawal() {
        return totalWithDrawal;
    }

    public void setTotalWithDrawal(int totalWithDrawal) {
        this.totalWithDrawal = totalWithDrawal;
    }
}
