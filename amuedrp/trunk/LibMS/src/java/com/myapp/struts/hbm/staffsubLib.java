/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.hbm;

import java.io.Serializable;

/**
 *
 * @author System Administrator
 */
public class staffsubLib implements Serializable {
    private StaffDetail staffDetail;
    private SubLibrary subLibrary;

    /**
     * @return the staffdetail
     */
    public StaffDetail getStaffDetail() {
        return staffDetail;
    }

    
    public void setStaffDetail(StaffDetail staffDetail) {
        this.staffDetail = staffDetail;
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
