/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.loan;

import java.util.ArrayList;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.module.attendance.LoggedEmployee;

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
public class LoanRequestController {


    public ArrayList<LoanRequest> loans;
    
    private UIData dataGrid;

    public UIData getDataGrid() {
       
        return dataGrid;
    }
    
    
    public void update()
    {
        ArrayList<LoanRequest> data = (ArrayList<LoanRequest>)dataGrid.getValue();
        for(LoanRequest lr : data)
        {
            System.out.println(lr.getInstallment()+","+lr.isApprove());
        }
    }
    
    

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    
    private int empCode;

    public ArrayList<LoanRequest> getLoans() {
        LoggedEmployee le = (LoggedEmployee) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LoggedEmployee");
        if(le!=null)
        {
            empCode = le.getProfile().getEmpId();
            loans = new LoanRequestDB().load(" where lr_emp_id="+empCode);
        }
        else
        {
            loans = new LoanRequestDB().load(" where lr_approved=0");
        }
        return loans;
    }

    public void setLoans(ArrayList<LoanRequest> loans) {
        this.loans = loans;
    }
    

}
