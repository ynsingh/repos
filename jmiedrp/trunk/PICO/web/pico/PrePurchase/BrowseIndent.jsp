<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page  import="org.displaytag.decorator.*" %>
<%@page  import="java.util.*" %>
<%@page  import="java.util.*" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="sajidaziz00000, Jamia Millia Islamia">
        <meta name="email" content="sajidaziz00@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <s:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <br><br>
               
                 <div style="background-color: #215dc6;">
                     <p align="center" class="pageHeading" style="color:  #ffffff"> &nbsp;INDENT LIST</p>
                     <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"/> </p>
                </div>
                
                <div style="border: solid 1px #000000; background:  gainsboro">
                <s:form name="frmindentBrowse">
                    <s:hidden name="erpmindtmast.indtIndentId" />
                    
                    <table align="center" width="90%" >
                        <tr><td>
                            <display:table name="IndentList" pagesize="20" decorator="PrePurchase.PrePurchaseDecorator"
                            excludedParams="*" export="false" cellpadding="0"
                            cellspacing="0" summary="true" id="doc"
                            requestURI="/PrePurchase/BrowseIndent.action"  >

                        <display:column  title="SNo" sortable="true" maxLength="100" headerClass="gridheader">
                            <c:out> ${doc_rowNum}
                        </display:column>

                        <display:column property="indtIndentId" title="Indent"
                            maxLength="10" headerClass="gridheader"
                            class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                            sortable="true"/>
                        <%-- <display:column property="irmReturnDate" title="Item Make"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>--%>
                        <display:column property="formattedIndtIndentDate" title="Date" style="width:9%"   headerClass="gridheader"
                                        maxLength="10" class="griddata" sortable="true"/>

                        <display:column property="departmentmaster.dmName" title="Department"
                                    maxLength="50" headerClass="gridheader" style="width:25%"
                                    class="griddata"  sortable="true"/>

                        <display:column property="budgetheadmaster.bhmName" title="Budget" style="width:9%"
                                    headerClass="gridheader" class="griddata"  sortable="true"/>

                        <display:column property="indtTitle" title="Indent Title" style="width:25%"
                                    headerClass="gridheader" class="griddata"  sortable="true"/>
                          <%--  <display:column property="function" title="Indent Title"
                                  maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>--%>

                        <display:column paramId="indtindentid" paramProperty="indtIndentId"
                                    href="/pico/PrePurchase/EditIndentMasterDetails.action"
                                    headerClass="gridheader" class="griddata"  title="Edit">
                                    Edit
                        </display:column>

                        <display:column paramId="indtindentid" paramProperty="indtIndentId"
                                    href="/pico/PrePurchase/DeleteIndentMasterDetails.action"
                                    headerClass="gridheader" class="griddata" title="Delete">
                                    Delete
                        </display:column>

                        <display:column paramId="indentId" paramProperty="indtIndentId" style="width=15%"
                                    href="/pico/PrePurchase/ShowWorkFlow.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Show Status">
                                    Show Status
                        </display:column>

                </display:table>
                </td></tr>
                <tr><td><s:submit name="btnSubmit" value="New Indent" action="ManageIndent" align="center"/></td></tr>
                </table>
                    
                </s:form>
                <br>
                </div>
                <br>
                </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
