<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    String regid="";
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
    regid = resource.getString("registrationid");
    System.out.println("Reg id="+regid);
    %>
    <body style="margin: 0px 0px 0px 0px;">
       <table align="center" width="100%"   dir="<%=rtl%>" >
       <tr  ><td  valign="bottom" height="10%"   colspan="2" >





    <img src="<%=request.getContextPath()%>/images/bp.PNG" alt="banner space"  border="0" align="<%=align%>" dir="<%=rtl%>" id="Image1" style="height:50px;width:200px;">
                                <br>


                            </td>
                            <td align="right" valign="bottom" >
                                <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="">
                </td>

            </tr>
            
</table><hr>

    </body>