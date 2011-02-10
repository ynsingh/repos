<%@ page import="java.sql.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>NOTICE...</title>
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
<script language="javaScript" src="fulldetail.js"></script>

</head>
<body style="background-color:#e0e8f5;">

<button  onmousedown="fontResize('-5')">Font Size -&nbsp;-</button><button  onmousedown="fontResize('+5')">Font Size ++</button>


    <%! String title,loc,pubyr,copy,publ,vol,ed,place,isbn,accno,subtitle,subject,id,lib_id;
    ResultSet rs=null;
%>

<%
      rs= (ResultSet)request.getAttribute("MyResultSet1");
       rs.beforeFirst();
        while(rs.next())
          {
            title=rs.getString("title");
            accno=rs.getString("accessionno");
            subject=rs.getString("subject");
           
            copy=rs.getString("noofcopy");
            isbn=rs.getString("isbn");
          
            
            pubyr=rs.getString("pub_yr");
            
            lib_id=rs.getString("library_id");

 %>


<TABLE class="datagrid" border='0' style="background-color:#e0e8f5;" cellpadding='0'>
   
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Title   </TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=title%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width="10%">Subject</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD  colspan=2 class="detailCardTD2"><%=subject%></TD>
    </TR>
   
    
    
  
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Publ. Yr</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=pubyr%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>ISBN</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=isbn%></TD>
    </TR>
    <TR><TD NOWRAP valign='top' class="detailCardTD1" width="10%">&nbsp;Accn No. :</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD  colspan=2 class="detailCardTD2"><%=accno%><br></TD>
    </TR>
    <TR><TD NOWRAP valign='top' class="detailCardTD1" width="10%">&nbsp;Library Id</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD  colspan=2 class="detailCardTD2"><%=lib_id%><br></TD>
    </TR>
 <TR><TD NOWRAP valign='top' class="detailCardTD1" ><a href="#">View Detail</a></TD>
       
    </TR>
</TABLE>
<%       }

%>



</body>
</html>