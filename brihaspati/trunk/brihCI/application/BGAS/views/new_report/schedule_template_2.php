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
	
	$data = array();
	$group_id = $this->Group_model->get_id('Designated-Earmarked/Endowment Funds');
        $this->db->select('name')->from('groups')->where('parent_id',$group_id);
        $query = $this->db->get();
        $counter = $query->num_rows();
        $q_result = $query->result();

        $data['q_result'] = $q_result;
        $data['counter'] = $counter;

	//$counter = "";
	//$counter = $object->counter1;
	$counter1 = $counter;
    	$counter2 = $counter + 1;
	echo "<table border=0 class=\"simple-table balance-sheet-table\">";
        echo "<thead><tr><th align=\"center\">Particulars</th><th align=\"center\" colspan=\"".$counter."\">FUND WISE BREAKUP</th><th align=\"center\" colspan=\"2\">TOTAL</th></tr></thead>";
	
	echo "<tr>";
        echo"<td></td>";

	 foreach($q_result as $row)
         {
         	$grp_name = $row->name;
                echo"<td align=\"center\">$grp_name</td>";
         } 
    	echo"<td align=\"center\">Current Year</td>";
    	echo"<td align=\"center\">Previous Year</td>";
    	echo "</tr>";
	echo "<tr>";
	echo "<td class=\"bold\"> A. </td>";
	echo "</tr>";
	$this->load->library('reportlist1');
        $object = new Reportlist1();
        $object->designated_fundA('1003','view','NULL',2);
	$net_value = $object->net_total1;
	$prev_amount = $object->prev_total;
	echo "<td align=\"right\">";
        echo "<strong>" . convert_amount_dc($net_value) . "</strong>";
        echo "</td>";
	
	echo "<td align=\"right\">";
        echo "<strong>" . convert_amount_dc($prev_amount) . "</strong>";
        echo "</td>";
        echo "</tr>";

	echo "<tr>";
        echo "<td class=\"bold\"> B. </td>";
        echo "</tr>";
	echo "<tr>";
	echo "<td>";
        echo "Utilisation/Expenditure towards objectives of funds";
	echo "</td>";
        echo "</tr>";
	$object = new Reportlist1();
        $object->designated_fundB('1003','view','NULL',2);
	$net_value3 = $object->net_total3;
	$prev_amount = $object->prev_amount;
        echo "<td align=\"right\">";
        echo "<strong>" . convert_amount_dc($net_value3) . "</strong>";
        echo "</td>";

        echo "<td align=\"right\">";
        echo "<strong>" . convert_amount_dc($prev_amount) . "</strong>";
        echo "</td>";
        echo "</tr>";

	echo "</table>";
?>
