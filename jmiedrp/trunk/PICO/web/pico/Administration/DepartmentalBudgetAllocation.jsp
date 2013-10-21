<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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

        <sx:head/>
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
                <div id ="mainContent" >

                    <br><br>
                    <div style ="background-color: #215dc6;">
                        <p align="center" class="pageHeading" style="color: #ffffff">DEPARTMENTAL BUDGET ALLOCATION</p>
                        <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmDepartmentalbudgetallocation" action="SaveDepartmentalBudgetAllocationAction"  validate="true">
                        <s:hidden name="dba.dbaId" />
                        <table border="0" cellpadding="4" cellspacing="0" align="center">
                            <tbody>
                                <tr><td><br>
                                        <s:select label="Institution" name="dba.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                                  onchange="getSubinstitutionList('SaveDepartmentalBudgetAllocationAction_dba_institutionmaster_imId', 'SaveDepartmentalBudgetAllocationAction_dba_subinstitutionmaster_simId');" cssClass="textInput"/>
                                        <s:select label="College/Faculty/School" name="dba.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simImList" listKey="simId" listValue="simName" onchange="getDepartmentList('SaveDepartmentalBudgetAllocationAction_dba_subinstitutionmaster_simId','SaveDepartmentalBudgetAllocationAction_dba_departmentmaster_dmId')"  cssClass="textInput"/>
                                        <s:select label="Department" name="dba.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"  cssClass="textInput"/>

                                        <s:select label="Budget Head" name="dba.budgetheadmaster.bhmId" headerKey="" headerValue="-- Please Select --" list="bhmList" listKey="bhmId" listValue="bhmName" cssClass="textInput"/>
                                        <s:textfield required="true" requiredposition="left" maxLength="100" size="50"
                                                     label="Amount Sanctioned" name="dba.dbaAmount"    cssClass="textInput"/>
                                        <s:label value="ALLOCATION DATE"  cssClass="pageSubHeading"/>
                                        <s:textfield required="true" requiredposition="left" maxLength="12" size="50"
                                                     label="From Date(dd-mm-yyyy)" name="fromdate"      cssClass="textInput"/>
                                        <%--<sx:datetimepicker name="dba.dbaFromDate" label="From Date(yyyy-mm-dd)"
                                                          displayFormat="yyyy-MM-dd" value="%{'today'}"  cssClass="textInput" />
                                       <sx:datetimepicker name="dba.dbaToDate" label="To Date(yyyy-mm-dd)"

                                                           displayFormat="yyyy-MM-dd" value="%{'today'}" cssClass="textInput"   />--%>
                                        <s:textfield required="true" requiredposition="left" maxLength="12" size="50"
                                                     label="To Date(dd-mm-yyyy)" name="todate"   cssClass="textInput"/>
                                    </td></tr>
                                <tr>
                                    <td>
                                        <s:submit theme="simple" name="btnSubmit" value="Save Departmental Budget Allocation"   cssClass="textInput" />
                                    </td>
                                    <td>
                                        <s:submit theme="simple" name="btnClear" value="Clear"   action="ClearDepartmentalBudgetAllocation" cssClass="textInput"/>


                                        <s:submit theme="simple" name="btnFetch" value="Fetch Departmental Budget Allocation"  action="FetchDepartmentalBudgetAllocation" cssClass="textInput"/>
                                    </td></tr><tr><td> <br><br> </td></tr>
                            </tbody>
                        </table>
                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>
            <div id ="mainContent" align="center">

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmDepartmentalBudgetAllocationBrowse"  >
                        <table width="100%" border="1" cellspacing="0" cellpadding="0" align="center" >
                            <tr><td>

                                    <display:table name="dbaList" pagesize="15" decorator="Administration.ActorDecorator"
                                                   excludedParams="*" export="true" cellpadding="0"
                                                   cellspacing="0" summary="true" id="doc"
                                                   requestURI="/Administration/ManageExportDepartmentalBudgetAllocation.action">
                                        <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>
                                    <display:column property="institutionmaster.imName" title="Institution"
                                                    maxLength="30" headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:20%" sortable="true"/>
                                    <display:column property="subinstitutionmaster.simName" title="Sub Institution"
                                                    maxLength="50" headerClass="gridheader"
                                                    class="griddata" style="width:20%" sortable="true"/>
                                    <display:column property="departmentmaster.dmName" title="Department"
                                                    maxLength="50" headerClass="gridheader"
                                                    class="griddata" style="width:20%" sortable="true"/>
                                    <display:column property="budgetheadmaster.bhmName" title="Budget_Head"
                                                    maxLength="30" headerClass="gridheader"
                                                    class="griddata"  sortable="true"/>
                                    <display:column property="dbaAmount" title="Amount"
                                                    maxLength="30" headerClass="gridheader"
                                                    class="griddata"  sortable="true"/>
                                    <display:column property="formatteddbaFromDate" title=" From "
                                                    maxLength="20" headerClass="gridheader"
                                                    class="griddata"  sortable="true"  style="width:10%" />
                                    <display:column property="formatteddbaToDate" title="To"
                                                    maxLength="20" headerClass="gridheader"
                                                    class="griddata"  sortable="true" style="width:10%"/>

                                    <display:column paramId="DBAID" paramProperty="dbaId"
                                                    href="/pico/Administration/EditDepartmentalBudgetAllocation"
                                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                        Edit
                                    </display:column>
                                    <display:column paramId="DBAID" paramProperty="dbaId"
                                                    href="/pico/Administration/DeleteDepartmentalBudgetAllocation.action"
                                                    headerClass="gridheader" class="griddata" media="html" title="Delete" >
                                        Delete
                                    </display:column>
                                </display:table>
                                <br></td></tr>
                                </table>
                            </s:form>
                            <br>
                            </div>
                            &nbsp;
                            <br>

                            </div>
                            <div id="footer" >
                                <jsp:include page="footer.jsp" flush="true" ></jsp:include>
                            </div>
                            </div>
                            </body>
                            </html>