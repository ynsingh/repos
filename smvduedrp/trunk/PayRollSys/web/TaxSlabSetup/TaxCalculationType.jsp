<%-- 
    Document   : TaxCalculationType
    Created on : 16 Jul, 2015, 3:10:44 PM
    Author     : shikhar, Manorama Pal 
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
        <title>Tax Calculation Type</title>
        
        <script type="text/javascript">
            <%--function radioButton(radio){
                var id = radio.name.substring(radio.name.lastIndexOf(':'));
                alert("id of radiobutton==="+id);
                var el = radio.form.elements;
                alert("el of radiobutton==="+el);
                for (var i = 0; i < el.length; i++) {
                    if (el[i].name.substring(el[i].name.lastIndexOf(':')) == id) {
                        el[i].checked = false;
                        alert("el i== of radiobutton==="+el[i]);
                    }
                }
                radio.checked = true;

            }--%>

	</script>
    </head>
    <body class="subpage" >
        <f:view>
            <rich:panel header="Tax Calculation Type">
                <h:form id="frm"> 
                    <br>
                    <rich:panel style="" header="Important Points related to Income Tax">
                        <h:outputText value="Income tax calculation on yearly basis, that is default setting."/><br/>
                        <h:outputText value="Update according to yours choice that is YEARLY, QUARTLY and MONTHLY."/>
                        <br/>   
                        <br/>
                        <%--    <rich:panel  style="height:40px;center;">--%>
                        <rich:messages style="align:center;">
                            <f:facet name="infoMarker">
                            <h:graphicImage url="/img/success.png"/>
                            </f:facet>
                            <f:facet name="errorMarker">
                            <h:graphicImage url="/img/err.png"/>
                            </f:facet>
                        </rich:messages>
                    </rich:panel>
                    <h:column>
                        <%--    ${TaxCalculationType.calcType} ..... --%>
                        <h:selectOneRadio  required="true" value="#{TaxCalculationType.calcType}">
                            <f:selectItem itemValue="YEARLY" itemLabel="YEARLY"/>
                            <f:selectItem itemValue="QUATERLY" itemLabel="QUATERLY" />
                            <f:selectItem itemValue="MONTHLY" itemLabel="MONTHLY"/>
                            <a4j:support actionListener="#{TaxCalculationType.calcType}"  event="onclick" reRender="clickradio"/>
                        </h:selectOneRadio>
                    </h:column>
                    <br/>
                    <br>
                    <h:panelGrid> 
                        <a4j:commandButton reRender="frm" value="update" action="#{TaxCalculationType.update}" />
                    </h:panelGrid>
                
                </h:form>
            </rich:panel>
        </f:view>
        
    </body>
</html>
