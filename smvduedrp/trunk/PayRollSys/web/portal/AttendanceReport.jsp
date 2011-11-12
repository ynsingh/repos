<%-- 
    Document   : AttendanceReport
    Created on : Apr 2, 2011, 5:41:56 PM
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
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <f:view>


        <h:form>
            <h:outputText value="Empoyee Code"/>
            <h:outputText value="#{AttendanceReport.empCode}"/>
        </h:form>

        <rich:dataTable value="#{AttendanceReport.data}" var="dat" width="100%" >
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Sunday"/>
                </f:facet>
                <rich:panel style="background-color:grey">
                <h:panelGrid  columns="1" rendered="#{(dat[0]!=null)}">
                <h:outputText value="#{dat[0]}"/>
                <h:outputText value="#{dat[0].oginTime}"/>
                <h:outputText value="#{dat[0].ogoutTime}"/>
                </h:panelGrid>
                </rich:panel>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Monday"/>
                </f:facet>
                <h:panelGrid columns="1" rendered="#{(dat[1]!=null)}">
                <h:outputText value="#{dat[1]}"/>
                <h:outputText value="In : #{dat[1].oginTime}" rendered="#{!(dat[1].abscent||dat[1].forward)}"/>
                <h:outputText value="Out : #{dat[1].ogoutTime}" rendered="#{!(dat[1].abscent||dat[1].forward)}"/>
                <h:graphicImage url="../img/err.png" rendered="#{dat[1].abscent&&!dat[1].forward}"/>
                </h:panelGrid>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Tuesday"/>
                </f:facet>
                <h:panelGrid  columns="1" rendered="#{(dat[2]!=null)}">
                <h:outputText value="#{dat[2]}"/>
                <h:outputText value="In : #{dat[2].oginTime}" rendered="#{!(dat[2].abscent||dat[2].forward)}"/>
                <h:outputText value="Out : #{dat[2].ogoutTime}" rendered="#{!(dat[2].abscent||dat[2].forward)}"/>
                <h:graphicImage url="../img/err.png" rendered="#{dat[2].abscent&&!dat[2].forward}"/>
                </h:panelGrid>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Wednesday"/>
                </f:facet>
               <h:panelGrid columns="1" rendered="#{(dat[3]!=null)}">
                <h:outputText value="#{dat[3]}"/>
                <h:outputText value="In : #{dat[3].oginTime} " rendered="#{!(dat[3].abscent||dat[3].forward)}"/>
                <h:outputText value="Out : #{dat[3].ogoutTime}" rendered="#{!(dat[3].abscent||dat[3].forward)}"/>
                <h:graphicImage url="../img/err.png" rendered="#{dat[3].abscent&&!dat[3].forward}"/>
                
                </h:panelGrid>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Thursday"/>
                </f:facet>
                <h:panelGrid columns="1" rendered="#{(dat[4]!=null)}">
                <h:outputText value="#{dat[4]}"/>
                <h:outputText value="In : #{dat[4].oginTime}" rendered="#{!(dat[4].abscent||dat[4].forward)}"/>
                <h:outputText value="Out : #{dat[4].ogoutTime}" rendered="#{!(dat[4].abscent||dat[4].forward)}"/>
                <h:graphicImage url="../img/err.png" rendered="#{dat[4].abscent&&!dat[4].forward}"/>
                </h:panelGrid>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Friday"/>
                </f:facet>
                
                <h:panelGrid columns="1" rendered="#{(dat[5]!=null)}">
                <h:outputText value="#{dat[5]}"/>
                <h:outputText value="In : #{dat[5].oginTime}" rendered="#{!(dat[5].abscent||dat[5].forward)}"/>
                <h:outputText value="Out : #{dat[5].ogoutTime}" rendered="#{!(dat[5].abscent||dat[5].forward)}"/>
                <h:graphicImage style="align:center" url="../img/err.png" rendered="#{dat[5].abscent&&!dat[5].forward}"/>
                </h:panelGrid>
             
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Saturday"/>
                </f:facet>
               <h:panelGrid columns="1" rendered="#{(dat[6]!=null)}">
                <h:outputText value="#{dat[6]}"/>
                <h:outputText value="In : #{dat[6].oginTime}" rendered="#{!(dat[6].abscent||dat[6].forward)}"/>
                <h:outputText value="Out : #{dat[6].ogoutTime}" rendered="#{!(dat[6].abscent||dat[6].forward)}"/>
                <h:graphicImage url="../img/err.png" rendered="#{dat[6].abscent&&!dat[6].forward}"/>
                </h:panelGrid>
            </h:column>
             <f:facet name="footer">
                 <h:outputText value="Total Abscent : #{AttendanceReport.totalAbscent}"/>
             </f:facet>
                
            
        </rich:dataTable>
    </f:view>


</html>
