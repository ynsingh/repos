<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>




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
            <br><br>
            <p align="center"><s:label value="EMPLOYEE MASTER SCREEN"  cssClass="pageHeading"/>
            <p align="center"><s:property value="message" /></p>

            <div id ="mainContent">
               <s:form name="frmEmployee" action="SaveEmployeeAction"  validate="true">
                   <s:hidden name="em.empId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                               <tr>
                                <td valign="middle" class="FormContent">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <br> <br>
                                    <%--<s:submit theme="simple" name="btnSubmit" value="Browse Employee"  action="BrowseDepartments" cssClass="textInput"/>--%>
                                    <%--<s:url action="BrowseDepartments" id="NavigatetoURL"></s:url>
                                    <a href='<s:property value="NavigatetoURL"/>'>Browse Departments</a>--%>

<%--                                    <s:select label="Institution" name="em.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName"
                                    onchange="getSubinstitutionAndEmployeeList('SaveEmployeeAction_em_institutionmaster_imId', 'SaveEmployeeAction_em_subinstitutionmaster_simId');" cssClass="textInput" />
                                    <s:select label="College/Faculty/School" name="em.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName" cssClass="textInput"/>
                                    <s:select label="Department" name="em.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" cssClass="textInput"/>
--%>


                      


                                    <s:select label="Institution" name="em.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName" cssClass="textInput" />
                                    <%-- onchange="getSubinstitutionAndEmployeeList('SaveEmployeeAction_em_institutionmaster_imId', 'SaveEmployeeAction_em_subinstitutionmaster_simId');" cssClass="textInput" /> --%>
                                    <s:select label="College/Faculty/School" name="em.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName"
                                              onchange="getDepartmentForAdminList('SaveEmployeeAction_em_subinstitutionmaster_simId','SaveEmployeeAction_em_departmentmaster_dmId')" cssClass="textInput"/>
                                    <s:select label="Department" name="em.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" cssClass="textInput"/>

                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="First Name" name="em.empFname" title="Enter Employee's First Name"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Middle Name" name="em.empMname" title="Enter Employee's Middle Name"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Last Name" name="em.empLname" title="Enter Employee's Last Name"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="E-Mail " name="em.empEmail" title="Enter Employee's E-mail Address"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Mobile No" name="em.empMobile" title="Enter Employee's Mobile Number"  cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Land Line No" name="em.empLandLine" title="Enter Employee's Land Line Number"  cssClass="textInput"/>
                                    <sx:datetimepicker name="em.empDob" label="Date-of-Birth(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>
                                    <sx:datetimepicker name="em.empDoj" label="Date-of-Joining(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                 label="Gender" name="em.empGender" title="Enter Employee's Gender"  cssClass="textInput"/>                                    
                                </td>
                            </tr> 
                            <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Employee"   cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Clear" action="ClearEmployee"  cssClass="textInput" />

                                    <s:submit theme="simple" name="btnSubmit" value="Browse" action="BrowseEmployee"  cssClass="textInput" />
                                </td>
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