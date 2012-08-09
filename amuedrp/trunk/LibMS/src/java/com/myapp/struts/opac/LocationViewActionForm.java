/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class LocationViewActionForm extends org.apache.struts.action.ActionForm {
    
    private String CMBLib;
    private String CMBSUBLib;

    public String getCMBLib() {
        return CMBLib;
    }

    public void setCMBLib(String CMBLib) {
        this.CMBLib = CMBLib;
    }

    public String getCMBSUBLib() {
        return CMBSUBLib;
    }

    public void setCMBSUBLib(String CMBSUBLib) {
        this.CMBSUBLib = CMBSUBLib;
    }
    

   
    public LocationViewActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }
}
