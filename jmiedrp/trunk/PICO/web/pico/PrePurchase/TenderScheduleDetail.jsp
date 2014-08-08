<%-- 
    Document   : TenderScheduleDetail
    Created on : 18 Apr, 2013, 11:53:47 AM
    Author     : wml3
    I18n By    : Mohd. Manauwar Alam
               : Feb 2014
--%>

<%--
    Document   : TenderSchedule
    Created on : 22 Mar, 2013, 10:29:11 AM
    Author     : Saeed
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
                <jsp:include page="../Administration/header.jsp"  flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu******************************-->
            <div id ="mainContent" > <br><br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <div style ="background-color: #215dc6;" align="center">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('PrePurchase.TenderScheduleDetail')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <br>

                    <s:form name="frmTenderScheduleDetailAction" action="TenderScheduleDetailAction"  theme="qxhtml">

                         <s:hidden name="tenschdlDet.tscdTscdId"/>
                         <s:hidden name="tenschdl.tscTscId"/>
                         <s:hidden name="vartscTscId"/>
                        <table border="0" cellpadding="4" cellspacing="0" align="center" >
                            <tbody>

                                <s:textfield  maxLength="30" size="20"  key="PrePurchase.TenderNo" requiredposition="" name="tenschdl.erpmTenderMaster.tmTmId" required="true" readonly="True" disabled="tsdDisable" >
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:textfield>

                                 <s:textfield  maxLength="30" size="20"  key="PrePurchase.ScheduleNo" requiredposition="" name="tenschdlDet.tscdScheduleNo" required="true" >
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:textfield>

                                <s:select key="PrePurchase.ScheduleType" required="true" requiredposition=""  name="tenschdlDet.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="gmIdList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"  cssClass="textInput">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                                 <s:textfield required="true" requiredposition="left" maxLength="100" size="40" title="Venue"
                                         key="PrePurchase.Venue" name="tenschdlDet.tscdVenue" >
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:textfield>

                                <s:textfield required="true" requiredposition="left" maxLength="10" size="30" title="Date [DD-MM-YYYY]"
                                         key="PrePurchase.ScheduleDate" name="schdDate" >
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:textfield>

                                <s:select key="PrePurchase.TimeHour" required="true" requiredposition="" name="strhr" headerKey="" headerValue="-- Please Select --" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'}"  cssClass="queryInput">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                                 <s:select key="PrePurchase.TimeMin" required="true" requiredposition="" name="strmin" headerKey="" headerValue="-- Please Select --" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59'}"  cssClass="queryInput">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                               

                                <tr><td align="left">
                                <s:submit theme="simple" name="btnSubmit" key = "PrePurchase.AddSchedule"   action="AddTenderScheduleDetailAction" />

                                <s:submit theme="simple" name="btnSubmit" key="PrePurchase.Clear"   action="ClearTenderScheduleDetail" />

                                <s:submit theme="simple" name="btnSubmit" key = "PrePurchase.Back"   action="BackTenderScheduleDetail" />
                                 </td></tr>
                          <%--      --%>
                            </tbody>

                        </table>
                    </s:form>
                </div>

                <div id ="mainContent" align="center">
                <s:form name="frmBrowseTenderSchedule">
                  <s:hidden name="tenschdl.tscTscId"/>
                  <s:hidden name="vartscTscId"/>
                 <s:label value="TENDER SCHEDULE DETAIL" />
                 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                     <display:table name="tenschdlDetList" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" id="doc" decorator="PrePurchase.PrePurchaseDecorator"
                               requestURI="/PrePurchase/TenderScheduleDetailAction.action">
                            <display:column  class="griddata" title="S.No" maxLength="100" sortable="true" style="width:10%" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                            </display:column>


                    <display:column property="tscdScheduleNo" title="Schedule No"
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                    <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Schedule Type"
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                    <display:column property="formattedTscScheduleDate" title="Schedule Date"
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                    <display:column property="tscdScheduleTime" title="Schedule Time"
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                    <display:column property="tscdVenue" title="Venue"
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                    <display:column paramId="tenschlDetId" paramProperty="tscdTscdId"
                                    href="/pico/PrePurchase/EditTenderScheduleDetail"
                                    headerClass="gridheader" class="griddata" media="html"   style="width:10%">
                        Edit
                    </display:column>

                    <display:column paramId="tenschlDetId" paramProperty="tscdTscdId"
                                    href="/pico/PrePurchase/DeleteTenderScheduleDetail"
                                    headerClass="gridheader" class="griddata" media="html"   style="width:10%">
                        Delete
                    </display:column>



        </display:table>


                </table>
             </s:form>
            </div>
                &nbsp;
            </div>

            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>

