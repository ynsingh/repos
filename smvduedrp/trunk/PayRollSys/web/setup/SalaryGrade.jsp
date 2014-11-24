<%-- 
    Document        : SalaryGrade
    Created on      : Jul 2, 2010, 4:26:10 AM
    Last Modified   : 4:03 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
    Last Modified   : 4:03 AM Monday, November 03, 2014
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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
    </head>
    <body class="subpage" id="">

        <f:view>
            <rich:panel header="Existing Pay Scales">
                <div align="right" >                                            
                <a4j:commandLink ajaxSingle="true" reRender="helppnl" onclick="Richfaces.showModalPanel('hnl');" >
                <h:graphicImage value="/img/help-icon.png" alt="Help" /> 
                </a4j:commandLink>
                 </div>
                <h:panelGrid columns="2">
                    <h:commandButton onclick="Richfaces.showModalPanel('pnl');" value="Add New"/>
                    <h:commandButton onclick="Richfaces.showModalPanel('dnl');" value="Upload Pay Band List"/><br/>
                    
                    <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                        </f:facet>
                         <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                         </f:facet>
                    </rich:messages>
                </h:panelGrid>
                <rich:separator  style="width:100%;" /><br/>
                <h:form>  
                    <rich:dataTable id="tbl" binding="#{SalaryGradeControllerBean.dataGrid}" 
                                    value="#{SalaryGradeControllerBean.grades}" var="grades" rowKeyVar="row"  rows="20" style="width:100%;">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Pay Band Name"/>
                            </f:facet>
                            <rich:inplaceInput value="#{grades.name}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText  value="Min Amount"/>
                            </f:facet>
                            <rich:inplaceInput  value="#{grades.minValue}" />
                        </h:column>
                       
                        <h:column >
                            <f:facet name="header">
                                <h:outputText  value="Max Amount"/>
                            </f:facet>
                            <rich:inplaceInput value="#{grades.maxValue}" />
                        </h:column>

                        <h:column>
                            <f:facet name="header">
                                <h:outputText  value="Academic Grade Pay /Grade Pay (AGP/GP)"/>
                            </f:facet>
                            <rich:inplaceInput  value="#{grades.gradePay}" />
                        </h:column>
                        
                        <h:column>
                            <f:facet name="header">
                                <h:outputText  value="Pay Band"/>
                            </f:facet>
                            <%--<rich:inplaceInput  value="#{grades.gradePay}" />--%>
                            <h:outputText value="#{grades.name}(#{grades.minValue} - #{grades.maxValue}) - #{grades.gradePay}"/>
                        </h:column>
                        <f:facet name="footer">
                        <rich:datascroller for="tbl" page="20"/>  
                        </f:facet>    
                    </rich:dataTable>

                    <h:panelGrid columns="2">
                        <h:commandButton value="Update" action="#{SalaryGradeControllerBean.save}"/>
                    </h:panelGrid>
                </h:form>
            </rich:panel>

            <rich:modalPanel id="pnl">
                  <f:facet name="header">
                        <h:panelGroup>
                            <h:outputText value="Add New Pay Scale"></h:outputText>
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
                            <h:outputText value="Grade Name"/>
                            <h:inputText id="grdName" required="true" requiredMessage="Enter New Grade Name" value="#{SalaryGradeBean.name}" />
                            <h:message for="grdName" styleClass="error"/>
                       <%-- </h:panelGrid>

                         <h:panelGrid columns="2">--%>
                            <h:outputText value="Minimum"/>
                            <h:inputText id="salMin" required="true" requiredMessage="Enter Minimum Value in Number"  value="#{SalaryGradeBean.minValue}" />
                            <h:message for="salMax" styleClass="error"/>
                        <%--</h:panelGrid>

                        <h:panelGrid columns="2">--%>
                            <h:outputText value="Maximum"/>
                            <h:inputText required="true" requiredMessage="Enter Maximum Value in Number" id="salMax" value="#{SalaryGradeBean.maxValue}" />
                            <h:message for="salMax" styleClass="error"/>
                        <%--</h:panelGrid>
                       
                        <h:panelGrid columns="2">--%>
                            <h:outputText value="Grade Pay"/>
                            <h:inputText id="salgp" required="true" requiredMessage="Enter Grade Pay amount"  value="#{SalaryGradeBean.gradePay}" />
                            <h:message for="salgp" styleClass="error"/>
                        </h:panelGrid>
                        <%--<h:panelGrid columns="2">--%>
                        <rich:spacer/>
                            <a4j:commandButton reRender="tbl" value="Save" action="#{SalaryGradeBean.save}" styleClass="submit" onclick="Richfaces.hideModalPanel('pnl');"/>
                            <a4j:commandButton value="Cancel" onclick="#{rich:component('pnl')}.hide(); return false;" />
                        </h:form>
                <%--</rich:panel>--%>
            </rich:modalPanel>
                    
                     <rich:modalPanel  width="500" height="240" autosized="true" id="dnl">
                
                <%--file upload for departments---------------------- --%>
                <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('dnl')" />
                </f:facet>
               <h:form>
              <h:panelGrid columns="2" columnClasses="top,top">
                  <rich:fileUpload fileUploadListener="#{SalaryGradeBean.listener}"
                maxFilesQuantity="#{FileUploadBean.uploadsAvailable}"
                id="upload"
                immediateUpload="#{FileUploadBean.autoUpload}"
                acceptedTypes="csv" allowFlash="#{FileUploadBean.useFlash}">
                      <a4j:support event="onuploadcomplete" reRender="tbl"/>
                  </rich:fileUpload>
               
                 </h:panelGrid>
                </h:form>
                
           <%---file upload END================================= --%>     
                </rich:modalPanel>
                 
                 
               <rich:modalPanel id="hnl" autosized="true" domElementAttachment="parent" width="700" height="400">
               <f:facet name="controls">
                    <h:graphicImage value="/img/cls.png" style="cursor:pointer"
                                    onclick="Richfaces.hideModalPanel('hnl')" />
                </f:facet>
                
                <h:form>
                    <rich:panel header="Help">
                    <h:panelGrid  id="helppnl">
                        <h:outputText style="font-size:1.5em;" value="Instruction for upload a csv file."/>

                    <h:outputText style="font-size:1.5em;" value=" 1. Open LibreOffice Calc in ubuntu and Excel in windows."/>

                    <h:outputText style="font-size:1.5em;" value=" 2. The file should contain four field i.e"/>

                    <h:outputText  style="font-size:1.5em;font-weight:bold;" value=" Pay Band Name"/>
                    <h:outputText  style="font-size:1.5em;font-weight:bold;"  value="Max Amount"/>
                    <h:outputText  style="font-size:1.5em;font-weight:bold;" value="Min Amount"/>
                    <h:outputText  style="font-size:1.5em;font-weight:bold;" value="Academic Grade Pay/ GradePay"/>
                    <h:outputText style="font-size:1.5em;" value="Example: "/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Pay Band Name = PB-2"/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Max Amount = 34900"/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Min Amount =9300"/>
                    <h:outputText style="font-size:1.5em;font-weight:bold;" value=" Grade Pay = 4800"/>
                    <h:outputText style="font-size:1.5em;"  value="3. Save as csv format."/>
                               
                    </h:panelGrid>
                    </rich:panel>
                    <h:commandButton value="Close" onclick="#{rich:component('hnl')}.hide(); return false;" />
                </h:form>
                </rich:modalPanel>
        </f:view>
    </tbody>
</body>
</html>
