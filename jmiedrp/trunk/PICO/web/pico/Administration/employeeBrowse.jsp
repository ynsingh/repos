<%-- 
    Document   : employeeBrowse
    Created on : Oct 30, 2011, 11:58:28 AM
    Author     : abc
--%>

<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
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
        <meta name="author" content="Tanvir Ahmed, Jamia Millia Islamia">
        <meta name="email" content="tahmed@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="menu.jsp"   flush="true" ></jsp:include >
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">

                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">EMPLOYEE LIST</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmemployeeBrowse">
                        <br>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                            <display:table name="emList" pagesize="25"
                                           excludedParams="*"  cellpadding="0"
                                           cellspacing="0"  id="doc"
                                           requestURI="/Administration/BrowseEmployee.action">
                                <display:column  class="griddata" title="S.No" sortable="true" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>
                                    <display:column property="institutionmaster.imShortName" title="Institution"
                                                    maxLength="10" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:10%" sortable="true"/>
                                    <display:column property="departmentmaster.dmName" title="Department"
                                                    maxLength="50" headerClass="gridheader"
                                                    class="griddata" style="width:20%" sortable="true"/>
                                    <display:column property="empFname" title="First Name"
                                                    maxLength="15" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:15%" sortable="true"/>
                                    <display:column property="empLname" title="Last Name"
                                                    maxLength="15" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:15%" sortable="true"/>
                                    <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Designation"
                                                    maxLength="30" headerClass="gridheader" style="width:15%" 
                                                    class="griddata" sortable="true"/>
                                    <display:column property="empEmail" title="Email Id"   style="width:15%" 
                                                    maxLength="50" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>

                                    <display:column paramId="empId" paramProperty="empId"
                                                    href="/pico/Administration/EditEmployee"
                                                    headerClass="gridheader" class="griddata" media="html"   title="Edit">
                                        Edit
                                    </display:column>

                                    <tr><td></td></tr>
                                    <display:column paramId="empId" paramProperty="empId" href="/pico/Administration/DeleteEmployee.action"
                                                    headerClass="gridheader"  class="griddata" media="html"  style="width:20%" title="Delete">
                                        Delete
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
