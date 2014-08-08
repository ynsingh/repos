<%--
I18n By    : Mohd. Manauwar Alam
           : Jan 2014
--%>


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
        <s:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
                </div>
                <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
                </div>

                <jsp:include page="jobBar.jsp" flush="true"></jsp:include>
                <!-- *********************************End Menu****************************** -->

                <div id ="mainContent">
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
<%--                    <p align="center" class="pageHeading" style="color: #ffffff">INSTITUTE RECORDS MANAGEMENT</p> --%>
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Administration.InstRecMgmt')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <br>
                    <s:form name="frmInstitution" action="SaveInstitutionAction" theme="qxhtml">
                        <s:hidden name="im.imId" />

<%--		<s:textfield requiredposition="" maxLength="100" size="100" cssErrorStyle="true"
			     label="Institution Name" required="true"   name="im.ImName" title="Enter Institution Name" cssClass="queryInput"> --%>
                        <s:textfield requiredposition="" maxLength="100" size="100" cssErrorStyle="true" key="Administration.InstitutionName"
                                     required = "true"   name = "im.ImName" title = "Enter Institution Name" cssClass="queryInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

       <%--                 <s:select label="Institution Type" required="true" requiredposition=""  name="im.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="institutiontypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="queryInput"> --%>
                        <s:select  required="true" requiredposition="" key="Administration.InstType"
                                  name="im.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="institutiontypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

             <%--           <s:textfield required="true" requiredposition="" maxLength="10" size="10"
                                     label="Institution Short Name" name="im.ImShortName" title="Enter Short Name for InstitutionName"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:select label="Institution Head Name" required="true" requiredposition="" name="im.employeemaster.empId"
                                  headerKey="0" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:select>

                        <s:textfield required="true" requiredposition="" maxLength="50" size="100"
                                     label="Institution Address" name="im.ImAddressLine1" title="Enter Institution Address" cssClass="textInput" >
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:textfield required="false" requiredposition="" maxLength="50" size="100"
                                     label="Address Line-2" name="im.ImAddressLine2" title="Enter Institution Address"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:textfield required="false" requiredposition="" maxLength="50" size="50"
                                     label="District" name="im.ImDistrict" title="Enter Institution Address"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield required="false"  cssStyle="right" maxLength="6" size="18"
                                     label="Pin Code" name="im.ImPinNo" title="Enter Institution Address"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:select label="State" required="true" requiredposition="" name="im.statemaster.stateId" headerKey="" headerValue="-- Please Select --" list="statemasterList" listKey="stateId" listValue="stateName" cssClass="queryInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:select label="Country" required="true" requiredposition="" name="im.countrymaster.countryId" headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"
                                  onchange="getStateList('SaveInstitutionAction_im_countrymaster_countryId','SaveInstitutionAction_im_statemaster_stateId')"  cssClass="textInput" ondblclick="getCountryList('SaveInstitutionAction_im_countrymaster_countryId');">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:textfield required="true" requiredposition=""  maxLength="100" size="100"
                                     label="Institution E-Mail" name="im.ImEmailId" title="Enter Institution E-Mail Address" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <tr><td> &nbsp; </td></tr>
                        <s:submit value="Save Institution" >
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit name="btnSubmit" value="Browse Institution" action="BrowseInstitutions">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit value="Clear Screen" onclick="ClearInstitutionFields();" >
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit name="btnReport" value="Export Data" action="PrintInstitutions">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" /> --%>
                        <s:textfield required="true" requiredposition="" maxLength="10" size="10" key="Administration.InstShortName"
                                      name="im.ImShortName" title="Enter Short Name for InstitutionName"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:select  required="true" requiredposition="" name="im.employeemaster.empId" key="Administration.HeadsName"
                                  headerKey="0" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:select>

                        <s:textfield required="true" requiredposition="" maxLength="50" size="100" key="Administration.Address"
                                      name="im.ImAddressLine1" title="Enter Institution Address" cssClass="textInput" >
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:textfield required="false" requiredposition="" maxLength="50" size="100" key="Administration.AddressLine2"
                                      name="im.ImAddressLine2" title="Enter Institution Address"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:textfield required="false" requiredposition="" maxLength="50" size="50" key="Administration.District"
                                      name="im.ImDistrict" title="Enter Institution Address"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:textfield required="false"  cssStyle="right" maxLength="6" size="18" key="Administration.PinCode"
                                      name="im.ImPinNo" title="Enter Institution Address"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:select  required="true" requiredposition="" name="im.statemaster.stateId" key="Administration.State"
                                  headerKey="" headerValue="-- Please Select --" list="statemasterList" listKey="stateId" listValue="stateName" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:select  required="true" requiredposition="" name="im.countrymaster.countryId" key="Administration.Country"
                                  headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"
                                  onchange="getStateList('SaveInstitutionAction_im_countrymaster_countryId','SaveInstitutionAction_im_statemaster_stateId')"  cssClass="textInput" ondblclick="getCountryList('SaveInstitutionAction_im_countrymaster_countryId');">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:textfield required="true" requiredposition=""  maxLength="100" size="100" key="Administration.EMail"
                                      name="im.ImEmailId" title="Enter Institution E-Mail Address" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <tr><td> &nbsp; </td></tr>
                        <s:submit key="Administration.Save" >
                            <s:param name="colspan" value="%{3}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit name="btnSubmit" key="Administration.Browse" action="BrowseInstitutions">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit key="Administration.Clear" onclick="ClearInstitutionFields();" >
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit name="btnReport" key="Administration.Print" action="PrintInstitutions">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>

</html>


