<%-- 
    Document   : ViewListOfTenders
    Created on : 17 Jul, 2013, 1:01:14 PM
    Author     : manauwar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <s:head />
    </head>

    <body  class="oneColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="header.jsp" flush="true"></jsp:include>
                </div>

                <!-- *********************************End Menu****************************** -->
                <div id ="mainContent">
                    <br>
                    <div align="right" >
                        <a href="Index" style="margin-right: 10px">HOME</a>
                    </div>
                <s:actionerror />

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{11}" />
                </s:bean>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">LIST OF TENDERS</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background:  gainsboro">

                    <s:form name="frmListoftenders" action="ListOfTendersAction"  theme="qxhtml">
                        <s:hidden value="institutionId"/>
                        <s:hidden value="tenderType"/>
                        <s:hidden value="tenderState"/>
                        <table border="0" cellpadding="4" cellspacing="0" align="center">
                            <tbody>

                                <tr>
                                    <td>

                                        <s:select key="Administration.InstitutionName" name = "institutionId" headerKey="0" headerValue="All Institutions"
                                                  list="imList" listKey="imId" listValue="imName" cssClass="textInput"
                                                  required="true">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{1}" />
                                        </s:select>
                                        <s:label value="..." cssClass="tdSpace"/>
                                        <s:select label="Tender Type" name = "tenderType" headerKey="0" headerValue="All Tender Type"
                                                  list="genList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"
                                                  >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{1}" />
                                        </s:select>
                                        <s:label value="..." cssClass="tdSpace"/>
                                        <s:select label="Tender State" name = "tenderState" list="#{'1':'All','2':'Live','3':'Expired'}"  cssClass="textInput" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{1}" />
                                        </s:select> 
                                      
                                    </td>
                                </tr> <tr>
                                    <td>
                                        <s:submit theme="simple" name="btnSubmit" value="Fetch Tender List" action="fetchTender" />

                                        <s:submit theme="simple" name="btnSubmit" value="Home"   action="Index"/>

                                        <a href="ExportTenderList.action" target="_blank" >
                                            Export
                                        </a>
                                    </td>

                                </tr>
                            </tbody>
                        </table>
                    </s:form>

                    <s:if test="tenderList.size > 0">
                        <hr>
                        
                        <s:label value="  LIST OF TENDERS ARE :" cssClass= "pageSubHeading" >
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{9}" />
                        </s:label>

                        <hr>
                        <s:form name="frmListoftenders" >
                            <table width="100%" align="center">
                                
                                        <display:table name="tenderList" pagesize="10" decorator="PrePurchase.PrePurchaseDecorator"
                                                       excludedParams="*" export="false" cellpadding="0"
                                                       cellspacing="0" summary="false" id="doc"
                                                       requestURI="/Administration/fetchTender.action">

                                            <display:column  class="griddata" title="S.No."  sortable="true" headerClass="gridheader" style="width:5%" >
                                        <c:out> ${doc_rowNum}
                                        </display:column>

                                        <display:column property="institutionmaster.imShortName" title="Institute"
                                                        headerClass="gridheader"
                                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                        style="width:8%" sortable="true"/>

                                        <display:column property="tmName" title="Tender Name"
                                                        headerClass="gridheader" class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                        style="width:15%" sortable="true"/>

                                        <display:column property="erpmGenMasterByTmTypeId.erpmgmEgmDesc" title="Tender Type"
                                                        headerClass="gridheader" class="griddata" style="width:15%" sortable="true"/>

                                        <display:column property="tmDate" title="Tender Date" format="{0,date,dd-MM-yyyy}"
                                                        headerClass="gridheader" class="griddata" style="width:10%" sortable="true"></display:column>


                                        <display:column property="tmFee" title="Tender Fee"
                                                        headerClass="gridheader" class="griddata" style="width:10%" sortable="true"/>
                                        <display:column  title="Notice Link"  property="dynamicDocumentLink"  autolink="true"
                                                         headerClass="gridheader" class="griddata" style="width:10%" sortable="true">

                                        </display:column>

                                        <display:column  title="Notice Link"  property="dynamicNoticeLink"  autolink="true"
                                                         headerClass="gridheader" class="griddata" style="width:10%" sortable="true">

                                        </display:column>

                                        <display:column paramId="SCHEDULEID" paramProperty="tmTmId"
                                                        href="/pico/Administration/getTenderSchedule"
                                                        headerClass="gridheader" class="griddata" media="html"  title="Schedule" style="width:10%">
                                            Schedule
                                        </display:column>


                                    </display:table>
                                   
                            </table>
                        </s:form>
                    </s:if>
                    <s:else>
                        <s:form name="frmListoftenders" >
                            <br>
                            <s:label value="%{message1}" >
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{11}" />
                            </s:label>


                        </s:form>
                    </s:else>

                    <s:if test="tenderScheduleList.size > 0">

                        <br>
                        <hr>
                        <s:label  cssClass= "pageSubHeading" value="%{message2}">
                            <s:param name="labelcolspan" value="%{0}" />
                            <s:param name="inputcolspan" value="%{11}" />
                        </s:label>

                        <hr>
                        <s:form name="frmListoftenders" >
                            <table width="100%">
                                <tr>
                                    <td>
                                        <display:table name="tenderScheduleList" pagesize="15" 
                                                       excludedParams="*" export="false" cellpadding="0"
                                                       cellspacing="0" summary="false" id="doc"
                                                       requestURI="/Administration/getTenderSchedule.action">

                                            <display:column  class="griddata" title="S.No."  sortable="true" headerClass="gridheader" style="width:5%" >
                                        <c:out> ${doc_rowNum}
                                        </display:column>

                                        <display:column property="tscdScheduleNo" title="Schedule No"
                                                        headerClass="gridheader"
                                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                        style="width:8%" sortable="true"/>
                                        <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Schedule Type"
                                                        headerClass="gridheader"
                                                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                        style="width:20%" sortable="true"/>

                                        <display:column property="tscdScheduleDate" title="Schedule Date" format="{0,date,dd-MM-yyyy}"
                                                        headerClass="gridheader" class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                        style="width:15%" sortable="true"/>

                                        <display:column property="tscdScheduleTime" title="Schedule Time"
                                                        headerClass="gridheader" class="griddata" style="width:15%" sortable="true"/>

                                        <display:column property="tscdVenue" title="Schedule Venue"
                                                        headerClass="gridheader" class="griddata" style="width:30%" sortable="true"/>

                                    </display:table>
                                    </td>
                                    </tr>
                            </table>
                        </s:form>
                    </s:if>
                    <s:else>
                        <s:form name="frmListoftenders" >

                            <br>

                            <s:label value="%{message2}" >
                                <s:param name="labelcolspan" value="%{0}" />
                                <s:param name="inputcolspan" value="%{11}" />
                            </s:label>


                        </s:form>
                    </s:else>

                    <br>
                </div>
                &nbsp;
                <s:actionerror />
            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>