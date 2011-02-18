<%-- 
    Document   : MonthlyRoll
    Created on : Feb 11, 2011, 5:51:39 AM
    Author     : Algox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    
        
    <rich:panel header="Monthly Roll">
        <rich:dataTable value="#{MonthlyGrossController.heads}" var="hd">
            <rich:column>
                <f:facet name="header">
                    <h:outputText value="#{hd}"/>
                </f:facet>
                <h:outputText value="#{hd}"/>
            </rich:column>
        </rich:dataTable>
        </rich:panel>    
</f:view>
