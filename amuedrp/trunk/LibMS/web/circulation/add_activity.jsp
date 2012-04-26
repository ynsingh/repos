<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.DeliquencyReason,java.util.List,com.myapp.struts.CirDAO.CirculationDAO "%>
<jsp:include page="/admin/header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <script type="text/javascript">
var intTextBox=200;
var intTextBox1=400;
var DateValue1=document.getElementById(intTextBox);
var DateValue2=document.getElementById(intTextBox1);
//FUNCTION TO ADD TEXT BOX ELEMENT
function addElement()
{
intTextBox = intTextBox + 1;
intTextBox1 = intTextBox1 + 1;
var contentID = document.getElementById('content');
var newTBDiv = document.createElement('table');
newTBDiv.setAttribute('id','strText'+intTextBox);
//newTBDiv.setAttribute('width','');
var tt=newTBDiv.innerHTML;
newTBDiv.innerHTML = "<tr><td align='left'>&nbsp;&nbsp; <input type='text' name='id' id='id' /></td><td align='left'>&nbsp;&nbsp; <input type='text' name='details' id='"+intTextBox+"'/></td></tr>";
contentID.appendChild(newTBDiv);
}

//FUNCTION TO REMOVE TEXT BOX ELEMENT
function removeElement()
{
if(intTextBox != 0)
{
var contentID = document.getElementById('content');
contentID.removeChild(document.getElementById('strText'+intTextBox));
intTextBox = intTextBox-1;
}
}
function DiffOfDate(DateValue1, DateValue2)
{
var DaysDiff;
Date1 = new Date(DateValue1);
Date2 = new Date(DateValue2);
DaysDiff = Math.floor((Date1.getTime() - Date2.getTime())/(1000*60*60*24));
if(DaysDiff > 0)
{
    document.getElementById("activityD").value=DaysDiff;
}
else
{
return false;
}
}
</script>

<%!
int fromIndex,toIndex;
int pagesize=4,size;
int pageIndex;
int noofpages;
int modvalue;
String index;
List obj1;
%>
<%

List<DeliquencyReason> l1=(List<DeliquencyReason>)session.getAttribute("actlist");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else

     {
     pageIndex = 1;
     }

 if(l1!=null)
        size = l1.size();
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

        <script type="text/javascript">


function check1()
{

 if(document.getElementById('id').value=="")
    {
        alert("Please Enter ID");

        document.getElementById('id').focus();

        return false;
    }
    if(document.getElementById('details').value=="")
    {
        alert("Please Enter Details");

        document.getElementById('details').focus();

        return false;
    }

  }

        function RotateSpinner(spinnerId, up) {
            document.getElementById(spinnerId).value = up ? parseInt(document.getElementById(spinnerId).value) + 1 : parseInt(document.getElementById(spinnerId).value) - 1;
        }
    </script>

    <style>
        *
        {
            padding: 0;
            margin: 0;
        }
        .spinner
        {
            list-style: none;
            display: inline-block;
            line-height: 0;
            vertical-align: middle;
        }
        .spinner input
        {
            font-size: .45em;
            border-width: .5px;
            height: 1.5em;
            width: 2em;
        }
    </style>
    <link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
    </head>
    <body onload="javascript:addElement();">
        <div
   style="  top:150px;
   left:150px;
   right:5px;
      position: absolute;


      visibility: show;">


  <%! int i=0,j=10; %>
  <html:form action="/addAct" styleId="form1" onsubmit="return check1();">


 <table  class="table" width="50%"  align="center" cellpadding="10px">
     <tr class="headerStyle"><td align="center" height="35px">Delinquency Reason SetUp</td></tr>
     <tr ><td  align="center">
     <table  border="0px" width="50%">
         <tr><td>
               Deliquency Id<br/>
               <input type='text' name='id' id='id' /><br/>
               Description<br/>
               <input type='text' name='details' id='details'/>
             </td><td valign="top"><br/><input type="submit" id="button1" name="button" value="Save" onclick="return check1();"/></td></tr>
         <tr><td colspan="2" class="err" height="35px">

                 <%
String msg1=(String)request.getAttribute("msg1");
if(msg1!=null)
{
%>
<%=msg1%>
      <%}%>


             </td></tr>
     </table>



</td></tr>


  <tr><td align='left'></td></tr>


 <tr class="headerStyle"><td  align="center" height="35px">View All Deliquency SetUp</td></tr>

  <tr><td colspan="3"><div id="divlist">
               <iframe name="listdiv" scrolling="no" frameborder="0" height="200px" width="100%" src="<%=request.getContextPath()%>/circulation/viewallDel.do"></iframe>
               </div>
           </td></tr>

        <tr >
        <td align="center" dir=""><p align="center" dir="">Pages&nbsp;&nbsp;
        <%for(int ii=1;ii<=noofpages;ii++){%>
        <a dir="" target="listdiv" href="<%=request.getContextPath()%>/circulation/viewallDel.do?pageIndex=<%=ii%>"><%=ii%></a>&nbsp;&nbsp;

        <%}%>
            </p>
        </td></tr>
 </table>

        </html:form>
        </div>
        </body>
</html>

