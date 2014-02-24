/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.api.Brihaspati;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.faces.context.ExternalContext;
import org.iitk.brihaspati.modules.utils.security.RemoteAuth;

/**
*
*  Copyright (c) 2010 - 2011,2014 SMVDU, Katra, IITKanpur.
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
*  Contributors: Members of ERP Team @ SMVDU, Katra,IITKanpur.
*  Modified Date: 17 Feb 2014, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
*
*/

public class BrihaspatiUniverAuth {

    /**
     * Creates a new instance of BrihaspatiUniverAuth
     */
    public BrihaspatiUniverAuth() {
    }

    public void brihaspatiAuth(BrihaspatiUniverAuthBean bua) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            System.out.println("Resp from seema-facesContext---: "+facesContext);
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            //stem.out.println("seema= : "+request.getContextPath()+"?email="+bua.getEmailId()+"&context="+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
            String paySourceId = "smvdu_payroll";
            //String resp = RemoteAuth.AuthR(bua.getEmailId(),request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/PayrollSys/adminLogin/brihaspatiMainLogin.jsf","smvdu_payroll");
             String resp = RemoteAuth.AuthR(bua.getEmailId(),request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/PayrollSys/adminLogin/verifyfrombrihaspati.jsf","smvdu_payroll");
             ExternalContext extContext = facesContext.getExternalContext(); 
             extContext.redirect(resp);
          
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   
    
}
