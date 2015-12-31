<?php include("header.php");
?>

<?php
session_start();
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'pmsmod.xml' );
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
xmlhttp.open("GET","pmsmod.xml",false);
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
		        <a href='tms.php'  title='Go To PMS'>CHOME</a>| 
			</td>
			 <td>
                        <a href='pmsmod.php'  title='Go To MODULE OF COMPONENT'>COMPONENT MODULE</a> 
                        </td>
			 <!--td>
                        <a href='brihaspatilist.php'  title='Go To BGAS'>LIST OF INSTITUTE</a> 
                        </td-->
</tr></table>
</div>
<div id="content">
<div id ="columnC">
        <div style="width:60%; margin-top:-8px; font-size:14px;color:#333 ;line-height:160%;">
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
                echo    "<input name='filenm' type='hidden' value='pmsmod.xml'/>";
                echo "<input name='redirect' type='hidden' value='pmsmod.php'/>";
                echo    "<input name='element' type='hidden' value='COMPONENT'/>";
                echo "<br>";
                echo    "<textarea name=\"UserAddress6\" rows=\"30\" cols=\"62\">";
                $xmlDoc = new DOMDocument();
                $xmlDoc->load( 'pmsmod.xml' );
                $searchNode = $xmlDoc->getElementsByTagName( "COMPONENT" );
                foreach( $searchNode as $searchNode5 )
                {

                    $xmlMOD = $searchNode5->getElementsByTagName( "MODULE" );
                    $valueMOD = $xmlMOD->item(0)->nodeValue;
                    echo str_replace("<br>","\n", $valueMOD);
                    //echo $valueSNO." ".$valueMOD."\n";
                }
                echo"</textarea>";
                echo    "<input type='submit'value='update'>";
                }
                ?>


		 		</div>
<div id ="columnD">
<?php
                 if(! empty($_SESSION['username']) )
                {?>
<div style="width:54.6%;float:right;margin-top:-45.4%;margin-right:-6%">
 <?php
                }else{
?>
               <div style="width:59%;float:right;margin-top:-56.6%;margin-right:-10.4%">
<?php
                }
?>
<!--div style="width:54.6%;float:right;margin-top:-54.4%;margin-right:-6%"-->
<img src="images/home/pms.png" />
                                </div>
<?php include("footer.php");
?>

