<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed'); 

	echo "<table border=\"0\"  width=\"100%\" cellpadding=\"3\" >";
        echo "<tbody>";

        echo "<tr align=\"right\">";
        echo "<td colspan=3>";
        echo "No. FA/NODUES/..";
        echo "<br>";
        echo "Dated: ".Date("F d, Y");
	echo "<br><br>";
        echo "</td>";
        echo "</tr>";

	echo "<tr class=\"bold\" >";
	echo "<td colspan=3 align=center><b>";
	echo $rdata['heading'];
	echo "<br><br><br>";
        echo "</b></td>";
        echo "</tr>";

	echo "<tr>";
	echo "<td colspan=3>";
	echo "To,<br>The Director/Dean Of Student Affairs<br>".$rdata['ins_name'];
	echo "<br><br><br>";
        echo "</td>";
	echo "</tr>";

	echo "<tr>";
	echo "<td colspan=3 >";
	echo "This     is     to     certified     that     this     office     has    <b> ‘ DUES’ Rs. ".$rdata['balence']."</b> against <b> Dr/Mr.</b> ". $rdata['partynme']." <b>PF Number-</b> ". $rdata['pfno']."<b> Email-</b> ".$rdata['emailadd']." <b>Address-</b> ".$rdata['paddress'] ."  ". $rdata['ins_name'] .". This for your kind information and necessary action please.";
	echo "<br><br><br>";
        echo "</td>";
	echo "</tr>";

        echo "<tr>";
        echo "<td colspan=3 align=middle>";
        echo "With Best Wishes,";
	echo "<br><br><br>";
        echo "</td>";
	echo "</tr>";

        echo "<tr align=\"right\">";
        echo "<td colspan=3>";
        echo "Incharge <br>".$rdata['dept_name'];
        echo "</td>";
	echo "</tr>";
        echo "</table>";
?>
