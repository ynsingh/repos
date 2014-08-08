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
        <meta name="author" content="Saeed, Jamia Millia Islamia">
        <meta name="email" content="saeed.coer@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp"   flush="true" ></jsp:include >
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">RECEIVE ISSUED ITEMS LIST</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <br>

                    <s:form name="frmReceiveItemsBrowse">
                        <s:hidden name="varIssueReceiveID"/>
                       
                        <table width="100%" >
                            <tr><td>
                            <display:table name="irList" pagesize="15" decorator="Inventory.InventoryDecorator"
                                           excludedParams="*"  cellpadding="0"
                                           cellspacing="0"  id="doc"
                                           requestURI="/Inventory/BrowseAction.action">
                                <display:column  class="griddata" title="S.No" maxLength="100" sortable="true" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>
                                    <display:column property="isrReceiptNo" title="Receipt No"
                                                    maxLength="35" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                    style="width:20%" sortable="true"/>
                                    <display:column property="formattedisrReceiptDate" title="Receipt Date"
                                                    maxLength="10" headerClass="gridheader"
                                                    class="griddata" sortable="true"  />
                                    <display:column property="employeemaster.empFname" title="" style="width:10%"
                                                    maxLength="30" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="employeemaster.empMname" title="Employee Name" style="width:10%"
                                                    maxLength="30" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="employeemaster.empLname" title="" style="width:10%"
                                                    maxLength="30" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="committeemaster.committeeName" title="Authority"
                                                    maxLength="35" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column property="erpmIssueMaster.ismIssueNo" title="Isuue No"
                                                    maxLength="30" headerClass="gridheader"
                                                    class="griddata" sortable="true"/>
                                    <display:column paramId="isrId" paramProperty="isrId"
                                                    href="/pico/Inventory/EditItems.action"
                                                    headerClass="gridheader" class="griddata" media="html"  value="Edit">
                                    </display:column>
                                    <display:column paramId="isrId" paramProperty="isrId"
                                                    href="/pico/Inventory/DeleteItems.action"
                                                    headerClass="gridheader" class="griddata" media="html" value="Delete">
                                    </display:column>

                                </display:table>
                                    </td></tr>
                        </table>
                        
                    </s:form>
                     <br>
                </div>
                
               <br><br>
            </div>
            <div id="footer">
                 <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>