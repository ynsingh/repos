/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author edrp02
 */
public class CirCardProcessActionForm extends org.apache.struts.action.ActionForm {
    
    private String library_id;
    private String  address;
    private String checkbox;
    private String name;
    private String id;
    private String clas;
    private String session;
    private String libname;
    private String libaddress;
    private String cardtype;
    private String cardgroup;
    private String labname;
    private String labid;
    private String labclass;
    private String labsession;
    private String labaddress;
    private String holdersign;
    private String dob;
    private String authoritysign;
    private String address2;
    private String memid;

    public String getLibname() {
        return libname;
    }

    public void setLibname(String libname) {
        this.libname = libname;
    }

    public String getLibaddress() {
        return libaddress;
    }

    public void setLibaddress(String libaddress) {
        this.libaddress = libaddress;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardgroup() {
        return cardgroup;
    }

    public void setCardgroup(String cardgroup) {
        this.cardgroup = cardgroup;
    }

    public String getLabname() {
        return labname;
    }

    public void setLabname(String labname) {
        this.labname = labname;
    }

    public String getLabid() {
        return labid;
    }

    public void setLabid(String labid) {
        this.labid = labid;
    }

    public String getLabclass() {
        return labclass;
    }

    public void setLabclass(String labclass) {
        this.labclass = labclass;
    }

    public String getLabsession() {
        return labsession;
    }

    public void setLabsession(String labsession) {
        this.labsession = labsession;
    }

    public String getLabaddress() {
        return labaddress;
    }

    public void setLabaddress(String labaddress) {
        this.labaddress = labaddress;
    }

    public String getHoldersign() {
        return holdersign;
    }

    public void setHoldersign(String holdersign) {
        this.holdersign = holdersign;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAuthoritysign() {
        return authoritysign;
    }

    public void setAuthoritysign(String authoritysign) {
        this.authoritysign = authoritysign;
    }



    
    
   



   


    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

   

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
    }

   

   

    

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getGroup() {
        return session;
    }

    public void setGroup(String session) {
        this.session = session;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }
    

   
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        return null;
    }
}
