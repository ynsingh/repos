<%--
    Document   : Add Department
    Created on : Sept 28, 2010, 4:26:31 PM
    Author     : Saurabh
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
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <title>Add Employee Type</title>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
            <f:view>
                <rich:panel header="Employee Types Master Setup">
                    <div align="right" >                                            
                    <a4j:commandLink ajaxSingle="true" reRender="helppnl" onclick="Richfaces.showModalPanel('hnl');" >
                    <h:graphicImage value="/img/help-icon.png" alt="Help" /> 
                    </a4j:commandLink>
                     </div>
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/></br></br>

                    <rich:messages>
                       <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                       </f:facet>
                       <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                        </f:facet>
                    </rich:messages>
                    <rich:separator  style="width:100%;" /><br/>
                     <h:form>
                         <h:panelGrid style="width:100%;">
                         <rich:dataTable id="tbl" value="#{EmployeeTypeControllerBean.employeeTypes}"
                                         binding="#{EmployeeTypeControllerBean.dataGrid}" var="et" rowKeyVar="row"  rows="5" style="width:100%;">
                        
                        <h:column>
			    <f:facet name="header">
                                <h:outputText value="Employee Type Code"/>
                            </f:facet>
                            <rich:inplaceInput value="#{et.empTypeCode}"/>
			</h:column>
			<h:column>
                            <f:facet name="header">
                                <h:outputText value="Employee Type Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{et.name}"/>
                        </h:column>
			<h:column>
                            <f:facet name="header">
                                <h:outputText value="Nick Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{et.nickname}" />
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PF Applies"/>
                            </f:facet>
                            <h:selectBooleanCheckbox value="#{et.pfApplies}"/>
                        </h:column>
			<h:column>
                            <f:facet name="header">
                                <h:outputText value="Max PF Limit"/>
                            </f:facet>
                            <rich:inplaceInput value="#{et.maxpf}" />
                        </h:column>
                         <f:facet name="footer">
                                <rich:datascroller for="tbl" page="1"/>  
                         </f:facet>

                         </rich:dataTable></br>
                    <h:commandButton action="#{EmployeeTypeControllerBean.update}" value="Update"/>
                        
                  </h:panelGrid>
                    </h:form>
                </rich:panel>
                <rich:modalPanel id="pnl" height="170"  width="300">
                                          
                        <f:facet name="header">
                            <h:panelGroup>
                                <h:outputText value="Add New Employee Type"></h:outputText>
                            </h:panelGroup>
                        </f:facet>
                            
                        <f:facet name="controls">
                        <h:panelGroup>
                            <h:graphicImage value="/img/close1.png" styleClass="hidelink" id="hidelink"/>
                            <rich:componentControl for="pnl" attachTo="hidelink" operation="hide" event="onclick"/>
                         </h:panelGroup>
                         </f:facet>
                        <h:form>
                        <h:panelGrid columns="2">
			   <h:outputText value="Employee Code*"/>
			   <h:inputText id="empCode" required="true"
                                                 requiredMessage="Enter Employee Type Code"
                                                 styleClass="fields" value="#{EmployeeTypeBean.empTypeCode}" style="width:160px; margin-left:20px;">          
              	                        <rich:ajaxValidator event="onblur"></rich:ajaxValidator>
                             </h:inputText>

				
                            <h:outputText value="Employee Type*"/>
                            <h:inputText id="empname" required="true"
                                                 requiredMessage="Please Enter Type Name."
                                                 styleClass="fields"  value="#{EmployeeTypeBean.name}" style="width:160px; margin-left:20px;">
                                        <rich:ajaxValidator event="onblur"></rich:ajaxValidator>
                                        </h:inputText>
      			    
			    <h:outputText value="Nick Name"/>
			    <h:inputText id="empNiickname" 
                                                 styleClass="fields"  value="#{EmployeeTypeBean.nickname}" style="width:160px; margin-left:20px;">                
                                    </h:inputText>
			    <h:outputText value="PF Applies"/>
                            <h:selectBooleanCheckbox value="#{EmployeeTypeBean.pfApplies}" style="margin-left:20px;"/>
			    <h:outputText value="Max PF Limit"/>
                            <h:inputText id="empPfLimit" 
                                                 styleClass="fields" value="#{EmployeeTypeBean.maxpf}" style="width:160px; margin-left:20px;"> 
			    </h:inputText>
                          
                            <%-- <h:commandButton  value="Save" action="#{EmployeeTypeBean.save}"/>--%>
			    <a4j:commandButton  value="Save" action="#{EmployeeTypeBean.save}" reRender="tbl" oncomplete="#{rich:component('pnl')}.hide();" style="margin-top:10px"/>
                            <h:commandButton onclick="Richfaces.hideModalPanel('pnl');" value="Close"/>
                        </h:panelGrid>
                        </h:form>
                
                </rich:modalPanel>
                    
               <rich:modalPanel id="hnl" autosized="true" domElementAttachment="parent" width="600" style="padding:0px 0px;">
                      
                   <f:facet name="controls">
                   <h:panelGroup>
                    <h:graphicImage value="/img/close1.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('hnl')" />
                    </h:panelGroup> 
                    </f:facet>
               
                 <h:form>
                
                    <rich:panel header="Help">
                    <h:panelGrid  id="helppnl">
                        <h:outputText style="font-size:1.5em; font-weight:bold;" value="Instruction for Employee Type"/>
                        <h:outputText style="font-size:1.5em; font-weight:bold;" value="----------------------------------------"/>
                        </br>
                        

                        <h:outputText style="font-size:1.5em;" value="Example: "/></br>
                        <h:outputText style="font-size:1.5em;" value=" Employee Code = REG01 or 0001"/></br>
                        <h:outputText style="font-size:1.5em;" value=" Employee Type Name =REGULAR"/></br>
                        <h:outputText style="font-size:1.5em;" value=" Nick Name = REG "/></br>
                        <h:outputText style="font-size:1.5em;" value=" Click on the Checlbox if you want to apply PF"/></br>
                        <h:outputText style="font-size:1.5em;" value=" Max PF Limit = 1000000 "/></br>
                    
                               
                    </h:panelGrid>
                    </rich:panel>
                    <h:commandButton value="Close" onclick="#{rich:component('hnl')}.hide(); return false;" />
                </h:form>
                </rich:modalPanel>

            </f:view>
        </div>
    </body>
</html>
