<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <s:property value="message" />
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">
               <s:form name="frmAddUser" action="AddAdministratorAction" >
                   <s:hidden name="erpmuId" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td colspan="2" align="left">
                            </tr>
                            <tr>
                                <td valign="middle" class="FormContent">
                                    <s:label value="INSTITUTE ADMINISTRATOR REGISTRATION" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <br>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Institution Name" name="im.imName" title="Enter Institution Name"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="10" size="10"
                                                 label="Institution Short Name" name="im.imShortName" title="Enter Short Name for Institution"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="College/Faculty/School Name" name="sim.simName" title="Enter College/Faculty/School Name"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="10" size="10"
                                                 label="College/Faculty/School Short Name" name="sim.simShortName" title="Enter Short Name for Institution"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Department Name" name="dm.dmName" title="Enter Department Name"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="10" size="10"
                                                 label="Department Short Name" name="dm.dmShortName" title="Enter Short Name for InstitutionName"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Address" name="im.imAddressLine1" title="Enter Address" cssClass="textInput"/>
                                    <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                 name="im.imAddressLine2" title="Enter Institute's Address" cssClass="textInput" />
                                    <s:select label="Country" required="true" name="im.countrymaster.countryId" headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"
                                              onchange="getStateList('AddAdministratorAction_im_countrymaster_countryId','AddAdministratorAction_im_statemaster_stateId')" ondblclick="getCountryList('AddAdministratorAction_im_countrymaster_countryId');"  cssClass="textInput"/>
                                    <s:select label="State" name="im.statemaster.stateId" headerKey="" headerValue="-- Please Select --" list="statemasterList" listKey="stateId" listValue="stateName" cssClass="textInput"/>
                                    <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                 label="District" name="im.imDistrict" title="Enter District "  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="6" size="6"
                                                 label="Pin Code" name="im.imPinNo" title="Enter Pin Code"  cssClass="textInput"/>
                                    <s:textfield requiredposition="left" maxLength="50" size="50" required="true"
                                                 label="User Name (E-Mail Address)" name="erpmusers.erpmuName" title="Enter User Name (E-Mail Address)" />
                                    <s:password requiredposition="left" maxLength="50" size="50" required="true"
                                                 label="Password" name="erpmusers.erpmuPassword" title="Enter password" />
                                   <s:password requiredposition="left" maxLength="50" size="50" required="true"
                                                 label="Retype the Password" name="RetypedPassword" title="Reenter password" />
                                   <s:textfield requiredposition="left" maxLength="50" size="50" required="true"
                                                 label="Full Name " name="erpmusers.erpmuFullName" title="Enter your full Name" />
                                   <sx:datetimepicker name="erpmusers.erpmuDob" label="Enter Date of Birth Format(dd-MMM-yyyy)" required="true"
                                                 displayFormat="dd-MMM-yyyy" value="%{'today'}" />
                                   <s:textfield requiredposition="left" maxLength="100" size="100" required="true"
                                                 label="Secret Question" name="erpmusers.erpmuSecretQuestion" title="Enter a secret question" />
                                   <s:textfield requiredposition="left" maxLength="100" size="100" required="true"
                                                 label="Answer to Secret Question" name="erpmusers.erpmuSecretAnswer" title="Enter your answer to secret question" />

                                </td>
                            </tr> <tr>
                                <td> </td><td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save"  />
                                    <s:reset theme="simple" name="bthReset" id="btnReset" value="Clear"/>
                                </td>
                                <td>
                                <td>
                            </tr>
                            <tr>
                </tr>
                 <tr>
                    <td> <br><br> </td>
                </tr>

                        </tbody>
                    </table>
                </s:form>
                <s:actionerror />
            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>