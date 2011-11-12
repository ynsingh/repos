/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.util.ArrayList;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.SalaryGradeDB;

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
public class SalaryGrade {

    private int maxValue;
    public int minValue;

    private int gradePay;

    public int getGradePay() {
        return gradePay;
    }

    public void setGradePay(int gradePay) {
        this.gradePay = gradePay;
    }
    

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }
    
    private String name;

    @Override
    public String toString()
    {
        return name + "("+maxValue+"-"+minValue+") - "+gradePay;
    }
    private int code;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private  SelectItem[] grades;

    public void setGrades(int[] ints)
    {
        
    }

    private ArrayList<SalaryGrade> allGrades;

    public ArrayList<SalaryGrade> getAllGrades() {
        return new SalaryGradeDB().load();
    }

    public void setAllGrades(ArrayList<SalaryGrade> allGrades) {
        this.allGrades = allGrades;
    }


    public SelectItem[] getGrades() {
        ArrayList<SalaryGrade> grds= new SalaryGradeDB().load();
        grades = new SelectItem[grds.size()];
        SalaryGrade sg = null;
        for(int i=0;i<grds.size();i++)
        {
            sg = grds.get(i);
            SelectItem si = new SelectItem(sg.code, sg.toString());
            grades[i] = si;
        }
        return grades;
    }

   public void save()
    {
       new SalaryGradeDB().save(this);
       name=null;
       maxValue=0;
       minValue=0;
   }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param string
     */
    public void setName(String string) {
        if(string!=null)
        name = string.toUpperCase();
    }

    /**
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * @param i
     */
    public void setCode(int i) {
        code = i;
    }

    

   
}
