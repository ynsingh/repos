<%-- 
    Document   : ProfileDetails
    Created on : Oct 26, 2012, 2:19:18 PM
    Author     : Vinay
--%>


<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:property value="title"/></title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <sj:head/>
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
        <script>
            $(function() {
                $("#accordion").accordion();
            });
        </script>
        <style type="text/css">
		.ui-accordion .ui-accordion-header{ padding-left:28px;}
		</style>
    </head>
    <body><%
            final Logger logger = Logger.getLogger(this.getClass());
            String ipAddress = request.getRemoteAddr();
            logger.warn(session.getAttribute("user_id") + " Accessed from: " + ipAddress + " at: " + new Date());
            String role = session.getAttribute("role").toString();
            if (session.getAttribute("user_id") == null) {
                session.invalidate();
                response.sendRedirect("../Login.jsp");
            }
        %>
        <div class="w100 fl-l">
            <div class="w990p mar0a">
                <!--Header Starts Here-->
                <s:include  value="/Header.jsp"/>
                <!--Header Ends Here-->

                <!--Middle Section Starts Here-->
                <div class="w100 fl-l">
                    <div class="middle_bg">
                        <!--Left box Starts Here-->
                        <s:include value="/Left-Nevigation.jsp"/> 
                        <!--Left box Ends Here-->
                        <!--Right box Starts Here-->
                        <div class="right_box">
                            <div class="my_account_bg"><s:property value="title"/></div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyPortfolio.jsp"/>">My Portfolio</a>&nbsp;>&nbsp;<s:property value="title"/>
                                    </div>
                                    <div class="w100 fl-l mart15">
                                        <div class="faculty_txt">
                                            <s:iterator value="ProfileDetailsList" status="stat">
                                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td align="left" width="25%" valign="top">
                                                            <img src="<s:url action='ImageAction'/>?userId=<s:property value="emailId"/>" width="148" height="150"/>
                                                        </td>
                                                        <td width="75%" valign="top"><h1><s:property value="fname"/>&nbsp;<s:property value="lname"/></h1>
                                                            <div class="faculty_detail">Visiting Professor of Education</div>
                                                            <ul>
                                                                <s:iterator value="profileContacts">
                                                                    <li> 
                                                                        <s:property value="address1"/>, <s:property value="address2"/><br/>
                                                                        <s:property value="city"/>, <s:property value="state"/><br/>
                                                                        <s:property value="country"/>, <s:property value="pin"/>
                                                                    </li>
                                                                    <li><a href="#"><b>Email:</b> <s:property value="emailId"/></a></li>
                                                                    <li>Res No.: <s:property value="HTelephone"/></li>
                                                                    <li>Off No.: <s:property value="OTelephone"/></li>
                                                                    <li>Mob No.: <s:property value="mobileNo"/></li>
                                                                    <li>Fax No.: <s:property value="faxNo"/></li>
                                                                    </s:iterator>
                                                            </ul></td>
                                                    </tr>
                                                </table>
                                                <div class="fl-l marl50">
                                                    <sj:accordion id="accordion1">
                                                        <s:if test="EmployeeList!='NULL'">
                                                            <sj:accordionItem title="Employment Information">
                                                                <table border="1" cellpadding="4" cellspacing="0">
                                                                    <tbody>
                                                                        <tr>
                                                                            <th width="81">S. No</th>
                                                                            <th width="79">Job Title</th>
                                                                            <th width="134">Organization Name</th>
                                                                            <th width="216">Address</th>
                                                                            <th width="86">Joining Date</th>
                                                                            <th width="156">Relieved On</th>
                                                                            <th>Role/Description</th>
                                                                        </tr>
                                                                        <s:iterator value="profileEmployments" var="ProfileEmployment" status="groupStatus">
                                                                            <tr>
                                                                                <td align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                                                                                <td valign="top"><s:property value="jtitle"/></td>
                                                                                <td valign="top"><s:property value="orgName"/></td>
                                                                                <td valign="top"><s:property value="oaddress"/>
                                                                                    <s:property value="ocity"/>
                                                                                    <s:property value="ostate"/>
                                                                                    <s:property value="ocountry"/>
                                                                                </td>
                                                                                <td valign="top"><s:property value="jdate"/></td>
                                                                                <td valign="top"><s:property value="ldate"/>
                                                                                    &nbsp;</td>
                                                                                <td valign="top" width="223"><s:property value="description"/></td>
                                                                            </tr>
                                                                        </s:iterator>
                                                                    </tbody>
                                                                </table>
                                                            </sj:accordionItem>
                                                        </s:if>
                                                        <s:if test="AcademicList!='NULL'">
                                                            <sj:accordionItem title="Academic Information">
                                                                <table border="1" cellpadding="4" cellspacing="0">
                                                                    <tbody>
                                                                        <tr>
                                                                            <th>S.No</th>
                                                                            <th>Degree/Programme</th>
                                                                            <th>Specialization</th>
                                                                            <th>Board/University/Institute</th>
                                                                            <th>Passing Year</th>
                                                                            <th>Percentage/Grade/Division</th>
                                                                        </tr>
                                                                        <s:iterator value="profileAcademics" status="groupStatus">
                                                                            <tr>
                                                                                <td align="center" valign="top"><s:property value="%{#groupStatus.count}"/></td>
                                                                                <td valign="top"><s:property value="degree"/></td>
                                                                                <td valign="top"><s:property value="fstudy"/></td>
                                                                                <td valign="top"><s:property value="university"/></td>
                                                                                <td align="center" valign="top"><s:property value="pyear"/></td>
                                                                                <td align="center" valign="top"><s:property value="percent"/>/<s:property value="location"/>/<s:property value="division"/></td>
                                                                            </tr>
                                                                        </s:iterator>
                                                                    </tbody>
                                                                </table>
                                                            </sj:accordionItem>
                                                        </s:if>
                                                        <s:if test="PublicationList!='NULL'">
                                                            <sj:accordionItem title="Publication" >
                                                                <table border="1" width="100%">
                                                                    <tr><td colspan="2" class="fbld">Patents</td></tr>
                                                                    <s:iterator  value="patents" status="stat"> 
                                                                        <tr>
                                                                            <td><s:property value="%{#stat.count}"/></td>
                                                                            <td>
                                                                                <s:bean name="org.IGNOU.ePortfolio.MyWorkspace.PatentInventorComparator" var="IDinventors"/>
                                                                                <s:sort comparator="#IDinventors" source="inventors">
                                                                                    <s:iterator>
                                                                                        <s:property value="lname"/>&nbsp;
                                                                                        <s:property value="fname"/>,
                                                                                    </s:iterator>
                                                                                </s:sort>
                                                                                "<i><s:property value="patentTitle"/></i>",
                                                                                <s:property value="country"/>
                                                                                <s:property value="patentNo"/>,
                                                                                <s:property value="patentDate"/>.
                                                                            </td>
                                                                        </tr>
                                                                    </s:iterator>
                                                                    <tr><td colspan="2" class="fbld">Journals</td></tr>
                                                                    <s:iterator value="journals" status="stat"> 
                                                                        <tr>
                                                                            <td><s:property value="%{#stat.count}"/></td>
                                                                            <td>  <s:bean name="org.IGNOU.ePortfolio.MyWorkspace.JournalAuthorComparator" var="JournalAuthorComparator"/>
                                                                                <s:sort comparator="#JournalAuthorComparator" source="journalAuthors">
                                                                                    <s:iterator>
                                                                                        <s:property value="lname"/>&nbsp;
                                                                                        <s:property value="fname"/>,
                                                                                    </s:iterator>
                                                                                </s:sort>
                                                                                "<s:property value="paperTitle"/>",
                                                                                <i><s:property value="journalName"/></i>,&nbsp;
                                                                                Vol.&nbsp;<s:property value="volumeNo"/>&nbsp;(<s:property value="date"/>) ,
                                                                                <s:property value="serialNo"/>,
                                                                                <s:property value="issnNo"/> ,
                                                                                pp.&nbsp;<s:property value="pfrom"/>-<s:property value="pto"/> .
                                                                            </td>
                                                                        </tr>                                  
                                                                    </s:iterator>

                                                                    <tr><td colspan="2" align="left" class="fbld">Conferences</td></tr>
                                                                    <s:iterator value="conferences" status="stat"> 
                                                                        <s:bean name="org.IGNOU.ePortfolio.MyWorkspace.ConferenceAuthorComparator" var="IdComparator"/>
                                                                        <tr>
                                                                            <td><s:property value="%{#stat.count}"/></td>
                                                                            <td> 
                                                                                <s:sort comparator="#IdComparator" source="conferenceAuthorses">
                                                                                    <s:iterator>
                                                                                        <s:property value="lname"/>&nbsp;
                                                                                        <s:property value="fname"/>,
                                                                                    </s:iterator>
                                                                                </s:sort>
                                                                                "<s:property value="paperTitle"/>",
                                                                                in&nbsp;<s:property value="conferenceName"/>,&nbsp;
                                                                                <s:property value="venue"/>&nbsp;
                                                                                <s:property value="state"/>),
                                                                                <s:property value="country"/>,
                                                                                <s:property value="dto"/> ,
                                                                                pp.&nbsp;<s:property value="pfrom"/>-<s:property value="pto"/> .
                                                                            </td>
                                                                        </tr>                                
                                                                    </s:iterator>
                                                                </table>
                                                            </sj:accordionItem>
                                                        </s:if>
                                                    </sj:accordion>
                                                </div>
                                            </s:iterator>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Right box End Here-->
                    </div>                        
                </div>
                <!--Middle Section Ends Here-->
            </div>
        </div>
        </div>
        <s:include value="/Footer.jsp"/>  
    </body>
</html>