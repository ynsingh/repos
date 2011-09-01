/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

import utils.DevelopmentSupport;

/**
 *
 * @author kazim
 */
public class LogoutAction extends DevelopmentSupport {
    private String message;

     public String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message ;
    }

@Override
    public String execute() throws Exception {
        try {
            getSession().removeAttribute("username");
            getSession().removeAttribute("userfullname");
            getSession().removeAttribute("imshortname");
            getSession().removeAttribute("simshortname");
            getSession().removeAttribute("dmshortname");
            getSession().invalidate();
            message="Thanks for using the PICO System";           
            return SUCCESS;
            }
        catch (Exception e) {
            message = "Unable to remove session";
            return ERROR;
        }
    }
}