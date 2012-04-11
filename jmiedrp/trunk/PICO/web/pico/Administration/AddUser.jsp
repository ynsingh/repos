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
                <s:form name="frmAddUser" action="AddUserAction">
                   <s:hidden name="erpmuId" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td colspan="2" align="left">
                            </tr>
                            <tr>
                                <td valign="middle" class="FormContent">
                                    <s:label value="USER REGISTRATION" />
                                    <s:property  value="message" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <br>
                                    <s:select label="Institution" name="erpmur.institutionmaster.imId" headerKey="0" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName"
                                              onchange="SetInstitutionDependenetLists('AddUserAction_erpmur_institutionmaster_imId','AddUserAction_erpmur_subinstitutionmaster_simId','AddUserAction_erpmur_institutionuserroles_iurId');"
                                              ondblclick="SetInstitutionList('AddUserAction_erpmur_institutionmaster_imId')"/>
                                    <s:select label="College/Faculty/School" name="erpmur.subinstitutionmaster.simId" headerKey="0" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName"
                                              onchange="getDepartmentList('AddUserAction_erpmur_subinstitutionmaster_simId','AddUserAction_erpmur_departmentmaster_dmId')"/>
                                    <s:select label="Department" name="erpmur.departmentmaster.dmId" headerKey="0" headerValue="-- Please Select --" list="dmIdList" listKey="dmId" listValue="dmName" />
                                    <s:textfield requiredposition="left" maxLength="50" size="50"
                                                 label="User Name (E-Mail Address)" name="erpmusers.erpmuName" title="Enter User Name (E-Mail Address)" />
                                    <s:password requiredposition="left" maxLength="50" size="50"
                                                 label="Password" name="erpmusers.erpmuPassword" title="Enter password" />
                                    <s:password requiredposition="left" maxLength="50" size="50"
                                                 label="Retype the Password" name="RetypedPassword" title="Reenter password" />
                                    <s:textfield requiredposition="left" maxLength="50" size="50"
                                                 label="Full Name " name="erpmusers.erpmuFullName" title="Enter your full Name" />
                                    <sx:datetimepicker name="erpmusers.erpmuDob" label="Enter Dte of Birth Format(dd-MMM-yyyy)"
                                                 displayFormat="dd-MMM-yyyy" value="%{'today'}" />
                                    <s:textfield requiredposition="left" maxLength="100" size="100"
                                                 label="Secret Question" name="erpmusers.erpmuSecretQuestion" title="Enter a secret question" />
                                    <s:textfield requiredposition="left" maxLength="100" size="100"
                                                 label="Answer to Secret Question" name="erpmusers.erpmuSecretAnswer" title="Enter your answer to secret question" />
                                    <s:select label="Institutional User Role" name="erpmur.institutionuserroles.iurId" headerKey="0" headerValue="-- Please Select --" list="iurIdList" listKey="iurId" listValue="iurName" required="true"/>
                                    <s:checkbox name="erpmur.erpmurDefault"  labelposition="left" label="Is this your default role?" value="1"/>
                                </td>
                            </tr> <tr>
                                <td></td><br><td>
                                    <s:submit theme="simple" name="btnSubmit" value="Register User"  />
                                    <s:reset theme="simple" name="bthReset" id="btnReset" value="Clear" action = "AddUser"/>
                                    <s:submit theme="simple" name="btnSubmit" value="Exit"   action="Index"/>
                        </td><td></td>
                            <tr><td><br></td></tr>
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