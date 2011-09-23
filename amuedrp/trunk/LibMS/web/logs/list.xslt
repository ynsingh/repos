<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited by XMLSpy® -->
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">
  <body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
<table style="background-color:teal;color:white;padding:4px" border="1" width="100%">
<tr style="background-color:yellow;color:blue;"><td>UserName</td><td>URL</td><td>Date Time</td><td>Role</td></tr>

    <xsl:for-each select="record/userinfo">
     <tr> 
    
        <td style="font-weight:bold"><xsl:value-of select="username"/></td>
<td>         <xsl:value-of select="url"/></td>

<td style="margin-bottom:1em;font-size:10pt">
        <xsl:value-of select="dateTime"/>
</td><td style="font-style:italic">          <xsl:value-of select="role"/></td>

</tr>
    </xsl:for-each>
</table>
  </body>
</html>
