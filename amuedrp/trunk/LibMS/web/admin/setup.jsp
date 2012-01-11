

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/admin/adminheader.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <%
String library_id=(String)session.getAttribute("library_name");
String sublibrary_id=(String)session.getAttribute("sublibrary_name");

    String login_id=(String) request.getAttribute("login_id");
    String user_name=(String) request.getAttribute("user_name");
    String staff_id=(String) request.getAttribute("staff_id");
    String staff_name=(String) request.getAttribute("staff_name");
    String location=(String)session.getAttribute("location1");
    String member=(String)session.getAttribute("member1");
    String submember=(String)session.getAttribute("submember1");
    String doc=(String)session.getAttribute("document1");


            request.setAttribute("login_id", login_id);
            request.setAttribute("user_name", user_name);
            request.setAttribute("library_id", library_id);
            request.setAttribute("staff_id", staff_id);
            request.setAttribute("staff_name", user_name);

session.setAttribute("page", "back");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS </title>
    </head>
    <body>
        <hr>
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;"><br><br><br>
     
 <table  class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage System Setup</td></tr>
                <tr><td valign="top" align="top"> 
      						
                        <p class="mess" align="justify">It is Mandatory to Set the following System Parameters for Library:&nbsp; <b><%=sublibrary_id%>, &nbsp;<%=library_id%>.</b><br> To continue click on <b>Set System Configuration</b> or use System Setup Menu.
                            <br></p><hr>
                        <table class="mess">
                            <tr>
                                                <%if(location!=null)
                                                   {%><td></td>
                                                    <td>Manage Location</td>
                                                    <%}else{ %>
                                                    <td><img src="<%=request.getContextPath()%>/images/images.jpeg" height="17px" width="25px" alt=""/></td><td>Manage Location</td><%}%>
                                                    </tr>
                                                    <tr>
                                                    <%if(member!=null)
                                                    {%><td/><td>
                                                        Manage Member Type </td>
                                                    <%}else{ %>
                                                    <td><img src="<%=request.getContextPath()%>/images/images.jpeg" height="17px" width="25px" alt=""/></td><td>Manage Member Type</td> <%}%>
                                                    </tr>
                                                    <tr>
                                                    <%if(submember!=null)
                                                    {%><td/><td>
                                                    Manage SubMember Type</td>
                                                    <%}else{ %>
                                                    <td><img src="<%=request.getContextPath()%>/images/images.jpeg" height="17px" width="25px" alt=""/></td><td>Manage SubMember Type</td><%}%>
                                                    </tr><tr>
                                                    <%if(doc!=null)
                                                    {%><td/><td>
                                                    Manage Document Category</td>
                                                    <%}else{ %>
                                                    <td><img src="<%=request.getContextPath()%>/images/images.jpeg" height="17px" width="25px" alt=""/></td><td>Manage Document Category</td><%}%>
                                                    </tr>

                        </table>

                                                

                    

                   
                    
              
                    </td></tr>
                <tr><td align="center"><input type="button" value="Set System Configuration" onClick="show()" class="txt2"/>
                    </td></tr></table>

 

        </div>
   

    </body>

<script language="javascript" type="text/javascript">
    function show()
    {
        location.href="<%=request.getContextPath()%>/checksystemsetup1.do";
    }
    </script>



</html>
