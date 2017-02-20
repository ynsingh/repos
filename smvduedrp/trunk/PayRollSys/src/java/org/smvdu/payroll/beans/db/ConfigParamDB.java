/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.db;

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
*  Contributors: Members of ERP Team @ SMVDU, Katra, IITKanpur
*  @author  Manorama Pal (palseema30@gmail.com)
*/

import java.io.File;
import javax.faces.context.FacesContext;
import org.smvdu.payroll.beans.ConfigSalLiabilityXml;
import org.smvdu.payroll.beans.UserInfo;



public class ConfigParamDB{

    private UserInfo userBean; 
    private int orgCode;
    
    public ConfigParamDB() {
        
      userBean = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
      orgCode = userBean.getUserOrgCode();
      
        
    }
    
    /** Method for creating xml file
    * @see  ConfigureSalLiabilityXml
    * @param key (String)
    * @param value (String)
    * @return boolean 
    */
    
    public boolean salaryLiabilityDate(String key,String value)  {
        boolean flage=false;
        try
        {
            String filepath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/salary/SalaryConfiguration.xml");
            File f=new File(filepath);
            String currentDate = new CommonDB().getDate();
            if(f.exists()) {
                flage=ConfigSalLiabilityXml.getAttributeValueExist(filepath, key,Integer.toString(orgCode));
             
            }
            /** Add entry in the xml file if not exists
             * @see ConfigurationXml
            */
            if(flage==false){
                
                ConfigSalLiabilityXml.ConfigurationXml(filepath, key, value, currentDate, Integer.toString(orgCode));
                flage=true;
            }
            /** update entry in the xml file if exists
             * @see ConfigurationXml
            */
            else{
               
                ConfigSalLiabilityXml.UpdateEntry(filepath, key,value,currentDate,Integer.toString(orgCode));
                flage=false;
                
            }
         
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return flage;
       
    }
    
      
}
