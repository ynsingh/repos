<%--
    Document   : pending_voter
    Created on : Jun 18, 2011, 4:48:20 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*,com.myapp.struts.hbm.VoterRegistration"%>

    <%@ page import="java.util.*,java.lang.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Block_Managergrid</title>
         <style>
    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold }
     .alternaterows { background-color: #efefef }
     .header        { background-color: #7697BC; color: #FFFFFF;font-weight: bold }

     .datagrid      { border: 1px solid #C7C5B2; font-family: arial; font-size: 9pt;
	    font-weight: normal }
</style>
    </head>
    <body onload="funload();">
        <form>
        <%!


   StaffDoc Ob;
   VoterRegistration voter;
  // Election_Manager_StaffDetail ems;
  // AdminRegistration adminReg;
   //ElectionManager electionmanager;
   //StaffDetail staffdetail;

   ArrayList requestList;
   int fromIndex=0, toIndex;

%>
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
<%
String Enrollment_No=resource.getString("enrollment");
pageContext.setAttribute("Enrollment_No",Enrollment_No );
String Voter_Name=resource.getString("votername");
pageContext.setAttribute("Voter_Name", Voter_Name);
String Status=resource.getString("workingstatus");
pageContext.setAttribute("Status", Status);
String Department=resource.getString("department");
pageContext.setAttribute("Department",Department);
String Year=resource.getString("year");
pageContext.setAttribute("Year",Year);
String Course=resource.getString("course");
pageContext.setAttribute("Course",Course);
String Edit=resource.getString("edit");
pageContext.setAttribute("Edit",Edit);

%>



 <%

 List rs = (List)session.getAttribute("resultset");
String status = (String)request.getParameter("status");
System.out.println(status+".................");
if(status!=null && status.equalsIgnoreCase("B")){
request.setAttribute("button", "Change Status");
}

   requestList = new ArrayList();
//requestList = (ArrayList)session.getAttribute("resultset");
   int tcount =0;
   int perpage=100;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/
   //if(request.getParameter("pageSize")!=null && request.getParameter("pageSize")!="")
   // perpage = Integer.parseInt((String)request.getParameter("pageSize"));

if(rs!=null){
  Iterator it = rs.iterator();
//System.out.println("it="+(tcount));
//requestList = (Login)rs.get(0);

   while (it.hasNext()) {


        voter = (VoterRegistration)rs.get(tcount);
       // staffdetail = (StaffDetail)rs.get(tcount).getStaffDetail();
System.out.println(voter.getId().getEnrollment());
        Ob = new StaffDoc ();
        //ems=new Election_Manager_StaffDetail();

       // Ob.setmanager_id(electionmanager.getId().getManagerId());
        //Ob.setinstitute_id(electionmanager.getId().getInstituteId());
        //Ob.setfirst_name(staffdetail.getFirstName());
        //Ob.setlast_name(staffdetail.getLastName());
        //Ob.setStaff_id(electionmanager.getStaffId());
        //Ob.setUser_id(electionmanager.getUserId());
        //Ob.setStatus(electionmanager.getStatus());
        Ob.setEnrollment(voter.getId().getEnrollment());
        Ob.setVoter_name(voter.getVoterName());
        Ob.setDepartment(voter.getDepartment());
        Ob.setCourse(voter.getCourse());
        Ob.setYear(voter.getYear());
        Ob.setStatus(voter.getStatus());
        //ems.getElectionManager().setStatus(ems.getElectionManager().getStatus());









   requestList.add(Ob);
   tcount++;
it.next();
   //System.out.println("tcount="+tcount);
		     }

//System.out.println("tcount="+tcount);

   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+perpage) >= tcount)
   toIndex = tcount;
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("id", "c"+tcount);
   pageContext.setAttribute("tCount", tcount);



  //   String page1 = (String)request.getParameter("pagesize");
     //System.out.println("page1="+page1);
  // if (page1!=null) pagesize = Integer.parseInt(page1);
  // fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");

  // if ((toIndex = fromIndex+(int)perpage) >= tcount)
 //  toIndex = tcount;
   //System.out.println("opacList="+opacList.size()+" tcount="+tcount);
  // if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));



   pageContext.setAttribute("rec",perpage);
   status = "&status="+status;
    pageContext.setAttribute("status",status);
     pageContext.setAttribute("tCount", tcount);
   pageContext.setAttribute("pagesize", perpage);
   pageContext.setAttribute("fromIndex", fromIndex);
   pageContext.setAttribute("fromIndex1", fromIndex+1);
pageContext.setAttribute("toIndex1", toIndex);
pageContext.setAttribute("id",tcount);
   }

String path=request.getContextPath();
pageContext.setAttribute("path", path);
  %>
  <script type="text/javascript" language="javascript">
function send(){
   // alert("ok");
     <%
     int pageNumber=1;
     if(request.getParameter("page") != null) {
       session.setAttribute("page", request.getParameter("page"));
       pageNumber = Integer.parseInt(request.getParameter("page"));
     } else {
       session.setAttribute("page", "1");
     }
     String nextPage = (pageNumber +1) + "";%>
var loc="/EMS/votersetup.do?page="+<%=nextPage%>;
//alert(loc);
location.href= loc;

<%


   //  System.out.println(((java.util.List)session.getAttribute("EmpList")).size());
     String myUrl = "/EMS/votersetup.do?page="+nextPage;
   //  System.out.println(myUrl);

     pageContext.setAttribute("myUrl", myUrl);
     %>

}

function sendprevious(){
  //  alert("ok");
     <%String previousPage ="";
if(pageNumber>=1)
   previousPage = (pageNumber -1) + "";
else
  previousPage = 0 + "";
%>
var loc="/EMS/votersetup.do?page="+<%=previousPage%>;
//alert(loc);
location.href= loc;

<%


   //  System.out.println(((java.util.List)session.getAttribute("EmpList")).size());
   //  String myUrl = "/EMS/votersetup.do?page="+nextPage;
   //  System.out.println(myUrl);

     pageContext.setAttribute("myUrl", myUrl);
     %>

}


 <%-- function changerec(){
        var x=document.getElementById('rec').value;
    var loc = window.location;
    loc = "http://<%=request.getHeader("host")%><%=request.getContextPath()%>/election_manager/voter_setup.jsp";


            loc = loc + "?pageSize="+x;
    location.href = loc;
funload();
    }

   document.onkeyup = keyHit
function keyHit(event) {

  if (event.keyCode == 13) {
  changerec();

    event.stopPropagation()
    event.preventDefault()
  }
}

function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }--%>
      function funload()
      {
          location.href = "#top";
          window.setTimeout(function(){
         var IFRAMERef = parent.document.getElementById('f1');
         var IFRAMERef1 = parent.document.getElementById('ifr3');
    var parheight= <%=perpage%>;
    var tc = <%=tcount%>;
    parheight = parheight>tc?tc:parheight;
    var heigh = parheight*14 + 160;
    //alert(heigh);
    <%--if(heigh>parheight) parheight=heigh;
    alert(parheight);--%>
    if(heigh!=undefined)
      {  IFRAMERef.style.height = heigh;
    IFRAMERef1.style.height = heigh;}
    else{
        IFRAMERef1.style.height = 500;
        IFRAMERef.style.height = 500;}
    //alert(IFRAMERef.height);
      },200);}

     
var s;
function RecSelect(forms,str) {

s="";

//alert(document.getElementById("<%="c"+tcount%>").value);
var max=eval("forms."+str+".length");
alert("Max value:"+max);
if (max)
{

for (var idx = 0; idx < max; idx++) {


if (eval("forms."+str+"[" + idx + "].checked") == true) {

//var t=setTimeout('alert(s+"dilatation complete")',5000);
if(s!="")
    s=s+forms.doc[idx].value;
else
s=forms.doc[idx].value;
}

}
alert(s);
}

}





  </script>
   </body>

<br><br>

<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("no_record_found")%></p>
<%}
else
{%>
<table align="" dir="" width="100%" style="padding-left: 10%;padding-right: 10%">
    <%--<tr><td colspan="2" align="right">View Next&nbsp;onblur="changerec()" style="width:50px"/>
        <select id="rec" onchange="changerec()" style="width:100px">
             <option value="10">Select</option>
           <option value="15">15</option>
            <option value="20">20</option>
             <option value="30">30</option>
       </select>

        </td></tr>--%>
    <tr dir=""><td dir="">
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>
 <column width="10%">
      <header value="Select" hAlign="left" styleClass="header"/>

<item hAlign="center">
<![CDATA[
<input type="checkbox" name="doc" value="${doc.enrollment}"/>
<input type="button" class="BlueButton" style="width:90px" name="dilate" value="Dilate" onclick="return RecSelect(this.form,'doc')">
]]>
</item>

    </column>
    <column width="10%">
      <header value="${Enrollment_No}" hAlign="left" styleClass="header"/>
      <item   value="${doc.enrollment}" hyperLink="${path}/newregistration2.do?id=${doc.enrollment}${status}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Voter_Name}" hAlign="left" styleClass="header"/>
      <item   value="${doc.voter_name}" hAlign="left" hyperLink="${path}/newregistration2.do?id=${doc.enrollment}${status}"  styleClass="item"/>
    </column>
    <column width="10%">
      <header value="${Department}" hAlign="left" styleClass="header"/>
      <item   value="${doc.department}" hAlign="left" hyperLink="${path}/newregistration2.do?id=${doc.enrollment}${status}"  styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Course}" hAlign="left" styleClass="header"/>
      <item   value="${doc.course}" hyperLink="${path}/newregistration2.do?id=${doc.enrollment}${status}"  hAlign="left" styleClass="item"/>
    </column>


       <column width="10%">
      <header value="${Year}" hAlign="left" styleClass="header"/>
      <item   value="${doc.year}" hyperLink="${path}/newregistration2.do?id=${doc.enrollment}${status}"  hAlign="left" styleClass="item"/>
    </column>


      <column width="10%">
      <header value="${Status}" hAlign="left" styleClass="header"/>
      <item   value="${doc.status}" hyperLink="${path}/newregistration2.do?id=${doc.enrollment}${status}"  hAlign="left" styleClass="item"/>
    </column>



 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>


 <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
</ui:dataGrid>

</td></tr>

 <input type="button" onclick="send()" value="nextPage"/>
  <input type="button" onclick="sendprevious()" value="previous"/>



</table>

  <%}%>

  <%
String msg=(String)request.getAttribute("msg");
if(msg!=null)
    out.println(msg);
%>

</form>

</html>
<%--


    Document   : approvallist
    Created on : May 12, 2011, 4:00:20 PM
    Author     : faraz


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.hbm.*,java.util.*,com.myapp.struts.admin.StaffDoc;" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript">

  

var haystackText = "";
function findMyText(str,needle, replacement) {
     if (haystackText.length == 0) {
          haystackText = str;
     }

     var match = new RegExp(needle, "ig");
     alert(match+"...........");
     var replaced = "";
    // if (replacement.length > 0) {
          replaced = haystackText.replace(match, replacement);
     //}
    // else {
          //var boldText = "<div style=\"background-color: yellow; display: inline; font-weight: bold;\">" + needle + "</div>";
       //   var boldText=needle;
       //   replaced = haystackText.replace(match, boldText);
     //}
return replaced;
}





function get_check_value4(ii)
{
var c="";



if(top.document.getElementById("f").list2.value !=null)
c = top.document.getElementById("f").list2.value ;

//alert("Fully Selection");



var t=document.getElementById("f1").check.length;

if(document.getElementById("f1").check.length>1){
alert(t);


           if(c=="" && document.getElementById("f1").check[ii].checked==true)
           {
           c =document.getElementById("f1").check[ii].value+";";

            alert("First Selction"+c);



            }
           else if(c!="" && document.getElementById("f1").check[ii].checked==true)
           {
               var t=document.getElementById("f1").check[ii].value;
               var myRegExp = "/"+t+"/";
            //  alert(myRegExp);
                var matchPos1 = c.search(myRegExp);

                if(matchPos1 == -1)
                    c = c+ document.getElementById("f1").check[ii].value+";";



            




           }
           alert("With Selection"+document.getElementById("f1").check[ii].value);
           if(document.getElementById("f1").check[ii].checked==false)
           {

           if(c!="")
           {
                var str = new String();
                str=c;
               
               var d=document.getElementById("f1").check[ii].value+";";
            
             c=  findMyText(str,d, "") ;
             
           }
           }

         
}








if(t==undefined)
    {
        alert("single selection");



      



       if (document.getElementById("check").checked==true)
      {
         //  alert("value="+document.getElementById("check").value);
         //  alert("kk"+c);
           if(c=="")
           {
           c=document.getElementById("check").value+";";
           }
            else
           {
                 var t=document.getElementById("check").value+";";
               var myRegExp = "/"+t+"/";

                var matchPos1 = c.search(myRegExp);

                if(matchPos1 == -1)
                    c = c+document.getElementById("check").value+";";






            }

       }
        if(document.getElementById("check").checked==false)
           {

           if(c!="")
           {
                var str = new String();
                str=c;
             
               var d=";"+document.getElementById("check").value+";";
            
             c=  findMyText(str,d, "") ;
             alert("After Deselection Single"+c);
           }
           }

        

   }

   
   
 top.document.getElementById("f").list2.value=c;
 alert(top.document.getElementById("f").list2.value);
}






</script>
<script type="text/javascript">
 var obj1;
 

function setValues1()
{
    alert("setvalues1");
    
    
    var list2 = top.document.getElementById("f").list2.value;
    alert(list2);

    if(list2!="undefined"||list2!=""||list2!=" ")
        {

            var check = document.getElementById("f1").check.length;
           
            if(check!=undefined)
                {
         
                    var list22 = list2.split(";");
                    var index =0;
                    while(check>index)
                        {
                            var checkvalue=document.getElementById("f1").check[index].value;
                            var i=0;
    
                             if(list22.length==0)
                            {
                                if(list2==checkvalue)
                                {
                                    
                                    document.getElementById("f1").check[index].checked=true;
                                  
                                }
                            }
                            else
                                {
alert(list22.length+"......");
                                
                        while(list22.length>i)
                            {
                       alert("gsdgs"+list22[i]);
                        if(list22[i]==checkvalue)
                                {

                                    document.getElementById("f1").check[index].checked=true;
                                  
                                }
                                i++;
                            }
                                }


                                index++;
                        }
                }
                else
                    {
                    //    alert("in else");
                        var checkvalue=document.getElementById("f1").check.value;
                    //    alert(checkvalue);
                        //var list22 = list2.replace(";","");
                        var list22 = list2.split(";");
                        var i=0;
                      if(list22.length==0)
                           {
                                if(list22==checkvalue)
                                {
                             //       alert("yes");
                                    document.getElementById("f1").check.checked=true;
                                 
                                }
                            }
                            else
                                {
                        while(list22.length>i)
                            {
                     //   alert(list22[i]);
                        if(list22[i]==checkvalue)
                                {

                                    document.getElementById("f1").check.checked=true;
                                }
                                i++;
                            }
                                }
                    }
        }

       
}







 </script>

<%!
int fromIndex,toIndex;
int pagesize=2,size;
int pageIndex;
int noofpages;
int modvalue;
String index;
List obj1;

%>
<%
int i=0;
 int j=0;
List<VoterRegistration> l11=(List<VoterRegistration>)session.getAttribute("VoterList");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }

 if(l11!=null)
        size = l11.size();
 else
        size = 0;

 //for calculating no of pages required
 modvalue = size%pagesize;
 if(modvalue>0)
    noofpages = size/pagesize+1;
 else
     noofpages = size/pagesize;
 //to calculate the starting item and ending item index for the desired page
fromIndex = (pageIndex-1)*pagesize;
toIndex = fromIndex + pagesize;
if(toIndex>size)toIndex=size;
//fromIndex++;



%>
<body onload="setValues1();">
<form id="f1" >
    <table border="0px" style="margin: 0px 0px 0px 0px;">
        <tr><td valign="top" >
                           <table border="0px" valign="top" >
             <tr bgcolor="#E0E8F5" ><td width="100" >Enrollment</td><td width="200">Name</td><td width="200">Select</td></tr>

             <logic:iterate id="VoterRegistration" name="VoterList" offset="<%=String.valueOf(fromIndex)%>" length="2">
                <tr>
                    <td><bean:write name="VoterRegistration" property="id.enrollment"/></td>
                   <td><bean:write name="VoterRegistration" property="voterName"/></td>
                    <td><input type="checkbox" id="check" name="check" value='<bean:write name="VoterRegistration" property="id.enrollment"/>'  onclick="get_check_value4(<%=i++%>)"/></td>
                 
                  
                </tr>

        </logic:iterate>
 </table>
                           </td></tr>

               </table>
</form>
</body>
--%>