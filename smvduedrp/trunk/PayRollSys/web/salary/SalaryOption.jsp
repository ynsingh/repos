<%-- 
    Document   : SalaryOption
    Created on : Jul 5, 2010, 1:54:00 AM
    Author     : Algox
--%>


<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
    </head>
    <body class="subpage" id="">
        <div class="container_form">
            <f:view>
                <h:form>
                    <rich:panel style="width:600px;" header="Configure Applicable Salary Heads">
                        <h:panelGrid columns="3">
                        <h:outputText value="Employee Type"/>
                        <h:selectOneMenu  value="#{SalaryHeadBean.empType}">
                            <f:selectItems value="#{EmployeeTypeBean.items}"/>
                        </h:selectOneMenu>
                        <a4j:commandButton reRender="plist" action="#{SalaryHeadBean.populate}" value="Load"/>
                        </h:panelGrid>
                        <rich:listShuttle id="plist" targetValue="#{SalaryHeadBean.selected}"
                                          sourceValue="#{SalaryHeadBean.heads}" var="itms">
                            <f:facet name="sourceCaption">
                                <h:outputText value="Existing Salary Heads" />
                            </f:facet>
                            <f:facet name="targetCaption">
                                <h:outputText value="Selected Salary Heads" />
                            </f:facet>
                            <rich:column>
                                <h:outputText value="#{itms}" />
                            </rich:column>
                        </rich:listShuttle>

                        <rich:separator/>
                        <h:commandButton value="Update" action="#{SalaryHeadBean.print}"  />
                        <h:commandButton value="Help" action="#{ETHBean.print}"  />
                    </rich:panel>
                </h:form>
            </f:view>
        </div>
    </body>
</html>
