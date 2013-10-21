<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Afreen Khan, Jamia Millia Islamia">
        <meta name="email" content="afreen.mca@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr" onload="SetInstitutionList('SaveUserProfileAction_erpmur_institutionmaster_imId')">
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
                    <p align="center" class="pageHeading" style="color: #ffffff">SETUP USER PROFILE FOR <s:property value="erpmuName"/></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <s:form name="frmUserProfileUser" action="SaveUserProfile">
                        <s:hidden name="erpmuName"/>
                        <table border="0" cellpadding="4" cellspacing="0" align="center">
                            <tbody>
                                <tr>
                                    <td colspan="2" align="right"><a href="Index.action">Go to Login Page</a></td>
                                </tr>
                                <tr>
                            <br>

                        </tr><tr>
                        <td>
                            <s:select label="Institution" name="erpmur.institutionmaster.imId" headerKey="0" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName"  onchange="SetInstitutionDependenetLists_UserProfile('SaveUserProfile_erpmur_institutionmaster_imId','SaveUserProfile_erpmur_subinstitutionmaster_simId')"/>
                        </td>
                        <%--<td>
                              <s:submit theme="simple" name="btnSubmit" value="If your institure is not listed, Click here"  />
                        </td>--%>
                        <s:select label="College/Faculty/School" name="erpmur.subinstitutionmaster.simId" headerKey="0" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName"/><%-- onchange="getDepartmentList('SaveUserProfile_erpmur_subinstitutionmaster_simId','SaveUserProfile_erpmur_departmentmaster_dmId')"/>--%>
                        <s:select label="Department" name="erpmur.departmentmaster.dmId" headerKey="0" headerValue="-- Please Select --" list="dmIdList" listKey="dmId" listValue="dmName" />
                        <s:select label="Institutional User Role" name="erpmur.institutionuserroles.iurId" headerKey="0" headerValue="-- Please Select --" list="iurIdList" listKey="iurId" listValue="iurName"/>
                        <s:checkbox label="Is this your default role?" name="erpmur.erpmurDefault" value="0"  />
                    </tr> <tr>
                        <td>
                            <s:submit theme="simple" name="btnSubmit" value="Save Profile "  />
                        </td><td>
                            <s:reset theme="simple" name="bthReset" id="btnReset" value="Reset"/>
                        <td>
                    </tr>
                    </tbody>
                </table>
            </s:form>
            <br>
        </div>
        &nbsp;
    </div>
    <div id ="mainContent" align="center">
        <div style="border: solid 1px #000000; background: gainsboro">

            <s:form name="frmAddPrfileBrowse">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                    <display:table name="erpmurList" pagesize="15" summary="false"
                                   excludedParams="*" export="false" cellpadding="0" id="doc"
                                   cellspacing="0" requestURI="/Administration/SaveUserProfile.action">
                        <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                            <c:out> ${doc_rowNum}
                            </display:column>
                            <display:column property="institutionmaster.imName" title="Institution Name"
                                            maxLength="35" headerClass="gridheader"
                                            class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false"/>
                            <display:column property="subinstitutionmaster.simName" title="College/Faculty/School"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" sortable="false"/>
                            <display:column property="departmentmaster.dmName" title="Department Name"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" sortable="false"/>
                            <display:column property="institutionuserroles.iurName" title="Role"
                                            maxLength="20" headerClass="gridheader"
                                            class="griddata" sortable="false"/>
                            <display:column property="erpmurDefault" title="Default Role"
                                            maxLength="5" headerClass="gridheader"
                                            class="griddata" sortable="false"/>
                            <display:column property="erpmurActive" title="Activation Status"
                                            maxLength="5" headerClass="gridheader"
                                            class="griddata" sortable="false"/>
                            <display:column paramId="erpmurId" paramProperty="erpmurId" href="DeleteUserProfile.action" headerClass="gridheader" class="griddata" media="html">
                                <img align="left" src="../images/TrashIcon.png" border="0" alt="Delete" style="cursor:pointer;"/>
                            </display:column>
                        </display:table>
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
