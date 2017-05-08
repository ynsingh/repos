<?php

        setlocale(LC_MONETARY, 'en_IN');
        $this->load->library('reportlist1');
        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
        $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

        $net_dr = 0.00;
        $net_opening_bal = 0.00;
        $net_cr = 0.00;
        $net_total = 0.00;
        $net_current_year = 0.00;
        $net_previous_year = 0.00;
        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"98%\">";
        echo "<thead><tr><th></th><th align=\"center\" colspan=\"5\">GROSS BLOCK</th><th align=\"center\" colspan=\"4\">DEPRECIATION FOR THE YEAR</th><th align=\"center\" colspan=\"2\">NET BLOCK</th></tr></thead>";
	 $this->db->select('name,code,id')->from('ledgers')->where('group_id',152);
         $ledger_detail = $this->db->get();
         $ledger_result = $ledger_detail->result();
         foreach($ledger_result as $row)
         {
         	$ledg_id =$row->id;
                $ledg_name = $row->name;
		//if($ledg_name == 'Patents and Copyrights (Patents Granted)')
                //echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/new_sub_schedule/' . $row->id . '/' . $row->name, $row->name, array('title' => $row->name, 'style' => 'color:#000000'));

         }

        echo "<tr>";
                echo "<td>S. No.</td>";
                echo "<td>Assets Heads</td>";
                echo "<td>Op Balance 01.04....</td>";
                echo "<td>Addition</td>";
                echo "<td>Deductions</td>";
                echo "<td>CI Balance</td>";
                echo "<td>Dep Opening <br> Balance </td>";
                echo "<td>Depreciation <br> for the Year</td>";
		 echo "<td> Deductions/ <br> Adjustment</td>";
                echo "<td> Total <br> Depreciation</td>";
                echo "<td> 31.03..... </td>";
                echo "<td> 31.03..... </td>";
        echo "</tr>";
	$object = new Reportlist1();
	$object->FixedAsset_C('2001',4,"view",'NULL');
	echo "</table>";
?>
