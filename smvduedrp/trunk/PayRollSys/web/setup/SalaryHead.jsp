<%--
    Document        : SalaryHead.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Modified        : 3:02 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
    Last Modified   : 3:02 AM Wednesday, November 12, 2014
    Author          : Manorama Pal Kishore Kumar Shukla
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <f:view>
        <rich:panel header="Existing Salary Heads">
            <h:panelGrid columns="2">
                <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/><br/>
                 <rich:messages>
                    <f:facet name="infoMarker">
                        <h:graphicImage url="/img/success.png"/>
                    </f:facet>
                </rich:messages>
            </h:panelGrid>
            <rich:separator  style="width:100%;" /><br/>
            <h:form>
                <rich:contextMenu attached="false" id="menu" submitMode="ajax">
                    <rich:menuItem ajaxSingle="true">
                        <b>Salary Head Options </b>
                    </rich:menuItem>

                    <rich:menuGroup value="Actions">
                        <rich:menuItem ajaxSingle="true">
                            Update {name}
                            <a4j:actionparam name="code" assignTo="#{SalaryHeadBean.number}" value="#{code}"/>
                        </rich:menuItem>
                        <rich:menuItem action="#{SalaryHeadBean.delete}" ajaxSingle="true">
                            Delete {name}
                            <a4j:actionparam name="code" assignTo="#{SalaryHeadBean.number}" value="#{code}"/>
                        </rich:menuItem>
                    </rich:menuGroup>
                </rich:contextMenu>
                <h:panelGrid columns="2">
                    <rich:dataTable id="headTable" binding="#{SalaryHeadControllerBean.dataGrid}" style="width:70%;"
                                    value="#{SalaryHeadControllerBean.heads}" var="heads" >
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Head Code"/>
                            </f:facet>
                            <rich:inplaceInput value="#{heads.SHCode}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Head Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{heads.name}" />
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Short Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{heads.alias}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Ledger Code"/>
                            </f:facet>
                            <h:outputText value="#{heads.ledgerCode}" />
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText style="width:100px;" value="Income"/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{heads.under}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Formula"/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{heads.calculationType}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Taxable"/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{heads.type}"/>
                        </h:column>
                        <%-- <h:column>
                            <f:facet name="header">
                                <h:outputText value="Hidden"/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{heads.display}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Schedule Type"/>
                            </f:facet>
                            <h:selectBooleanCheckbox style="font-weight:1.2em;" value="#{heads.processType}"/>
                            <h:outputText value="Regular" rendered="#{!heads.processType}"/>
                            <h:commandButton value="Schedule" rendered="#{heads.processType}"/>
                         </h:column> 04/05/12 Changed Currently Schudule Type And Hidden Are Not Used Or Activate--%>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Linked"/>
                            </f:facet>
                            <h:selectBooleanCheckbox disabled="true" readonly="true" value="#{heads.special}"/>
                        </h:column>

                    </rich:dataTable>

                    <rich:panel style="" header="Important Points related to salary Heads">
                        <h:panelGrid columns="1">
                            <h:outputText value="1. Salary Heads marked with Income are Income , otherwise Deduction"/>
                            <h:outputText value="2. Salary Heads marked with Formula are Formula , otherwise Consolidated"/>
                            <h:outputText value="3. Salary Heads marked with Taxable are taxable , otherwise Non-Taxable"/>
                            <h:outputText value="4. Salary Heads marked with Hidden are neither Income nor Deduction. It will be contributiong to some Report Only."/>
                            <h:outputText value="5. Scheduled Salary Heads will be evaluated in choosen months only. Regular will be evaluated every month"/>
                            <h:outputText value="6. Salary Categories are just meant to organize salary heads . It doesnot do anything otherwise."/>
                            <h:outputText value="7. Sort Name of salary head is a Must. It is used to refer to salary heads while creating formula."/>
                            <h:outputText value="8. Name, Income/deduction, Formula etc  of salary heads can be edited any time."/>
                            <h:outputText value="9. Linked Salary heads are integrated with other modules, and automatically contribute in corresponding reports."/>
                        </h:panelGrid>
                    </rich:panel>

                </h:panelGrid>
                <h:commandButton action="#{SalaryHeadControllerBean.update}" value="Update"/>
            </h:form>
        </rich:panel>
        <rich:modalPanel width="400" height="240" autosized="true" id="pnl">
            <f:facet name="header">
                        <h:panelGroup>
                            <h:outputText value="Add New Salary Head"></h:outputText>
                        </h:panelGroup>
                    </f:facet>
            <f:facet name="controls">
                        <h:panelGroup>
                            <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                            <rich:componentControl for="pnl" attachTo="hidelink" operation="hide" event="onclick"/>
                         </h:panelGroup>
                    </f:facet>
            <h:form>
                  <h:panelGrid columns="3">
                        <h:outputText value="Salary Head Code"/>
                        <h:inputText id="shCode" required="true" requiredMessage="Enter Salary Head Code" value="#{SalaryHeadBean.SHCode}" />
                        <h:message for="shCode" styleClass="error"/>
                        <h:outputText value="Salary Head"/>
                        <h:inputText id="shName" required="true" requiredMessage="Enter Salary Head Name" value="#{SalaryHeadBean.name}" />
                        <h:message for="shName" styleClass="error"/>
                        <h:outputText value="Short Name"/>
                        <h:inputText id="shortName" required="true" requiredMessage="Enter Salary Head Short Name" value="#{SalaryHeadBean.alias}" />
                        <h:message for="shortName" styleClass="error"/>
                        <h:outputText value="Under"/>
                        <h:selectOneMenu value="#{SalaryHeadBean.under}">
                            <f:selectItem itemValue="false" itemLabel="Income"/>
                            <f:selectItem itemValue="true" itemLabel="Deduction"/>
                        </h:selectOneMenu>
                        <h:outputText value=""/>
                        <h:outputText value="Schedule Type"/>
                        <h:selectOneMenu value="#{SalaryHeadBean.processType}">
                            <f:selectItem itemValue="false" itemLabel="Regular"/>
                            <f:selectItem itemValue="true" itemLabel="Scheduled"/>
                        </h:selectOneMenu>
                        <h:outputText value=""/>
                        <h:outputText value="Calculation Type"/>
                        <h:selectOneMenu value="#{SalaryHeadBean.calculationType}">
                            <f:selectItem itemValue="false" itemLabel="Consolidated"/>
                            <f:selectItem itemValue="true" itemLabel="Formula"/>
                        </h:selectOneMenu>  
                        <h:outputText value=""/>
                        <h:outputText value="Category"/>
                        <h:selectOneMenu value="#{SalaryHeadBean.typeCode}">
                            <f:selectItems  value="#{SalaryHeadControllerBean.category}"/>
                        </h:selectOneMenu>
                        <h:outputText value=""/>
                        <h:outputText value="Taxable"/>
                        <h:selectBooleanCheckbox value="#{SalaryHeadBean.type}"/>
                   </h:panelGrid>
                    <a4j:commandButton value="Save" styleClass="submit" reRender="headTable"
                                           action="#{SalaryHeadBean.save}" />
                    <h:commandButton value="Close" onclick="Richfaces.hideModalPanel('pnl');" />
                </h:form>
        </rich:modalPanel>
                   
    </f:view>
