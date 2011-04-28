/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class CirCheckoutActionForm extends org.apache.struts.action.ActionForm {
    
    private String memid;
    private int max_chkout;

    public int getMax_chkout() {
        return max_chkout;
    }

    public void setMax_chkout(int max_chkout) {
        this.max_chkout = max_chkout;
    }
    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
    }
    
   

    /**
     * @return
     */
   
    /**
     *
     */
    public CirCheckoutActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }
}
