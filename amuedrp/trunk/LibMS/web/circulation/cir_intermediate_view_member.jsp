<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--
     Design by Iqubal Ahmad
     Modified on 2011-02-02
     This jsp page is meant for Dynamically change button value as View,Update,Deleate this is only page from where
     View, update, Deleate is done also used for   Ajax for
     Dept,Fac,course & image upload can be done.
     This jsp page is third page  for one Complete Process  of member Registration.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp"/>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>



<%
   
List<CirMemberAccount>     cirmemacct  =(List<CirMemberAccount>)request.getAttribute("cirmemaccountdetail");
CirMemberDetail cirmem=(CirMemberDetail)request.getAttribute("cirmemdetail");

System.out.println(cirmemacct+".........................");
String mem_id=(String)request.getAttribute("memid");
String sublibrary_id=(String)session.getAttribute("sublibrary_id");
String button=(String)request.getAttribute("button");
    %>


</head>
<body >


    <html:form action="/cirshowaccount" method="post">
Given Member Has Account in Following Libraries U can view its details of ur your sublibrary only
<html:hidden value="<%=sublibrary_id %>" property="library"/>
<html:hidden value="<%=mem_id %>" property="memId"/>
<html:hidden value="<%=button %>" property="button"/>
<html:select  property="library" styleId="Library" disabled="true"   size="5"  style="width: 160px" value="<%=sublibrary_id%>">
                         <html:options  collection="cirmemaccountdetail"  labelProperty="id.sublibraryId" property="id.sublibraryId"></html:options>
                     </html:select>

  
<input type="submit" value="Proceed"/>

 </html:form>


</body>



<script language="javascript" type="text/javascript">
    function quit()
    {
        location.href="<%=request.getContextPath()%>/circulation/cir_member_reg.jsp";
    }

    function del()
    {

    var option=document.getElementById('button').value;
    if(option=="Delete"){
        var a=confirm("Do You Want To Delete Record ");
       // alert(a);
        if(a!=true)
            {
                document.getElementById('button').focus();
               return false;

        }
        else
            return true;
    }
    }

</script>
 <script language="javascript" type="text/javascript">



    function validation()
    {



    var TXTREG_DATE=document.getElementById('TXTREG_DATE');
    var TXTEXP_DATE=document.getElementById('TXTEXP_DATE');





var str="Enter Following Values:-";




    if(TXTREG_DATE.value=="")
       { str+="\n Enter Date of Registration";
            alert(str);
            document.getElementById('TXTREG_DATE').focus();
            return false;

       }

    if(TXTEXP_DATE.value=="")
      {  str+="\n Enter Expiry date";
           alert(str);
           document.getElementById('TXTEXP_DATE').focus();
            return false;

      }

if(IsDateGreater(TXTREG_DATE.value,TXTEXP_DATE.value)==true)
    {

       str+="\nDate of Expiry Should be greater than Date of Registration";
       alert(str);
         document.getElementById('TXTEXP_DATE').focus();
         return false;
    }

if(str=="Enter Following Values:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('email_id').focus();
        return false;
    }




    }


function IsDateGreater(DateValue1, DateValue2)
{

var DaysDiff;
Date1 = new Date(DateValue1);
Date2 = new Date(DateValue2);
DaysDiff = Math.floor((Date1.getTime() - Date2.getTime())/(1000*60*60*24));
if(DaysDiff > 0)
{

  return true;
}
else
{

return false;
}
}

</script>




   <div
   style="
      top: 650px;

      position: absolute;

      visibility: show;">
           <jsp:include page="/admin/footer.jsp" /> 
   </div>

</html>
