<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
       
    </head>
    <body>
      <table class="datagrid">
<%
ArrayList<String> marc=new ArrayList<String>();
marc.add("Control No");
marc.add("Control No Identifier");
marc.add("Date & Time of Entry");
marc.add("Physical Description FIXED FEILD");
marc.add("ISBN");
marc.add("ISSN");
marc.add("LANGUAGE CODE");
marc.add("GEOGRAHICAL AREA CODE");
marc.add("DEWEY DECIMAL CLASSIFICATION NUMBER");
marc.add("AUTHOR NAME/MAIN ENTRY");
marc.add("MAIN ENTRY CORPORATE NAME");
marc.add("UNIFORM TITLE");
marc.add("ABBREVIATED TITLE");
marc.add("STATEMENT RESPONSIBILITY");
marc.add("EDITION");
marc.add("COMPUTER FILE CHARACTERICTS");
marc.add("PUBLICATION DETAILS");
marc.add("PROJECTED PUBLCATION DATE");
marc.add("PHYSICAL DESCRIPTION");
marc.add("PLAYING TIME");
marc.add("CONTENT TYPE");
marc.add("SERIES STATEMENT");
marc.add("GENERAL NOTES");
marc.add("DISSERATION NOTES");
marc.add("BIBLIOGRAHIC NOTES");
marc.add("FORMATTED CONTENT NOTES");
marc.add("SUMMARY");
marc.add("LANGUAGE CODE");
marc.add("SUBJECT ADDED ENTRY PERSONAL NAME");
marc.add("PERSONAL NAME");
marc.add("ADDED ENTRY");
marc.add("PERSONAL NAME");
marc.add("UNIFORM TITLE");
marc.add("LOCATION");
marc.add("HOST NAME");
marc.add("LEADER");
 int i=0;
%>
            <tr><td colspan="4" class="headerStyle">BIBLIOGRAPHIC DETAILS IN MARC-21 FORMAT OF TITLE</td></tr>
          <%--  <logic:iterate id="BiblioTemp" name="opacList">
                <tr>
                    <td style="font-weight: bold;">
                        <bean:write name="BiblioTemp" property="id.marctag"/>&nbsp;
                    </td>
                    <td>
                        <logic:present name="BiblioTemp" property="indicator1">
                        <bean:write name="BiblioTemp" property="indicator1"/>
                        </logic:present>
                        <logic:notPresent name="BiblioTemp" property="indicator1">
                        #
                        </logic:notPresent>&nbsp;
                    </td>
                    <td>
                     &nbsp;   <logic:notPresent name="BiblioTemp" property="indicator2">
                        #
                        </logic:notPresent>
                        <logic:present name="BiblioTemp" property="indicator2">
                        <bean:write name="BiblioTemp" property="indicator2"/>
                        </logic:present>       &nbsp;
                    </td>
                    <td style="text-align: justify">
                      &nbsp;  <logic:lessEqual value="010" name="BiblioTemp" property="id.marctag">
                        <bean:write name="BiblioTemp" property="$a"/>
                        </logic:lessEqual>
                        <logic:greaterEqual value="011" name="BiblioTemp" property="id.marctag">
                        $a<bean:write name="BiblioTemp" property="$a"/>
                        </logic:greaterEqual>
                        <logic:present name="BiblioTemp" property="$b">
                        $b<bean:write name="BiblioTemp" property="$b"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$c">
                        $c<bean:write name="BiblioTemp" property="$c"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$d">
                        $d<bean:write name="BiblioTemp" property="$d"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$e">
                        $e<bean:write name="BiblioTemp" property="$e"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$f">
                        $f<bean:write name="BiblioTemp" property="$f"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$g">
                        $g<bean:write name="BiblioTemp" property="$g"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$h">
                        $h<bean:write name="BiblioTemp" property="$h"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$i">
                        $i<bean:write name="BiblioTemp" property="$i"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$j">
                        $j<bean:write name="BiblioTemp" property="$j"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$k">
                        $k<bean:write name="BiblioTemp" property="$k"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$l">
                        $l<bean:write name="BiblioTemp" property="$l"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$m">
                        $m<bean:write name="BiblioTemp" property="$m"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$n">
                        $n<bean:write name="BiblioTemp" property="$n"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$o">
                        $o<bean:write name="BiblioTemp" property="$o"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$p">
                        $p<bean:write name="BiblioTemp" property="$p"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$q">
                        $q<bean:write name="BiblioTemp" property="$q"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$r">
                        $r<bean:write name="BiblioTemp" property="$r"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$s">
                        $s<bean:write name="BiblioTemp" property="$s"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$t">
                        $t<bean:write name="BiblioTemp" property="$t"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$u">
                        $u<bean:write name="BiblioTemp" property="$u"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$v">
                        $v<bean:write name="BiblioTemp" property="$v"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$w">
                        $w<bean:write name="BiblioTemp" property="$w"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$x">
                        $x<bean:write name="BiblioTemp" property="$x"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$y">
                        $y<bean:write name="BiblioTemp" property="$y"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$z">
                        $z<bean:write name="BiblioTemp" property="$z"/>
                        </logic:present>
                    </td>
                </tr>
            </logic:iterate>
          --%>
           <logic:iterate id="BiblioTemp" name="opacList">
                <tr>
                    <td align="left" style="border:dashed 1px cyan;text-align: left;height: 20px;text-transform: uppercase" class="header1">
                        <bean:write name="BiblioTemp" property="id.marctag"/>

                    -<%=marc.get(i++)%>
                        <br/>
                        <table class="datagrid" bgcolor="white"  style="margin-left:50px;text-transform:none;"><tr><td>
                        <logic:present name="BiblioTemp" property="indicator1">
                        <bean:write name="BiblioTemp" property="indicator1"/>
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
                        </logic:present>
                    </td>
                    <td style="text-align: justify">
                    &nbsp;&nbsp;    <logic:lessEqual value="010" name="BiblioTemp" property="id.marctag">
                        <bean:write name="BiblioTemp" property="$a"/><br/>
                        </logic:lessEqual>
                        <logic:greaterEqual value="011" name="BiblioTemp" property="id.marctag">
                        $a&nbsp;<bean:write name="BiblioTemp" property="$a"/><br/>
                        </logic:greaterEqual>
                        <logic:present name="BiblioTemp" property="$b">
                        $b&nbsp;<bean:write name="BiblioTemp" property="$b"/><br/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$c">
                        $c&nbsp;<bean:write name="BiblioTemp" property="$c"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$d">
                        $d&nbsp;<bean:write name="BiblioTemp" property="$d"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$e">
                        $e&nbsp;<bean:write name="BiblioTemp" property="$e"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$f">
                        $f&nbsp;<bean:write name="BiblioTemp" property="$f"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$g">
                        $g&nbsp;<bean:write name="BiblioTemp" property="$g"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$h">
                        $h&nbsp;<bean:write name="BiblioTemp" property="$h"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$i">
                        $i&nbsp;<bean:write name="BiblioTemp" property="$i"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$j">
                        $j&nbsp;<bean:write name="BiblioTemp" property="$j"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$k">
                        $k&nbsp;<bean:write name="BiblioTemp" property="$k"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$l">
                        $l&nbsp;<bean:write name="BiblioTemp" property="$l"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$m">
                        $m&nbsp;<bean:write name="BiblioTemp" property="$m"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$n">
                        $n&nbsp;<bean:write name="BiblioTemp" property="$n"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$o">
                        $o&nbsp;<bean:write name="BiblioTemp" property="$o"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$p">
                        $p&nbsp;<bean:write name="BiblioTemp" property="$p"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$q">
                        $q&nbsp;<bean:write name="BiblioTemp" property="$q"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$r">
                        $r&nbsp;<bean:write name="BiblioTemp" property="$r"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$s">
                        $s&nbsp;<bean:write name="BiblioTemp" property="$s"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$t">
                        $t&nbsp;<bean:write name="BiblioTemp" property="$t"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$u">
                        $u&nbsp;<bean:write name="BiblioTemp" property="$u"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$v">
                        $v&nbsp;<bean:write name="BiblioTemp" property="$v"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$w">
                        $w&nbsp;<bean:write name="BiblioTemp" property="$w"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$x">
                        $x&nbsp;<bean:write name="BiblioTemp" property="$x"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$y">
                        $y&nbsp;<bean:write name="BiblioTemp" property="$y"/>
                        </logic:present>
                        <logic:present name="BiblioTemp" property="$z">
                        $z&nbsp;<bean:write name="BiblioTemp" property="$z"/>
                        </logic:present>
                    </td>
                </tr>
                        </table>
                    </td></tr>

            </logic:iterate>
        </table>
    </body>
</html>
