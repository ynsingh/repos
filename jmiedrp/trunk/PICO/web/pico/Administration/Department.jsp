<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
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
           
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
                               <p align="center"><s:label value="DEPARTMENT RECORD MANAGEMENT"  cssClass="pageHeading"/>
                               <p align="center"><s:property value="message" /></p>

            <div id ="mainContent">
               <s:form name="frmDepartment" action="SaveDepartmentAction"  validate="true">
                   <s:hidden name="dm.dmId" />
                  
                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                               <tr>
                                <td valign="middle" class="FormContent">
                              
                                </td>
                            </tr>
                            <tr>
                                <td> 
                                    <br> <br><s:submit theme="simple" name="btnSubmit" value="Browse Department"  action="BrowseDepartments" cssClass="textInput"/>
                                    <%--<s:url action="BrowseDepartments" id="NavigatetoURL"></s:url>
                                    <a href='<s:property value="NavigatetoURL"/>'>Browse Departments</a>--%>
                                    <s:select label="Institution" name="dm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName"
                                    onchange="getSubinstitutionList('SaveDepartmentAction_dm_institutionmaster_imId', 'SaveDepartmentAction_dm_subinstitutionmaster_simId');" cssClass="textInput"/>
                                    <s:select label="College/Faculty/School" name="dm.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Department Name" name="dm.dmName" title="Enter Department Name"  cssClass="textInput"/>
                                    <s:select label="Department Head Name" required="true" name="dm.employeemaster.empId" headerKey="" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="10" size="10"
                                                 label="Department Short Name" name="dm.dmShortName" title="Enter Short Name for InstitutionName"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Department Address" name="dm.dmAddressLine1" title="Enter Address" cssClass="textInput"/>
                                    <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                 name="dm.dmAddressLine2" title="Enter Department's Address" cssClass="textInput" />
                                   


                                    <s:select label="Country" required="true" name="dm.countrymaster.countryId" headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"
                onchange="getStateList('SaveDepartmentAction_dm_countrymaster_countryId','SaveDepartmentAction_dm_statemaster_stateId')"  cssClass="textInput"/>


                                    <s:select label="State" name="dm.statemaster.stateId" headerKey="" headerValue="-- Please Select --" list="statemasterList" listKey="stateId" listValue="stateName" cssClass="textInput"/>

                                     <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                 label="District" name="dm.dmDistrict" title="Enter District "  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="6" size="6"
                                                 label="Pin Code" name="dm.dmPinNo" title="Enter Pin Code"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Department E-Mail" name="dm.dmEmailId" title="Enter Department's E-Mail Address" cssClass="textInput"/>

                                
                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Department"   cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear" onclick="ClearDepartmentFields();"  cssClass="textInput" />
                                <td>
                            </tr>
                            <tr>
                    <td> <br><br> </td>
                </tr>
                 <tr>
                    
                </tr>

                        </tbody>
                    </table>
                </s:form>
            </div>
            <div id="footer" >
                <jsp:include page="footer.jsp" flush="true" ></jsp:include>
            </div>
        </div>
    </body>
</html>