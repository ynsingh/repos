<%-- 
    Document   : TaxSlabs
    Created on : 16 Apr, 2012, 10:50:10 AM
    Author     : smvdu
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

    </head>

    <f:view>
        <body>

            <rich:panel header="Tax Slabs">
                <rich:comboBox defaultLabel="Select one" value="#{newGender.gender}">
                    <f:selectItems value="#{newGender.loadGen}"/>
                </rich:comboBox><br/>
                <a4j:commandButton value="Load" action="#{taxType.gender}" />
                <br/>
                <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                <h:form id="taxslabs">
                     <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                    </rich:messages>
                    <h:panelGrid columns="3">
                        <rich:dataTable id="ab"  style="width:800px;" binding="#{taxTypeController.datagrid}"
                                        value="#{taxTypeController.types}" var="tt">
                            <rich:column width="5%">
                                <f:facet name="header">
                                    <h:outputText value="Name"/>
                                </f:facet>
                                <h:outputText value="#{tt.name}" />
                            </rich:column>
                            <rich:column width="10%">
                                <f:facet name="header">
                                    <h:outputText  value="Start Slab"/>
                                </f:facet>
                                <rich:inplaceInput value="#{tt.start}" />
                            </rich:column>
                            <rich:column width="10%">
                                <f:facet name="header">
                                    <h:outputText  value="End Slab"/>
                                </f:facet>
                                <rich:inplaceInput value="#{tt.end}" />
                            </rich:column>
                            <rich:column width="10%">
                                <f:facet name="header">
                                    <h:outputText  value="% Tax Percent"/>
                                </f:facet>
                                <rich:inplaceInput value="#{tt.taxpercent}" />
                            </rich:column>

                            <%--      <rich:column width="10%">
                                <f:facet name="header">
                                    <h:outputText  value="Gender"/>
                                </f:facet>
                                <rich:inplaceInput value="#{tt.gender}" />
                            </rich:column>     --%>


                        </rich:dataTable>
                    </h:panelGrid>
                    <a4j:commandButton  reRender="ab" action="#{taxTypeController.update}" value="Update"/>
                </h:form>


            </rich:panel>
            <br />
            <hr/>

            <rich:modalPanel id="pnl">
                <h:form>
                    <rich:panel header="Add New Tax Slab">

                        <h:panelGrid
                            columns="3"
                            styleClass="data_entry_form"
                            columnClasses="label,field">

                            <h:outputText styleClass="Label"  value="Name"/>
                            <h:inputText id="headnam" value="#{taxType.name}"/>
                            <br/>

                            <h:outputText styleClass="Label"  value="Start Slab"/>
                            <h:inputText id="headname" value="#{taxType.start}"/>
                            <br/>

                            <h:outputText styleClass="Label"  value="End Slab"/>
                            <h:inputText id="Label" value="#{taxType.end}"/>
                            <br/>

                            <h:outputText styleClass="Label"  value="Tax Percent"/>
                            <h:inputText value="#{taxType.taxpercent}"/>
                            <br/>


                            <h:outputText styleClass="Label"  value="Select One"/>
                            <rich:comboBox defaultLabel="Select one" value="#{taxType.gender}">
                                <f:selectItem itemValue="Male"/>
                                <f:selectItem itemValue="Female"/>
                                <f:selectItem itemValue="Senior Citizen"/>
                            </rich:comboBox>
                            <br/>
                            <a4j:commandButton value="Save" action="#{taxType.save}" />
                            <a4j:commandButton onclick="Richfaces.hideModalPanel('pnl');" value="Close"/>



                        </h:panelGrid>

                    </rich:panel>

                </h:form>
            </rich:modalPanel>

        </body>
    </f:view>

</html>
