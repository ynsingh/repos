<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>

<jsp:include page="/admin/header.jsp"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">

<%
String button=(String)request.getAttribute("button");
String book_type=(String)request.getAttribute("book_type");
String emptype_id=(String)request.getAttribute("emptype_id");
String emptype_name=(String)request.getAttribute("emptype_name");
String subemptype_name=(String)request.getAttribute("subemptype_name");
String subemptype_id=(String)request.getAttribute("subemptype_id");
String book_typefullname=(String)request.getAttribute("book_typefullname");
String fine=(String)request.getAttribute("fine");
String rs=fine.substring(0,fine.indexOf("."));
String ps=fine.substring(fine.indexOf(".")+1,fine.length());
String limit=(String)request.getAttribute("limit");

%>
    <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    boolean button_visibility=true;
     boolean read=true;
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
function check1()
{

     var buttonvalue="Update";
    document.getElementById("button").setAttribute("value", buttonvalue);

     if(document.getElementById('permitday').value=="")
    {
        alert("Enter Issue Days Limit");


        document.getElementById('permitday').focus();

        return false;
    }
     if(document.getElementById('fineperdayRs').value=="")
    {
       alert("Enter Fine in Rupee or 0 for None");

        document.getElementById('fineperdayRs').focus();

        return false;
    }
     if(document.getElementById('fineperdayPs').value=="")
    {
        alert("Enter Fine in Paise or 0 for None");

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


function confirm1()
{
   document.getElementById('button').value="<%=button%>" ;
    var option=document.getElementById('button').value;
    if(option=="Delete"){
        var a=confirm("<%=resource.getString("circulation.cir_newmember.douwanttodelrec")%>");
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


  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_book.jsp";
      return false;
  }



    </script>

      <%! String button1;%>
 <%

 if(button.equals("Update"))
 {
   button1=resource.getString("circulation.cir_member_reg.update");
     read=false;

   button_visibility=true;
 }
 if(button.equals("Delete"))
 {
   button1=resource.getString("circulation.cir_member_reg.delete");
   read=true;

   button_visibility=true;
 }
%>
</head>
<body>

    <html:form method="post" action="/updatebook" onsubmit="return check1()">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
 <input type="hidden" name="button" value="<%=button%>"/>
    <table dir="<%=rtl%>" border="1" class="table" width="400px" height="200px" align="center">

        <%if(button.equalsIgnoreCase("view")){%>

       
                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_book.configfinedetail")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                        <table dir="<%=rtl%>" cellspacing="10px">
                            <tr><td dir="<%=rtl%>" width="150px" colspan="2"><%=resource.getString("opac.newmemberentry.typemem")%> </td>
                                <td dir="<%=rtl%>" width="150px" align="center"> <html:hidden  styleId="emptype" property="emptype_id" value="<%=emptype_id%>" />
                                    <input type="text" readonly   value="<%=emptype_name %>" />
                                </td>
                            </tr>
                            <tr><td dir="<%=rtl%>" width="150px" colspan="2"><%=resource.getString("opac.newmemberentry.memsubcat")%> </td><td width="150px" align="center"><html:hidden property="sub_emptype_id" name="BookCategoryDecideActionForm1" styleId="subemptype"  value="<%=subemptype_id%>"   />
                                <input type="text" readonly value="<%=subemptype_name%>"  />

                                </td>
                           
                            <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.document_category.enterdoccatename")%>

                        </td>
                        <td dir="<%=rtl%>" align="center"> <html:text styleId="book_type" property="book_type" value="<%=book_typefullname%>" readonly="true"/></td>
                    </tr>
                       
                    <tr><td dir="<%=rtl%>" colspan="2"><%=resource.getString("systemsetup.addbookcategory.noofdaypermited")%>

                        </td>
                        <td dir="<%=rtl%>" align="center"><html:text styleId="permitday"  onkeypress="return isNumberKey(event)" value="<%=limit%>" readonly="true" property="permitday"/><br>
                        <font size="-2" color="blue">(<%=resource.getString("systemsetup.addbookcategory.onlynumericval")%>)</font></td>
                    </tr>
                     <tr><td dir="<%=rtl%>"  colspan="3" height="5px"> </td>
                    </tr>
                  <tr><td  dir="<%=rtl%>" colspan="2"><%=resource.getString("systemsetup.addbookcategory.fineperday")%>

                        </td>
                        <td dir="<%=rtl%>" align="center"><font size="-1"> <%=resource.getString("systemsetup.addbookcategory.rs")%><html:text styleId="fineperdayRs" onkeypress="return isNumberKey(event)" readonly="true" value="<%=rs%>" property="fineRs"  size="2"/><%=resource.getString("systemsetup.addbookcategory.paise")%><html:text styleId="fineperdayPs" onkeypress="return isNumberKey(event)" value="<%=ps%>" property="finePs" readonly="true" size="2" /></font>
                            <br>
                        </td>
                    </tr>




                     <tr><td dir="<%=rtl%>"  align="center" colspan="3"><br/>
                             <input type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onClick="quit()"/>
                
                        </td>

                    </tr>
                        </table>

                    <%} else if(button.equalsIgnoreCase("update")){%>

                    <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Configure Fine Detail</td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                        <table dir="<%=rtl%>" cellspacing="10px">
                            <tr><td dir="<%=rtl%>" width="150px" colspan="2"><%=resource.getString("opac.newmemberentry.typemem")%> </td>
                                <td dir="<%=rtl%>" width="150px" align="center"> <html:hidden  styleId="emptype" property="emptype_id" value="<%=emptype_id%>" />
                                    <input type="text" readonly  value="<%=emptype_name %>" />
                                </td>
                            </tr>
                            <tr><td dir="<%=rtl%>" width="150px" colspan="2"><%=resource.getString("opac.newmemberentry.memsubcat")%></td><td width="150px" align="center"><html:hidden property="sub_emptype_id" value="<%=subemptype_id%>" name="BookCategoryDecideActionForm1" styleId="subemptype"  />
                                    <input type="textbox"  value="<%=subemptype_name%>" readonly styleId="subemptype"  />
                                </td>

                            <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.addbookcategory.doccategory")%><br><br>

                        </td>
                        <td dir="<%=rtl%>" align="center"> <html:hidden styleId="book_type" property="book_type" value="<%=book_type%>"/>
                            <input type="text" Id="book_type"  value="<%=book_typefullname %>" readonly/>

                        </td>
                    </tr>
                       
                    <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.addbookcategory.noofdaypermited")%><br><br>

                        </td>
                        <td dir="<%=rtl%>" align="center"><html:text styleId="permitday"  onkeypress="return isNumberKey(event)" value="<%=limit%>" property="permitday"/><br>
                     <font size="-2" color="blue">(<%=resource.getString("systemsetup.addbookcategory.onlynumericval")%>)</font></td>
                    </tr>
                     <tr><td dir="<%=rtl%>"  colspan="3" height="5px"> </td>
                    </tr>
                  <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.addbookcategory.fineperday")%>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"><font size="-1"> <%=resource.getString("systemsetup.addbookcategory.rs")%><html:text styleId="fineperdayRs" onkeypress="return isNumberKey(event)" property="fineRs" value="<%=rs%>"  size="2"/><%=resource.getString("systemsetup.addbookcategory.paise")%><html:text styleId="fineperdayPs" onkeypress="return isNumberKey(event)" property="finePs" value="<%=ps%>"  size="2" />
                            </font>  </td>

                       
                    </tr>




                     <tr><td dir="<%=rtl%>"  align="center" colspan="3"><br/>
                             <input type="submit"  value="<%=button1%>" onclick="return check1();"/>
                            <input type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onClick="quit()"/>

                        </td>

                    </tr>
                    </table>


                    <%}else if(button.equalsIgnoreCase("delete")){%>
                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_book.configfinedetail")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                        <table dir="<%=rtl%>" cellspacing="10px">
                            <tr><td dir="<%=rtl%>" width="150px" colspan="2"><%=resource.getString("opac.newmemberentry.typemem")%></td>
                                <td dir="<%=rtl%>" width="150px" align="center"> <html:hidden  styleId="emptype" property="emptype_id" value="<%=emptype_id%>"/>
                                 <input type="text" readonly  value="<%=emptype_name %>" /></td>
                            </tr>
                            <tr><td dir="<%=rtl%>" width="150px" colspan="2"><%=resource.getString("opac.newmemberentry.memsubcat")%> </td><td width="150px" align="center"><html:hidden property="sub_emptype_id" value="<%=subemptype_id%>" name="BookCategoryDecideActionForm" styleId="subemptype" />
                                <input type="textbox"  value="<%=subemptype_name%>" readonly Id="subemptype"  />

                                </td>

                            <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.addbookcategory.doccategory")%>

                        </td>
                        <td dir="<%=rtl%>" align="center"> <html:hidden styleId="book_type" property="book_type" value="<%=book_type%>" />
                          <input type="text" Id="book_type"  value="<%=book_typefullname %>" readonly/>

                        </td>
                    </tr>
                       
                    <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.addbookcategory.noofdaypermited")%>

                        </td>
                        <td dir="<%=rtl%>" align="center"><html:text styleId="permitday" value="<%=limit%>"  onkeypress="return isNumberKey(event)" readonly="true" property="permitday"/><br>
                        <font size="-2" color="blue">(<%=resource.getString("systemsetup.addbookcategory.onlynumericval")%>)</font></td>
                    </tr>
                       <tr><td dir="<%=rtl%>"  colspan="3" height="5px"> </td>
                  <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.addbookcategory.fineperday")%>

                        </td>
                        <td dir="<%=rtl%>" align="center"> <font size="-1"><%=resource.getString("systemsetup.addbookcategory.rs")%><html:text styleId="fineperdayRs" value="<%=rs%>" onkeypress="return isNumberKey(event)" readonly="true" property="fineRs"  size="2"/><%=resource.getString("systemsetup.addbookcategory.paise")%><html:text styleId="fineperdayPs" value="<%=ps%>" onkeypress="return isNumberKey(event)" property="finePs" readonly="true" size="2" /></font><br>
                       </td>
                    </tr>




                     <tr><td dir="<%=rtl%>" colspan="3" align="center"><br/>
                             <input type="submit"  value="<%=resource.getString("admin.acq_registerstaff.confirm")%>" styleId="button1" onclick="return confirm1();"/>
                            <input type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onClick="quit()"/><br/>
                        </td>

                    </tr>
                        </table>

                    <%}%>


                


  


                    <input  type="hidden" name="button" id="button"/>






</td></tr></table>
        </div>

</html:form>

</body>

    

</html>
