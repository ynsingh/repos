/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

/**
 *
 * @author Faraz
 */
public class AccessionSearchActionForm extends org.apache.struts.action.ActionForm {
    
    private String TXTKEY;
    private String TXTPAGE;
    private String CMBLib;

  
    /**
     *
     */
    public AccessionSearchActionForm() {
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

    /**
     * @return the TXTPAGE
     */
    public String getTXTPAGE() {
        return TXTPAGE;
    }

    /**
     * @param TXTPAGE the TXTPAGE to set
     */
    public void setTXTPAGE(String TXTPAGE) {
        this.TXTPAGE = TXTPAGE;
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

}
