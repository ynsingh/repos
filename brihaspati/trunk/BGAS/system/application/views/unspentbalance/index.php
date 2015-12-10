<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
$this->load->library('session');
$newdata = array(
        'unspent_type'  => 'plan',
);
$this->session->set_userdata($newdata);
//Get current and previous year...
$this->db->from('settings');
$value = $this->db->get();
foreach($value->result() as $row) {
	$ins_name=$row->ins_name;
        $fy_start=explode("-",$row->fy_start);
        $fy_end=explode("-",$row->fy_end);
}
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
	$docs_path_url= realpath(BASEPATH.'../docs');
	$file_list = get_filenames($docs_path_url);
	$arr_len=count($file_list);
	$file_name=Date("F d, Y").'plan_report'.'.txt';
	$i=0;
////table1
if(!$print_preview){
if( $make_txt){
echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"25%\" align=\"right\">";
echo "<thead><tr><th>Name Of Available File</th><th>Delete File</th></tr></thead>";
echo "<tbody>";
 for($i=0; $i<$arr_len; $i++){
                $exp_date=explode(",",$file_list[$i]);
                if($file_list[$i] != 'notesToAccount.txt' || $file_list[$i] != 'BGASInstallationdoc.pdf'){
                        if(@$exp_date[1] == ' 2015plan_report.txt'){
                        echo "<tr>";
                                echo"<p>";
				echo "<td>" . anchor('unspentbalance/view_file/'.$exp_date[0].'/plan_report', $file_list[$i]) . "</td>";
                                echo "<td>" . anchor('unspentbalance/delete/'. $exp_date[0].'/plan_report', 'Delete') . "</td>";
                                echo"</p>";
                        echo "</tr>";
                        }
                }
        }

echo "</tbody>";
echo "</table>";
}
}
if( $save_report){
        ob_start();
}
echo form_open('ckeditor');
/////end(table1)
//////start(table2)
echo "<table border=\"0\" width=\"70%\" >";
echo "<tbody>";
echo "<tr>";
	//echo "<td colspan=\"8\" align=\"center\">";
	echo "<td align=\"center\" class=\"bold\">";
	echo"<font size=\"5\">";
	echo $ins_name;
	echo"</font>";
	echo "</td>";
echo "</tr>";
echo "<tr>";
	echo "<td class=\"bold\" align=\"center\">";
	echo "<font size=\"3\" face=\"Arial\">";
	echo "Finance and Accounts Section";
	echo "</font>";
	echo "</td>";
echo "</tr>";
	echo "<tr>";
	echo "</tr>";
echo "<tr>";
	echo "<td></td>";
echo "</tr>";
echo "<tr>";
	echo "<td align=\"center\" class=\"bold\">";
	echo"<font size=\"3\">";
	echo "FORM GRF 19-A";
	echo"</font>";
	echo "</td>";
echo "</tr>";
echo "<tr>";
	echo "<td align=\"center\">";
	echo "(See Government of India's Decision(1) below Rule 150)";
	echo "</td>";
echo" </tr>";
echo "<tr>";
	echo "<td></td>";
echo "</tr>";
echo "<tr>";
	echo "<td align=\"center\" class=\"bold\">";
	echo "<font size=\"3\" face=\"Arial\">";
	echo "Form of Utilization Certificate";
	echo "</font>";
	echo "</td>";
echo "</tr>"; 
echo "<tr>";
	echo "<td align=\"center\" class=\"bold\">";
	echo "<font size=\"3\" face=\"Arial\">";
	echo "(Normal Plan ".$curr_year.")";
	echo "</font>";
	echo "</td>";
echo "</tr>";
echo"</table>";
/////////start(table3)
echo "<table border=\"1\" class=\"simple-table\"  width=\"70%\" align=\"left\">";
	echo "<thead><tr><td width=\"90\" colspan=\"2\" class=\"bold\">Sanction letter No. & Date</td><td colspan=\"2\" align=\"center\" class=\"bold\">General</td><td colspan=\"2\" align=\"center\" class=\"bold\">SCSP</td><td colspan=\"2\" align=\"center\" class=\"bold\">TSP</td></tr></thead>";
	echo "<tr><td colspan=\"2\"></td><td class=\"bold\">OH:35</td><td class=\"bold\">OH:31</td><td class=\"bold\">OH:35</td><td class=\"bold\">OH:31</td><td class=\"bold\">OH:35</td><td class=\"bold\">OH:31</td>";
	$general_total1='';
	$general_total2='';
	$scsp_total1='';
	$scsp_total2='';
	$tsp_total1='';
	$tsp_total2='';
	$OH35_total='';
	$OH31_total='';
	$total='';
	$this->db->from('entries')->select('sanc_letter_no, sanc_letter_date, sanc_type, sanc_value, dr_total');
	$senction_ltr_details = $this->db->get();
	foreach($senction_ltr_details->result() as $rows) {
	if($rows->sanc_type == 'plan'){
	$sanc_val=$rows->sanc_value;
	echo "<tr>";
		echo"<td colspan=\"2\">";
		echo"F.No. ".$rows->sanc_letter_no."-".date_mysql_to_php($rows->sanc_letter_date);
		echo"</td>";
	if($sanc_val == 'General OH:35'){
		echo"<td align=\"right\">";
		echo $rows->dr_total;
		$general_total1 = float_ops($general_total1, $rows->dr_total, '+');
		echo"</td>";
	}else{
		echo"<td align=\"right\">";
                echo "0";
                echo"</td>";

	}
	if($sanc_val == 'General OH:31'){
		echo"<td align=\"right\">";
		echo $rows->dr_total;
		$general_total2 = float_ops($general_total2, $rows->dr_total, '+');
		echo"</td>";
	}else{
                echo"<td align=\"right\">";
                echo "0";
                echo"</td>";

        }

	if($sanc_val == 'SCSP OH:35'){
		echo"<td align=\"right\">";
		echo $rows->dr_total;
		$scsp_total1 = float_ops($scsp_total1, $rows->dr_total, '+');
		echo"</td>";
	}else{
                echo"<td align=\"right\">";
                echo "0";
                echo"</td>";

        }

	if($sanc_val == 'SCSP OH:31'){
		echo"<td align=\"right\">";
		echo $rows->dr_total;
		$scsp_total2 = float_ops($scsp_total2, $rows->dr_total, '+');
		echo"</td>";
	}else{
                echo"<td align=\"right\">";
                echo "0";
                echo"</td>";

        }

	if($sanc_val == 'TSP OH:35'){
		echo"<td align=\"right\">";
		echo $rows->dr_total;
		$tsp_total1 = float_ops($tsp_total1, $rows->dr_total, '+');
		echo"</td>";
	}else{
                echo"<td align=\"right\">";
                echo "0";
                echo"</td>";

        }

	if($sanc_val == 'TSP OH:31'){
		echo"<td align=\"right\">";
		echo $rows->dr_total;
		$tsp_total2 = float_ops($tsp_total2, $rows->dr_total, '+');
		echo"</td>";
	}else{
                echo"<td align=\"right\">";
                echo "0";
                echo"</td>";

        }
	
	echo"</tr>";
	}
	}
	echo "<tr><td colspan=\"2\" align=\"center\" class=\"bold\">Total:</td>";
	echo"<td align=\"right\">".$general_total1."</td>";
	echo"<td align=\"right\">".$general_total2."</td>";
	echo"<td align=\"right\">".$scsp_total1."</td>";
	echo"<td align=\"right\">".$scsp_total2."</td>";
	echo"<td align=\"right\">".$tsp_total1."</td>";
	echo"<td align=\"right\">".$tsp_total2."</td>";
	$total = $general_total1+$general_total2+$scsp_total1+$scsp_total2+$tsp_total1+$tsp_total2;
	$OH35_total=$general_total1+$scsp_total1+$tsp_total1;
	$OH31_total=$general_total2+$scsp_total2+$tsp_total2;
	echo"</tr>";
echo "</table>";/////end(table3)
//////////start(tabble4)
echo"<br><br>";
echo "<table border=\"0\" width=\"70%\"  align=\"justify\">";
echo"<tr height=\"10\"></tr>";
echo "<tr align=\"justify\">";
	echo"<td width=\"100%\">";
	echo "Cirtified that Rs.".$total." (Object Head-35-Rs.".$OH35_total." and Object Head-31-Rs.".$OH31_total." ) of grants-in-aid under Normal Plan sanctioned by the MHRD during the year" .$curr_year."in favour of IIT Kanpur under the Ministry letter no.s and date given in the margin.";
	echo"</td>";
echo "</tr>"; 
echo "<br><br>";
echo "<tr align=\"justify\">";
	echo"<td width=\"100%\">";
	echo "The total plan expenditure made during the year was Rs.26098.49 lakh (Rs. ".$OH35_total." under Object Head - 35, Rs.".$OH31_total." under Object Head - 31 and Internal Income of Rs. 98.49 lakh) for planned activities of the Institute in the financial year ". $curr_year;
	echo"</td>";
echo "</tr>";

echo "<tr></tr>";
echo "<tr align=\"justify\">";
	echo"<td>";
	echo "Cirtified that I have satisfied myself that the conditions on which the grant-in-aid was sanctioned have been duly fulfilled and that I have exercised the following checks to see that the money was actually utilized for the purpose for which it was sanctioned.";
	echo"<td>";
echo "</tr>";
echo "<br><br>";
echo "<tr>";
	echo"<td>";
	echo "Kinds of checks exercised: Annual Accounts " .$curr_year." .";
	echo"</td>";
echo "</tr>";

echo "<tr></tr>";
echo "<tr>";
	echo "<td class=\"bold\"> Dated: ".Date("F d, Y")."</td>";
echo "</tr>";
echo "</table>";
echo"<br></br>";
echo"<br>";
echo"<br>";
echo"<br>";
//////start(table5)
echo "<table border=\"0\"  width=\"70%\" >";
echo "<tr height=\"10\"></tr>";
echo "<tr>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\">Sr. Deputy Registrar (F&A) </font></td>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\">Finance Officer</font><td></td></td>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\">Director</font></td><td></td>";
echo "</tr>";
echo "<tr>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\"> IIT Kanpur</font></td>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\"> IIT Kanpur</font></td><td></td>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\"> IIT Kanpur</font></td><td></td>";
echo "</tr>";
echo "</table>";////end(table5)
echo "</tbody>";
echo "</table>";/////end(table2)
echo"<br><br><br>";
echo"<table>";
echo"<tr>";
$this->load->library('session');
$date1 = $this->session->userdata('date1');
$date2 = $this->session->userdata('date2');
if(!$print_preview){
	echo form_submit('submit', 'Edit');
	echo form_close();
}
if(! $print_preview)
{
	echo form_open('unspentbalance/printpreview/plan_report/');
        echo form_submit('submit', 'Print');
        echo form_close();
}
?>
<?php
if(!$make_txt){
	// Get the content that is in the buffer and put it in your file //
	file_put_contents('docs/'.Date("F d, Y").'plan_report'.'.txt', ob_get_contents());
}
if(!$save_report){
	if(!$print_preview){
		echo form_open('unspentbalance/planreport/');
		echo form_submit('submit', 'Save');
		echo"&nbsp;&nbsp;&nbsp;";
	}
}
echo"</table>";
echo"</tr>";
?>
