<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" %>
<%@ page import="java.util.*,java.lang.*"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>

<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
String user=(String)session.getAttribute("username");
String instituteName=(String)session.getAttribute("institute_name");
 String contextPath = request.getContextPath();
 String role=(String)session.getAttribute("login_role");
    %>

<%! boolean read = false;%>
<%
            String btn = (String) request.getAttribute("button");
            if (btn.equals("Add") || btn.equals("Delete")) {
                read = true;
            } else {
                read = false;
            }
            String status = request.getParameter("status");
            if(status==null)
            status=(String)request.getAttribute("Status");
            if(status.equalsIgnoreCase("U")){read = false;btn="Update";}

            String msg1 = (String) request.getAttribute("msg1");
%>
<script type="text/javascript">

    <%--function check2()
{
    if(document.getElementById('enrollment').value=="")
    {
        alert("Enter Enrollment");

        document.getElementById('enrollment').focus();

        return false;
    }

  }--%>
      function submit()
      {
          //alert(document.getElementById("img").value);

          document.getElementsById("filename").value=document.getElementById("img").value;
          alert(document.getElementsById("filename").value);
      }

     function send()
      {
          //alert(document.getElementById("img").value);

         window.location="<%=request.getContextPath()%>/election_manager/search_candidate.jsp?status=A";
         return true;
      }

</script>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>EMS</title>
    <link href="/EMS/css/Style1.css" rel="stylesheet" type="text/css" />
    <link href="/EMS/css/page.css" rel="stylesheet" type="text/css" />

    <%

                String enrollment = (String) request.getAttribute("enrollment");
                String instituteid = (String) request.getAttribute("instituteid");
                String dep = (String) request.getAttribute("dep");
                String cour = (String) request.getAttribute("cour");
                String dur = (String) request.getAttribute("dur");
                String sess = (String) request.getAttribute("sess");
                String jdate = (String) request.getAttribute("jdate");
                String vname = (String) request.getAttribute("vname");
                String gen = (String) request.getAttribute("gen");
                String bdate = (String) request.getAttribute("bdate");
                String fname = (String) request.getAttribute("f_name");
                String mname = (String) request.getAttribute("m_name");
                String yr = (String) request.getAttribute("year");
                String mnumb = (String) request.getAttribute("mnumb");
                String cadd = (String) request.getAttribute("cadd");
                String padd = (String) request.getAttribute("padd");
                String city = (String) request.getAttribute("city");
                String city1 = (String) request.getAttribute("city1");
                String state = (String) request.getAttribute("state");
                String state1 = (String) request.getAttribute("state1");
                String country = (String) request.getAttribute("country");
                String country1 = (String) request.getAttribute("country1");
                String zcode = (String) request.getAttribute("zcode");
                String zcode1 = (String) request.getAttribute("zcode1");
                String email = (String) request.getAttribute("email");
                String img = (String) request.getAttribute("image");
                String file = (String) request.getAttribute("filename");
                String position=(String)request.getAttribute("position");
                String election=(String)request.getAttribute("election");
                String alternateemail=(String)request.getAttribute("alternateemail");
                String institute_id=(String)session.getAttribute("institute_id");
                String proposedby=(String)session.getAttribute("proposedby");
                String secondedby=(String)session.getAttribute("secondedby");
                String positionaccepted=(String)session.getAttribute("positionaccepted");
    %>



    <script language="javascript" type="text/javascript" src="js/datetimepicker.js"></script>



    <script type="text/javascript">



        function check3()
        {
   
            if(document.getElementById('ins1').options[document.getElementById('ins1').selectedIndex].value=="Select")
            {
                alert("Enter Institute Name");

                document.getElementById('ins1').focus();

                return false;
            }
            if(document.getElementById('dep1').value=="")
            {
                alert("Enter department Name");

                document.getElementById('dep1').focus();

                return false;
            }

            if(document.getElementById('cour1').value=="")
            {
                alert("Enter Course ");

                document.getElementById('cour1').focus();

                return false;
            }

            if(document.getElementById('vname1').value=="")
            {
                alert("Enter  Voter Name");

                document.getElementById('vname1').focus();

                return false;
            }
            if(document.getElementById('gen1').options[document.getElementById('gen1').selectedIndex].value=="Select")
            {
                alert("Enter  Gender");

                document.getElementById('gen1').focus();

                return false;
            }
            if(document.getElementById('3').value=="")
            {
                alert("Enter BirthDate");

                document.getElementById('3').focus();

                return false;
            }
            if(document.getElementById('fname1').value=="")
            {
                alert("Enter Father's Name");

                document.getElementById('fname1').focus();

                return false;
            }
            if(document.getElementById('mname1').value=="")
            {
                alert("Enter Mother's Name");

                document.getElementById('mname1').focus();

                return false;
            }
            if(document.getElementById('mnumb1').value=="")
            {
                alert("Enter Mobile Number");

                document.getElementById('mnumb1').focus();

                return false;
            }
            if(document.getElementById('email1').value=="")
            {
                alert("Enter Email ID");

                document.getElementById('email1').focus();

                return false;
            }
            if(document.getElementById('country1').value=="")
            {
                alert("Enter Country");

                document.getElementById('country1').focus();

                return false;
            }
            if(document.getElementById('enrolled').options[document.getElementById('enrolled').selectedIndex].value=="Select")
            {
                alert("Select Enrolled Level");

                document.getElementById('enrolled').focus();

                return false;
            }
            if(document.getElementById('mark').value=="")
            {
                alert("Enter % Marks in Last Exam");

                document.getElementById('mark').focus();

                return false;
            }
            if(document.getElementById('attendence').value=="")
            {
                alert("Enter %age of Attendence");

                document.getElementById('attendence').focus();

                return false;
            }

             var radio_choice = false;

            // Loop from zero to the one minus the number of radio button selections
            for (counter = 0; counter < document.getElementById("radio_form").backlog.length; counter++)
            {
                // If a radio button has been selected it will return true
                // (If not it will return false)

                if (document.getElementById("radio_form").backlog[counter].checked)
                    radio_choice = true;
            }

            if (!radio_choice)
            {
                // If there were no selections made display an alert box
                alert("Please choose backlog option")
                return false;
            }
          radio_choice = false;
            for (counter = 0; counter < document.getElementById("radio_form").criminal.length; counter++)
            {
                if (document.getElementById("radio_form").criminal[counter].checked)
                    radio_choice = true;
            }

            if (!radio_choice)
            {
                // If there were no selections made display an alert box
                alert("Please choose option whether u have criminal log or not")
                return false;
            }


 radio_choice = false;
            for (counter = 0; counter < document.getElementById("radio_form").indisc.length; counter++)
            {
                if (document.getElementById("radio_form").indisc[counter].checked)
                    radio_choice = true;
            }

            if (!radio_choice)
            {
                // If there were no selections made display an alert box
                alert("Please choose option whether u have  involed in Indiscipilinary action  or not")
                return false;
            }

/*radio_choice = false;
            for (counter = 0; counter < document.getElementById("radio_form").position.length; counter++)
            {
                if (document.getElementById("radio_form").position[counter].checked)
                    radio_choice = true;
            }

            if (!radio_choice)
            {
                // If there were no selections made display an alert box
                alert("Please choose position for which u r goin to register")
                return false;
            }


            return true;
        }*/

        
            // set var radio_choice to false
           
            
        













        function copy1(){
            var i=document.getElementById("enrollment");
            var j=document.getElementById("enrollment1");
            i.value=j.value;
            var ins=document.getElementById("ins");
            var ins1=document.getElementById("ins1");
            ins.value=ins1.value;
            var dep=document.getElementById("dep");
            var dep1=document.getElementById("dep1");
            dep.value=dep1.value;
            var cour=document.getElementById("cour");
            var cour1=document.getElementById("cour1");
            cour.value=cour1.value;
            var year=document.getElementById("year");
            var year1=document.getElementById("year1");
            year.value=year1.value;
            var dur=document.getElementById("dur");
            var dur1=document.getElementById("dur1");
            dur.value=dur1.value;
            var sess=document.getElementById("sess");
            var sess1=document.getElementById("sess1");
            sess.value=sess1.value;
            var jdate=document.getElementById("jdate");
            var jdate1=document.getElementById("1");
            jdate.value=jdate1.value;
            var vname=document.getElementById("vname");
            var vname1=document.getElementById("vname1");
            vname.value=vname1.value;
            var gen=document.getElementById("gen");
            var gen1=document.getElementById("gen1");
            gen.value=gen1.value;
            var bdate=document.getElementById("bdate");
            var bdate1=document.getElementById("3");
            bdate.value=bdate1.value;
            var fname=document.getElementById("fname");
            var fname1=document.getElementById("fname1");
            fname.value=fname1.value;

            var mname=document.getElementById("mname");
            var mname1=document.getElementById("mname1");
            mname.value=mname1.value;
            var mnumb=document.getElementById("mnumb");
            var mnumb1=document.getElementById("mnumb1");
            mnumb.value=mnumb1.value;
            var cadd=document.getElementById("cadd");
            var cadd1=document.getElementById("cadd1");
            cadd.value=cadd1.value;
            var city=document.getElementById("city");
            var city1=document.getElementById("city1");
            city.value=city1.value;
            var state=document.getElementById("state");
            var state1=document.getElementById("State1");
            state.value=state1.value;
            var state2=document.getElementById("state2");
            var state21=document.getElementById("state21");
            state2.value=state21.value;
            var zcode=document.getElementById("zcode");
            var zcode1=document.getElementById("zcode1");
            zcode.value=zcode1.value;
            var country=document.getElementById("country");
            var country1=document.getElementById("country1");
            country.value=country1.value;
            var padd=document.getElementById("padd");
            var padd1=document.getElementById("padd1");
            cadd.value=padd1.value;
            var city2=document.getElementById("city2");
            var city21=document.getElementById("city21");
            city2.value=city21.value;
            var zcode2=document.getElementById("zcode2");
            var zcode21=document.getElementById("zcode21");
            zcode2.value=zcode21.value;
            var country2=document.getElementById("country2");
            var country21=document.getElementById("country21");
            country2.value=country21.value;
            var email=document.getElementById("email");
            var email1=document.getElementById("email1");
            email.value=email1.value;


             var election=document.getElementById("elections");
            var election1=document.getElementById("elections1");
            election.value=election1.value;




             var pos=document.getElementById("position");
            var pos1=document.getElementById("position1");
            pos.value=pos1.value;


            var button=document.getElementById("button");
            var button1=document.getElementById("button1");
            button.value=button1.value;
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
    </script>
</head>



<body>
 

    
    <%--  <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>--%>


    <div
        style="  top:10%;
        left:55%;
        right:5px;
        position: absolute;

        visibility: show;" >
        <%if (btn.equals("View") == true) {%>

        <%if (session.getAttribute("image") != null) {%>
        <html:img src="/EMS/Candidate/upload.jsp"  alt="no Image Selected" width="80" height="80"/>

        <%} else {%>

        <html:img src="/EMS/images/no-image.jpg"  alt="no Image Selected" width="80" height="80"/>
        <%}%>


        <%} else {%>

        <%if (request.getAttribute("imagechange") != null) {%>
        <html:img src="/EMS/Candidate/upload.jsp"  alt="no Image Selected" width="80" height="80"/>
        <%} else {%>
        <html:img src="/EMS/Candidate/viewimage.jsp" alt="no image selected" width="80" height="80" />
        <%}%><br/>


        <%}%>

    </div>


    <div
        style="  top:50px;
        left:60%;
        right:5px;
        position: absolute;

        visibility: visible; z-index: 100;" >


        <html:form action="/candidateimageupload1" method="post" styleId="form1" enctype="multipart/form-data">
            <%if (btn.equals("Update") == true || btn.equals("View") == true) {%>
            <html:file  property="img" name="CandidateRegActionForm" styleId="img" onchange="submit()"  onclick="copy1()" />
       
        <%}%>


            <input type="hidden" name="filename" tabindex="16" id="filename" />

            <html:hidden property="enrollment" name="CandidateRegActionForm" styleId="enrollment"/>
            <html:hidden property="institute_id" name="CandidateRegActionForm" styleId="ins"/>
            <html:hidden property="department" name="CandidateRegActionForm" styleId="dep"/>
            <html:hidden property="course" name="CandidateRegActionForm" styleId="cour"/>
            <html:hidden property="year" name="CandidateRegActionForm" styleId="year"/>
            <html:hidden property="duration" name="CandidateRegActionForm" styleId="dur"/>
            <html:hidden property="session" name="CandidateRegActionForm" styleId="sess"/>
            <html:hidden property="j_date" name="CandidateRegActionForm" styleId="jdate"/>
            <html:hidden property="v_name" name="CandidateRegActionForm" styleId="vname"/>
            <html:hidden property="gender" name="CandidateRegActionForm" styleId="gen"/>
            <html:hidden property="b_date" name="CandidateRegActionForm" styleId="bdate"/>
            <html:hidden property="f_name" name="CandidateRegActionForm" styleId="fname"/>
            <html:hidden property="m_name" name="CandidateRegActionForm" styleId="mname"/>

            <html:hidden property="m_number" name="CandidateRegActionForm" styleId="mnumb"/>
            <html:hidden property="c_add" name="CandidateRegActionForm" styleId="cadd"/>
            <html:hidden property="city" name="CandidateRegActionForm" styleId="city"/>
            <html:hidden property="state" name="CandidateRegActionForm" styleId="state"/>
            <html:hidden property="zipcode" name="CandidateRegActionForm" styleId="zcode"/>
            <html:hidden property="country" name="CandidateRegActionForm" styleId="country"/>
            <html:hidden property="p_add" name="CandidateRegActionForm" styleId="padd"/>
            <html:hidden property="city1" name="CandidateRegActionForm" styleId="city2"/>

            <html:hidden property="state1" name="CandidateRegActionForm" styleId="state2"/>

            <html:hidden property="zipcode1" name="CandidateRegActionForm" styleId="zcode2"/>

            <html:hidden property="country1" name="CandidateRegActionForm" styleId="country2"/>
            <html:hidden property="position" name="CandidateRegActionForm" styleId="position2"/>
            <html:hidden property="elections" name="CandidateRegActionForm" styleId="election2"/>

            <html:hidden property="email" name="CandidateRegActionForm" styleId="email"/>
            <html:hidden property="button" name="CandidateRegActionForm" styleId="button"/>
        </html:form>



    </div>
    <html:form action="/candidateregistration1" method="post"    onsubmit="return check3()" styleId="radio_form">

        <table  dir="<%=rtl%>" class="datagrid" align="center" width="60%">
        <tr><td class="header" align="center" colspan="3" bgcolor="cyan"> <%=resource.getString("candidateregform")%></td></tr>
         <tr><td>
              
                                    <tr>
                                        <td ><%=resource.getString("enrollment")%>*:</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm"  styleId="enrollment1" property="enrollment"  value="<%=enrollment%>" /></td>
                                        
                                        <td rowspan="19">
                                            
                                            
                                            
                                            <table>
                                         <tr>
                                        <td align="left" colspan="2"></td>
                                    </tr>
                                           <tr>
                                        <td align="left"><%=resource.getString("postaladd")%>:</td> <td><html:text readonly="<%=read%>" name="CandidateRegActionForm" styleId="cadd1" property="c_add" value="<%=cadd%>"  /></td>
                                    </tr>
                                    <tr>
                                        <td align="left"><%=resource.getString("city")%>:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="city"  value="<%=city%>" styleId="city1"/></td>
                                    </tr>

                                    <tr>
                                        <td align="left"><%=resource.getString("state")%>:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="state" value="<%=state%>" styleId="state1"/></td>
                                    </tr>


                                    <tr>
                                        <td align="left"><%=resource.getString("pin")%>:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="zipcode"  value="<%=zcode%>" styleId="zcode1"/></td>
                                    </tr>
                                    <tr>
                                        <td align="left"><%=resource.getString("country")%>:*</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="country"  value="<%=country%>" styleId="country1"/></td>

                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2"><input type="checkbox" id="Checkbox1" name="check" value="off" tabindex="17" onclick="return copy();" >&nbsp;&nbsp;<b>Click Here</b>&nbsp;(If permanent address is same as corresponding address)</td>
                                    </tr>
                                    <tr>    <td align="left"><%=resource.getString("permanent")%></td><td><html:text  readonly="<%=read%>" name="CandidateRegActionForm" property="p_add" value="<%=padd%>" styleId="padd1"/></td></tr>
                                    <tr>    <td align="left"><%=resource.getString("city")%></td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="city1" value="<%=city1%>"  styleId="city21"/></td></tr>
                                    <tr>    <td align="left"><%=resource.getString("state")%></td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="state1" value="<%=state1%>" styleId="state21"/></td></tr>
                                    <tr> <td align="left"><%=resource.getString("pin")%></td><td><html:text  readonly="<%=read%>" name="CandidateRegActionForm" property="zipcode1"  value="<%=zcode1%>" styleId="zcode21"/></td><td colspan="2"></tr>
                                    <tr><td align="left"><%=resource.getString("country")%></td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="country1" value="<%=country1%>" styleId="country21"/></td></tr>
                                     <tr><td align="left">Alternate Email</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" property="alternateemail" value="<%=alternateemail%>" styleId="alternateemail"/></td></tr>

                                    
                                            </table>


                                        </td>

                                    </tr>
                                    
                                    <tr><td align="left"><%=resource.getString("institutename")%>*</td><td>
                                            <html:select property="institute_id" styleId="ins1"  name="CandidateRegActionForm"  tabindex="10" disabled="true">
                                                 <html:option  value="Select"> Select </html:option>
            <html:options collection="Institute"  labelProperty="instituteName" property="instituteId"  name="Institute" ></html:options>
                                                  </html:select>
            <html:hidden property="institute_id" value="<%=institute_id%>"  name="CandidateRegActionForm"/>
                                    </tr>
                                    <tr>
                                        <td align="left"><%=resource.getString("department")%>*:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" styleId="dep1" property="department"  value="<%=dep%>" /></td>

                                    </tr>

                                    <tr>
                                        <td align="left"><%=resource.getString("course")%>*:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" styleId="cour1" property="course" value="<%=cour%>"/></td>

                                    </tr>

                                    <tr>
                                        <td align="left"> <%=resource.getString("year")%>:</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm" styleId="year1" property="year" value="<%=yr%>"/></td>

                                    </tr>
                                    <tr><td  width="30%"><%=resource.getString("courseduration")%>:</td><td><html:text  readonly="<%=read%>" name="CandidateRegActionForm" styleId="dur1" value="<%=dur%>" property="duration" /> </td><td width="30%">
                                    </tr>
                                    <tr>
                                        <td align="left"><%=resource.getString("currentsession")%>:</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm" styleId="sess1" value="<%=sess%>" property="session"  /></td>
                                    </tr
                                    <tr><td width="15%"><%=resource.getString("dateofjoin")%><br>(DD-MM-YYYY)</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm" styleId="1" property="j_date" value="<%=jdate%>" />
                                            <a href="javascript:NewCal('1','ddmmmyyyy')"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></td></tr>


                                    <tr>
                                        <td align="left"><%=resource.getString("candidatename")%>*</td>
                                        <td>
                                            <table ><tr><td>
                                                        <select name="courtesy" size="1" id="courtesy" tabindex="2" style="align:right" disabled="<%=read%>">

                                                            <option selected value="Select">Select</option>
                                                            <option  value="mr">Mr.</option>
                                                            <option value="mrs">Mrs.</option>
                                                            <option  value="ms">Ms.</option>
                                                        </select></td>
                                                    <td><html:text readonly="<%=read%>"  name="CandidateRegActionForm"  styleId="vname1"  value="<%=vname%>"  property="v_name"/></td>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr><td align="left"><%=resource.getString("gender")%>*</td><td>
                                            <html:select property="gender" styleId="gen1"  name="CandidateRegActionForm"  tabindex="10" disabled="<%=read%>">

                                                <html:option  value="Select"> Select </html:option>
                                                <html:option  value="male">Male</html:option>
                                                <html:option value="female">Female</html:option>

                                            </html:select>

                                    </tr>
                                    <tr>
                                        <td><%=resource.getString("birthday")%>*<br>(DD-MM-YYYY)</td><td><html:text readonly="<%=read%>"  name="CandidateRegActionForm"  property="b_date"  value="<%=bdate%>" styleId="3" />
                                            <a href="javascript:NewCal('3','ddmmmyyyy')"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></td>
                                    </tr>


                                    <tr> <td><%=resource.getString("fathername")%>*</td><td><html:text  readonly="<%=read%>" name="CandidateRegActionForm" styleId="fname1"  value="<%=fname%>"  property="f_name"/></td></tr>
                                    <tr> <td><%=resource.getString("mothername")%>*</td><td><html:text readonly="true" name="CandidateRegActionForm" styleId="mname1" value="<%=mname%>" property="m_name"/></td>
                                    </tr>

                                    <tr>
                                        <td align="left"><%=resource.getString("mobileno")%>*:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm" styleId="mnumb1" value="<%=mnumb%>" property="m_number" /></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><%=resource.getString("important")%>! <%=resource.getString("workingemail")%>:</td>

                        </tr>
                        






                        <tr>

                            <td align="left" ><%=resource.getString("emailid")%>*:</td><td><html:text readonly="<%=read%>" name="CandidateRegActionForm"   value="<%=email%>" styleId="email1" property="email"/></td>

                        </tr>
                        

                        <tr><td align="center" height="10px;" colspan="3" bgcolor="cyan">Academic Detail</td></tr>
                        <tr><td align=""><%=resource.getString("department")%>*
                            </td><td> <html:select property="enrolled_in" styleId="enrolled"  name="CandidateRegActionForm"  tabindex="10" disabled="<%=read%>">

                                    <html:option  value="Select"> Select </html:option>
                                    <html:option  value="ug">Under Graduate</html:option>
                                    <html:option value="pg">Post Graduate</html:option>
                                    <html:option value="other">Others</html:option>

                                </html:select>   </td>
                        </tr>
                        <tr>
                            <td align="left"><%=resource.getString("marks")%> %*:</td><td><html:text  name="CandidateRegActionForm" styleId="mark" property="p_marks" readonly="<%=read%>" /></td>
                        </tr>
                        <tr>
                            <td align="left"> <%=resource.getString("attendence")%>%*:</td><td><html:text  name="CandidateRegActionForm" styleId="attendence" property="p_attendence"  readonly="<%=read%>"/></td>
                        </tr>
              

            <tr><td align="left" ><%=resource.getString("backlog")%>*
                    </td><td><html:radio name="CandidateRegActionForm" property="backlog" value="yes" disabled="<%=read%>"/>Yes<html:radio name="CandidateRegActionForm" property="backlog" styleId="backlog" value="no" disabled="<%=read%>"/>N0 </td>
            </tr>
             <tr><td align="left"><%=resource.getString("crimi")%>*</td><td><html:radio name="CandidateRegActionForm" property="criminal"  value="yes" disabled="<%=read%>"/>Yes<html:radio  name="CandidateRegActionForm" property="criminal"  styleId="criminal" value="no" disabled="<%=read%>"/>No</td></tr>
            <tr><td><%=resource.getString("indisc")%>* </td><td><html:radio name="CandidateRegActionForm" property="indisc" value="yes" disabled="<%=read%>"/>Yes<html:radio name="CandidateRegActionForm" property="indisc"  value="no" disabled="<%=read%>"/>No</td></tr>
      
            <tr><td><%=resource.getString("electionname")%>* </td><td><html:text name="CandidateRegActionForm" styleId="elections1" property="elections" value="<%=election%>" readonly="<%=read%>"/>
                </td>
            </tr>
            
            <tr><td><%=resource.getString("positionname")%>* </td><td><html:text name="CandidateRegActionForm" styleId="position1" property="position" value="<%=position%>" readonly="<%=read%>"/>
                </td>
            </tr>

                                   


           
            <tr>
                <td align="center" colspan="3">

                 <%=resource.getString("(*)")%>
                    <br>




                    <%if (btn.equals("Update")) {%>
                    <input id="button1"  name="button" type="submit" value="<%=btn%>" class="txt1" />
              
                    <%} else if (btn.equals("Delete")) {%>
                    <input id="button1"  name="button" type="submit" value="<%=btn%>" class="txt1" />
                 
                    <%} else if (btn.equals("View")) {%>
                    <input id="button1"  name="button" type="submit" value="<%=resource.getString("accept")%>" class="txt1" />
           
           <%} else if(status.equals("R")==false){%>
           <input name="button" type="button" value="back" onclick="return send()"  class="txt1"/>
                   <%}%>

                  <%-- <input name="button" type="button" value="<%=resource.getString("cancel")%>" onclick="return send()" class="txt1"/>
                   --%>
              
                </td>
                
               
          

            </tr>
           
              

    </table>
                            
         
                       
                                      
                                   


    </html:form>


