/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 * @author Algox
 */
public class DateBean {
    private String date;
    private int value=80;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void plus()
    {
        value+=4;
    }

    public String getDate() {
        if(date==null)
        {
            date = new CommonDB().getDate();
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        //System.err.println(">> Date "+date);
    }
    

}
