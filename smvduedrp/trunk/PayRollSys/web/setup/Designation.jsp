<%--
    Document        : Designation.jsp
    Created on      : 3:02 AM Friday, October 01, 2010
    Last Modified   : 4:03 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
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
        <title>Designation</title>
     </head>
    <body class="subpage" id="">
        <div class="container_form">
            <f:view>
                <rich:panel header="Existing Designations">
                    <div align="right" > 
                        <a4j:commandLink   onclick="javascript:window.print();" style="margin-right:10px;">
                        <h:graphicImage value="/img/Printer-icon.png" alt="Print"  /> 
                        </a4j:commandLink>
                        <a4j:commandLink ajaxSingle="true" reRender="helppnl" onclick="Richfaces.showModalPanel('hnl');" >
                        <h:graphicImage value="/img/help-icon.png" alt="Help" /> 
                        </a4j:commandLink>
                    </div>
                    <h:panelGrid columns="3">
                        <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                        <h:commandButton onclick="Richfaces.showModalPanel('dnl');" value="Upload Designation List"/><br/>
                        <rich:messages  >
                            <f:facet name="infoMarker">
                                <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                                <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                    </h:panelGrid>
                    <h:form >
                        <h:panelGrid  id="desigtbl" columns="2" style="width:100%;">
                            <rich:dataTable id="designation"  binding="#{DesignationControllerBean.dataGrid}" 
                                              value="#{DesignationControllerBean.designations}" 
                                             var="desig"  rowKeyVar="row"  rows="20" style="width:100%;">
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText  value="Designation Code"/>
                                    </f:facet>
                                    <rich:inplaceInput value="#{desig.dcode}" />
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText  value=" Designation Name"/>
                                    </f:facet>
                                    <rich:inplaceInput value="#{desig.name}" />
                                </h:column>
                                <h:column>
                                    <f:facet name="header">
                                        <h:outputText  value="Designation Nick Name"/>
                                    </f:facet>
                                    <rich:inplaceInput value="#{desig.nickName}" />
                                </h:column>
                                <f:facet name="footer">
                                <rich:datascroller for="designation" page="20"/>  
                            </f:facet>
                            </rich:dataTable>
                        </h:panelGrid>
                        <h:panelGrid columns="2">
                        <a4j:commandButton  reRender="desigtbl"  value="Update" action="#{DesignationControllerBean.update}"/>
                        </h:panelGrid>
                    </h:form>
                </rich:panel>
                <rich:modalPanel id="pnl">
                        
                           <f:facet name="controls">
                             <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('pnl')" />
                           </f:facet>
                    
                        <h:form>                                                                                
                           <rich:panel header="Add New Designation">
                           <h:panelGrid columns="3">
                           <h:outputText value="Designation Code"/>
                           <h:inputText id="desigCode" required="true" requiredMessage="Please Enter Designation Code" value="#{DesignationBean.dcode}"/>
                           <h:message styleClass="error" for="desigCode" tooltip="Employee Type"/>
                           <h:outputText value="Designation Name"/>
                           <h:inputText id="desigName" required="true" requiredMessage="Please Enter New Designation Name" value="#{DesignationBean.name}"/>
                           <h:message for="desigName" styleClass="error"/>
                           <h:outputText value="Designation Nick Name"/>
                           <h:inputText id="nickName" required="true" requiredMessage="Please Enter Designation NickName" value="#{DesignationBean.nickName}"/>
                           <h:message styleClass="error" for="nickName" tooltip="*"/>
                           </h:panelGrid>
                          </rich:panel>
                           <a4j:commandButton value="Save"  action="#{DesignationBean.save}"  reRender="designation" oncomplete="#{rich:component('pnl')}.hide();"/>
                           <a4j:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                        </h:form>
                    </rich:modalPanel>
                   <rich:modalPanel  width="300" height="240" autosized="true" id="dnl">
                
                   <%--file upload for departments---------------------- --%>
                   <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('dnl')" />
                    </f:facet>
                    <h:form>
                        <h:panelGrid columns="2" columnClasses="top,top">
                            <rich:fileUpload fileUploadListener="#{DesignationBean.listener}"
                            maxFilesQuantity="#{FileUploadBean.uploadsAvailable}"
                            id="upload"
                            immediateUpload="#{FileUploadBean.autoUpload}"
                            acceptedTypes="csv" allowFlash="#{FileUploadBean.useFlash}">
                            <a4j:support event="onuploadcomplete" reRender="designation"/>
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

                    <h:outputText  style="font-size:1.5em;font-weight:bold;" value=" Designation Code  	   Designation Name         Designation Nick Name"/>
                    <h:outputText style="font-size:1.5em;" value="Example: "/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Designation Code = Pm03"/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Designation Name = Project Manager"/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Designation Nick Name = PM "/>
                    <h:outputText style="font-size:1.5em;"  value="3. Save as csv format ."/>
                               
                    </h:panelGrid>
                    </rich:panel>
                    <h:commandButton value="Close" onclick="#{rich:component('hnl')}.hide(); return false;" />
                </h:form>
                </rich:modalPanel>        
             
            </f:view>
        </div>
    </body>
</html>
