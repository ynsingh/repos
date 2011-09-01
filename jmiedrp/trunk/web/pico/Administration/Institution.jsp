<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <s:property value="message" />
           <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">
               <s:form name="frmInstitution" action="SaveInstitutionAction"  validate="true">
                   <s:hidden name="im.imId" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td colspan="2" align="left">                                    
                            </tr>
                            <tr>
                                <td valign="middle" class="FormContent">
                                    <s:label value="INSTITUTION RECORDS MANAGEMENT"  cssClass="pageHeading"/>
                                </td>
                            </tr>
                            <tr>
                                <td> <br><br>
                                     <%--<s:submit theme="simple" name="btnSubmit" value="Browse Institutions" action="BrowseInstitutions" />--%>
                                  <s:url action="BrowseInstitutions" id="NavigatetoURL" ></s:url>
                                    <a href='<s:property value="NavigatetoURL"/>'>Browse Institutions</a>
                                    <br>
                                  <s:textfield requiredposition="left" maxLength="100" size="100"
                                                 label="Institution Name" required="true" name="im.ImName" title="Enter InstitutionName" cssClass="textInput" />
                                  <s:select label="Institution Head Name" required="true" name="im.employeemaster.empId" headerKey="" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname" cssClass="textInput"/>

                                   <s:select label="Institution Type" required="true" name="im.erpmgmInstitutiontype.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="institutiontypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>
                                   <s:textfield required="true" requiredposition="left" maxLength="10" size="10"
                                                 label="Institution Short Name" name="im.ImShortName" title="Enter Short Name for InstitutionName"  cssClass="textInput"/>
                                   <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Institution Address" name="im.ImAddressLine1" title="Enter Institution Address" cssClass="textInput" />
                                   <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                 name="im.ImAddressLine2" title="Enter Institution Address"  cssClass="textInput"/>
                                   
               <s:select label="Country" required="true" name="im.countrymaster.countryId" headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"
                onchange="getStateList('SaveInstitutionAction_im_countrymaster_countryId','SaveInstitutionAction_im_statemaster_stateId')"  cssClass="textInput"/>
               <s:select label="State" required="true" name="im.statemaster.stateId" headerKey="" headerValue="-- Please Select --" list="statemasterList" listKey="stateId" listValue="stateName" cssClass="textInput"/>

               <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                 label="District" name="im.ImDistrict" title="Enter Institution Address"  cssClass="textInput"/>
               <s:textfield required="false" requiredposition="left" maxLength="6" size="6"
                 label="Pin Code" name="im.ImPinNo" title="Enter Institution Address"  cssClass="textInput"/>
               <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                           label="Institution E-Mail" name="im.ImEmailId" title="Enter Institution E-Mail Address" cssClass="textInput" />
               </td>
               </tr> <tr>
               <td>
                   <s:submit theme="simple" name="btnSubmit" value="Save Institution"  cssClass="textInput"/>
               </td>
               <td>
               <s:submit theme="simple" name="bthReset" value="Clear" onclick="ClearInstitutionFields();" cssClass="textInput"/>
               <td>
               </tr>
               <tr>
              <td> <br><br> </td>
              </tr>
              <tr>
              <td> <br><br> </td>
               </tr>
              </tbody>
              </table>
              </s:form>
              </div>
              <div id="footer">
              <jsp:include page="footer.jsp" flush="true"></jsp:include>
              </div>
              </div>
              </body>
              </html>