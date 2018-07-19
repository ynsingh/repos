<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	//ob_start();
	echo form_open('ckeditor');
	$this->load->library('session');
	$newdata = array(
		'unspent_type'  => 'non-plan',
	);
	$this->session->set_userdata($newdata);
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
	if( $save_report)
	{
		ob_start();
	}
	$docs_path_url = realpath(BASEPATH.'/docs/BGAS');
	
      //  print_r($docs_path_url);
       // die;
	$file_list = get_filenames($docs_path_url);
	//print_r($file_list);
	$arr_len = is_array($file_list) ? count($file_list) : 1;
        //$arr_len = count($file_list);
        $file_name = Date("F d, Y").'nonplan_report'.'.txt';
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

	$count=0;
        $fund_length = count($fund);
       // $this->db->from('settings');
        //$value = $this->db->get();
        /*foreach($value->result() as $row)
        {
                $ins_name = $row->ins_name;
                $fy_start = explode("-",$row->fy_start);
                $fy_end = explode("-",$row->fy_end);
        }*/
       // $curr_year = $fy_start[0] ."-" .$fy_end[0];
        //$prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

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
			echo "<table border=0 cellpadding=5 class=\"simple-table balance-sheet-table\" width=\"25%\" align=\"right\">";
			echo "<thead><tr><th>Name Of Available File</th><th>Delete File</th></tr></thead>";
			echo "<tbody>";
        		for($i=0; $i<$arr_len; $i++)
			{
				//echo $file_list[$i];
				$exp_date=explode(",",$file_list[$i]);
				if($file_list[$i] != 'notesToAccount.txt')
				{
					if(@$exp_date[1] ==' '.Date("Y").'nonplan_report.txt')
					{
        					echo "<tr>";
                				echo "<p>";
						echo "<td>" . anchor('unspentbalance/view_file/'.$exp_date[0].'/nonplan_report', $file_list[$i]) . "</td>";
                				echo "<td>" . anchor('unspentbalance/delete/'. $exp_date[0].'/nonplan_report', 'Delete') . "</td>";
                                		echo "</p>";
                				echo "</p>";
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
	echo "(Non-Plan".$curr_year.")";
	echo "</font>";
	echo "</td>";
	echo "</tr>";

	echo "</table>";

	echo "<tr height=\"30\"></tr>";
	echo "<table border=\"1\" class=\"simple-table\" width=\"70%\">";
	echo "<thead><tr><td width=\"90\" colspan=\"2\" class=\"bold\">Sanction letter No. & Date</td><td align=\"center\" class=\"bold\">Salary</td><td align=\"center\" class=\"bold\">Pension & Pensionary Benifit</td><td align=\"center\" class=\"bold\">Non-Salary</td></tr></thead>";
	echo "<tr><td colspan=\"2\"></td><td class=\"bold\" align=\"center\">OH:36</td><td class=\"bold\" align=\"center\">OH:31</td><td class=\"bold\" align=\"center\">OH:31</td>";
	$Non_salary_total = 0;
	$salary_total = 0;
	$Pension_benefit_total = 0;
	$total = 0;
	
	$this->db->distinct();
        $this->db->select('sanc_letter_date')->from('entries')->where('sanc_type','non_plan');
        $sanc_non_plan_report = $this->db->get();
        foreach($sanc_non_plan_report->result() as $row_1)
        {
                $sanc_letter_date_non_plan = $row_1->sanc_letter_date;
                echo "<tr>";
                $this->db->distinct();
                $this->db->select('sanc_letter_no')->from('entries')->where('sanc_letter_date',$sanc_letter_date_non_plan);
                $sanc_non_plan_report1 = $this->db->get();
                foreach($sanc_non_plan_report1->result() as $row_2)
                {
                        $sanc_letter_no_non_plan = $row_2->sanc_letter_no;
                        echo "<td colspan=\"2\">";
                        echo "F.No. ".$sanc_letter_no_non_plan." dated ".date_mysql_to_php($sanc_letter_date_non_plan);
                        echo "</td>";
                }

                $this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_non_plan)->where('sanc_value','Salary OH:36');
                $sanc_non_plan_report2 = $this->db->get();
                foreach($sanc_non_plan_report2->result() as $row_3)
                {
                        $dr_total_non_plan_1 = $row_3->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_non_plan_1;
                        $salary_total = float_ops($salary_total, $dr_total_non_plan_1, '+');
                        echo "</td>";
                }

		$this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_non_plan)->where('sanc_value','Pension And Pensionary Benefit OH:31');
                $sanc_non_plan_report3 = $this->db->get();
                foreach($sanc_non_plan_report3->result() as $row_4)
                {
                        $dr_total_non_plan_2 = $row_4->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_non_plan_2;
                        $Pension_benefit_total = float_ops($Pension_benefit_total, $dr_total_non_plan_2, '+');
                        echo "</td>";
                }

                $this->db->select('dr_total')->from('entries')->where('sanc_letter_date',$sanc_letter_date_non_plan)->where('sanc_value','Non Salary OH:31');
                $sanc_non_plan_report4 = $this->db->get();
                foreach($sanc_non_plan_report4->result() as $row_5)
                {
                        $dr_total_non_plan_3 = $row_5->dr_total;
                        echo "<td align=\"right\">";
                        echo $dr_total_non_plan_3;
                        $Non_salary_total = float_ops($Non_salary_total, $dr_total_non_plan_3, '+');
                        echo "</td>";
                }
		echo "</tr>";
        }

/*
	$this->db->from('entries')->select('sanc_letter_no, sanc_letter_date, sanc_type, sanc_value, dr_total');
        $senction_ltr_details = $this->db->get();
        foreach($senction_ltr_details->result() as $rows) 
	{
        	if($rows->sanc_type == 'non_plan')
		{
			$sanc_val = $rows->sanc_value;
			echo "<tr>";
			echo "<td colspan=\"2\">";
			echo "F.No. ".$rows->sanc_letter_no."-".date_mysql_to_php($rows->sanc_letter_date);
			echo "</td>";

			if($sanc_val == 'Salary OH:36')
			{
				echo "<td align=\"right\">";
				echo $rows->dr_total;
				$salary_total = float_ops($salary_total, $rows->dr_total, '+');
				echo "</td>";
			}
			else
			{
				echo "<td align=\"right\">";
                		echo "0";
                		echo "</td>";
			}

			if($sanc_val == 'Pension And Pensionary Benefit OH:31')
			{
				echo "<td colspan=\"2\" align=\"right\">";
				echo $rows->dr_total;
				$Pension_benefit_total = float_ops($Pension_benefit_total, $rows->dr_total, '+');
				echo "</td>";
			}
			else
			{
                		echo "<td colspan=\"2\" align=\"right\">";
                		echo "0";
                		echo "</td>";
        		}

			if($sanc_val == 'Non Salary OH:31')
			{
				echo "<td align=\"right\">";
				echo $rows->dr_total;
		 		$Pension_benefit_total = float_ops($Pension_benefit_total, $rows->dr_total, '+');
				echo "</td>";
			}
			else
			{
                		echo "<td align=\"right\">";
                		echo "0";
                		echo "</td>";
        		}
			echo "</tr>";
		}
	}*/
	
	$total=$salary_total+$Pension_benefit_total+$Non_salary_total;
	$neg_balance=$total_nonplan_exp-$total+$nonplan_op_balance+$total_nonplan_Income;
	$neg_salary = 0;
	$neg_pension = 0;

	echo "<tr><td colspan=\"2\" align=\"center\" class=\"bold\">Total:</td><td align=\"right\">$salary_total</td><td align=\"right\">$Pension_benefit_total</td><td align=\"right\">$Non_salary_total</td></tr>";
	echo "</table>";

	echo "<table border=\"0\" width=\"70%\"  align=\"justify\">";
	echo "<tr height=\"10\"><tr>";

	echo "<tr align=\"justify\">";
        echo "<td width=\"70%\">";
	echo "Certified that Rs.".$total." of grants-in-aid under Non-Plan was sanctioned and released by the MHRD during the year ". $curr_year ." in favour of ".$ins_name." under the Ministry letter no.s and date given in the margin.";
	echo "</td>";
	echo "</tr>"; 
	
	echo "<tr align=\"justify\">";
	echo "<td width=\"70%\">";
	echo "With an opening balance of Rs. ".$nonplan_op_balance." towards OH-31 (Pension and pensionary Benifits) and an Internal Income of Rs ".$total_nonplan_Income.", the total Non-Plan expenditure of the Institute during the financial year ".$curr_year." was Rs ".$total_nonplan_exp.".Institute stands with a negative balance of Rs ".$neg_balance." (Rs. ".$neg_salary." towards Salary OH-36 and Rs. ".$neg_pension." towards Pension and Pensionary Benefits).";
	echo "</td>";
	echo "</tr>";

	echo "<tr align=\"justify\">";
	echo "<td width=\"70%\">";
	echo "Certified that I have satisfied myself that the conditions on which the grant-in-aid was sanctioned have been duly fulfilled and that I have exercised the following checks to see that the money was actually utilized for the purpose for which it was sanctioned.";
	echo "</td>";
	echo "</tr>";

	echo "<tr align=\"justify\">";
	echo "<td>";
	echo "Kinds of checks exercised: Annual Accounts ".$curr_year;
	echo "</td>";
	echo "</tr>";

	echo "<tr>";
	echo "<td class=\"bold\"> Dated: ".Date("F d, Y")."</td>";
	echo "</tr>";

	echo "</table>";

	echo "<br></br>";
	echo "<br></br>";
	echo "<br></br>";

	echo "<table border=\"0\"  width=\"70%\">";

	echo "<tr>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\">Deputy Registrar (F&A) </font></td>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\">Finance Officer</font><td></td></td>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\">Director</font></td><td></td>";
	echo "</tr>";

	echo "<tr>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\"> ".$ins_name."</font></td>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\">".$ins_name." </font></td><td></td>";
	echo "<td class=\"bold\" align=\"center\" colspan=\"2\"><font size=\"3\" face=\"Arial\">".$ins_name." </font></td><td></td>";
	echo "</tr>";

	echo "</table>";
	echo "</tbody>";
	echo "</table>";

	echo "<table>";
	echo "<tr>";
	if(!$print_preview)
	{
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
	if(!$make_txt)
	{
		// Get the content that is in the buffer and put it in your file //
		file_put_contents('docs/BGAS/'.Date("F d, Y").'nonplan_report'.'.txt', ob_get_contents());
	}
	if(!$save_report)
	{
		echo form_open('unspentbalance/nonplanreport/');
		echo form_submit('submit', 'Save');
		echo "&nbsp;&nbsp;&nbsp;";
	}
	echo "</tr>";
	echo "</table>";
	echo "<br>";
?>
