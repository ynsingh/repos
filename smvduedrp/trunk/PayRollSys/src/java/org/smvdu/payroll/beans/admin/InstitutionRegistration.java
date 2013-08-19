/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.admin;

import javax.faces.event.ActionEvent;


/**
 *
 * @author ERP
 */
public class InstitutionRegistration {

    /** Creates a new instance of InstitutionRegistration */
    public InstitutionRegistration() {
    }

    public String Code(ActionEvent actionEvent)
    {
        try
        {
            return "admin/InstProfile.jsf";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
