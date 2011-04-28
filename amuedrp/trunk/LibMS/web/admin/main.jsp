<html><head>
    </head>
    <body  >

<jsp:include page="header.jsp" flush="true" />
<div
   style="
      top: 105;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table width="100%" height="600px" valign="top" style="">
        <tr><td valign="top" width="120px" style="">
                </td><td width="1200px">


<%
session.removeAttribute("page");
String msg=(String)request.getAttribute("msg");
if (msg!=null)
    {
%>
<%=msg%>
<%}%>


            </td>               <td width="120px"  valign="top">  </td> </tr>

                </table>


                </div>


      </body>
</html>