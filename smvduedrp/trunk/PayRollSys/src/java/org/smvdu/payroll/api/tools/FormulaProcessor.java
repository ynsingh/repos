/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.tools;

import com.graphbuilder.math.Expression;
import com.graphbuilder.math.ExpressionTree;
import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.VarMap;
import java.util.ArrayList;
import org.smvdu.payroll.beans.SalaryData;
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
public class FormulaProcessor {

    public void processFormula(ArrayList<SalaryData> data)  {
        System.out.println("Processing Formula ...");
        VarMap map = new VarMap(false);


        for(SalaryHead sh : new SalaryHeadDB().loadAllHeads())
        {
            map.setValue(sh.getAlias(), 0);
        }
        //map.setValue("basic", currentBasic);
        FuncMap fm = null;
        for(SalaryData sd : data)
        {
            System.err.println(sd.getAlias()+","+sd.getFormula()+","+sd.getHeadCode());
            if(sd.getFormula()!=null&&!sd.getFormula().isEmpty())
            {
                Expression x = ExpressionTree.parse(sd.getFormula());
                int val = (int) Math.round(x.eval(map, fm));
                
                map.setValue(sd.getAlias(),val);
                sd.setHeadValue(val);
            }
         else
            {
                map.setValue(sd.getAlias(), sd.getHeadValue());
            }
        }
    }

}
