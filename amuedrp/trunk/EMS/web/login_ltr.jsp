<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
    <script type="text/javascript">
        function submitLogin()
{
    var buttonvalue="Log In";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitForget()
{
    var buttonvalue="Forget Password";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
    </script>
<tr>
    <td style="margin-left: 65px" valign="top" colspan="3" width="100%" align="center">
        <table style=" margin-top: 0px;width: 100%" align="center"  dir="<%=rtl%>">
            <tr><td colspan="2"  valign="bottom" height="50px" align="<%=align%>">
                                <img src="images/ems.JPG" alt="banner space"  border="0" align="top" id="Image1" style="height: 125px;width:260px">
                            <br>
                <%String align="left"; if(page.equals(false)){align="right";}else{align="left";}%>
                           
                                <table dir="<%=rtl%>"><tr><td><span style="font-family: Tahoma" dir="<%=rtl%>"><%=resource.getString("login.message.logo.under")%></span><br></td></tr>
                                    <tr><td></td></tr></table>

                            </td><td  align="center" width="250px"> <img src="images/logo.png" alt=""  border="0" align="top" id="Image1" style="" height="100px" width="250px">
                            </td></tr>
            </table></td>
            </tr>

            <tr style=""><td width="1000px" colspan="2" valign="top" align="center" dir="<%=rtl%>" >

                               <br>
                            <span style="font-family: Arial;font-size:15px;" dir="<%=rtl%>"><br>
                                <table style="border:solid 1px #BFDBFF; height: 250px; width: 800px" dir="<%=rtl%>">
                                    <tr><td colspan="2" width="400px" style="background: #BFDBFF;height: 20px">
                                            <span style="text-align: center;text-decoration: blink; font-size: 14px; color: navy; font-family:arial " ><b style="text-align: center"><%=resource.getString("electionupdates") %></b></span>
                                        </td>

                                    </tr>
                                    <tr><td width="800px" style="border: solid 1px #BFDBFF;">
                                            <iframe src="<%=request.getContextPath()%>/news.jsp"  frameborder="0" width="100%" height="100%" style="vertical-align: text-top" scrolling="no"></iframe></td>
                                    </tr>
                                </table>





                        </span>

                </td><td  width="800px" colspan="2" valign="top" align="center">
                    <br><table dir="<%=rtl%>" width="90%" align="center"><tr><td align="center" style=" font-family:Tahoma;font-size:15px;"><%=resource.getString("login.message.selectlanguage")%><select name="locale" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select></td></tr></table>
                    <table  cellpadding="0" style="border:solid 1px #BFDBFF;color:#006BF5;font-family:Tahoma;font-size:13px;font-weight: bold;height:150px;width:300px" dir="<%=rtl%>">
                    <tr><td colspan="2" height="2px" style="color:#006BF5; vertical-align: top" align="center" ><br><%=resource.getString("login.message.signin.top")%><br>

                                <img src="images/lib1.PNG" style="height:75px;width:75px;" alt="" style=""  border="0" align="top" id="Image1" style="">&nbsp;</td>


                    </tr>
                    <tr>
                    <td  align="center">
                        <table dir="<%=rtl%>">
                            <tr> <td><%=resource.getString("login.message.signin.username")%></td>
                                <td align="left"><input name="username" type="text" id="username" onblur="return search();" value="" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;"/>
                    <br/> <div align="left" id="searchResult" class="err" style="border:#000000; "></div></td>
                    </tr>
                           <tr>
                    <td  height="20px"><%=resource.getString("login.message.signin.password")%></td>
                    <td align="left"><input name="password" class="err" type="password" id="password" value="" onblur="return search1();" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;">
                     <div align="left" id="searchResult1" class="err" style="border:#000000; "></div>
                    </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td><td height="20px" align="center"><input id="rememberme" type="checkbox" name="rememberme"><%=resource.getString("login.message.signin.remember")%>
                        <br>


                    </td>
                    </tr>
                    <tr>
                    <td align="center" valign="bottom">
                        <input type="submit" name="button"  value="<%=resource.getString("login.button.sigin.login")%>" dir="<%=rtl%>"  onclick="return submitLogin();" /></td><td><input type="submit" name="button" value="<%=resource.getString("login.button.sigin.forgetpassword")%>" onclick="return submitForget();" />
                    </td>
                    </tr>
                        </table>
                    
                    </td></tr>



                    </table>
                    <br>

                    <span style="font-family: Tahoma;font-size:14px"><b><%=resource.getString("login.message.newlib")%></b></span><br><br>
                   &nbsp;&nbsp;&nbsp;&nbsp; <img src="images/B.jpg" alt="" style="height:20px;width:20px;"  border="0" align="top" id="Image1" style="">&nbsp;&nbsp;<b><a href="/EMS-Struts/admin/admin_registration.jsp" style="text-decoration: none;color:brown;font-family: Tahoma;font-size:13px;font-weight: bold;"><%= resource.getString("login.href.institute.registration") %></a></b>



                </td></tr>
            