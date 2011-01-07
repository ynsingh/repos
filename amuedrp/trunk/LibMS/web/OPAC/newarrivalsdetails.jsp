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
</style>
<script language="javaScript" src="fulldetail.js"></script>
</head>
<body>
<div id="wb_Form1" style="position: absolute; left: 60px; top: 5px;
width: 1050px; height: 460px; z-index: 3;">
<button class='buttonclass' onmousedown="fontResize('-5')">Font Size -&nbsp;-</button>
<button class='buttonclass' onmousedown="fontResize('+5')">Font Size ++</button>


    <%! String title,loc,pubyr,copy,publ,vol,ed,place,isbn,accno,subtitle,subject,id,lib_id;
    ResultSet rs=null;
%>

<%
      rs= (ResultSet)request.getAttribute("MyResultSet");
       rs.beforeFirst();
        while(rs.next())
          {
            title=rs.getString(1);
            accno=rs.getString(3);
            subject=rs.getString(5);
            loc=rs.getString(6).toUpperCase();
            copy=rs.getString(7);
            isbn=rs.getString(9);
            publ=rs.getString(10);
            place=rs.getString(11);
            subtitle=rs.getString(12);
            pubyr=rs.getString(14);
            //vol=rs.getString(16);
            //ed=rs.getString(17);
            lib_id=rs.getString("library_id");

 %>

<br>
<TABLE class="detailCardTable" border='0' cellapcing='0' cellpadding='0'>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Location:</TD>
        <TD>&nbsp;</TD>
        <TD valign='top' ><B><%=loc%></B></TD>
    </TR>
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
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>SubTitle  </TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=subtitle%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Vol.  </TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=vol%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Edition  </TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=ed%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Publ.Plc</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=publ%>,&nbsp;<%=place%></TD>
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

</TABLE>
<%       }

%>


</div>
</body>
</html>