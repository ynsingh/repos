/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.Date;
import org.richfaces.component.html.HtmlCalendar;

/**
 *
 * @author Algox
 */
public class MyTempBean {




    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        System.err.println("Setting date ..."+date);
        this.date = date;
    }


    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    private org.richfaces.component.html.HtmlCalendar cal;

    public HtmlCalendar getCal() {
        return cal;
    }

    public void setCal(HtmlCalendar cal) {
        this.cal = cal;
    }


    public void print()
    {
        cal.getValue().toString();
        label = date;
    }


    

}
