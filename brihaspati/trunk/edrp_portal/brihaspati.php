<?php include("header.php");
?>

<?php
$xmlDoc->load( 'brihaspati.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "BRIH" );

foreach( $searchNode as $searchNode )
{

        $xmlInfo = $searchNode->getElementsByTagName( "INFO" );
        $valueInfo = $xmlInfo->item(0)->nodeValue;
        $xmlUrl = $searchNode->getElementsByTagName( "URL" );
        $valueUrl= $xmlUrl->item(0)->nodeValue;
        $xmlANAME = $searchNode->getElementsByTagName( "ANAME" );
        $valueANAME = $xmlANAME->item(0)->nodeValue;
        $xmlIMG = $searchNode->getElementsByTagName( "IMG" );
        $valueIMG= $xmlIMG->item(0)->nodeValue;
        $xmlNAME = $searchNode->getElementsByTagName( "NAME" );
        $valueNAME= $xmlNAME->item(0)->nodeValue;
}
?>

<script>
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.open("GET","brihaspati.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var info=xmlDoc.getElementsByTagName("BRIH");
var server=xmlDoc.getElementsByTagName("SERVER");
</script>
<div id="link">

<table>

<tr>

        <script type="text/javascript">

        for (i=0;i<link.length;i++)

        { 

                document.write("<td><a href=");

                document.write(link[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue);

                document.write(">");

                document.write(link[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue);

                document.write("</a></td>");

        }

        </script>

                     <td>
			  <td>

                        <a href='brihaspati.php'   title='Go To BRIHASPATI'>CHOME</a>|

                        </td>

                         <td>

                        <a href='brihasmod.php'  title='Go To MODULELIST'>COMPONENT MODULE</a>|

                        </td>

                         <td>

                        <a href='brihaspatilist.php'  title='Go To INSTITUTELIST'>LIST OF INSTITUTE</a>

                        </td>


</tr></table>

</div>

<div id="content">
              <div style="width:67%; margin-top:-26px;">
               <h2>                <script type="text/javascript">
                for (i=0;i<x.length;i++)
                { 
                document.write("<tr><td>");
                document.write(info[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue);
                document.write("</td><td>");
                document.write(info[i].getElementsByTagName("ANAME")[0].childNodes[0].nodeValue);
                        }
       
 </script>
</h2>
		<div>
		<p>
		 <?php
                if( empty($_SESSION['username']) )
                        {
                        echo $valueInfo ;
                        }
                        else
                        {
                echo    "<form action=\"project.php\" method=\"post\">";
                echo    "<input name='filenm' type='hidden' value='brihaspati.xml'/>";
                echo "<input name='redirect' type='hidden' value='brihaspati.php'/>";
                echo    "<textarea name=\"UserAddress1\" rows=\"11\" cols=\"70\">$valueInfo </textarea>";
                echo    "<input type='submit'value='update'>";
                                }
                ?>
		</p>
                </div>
                </div>
                <span style="float:right;color:#333;margin-top:-190px;">
                <script type="text/javascript">
                        for (i=0;i<x.length;i++)
                         {               
                                document.write("<tr><td><img src=");
                                document.write(info[i].getElementsByTagName("IMG")[0].childNodes[0].nodeValue);
                                document.write("usemap=#brih></td><td>");
                                 }      
       
                </script>
                <map name="brih">
                <script type="text/javascript">
                        for (i=0;i<x.length;i++)
                          { 
                                 document.write("<area shape=rect coords=14,311,119,361 alt=login href=");
                                 document.write(info[i].getElementsByTagName("URL")[0].childNodes[0].nodeValue);
                                 document.write("></map>");
                                }
       
                </script>
                 </span>
                <p><b><i>
<?php
if( empty($_SESSION['username']) )
                        {
                        echo $valueNAME ;
                        }
                        else
                        {
                echo    "<form action=\"project.php\" method=\"post\">";
                echo    "<input name='filenm' type='hidden' value='brihaspati.xml'/>";
		echo    "<input name='element' type='hidden' value='BRIH'/>";
                echo    "<input name='UserAddress2' type='hidden' value= '$valueUrl'/>";
                echo    "<input name='UserAddress5' type='hidden' value= '$valueIMG'/>";
                echo    "<input name='UserAddress4' type='hidden' value= '$valueANAME'/>";
              echo    "<textarea name=\"UserAddress3\" rows=\"2\" cols=\"70\">$valueNAME </textarea>";
                        echo "<input type='submit' value='update'>";
                        }
                ?>

                  Login Page:-</i></b></p>
            
		<script type="text/javascript">
                for (i=0;i<server.length;i++)
                {
		var count = i+1 
              document.write("<tr><td><a href=");
                document.write(server[i].getElementsByTagName("URL")[0].childNodes[0].nodeValue);
                document.write(">");
                document.write(" BRIHASPATI SERVER"+count);
                document.write("</a></td><td><br>");
                        }
 </script>
<br>
<embed src="uploads/brihaspati.pdf" width="700px" height="500px">
   <br> <br> <br> <br> <br> <br>  
<?php include("footer.php");
?>

