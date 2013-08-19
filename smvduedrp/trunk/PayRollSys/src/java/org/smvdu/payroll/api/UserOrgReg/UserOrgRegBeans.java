/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.UserOrgReg;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.api.email.MailTest;
 
/**
 *
 * @author smvduerp
 */
public class UserOrgRegBeans implements Serializable {

    /** Creates a new instance of UserOrgRegBeans */
    public UserOrgRegBeans() {
    }
    private String Name= new String();
    private String Gender = new String();
    private String Phone = new String();
    private String fatherName = new String();
    private String Dateoffbirth = new String();
    private int  OrgCode ;
    private String address= new String();
    private String email = new String();
    private boolean terms;
    private String regCode = new String();
    public String getDateoffbirth() {
        return Dateoffbirth;
    }

    public void setDateoffbirth(String Dateoffbirth) {
        this.Dateoffbirth = Dateoffbirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getName() {
        return Name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(int OrgCode) {
        this.OrgCode = OrgCode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTerms() {
        return terms;
    }

    public void setTerms(boolean terms) {
        this.terms = terms;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    
      public void save() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            if (this.isTerms() == false) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Select Terms And Condition ");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }
            /*if (this.getRegCode()) {
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Plz Select Terms And Condition ");
                //message.setDetail("First Name Must Be At Least Three Charecter ");
                fc.addMessage("", message);
                return;
            }*/
            Exception ex = new UserOrgRegDB().save(this);
            if (ex == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Data Saved (" + this.getEmail() + ")", "Employee Data Saved (" + this.getEmail() + ")"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ""+ex, ""));
                throw ex;
            }
        }
        catch(Exception ex)
        {
            
        }
        }
        public void sendingMailToNewUser()
        {
            try
            {
                boolean b = new MailTest().sendMail(this);
                if(b == true)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Check Your Email For Registration Code", ""));
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }

        public void registartionCode()
        {
            try
            {

                if(new UserOrgRegDB().checkRegCode(this) == false)
                {
                    this.setEnable(false);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter Your Registration Code", ""));
                    return;
                }
                else
                {
                    this.setEnable(true); 
 }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
}
