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
		$design_earm_funds_group = array();
                $design_earm_funds_ledger = array();
		$code = $data['code'];
		$this->load->model('Group_model');
                $group_details = $this->Group_model->get_schedule($code);
                foreach ($group_details as $id => $group)
                {
                        $id  = $group['id'];
                        //$schedule = $group['schedule'];
                        $name = $group['name'];
                }
		
		if($name == 'Current Liabilities'){
			$name = 'Current Liabilities And Provisions';
		}


		echo "<h2> Schedule - ".$count." " .$data['name']."</h2>";
		
		if($data['name'] == $ledger_name){
			$this->load->view('report/schedule_template_1', $data);
		} elseif($data['name'] == 'Designated-Earmarked Funds')
		{
		//add child groups and ledgers for the fund
                        $num_of_childs = $this->Group_model->get_numOfChild($id);
                        $count1 = 0;

                        if($num_of_childs > 0){
                                //get child id, name, code
                                $this->db->select('id, name, code');
                                $this->db->from('groups')->where('parent_id', $id);
                                $group_result = $this->db->get();

                                foreach($group_result->result() as $row){
                                        $design_earm_funds_group[$count1]['id'] = $row->id;
                                        $design_earm_funds_group[$count1]['name'] = $row->name;
                                        $design_earm_funds_group[$count1]['code'] = $row->code;
                                        $count1++;
                                }
                        }

                        $num_of_childs = $this->Ledger_model->get_numOfChild($id);

                        if($num_of_childs > 0){
                                //get child id, name, code
                                $this->db->select('id, name, code');
                                $this->db->from('ledgers')->where('group_id', $id);
                                $ledger_result = $this->db->get();

                                foreach($ledger_result->result() as $row){
                                        $design_earm_funds_ledger[$count1]['id']= $row->id;
                                        $design_earm_funds_ledger[$count1]['name'] = $row->name;
                                        $design_earm_funds_ledger[$count1]['code'] = $row->code;
                                        $count1++;
                                }
                        }
				$data['designated_earmarked_funds_group'] = $design_earm_funds_group;
                 	        $data['designated_earmarked_funds_ledger'] = $design_earm_funds_ledger;
                        	$this->load->view('report/schedule_template_2', $data);
			}

		elseif($data['name'] == 'Loan/Borrowings')
		{
			$this->load->view('report/schedule_template_3', $data); 
		}
		
		elseif($name == 'Current Liabilities And Provisions'){
                        $count1 = 0;
                        $this->db->select('id, name, code')->from('groups');
                        $this->db->where('parent_id', $id);
                        $query_result = $this->db->get();

                        foreach($query_result->result() as $row){
                         	$current_liabilities[$count1]['id'] = $row->id;
                                $current_liabilities[$count1]['name'] = $row->name;
                                $current_liabilities[$count1]['code'] = $row->code;
                                $count1++;
                                }

                                $this->db->select('id, name, code')->from('groups');
                                $this->db->where('parent_id', 157);
                                $query_result1 = $this->db->get();

                                $counter = 0;
                                foreach($query_result1->result() as $row1){
                                  $provisions[$count1]['id'] = $row1->id;
                                  $provisions[$count1]['name'] = $row1->name;
                                  $provisions[$count1]['code'] = $row1->code;
                                  $count1++;
				  }

	                        $data['current_liabilities'] = $current_liabilities;
        	                $data['provisions'] = $provisions;
				$this->load->view('report/schedule_template_4', $data);
			}


		
		elseif($data['name'] == 'Investments')
		{
			$this->load->view('report/schedule_template_8', $data);
		}

		elseif($data['name'] == 'Fixed Assets')
		{
			$fixed_assets = array();
                        $count1 = 0;

                        $this->db->select('id')->from('groups');
                        $this->db->where('parent_id', $id);
                        $group_query = $this->db->get();
                        foreach($group_query->result() as $row){
                                $this->db->select('id')->from('groups');
                                $this->db->where('parent_id', $row->id);
                                $child_group_query = $this->db->get();
                                foreach($child_group_query->result() as $row1){
                                        $fixed_assets[$count1]['id'] = $row1->id;
                                        $count1++;
                                }

                                $this->db->select('id,name')->from('ledgers');
                                $this->db->where('group_id', $row->id);
                                $child_ledger_query = $this->db->get();
                                foreach($child_ledger_query->result() as $row1){
                                        $fixed_assets[$count1]['id'] = $row1->id;
                                        $count1++;
                                }
                        } 

			$data['fixed_assets'] = $fixed_assets;
			$this->load->view('report/schedule_template_5', $data);
		//	return;
		}
			
		elseif($name == 'Current Assets'){
                        $current_assets_group = array();
                        $current_assets_ledger = array();
                        $count1 = 0;

                        $this->db->select('id');
                        $this->db->from('groups')->where('parent_id', $id);
                        $group_query = $this->db->get();
                        foreach($group_query->result() as $row){
                                $current_assets_group[$count1]['id'] = $row->id;
                                $count1++;
                        }

                        $count1 = 0;
                        $this->db->select('id');
                        $this->db->from('ledgers')->where('group_id', $id);
                        $ledger_query = $this->db->get();
                        foreach($ledger_query->result() as $row1){
                                $current_assets_ledger[$count1]['id'] = $row1->id;
                                $count1++;
                        }

                        $data['current_assets_group'] = $current_assets_group;
                        $data['current_assets_ledger'] = $current_assets_ledger;
                        $this->load->view('report/schedule_template_6', $data);
			}

		elseif($name == 'Loans Advances and Deposits'){
                        $loans_advances = array();
                        $count1 = 0;

                        $this->db->select('id');
                        $this->db->from('groups')->where('parent_id', $id);
                        $group_query = $this->db->get();
                        foreach($group_query->result() as $row){
                                $loans_advances[$count1]['id'] = $row->id;
                                $count1++;
                        }

                        $data['loans_advances'] = $loans_advances;
                        $this->load->view('report/schedule_template_7', $data);
			}
		

		else
			$this->load->view('report/schedule_template',$data);

		echo "<br><br>";
		$count++;
	}
	
	//Notes to Accounts
	echo "<h2> Schedule - 22 Notes to Accounts </h2>";
	$this->load->view('report/notesToAccount','');
?>
