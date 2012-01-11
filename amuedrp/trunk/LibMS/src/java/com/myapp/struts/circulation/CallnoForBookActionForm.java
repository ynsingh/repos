/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class CallnoForBookActionForm extends org.apache.struts.action.ActionForm {
    
    private String call_no;
    private String accessionno;

    public String getAccessionno() {
        return accessionno;
    }

    public void setAccessionno(String accessionno) {
        this.accessionno = accessionno;
    }

    public String getCall_no() {
        return call_no;
    }

    public void setCall_no(String call_no) {
        this.call_no = call_no;
    }

    

    /**
     * @return
     */
    

    /**
     *
     */
    public CallnoForBookActionForm() {
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
