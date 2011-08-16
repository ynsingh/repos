<%-- 
    Document   : checkinbookdetail
    Created on : Mar 22, 2011, 4:15:51 AM
    Author     : edrp01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/admin/header.jsp" flush="true" />
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%



%>
    </head>
     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    <body>
<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <html:form   action="/circheckinbookdetail" method="post">

        <table width="600px" dir="<%=rtl%>"  valign="top" align="center" class="table">

  <tr><td dir="<%=rtl%>"  class="headerstyle" bgcolor="#E0E8F5" height="25px"  align="center">



          <b><%=resource.getString("circulation.cir_checkin.bookreturn")%></b>





        </td></tr>
 
        <tr><td dir="<%=rtl%>"  width="600px" height="200px" valign="top" style="" align="center">
                <br>
                <table dir="<%=rtl%>" cellspacing="10px">

                    <tr>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.cir_newmember.memberid")%></td>
                        <td dir="<%=rtl%>" >
                            <html:text    property="TXTMEMID" readonly="true" style="width:160px" />
                        </td>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.cirmembermessage.membername")%></td>
                        <td>
                            <html:text    property="TXTMEMNAME"  readonly="true" style="width:160px" />
                        </td>
                    </tr>
                    <tr>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.showcirreqopac.accessno")%></td>
                        <td>
                            <html:text    property="TXTACCESSION"  readonly="true" style="width:160px" />
                        </td>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("opac.myaccount.reservationrequest.title")%></td>
                        <td dir="<%=rtl%>" >
                           <html:text    property="TXTTITLE" style="width:160px" readonly="true"/>
                        </td>
                        
                    </tr>
                    <tr>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("opac.myaccount.reservationrequest.author")%></td>
                        <td dir="<%=rtl%>" >
                           <html:text    property="TXTAUTHOR" style="width:160px" readonly="true"/>
                        </td>
                        <td  dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.showcirreqopac.callno")%></td>
                        <td dir="<%=rtl%>" >
                           <html:text    property="TXTCALL" style="width:160px" readonly="true" />
                        </td>

                    </tr>
                    <tr>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.cir_view_book.duedate")%></td>
                        <td dir="<%=rtl%>" >
                           <html:text    property="TXTDUEDATE" style="width:160px" readonly="true"/>
                        </td>
                        <td  dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.cir_checkinbookdetail.returndate")%></td>
                        <td dir="<%=rtl%>" >
                           <html:text    property="TXTRETURNINGDATE" style="width:160px" readonly="true" />
                        </td>

                    </tr>
                    <tr>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.cir_checkinbookdetail.fine")%></td>
                        <td dir="<%=rtl%>" >
                           <html:text    property="TXTFINE" style="width:160px" readonly="true"/>
                        </td>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.cir_checkinbookdetail.delayed")%></td>
                        <td dir="<%=rtl%>" >
                           <html:text    property="TXTDELAYED" style="width:160px" readonly="true"/>
                        </td>

                    </tr>
                    
 <tr>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.cir_checkinbookdetail.damage")%></td>
                        <td dir="<%=rtl%>" >
                            <html:select property="TXTDAMAGEDSTATUS" styleId="damaged" style="width:160px" onchange="return showReason();">
                                <html:option value="Yes"><%=resource.getString("circulation.cir_checkinbookdetail.yes")%></html:option>
                                <html:option value="No"><%=resource.getString("circulation.cir_checkinbookdetail.no")%></html:option>


                            </html:select>
                        </td>
                        <td dir="<%=rtl%>" class="txt2"><%=resource.getString("circulation.cir_checkinbookdetail.lossstatus")%></td>
                        <td dir="<%=rtl%>" >
                           <html:select property="TXTLOSSSTATUS" style="width:160px">
                                <html:option value="Yes"><%=resource.getString("circulation.cir_checkinbookdetail.yes")%></html:option>
                                <html:option value="No"><%=resource.getString("circulation.cir_checkinbookdetail.no")%></html:option>


                            </html:select>
                        </td>

                    </tr>
            
                    <tr>
                        <td dir="<%=rtl%>" class=txt2>
                         <div id="txtreason" style="visibility: hidden;">
                 </div></td>
                        <td>
                            <div id="reasonEnabled" style="visibility: hidden;">
                                <html:text    property="TXTREASON" style="width:160px" />
                            </div>
                        </td>
                       
                    </tr>
                    


                </table>












</td></tr>
        <tr><td align="center">


                            <input type="submit" class="btn" id="Button1" name="button" value="<%=resource.getString("circulation.cir_checkinbookdetail.chkin")%>" />
                            <input type="button" id="Button5" name="button" value="<%=resource.getString("circulation.cir_newmember.back")%>" class="btn" onclick="return back()"/>
                


            </td></tr>
         </table>

                        <br><br>


 </html:form>

</div>
</body>
 <script language="javascript" type="text/javascript">

   function back()
    {

        location.href="<%=request.getContextPath()%>/circulation/cir_checkin.jsp";
    }

function showReason()
{
    
    var divreason = document.getElementById("reasonEnabled");
    if(document.getElementById("damaged").value == "Yes")
        {

        document.getElementById("txtreason").style.visibility= "visible";
        document.getElementById("txtreason").innerHTML = "Reason";
        document.getElementById("reasonEnabled").style.visibility= "visible";
        

        }
        else
            {
             document.getElementById("txtreason").innerHTML = "";
            document.getElementById("reasonEnabled").style.visibility= "hidden";
            document.getElementById("txtreason").style.visibility= "hidden";
            }
            return true;
}
   


</script>

</html>
