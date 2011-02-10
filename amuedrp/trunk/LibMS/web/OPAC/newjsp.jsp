<%@ page import="java.sql.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <style>

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
<body>





<%! String title,author,publ_pl,pub_name,pub_yr,pages,index,callno,phy_width,loc,pubyr,copy,vol,ed,publ,place,isbn,accno,subtitle,subject,id,lib_id;
    ResultSet rs=null;
    
%>
<%



    
      rs=(ResultSet) request.getAttribute("MyResultSet");
  
        if(rs!=null){
        while(rs.next())
          { 
            title=rs.getString(6);
            subtitle=rs.getString(7);
            author=rs.getString(8);
            publ_pl=rs.getString(11);
            pub_name=rs.getString(10);
            pub_yr=rs.getString(12);
            pages=rs.getString("no_of_pages");
            index=rs.getString("index_no");
            callno=rs.getString("call_no");
            isbn=rs.getString("isbn10");
            ed=rs.getString("edition");
            lib_id = rs.getString("library_id");
            phy_width=rs.getString("physical_width");
 %>


<button  onmousedown="fontResize('-5')">Font Size -&nbsp;-</button><button  onmousedown="fontResize('+5')">Font Size ++</button>
<br>
<TABLE  border='0' class="datagrid" cellspacing='0' cellpadding='0' valign="top" width="200px">
   
   
    <TR>
        <TD NOWRAP valign='top'  width=10%>Title:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=title%></TD>
    </TR>

    <TR>
        <TD NOWRAP valign='top'  width="10%">Main Author:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2 ><%=author%></TD>
    </TR>
      
    <TR>
        <TD NOWRAP valign='top'  width=10%>Publication Place:  </TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=publ_pl%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Publisher Name:  </TD>
        <TD valign='top' >&nbsp; </TD>
        <TD colspan=2><%=pub_name%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Publishing Year:  </TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=pub_yr%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Pages:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=index%>,<%=pages%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Call No: </TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=callno%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>ISBN:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=isbn%></TD>
    </TR>
    <TR><TD NOWRAP valign='top'  width="10%">Library Id:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=lib_id%><br></TD>
    </TR>
    <tr><td colspan="2"><a href="#">View Detail</a></td></tr>
</TABLE><br><br>
<%       }

}
%>


</body>
</html>