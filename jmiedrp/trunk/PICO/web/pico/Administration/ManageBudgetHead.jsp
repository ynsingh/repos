<%--
I18n By    : Mohd. Manauwar Alam
           : Jan 2014
--%>


<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <br><br>
            <div id ="mainContent"> 
            <div style ="background-color: #215dc6;">
<%--                    <p align="center" class="pageHeading" style="color: #ffffff">BUDGET HEAD RECORDS MANAGEMENT</p> --%>
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Administration.BudgetHeadRecMgmt')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
            </div>

            <div style="border: solid 1px #000000; background: gainsboro">
                <s:form name="frmBudgetHeads" action="SavebudgeheadAction"  validate="true" enctype="multipart/form-data">
                    <s:hidden name="bhm.bhmId" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td><br>
                         <%--           <s:select label="Institution" required="true" requiredposition="" name="bhm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="50"
                                                 label="Budget Head Name" name="bhm.bhmName" title="Enter Capital Item Category Name"  cssClass="textInput"/>

                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Budget Head "  cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Fetch Budget Head Entries" action="FetchEntries" cssClass="textInput"/>
                                </td>
                                <td>
                                  <s:submit theme="simple" name="bthReset" value="Clear"  action="ClearBudgetHead" cssClass="textInput"/> --%>
                                    <s:select key="Administration.InstitutionName" required="true"  name="bhm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" cssClass="textInput"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="50"
                                                 key="Administration.BudgetHeadName" name="bhm.bhmName" title="Enter Capital Item Category Name"  cssClass="textInput"/>

                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" key="Administration.Save"  cssClass="textInput"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" key="Administration.Browse" action="FetchEntries" cssClass="textInput"/>
                                </td>
                                <td>
                                  <s:submit theme="simple" name="bthReset" key="Administration.Clear"  action="ClearBudgetHead" cssClass="textInput"/>
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                        </tbody>
                    </table>

                   </s:form>

            <hr>
            <s:if test="bhmList.size() > 0">
            <s:form name="frmBudgetHeadBrowse">
                 <table width="60%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="bhmList" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/Administration/ManageExportAction.action" >
                        <display:column  class="griddata" title="S.No." style="width:10%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>

                        <display:column property="institutionmaster.imName" title="Institution"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:30%" sortable="true"  href=""/>
                       <display:column property="bhmName" title="Description"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true"/>
                        <display:column paramId="BhmId" paramProperty="bhmId"
                                    href="/pico/Administration/EditBudgetHeadAction.action" 
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                        </display:column>
                        <display:column paramId="BhmId" paramProperty="bhmId"
                                    href="/pico/Administration/DeleteBudgetHeadAction.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete" style="width:30%">
                                    Delete
                        </display:column>
                    </display:table>
                <br></td></tr>
                </table>
                 <br>
            </s:form>
            </s:if>
        </div>
        </div>
            <br>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
