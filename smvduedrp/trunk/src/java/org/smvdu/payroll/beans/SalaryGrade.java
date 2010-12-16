/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.SalaryGradeDB;

/**
 *
 * @author Algox
 */
public class SalaryGrade {

    private int maxValue;
    public int minValue;

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
        return name + "("+maxValue+"-"+minValue+")";
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
