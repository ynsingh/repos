/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.SalaryProfileDB;

/**
 *
 * @author Algox
 */
public class SalaryProfile {
    
    private String name;

   
    private int number;
    @Override
    public String toString()
    {
        return name;
    }
    private SelectItem[] profiles;

    public SelectItem[] getProfiles() {
        ArrayList<SalaryProfile> saf = new SalaryProfileDB().loadProfiles();
        profiles = new SelectItem[saf.size()];
        SalaryProfile sf = null;
        for(int i=0;i<saf.size();i++)
        {
            sf = saf.get(i);
            SelectItem si = new SelectItem(sf.number, sf.name);
            profiles[i]=  si;
        }
        return profiles;
    }

    public void setProfiles(String[] profiles) {
        
    }

    public void save()
    {
        new SalaryProfileDB().save(name);
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
        return number;
    }

    /**
     * @param i
     */
    public void setNumber(int i) {
        number = i;
    }

    /**
     *
     */
    public SalaryProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

}
