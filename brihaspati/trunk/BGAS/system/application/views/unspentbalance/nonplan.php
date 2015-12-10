<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
ob_start();
echo form_open('ckeditor');
$this->load->library('session');
$newdata = array(
	'unspent_type'  => 'non-plan',
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
if( $save_report){
ob_start();
}
$docs_path_url= realpath(BASEPATH.'../docs');
        $file_list = get_filenames($docs_path_url);
        $arr_len=count($file_list);
        $file_name=Date("F d, Y").'nonplan_report'.'.txt';
if(!$print_preview){
if( $make_txt){
echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"25%\" align=\"right\">";
echo "<thead><tr><th>Name Of Available File</th><th>Delete File</th></tr></thead>";
echo "<tbody>";
        for($i=0; $i<$arr_len; $i++){
		$exp_date=explode(",",$file_list[$i]);
		if($file_list[$i] != 'notesToAccount.txt'){
			if(@$exp_date[1] == ' 2015nonplan_report.txt'){
        		echo "<tr>";
                		echo"<p>";
				echo "<td>" . anchor('unspentbalance/view_file/'.$exp_date[0].'/nonplan_report', $file_list[$i]) . "</td>";
                		echo "<td>" . anchor('unspentbalance/delete/'. $exp_date[0].'/nonplan_report', 'Delete') . "</td>";
                                echo"</p>";
                		echo"</p>";
        		echo "</tr>";
        		}
		}
	}
echo "</tbody>";
echo "</table>";
}
}
/////

echo "<table border=\"0\"  width=\"70%\" >";
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
	echo "(Non-Plan".$curr_year.")";
	echo "</font>";
	echo "</td>";
echo "</tr>";
echo"</table>";
echo"<tr height=\"30\"></tr>";
echo "<table border=\"1\" class=\"simple-table\" width=\"70%\">";
	echo "<thead><tr><td width=\"90\" colspan=\"2\" class=\"bold\">Sanction letter No. & Date</td><td align=\"center\" class=\"bold\">Salary</td><td colspan=\"2\" align=\"center\" class=\"bold\">Pension & Pensionary Benifit</td><td align=\"center\" class=\"bold\">Non-Salary</td></tr></thead>";
	echo "<tr><td colspan=\"2\"></td><td class=\"bold\" align=\"center\">OH:36</td><td class=\"bold\" colspan=\"2\" align=\"center\">OH:31</td><td class=\"bold\" align=\"center\">OH:31</td>";
	 $Non_salary_total='';
	$salary_total='';
	$Pension_benefit_total='';
	$total='';
	$this->db->from('entries')->select('sanc_letter_no, sanc_letter_date, sanc_type, sanc_value, dr_total');
        $senction_ltr_details = $this->db->get();
        foreach($senction_ltr_details->result() as $rows) {
        if($rows->sanc_type == 'non_plan')
	{
	$sanc_val=$rows->sanc_value;
	echo "<tr>";
		echo"<td colspan=\"2\">";
		echo"F.No. ".$rows->sanc_letter_no."-".date_mysql_to_php($rows->sanc_letter_date);
		echo"</td>";
	if($sanc_val == 'Salary OH:36'){
		echo"<td align=\"right\">";
		echo $rows->dr_total;
		$salary_total = float_ops($salary_total, $rows->dr_total, '+');

		echo"</td>";
	}else{
		echo"<td align=\"right\">";
                echo "0";
                echo"</td>";

	}
	if($sanc_val == 'Pension And Pensionary Benefit OH:31'){
		echo"<td colspan=\"2\" align=\"right\">";
		echo $rows->dr_total;
		$Pension_benefit_total = float_ops($Pension_benefit_total, $rows->dr_total, '+');
		echo"</td>";
	}else{
                echo"<td colspan=\"2\" align=\"right\">";
                echo "0";
                echo"</td>";

        }

	if($sanc_val == 'Non Salary OH:31'){
		echo"<td align=\"right\">";
		echo $rows->dr_total;
		 $Pension_benefit_total = float_ops($Pension_benefit_total, $rows->dr_total, '+');
		echo"</td>";
	}else{
                echo"<td align=\"right\">";
                echo "0";
                echo"</td>";

        }
	echo"</tr>";
	}
	}
	$total=$salary_total+$Pension_benefit_total+$Non_salary_total;
	echo "<tr><td colspan=\"2\" align=\"center\" class=\"bold\">Total:</td><td></td><td></td></tr>";
echo "</table>";
echo "<table border=\"0\" width=\"70%\"  align=\"justify\">";
echo"<tr height=\"10\"><tr>";
echo "<tr align=\"justify\">";
        echo"<td width=\"70%\">";
	echo "Cirtified that Rs.14586.00 lakh of grants-in-aid under Non-Plan was sanctioned and released by the MHRD during the year ". $curr_year ." in favour of IIT Kanpur under the Ministry letter no.s and date given in the margin.";
	echo"</td>";
echo "</tr>"; 
echo "<tr align=\"justify\">";
	 echo"<td width=\"70%\">";
	echo "With an opening balance of Rs. 1323,<F12>.36 lakh towards OH-31 (Pension and pensionary Benifits) and an Internal Income of Rs.5904.88 lakh, the total Non-Plan expenditure of the Institute during the financial year ".$curr_year." was Rs.22145.16 lakh.Institute stands with a negative balance of Rs.330.92 lakh (Rs. 25.71 lakh towards Salary; OH-36 and Rs.305.21 lakh towards Pension and Pensionary Benefits).";
	 echo"</td>";
echo "</tr>";
echo "<tr align=\"justify\">";
	 echo"<td width=\"70%\">";
	echo "Cirtified that I have satisfied myself that the conditions on which the grant-in-aid was sanctioned have been duly fulfilled and that I have exercised the following checks to see that the money was actually utilized for the purpose for which it was sanctioned.";
	 echo"</td>";
echo "</tr>";
echo "<tr align=\"justify\">";
	echo"<td>";
	echo "Kinds of checks exercised: Annual Accounts ".$curr_year;
	 echo"</td>";
echo "</tr>";
echo "<tr>";
	echo "<td class=\"bold\"> Dated: </td>";
echo "</tr>";
echo "</table>";
echo"<br></br>";
echo"<br></br>";
echo"<br></br>";
echo "<table border=\"0\"  width=\"70%\">";
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
echo "</table>";
echo "</tbody>";
echo "</table>";
echo"<table>";
echo"<tr>";
if(!$print_preview){
echo form_submit('submit', 'Edit');
}
//echo form_submit('submit', 'Print');
echo form_close();
if(! $print_preview)
{
        echo form_open('unspentbalance/printpreview/nonplan_report/');
        echo form_submit('submit', 'Print');
        echo form_close();
}
?>
<?php
if(!$make_txt){
// Get the content that is in the buffer and put it in your file //
file_put_contents('docs/'.Date("F d, Y").'nonplan_report'.'.txt', ob_get_contents());
}
if(!$save_report){
echo form_open('unspentbalance/nonplanreport/');
echo form_submit('submit', 'Save');
echo"&nbsp;&nbsp;&nbsp;";
}
echo"</tr>";
echo"</table>";
echo"<br>";
?>
