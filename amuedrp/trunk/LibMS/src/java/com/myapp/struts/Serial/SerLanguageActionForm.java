/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Serial;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class SerLanguageActionForm extends org.apache.struts.action.ActionForm {
    
    private String lan_id;
    private String button;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getLan_id() {
        return lan_id;
    }

    public void setLan_id(String lan_id) {
        this.lan_id = lan_id;
    }

    

   
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }
}
