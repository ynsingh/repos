<?php include("header.php");
?>

<?php
session_start();
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'emsmod.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "COMPONENT" );

foreach( $searchNode as $searchNode )
{

        $xmlmod = $searchNode->getElementsByTagName( "MODULE" );
        $valuemod= $xmlmod->item(0)->nodeValue;
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
xmlhttp.open("GET","emsmod.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var mod=xmlDoc.getElementsByTagName("COMPONENT");

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
		        <a href='ems.php'  title='Go To EMS'>CHOME</a> | 
			</td>
			 <td>
                        <a href='emsmod.php'  title='Go To MODULE OF COMPONENT'>COMPONENT MODULE</a>| 
                        </td>
			 <td>
                        <a href='emsinstlist.php'  title='Go To INSTITUTE LIST'>LIST OF INSTITUTE</a> 
                        </td>
</tr></table>
</div>
<div id="content">
<div id ="columnC">
        <div style="width:80%; margin-top:-15px;font-size:14px;color:#333; line-height:160%;">
                     <?php
                 if( empty($_SESSION['username']) )
                {?>


                               <script type="text/javascript">
                for (i=0;i<mod.length;i++)
                { 
                document.write("<tr>");
                document.write("<td>");
                document.write(mod[i].getElementsByTagName("MODULE")[0].childNodes[0].nodeValue);
                document.write("<br/></td><tr>");
                }
       
 </script>
               <?php
                }else{
                echo    "<form action=\"project.php\" method=\"post\">";
                echo    "<input name='filenm' type='hidden' value='emsmod.xml'/>";
                echo "<input name='redirect' type='hidden' value='emsmod.php'/>";
                echo    "<input name='element' type='hidden' value='COMPONENT'/>";
                echo "<br>";
                echo    "<textarea name=\"UserAddress6\" rows=\"30\" cols=\"60\">";
                $xmlDoc = new DOMDocument();
                $xmlDoc->load( 'emsmod.xml' );
                $searchNode = $xmlDoc->getElementsByTagName( "COMPONENT" );
                foreach( $searchNode as $searchNode4 )
                {

                    $xmlMOD = $searchNode4->getElementsByTagName( "MODULE" );
                    $valueMOD = $xmlMOD->item(0)->nodeValue;
                    //echo $valueSNO." ".$valueMOD."\n";
                    echo str_replace("<br>","\n", $valueMOD);
                }
                echo"</textarea>";
                echo    "<input type='submit'value='update'>";
                }
                ?>

		 		</div>
<div id ="columnD">
<!--div style="width:52.5%;float:right;margin-top:-37.9%;margin-right:-6%"-->
<?php
                 if(! empty($_SESSION['username']) )
                {?>
<div style="width:56%;float:right;margin-top:-45.5%;margin-right:-9.5%">
 <?php
                }else{
?>
               <div style="width:52.5%;float:right;margin-top:-55.6%;margin-right:-6%">
<?php
                }
?>

<img src="images/home/ems.png" />

</div>	 	
	<?php include("footer.php");
?>
	
