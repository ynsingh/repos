<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	
	$this->load->library('session');
	$newdata = array(
        	'unspent_type'  => 'plan',
	);
	$this->session->set_userdata($newdata);
	$plan_total = 0;
        $nonplan_total = 0;
        $plan_op_balance = 0;
        $total_plan_exp = 0;
        $total_nonplan_exp = 0;
        $nonplan_op_balance = 0;
        $total_plan_fund = 0;
        $total_nonplan_fund = 0;
        $total_plan_Income = 0;
        $total_nonplan_Income = 0;
        $unspent_balance = 0;
	//Get current and previous year...
	$this->db->from('settings');
	$value = $this->db->get();
	foreach($value->result() as $row) 
	{
		$ins_name = $row->ins_name;
        	$fy_start = explode("-",$row->fy_start);
        	$fy_end = explode("-",$row->fy_end);
	}
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';
	$docs_path_url = realpath(BASEPATH.'../docs');
	$file_list = get_filenames($docs_path_url);
	$arr_len = count($file_list);
	$file_name = Date("F d, Y").'plan_report'.'.txt';
	$i = 0;

	$count=0;
        $fund_length = count($fund);
        $this->db->from('settings');
        $value = $this->db->get();
        /*foreach($value->result() as $row)
        {
                $ins_name = $row->ins_name;
                $fy_start = explode("-",$row->fy_start);
                $fy_end = explode("-",$row->fy_end);
        }*/
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

        $this->db->select('id, sanc_type')->from('entries');
        $entry_id = $this->db->get();
        foreach($entry_id->result() as $row1_1)
        {
                $this->db->select('id, ledger_id, amount')->from('entry_items')->where('entry_id', $row1_1->id);
                $entry_details = $this->db->get();
                foreach($entry_details->result() as $row1_2)
                {
                        if($row1_1->sanc_type == 'plan')
			{
                                $plan_total = float_ops($plan_total, $row1_2->amount, '+');
			}
                        else
			{
                                $nonplan_total = float_ops($nonplan_total, $row1_2->amount, '+');
			}
                        $this->db->select('id, op_balance, code, name')->from('ledgers')->where('id', $row1_2->ledger_id);
                        $ledger_details = $this->db->get();
                        foreach($ledger_details->result() as $row2_1)
                        {
                                $led_firsttwo_digit = substr($row2_1->code, 0, 2);
                                //calculate fund total....
                                foreach($fund as $x => $x_value)
                                {
                                        if($row2_1->name == $fund[$x])
                                        {
                                                if($row1_1->sanc_type == 'plan')
						{
 							$total_plan_fund = float_ops($total_plan_fund, $row1_2->amount, '+');
						}
                                                else
						{
                                                        $total_nonplan_fund = float_ops($total_nonplan_fund, $row1_2->amount, '+');
						}
                                        }
                                }
                                //Calculate expence total........
                                if($led_firsttwo_digit == '40')
                                {
                                        if($row1_1->sanc_type == 'plan')
					{
                                                $total_plan_exp = float_ops($total_plan_exp, $row1_2->amount, '+');
					}
                                        else
					{
                                                $total_nonplan_exp = float_ops($total_nonplan_exp, $row1_2->amount, '+');
					}
                                }
                                //Calculate Income total........
                                if($led_firsttwo_digit == '30')
                                {
                                        if($row2_1->name != $fund[3])
                                        {
                                                if($row1_1->sanc_type == 'plan')
						{
                                                        $total_plan_Income = float_ops($total_plan_Income, $row1_2->amount, '+');
						}
                                                else
						{
                                                        $total_nonplan_Income = float_ops($total_nonplan_Income, $row1_2->amount, '+');
						}
                                        }
                                }
                                //Calculate opening balance..........
                                if($row1_1->sanc_type == 'plan')
				{
                                        $plan_op_balance = float_ops($plan_op_balance, $row2_1->op_balance, '+');
				}
                                else
				{
                                        $nonplan_op_balance = float_ops($nonplan_op_balance, $row2_1->op_balance, '+');
				}
                        }
                }
        }
	$plan_unspent_balance = $total_plan_Income-$total_plan_exp;
        $nonplan_unspent_balance = $total_nonplan_Income-$total_nonplan_exp;


	if(!$print_preview)
	{
		if( $make_txt)
		{
			echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"25%\" align=\"right\">";
			echo "<thead><tr><th>Name Of Available File</th><th>Delete File</th></tr></thead>";
			echo "<tbody>";
 			for($i=0; $i<$arr_len; $i++)
			{
                		$exp_date = explode(",",$file_list[$i]);
                		if($file_list[$i] != 'notesToAccount.txt' || $file_list[$i] != 'BGASInstallationdoc.pdf')
				{
                        		if(@$exp_date[1] == ' 2015plan_report.txt')
					{
                        			echo "<tr>";
                                		echo "<p>";
						echo "<td>" . anchor('unspentbalance/view_file/'.$exp_date[0].'/plan_report', $file_list[$i]) . "</td>";
                                		echo "<td>" . anchor('unspentbalance/delete/'. $exp_date[0].'/plan_report', 'Delete') . "</td>";
                                		echo "</p>";
                        			echo "</tr>";
                        		}
                		}
        		}
			echo "</tbody>";
			echo "</table>";
		}
	}
	if( $save_report)
	{
        	ob_start();
	}
	echo form_open('ckeditor');
	
	echo "<table border=\"0\" width=\"70%\" >";
	echo "<tbody>";

	echo "<tr>";
	echo "<td align=\"center\" class=\"bold\">";
	echo "<font size=\"5\">";
	echo $ins_name;
	echo "</font>";
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
	echo "<font size=\"3\">";
	echo "FORM GRF 19-A";
	echo "</font>";
	echo "</td>";
	echo "</tr>";

	echo "<tr>";
	echo "<td align=\"center\">";
	echo "(See Government of India's Decision(1) below Rule 150)";
	echo "</td>";
	echo "</tr>";

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

	echo "</table>";

	echo "<table border=\"1\" class=\"simple-table\"  width=\"70%\" align=\"left\">";
	echo "<thead><tr><td width=\"90\" colspan=\"2\" class=\"bold\">Sanction letter No. & Date</td><td colspan=\"2\" align=\"center\" class=\"bold\">General</td><td colspan=\"2\" align=\"center\" class=\"bold\">SCSP</td><td colspan=\"2\" align=\"center\" class=\"bold\">TSP</td><td colspan=\"2\" align=\"center\" class=\"bold\">NER</td></tr></thead>";
	echo "<tr><td colspan=\"2\"></td><td class=\"bold\">OH:35</td><td class=\"bold\">OH:31</td><td class=\"bold\">OH:35</td><td class=\"bold\">OH:31</td><td class=\"bold\">OH:35</td><td class=\"bold\">OH:31</td><td class=\"bold\">OH:35</td><td class=\"bold\">OH:31</td>";
	$general_total1 = '';
	$general_total2 = '';
	$scsp_total1 = '';
	$scsp_total2 = '';
	$tsp_total1 = '';
	$tsp_total2 = '';
	$ner_total1 = '';
	$ner_total2 = '';
	$OH35_total = '';
	$OH31_total = '';
	$total = '';
	$total_plan_expenditure = '';
	$income_total = '';
	
	$this->db->distinct();
	$this->db->select('sanc_letter_date')->from('entries')->where('sanc_type','plan');
	$sanc_plan_report = $this->db->get();
	foreach($sanc_plan_report->result() as $row_1)
	{
		$sanc_letter_date_plan = $row_1->sanc_letter_date;
		echo "<tr>";
		$this->db->distinct();
		$this->db->select('sanc_letter_no')->from('entries')->where('sanc_letter_date',$sanc_letter_date_plan);
		$sanc_plan_report1 = $this->db->get();
        	foreach($sanc_plan_report1->result() as $row_2)
		{
			$sanc_letter_no_plan = $row_2->sanc_letter_no;
			echo "<td colspan=\"2\">";
                        echo "F.No. ".$sanc_letter_no_plan." dated ".date_mysql_to_php($sanc_letter_date_plan);
                        echo "</td>";
		}

		$this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_plan)->where('sanc_value','General OH:35');
		$sanc_plan_report2 = $this->db->get();
		foreach($sanc_plan_report2->result() as $row_3)
                {
                        $dr_total_plan_1 = $row_3->dr_total;
               		echo "<td align=\"right\">";
                 	echo $dr_total_plan_1;
                   	$general_total1 = float_ops($general_total1, $dr_total_plan_1, '+');
                      	echo "</td>";                                                         
                }
		
		$this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_plan)->where('sanc_value','General OH:31');
                $sanc_plan_report3 = $this->db->get();
                foreach($sanc_plan_report3->result() as $row_4)
                {
                        $dr_total_plan_2 = $row_4->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_plan_2;
                        $general_total2 = float_ops($general_total2, $dr_total_plan_2, '+');
                        echo "</td>";
                }

		$this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_plan)->where('sanc_value','SCSP OH:35');
                $sanc_plan_report4 = $this->db->get();
                foreach($sanc_plan_report4->result() as $row_5)
                {
                        $dr_total_plan_3 = $row_5->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_plan_3;
                        $scsp_total1 = float_ops($scsp_total1, $dr_total_plan_3, '+');
                        echo "</td>";
                }

		$this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_plan)->where('sanc_value','SCSP OH:31');
                $sanc_plan_report5 = $this->db->get();
                foreach($sanc_plan_report5->result() as $row_6)
                {
                        $dr_total_plan_4 = $row_6->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_plan_4;
                        $scsp_total2 = float_ops($scsp_total2, $dr_total_plan_4, '+');
                        echo "</td>";
                }

		$this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_plan)->where('sanc_value','TSP OH:35');
                $sanc_plan_report6 = $this->db->get();
                foreach($sanc_plan_report6->result() as $row_7)
                {
                        $dr_total_plan_5 = $row_7->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_plan_5;
                        $tsp_total1 = float_ops($tsp_total1, $dr_total_plan_5, '+');
                        echo "</td>";
                }

		$this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_plan)->where('sanc_value','TSP OH:31');
                $sanc_plan_report7 = $this->db->get();
                foreach($sanc_plan_report7->result() as $row_8)
                {
                        $dr_total_plan_6 = $row_8->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_plan_6;
                        $tsp_total2 = float_ops($tsp_total2, $dr_total_plan_6, '+');
                        echo "</td>";
                }

		$this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_plan)->where('sanc_value','NER OH:35');
                $sanc_plan_report8 = $this->db->get();
                foreach($sanc_plan_report8->result() as $row_9)
                {
                        $dr_total_plan_7 = $row_9->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_plan_7;
                        $ner_total1 = float_ops($ner_total1, $dr_total_plan_7, '+');
                        echo "</td>";
                }

		$this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_plan)->where('sanc_value','NER OH:31');
                $sanc_plan_report9 = $this->db->get();
                foreach($sanc_plan_report9->result() as $row_10)
                {
                        $dr_total_plan_8 = $row_10->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_plan_8;
                        $ner_total2 = float_ops($ner_total2, $dr_total_plan_8, '+');
                        echo "</td>";
                }
		echo "</tr>";
	}
	echo "<tr><td colspan=\"2\" align=\"center\" class=\"bold\">Total:</td>";
        echo "<td align=\"right\">".$general_total1."</td>";
        echo "<td align=\"right\">".$general_total2."</td>";
        echo "<td align=\"right\">".$scsp_total1."</td>";
        echo "<td align=\"right\">".$scsp_total2."</td>";
        echo "<td align=\"right\">".$tsp_total1."</td>";
        echo "<td align=\"right\">".$tsp_total2."</td>";
        echo "<td align=\"right\">".$ner_total1."</td>";
        echo "<td align=\"right\">".$ner_total2."</td>";
        $total = $general_total1+$general_total2+$scsp_total1+$scsp_total2+$tsp_total1+$tsp_total2+$ner_total1+$ner_total2;
        $OH35_total = $general_total1+$scsp_total1+$tsp_total1+$ner_total1;
        $OH31_total = $general_total2+$scsp_total2+$tsp_total2+$ner_total2;
	$total_plan_expenditure = $total_plan_Income+$OH35_total+$OH31_total;
        echo "</tr>";
        echo "</table>";
			
		
 /*		echo"F.No. ".$row_1->sanc_letter_no."-".date_mysql_to_php($row_1->sanc_letter_date);
    		echo"</td>";
		$this->db->from('entries')->select('sanc_letter_no, sanc_letter_date, sanc_type, sanc_value, dr_total');
	$senction_ltr_details = $this->db->get();
	foreach($senction_ltr_details->result() as $rows) 
	{
		if($rows->sanc_type == 'plan')
		{
			$sanc_val=$rows->sanc_value;
			echo "<tr>";
			echo"<td colspan=\"2\">";
			echo"F.No. ".$rows->sanc_letter_no."-".date_mysql_to_php($rows->sanc_letter_date);
			echo"</td>";
			if($sanc_val == 'General OH:35')
			{
				echo"<td align=\"right\">";
				echo $rows->dr_total;
				$general_total1 = float_ops($general_total1, $rows->dr_total, '+');
				echo"</td>";
			}
			else
			{
				echo"<td align=\"right\">";
                		echo "0";
                		echo"</td>";
			}
			if($sanc_val == 'General OH:31')
			{
				echo"<td align=\"right\">";
				echo $rows->dr_total;
				$general_total2 = float_ops($general_total2, $rows->dr_total, '+');
				echo"</td>";
			}
			else
			{
                		echo"<td align=\"right\">";
                		echo "0";
                		echo"</td>";
        		}
			if($sanc_val == 'SCSP OH:35')
			{
				echo"<td align=\"right\">";
				echo $rows->dr_total;
				$scsp_total1 = float_ops($scsp_total1, $rows->dr_total, '+');
				echo"</td>";
			}
			else
			{
                		echo"<td align=\"right\">";
                		echo "0";
                		echo"</td>";
        		}
			if($sanc_val == 'SCSP OH:31')
			{
				echo"<td align=\"right\">";
				echo $rows->dr_total;
				$scsp_total2 = float_ops($scsp_total2, $rows->dr_total, '+');
				echo"</td>";
			}
			else
			{
                		echo"<td align=\"right\">";
                		echo "0";
                		echo"</td>";
        		}
			if($sanc_val == 'TSP OH:35')
			{
				echo"<td align=\"right\">";
				echo $rows->dr_total;
				$tsp_total1 = float_ops($tsp_total1, $rows->dr_total, '+');
				echo"</td>";
			}
			else
			{
                		echo"<td align=\"right\">";
                		echo "0";
                		echo"</td>";
        		}
			if($sanc_val == 'TSP OH:31')
			{
				echo"<td align=\"right\">";
				echo $rows->dr_total;
				$tsp_total2 = float_ops($tsp_total2, $rows->dr_total, '+');
				echo"</td>";
			}
			else
			{
                		echo"<td align=\"right\">";
                		echo "0";
                		echo"</td>";
        		}
			if(($sanc_val == 'NER OH:35')||($sanc_val == 'Plan NER OH:35'))
                        {
                                echo"<td align=\"right\">";
                                echo $rows->dr_total;
                                $ner_total1 = float_ops($ner_total1, $rows->dr_total, '+');
                                echo"</td>";
                        }
                        else
                        {
                                echo"<td align=\"right\">";
                                echo "0";
                                echo"</td>";
                        }
			if(($sanc_val == 'NER OH:31')||($sanc_val == 'Plan NER OH:31'))
                        {
                                echo"<td align=\"right\">";
                                echo $rows->dr_total;
                                $ner_total2 = float_ops($ner_total2, $rows->dr_total, '+');
                                echo"</td>";
                        }
                        else
                        {
                                echo"<td align=\"right\">";
                                echo "0";
                                echo"</td>";
                        }
			echo"</tr>";
		}
	}*/
/*
	echo "<tr><td colspan=\"2\" align=\"center\" class=\"bold\">Total:</td>";
	echo"<td align=\"right\">".$general_total1."</td>";
	echo"<td align=\"right\">".$general_total2."</td>";
	echo"<td align=\"right\">".$scsp_total1."</td>";
	echo"<td align=\"right\">".$scsp_total2."</td>";
	echo"<td align=\"right\">".$tsp_total1."</td>";
	echo"<td align=\"right\">".$tsp_total2."</td>";
	echo"<td align=\"right\">".$ner_total1."</td>";
	echo"<td align=\"right\">".$ner_total2."</td>";
	$total = $general_total1+$general_total2+$scsp_total1+$scsp_total2+$tsp_total1+$tsp_total2;
	$OH35_total=$general_total1+$scsp_total1+$tsp_total1;
	$OH31_total=$general_total2+$scsp_total2+$tsp_total2;
	echo"</tr>";
	echo "</table>";
*/
/////end(table3)
	//////////start(tabble4)
	echo "<br><br>";
	echo "<table border=\"0\" width=\"70%\"  align=\"justify\">";
	echo "<tr height=\"10\"></tr>";
	echo "<tr align=\"justify\">";
	echo "<td width=\"100%\">";
	echo "Certified that Rs.".$total." (Object Head-35 - Rs.".$OH35_total." and Object Head-31 - Rs.".$OH31_total." ) of grants-in-aid under Normal Plan sanctioned by the MHRD during the year ".$curr_year." in favour of IIT Kanpur under the Ministry letter no.s and date given in the margin.";
	echo "</td>";
	echo "</tr>"; 
	echo "<br><br>";
	echo "<tr align=\"justify\">";
	echo "<td width=\"100%\">";
	echo "The total plan expenditure made during the year was Rs.".$total_plan_expenditure." (Rs. ".$OH35_total." under Object Head - 35, Rs.".$OH31_total." under Object Head - 31 and Internal Income of Rs.".$total_plan_Income.") for planned activities of the Institute in the financial year ". $curr_year;
	echo "</td>";
	echo "</tr>";

	echo "<tr></tr>";
	echo "<tr align=\"justify\">";
	echo "<td>";
	echo "Certified that I have satisfied myself that the conditions on which the grant-in-aid was sanctioned have been duly fulfilled and that I have exercised the following checks to see that the money was actually utilized for the purpose for which it was sanctioned.";
	echo "<td>";
	echo "</tr>";
	echo "<br><br>";
	echo "<tr>";
	echo "<td>";
	echo "Kinds of checks exercised: Annual Accounts " .$curr_year." .";
	echo "</td>";
	echo "</tr>";

	echo "<tr></tr>";
	echo "<tr>";
	echo "<td class=\"bold\"> Dated: ".Date("F d, Y")."</td>";
	echo "</tr>";
	echo "</table>";
	echo "<br></br>";
	echo "<br>";
	echo "<br>";
	echo "<br>";
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
	echo "<br><br><br>";
	echo "<table>";
	echo "<tr>";
	$this->load->library('session');
	$date1 = $this->session->userdata('date1');
	$date2 = $this->session->userdata('date2');
	if(!$print_preview)
	{
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
	if(!$make_txt)
	{
		// Get the content that is in the buffer and put it in your file //
		file_put_contents('docs/'.Date("F d, Y").'plan_report'.'.txt', ob_get_contents());
	}
	if(!$save_report)
	{
		if(!$print_preview)
		{
			echo form_open('unspentbalance/planreport/');
			echo form_submit('submit', 'Save');
			echo "&nbsp;&nbsp;&nbsp;";
		}
	}
	echo "</table>";
	echo "</tr>";
?>
