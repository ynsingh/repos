<%-- 
    Document   : candidate_registration
    Created on : 18 Jun, 2011, 7:28:39 PM
    Author     : akhtar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" import="com.myapp.struts.hbm.*,java.util.*" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%! boolean read = true;%>

<script type="text/javascript">

    
      function submit()
      {
          //alert(document.getElementById("img").value);

          document.getElementsById("filename").value=document.getElementById("img").value;
          //alert(document.getElementsById("filename").value);
      }
  function copy()
                                        {
                                            var a=document.getElementById("cadd1").value;
                                            var b=document.getElementById("city1").value;
                                            var c=document.getElementById("state1").value;
                                            var d=document.getElementById("zcode1").value;
                                            var e=document.getElementById("country1").value;
                                            document.getElementById("padd1").value=a;
                                            document.getElementById("city21").value=b;
                                            document.getElementById("state21").value=c;
                                            document.getElementById("zcode21").value=d;
                                            document.getElementById("country21").value=e;
                                        }
                                    
      function send()
      {
          <%--window.location=--%> window.back();
          return false;
      }
</script>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  
    <link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />

    <%
        String id = request.getParameter("id");
         List<CandidateRegLoginDetails> lstcand = (List<CandidateRegLoginDetails>)session.getAttribute("nominationList");
         CandidateRegLoginDetails cand = lstcand.get(Integer.parseInt(id));
         if(cand!=null){
         VoterRegistration voter = (VoterRegistration)cand.getVoterRegistration();
         Election e = (Election)cand.getElection();
         Position1 p = cand.getPosition1();
         String enrollment = (String) voter.getId().getEnrollment();
                String instituteid = (String) voter.getId().getInstituteId();
                String dep = (String) voter.getDepartment();
                String cour = (String) voter.getCourse();
                String dur = (String) voter.getCourseDuration();
                String sess = (String) voter.getCurrentSession();
                String jdate = (String) voter.getJoiningDate();
                String vname = (String) voter.getVoterName();
                String gen = (String) voter.getGender();
                String bdate = (String) voter.getBirthdate();
                String fname = (String) voter.getFName();
                String mname = (String) voter.getMName();
                String yr = (String) voter.getYear();
                String mnumb = (String) voter.getMobileNumber();
                String cadd = (String) voter.getCAddress();
                String padd = (String) voter.getPAddress();
                String city = (String) voter.getCity();
                String city1 = (String) voter.getCity1();
                String state = (String) voter.getState();
                String state1 = (String) voter.getState1();
                String country = (String) voter.getCountry();
                String country1 = (String) voter.getCountry1();
                String zcode = (String) voter.getZipCode();
                String zcode1 = (String) voter.getZipCode1();
                String email = (String) voter.getEmail();
                byte[] img = (byte[]) voter.getImage();
                session.setAttribute("image",img);
           session.setAttribute("voter",voter);
                //String file = (String) request.getAttribute("filename");
                String position=(String)p.getPositionName();
                String election=(String) e.getElectionName();
    %>



    <script language="javascript" type="text/javascript" src="js/datetimepicker.js"></script>



  
</head>



<body>
   
    


    <div
        style="  top:60px;
        left:70%;
        right:5px;
        position: absolute;

        visibility: show;" >
        

        <%if (session.getAttribute("image") != null) {%>
        <html:img src="/EMS/Candidate/viewimage.jsp"  alt="no Image Selected" width="100" height="100"/>

        <%} else {%>

        <html:img src="/EMS/images/no-image.jpg"  alt="no Image Selected" width="100" height="100"/>
        <%}%>
        

    </div>


   
    <html:form action="/candidateregistration" method="post"   onsubmit="return check3()" styleId="radio_form">


    
             <table  class="table" align="center" width="80%">
                        <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="10px;" colspan="2"><b>Candidate Details </b></td></tr>
                        <tr><td >
                                <table class="txtStyle">
                                    <tr>
                                        <td >Enrollment Number*:</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm"  styleId="enrollment1" property="enrollment"  value="<%=enrollment%>" /></td><td>
                                        <td></td>

                                    </tr>
                                    <%--<tr>
                                        <td width="30%">Institute Name*:</td><td><html:text readonly="<%=read %>"  name="CandidateRegActionForm"  styleId="ins1"  value="<%=instituteid%>"  property="institute_id"/></td><td width="30%">
                                    <td></td>
                                    </tr>--%>
                                    <tr><td align="left">Institute Name*</td><td>
                                            <html:select property="institute_id" styleId="ins1" value="<%=instituteid%>" name="CandidateRegActionForm"  tabindex="10" disabled="<%=read%>">
 <html:option  value="Select"> Select </html:option>
            <html:options collection="Institute"  labelProperty="instituteName" property="instituteId"  name="Institute" ></html:options>
                                               <%-- <html:option  value="Select"> Select </html:option>
                                                <html:option  value="amu">Aligarh muslim university</html:option>
                                                <html:option value="jmi">Jamia Millia islamia</html:option>
                                                <html:option value="du">Delhi University</html:option>
                                                <html:option value="jnu">JNU</html:option>--%>
                                            </html:select>

                                    </tr>
                                    <tr>
                                        <td align="left">Department*:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" styleId="dep1" property="department"  value="<%=dep%>" /></td>

                                    </tr>

                                    <tr>
                                        <td align="left">Course*:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" styleId="cour1" property="course" value="<%=cour%>"/></td>
                                    </tr>

                                    <tr>
                                        <td align="left">Year :</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm" styleId="year1" property="year" value="<%=yr%>"/></td>

                                    </tr>
                                     <tr><td  width="30%">Duration of course:</td><td><html:text  readonly="<%=read%>" name="CandidateRegActionForm" styleId="dur1" value="<%=dur%>" property="duration" /> </td><td align="left">Corresponding Address:</td><td> <html:text readonly="<%=read%>" name="CandidateRegActionForm" styleId="cadd1" property="c_add" value="<%=cadd%>"  /></td>
                                    </tr>
                                    <tr>
                                        <td align="left">Current Session:</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm" styleId="sess1" value="<%=sess%>" property="session"  /></td>  <td align="left">City:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="city"  value="<%=city%>" styleId="city1"/></td>
                                    </tr
                                    <tr><td width="15%">Date of Joining<br>(DD-MM-YYYY)</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm" styleId="1" property="j_date" value="<%=jdate%>" />
                                            <%--<a href="javascript:NewCal('1','ddmmmyyyy')"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>--%></td>

 <td align="left">State:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="state" value="<%=state%>" styleId="state1"/></td></tr>
                                    <tr>
                                        <td align="left">Candidate Name*</td>
                                        <td>
                                            <table><tr>
                                                    <td><html:text readonly="<%=read%>"  name="CandidateRegActionForm"  styleId="vname1"  value="<%=vname%>"  property="v_name"/></td>
                                            </table>
                                        </td><td align="left">Zip Code:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="zipcode"  value="<%=zcode%>" styleId="zcode1"/></td>
                                    </tr>
                                    <tr><td align="left">Gender*</td><td>
                                            <html:select property="gender" styleId="gen1" value="<%=gen%>"  name="CandidateRegActionForm"  tabindex="10" disabled="<%=read%>">

                                                <html:option  value="Select"> Select </html:option>
                                                <html:option  value="male">Male</html:option>
                                                <html:option value="female">Female</html:option>

                                            </html:select>
                                        </td>  <td align="left">Country:*</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="country"  value="<%=country%>" styleId="country1"/></td>
                                    </tr>
                                    <tr>
                                        <td>Date of Birth*<br>(DD-MM-YYYY)</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm"  property="b_date"  value="<%=bdate%>" styleId="3" />
                                            <%--<a href="javascript:NewCal('3','ddmmmyyyy')"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>--%></td>
                                     <td colspan="2"><input type="checkbox" id="Checkbox1" name="check" value="off" tabindex="17" onclick="return copy();" >&nbsp;&nbsp;<b>Click Here</b>&nbsp;(If permanent address is same as corresponding address)</td>
                                    </tr>


                                    <tr> <td>Father's Name*</td><td><html:text  readonly="<%=read%>" name="CandidateRegActionForm" styleId="fname1"  value="<%=fname%>"  property="f_name"/></td>
                                     <td align="left">Permanent Address</td><td><html:text  readonly="<%=read%>" name="CandidateRegActionForm" property="p_add" value="<%=padd%>" styleId="padd1"/></td>
                                    </tr>
                                    <tr> <td>Mother's Name*</td><td><html:text readonly="true" name="CandidateRegActionForm" styleId="mname1" value="<%=mname%>" property="m_name"/></td>
                                         <td align="left">City</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="city1" value="<%=city1%>"  styleId="city21"/></td>
                                    </tr>

                                    <tr>
                                        <td align="left">Mobile No*:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" styleId="mnumb1" value="<%=mnumb%>" property="m_number" /></td>
                                        <td align="left">State</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="state1" value="<%=state1%>" styleId="state21"/></td>
                                    </tr>
                                     <tr>

                            <td align="left" >email*:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm"   value="<%=email%>" styleId="email1" property="email"/></td>
                             <td align="left">ZIP Code</td><td><html:text  readonly="<%=read%>" name="CandidateRegActionForm" property="zipcode1"  value="<%=zcode1%>" styleId="zcode21"/></td>

                        </tr>
                        <tr><td align="left">Country</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="country1" value="<%=country1%>" styleId="country21"/></td></tr>
                         <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="10px;" colspan="4"><b>Nomination Detail </b></td></tr>


            <tr><td>Election*</td><td> <html:text name="CandidateRegActionForm" property="elections" value="<%=election%>" readonly="<%=read%>"/>
                </td>
            <td>Position*</td><td><html:text name="CandidateRegActionForm" property="position" value="<%=position%>" readonly="<%=read%>"/>
                </td>
            </tr>
             <tr>
                <td align="center" colspan="4">










                    <input name="button" type="button" value="Back" onclick="return send()" class="txt1"/>

                </td>
            </tr>
                                </table>
                            </td></tr>

                       
                       
          
           
            



        
           
           </table>


    </html:form>

</body>
<%}else{%>
<p>No Record Found with this id!</p>
<%}%>
</html>
