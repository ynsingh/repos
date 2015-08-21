<%-- 
    Document   : PendingCollegeList
    Created on : Dec 24, 2012, 11:16:48 AM
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
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        </head>
        <body> <f:view>
            <rich:panel style="height:380px; width:1290px" header="List of Pending Institute's">
                <h:form>
                    <rich:panel style="margin-left:100px;margin-right:100px;border:none;" >
                        <rich:messages>
                            <f:facet name="infoMarker">
                                <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                        </rich:messages>
                        <rich:dataTable id="upda" style="font-size:14px;font-weight:bold;width:1050px;" rowKeyVar="row" rows="7" value="#{OrgProfileBean.pendingList}" binding="#{OrgProfileBean.dataGrid3}"  var="ins"> 
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
                                <h:outputText value="#{ins.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header" >
                                    <h:outputText value="Web Site"/>
                                </f:facet>
                                <h:outputText value="#{ins.web}"/> 
                            </h:column>
                            <h:column>
                                <f:facet name="header" >
                                    <h:outputText value="Admin Email ID"/>
                                </f:facet>
                                <h:outputText value="#{ins.email}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header" >
                                    <h:outputText value="Admin Phone"/>
                                </f:facet>
                                <h:outputText value="#{ins.phone}"/>
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
                            
                             <f:facet name="footer">
                                    <rich:datascroller for="upda" page="5" />
                            </f:facet>

                      </rich:dataTable>
                        <rich:panel>
                        <a4j:commandButton value="Update" action="#{OrgProfileBean.updateRequest}"/>
                            <a4j:commandButton reRender="list" value="Filter College's"/>
                        </rich:panel>
                     </rich:panel>
                    <%--  <rich:panel style="margin-left:100px;margin-right:100px;">
                        <h:panelGrid columns="2">
                            <a4j:commandButton value="Update" action="#{OrgProfileBean.updateRequest}"/>
                            <a4j:commandButton reRender="list" value="Filter College's"/>
                        </h:panelGrid>
                    </rich:panel>--%>
                </h:form>
            </rich:panel> </f:view>
        </body>
    </html>
