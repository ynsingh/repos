<%-- 
    Document   : displayMessage
    Created on : Oct 20, 2011, 12:23:13 PM
    Author     : edrp01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,chat.*,java.io.*,com.myapp.struts.utility.*,com.myapp.struts.hbm.*,com.myapp.struts.hbm.LoginDAO.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="refresh" content="5">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%! String candidate="";%>
    <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>
    <link rel="stylesheet" href="/EMS/css/page.css"/>
<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
String user=(String)session.getAttribute("username");
String instituteName=(String)session.getAttribute("institute_name");
 String contextPath = request.getContextPath();
 String role=(String)session.getAttribute("login_role");
    %>
    <body style="margin:0px 0px 0px 0px">
        <table  width="100%" class="table" style="margin:0px 0px 0px 0px;">
            <tr><td colspan="2">

                 <table align="center" style="" dir="<%=rtl%>" width="100%">

        <tr>
            <td  valign="top" colspan="2" width="100%" align="<%=align%>">

                <table  align="<%=align%>"  dir="<%=rtl%>" width="100%">
            <tr><td valign="bottom"  align="<%=align%>">
            <img src="<%=request.getContextPath()%>/images/logo.bmp" alt="banner space"  border="0" align="top" id="Image1">
            </td>
            <td style="color: maroon;font-size: 12px"><%=instituteName%><br>&nbsp; Role[<%=role%>]</td>
            <td align="right" valign="top" dir="<%=rtl%>"><span style="font-family:arial;color:brown;font-size:12px;" dir="<%=rtl%>"><b dir="<%=rtl%>">Hi [<%=user%>]&nbsp;<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;</a></b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             </td></tr>
            </table><br>
            </td>

            </tr>

        </table>




                </td></tr>
            <tr>
                <td width="80%" class="headerStyle" height="50px">

            <%     String home=AppPath.getPropertiesFilePath();
                       FileInputStream in1 = new FileInputStream(home+"chat.properties");

                 Properties pro=new Properties();
                 pro = new Properties();
		 pro.load(in1);

  String d=null;
  Enumeration em = pro.keys();
  while(em.hasMoreElements()){
  String str = (String)em.nextElement();

  if(str.equalsIgnoreCase((String)session.getAttribute("institute_id")+"&candidate&"+(String)session.getAttribute("chatter")+"&h")) {

 d=(String)pro.get(str);
      break;
  }
  }
                 in1.close();
//System.out.println(d);
if(d==null)
{
session.removeAttribute("candidate");
%>
<script>
var x=confirm("Candidate Close Chat Room. You Need to logout from chat");
if(x!=true)
{top.location.href="/EMS/chatlogout.do";
}else{
top.location.href="/EMS/chatlogout.do";
}
</script>
<%}
else{
%>
<%
String [] array=d.split("&");




%>
<i>Election Name:<%=array[0]%>&nbsp;&nbsp;Position Name: <%=array[1]%></i>
                    <%session.setAttribute("candidate",d);
}





%>



                </td><td class="headerStyle">Online Voters
       
                </td></tr>

            
        <tr>
                 


                <td>


                    <%

              
                   in1 = new FileInputStream(home+"chatlog.properties");

                 pro=new Properties();
                 pro = new Properties();
		 pro.load(in1);

                em = pro.keys();
  while(em.hasMoreElements()){
 String  str = (String)em.nextElement();

if(str.startsWith(session.getAttribute("institute_id")+"&"+session.getAttribute("chatter")+"&"+session.getAttribute("chatter")))
{%><%--<i class="txt"><%=pro.get(str)%></i><br>--%>

<%System.out.println("first");

     candidate=(String)session.getAttribute("chatter");


     //read chatlog file & get all message of voter for specific current canidate

           in1 = new FileInputStream(home+"chatlog.properties");
           pro=new Properties();
           pro = new Properties();
           pro.load(in1);
           em = pro.keys();
         while(em.hasMoreElements())
         {
            str = (String)em.nextElement();
            if(str.startsWith(session.getAttribute("institute_id")+"&"+candidate+"&"))
            {
%><i class="txt">
    <%=pro.get(str)%>
    </i><br><%
            }
        }
           in1.close();
           break;

}

 
 //System.out.println(str+candidate+"..............////////////////////"+session.getAttribute("chatter"));
   }
                 in1.close();

/*HashMap hm =(HashMap) session.getAttribute("ChatterList");
if(hm!=null){
Set set = hm.entrySet();
// Get an iterator
Iterator it = set.iterator();
// Display elements
while(it.hasNext()) {
Map.Entry me = (Map.Entry)it.next();
Chatter demo=(Chatter)me.getValue();
String room=null;
if(session.getAttribute("chatroom")!=null)
    room=(String)session.getAttribute("chatroom");
System.out.println(room+demo.getRoom());
if(room.equalsIgnoreCase(demo.getRoom()))
out.println(demo.getName()+"/"+demo.getPosition()+"/"+demo.getRoom());
}

}*/








%>
                </td>
                <td valign="top"> <%home=AppPath.getPropertiesFilePath();
                  in1 = new FileInputStream(home+"chat.properties");

                  pro=new Properties();
                  pro = new Properties();
		  pro.load(in1);

d=null;
 em = pro.keys();
  while(em.hasMoreElements()){
  String str = (String)em.nextElement();

  if(str.equalsIgnoreCase((String)session.getAttribute("institute_id")+"&candidate&"+(String)session.getAttribute("chatter")) || str.endsWith((String)session.getAttribute("chatter"))) {

if(str.startsWith((String)session.getAttribute("institute_id")+"&candidate"))
    {
//String temp=str.substring(0,str.lastIndexOf("&"));
%><font color="red">
    <%
    String pro1=str.substring(str.lastIndexOf("&")+1,str.length());
   
Login obj=(Login)LoginDAO.getLoginUserName(pro1,(String)session.getAttribute("institute_id"));
                 %>    

    
    <%=obj.getUserName()%></font><br>
<%}
    else
      {
String temp=str.substring(0,str.lastIndexOf("&"));
String t1=temp.substring(temp.lastIndexOf("&")+1,temp.length());
Login obj=(Login)LoginDAO.getLoginUserName(t1,(String)session.getAttribute("institute_id"));
if(str.equalsIgnoreCase((String)session.getAttribute("user_id"))){
%><font color="blue"><%=obj.getUserName()%></font><br>

    <%}else{%>
    <font color="cyan"><%=obj.getUserName()%></font><br>
    <%}}
 
 
 
  }


  }
                 in1.close();
%>


</td>
        </tr>
        <tr><td>  <%

        //Search Particlar Key Pattern
/*String home=System.getProperty("user.home");
                       FileInputStream in1 = new FileInputStream(home+"/chat.properties");

                 Properties pro=new Properties();
                 pro = new Properties();
				pro.load(in1);
        Enumeration em = pro.keys();


//  System.out.println("All keys of the property file : ");
//  System.out.println(pro.keySet());
//  System.out.println("All values of the property file : ");
   em = pro.keys();
  while(em.hasMoreElements()){
  String str = (String)em.nextElement();

 // if(str.startsWith((String)session.getAttribute("chatter")))
 // {  String d=(String)pro.get(str);
    //  String msg=d.substring(d.lastIndexOf("&"),d.length());
//  out.println(d);
//  }
//  }
//in1.close();
/*HashMap hm1 =(HashMap) session.getAttribute("MessageList");
if(hm1!=null){
Set set1 = hm1.entrySet();
// Get an iterator
Iterator it1 = set1.iterator();
// Display elements
while(it1.hasNext()) {
Map.Entry me1 = (Map.Entry)it1.next();
Message demo1=(Message)me1.getValue();
String room=null;
if(session.getAttribute("chatroom")!=null)
  room=(String)session.getAttribute("chatroom");
System.out.println(room+demo1.getRoom());
if(room.equalsIgnoreCase(demo1.getRoom()))
*/
//out.println(demo1.getMessage()+" "+demo1.getChatterName()+" "+demo1.getTimeStamp());

    //out.println("<tr>");
    //out.println("<td>" +
    // demo1.getChatterName()+ "</td><td> " +
    //  demo1.getMessage() + "</td><td> " +
    //  demo1.getTimeStamp() + "</td><td> " );
   // out.println("</tr>");


     out.println("<tr>");
     out.println("<td>");
   //  demo1.getChatterName()+ ": (" +demo1.getTimeStamp()
    //   + ")-> " + demo1.getMessage() + "</td> " );
    out.println("</tr>");

  

//}
//}

%></td> </tr>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     
        </table>
    </body>
</html>
