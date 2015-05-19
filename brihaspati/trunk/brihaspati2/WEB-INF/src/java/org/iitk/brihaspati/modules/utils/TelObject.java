package org.iitk.brihaspati.modules.utils;
/*
 * @(#)TelObject.java  
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

import java.util.List;
import java.io.IOException;
import org.apache.turbine.Turbine;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.apache.turbine.om.security.User;
import org.apache.turbine.util.parser.ParameterParser;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.torque.util.Criteria;
import org.apache.commons.lang.StringUtils;
import org.iitk.brihaspati.modules.utils.UserUtil;
import org.iitk.brihaspati.om.TelephoneDirectory;
import org.iitk.brihaspati.om.TelephoneDirectoryPeer;
import org.iitk.brihaspati.om.TurbineUser;
import org.iitk.brihaspati.om.TurbineUserPeer;
/**
 * @author <a href="mailto:seemanti05@gmail.com">Seemanti Shukla</a>
 */

//Create TelObject class so as to retrieve telobj data & set in "TELEPHONE_DIRECTORY".
public class TelObject {
     
   private String eMail;
   private String name="";
   private String address;
   private String state;
   private String country;
   private String department;
   private String designation;
   private String officeno;
   private String mobileno;
   private String homeno;
   private String otherno;
   private String offdirectory;
   private String mobdirectory;
   private String homedirectory;                                                
   private String othdirectory;   
   StringBuffer sb = new StringBuffer();
   public TelObject(String a,String c,String d,String e,String f,String g,String h,String i,String j,String k,String l,String m,String n,String o){
      //get runtime telephone data.  
      eMail = a;   
     // name= b;
      address= c;
      state = d;
      country = e;
      department= f;
      designation = g;
      officeno= h;
      mobileno= i;
      homeno= j;
      otherno= k;
      offdirectory= l;
      mobdirectory= m;
      homedirectory= n;
      othdirectory= o;
   }

   /* This method sets the value into"TELEPHONE_DIRECTORY" table.	
    * It updates the table "TELEPHONE_DIRECTORY" when the Telephone directory data changes else do not updates it. 
    */ 
   public boolean setTelObject(RunData data) throws Exception{
      User user=data.getUser();
      String loginName=user.getName();
      int uid=UserUtil.getUID(loginName);		

      List list=null;
      Criteria tel = new Criteria();
      tel.add(TurbineUserPeer.USER_ID,uid);
      list = TurbineUserPeer.doSelect(tel);
      //Get user FirstName,LastName.
      String admin_fname =((TurbineUser)list.get(0)).getFirstName();
      String admin_lname =((TurbineUser)list.get(0)).getLastName();
      name=admin_fname+admin_lname;
      List li=null;
      Criteria tele = new Criteria();
      tele.add(TelephoneDirectoryPeer.USER_ID,uid);
      li = TelephoneDirectoryPeer.doSelect(tele);
      if(li.size()==0){
         try {
            tele.add(TelephoneDirectoryPeer.MAIL_ID,eMail);
            tele.add(TelephoneDirectoryPeer.NAME, name);
            tele.add(TelephoneDirectoryPeer.ADDRESS, address);
            tele.add(TelephoneDirectoryPeer.STATE, state);
            tele.add(TelephoneDirectoryPeer.COUNTRY, country);
            tele.add(TelephoneDirectoryPeer.DEPARTMENT, department);
            tele.add(TelephoneDirectoryPeer.DESIGNATION, designation);
            if(offdirectory.equals("Public")){
               String PubOffNo="1-"+officeno;
               tele.add(TelephoneDirectoryPeer.OFFICE_NO, PubOffNo);
            } 
            else if(offdirectory.equals("Protected")){
               String ProOffNo="2-"+officeno;
               tele.add(TelephoneDirectoryPeer.OFFICE_NO, ProOffNo);
            } 
            else{
               String PriOffNo="3-"+officeno;
               tele.add(TelephoneDirectoryPeer.OFFICE_NO, PriOffNo);
            }
            if(mobdirectory.equals("Public")){
               String PubMobNo="1-"+mobileno;
               tele.add(TelephoneDirectoryPeer.MOBILE_NO, PubMobNo);
            } 
            else if(mobdirectory.equals("Protected")){
               String ProMobNo="2-"+mobileno;
               tele.add(TelephoneDirectoryPeer.MOBILE_NO, ProMobNo);
            }
            else{
               String PriMobNo="3-"+mobileno;
               tele.add(TelephoneDirectoryPeer.MOBILE_NO, PriMobNo);
           }
           if(homedirectory.equals("Public")){
              String PubHomeNo="1-"+homeno;
              tele.add(TelephoneDirectoryPeer.HOME_NO, PubHomeNo);
            } 
            else if(homedirectory.equals("Protected")){
              String ProHomeNo="2-"+homeno;
              tele.add(TelephoneDirectoryPeer.HOME_NO, ProHomeNo);
            } 
            else{
              String PriHomeNo="3-"+homeno;
              tele.add(TelephoneDirectoryPeer.HOME_NO, PriHomeNo);
            }
            if(othdirectory.equals("Public")){
               String PubOthNo="1-"+otherno;
               tele.add(TelephoneDirectoryPeer.OTHER_NO, PubOthNo);
            } 
            else if(othdirectory.equals("Protected")){
               String ProOthNo="2-"+otherno;
               tele.add(TelephoneDirectoryPeer.OTHER_NO, ProOthNo);
            } 
            else{
               String PriOthNo="3-"+otherno;
               tele.add(TelephoneDirectoryPeer.OTHER_NO, PriOthNo);
            }
            TelephoneDirectoryPeer.doInsert(tele);
            sb.append("Telephone Directory data updated successfully."+"\n");
            data.addMessage(sb.toString());
         }
         catch(Exception e){ 
            ErrorDumpUtil.ErrorLog("============= "+e.getMessage());
         }
      }

      else
      {
         TelephoneDirectory element=(TelephoneDirectory)(li.get(0));
         int id=(element.getId());
         tele.add(TelephoneDirectoryPeer.ID,id);
	 try {
            //Get all the Telephone Directory data from TELEPHONE_DIRECTORY table so as to compare them with Runtime Telephone Directory data.
            ErrorDumpUtil.ErrorLog("Hiii m here...........");
            String email =((TelephoneDirectory)li.get(0)).getMailId();
            String Name  =((TelephoneDirectory)li.get(0)).getName();
            String Address  =((TelephoneDirectory)li.get(0)).getAddress();
            String mobile_no  =((TelephoneDirectory)li.get(0)).getMobileNo();
            String other_no  =((TelephoneDirectory)li.get(0)).getOtherNo();
            String home_no  =((TelephoneDirectory)li.get(0)).getHomeNo();
            String office_no  =((TelephoneDirectory)li.get(0)).getOfficeNo();
            String Designation  =((TelephoneDirectory)li.get(0)).getDesignation();
            String Department  =((TelephoneDirectory)li.get(0)).getDepartment();
            String State  =((TelephoneDirectory)li.get(0)).getState();
            String Country =((TelephoneDirectory)li.get(0)).getCountry();
            String mob_dir=null,other_dir=null,home_dir=null,office_dir=null;
 
            int  limit  = 2;

            String[] mobile_no_parts = mobile_no.split("-", limit);
            String mobile_no_part1 = mobile_no_parts[0];
            String mobile_no_part2 = mobile_no_parts[1];
            ErrorDumpUtil.ErrorLog("mobile_no_parts[]........."+mobile_no_parts[1]);
            if(mobile_no_part1.equals("1"))
               mob_dir = "Public";
            else if(mobile_no_part1.equals("2"))
               mob_dir = "Protected";
            else mob_dir = "Private"; 
         

            String[] other_no_parts = other_no.split("-", limit);
            String other_no_part1 = other_no_parts[0];
            String other_no_part2 = other_no_parts[1];
            if(other_no_part1.equals("1"))
                  other_dir = "Public";
            else if(other_no_part1.equals("2"))
                  other_dir = "Protected";
            else other_dir = "Private";
 
 
            String[] home_no_parts = home_no.split("-", limit);
            String home_no_part1 = home_no_parts[0];
            String home_no_part2 = home_no_parts[1];
            if(home_no_part1.equals("1"))
                  home_dir = "Public";
            else if(home_no_part1.equals("2"))
                  home_dir = "Protected";
            else home_dir = "Private";
  
            String[] office_no_parts = office_no.split("-", limit);
            String office_no_part1 = office_no_parts[0];
            String office_no_part2 = office_no_parts[1];
            if(office_no_part1.equals("1"))
                  office_dir = "Public";
            else if(office_no_part1.equals("2"))
                  office_dir = "Protected";
            else office_dir = "Private";
     
         //If changed then set the updated values of Telephone Directory data in table else not.
         if( (!eMail.equals(email)) || (!name.equals(Name)) || (!address.equals(Address)) || (!mobdirectory.equals(mob_dir)) || (!othdirectory.equals(other_dir)) || (!homedirectory.equals(home_dir)) || (!offdirectory.equals(office_dir)) || (!mobileno.equals(mobile_no_part2)) || (!otherno.equals(other_no_part2)) || (!homeno.equals(home_no_part2)) || (!officeno.equals(office_no_part2)) || (!designation.equals(Designation)) || (!department.equals(Department)) || (!state.equals(State)) || (!country.equals(Country))  )
         {  
            tele.add(TelephoneDirectoryPeer.MAIL_ID,eMail);
            tele.add(TelephoneDirectoryPeer.NAME, name);
            tele.add(TelephoneDirectoryPeer.ADDRESS, address);
            tele.add(TelephoneDirectoryPeer.STATE, state);
            tele.add(TelephoneDirectoryPeer.COUNTRY, country);
            tele.add(TelephoneDirectoryPeer.DEPARTMENT, department);
            tele.add(TelephoneDirectoryPeer.DESIGNATION, designation);

            //FOR ALL THE DIRECTORIES (Office_no,mobile_no,home_no,other_no).
            //check for mode whether mode is public || private || protected.
            //Store no. in a string on the basis of mode selected by user.
            //Finally add this string in corresponding column of TelephoneDirectory table.
   
            if(offdirectory.equals("Public")){
               String PubOffNo="1-"+officeno;
               tele.add(TelephoneDirectoryPeer.OFFICE_NO, PubOffNo);
            } 
            else if(offdirectory.equals("Protected")){
               String ProOffNo="2-"+officeno;
	       tele.add(TelephoneDirectoryPeer.OFFICE_NO, ProOffNo);
            } 
            else {
               String PriOffNo="3-"+officeno;
               tele.add(TelephoneDirectoryPeer.OFFICE_NO, PriOffNo);
            }
       
            if(mobdirectory.equals("Public")){
               String PubMobNo="1-"+mobileno;                                 	
	       tele.add(TelephoneDirectoryPeer.MOBILE_NO, PubMobNo);                                      
            }  
            else if(mobdirectory.equals("Protected")){                              
               String ProMobNo="2-"+mobileno;	                                
               tele.add(TelephoneDirectoryPeer.MOBILE_NO, ProMobNo);       	                        
            } 
            else{
               String PriMobNo="3-"+mobileno;                  	                
               tele.add(TelephoneDirectoryPeer.MOBILE_NO, PriMobNo);                       	        
            }

            if(homedirectory.equals("Public")){
               String PubHomeNo="1-"+homeno;      
	       tele.add(TelephoneDirectoryPeer.HOME_NO, PubHomeNo);
            } 
            else if(homedirectory.equals("Protected")){	
	       String ProHomeNo="2-"+homeno;
               tele.add(TelephoneDirectoryPeer.HOME_NO, ProHomeNo);
            }
            else{
               String PriHomeNo="3-"+homeno;
               tele.add(TelephoneDirectoryPeer.HOME_NO, PriHomeNo);
            }

            if(othdirectory.equals("Public")){
               String PubOthNo="1-"+otherno;
               tele.add(TelephoneDirectoryPeer.OTHER_NO, PubOthNo);
            }
            else if(othdirectory.equals("Protected")){
               String ProOthNo="2-"+otherno;
               tele.add(TelephoneDirectoryPeer.OTHER_NO, ProOthNo);
            }
            else{
               String PriOthNo="3-"+otherno;
               tele.add(TelephoneDirectoryPeer.OTHER_NO, PriOthNo);
            }
            tele.addGroupByColumn(TelephoneDirectoryPeer.USER_ID);
            TelephoneDirectoryPeer.doUpdate(tele);
            sb.append("Telephone Directory data updated successfully."+"\n");
         }
         else sb.append("No change in Telephone Directory data."+"\n");
         data.addMessage(sb.toString());
      }//END OF TRY BLOCK
      catch(Exception e) {
         ErrorDumpUtil.ErrorLog("The exception in inserting Telephone data into TelephoneDirectory table!!!"+e);
      } 
   } 
      return true;
   }//End of setTelObject() method.

}//End of class.


