<%-- 
    Document   : test
    Created on : Jul 17, 2012, 4:40:13 PM
    Author     : Amit
--%>

<%@page import="org.IGNOU.ePortfolio.Model.ProfileReferences"%>
<%@page import="org.IGNOU.ePortfolio.Model.ProfileProAffiliation"%>
<%@page import="org.IGNOU.ePortfolio.Model.Conference"%>
<%@page import="org.IGNOU.ePortfolio.Model.Journal"%>
<%@page import="org.IGNOU.ePortfolio.Model.Patent"%>
<%@page import="org.IGNOU.ePortfolio.Model.ProfileHonorAward"%>
<%@page import="org.IGNOU.ePortfolio.Model.Projects"%>
<%@page import="org.IGNOU.ePortfolio.Model.ProfileTest"%>
<%@page import="org.IGNOU.ePortfolio.Model.ProfileCertification"%>
<%@page import="org.IGNOU.ePortfolio.Model.ProfileAcademic"%>
<%@page import="org.IGNOU.ePortfolio.Model.ProfileSkill"%>
<%@page import="org.IGNOU.ePortfolio.Model.ProfileEmployment"%>
<%@page import="java.util.Set"%>
<%@page import="org.IGNOU.ePortfolio.Model.ProfileContact"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.IGNOU.ePortfolio.Model.UserList"%>
<%@page import="java.util.List"%>
<%@page import="org.IGNOU.ePortfolio.Builder.Action.ResumeBuild"%>
<%@page import="java.io.File"%>
<%@page import="java.io.Serializable"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%
    ResumeBuild rb = new ResumeBuild();
    List<UserList> Rd = rb.MakeResumes();
    Integer lsize = Rd.size();
    Integer i = 0;
%>
<!DOCTYPE html>
<html> 
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

            boolean isWord = true;
            if ("1".equals(request.getParameter("word"))) {
                isWord = false;
            }
        %>
        <%
            if (!isWord) {
                String formName = "";
                formName = "ePOrtfolio.doc";
                File filepath = new File(formName);
                response.setContentType("application/vnd.ms-word");
                response.setHeader("Content-Disposition", "inline; filename=" + filepath);
            }
        %>

        <table width="98%" class="mar0a" border="1">
            <%
                Set<ProfileEmployment> pemployment = Rd.iterator().next().getProfileEmployments();
                Set<ProfileSkill> pskill = Rd.iterator().next().getProfileSkills();
                Set<ProfileAcademic> pAcademic = Rd.iterator().next().getProfileAcademics();
                Set<ProfileCertification> pcertification = Rd.iterator().next().getProfileCertifications();
                Set<ProfileTest> ptest = Rd.iterator().next().getProfileTests();
                Set<Projects> projects = Rd.iterator().next().getProjectses();

                Set<ProfileHonorAward> phonor = Rd.iterator().next().getProfileHonorAwards();
                Set<Patent> patent = Rd.iterator().next().getPatents();
                Set<Journal> journal = Rd.iterator().next().getJournals();
                Set<Conference> conference = Rd.iterator().next().getConferences();
                Set<ProfileProAffiliation> pAffilation = Rd.iterator().next().getProfileProAffiliations();
                Set<ProfileContact> pcontact = Rd.iterator().next().getProfileContacts();
                Set<ProfileReferences> preference = Rd.iterator().next().getProfileReferenceses();

                Iterator<ProfileEmployment> iemp = pemployment.iterator();
                Iterator<ProfileSkill> iskill = pskill.iterator();
                Iterator<ProfileAcademic> iAcad = pAcademic.iterator();
                Iterator<ProfileCertification> icer = pcertification.iterator();
                Iterator<ProfileTest> iTest = ptest.iterator();
                Iterator<Projects> iProject = projects.iterator();
                Iterator<ProfileHonorAward> iHonor = phonor.iterator();
                Iterator<Patent> ipatent = patent.iterator();
                Iterator<Journal> ijournal = journal.iterator();
                Iterator<Conference> iconference = conference.iterator();
                Iterator<ProfileProAffiliation> iproAff = pAffilation.iterator();
                Iterator<ProfileReferences> iRef = preference.iterator();

            %>  
            <tr>
                <th colspan="2" align="left">Personal Information</th>
            </tr>
            <tr><td colspan="2">
                    <table align="center" border="1" width="100%">
                        <tr><th align="left">Name</th><td><%=Rd.iterator().next().getFname()%>&nbsp;<%=Rd.iterator().next().getLname()%></td></tr>
                        <tr><th align="left">Father Name</th><td>&nbsp;</td></tr>
                        <tr><th align="left">Date of Birth</th><td>&nbsp;</td></tr>
                        <tr><th align="left">Place of Birth</th><td>&nbsp;</td></tr>

                        <tr><th align="left">Mailing Address: </th><td>
                                <%=pcontact.iterator().next().getAddress1()%>&nbsp;
                                <%=pcontact.iterator().next().getAddress2()%>&nbsp;
                                <%=pcontact.iterator().next().getCity()%>&nbsp;
                                <%=pcontact.iterator().next().getState()%>&nbsp;
                                <%=pcontact.iterator().next().getCountry()%>&nbsp;
                                <%=pcontact.iterator().next().getPin()%>
                            </td></tr>
                        <tr><th align="left">Telephone&nbsp;(Res): </th><td >
                                <%=pcontact.iterator().next().getHTelephone()%>
                            </td></tr>
                        <tr><th align="left">Telephone&nbsp;(Off): </th><td >
                                <%=pcontact.iterator().next().getOTelephone()%>
                            </td></tr>
                        <tr><th align="left">Mobile : </th><td >
                                <%=pcontact.iterator().next().getMobileNo()%>
                            </td></tr>
                        <tr><th align="left">Fax :</th><td >
                                <%=pcontact.iterator().next().getFaxNo()%>
                            </td></tr>
                        <tr><th align="left">Emails :</th><td >
                                <%=pcontact.iterator().next().getEmail1()%>,&nbsp; <%=pcontact.iterator().next().getEmail2()%>&nbsp;<%=pcontact.iterator().next().getEmail3()%>&nbsp;
                            </td></tr>
                        <tr><th align="left">Website</th><td >
                                <%=pcontact.iterator().next().getOwebsite()%>&nbsp;(Org),&nbsp;<%=pcontact.iterator().next().getPwebsite()%>&nbsp;(Per)
                            </td></tr>
                        <tr><th colspan="2" align="left">References</th></tr>
                    </table>
            <tr>
                <th colspan="2" align="left">Summary</th>
            </tr>
            <tr><th colspan="2" align="left">Experience Details</th></tr>
            <tr>
                <td colspan="2">
                    <table border="1" align="center" width="100%">
                        <tr><th>S. No</th>
                            <th>Job Title</th>
                            <th>Organization Name</th>
                            <th width="225">Address</th>
                            <th>Joining Date</th>
                            <th>Relieved On</th>
                            <th>Role/Description</th>

                        </tr>

                        <tr>  <td>  1</td>      
                            <td> <%=pemployment.iterator().next().getJtitle()%></td>
                            <td><%=pemployment.iterator().next().getOrgName()%></td>
                            <td width="225"><%=pemployment.iterator().next().getOaddress()%>
                                <%=pemployment.iterator().next().getOcity()%>
                                <%=pemployment.iterator().next().getOstate()%>
                                <%=pemployment.iterator().next().getOcountry()%>
                            </td>
                            <td><%=pemployment.iterator().next().getJdate()%></td>
                            <td><%=pemployment.iterator().next().getLdate()%>&nbsp;</td>
                            <td width="250"><%=pemployment.iterator().next().getDescription()%></td>

                        </tr>
                    </table>
                </td>      
            </tr>
            <tr>
                <th colspan="2" align="left">Skills Set</th>
            </tr>

            <tr>
                <td colspan="2">
                    <%
                        while (iskill.hasNext()) {
                    %>
                    <%=iskill.next().getSkills()%>,


                    <%
                        }
                    %>

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

                        <tr>
                            <td><%=pAcademic.iterator().next().getDegree()%></td>
                            <td><%=pAcademic.iterator().next().getFstudy()%></td>
                            <td><%=pAcademic.iterator().next().getUniversity()%></td>
                            <td><%=pAcademic.iterator().next().getPyear()%></td>
                            <td><%=pAcademic.iterator().next().getPercent()%></td>
                            <td><%=pAcademic.iterator().next().getLocation()%></td>
                            <td align="center"><%=pAcademic.iterator().next().getDivision()%></td>
                        </tr>

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

                        <tr valign="middle"> 
                            <td><%=pcertification.iterator().next().getCertificationName()%></td>
                            <td><%=pcertification.iterator().next().getCertificationAuthority()%>
                            </td>
                            <td><%=pcertification.iterator().next().getLicense()%></td>
                            <td width="85"><%=pcertification.iterator().next().getCertificationDate()%> &nbsp;-&nbsp; <%=pcertification.iterator().next().getValidDate()%>
                            </td>
                        </tr>

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

                        <tr>
                            <td>
                                <%=ptest.iterator().next().getTname()%>
                            </td><td>
                                <%=ptest.iterator().next().getScore()%>
                            </td><td>
                                <%=ptest.iterator().next().getTdate()%>
                            </td><td>
                                <%=ptest.iterator().next().getTdescription()%>
                            </td>

                        </tr>

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

                        <tr>
                            <td align="center"><%=i + 1%></td>
                            <td><%=projects.iterator().next().getProName()%></td>
                            <td><%=projects.iterator().next().getRole()%>
                            </td>
                            <td align="center"><%=projects.iterator().next().getTeamSize()%>
                            </td>
                            <td><%=projects.iterator().next().getStartDate()%>
                                -
                                <%=projects.iterator().next().getEndDate()%>
                            </td>
                            <td><%=projects.iterator().next().getAgency()%>
                            </td>
                            <td align="center"><%=projects.iterator().next().getBudget()%>
                            </td>
                            <td><%=projects.iterator().next().getDescription()%>
                            </td>    
                        </tr>

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

                        <tr>  
                            <td>
                                <%=phonor.iterator().next().getHaTitle()%>
                            </td><td>
                                <%=phonor.iterator().next().getIssuer()%>
                            </td><td>
                                <%=phonor.iterator().next().getHaDate()%>
                            </td><td>
                                <%=phonor.iterator().next().getHaDescription()%>
                            </td>
                        </tr>

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

                        <tr>
                            <td>1</td>
                            <td>


                                <%=patent.iterator().next().getLname()%>&nbsp;
                                <%=patent.iterator().next().getFname()%>,

                                "<i> <%=patent.iterator().next().getPatentTitle()%></i>",
                                <%=patent.iterator().next().getCountry()%>
                                <%=patent.iterator().next().getPatentNo()%>,
                                <%=patent.iterator().next().getPatentDate()%>.
                            </td>
                        </tr>

                        <tr><td colspan="2">Journals</td></tr>

                        <tr>
                            <td>1</td>
                            <td> 
                                <%=journal.iterator().next().getLname()%>&nbsp;
                                <%=journal.iterator().next().getFname()%>,


                                "<%=journal.iterator().next().getPaperTitle()%>",
                                <i><%=journal.iterator().next().getJournalName()%></i>,&nbsp;
                                Vol.&nbsp;<%=journal.iterator().next().getVolumeNo()%>&nbsp;(<%=journal.iterator().next().getDate()%>) ,
                                <%=journal.iterator().next().getSerialNo()%>,
                                <%=journal.iterator().next().getIssnNo()%> ,
                                pp.&nbsp;<%=journal.iterator().next().getPfrom()%>-<%=journal.iterator().next().getPto()%> .
                            </td>
                        </tr>                                  


                        <tr><td colspan="2" align="left">Conferences</td></tr>
                        <tr>
                            <td>1</td>
                            <td> 

                                <%=conference.iterator().next().getLname()%>&nbsp;
                                <%=conference.iterator().next().getFname()%>,

                                "<%=conference.iterator().next().getPaperTitle()%>",
                                in&nbsp;<%=conference.iterator().next().getConferenceName()%>,&nbsp;
                                <%=conference.iterator().next().getVenue()%>&nbsp;
                                <%=conference.iterator().next().getState()%>),
                                <%=conference.iterator().next().getCountry()%>,
                                <%=conference.iterator().next().getDto()%> ,
                                pp.&nbsp;<%=conference.iterator().next().getPfrom()%>-<%=conference.iterator().next().getPto()%> .
                            </td>
                        </tr>                                

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

                        <tr>  
                            <td><%=pAffilation.iterator().next().getRole()%>

                            </td><td>
                                <%=pAffilation.iterator().next().getOrgBody()%>
                            </td><td>
                                <%=pAffilation.iterator().next().getVfrom()%>-<%=pAffilation.iterator().next().getVupto()%>
                            </td><td>
                                <%=pAffilation.iterator().next().getPlace()%>
                            </td><td>
                                <%=pAffilation.iterator().next().getCountry()%>
                            </td><td>
                                <%=pAffilation.iterator().next().getSummary()%>
                            </td>
                        </tr>

                    </table>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <table align="center" border="1" width="100%">
                        <tr>
                            <th width="150">Name &amp; Designation</th>
                            <th>Address, Email ID, Phone No.</th>

                        </tr>

                        <tr>
                            <td><strong><%=preference.iterator().next().getName()%></strong><br/>
                                <%=preference.iterator().next().getDesignation()%></td>
                            <td>
                                <%=preference.iterator().next().getDepartment()%><br/>
                                <%=preference.iterator().next().getOrgUniv()%><br/>
                                <%=preference.iterator().next().getPlace()%>
                                <%=preference.iterator().next().getCity()%>
                                <%=preference.iterator().next().getState()%>
                                <%=preference.iterator().next().getCountry()%><br/>
                                <%=preference.iterator().next().getPhoneno()%>
                                <%=preference.iterator().next().getMobileno()%><br/>
                                <%=preference.iterator().next().getEmailId()%><br/>
                                <%=preference.iterator().next().getWebsite()%>
                            </td>


                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <th align="left" valign="top" height="50px;">Date</th>
                <th align="right" valign="top">Signature</th>
            </tr>
        </table>
    </td>
</tr>
</table>
</body>
</html>
