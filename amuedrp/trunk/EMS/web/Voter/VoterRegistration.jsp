
<%@ page language="java" import="com.myapp.struts.hbm.VoterRegistration"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%! boolean read=false;%>
<%
String btn=(String)request.getAttribute("button");
if(btn.equals("AB")) btn="Block";
request.setAttribute("button",btn);
if(btn.equals("View")||btn.equals("Delete"))
read=true;
else
  {
   read=false;
}
String msg1=(String) request.getAttribute("msg1");
VoterRegistration voter = (VoterRegistration)session.getAttribute("voter");
String status = voter.getStatus();
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
        //alert(document.getElementsById("filename").value);
        return true;
    }

function send()
{
    var host = "<%=request.getHeader("host")%>";
    var str = "http://"+host+"<%=request.getContextPath()%>/election_manager/search_voter.jsp";
    var winloc = top.location.href;
    var winpath = winloc.substr(0, winloc.lastIndexOf("?"));
    if(winpath==str)
    {
        window.location = top.location;
        return false;
    }
    else{
        window.location="<%=request.getContextPath()%>/electionmanager.do";
    return false;}
}
</script>

    <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>EMS</title>
<%--<link href="<%=request.getContextPath()%>/css/Style1.css" rel="stylesheet" type="text/css" />--%>

 <%
String institute_id = (String)session.getAttribute("institute_id");
String enrollment=(String)request.getAttribute("enrollment");
String instituteid=(String)request.getAttribute("instituteid");
String dep=(String)request.getAttribute("dep");
String cour=(String)request.getAttribute("cour");
String dur=(String)request.getAttribute("dur");
String sess=(String)request.getAttribute("sess");
String jdate=(String)request.getAttribute("jdate");
String vname=(String)request.getAttribute("vname");
String gen=(String)request.getAttribute("gen");
String bdate=(String)request.getAttribute("bdate");
String fname=(String)request.getAttribute("f_name");
String mname=(String)request.getAttribute("m_name");
String yr=(String)request.getAttribute("year");
String mnumb=(String)request.getAttribute("mnumb");
String cadd=(String)request.getAttribute("cadd");
String padd=(String)request.getAttribute("padd");
String city=(String)request.getAttribute("city");
String city1=(String)request.getAttribute("city1");
String state=(String)request.getAttribute("state");
String state1=(String)request.getAttribute("state1");
String country=(String)request.getAttribute("country");
String country1=(String)request.getAttribute("country1");
String zcode=(String)request.getAttribute("zcode");
String zcode1=(String)request.getAttribute("zcode1");
String email=(String)request.getAttribute("email");
String img=(String)request.getAttribute("image");
String file=(String)request.getAttribute("filename");

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

   return true;

  }





 function copy1(){
     //alert("this is working");
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
//alert("this is working");
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
  var state1=document.getElementById("state1");
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
  padd.value=padd1.value;
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
  alert(email1.value);
  var button=document.getElementById("button");
  var button1=document.getElementById("button1");
  button.value=button1.value;
 // alert("this is working");
  return true;
 <%--// button.value=<%=btn%>;--%>
  }
</script>
</head>



<body>
    <h1>
                     Voter Registration Form
                </h1>
    <%--  <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>--%>


 


    <div
   style="  top:250px;
   left:800px;
   right:5px;
      position: absolute;

      visibility: visible; z-index: 100;" >


 <html:form action="/voterimageupload" method="post" styleId="form1" enctype="multipart/form-data">
       <%if(btn.equals("Update")==true || btn.equals("Add")==true || btn.equals("Submit")==true)
                        {%>
     <html:file  property="img" name="VoterRegActionForm" styleId="img" onchange="submit();"  onclick="return copy1();" />
                        <%}%>


      <input type="hidden" name="filename" tabindex="16" id="filename" />

       <html:hidden property="enrollment" name="VoterRegActionForm" styleId="enrollment"/>
          <html:hidden property="institute_id" name="VoterRegActionForm" styleId="ins"/>
          <html:hidden property="department" name="VoterRegActionForm" styleId="dep"/>
          <html:hidden property="course" name="VoterRegActionForm" styleId="cour"/>
           <html:hidden property="year" name="VoterRegActionForm" styleId="year"/>
           <html:hidden property="duration" name="VoterRegActionForm" styleId="dur"/>
           <html:hidden property="session" name="VoterRegActionForm" styleId="sess"/>
             <html:hidden property="j_date" name="VoterRegActionForm" styleId="jdate"/>
              <html:hidden property="v_name" name="VoterRegActionForm" styleId="vname"/>
               <html:hidden property="gender" name="VoterRegActionForm" styleId="gen"/>
                <html:hidden property="b_date" name="VoterRegActionForm" styleId="bdate"/>
          <html:hidden property="f_name" name="VoterRegActionForm" styleId="fname"/>
          <html:hidden property="m_name" name="VoterRegActionForm" styleId="mname"/>

          <html:hidden property="m_number" name="VoterRegActionForm" styleId="mnumb"/>
           <html:hidden property="c_add" name="VoterRegActionForm" styleId="cadd"/>
          <html:hidden property="city" name="VoterRegActionForm" styleId="city"/>
          <html:hidden property="state" name="VoterRegActionForm" styleId="state"/>
           <html:hidden property="zipcode" name="VoterRegActionForm" styleId="zcode"/>
             <html:hidden property="country" name="VoterRegActionForm" styleId="country"/>
                <html:hidden property="p_add" name="VoterRegActionForm" styleId="padd"/>
          <html:hidden property="city1" name="VoterRegActionForm" styleId="city2"/>

          <html:hidden property="state1" name="VoterRegActionForm" styleId="state2"/>

          <html:hidden property="zipcode1" name="VoterRegActionForm" styleId="zcode2"/>

          <html:hidden property="country1" name="VoterRegActionForm" styleId="country2"/>

         <html:hidden property="email" name="VoterRegActionForm" styleId="email"/>
           <html:hidden property="button" name="VoterRegActionForm" styleId="button"/>
           <html:hidden property="page" value="VoterRegistration" name="VoterRegActionForm" styleId="page"/>
    </html:form>



     </div>
     <html:form action="/addvoterregistration" method="post" style="position:absolute; left:90px; top:90px;"  onsubmit="return check3();">
     <div
   style="  top:60px;
   left:815px;
   right:5px;
      position: absolute;

      visibility: show;" >
  <%if(btn.equals("Add")==true || btn.equals("Submit")==true){%>

                            <%if(session.getAttribute("image")!=null){%>
                         <html:img src="/EMS/Voter/upload.jsp"  alt="no Image Selected" width="100" height="100"/>
                        <%}else{%>

                        <html:img src="/EMS/images/no-image.jpg"  alt="no Image Selected" width="100" height="100"/>
                           <%}%>


                           <%}else{%>

                            <%if (request.getAttribute("imagechange")!=null){%>
                        <html:img src="/EMS/Voter/upload.jsp"  alt="no Image Selected" width="120" height="120"/>
                        <%}else{%>
                        <html:img src="/EMS/Voter/viewimage.jsp" alt="no image selected" width="120" height="120" />
                        <%}%><br/>


                           <%}%>

 </div>

        <table border="1">
            <tr><td>
        <table border="0" class="table" align="center">
            <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="10px;" colspan="2"><b>Register Yourself </b></td></tr>
            <tr><td colspan="2">
                    <table>
              <tr>
                  <td >Enrollment Number*:</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text readonly="<%=read %>"  name="VoterRegActionForm"  styleId="enrollment1" property="enrollment"  value="<%=enrollment%>" /></td><td>
<td></td>

</tr>
<%--<tr>
    <td width="30%">Institute Name*:</td><td><html:text readonly="<%=read %>"  name="VoterRegActionForm"  styleId="ins1"  value="<%=instituteid%>"  property="institute_id"/></td><td width="30%">
<td></td>
</tr>--%>
<tr><td align="left">Institute Name*</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <html:select property="institute_id" value="<%=institute_id%>"  styleId="ins1"  name="VoterRegActionForm"  tabindex="10">
            <html:option  value="Select"> Select </html:option>
            <html:options collection="instituteList"  property="instituteId"  labelProperty="instituteName"/>
                                <%--<html:option  value="amu">Aligarh muslim university</html:option>
                                <html:option value="jmi">Jamia Millia islamia</html:option>
                               <html:option value="du">Delhi University</html:option>
                               <html:option value="jnu">JNU</html:option>--%>
</html:select>

      </tr>
<tr>
<td align="left">Department*:</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text readonly="<%=read %>" name="VoterRegActionForm" styleId="dep1" property="department"  value="<%=dep%>" /></td>
<td>



</td>

</tr>

<tr>
<td align="left">Course*:</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text readonly="<%=read %>" name="VoterRegActionForm" styleId="cour1" property="course" value="<%=cour%>"/></td>
</tr>

<tr>
    <td align="left">Year :</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text readonly="<%=read %>"  name="VoterRegActionForm" styleId="year1" property="year" value="<%=yr%>"/></td>
                                               <%-- <td>Upload Photo:</td><td><html:file property="image"/></td>--%>
</tr>
                    </table>
                </td></tr>

            <tr><td>
                        <table width="700" border="0" cellspacing="6" cellpadding="2" align="left">
                        <tr>
                                 </tr>
<%--<tr>
<td align="left">Faculty Roll No :</td><td><html:text name="VoterRegActionForm" property="electionid"/></td>
</tr>
<tr--%>
<tr><td  width="30%">Duration of course:</td><td><html:text  readonly="<%=read %>" name="VoterRegActionForm" styleId="dur1" value="<%=dur%>" property="duration" /> </td><td width="30%">
    </tr>
<tr>
<td align="left">Current Session:</td><td><html:text readonly="<%=read %>"  name="VoterRegActionForm" styleId="sess1" value="<%=sess%>" property="session"  /></td>
</tr
<tr><td width="15%">Date of Joining<br>(DD-MM-YYYY)</td><td><html:text readonly="<%=read %>"  name="VoterRegActionForm" styleId="1" property="j_date" value="<%=jdate%>" />
<a href="javascript:NewCal('1','ddmmmyyyy')"><img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></td></tr>


 <tr>
                <td align="left">Voter(student) Name*</td>
                <td>
                        <table><tr><td>
                         <select name="courtesy" size="1" id="courtesy" tabindex="2" style="align:right">

                         <option selected value="Select">Select</option>
                         <option  value="mr">Mr.</option>
                         <option value="mrs">Mrs.</option>
                        <option  value="ms">Ms.</option>
                         </select></td>
                        <td><html:text  name="VoterRegActionForm"  styleId="vname1"  value="<%=vname%>"  property="v_name" readonly="<%=read %>"/></td>
                        </table>
                        </td>
                        </tr>
<tr><td align="left">Gender*</td><td>
        <html:select property="gender" styleId="gen1"  name="VoterRegActionForm"  tabindex="10">

            <html:option  value="Select"> Select </html:option>
                                <html:option  value="male">Male</html:option>
                                <html:option value="female">Female</html:option>

</html:select>

      </tr>
      <tr>
      <td>Date of Birth*<br>(DD-MM-YYYY)</td><td><html:text readonly="<%=read %>"  name="VoterRegActionForm"  property="b_date"  value="<%=bdate%>" styleId="3" />
<a href="javascript:NewCal('3','ddmmmyyyy')"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a></td>
</tr>


<tr> <td>Father's Name*</td><td><html:text  readonly="<%=read %>" name="VoterRegActionForm" styleId="fname1"  value="<%=fname%>"  property="f_name"/></td></tr>
  <tr> <td>Mother's Name*</td><td><html:text readonly="<%=read %>" name="VoterRegActionForm" styleId="mname1" value="<%=mname%>" property="m_name"/></td>
                                  </tr>

<tr>
<td align="left">Mobile No*:</td><td><html:text readonly="<%=read %>" name="VoterRegActionForm" styleId="mnumb1" value="<%=mnumb%>" property="m_number" /></td>
</tr>
                        </table>   </td>
                <td>

 <table> <tr>
         <td align="left">Corresponding Address:</td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td> &nbsp;&nbsp;<html:text readonly="<%=read %>" name="VoterRegActionForm" styleId="cadd1" property="c_add" value="<%=cadd%>"  /></td>
</tr>
<tr>
    <td align="left">City:</td><td>&nbsp;&nbsp;<html:text readonly="<%=read %>" name="VoterRegActionForm" property="city"  value="<%=city%>" styleId="city1"/></td>
</tr>

 <tr>
<td align="left">State:</td><td>&nbsp;&nbsp;<html:text readonly="<%=read %>" name="VoterRegActionForm" property="state" value="<%=state%>" styleId="state1"/></td>
</tr>


<tr>
<td align="left">Zip Code:</td><td>&nbsp;&nbsp;<html:text readonly="<%=read %>" name="VoterRegActionForm" property="zipcode"  value="<%=zcode%>" styleId="zcode1"/></td>
</tr>
<tr>
<td align="left">Country:*</td><td>&nbsp;&nbsp;<html:text readonly="<%=read %>" name="VoterRegActionForm" property="country"  value="<%=country%>" styleId="country1"/></td>

</tr>
<tr>
       <td colspan="2"><input type="checkbox" id="Checkbox1" name="check" value="off" tabindex="17" onclick="return copy();" >&nbsp;&nbsp;<b>Click Here</b>&nbsp;(If permanent address is same as corresponding address)</td>
        </tr>
        <tr>    <td align="left">Permanent Address</td><td><html:text  readonly="<%=read %>" name="VoterRegActionForm" property="p_add" value="<%=padd%>" styleId="padd1"/></td></tr>
       <tr>    <td align="left">City</td><td><html:text readonly="<%=read %>" name="VoterRegActionForm" property="city1" value="<%=city1%>"  styleId="city21"/></td></tr>
        <tr>    <td align="left">State</td><td><html:text readonly="<%=read %>" name="VoterRegActionForm" property="state1" value="<%=state1%>" styleId="state21"/></td></tr>
         <tr> <td align="left">ZIP Code</td><td><html:text  readonly="<%=read %>" name="VoterRegActionForm" property="zipcode1"  value="<%=zcode1%>" styleId="zcode21"/></td><td colspan="2"></tr>
          <tr><td align="left">Country</td><td><html:text readonly="<%=read %>" name="VoterRegActionForm" property="country1" value="<%=country1%>" styleId="country21"/></td></tr>
 </table>

                </td>
            </tr>
  <tr>
  <td>
  <table>
   <script>
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



                </table>
            </td>
            </tr>
                        <%-- <input type="submit" id="Button1" name="" value="Register" >
                            <input type="reset" id="Button2" name="submit" value="Reset" >
                            <input type="button" id="Button3" name="" value="Back" onclick="return send()">--%>
                        <%--</td></tr>--%>
                <tr>
          <td colspan="2"><b>Important! </b>Please provide a working email address:</td>

        </tr>

<tr>

    <td align="left" colspan="2">email*:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:text readonly="<%=read %>" name="VoterRegActionForm"   value="<%=email%>" styleId="email1" property="email"/></td>

</tr>
</html:form>

<tr><td colspan="5" height="5px" class="mandatory" align="right"><a class="star">*</a>indicated fields are mandatory</td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
<td align="center" colspan="5">
     <%if(btn.equals("Update")){%>
    <input id="button1"  name="button" type="submit" value="<%=btn%>" class="txt1" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Cancel" onclick="return send()"  class="txt1"/>
    <%}else if(btn.equals("Delete")){%>
    <input id="button1"  name="button" type="submit" value="<%=btn%>" class="txt1" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()"  value="Cancel" class="txt1"/>
   <%}else if(btn.equals("Add") || btn.equals("Submit")==true){%>
    <input id="button1"  name="button" type="submit" value="Submit" class="txt1" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Cancel" onclick="return send()" class="txt1"/>
    <%}else if(status.equalsIgnoreCase("Block")&& btn.equalsIgnoreCase("Block")){%>
    <input id="button1"  name="button" type="submit" value="Unblock" class="txt1" />
    <%}else if(status.equalsIgnoreCase("REGISTERED")&& btn.equalsIgnoreCase("Block")){%>
    <input id="button1"  name="button" type="submit" value="Block"  class="txt1" />
    <%}else{%>
    <%--<input  name="button" type="submit" value="Accept"  class="txt1" />--%>
    <input  name="button" type="submit" value="Back" onclick="return send()" class="txt1" />



    <%}%>
	</td>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
                        </table>

</td>
</tr>
        </table>
