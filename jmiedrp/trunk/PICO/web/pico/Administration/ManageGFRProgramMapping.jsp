<%-- 
    Document   : ManageGFRProgramMapping
    Created on : 9 Jan, 2013, 5:11:33 PM
    Author     : Saeed
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
        <meta name="author" content="Saeed, Jamia Millia Islamia">
        <meta name="email" content="saeed.coer@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
                </div>

                <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
                </div>

                <!-- *********************************End Menu****************************** -->
                <div id ="mainContent" >
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{16}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">GFR & Program Mapping</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <br>

                    <s:form name="FrmManageGFRProgramMappingAction" action="ManageGFRProgramMappingAction" theme="qxhtml" >

                        <s:label value=".." cssClass="tdSpace"/>

                        <tr><td align="">
                                <s:select cssClass="textInput" label="Sub Module"  required="true" requiredposition="" name="gfrprgrmMap.erpmprogram.erpmsubmodule.erpmSubModuleId"
                                          headerKey="" headerValue="-- Please Select --" list="erpmsmList" listKey="erpmSubModuleId" listValue="esmName"
                                          onchange="getProgramListForSubModule('ManageGFRProgramMappingAction_gfrprgrmMap_erpmprogram_erpmsubmodule_erpmSubModuleId', 'ManageGFRProgramMappingAction_gfrprgrmMap_erpmprogram_erpmpId');">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                        <s:label value="......" cssClass="tdSpace"/>


                                <s:select cssClass="textInput"  label="Program" required="true" requiredposition="" name="gfrprgrmMap.erpmprogram.erpmpId" labelposition=""
                                          headerKey="" headerValue="-- Please Select --" list="erpmprgmList" listKey="erpmpId" listValue="erpmpDisplayName">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                        <s:label value="......" cssClass="tdSpace"/>
                        
                            <td align="right">
                                <s:submit theme="simple" value="Done" action="Done">
                                    <s:param name="colspan" value="%{1}" />
                                    <s:param name="align" value="%{3}" />
                                </s:submit>

                            </td></tr>
                            <%--   <tr><td align="left">
                            <s:submit theme="simple" value="Done" action="Done">
                                        <s:param name="colspan" value="%{1}" />
                                        <s:param name="align" value="%{3}" />
                            </s:submit>
                            </td></tr>--%>
                        </s:form>
                    <br>
                </div>
                &nbsp;
                <div id ="mainContent" align="center">
                    <s:form name="frmGFRMappingBrowse">
                        <p align="center"><s:label value="GFR RULES ALREADY MAPPED TO THE SELECTED PROGRAM" /></p>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                            <display:table name="GfrProgramMappingList" pagesize="10"
                                           excludedParams="*" export="true" cellpadding="5"
                                           cellspacing="5" id="doc"
                                           requestURI="/Administration/ManageGFRProgramMappingAction.Action">

                                <display:column property="gfrMaster.gfrGfrId" title="Record ID"
                                                maxLength="35" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true">
                                </display:column>
                                <display:column property="gfrMaster.gfrSection" title="Section"
                                                maxLength="10" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>

                                <display:column property="gfrMaster.gfrChapterNo" title="Chapter No"
                                                maxLength="35" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>

                                <display:column property="gfrMaster.gfrChapterName" title="Chapter Name"
                                                maxLength="10" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>
                                <display:column property="gfrMaster.gfrRuleNo" title="GFR Rule No"
                                                maxLength="10" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>
                                <display:column property="gfrMaster.gfrDescription" title="GFR Description"
                                                maxLength="120" headerClass="gridheader"
                                                class="griddata" style="width:40%" sortable="true"/>
                                <display:column paramId="gfrMappingId" paramProperty="gpmId"
                                                href="/pico/Administration/Exclude" value="Exclude"
                                                headerClass="gridheader" class="griddata" sortable="true">
                                </display:column>

                            </display:table>
                        </table
                    </s:form>
                </div>

                <div id ="mainContent" align="center">
                    <s:form name="frmGFRMappingBrowse">
                        <p align="center"><s:label value="AVAILABLE GFR RULES" /></p>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                            <display:table name="gfrMasterList" pagesize="10"
                                           excludedParams="*" export="true" cellpadding="5"
                                           cellspacing="5" id="doc"
                                           requestURI="/Administration/ManageGFRProgramMappingAction.Action">

                                <display:column property="gfrGfrId" title="Record ID"
                                                maxLength="35" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true">
                                </display:column>
                                <display:column property="gfrSection" title="Section"
                                                maxLength="10" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>
                                <display:column property="gfrChapterNo" title="Chapter No"
                                                maxLength="35" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>
                                <display:column property="gfrChapterName" title="Chapter Name"
                                                maxLength="10" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>

                                <display:column property="gfrRuleNo" title="GFR Rule No"
                                                maxLength="10" headerClass="gridheader"
                                                class="griddata" style="width:10%" sortable="true"/>
                                <display:column property="gfrDescription" title="GFR Description"
                                                maxLength="120" headerClass="gridheader"
                                                class="griddata" style="width:40%" sortable="true"/>
                                <display:column paramId="gfrMasterId" paramProperty="gfrGfrId"
                                                href="/pico/Administration/Include" value="Include"
                                                headerClass="gridheader" class="griddata" sortable="true">
                                </display:column>
                                <%--    --%>

                            </display:table>
                        </table>
                    </s:form>
                </div>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>



