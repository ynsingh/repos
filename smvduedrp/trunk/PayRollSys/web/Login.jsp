<%--
    Document        : Login.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 3:21 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link rel="stylesheet" type="text/css" href="loginpage.css"/>
        <title>Payroll System | Login</title>
    </head>

    <div class="mainpanel">
        

        <f:view>

           
            <rich:panel styleClass="innerpanel">
                <h:panelGrid columns="2" id="connect">
                    <h:graphicImage rendered="#{CommonDBBean.connected}" style="width:30px;height:30px;" url="/img/connected.jpg"/>
                    <h:outputText rendered="#{CommonDBBean.connected}" value="Database Server Connected. Login now to Access Payroll System " style="color:green;font-size:12px;align:center"/>
                    <h:graphicImage  rendered="#{!CommonDBBean.connected}" alt="Disconnected" url="/img/disconnected.jpg"/>
                    <h:outputText rendered="#{!CommonDBBean.connected}" value="Database Server Not Connected. Configure Database Server First !" style="color:red;font-size:12px;align:center"/>
                </h:panelGrid>                
            </rich:panel>
            <rich:panel style="width:615px;margin-left:20px;">
                    <h:outputText value="Payroll System" style="font-size: 20px;align:center;"/>
                    <h:outputText value="| Tax Management | Leave management | Reports" style="font-size: 14px;align:center;"/>
            </rich:panel>
                <h:panelGrid columns="2">
                <h:panelGrid >
                    <h:graphicImage url="/img/myfile.png" style="margin-left: 70px;" width="250" height="150" alt="Logo"/>
                </h:panelGrid>
                <rich:panel header="User Login">
                   <f:facet name="footer">
                        <h:graphicImage value="/img/ilogo.png" style="cursor:pointer" onclick="Richfaces.hideModalPanel('themepanel')" />
                    </f:facet>
                    
                    <h:outputText value="" rendered="#{CommonDBBean.connected}"/>
                    <h:form>
                        <rich:messages>
                             <f:facet name="errorMarker">
                                <h:graphicImage url="/img/err.png"/>
                              </f:facet>
                        </rich:messages>
                        <h:panelGrid columns="2">
                            <h:outputText value="Organization Name"/>
                            <h:selectOneMenu value="#{UserBean.userOrgCode}">
                                <f:selectItems value="#{OrgController.items}"/>
                            </h:selectOneMenu>
                        
                            <h:outputText value="Username"/>
                            <h:inputText label="User Name" value="#{UserBean.userName}" />
                        
                            <h:outputText value=" Password:"/>
                            <h:inputSecret label="Password" value="#{UserBean.password}"/>
                        </h:panelGrid>
                        <rich:separator/>
                        <h:panelGrid columns="2">
                            <h:outputText value="Developed By SMVDU"/>
                            <h:outputText value="Developers@smvdu.ac.in"/>
                        </h:panelGrid>
                        <rich:separator/>
                        <h:commandButton action="#{UserBean.validate}" value="Login" />                        
                    </h:form>
                </rich:panel>
                </h:panelGrid>
        </f:view>
    </div>
</html>