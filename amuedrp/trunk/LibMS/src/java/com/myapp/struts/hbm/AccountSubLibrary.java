/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

/**
 *
 * @author System Administrator
 */
public class AccountSubLibrary {
    private Login Login;
    private SubLibrary subLibrary;

    /**
     * @return the staffdetail
     */
    public Login getLogin() {
        return Login;
    }

    
    public void setLogin(Login loginDetail) {
        this.Login = loginDetail;
    }

    /**
     * @return the sublibrary
     */
    public SubLibrary getSubLibrary() {
        return subLibrary;
    }

    /**
     * @param sublibrary the sublibrary to set
     */
    public void setSubLibrary(SubLibrary sublibrary) {
        this.subLibrary = sublibrary;
    }

    

}
