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
public class CardInformationActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTMEMID;

    public String getTXTMEMID() {
        return TXTMEMID;
    }

    public void setTXTMEMID(String TXTMEMID) {
        this.TXTMEMID = TXTMEMID;
    }


   
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
      
        return null;
    }
}
