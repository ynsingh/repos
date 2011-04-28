/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


/**
 *
 * @author Dushyant
 */
public class LoginActionForm extends org.apache.struts.action.ActionForm {
    
    private String username;

    private String password;
private String button1;
private String locale;

    
    public String getUsername() {
        return username;
    }

    
    public void setUsername(String string) {
        username = string;
    }

   
    public String getPassword() {
        return password;
    }

   
    public void setPassword(String string) {
        password = string;
    }

    /**
     *
     */
    public LoginActionForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        

            // TODO: add 'error.name.required' key to your resources
        
        return null;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request){
        
    }


  
    public String getButton1() {
         return button1;

       
    }

    
    public void setButton1(String button1) {
        this.button1 = button1;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }
}
