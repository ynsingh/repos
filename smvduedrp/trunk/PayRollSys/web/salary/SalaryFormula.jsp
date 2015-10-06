<%-- 
    Document   : SalaryFormula
    Created on : Sept 28, 2010
    Author     : Saurabh Kumar
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <title>Formula Builder</title>
    </head>
        <f:view>
            <rich:panel header="Salary Formula ">
                <div align="right" > 
                <a4j:commandLink   onclick="javascript:window.print();" style="margin-right:10px;">
                <h:graphicImage value="/img/Printer-icon.png" alt="Print"  /> 
                </a4j:commandLink>
                <a4j:commandLink ajaxSingle="true" reRender="helppnl" onclick="Richfaces.showModalPanel('hnl');">
                <h:graphicImage value="/img/help-icon.png" alt="Help" /> 
                </a4j:commandLink>
                </div>
                <h:form id="salaryformula">
                    <rich:messages  >
                        <f:facet name="infoMarker">
                           <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                    <h:panelGrid columns="3" style="width:100%;"> 
                        
                        <rich:dataTable id="tbl" value="#{TableBean.data}" var="formula" binding="#{SalaryFormulaBean.data}"  rowKeyVar="row"  rows="20" style="width:100%;">
                            <a4j:keepAlive beanName="SalaryFormulaBean" ajaxOnly="true"/>
                            <h:inputHidden id="salCode" value="#{SalaryFormulaBean.salCode}"/>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Head Name"/>
                                </f:facet>
                                <h:outputText value="#{formula.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Formula"/>
                                </f:facet>
                                <h:outputText value="#{formula.formula}">
                                   <%--<f:validator validatorId="UrlValidator"/>--%>
                                </h:outputText>    
                               <%-- <rich:inplaceInput  value="#{formula.formula}"
                                                    layout="block"
                                                    id="inplace"
                                                    requiredMessage="Formula should be in () ex (BP+GP)*1.19"
                                                    changedHoverClass="hover" viewHoverClass="hover"
                                                    viewClass="inplace" changedClass="inplace"
                                                    selectOnEdit="true" editEvent="ondblclick"
                                                    showControls="true" oneditactivation="if (!confirm('Are you sure you want to change the value?')){return false;}">
                                    <a4j:support event="onviewactivated" reRender="#{row}">
                                        <a4j:actionparam name="#{row}" value="#{TableBean.salCode}"/>
                                        <f:setPropertyActionListener value="#{row}" target="#{TableBean.salCode}" />
                                    </a4j:support>
                                <f:facet name="controls">
                                <a4j:commandButton reRender="tbl" value="Update" action="#{TableBean.update}" />
                                </f:facet> 
                                </rich:inplaceInput>--%>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Depends On"/>
                                </f:facet>
                                <h:outputText value="#{formula.fields}"/>
                            </h:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="Actions"/>
                                </f:facet>
                                <a4j:commandLink styleClass="no-decor" ajaxSingle="true" reRender="editGrid" oncomplete="#{rich:component('editPane')}.show()">
                                    <h:graphicImage value="/img/edit.gif" alt="edit" />
                                    <f:setPropertyActionListener value="#{row}" target="#{SalaryFormulaBean.currentRecordindex}" />
                                    <f:setPropertyActionListener value="#{formula}" target="#{SalaryFormulaBean.editedRecord}" />
                                </a4j:commandLink>
                            </rich:column>
                            <f:facet name="footer">
                                <rich:datascroller for="tbl" page="20"/>  
                            </f:facet>
                        </rich:dataTable>
                        </h:panelGrid > 
                    </h:form>
                </rich:panel>
                <rich:modalPanel id="editPane" autosized="true"  domElementAttachment="parent" width="400" height="170">
                    <f:facet name="header">
                        <h:outputText value=" Add Formula " />
                    </f:facet>
                    <f:facet name="controls">
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('editPane')" />
                    </f:facet>
                    <h:form>
                        <%--<rich:panel >--%>
                            <h:panelGrid id="editGrid">
                               <%-- <h:outputText value="Formula Code"/>
                                <h:inputText value="#{SalaryFormulaBean.editedRecord.SFCode}" />
                                <h:outputText value="Salary Head"/>--%>
                                <h:inputText size="40" value="#{SalaryFormulaBean.editedRecord.name}" disabled="true"/>
                                <h:outputText value="Formula"/>
                                <h:inputText size="40" value="#{SalaryFormulaBean.editedRecord.formula}" />
                                <h:outputText value="Depends On"/>
                                <h:inputText size="40" value="#{SalaryFormulaBean.editedRecord.fields}" disabled="true"/>
                                <%--<h:outputText styleClass="Label" value="Effective Date"/>
                                <rich:calendar enableManualInput="false" converter="dateConverter"
                                                   showWeekDaysBar="false" showFooter="false" styleClass="special"
                                                   datePattern="yyyy-MM-dd" id="effDate" popup="true" required="true"
                                                   requiredMessage="*Enter effective Date as yyyy-mm-dd" value="#{SalaryFormulaBean.editedRecord.effectiveDate}"/>
                                <h:message styleClass="error" for="effDate" tooltip="*"/>--%>
                            </h:panelGrid>
                            <a4j:commandButton value="Update"   action="#{SalaryFormulaBean.save}" reRender="salaryformula" oncomplete="#{rich:component('editPane')}.hide();"/>
                            <a4j:commandButton value="Cancel" onclick="#{rich:component('editPane')}.hide(); return false;" />
                       <%-- </rich:panel>--%>
                    </h:form>
                </rich:modalPanel>
                <rich:modalPanel id="hnl"  width="700" height="500" autosized="true">
                    <f:facet name="header">
                        <h:outputText value=" Salary Formula Help" />
                    </f:facet>
                    <f:facet name="controls">
                        <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('hnl')" />
                    </f:facet>
                    <font size="2.0em">  
                    <p><b>Instruction for Add and Update Salary Formula</b></p>  
                    <p>Click on the link which is given in Action column<br>
                        After click there is a pop up panel on your screen which contain Salary Head <br><br>
                       and empty area given to insert the formula for the Head.<br><br>
                       <b>e.g. : if central government pay 119% DA (Dearness Allowance) then formula is <font color="green"> (BP+GP)*1.19 </font><br>
                         (here BP and GP is the Short Name of the Basic Pay and Grade Pay).</b><br>
                       use Short Name of the Salary Head for creating formula. <br><br><br>
                      Note: exactly you follow the above example for creating the formula for the Salary Head. 
                    </p>
                    <h:commandButton value="Close" onclick="#{rich:component('hnl')}.hide(); return false;" />
                </rich:modalPanel>
            </f:view>          
                    
    

