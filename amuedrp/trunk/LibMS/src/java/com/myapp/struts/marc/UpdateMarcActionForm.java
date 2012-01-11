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
public class UpdateMarcActionForm extends org.apache.struts.action.ActionForm {
    
    private String bib_id;
    private String button;

    public String getBib_id() {
        return bib_id;
    }

    public void setBib_id(String bib_id) {
        this.bib_id = bib_id;
    }

    private int number;

    /**
     * @return
     */
    

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


    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
  
}
