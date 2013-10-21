<%-- 
    Document   : ManageCommitteeMaster
    Created on : 13 Oct, 2011, 07:47 AM
    Author     : sknaqvi
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
            
            <jsp:include page="jobBar.jsp" flush="true"></jsp:include>
            
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent"> 
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />                    
                </s:bean>

                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">WORKFLOW MASTER</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                <s:form name="frmWorkFlowMaster" action="SaveWorkFlowMasterAction" theme="qxhtml">
                        <s:hidden name="wfm.wfmId" />
                        <br>
                        <s:select label="Institution" name="wfm.institutionmaster.imId" headerKey="0" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName"
                                  required = "true" requiredposition="right" 
                                  onchange="getSubinstitutionList('SaveWorkFlowMasterAction_wfm_institutionmaster_imId', 'SaveWorkFlowMasterAction_wfm_subinstitutionmaster_simId');" cssClass="textInput" >
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>
                        
                        <s:label/>
                        <s:label value="................." cssClass="tdSpace" />
                        
                        <s:select label="College/Faculty/School" name="wfm.subinstitutionmaster.simId" headerKey="0" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName" cssClass="textInput" required = "true" requiredposition="right"
                                  onchange="getAllDepartmentList('SaveWorkFlowMasterAction_wfm_subinstitutionmaster_simId', 'SaveWorkFlowMasterAction_wfm_departmentmaster_dmId');">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:select>
                                                
                        <s:select label="Department" name="wfm.departmentmaster.dmId" headerKey="0" headerValue="All Departments" list="dmList" listKey="dmId" listValue="dmName" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{4}" />
                        </s:select>
                            
                        <s:select label="Workflow Type" name="wfm.erpmGenMaster.erpmgmEgmId" headerKey="0" 
                                  headerValue="-- Please Select --" list="egmList" listKey="erpmgmEgmId" 
                                  listValue="erpmgmEgmDesc" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:select>

                        <s:textfield label="Name of the Workflow" name="wfm.wfmName" title="Enter Name of the Committee/Authority"
                                     required="true" requiredposition="left" maxLength="100" size="100"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>
                            
                        <s:iterator value="{1,1,1,1,1,1,1,1}">
                            <s:label value="..." cssClass="tdSpace"/>
                        </s:iterator>

                        <s:submit value="Save Workflow" >
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit value="Fetch Workflow Entries" action="FetchWorkFlowMaster">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit value="Clear Form" action="ClearWorkFlowMasterAction">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
                        
                        <s:iterator value="{1,1,1,1,1,1,1,1}">
                            <s:label value="..." cssClass="tdSpace"/>
                        </s:iterator>

                </s:form>                             
            </div>
                <br>
            </div>

           <s:if test="wfmList.size()>0">                 
                <div id ="mainContent" align="center" style="border: solid 1px #666; background: gainsboro">
                    <s:form name="frmWorkflowMasterBrowse">
                        
                        <table width="80%" border="0" cellspacing="0" cellpadding="0" align="center" >
                            <tr><td>
                                    <display:table name="wfmList" pagesize="15"
                                                   excludedParams="" export="true" cellpadding="0"
                                                   cellspacing="0" id="doc"
                                                   requestURI="/Administration/FetchWorkFlowMaster">
                                        <display:column  class="griddata" title="Record" sortable="true" headerClass="gridheader">
                                    <c:out> ${doc_rowNum}
                                    </display:column>

                                    <display:column property="institutionmaster.imName" title="Institution"
                                                    headerClass="gridheader"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false" />

                                    <display:column property="subinstitutionmaster.simName" title="SubInstitution"
                                                    headerClass="gridheader"
                                                    class="griddata" sortable="false"/>

                                    <display:column property="departmentmaster.dmName" title="Department"
                                                    headerClass="gridheader"
                                                    class="griddata" sortable="false"/>

                                    <display:column property="wfmName" title="Work Flow Name" headerClass="gridheader"
                                                    class="griddata" sortable="false"/>

                                    <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Work Flow Type"
                                                    headerClass="gridheader"
                                                    class="griddata" sortable="false"/>

                                    <display:column paramId="wfmId" paramProperty="wfmId"
                                                    href="/pico/Administration/EditWorkFlowMasterAction.action"
                                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                        Edit
                                    </display:column>
                                    <display:column paramId="wfmId" paramProperty="wfmId"
                                                    href="/pico/Administration/DeleteWorkFlowMasterAction.action"
                                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                        Delete
                                    </display:column>

                                    <display:column paramId="wfmId" paramProperty="wfmId"
                                                    href="/pico/Administration/AddWorkFlowDetail.action"
                                                    headerClass="gridheader" class="griddata" media="html" title="Add Workflow Details">
                                        Add Workflow Details
                                    </display:column>

                                </display:table>
                                </td></tr>
                        </table>
                    </s:form>
                    <br>
                &nbsp;
            </div>           
            </s:if>
                    <s:else>
                        <br><br><br><br><br><br><br><br><br>
                    </s:else>    
                        
            
            <div id="footer" >
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
