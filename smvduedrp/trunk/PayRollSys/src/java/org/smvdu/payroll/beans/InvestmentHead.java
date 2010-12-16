/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.model.SelectItem;

import org.smvdu.payroll.beans.db.InvestmentHeadDB;

/**
 *
 * @author Algox
 */
public class InvestmentHead  {


    private String  selectionLength = "Selection Length : ";
    private SelectItem[] selectedItems;

    public SelectItem[] getSelectedItems() {
        ArrayList<InvestmentHead> deps = new InvestmentHeadDB().loadHeads();
        selectedItems =new SelectItem[deps.size()];
        InvestmentHead dep = null;
        for(int i=0;i<deps.size();i++)
        {
            dep = deps.get(i);
            SelectItem si = new SelectItem(dep.code, dep.name);
            selectedItems[i] = si;
        }
        return selectedItems;
    }

    public String getSelectionLength() {
        return selectionLength;
    }

    public void setSelectionLength(String selectionLength) {
        //this.selectionLength = selectionLength;
    }

    public String print()   {
        int[] da = new int[selectedItems.length];
        for(int i=0;i<selectedItems.length;i++)
        {
            SelectItem si = selectedItems[i];
            da[i] = (Integer)si.getValue();
            System.out.println("Value : "+si.getValue());
        }
        new TempDB().save(da);
        return "case1";
        //this.selectedItems = selectedItems;
    }

    public void setSelectedItems(SelectItem[] selectedItems) {
        int[] da = new int[selectedItems.length];
        for(int i=0;i<selectedItems.length;i++)
        {
            SelectItem si = selectedItems[i];
            da[i] = (Integer)si.getValue();
            System.out.println("Value : "+si.getValue());
        }
        new TempDB().save(da);
        this.selectedItems = selectedItems;

        selectionLength+= this.selectedItems.length;
    }



    private SelectItem[] allDepts;
    private String name;

    public void setAllDepts(SelectItem[] allDepts) {
        this.allDepts = allDepts;
    }

    public void setAllheads(ArrayList<InvestmentHead> departments) {
        this.allHeads = departments;
    }

    public ArrayList<InvestmentHead> getAllHeads() {
        allHeads= new InvestmentHeadDB().loadHeads();
        return allHeads;
    }
    

    private ArrayList<InvestmentHead> allHeads;

    private int code;
    private boolean benefit;

    private String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    

    public boolean isBenefit() {
        return benefit;
    }

    public void setBenefit(boolean benefit) {
        this.benefit = benefit;
    }
    
    private String message ="";
    private String error = " <font color='green'>* Department name Cannot be empty </font>";

    public ArrayList<InvestmentHead> getHeads() {
        ArrayList<InvestmentHead> deps = new InvestmentHeadDB().loadHeads();
        return deps;
    }
    public SelectItem[] getAllHeadsAsOption() {


        ArrayList<InvestmentHead> deps = new InvestmentHeadDB().loadHeads();
        allDepts =new SelectItem[deps.size()];
        InvestmentHead dep = null;
        for(int i=0;i<deps.size();i++)
        {
            dep = deps.get(i);
            SelectItem si = new SelectItem(dep.code, dep.name);            
            allDepts[i] = si;
        }
        return allDepts;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public void setAllDepts(ArrayList<InvestmentHead> allDepts) {
        //this.allDepts = allDepts;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
    public int getNumber() {
        return code;
    }

    /**
     * @param i
     */
    public void setNumber(int i) {
        code = i;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void save()
    {
        new InvestmentHeadDB().save(name,benefit,details);
        name=null;
    }
    public InvestmentHead() {
       
    }

}
