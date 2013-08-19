/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.BrihaspatiRemoteAuth;


/**
 *
 * @author ERP
 */
public class BrihaspatiBean {

    /** Creates a new instance of BrihaspatiBean */
    public BrihaspatiBean() {
    }
    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void bri()
    {
        System.out.println("DAta Should Be Write Here : "+new BrihaspatiAuthLogin().systemPro(this)); 
    }
}
