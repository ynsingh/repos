/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.report;

import java.util.ArrayList;
import org.richfaces.component.UIDataTable;
import org.smvdu.payroll.beans.setup.SalaryHead;
import org.smvdu.payroll.beans.db.SalaryHeadDB;



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
public class MonthlyGrossController {

    private int typeCode;

    private UIDataTable table;

    public UIDataTable getTable() {

        return table;
    }

    public void setTable(UIDataTable table) {
        this.table = table;
    }





    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    private ArrayList<String> heads;

    public ArrayList<String> getHeads() {
        heads = new SalaryHeadDB().getApplicableHeads("EMP001");
        return heads;
    }

    public void setHeads(ArrayList<String> heads) {
        this.heads = heads;
    }


    private ArrayList<SalaryHead> applicableHeads;

    public ArrayList<SalaryHead> getApplicableHeads() {
        System.out.println("Loading applicable heads ...");
        applicableHeads = new SalaryHeadDB().loadAppliedHeads(1);
        System.out.println("applicable heads ..."+applicableHeads.size());
        for(SalaryHead sh : applicableHeads)
        {
            System.out.println(sh.getName());
        }
        return applicableHeads;
    }

    public void setApplicableHeads(ArrayList<SalaryHead> applicableHeads) {
        this.applicableHeads = applicableHeads;
    }


   
    

    

    
}
