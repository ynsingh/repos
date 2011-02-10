<%-- 
    Document   : header
    Created on : Nov 13, 2010, 4:45:02 PM
    Author     : System Administrator
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN "http://www.w3.org/TR/html4/strict.dtd">
<%

String user=(String)session.getAttribute("username");
String pass=(String)session.getAttribute("pass");
 session.setAttribute("pass","t");
  String user_id=   (String)session.getAttribute("user_id");
String user_name=   (String) session.getAttribute("username");
  String question=  (String)request.getAttribute("question");
   String staff_id=  (String) request.getAttribute("staff_id");




%>
<script>
    // call the repeater with a function as the argument
function repeater()
{
  alert("Session is Expired.You Need to Login Again?")

        parent.location.href="/LibMS-Struts/login.jsp";
}
window.setTimeout(repeater, 1800000);
    </script>

       <body style="margin:0px 0px 0px 0px;">


<table width="100%" height="80px;" border="0px" style="">

                <tr><td >

                        <p align="left"  style="font-family:Tempus Sans ITC;color:brown;font-size:30px;"><span><b> &nbsp;&nbsp; <img src="/LibMS-Struts/images/lib.PNG" alt="banner space"  border="0" align="top" id="Image1" style="height:50px;width:200px;"></b></span></td>
                    <td><p align="center"  style="font-family:Tempus Sans ITC;color:brown;font-size:20px;"><span><b> </b></span></td>

                    <td align="right" width="250px" valign="top"><span style="font-family:arial;color:brown;font-size:12px;"><b>Hello [<%=user%>]&nbsp;|<a href="/LibMS-Struts/admin/logout.jsp" style="text-decoration: none;color:brown" >&nbsp;Sign Out</a></b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                
                     </td>
                </tr>




                </table>


           <hr/>
    </body>

   