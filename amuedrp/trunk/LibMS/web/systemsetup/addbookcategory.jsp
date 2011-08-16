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
<jsp:include page="/admin/header.jsp"/>

<%
String library_id=(String)session.getAttribute("library_id");
String book_type=(String)request.getAttribute("book_type");
String emptype_id=(String)request.getAttribute("emptype_id");
String emptype_name=(String)request.getAttribute("emptype_name");
String subemptype_name=(String)request.getAttribute("subemptype_name");
String subemptype_id=(String)request.getAttribute("subemptype_id");
String book_typefullname=(String)request.getAttribute("book_typefullname");



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
    
     if(document.getElementById('permitday').value=="")
    {
        alert("<%=resource.getString("systemsetup.addbookcategory.enterissuedaylmt")%>");


        document.getElementById('permitday').focus();

        return false;
    }
     if(document.getElementById('fineperdayRs').value=="")
    {
        alert("<%=resource.getString("systemsetup.addbookcategory.enterfineinrupee")%>");

        document.getElementById('fineperdayRs').focus();

        return false;
    }
     if(document.getElementById('fineperdayPs').value=="")
    {
        alert("<%=resource.getString("systemsetup.addbookcategory.enterfineinpaisae")%>");

        document.getElementById('fineperdayPs').focus();

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

      window.location="<%=request.getContextPath()%>/systemsetup/manage_book.jsp";
      return false;
  }



    </script>
</head>
<body>

    <html:form method="post" action="/addbook" onsubmit="return check1()">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table dir="<%=rtl%>" class="table" width="400px" height="200px" align="center">


                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_book.configfinedetail")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                        <table dir="<%=rtl%>" cellspacing="10px" width="80%">
                            <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("opac.newmemberentry.typemem")%> </td>
                                <td dir="<%=rtl%>" align="center"> <html:hidden  styleId="emptype" property="emptype_id" value="<%=emptype_id%>"/>
                                    <html:text  styleId="emptype" readonly="true" property="emptype_id" value="<%=emptype_name%>"/>
                                </td>
                            </tr>
                            <tr><td dir="<%=rtl%>" colspan="2"><%=resource.getString("opac.newmemberentry.memsubcat")%> </td><td width="150px" align="center"><html:hidden property="sub_emptype_id" name="BookCategoryDecideActionForm" styleId="subemptype" value="<%=subemptype_id%>" />
                                <html:text property="sub_emptype_id" name="BookCategoryDecideActionForm" styleId="subemptype_name" value="<%=subemptype_name%>"  readonly="true" />

                                </td>
                           
                            <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.addbookcategory.doccategory")%>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"> <html:hidden styleId="book_type" property="book_type" value="<%=book_type%>"/>
                        <html:text styleId="book_type" property="book_type" value="<%=book_typefullname%>"  readonly="true"/>

                        </td>
                    </tr>
                      

                      
                       <html:hidden styleId="fullname" name="BookCategoryDecideActionForm"  property="full_name"/>
                    
                    <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.addbookcategory.noofdaypermited")%><br><br>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"><html:text styleId="permitday" value="" onkeypress="return isNumberKey(event)" property="permitday"/><br>
                            <font size="-2" color="blue">(<%=resource.getString("systemsetup.addbookcategory.onlynumericval")%>)</font></td>
                    </tr>
                    <tr><td dir="<%=rtl%>"  colspan="3" height="5px"> </td>
                    </tr>

                  <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.addbookcategory.fineperday")%>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"><font size="-1"><%=resource.getString("systemsetup.addbookcategory.rs")%> <html:text styleId="fineperdayRs"  onkeypress="return isNumberKey(event)" property="fineRs" value="" size="2"/><%=resource.getString("systemsetup.addbookcategory.paise")%> <input type="text" id="fineperdayPs" onkeypress="return isNumberKey(event)" name="finePs" size="2" value=""/>
                            </font>  </td>
                    </tr>

                     <tr><td dir="<%=rtl%>" class="txt2" colspan="3" align="center"><br/>
                             <input type="submit"  value="<%=resource.getString("circulation.cir_newmember.submit")%>"/>
                             <input type="button" onClick="quit()"  value="<%=resource.getString("circulation.cir_member_reg.back")%>"/><br/>
                        </td>

                    </tr>


                </table>


    <input type="hidden" name="library_id" value="<%=library_id%>">









</td></tr></table>
        </div>

</html:form>

</body>

    

</html>
