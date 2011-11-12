/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.model;

import com.graphbuilder.math.Expression;
import com.graphbuilder.math.ExpressionTree;
import java.util.ArrayList;
import javax.faces.component.UIData;
import org.smvdu.payroll.beans.SalaryFormula;
import org.smvdu.payroll.beans.db.SalaryFormulaDB;

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
public class MyTableModel 
{
    private ArrayList<SalaryFormula> data;

    private UIData myGrid;

    public ArrayList<SalaryFormula> getData() {
        return data;
    }

    public void setData(ArrayList<SalaryFormula> data) {
        this.data = data;
    }

    public UIData getMyGrid() {
        //myGrid.
        return myGrid;
    }

    public void setMyGrid(UIData myGrid) {
        this.myGrid = myGrid;
    }

    public MyTableModel()
    {
        data = new SalaryFormulaDB().loadFormula();
        for(SalaryFormula sf : data)
        {
            if((!(sf.getFormula()==null))&&!sf.getFormula().isEmpty())
            {
                Expression ex = ExpressionTree.parse(sf.getFormula());
                String[] ss = ex.getVariableNames();
                String k = "";
                for(String s : ss)
                {
                    k+=s+" ";
                }
                sf.setFields(k);
            }
        }

    }



    public void update()
    {
       ArrayList<SalaryFormula> datas = (ArrayList<SalaryFormula>) myGrid.getValue();
       for(SalaryFormula sd : datas)
       {
           System.out.println(sd.getName()+" : "+sd.getFormula());
       }
       System.out.println("Saving Data ...");
       new SalaryFormulaDB().save(myGrid);
    }



}
