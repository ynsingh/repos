/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

/**
 *
 * @author Faraz
 */
public class CallNoSearchActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTKEY;
    private String CMBLib;
    private String CMBSUBLib;
    private String checkbox;
    private String language;

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCMBSUBLib() {
        return CMBSUBLib;
    }

    public void setCMBSUBLib(String CMBSUBLib) {
        this.CMBSUBLib = CMBSUBLib;
    }
    
    public CallNoSearchActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the TXTKEY
     */
    public String getTXTKEY() {
        return TXTKEY;
    }

    /**
     * @param TXTKEY the TXTKEY to set
     */
    public void setTXTKEY(String TXTKEY) {
        this.TXTKEY = TXTKEY;
    }
    public String getCMBLib() {
        return CMBLib;
    }

    /**
     * @param CMBLib the CMBLib to set
     */
    public void setCMBLib(String CMBLib) {
        this.CMBLib = CMBLib;
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
    
     */
   
}
