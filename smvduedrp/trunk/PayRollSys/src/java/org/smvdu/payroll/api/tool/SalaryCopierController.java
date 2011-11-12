/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api.tool;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.UserInfo;

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
public class SalaryCopierController {

    private UserInfo user;
    private ArrayList<SalaryCopy> copies;



    private SelectItem[] copyProvider;

    public SelectItem[] getCopyProvider() {
        copies = new SalaryCopierDB().loadDates(2010);
        copyProvider= new SelectItem[copies.size()];
        SalaryCopy sc = null;
        for(int i=0;i<copies.size();i++)
        {
            sc= copies.get(i);
            SelectItem si = new SelectItem(sc.getRealDate(), sc.getDate());
            copyProvider[i] = si;
        }
        return copyProvider;
    }

    public void setCopyProvider(SelectItem[] copyProvider) {
        this.copyProvider = copyProvider;
    }



    public ArrayList<SalaryCopy> getCopies() {
        copies = new SalaryCopierDB().loadDates(2010);
        return copies;
    }

    public void setCopies(ArrayList<SalaryCopy> copies) {
        this.copies = copies;
    }


    private String current;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }



    public void copy()  {
        String sc = current;        
        System.out.println("Copy From : "+sc+" ,Copy To : "+user.getCurrentDate());
        Exception e = new SalaryCopierDB().copyData(current,user.getCurrentDate());
        FacesMessage msg = null;
        if(e==null)
        {
            msg = new  FacesMessage(FacesMessage.SEVERITY_INFO, "Data Copied Successfully", "");
        }
        else
        {
            msg = new  FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
        }
        FacesContext.getCurrentInstance().addMessage("", msg);
    }
    public SalaryCopierController()  {
        user = (UserInfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
    }

    
    private String copyTo;

   

    

    public String getCopyTo() {
        
        copyTo = user.getCurrentMonthName();
        System.out.println("Enter String Here + : "+user.getCurrentDate());
        return copyTo;
    }

    public void setCopyTo(String copyTo) {
        this.copyTo = copyTo;
    }

    

}
