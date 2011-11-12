<%-- 
    Document   : DraftUploader
    Created on : May 27, 2011, 2:26:19 PM
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<f:view>

    <rich:panel>
         <h:form>
            <rich:fileUpload addControlLabel="Add NEW " fileUploadListener="#{DraftUpload.listener}"
                             id="uploasd"
                             immediateUpload="false"
                             acceptedTypes="jpg, gif, png, bmp" allowFlash="false">
                <a4j:support event="onuploadcomplete" reRender="logoimg" />
            </rich:fileUpload>
        </h:form>

        <rich:dataTable value="#{DraftUpload.drafts}" var="df">
            <h:column>
            <a4j:mediaOutput element="img" mimeType="img/png"
                             createContent="#{df.data}" value="#{row}"
                             style="width:140px; height:140px;" cacheable="true">
            </a4j:mediaOutput>
            </h:column>
            <rich:column>
                <f:facet name="header">
                    <h:outputText value="Tags"/>
                </f:facet>
                <h:outputText value="#{df.tags}"/>
            </rich:column>
            <rich:column>
                <f:facet name="header">
                    <h:outputText value="Date"/>
                </f:facet>
                <h:outputText value="#{df.date}"/>
            </rich:column>
        </rich:dataTable>

    </rich:panel>

    <rich:modalPanel id="pnl">
       
    </rich:modalPanel>
</f:view>
