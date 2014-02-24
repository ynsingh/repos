<%-- 
    Document   : verifyfrombrihaspati
    Created on : Jul 21, 2010, 6:38:58 PM
    Author     :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
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
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
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
*   Modified Date: 17 feb Dec 2014, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
*
--%>

<%@page import="org.smvdu.payroll.beans.UserInfo" %>
<%@page import="org.smvdu.payroll.beans.db.UserDB"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@page import="javax.faces.view.facelets.FaceletContext"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="java.sql.Connection"%>
<%@page  import="org.iitk.brihaspati.modules.utils.security.ReadNWriteInTxt"%>
<%@page  import="org.iitk.brihaspati.modules.utils.security.RemoteAuth"%>
<%@page import="org.iitk.brihaspati.modules.utils.security.EncrptDecrpt"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    try{
        
            UserInfo user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
            String homeDir = System.getProperty("user.home");
            String path = homeDir +"/remote_auth/brihaspati3-remote-access.properties";
            String line=ReadNWriteInTxt.readLin(path,"smvdu_payroll");
            String skey=StringUtils.substringBetween(line,";",";");
            String rand=request.getParameter("rand");
            String hash=request.getParameter("hash");
            String encd=request.getParameter("encd");
            
            String enUrl1=EncrptDecrpt.decrypt(encd,"smvdu_payroll");
            
            String email1=StringUtils.substringBetween(enUrl1,"email=","&sess=");
            String sessno=StringUtils.substringAfter(enUrl1,"&sess=");


            String hashcode=EncrptDecrpt.keyedHash(email1, rand, skey);
            boolean matched=hashcode.equals(hash);
            if(matched){
                    String path1=homeDir+"/remote_auth/remote-user.txt";
                    boolean flag=ReadNWriteInTxt.readF(path1,email1+";"+sessno);
                    if(flag){
                        //System.out.println("I am verified");
                        int orgcode=new UserDB().getOrgcode(email1);
                        String pass= new UserDB().getpassword(email1);
                        //new UserInfo().remoteuservalidate(email1,pass,orgcode);
                        user.remoteuservalidate(email1, pass, orgcode);
                        
                    }       
            }
      
    }
    catch (Exception e) {
        //message = "Brihaspati Server seems to be unreachable or there is some other problem. Actual exception is: " + e.getMessage();
        //return "ERROR";
         }
    
%>
