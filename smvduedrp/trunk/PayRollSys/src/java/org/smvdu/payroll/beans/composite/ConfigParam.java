/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.io.File;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.UserInfo;
import org.smvdu.payroll.beans.db.ConfigParamDB;
import org.smvdu.payroll.beans.ConfigSalLiabilityXml;

/**
*  Copyright (c) 2010, 2011, 2014 SMVDU Katra.
*  Copyright (c) 2014 - 2017 ETRG, IITK.
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
* @author  Manorama Pal (palseema30@gmail.com)
*/

public class ConfigParam implements Serializable{
    
    
    private final int orgCode;
    private String key;
    private String value;
    private String dayvalue;
    
    private static final Logger LOG = Logger.getLogger(ConfigParam.class.getName());
    
    public ConfigParam(){
        UserInfo uf = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        orgCode = uf.getUserOrgCode();
        dayvalue=getDayNumberSuffix();
        
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }


    public String getDayValue() {
        return dayvalue;
    }
    public void setDayValue(String dayvalue) {
       this.dayvalue = dayvalue;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    /**Method for Add and update Entry in xml file of SalaryLiability creation
    * @see ConfigParamDB()
    */
    public void update(){   
        
    try{
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        key="SalaryLiability";
        boolean exm = new ConfigParamDB().salaryLiabilityDate(key,value);
            if(exm == true)
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Salary Liability date creation setup done successfully");
                fc.addMessage("", message);
                fc.getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("ConfigureSalLiability.jsf");
            }
            else
            {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Salary Liability date will be updated successfully");
                fc.addMessage("",message);
                fc.getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("ConfigureSalLiability.jsf");
            }
                
        }//try
        catch(Exception exm)
        {
            exm.printStackTrace();
        }
    }  
     
    /**Method for Day number Suffix
     * @return String
     */ 
     
    public String getDayNumberSuffix(){
        int day=0;
        String filepath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/salary/SalaryConfiguration.xml");
        File f=new File(filepath);
        if(f.exists()){
            value=ConfigSalLiabilityXml.getKeyValue(filepath,"SalaryLiability",Integer.toString(orgCode));
            if(value !=null){
                String[] parts = value.split("-");
            
                day =Integer.parseInt(parts[2]);
            }
            
        }
        try{
            switch (day){
                case 0:
                    return null;
                case 1:
                case 21:
                case 31:
                    return String.valueOf(day)+"st";
                    
                case 2:
                case 22:
                    return String.valueOf(day)+"nd";
                    

                case 3:
                case 23:
                    return String.valueOf(day)+"rd";
                    

                default:
                    return String.valueOf(day)+"th";
                    
            }
           
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
         
}
