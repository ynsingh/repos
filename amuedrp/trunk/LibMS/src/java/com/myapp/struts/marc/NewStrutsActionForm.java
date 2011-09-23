/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author zeeshan
 */
public class NewStrutsActionForm extends org.apache.struts.action.ActionForm {
    
    private String name,btn,tagnumber;

    public String getTagnumber() {
        return tagnumber;
    }

    public void setTagnumber(String tagnumber) {
        this.tagnumber = tagnumber;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    private int number;

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
        name = string;
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
    public NewStrutsActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }


}
