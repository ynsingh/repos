<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printreport.css">
<div id="report-main">
<div id = "top">
	<div id="logo">
	<div id="logo1">
	<?php
		$this->db->select('id, name')->from('settings');
	    	$ins_id = $this->db->get();
	    	foreach( $ins_id->result() as $row)
	    	{
	       		$row1 = $row->name;
	    	}
	    	$this->upload_path= realpath(BASEPATH.'../uploads/logo');
	    	$file_list = get_filenames($this->upload_path);
		if ($file_list)
        	{
                	foreach ($file_list as $row2)
                	{
                        	$ext = substr(strrchr($row2, '.'), 1);
                        	$my_values = explode('.',$row2);
                        	if($my_values[0] == $row1)
                        	{
                                	echo "<td align=\"center\">";
                                	echo img(array('src' => base_url() . "uploads/logo/" . $row1.'.'.$ext));
                        	}
                        	$newrep_lace = str_replace('_', ' ', $my_values[0]);
                        	if(($newrep_lace == $row1) && ($my_values[0] != $row1))
                        	{
                                	echo "<td align=\"center\">";
                                	echo img(array('src' => base_url() . "uploads/logo/" . $my_values[0].'.'.$ext));
                        	}
                	}
        	}

	    //if ($file_list)
	    //{
		    //foreach ($file_list as $row2)
		    //{
		        //$ext = substr(strrchr($row2, '.'), 1);
		        //$my_values = explode('.',$row2);
		        //if($my_values[0] == $row1)
		        //{
		        	//echo img(array('src' => base_url() . "uploads/logo/" . $row1.'.'.$ext));
		        //}
		    //}
	    //}
	    echo "<br>";
	    echo $this->config->item('account_name');
		?>
	</div>
	</div>
	<div id="info1">
		<?php
		$curr_date = date_today_php();
		$date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');
		$start_date = $this->session->userdata('startdate');
		$end_date = $this->session->userdata('enddate');
		$from_date = '';
		$to_date = '';
		if($date1 == '' && $date2 == '')
		{
			$from_date = $start_date;
			$to_date = $end_date;
		}
		else {
			$from_date = $date1;
			$to_date = $date2;
		}

		if($title == 'Income and Expenditure Statement'){
		  	echo "<p class=\"bold\">"  . $this->config->item('account_ins_name')."</p>";
		  	echo "<p class=\"bold\">".$this->config->item('account_address') . "</p>";
		  	echo "<p class=\"bold\">".$title . "</p>";
	        	echo "<p>". "For the period " . $from_date . " to " . $to_date ."</p>";
		}else{
			echo "<p align=\"center\" class=\"bold\" >" . $this->config->item('account_ins_name')."</p>"; 
			//"<br>".$this->config->item('account_address') .
			echo "<p class=\"bold\">".$this->config->item('account_address') . "</p>";
			echo "<h3>" . "<p class=\"bold\">".$title . "</p>" . "</h3>";
			//"<br>".$title . "</h2><br>";
			//echo $this->config->item('account_address') . "</td>"; 
			echo"<p>";
			if(($title != "Balance Sheet")&&($title != "Balance Sheet As At ".$curr_date)&&($title != "Day Statement"))
			{
				if($title == "Depreciation Of Assets"){
					echo "Depreciation As Today";
				}elseif($title != "Depreciation Rate"){
			//		echo	 "For the period " . $from_date . " to " . $to_date ;
				}
			}
			echo"</p>";
		}
		?>
	</div>
	<div id="info2">
	<div id="info3">
		<?php
		echo "<p align=\"center\">" . 'Financial year'."</p>";
		echo"<p>" . date_mysql_to_php_display($this->config->item('account_fy_start'));
		echo "-"; 
		echo date_mysql_to_php_display($this->config->item('account_fy_end'));
		echo "</p>"; 
		?>
	</div>
	</div>
</div>
<div id="middle">
	<div id="table">
	<?php 
		if(isset($isSchedule))
			$this->load->view($report, $arr);
		else
			$this->load->view($report); 
	?>
	</div>
</div>
<div id="bottom">
<?php
if($title == "Balance Sheet As At ".$curr_date){
echo "Balance Sheet Prepared By Brihaspati General Accounting System (BGAS)"."</br>";
}
?>

<br>
	<form><input class="hide-print" type="button" onClick="window.print()" value="Print Statement"></form>
</div>
</div>
</html>
