/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.db.CommonDB;
import org.smvdu.payroll.beans.db.OrgProfileDB;

/**
 *
 * @author Algox
 */
public class Org {
    private PreparedStatement ps;
    private ResultSet rs;
    private String email;
    private String phone;
    private String name;
    private String web;
    private String address1;
    private String address2;
    private String tagLine;
    private int id;
    private String masterPassword;
    private String recoveryEMailId;

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public String getRecoveryEMailId() {
        return recoveryEMailId;
    }

    public void setRecoveryEMailId(String recoveryEMailId) {
        this.recoveryEMailId = recoveryEMailId;
    }

    private String vpass;

    public String getVpass() {
        return vpass;
    }

    public void setVpass(String vpass) {
        this.vpass = vpass;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    





    public void getProfile()   {
        Org o = new OrgProfileDB().loadOrgProfile(id);
        this.name = o.getName();
        tagLine = o.getTagLine();
        phone = o.getPhone();
        email = o.getEmail();
        address1 = o.getAddress1();
        address2 = o.getAddress2();
        web = o.getWeb();
    }


    public String save()  {
        if(!masterPassword.equals(vpass))
        {
            FacesContext.getCurrentInstance().addMessage("instpass2", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords doesnot match", ""));
            return null;
        }
        Exception e = new OrgProfileDB().save(this);
        if(e==null)
        {
            return "forwarder.jsf";
        }
        else
        {
            return null;
        }
    }
    public void update()    {
        try
        {
            Connection c = new CommonDB().getConnection();
            ps= c.prepareStatement("update org_profile set org_tagline=?,org_email=?,org_web=?,org_phone=?,"
                    + "org_address1=?,org_address2=?");
            ps.setString(1, tagLine);
            ps.setString(2, email);
            ps.setString(3, web);
            ps.setString(4, phone);
            ps.setString(5, address1);
            ps.setString(6, address2);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
