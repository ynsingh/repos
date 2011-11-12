/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.validator;

import java.sql.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.smvdu.payroll.beans.db.CommonDB;

/**
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class DateValidator implements Validator{


    public DateValidator()
    {
        System.err.println("Date Validator Active .");
    }
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       String mydate = (String)value;
       try
       {
            Date dt = Date.valueOf(mydate);
            String[] ddd = mydate.split("-");
            int year = Integer.parseInt(ddd[0]);
            int month = Integer.parseInt(ddd[1]);
            int date = Integer.parseInt(ddd[2]);
            int od = new CommonDB().getYear();
            if(year<1900||year>od)
            {
                throw new Exception("Invalide Year. ");
            }
            if(month<1||month>12)
            {
                throw new Exception("Invalid Month. ");
            }
            if(date<1||date>31)
            {
                throw new Exception("Invalide Day. ");
            }
            System.err.println(dt);
       }
       catch(Exception e)
       {
            FacesMessage message = new FacesMessage();
            message.setSummary(e.getMessage() + ".Input Date as yyyy-mm-dd");
            throw new ValidatorException(message);
       }
   
    }

}
