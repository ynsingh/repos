<?php
     if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Payment_model extends CI_Model {
   function __construct() {
        parent::__construct();
    }


        function Payment_model()
        {
                parent::Model();
        }

	
	public function record_count() {
                 return $this->db->count_all("bill_approval");
        }

	
	function bill_uploadvalues($pagination_counter, $page_count)
        {
                $data=array();
                $this->db->select('bill_no,submit_date,submitted_by,total_amount,bill_name,expense_type,decision,approval_date,approved_amount,approved_by,vc_date,bank_cash_account,mode_of_payment,payment_status,payment_date')->from('bill_approval')->order_by('bill_no', 'desc')->limit($pagination_counter, $page_count);
                $query = $this->db->get();
                if ($query->num_rows() > 0) {
                foreach ($query->result() as $row) {
                $data[] = $row;
                }
                return $data;
                }
                return false;

        }

	function  amount_for_approval($expensetype)
	{
		$app_amount='';
		$this->db->select('approved_amount')->from('bill_approval')->where('expense_type',$expensetype);
		$query = $this->db->get();
		if($query->num_rows() > 0) {
			foreach ($query->result() as $row){
				$app_amount1= $row->approved_amount;
				if($app_amount1!='0.00'){
					$app_amount=$app_amount+$app_amount1;
				}
			}
			return $app_amount;
		}
		
	}
	
	function bill_printvalue($bill_no)
        {
                $this->db->from('bill_approval')->where('bill_no',$bill_no);
                $show_bill = $this->db->get();
                return $show_bill->row();
        }

        function get_ledger_code($id)
        {
                $this->db->select('code');
                $this->db->from('ledgers')->where('id =', $id);
                $ledger_result = $this->db->get();
                if ($ledger = $ledger_result->row())
                        return $ledger->code;
                else
                        return 0;
        }

		

    function get_all_expense_detail($id)
    {
		$total1=0.00;
        $total2=0.00;
        $total3=0.00;
        $total4=0.00;
        $counter =0;
        $ledg_id = "";
        $ledger_id ="";
        $total=0.00;
        $total_amount=0.00;
		$num_row="";
		$sum_all=0.00;
		$sum_total =0.00;
        $dr_sum_total = 0.00;
        $cr_sum_total = 0.00;
        $cr_sum =0.00;
        $dr_total= 0.00;
        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
        $this->db->select('code')->from('groups')->where('id', $id);
        $code_result= $this->db->get();
        $code1 = $code_result->row();
        $code = $code1->code;
       // echo $code;
        $this->db->select('id')->from('ledgers');
        $this->db->like('code', $code);
                
		$query_result =$this->db->get();
		$no_row = $query_result->num_rows();
		if($no_row != 0)
        {
            $q_result = $query_result->result();
		       		
			foreach ($q_result as $row)
			{
        		$ledger_id = $row->id;

                $this->db->select('entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc,entry_items.id as entry_items_id');
                $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id);
                $this->db->where('date >=', $date1);
                $this->db->where('date <=', $date2);      
        		//$this->db->select('entry_id,id,dc,amount');
        		//$this->db->from('entry_items')->where('ledger_id', $ledger_id);
                //$this->db->where('update_date >=', $date1);
                //$this->db->where('update_date <=', $date2);
        		$result11 =$this->db->get();
        		$entry_result = $result11->result();
                foreach($entry_result as $query_row)
                {
                    $entry_item_id =$query_row->entry_items_id;
                            $dc = $query_row->entry_items_dc;
                            if($dc == "D"){
                                $dr_sum = $query_row->entry_items_amount;
                                $dr_sum_total = $dr_sum_total + $dr_sum;
                            }else{
                                $cr_sum = $query_row->entry_items_amount;
                                $cr_sum_total = $cr_sum_total + $cr_sum;
                            }
                    		$this->db->select('amount,fund_id')->from('fund_management')->where('entry_items_id', $entry_item_id);
                    		$this->db->where('date >=', $date1);
                            $this->db->where('date <=', $date2);
                            $result2 =$this->db->get();
                    		$fund_result = $result2->result();

                    		foreach($fund_result as $row2)
                    		{
                        		$fund_id =$row2->fund_id;
                        		$fund_code =$this->get_ledger_code($fund_id);
                        		$code = substr($fund_code,0,6);
                                            		
        						if($code == "100101")
                                {
                          			$fund_amount = $row2->amount;
                            		$total1 = $total1+$fund_amount;
                         		}elseif($code == "100102"){

                                		$fund_amount = $row2->amount;
                                		$total2 = $total2+$fund_amount;
                         		}elseif($code == "100103"){

                                		$fund_amount = $row2->amount;
                                		$total3 = $total3+$fund_amount;

                         		}elseif($code !="100101" && $code !="100102" && $code != "100103"){

                                		$fund_amount = $row2->amount;
                                		$total4 = $total4 + $fund_amount;
        						}
                            }
                        //}
    				//}
                }
			}
            $sum_total = $dr_sum_total - $cr_sum_total;
    		$value1=$total1+$total2+$total3+$total4;
    		$value = $sum_total - $value1;
    		$total2 = $total2+$value;
            $total_amount = $total1."#".$total2."#".$total3."#".$total4;

		}
	    return $total_amount;
    }


	function get_all_income_detail($id)
	{
		$counter =0;
		$sum_total =0.00;
		$cr_total =0.00;
		$dr_total =0.00;
		$total1 = 0.00;
		$total2 =0.00;
		$t1 =0.00;
		$t2 =0.00;
		$t3 = 0.00;
		$t4 = 0.00;
		$t01 = 0.00;
        $t02 = 0.00;
        $t03 = 0.00;
        $t04 = 0.00;
		$fund_amount = 0.00;
		$diff = 0.00;
		$transit = 0.00;
        $this->load->library('session');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
		$this->db->select('code')->from('groups')->where('id', $id);
        $code_result= $this->db->get();
        $code = $code_result->row();
        //echo "code----id---->";print_r($code);
        $this->db->select('id')->from('ledgers');
        if(!empty($code))
        {
        foreach( $code as $code1){
               $this->db->like('code', $code1);
         }
        }
        $query_result =$this->db->get();
        $no_row = $query_result->num_rows();

        $q_result = $query_result->result();
        foreach ($q_result as $row)
        {
            $ledger_id = $row->id;
           // $this->db->select('entry_id,id,amount,dc');
           // $this->db->from('entry_items')->where('ledger_id', $ledger_id);
          //  $this->db->where('update_date >=', $date1);
          //  $this->db->where('update_date <=', $date2);
            $this->db->select('entry_items.amount as entry_items_amount, entry_items.dc as entry_items_dc,entry_items.id as entry_items_id');
            $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledger_id);
            $this->db->where('date >=', $date1);
            $this->db->where('date <=', $date2);
            $result11 =$this->db->get();
            $entry_result = $result11->result();
            foreach($entry_result as $query_row)
            {
                $dc = $query_row->entry_items_dc;
                $sum = $query_row->entry_items_amount;
                $entry_item_id =$query_row->entry_items_id;
                    if($id == "25")
					{
                        $this->db->select('amount,fund_id')->from('fund_management')->where('entry_items_id', $entry_item_id);
                        $this->db->where('date >=', $date1);
                        $this->db->where('date <=', $date2);
                        $result2 =$this->db->get();
                        $fund_result = $result2->result();
                        $n_row = $result2->num_rows();
                        if($n_row >0)
                        {	
							foreach($fund_result as $row2)
                            {
                        		$fund_id =$row2->fund_id;
                        		$fund_code =$this->get_ledger_code($fund_id);
                        		$code = substr($fund_code,0,6);
								$code1 = substr($fund_code,0,4);
								if($code == "100101"){
                            		$fund_amount = $row2->amount;
                            		$total1 = $total1+$fund_amount;
                    		    }elseif($code1 == "1002"){

                        		$fund_amount = $row2->amount;
                        		$total2 = $total2+$fund_amount;
							   }
						    }
                        }else{
						
                            if($dc == "C")
						    {
                        		$cr_total = $cr_total + $sum;
						    }elseif($dc == "D"){
                        		$dr_total = $dr_total + $sum;
						    }
                        }

                    }elseif($dc == "C"){
						$cr_total = $cr_total + $sum;

					}elseif($dc == "D"){
                        $dr_total = $dr_total + $sum;
					}
            }
		}

		if($id == "24")
        {
        	$this->db->select('id');
        	$this->db->from('ledgers');
        	$this->db->like('code', '10', 'after');
			$this->db->not_like('code', '1003', 'after');
    		$this->db->not_like('code', '1004', 'after');
    		$this->db->not_like('code', '1005', 'after');
			$this->db->not_like('code', '1006', 'after');
			$amount= "";
        	$query = $this->db->get();
        	$query_result = $query->result();
        	foreach($query_result as $row3)
			{
				$ledg_id = $row3->id;
			    $fund_code =$this->get_ledger_code($ledg_id);
				$code = substr($fund_code,0,6);
                $code1 = substr($fund_code,0,4);
				//$this->db->select('dc,amount,id,entry_id')->from('entry_items')->where('ledger_id',$ledg_id)->where('dc','D');		
				//$this->db->where('update_date >=', $date1);
                //$this->db->where('update_date <=', $date2);
                $this->db->select('entry_items.amount as entry_items_amount');
                $this->db->from('entries')->join('entry_items', 'entries.id = entry_items.entry_id')->where('entry_items.ledger_id', $ledg_id)->where('dc','D');
                $this->db->where('date >=', $date1);
                $this->db->where('date <=', $date2);
                $entry = $this->db->get();
				$entry_result =$entry->result();
				foreach($entry_result as $row4)
				{
                  
    					if($code == '100101')
    					{
    						$amount = $row4->entry_items_amount;
    						$t1 = $t1 + $amount;
    					}elseif($code == '100103'){
    						$amount = $row4->entry_items_amount;
    						$t2 = $t2 + $amount;
    					}elseif($code1 == '1002'){
    						$amount = $row4->entry_items_amount;
    						$t3 = $t3 +$amount;
    					}elseif($code == '100102'){
    						$amount = $row4->entry_items_amount;
    						$t4 = $t4 + $amount;
    					}
                   
				}
			}
			$fund_amount = $t1 + $t2 + $t3 + $t4;
          //  echo "fund=$fund_amount";
			$this->load->model('ledger_model');
            $transit= $this->Ledger_model->get_ledger_balance1('123');
            $transit = 0- $transit;
            //echo"transit=$transit";
			if($transit == $fund_amount){
				$t01 = $t1;
				$t02 = $t2;
				$t03 = $t3;
				$t04 = $t4;	
			}elseif($transit > $fund_amount){
			
				$diff = $transit - $fund_amount;	
			}
		}
		$sum_total = $cr_total - $dr_total;
		$total = $sum_total . "#" . $total1. "#" . $total2 . "#". $t01. "#" . $t02 . "#" . $t03 . "#" . $t04 . "#".$diff;
	   return $total;
	}	

	function income_xml_data($ledg_id)
	{
		$result = $this->get_all_income_detail($ledg_id);
		$my_values = explode('#',$result);
                                        $total1 =$my_values[0];
                                        $total2 =$my_values[1];
                                        $total3 =$my_values[2];
                                        $total4 =$my_values[3];
                                        $total5 =$my_values[4];
                                        $total6 =$my_values[5];
                                        $total7 =$my_values[6];
                                        $total9=$my_values[7];
					$income = new Reportlist();
                                        $income->init($ledg_id);
                                        $total = $income->total;

					$income1 = new Reportlist();
                			$income1->init('26');
                			$income_total = $income1->total;

                                    //    $CI =& get_instance();
                                        $this->load->model('Ledger_model');
                                        $transit= $this->Ledger_model->get_ledger_balance('123');
                                        $transit = 0-$transit;
					if($ledg_id == "24"){
                //      echo"total==$total";
                                             $total = (-$total) + $total4 + $total5 + $total7 + $total6;
					     $total = -$total;
					}
					elseif($ledg_id == 26){
                                                $total= (-$income_total) - $transit;

					}else{
						$total = $total;
					}
		return $total;
	}

	function xml_creation($type,$ledg_id,$database,$name,$curr_year,$total)
	{
		$CI =& get_instance();
		$counter = "0";
		if($type == "Income")
		{
                	$total = $this->income_xml_data($ledg_id);
				
		}elseif($type == "Expense"){
			
			$expense = new Reportlist();
                        $expense->init($ledg_id);
                        $total = $expense->total; 
		 }
                            
		$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/BGAS/xml');
                $file_name="";

                /* 
               * code for creating Income Xml file.
                */
		$type1 =$type."_Name";
		$doc = new DOMDocument();
                $doc->formatOutput = true;

                //echo $file_name=$type."-".$database."-".$curr_year.".xml";echo "<br>";
                echo "Datain--->".$file_name=$type."-".$database.".xml";echo "<br>";
                $tt=$acctpath."/".$file_name;

                if(file_exists($tt))
                {
                $doc->preserveWhiteSpace = false;
                $doc->load($tt);
                $type = $doc->firstChild;
                $type1 = $doc->createElement($type1);

                $group_name = $doc->createElement('Group_Name');
                $textNode = $doc->createTextNode($name);
                $group_name->appendChild($textNode);
		$type1->appendChild($group_name);

		$amount = $doc->createElement('Amount');
                $textNode2 = $doc->createTextNode($total);
                $amount->appendChild($textNode2);
                $type1->appendChild($amount);

                $group_id = $doc->createElement('Group_ID');
                $textNode1 = $doc->createTextNode($ledg_id);
                $group_id->appendChild($textNode1);
                $type1->appendChild($group_id);

                /*      $grnd_total = $doc->createElement('Total');
                	$textNode3 = $doc->createTextNode($sum);
                        $grnd_total->appendChild($textNode3);
                        $type1->appendChild($grnd_total);*/

		$type->appendChild($type1);
                $ttt=$doc->saveXML();
                $handle = fopen($tt, "w");
                fwrite($handle, $ttt);
                fclose($handle);
                }else{
                $r = $doc->createElement( $type );
                $doc->appendChild( $r );
                $b = $doc->createElement( $type1 );
                $group_name = $doc->createElement( "Group_Name" );
                $group_name->appendChild($doc->createTextNode($name));
                $b->appendChild( $group_name );

                $amount = $doc->createElement( "Amount");
		$amount->appendChild($doc->createTextNode($total));
		$b->appendChild( $amount );

                $group_id = $doc->createElement('Group_ID');
                $textNode1 = $doc->createTextNode($ledg_id);
                $group_id->appendChild($textNode1);
                $b->appendChild( $group_id );

                /*     $grnd_total = $doc->createElement('Total');
                       $textNode2 = $doc->createTextNode($sum);
                       $grnd_total->appendChild($textNode2);
                       $b->appendChild($grnd_total);*/

               $r->appendChild( $b );
               $doc->save($tt);
               $doc->saveXML();
               }
	return $counter;
	}
	
	function database_name()
	{
		$db_name = "";
		$CI =& get_instance();
                $CI->db->from('settings')->where('id', 1);
                $settings_q = $CI->db->get();
                $settings= $settings_q->row();
                $ins_name = $settings->ins_name;
                $uni_name = $settings->uni_name;
		
		$date1 = explode("-", $settings->fy_start);
		$date2 = explode("-", $settings->fy_end);
		$year_start = $date1[0];
		$year_end = $date2[0];
                $curr_year = $year_start . "-" . $year_end;

		$CI =& get_instance();
                $db = $CI->load->database('login', TRUE);
                $db->select('databasename, uname, dbpass, hostname, port');

                $db->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $curr_year);
                $login_q = $db->get();
                if($login_q->num_rows()>0){
                        $login = $login_q->row();
                        $db_name = $login->databasename;
		}
		return $db_name;
	}
	
	   function db_user_name()
        {
                $db_name = "";
                $CI =& get_instance();
                $CI->db->from('settings')->where('id', 1);
                $settings_q = $CI->db->get();
                $settings= $settings_q->row();
                $ins_name = $settings->ins_name;
                $uni_name = $settings->uni_name;
                $lable_name = $settings->name;

                $date1 = explode("-", $settings->fy_start);
                $date2 = explode("-", $settings->fy_end);
                $year_start = $date1[0];
                $year_end = $date2[0];
                $curr_year = $year_start . "-" . $year_end;
                $CI =& get_instance();
                $db = $CI->load->database('login', TRUE);
                $db->select('uname, dbpass');

                $db->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $curr_year);
                $login_q = $db->get();
                if($login_q->num_rows()>0){
                        $login = $login_q->row();
                        $db_name = $login->uname;
                        $db_pass = $login->dbpass;

                }
                $val= $db_name.'##'.$db_pass.'##'.$lable_name;
                return $val;
        }
	
	 function get_cheque_no($entry_id){
		$cheque_no='';
                $this->db->select('update_cheque_no')->from('cheque_print')->where('entry_no',$entry_id);
                $ledger_q = $this->db->get();
                $no_of_row=$ledger_q->num_rows();
                if($no_of_row > 0){
                        foreach($ledger_q->result() as $row)
                        {
                                $cheque_no= $row->update_cheque_no;
                        }
                }
                return $cheque_no;
        }

	function xml_read($file_name, $name){
		$amount="0.00";
		if(file_exists($file_name))
        {
			$xml=simplexml_load_file($file_name);
			foreach($xml->children() as $books){
				if($books->Group_Name == $name)
					$amount=$books->Amount.".00";
				if($amount == 0)
					$amount="0.00";
			} 	
		}
                return $amount;
	}

/*	function xml_read_COA($file_name, $name){
                $amount="0.00";
                if(file_exists($file_name))
                {
                        $xml=simplexml_load_file($file_name);
                        foreach($xml->children() as $books){
				print_r($books->Ledgers_Name);
                                if($books->Ledgers_Name == $name)
				$amount=$books->Ledger_Name.".00"."#".$books->op_balance.".00"."#".$books->op_balance_dc.".00";
                        }

                }
                return $amount;
        }
*/

	function xml_read1($file_name, $name){
                $amount="0.00"."#"."0.00"."#"."0.00"."#"."0.00";
                if(file_exists($file_name))
                {
                        $xml=simplexml_load_file($file_name);
                        foreach($xml->children() as $books){
                                if($books->Group_Name == $name)
                                        $amount=$books->Amount.".00"."#".$books->plan.".00"."#".$books->nonplan.".00"."#".$books->plansfc.".00";
                        }
                }
                return $amount;
        }

	 function xml_read_schedule1($file_name, $name){
                $amount="0.00"."#"."0.00";
                if(file_exists($file_name))
                {
                        $xml=simplexml_load_file($file_name);
                        foreach($xml->children() as $books){
                                if($books->Group_Name == $name)
                                        $amount=$books->Amount.".00"."#".$books->Group_ID.".00";
                        }
                }
                return $amount;
        }
		
	function xml_creation_schedule($type,$plan,$nonplan,$plansfc, $database,$name,$curr_year,$total)
        {
                $CI =& get_instance();
                $counter = "0";
                if($type == "Income")
                {
                        $total = $this->income_xml_data($ledg_id);

                }elseif($type == "Expense"){

                        $expense = new Reportlist();
                        $expense->init($ledg_id);
                        $total = $expense->total;
                 }

                $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/BGAS/xml');
                $file_name="";

                /* 
               * code for creating Income Xml file.
                */
                $type1 =$type."_Name";
                $doc = new DOMDocument();
                $doc->formatOutput = true;

                $file_name=$type."-".$database."-".$curr_year.".xml";
                $tt=$acctpath."/".$file_name;

                if(file_exists($tt))
                {
                $doc->preserveWhiteSpace = false;
                $doc->load($tt);
                $type = $doc->firstChild;
                $type1 = $doc->createElement($type1);

                $group_name = $doc->createElement('Group_Name');
                $textNode = $doc->createTextNode($name);
                $group_name->appendChild($textNode);
                $type1->appendChild($group_name);

                $amount = $doc->createElement('Amount');
                $textNode2 = $doc->createTextNode($total);
                $amount->appendChild($textNode2);
                $type1->appendChild($amount);

                $plan_name = $doc->createElement('plan');
                $textNode1 = $doc->createTextNode($plan);
                $plan_name->appendChild($textNode1);
                $type1->appendChild($plan_name);
		
		$nonplan_name = $doc->createElement('nonplan');
                $textNode1 = $doc->createTextNode($nonplan);
                $nonplan_name->appendChild($textNode1);
                $type1->appendChild($nonplan_name);

		$plansfc_name = $doc->createElement('plansfc');
                $textNode1 = $doc->createTextNode($plansfc);
                $plansfc_name->appendChild($textNode1);
                $type1->appendChild($plansfc_name);

                $type->appendChild($type1);
                $ttt=$doc->saveXML();
                $handle = fopen($tt, "w");
                fwrite($handle, $ttt);
                fclose($handle);
                }else{
                $r = $doc->createElement( $type );
                $doc->appendChild( $r );
                $b = $doc->createElement( $type1 );
                $group_name = $doc->createElement( "Group_Name" );
                $group_name->appendChild($doc->createTextNode($name));
                $b->appendChild( $group_name );

                $amount = $doc->createElement( "Amount");
                $amount->appendChild($doc->createTextNode($total));
                $b->appendChild( $amount );

                $plan_name = $doc->createElement('plan');
                $textNode1 = $doc->createTextNode($plan);
                $plan_name->appendChild($textNode1);
                $b->appendChild( $plan_name );

		$nonplan_name = $doc->createElement('nonplan');
                $textNode1 = $doc->createTextNode($nonplan);
                $nonplan_name->appendChild($textNode1);
                $b->appendChild( $nonplan_name );

		$plansfc_name = $doc->createElement('plansfc');
                $textNode1 = $doc->createTextNode($plansfc);
                $plansfc_name->appendChild($textNode1);
                $b->appendChild( $plansfc_name );

		$r->appendChild( $b );
		$doc->save($tt);
		$doc->saveXML();
               }
        return $counter;
        }




}
?>
