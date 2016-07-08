<%-- 
    Document   : ServerList
    Created on : Jan 3, 2013, 1:16:59 AM
    Author     : KESU
GUI Modified date 21 July 2015, IITK , Om Prakash (omprakashkgp@gmail.com)
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE html>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
            <link type="text/css" rel="stylesheet" href="../JQuery/themes/base/jquery-ui.css"/>
    
       </head>
        <body>
            <rich:panel style="height:320px; width:1290px" header="SMTP Configuration">
                <div align="right" >                                            
                    <a4j:commandLink ajaxSingle="true" reRender="smtph" onclick="Richfaces.showModalPanel('smtphelp');">
                        <h:graphicImage id="hl" value="/img/help-icon.png" alt="Help"></h:graphicImage><rich:toolTip for="lh" value="Help"></rich:toolTip>
                    </a4j:commandLink>
                </div>
                <h:panelGrid columns="1" id="smtpd" > 
                  <a4j:commandButton value="Add New SMTP Configuration" onclick="Richfaces.showModalPanel('adnew');" ></a4j:commandButton> 
                    
                           <rich:panel style="border:none;margin-left:100px;margin-right:100px;" >
                            <rich:messages>
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                            </rich:messages>
                               <h:form id="smtpform">
                                 <rich:dataTable id="si" style="font-size:14px; width:1050px;"  rows="9" value="#{SmtpConfigBean.smtpDetails}" binding="#{SmtpConfigBean.dataGrid7}"  var="sm">
                                <h:column >
                                    <f:facet name="header" >
                                        <h:outputText value="SMTP Name" />
                                    </f:facet>
                                    <rich:inplaceInput value="#{sm.hostName}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=" SMTP Port "/>
                                    </f:facet>
                                    <rich:inplaceInput value="#{sm.smtpPort}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=" From / Authentication ID "/>
                                    </f:facet>
                                    <h:inputText value="#{sm.fromEmailId}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=" Password "/>
                                    </f:facet>
                                    <h:inputSecret value="#{sm.fromPassword}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                         <h:outputText value="Mail From"/>
                                    </f:facet>
                                   <h:inputText value="#{sm.mailfrom}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=" Check / Uncheck "/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox value="#{sm.smtpStatus}"/>
                                </h:column>
                                <h:column>
                                  <f:facet name="header" >
                                        <h:outputText value="  "/>
                                  </f:facet>

                                    <h:commandButton value="Update" action="#{SmtpConfigBean.updateAdminSMTP}" />
                                </h:column>
                            <%--   <f:facet name="footer">
                          <rich:datascroller for="si" page="5" />
                        </f:facet>--%>
                            </rich:dataTable>
                             <%--  <rich:panel> 
                            <a4j:commandButton value="Update" reRender="si, smtpd" action="#{OrgProfileBean.updateAdminSMTP}"/>
                           </rich:panel> --%>
                         </h:form> 
                         </rich:panel>
                    </h:panelGrid>
            </rich:panel>
            
    <%--        <rich:tabPanel style="border:none;height:320px;width:1290px"> 
                <%--    <rich:tab label="IP List">
                    <rich:panel>
                        <h:form>
                            <h:dataTable headerClass="headerStyle" rowClasses="rowStyle" style="font-size:14px;font-weight:bold;" id="upda" rows="20"  value="#{OrgProfileBean.serverDetails}" binding="#{OrgProfileBean.dataGrid4}"  var="ins"> 
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=""/>
                                    </f:facet>
                                    <h:inputHidden value="#{ins.id}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Institute Name"/>
                                    </f:facet>
                                    <h:outputText value="#{ins.name}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Admin Email ID"/>
                                    </f:facet>
                                    <h:outputText value="#{ins.email}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Web Site"/>
                                    </f:facet>
                                    <h:outputText value="#{ins.web}"/> 
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="IP Address"/>
                                    </f:facet>
                                    <h:outputText value="#{ins.ipAddress}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Port"/>
                                    </f:facet>
                                    <h:outputText value="#{ins.port}"/>
                                </h:column>
                            </h:dataTable>
                            <br>
                            <br>
                            <rich:separator/>
                        </h:form>
                    </rich:panel>
                </rich:tab>--%>
                            <%--  <rich:tab label="Block/Unblock IP">
                    <rich:panel>
                        <rich:messages>
                            <f:facet name="infoMarker">
                                <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                        </rich:messages>
                        <h:form>
                            <h:dataTable headerClass="headerStyle" rowClasses="rowStyle" style="font-size:14px;font-weight:bold;" rows="20"  value="#{OrgProfileBean.serverIpDetails}" binding="#{OrgProfileBean.dataGrid5}"  var="ip">
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="IP Address"/>
                                    </f:facet>
                                    <h:outputText value="#{ip.ipAddress}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Port"/>
                                    </f:facet>
                                    <h:outputText value="#{ip.port}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value="Check/Uncheck"/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox value="#{ip.ipStatus}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=""/>
                                    </f:facet>
                                    <a4j:commandButton value="Update" action="#{OrgProfileBean.updateIpList}"/>
                                </h:column>
                            </h:dataTable>
                        </h:form>
                    </rich:panel>
                </rich:tab> --%>
 <%--         <rich:tab label="SMTP Configuration" > 
                    <h:panelGrid columns="1" id="smd" style="margin-left:100px;margin-right:100px;" >
                        <a4j:commandButton onclick="Richfaces.showModalPanel('adnew');" value="Add New SMTP Configration"/>
                        <rich:panel style="border:none;" >
                            <rich:messages>
                                <f:facet name="infoMarker">
                                    <h:graphicImage url="/img/success.png"/>
                                </f:facet>
                            </rich:messages>
                            <%-- </rich:panel> --%>
 <%--                       <h:form>
                            <rich:dataTable style="font-size:14px; width:1050px;"  rows="9" value="#{OrgProfileBean.smtpDetails}" binding="#{OrgProfileBean.dataGrid7}"  var="sm">
                                <h:column >
                                    <f:facet name="header" >
                                        <h:outputText value="SMTP Name" />
                                    </f:facet>
                                    <h:outputText value="#{sm.hostName}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=" Server Name "/>
                                    </f:facet>
                                    <h:outputText value="#{sm.smtpServerName}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=" SMTP Port "/>
                                    </f:facet>
                                    <h:outputText value="#{sm.smtpPort}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=" From / Authentication ID "/>
                                    </f:facet>
                                    <h:outputText value="#{sm.fromEmailId}"/>
                                </h:column>
                                <h:column>
                                    <f:facet name="header" >
                                        <h:outputText value=" Check / Uncheck "/>
                                    </f:facet>
                                    <h:selectBooleanCheckbox value="#{sm.smtpStatus}"/>
                                </h:column>
                            </rich:dataTable>
                            <rich:panel>
                            <a4j:commandButton value="Update" reRender="smd" action="#{OrgProfileBean.updateAdminSMTP}"/>
                            </rich:panel>
                        </h:form> 
                        </rich:panel> --%>
                        <rich:modalPanel label="Add New Server" id="adnew" width="340" height="240">
                           <f:facet name="header">
                            <h:panelGroup>
                                <h:outputText value="Add New SMTP Configuration"></h:outputText>
                            </h:panelGroup>
                            </f:facet>
                           <f:facet name="controls">
                            <h:panelGroup>
                             <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                             <rich:componentControl for="adnew" attachTo="hidelink" operation="hide" event="onclick"/>
                            </h:panelGroup>
                           </f:facet>
                            <h:panelGrid columns="1" id="op">
                                <h:form>
                                    <rich:panel style="border:none" >
                                        <rich:messages>
                                            <f:facet name="infoMarker">
                                                <h:graphicImage url="/img/success.png"/>
                                            </f:facet>
                                            <f:facet name="errorMarker">
                                                <h:graphicImage url="/img/err.png"/>
                                            </f:facet>
                                        </rich:messages>
                                    </rich:panel>
                                    <rich:panel>
                                        <h:panelGrid columns="2">
                                            <h:outputText value="SMTP Name"/>
                                            <h:inputText requiredMessage="Please Enter the SMTP name " required="true" value="#{SmtpConfigBean.hostName}"/>
                                            <%-- <h:outputText value="Host Name"/>
                                            <h:inputText required="true" requiredMessage="Please Enter the Host Name" value="#{OrgProfileBean.smtpServerName}"/>--%>
                                            <h:outputText value="SMTP Port"/>
                                            <h:inputText required="true" value="#{SmtpConfigBean.smtpPort}"/>
                                            <h:outputText value="From / Authentication ID"/>
                                            <h:inputText value="#{SmtpConfigBean.fromEmailId}"/>
                                            <h:outputText value="Password"/>
                                            <h:inputSecret value="#{SmtpConfigBean.fromPassword}"/>
                                            <h:outputText value="Mail From"/>
                                            <h:inputText id="mailfrom" value="#{SmtpConfigBean.mailfrom}" required="true" requiredMessage="Enter email (e.g. om@gmail.com)" validatorMessage=" Invalid email format ">
                                                <f:validateRegex pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/>
                                            </h:inputText> 
                                            </h:panelGrid>
                                            <h:panelGrid columns="3">
                                               <a4j:commandButton value="Save"  action="#{SmtpConfigBean.saveSMTPDetails}" reRender="#{SmtpConfigBean.smtpDetails}, si, smtpd" oncomplete="#{rich:component('adnew')}.hide();" /> &nbsp;
                                               <a4j:commandButton onclick="Richfaces.hideModalPanel('adnew');" value="Close"/>
                                            </h:panelGrid>
                                    </rich:panel>
                                </h:form>
                            </h:panelGrid>
                        </rich:modalPanel>
                        
                        <rich:modalPanel id="smtphelp" autosized="true" domElementAttachment="parent" width="475" height="375">
                           <f:facet name="controls">
                                <h:graphicImage value="/img/close1.png" style="cursor:pointer"  
                                                onclick="Richfaces.hideModalPanel('smtphelp');" />
                                </f:facet>
                                <h:form>
                                <rich:panel header="Help" >
                                    <h:panelGrid id="smtph">
                                    <h:outputText style="font-size:1.5em;font-weight:bold;" value="Instruction for SMTP Configuration"/>    
                                    <h:outputText style="font-size:1.4;" value=" SMTP Name "/>
                                    <h:outputText style="font-size:1.4;font-weight:bold" value="Example : smtp.cc.iitk.ac.in"/>
                                    <h:outputText style="font-size:1.4;" value=" SMTP Port"/>
                                    <h:outputText style="font-size:1.4;font-weight:bold" value="Example : 25"/>
                                    <h:outputText style="font-size:1.4;" value=" From / Authentication ID"/>
                                    <h:outputText style="font-size:1.4;font-weight:bold" value="Example : mprakash"/>
                                    <h:outputText style="font-size:1.4;" value=" Password"/>
                                    <h:outputText style="font-size:1.4;font-weight:bold" value="Example : ********"/>
                                    <h:outputText style="font-size:1.4;" value=" Mail From"/>
                                    <h:outputText style="font-size:1.4;font-weight:bold" value="Example : mprakash@iitk.ac.in"/>
                                    <h:outputText style="font-size:1.4;" value="Now press the save button for insert the value in database"/>
                                    </h:panelGrid>
                                </rich:panel>
                                <h:commandButton value="Close" onclick="#{rich:component('smtphelp')}.hide(); return false;" />
                                </h:form>
                           </rich:modalPanel>
                  <%--  </h:panelGrid> 
                 </rich:tab>
           </rich:tabPanel> --%>
       </body>
    </html>
</f:view>