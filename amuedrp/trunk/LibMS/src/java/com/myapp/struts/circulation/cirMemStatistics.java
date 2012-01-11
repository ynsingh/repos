/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.hbm.CirMemberAccount;

public class cirMemStatistics {
private String year;
private String month;
private int reg;
private String library_id;
private String sublibrary_id;
private CirMemberAccount cirMemberAccount;

    public CirMemberAccount getCirMemberAccount() {
        return cirMemberAccount;
    }

    public void setCirMemberAccount(CirMemberAccount cirMemberAccount) {
        this.cirMemberAccount = cirMemberAccount;
    }

    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getSublibrary_id() {
        return sublibrary_id;
    }

    public void setSublibrary_id(String sublibrary_id) {
        this.sublibrary_id = sublibrary_id;
    }
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }





}