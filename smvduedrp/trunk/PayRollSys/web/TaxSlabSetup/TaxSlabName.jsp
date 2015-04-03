<%--
    Document   : TaxSlabName
    Created on : May 1, 2012, 4:29:00 PM
    Author     : ERP
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
            <title>Slab Name</title>
            <link type="text/css" rel="stylesheet" href="../bankDetails.css"/>
        </head>
        <body>
           <f:view>
            <rich:panel header="Slab Details">
                <h:form>
                    
                    <rich:messages> 
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                    
                    
                    <h:panelGrid columns="4">
                                                  
                            <h:outputText value="Financial Year"/>
                             <h:selectOneMenu value="#{taxSlabHeadBean.session}">
                            <f:selectItems value="#{SessionBean.arrayAsItem}"/>
                            </h:selectOneMenu>
                            <a4j:commandButton reRender="sl" value="Load Slabs" action="#{taxSlabHeadBean.populate}"/>
                     
                            <a4j:commandButton value="Add New Slab" onclick="Richfaces.showModalPanel('tsn');"/>

                    </h:panelGrid>
                </h:form>
                
                <h:form>
                    <rich:panel id="sl">


                        <rich:dataTable binding="#{taxSlabHeadBean.grid}" value="#{taxSlabHeadBean.taxHeadValue}" var="sld" style="width:100%;">

                      <%--      <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Seq. No."/>
                                </f:facet>
                                <h:outputText value="#{sld.slabHeadCode}"/>
                            </h:column> --%>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Slab Name"/>
                                </f:facet>
                                <h:outputText value="#{sld.slabName}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Slab Start Value"/>
                                </f:facet>
                                <h:outputText value="#{sld.startSlabValue}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Slab End Value"/>
                                </f:facet>
                                <h:outputText value="#{sld.endSlabValue}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Slab Percent"/>
                                </f:facet>
                                <h:outputText value="#{sld.percent}"/>
                            </h:column>
                              <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Slab Surcharge"/>
                                </f:facet>
                                <h:outputText value="#{sld.surcharge}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Educational Cess"/>
                                </f:facet>
                                <h:outputText value="#{sld.eduCess}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Heigher Edu. Cess"/>
                                </f:facet>
                                <h:outputText value="#{sld.heduCess}"/>
                            </h:column>         
                        </rich:dataTable>
                    </rich:panel>
                    <h:panelGrid>
                      <%--<a4j:commandButton reRender="sl" value="Update" action="#{slabConttroler.update}"/>--%>
                    </h:panelGrid>
                       
                </h:form>

                    
            </rich:panel>
           
               
            <rich:modalPanel label="Add Slab" id="tsn" moveable="true" height="280"  width="350">
                <f:facet name="header">
                     <h:panelGroup>
                         <h:outputText value="Add New Slabs"></h:outputText>
                     </h:panelGroup>
                </f:facet>
                
                <f:facet name="controls">
                     <h:panelGroup>
                        <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                        <rich:componentControl for="tsn" attachTo="hidelink" operation="hide" event="onclick"/>
                     </h:panelGroup>
                </f:facet>
                <h:form>
                     <rich:panel>
                        <h:panelGrid columns="2">
                            <h:outputText styleClass="Label" value="Financial Year"/>
                             <h:selectOneMenu  styleClass="combo"  id="empDesig"  value="#{taxSlabHeadBean.fyearDropDown}">
                                   <f:selectItems value="#{SessionBean.currentSessionItem}"/>
                            </h:selectOneMenu>
                            <h:outputText value="Slab Name"/>
                            <h:inputText id="sln" value="#{taxSlabHeadBean.slabName}" required="true" requiredMessage="Please Enter Slab Name."/>
                            <h:outputText value="Slab Start Value"/>
                            <h:inputText id="st" value="#{taxSlabHeadBean.startSlabValue}" required="true" requiredMessage="Please Enter Slab Start value."/>
                            <h:outputText value="Slab End Value"/>
                            <h:inputText id="se" value="#{taxSlabHeadBean.endSlabValue}" required="true" requiredMessage="Please Enter Slab End Value."/>
                            <h:outputText value="Slab Percent"/>
                            <h:inputText id="sp" value="#{taxSlabHeadBean.percent}" required="true" requiredMessage="Please Enter Slab Percent."/>
                            <h:outputText value="Slab Surcharge"/>
                            <h:inputText id="sr" value="#{taxSlabHeadBean.surcharge}" required="true" requiredMessage="Please Enter Surcharge"/>
                            <h:outputText value="Educational Cess"/>
                            <h:inputText id="ed" value="#{taxSlabHeadBean.eduCess}" required="true" requiredMessage="Please Enter Educational Cess"/>
                            <h:outputText value="Heigher Edu. Cess"/>
                            <h:inputText id="hed" value="#{taxSlabHeadBean.heduCess}" required="true" requiredMessage="Please Enter Heigher Edu. Cess"/>
                            
                        </h:panelGrid>
                    </rich:panel>
                    <rich:toolTip for="sln" value="Only Alpha Numeric Will Be Valid No Other Special Charecter Will Be Accepted(e.g @#$%^&*...)"/>
                    <rich:separator/>
                    <rich:panel>
                        <a4j:commandButton value="Save" action="#{taxSlabHeadBean.saveSlabHead}" onclick="Richfaces.hideModalPanel('tsn');"/>
                        <a4j:commandButton value="Close" onclick="Richfaces.hideModalPanel('tsn');"/>
                    </rich:panel>
                </h:form>
            </rich:modalPanel>
            </f:view>
        </body>
    </html>
