<HTML>
    <HEAD>
        <TITLE> Use of Try catch in jsp</TITLE>
    </HEAD>
	<BODY>
	<table align="center" bgcolor="#E1E1FF" border="1">
        <H1>Use of Try catch in jsp</H1>
		
        <%
		String  filename="0";
try{
  filename=  ${userImageFileName} ;
}catch(Exception e){}
                out.print(""+filename);
        %>
		</table>
    </BODY>
</HTML>