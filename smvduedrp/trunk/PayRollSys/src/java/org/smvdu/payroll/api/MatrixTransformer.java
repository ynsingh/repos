/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api;

import java.util.ArrayList;
import org.smvdu.payroll.api.controller.GrossData;
import org.smvdu.payroll.api.report.IndividualGrossDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;
import org.smvdu.payroll.beans.setup.SalaryHead;

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
public class MatrixTransformer {

    public static ArrayList<GrossData> transform(Integer[][] original,String empCode) {
        if(original==null)
        {
            return null;
        }
        ArrayList<SalaryHead> heads = new SalaryHeadDB().loadAllHeads();
        heads.add(new SalaryHead("Gross Income"));
        heads.add(new SalaryHead("Total Deduction"));
        heads.add(new SalaryHead("Net Salary"));
        ArrayList<GrossData> copy = new ArrayList<GrossData>();
        for(int i=0;i<original.length;i++)
        {
           GrossData gd = new GrossData();
           Integer[] ids = new Integer[13];
           System.arraycopy(original[i], 0, ids, 0, 12);
           gd.setSalaryHead(heads.get(i).getName());
           if(!heads.get(i).isType())
           {
               gd.setStyle("font-weight:italic;");
           }
           Integer total = 0;
           for(int k=0;k<12;k++)
           {
              try
              {
                    total+=ids[k];
               }
              catch(Exception e)
              {
                  
              }
           }
           ids[12] = total;
           gd.setSalaryData(ids);
           copy.add(gd);           
        }

        String[] blanks = {"-.-","-.-","-.-","-.-","-.-","-.-","-.-","-.-","-.-","-.-","-.-","-.-","-.-"};
        GrossData gd0 = new GrossData();
        gd0.setSalaryHead("Salary Summary");
        gd0.setSalaryData(blanks);
        copy.add(gd0);
        Integer[][] grosses = new IndividualGrossDB().fetchSummary(empCode);
        GrossData gd1 = new GrossData();
        gd1.setSalaryHead("Gross Income");
        gd1.setSalaryData(grosses[0]);
        gd1.setStyle("font-weight:bold");
        copy.add(gd1);
        GrossData gd2 = new GrossData();
        gd2.setSalaryHead("Total Deduction");
        gd2.setSalaryData(grosses[1]);
        gd2.setStyle("font-weight:bold");
        copy.add(gd2);
        GrossData gd3 = new GrossData();
        gd3.setSalaryHead("Net Salary");
        gd3.setSalaryData(grosses[2]);
        gd3.setStyle("font-weight:bold");
        copy.add(gd3);

          return copy;
        
    }

}
