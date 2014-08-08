<%--
    Document        : AddUser.jsp
    Created on      : 3:02 AM Saturday, October 02, 2010
    Last Modified   : 6:00 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Add New Company / Institute</title>
        </head>
        <body>
            <rich:panel header="Create Company/Institute Profile">
                <a href="../Login.jsf" style="font-weight:bold; font-size:15px;">Home</a>
                <h:form id="frmTest">
                   <rich:panel id="up">

                    <%--</rich:panel>--%>
                    <rich:messages>
                        <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                            <h:outputText value="kk"/>
                        </f:facet>
                    </rich:messages>
                    <h:panelGrid id="pnl" columns="3">

                        <h:column><h:outputText value="Institute Name"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column><h:inputText id="instName" requiredMessage="Name cannot be empty" required="true" value="#{OrgProfileBean.name}"/></h:column>
                        <h:column><h:message for="instName" /></h:column>



                        <%--           <h:outputText value="Institute Tag Line" />
                                   <h:inputText id="insttag" value="#{OrgProfileBean.tagLine}"/>
                                   <h:message for="insttag" />    --%>

                        <h:column>   <h:outputText value="Institute Address"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>   <h:inputText id="insta1" value="#{OrgProfileBean.address1}"/></h:column>
                        <h:column>   <h:message for="insta1" /></h:column>

                        <%--               <h:outputText value="Institute Address(Line 2)"/>
                                      <h:inputText id="insta2" value="#{OrgProfileBean.address2}"/>
                                      <h:message for="insta2" />    --%>

                        <h:column>    <h:outputText value="City"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>    <h:inputText id="instc" value="#{OrgProfileBean.city}"/></h:column>
                        <h:column>    <h:message for="instac" /></h:column>

                        <h:column>    <h:outputText value="Pincode"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>    <h:inputText id="instpc" value="#{OrgProfileBean.pincode}"/></h:column>
                        <h:column>    <h:message for="instpc" /></h:column>

                        <h:column>     <h:outputText value="State"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>     <h:inputText id="insts" requiredMessage="State cannot be empty" required="true"  value="#{OrgProfileBean.state}"/></h:column>
                        <h:column>     <h:message for="insts" /></h:column>

                        <h:column>    <h:outputText value="Landline No."/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>    <h:inputText id="instln" value="#{OrgProfileBean.ll}"/></h:column>
                        <h:column>    <h:message for="instln" /></h:column>

                        <h:outputText value="Country Code"/>
                        <h:inputText id="instcc" value="#{OrgProfileBean.countryCode}"/>
                        <h:message for="instcc" />

                        <h:column>                <h:outputText value="Region Code"/><h:outputText value="*    Ex. For Jammu(0191)" style="color:red;"/></h:column>
                        <h:column>                <h:inputText id="instrc" value="#{OrgProfileBean.regionCode}"/></h:column>
                        <h:column>                 <h:message for="instrc" /></h:column>

                        <h:column>     <h:outputText value="Number"/><h:outputText value="*    Ex. For SMVDU(2470067)" style="color:red;"/></h:column>
                        <h:column>     <h:inputText id="instph" requiredMessage="Number cannot be empty" required="true"   value="#{OrgProfileBean.phone}"/></h:column>
                        <h:column>     <h:message for="instph" /></h:column>

                        <h:column>     <h:outputText value="Institute Domain"/><h:outputText value="*  @" style="color:red;"/></h:column>
                        <h:column>     <h:inputText id="instidn" requiredMessage="Domain cannot be empty" required="true"  value= "#{OrgProfileBean.instDomain}"/></h:column>
                        <h:column>     <h:message for="instidn" /></h:column>

                        <h:outputText value="Type of Institute"/>
                        <h:selectOneMenu value="#{OrgProfileBean.toi}">
                            <f:selectItem itemLabel="Govt." itemValue="Govt."/>
                            <f:selectItem itemLabel="Semi-Govt" itemValue="Semi-Govt"/>
                            <f:selectItem itemLabel="Self-financed" itemValue="Self-financed"/>
                            <f:selectItem itemLabel="Other" itemValue="Other"/>
                        </h:selectOneMenu>
                        <h:message for="insttoi" />

                        <h:column>                <h:outputText value="Institute Website"/><h:outputText value="* http://" style="color:red;"/></h:column>
                        <h:column>               <h:inputText id="instweb" requiredMessage="Website cannot be empty" required="true"  value="#{OrgProfileBean.web}"/></h:column>
                        <h:column>               <h:message for="instweb" /></h:column>

                        <h:column>       <h:outputText value="Affiliation"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>       <h:inputText id="instan" value="#{OrgProfileBean.affiliation}"/></h:column>
                        <h:column>       <h:message for="instan" /></h:column>

                        <h:column>       <h:outputText value="Admin's First Name"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>       <h:inputText id="instafn" value="#{OrgProfileBean.adminfn}"/></h:column>
                        <h:column>       <h:message for="instafn" /></h:column>

                        <h:column>      <h:outputText value="Admin's Last Name"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputText id="instaln" value="#{OrgProfileBean.adminln}"/></h:column>
                        <h:column>      <h:message for="instaln" /></h:column>

                        <h:column>      <h:outputText value="Admin's Designation"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputText id="instd" value="#{OrgProfileBean.adminDesig}"/></h:column>
                        <h:column>      <h:message for="instd" /></h:column>

                        <h:column>      <h:outputText value="Admin's E-Mail ID"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputText id="instrec" requiredMessage="E mail cannot be empty" required="true" value="#{OrgProfileBean.email}"/></h:column>
                        <h:column>      <h:message for="instrec" /></h:column>

                        <h:column>      <h:outputText value="Admin's Password"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputSecret id="instpass" requiredMessage="Password Cant be Empty" required="true" value="#{OrgProfileBean.masterPassword}"/></h:column>
                        <h:column>      <h:message for="instpass" /></h:column>

                        <h:column>      <h:outputText value="Admin's Re type Password"/><h:outputText value="*" style="color:red;"/></h:column>
                        <h:column>      <h:inputSecret id="instpass2" requiredMessage="Retype Password Cant be Empty" required="true" value="#{OrgProfileBean.vpass}"/></h:column>
                        <h:column>      <h:message for="instpass2" /></h:column>  



                        <%--                         <h:outputText value="Recovery E-Mail"/>
                                               <h:inputText id="instmail" value="#{OrgProfileBean.email}"/>
                                               <h:message for="instmail" />     --%>

                        <%--             <h:column>        <h:outputText value="Tan No."/><h:outputText value="*" style="color:red;"/></h:column>
                               <h:column>        <h:inputText id="tanno" value="#{OrgProfileBean.tanno}"/></h:column>
                               <h:column>        <h:message for="tanno" /></h:column>                                      --%>


                        
                    <h:outputText value=""/>
                    </h:panelGrid>
                    <a4j:commandButton value="Save" reRender="up" action="#{OrgProfileBean.save}"/>&nbsp;
                    <a4j:commandButton value="Reset" onclick="this.form.reset()"/>
                    </rich:panel>
                    </h:form>
                   </rich:panel>
        </body>
    </html>
</f:view>