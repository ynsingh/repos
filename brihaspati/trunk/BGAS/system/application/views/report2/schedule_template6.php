<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	
    $this->db->from('settings');
    $detail = $this->db->get();
    foreach ($detail->result() as $row)
    {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
    }
    $fy_start=explode("-",$date1);
    $fy_end=explode("-",$date2);

    $curr_year = '('.$fy_start[0] ."-" .$fy_end[0] .')';
    $prev_year = '(' . ($fy_start[0]-1) ."-" . ($fy_end[0]-1) .')';

    $counter1 = (2 * $counter) + 4;
    $counter2 = $counter + 1;
	$this->load->library('reportlist1');
    $object = new Reportlist1();
	if(!($print_preview))
	{
        echo "<table border=0 class=\"simple-table balance-sheet-table\" width=\"100%\" >";
        echo "<thead><tr><th align=\"center\">Particulars</th><th colspan=\"".$counter1."\" align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";
	}else{
		echo "<table border=1 solid class=\"simple-table balance-sheet-table\" width=\"100%\" >";
        echo "<thead><tr><th align=\"center\">Particulars</th><th colspan=\"".$counter1."\" align=\"center\">Current Year<br>$curr_year</th><th align=\"center\">Previous Year<br>$prev_year</th></tr></thead>";
	}

    
    echo "<tr>";
    echo"<td></td>";
    echo"<td colspan=\"".$counter2."\" align=\"center\" >Plan</td>";
    echo"<td align=\"center\" >Total Plan</td>";
    echo"<td align=\"center\" colspan=\"".$counter."\">Non Plan</td>";
    echo"<td align=\"center\">Total Non Plan</td>";
    echo"<td align=\"center\">Total</td>";
    echo"<td align=\"center\">Total</td>";
    echo "</tr>";

    echo "<tr>";
    echo"<td></td>";
    foreach($q_result as $row){

        $grp_name = $row->name;
        if($grp_name == 'UGC Recurring Grand'){
            echo"<td colspan=\"2\" align=\"center\" >$grp_name</td>";
        }else{  
            echo"<td align=\"center\">$grp_name</td>";
        }
    }  
    echo"<td></td>";

    foreach($q_result as $row){

        $grp_name = $row->name;
         
            echo"<td align=\"center\">$grp_name</td>";
    } 
    //echo"<td></td>";
    echo"<td></td><td></td><td></td></tr>";

    echo "<tr>";
    echo"<td></td>";
    foreach($q_result as $row){

        $grp_name = $row->name;
        if($grp_name == 'UGC Recurring Grand'){
            echo"<td align=\"center\">Plan</td>";
            echo"<td align=\"center\">Specific Schemes</td>";
        }else{  
            echo"<td></td>";
        }
    }
    echo"<td></td><td></td><td></td><td></td><td></td><td></td></tr>";
    
    $this->load->library('reportlist1');
    $object = new Reportlist1(); 
    $object->schedule10($code,'view','NULL',$count);   

     echo"</table>";