/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import com.myapp.struts.hbm.*;

/**
 *
 * @author System Administrator
 */
public class MemberSubLibrary {
    protected SubLibrary subLibrary;
    protected CirMemberAccount cirMemberAccount;

    public CirMemberAccount getCirMemberDetail() {
        return cirMemberAccount;
    }

    public void setCirMemberAccount(CirMemberAccount cirMemberAccount) {
        this.cirMemberAccount = cirMemberAccount;
    }

    public SubLibrary getSubLibrary() {
        return subLibrary;
    }

    public void setSubLibrary(SubLibrary subLibrary) {
        this.subLibrary = subLibrary;
    }

    /**
     * @return the Sublib
     */
    

    
}
