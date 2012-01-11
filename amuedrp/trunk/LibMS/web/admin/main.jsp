<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<html><head>
    </head>
    <body  >

<jsp:include page="header.jsp" flush="true" />
<div
   style="
      top: 14%;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">


        <font size="-2"><br/>&nbsp;&nbsp;<i>You are here : </i>LibMS->Welcome Page</font>



    <table width="100%" height="600px" valign="top" style="" >
        <tr><td valign="top" width="90%" style="">
                <br/><%--<p class="mess">
    Request for Opac MemberShip Pending, View Details<br>
    Request for CheckOut Pending, View Details


</p>--%>
           


<%

session.removeAttribute("page");
String msg=(String)request.getAttribute("msg");
if (msg!=null)
    {
%>
<%=msg%>
<%}%>


            </td>               <td   valign="top"> 
          
            </td> </tr>

                </table>


                </div>


      </body>
</html>