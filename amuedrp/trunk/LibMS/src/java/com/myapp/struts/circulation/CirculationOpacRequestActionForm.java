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
public class CirculationOpacRequestActionForm extends org.apache.struts.action.ActionForm {
    
    private String cmdSubLib;

    public String getCmdSubLib() {
        return cmdSubLib;
    }

    public void setCmdSubLib(String cmdSubLib) {
        this.cmdSubLib = cmdSubLib;
    }

    
       /**
     * @return
     */
   
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
