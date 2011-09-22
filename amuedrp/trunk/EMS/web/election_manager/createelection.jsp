<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String msg=(String)request.getAttribute("msg");
if(msg==null){msg=(String)request.getAttribute("msg1");}
if(msg!=null)
    {
    %>
    <script>
        alert("<%=msg%>");
        window.back();
    </script>
<%}%>

<jsp:include page="/election_manager/login.jsp"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.util.*,java.io.*,java.net.*,java.sql.Timestamp,java.lang.Object"%>
<%--<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>--%>
<link type="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    String regid="";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

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
    regid = resource.getString("registrationid");
    System.out.println("Reg id="+regid);
    %>
<script src="js/datetimepicker.js"></script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>


<script language="javascript">


$(document).ready(function()
{
   var jQueryDatePicker1Opts =
   {
      dateFormat: 'yy-mm-dd',
      changeMonth: true,
      changeYear: true,
      showButtonPanel: false,
      showAnim: 'show'
   };
   $("#start_date").datepicker(jQueryDatePicker1Opts);
   $("#end_date").datepicker(jQueryDatePicker1Opts);
    $("#Nstart_date").datepicker(jQueryDatePicker1Opts);
   $("#Nend_date").datepicker(jQueryDatePicker1Opts);
   $("#Scr_date").datepicker(jQueryDatePicker1Opts);
   $("#Scrend_date").datepicker(jQueryDatePicker1Opts);
   $("#wtd_date").datepicker(jQueryDatePicker1Opts);
   $("#wtdend_date").datepicker(jQueryDatePicker1Opts);


});

    </script>
<%! boolean read=false;%>
<%
String button=(String)request.getAttribute("button");
if (button.equals("View")||button.equals("Delete"))
read=true;
else
   {
    read=false;
}
String msg1=(String) request.getAttribute("msg1");
%>
<script type="text/javascript">
var j=0;
var i=0;
function createposition()
{
i=0;
j=j+1;
var divtag = document.createElement("div");
     divtag.id = "position"+j;
     divtag.style.backgroundColor = "#E0F8F7";
     divtag.style.border = "solid 5px #0B3B24";
     divtag.style.borderTopLeftRadius = "10px";
     divtag.style.width = "930px";
     divtag.style.align = "center";
     divtag.style.marginLeft = "0px";
     divtag.innerHTML ='<table><tr><td>Position Name *&nbsp;&nbsp;<input type="text" Id="position_name'+i+''+j +'" size="25px"/></td>&nbsp;&nbsp;<td>Number of choice *<input type="text" Id="numberofchoice'+i+''+ j +'" size="25px"/></td><td><input type="button" id="but0'+ j +'" value="Save" onclick="search('+ j +');"/></td></tr><tr><td colspan="2">Position instruction for voter:&nbsp;&nbsp;<textarea id="instruct0'+ j+'" rows="3" style="width: 415px; height: 46px;"></textarea></td></tr></table>';
    // var tagbr = document.createElement("html");

     //document.getElementById("position").appendChild(tagbr);
document.getElementById("position").appendChild(divtag);
       // current.parentNode.appendChild('<div id="position1" style="position: relative;">Position Id *<br><input name="BallotActionForm" property="position_id" styleId="position_id" size="40px"/><br><br>Position Name *<br><input name="BallotActionForm" property="position" styleId="position" size="40px"/><br><br><div id="candidate"><div id="0">Candidate Name *<br><input name="BallotActionForm" property="candidate" styleId="candidate" size="40px"/><br><br>Number of choice *<br><t name="BallotActionForm" property="numberofchoice" styleId="numberofchoice" size="40px"/><br><br></div><input type="button" name="add candidate" style="border: transparent" onclick="create(this);" value="Add More Candidate" size="50px"></div></div>');

}


function newXMLHttpRequest() {
var xmlreq = false;
// Create XMLHttpRequest object in non-Microsoft browsers
if (window.XMLHttpRequest) {
xmlreq = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
// Try to create XMLHttpRequest in later versions
// of Internet Explorer
xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e1) {
// Failed to create required ActiveXObject
try {
// Try version supported by older versions
// of Internet Explorer
xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
} catch (e2) {
// Unable to create an XMLHttpRequest by any means
xmlreq = false;
}
}
}
return xmlreq;
}
/*
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes it XML response to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
function getReadyStateHandler(req, responseXmlHandler) {
// Return an anonymous function that listens to the XMLHttpRequest instance
return function () {
// If the request's status is "complete"
if (req.readyState == 4) {
// Check that we received a successful response from the server
if (req.status == 200) {
// Pass the XML payload of the response to the handler function.
responseXmlHandler(req.responseXML);
} else {
// An HTTP problem has occurred
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}
function search(current) {
    //alert("index="+index+" current="+current);
    var position = "position_name0"+current;
    var posinstruct = "instruct0" + current;
    var numberofchoice = "numberofchoice0"+current;
    var position_name = document.getElementById(position).value;
    var noofchoice = document.getElementById(numberofchoice).value;
    var instruct = document.getElementById(posinstruct).value;
    var electionId = document.getElementById("electionId").value;
    position_name = position_name.replace(/^\s*|\s*$/g,"");
    noofchoice = noofchoice.replace(/^\s*|\s*$/g,"");
    instruct = instruct.replace(/^\s*|\s*$/g,"");
    var posid = "position_id0"+current;
    var PositionId="";
    var butId = "but0"+current;
    var buttonVal = document.getElementById(butId).value;
    if(buttonVal=="Update")
        {
            PositionId = document.getElementById(posid).value;
        }
if (position_name.length >= 1)
{
if(position_name!="" && position_name!=null && noofchoice!="" && noofchoice!=null && electionId!="" && electionId!=null)
{
    var req = newXMLHttpRequest();


req.onreadystatechange = getReadyStateHandler(req, update1);

req.open("POST","<%=request.getContextPath()%>/AddPosition.do?setPosition="+position_name+"&setChoice="+noofchoice+"&setElectionId="+electionId+"&setposId="+PositionId+"&setposInstruction="+instruct, true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
var idPos = "position"+current;

document.getElementById(idPos).style.backgroundColor = "#D8CEF6";
 document.getElementById(idPos).style.border = "solid 5px #F2F5A9";

return true;
}
alert("Please Enter Values in the Field");
return false;
}
alert("Please Enter Values in the Field");
return false;
}
function update1(cartXML)
{
//alert("call");

var em = cartXML.getElementsByTagName("email_ids")[0];
var em1 = em.getElementsByTagName("message");
//var em = cartXML.firstChild.value;
for(i=0;i<em1.length;i++)
    {
alert(em1[i].firstChild.nodeValue);

    }
}
function deletePosition(current) {
    //alert("index="+index+" current="+current);
    var req = newXMLHttpRequest();
 var electionId = document.getElementById("electionId").value;
 var posId = "position_id0"+current;
 var PositionId = document.getElementById(posId).value;
req.onreadystatechange = getReadyStateHandler(req, update1);

req.open("POST","<%=request.getContextPath()%>/deletePosition.do?getElectionId="+electionId+"&positionId="+PositionId, true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
return true;
}
function searchPositions() {
    //alert("index="+index+" current="+current);
    var req = newXMLHttpRequest();
 var electionId = document.getElementById("electionId").value;
req.onreadystatechange = getReadyStateHandler(req, updatePositions);

req.open("POST","<%=request.getContextPath()%>/getPosition.do?getElectionId="+electionId, true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
return true;
}

function updatePositions(cartXML)
{


var em = cartXML.getElementsByTagName("positions")[0];
var em1 = em.getElementsByTagName("position");
//var em = cartXML.firstChild.value;
for(iii=0;iii<em1.length;iii++)
    {
        //position block creation
                var divtag = document.createElement("div");
                divtag.id = "position"+iii;
                divtag.style.backgroundColor = "#D8CEF6";
                divtag.style.border = "solid 5px #F2F5A9";
                divtag.style.borderTopLeftRadius = "10px";
                divtag.style.width = "930px";
                divtag.style.align = "left";

                divtag.style.marginTop = "5px";
                divtag.style.marginLeft = "3px";
                <%if(!button.equals("View") && !button.equals("Block")){%>
                            divtag.innerHTML ='<table><tr><td>Position Name *&nbsp;&nbsp;<input type="text" Id="position_name0'+iii +'" size="25px"/><input type="hidden" Id="position_id0'+iii +'" size="25px"/></td>&nbsp;&nbsp;<td>Number of choice *&nbsp;&nbsp;<input type="text" Id="numberofchoice0'+ iii +'" size="25px"/></td><td><input type="button" id="but0'+ iii +'" value="Update" onclick="search('+ iii +');"/></td>&nbsp;&nbsp;<td><input type="button" value="Delete" onclick="deletePosition('+ iii +');"/></td></tr><tr><td colspan="3">Position instruction for voter:&nbsp;&nbsp;<textarea id="instruct0'+ iii+'" rows="3" style="width: 415px; height: 46px;"></textarea></td></tr></table>';<%}else{%>
                           divtag.innerHTML ='<table><tr><td>Position Name *&nbsp;&nbsp;<input type="text" Id="position_name0'+iii +'" size="25px"/><input type="hidden" Id="position_id0'+iii +'" size="25px"/></td>&nbsp;&nbsp;<td>Number of choice *&nbsp;&nbsp;<input type="text" Id="numberofchoice0'+ iii +'" size="25px"/></td></tr><tr><td colspan="3">Position instruction for voter:&nbsp;&nbsp;<textarea id="instruct0'+ iii+'" rows="3" style="width: 415px; height: 46px;"></textarea></td></tr></table>';<%}%>
                        document.getElementById("position").appendChild(divtag);
        //end of block
var positionname1 = em1[iii].getElementsByTagName("positionname");
var positionname = positionname1[0].firstChild.nodeValue;
var noofchoice1 = em1[iii].getElementsByTagName("noofchoice");
var noofchoice = noofchoice1[0].firstChild.nodeValue;
var positionId = em1[iii].getElementsByTagName("positionId");
var positionid = positionId[0].firstChild.nodeValue;
var instruct = em1[iii].getElementsByTagName("instruction");
var instrucT = instruct[0].firstChild.nodeValue;
var posiId = "position_name0"+iii;
var nochoice = "numberofchoice0"+iii;
var posid = "position_id0"+iii;
var inst = "instruct0"+iii;
document.getElementById(posiId).value = positionname;
document.getElementById(nochoice).value = noofchoice;
document.getElementById(posid).value = positionid;
document.getElementById(inst).value = instrucT;
//var ca = em1[iii].getElementsByTagName("candidate");
/*for(jj=0;jj<ca.length;jj++)
    {
        var candidatename1 = ca[jj].getElementsByTagName("candidatename");
        var candidatename;
            if(candidatename1[0].firstChild!=null) candidatename = candidatename1[0].firstChild.nodeValue;
            else candidatename = "";
        var candidateid1 = ca[jj].getElementsByTagName("candidateid");
        var candidateid = candidateid1[0].firstChild.nodeValue;
        //creating candidate block
            var divtag = document.createElement("div");
            divtag.id = iii+''+jj;
            divtag.style.position = "relative";
            divtag.style.backgroundColor = "#F2F2F2";
            divtag.style.border = "3px solid #F2F5A9";
            divtag.style.display = "block";
            divtag.style.width = "475px";
            divtag.style.marginTop = "5px";
            divtag.style.marginLeft = "55px";
            divtag.style.overflow = "hidden";
            if(jj+1==ca.length) divtag.style.marginBottom = "5px";
            var id = "candidate0"+iii;
            divtag.innerHTML = candidateid + '    Candidate Name * <input type="text"   value="'+ candidatename +'" Id="candidate_name'+ iii +''+jj +'" size="40px"/>&nbsp;&nbsp;<input type="button" value="Save" onclick="search1('+iii+','+jj+');"/>' ;
            document.getElementById(id).appendChild(divtag);
         //end of creation
i=jj;
    }
   var idadd = "add0"+iii;
document.getElementById(idadd).attributes.onclick.value = "create("+jj+","+iii+",this)";*/
//alert("create("+jj+","+iii+",this);");
//alert(document.getElementById(idadd).attributes.onclick.value);
}

j=iii-1;
if(j<0)j=0;

//alert(i+" "+j);
}

function check1()
{
    if(document.getElementById('electionid').value=="")
    {
        alert("Enter Election ID");

        document.getElementById('electionid').focus();

        return false;
    }
    if(document.getElementById('electionname').value=="")
        {
            alert ("Please Name Your Election");
           
            document.getElementById('electionname').focus();


            return false;
        }

        if(document.getElementById('start_date').value=="")
        {
            alert ("Please Define Election Start Date");
            document.getElementById('start_date').focus();
            return false;
        }



        if(document.getElementById('end_date').value=="")
        {
            alert ("Please Define Election End Date");
            document.getElementById('end_date').focus();
            return false;
        }


        if(document.getElementById('Nstart_date').value=="")
        {
            alert ("Please Define Nomination Start Date");
            document.getElementById('Nstart_date').focus();

            return false;
        }



        if(document.getElementById('Nend_date').value=="")
        {
            alert ("Please Define Nomination End Date");
            document.getElementById('Nend_date').focus();
            return false;
        }

        if(document.getElementById('Scr_date').value=="")
        {
            alert ("Please Define Scrutny Start Date");
            document.getElementById('Scr_date').focus();
            return false;
        }

         if(document.getElementById('Scrend_date').value=="")
        {
            alert ("Please Define Scrutny End Date");
            document.getElementById('Scrend_date').focus();
            return false;
        }


        if(document.getElementById('wtd_date').value=="")
        {
            alert ("Please Define Nomination Withdrawl Start Date");
            document.getElementById('wtd_date').focus();
            return false;
        }


        if(document.getElementById('wtdend_date').value=="")
        {
            alert ("Please Define Nomination Withdrawl End Date");
            document.getElementById('wtdend_date').focus();
            return false;
        }

        if(document.getElementById('cri').value=="")
        {
            alert ("Please set critaria for Nomination");
            document.getElementById('cri').focus();
            return false;
        }
  }


  function datecheck()
  {





      var diff;
      var tempo = document.getElementById('Nstart_date').value;
      alert(tempo);
      var nominationEndDate=document.getElementById('Nend_date').value;
alert("yes");
temp=new Date(tempo);
     d = temp.getDate();
       month = temp.getMonth();
       yr = temp.getYear();
alert("yes");
       h = temp.getHours();


       alert( "jghsdjhsdh"+ "" + h);
         m = temp.getMinutes();
         s = temp.getSeconds();
         mm = temp.getMilliseconds();
alert("yes");
         d1 = new Date(yr,month,d,h,m,s,mm);

              temp1=new Date(nominationEndDate);
              de=temp1.getDate();
              me=temp1.getMonth();
              yre=temp1.getYear();
              he=temp1.getHours();
              mie=temp1.getMinutes();
              se=temp1.getSeconds();
              mme=temp1.getMilliseconds();

              d2=new Date(yre, me, de, he, mie, se, mme);


alert(d1.getTime());
              t=new Date(temp);
       alert(t);
       d=new Date(nominationEndDate);
      diff= Math.floor((d2.getTime() - d1.getTime())/(1000*60*60*24));
      
     // alert(diff);
     // var newdat=new Date(t);
  // Date
      if(diff>0)
          alert("change nomination date");
return false;

      //alert("hiiii"+t);
     

                var nominationEndDate=document.getElementById('Nend_date').value;
                nominationEndDate.timeStamp;
      alert("nominationEndDate =" +nominationEndDate);
      var scrutnyDate=documnet.getElementById('Scr_date').value;
      var scrutnyEndDate=document.getElementById('Scrend_date').value;
      var timediff=nominationEndDate-temp;
      timediff.timeStamp;
      alert("gggggggggggg"+timediff.timeStamp);
      if(timediff<=0)
          alert("Change Nomination time Accordingly");
      document.getElementById('Nend_date').focus();
      return false;
  }

function send()
{
    window.location="<%=request.getContextPath()%>/manageElection.do";
    return false;
}
function matchDate(d1,d2)
{
    if(d1.getYear()==d2.getYear())
            if(d1.getMonth()==d2.getMonth())
                    if(d1.getDay()==d2.getDay())
                            if(d1.getHours()==d2.getHours())
                                    if(d1.getMinutes()==d2.getMinutes())
                                            if(d1.getSeconds()==d2.getSeconds())
                                                     return true;
                                            else{
                                                if(d1.getSeconds()>d2.getSeconds())
                                                return true;
                                                else return false;}
                                    else{
                                        if(d1.getMinutes()>d2.getMinutes())
                                            return true;
                                            else return false;}
                             else{
                                 if(d1.getHours()>d2.getHours())
                                    return true;
                                    else return false;}
                    else{
                        if(d1.getDay()>d2.getDay())
                                    return true;
                                    else return false;}
           else{
               if(d1.getMonth()>d2.getMonth())
                    return true;
                    else return false;}
    else
        if(d1.getYear()>d2.getYear())
        return true;
        else return false;
}
function checkdates()
{
    var nstart= document.getElementById("Nstart_date");
    var nend = document.getElementById("Nend_date");
    var sstart = document.getElementById("Scr_date");
    var send = document.getElementById("Scrend_date");
    var wstart = document.getElementById("wtd_date");
    var wend = document.getElementById("wtdend_date");
    var start = document.getElementById("start_date");
    var end = document.getElementById("end_date");

        if(nstart!=undefined && nend!=undefined)
            {
                var d1 = new Date(nstart.value);
                var d2 = new Date(nend.value);
                if(matchDate(d1,d2))
                  {
                      alert("Nomination Start Date should be lesser than nomination End Date");
                      nstart.focus();
                      return false;
                  }
                  else if(sstart!=undefined && send!=undefined)
            {
                var d1 = new Date(sstart.value);
                var d2 = new Date(send.value);
              if(matchDate(d1,d2))
                  {
                      alert("Scrutny Start Date should be lesser than Scrutny End Date");
                      sstart.focus();
                      return false;
                  }
                  else if(wstart!=undefined && wend!=undefined)
            {
                var d1 = new Date(wstart.value);
                var d2 = new Date(wend.value);
              if(matchDate(d1,d2))
                  {
                      alert("Withdrawal Start Date should be lesser than Withdrawal End Date");
                      wstart.focus();
                      return false;
                  }
                  else if(start!=undefined && end!=undefined)
            {   var d1 = new Date(start.value);
                var d2 = new Date(end.value);
              if(matchDate(d1,d2))
                  {
                      alert("Election Start Date should be lesser than Election End Date");
                      start.focus();
                      return false;
                  }
              else{
                  var sdate = new Date(start.value);
                  var wdate = new Date(wend.value);
                  var wsdate = new Date(wstart.value);
                  var sendDate = new Date(send.value);
                  var sstartdate = new Date(sstart.value);
                  var nenddate = new Date(nend.value);
                  if(matchDate(wdate,sdate))
                      if(matchDate(sendDate,wsdate))
                          if(matchDate(nenddate,sstartdate))
                            return true;
                          else{
                                    alert("order of nomination,scrutny, widthdrawal and election date are not proper");
                                    nend.focus();
                                    return false;
                                }
                      else{
                                    alert("order of nomination,scrutny, widthdrawal and election date are not proper");
                                    send.focus();
                                    return false;
                          }
                  else{
                                    alert(wdate+" "+ sdate+"order of nomination,scrutny, widthdrawal and election date are not proper");
                                    start.focus();
                                    return false;
                                }
                      <%--else{
                          alert("order of nomination,scrutny, widthdrawal and election date are not proper");
                  send.focus();
                  return false;
                      }
              else{
                  alert("order of nomination,scrutny, widthdrawal and election date are not proper");
                  alert(start.value + " " + wend.value);
                  start.focus();
                  return false;}--%>
              }
            }
            else{
                alert("Please enter election date");
                start.focus();
                return false;
            }
            }
            else{
                alert("Please enter nomination Withdrawal date");
                wstart.focus();
                return false;
            }
            }
            else{
                alert("Please enter Scrutny date");
                sstart.focus();
                return false;
            }
            }
            else{
                alert("Please enter nomination date");
                nstart.focus();
                return false;
            }
}
</script>
    <% String institute_id=(String)session.getAttribute("institute_id");%>
    <% String user_id=(String)session.getAttribute("user_id");%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        
         <script language="javascript" type="text/javascript" src="js/datetimepicker.js">
    </script>
        <script language="javascript" type="text/javascript">
        function funconLoad()
        {
            searchPositions();  
        }

        </script>
    </head>
    <body onload="funconLoad()" >
   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
   <html:form method="post" action="/createelection" onsubmit="return check1()" style="top: 100px;position: absolute">
       <table align="center" width="80%" valign="top">
           <tr><td width="70%" align="left" valign="top" style="border: solid #ECF1EF 5px;">
                   <table><tr><td colspan="2" style="border: 2px solid teal"><div style="background-color: teal;width: 100%;color: white">Election Details </div>
     <html:hidden value="<%=institute_id%>" name="DepActionForm" property="instituteId"/>
  <html:hidden value="<%=user_id%>" name="DepActionForm" property="createdby"/>
     <table>
         <tr><td style="vertical-align: top;width: 550px;">
    Election Id
    <html:text readonly="true" property="electionId" styleId="electionId" name="DepActionForm" styleClass="textBoxWidth" />
             </td>
 <td style="width: 500px">
     Election Name<html:text readonly="<%=read %>" name="DepActionForm" style="width: 295px"  property="electionname" styleId="electionname"/><br><span style="font-size: 12px">Name your election: (ex. "Board of Directors Election 2009")*</span>
  </td>
         </tr></table></td></tr>
         <tr><td colspan="2">
         <div style="background-color: transparent; border: 2px solid teal">
             <span style="vertical-align: top; margin-left: 20px;">
                 <div style="background-color: teal;width: 100%;color: white">  Details </div><br><html:textarea readonly="<%=read %>" style="width: 700px;margin-left: 20px;" name="DepActionForm" property="description" styleId="details"/>
             </span
             <table style="margin-left: 20px;">

                 <tr><td><span style="font-size: 14px;font-weight: 600">Nomination start date and time: </span><br><span style="font-size: 12px">(Nominations opens and announcement are emailed)  *</span><br><html:text readonly="<%=read %>" name="DepActionForm" property="nominationStart"    styleId="Nstart_date"/><%if(!(request.getAttribute("button").equals("View") || request.getAttribute("button").equals("Block"))){%><a href="javascript:NewCal('Nstart_date','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" height="19" alt="Pick a date"/></a><%}%></td>
             <td><span style="font-size: 14px;font-weight: 600">Nomination end date and time:</span><br><span style="font-size: 12px"> (Nominations closed)  *</span><br><html:text readonly="<%=read %>" name="DepActionForm" property="nominationEnd"    styleId="Nend_date"/><%if(!(request.getAttribute("button").equals("View") || request.getAttribute("button").equals("Block"))){%><a href="javascript:NewCal('Nend_date','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" height="19" alt="Pick a date"/></a><%}%></td></tr>
         <tr><td><span style="font-size: 14px;font-weight: 600">Scrutny start date and time: </span><br><span style="font-size: 12px">  *</span><br><html:text readonly="<%=read %>" name="DepActionForm" property="scrutnyDate"    styleId="Scr_date"/><%if(!(request.getAttribute("button").equals("View") || request.getAttribute("button").equals("Block"))){%><a href="javascript:NewCal('Scr_date','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" height="19" alt="Pick a date"/></a><%}%></td>
             <td><span style="font-size: 14px;font-weight: 600">Scrutny end date and time:</span><br><span style="font-size: 12px"> *</span><br><html:text readonly="<%=read %>" name="DepActionForm" property="scrutnyEndDate"    styleId="Scrend_date"/><%if(!(request.getAttribute("button").equals("View") || request.getAttribute("button").equals("Block"))){%><a href="javascript:NewCal('Scrend_date','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" height="19" alt="Pick a date"/></a><%}%></td></tr>
        <tr><td><span style="font-size: 14px;font-weight: 600">Nomination Withdrawl start date and time: </span><br><span style="font-size: 12px">  *</span><br><html:text readonly="<%=read %>" name="DepActionForm" property="withdrawlDate"    styleId="wtd_date"/><%if(!(request.getAttribute("button").equals("View") || request.getAttribute("button").equals("Block"))){%><a href="javascript:NewCal('wtd_date','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" height="19" alt="Pick a date"/></a><%}%></td>
             <td><span style="font-size: 14px;font-weight: 600">Nomination Withdrawl end date and time:</span><br><span style="font-size: 12px"> *</span><br><html:text readonly="<%=read %>" name="DepActionForm" property="withdrawlEndDate"    styleId="wtdend_date"/><%if(!(request.getAttribute("button").equals("View") || request.getAttribute("button").equals("Block"))){%><a href="javascript:NewCal('wtdend_date','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" height="19" alt="Pick a date"/></a><%}%></td></tr>
        <tr><td style="vertical-align: top">
                 <span style="font-size: 14px;font-weight: 600">Election start date and time: </span><br><span style="font-size: 12px">(voting opens and announcement are emailed)  *</span><br><html:text readonly="<%=read %>" name="DepActionForm" property="startdate"    styleId="start_date"/><%if(!(request.getAttribute("button").equals("View") || request.getAttribute("button").equals("Block"))){%><a href="javascript:NewCal('start_date','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" hieght="19" alt="Pick a date"/></a><%}%></td>
             <td><span style="font-size: 14px;font-weight: 600">Election end date and time: </span><br><span style="font-size: 12px; azimuth: right">(voters can no longer cast ballots and results are emailed)  *</span><br><html:text readonly="<%=read %>" name="DepActionForm" property="enddate"    styleId="end_date"/><%if(!(request.getAttribute("button").equals("View") || request.getAttribute("button").equals("Block"))){%><a href="javascript:NewCal('end_date','ddmmyyyy',true,24)"><img src="images/cal.gif" width="16" hieght="19" alt="Pick a date"/></a><%}%></td></tr>
             </table>
         </div></td></tr>
         <tr><td colspan="2" style="border: 2px solid teal"><div style="background-color: teal;width: 100%;color: white">Positions</div>
                 <table><tr><td><div id="position" style="width: 950px;"></div></td></tr>
                     <tr><td><div style="position: static"><%if(!button.equals("View")&& !button.equals("Block")){%><input type="button" id="add Position" name="add Position" style="margin-left: 0px;" value="Add New Position" size="50px" onclick="createposition();"><%}%></div></td></tr></table></td></tr>
         <tr><td colspan="2">
                 <div style="border: 2px solid teal">
                     <div style="background-color: teal;width: 100%;color: white">Set Critaria </div>
                     <table>
                         <tr><td colspan="2"><br>Critaria for Candidates Nomination:* <html:text readonly="<%=read %>" name="DepActionForm" property="critaria" size="50px" styleId="cri"/></td></tr></table>
                 </div>
                 </td></tr>
         <tr><td colspan="2">
                 <div style="border: 2px solid teal"><table>
         <tr><td colspan="3"><div style="font-family: Liberation Serif;font-size: 20px;background-color: teal;width: 100%;color: white">Set Eligiblity </div></td></tr>
         <tr><td style="width: 250px">Department* <html:text readonly="<%=read %>" name="DepActionForm" property="deaprtment" size="12px"/></td>
             <td style="width: 350px">% Marks:(Greater than or  Equal to) * <html:text readonly="<%=read %>" name="DepActionForm" property="marks" size="18px"/></td>
             <td style="width: 200px">% Attendence:(Minimum)<br> <html:text readonly="<%=read %>" name="DepActionForm" property="attendence" size="18px"/></td></tr>
         <tr><td style="width: 250px">Backlog:(allowed) *</td><td>Criminal Background:(Allowed)*</td><td style="width: 550px">Student involves in Indisciplinery Activity(allowed)*</td></tr>
         <tr><td style="width: 250px"><html:radio name="DepActionForm" property="backlog" value="yes" />Yes<html:radio name="DepActionForm" property="backlog" value="no"/>No</td>
             <td style="width: 250px"><html:radio name="DepActionForm" property="criminal" value="yes"/>Yes<html:radio name="DepActionForm" property="criminal" value="no"/>No</td>
    <td style="width: 250px"><html:radio name="DepActionForm" property="indiscipline" value="yes"/>Yes<html:radio name="DepActionForm" property="indiscipline" value="no"/>No</td></tr>
                     </table>
                 </div>
                 </td></tr>
         <%if(request.getAttribute("button").equals("Block")){%>
         <tr><td>Election Status:&nbsp;&nbsp;<select id="block" name="block"><option selected value="block">Block</option><option selected value="unblock">UnBlock</option></select>
             </td></tr><%}%>






         <tr><td>*indicated fields are mandatory</td></tr>

         <tr>
             <td>
    <%if(button.equals("Update")){%>
    <input id="button1"  name="button" type="submit" onclick="<%--return checkdates();--%>" value="<%=button%>" class="txt1" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Cancel" onclick="return send()"  class="txt1"/>
    <%}else if(button.equals("Delete")){%>
    <input id="button1"  name="button" type="submit" value="<%=button%>" class="txt1" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()"  value="Cancel" class="txt1"/>
   <%}else if(button.equals("Add")){%>
   <input id="button1"  name="button" type="submit" onclick="<%--return checkdates();--%>" value="Submit" class="txt1"  />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Cancel" onclick="return send()" class="txt1"/>
    <%}else{%>
    <input  name="button" type="button" value="Back" class="txt1" onclick="return send();"/><%}%>
             </td></tr>
     </table>

               </td></tr>
       </table>
  </html:form>
    </body>
</html>

