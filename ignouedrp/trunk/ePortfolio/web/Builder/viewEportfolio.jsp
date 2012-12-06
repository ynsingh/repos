<%-- 
    Document   : viewEportfolio
    Created on : Jun 25, 2012, 11:50:11 AM
    Author     : IGNOU Team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Profile</title>
        <link href="<s:url value="/css/master.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/collapse.css"/>" rel="stylesheet" type="text/css" />
        <link href="<s:url value="/css/skin.css"/>" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<s:url value="/js/jquery-1.6.4.min.js"/>"></script>
        
        <script type="text/javascript" src="<s:url value="/js/expand.js"/>"></script>
         <script>
            $(function() {
                $( "#accordion" ).accordion();
            });
        </script>
         <script type="text/javascript">
            function getContent()
            {
                var contentv;
                contentv=document.getElementById("pdfReport").innerHTML;
                document.getElementById("report").value=contentv;
                //alert(contentv);
            }
            // alert(contentv);
        </script>
    </head>
    <body>
        <%
            if (session.getAttribute("user_id") == null) {
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
                            <div class="my_account_bg">My ePortfolio view</div>
                            <div class="v_gallery">
                                <div class="w100 fl-l mart10">
                                    <div class="bradcum">
                                        <a href="<s:url value="/Welcome-Index.jsp"/>">Home</a>&nbsp;>&nbsp;<a href="<s:url value="/MyBuilder.jsp"/>">My Builder</a>&nbsp;> My ePortfolio View
                                    </div>
                                        <s:form method="post" action="ExportAct" theme="simple" namespace="/Builder">
                                            <input type="hidden" name="report" id="report" value="" />
                                            <div class="w100 fl-l" id="pdfReport">
                                     <table width="98%" class="mar0a mart10" border="1">
                                        
                                        <s:iterator value="userDetaillist">
                                            <tr>
                                                <th colspan="2" align="left">Personal Information</th>
                                            </tr>
                                            <tr><td colspan="2">
                                                    <table align="center" border="1" width="100%">
                                                        <tr><th align="left">Name</th><td><s:property value="fname"/>&nbsp;<s:property value="lname"/></td></tr>
                                                        <tr><th align="left">Father Name</th><td>&nbsp;</td></tr>
                                                        <tr><th align="left">Date of Birth</th><td>&nbsp;</td></tr>
                                                        <tr><th align="left">Place of Birth</th><td>&nbsp;</td></tr>
                                                        <s:iterator value="profileContacts" >
                                                            <tr><th align="left">Mailing Address: </th><td>
                                                                    <s:property value="address1"/>&nbsp;
                                                                    <s:property value="address2"/>&nbsp;
                                                                    <s:property value="city"/>&nbsp;
                                                                    <s:property value="state"/>&nbsp;
                                                                    <s:property value="country"/>&nbsp;
                                                                    <s:property value="pin"/>
                                                                </td></tr>
                                                            <tr><th align="left">Telephone&nbsp;(Res): </th><td >
                                                                    <s:property value="HTelephone"/>
                                                                </td></tr>
                                                            <tr><th align="left">Telephone&nbsp;(Off): </th><td >
                                                                    <s:property value="OTelephone"/>
                                                                </td></tr>
                                                            <tr><th align="left">Mobile : </th><td >
                                                                    <s:property value="mobileNo"/>
                                                                </td></tr>
                                                            <tr><th align="left">Fax :</th><td >
                                                                    <s:property value="faxNo"/>
                                                                </td></tr>
                                                            <tr><th align="left">Emails :</th><td >
                                                                    <s:property value="emailId"/>,&nbsp; <s:property value="email1"/>&nbsp;<s:property value="email2"/>&nbsp;<s:property value="email3" />
                                                                </td></tr>
                                                            <tr><th align="left">Website</th><td >
                                                                    <s:property value="owebsite"/>&nbsp;(Org),&nbsp;<s:property value="pwebsite"/>&nbsp;(Per)
                                                                </td></tr>
                                                            </s:iterator>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="2" align="left">Summary</th>
                                            </tr>
                                            <tr><th colspan="2" align="left">Experience Details</th></tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table border="1" align="center" width="100%">
                                                        <tr><th>Job Title</th>
                                                            <th>Organization Name</th>
                                                            <th width="225">Address</th>
                                                            <th>Joining Date</th>
                                                            <th>Relieved On</th>
                                                            <th>Role/Description</th>

                                                        </tr>
                                                        <s:iterator value="profileEmployments">
                                                            <tr>
                                                                <td><s:property value="jtitle"/></td>
                                                                <td><s:property value="orgName"/></td>
                                                                <td width="225"><s:property value="oaddress"/>
                                                                    <s:property value="ocity"/>
                                                                    <s:property value="ostate"/>
                                                                    <s:property value="ocountry"/>
                                                                </td>
                                                                <td><s:property value="jdate"/></td>
                                                                <td><s:property value="ldate"/>&nbsp;</td>
                                                                <td width="250"><s:property value="description"/></td>

                                                            </tr>
                                                        </s:iterator>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="2" align="left">Skills Set</th>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <s:iterator value="profileSkills" >
                                                        <s:property value="skills"/>,&nbsp;
                                                    </s:iterator>

                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="2" align="left">Education</th>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table border="1" align="center" width="100%">
                                                        <tr>
                                                            <th>Degree/Programme</th>
                                                            <th>Specialization</th>
                                                            <th>Board/University/Institute</th>
                                                            <th>Passing Year</th>
                                                            <th>Percentage</th>
                                                            <th>Grade</th>
                                                            <th>Division</th>

                                                        </tr>
                                                        <s:iterator value="profileAcademics" >
                                                            <tr>
                                                                <td><s:property value="degree"/></td>
                                                                <td><s:property value="fstudy"/></td>
                                                                <td><s:property value="university"/></td>
                                                                <td align="center"><s:property value="pyear"/></td>
                                                                <td align="center"><s:property value="percent"/></td>
                                                                <td><s:property value="location"/></td>
                                                                <td align="center"><s:property value="division"/></td>
                                                            </tr>
                                                        </s:iterator>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr><th colspan="2" align="left">Certification</th></tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table border="1" align="center" width="100%">
                                                        <tr>
                                                            <th>Certification Name</th>
                                                            <th>Certification Authority</th>
                                                            <th>License No.</th>
                                                            <th>Validity<br/>(From-To)</th>
                                                        </tr>
                                                        <s:iterator value="profileCertifications" >
                                                            <tr valign="middle"> 
                                                                <td><s:property value="certificationName"/> </td>
                                                                <td><s:property value="certificationAuthority"/></td>
                                                                <td><s:property value="license"/></td>
                                                                <td width="85"><s:property value="certificationDate"/> &nbsp;&nbsp; <s:property value="validDate"/>
                                                                </td>
                                                            </tr>
                                                        </s:iterator>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="2" align="left">Test Scores</th>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table border="1" width="100%">
                                                        <tr>
                                                            <th width="150">Name</th>
                                                            <th>Score</th>
                                                            <th>Date</th>
                                                            <th  width="200">Description</th>
                                                        </tr>
                                                        <s:iterator value="profileTests">
                                                            <tr>
                                                                <td>
                                                                    <s:property value="tname"/>
                                                                </td><td>
                                                                    <s:property value="score"/>
                                                                </td><td>
                                                                    <s:property value="tdate"/>
                                                                </td><td>
                                                                    <s:property value="tdescription" escape="false"/>
                                                                </td>

                                                            </tr>
                                                        </s:iterator>
                                                    </table>   
                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="2" align="left">Project</th>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table align="center" width="100%" border="1">
                                                        <tr>
                                                            <th width="5%" align="center">S. No</th>
                                                            <th width="9%">Title</th>
                                                            <th width="6%">Role</th>
                                                            <th width="9%">Team Size</th>
                                                            <th width="14%">Duration</th>
                                                            <th width="14%">Funding Agency</th>
                                                            <th width="11%">Budget</th>
                                                            <th width="19%">Description</th>
                                                        </tr>
                                                        <s:iterator value="projectses" status="stat" >
                                                            <tr>
                                                                <td align="center"><s:property value="%{#stat.count}"/></td>
                                                                <td><a href="<s:property value="proUrl"/>" target="_blank">
                                                                        <s:property value="proName"/>
                                                                    </a> </td>
                                                                <td><s:property value="role"/>
                                                                </td>
                                                                <td align="center"><s:property value="teamSize"/>
                                                                </td>
                                                                <td><s:property value="startDate"/>
                                                                    -
                                                                    <s:property value="endDate"/>
                                                                </td>
                                                                <td><s:property value="agency"/>
                                                                </td>
                                                                <td align="center"><s:property value="budget"/>
                                                                </td>
                                                                <td><s:property value="description" escape="false"/>
                                                                </td>    
                                                            </tr>
                                                        </s:iterator>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="2" align="left">Honor and Awards</th>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table border="1" width="100%">
                                                        <tr>
                                                            <th width="120">Title</th>
                                                            <th>Issuer</th>
                                                            <th width="75">Issue Date</th>
                                                            <th  width="200">Description</th>
                                                        </tr>
                                                        <s:iterator value="profileHonorAwards" >
                                                            <tr>  
                                                                <td>
                                                                    <s:property value="haTitle"/>
                                                                </td><td>
                                                                    <s:property value="issuer"/>
                                                                </td><td>
                                                                    <s:property value="haDate"/>
                                                                </td><td>
                                                                    <s:property value="haDescription"/>
                                                                </td>
                                                            </tr>
                                                        </s:iterator>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="2" align="left">Publications</th>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table border="1" width="100%">
                                                        <tr><td colspan="2">Patents</td></tr>
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
                                                        <tr><td colspan="2">Journals</td></tr>
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

                                                        <tr><td colspan="2" align="left">Conferences</td></tr>
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
                                                </td>
                                            </tr>
                                            <tr>
                                                <th colspan="2" align="left">Professional Affiliation</th>
                                            </tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table width="100%" border="1">
                                                        <tr>
                                                            <th>Role</th>
                                                            <th>Organization/Body</th>
                                                            <th width="200">Duration</th>
                                                            <th>Place</th>
                                                            <th>Country</th>
                                                            <th  width="250">Summary</th>
                                                        </tr>
                                                        <s:iterator value="profileProAffiliations">
                                                            <tr>  
                                                                <td>
                                                                    <s:property value="role"/>
                                                                </td><td>
                                                                    <s:property value="orgBody"/>
                                                                </td><td>
                                                                    <s:property value="vfrom"/>-<s:property value="vupto"/>
                                                                </td><td>
                                                                    <s:property value="place"/>
                                                                </td><td>
                                                                    <s:property value="country"/>
                                                                </td><td>
                                                                    <s:property value="summary"/>
                                                                </td>
                                                            </tr>
                                                        </s:iterator>
                                                    </table>
                                                </td>
                                            </tr>
                                           
                                            <tr><th colspan="2" align="left">References</th></tr>
                                            <tr>
                                                <td colspan="2">
                                                    <table align="center" border="1" width="100%">
                                                        <tr>
                                                            <th width="150">Name &amp; Designation</th>
                                                            <th>Address, Email ID, Phone No.</th>

                                                        </tr>
                                                        <s:iterator value="profileReferenceses" >
                                                            <tr>
                                                                <td><strong><s:property value="name"/></strong><br/>
                                                                    <s:property value="designation"/></td>
                                                                <td>
                                                                    <s:property value="department"/><br/>
                                                                    <s:property value="orgUniv"/><br/>
                                                                    <s:property value="place"/>
                                                                    <s:property value="city"/>
                                                                    <s:property value="state"/>
                                                                    <s:property value="country"/><br/>
                                                                    <s:property value="phoneno"/>
                                                                    <s:property value="mobileno"/><br/>
                                                                    <s:property value="emailId"/><br/>
                                                                    <s:property value="website"/>
                                                                </td>

                                                            </s:iterator>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            
                                        </s:iterator>
                                     </table>
                                            </div>
                                             <p class="clear tc">
                                                 <br/>
                                           <button type="submit" name="ButtonName" value="Export" onclick="getContent();">Export As Pdf</button>
                                           <a href="PortfolioExport.jsp?word=1">Export to Word</a>
                                              </p>   </s:form>
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