<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List"%>

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
function check1()
{
    
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
   var answer = confirm ("Do you want to Delete Record?")
if (answer!=true)
    {
        document.getElementById('button1').focus();
        return false;
    }
    else
        {
   
        return true;

        }


}


  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_book.jsp";
      return false;
  }



    </script>
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
    <table border="1" class="table" width="400px" height="200px" align="center">

        <%if(button.equalsIgnoreCase("view")){%>

       
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Configure Fine Detail</td></tr>
                <tr><td valign="top" align="center"> <br/>
                        <table cellspacing="10px">
                            <tr><td width="150px" colspan="2">Member Type </td>
                                <td width="150px" align="center"> <html:hidden  styleId="emptype" property="emptype_id" value="<%=emptype_id%>" />
                                    <input type="text" readonly   value="<%=emptype_name %>" />
                                </td>
                            </tr>
                            <tr><td width="150px" colspan="2">SubMember Type </td><td width="150px" align="center"><html:hidden property="sub_emptype_id" name="BookCategoryDecideActionForm1" styleId="subemptype"  value="<%=subemptype_id%>"   />
                                <input type="text" readonly value="<%=subemptype_name%>"  />

                                </td>
                           
                            <tr><td   colspan="2">Enter Book Type

                        </td>
                        <td align="center"> <html:text styleId="book_type" property="book_type" value="<%=book_typefullname%>" readonly="true"/></td>
                    </tr>
                       
                    <tr><td  colspan="2">Number Of Permit Days

                        </td>
                        <td align="center"><html:text styleId="permitday"  onkeypress="return isNumberKey(event)" value="<%=limit%>" readonly="true" property="permitday"/><br>
                        <font size="-2" color="blue">(Only numberic value permitted)</font></td>
                    </tr>
                     <tr><td   colspan="3" height="5px"> </td>
                    </tr>
                  <tr><td   colspan="2">Fine Per Day

                        </td>
                        <td align="center"><font size="-1"> Rs:<html:text styleId="fineperdayRs" onkeypress="return isNumberKey(event)" readonly="true" value="<%=rs%>" property="fineRs"  size="2"/>Paise:<html:text styleId="fineperdayPs" onkeypress="return isNumberKey(event)" value="<%=ps%>" property="finePs" readonly="true" size="2" /></font>
                            <br>
                        </td>
                    </tr>




                     <tr><td  align="center" colspan="3"><br/>
                             <input type="button" value="Back" onClick="quit()"/>
                
                        </td>

                    </tr>
                        </table>

                    <%} else if(button.equalsIgnoreCase("update")){%>

                    <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Configure Fine Detail</td></tr>
                <tr><td valign="top" align="center"> <br/>
                        <table cellspacing="10px">
                            <tr><td width="150px" colspan="2">Member Type </td>
                                <td width="150px" align="center"> <html:hidden  styleId="emptype" property="emptype_id" value="<%=emptype_id%>" />
                                    <input type="text" readonly  value="<%=emptype_name %>" />
                                </td>
                            </tr>
                            <tr><td width="150px" colspan="2">SubMember Type </td><td width="150px" align="center"><html:hidden property="sub_emptype_id" value="<%=subemptype_id%>" name="BookCategoryDecideActionForm1" styleId="subemptype"  />
                                    <input type="textbox"  value="<%=subemptype_name%>" readonly styleId="subemptype"  />
                                </td>

                            <tr><td  colspan="2">Document Category<br><br>

                        </td>
                        <td align="center"> <html:hidden styleId="book_type" property="book_type" value="<%=book_type%>"/>
                            <input type="text" Id="book_type"  value="<%=book_typefullname %>" readonly/>

                        </td>
                    </tr>
                       
                    <tr><td   colspan="2">Number of Days Permitted<br><br>

                        </td>
                        <td align="center"><html:text styleId="permitday"  onkeypress="return isNumberKey(event)" value="<%=limit%>" property="permitday"/><br>
                     <font size="-2" color="blue">(Only numberic value permitted)</font></td>
                    </tr>
                     <tr><td   colspan="3" height="5px"> </td>
                    </tr>
                  <tr><td   colspan="2">Fine Per Day

                        </td>
                        <td align="left"><font size="-1"> Rs:<html:text styleId="fineperdayRs" onkeypress="return isNumberKey(event)" property="fineRs" value="<%=rs%>"  size="2"/>Paise:<html:text styleId="fineperdayPs" onkeypress="return isNumberKey(event)" property="finePs" value="<%=ps%>"  size="2" />
                            </font>  </td>

                       
                    </tr>




                     <tr><td  align="center" colspan="3"><br/>
                             <html:submit  value="<%=button%>" onclick="return check1();"/>
                            <input type="button" value="Back" onClick="quit()"/>

                        </td>

                    </tr>
                    </table>


                    <%}else if(button.equalsIgnoreCase("delete")){%>
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Configure Fine Detail</td></tr>
                <tr><td valign="top" align="center"> <br/>
                        <table cellspacing="10px">
                            <tr><td width="150px" colspan="2">Member Type </td>
                                <td width="150px" align="center"> <html:hidden  styleId="emptype" property="emptype_id" value="<%=emptype_id%>"/>
                                 <input type="text" readonly  value="<%=emptype_name %>" /></td>
                            </tr>
                            <tr><td width="150px" colspan="2">SubMember Type </td><td width="150px" align="center"><html:hidden property="sub_emptype_id" value="<%=subemptype_id%>" name="BookCategoryDecideActionForm" styleId="subemptype" />
                                <input type="textbox"  value="<%=subemptype_name%>" readonly Id="subemptype"  />

                                </td>

                            <tr><td   colspan="2">Document Category

                        </td>
                        <td align="center"> <html:hidden styleId="book_type" property="book_type" value="<%=book_type%>" />
                          <input type="text" Id="book_type"  value="<%=book_typefullname %>" readonly/>

                        </td>
                    </tr>
                       
                    <tr><td   colspan="2">Number Of Permit Days

                        </td>
                        <td align="center"><html:text styleId="permitday" value="<%=limit%>"  onkeypress="return isNumberKey(event)" readonly="true" property="permitday"/><br>
                        <font size="-2" color="blue">(Only numberic value permitted)</font></td>
                    </tr>
                       <tr><td   colspan="3" height="5px"> </td>
                  <tr><td   colspan="2">Fine Per Day

                        </td>
                        <td align="center"> <font size="-1">Rs:<html:text styleId="fineperdayRs" value="<%=rs%>" onkeypress="return isNumberKey(event)" readonly="true" property="fineRs"  size="2"/>Paise:<html:text styleId="fineperdayPs" value="<%=ps%>" onkeypress="return isNumberKey(event)" property="finePs" readonly="true" size="2" /></font><br>
                       </td>
                    </tr>




                     <tr><td  colspan="3" align="center"><br/>
                             <html:submit  value="Confirm" styleId="button1" onclick="return confirm1();"/>
                            <input type="button" value="Back" onClick="quit()"/><br/>
                        </td>

                    </tr>
                        </table>

                    <%}%>


                


  









</td></tr></table>
        </div>

</html:form>

</body>

    

</html>
