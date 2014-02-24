<%-- 
    Document   : brihaspatiMainLogin
    Created on : Jun 26, 2012, 3:14:45 PM
    Author     :  *  Copyright (c) 2010 - 2011,2014 SMVDU, Katra.
*   All Rights Reserved.
*   Redistribution and use in source and binary forms, with or 
*   without modification, are permitted provided that the following 
*   conditions are met: 
**  Redistributions of source code must retain the above copyright 
*   notice, this  list of conditions and the following disclaimer. 
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
*  Modified Date: 17 feb Dec 2014, IITK (palseema@rediffmail.com, kshuklak@rediffmail.com)
*
 */
*

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <h:form>
                <h:panelGrid columns="1">
                    <%--
                    <rich:panel style="height:170px; width:1340px;">
                   <%    
                    out.println("&context="+request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
                   %>
                    </rich:panel>
                    --%>
                    <rich:panel style="height:170px; width:1340px;height:430px;">
                        <f:facet name="header">
                            <h:outputText value="Confirm Your Registration From Brihaspati Server" style="font-size:18px;font-weight:bold;"/>
                        </f:facet>
                        <rich:panel style="margin-left:35%;margin-right:35%;width:30%;margin-top:100px;height:100px;">
                            <h:panelGrid columns="3">
                                <h:outputText value="Email ID"/>
                                <h:inputText id="pass" value="#{brihaspatiBean.emailId}"/>
                                <h:commandButton value="Proceed" action="#{brihaspatiBean.brihaspatiAuth}" />
                                <h:messages/>
                            </h:panelGrid>
                        </rich:panel>
                    </rich:panel>
                </h:panelGrid>
            </h:form>
        </body>
    </html>
</f:view>