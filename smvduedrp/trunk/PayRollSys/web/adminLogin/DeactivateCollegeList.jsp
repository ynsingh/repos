<%-- 
    Document   : DeactivateCollegeList
    Created on : Aug 3, 2016, 2:34:52 PM

*  Copyright (c) 2010 - 2011.2014 SMVDU, Katra.
*  Copyright (c) 2014 - 2016 ETRG, IITK.
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
*    Author : Om Prakash (omprakashkgp@gmail.com), IITK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE html>
<html>
    <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <f:view>
    <body>
       <a4j:region>
           <rich:panel header="List Of Deactivate College/Institute's" style="font-size:17px;font-weight:bold;width:1290px;height:380px;">
            <div align="right" >                                            
                <a4j:commandLink ajaxSingle="true" reRender="sListh" onclick="Richfaces.showModalPanel('sListhelp');">
                   <h:graphicImage id="hl" value="/img/help-icon.png" alt="Help"></h:graphicImage><rich:toolTip for="lh" value="Help"></rich:toolTip>
                 </a4j:commandLink>
               </div>
               <h:form>
                   <rich:panel style="margin-left:100px;margin-right:100px;border:none;" >
                   <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                   </rich:messages>
                       <rich:dataTable id="upda3" style="font-size:14px;font-weight:bold;width:1050px;" rowKeyVar="row" rows="7" value="#{OrgProfileBean.deactivateList}" binding="#{OrgProfileBean.dataGrid9}"  var="ins">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="S.No"/>
                                </f:facet> 
                                <h:outputText value="#{ins.srNo}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Institute Name"/>
                                </f:facet>
                                <h:outputText value="#{ins.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Web Site"/>
                                </f:facet>
                                <h:outputText value="#{ins.web}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Email"/>
                                </f:facet>
                                <h:outputText value="#{ins.email}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Phone No."/>
                                </f:facet>
                                <h:outputText value="#{ins.phone}"/>
                            </h:column>    
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Check/Uncheck"/>
                                </f:facet>
                                <h:selectBooleanCheckbox value="#{ins.status}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Status"/>
                                </f:facet>
                                 <h:graphicImage value="/img/#{ins.imgUrl}"/>
                            </h:column>
                            
                        </rich:dataTable> 
                        <rich:panel>
                            <a4j:commandButton reRender="#{OrgProfileBean.deactivateList}, upda3 " value="Activate" action="#{OrgProfileBean.updateDeactivate}"/> &nbsp;
                            <a4j:commandButton reRender="#{OrgProfileBean.deactivateList}, upda3 " value="Delete" action="#{OrgProfileBean.deleteDeactivate}"/>
                        </rich:panel>
                   </rich:panel>
               </h:form>
           </rich:panel>
       </a4j:region>  

             <rich:modalPanel id="sListhelp" autosized="true" domElementAttachment="parent" width="600" height="400">
                   <f:facet name="controls">
                        <h:graphicImage value="/img/close1.png" style="cursor:pointer"  
                                              onclick="Richfaces.hideModalPanel('sListhelp');" />
                         </f:facet>
                             <h:form>
                              <rich:panel header="Help" >
                                    <h:panelGrid id="sListh">
                                    <h:outputText style="font-size:1.5em;font-weight:bold;" value="Instruction for Accept, Deactivate, Delete College/Institute's. "/>    
                                    <h:outputText style="font-size:14px;" value=" In Institute Management link, there are three link. "/>
                                    <h:outputText style="font-size:14px;font-weight:bold;" value=" 1. Check Pending College/Institute's "/>
                                    <h:outputText style="font-size:14px;" value=" From this link you can accept the pending College/Institute . Check the Checkbox and click on update button. "/>
                                    <h:outputText style="font-size:14px;font-weight:bold;" value=" 2. Check Registred College/Institute's "/>
                                    <h:outputText style="font-size:14px;" value=" From this link you can Deactivate College/Institute. "/>
                                    <h:outputText style="font-size:14px;" value=" uncheck the checkbox and click on Deactivate button "/>
                                    <h:outputText style="font-size:14px;font-weight:bold;" value=" 3. Delete/Activate College/Institute's "/>
                                    <h:outputText style="font-size:14px;" value=" From this link you can Delete and Activate the College/Institute. "/>
                                    <h:outputText style="font-size:14px;" value=" Check the checkbox and click on delete button for delete College/Institute's."/>
                                    <h:outputText style="font-size:14px;" value=" Check the checkbox and click on activate button for activate College/Institute's. "/>
                                    <h:outputText style="font-size:14px;font-weight:bold;" value=" Change Password "/>
                                                  <h:outputText style="font-size:14px;" value=" Click on Change Password button. Now insert new Password and click on Submit button. "/>
                                    <h:outputText style="font-size:14px;" value=" If you want to modify the name of institute click on institute name and change the name after that click on Update button. "/>
                                    </h:panelGrid>
                               </rich:panel>
                             <h:commandButton value="Close" onclick="#{rich:component('sListhelp')}.hide(); return false;" />
                           </h:form>
           </rich:modalPanel>
    </body>
    </f:view>
</html>
