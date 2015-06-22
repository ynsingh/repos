<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <meta name="author" content="Jaivir Singh, IIT Kanpur"> <%-- 20June2015 Institute List in Index apge   --%>
        <meta name="email" content="jaivirpal@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
                <div id="sidebar1">
                    <jsp:include page="menu.jsp" flush="true"></jsp:include>
                </div>

                <jsp:include page="jobBar.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <br><br>

		<div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Administration.RegInstList')" /></p> 
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
            	<s:form name="frmInstitutionsBrowse">                                                     
                 <s:property value="message" />
                
                 <table border="0" cellspacing="0" cellpadding="0" align="center">
                    <display:table name="imIdList" pagesize="15"
                               excludedParams="*"  cellpadding="0"
                               cellspacing="0"  id="doc"
                               requestURI="/Administration/ViewRegisteredInstitutions.action">
                   <display:column  class="griddata" title="S.No" sortable="true" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                   </display:column>
                   <display:column property="imName" title="Institution Name"
                                    maxLength="35" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:20%" sortable="true"/>
                   <display:column property="imShortName" title="Short Name"
                                    maxLength="10" headerClass="gridheader"
                                    class="griddata" sortable="true"  />
                   <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Insitute Type"
                                    maxLength="30" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                   <display:column property="imEmailId" title="Admin Email Id"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                   <display:column property="countrymaster.countryName" title="Country"
                                    maxLength="30" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                   <display:column property="statemaster.stateName" title="State"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" sortable="true"/>
                </display:table>
                </table>
             </s:form>
             <br>
                </div>
            </div>
                <br>
             <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
