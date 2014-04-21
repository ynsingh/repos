<?php
	/**
	 * This program provides the print preview
	 * for all the schedules.
	 * @author Priyanka Rawat <rpriyanka12@ymail.com> 
	 */

	//get the name of the ledger,
	//to which net profit/loss will bw transfered.
	$this->db->select('ledger_name');
        $this->db->from('settings')->where('id', 1);
        $query_result = $this->db->get();
        $result = $query_result->row();
        $ledger_name = $result->ledger_name;

	$count = 1;
	foreach($arr as $data)
	{
		echo "<h2> Schedule - ".$count." " .$data['name']."</h2>";
		
		//if($data['name'] == 'General Funds' || $data['name'] == 'Reserves and Surplus')
		if($data['name'] == $ledger_name)
			$this->load->view('report/schedule_template_1', $data);	
		else
			$this->load->view('report/schedule_template',$data);

		echo "<br><br>";
		$count++;
	}
	
	//Notes to Accounts
	echo "<h2> Schedule - 22 Notes to Accounts </h2>";
	$this->load->view('report/notesToAccount','');
?>
