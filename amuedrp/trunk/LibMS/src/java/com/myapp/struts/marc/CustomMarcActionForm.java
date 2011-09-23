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
public class CustomMarcActionForm extends org.apache.struts.action.ActionForm {
    
    private String allmarc[],sub_names[],marctags[];
    private char ind1[],ind2[];
   private String zclick;

    public String getZclick() {
        return zclick;
    }

    public void setZclick(String zclick) {
        this.zclick = zclick;
    }

    public char[] getInd1() {
        return ind1;
    }

    public void setInd1(char[] ind1) {
        this.ind1 = ind1;
    }

    public char[] getInd2() {
        return ind2;
    }

    public void setInd2(char[] ind2) {
        this.ind2 = ind2;
    }

   
    public String[] getMarctags() {
        return marctags;
    }

    public void setMarctags(String[] marctags) {
        this.marctags = marctags;
    }

    public String[] getAllmarc() {
        return allmarc;
    }

    public void setAllmarc(String[] allmarc) {
        this.allmarc = allmarc;
    }

    public String[] getSub_names() {
        return sub_names;
    }

    public void setSub_names(String[] sub_names) {
        this.sub_names = sub_names;
    }

   

    /**
     * @return
     */
   

    /**
     *
     */
    public CustomMarcActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
   
}
