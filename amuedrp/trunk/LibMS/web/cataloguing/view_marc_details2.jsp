<%-- 
    Document   : view_marc_details2
    Created on : Sep 14, 2011, 3:00:46 PM
    Author     : EdRP-05
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<script>
            function back()
            {
                location.href="<%=request.getContextPath()%>/cataloguing/view_marc_repos_1.jsp";
            }
</script>
    </head>
    <body>
  <table class="datagrid">
      <html:form action="/cataloguing/updateExistMarc" method="post">
            <logic:iterate id="BiblioTemp" name="opacList">
                <tr>
                    <td>
                        <bean:write name="BiblioTemp" property="id.marctag"/>
                        <input name="marctag" type="hidden" value="<bean:write name="BiblioTemp" property="id.marctag"/>"/>
                    </td>
                    <td>
                        <logic:present name="BiblioTemp" property="indicator1">
                        <bean:write name="BiblioTemp" property="indicator1"/>
                        <input name="indicator1" type="hidden" value="<bean:write name="BiblioTemp" property="indicator1"/>"/>
                        </logic:present>
                        <logic:notPresent name="BiblioTemp" property="indicator1">
                        #
                        </logic:notPresent>
                    </td>
                    <td>
                        <logic:notPresent name="BiblioTemp" property="indicator2">
                        #
                        </logic:notPresent>
                        <logic:present name="BiblioTemp" property="indicator2">
                        <bean:write name="BiblioTemp" property="indicator2"/>
                        <input name="indicator2" type="hidden" value="<bean:write name="BiblioTemp" property="indicator2"/>"/>
                        </logic:present>
                    </td>
                    <td style="text-align: justify">
                        <logic:lessEqual value="010" name="BiblioTemp" property="id.marctag">
                        <input name="a" type="text" value="<bean:write name="BiblioTemp" property="$a"/>"/>
                        </logic:lessEqual>
                        <logic:greaterEqual value="011" name="BiblioTemp" property="id.marctag">
                            $a<input name="a" type="text" value="<bean:write name="BiblioTemp" property="$a"/>"/>
                        </logic:greaterEqual>
                        <logic:present name="BiblioTemp" property="$b">
                            $b<input type="text" name="b" value="<bean:write name="BiblioTemp" property="$b"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$c">
                            $c<input type="text" name="c" value="<bean:write name="BiblioTemp" property="$c"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$d">
                            $d<input type="text" name="d" value="<bean:write name="BiblioTemp" property="$d"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$e">
                            $e<input type="text" name="e" value="<bean:write name="BiblioTemp" property="$e"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$f">
                            $f<input type="text" name="f" value="<bean:write name="BiblioTemp" property="$f"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$g">
                            $g<input type="text" name="g" value="<bean:write name="BiblioTemp" property="$g"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$h">
                            $h<input type="text" name="h" value="<bean:write name="BiblioTemp" property="$h"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$i">
                            $i<input type="text" name="i" value="<bean:write name="BiblioTemp" property="$i"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$j">
                            $j<input type="text" name="j" value="<bean:write name="BiblioTemp" property="$j"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$k">
                            $k<input type="text" name="k" value="<bean:write name="BiblioTemp" property="$k"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$l">
                            $l<input type="text" name="l" value="<bean:write name="BiblioTemp" property="$l"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$m">
                            $m<input type="text" name="m" value="<bean:write name="BiblioTemp" property="$m"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$n">
                            $n<input type="text" name="n" value="<bean:write name="BiblioTemp" property="$n"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$o">
                            $o<input type="text" name="o" value="<bean:write name="BiblioTemp" property="$o"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$p">
                            $p<input type="text" name="p" value="<bean:write name="BiblioTemp" property="$p"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$q">
                            $q<input type="text" name="q" value="<bean:write name="BiblioTemp" property="$q"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$r">
                            $r<input type="text" name="r" value="<bean:write name="BiblioTemp" property="$r"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$s">
                            $s<input type="text" name="s" value="<bean:write name="BiblioTemp" property="$s"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$t">
                            $t<input type="text" name="t" value="<bean:write name="BiblioTemp" property="$t"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$u">
                            $u<input type="text" name="u" value="<bean:write name="BiblioTemp" property="$u"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$v">
                            $v<input type="text" name="v" value="<bean:write name="BiblioTemp" property="$v"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$w">
                            $w<input type="text" name="w" value="<bean:write name="BiblioTemp" property="$w"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$x">
                            $x<input type="text" name="x" value="<bean:write name="BiblioTemp" property="$x"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$y">
                            $y<input type="text" name="y" value="<bean:write name="BiblioTemp" property="$y"/>"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$z">
                            $z<input type="text" name="z" value="<bean:write name="BiblioTemp" property="$z"/>"/>
                        </logic:present>
                    </td>
                </tr>
            </logic:iterate>
                <html:submit>Update</html:submit>
                <html:button property="button" onclick="return back()">Back</html:button>
      </html:form>
      </table>
    </body>
</html>
