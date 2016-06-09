/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.composite.SessionController;
import org.smvdu.payroll.beans.db.TaxCalculationTypeDB;

/**
*  Copyright (c) 2010, 2011, 2014 SMVDU Katra.
*  Copyright (c) 2015, 2016 ETRG, IITK.
*  All Rights Reserved.
** Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
** Redistributions of source code must retain the above copyright 
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
* @author shikhar, Manorama Pal
*/
public class TaxCalculationType implements Serializable{
    private String calcType;
    private int orgCode;
    private int session;
    //private HtmlDataTable htmlDataTable;
    SessionController sessionId = new SessionController();
    
    public TaxCalculationType()
    {
        UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = uf.getUserOrgCode();
        session=sessionId.getCurrentSession();
        calcType=new TaxCalculationTypeDB().TaxCalculationMode();
                
    }
    public String getCalcType(){
       return calcType;
    }

    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }
    
   
    public int getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(int orgCode) {
        this.orgCode = orgCode;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }
   /* public void setSelected(ValueChangeEvent event) {
       TaxCalculationType calsettype = (TaxCalculationType) htmlDataTable.getRowData();
       System.out.println("taccalctype=calsettype==="+calsettype);
    }   
    public void setSelected(ValueChangeEvent event) {
        employee = (Employee) htmlDataTable.getRowData();
        list = new ArrayList<employee>();
	list.add(employee);
    }*/
    
    public void update(){
        
    try{
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        Exception exm = new TaxCalculationTypeDB().update(this);
            if(exm == null)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Tax Calculation Type updated sucessfully...");
                fc.addMessage("", message);
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                message.setSummary("Setup Not Updated.....PleaseTry Again");
                fc.addMessage(""+exm , message);
            }
                
        }//try
        catch(Exception exm)
        {
            exm.printStackTrace();
        }
    }   
  
}
