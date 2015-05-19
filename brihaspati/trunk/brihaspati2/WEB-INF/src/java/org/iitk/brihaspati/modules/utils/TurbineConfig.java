package org.iitk.brihaspati.modules.utils;
/*
 * @(#)TurbineConfig.java  
 *
 *  Copyright (c) 2005,2009-2013 ETRG,IIT Kanpur. 
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or 
 *  without modification, are permitted provided that the following 
 *  conditions are met:
 * 
 *  Redistributions of source code must retain the above copyright  
 *  notice, this  list of conditions and the following disclaimer.
 * 
 *  Redistribution in binary form must reproducuce the above copyright 
 *  notice, this list of conditions and the following disclaimer in 
 *  the documentation and/or other materials provided with the 
 *  distribution.
 * 
 * 
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  Contributors: Members of ETRG, I.I.T. Kanpur 
 */

import java.io.IOException;
import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;

import org.iitk.brihaspati.modules.utils.AdminProperties;
import org.iitk.brihaspati.modules.utils.StringUtil;

/**
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 */

//create TurbineConfig class so as to retrieve  turbineconfig object.
public class TurbineConfig{

   private String fileupldsze;

   //constructor to retrieve TurbineConfig data at Runtime.
   public TurbineConfig(String a) {
      
      fileupldsze= a;
   }

   //Method to set TurbineConfig data in TurbineResource Properties File 
   public void setTurbineConfig(String TRpath,String path) {
      try {
         long bytUplodsze = Long.parseLong(fileupldsze)*1024*1024;//Calculates fileupldsze into bytes & stored in long type variable.
         AdminProperties.setTRValue(TRpath,Long.toString(bytUplodsze),"services.UploadService.size.max");
         AdminProperties.setPropertyValue(path,Long.toString(bytUplodsze),"services.UploadService.size.max");
         
      }
      catch(Exception e) {
      e.printStackTrace();
      }

   }
}
