<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');

	echo form_open('ckeditor');
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
	$this->load->library('session');
	$this->load->model('Ledger_model');
	$newdata = array(
        	'unspent_type'  => 'summary',
	);
	$this->session->set_userdata($newdata);
	if($save_report)
	{
		ob_start();
	}
	$docs_path_url = realpath(BASEPATH.'../docs/BGAS');
        $file_list = get_filenames($docs_path_url);
        $arr_len = count($file_list);
       // $file_name = Date("F d, Y").'summary_report'.'.txt';
        $file_name = 'summary_report'.Date("Ymd").'.txt';
        $i=0;
	$count=0;
	$fund_length = count($fund);

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

	$change_date = $fy_start[0];
	$change_date1 = substr($change_date,-2);
	$fy_st_art = explode(" ",$fy_start[2]);
	$fy_st_art1 = $fy_st_art[0].".".$fy_start[1].".".$change_date1;

	$this->db->select('id, sanc_type')->from('entries');
	$entry_id = $this->db->get();
	foreach($entry_id->result() as $row)
	{
		$this->db->select('id, ledger_id, amount')->from('entry_items')->where('entry_id', $row->id);
		$entry_details = $this->db->get();
		foreach($entry_details->result() as $row1)
		{
			if($row->sanc_type == 'plan')
				$plan_total = float_ops($plan_total, $row1->amount, '+');
			else	
				$nonplan_total = float_ops($nonplan_total, $row1->amount, '+');
			$this->db->select('id, op_balance, code, name')->from('ledgers')->where('id', $row1->ledger_id);
			$ledger_details = $this->db->get();
			foreach($ledger_details->result() as $row2)
			{
				$led_firsttwo_digit = substr($row2->code, 0, 2);
				//calculate fund total....
			 	foreach($fund as $x => $x_value) 
				{
					if($row2->name == $fund[$x])
					{
						if($row->sanc_type == 'plan')
							$total_plan_fund = float_ops($total_plan_fund, $row1->amount, '+');
						else
							$total_nonplan_fund = float_ops($total_nonplan_fund, $row1->amount, '+');
					}
				}
				//Calculate expence total........
				if($led_firsttwo_digit == '40')
				{
					if($row->sanc_type == 'plan')
						$total_plan_exp = float_ops($total_plan_exp, $row1->amount, '+');
					else
						$total_nonplan_exp = float_ops($total_nonplan_exp, $row1->amount, '+');
				}
				//Calculate Income total........
                        	if($led_firsttwo_digit == '30')
                        	{
					if($row2->name != $fund[3])
					{
                                		if($row->sanc_type == 'plan')
                                			$total_plan_Income = float_ops($total_plan_Income, $row1->amount, '+');
                                		else
                                			$total_nonplan_Income = float_ops($total_nonplan_Income, $row1->amount, '+');
					}
                        	}
				//Calculate opening balance..........
				if($row->sanc_type == 'plan')
					$plan_op_balance = float_ops($plan_op_balance, $row2->op_balance, '+');
                        	else    
                                	$nonplan_op_balance = float_ops($nonplan_op_balance, $row2->op_balance, '+');
			}
		}
	}
	$total_plan_fund = $total_plan_fund+$plan_op_balance+$plan_total+$total_plan_Income;
	$total_nonplan_fund = $total_nonplan_fund+$nonplan_op_balance+$nonplan_total+$total_nonplan_Income;
	//$plan_unspent_balance = $total_plan_Income-$total_plan_exp;
	//$nonplan_unspent_balance = $total_nonplan_Income-$total_nonplan_exp;
	$plan_unspent_balance = $total_plan_fund-$total_plan_exp;
	$nonplan_unspent_balance = $total_nonplan_fund-$total_nonplan_exp;
	if(!$print_preview)
	{
		if( $make_txt)
		{
			echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"25%\" align=\"right\">";
			echo "<thead><tr><th>Name Of Available File</th><th>Delete File</th></tr></thead>";
			echo "<tbody>";
        		for($i=0; $i<$arr_len; $i++)
			{
				//echo $file_list[$i];
                	//	$exp_date = explode(",",$file_list[$i]);
                		if($file_list[$i] != 'notesToAccount.txt')
				{
					if(substr($file_list[$i],0,14) == 'summary_report')
                        		//if(@$exp_date[1] == ' '.Date("Y").'summary_report.txt')
					{
                        			echo "<tr>";
                                		echo "<p>";
                                		echo "<td>" . anchor('unspentbalance/view_file/'.substr($file_list[$i],0,14).'/'.substr($file_list[$i],14,26), $file_list[$i]) . "</td>";
                                		//echo "<td>" . anchor('unspentbalance/view_file/'.$file_list[$i], $file_list[$i]) . "</td>";
                                		//echo "<td>" . anchor('unspentbalance/view_file/'.$exp_date[0].'/summary_report', $file_list[$i]) . "</td>";
                                		echo "<td>" . anchor('unspentbalance/delete/'.$file_list[$i], 'Delete') . "</td>";
                                		//echo "<td>" . anchor('unspentbalance/delete/'. $exp_date[0].'/summary_report', 'Delete') . "</td>";
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

	echo "<table border=\"0\"  width=\"70%\" cellpadding=\"3\" >";
	echo "<tbody>";
	
	echo "<tr>";
	echo "<td>";
	$this->db->select('id, name, ins_name,address')->from('settings');
      	$ins_id = $this->db->get();
     	foreach( $ins_id->result() as $row)
      	{
		$ins_name = $row->ins_name;
		$ins_address = $row->address;
            	$row1 = $row->name;
      	}
      	$this->upload_path = realpath(BASEPATH.'../uploads/BGAS/logo');
     	$file_list = get_filenames($this->upload_path);
     	if ($file_list)
     	{
        	foreach ($file_list as $row2)
            	{
			$ext = substr(strrchr($row2, '.'), 1);
                        $my_values = explode('.',$row2);
                        if($my_values[0] == $row1)
                        {
                               // echo "<td align=\"center\">";
                                echo img(array('src' => base_url() . "uploads/BGAS/logo/" . $row1.'.'.$ext));
                        }
                        $newrep_lace = str_replace('_', ' ', $my_values[0]);
                        if(($newrep_lace == $row1) && ($my_values[0] != $row1))
                        {
                               // echo "<td align=\"center\">";
                                echo img(array('src' => base_url() . "uploads/BGAS/logo/" . $my_values[0].'.'.$ext));
                        }

             		/*$ext = substr(strrchr($row2, '.'), 1);
                 	$my_values = explode('.',$row2);
                    	if($my_values[0] == $row1)
                     	{
                    		echo img(array('src' => base_url() . "uploads/logo/" . $row1.'.'.$ext));
                     	}*/
            	}
  	}
	echo "</td>";
	echo "<td  class=\"bold\">";
	echo "<font size=\"5\">";
	echo $ins_name;
	echo "</font>";
	echo "</td>";
	echo "<td>";
	//echo"fruhehfguyrthjsdgfuergfuergfurerguerjhfgu89pjnf";
	echo "</td>";
	echo "</tr>";

	echo "<tr>";
	echo "<td></td>";
	echo "<td></td>";
	echo "<td>";
	echo "Post Office:";
	echo " ";
	//echo "I.I.T Kanpur - 208 016 (India)";
	echo "$ins_address";
	echo "</td>";
	echo "</tr>";

	echo "<tr></tr>";

	echo "<tr></tr>";

	echo "<tr align=\"left\">";
	echo "<td class=\"bold\" >";
	echo "<font size=\"4\">";
	//echo"Munish Malik";
	echo "......................................";
	echo "</font>";
	echo "<br>";
	echo "Finance Officer and Head, Finance & Accounts";
	echo "</td>";
	echo "<td></td>";
	echo "<td>";
	//echo "No. IITK/FIN/BUDGET/2014-2015/";
	echo "No. .............................";
	echo "<br>";
	echo "Dated: ".Date("F d, Y");
	echo "</td>";
	echo "</tr>";

	echo "<tr></tr>";
	echo "<tr></tr>";
	echo "<tr></tr>";

	echo "<tr class=\"bold\">";
	echo "<td>";
	//echo"Shri Pratap Singh";
	echo "..........................................";
	echo "<br>";
	echo "Deputy Secretary (Finance)";
	echo "<br>";
	echo "Ministry Of Human Resources Development";
	echo "<br>";
	echo "Department Of Higher Education ";
	echo "<br>";
	echo "Integrated Finance Division";
	echo "<br>";
	echo "Sastri Bhawan";	
	echo "<br>";
	echo "New Delhi - 110 115";echo"<br>";
	echo "</td>";
	echo "<br>";
	echo "</tr>";

	echo "<tr></tr>";
	echo "<tr></tr>";
	echo "<tr></tr>";
	echo "</table>";
	
	echo "<table>";
	
	echo "<tr width=\"100%\">";
	echo "<td class=\"bold\" width=\"100%\">";
	echo "Subject:  Report on Unspent Balances lying with the Central Universities / Autonomous Bodies as on ".Date("F d, Y")." both under Plan and Non-Plan</td>";
	echo "</tr>";

	echo "<tr></tr>";
	echo "<tr></tr>";

	echo "<tr>";
	echo "<td>";
	echo "Dear Sir,";
	echo "</td>";
	echo "</tr>";

	echo "<tr></tr>";
	echo "<tr></tr>";
	echo "<tr></tr>";
	echo "<tr></tr>";

	echo "<tr>";
	echo "<td>";
	echo "This has reference to your letter no. .................................................. date ..................... on the above mentioned subject. The balanace as on ".Date("F d, Y")." are given below:</td>";
	echo "</tr>";

	echo "<tr></tr>";
	echo "<tr></tr>";

	echo "<tr>";
	echo "<table border=\"1\" class=\"simple-table\" width=\"70%\">";

        echo "<thead><tr><td width=\"90\" class=\"bold\">Head </td><td align=\"center\" class=\"bold\">opening Balance as on ".$fy_st_art1."</td><td align=\"center\" class=\"bold\">Grant recieved during the year(including any spl. Grant, one time grant etc.)</td><td align=\"center\" class=\"bold\">Internal Income of the Institute</td><td align=\"center\" class=\"bold\">Total Funds available during ".$curr_year." (1+2+3)</td><td align=\"center\" class=\"bold\">Actual Exp. Up-to ".Date("F d, Y")."</td><td align=\"center\" class=\"bold\">Unspent balance as on ".Date("F d, Y")." (4-5)</td></tr></thead>";
echo "<tr>";
	echo "<td></td><td class=\"bold\">1</td><td class=\"bold\">2</td><td class=\"bold\">3</td><td class=\"bold\">4</td><td class=\"bold\">5</td><td class=\"bold\">6</td>";
	echo "</tr>";

	echo "<tr>";
	echo "<td class=\"bold\">Plan</td><td class=\"bold\">".$plan_op_balance."</td><td>".$plan_total."</td><td>".$total_plan_Income."</td><td>".$total_plan_fund."</td><td>".$total_plan_exp."</td><td>".$plan_unspent_balance."</td>";
	echo "</tr>";

	echo "<tr>";
	echo "<td class=\"bold\">Non-Plan</td><td>".$nonplan_op_balance."</td><td>".$nonplan_total."</td><td>".$total_nonplan_Income."</td><td>".$total_nonplan_fund."</td><td>".$total_nonplan_exp."</td><td>".$nonplan_unspent_balance."</td>";
	echo "</tr>"; 

	echo "</table>";
	echo "</tr>";

	echo "<br><br>";
	echo "<br>";

	echo "<tr>";
	echo "With Best Wishes,";
	echo "</tr>";

	echo "</tbody>";
	echo "</table>";

	echo "<br><br>";
	echo "<br>";
	echo "<br>";
	echo "<br><br>";

	echo "<table>";
	echo "<tr>";
	if(!$print_preview)
	{
		echo form_submit('submit', 'Edit');
	}
	echo form_close();
	if(! $print_preview)
	{
        	echo form_open('unspentbalance/printpreview/summary_report/');
        	echo form_submit('submit', 'Print');
        	echo form_close();
	}
?>
<?php
	if(!$make_txt)
	{
		// Get the content that is in the buffer and put it in your file //
		//file_put_contents('docs/BGAS/'.Date("F d, Y").'summary_report'.'.txt', ob_get_contents());
		file_put_contents('docs/BGAS/'.'summary_report'.Date("Ymd").'.txt', ob_get_contents());
	}
	if(!$print_preview)
	{
		if(!$save_report)
		{
			echo form_open('unspentbalance/summaryreport/');
			echo form_submit('submit', 'Save');
			echo "&nbsp;&nbsp;&nbsp;";
			echo "</tr>";
			echo "</table>";
		}
	}

