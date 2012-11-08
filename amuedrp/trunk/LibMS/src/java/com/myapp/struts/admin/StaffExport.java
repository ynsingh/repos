/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.admin;
import com.myapp.struts.hbm.*;


import java.io.Serializable;

public class StaffExport  implements Serializable{
    StaffDetail staffDetail;
    SubLibrary subLibrary;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    Login login;

    public StaffDetail getStaffDetail() {
        return staffDetail;
    }

    public void setStaffDetail(StaffDetail staffDetail) {
        this.staffDetail = staffDetail;
    }

    public SubLibrary getSubLibrary() {
        return subLibrary;
    }

    public void setSubLibrary(SubLibrary subLibrary) {
        this.subLibrary = subLibrary;
    }

}
