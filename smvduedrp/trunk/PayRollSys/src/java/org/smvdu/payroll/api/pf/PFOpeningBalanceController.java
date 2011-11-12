/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.pf;

import java.util.ArrayList;
import javax.faces.component.UIData;

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
public class PFOpeningBalanceController {


    private String empCode;

    private int total=0;
    private int cTotal=0;


    private int totalInterest;
    private int closingBalance;

    public int getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(int closingBalance) {
        this.closingBalance = closingBalance;
    }

    public int getTotalInterest() {
        totalInterest = interestOnOpbal;
        return totalInterest;
    }

    public void setTotalInterest(int totalInterest) {
        this.totalInterest = totalInterest;
    }
    

    private int interestOnOpbal;

    public int getInterestOnOpbal() {
        interestOnOpbal = (cTotal*8)/1200;
        return interestOnOpbal;
    }

    public void setInterestOnOpbal(int interestOnOpbal) {
        this.interestOnOpbal = interestOnOpbal;
    }
    


    

    public int getcTotal() {
        return cTotal;
    }

    public void setcTotal(int cTotal) {
        this.cTotal = cTotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }


    private ArrayList<PFData> pfData;
    private PFOpeningAccount openingAccount;

    public PFOpeningAccount getOpeningAccount() {
        return openingAccount;
    }

    public void setOpeningAccount(PFOpeningAccount openingAccount) {
        this.openingAccount = openingAccount;
    }

    public ArrayList<PFData> getPfData() {
        return pfData;
    }

    public void setPfData(ArrayList<PFData> pfData) {
        this.pfData = pfData;
    }

    public void loadData()
    {
        openingAccount = new PFOpeningBalanceDB().getAccount(empCode);
    }

    private ArrayList<PFOpeningAccount> data;
    private UIData dataGrid;

    public ArrayList<PFOpeningAccount> getData() {
        data =new PFOpeningBalanceDB().getAccount();
        dataGrid.setValue(data);
        return data;
    }

    public void setData(ArrayList<PFOpeningAccount> data) {
        this.data = data;
    }

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }



    public void save()
    {
        //System.err.println("Emp Code : "+empCode+", Opening Balance : "+openingAccount.getOpBal());
    }

    public void update()
    {
        ArrayList<PFOpeningAccount> pac = (ArrayList<PFOpeningAccount>)dataGrid.getValue();
        new PFOpeningBalanceDB().update(pac);
    }
    

}
