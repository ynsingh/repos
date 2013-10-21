<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div id="header">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <s:form name="frmBudgetTypeBrowse">

                    <br><br>
                    <div style ="background-color: #215dc6;">
                        <p align="center" class="pageHeading" style="color: #ffffff">BROWSE BUDGET TYPES</p>
                        <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                    </div>

                    <div style="border: solid 1px #000000; background: gainsboro">

                        <s:property value="message" />
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                            <display:table name="btmList" pagesize="15"
                                           excludedParams="*" export="true" cellpadding="0"
                                           cellspacing="0"
                                           requestURI="/Administration/BrowseBudgetTypes.action">
                                <display:column property="btmName" title="Budget Type Name"
                                                maxLength="35" headerClass="gridheader"
                                                class="griddata" style="width:30%" sortable="true"/>
                                <display:column paramId="btmId" paramProperty="btmId"
                                                href="/pico/Administration/EditBudgetType.action?editFlag=Y"
                                                headerClass="gridheader" class="griddata" media="html">
                                    <img align="right" src="../images/edit.jpg" border="0" alt="Edit" style="cursor:pointer;"/>
                                </display:column>
                                <display:column paramId="btmId" paramProperty="btmId" href="/pico/Administration/DeleteBudgetType.action" headerClass="gridheader" class="griddata" media="html">
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
