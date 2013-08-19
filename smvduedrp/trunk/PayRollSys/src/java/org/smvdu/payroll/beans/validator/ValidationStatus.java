/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.validator;

/**
 *
 * @author ERP
 */
public class ValidationStatus {
private boolean emailPattern;

    public boolean isEmailPattern() {
        System.out.println("is"+emailPattern);
        return emailPattern;
    }

    public void setEmailPattern(boolean emailPattern) {
        this.emailPattern = emailPattern;
    }
}
