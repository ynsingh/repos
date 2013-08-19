/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.user;

import javax.faces.bean.*;

/**
 *
 * @author ERP
 */
@ManagedBean
@RequestScoped
public class SalaryMessage {


    private String message = new String();

    public String getMessage() {
        System.out.println("D : "+message);
        return message;
    }

    public void setMessage(String message) {
        System.out.println("DA : "+message);
        this.message = message;
    }
}
