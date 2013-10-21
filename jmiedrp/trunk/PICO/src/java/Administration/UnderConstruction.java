/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

/**
 *
 * @author kazim
 */

import java.io.*;
import utils.DevelopmentSupport;

public class UnderConstruction extends DevelopmentSupport{

    private InputStream inputStream;
    private String message;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMessage() {
        return message;
    }

@Override
    public String execute() throws Exception {
        try {
            message = "Kindly wait for the Next Update";
            return SUCCESS;
            }
        catch (Exception e) {
           message = "Exception in Initialising Institutions -> InstitutionAxn" + e.getMessage();
           return ERROR;
        }
       }

}
