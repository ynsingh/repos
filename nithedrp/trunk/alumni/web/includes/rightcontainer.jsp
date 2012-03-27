<h2>NEWS/Announsments</h2>
<%
            quer = "select * from divtext where divtype = 'news'";
            rs = stmt.executeQuery(quer);
            while (rs.next()) {
                if (!rs.getString(3).equals("")) {%>
<div class=<%=rs.getString(2)%>>
    <h3><a href=news.jsp?newsid=<%=rs.getString("divid")%>><%=rs.getString(3)%></a></h3>
    <%
                        String news1 = rs.getString("message");
                        String news2 = new String();
                        if (news1.length() > 150) {
                            news2 = news1.substring(0, 150);
                            news2 = news2.concat(" ......");
                        } else {
                            news2 = news1;
                        }
    %>

    <p><%=news2%></p>
</div>
<%
                }
            }
            if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator"))) {
%>
<a  style=" font-size:9px"id="newsEdit"><span >&#187;&#187;Add/Edit News</span></a>
<%                              }
%>