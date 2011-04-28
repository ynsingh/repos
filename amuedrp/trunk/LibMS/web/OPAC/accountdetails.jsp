<%--
    Document   : Account Details.jsp
    Created on : Jun 15, 2010, 1:15:37 PM
    Author     : Mayank Saxena
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.hbm.*,com.myapp.struts.CirculationDAO.*"%>
<%@ page import="java.util.*, java.lang.*,java.text.*,java.util.Calendar"%>

<html>
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Account Details..</title>
<style type="text/css">

body
{
   background-color: #FFFFFF;
   color: #000000;
}
th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
  String align="";
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>



<%
String lastchkoutdate,no_of_chkout ,reservation_made="", fine="" ,name;
String DATE_FORMAT_NOW = "yyyy-MM-dd";

    
    CirMemberAccount cirmem=(CirMemberAccount)session.getAttribute("memberaccount");
   
     
            name=(String)session.getAttribute("mem_name");
            fine=cirmem.getFine();
           no_of_chkout=cirmem.getCurrentIssuedBook();
           reservation_made=cirmem.getReservationMade();
           String library_id = (String)session.getAttribute("memlib");
           String sub_library_id = (String)session.getAttribute("memsublib");
           String memId = (String)session.getAttribute("mem_id");
           if(memId==null)memId = (String)session.getAttribute("id");
            List<CirCheckout> lst = (List<CirCheckout>)CirculationDAO.searchCheckOutListByStatus(library_id, sub_library_id, memId,"issued");
           // session.setAttribute("CheckoutGrid", lst);
           Calendar ca1 = Calendar.getInstance();
            Calendar ca2 = Calendar.getInstance();
            Calendar ca3 = Calendar.getInstance();
/*extract month day and year from now()*/
            float fine1=0;
            if(lst!=null){
            Iterator it = lst.iterator();
            
            while(it.hasNext()){
                CirCheckout cirCheckOut = (CirCheckout)it.next();
            String date=cirCheckOut.getDueDate();
            String DocId = cirCheckOut.getDocumentId();
            String year=date.substring(0,date.indexOf("-"));


            String month=date.substring(date.indexOf("-")+1,date.indexOf("-", date.indexOf("-")+1));


            String day=date.substring(date.lastIndexOf("-")+1,date.length());


System.out.println(year+"-"+month+"-"+day);
            long day1 = ca1.get(Calendar.DATE)-Integer.parseInt(day);
            long month1 = (ca1.get(Calendar.MONTH)+1)-Integer.parseInt(month);
            long year1 = ca1.get(Calendar.YEAR)-Integer.parseInt(year);

            /**************************/
            ca2.set(Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(day));

             /*extract month day and year from now()*/
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

             date=sdf.format(cal.getTime());
             year=date.substring(0,date.indexOf("-"));


            month=date.substring(date.indexOf("-")+1,date.indexOf("-", date.indexOf("-")+1));


            day=date.substring(date.lastIndexOf("-")+1,date.length());


            ca3.set(Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(day));

            long milliseconds1 = ca2.getTimeInMillis();
            long milliseconds2 = ca3.getTimeInMillis();
            long diff = milliseconds2 - milliseconds1;
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            long diffHours = diff / (60 * 60 * 1000);
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.println("Days"+diffDays);
            
            
                BookCategory bookCat = (BookCategory)CirculationDAO.searchfineDetailsByType(library_id,sub_library_id,DocId);
                if(diffDays>0)
                    fine1 += bookCat.getFine()*diffDays;
            

            }
            }
            lastchkoutdate=cirmem.getLastchkoutdate();
            //session.setAttribute("memname",name);

         




%>


</head><body>
 

<form name="Form1" method="post" id="Form1">

    <table  align="<%=align%>" dir="<%=rtl%>" width="800px" class="datagrid" style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">

      

  <tr><td  width="800px" dir="<%=rtl%>" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="<%=align%>">
          <table>
              <tr><td width="640px" dir="<%=rtl%>" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="<%=align%>"><b>
               
		     
	&nbsp;&nbsp;
                <a href="accountdetails.jsp"  style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.home")%></a>&nbsp;|&nbsp;
            <a href="./OpacLib.do?name=newdemand"  style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
    <%--|&nbsp;<a href="./OpacLib.do?name=reservationrequest"  style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.reservationrequest")%></a>--%>

            


          </b>
                  </td><td align="<%=align%>" dir="<%=rtl%>" style="color:white;font-family:Tahoma;font-size:12px"><%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%>&nbsp;<b>|</b>&nbsp;<a href="home.do"  style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.logout")%></a></td></tr></table>
        </td></tr>
  
    

    <tr><td height="300px" valign="top" dir="<%=rtl%>" class="btn1" align="<%=align%>" colspan="2"><br><br>
            <table width="500px" class="btn1">
                <tr><td align="center" dir="<%=rtl%>" colspan="2" class="btn1" >
                        <b>  <%=resource.getString("opac.accountdetails.accountdetails")%></b><br><br>
        </td></tr>
                <tr><td dir="<%=rtl%>" align="<%=align%>"><b><%=resource.getString("opac.accountdetails.finedue")%></b></td><td dir="<%=rtl%>" align="<%=align%>"><input type="text" id="TXTFINE" name="TXTFINE" value="<%=fine%>" disabled="disabled"><a href="<%=request.getContextPath()%>/opac/cir_memfine_details.do" style="text-decoration: none;color:blue"><b>&nbsp;<%=resource.getString("opac.accountdetails.details")%></b></a></td></tr>
                <tr><td dir="<%=rtl%>" align="<%=align%>"><b><%=resource.getString("opac.accountdetails.checkouts")%></b></td><td dir="<%=rtl%>" align="<%=align%>"><input type="text" id="TXTCHECKOUT"  name="TXTCHECKOUT" value="<%=no_of_chkout%>" disabled="disabled"><a href="<%=request.getContextPath()%>/opac/cir_memcheckout_details.do" style="text-decoration: none;color:blue"><b>&nbsp;<%=resource.getString("opac.accountdetails.details")%></b></a></td></tr>
               <%-- <tr><td dir="<%=rtl%>" align="<%=align%>"><b><%=resource.getString("opac.accountdetails.reservation")%></b></td><td dir="<%=rtl%>" align="<%=align%>"><input type="text" id="TXTRESERVATION" name="TXTRESERVATION" value="<%=reservation_made%>" disabled="disabled"><a href="view_reservation.jsp" style="text-decoration: none;color:blue"><b>&nbsp;<%=resource.getString("opac.accountdetails.details")%></b></a></td></tr>--%>
                <tr><td dir="<%=rtl%>" align="<%=align%>"><b><%=resource.getString("opac.accountdetails.lastcheckoutdate")%></b></td><td dir="<%=rtl%>" align="<%=align%>"><input type="text" id="TXTCHKDATE" name="TXTCHKDATE" value="<%=lastchkoutdate%>" disabled="disabled"></td></tr>
                <tr><td dir="<%=rtl%>" align="<%=align%>"><b>Probable <%=resource.getString("opac.accountdetails.finedue")%></b></td><td dir="<%=rtl%>" align="<%=align%>"><input type="text" id="TXTProbFine" name="TXTPROBFINE" value="<%=fine1%>" disabled="disabled"><a href="view_checkoutfinedetails.jsp" style="text-decoration: none;color:blue"><b>&nbsp;<%=resource.getString("opac.accountdetails.details")%></b></a></td></tr>
                <tr><td dir="<%=rtl%>" colspan="2" align="<%=align%>"><br><br><br>
            <%
    String message=(String)request.getAttribute("msg");
    String type = (String)session.getAttribute("type");
    if(message!=null){
       %> <br>
	           <TABLE style="background-color: #E3E4FA;"
                    border="1" align="center">
		      <tr><th dir="<%=rtl%>"><%=message%></th></tr>
		   </TABLE>

   <% }else
        message="";
    if(type!=null){
    %>
    <script type="text/javascript">
        var check=confirm("Do you want to continue with your inbox now!");
        //alert(check);
        if(!check){
            <%session.removeAttribute("mem_id"); %>
            top.location = "<%=request.getContextPath()%>/OPAC/OPACmain1.jsp";}
    </script><%
session.removeAttribute("type");
               }%>
        </td></tr>

            </table>



        </td></tr>
    

</table>

  
    


</form>
 


</body>
</html>