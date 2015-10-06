/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.validator;

/**
 *
 * @author Seema Pal(palseema30@gmail.com)
 */
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class UrlValidator implements Validator {

    /**This method check the Url validation
     * @param facesContext
     * @param component
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value)throws ValidatorException {
        
        StringBuilder url = new StringBuilder();
        String urlValue = value.toString();
        Pattern patt= Pattern.compile("[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher m = patt.matcher(urlValue);
        //System.out.println("urlvalidator==="+urlValue);
        boolean matchFound = m.matches();
        if (!matchFound) {
            if(!urlValue.startsWith("http://", 0)){
                url.append("http://");
            }
            url.append(urlValue);
            try {
                new URI(url.toString());
            }
            catch (URISyntaxException e) {
                FacesMessage msg =new FacesMessage("URL validation failed","Invalid URL format");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Invalid URL format.");
            throw new ValidatorException(message);
        }    
    }
}        
