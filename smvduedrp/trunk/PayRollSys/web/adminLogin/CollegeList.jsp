    <%-- 
    Document   : AdminIframe
    Created on : Dec 13, 2012, 9:13:24 PM
    Author     : KESU
    GUI Modified date 21 July 2015, IITK , Om Prakash (omprakashkgp@gmail.com)
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
        <body>
           
            <a4j:region>
                <rich:panel header="List Of Registered College/Institute's" style="font-size:17px;font-weight:bold;width:1290px;height:380px;">
                    <h:form>
                        <h:panelGrid columns="1" >
                            <rich:panel style="margin-left:100px;margin-right:100px;border:none">
                                <h:panelGrid columns="10">
                                    <h:outputText style="font-size:14px;color:#4B4B4B;font-weight:bold;" value="Total No. Of Registered College : "/>
                                    <h:outputText style="font-size:14px;color:red;font-weight:bold;" value="#{OrgProfileBean.totalNoCollege}"/>&nbsp;&nbsp;&nbsp;
                                    <h:outputText style="font-size:14px;color:#4B4B4B;font-weight:bold;" value="Total No. Of Activated College : "/>
                                    <h:outputText style="font-size:14px;color:red;font-weight:bold;" value="#{OrgProfileBean.totalNoAcCollege}"/>&nbsp;&nbsp;&nbsp;
                                    <h:outputText style="font-size:14px;color:#4B4B4B;font-weight:bold;" value="Total No. Of Deactivated College : "/>
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
                                     <a4j:commandButton reRender="upda" value="Activate/Deactivate" action="#{OrgProfileBean.updateAciveInActive}"/>
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
        </body>
        </f:view>
    </html>
