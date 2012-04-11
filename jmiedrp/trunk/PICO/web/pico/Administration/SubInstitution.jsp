<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

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
               <s:form name="frmSubInstitution" action="SaveSubInstitutionAction"  validate="true">
                   <s:hidden name="sim.simId" />
                   <s:hidden name="cm.committeeId"/>
                   <s:hidden name="cm.departmentmaster.dmId"/>
                   <s:hidden name="cm.institutionmaster.imId"/>
                   <s:hidden name="cm.erpmGenMaster.erpmgmEgmId"/>
                   <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td colspan="2" align="left">
                            </tr>
                            <tr>
                                <td valign="middle" class="FormContent">
                                    <s:label value="COLLEGE/FACULTY/SCHOOL MANAGEMENT"  cssClass="pageHeading"/>
                                </td>
                            </tr>
                            <tr>
                                <td> <br><br>
                                 <%--   <s:url action="BrowseSubInstitutions" id="NavigatetoURL"></s:url>
                                    <a href='<s:property value="NavigatetoURL"/>'>Browse Sub-Institutions</a>--%>
                                    <br>




    <s:select label="Institution" required="true" name="sim.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="simImIdList" listKey="imId" listValue="imName" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="College/Faculty/School Name" name="sim.simName" title="Enter College/Faculty/School"  cssClass="textInput"/>
                                    <s:select label="College/Faculty/School Head Name" required="true" name="sim.employeemaster.empId" headerKey="" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname"
                                               onchange="getEmployeeEmail('SaveSubInstitutionAction_sim_employeemaster_empId','SaveSubInstitutionAction_sim_simEmailId')" cssClass="textInput"/>

                                    <s:select label="Type" required="true" name="sim.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="simTypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="10" size="10"
                                                 label="Sub Institution Short Name" name="sim.simShortName" title="Enter Short Name for InstitutionName" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Sub Institution Address" name="sim.simAddressLine1" title="Enter Address" cssClass="textInput"/>
                                    <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                 name="sim.simAddressLine2" title="Enter Institution Address"  cssClass="textInput"/>

                                    <s:select label="Country" required="true" name="sim.countrymaster.countryId" headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"
                onchange="getStateList('SaveSubInstitutionAction_sim_countrymaster_countryId','SaveSubInstitutionAction_sim_statemaster_stateId')" cssClass="textInput"/>
                                   <s:select label="State" required="true"  name="sim.statemaster.stateId" headerKey="" headerValue="-- Please Select --" list="statemasterList" listKey="stateId" listValue="stateName" cssClass="textInput"/>
                                    <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                 label="District" name="sim.simDistrict" title="Enter District "  cssClass="textInput"/>
                                    <s:textfield required="false" requiredposition="left" maxLength="6" size="6"
                                                 label="Pin Code" name="sim.simPinNo" title="Enter Pin Code" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Sub Head Designation " name="sim.simHeadDesignation" title="Enter Head Designation"  cssClass="textInput"/>

                                    <s:textfield required="false" requiredposition="left" maxLength="30" size="30"
                                                 label="Phone" name="sim.simPhone" title="Enter Phone" cssClass="textInput"/>

                                    <s:textfield required="false" requiredposition="left" maxLength="30" size="30"
                                                 label="Fax " name="sim.simFax" title="Enter Fax" cssClass="textInput"/>

                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Head E-Mail" name="sim.simEmailId" title="Enter Subinstitue's E-Mail " cssClass="textInput"/>


                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Sub Institution" />
                                </td>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Browse Sub Institution"  action="BrowseSubInstitutions"/>
                                    <s:submit theme="simple" name="bthReset" value="Clear" onclick="ClearSubInstitutionFields();"/>
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