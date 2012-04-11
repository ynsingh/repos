<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Budgettypemaster.js"></script>
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
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
                <s:hidden name="erpmuName" />
            <!-- *********************************End Menu****************************** -->
              <s:property value="message" />
            <div id ="mainContent" align="center">

                <br>
                <s:form name="frmManageAuthorizationRequests">
                    <br><br>

                    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                    <display:table name="erpmurList" pagesize="15" summary="false"
                                   excludedParams="*" export="false" cellpadding="0" id="doc"
                                   cellspacing="0" requestURI="/Administration/ManageAuthorizationRequests.action">
                         <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="erpmusers.erpmuName" title="User Name"
                                        maxLength="20" headerClass="gridheader"
                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false"/>
                        <display:column property="erpmurId" title="Profile ID"
                                        maxLength="5" headerClass="gridheader"
                                        class="griddata" sortable="false"/>
                        <display:column property="institutionmaster.imName" title="Institution Name"
                                        maxLength="35" headerClass="gridheader"
                                        class="griddata" sortable="false"/>
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
                        <display:column paramId="erpmurId" paramProperty="erpmurId" href="ApproveUserProfile.action"
                                        headerClass="gridheader" class="griddata" media="html" title="Approve">
                            Approve
                        </display:column>
                    </display:table>
                </table>
             </s:form>
                <br>
            </div>
             <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>




