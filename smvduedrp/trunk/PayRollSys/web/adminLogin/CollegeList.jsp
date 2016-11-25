    <%-- 
    Document   : AdminIframe
    Created on : Dec 13, 2012, 9:13:24 PM
    Author     : KESU
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
*  GUI Modified date 21 July 2015, Om Prakash (omprakashkgp@gmail.com), IITK
*  Modified : 31 August 2016
*  Last Modification : November, 2016, Om Prakash 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>

    <html>
        <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        <f:view>
           <script type="text/javaScript">
                
                function loadIframe(iframeName, url) {
                    
                    parent.document.getElementById('ifrm').src = url;
                    if ( window.frames[iframeName] ) {
                        window.frames[iframeName].location = url;
                        return false;
                    }
                    return true;
                }
                // assign new url to iframe element's src property
            </script> 
        <body>
           
            <a4j:region>
                <rich:panel header="List Of Registered College/Institute's" style="font-size:17px;font-weight:bold;width:1290px;height:380px;">
                    <div align="right" >                                            
                    <a4j:commandLink ajaxSingle="true" reRender="sListh" onclick="Richfaces.showModalPanel('sListhelp');">
                        <h:graphicImage id="hl" value="/img/help-icon.png" alt="Help"></h:graphicImage><rich:toolTip for="lh" value="Help"></rich:toolTip>
                    </a4j:commandLink>
                    </div>
                    <h:form>
                        <h:panelGrid columns="1" >
                            <rich:panel style="margin-left:100px;margin-right:100px;border:none">
                                <h:panelGrid columns="10">
                                    <h:outputText style="font-size:14px;color:#4B4B4B;font-weight:bold;" value="Total No. Of Registered College : "/>
                                    <h:outputText style="font-size:14px;color:red;font-weight:bold;" value="#{OrgProfileBean.totalNoCollege}"/>&nbsp;&nbsp;&nbsp;
                                    <h:outputText style="font-size:14px;color:#4B4B4B;font-weight:bold;" value="Total No. Of Activated College : "/>
                                    <h:outputText style="font-size:14px;color:red;font-weight:bold;" value="#{OrgProfileBean.totalNoAcCollege}"/>&nbsp;&nbsp;&nbsp;
                                    <%-- <h:outputText style="font-size:14px;color:#4B4B4B;font-weight:bold;" value="Total No. Of Deactivated College : "/>--%> 
                                    <rich:menuItem style="font-size:14px;color:#4B4B4B;font-weight:bold;" value="Total No. of Deactivated College :" onclick="return loadIframe('ifrm', 'adminLogin/DeactivateCollegeList.jsf')"/>
                                    <h:outputText style="font-size:14px;color:red;font-weight:bold;" value="#{OrgProfileBean.totalNoDeAcCollege}"/>
                                </h:panelGrid>
                            </rich:panel>
                               <rich:panel style="margin-left:100px;margin-right:100px;border:none;" > 
                                <rich:messages>
                                    <f:facet name="infoMarker">
                                        <h:graphicImage url="/img/success.png"/>
                                    </f:facet>
                                </rich:messages>
                                
                                    <rich:dataTable style="font-size:14px;font-weight:bold;width:1050px;" id="upda" rowKeyVar="row" rows="20" value="#{OrgProfileBean.collegeList}" binding="#{OrgProfileBean.dataGrid1}"  var="ins"> 
                                        <a4j:keepAlive beanName="OrgProfileBean" ajaxOnly="true"/>
                                        <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="S.No."/>
                                        </f:facet>
                                           <%-- <h:inputHidden value="#{ins.id}"/> --%>
                                           <h:outputText value="#{ins.srNo}"/>
                                        </h:column> 
                                      <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Institute Name"/>
                                        </f:facet>
                                        <rich:inplaceInput value="#{ins.name}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Web Site"/>
                                        </f:facet>
                                        <rich:inplaceInput value="#{ins.web}"/> 
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Admin Email ID"/>
                                        </f:facet>
                                        <rich:inplaceInput value="#{ins.email}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Admin Phone"/>
                                        </f:facet>
                                        <h:outputText value="#{ins.phone}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Registered Date"/>
                                        </f:facet>
                                        <h:outputText value="#{ins.date}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Check/Uncheck"/>
                                        </f:facet>
                                        <h:selectBooleanCheckbox  value="#{ins.status}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value="Status"/>
                                        </f:facet>
                                        <h:graphicImage value="/img/#{ins.imgUrl}"/>
                                    </h:column>
                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value=""/>
                                        </f:facet>
                                        <h:commandButton value="Update" action="#{OrgProfileBean.updateRow}"/>
                                    </h:column>

                                    <h:column>
                                        <f:facet name="header" >
                                            <h:outputText value=""/>
                                        </f:facet>
                                        <a4j:commandButton value="Change Password"  immediate="true" ajaxSingle="true" reRender="editGrid" onclick="Richfaces.showModalPanel('adnew');">
                                        <f:setPropertyActionListener value="#{ins}" target="#{OrgProfileBean.editedRecord}" />
                                        </a4j:commandButton>
                                   </h:column>
                                    <f:facet name="footer">
                                    <rich:datascroller for="upda" page="5" />
                                    </f:facet>

                                </rich:dataTable>
                                    <rich:panel>
                                     <a4j:commandButton reRender="#{OrgProfileBean.collegeList}, upda" value="Deactivate" action="#{OrgProfileBean.updateAciveInActive}"/>
                                    </rich:panel>
                                </rich:panel>
                                    <%--<rich:panel style="margin-left:100px;margin-right:100px;border:none">
                                <h:panelGrid columns="2">
                                 
                                      <a4j:commandButton reRender="upda" value="Activate/Deactivate" action="#{OrgProfileBean.updateAciveInActive}"/> 
                                </h:panelGrid>
                            </rich:panel>--%>                       
                        </h:panelGrid>
                      </h:form>
                </rich:panel>
              </a4j:region>
                                     
            <rich:modalPanel label="Change Password" id="adnew" width="500" height="200" autosized="true">
                <a4j:support event="onbeforeshow" action="#{OrgProfileBean.updatePassword}"/>
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Change Password"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="controls">
                    <h:panelGroup>
                        <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelinkpnl"/>
                        <rich:componentControl for="adnew" attachTo="hidelinkpnl" operation="hide" event="onclick"/>
                    </h:panelGroup>
                </f:facet>
                <h:form>
                    <rich:panel>
                        <rich:messages>
                            <f:facet name="infoMarker">
                                <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                                <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                        <h:panelGrid id="editGrid" columns="2" style="width:500; height:200;">
                            <h:outputText value="User ID"/>
                            <h:outputText value="#{OrgProfileBean.editedRecord.email}"/>
                            <h:outputText value="Password"/>
                            <h:inputSecret size="30" value="#{OrgProfileBean.editedRecord.adPassword}"/>
                            <h:outputText value="Re Password"/>
                            <h:inputSecret size="30" value="#{OrgProfileBean.editedRecord.adRePassword}"/>
                        </h:panelGrid>
                        <h:panelGrid columns="2" style="padding-left:15%;">
                            <a4j:commandButton reRender="upda" value="Submit"  action="#{OrgProfileBean.updatePassword}"/>
                            <a4j:commandButton value="Reset" type="reset"/>
                        </h:panelGrid>
                    </rich:panel>
                </h:form>
            </rich:modalPanel>   
                                    
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
                                    <h:outputText style="font-size:14px;" value=" uncheck the checkbox and click on Deactivate button. "/>
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
