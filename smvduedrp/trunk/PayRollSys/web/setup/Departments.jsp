<%--
    Document   : DefaultSalaryData
    Created on : Dec 24, 2010, 11:37:17 AM
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

<%@page import="org.smvdu.payroll.beans.upload.FileUploadBean" %>
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
        <style>
.top {
    vertical-align: top;
    
}
.info {
    height: 202px;
    overflow: auto;
}
</style>

    </head>
    <body>
        <f:view>
               <rich:panel header="Existing Departments">
               <div align="right" >                                            
                <a4j:commandLink ajaxSingle="true" reRender="helppnl" onclick="Richfaces.showModalPanel('hnl');" >
                <h:graphicImage value="/img/help-icon.png" alt="Help" /> 
                </a4j:commandLink>
                 </div>
                 <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                 <h:commandButton onclick="Richfaces.showModalPanel('dnl');" value="Upload Deptarment List"/><br/>
                 </div>
                 <rich:separator  style="width:100%;" /><br/>
                 <h:outputText style="font-size:1em;font-color:green;" value="Total Department   : #{DepartmentControllerBean.total}"/>
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
                <h:form id="deptForm"> 
                  <%--  <rich:panel>--%>
                    <h:panelGrid id="deptlist" style="width:100%;">  
                    <rich:dataTable id="tbl" binding="#{DepartmentControllerBean.dataGrid}" 
                                        value="#{DepartmentControllerBean.departments}" var="dept" rowKeyVar="row"  rows="10" style="width:100%;">
                        
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Department Code"/>
                            </f:facet>
                            <rich:inplaceInput value="#{dept.DCode}" />
                        </h:column>  
                        <h:column >
                            <f:facet name="header">
                                <h:outputText value="Department Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{dept.name}" />
                        </h:column>                       
                         <h:column >
                            <f:facet name="header">
                                <h:outputText value="Department Nick Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{dept.nickName}" />
                        </h:column> 
                        <f:facet name="footer">
                                <rich:datascroller for="tbl" page="1"/>  
                            </f:facet>
                    </rich:dataTable>
                    </h:panelGrid>
                    <h:panelGrid columns="2">
                        <a4j:commandButton value="Update" reRender="tbl" action="#{DepartmentControllerBean.update}"/>                    
                    </h:panelGrid>
                   <%-- </rich:panel>--%>
                </h:form>
            </rich:panel>
            <rich:modalPanel width="300" autosized="true" id="pnl">
                <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('pnl')" />
                </f:facet>
                
                <h:form>
                    <rich:panel header="Add New Department">
                    <h:panelGrid columns="3">
                    <h:outputText value="Department Code"/>
                    <h:inputText id="deptCode" required="true" requiredMessage="Please Enter Department Code" value="#{DepartmentBean.DCode}"/>
                    <h:message styleClass="error" for="deptCode" tooltip="Employee Type"/>
                    <h:outputText value="Department Name"/>
                    <h:inputText id="deptName" required="true" requiredMessage="Please Enter Department Name" value="#{DepartmentBean.name}"/>
                    <h:message styleClass="error" for="deptName" tooltip="*"/>
                    <h:outputText value="Department Nick Name"/>
                    <h:inputText id="nickName" required="true" requiredMessage="Please Enter Department NickName" value="#{DepartmentBean.nickName}"/>
                    <h:message styleClass="error" for="nickName" tooltip="*"/>
                    </h:panelGrid>
                    </rich:panel>
                    <a4j:commandButton value="Save" action="#{DepartmentBean.save}" reRender="deptForm,tbl" oncomplete="#{rich:component('pnl')}.hide();" />
                    <a4j:support event="oncomplete" reRender="tbl"/>
                    <h:commandButton value="Close" onclick="#{rich:component('pnl')}.hide(); return false;" />
                </h:form>
                </rich:modalPanel>
                <rich:modalPanel height="240" autosized="true" id="dnl">
                
                <%--file upload for departments---------------------- --%>
                <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('dnl')" />
                </f:facet>
               <h:form>
              <h:panelGrid columns="2" columnClasses="top,top">
                  <rich:fileUpload fileUploadListener="#{DepartmentBean.listener}"
                maxFilesQuantity="#{FileUploadBean.uploadsAvailable}"
                id="upload"
                immediateUpload="#{FileUploadBean.autoUpload}"
                acceptedTypes="csv" allowFlash="#{FileUploadBean.useFlash}">
                      <a4j:support event="onuploadcomplete" reRender="tbl"/>
                  </rich:fileUpload>
               
                 </h:panelGrid>
                </h:form>
                
           <%---file upload END================================= --%>     
                </rich:modalPanel>
                 
                 
               <rich:modalPanel id="hnl" autosized="true" domElementAttachment="parent" width="700" height="400">
               <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('hnl')" />
                </f:facet>
                
                <h:form>
                    <rich:panel header="Help">
                    <h:panelGrid  id="helppnl">
                        <h:outputText style="font-size:1.5em;" value="Instruction for upload a csv file."/>

                    <h:outputText style="font-size:1.5em;" value=" 1. Open LibreOffice Calc in ubuntu and Excel in windows."/>

                    <h:outputText style="font-size:1.5em;" value=" 2. The file should contain three field i.e"/>

                    <h:outputText  style="font-size:1.5em;font-weight:bold;" value=" Department Code  	Department Name        Department Nick Name"/>
                    <h:outputText style="font-size:1.5em;" value="Example: "/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Department Code = EE03"/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Department Name =Electrical Engineering"/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Department Nick Name = EE "/>
                    <h:outputText style="font-size:1.5em;"  value="3. Save as csv format."/>
                               
                    </h:panelGrid>
                    </rich:panel>
                    <h:commandButton value="Close" onclick="#{rich:component('hnl')}.hide(); return false;" />
                </h:form>
                </rich:modalPanel>
        </f:view>
    </body>
</html>
