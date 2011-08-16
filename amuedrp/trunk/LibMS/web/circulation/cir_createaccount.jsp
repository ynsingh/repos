<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
      This jsp page is used for  Create Account By Accepting password for OPAC NewMember Entry For Particular Member
      This jsp page is Fifth page  for one Complete Process  of member Registration.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*,java.io.*,java.util.*"%>
<jsp:include page="/admin/header.jsp" flush="true" />
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String mem_id=(String)request.getParameter("mem_id");
String mem_name=(String)request.getParameter("mem_name");
String last_name=(String)request.getParameter("last_name");
String mail_id=(String)request.getParameter("mail_id");
String[] lm=(String[])session.getAttribute("noofaccount");

%>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS</title>
    </head>
      <script language="javascript" type="text/javascript">
         function Submit()
{
    var buttonvalue="Submit";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
         function check2()
{
    KeyValue=document.getElementById('mem_id').value;
        KeyValue1=document.getElementById('password').value;

        if(KeyValue==KeyValue1)
            {
               availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="<%=resource.getString("circulation.cir_newmember.loginandpascannotsame")%>";
                document.getElementById('password').value="";

            }
else{
 availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="";

}

}

function check1()
{



 //  var x=document.getElementById('password');
      //  if(x.value=="")
       //     {
       //         alert("Password should not be blank");
        //        document.getElementById('password').focus();
        //        return false;
        //    }
  //   if(document.getElementById('password1').value=="")
   // {
     //   alert("Enter Confirm password...");

   //     document.getElementById('password1').focus();
// document.getElementById('password1').focus();
    //    return false;
   // }
      //    var x1=document.getElementById('password');
     //   var x2=document.getElementById('password1');
     //   if(x1.value!=x2.value)
     //       {
    //            alert("password mismatch");
     //           document.getElementById('password1').focus();
     //           return false;
     //       }




var x=document.getElementById('card_id');
        if(x.value=="")
            {
                alert("<%=resource.getString("circulation.cir_createaccount.cardidcanotbeblank")%>");
                 document.getElementById('card_id').focus();
                return false;
            }
    return true;
  }
   function dupli()
    {







   //   var x1=document.getElementById('password');
    //    var x2=document.getElementById('password1');
    //    if(x1.value!=x2.value)
    //        {
     //           alert("password mismatch");
     //           document.getElementById('password').value="";
     //           document.getElementById('password1').value="";
      //          document.getElementById('password').focus();
       //         return false;
       //     }
        //    else
         //       return true;




           //     return true;


    }

    function quit()
    {
       window.location="<%=request.getContextPath()%>/admin/main.jsp";
       return false;

    }

    </script>

    <body >


  <html:form  action="/cir_account" method="post" onsubmit="return check1()">
      <table width="400px" dir="<%=rtl%>"   valign="top" align="center" class="table">
          <tr><td dir="<%=rtl%>"  class="headerStyle" valign="top" align="center" height="10px"><%=resource.getString("circulation.cir_createaccount1.mangmemacc")%></td></tr>
        <tr><td dir="<%=rtl%>"  valign="top" align="center">

                    <table dir="<%=rtl%>" width="400px" align="center" width="100%">
                        <tr><td dir="<%=rtl%>"class="btn" width="250px"><%=resource.getString("circulation.cir_createaccount1.memberid/loginid")%></td><td dir="<%=rtl%>" width="250px"><input type="text" id="mem_id"  name="mem_id" readonly  value="<%=mem_id%>"></td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px"></td></tr>
                        <tr><td dir="<%=rtl%>" class="btn"><%=resource.getString("circulation.cir_createaccount1.memname")%></td><td dir="<%=rtl%>"><input type="text"  name="mem_name" id="mem_name"   readonly  value="<%=mem_name%> <%=last_name%>"></td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px"></td></tr>
                        <tr><td dir="<%=rtl%>" class="btn" ><%=resource.getString("circulation.cir_newmember.email")%></td><td dir="<%=rtl%>"><input type="text"  name="mail_id" id="mail_id" readonly  name="Editbox2" value="<%=mail_id%>"/> </td></tr>
                        <tr><td dir="<%=rtl%>"colspan="2" height="5px"></td></tr>
                    
                         <tr><td dir="<%=rtl%>" class="btn"><%=resource.getString("circulation.cir_createacct.cardtype")%></td><td>
                                 <select id="card" name="card_type">
                                     <% if(lm!=null){%>
                                     <option value="sc"><%=resource.getString("circulation.cir_createacct.smartcard")%></option>
                                     <option value="gc" selected><%=resource.getString("circulation.cir_createacct.gencard")%></option>
                                     <%}else{%>
                                     <option value="gc" selected><%=resource.getString("circulation.cir_createacct.gencard")%></option>
                                     <%}%>
                                 </select>
                                 </td></tr>


                        <tr><td dir="<%=rtl%>" class="btn"><%=resource.getString("circulation.cir_newmember.cardid")%></td><td dir="<%=rtl%>"><input type="text"   name="card_id" id="card_id"   value=""></td></tr>

                        <tr><td dir="<%=rtl%>" colspan="2" align="center">
                                <br>
                                <br>
                         <input type="submit" id="Button1"   value="<%=resource.getString("circulation.cir_newmember.submit")%>" class="btn" onclick="return Submit();">
                         <input type="reset" id="Button2" value="<%=resource.getString("circulation.cir_newmember.reset")%>" onclick=" " class="btn">
                         <input type="button" id="Button3"  value="<%=resource.getString("circulation.cir_newmember.back")%>" onclick=" return quit()" class="btn">
                         <br><br>
                            </td></tr>

<input type="hidden" value="<%=mem_name%>" name="mem_name"/>
<input type="hidden" value="<%=last_name%>" name="last_name"/>
<input type="hidden" id="button" name="button" value=""/>
                    </table>













</td></tr></table>
                        </html:form>
        </div>



    </body>




</html>
