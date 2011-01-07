<%@ page import="java.sql.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Mayank Saxena" content="MCA,AMU">
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<script language="javaScript" src="fulldetail.js"></script>
<!--result.css removed-->
</head>
<body>

<div id="wb_Form1" style="position: absolute; left: 60px; top: 5px;
width: 1050px; height: 460px; z-index: 3;">
<button class='buttonclass' onmousedown="fontResize('-5')">Font Size -&nbsp;-</button>
<button class='buttonclass' onmousedown="fontResize('+5')">Font Size ++</button>


<%! String title,loc,pubyr,copy,vol,ed,publ,place,isbn,accno,subtitle,subject,id,lib_id;
    ResultSet rs=null;
%>
<%
//id=request.getParameter("id");


     // String qry = "select * from document where accessionno='"+id+"'";
      rs=(ResultSet) request.getAttribute("MyResultSet");
  //    rs=MyResultSet.getMyResultSet(qry);
        if(rs!=null){
        while(rs.next())
          { 
            title=rs.getString(1);
            accno=rs.getString(3);
            subject=rs.getString(5);
            loc=rs.getString(6);
            copy=rs.getString(7);
            isbn=rs.getString(9);
            publ=rs.getString(10);
            place=rs.getString(11);
            subtitle=rs.getString(12);
            pubyr=rs.getString(15);
            vol=null;//rs.getString(16);
            ed=null;//rs.getString(17);
            lib_id = rs.getString("library_id");
 %>

<br>
<TABLE class="detailCardTable" border='0' cellspacing='0' cellpadding='0' align="left">
    <br> <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Location:</TD>
        <TD>&nbsp;</TD>
        <TD valign='top' ><B><%=loc%></B></TD>
    </TR>

    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width="10%">Subject:</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD  colspan=2 class="detailCardTD2"><%=subject%></TD>
    </TR>
       <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Title:</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=title%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>SubTitle:  </TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=subtitle%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Volume:  </TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=vol%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Edition:  </TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=ed%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Publication Place:</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=publ%>,&nbsp;<%=place%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>Publication Year: </TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=pubyr%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' class="detailCardTD1" width=10%>ISBN:</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD class="detailCardTD2" colspan=2><%=isbn%></TD>
    </TR>
    <TR><TD NOWRAP valign='top' class="detailCardTD1" width="10%">Accession no:</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD  colspan=2 class="detailCardTD2"><%=accno%><br></TD>
    </TR>
    <TR><TD NOWRAP valign='top' class="detailCardTD1" width="10%">Library Id:</TD>
        <TD valign='top' class="detailCardTD1">&nbsp; </TD>
        <TD  colspan=2 class="detailCardTD2"><%=lib_id%><br></TD>
    </TR>

</TABLE><br><br>
<%       }

}
%>


</div>
</body>
</html>