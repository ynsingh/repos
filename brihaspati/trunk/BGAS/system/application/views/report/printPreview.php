<?php
	/**
	 * This program provides the print preview
	 * for all the schedules.
	 * @author Priyanka Rawat <rpriyanka12@ymail.com> 
	 */

	$count = 1;
	foreach($arr as $data)
	{
		echo "<h2> Schedule - ".$count." " .$data['name']."</h2>";
		//echo "<br>";
		if($data['name'] == 'General Funds' || $data['name'] == 'Reserves and Surplus')
			$this->load->view('report/schedule_template_1', $data);	
		else
			$this->load->view('report/schedule_template',$data);

		echo "<br><br>";
		$count++;
	}
?>
