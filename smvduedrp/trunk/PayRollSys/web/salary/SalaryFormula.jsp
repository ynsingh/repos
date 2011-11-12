<%-- 
    Document   : SalaryFormula
    Created on : Sept 28, 2010
    Author     : Saurabh Kumar
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
       
        <title>Formula Builder</title>
        

    </head>
    <body class="subpage">
        <div class="container_form">
                    <f:view>
                        <rich:panel header="Salary Formula Creator">
                         <h:form id="salaryformula">
                             <rich:dataTable id="tbl" value="#{TableBean.data}" var="formula" binding="#{TableBean.myGrid}">
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
                                     <rich:inplaceInput value="#{formula.formula}"/>
                                 </h:column>
                                 <h:column>
                                      <f:facet name="header">
                                         <h:outputText value="Depends On"/>
                                     </f:facet>
                                     <h:outputText value="#{formula.fields}"/>
                                 </h:column>
                            </rich:dataTable>
                             <a4j:commandButton reRender="tbl" value="Update" action="#{TableBean.update}" />
                            </h:form>
                         </rich:panel>
                        </f:view>          
        </div>
    </body>
</html>
