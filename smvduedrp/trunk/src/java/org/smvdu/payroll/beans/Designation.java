/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.DesignationDB;

/**
 *
 * @author Algox
 */
public class Designation  {
    
    private String name;

    private int code;

    public String toString()
    {
        return name;
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

    private ArrayList<Designation> designations;

    public ArrayList<Designation> getDesignations() {
        return new DesignationDB().loadDesignations();
    }

    public void setDesignations(ArrayList<Designation> designations) {
        this.designations = designations;
    }
    
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

    private String message ="";

    public String getMessage() {
        return message;
    }


    public void save()
    {
        new DesignationDB().save(name);
        name=null;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private SelectItem[] desigs;

    public SelectItem[] getDesigs() {
        ArrayList<Designation> all = new DesignationDB().loadDesignations();
        desigs = new SelectItem[all.size()];
        Designation d = null;
        for(int i=0;i<all.size();i++)
        {
            d =all.get(i);
            SelectItem si = new SelectItem(d.code, d.name);
            desigs[i]= si;
        }
        return desigs;
    }

    public void setDesigs(String[] desigs) {
        ArrayList<Designation> des = new ArrayList<Designation>();
        for(String s: desigs)
        {
            Designation d = new Designation();
            d.setName(s);
            des.add(d);
        }
        //this.desigs = des;
    }



    private String error = " <font color='green'>* Designation name Cannot be empty </font>";

    /**
     *
     */
    public Designation() {
        super();
        // TODO Auto-generated constructor stub
    }

   
}
