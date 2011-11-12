<%--
    Document        : AddUser.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 4:03 AM Saturday, October 02, 2010
    Author          :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Employee</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <script type="text/javascript">

            function activate()
            {
                var x = document.getElementById("editForm:empCode").value;
                if(x!="")
                {
                    document.getElementById("editForm:ldbtn").disabled=false;
                    document.getElementById("editForm:btnSave").disabled=false;
                    document.getElementById("editForm:btnReset").disabled=false;
                }
                else
                {
                    document.getElementById("editForm:ldbtn").disabled=true;
                    document.getElementById("editForm:btnSave").disabled=false;
                    document.getElementById("editForm:btnReset").disabled=false;
                }
            }

        </script>



    </head>
    <body class="subpage"  id="">
        <div class="container_form">
            <f:view>

                <hr class="line" />
                <h:form>
                    <rich:panel style="width:100%;" header="Edit Employee Profile" id="pnl">
                        <h:panelGrid columns="2">
                            <rich:messages  >
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                                <f:facet name="errorMarker">
                                    <h:graphicImage url="/img/err.png"/>
                                </f:facet>
                            </rich:messages>
                        </h:panelGrid>

                        <rich:panel>
                            <h:panelGrid columns="5">
                                <h:outputText  styleClass="Label" value="Employee Code"/>                                
                                <h:inputText onblur="activate()"  id="empCode"value="#{EmployeeBean.code}" styleClass="fields" required="true" requiredMessage="Enter Employee Code" immediate="true"/>
                                <rich:suggestionbox for="empCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                                    <h:column>
                                        <h:outputText value="#{abc.name}"/>
                                    </h:column>
                                    <h:column>
                                        <h:outputText value="#{abc.code}"/>
                                    </h:column>
                                </rich:suggestionbox>
                                <rich:toolTip value="Enter few characters of name and choose from List" for="empCode"/>
                                <a4j:commandButton id="ldbtn" styleClass="panel" action="#{EmployeeBean.loadProfile}" value="Select"/>
                                <a4j:commandButton value="Delete Employee"/>
                            </h:panelGrid>
                        </rich:panel>

                    </rich:panel>
                        
                </h:form>
            </f:view>

        </div>
    </body>
</html>
