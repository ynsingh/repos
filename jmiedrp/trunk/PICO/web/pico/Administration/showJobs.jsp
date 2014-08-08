
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

                <br><br><p align="center"><s:label value="Pending Jobs"  cssClass="pageHeading"/>
            <p align="center"><s:property value="message" /></p>

            <div id ="mainContent">             
                <s:form name="frmitem">
                    <table border="0" width="60%" align="center">
                        <tr><td>
                                <display:table name="userMessageList" pagesize="15"
                                               excludedParams="*"  export="true" cellpadding="0"
                                               cellspacing="0" summary="true" id="doc" decorator="Administration.ActorDecorator"
                                               requestURI="/Administration/showJobs">
                                               
                                <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                                <c:out> ${doc_rowNum}
                                </display:column>

                                <display:column property="erpmusersByUmFromErpmuId.erpmuFullName" title="Source User"
                                                maxLength="100" headerClass="gridheader" style="width:25%"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                sortable="true"/>

                                <display:column property = "umMessage" title="Message" maxLength="100" style="width:60%"  headerClass="gridheader"
                                                class="griddata" sortable="true"/>

                                <display:column property="umActionName" title="Link" headerClass="gridheader"/>
                            </display:table>    
                            <br>
                            </td></tr>
                            </table>
                        </s:form>
                        </div>
                        <div id="footer" >
                            <jsp:include page="footer.jsp" flush="true" ></jsp:include>
                        </div>
                        </div>
                        </body>
                        </html>
