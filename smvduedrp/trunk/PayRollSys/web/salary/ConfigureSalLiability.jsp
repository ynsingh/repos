<%-- 
    Document   : configureSalLiability
    Created on : Jun 30, 2016, 1:59:37 PM
    Author     : Manorama Pal (palseema30@gmail.com)
--%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    
    </head>
    
        <f:view>
            <rich:panel id="sd" header="Configure Salary Liability Date ">
                <%--<a4j:keepAlive beanName="ConfigParamBean" ajaxOnly="true"/>--%>
                <h:panelGrid >
                    <rich:messages>
                    <f:facet name="infoMarker">
                        <h:graphicImage url="/img/success.png"/>
                    </f:facet>
                    <f:facet name="errorMarker">
                        <h:graphicImage url="/img/err.png"/>
                    </f:facet>
                    </rich:messages>
                </h:panelGrid> 
                <h:form id="cp">
                    <c:choose>  
                        <c:when test="${not empty ConfigParamBean.value}">    
                        <h:outputText value="Employee Salary Liability will be created on "/>
                        <%-- <c:set var="dateParts" value="#{fn:split(ConfigParamBean.value,'-')}" />--%>
                        <h:outputText  id="dayValue" value="#{ConfigParamBean.dayValue}" style="color: red;"/>
                        <h:outputText value=" Day of every month"/><br/><br/>
                        </c:when>
                    </c:choose>    
                
                    <%-- <h:inputHidden id="newval" value="#{ConfigParamBean.value}"/>
                    ${ConfigParamBean.value} ....this is the value--%>
                    <h:panelGrid columns="3">
                        <h:outputText style="font-weight:bold;" value="Date"/>
                        <rich:calendar inputSize="10" converter="dateConverter" showWeekDaysBar="false"
                            showFooter="false" styleClass="special" datePattern="yyyy-MM-dd" popup="true"
                            required="true" requiredMessage="Select date for salary liability creation"
                            value="#{ConfigParamBean.value}">
                        </rich:calendar>
                        <a4j:commandButton action="#{ConfigParamBean.update}"  value="Set Date"  reRender="cp" />
                        <%--<rich:componentControl attachTo="button" for="dayValue" event="oncomplete" operation="show" />--%>
                    </h:panelGrid>
                </h:form>
            </rich:panel>
        </f:view>   
    

