/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.BrihaspatiRemoteAuth;


/**
 *
 * @author ERP
 */
public class BrihaspatiAuthLogin {

    /** Creates a new instance of BrihaspatiAuthLogin */
    public BrihaspatiAuthLogin() {
    }
    public String systemPro(BrihaspatiBean bb)
    {
        try
        {
            return bb.getEmailId();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
