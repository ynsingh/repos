
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
            <div id ="mainContent" align="center">

                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">SUB-INSTITUTION LIST</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmSubInstitutionsBrowse" >
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                            <display:table name="simList" pagesize="15"
                                           excludedParams="*" export="true" cellpadding="0"
                                           cellspacing="0" id="doc"
                                           requestURI="/Administration/BrowseSubInstitutions.action">
                                <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>
                                    <display:column property="institutionmaster.imName" title="Institution Name"
                                                    maxLength="30" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:15%" sortable="true"/>
                                    <display:column property="simName" title="College/Faculty/School" style="width:25%" 
                                                    maxLength="40" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="simShortName" title="Short Name"
                                                    maxLength="10" headerClass="gridheader" style="width:15%"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="statemaster.stateName" title="State" style="width:15%"
                                                    maxLength="35" headerClass="gridheader" 
                                                    class="griddata" sortable="true"/>
                                    <display:column property="simEmailId" title="E_Mail_ID" style="width:20%"
                                                    maxLength="35" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column paramId="SIMID" paramProperty="simId"
                                                    href="/pico/Administration/EditSubInstitution"
                                                    headerClass="gridheader" class="griddata" media="html"  title="Edit" >
                                        Edit
                                    </display:column>
                                    <display:column paramId="SIMID" paramProperty="simId" href="/pico/Administration/DeleteSubInstitution.action"
                                                    headerClass="gridheader" class="griddata" media="html" title="Delete" style="width:30%" >
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
