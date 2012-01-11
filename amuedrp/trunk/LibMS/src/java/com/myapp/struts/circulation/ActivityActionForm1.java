/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
/**
 *
 * @author asif633@gmail.com
 */
public class ActivityActionForm1 extends org.apache.struts.action.ActionForm {
 private String id[];
 private String details[];

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
 private String button;

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }
 
}
