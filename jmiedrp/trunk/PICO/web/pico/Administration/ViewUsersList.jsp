<%-- 
    Document   : ViewUsersList
    Created on : 28 October, 2014
    Author     : Jaivir Singh
--%>


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
        <meta name="author" content="Jaivir Singh, IIT Kanpur">
        <meta name="email" content="jaivirpal@gmail.com">
      <%--  <meta name="copyright" content="NMEICT, MHRD, Govt. of India">  --%>
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
            <div id ="mainContent" align="center"><br><br>
                <br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Administration.UsersList')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p> 
                </div>
			<table><tr>
			<td align="center"><s:property value="message"/> <br></td>
			</tr></table>
                <div style="border: solid 1px #000000; background: gainsboro">
             	<s:form name="frmUserListBrowse">  
                <s:property value="message" /> 
                 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                    <display:table name="ruList" pagesize="15"
                               excludedParams="*"  cellpadding="0"
                               cellspacing="0"  id="doc"
                               requestURI="/Administration/ErpmUserList.action">
                   <display:column  class="griddata" title="S.No" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                   </display:column>
                   <display:column property="erpmuFullName" title="User Name"
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata" /> 
                   <display:column property="erpmuName" title="User email"
                                maxLength="35" headerClass="gridheader"
                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"  style="width:20%"/>
		  
		<%--   <display:column paramId="erpmuId" paramProperty="erpmuId"
                		href="/pico/Administration/EditDetail.action"
                                headerClass="gridheader" class="griddata" media="html"  value="Edit">
                   </display:column>
                   <display:column paramId="erpmuId" paramProperty="erpmuId"
	                   href="/pico/Administration/ChangePsword.action"
        	   	   headerClass="gridheader" class="griddata" media="html" value="ChangePassword">
                   </display:column>
		--%>		
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
