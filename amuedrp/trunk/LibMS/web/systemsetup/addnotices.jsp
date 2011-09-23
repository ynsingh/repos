<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="/admin/header.jsp" flush="true" />

<%
String  date="" ;

  Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=year+"-"+ (month+1) +"-"+day;
String library_id=(String)session.getAttribute("library_id");
String notice_id=(String)request.getAttribute("notice_id");

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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Book Category </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">


function check1()
{
    
    
    if(document.getElementById('subject_type').value=="")
    {
        alert("Enter Subject");

        document.getElementById('subject_type').focus();

        return false;
    }
     if(document.getElementById('detail_type').value=="")
    {
        alert("Enter Detail");


        document.getElementById('detail_type').focus();

        return false;
    }
   
   
return true;
  }

function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }




  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_notices.jsp";
      return false;
  }
  function loadHelp()
    {
        window.status="Press F1 for Help";

    }




    </script>
      <script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
</head>

 <body onload="loadHelp()" >
    <html:form method="post" action="/notice" onsubmit="return check1();">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table dir="<%=rtl%>" border="1" class="table" width="400px" height="200px" align="center">


                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_notice.addnotices")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                        <table dir="<%=rtl%>" cellspacing="10px">
                             <tr><td dir="<%=rtl%>"  class="txt2" colspan="2"><%=resource.getString("circulation.cir_checkout_report.date")%>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"><html:text   styleId="date_type" property="date" readonly="true" value="<%=date%>"/><br>
                       </td>
                    </tr>
                 
                            <tr><td dir="<%=rtl%>" width="150px" class="txt2" colspan="2"><%=resource.getString("systemsetup.manage_notice.noticesid")%> </td>
                                <td dir="<%=rtl%>" width="150px" align="<%=align%>"> <html:text  styleId="noticetype" property="notice_id" value="<%=notice_id%>" readonly="true"/>
                                </td>
                            </tr>
                          
                           
                            <tr><td dir="<%=rtl%>"  class="txt2" colspan="2"><%=resource.getString("systemsetup.manage_notice.entersub")%><span class="star">*</span>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"> <html:text styleId="subject_type" property="subject"  onfocus="statwords('Please Enter Valid subject ');" value="" onblur="return loadHelp();"  /></td>
                    </tr>
                       <tr><td dir="<%=rtl%>"  class="txt2" colspan="2"><%=resource.getString("systemsetup.manage_notice.enterdetail")%><span class="star">*</span>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"> <html:textarea  styleId="detail_type"  onfocus="statwords('Please Enter Detail ');"  property="detail" onblur="return loadHelp();"/></td>
                    </tr>
                   

                    <tr><td dir="<%=rtl%>"  class="txt2" colspan="3" align="center">
                            <br/>   <input type="submit"  value="<%=resource.getString("circulation.cir_newmember.submit")%>"/>
                             <input type="button" onClick="quit()"  value="<%=resource.getString("circulation.cir_member_reg.back")%>"/><br/><br/>
                        </td>

                    </tr>


                </table>


    <input type="hidden" name="library_id" value="<%=library_id%>">









</td></tr></table>
        </div>

</html:form>

</body>
 
    

</html>
