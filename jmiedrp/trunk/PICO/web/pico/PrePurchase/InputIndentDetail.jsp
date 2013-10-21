<%-- 
    Document   : InputIndentDetail
    Created on : 9 Oct, 2012, 10:24:54 AM
    Author     : erp02
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Tanvir Ahmed, Jamia Millia Islamia">
        <meta name="email" content="tanvirahmed74@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />


    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br>
                <br>
                <div style ="background-color: #215dc6;">
                    <p align="center"><s:label cssClass="pageHeading" value="INPUT INDENT DETAILS" style="color: #ffffff" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <%--------------------this is a Purchase Challan Form --------------------%>

                <s:actionerror />
                <div style="border: solid 1px #000000; background: gainsboro">
                    <s:form name="FrmInputIndent" action="InputIndent" theme="qxhtml">
                       

                        <table border="0" cellpadding="4" cellspacing="0" align="center" >
                            <tbody>

                   <s:select label="Institution" name="institution" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" 
                      onchange="getSubinstitutionList('InputIndent_institution', 'InputIndent_subinstitution');" ondblclick="getInsituteaftervalidation('InputIndent_institution');">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
                   </s:select>

                                           
                   <s:select  label="College/Faculty/School" name="subInstitution" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName"
                      onchange="getDepartmentList('InputIndent_subInstitution', 'InputIndent_department');">
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
                   </s:select>

                   <s:select  label="Department Name" name="department"
                      headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"
                       ondblclick="getDepartmentAfterValidation('InputIndent_department');">
                      <s:param name="labelcolspan" value="%{1}" />
                      <s:param name="inputcolspan" value="%{7}" />
                   </s:select>

                  <s:textfield  maxLength="50" size="30"  label="First Date(dd-mm-yyyy)" name="firstDate" required="true" title="Enter the date " >
                      <s:param name="labelcolspan" value="%{1}" />
                      <s:param name="inputcolspan" value="%{3}" />
                  </s:textfield>

                  <s:textfield  maxLength="50" size="30" label="Last Date(dd-mm-yyyy)" name="lastDate" required="true" title="Enter the date " >
                      <s:param name="labelcolspan" value="%{1}" />
                      <s:param name="inputcolspan" value="%{3}" />
                  </s:textfield>
                         <tr>
                     </tr> <tr>
                          <td align="left">
                            <s:submit theme="simple" name="btnSubmit" value="Export as PDF" action="SubmitIndentDetail"/>
                         </td>
                                    
                                </tr>
                            </tbody>
                        </table

                    </s:form>
                </div>
            </div>
            <br>
            <div id="footer" >
                <jsp:include page="../Administration/footer.jsp" flush="true" ></jsp:include>
            </div>
        </div>
    </body>
</html>
