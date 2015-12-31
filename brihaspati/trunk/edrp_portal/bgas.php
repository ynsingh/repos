<?php include("header.php");
?>
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

			<a href='bgas.php'  title='Go To BGAS'>CHOME</a>|

                        </td>

                         <td>

                        <a href='cmp.php'  title='Go To MODULE OF COMPONENT'>COMPONENT MODULE</a>|

                        </td>

                         <td>

                        <a href='bgaslist.php'  title='Go To INSTITUTE LIST'>LIST OF INSTITUTE</a>


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

                echo    "<input name='filenm' type='hidden' value='bgas.xml'/>";

                echo "<input name='redirect' type='hidden' value='bgas.php'/>";

                echo    "<textarea name=\"UserAddress1\" rows=\"11\" cols=\"70\">$valueInfo </textarea>";

                echo    "<input type='submit'value='update'>";

                               }

                ?>

		</p>

		</div>

		</div>

	 	<span style="float:right;color:#333;margin-top:-110px;"> 

		<script type="text/javascript">

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

                document.write("<area shape=rect coords=14,311,119,361 alt=login href=");

                document.write(info[i].getElementsByTagName("URL")[0].childNodes[0].nodeValue);

                document.write("></map>");

                }

       

		</script>

                 </span>             

		 <p><b><i>

<?php
/*
 	$mysql_hostname = "localhost";

    	$mysql_user = "root";

    	$mysql_password = "";

    	$mysql_database = "edrp_portal";

	// Create connection

	$conn = new mysqli($mysql_hostname, $mysql_user, $mysql_password, $mysql_database);

	// Check connection

	if ($conn->connect_error) {

	    die("Connection failed: " . $conn->connect_error);

	}



	$sql = "SELECT id, comp_module, comp_Inst_name FROM comp_module WHERE comp_name='BGAS'";

	$result = $conn->query($sql);

	/*if ($result->num_rows > 0) {

	    // output data of each row

	    while($row = $result->fetch_assoc()) {

        	echo "id: " . $row["id"]. " - Name: " . $row["comp_module"]. " " . $row["com_Inst_name"]. "<br>";

    	}

	} else {

	    echo "0 resultsgtyhtolhikyklhjrt7yuihjtyjit";

	} 

	$conn->close();
*/
if( empty($_SESSION['username']) )

                        {

                        echo $valueNAME ;

                        }

                        else

                        {

                echo    "<form action=\"project.php\" method=\"post\">";

                echo    "<input name='filenm' type='hidden' value='bgas.xml'/>";

                echo    "<input name='element' type='hidden' value='BGAS'/>";

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

                document.write(" BGAS SERVER"+count);

                document.write("</a></td><td><br>");

                        }

 </script>

   <br>



 </script>
<embed src="uploads/bgas.pdf" width="700px" height="500px">
</div>
</a>
 <?php include("footer.php");
?>
      
