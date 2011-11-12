/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.loan;

import java.util.ArrayList;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;

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
public class LoanController {

    private UIData dataGrid;
    private ArrayList<Loan> loans;

    private int total;


    private SelectItem[] asItem;

    public SelectItem[] getAsItem() {
        loans = new LoanDB().load();
        asItem = new SelectItem[loans.size()];
        Loan loan = null;
        for(int i=0;i<loans.size();i++)
        {
            loan = loans.get(i);
            SelectItem si = new SelectItem(loan.getCode(), loan.getName());
            asItem[i] = si;
        }
        return asItem;
    }

    public void setAsItem(SelectItem[] asItem) {
        this.asItem = asItem;
    }




    public int getTotal() {
        loans = new LoanDB().load();
        if(loans==null)
        {
            total=0;
        }
 else
        {
            total = loans.size();
 }
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public ArrayList<Loan> getLoans() {
        loans = new LoanDB().load();
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }
    

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    

}
