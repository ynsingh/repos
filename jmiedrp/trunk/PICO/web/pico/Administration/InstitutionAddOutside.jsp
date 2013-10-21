<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
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
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">

                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">INSTITUTION MANAGEMENT</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmInstitution" action="SaveInstitutionAction"  validate="true">
                        <s:hidden name="ImId" />
                        <table border="0" cellpadding="4" cellspacing="0" align="center">
                            <tbody>
                                <tr>
                                    <td colspan="2" align="left">
                                </tr>
                                <tr>
                                    <td> <br><br>
                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="100"
                                                     label="Institution Name" name="im.ImName" title="Enter InstitutionName" />
                                        <s:select label="Institution Type" name="InstitutionType" headerKey="" headerValue="-- Please Select --" list="institutiontypeList" listKey="itTypeId" listValue="itName"/>
                                        <s:textfield required="true" requiredposition="left" maxLength="10" size="10"
                                                     label="Institution Short Name" name="im.ImShortName" title="Enter Short Name for InstitutionName" />
                                        <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                     label="Institution Address" name="im.ImAddressLine1" title="Enter Institution Address" />
                                        <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                     name="im.ImAddressLine2" title="Enter Institution Address" />
                                        <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                     label="District" name="im.ImDistrict" title="Enter Institution Address" />
                                        <s:select label="State" name="StateCode" headerKey="" headerValue="-- Please Select --" list="statemasterList" listKey="stateId" listValue="stateName"/>
                                        <s:textfield required="true" requiredposition="left" maxLength="6" size="6"
                                                     label="Pin Code" name="im.ImPinNo" title="Enter Institution Address" />
                                        <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                     label="Institution E-Mail" name="im.ImEmailId" title="Enter Institution E-Mail Address" />
                                        <s:hidden name="EntryOn" value="true" />
                                    </td>
                                </tr> <tr>
                                    <td>
                                        <s:submit theme="simple" name="btnSubmit" value="Save Institution"  />
                                    </td>
                                    <td>
                                        <s:submit theme="simple" name="bthReset" value="Clear" onclick="javascript:clearBudgettypemasterFields();"  />
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
