/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.validator;


/**
 *
 * @author ERP
 */
public class ValidatorStatus {

    /** Creates a new instance of ValidatorStatus */
    public ValidatorStatus() {
    }

    private boolean userNameStstus;
    private boolean emailIdStstus;

    public boolean isEmailIdStstus() {
        return emailIdStstus;
    }

    public void setEmailIdStstus(boolean emailIdStstus) {
        this.emailIdStstus = emailIdStstus;
    }

    public boolean isUserNameStstus() {
        return userNameStstus;
    }

    public void setUserNameStstus(boolean userNameStstus) {
        this.userNameStstus = userNameStstus;
    }
}
