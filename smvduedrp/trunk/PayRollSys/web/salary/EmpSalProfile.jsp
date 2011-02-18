<%-- 
    Document   : newjsf
    Created on : Feb 10, 2011, 8:02:45 PM
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
   <h:form>
                        <rich:panel style="width:280px;border:1;">
                            <h:panelGrid columns="4">
                            <h:outputText value="Employee Code"/>
                            <h:inputText id="empcCode" value="#{SalaryProcessingBean.empCode}" />
                            <rich:suggestionbox for="empcCode" var="abc" fetchValue="#{abc.code}"  suggestionAction="#{SearchBean.getSuggestion}">
                            <h:column>
                            <h:outputText value="#{abc.name}"/>
                            </h:column>
                            <h:column>
                            <h:outputText value="#{abc.code}"/>
                            </h:column>
                            </rich:suggestionbox>
                            <a4j:commandButton status="topstat" action="#{SalaryProcessingBean.loadProfile}" reRender="empDetail,mypnl,salpnl" value="Load"/>
                            <h:outputText value="For month"/>
                            <h:outputText style="font-size:12px;" value="#{UserBean.currentMonthName}"/>
                            </h:panelGrid>
                        </rich:panel>
                    </h:form>
</f:view>
