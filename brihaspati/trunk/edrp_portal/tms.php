<?php include("header.php");
?>

<?php
session_start();
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'tms.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "TMS" );

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
xmlhttp.open("GET","tms.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var info=xmlDoc.getElementsByTagName("TMS");
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

                        <a href='tms.php'  title='Go To PMS'>CHOME</a>|

                        </td>

                         <td>

                        <a href='pmsmod.php'  title='Go To MODULE OF COMPONENT'>COMPONENT MODULE</a> 

                        </td>

                         <!--td>

                        <a href=''  title='Go To BGAS'>LIST OF INSTITUTE</a> 

                        </td-->



</tr></table>

</div>

<div id="content">
               <div style="width:67%; margin-top:-20px;">
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
                <div >
                <p>
		<?php
                if( empty($_SESSION['username']) )
                        {
                        echo $valueInfo ;
                        }
                        else
                        {
                echo    "<form action=\"project.php\" method=\"post\">";
                echo    "<input name='filenm' type='hidden' value='tms.xml'/>";
                echo "<input name='redirect' type='hidden' value='tms.php'/>";
                echo    "<textarea name=\"UserAddress1\" rows=\"11\" cols=\"70\">$valueInfo </textarea>";
                echo    "<input type='submit'value='update'>";
                                }
                ?>
                </p>
                </div>
                </div>
         <span style="float:right;color:#333;margin-top:-175px;"> <script type="text/javascript">
                for (i=0;i<x.length;i++)
                {
			document.write("<tr><td><img src=");
                document.write(info[i].getElementsByTagName("IMG")[0].childNodes[0].nodeValue);
	         document.write("usemap=#bgas></td><td>");
                        }
       
 </script>
                <map name="bgas">
                <script type="text/javascript">
                for (i=0;i<x.length;i++)
                { 
                                        document.write("<area shape=rect coords=17,278,109,306 alt=login href=");
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
                echo    "<input name='filenm' type='hidden' value='tms.xml'/>";
                echo    "<input name='element' type='hidden' value='TMS'/>";
                echo    "<input name='UserAddress2' type='hidden' value= '$valueUrl'/>";
                echo    "<input name='UserAddress5' type='hidden' value= '$valueIMG'/>";
                echo    "<input name='UserAddress4' type='hidden' value= '$valueANAME'/>";
              echo    "<textarea name=\"UserAddress3\" rows=\"2\" cols=\"70\">$valueNAME </textarea>";
                        echo "<input type='submit'value='update'>";
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
                document.write(" PMS SERVER"+count);
                document.write("</a></td><td><br>");
                        }
 </script>
<br>
</div>
<?php include("footer.php");
?>


