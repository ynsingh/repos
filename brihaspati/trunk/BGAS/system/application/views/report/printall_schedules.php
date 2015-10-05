<?php

	$this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
	
	$this->db->select('id,name')->from('groups')->where('parent_id',0);
        $query = $this->db->get();
        foreach($query->result() as $row)
        {
        	$id = $row->id;
	}
		$this->db->select('name,id')->from('groups')->where('parent_id',2);
            	$group_data = $this->db->get();
            	foreach($group_data->result() as $group){
                	$group_id = $group->id;
			$name = $group->name;
			if($name == 'Corpus')
                        $name = 'Corpus/Capital Funds';
			if($name == 'Corpus/Capital Funds')
			{
				echo "</br><h2> Schedule - 1 Corpus/Capital Funds</h2><br/>";
				$this->load->view('new_report/schedule_template_1', $group);
			}elseif($name == "Designated-Earmarked/Endowment Funds"){
				echo "</br><h2> Schedule - 2 Designated-Earmarked/Endowment Funds</h2>";

				$this->load->view('new_report/schedule_template_2', $group);
			}elseif($name == 'Current Liabilities & Provisions')
			{
				echo "</br><h2> Schedule - 3 Current Liabilities & Provisions</h2>";
                		$this->load->view('new_report/schedule_template_3', $group);
			}
		}
		//}else{
			$count = 4;
			$this->db->select('name,id')->from('groups')->where('parent_id',1);
                        $group_data = $this->db->get();
                        foreach($group_data->result() as $group){
                        	$group_id = $group->id;
                                $name = $group->name;
				if($name == 'Fixed Assets')
                                {
					 echo "<h2> Schedule - 4 Fixed Assets</h2>";
                                        $this->load->view('new_report/schedule_template_4', $group);
                                }elseif($name == 'Investments'){
					 echo "<br/><h2> Schedule - 5 Designated-Earmarked/Endowment Funds</h2>";
                                        $this->load->view('new_report/schedule_template_5', $group);
                                }elseif($name == 'Current Assets'){
					 echo "<br/><h2> Schedule - 7 Current Assets</h2>";
                                        $this->load->view('new_report/schedule_template_7', $group);
                                }elseif($name == 'Loans Advances and Deposits'){
					 echo "<br/><h2> Schedule - 8 Loans Advances and Deposits</h2>";
                                        $this->load->view('new_report/schedule_template_8', $group);
                                }

				$this->db->select('id,name,code')->from('groups')->where('parent_id',$group_id);
             			$main1 = $this->db->get();
             			foreach ($main1->result() as $row1)
             			{
				$group_name =  $row1->name;
					if($group_name == 'Corpus Fund Investments')
                                	{
                                	$group_name = 'Investments Others';
					if($group_name = "Investments Others")
					{
					echo "</br><h2> Schedule - 6 Investments Others</h2>";
					$this->load->view('new_report/schedule_template_6', $row1);
					}
					}
				}
                                }
			//}//else
            	//} 

		//Notes to Accounts
	echo "<h2> Schedule - 22 Notes to Accounts </h2>";
	$this->load->view('report/notesToAccount','');
?>

