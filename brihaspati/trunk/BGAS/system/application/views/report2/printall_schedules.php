<?php

 	$this->db->select('name,id,code')->from('groups')->where('parent_id IN (3,4)');
        $group_data = $this->db->get();
      	foreach($group_data->result() as $group){
       		$group_id = $group->id;
        	$name = $group->name;
		$code = $group->code;
		//print_r($group);
		if($code == "3003" || $code == "3004" || $code == "3005" || $code == "3006")
                        {
				if($code == "3003")
                                echo "</br><h2> Schedule - 11 Income from Royalty & Publications</h2><br/>";
				elseif($code == "3004")
				echo "</br><h2> Schedule - 12 Interest Earned</h2><br/>";
				elseif($code == "3005")
                                echo "</br><h2> Schedule - 13 Other Incomes</h2><br/>";
				elseif($code == "3006")
                                echo "</br><h2> Schedule - 14 Prior Period Income</h2><br/>";
                                $this->load->view('report2/schedule_template5', $group);
			}
		elseif($code == "3001"){
			 	echo "</br><h2> Schedule - 9  Academic Receipts</h2><br/>";
                                $this->load->view('report2/schedule_template1', $group);

		}elseif($code == "3002"){
				echo "</br><h2> Schedule - 10  Grant/Subsidies and Donations</h2><br/>";
                                $this->load->view('report2/schedule_template6', $group);

		}elseif($code == "4003"){
			  	echo "</br><h2> Schedule - 17 Administrative and General expenses</h2><br/>";
                                $this->load->view('report2/schedule_template3', $group);
		}else{
				//$count = 15;
				//foreach($group_data->result() as $group){
				//$groupname = $group->name;
				if($code == "4001")
				echo "</br><h2> Schedule - 15  Staff Payments and Benefits(Establishment Expenses)</h2><br/>";
				elseif($code == "4002")
				echo "</br><h2> Schedule - 16  Academic Expenses</h2><br/>";
				elseif($code == "4004")
				echo "</br><h2> Schedule - 18  Transportations Expenses</h2><br/>";
				elseif($code == "4005")
				echo "</br><h2> Schedule - 19 Repairs and Maintenance </h2><br/>";
				elseif($code == "4006")
				echo "</br><h2> Schedule - 20 Finance Costs </h2><br/>";
				elseif($code == "4007")
				echo "</br><h2> Schedule - 4  Depreciation </h2><br/>";
				elseif($code == "4008")
				echo "</br><h2> Schedule - 21  Other expenses </h2><br/>";
				elseif($code == "4009")
				echo "</br><h2> Schedule - 22  Prior Period Expenses</h2><br/>";
                                $this->load->view('report2/schedule_template2', $group);
				//$count++;
				//}
		}
		}
		 //Notes to Accounts
        	echo "<h2> Schedule - 23 Notes to Accounts </h2>";
        	$this->load->view('report/notesToAccount','');
?>
