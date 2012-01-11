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
public class SerAddLanguageActionForm extends org.apache.struts.action.ActionForm {
    
    private String lan_id;
    private String language_name;
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

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
       
        return null;
    }
}
