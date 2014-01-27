/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;


import java.util.ArrayList;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import org.smvdu.payroll.beans.db.SalaryProcessingSetupDB;
import org.smvdu.payroll.beans.setup.SalaryProcessingSetup;

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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IIT Kanpur.
*  @author <a href="mailto:kishore.shukla@gmail.com">Kishore Kumar Shukla</a>
*  @author <a href="mailto:palseema@rediffmail.com">Manorama Pal</a>
*
 */
public class SalaryProcessingSetupController {
    
    private ArrayList<SalaryProcessingSetup> salpromode;
    private UIData dataGrid;
    
    public SalaryProcessingSetupController()
    {
        salpromode = new SalaryProcessingSetupDB().activeSalaryprocessmode();
        for(SalaryProcessingSetup sps : salpromode)
        {
             //System.out.println("activeSalaryprocessmode: "+sps.getsalaryprocessmode());
             String salpromode=sps.getsalaryprocessmode();
             sps.setsalaryprocessmode(salpromode);
             
            //System.out.println("activeSalaryprocessmode:line63 "+sps.getsalaryprocessmode());
        }
    }
       
    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<SalaryProcessingSetup> getSalProMode() {
        return salpromode;
    }
    
    public void setSalProMode(ArrayList<SalaryProcessingSetup> salpromode) {
        this.salpromode = salpromode;
    }
   
    public void update()
    {
        try{
            
                FacesContext fc = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                ArrayList<SalaryProcessingSetup>data= new SalaryProcessingSetupDB().AllDetailSalaryProcessMode();
        
                Exception exm = new SalaryProcessingSetupDB().update(data);
        
                //System.out.println("setup updationexm==== : "+exm);
                if(exm == null)
                {
                        message.setSeverity(FacesMessage.SEVERITY_INFO);
                        message.setSummary("Salary processing setup updated sucessfully...");
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
