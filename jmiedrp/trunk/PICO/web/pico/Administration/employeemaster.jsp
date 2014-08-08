<%--
      I18n By    : Mohd. Manauwar Alam
                 : Jan 2014
--%>


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

            <div id ="mainContent">

                <br><br>
                <div style ="background-color: #215dc6;">
<%--                    <p align="center" class="pageHeading" style="color: #ffffff">EMPLOYEE MASTER SCREEN</p> --%>
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Administration.EmpMastScreen')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

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
                          <%--              <s:select label="Institution" required="true" name="em.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName" cssClass="textInput"  --%>
                                        <s:select key="Administration.InstitutionName" required="true" name="em.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName" cssClass="textInput"
                                                  onchange="getSubinstitutionList('SaveEmployeeAction_em_institutionmaster_imId', 'SaveEmployeeAction_em_subinstitutionmaster_simId');"/>

                            <%--            <s:select label="College/Faculty/School" required="true" name="em.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName"--%>
                                        <s:select key="Administration.SubinstitutionName" required="true" name="em.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName"
                                                  onchange="getDepartmentForAdminList('SaveEmployeeAction_em_subinstitutionmaster_simId','SaveEmployeeAction_em_departmentmaster_dmId')" cssClass="textInput"/>

                               <%--         <s:select label="Department" required="true" name="em.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" cssClass="textInput"/> --%>
                                        <s:select key="Administration.DepartmentName" required="true" name="em.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" cssClass="textInput"/>

                              <%--          <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     label="First Name" name="em.empFname" title="Enter Employee's First Name"  cssClass="textInput"/>

                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     label="Middle Name" name="em.empMname" title="Enter Employee's Middle Name"  cssClass="textInput"/>

                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     label="Last Name" name="em.empLname" title="Enter Employee's Last Name"  cssClass="textInput"/>

                                        <s:select label="Designation" name="em.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="designationList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>

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
                                    <s:submit theme="simple" name="btnSubmit" value="Save Employee" cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="btnReport"  value="Export Data" cssClass="textInput" action="PrintEmployees"/>

                                    <s:submit theme="simple" name="btnSubmit" value="Browse" action="BrowseEmployee"  cssClass="textInput" />

                                    <s:submit theme="simple" name="btnSubmit" value="Clear" action="ClearEmployee"  cssClass="textInput" /> --%>
                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     key="Administration.FirstName" name="em.empFname" title="Enter Employee's First Name"  cssClass="textInput"/>

                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     key="Administration.MiddleName" name="em.empMname" title="Enter Employee's Middle Name"  cssClass="textInput"/>

                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     key="Administration.LastName" name="em.empLname" title="Enter Employee's Last Name"  cssClass="textInput"/>

                                        <s:select key="Administration.Designation" name="em.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="designationList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>

                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     key="Administration.EMail" name="em.empEmail" title="Enter Employee's E-mail Address"  cssClass="textInput"/>

                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     key="Administration.MobileNo" name="em.empMobile" title="Enter Employee's Mobile Number"  cssClass="textInput"/>

                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     key="Administration.PhoneNumber" name="em.empLandLine" title="Enter Employee's Land Line Number"  cssClass="textInput"/>

                            <sx:datetimepicker name="em.empDob" label="Date-of-Birth(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>

                            <sx:datetimepicker name="em.empDoj" label="Date-of-Joining(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>

                            <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                         key="Administration.Gender" name="em.empGender" title="Enter Employee's Gender"  cssClass="textInput"/>
                            </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" key="Administration.Save" cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="btnReport"  key="Administration.Print" cssClass="textInput" action="PrintEmployees"/>

                                    <s:submit theme="simple" name="btnSubmit" key="Administration.Browse" action="BrowseEmployee"  cssClass="textInput" />

                                    <s:submit theme="simple" name="btnSubmit" key="Administration.Clear" action="ClearEmployee"  cssClass="textInput" />
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>
            <div id="footer" >
                <jsp:include page="footer.jsp" flush="true" ></jsp:include>
            </div>
        </div>
    </body>
</html>
