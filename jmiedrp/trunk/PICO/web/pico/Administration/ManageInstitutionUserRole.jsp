<%--
    Document   : ManageCapitalCategory
    Created on : 5 Jan, 2011, 9:53:25 PM
    Author     : sknaqvi
    I18n By    : Mohd. Manauwar Alam
               : Jan 2014
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
        <!--<script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>-->
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
        <div id="container" >
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>
            
            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            
            <jsp:include page="jobBar.jsp" flush="true"></jsp:include>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent"> <br><br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>

                <div style ="background-color: #215dc6;">
      <%--              <p align="center" class="pageHeading" style="color: #ffffff">INSTITUTION USER ROLE MANAGEMENT</p> --%>
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Administration.InstUserRoleMgmt')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>                    
                </div>

                <div style="border: solid 1px #000000; background: gainsboro" >
                
                <s:form name="frmInstitutionUserRoleAddEdit" action="SaveIUR"  validate="true">
                    <s:hidden name="iur.iurId" />
                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
<%--                                <td colspan="2" align="middle" >                                    
                                </td> --%>
                            </tr>
                            <tr>
                                <td>
                         <%--           <s:select label="Institution Name" required="true" requiredposition="" name="iur.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"/>
                                    <s:textfield label="Role Name" name="iur.iurName" title="Enter Role Name"
                                                 required="true" requiredposition="left" maxLength="50" size="50" />
                                    <s:textfield label="Remarks" name="iur.iurRemarks" title="Remarks about Role"
                                                 required="false" requiredposition="left" maxLength="100" size="100" />
                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Role"  />
                                
                                
                                    <s:submit theme="simple" name="btnSubmit" value="Clear"  />
                                
                                
                                    <s:submit theme="simple" name="bthReset" value="Fetch Roles" action="FetchIUR" /> --%>
                                    <s:select key="Administration.InstitutionName" required="true" requiredposition="" name="iur.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"/>
                                    <s:textfield key="Administration.RoleName" name="iur.iurName" title="Enter Role Name"
                                                 required="true" requiredposition="left" maxLength="50" size="50" />
                                    <s:textfield key="Administration.Remarks" name="iur.iurRemarks" title="Remarks about Role"
                                                 required="false" requiredposition="left" maxLength="100" size="100" />
                                </td>
                            </tr> <tr>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" key="Administration.Save"  />


                                    <s:submit theme="simple" name="bthReset" key="Administration.Clear" action="Clear" />


                                    <s:submit theme="simple" name="bthReset" key="Administration.Browse" action="FetchIUR" />
                                </td>
                            </tr>
                            <tr><td><br></td><td><br></td></tr>
                        </tbody>
                    </table>
                </s:form>

           <s:if test="iurList.size() > 0">
               <hr>
                <s:form name="frmIURBrowse">
                    <table border="0" cellspacing="0" cellpadding="0" align="center">
                        <display:table name="iurList" pagesize="15"
                               excludedParams="*" export="false" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Administration/FetchIUR.action">
                        
                            <display:column  class="griddata" title="Record" sortable="true" style="width:5%"  headerClass="gridheader">
                                <c:out> ${doc_rowNum}
                            </display:column>
                        
                            <display:column property="iurName" title="Role" 
                                    style="width:25%"  headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                    sortable="false"/>
                        
                            <display:column property="iurRemarks" title="Description"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>
                        
                            <display:column paramId="IurId" paramProperty="iurId"
                                    href="/pico/Administration/InitializeIURP.action"
                                    headerClass="gridheader" class="griddata" media="html">
                                    Initialize Privileges                        
                            </display:column>
                                    
                            <display:column paramId="IurId" paramProperty="iurId"
                                    href="/pico/Administration/AddEditIURP.action"
                                    headerClass="gridheader" class="griddata" media="html">
                                    View/Edit Role Privileges
                            </display:column>
                                    
                            <display:column paramId="IurId" paramProperty="iurId"
                                    href="/pico/Administration/EditIUR.action"
                                    headerClass="gridheader" class="griddata" media="html" >
                                    Edit
                            </display:column>
                                    
                            <display:column paramId="IurId" paramProperty="iurId"
                                    href="/pico/Administration/DeleteIUR.action"
                                    headerClass="gridheader" class="griddata" media="html">
                                    Delete
                            </display:column>
                                    
                    </display:table>
                </table>
             </s:form>
             </s:if>
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
