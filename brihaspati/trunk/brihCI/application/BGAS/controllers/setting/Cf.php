<?php if(! defined('BASEPATH')) exit('No direct script access allowed');
class Cf extends CI_Controller{
    function __construct() {
                parent::__construct(); 
	        $this->load->library('session');
                $this->load->model('Setting_model');
                $this->load->helper('file');
                $this->load->library('balancesheet');
                $this->load->library('Paymentreceipt');
                $this->load->library('Reportlist');
                $this->load->library('Reportlist1');
                $this->load->library('accountlist');
                $this->load->model('Ledger_model');
                $this->load->model('payment_model');      
                $this->load->model('Secunit_model');      

		/* Check access */

		if ( ! check_access('change account settings'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}

		return;
	}

	function index()
	{ 
		$this->logger->write_logmessage("insert", "Enter into carry farword methed ");
        	$sc_path = realpath(BASEPATH.'../application/BGAS/controllers/admin/schema.sql');
		$flag = FALSE;
		ini_set('max_execution_time', 400);
		$this->template->set('page_title', 'Carry forward account');
		/* Check access */
		if ( ! check_access('cf account'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('setting');
			return;
		}

        //first we get details of account which is going to cf.

		/* Form fields */
        $this->db->from('settings');
        $detail = $this->db->get();
        foreach ($detail->result() as $row)
        {
            $date1 = $row->fy_start;
            $date2 = $row->fy_end;
        }
        //create a hash table;
        $newdata = array(
            'date1'  => $date1,
            'date2'  => $date2
        );
        $this->session->set_userdata($newdata);

		//Geting bgasAccData by dblebel.

        $current_active_account = $this->session->userdata('active_account');

	$this->logger->write_logmessage("insert", "In carry farword methed-the current active account is  ".$current_active_account);
        //$current_active_account =mbgas1718;    

        $logindb=$this->load->database('login', TRUE);
        $logindb->select('databasename, uname, dbpass')->from('bgasAccData')->where('dblable', $current_active_account);
        $acc_detail=$logindb->get();
	$acc_info = $acc_detail->row();


		/* Current settings */

		$account_data = $this->Setting_model->get_current();
        //$account_data = Other Current Liabilities
		$ledger_name = $account_data->ledger_name;
        
        //check for ledger for carry forward 
        if($ledger_name == '' || $ledger_name == '0' || $ledger_name == null)
       	{
		$this->logger->write_logmessage("error", "In carry farword methed-Account head to which the profit and loss balance will be carry forward, does not exist.So, please go to the \'Settings --> Account Settings\' page to set the name of account head. ".$ledger_name);
	        $this->messages->add('Account head to which the profit and loss balance will be carry forward, does not exist.', 'error');
            	$this->messages->add('So, please go to the \'Settings --> Account Settings\' page to set the name of account head.', 'error');
            	redirect('setting');
            	return;
        }
		/* Difference of income and expenditure  */
		$fy_start=explode("-",$account_data->fy_start); 
        $fy_end=explode("-",$account_data->fy_end);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);

		/* Form fields */
		$i=0;	
		list($last_year_end_date, $last_year_end_time) = explode(' ', $account_data->fy_end);
		list($last_year_end_year, $last_year_end_month, $last_year_end_day) = explode('-', $last_year_end_date);
		$last_year_end_ts = strtotime($account_data->fy_end);
		$default_start_ts = $last_year_end_ts + (60 * 60 * 24); /* Adding 24 hours */
		$default_start = date("Y-m-d 00:00:00", $default_start_ts);
		$default_end = ($last_year_end_year + 1) . "-" . $last_year_end_month . "-" . $last_year_end_day . " 00:00:00";
		$start_date = date_mysql_to_php($default_start);
		$end_date = date_mysql_to_php($default_end);
		$exp_start_date=explode("/",$start_date);
		$exp_end_date=explode("/",$end_date);
		$Pre_year = substr($exp_start_date[2], -2);
		$last_year = substr($exp_end_date[2], -2);
		$pre_cf_year=$Pre_year -1;
		$last_cf_year=$last_year-1;
		$this_year=$pre_cf_year.$last_cf_year;
		$match_year=strpbrk($acc_info->databasename,$pre_cf_year.$last_cf_year); 
		if($match_year == $this_year){
	                $new_db_name = chop($acc_info->databasename,$this_year);
			$new_db_name= $new_db_name.$Pre_year.$last_year;
		}else{
			$new_db_name=$acc_info->databasename.$Pre_year.$last_year;

		}

		/* Form fields */
		$data['account_label'] = array(
			'name' => 'account_label',
			'id' => 'account_label',
			'maxlength' => '30',
			'size' => '30',
			'value' => $current_active_account.$Pre_year.$last_year,
		);
		$data['account_name'] = array(
			'name' => 'account_name',
			'id' => 'account_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => '',
		);
		$data['fy_start'] = array(
			'name' => 'fy_start',
			'id' => 'fy_start',
			'maxlength' => '11',
			'size' => '11',
			'value' => date_mysql_to_php($default_start),
		);
		$data['fy_end'] = array(
			'name' => 'fy_end',
			'id' => 'fy_end',
			'maxlength' => '11',
			'size' => '11',
			'value' => date_mysql_to_php($default_end),
		);

		$data['database_name'] = array(
			'name' => 'database_name',
			'id' => 'database_name',
			'maxlength' => '100',
			'size' => '40',
			'value' => $new_db_name,
		);

		$data['database_username'] = array(
			'name' => 'database_username',
			'id' => 'database_username',
			'maxlength' => '100',
			'size' => '40',
			'value' => $acc_info->uname,
		);

		$data['database_password'] = array(
			'name' => 'database_password',
			'id' => 'database_password',
			'maxlength' => '100',
			'size' => '40',
			'value' => $acc_info->dbpass,
		);

		$data['database_host'] = array(
			'name' => 'database_host',
			'id' => 'database_host',
			'maxlength' => '100',
			'size' => '40',
			'value' => 'localhost',
		);

		$data['database_port'] = array(
			'name' => 'database_port',
			'id' => 'database_port',
			'maxlength' => '100',
			'size' => '40',
			'value' => '3306',
		);

		$data['create_database'] = FALSE;
		$data['account_name']['value'] = $this->config->item('account_name');

		/* Form validations */
		$this->form_validation->set_rules('account_label', 'C/F Label', 'trim|required|min_length[2]|max_length[30]|alpha_numeric');
		//$this->form_validation->set_rules('account_label', 'C/F Label', 'trim|required|min_length[2]|max_length[30]');
		$this->form_validation->set_rules('account_name', 'C/F Account Name', 'trim|required|min_length[2]|max_length[100]');
		$this->form_validation->set_rules('fy_start', 'C/F Financial Year Start', 'trim|required|is_date');
		$this->form_validation->set_rules('fy_end', 'C/F Financial Year End', 'trim|required|is_date');

		$this->form_validation->set_rules('database_name', 'Database Name', 'trim|required');
		$this->form_validation->set_rules('database_username', 'Database Username', 'trim|required');

		/* Repopulating form */
		if ($_POST)
		{
			$data['account_label']['value'] = $this->input->post('account_label', TRUE);
			$data['account_name']['value'] = $this->input->post('account_name', TRUE);
			$data['fy_start']['value'] = $this->input->post('fy_start', TRUE);
			$data['fy_end']['value'] = $this->input->post('fy_end', TRUE);
			$data['create_database'] = $this->input->post('create_database', TRUE);
			$data['database_name']['value'] = $this->input->post('database_name', TRUE);
			$data['database_username']['value'] = $this->input->post('database_username', TRUE);
			$data['database_password']['value'] = $this->input->post('database_password', TRUE);
			$data['database_host']['value'] = $this->input->post('database_host', TRUE);
			$data['database_port']['value'] = $this->input->post('database_port', TRUE);
		}

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'setting/cf', $data);
			return;
		}
		else
		{

			            //Get values from config..................                      
                        $data_account_address = $this->config->item('account_address');
                        $data_account_email = $this->config->item('account_email');
                        $data_account_currency = $this->config->item('account_currency_symbol');
                        $data_account_date = $this->config->item('account_date_format');
                        $data_account_timezone = $this->config->item('account_timezone');

                        //Get value from setting table.............

                        $data_ledger_name = $account_data->ledger_name;
                        $data_liability_ledger_name = $account_data->liability_ledger_name;
                        $data_chart_account = $account_data->chart_account;
				
                        $data_account_flag = $account_data->account_flag;
                        $data_account_manage_inventory = $account_data->manage_inventory;
                        $data_account_account_locked = $account_data->account_locked;
                        $data_account_email_protocol = $account_data->email_protocol;
                        $data_account_email_host = $account_data->email_host;
                        $data_account_email_port = $account_data->email_port;
                        $data_account_email_username = $account_data->email_username;
                        $data_account_email_password = $account_data->email_password;
                        $data_account_print_paper_height = $account_data->print_paper_height;
                        $data_account_print_paper_width = $account_data->print_paper_width;
                        $data_account_print_margin_top = $account_data->print_margin_top;
                        $data_account_print_margin_bottom = $account_data->print_margin_bottom;
                        $data_account_print_margin_left = $account_data->print_margin_left;
                        $data_account_print_margin_right = $account_data->print_margin_right;
                        $data_account_print_orientation = $account_data->print_orientation;
                        $data_account_print_page_format = $account_data->print_page_format;

                        $data_ins_name = $account_data->ins_name;
                        $data_dept_name = $account_data->dept_name;
                        $data_uni_name = $account_data->uni_name;

                        $data_database_host = $this->input->post('database_host', TRUE);
                        $data_database_port = $this->input->post('database_port', TRUE);
                        $data_database_name = $this->input->post('database_name', TRUE);
                        $data_database_username = $this->input->post('database_username', TRUE);
                        $data_database_password = $this->input->post('database_password', TRUE);

                        
                        
                        $data_account_label = $this->input->post('account_label', TRUE);
                        $data_account_label = strtolower($data_account_label);
                        $data_account_name = $this->input->post('account_name', TRUE);
                        $data_fy_start = date_php_to_mysql($this->input->post('fy_start', TRUE));
                        $data_fy_end = date_php_to_mysql_end_time($this->input->post('fy_end', TRUE));
                        $data_database_type = 'mysql';


			/* CF Income Schedules Balance using xml      */
                 /*       $this->db->select('code')->from('groups')->where('parent_id','3');
                        $groups = $this->db->get();
                        $count = 11;
                        foreach($groups->result() as $row)
                        {
                        	$code = $row->code;
                        	$schedule = new Reportlist();
                        	$schedule->get_IE_schedule($code,"CF",$current_active_account.$Pre_year.$last_year,$count);
                        	$count++;
                        } */

			/* CF Expenditure schedules Balance using xml*/
		/*	$this->db->select('code')->from('groups')->where('parent_id','4');
                       	$groups = $this->db->get();
                       	$count = 17;
                       	foreach($groups->result() as $row)
                       	{
        	        	$code = $row->code;
                               	$schedule = new Reportlist();
                               	$schedule->get_IE_schedule($code,"CF",$current_active_account.$Pre_year.$last_year,$count);
                               	$count++;
                       	} */

			if($data_chart_account == "minimal" || $data_chart_account == "standard"){
                        $flag = "TRUE";
			$this->logger->write_logmessage("insert", "In carry farword methed-the current chart account is  ".$data_chart_account);
                       	$income = new Reportlist();
                       	//$income->income_exp_mhrd(3,"CF",$current_active_account.$Pre_year.$last_year);
                       	$income->income_exp_mhrd(3,"CF",$current_active_accountr);
                       	$expense = new Reportlist();
                       	$expense->income_exp_mhrd(4,"CF" ,$current_active_account.$Pre_year.$last_year); 
			
			            /*CF Payment Receipt  */
			            $payment = new Paymentreceipt();
                        $payment->payment_receipt('Payment', "CF",$current_active_account.$Pre_year.$last_year);
                        $receipt = new Paymentreceipt();
                        $receipt->payment_receipt('Receipt',"CF",$current_active_account.$Pre_year.$last_year);
			
			            /*CF MHRD balancesheet*/
			            $liability = new Reportlist();
                        $liability->new_balance_sheet(0,2,"CF",$current_active_account.$Pre_year.$last_year,0);
                        $asset = new Reportlist();
                        $asset->new_balance_sheet(6,1,"CF",$current_active_account.$Pre_year.$last_year,9);
			}


			if($data_chart_account == "mhrd2015")
            		{
			    $flag = "TRUE";
			$this->logger->write_logmessage("insert", "In carry farword methed-the current chart account is  ".$data_chart_account);
    			$income_expense = new Reportlist1();
	    		//$income_expense->income_exp_mhrdnew(3,"CF" ,$current_active_account.$Pre_year.$last_year);
	    		$income_expense->income_exp_mhrdnew(3,"CF" ,$current_active_account);
			$this->logger->write_logmessage("insert", "In carry farword methed-generate Income xml and current active account is  ".$current_active_account);
		    	//$income_expense->income_exp_mhrdnew(4,"CF" ,$current_active_account.$Pre_year.$last_year);
		    	$income_expense->income_exp_mhrdnew(4,"CF" ,$current_active_account);
			$this->logger->write_logmessage("insert", "In carry farword methed-generate rcpence xml and current active account is  ".$current_active_account);
			        /*CF Payment Receipt  */
               		$payment = new Paymentreceipt();
               		//$payment->payment_receipt('Payment', "CF",$current_active_account.$Pre_year.$last_year);
               		$payment->payment_receipt('Payment', "CF",$current_active_account);
			$this->logger->write_logmessage("insert", "In carry farword methed-generate payment xml and current active account is  ".$current_active_account);
               		$receipt = new Paymentreceipt();
               		//$receipt->payment_receipt('Receipt',"CF",$current_active_account.$Pre_year.$last_year);  
               		$receipt->payment_receipt('Receipt',"CF",$current_active_account);  
			$this->logger->write_logmessage("insert", "In carry farword methed-generate receipt xml and current active account is  ".$current_active_account);

			        /*CF MHRD balancesheet*/
			$mhrd_liability = new Reportlist1();
    			//$mhrd_liability->new_mhrd(2, "CF", $current_active_account.$Pre_year.$last_year);
    			$mhrd_liability->new_mhrd(2, "CF", $current_active_account);
			$this->logger->write_logmessage("insert", "In carry farword methed-generate liability xml and current active account is  ".$current_active_account);
	    		$mhrd_asset = new Reportlist1();
        		//$mhrd_asset->new_mhrd(1, "CF", $current_active_account.$Pre_year.$last_year); 
        		$mhrd_asset->new_mhrd(1, "CF", $current_active_account); 
			$this->logger->write_logmessage("insert", "In carry farword methed-generate asset xml and current active account is  ".$current_active_account);

			        /*cf balncesheet schedule for
			*****MHRD FORMAT 2015*******/
			//Schedule 1
		    	$mhrd_schedule = new Reportlist1();
			//$mhrd_schedule->get_liabilityschedule('1001',1, 'CF',$current_active_account.$Pre_year.$last_year);
			$mhrd_schedule->get_liabilityschedule('1001',1, 'CF',$current_active_account);
                	//$mhrd_schedule->get_liabilityschedule('1002',1, 'CF',$current_active_account.$Pre_year.$last_year);
                	$mhrd_schedule->get_liabilityschedule('1002',1, 'CF',$current_active_account);

			//Scheule2
                	//$mhrd_schedule->designated_fundA('1003','CF',$current_active_account.$Pre_year.$last_year,2);
                	$mhrd_schedule->designated_fundA('1003','CF',$current_active_account,2);
                	//$mhrd_schedule->designated_fundB('1003','CF',$current_active_account.$Pre_year.$last_year,2);
                	$mhrd_schedule->designated_fundB('1003','CF',$current_active_account,2);
	
			//Schedule3
                	//$mhrd_schedule->Current_liability(84,'100401',3, 'CF',$current_active_account.$Pre_year.$last_year);
                	$mhrd_schedule->Current_liability(84,'100401',3, 'CF',$current_active_account);
			//$mhrd_schedule->Provision(109,'100402',3, 'CF',$current_active_account.$Pre_year.$last_year);
			$mhrd_schedule->Provision(109,'100402',3, 'CF',$current_active_account);
	
			
			//Schedule4
                	//$mhrd_schedule->FixedAsset_A('2001','4','CF',$current_active_account.$Pre_year.$last_year);
                	$mhrd_schedule->FixedAsset_A('2001','4','CF',$current_active_account);
                	//$mhrd_schedule->FixedAsset_B('2001','4','CF',$current_active_account.$Pre_year.$last_year);
                	$mhrd_schedule->FixedAsset_B('2001','4','CF',$current_active_account);
			//$mhrd_schedule->FixedAsset_C(2001, 4,'CF',$current_active_account.$Pre_year.$last_year); 
			$mhrd_schedule->FixedAsset_C(2001, 4,'CF',$current_active_account); 

			//Schedule5
                	//$mhrd_schedule->get_Assetschedule('2002',5,'CF',$current_active_account.$Pre_year.$last_year);
                	$mhrd_schedule->get_Assetschedule('2002',5,'CF',$current_active_account);

			//Schedule6
                	//$mhrd_schedule->schedule_template6('200201',5,'CF',$current_active_account.$Pre_year.$last_year);
                	$mhrd_schedule->schedule_template6('200201',5,'CF',$current_active_account);

 			//Schedule7
                        //$mhrd_schedule->get_Assetschedule('2003',7,'CF',$current_active_account.$Pre_year.$last_year);
                        $mhrd_schedule->get_Assetschedule('2003',7,'CF',$current_active_account);
			
			//Schedule8
                        //$mhrd_schedule->get_Assetschedule('2004',8,'CF',$current_active_account.$Pre_year.$last_year);
                        $mhrd_schedule->get_Assetschedule('2004',8,'CF',$current_active_account);
			
			/*cf I/E schedule for
                        *****MHRD FORMAT 2015*******/
			//Schedule14
                        //$mhrd_schedule->schedule9('3001','CF',$current_active_account.$Pre_year.$last_year,9,'A');
                        $mhrd_schedule->schedule9('3001','CF',$current_active_account,9,'A');
			
			//Schedule17
			//$mhrd_schedule->schedule17('4003','CF',$current_active_account.$Pre_year.$last_year,17,'A');
			$mhrd_schedule->schedule17('4003','CF',$current_active_account,17,'A');
			//$mhrd_schedule->schedule17('4003','CF',$current_active_account.$Pre_year.$last_year,17,'B');
			$mhrd_schedule->schedule17('4003','CF',$current_active_account,17,'B');
			//$mhrd_schedule->schedule17('4003','CF',$current_active_account.$Pre_year.$last_year,17,'C');
			$mhrd_schedule->schedule17('4003','CF',$current_active_account,17,'C');
	
			//Schedule 14,13,11,12
			//$mhrd_schedule->get_schedule14('3006','CF',$current_active_account.$Pre_year.$last_year,'14');
			$mhrd_schedule->get_schedule14('3006','CF',$current_active_account,'14');
			//$mhrd_schedule->get_income_schedule('3005','CF',$current_active_account.$Pre_year.$last_year,'13');
			$mhrd_schedule->get_income_schedule('3005','CF',$current_active_account,'13');
			//$mhrd_schedule->get_income_schedule('3003','CF',$current_active_account.$Pre_year.$last_year,'11');
			$mhrd_schedule->get_income_schedule('3003','CF',$current_active_account,'11');
			//$mhrd_schedule->get_income_schedule('3004','CF',$current_active_account.$Pre_year.$last_year,'12');
			$mhrd_schedule->get_income_schedule('3004','CF',$current_active_account,'12');
			
			//Schedule 10,16,18,19,20,21,22
			//$mhrd_schedule->schedule10('3002','CF',$current_active_account.$Pre_year.$last_year,10);
			$mhrd_schedule->schedule10('3002','CF',$current_active_account,10);
			//$mhrd_schedule->get_exp_schedules('4001','CF',$current_active_account.$Pre_year.$last_year,15);
			$mhrd_schedule->get_exp_schedules('4001','CF',$current_active_account,15);
			//$mhrd_schedule->get_exp_schedules('4002','CF',$current_active_account.$Pre_year.$last_year,16);
			$mhrd_schedule->get_exp_schedules('4002','CF',$current_active_account,16);
			//$mhrd_schedule->get_exp_schedules('4004','CF',$current_active_account.$Pre_year.$last_year,18);
			$mhrd_schedule->get_exp_schedules('4004','CF',$current_active_account,18);
			//$mhrd_schedule->get_exp_schedules('4005','CF',$current_active_account.$Pre_year.$last_year,19);
			$mhrd_schedule->get_exp_schedules('4005','CF',$current_active_account,19);
			//$mhrd_schedule->get_exp_schedules('4006','CF',$current_active_account.$Pre_year.$last_year,20);
			$mhrd_schedule->get_exp_schedules('4006','CF',$current_active_account,20);
			//$mhrd_schedule->get_exp_schedules('4007','CF',$current_active_account.$Pre_year.$last_year,23);
			$mhrd_schedule->get_exp_schedules('4007','CF',$current_active_account,23);
			//$mhrd_schedule->get_exp_schedules('4008','CF',$current_active_account.$Pre_year.$last_year,21);
			$mhrd_schedule->get_exp_schedules('4008','CF',$current_active_account,21);
			//$mhrd_schedule->get_exp_schedules('4009','CF',$current_active_account.$Pre_year.$last_year,22);
			$mhrd_schedule->get_exp_schedules('4009','CF',$current_active_account,22);
			}
			$this->logger->write_logmessage("insert", "In carry farword methed-after generate schedule xml and current active account is  ".$current_active_account);

			/*CF corporate format balancesheet */
                    /*    $corp_liability = new Reportlist();
			$corp_liability->init(1);
                        $corp_liability->bal_corp_format_asset(0, $current_active_account.$Pre_year.$last_year);
                        $corp_asset = new Reportlist();
			$corp_asset->init(2);
                        $corp_asset->bal_corp_format_liability(0, $current_active_account.$Pre_year.$last_year); */

			
			/* CF Asset Liability MHRD Balance using xml */
	
                     /*   $liability = new Reportlist();
                        $liability->new_balance_sheet(0,2,"CF",$current_active_account.$Pre_year.$last_year,0);
                        $asset = new Reportlist();
                        $asset->new_balance_sheet(6,1,"CF",$current_active_account.$Pre_year.$last_year,9);
                        $asset = new Balancesheet();
                        $asset->get_schedule(9,'2003',"CF",$current_active_account.$Pre_year.$last_year);
                        $asset->loans_advances(10,'2004',"CF",$current_active_account.$Pre_year.$last_year);
                        $asset->Investments(21,'Earmarked Funds',8,'200201',$current_active_account.$Pre_year.$last_year,"CF",0);
                        $asset->Investments(21,'others',8,'200201',$current_active_account.$Pre_year.$last_year,"CF",6);
                        $asset->Investments(22,'Earmarked Funds',8,'200202',$current_active_account.$Pre_year.$last_year,"CF",12);
                        $asset->Investments(22,'others',8,'200202',$current_active_account.$Pre_year.$last_year,"CF",18);
                        $asset->fixed_assets(14,7,'2001',"CF",$current_active_account.$Pre_year.$last_year);
                        $liability = new Balancesheet();
                        $liability->schedule_five('12',5,'100301',"CF",$current_active_account.$Pre_year.$last_year);
                        $liability->schedule_five('13',5,'100302',"CF",$current_active_account.$Pre_year.$last_year);
                        $liability->current_liabilities(8,6,'1004',"CF",$current_active_account.$Pre_year.$last_year);
                        $liability->provisions(157,6,'1005',"CF",$current_active_account.$Pre_year.$last_year); */
	
			$income = new Reportlist();
                        $diff = $income->income_expense_diff();
			            /* Only importing Assets and Liability closing balance */
			$this->logger->write_logmessage("insert", "In carry farword methed- after getting diff (income-exp) and current active account is  ".$current_active_account);
                        $assets = new Accountlist();
                        $assets->init(1);
                        $liability = new Accountlist();
                        $liability->init(2);
			$cf_ledgers = array_merge($assets->get_ledger_ids(), $liability->get_ledger_ids());
			$this->db->select('name, id');
			$this->db->from('ledgers')->order_by('id', 'asc');
                       	$ledger_q = $this->db->get();
			$this->logger->write_logmessage("insert", "In carry farword methed- getting the ledgers and current active account is  ".$current_active_account);
                        foreach ($ledger_q->result() as $row)
                        {
                        	/* CF only Assets and Liability with Closing Balance */
                                if (in_array($row->id, $cf_ledgers))
                                {
                                	/* Calculating closing balance for previous year */
                                       	// calculating diff of income expenditure to be transffered
                                       	$ledger_name = $this->Ledger_model->get_ledger_name($row->id);
					if ($ledger_name == "Balance of net income/expenditure transferred from I/E Account")
                                        {
                                        	$balance = $this->Ledger_model->get_ledger_balance($row->id);
                                            if($balance < 0){
                                             	if($diff < 0){
                                                   	$diff = 0 - $diff;
                                                                $cl_balance = $diff + $balance;
                                                        }elseif($diff > 0){
                                                        	$balance = 0 - $balance;
                                                                $cl_balance = $diff + $balance;
                                                        }else{
                                                                $cl_balance = $balance;
                                                        }
						}elseif($balance > 0){
                                                	if($diff < 0){
                                                	        $diff = 0 - $diff;
                                                                $cl_balance = $diff + $balance;
                                                        }elseif($diff > 0){
                                                                $cl_balance = $diff - $balance;
                                                        }else{
                                                        	$cl_balance = $balance;
                                                        }
						}else{
                                 	               if($diff < 0){
                                                 	      $cl_balance = $diff;
                                                        }elseif($diff > 0){
                                                              $cl_balance = $diff;
                                                        }else{
                                                              $cl_balance = 0;
							}
                                                }

                                                        if (float_ops($cl_balance, 0, '<'))
                                                        {
                                                        	$op_balance = -$cl_balance;
                                                                $op_balance_dc = "D";
                                                        }else {
                                                        	$op_balance = $cl_balance;
                                                                $op_balance_dc = "C";
                                                        }

					}else{
                                                        $cl_balance = $this->Ledger_model->get_ledger_balance($row->id);

                                                        if (float_ops($cl_balance, 0, '<'))
                                                        {
                                                                $op_balance = -$cl_balance;
                                                                $op_balance_dc = "C";
                                                        } else {
                                                                $op_balance = $cl_balance;
                                                                $op_balance_dc = "D";
                                                        }
					}
					//				 echo "if";   print_r($current_active_account); die;
					//				 $caaccount =$current_active_account;
							    $payment = new Paymentreceipt();
			$this->logger->write_logmessage("insert", "In carry farword methed-generate Ledgers xml and id is in merged array and current active account is  ".$current_active_account);
                        		//	$payment->ledgers_op_cl_balance('Ledgers',$row->id, $caaccount.$Pre_year.$last_year,$row->name,$curr_year, $op_balance, $op_balance_dc);
                        			$payment->ledgers_op_cl_balance('Ledgers',$row->id, $current_active_account,$row->name,$curr_year, $op_balance, $op_balance_dc);

				} else {
			//		echo "else"; print_r($current_active_account); die;
					$payment = new Paymentreceipt();
			$this->logger->write_logmessage("insert", "In carry farword methed-generate ledgers xml and id  is not in merged array and current active account is  ".$current_active_account);
						//$payment->ledgers_op_cl_balance('Ledgers',$row->id, $current_active_account.$Pre_year.$last_year,$row->name,$curr_year, '0', '0');
						$payment->ledgers_op_cl_balance('Ledgers',$row->id, $current_active_account,$row->name,$curr_year, '0', '0');
                                        }
                        }
			$this->logger->write_logmessage("insert", "In carry farword methed- after generate Ledgers xml and current active account is  ".$current_active_account);

			/* check for database label exist */
                        $logindb=$this->load->database('login', TRUE);
                        $logindb->select('dblable')->from('bgasAccData')->where('dblable', $data_account_label);
                        if ($logindb->get()->num_rows() > 1)
                        {
                                $this->messages->add('Account with same label already exists.', 'error');
                                $this->template->load('admin_template', 'admin/create', $data);
                                return;
                        }
                        $logindb->close();
			/*
			$ini_file = $this->config->item('config_path') . "accounts/" . $data_account_label . ".ini";

			/* Check if database ini file exists /
			if (get_file_info($ini_file))
			{
				$this->messages->add('Account with same label already exists.', 'error');
				$this->template->load('template', 'setting/cf', $data);
				return;
			}
		*/
			/* Check if start date is less than end date */

//            echo "*************Test1**************";


			if ($data_fy_end <= $data_fy_start)
			{
				$this->messages->add('Financial start date cannot be greater than end date.', 'error');
				$this->template->load('template', 'setting/cf', $data);
				return;
			}

			if ($data_database_host == "")
				$data_database_host = "localhost";
			if ($data_database_port == "")
				$data_database_port = "3306";

			/* Creating account database */
			if ($this->input->post('create_database', TRUE) == "1")
			{
				$ini_file = $this->config->item('config_path') . "accounts/sqladmin.ini";
                if ( ! get_file_info($ini_file))
                {
                    $this->messages->add('MySQL settings file label sqladmin.ini does not exists.', 'error');
                    $this->messages->add('So you first set the MySQL administrator user name and password.', 'error');
                    $this->template->load('admin_template', 'admin/create', $data);
                    return;
                }
                else
                {
                    //get the mysql root and password from sqladmin.ini file
 
                    $data_sql_accounts = parse_ini_file($ini_file);
                    $data_database_admin_username = $data_sql_accounts['sql_admin_name'];
                    //$database_root_user = $data_sql_accounts['sql_admin_name'];
                    $data_database_admin_password = $data_sql_accounts['sql_admin_password'];
                    //$database_root_password = $data_sql_accounts['sql_admin_password'];

                    //make connection to the root database to create new account database.

                    $con = mysqli_connect($data_database_host,$data_database_admin_username,$data_database_admin_password );
                    //$con = mysqli_connect($data_database_host,$database_root_user,$database_root_password );
                    //if connection fails
                    
                    if (mysqli_connect_errno())
                    {
                        "Failed to connect to MySQL: " . mysqli_connect_error();
                    }
                    
					if($con)
					{
						/* Check if database already exists */
						//$db_selected = mysql_select_db($data_database_name, $new_link);
                        			$db_selected = mysqli_select_db($con, $data_database_name);
						if ($db_selected) 
                        			{
							mysqli_close($con);
							$this->messages->add('Database already exists.', 'error');
							$this->template->load('template', 'setting/cf', $data);
							return;
						}
						else
                        			{
                            $upflag=true;
                            $eflag=true; $eflag1=true; $eflag2=true;
	                        $data_database_name1='mysql';
        	                $db_selected1 = mysqli_select_db($con,$data_database_name1);
                	        /* Check if database user already exists */
                        	$query="select * from user where User='".$data_database_username."'";
                            $result = mysqli_query($con,$query);
                            //$num_rows = mysql_num_rows($result);
/*
	                        if($num_rows > 0)
        	                {
                	        /* Check with password matched */
/*                        	    @$link_me = mysql_connect($data_database_host . ':' . $data_database_port, $data_database_username, $data_database_password);
                                if(!$link_me)
                                {
	                                $upflag=false;
                                    mysql_close($new_link);
                                    $this->messages->add('Database user name already exists and password is not matched.', 'error');
	                                $this->template->load('template', 'setting/cf', $data);
        	                        return;
                	            }
                        	    //mysql_close($new_link);
                            }
*/
                            if($result)
                            {

                                /* Check with password matched */
                                $newcon = mysqli_connect($data_database_host . ':' . $data_database_port, $data_database_username, $data_database_password);

                                if(!$newcon)
                                {
                                    $upflag=false;
                                    mysqli_close($con);
                                    $this->messages->add('Database1 user name already exists and password is not matched.', 'error');
                                    $this->template->load('admin_template', 'admin/create', $data);
                                    return;
                                }
                            //mysql_close($new_link);
                            }

		//	if($upflag)
                  //          {
                                 /* Creating account database */
/*                                $db_create_q = 'CREATE DATABASE ' . mysql_real_escape_string($data_database_name).'; ';
                                $db_create_q1 = 'GRANT ALL ON '. mysql_real_escape_string($data_database_name).'.* TO '. mysql_real_escape_string($data_database_username).'@127.0.0.1 IDENTIFIED BY "'. mysql_real_escape_string($data_database_password).'"; ';
	                            $db_create_q2 = 'GRANT ALL ON '. mysql_real_escape_string($data_database_name).'.* TO '. mysql_real_escape_string($data_database_username).'@localhost IDENTIFIED BY "'. mysql_real_escape_string($data_database_password).'"; ';
        	                    if (mysql_query($db_create_q, $new_link))
                	            {
                        	        $eflag=false;
                                	$this->messages->add('Created new account database.', 'success');
                                }
                                if (mysql_query($db_create_q1, $new_link))
                                {
                                    $eflag1=false;
                                    $this->messages->add('Granting permission to user to access new database  with local ip.', 'success');
	                            }	
        	                    if (mysql_query($db_create_q2, $new_link))
                	            {
                        	        $eflag2=false;
                                	$this->messages->add('Granting permission to user to access new database  with local name.', 'success');
                                }
                                if ($eflag ||$eflag1  || $eflag2) 
                                {
                                    mysql_close($new_link);
                                    $this->messages->add('Error creating account database. ' . mysql_error(), 'error');
                                    $this->template->load('template', 'setting/cf', $data);
	                                return;
        	                    }
                	            mysql_close($new_link);
                            }//end upflag
*/
                            if($upflag){
                                /* Creating account database */
                                $db_create_q = 'CREATE DATABASE ' . mysqli_real_escape_string($con, $data_database_name).'; ';
                                $db_create_q1 = 'GRANT ALL ON '. mysqli_real_escape_string($con, $data_database_name).'.* TO '. mysqli_real_escape_string($con,$data_database_username).'@127.0.0.1 IDENTIFIED BY "'. mysqli_real_escape_string($con, $data_database_password).'"; ';
                                $db_create_q2 = 'GRANT ALL ON '. mysqli_real_escape_string($con, $data_database_name).'.* TO '. mysqli_real_escape_string($con, $data_database_username).'@localhost IDENTIFIED BY "'. mysqli_real_escape_string($con, $data_database_password).'"; ';
                                if (mysqli_query($con, $db_create_q))
                                {
                                    $eflag=false;
                                    $this->messages->add('Created new account database.', 'success');
                                }
                                if (mysqli_query($con,$db_create_q1))
                                {
                                    $eflag1=false;
                                    $this->messages->add('Granting permission to user to access new database  with local ip.', 'success');
                                }
                                if (mysqli_query($con,$db_create_q2))
                                {
                                    $eflag2=false;
                                    $this->messages->add('Granting permission to user to access new database  with local name.', 'success');
                                }
                                if ($eflag ||$eflag1  || $eflag2) {
                                    mysqli_close($con);
                                    $this->messages->add('Error1 creating account database. ' , 'error');
                                    $this->template->load('admin_template', 'admin/create', $data);
                                    return;
                                }
                                mysqli_close($con);
                            }
                    //}

                        }//end selected db else

		      }//close if new link
		else 
                    {
						$this->messages->add('Error connecting to database. ' . mysql_error(), 'error');
						$this->template->load('template', 'setting/cf', $data);
						return;
		    }
	            } //sql admin ini file else
		}//create database if not created

				 /* Adding org name unit name year and database name in login database */
            $sy =$last_year_end_year;
            $ey = $last_year_end_year+1;
            $fy = $sy.'-'.$ey;

            $tablebad="bgasAccData";
            $logindb=$this->load->database('login', TRUE);
            $logindb->trans_start();
            $insert_data = array(
                'organization'=> $data_ins_name,
                'unit'=>  $data_uni_name,
                'databasename' =>  $data_database_name,
                'fyear' => $fy,
                'uname' => $data_database_username,
                'dbpass' => $data_database_password,
                'hostname' => $data_database_host,
                'port' => $data_database_port,
                'dbtype' => $data_database_type,
                'dblable' => $data_account_label,
                'prevyeardb' => $current_active_account
            );
            if ( ! $logindb->insert($tablebad, $insert_data))
            {
                $logindb->trans_rollback();
                $this->messages->add('Error1 in Adding value in bgasAccData table under login data base ' . $data_database_name . '.', 'error');
                                        //$db1->close();
            } 
            else 
            {
                $logindb->trans_complete();
                $this->messages->add('Added Values in bgasAccData table under login data base- ' . $data_database_name . '.', 'success');
            }
            $logindb->close();

            $dsn = mysqli_connect($data_database_host, $data_database_username, $data_database_password, $data_database_name);
            $dsn = new mysqli($data_database_host, $data_database_username, $data_database_password, $data_database_name);
                            	//$newacc = $this->load->database($dsn, TRUE);
            if (mysqli_connect_errno())
            {
                "Failed to connect to MySQL: " . mysqli_connect_error();
            }
            else
                "connect to MySQL: ";
            $res = mysqli_query($dsn,"SHOW TABLES");

           	if ( !$dsn )
           	{
               	$this->messages->add('Error connecting to database.', 'error');
              	$this->template->load('template', 'setting/cf', $data);
              	return;
           	}/*  else if ($newacc->_error_message() != "") {
                                	$this->messages->add('Error connecting to database. ' . $newacc->_error_message(), 'error');
                                	$this->template->load('template', 'setting/cf', $data);
                                	return;
                            	} else if ($newacc->query("SHOW TABLES")->num_rows() > 0) {
                                */
            else if (mysqli_num_rows(mysqli_query($dsn,"SHOW TABLES")) > 0) 
            {
                $this->messages->add('Selected database is not empty.', 'error');
                $this->template->load('template', 'setting/cf', $data);
                return;
            } 
            else 
            {
				/* Executing the database setup script */
                //$sc_path = realpath(BASEPATH.'../application/BGAS/controllers/admin/schema.sql');echo "<br>";
				//$setup_account = read_file('system/application/controllers/admin/schema.sql');
                $setup_account = read_file($sc_path);
		$setup_account_array = explode(";", $setup_account);
		foreach($setup_account_array as $row)
		{
                    mysqli_query($dsn,$row);
/*
					if (strlen($row) < 5)
						continue;
					$newacc->query($row);
					if ($newacc->_error_message() != "")
					{
						$this->messages->add('Error initializing account database.', 'error');
						$this->template->load('template', 'setting/cf', $data);
						return;
					}
*/
				}
				$this->messages->add('Initialized account database.', 'success');

				/* Adding account settings */
/*
				$newacc->trans_start();
				if ( ! $newacc->query("INSERT INTO settings (id, name, address, email, fy_start, fy_end, currency_symbol, date_format, timezone, manage_inventory, account_locked, email_protocol, email_host, email_port, email_username, email_password, print_paper_height, print_paper_width, print_margin_top, print_margin_bottom, print_margin_left, print_margin_right, print_orientation, print_page_format, database_version, ins_name, dept_name, uni_name, ledger_name, liability_ledger_name, chart_account, account_flag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", array(1, $data_account_name, $data_account_address, $data_account_email, $data_fy_start, $data_fy_end, $data_account_currency, $data_account_date, $data_account_timezone, $data_account_manage_inventory, 0, $data_account_email_protocol, $data_account_email_host, $data_account_email_port, $data_account_email_username, $data_account_email_password, $data_account_print_paper_height, $data_account_print_paper_width, $data_account_print_margin_top, $data_account_print_margin_bottom, $data_account_print_margin_left, $data_account_print_margin_right, $data_account_print_orientation, $data_account_print_page_format, 4, $data_ins_name, $data_dept_name, $data_uni_name, $data_ledger_name, $data_liability_ledger_name, $data_chart_account, $data_account_flag)))
				{
					$newacc->trans_rollback();
					$this->messages->add('Error adding account settings.', 'error');
					$this->template->load('template', 'setting/cf', $data);
					return;
				} else {
					$newacc->trans_complete();
					$this->messages->add('Added account settings.', 'success');
				}
*/
                $resultnew = mysqli_query($dsn,"INSERT INTO settings (id, name, address, email, fy_start, fy_end, currency_symbol, date_format, timezone, manage_inventory, account_locked, email_protocol, email_host, email_port, email_username, email_password, print_paper_height, print_paper_width, print_margin_top, print_margin_bottom, print_margin_left, print_margin_right, print_orientation, print_page_format, database_version, ins_name, dept_name, uni_name, ledger_name, liability_ledger_name, chart_account, account_flag) VALUES (1, '$data_account_name', '$data_account_address', '$data_account_email', '$data_fy_start', '$data_fy_end', '$data_account_currency', '$data_account_date', '$data_account_timezone', '$data_account_manage_inventory', 0, '$data_account_email_protocol', '$data_account_email_host', '$data_account_email_port', '$data_account_email_username', '$data_account_email_password', '$data_account_print_paper_height', '$data_account_print_paper_width', '$data_account_print_margin_top', '$data_account_print_margin_bottom', '$data_account_print_margin_left', '$data_account_print_margin_right', '$data_account_print_orientation', '$data_account_print_page_format', 4, '$data_ins_name', '$data_dept_name', '$data_uni_name', '$data_ledger_name', '$data_liability_ledger_name', '$data_chart_account', '$data_account_flag')");

                if(!$resultnew)
                {
                    //$dsn->trans_rollback();
                    $this->messages->add('Error adding account settings.', 'error');
                    $this->template->load('admin_template', 'admin/create', $data);
                    return;
                } else {
                    //$newacc->trans_complete();
                    $this->messages->add('Added account settings.', 'success');
                }
				/**************** Importing the C/F Values : START ***************/

		$cf_status = TRUE;

//                $data_database_host;
  //              $data_database_username;
    //            $data_database_password;
                  
		$database=$acc_info->databasename;

                //Import of old preveous FY year start to next year 

				//$dsn1 = "mysql://${data_database_username}:${data_database_password}@${data_database_host}:${data_database_port}/${database}";
                $dsn1 = mysqli_connect($data_database_host, $data_database_username, $data_database_password, $database);
                $dsn1 = new mysqli($data_database_host, $data_database_username, $data_database_password, $database);
/*
                $oldacc = $this->load->database($dsn1, TRUE);
                echo "<br>--2-- <br>";print_r($oldacc);
				$oldacc->from('groups')->order_by('id', 'asc');
				$group_q = $oldacc->get();
*/
                $selgrp = "SELECT * FROM groups ORDER BY id ASC";
                $resultgrp = $dsn1->query($selgrp);
                
			if ($resultgrp->num_rows > 0)
    			{				
					//foreach ($resultgrp->result() as $row)
					//foreach ($resultgrp->fetch_assoc() as $key=>$value)
					foreach ($resultgrp as $row)
					{
                        $id1 = $row['id'];
                        $code1 = $row['code'];
                        $parent_id1 = $row['parent_id'];
                        $name1 = $row['name'];
                        $affects_gross1 = $row['affects_gross'];
                        $schedule1 = $row['schedule'];
                        
                        //echo $i =$i+1; echo "<br>";
                        //echo $value;
                      /*  $code 
                        $id1= $row['id'];
                        $code1 =$row['code'];
                        $parent_id1 = $row['parent_id'];
                        $name1 =$row['name'];
                        $affect_gross1 = $row['affects_gross'];
                        $schedule = $row['schedule'];*/
						//if ( ! $newacc->query("INSERT INTO groups (id, code, parent_id, name, affects_gross, schedule) VALUES (?, ?, ?, ?, ?, ?)", array($row->id, $row->code, $row->parent_id, $row->name, $row->affects_gross, $row->schedule)))
//						if ( ! $dsn->query("INSERT INTO groups (id, code, parent_id, name, affects_gross, schedule) VALUES ($row['id'], $row['code'], $row['parent_id'], $row['name'], $row['affects_gross'], $row['schedule'])"))
                        //"INSERT INTO groups (id, code, parent_id, name, affects_gross, schedule) VALUES (['id'], $row['code'], $row['parent_id'], $row['name'], $row['affects_gross'], $row['schedule'])");
                        //echo "INSERT INTO groups (id, code, parent_id, name, affects_gross, schedule) VALUES ($id1, $code1, $parent_id1, $name1, $affects_gross1, $schedule1)";
                        //cho "INSERT INTO groups (id, code, parent_id, name, affects_gross, schedule) VALUES ('$id1', '$code1', '$parent_id1', '$name1', '$affects_gross1', '$schedule1')";
                        $dsn->query("INSERT INTO groups (id, code, parent_id, name, affects_gross, schedule) VALUES ('$id1', '$code1', '$parent_id1', '$name1', '$affects_gross1', '$schedule1')");
					/*	if (!$dsn->query("INSERT INTO groups (id, code, parent_id, name, affects_gross, schedule) VALUES ('$id1', '$code1', '$parent_id1', '$name1', '$affects_gross1', '$schedule1')"));
						{
							$this->messages->add('Failed to add Group account - ' . $name1 . '.', 'error');
							$cf_status = FALSE;
						}
                    */    
                    
					}	
				}
				    //$oldacc->from('ledgers')->order_by('id', 'asc');
                    //            $led = $oldacc->get();
                //import of ledger
                $selled = "SELECT * FROM ledgers ORDER BY id ASC";
                $resultled = $dsn1->query($selled);
                //writing of data in xml file                
			$this->logger->write_logmessage("insert", "In carry farword methed- selecting all from ledgers db and current active account is  ".$current_active_account);
                foreach ($resultled as $row)
                {
                    $idl = $row['id'];
                    $codel = $row['code'];
                    $group_idl= $row['group_id'];
                    $namel= $row['name'];
                    $typel= $row['type'];
                    $reconciliationl= $row['reconciliation'];
		    $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/BGAS/xml');
    			//$file_name="Ledgers"."-".$current_active_account.$Pre_year.$last_year."-".$curr_year.".xml";
		    //$file_name="Ledgers"."-".$current_active_account."-".$prev_year.".xml";
			$this->logger->write_logmessage("insert", "In carry farword methed- In ledgers for loop and current active account is  ".$current_active_account);
    		    $file_name="Ledgers"."-".$current_active_account.".xml";
			$this->logger->write_logmessage("insert", "In carry farword methed-  after generating file name " .$file_name ." and current active account is  ".$current_active_account);
                    $tt=$acctpath."/".$file_name;
                    
                    if(file_exists($tt))
                    {
                    $doc = new DomDocument();
                    $doc->formatOutput = true;
                    $doc->load($tt);
                    $xpath = new DomXPath($doc);
               		$xpath->query("/Ledgers/Ledgers_Name/op_balance");
               		$xpath->query("/Ledgers/Ledgers_Name/op_balance_dc");
               		$op_balance = $xpath->query("/Ledgers/Ledgers_Name/op_balance");
               		$op_balance_dc = $xpath->query("/Ledgers/Ledgers_Name/op_balance_dc");
               		$op_balance = @$op_balance->item($i)->nodeValue;
               		$op_balance_dc = @$op_balance_dc->item($i)->nodeValue;
               		}
					$i++;

    				//if ( ! $newacc->query("INSERT INTO ledgers (id, code, group_id, name, op_balance, op_balance_dc, type, reconciliation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", array($row->id, $row->code, $row->group_id, $row->name, $op_balance, $op_balance_dc, $row->type, $row->reconciliation)))
                    //echo "INSERT INTO ledgers (id, code, group_id, name, op_balance, op_balance_dc, type, reconciliation) VALUES ('$idl', '$codel', '$group_idl', '$namel, $op_balance, $op_balance_dc, '$typel', '$reconciliationl')";
    				if ( ! $dsn->query("INSERT INTO ledgers (id, code, group_id, name, op_balance, op_balance_dc, type, reconciliation) VALUES ('$idl', '$codel', '$group_idl', '$namel', '$op_balance', '$op_balance_dc', '$typel', '$reconciliationl')"))
					{
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in ledgers ".$namel ." and op value is".$op_balance. " and current active account is  ".$current_active_account);
							$this->messages->add('Failed to add Ledger account - ' . $namel . '.', 'error');
							$cf_status = FALSE;
					}
                }

//				$oldacc->from('entry_types')->order_by('id', 'asc');
//				$entry_type_q = $oldacc->get();

                //imporing of data of entry table
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in entry type and current active account is  ".$current_active_account);

                $selentry = "SELECT * FROM entry_types ORDER BY id ASC";
                $resultentry = $dsn1->query($selentry);

				foreach ($resultentry as $row)
				{
                    $ide = $row['id'];
                    $labele= $row['label'];
                    $namee= $row['name'];
                    $descriptione=$row['description'];
                    $base_typee = $row['base_type'];
                    $numberinge=$row['numbering'];
                    $prefixe=$row['prefix'];
                    $suffixe=$row['suffix'];
                    $zero_paddinge=$row['zero_padding'];
                    $bank_cash_ledger_restrictione=$row['bank_cash_ledger_restriction'];
        
//					if ( !$dsn->query("INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES ('$ide', '$labele', '$namee', '$descriptione', '$base_typee', '$numberinge', '$prefixe', '$suffixe', '$zero_paddinge', '$bank_cash_ledger_restrictione')))
					if ( !$dsn->query("INSERT INTO entry_types (id, label, name, description, base_type, numbering, prefix, suffix, zero_padding, bank_cash_ledger_restriction) VALUES ('$ide', '$labele', '$namee', '$descriptione', '$base_typee', '$numberinge', '$prefixe', '$suffixe', '$zero_paddinge', '$bank_cash_ledger_restrictione')"))
					{
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in entry type " .$namee ." and current active account is  ".$current_active_account);
						$this->messages->add('Failed to add Entry type  - ' . $namee . '.', 'error');
						$cf_status = FALSE;
					}
				}

				//$oldacc->from('tags')->order_by('id', 'asc');
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in tags and current active account is  ".$current_active_account);
                //imporing of tag table data
                $seltag = "SELECT * FROM tags ORDER BY id ASC";
				//$tag_q = $oldacc->get();
                $resulttag = $dsn1->query($seltag);
				foreach ($resulttag as $row)
				{
                    $idt = $row['id'];
                    $titlet = $row['title'];
                    $colort = $row['color'];
                    $backgroundt = $row['background']; 
					if ( ! $dsn->query("INSERT INTO tags (id, title, color, background) VALUES ('$idt', '$titlet', '$colort', '$backgroundt')"))
					{
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in tag " .$titlet ." and current active account is  ".$current_active_account);
						$this->messages->add('Failed to add Tag - ' . $titlet . '.', 'error');
						$cf_status = FALSE;
					}
				}

			$this->logger->write_logmessage("insert", "In carry farword methed-   inserting record in budget and current active account is  ".$current_active_account);
				/* Importing budgets values */
                    $selbud = "SELECT * FROM budgets ORDER BY id ASC";
                    $resultbud = $dsn1->query($selbud);
					//$oldacc->from('budgets')->order_by('id','asc');
					//$budgets_q = $oldacc->get();
					foreach ($resultbud as $row)
					{
                        $idb = $row['id'];
                        $codeb = $row['code'];
                        $group_idb = $row['group_id'];
                        $budgetnameb = $row['budgetname'];
                        $bd_balanceb = $row['bd_balance'];
                        $typeb = $row['type'];
                        $allowedoverb = $row['allowedover'];
                        $consume_amountb = $row['consume_amount'];
                        
						if( ! $dsn->query("INSERT INTO budgets (id, code, group_id, budgetname, bd_balance, type, allowedover, consume_amount) VALUES ('$idb', '$codeb', '$group_idb', '$budgetnameb', '$bd_balanceb', '$typeb', '$allowedoverb', '$consume_amountb')"))
						{
			$this->logger->write_logmessage("error", "In carry farword methed-   inserting record in budget " .$budgetnameb ." and current active account is  ".$current_active_account);
							$this->messages->add('Failed to add budgets values ' . $budgetnameb . '.', 'error');
							$cf_status = FALSE;
						}
					}
				
				/* Importing budget_allocate values */
			$this->logger->write_logmessage("insert", "In carry farword methed-   inserting record in budget allocate and current active account is  ".$current_active_account);
//                                        $oldacc->from('budget_allocate')->order_by('id','asc');
//                                        $budgets_q = $oldacc->get();
                    $selbudal = "SELECT * FROM budget_allocate ORDER BY id ASC";
                    $resultbudal = $dsn1->query($selbudal);

                                        foreach ($resultbudal as $row)
                                        {
                                            $idba = $row['id'];
                                            $codeba = $row['code'];
                                            $allocation_amountba = $row['allocation_amount'];
                                            $creation_dateba = $row['creation_date'];
                
                                                if( !$dsn->query("INSERT INTO budget_allocate (id, code, allocation_amount, creation_date) VALUES ('$idba', '$codeba', '$allocation_amountba', '$creation_dateba')"))
                                                {
			$this->logger->write_logmessage("error", "In carry farword methed-   inserting record in budget allocate " .$codeba ."and value is ".$allocation_amountba." and current active account is  ".$current_active_account);
                                                        $this->messages->add('Failed to add budget allocated values ', 'error');
                                                        $cf_status = FALSE;
                                                }
                                        }
			
				/* Importing Fund values */
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in fund management and current active account is  ".$current_active_account);
//                                        $oldacc->from('fund_management')->order_by('id','asc');
//                                        $budgets_q = $oldacc->get();
                                        $selfundman = "SELECT * FROM fund_management ORDER BY id ASC";
                                        $resultfundman = $dsn1->query($selfundman);
                                        foreach ($resultfundman as $row)
                                        {
                                                $fundm1 =$row['id'];    
                                                $fundm2 =$row['fund_id'];    
                                                $fundm3 =$row['fund_name'];    
                                                $fundm4 =$row['amount'];    
                                                $fundm5 =$row['date'];    
                                                $fundm6 =$row['type'];    
                                                $fundm7 =$row['entry_items_id'];    
                                                if( ! $dsn->query("INSERT INTO fund_management (id, fund_id, fund_name, amount, date, type, entry_items_id) VALUES ('$fundm1', '$fundm2', '$fundm3', '$fundm4',  '$fundm5',  '$fundm6',  '$fundm7')"))
                                                {
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in fund management " .$fundm3 ." and value is ".$fundm4." and current active account is  ".$current_active_account);
                                                        $this->messages->add('Failed to add fund values ', 'error');
                                                        $cf_status = FALSE;
                                                }
                                        }

//                                        $oldacc->from('fund_group_table')->order_by('id','asc');
//                                        $budgets_q = $oldacc->get();
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in fund group and current active account is  ".$current_active_account);
                                        $selfundgrp = "SELECT * FROM fund_group_table ORDER BY id ASC";
                                        $resultfundgrp = $dsn1->query($selfundgrp);

                                        foreach ($resultfundgrp as $row)
                                        {
                                                $fundgrp1 = $row['id'];
                                                $fundgrp2 = $row['code'];
                                                $fundgrp3 = $row['group_id'];
                                                $fundgrp4 = $row['name'];
                                                $fundgrp5 = $row['short_name'];
                                                if( ! $dsn->query("INSERT INTO fund_group_table (id, code, group_id, name, short_name) VALUES ('$fundgrp1','$fundgrp2','$fundgrp3','$fundgrp4','$fundgrp5')"))
                                                {
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in fund group " .$fundgrp4 ." and current active account is  ".$current_active_account);
                                                        $this->messages->add('Failed to add fund group table ', 'error');
                                                        $cf_status = FALSE;
                                                }
                                        }

				/* Importing projection values */
//                                        $oldacc->from('projection')->order_by('id','asc');
//                                        $old_asset_register = $oldacc->get();
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in projection and current active account is  ".$current_active_account);
                                        $selproj = "SELECT * FROM projection ORDER BY id ASC";
                                        $resultproj = $dsn1->query($selproj);

                                        foreach ($resultproj as $row)
                                        {
                                                $proj1 = $row['id'];
                                                $proj2 = $row['code'];
                                                $proj3 = $row['group_id'];
                                                $proj4 = $row['projection_name'];
                                                $proj5 = $row['bd_balance'];
                                                $proj6 = $row['earned_amount'];
                                                $proj7 = $row['type'];
                                                if( ! $dsn->query("INSERT INTO projection (id, code, group_id, projection_name, bd_balance, earned_amount, type) VALUES ('$proj1','$proj2' ,'$proj3' ,'$proj4' ,'$proj5' ,'$proj6' ,'$proj7')"))
                                                {
                                                        $this->messages->add('Failed to add projection.', 'error');
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in projection " .$proj4 ." and value is ".$proj5 ."and current active account is  ".$current_active_account);
                                                        $cf_status = FALSE;
                                                }

                                        }

//                                        $oldacc->from('projection_allocate')->order_by('id','asc');
//                                        $old_asset_register = $oldacc->get();
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in projection allocate and current active account is  ".$current_active_account);
                                        $selproja = "SELECT * FROM projection_allocate ORDER BY id ASC";
                                        $resultproja = $dsn1->query($selproja);

                                        foreach ($resultproja as $row)
                                        {
                                                $proja1 = $row['id'];            
                                                $proja2 = $row['code'];            
                                                $proja3 = $row['allocation_amount'];            
                                                $proja4 = $row['creation_date'];            
                                                if( ! $dsn->query("INSERT INTO projection_allocate (id, code, allocation_amount, creation_date) VALUES (?, ?, ?, ?)", array($row->id, $row->code, $row->allocation_amount, $row->creation_date)))
                                                {
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in projection allocate " .$$row->code ." and current active account is  ".$current_active_account);
                                                        $this->messages->add('Failed to add projection.', 'error');
                                                        $cf_status = FALSE;
                                                }

                                        }


				/* Importing asset_register values */
//                                        $oldacc->from('old_asset_register')->order_by('id','asc');
//                                        $old_asset_register = $oldacc->get();
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in old asset register and current active account is  ".$current_active_account);
                                        $seloldassreg = "SELECT * FROM old_asset_register ORDER BY id ASC";
                                        $resultoldassreg = $dsn1->query($seloldassreg);

                                        foreach ($resultoldassreg as $row)
                                        {
                                            $oldassreg1 = $row['id'];
                                            $oldassreg2 = $row['date_of_purchase'];
                                            $oldassreg3 = $row['code'];
                                            $oldassreg4 = $row['asset_name'];
                                            $oldassreg5 = $row['cost'];
                                            $oldassreg6 = $row['depreciated_value'];
                                            $oldassreg7 = $row['current_value'];
                                            $oldassreg8 = $row['Financial_year'];
                                            $oldassreg9 = $row['asset_status'];
                                                 $dsn->query("INSERT INTO old_asset_register (id, date_of_purchase, code, asset_name, cost, depreciated_value, current_value, Financial_year, asset_status) VALUES ('$oldassreg1','$oldassreg2','$oldassreg3','$oldassreg4','$oldassreg5' ,'$oldassreg6','$oldassreg7','$oldassreg8','$oldassreg9')");

                                        }
/*
                                        $oldacc->from('new_asset_register')->order_by('id','asc');
                                        $old_asset_register = $oldacc->get();
                                        foreach ($old_asset_register->result() as $row)
                                        {
                                                if( ! $newacc->query("INSERT INTO old_asset_register (id, date_of_purchase, code, asset_name, cost, depreciated_value, current_value, Financial_year, asset_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", array('0', $row->date_of_purchase, $row->code, $row->asset_name, $row->cost, $row->depreciated_value, $row->current_value, $row->Financial_year, $row->asset_status)))
                                                {
                                                        $this->messages->add('Failed to add new_Asset Register.', 'error');
                                                        $cf_status = FALSE;
                                                }

                                        }
*/
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in old asset register from new asset register and current active account is  ".$current_active_account);
                                        $selnewassreg = "SELECT * FROM new_asset_register ORDER BY id ASC";
                                        $resultnewassreg = $dsn1->query($selnewassreg);

                                        foreach ($resultnewassreg as $row)
                                        {
            
                                     //       $newassreg1 = $row['id'];
                                            $newassreg2 = $row['date_of_purchase'];
                                            $newassreg3 = $row['code'];
                                            $newassreg4 = $row['asset_name'];
                                            $newassreg5 = $row['cost'];
                                            $newassreg6 = $row['depreciated_value'];
                                            $newassreg7 = $row['current_value'];
                                            $newassreg8 = $row['Financial_year'];
                                            $newassreg9 = $row['asset_status'];
                                                //$dsn->query("INSERT INTO old_asset_register (id, date_of_purchase, code, asset_name, cost, depreciated_value, current_value, Financial_year, asset_status) VALUES ('$newassreg1','$newassreg2','$newassreg3','$newassreg4','$newassreg5' ,'$newassreg6','$newassreg7','$newassreg8','$newassreg9')");
                                                $dsn->query("INSERT INTO old_asset_register (date_of_purchase, code, asset_name, cost, depreciated_value, current_value, Financial_year, asset_status) VALUES ('$newassreg2','$newassreg3','$newassreg4','$newassreg5' ,'$newassreg6','$newassreg7','$newassreg8','$newassreg9')");

                                        }


//                                        $oldacc->from('old_fund_asset_register')->order_by('id','asc');
//                                        $old_asset_register = $oldacc->get();
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in old fund asset register and current active account is  ".$current_active_account);
                                        $seloldfunassreg = "SELECT * FROM old_fund_asset_register ORDER BY id ASC";
                                        $resultoldfunassreg = $dsn1->query($seloldfunassreg);

                                        foreach ($resultoldfunassreg as $row)
                                        {
                                                $oldfunassreg1 = $row['id'];
                                                $oldfunassreg2 = $row['asset_id'];
                                                $oldfunassreg3 = $row['date_of_purchase'];
                                                $oldfunassreg4 = $row['code'];
                                                $oldfunassreg5 = $row['fund_name'];
                                                if( !$dsn->query("INSERT INTO old_fund_asset_register (id, asset_id, date_of_purchase, code, fund_name) VALUES ($oldfunassreg1,$oldfunassreg2,$oldfunassreg3,$oldfunassreg4,$oldfunassreg)"))
                                                {
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in old fund asset register " .$oldfunassreg2 ." and fund name is ".$oldfunassreg ."and current active account is  ".$current_active_account);
                                                        $this->messages->add('Failed to add old_fund_asset register.', 'error');
                                                        $cf_status = FALSE;
                                                }

                                        }
/*
                                        $oldacc->from('new_fund_asset_register')->order_by('id','asc');
                                        $old_asset_register = $oldacc->get();
                                        foreach ($old_asset_register->result() as $row)
                                        {
                                                if( ! $newacc->query("INSERT INTO old_fund_asset_register(id, asset_id, date_of_purchase, code, fund_name) VALUES (?, ?, ?, ?, ?)", array($row->id, $row->asset_id, $row->date_of_purchase, $row->code, $row->fund_name)))
                                                {
                                                        $this->messages->add('Failed to add new_Asset Register.', 'error');
                                                        $cf_status = FALSE;
                                                }

                                        }
*/
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in old fund asset register from new fund asset register and current active account is  ".$current_active_account);
                                        $selnewfunassreg = "SELECT * FROM new_fund_asset_register ORDER BY id ASC";
                                        $resultnewfunassreg = $dsn1->query($selnewfunassreg);

                                        foreach ($resultnewfunassreg as $row)
                                        {
                                               // $newfunassreg1 = $row['id'];
                                                $newfunassreg2 = $row['asset_id'];
                                                $newfunassreg3 = $row['date_of_purchase'];
                                                $newfunassreg4 = $row['code'];
                                                $newfunassreg5 = $row['fund_name'];
                                                //check this code later for not clearence.@sharad3nov
                                                //if( !$dsn->query("INSERT INTO old_fund_asset_register (id, asset_id, date_of_purchase, code, fund_name) VALUES ($newfunassreg1,$newfunassreg2,$newfunassreg3,$newfunassreg4,$newfunassreg)"))
                                                if( !$dsn->query("INSERT INTO old_fund_asset_register (asset_id, date_of_purchase, code, fund_name) VALUES ($newfunassreg2,$newfunassreg3,$newfunassreg4,$newfunassreg)"))
                                                {
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in old fund asset register from new fund asset register " .$newfunassreg2 ." and fund anme is ".$newfunassreg ."and current active account is  ".$current_active_account);
                                                        $this->messages->add('Failed to add old_fund_asset register.', 'error');
                                                        $cf_status = FALSE;
                                                }

					}
					//change start by nks
					$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in old_sponsored_asset_register and current active account is  ".$current_active_account);
                                        $seloldsponassreg = "SELECT * FROM old_sponsored_asset_register ORDER BY id ASC";
                                        $resultoldsponassreg = $dsn1->query($seloldsponassreg);

                                        foreach ($resultoldsponassreg as $row)
                                        {
                                                $oldsponassreg1 = $row['id'];
                                                $oldsponassreg2 = $row['asset_id'];
                                                $oldsponassreg3 = $row['date_of_purchase'];
                                                $oldsponassreg4 = $row['code'];
                                                $oldsponassreg5 = $row['fund_name'];
                                                if( !$dsn->query("INSERT INTO old_sponsored_asset_register (id, asset_id, date_of_purchase, code, fund_name) VALUES ($oldsponassreg1,$oldsponassreg2,$oldsponassreg3,$oldsponassreg4,$oldsponassreg)"))
                                                {
                        $this->logger->write_logmessage("error", "In carry farword methed-  inserting record in old_sponsored_asset_register " .$oldsponassreg2 ." and fund name is ".$oldsponassreg ."and current active account is  ".$current_active_account);
                                                        $this->messages->add('Failed to add old_sponsored_asset_register.', 'error');
                                                        $cf_status = FALSE;
                                                }

                                        }


					$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in old spon asset register from new_sponsored_asset_register and current active account is  ".$current_active_account);
                                        $selnewsponassreg = "SELECT * FROM new_sponsored_asset_register ORDER BY id ASC";
                                        $resultnewsponassreg = $dsn1->query($selnewsponassreg);

                                        foreach ($resultnewsponassreg as $row)
                                        {
                                               // $newfunassreg1 = $row['id'];
                                                $newsponassreg2 = $row['asset_id'];
                                                $newsponassreg3 = $row['date_of_purchase'];
                                                $newsponassreg4 = $row['code'];
                                                $newsponassreg5 = $row['fund_name'];
                                                //if( !$dsn->query("INSERT INTO old_fund_asset_register (id, asset_id, date_of_purchase, code, fund_name) VALUES ($newfunassreg1,$newfunassreg2,$newfunassreg3,$newfunassreg4,$newfunassreg)"))
                                                if( !$dsn->query("INSERT INTO old_sponsored_asset_register (asset_id, date_of_purchase, code, fund_name) VALUES ($newsponassreg2,$newsponassreg3,$newsponassreg4,$newsponassreg)"))
                                                {
                        $this->logger->write_logmessage("error", "In carry farword methed-  inserting record in old spon asset register from new_sponsored_asset_register " .$newsponassreg2 ." and fund anme is ".$newsponassreg ."and current active account is  ".$current_active_account);
                                                        $this->messages->add('Failed to add old_spon_asset register from new_sponsored_asset_register.', 'error');
                                                        $cf_status = FALSE;
                                                }

                                        }

/*				
				if ($oldacc->table_exists('old_sponsored_asset_register'))
                                {
                                        $oldacc->from('old_sponsored_asset_register')->order_by('id','asc');
                                        $old_asset_register = $oldacc->get();
                                        foreach ($old_asset_register->result() as $row)
                                        {
                                                if( ! $newacc->query("INSERT INTO old_sponsored_asset_register (id, asset_id, date_of_purchase, code, fund_name) VALUES (?, ?, ?, ?, ?)", array($row->id, $row->asset_id, $row->date_of_purchase, $row->code, $row->fund_name)))
                                                {
                                                        $this->messages->add('Failed to add old_Asset Register.', 'error');
                                                        $cf_status = FALSE;
                                                }

                                        }

                                        $oldacc->from('new_sponsored_asset_register')->order_by('id','asc');
                                        $old_asset_register = $oldacc->get();
                                        foreach ($old_asset_register->result() as $row)
                                        {
                                                if( ! $newacc->query("INSERT INTO old_sponsored_asset_register(id, asset_id, date_of_purchase, code, project_name) VALUES (?, ?, ?, ?, ?)", array($row->id, $row->asset_id, $row->date_of_purchase, $row->code, $row->project_name)))
                                                {
                                                        $sset_registerthis->messages->add('Failed to add new_Asset Register.', 'error');
                                                        $cf_status = FALSE;
                                                }

                                        }

                                }

*/	
                               /* Importing depreciation_master values */
//                                        $oldacc->from('depreciation_master')->order_by('id','asc');
//                                        $depreciation_master = $oldacc->get();
			$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in depriciation master and current active account is  ".$current_active_account);
                                        $seldeprmaster = "SELECT * FROM depreciation_master ORDER BY id ASC";
                                        $resultdeprmaster = $dsn1->query($seldeprmaster);

                                        foreach ($resultdeprmaster as $row)
                                        {
                                                $depm1 = $row['id'];
                                                $depm2 = $row['parent_id'];
                                                $depm3 = $row['code'];
                                                $depm4 = $row['name'];
                                                $depm5 = $row['percentage'];
                                                $depm6 = $row['life_time'];
                                                if( ! $dsn->query("INSERT INTO depreciation_master (id, parent_id, code, name, percentage, life_time) VALUES ('$depm1','$depm2','$depm3','$depm4','$depm5','$depm6')"))
                                                {
			$this->logger->write_logmessage("error", "In carry farword methed-  inserting record in depreciation master " .$depm4 ." and value is ". $depm5." and current active account is  ".$current_active_account);
                                                        $this->messages->add('Failed to add Depreciation master table. ', 'error');
                                                        $cf_status = FALSE;
                                                }
                                        }





				/* Importing payrollsetup details */
/*commented by sharad
				if ($oldacc->table_exists('payrollsetup'))
				{		
					$oldacc->from('payrollsetup')->order_by('id','asc');
                                	$payset_q = $oldacc->get();
					if(($payset_q->num_rows()) > 0){
        	                        	foreach ($payset_q->result() as $row)
                	        	        {
                		                        if( ! $newacc->query("INSERT INTO payrollsetup (id, code, budgetname, type) VALUES (?, ?, ?, ?)", array($row->id, $row->code, $row->budgetname, $row->type)))
        	                	                {
	                                	                $this->messages->add('Failed to add payrollsetup values ' . $row->budgetname . '.', 'error');
                                                		$cf_status = FALSE;
                                        		}
	                                	}
					}
				}
*/
				/* Importing Secondary unit details */
					//$oldacc->from('addsecondparty')->order_by('id','asc');
//                	$secparty_q = $oldacc->get();
		$this->logger->write_logmessage("insert", "In carry farword methed-  inserting record in addsecondparty and current active account is  ".$current_active_account);
                    $seladdsecparty = "SELECT * FROM addsecondparty ORDER BY id ASC";
                    $resultaddsecparty = $dsn1->query($seladdsecparty);

					//if(($resultaddsecparty->num_rows > 0)
                    //{
	                    foreach($resultaddsecparty as $row)
	        	        {
                            $addsecparty1 = $row['id'];
                            $addsecparty2 = $row['sacunit'];
                            $addsecparty3 = $row['partyname'];
                            $addsecparty4 = $row['mobnum'];
                            $addsecparty5 = $row['email'];
                            $addsecparty6 = $row['address'];
                            $addsecparty7 = $row['permanentaddress'];
                            $addsecparty8 = $row['bancacnum'];
                            $addsecparty9 = $row['bankname'];
                            $addsecparty10 = $row['branchname'];
                            $addsecparty11 = $row['ifsccode'];
                            $addsecparty12 = $row['bankaddress'];
                            $addsecparty13 = $row['pan'];
                            $addsecparty14 = $row['tan'];
                            $addsecparty15 = $row['staxnum'];
                            $addsecparty16 = $row['vat'];
                            $addsecparty17 = $row['gst'];
			    $addsecparty18 = $row['partyrole'];
			    $sopbal=$this->Secunit_model->get_secop_balance1($sec_unit_id);
			    if($sopbal>=0)
				    $sdc="D";
			    else
				    $sdc="C";

                            $addsecparty19 = abs($sopbal);
                            $addsecparty20 = $sdc;
        	        	    if( ! $dsn->query("INSERT INTO addsecondparty (id, sacunit, partyname, mobnum, email, address, permanentaddress, bancacnum, bankname,  branchname, ifsccode, bankaddress, pan, tan, staxnum, vat, gst, partyrole, opbal, dc) VALUES ('$addsecparty1', '$addsecparty2', '$addsecparty3', '$addsecparty4', '$addsecparty5', '$addsecparty6', '$addsecparty7', '$addsecparty8', '$addsecparty9', '$addsecparty10', '$addsecparty11', '$addsecparty12', '$addsecparty13', '$addsecparty14', '$addsecparty15', '$addsecparty16', '$addsecparty17', '$addsecparty18', '$addsecparty19', '$addsecparty20')"))
                	        {
			$this->logger->write_logmessage("insert", "In carry farword methed- inserting record in addsecondparty " .$addsecparty3 ." and op bal is ".$addsecparty19 ."and current active account is  ".$current_active_account);
                        	    $this->messages->add('Failed to add addsecondparty values ' . $addsecparty3 . '.', 'error');
                                $cf_status = FALSE;
	                        }
	        	        }
					//}
				
				/* After succesfull carry farward old account will be locked for further use. */
                
				//$update_data = array('account_locked'=> '1');
                $updateacct = "UPDATE settings SET account_locked = '1' where id='1'";
                $resultaddsecparty = $dsn1->query($updateacct);
                if(! $resultaddsecparty)
                {
                    $this->messages->add('Error in locking account.', 'error');
                }
                else
                {
                    $this->messages->add('Account is locked', 'success');
                }
/*
				if ( ! $oldacc->where('id', '1')->update('settings', $update_data))
                {
                    $oldacc->trans_rollback();
                    $this->messages->add('Error in locking account.', 'error');
                } else {
				    $oldacc->trans_complete();
                    $this->messages->add('Account is locked', 'success');
                }
*/
				if ($cf_status)
					$this->messages->add('Account carried forward.', 'success');
				else
					$this->messages->add('Error carrying forward to new account.', 'error');
	
			$this->logger->write_logmessage("insert", "In carry farword methed-  last carry farword status " .$cf_status ." and current active account is  ".$current_active_account);
				$this->messages->add('Income schedules xml created'.$current_active_account.$Pre_year.$last_year, 'success');
				$this->messages->add('Expenditure schedule xml created'.$current_active_account.$Pre_year.$last_year, 'success');
				$this->messages->add('I/E and Payment/Receipt xml created'.$current_active_account.$Pre_year.$last_year, 'success');
				/* Adding account settings to file. Code copied from manage controller */
/*				$con_details = "[database]" . "\r\n" . "db_type = \"" . $data_database_type . "\"" . "\r\n" . "db_hostname = \"" . $data_database_host . "\"" . "\r\n" . "db_port = \"" . $data_database_port . "\"" . "\r\n" . "db_name = \"" . $data_database_name . "\"" . "\r\n" . "db_username = \"" . $data_database_username . "\"" . "\r\n" . "db_password = \"" . $data_database_password . "\"" . "\r\n";

				$con_details_html = '[database]' . '<br />db_type = "' . $data_database_type . '"<br />db_hostname = "' . $data_database_host . '"<br />db_port = "' . $data_database_port . '"<br />db_name = "' . $data_database_name . '"<br />db_username = "' . $data_database_username . '"<br />db_password = "' . $data_database_password . '"<br />'r

				/* Writing the connection string to end of file - writing in 'a' append mode /
				if ( ! write_file($ini_file, $con_details))
				{
					$this->messages->add('Failed to add account settings file. Check if "' . $ini_file . '" file is writable.', 'error');
					$this->messages->add('You can manually create a text file "' . $ini_file . '" with the following content :<br /><br />' . $con_details_html, 'error');
				} else {
					$this->messages->add('Added account settings file to list of active accounts.', 'success');
				}
*/
				}
				redirect('setting');
				return;
		}
		redirect("setting");
		return;
	}//end of Index method

}//end of class
/* End of file cf.php */
/* Location: ./system/application/controllers/setting/cf.php */
