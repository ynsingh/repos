<?php
   if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Payment extends CI_Controller {

function __construct() {
        parent::__construct();
		$this->load->model('Entry_model');
                $this->load->model('Ledger_model');
                $this->load->model('Tag_model');
                $this->load->model('Payment_model');
                $this->load->model('Budget_model');
                $this->load->model('Secunit_model');
                $this->load->library('GetParentlist');
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('user/login');
        }
    }

        function Payment()
        {
                parent::Controller();
                //$this->load->model('Entry_model');
                //$this->load->model('Ledger_model');
                //$this->load->model('Tag_model');
		//$this->load->model('Payment_model');
		//$this->load->model('Budget_model');
		//$this->load->model('Secunit_model');
		//$this->load->library('GetParentlist');
                return;
        }

        function index()
        {
                redirect('entry/show/payment');
                return;
        }
	
	function fileupload()
        {
	//	$path= "";
		$filename="";	
		$path=getcwd().'/uploads/BGAS/Bills';
	//	echo"path is=$path";
		if (!is_dir('uploads/BGAS/Bills')) {
                       mkdir('./uploads/BGAS/Bills');
			chmod('./uplaods/BGAS/Bills',0777);

                }

                $config['upload_path'] = $path;
                $config['allowed_types'] = 'gif|jpg|jpeg|png|pdf|';
                $config['max_size'] = '1000';
                $config['max_width'] = '1920';
                $config['max_height'] = '1280';
                $this->load->library('upload', $config);
		 if(!$this->upload->do_upload()) 
			$this->upload->display_errors();
                 else {
                	$fInfo = $this->upload->data();
                        $filename=$fInfo['file_name'];
              	}
                return $filename;
        }

	
	function showupload_bill()
	{	
		$this->template->set('page_title', 'View Bill');	
		$this->template->set('nav_links', array( 'payment/bill_upload/' =>'Bill Upload'));
		$this->load->helper("url");
                $this->load->library('pagination');
	/*	$this->db->from('bill_approval');
                $bdetail = $this->db->get();
                $data['bill_data'] = $bdetail;*/

                $page_count = (int)$this->uri->segment(4);
                //$page_count = $this->input->xss_clean($page_count);
		$page_count =$this->security->xss_clean($page_count);
                if ( ! $page_count)
                       $page_count = "0";
                $config['base_url'] = site_url('payment/showupload_bill/all');
                $pagination_counter = $this->config->item('row_count');
                 $config['uri_segment'] = 4;
                $config['num_links'] = 10;
                $config['per_page'] = $pagination_counter;
                $config['full_tag_open'] = '<ul id="pagination-flickr">';
                $config['full_close_open'] = '</ul>';
                $config['num_tag_open'] = '<li>';
                $config['num_tag_close'] = '</li>';
                $config['cur_tag_open'] = '<li class="active">';
                $config['cur_tag_close'] = '</li>';
                $config['next_link'] = 'Next &#187;';
                $config['next_tag_open'] = '<li class="next">';
                $config['next_tag_close'] = '</li>';
                $config['prev_link'] = '&#171; Previous';
                $config['prev_tag_open'] = '<li class="previous">';
                $config['prev_tag_close'] = '</li>';
                $config['first_link'] = 'First';
                $config['first_tag_open'] = '<li class="first">';
                $config['first_tag_close'] = '</li>';
                $config['last_link'] = 'Last';
                $config['last_tag_open'] = '<li class="last">';
                $config['last_tag_close'] = '</li>';
                $data["links"] = $this->pagination->create_links();
		$data["bill_data"] = $this->Payment_model->bill_uploadvalues($pagination_counter, $page_count);
                $config['total_rows'] = $this->Payment_model->record_count();

                $this->pagination->initialize($config);
 //code for search             
/*	 	$text = '';
                $data['search'] = '';
                $data['search_by'] = array(
                        "Select" => "Select",
                        "bill_no" => "Bill No",
                        "submit_date"=> "Submit Date",
                        "expense_type"=> "Expense Type",
                        "total_amount"=> "Total Amount",
                        "submitter_id"=> "Submitter ID",
                        "decision"=> "Decision",
                        "approval_date"=> "Approval Date",
                        "approved_amount"=> "Approved Name",
			"approved_by"=> "Approved By",
                        "vc_date"=> "Voucher Creation Date",
                        "bank_cash_account"=> "Bank Cash Account",
                        "mode_of_payment"=> "Payment Mode",
			"payment_status"=> "Payment Status",
                        "payment_date"=> "Payment Date",

                );
                $data['search_by_active'] = '';

                $data['text'] = array(
                        'name' => 'text',
                        'id' => 'text',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
                if ($_POST)
                {
                        $data['search_by_active']['value'] = $this->input->post('search_by', TRUE);
                        $data['text']['value'] = $this->input->post('text', TRUE);
                }
                /* Form validation */

  //              $this->form_validation->set_rules('search_by', 'Search By', 'trim|required');
    //            $this->form_validation->set_rules('text', 'Text', 'trim|required');
                /* Validating form */

      /*          if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'payment/showupload_bill', $data);
                        return;
                }
                else
                {
                        $data_search_by = $this->input->post('search_by', TRUE);
                        $data_text = $this->input->post('text', TRUE);
                }
             
	
		   if($data_search_by == "Select")
                {
                        $this->messages->add('Please select search type from dropdown list.', 'error');
			 redirect('payment/showupload_bill');
                }
               else {
                                // bill no should be a number
                               if($data_search_by == "bill_no") {
                                        $search_text = $data_text;
                                        if(! ctype_alnum($data_text)) {
                                                $this->messages->add('Please enter a number.', 'error');
                                                redirect('payment/showupload_bill');
                                        }
                                }
            /*                    if($data_search_by == "submitter_id" || $data_search_by == "approved_by") {
                                        $search_text = $data_text;
                                        // submitter and verifier should be alphanumeric
                                        if(! ctype_alpha($data_text)) {
                                                $this->messages->add('Please enter alphanumeric value.', 'error');
                                                redirect('payment/showupload_bill']);
                                        }
                                }
		
	/*			if($data_search_by == "submit_date" || $data_search_by == "approval_date" || $data_search_by == "payment_date" || $data_search_by == "vc_date")
                                {
                                        $search_text = $data_text;
                                        // if date and update date is single digit
                                        if(ctype_digit($data_text)) {
                                                $field = $data_search_by . '      ' . 'LIKE';
                                        }
                                        else {
                                                $date=explode(' ', $data_text);
                                                // if date and update format is dd mm yy only
                                                if(count($date)>1) {
                                                        // if date and update date contain two digit
                                                        if(count($date) == '2') {
                                                                // if month is character
                                                                if(ctype_alpha($date['1'])) {
                                                                        if(strtotime($date['1'])) {
                                                                                $month = $date['1'];
                                                                                $x = date('m', strtotime($month));
                                                                                $data_text = $x . "-" . $date[0];
                                                                                $field = $data_search_by . '      ' . 'LIKE';
                                                                        }
                                                                        else {
                                                                                $this->messages->add('Invalid Month.', 'error');
                                                                                redirect('payment/showupload_bill');
                                                                        }
                                                                }
                                                                // if month is digit
                                                                else if(ctype_digit($date['1'])) {
                                                                        // if month is valid or not
                                                                        if("1" <= $date['1'] && $date['1'] <= "12") {
                                                                                $field = $data_search_by . '      ' . 'LIKE';
                                                                                $data_text = $date[1]. "-" . $date[0];
                                                                        }
                                                                        else {
                                                                                $this->messages->add('Invalid Month.', 'error');
                                                                                redirect('payment/showupload_bill');
                                                                        }
                                                                }
                                                                // if date is unvalid
                                                                else {
                                                                        $this->messages->add('Invalid date format. Please enter date in dd mm yy format.', 'error');
                                                                        redirect('payment/showupload_bill');
                                                                }
                                                        }
                                                        // if date and update date contain three digit
                                                        if(count($date) == '3') {
                                                                $date0 = $date['0'];
                                                                $x = $date['1'];
                                                                $date2 = $date['2'];
                                                                //it converts month name to digit
                                                                if(ctype_alpha($date['1'])) {
                                                                        $month = $date['1'];
                                                                        $x = date('m', strtotime($month));
                                                                        $data_text = $date[2]. "-" . $x . "-" . $date[0];
                                                                }
                                                                $data_text = $date0. "-" . $x . "-" . $date2;
                                                                //check for date is valid or not
                                                                $valid_date = checkdate($x,$date0,$date2);
                                                                if($valid_date == 'true') {
                                                                        //check date is exist in financial year or not
                                                                        if($date2 == $fy_start || $date2 == $fy_end) {
                                                                                $data_text = $date2."-". $x ."-".$date0;
                                                                        }
                                                                        else {
                                                                                $this->messages->add($data_text . ' does not exist in financial year.', 'error');
                                                                                redirect('payment/showupload_bill');
                                                                        }
                                                                }
                                                                else {
                                                                        $this->messages->add($data_text . ' is invalid date.', 'error');
                                                                        redirect('payment/showupload_bill');
                                                                }
                                                        }
                                                }
                                                else {
                                                        $this->messages->add('Invalid date format. Please enter date in dd mm yy format.', 'error');
                                                        redirect('payment/showupload_bill');
                                                }
                                        }
                                }

				if($data_search_by == "total_amount" || $data_search_by == "approved_amount") {
                                        $search_text = $data_text;
                                        // these amount total should be number or decimal
                                        if(! is_numeric($data_text)) {
                                                $this->messages->add('Please enter a number.', 'error');
                                                redirect('payment/showupload_bill');
                                        }
                                }
				if($data_search_by == "decision" || $data_search_by == "mode_of_payment" || $data_search_by == "payment_status") {
                                        $search_text = $data_text;
                                        // these should be alphabatic
                                        if(! ctype_alpha($data_text)) {
                                                $this->messages->add('Please enter a number.', 'error');
                                                redirect('payment/showupload_bill');
                                        }
                                }

*/
    //		}
       /*         $field = $data_search_by . '      ' . 'LIKE';
                $text = $data_text;
              //  $data["bill_data"] = $this->Payment_model->bill_uploadvalues($pagination_counter, $page_count, $field,'%' . $text . '%');

                $this->db->from('bill_approval')->where($field, '%' . $text . '%');//->order_by('date', 'desc')->order_by('id', 'desc');
                $b_detail = $this->db->get();
                $data['bill_data'] = $b_detail;
                if( $b_detail->num_rows() < 1 )
                {
                        $this->messages->add($text . ' is not found.', 'error');
                        redirect('payment/showupload_bill');
                }
                $data['search'] = $data_search_by;	*/
		$this->template->load('template', 'payment/showupload_bill', $data);
		return;
                
	}
	
	function bill_upload()
	{
		/* Check access */
                if ( ! check_access('bill upload'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('payment/showupload_bill');
                        return;
                }
		$this->load->library('form_validation');
		$this->template->set('page_title', 'Bill Upload');
		$data['expenses']= $this->Ledger_model->get_codewise_ledgers();
		$data['expenses_active']=0;
		$data['secunit_active']=0;
		$data['secunit']=$this->Secunit_model->get_all_secunitid();
		$data['submitted_by'] = array(
                        'name' => 'submitted_by',
                        'id' => 'submitted_by',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
		$data['totalamount'] = array(
                        'name' => 'totalamount',
                        'id' => 'totalamount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
		/* Form validations */
		$this->form_validation->set_rules('submitted_by', 'SUBMITTER', 'trim|valid_emails|required');
                $this->form_validation->set_rules('totalamount', 'TOTAL AMOUNT', 'trim|required|min_length[1]|max_length[100]');
                $this->form_validation->set_rules('expenses', 'EXPENSES', 'trim|required');
		$this->form_validation->set_rules('secunit', 'SECONDARY UNIT ID', 'trim|required');
		/* Re-populating form */
		if ($_POST)
                {
                        $data['submitted_by']['value'] = $this->input->post('submitted_by', TRUE);
                        $data['totalamount']['value'] = $this->input->post('totalamount', TRUE);
                        $data['expenses_active'] = $this->input->post('expenses', TRUE);
			 $data['secunit_active'] = $this->input->post('secunit', TRUE);
                }
	/*validating form*/	
		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'payment/bill_upload', $data);
                        return;
                }
		else
                {
                        $expenses=$this->input->post('expenses',TRUE);
			$submitted_by = $this->input->post('submitted_by', TRUE);
                        $total_amount = $this->input->post('totalamount',TRUE);
                        $today = date("Y-m-d H:i:s");
                        $username=$this->session->userdata['user_name'];
			$filename=$this->fileupload();
			$secunit = $this->input->post('secunit', TRUE);
		  if($filename == ""){
				$this->messages->add('Please select a File having extension gif | jpg | jpeg | png | pdf |', 'error');
                                $this->template->load('template', 'payment/bill_upload', $data);
                                return;
				}
                  if($expenses == 0)
                        {
                                $this->messages->add('Please Select Expense Type From Dropdown List.', 'error');
                                $this->template->load('template', 'payment/bill_upload', $data);
                                return;
                                }

			$this->db->trans_start();
                        $insert_data = array(
                                'submit_date' => $today,
                                'submitted_by' => $submitted_by,
                                'expense_type' => $expenses,
                                'bill_name'    => $filename,
                                'submitter_id' => $username,
                                'total_amount' => $total_amount,
				'secunitid' => $secunit,
                                );
				
	//		$ledger_id = explode("-", $expenses);
			if ( ! $this->db->insert('bill_approval', $insert_data))
                      	{
	                        $this->db->trans_rollback();
				$this->messages->add('Error upload bill. '.$username.$submitter_id.$total_amount.'error');
                                $this->logger->write_message("error",'Error upload bill'.$username.$submitter_id.$total_amount.'error');
                                $this->template->load('template', 'payment/bill_upload', $data);
                                return;
                        } else {
                                 $this->db->trans_complete();
				 $this->messages->add('Bill Is Uploaded Successfully. ');
                                 $this->logger->write_message("success",'Bill Is Uploaded Successfully. ');
                                 redirect('payment/showupload_bill');
                                 return;

                        }
		}
			$this->template->load('template', 'payment/bill_upload',$data);
                        return;
	}
	

	function show_bill_approval()
	{
		$this->template->set('page_title', 'show Bills For Approval/Rejection');
		$this->load->helper("url");
                $this->load->library('pagination');
                $page_count = (int)$this->uri->segment(4);
                //$page_count = $this->input->xss_clean($page_count);
                $page_count = $this->security->xss_clean($page_count);
                if ( ! $page_count)
                       $page_count = "0";
                $config['base_url'] = site_url('payment/show_bill_approval/all');
                $pagination_counter = $this->config->item('row_count');
                 $config['uri_segment'] = 4;
                $config['num_links'] = 10;
                $config['per_page'] = $pagination_counter;
                $config['full_tag_open'] = '<ul id="pagination-flickr">';
                $config['full_close_open'] = '</ul>';
                $config['num_tag_open'] = '<li>';
                $config['num_tag_close'] = '</li>';
                $config['cur_tag_open'] = '<li class="active">';
                $config['cur_tag_close'] = '</li>';
                $config['next_link'] = 'Next &#187;';
                $config['next_tag_open'] = '<li class="next">';
                $config['next_tag_close'] = '</li>';
                $config['prev_link'] = '&#171; Previous';
                $config['prev_tag_open'] = '<li class="previous">';
                $config['prev_tag_close'] = '</li>';
                $config['first_link'] = 'First';
                $config['first_tag_open'] = '<li class="first">';
                $config['first_tag_close'] = '</li>';
                $config['last_link'] = 'Last';
                $config['last_tag_open'] = '<li class="last">';
                $config['last_tag_close'] = '</li>';
                $data["links"] = $this->pagination->create_links();
                $data["bill_data"] = $this->Payment_model->bill_uploadvalues($pagination_counter, $page_count);
                $config['total_rows'] = $this->Payment_model->record_count();
		$this->pagination->initialize($config);
		$this->template->load('template', 'payment/show_bill_approval',$data);
		return;	
	}
		
	 function billapproval($bill_no)
          {
			/* Check access */
                if ( ! check_access('approve/reject'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('payment/showupload_bill');
                        return;
                }
			$this->load->library('form_validation');
                        $this->template->set('page_title', 'Bill Approval Form');
                        $this->db->select('bill_no,submit_date,submitted_by,bill_name,total_amount,expense_type,secunitid')->from('bill_approval')->where('bill_no',$bill_no);
			$username = $this->config->item('account_name');
                        $bill_r = $this->db->get();
                        $bill_app = $bill_r->row();

			$data['decision_active']="Select";
			$data['exp_type_active']="Select";
			$data['fund'] = $this->Ledger_model->get_fund_ledgers();
			 $data['fund_active']="0";
                        $data['bill_no'] = array(
                        'name' => 'bill_no',
                        'id' => 'bill_no',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->bill_no,
                        'readonly'=>'true',
                        );
                      /*  $data['bill_name'] = array(
                        'name' => 'bill_name',
                        'id' => 'bill_name',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->bill_name,
                        'readonly'=>'true',
                        );*/
			$data['bill_name']= $bill_app->bill_name;
                        $data['submitted_by'] = array(
                        'name' => 'submitted_by',
                        'id' => 'submitted_by',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->submitted_by,
                        'readonly'=>'true',
			);
			$data['total_amount'] = array(
                        'name' => 'total_amount',
                        'id' => 'total_amount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->total_amount,
                        'readonly'=>'true',
                         );
                        $data['expensestype'] = array(
                        'name' => 'expensestype',
                        'id' => 'expensestype',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$bill_app->expense_type,
                        'readonly'=>'true',
                        );
			$data['decision']=array(
			'Approved' =>'Approve',
			'Rejected'  =>'Reject',
			'Select' =>'Select',
			);
			$data['exp_type']=array(
                        'Select' =>'Select',
			'Capital' => 'Capital Expenditure',
                        'Revenue' => 'Revenue Expenditure',
                        );

			$data['approved_amount'] = array(
                        'name' => 'approved_amount',
                        'id' => 'approved_amount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$bill_app->total_amount,
                        );
                        $data['being'] = array(
                        'name' => 'being',
                        'id' => 'being',
                        'cols' => '50',
                        'rows' => '4',
                        'value' => '',
                        );
                        $data['secunitid'] = array(
                        'name' => 'secunitid',
                        'id' => 'secunitid',
                        'maxlength' => '10',
                        'size' => '10',
                        'value' =>$bill_app->secunitid,
			'readonly'=>'true',
                        );
	              /*  $data['sanc_letter_no'] = array(
                        'name' => 'sanc_letter_no',
                        'id' => 'sanc_letter_no',
                        'maxlength' => '255',
                        'size' => '11',
                        'value' => ''
        	        );

                	$data['sanc_letter_date'] = array(
                        'name' => 'sanc_letter_date',
                        'id' => 'sanc_letter_date',
                        'maxlength' => '11',
                        'size' => '11',
                        'value' => date_today_php()
               		 );*/

                	$data['sanc_type'] = array(
                        'select' => 'Select',
                        'plan' => 'Plan',
                        'non_plan' => 'Non Plan'
                     	 );

                        $data['active_sanc_type'] = 'select';

              		$data['plan'] = array(
                        'select' => 'Select',
                        'General OH:35' => 'General OH:35',
                        'General OH:31' => 'General OH:31',
                        'SCSP OH:35' => 'SCSP OH:35',
                        'SCSP OH:31' => 'SCSP OH:31',
                        'TSP OH:35' => 'TSP OH:35',
                        'TSP OH:31' => 'TSP OH:31'
			);

			$data['active_plan'] = 'select';

	                $data['non_plan'] = array(
                        'select' => 'Select',
                        'Salary OH:36' => 'Salary OH:36',
                        'Pension And Pensionary Benefit OH:31' => 'Pension And Pensionary Benefit OH:31',
                        'Non Salary OH:31' => 'Non Salary OH:31'
        	        );

                	$data['active_non_plan'] = 'select';

                       $datal['bill_no'] = $bill_no;
                       $data['date'] = date("Y-m-d H:i:s");
			/* Form validations */
			$this->form_validation->set_rules('decision', 'Approve/Reject', 'trim|required');            
			//$this->form_validation->set_rules('approved_amount', 'APPROVED AMOUNT', 'trim|required|min_length[1]|max_length[100]');
                        $this->form_validation->set_rules('being','Being', 'trim|required');
		//	$this->form_validation->set_rules('fund','Select Fund', 'trim|required');
		//	$this->form_validation->set_rules('exp_type','Expenditure Type', 'trim|required');
			/* Re-populating form */
                        if ($_POST)
                        {
                        $data['bill_no']['value'] = $this->input->post('bill_no', TRUE);
                //        $data['bill_name']['value'] = $this->input->post('bill_name', TRUE);
                        $data['submitted_by']['value'] = $this->input->post('submitted_by', TRUE);
                        $data['total_amount']['value'] = $this->input->post('total_amount', TRUE);
                        $data['expensestype']['value']=$this->input->post('expensestype', TRUE);
			$data['decision_active']['value']=$this->input->post('decision',TRUE);
                        $data['approved_amount']['value'] = $this->input->post('approved_amount', TRUE);
                        $data['being']['value'] = $this->input->post('being', TRUE);
			$data['secunitid']['value'] = $this->input->post('secunitid', TRUE);
			$data['fund_active']['value'] = $this->input->post('fund', TRUE);
			$data['exp_type_active']['value'] = $this->input->post('exp_type', TRUE);
			  if($data['active_sanc_type'] != 'select'){
                                if($data['active_sanc_type'] == 'plan')
                                        $data['active_plan'] = $this->input->post('plan', TRUE);
                                else
                                        $data['active_non_plan'] = $this->input->post('non_plan', TRUE);
                        }

                        }
	//		print_r($data);
       /*form validation*/
                        if ($this->form_validation->run() == FALSE)
                        {
                                $this->messages->add(validation_errors(), 'error');
                                $this->template->load('template', 'payment/billapproval', $data);
                                return;

                        }
			else{
                                $bill_name = $this->input->post('bill_name', TRUE);
				$bill_no = $this->input->post('bill_no', TRUE);
                                $submitted_by=$this->input->post('submitted_by', TRUE);
                                $expenses=$this->input->post('expensestype',TRUE);
                                $my_values = explode('.',$expenses);
                                $ids=$my_values[0];
				$decision=$this->input->post('decision', TRUE);
				$approved_amount = $this->input->post('approved_amount', TRUE);
				$fund = $this->input->post('fund', TRUE);
				$exp_type = $this->input->post('exp_type', TRUE);
				$data_amount = $approved_amount;
                                $sanc_value = '';
                                $data_sanc_type = $this->input->post('sanc_type', TRUE);
                                if($data_sanc_type != 'select'){
                                        if($data_sanc_type == 'plan')
                                                $sanc_value = $this->input->post('plan', TRUE);
                                        else
                                                $sanc_value = $this->input->post('non_plan', TRUE);
                                }
				if($fund !=0 && $exp_type == "Select")
				{
				$this->messages->add('Please Select Expenditure Type From Dropdown List.', 'error');
                                $this->template->load('template', 'payment/billapproval', $data);
                                return;

				}
				if($data_sanc_type != 'select')
				{
				    if($data_sanc_type == "plan" && $sanc_value == "select") 
				      {   
					$this->messages->add('Please Select Plan Sanction From Dropdown List.', 'error');
                                	$this->template->load('template', 'payment/billapproval', $data);
                                	return;
					}
				    if($data_sanc_type == "plan" && $sanc_value == "select")
                                      {  
                                        $this->messages->add('Please Select Plan Sanction From Dropdown List.', 'error');
                                        $this->template->load('template', 'payment/billapproval', $data);
                                        return;
                                        }
				}
				if($decision== "Select")
                                {
                                $this->messages->add('Please Select Decision From Dropdown List.', 'error');
                                $this->template->load('template', 'payment/billapproval', $data);
                                return;
                                }

				if($decision=='Approved'){
					$this->db->from('ledgers')->where('id', $ids);
					$query_q = $this->db->get();
		                        $query_n = $query_q->row();
                		        $this->id = $query_n->id;
                        		$this->code = $query_n->code;
					$this->group_id = $query_n->group_id;
					$ledg_code=$this->code;
					$groupid=$this->group_id;
					$id=$this->id;
					/**
                                         * To identify "expense" entry, code of the selected ledger head
                                         * is being fetched. If the ledger head has "Expenses" as its 
                                         * super parent and the account is being debited, then it must
                                         * be a "expense" entry.
					 * Then, the corresponding budget amount is updated. Since,
					 * the budget amount is set for "expense" only.
                                         * Lines added by Priyanka
                                         */
					$this->load->model('Budget_model');
                                        $account_code = $this->Budget_model->get_account_code('Expenses');
					$temp = $this->startsWith($ledg_code, $account_code);
                                        if($temp)
					{//01	
						//get budget amnt 
						$parents;
						$query1=$this->db->from('budgets')->where('code', $ledg_code)->get();

						/**
						* code for if particular head is not in budget,
						* then made payment from parent which is present
						* in budget.
						*/
						if($query1->num_rows() > 0)
				                {
							$this->db->from('budgets')->where('code', $ledg_code);
				                        $query_l = $this->db->get();
				                        $query_l = $query_l->row();
				                        $this->amt = $query_l->bd_balance;
							$this->useamt = $query_l->approval_amount;
							$this->consumeamt = $query_l->consume_amount;
							$this->allow=$query_l->allowedover;
				                        $budgetamt=$this->amt;
							$useamt=$this->useamt;
							$consumeamt=$this->consumeamt;
							$allow=$this->allow;

							if($budgetamt > $useamt)
							{//if1
								$available_amount=$budgetamt - $useamt;//its wrong

								/**  payment amount is greater than or equal to available amount **/

								if($data_amount > $available_amount)
								{
									/* check for allowed over expense*/
									if(($allow == -1) || ($allow == 0))
		                                        		{
		                                                		$this->messages->add('Budget is not sufficient to make this payment.','error');
		                                               		 	$this->template->load('template', 'payment/billapproval',$data);
		                                                		return;
		                                        		}
									else
									{
										/* check for payment amount by adding allowd over amount + consume amount */
										$available_amount = $budgetamt - $useamt + $allow;
										if($data_amount >= $available_amount)
										{
			                                                      		$this->messages->add('Budget is not sufficient to make this payment.','error');
			                                                        	$this->template->load('template', 'payment/billapproval',$data);
		        	                                                	return;
										}
										else
										{
											/* Update budget table */
											$sumamt=$data_amount + $useamt;
											$sumamt1=$data_amount + $consumeamt;
											$allow_left = $available_amount - $data_amount ;
											$update_data1 = array('consume_amount' => $sumamt1, 'approval_amount' => $sumamt, 'allowedover' => $allow_left);
								                	if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
		                							{
		                        							$this->db->trans_rollback();
		                        							$this->messages->add('Error updating total expenses amount in budget.', 'error');
		                        							$this->template->load('template', 'payment/billapproval', $data);
		                        							return;
		                							}
											$parents = new GetParentlist();
						                                	$parents->init($groupid,$data_amount);

											$this->db->from('budgets')->where('budgetname', 'Main Budget');
								                        $query_ll = $this->db->get();
				                				        $query_ll = $query_ll->row();
								                        $this->amt1 = $query_ll->bd_balance;
				                				        $this->useamt1 = $query_ll->approval_amount;
				                				        $this->consumeamt1 = $query_ll->consume_amount;
											$update_data2 = $this->useamt1 + $data_amount;
											$update_data21 = $this->consumeamt1 + $data_amount;
											$update_data3 = array('approval_amount' => $update_data2, 'consume_amount' => $update_data21);

											if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
				                                                        {
				                                                                $this->db->trans_rollback();
				                                                                $this->messages->add('Error updating total expenses amount in budget.', 'error');
				                                                                $this->template->load('template', 'payment/billapproval', $data);
				                                                                return;
				                                                        }
										}							
									}
								}
								else
								{
									$sumamt=$data_amount + $useamt;
									$sumamt1=$data_amount + $consumeamt;
									$update_data1 = array('approval_amount' => $sumamt, 'consume_amount' => $sumamt1);
		                                                	if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
		                                                	{
		                                                        	$this->db->trans_rollback();
		                                                        	$this->messages->add('Error updating total expenses amount in budget.', 'error');
		                                                                $this->template->load('template', 'payment/billapproval', $data);
		                                                                return;
		                                                	}
									$parents = new GetParentlist();
				                                	$parents->init($groupid,$data_amount);

									$this->db->from('budgets')->where('budgetname = ', 'Main Budget');
		                                                	$query_ll = $this->db->get();
		                                                	$query_ll = $query_ll->row();
		                                                	$this->amt1 = $query_ll->bd_balance;
		                                                	$this->useamt1 = $query_ll->approval_amount;
				                			$this->consumeamt1 = $query_ll->consume_amount;
		                                                	$update_data2 = $this->useamt1 + $data_amount;
									$update_data21 = $this->consumeamt1 + $data_amount;
		                                                	$update_data3 = array('approval_amount' => $update_data2, 'consume_amount' => $update_data21);

									if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
				                         		{
				                                        	$this->db->trans_rollback();
				                                                $this->messages->add('Error updating total expenses amount in budget.', 'error');
				                                                $this->template->load('template', 'payment/billapproval', $data);
				                                                return;
				                                        }
								}
							}//1	
							/* consume amount is greater than allocated budget amount*/ 
							if($useamt >= $budgetamt)
							{//2
								/* check for allowed over expenses */
								if(($allow == -1) || ($allow == 0))
			                                	{
		                                        		$this->messages->add('Budget is not sufficient to make this payment.','error');
		                                        		return;
								}
								/** get over consume amount and check with allowed left **/						
								$overconsume_amount = $useamt - $budgetamt ;
								/* payment amount is greater than allowed over amount*/
								if($data_amount > $allow)
								{
			                                        	$this->template->load('template', 'payment/billapproval',$data);
									return;
								}
								/* payment amount is less than allowed over amount*/
								if($data_amount <= $allow)
								{
									$overconsume_amount = $useamt - $budgetamt ;
									$available_amount = $allow ;
									$allowed_left = $allow - $data_amount;
									$consume_amount = $useamt + $data_amount;
									$consume_amount1 = $consumeamt + $data_amount;
									$update_data1 = array('consume_amount' => $consume_amount1, 'approval_amount' => $consume_amount, 'allowedover' => $allowed_left);
		                                                	if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
		                                                	{
		                                                	        $this->db->trans_rollback();
		                                                	        $this->messages->add('Error updating total expenses amount in budget.', 'error');
		                                                	        $this->template->load('template', 'payment/billapproval', $data);
		                                                	        return;
		                                                	}
									$parents = new GetParentlist();
				                                	$parents->init($groupid,$data_amount);

									$this->db->from('budgets')->where('budgetname', 'Main Budget');
		                                                	$query_ll = $this->db->get();
		                                                	$query_ll = $query_ll->row();
		                                                	$this->amt1 = $query_ll->bd_balance;
		                                                	$this->useamt1 = $query_ll->approval_amount;
				                			$this->consumeamt1 = $query_ll->consume_amount;
									$update_data21 = $this->consumeamt1 + $data_amount;
		                                                	$update_data2 = $this->useamt1 + $data_amount;
		                                                	$update_data3 = array('approval_amount' => $update_data2, 'consume_amount' => $update_data21);

									if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
			                                        	{
		         	                                	       $this->db->trans_rollback();
		                                                	       $this->messages->add('Error updating total expenses amount in budget.', 'error');
		                                                	       $this->template->load('template', 'payment/billapproval', $data);
		                                                	       return;
		                                                	}
								}	
							}//2
						}
						else
						{   
							$this->db->from('groups')->where('id', $groupid);
                                                        $group_q = $this->db->get();
                        				$group = $group_q->row();
                       					$this->parent_id = $group->parent_id;
							$parent_id = $this->parent_id;
						//	$parents_get ="";
		                        		$parents_get=$this->init1l($id,$groupid,$data_amount,$data,$parent_id);
							
						}
					}//01	
				
                                $billno=$bill_no;
                                $being=$this->input->post('being', TRUE);
                                $today = date("Y/m/d H:i:s");
				$date = explode('/', $today);
				$data_date = $date[0] .'-' . $date[1] .'-' .$date[2];
                                $approvedby=$this->session->userdata['user_name'];
				//$this->load->library('sendEmail');
                                  //      $asset = new SendEmail();
                                    //    $asset->init($submitted_by,$being,$approvedby,$decision);
				//$this->db->select_max('number')->from('entries')->where('entry_type', '2');
				//$bill_r = $this->db->get();
	/* megha		$this->db->select_max('number')->from('entries')->where('entry_type', '2');
				$bill_r = $this->db->get();
				$number;
				foreach($bill_r->result() as $row) {
					$number = $row->number;
					$number = $number + 1;
				}
                                        $this->db->trans_start();
					$insert_data=array(
					'entry_type' =>'2',
					'date' 	     => $today,
					'update_date' => $today,
					'forward_refrence_id'  => '0',
					'backward_refrence_id' => '0',
					'submitted_by' => $approvedby,
					'cr_total' => $approved_amount,
					'dr_total' => $approved_amount,
					'number' => $number,
					);
					if ( ! $this->db->insert('entries', $insert_data))
                        		{
                                		$this->db->trans_rollback();
                                		$this->messages->add('Error addding Entry.', 'error');
                                		$this->template->load('template', 'payment/billapproval', $data);
                                		return;
                        		} else {
						$entry_id = $this->db->insert_id();
                        		}
					$insert_ledger_data = array(
                                        'entry_id' => $entry_id,
                                        'ledger_id' =>'170',
                                        'amount' => $approved_amount,
                                        'dc' => 'C',
                                        'update_date' => $data_date,
					'forward_refrence_id'  => '0',
					'backward_refrence_id' => '0',
                                );
                                if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                {
                                        $this->db->trans_rollback();
                                        $this->messages->add('Error adding Ledger account - ' . $data_ledger_id . ' to Entry.', 'error');
                                        $this->template->load('template', 'payment/billapproval', $data);
                                        return;
                                }
				$insert_ledger_data = array(
                                        'entry_id' => $entry_id,
                                        'ledger_id' => $ids,
                                        'amount' => $approved_amount,
                                        'dc' => 'D',
                                        'update_date' => $data_date,
					'forward_refrence_id'  => '0',
					'backward_refrence_id' => '0',
                                );
                                if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                {
                                        $this->db->trans_rollback();
                                        $this->messages->add('Error adding Ledger account - ' . $data_ledger_id . ' to Entry.', 'error');
                                        $this->template->load('template', 'payment/billapproval', $data);
                                        return;
                                }*/
				$update_data = array(
                                'approved_amount' =>$approved_amount,
                                'approval_date'   =>$today,
                                'being'           =>$being,
                                'approved_by'     =>$approvedby,
                                'decision'        =>$decision,
				'fund_name'       =>$fund,
				'expenditure_type'=>$exp_type,
				'sanc_type' => $data_sanc_type,
                                'sanc_value' => $sanc_value

			//	'entry_id'        =>$entry_id,
                                 );
                                 if ( ! $this->db->where('bill_no', $billno)->update('bill_approval', $update_data))
                                 {
                                 	$this->db->trans_rollback();
                                       	$this->template->load('template', 'payment/billapproval', $data);
                                        return;
                                 }
			}
			else
			{
				$billno = $bill_no;
                                $today =date("Y-m-d H:i:s");
                                $reject_by=$this->session->userdata['user_name'];
                                $emailto=$this->input->post('submitted_by', TRUE);
                                $being = $this->input->post('being', TRUE);
                                $reason="Bill Rejection";

                                $this->db->trans_start();
                                $update_data = array(
                                'approval_date'   =>$today,
                                'being'            =>$being,
                                'approved_by'      =>$reject_by,
                                'decision'         => $decision,
				'payment_status'   =>'NO',
                                );
                                if ( ! $this->db->where('bill_no', $billno)->update('bill_approval', $update_data))
                                {
                                        $this->db->trans_rollback();
                                        $this->template->load('template', 'payment/billapproval', $data);
                                        return;
                                }
			}
			$this->db->trans_complete();

			if($decision == 'Approved'){
                                $this->messages->add('Bill is approved successfully. ');
                                $this->logger->write_message("success",'Bill is approved successfully. By user '.$username);
                                 redirect('payment/showupload_bill');
                                 return;
                        }else{
                                $this->messages->add('Bill is Rejected successfully. ');
                                $this->logger->write_message("success",'Bill is Rejected successfully. By user '.$username);
                                 redirect('payment/showupload_bill');
                                 return;

                                }

		//	$this->template->load('template', 'payment/billapproval', $data);
		}
		$this->template->load('template', 'payment/billapproval', $data);
                return;

	}

	function voucherfilling($bill_no)
        {
		/* Check access */
                if ( ! check_access('vouchercreation'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('payment/showupload_bill');
                        return;
                }
                $this->load->library('validation');
                $this->template->set('page_title', 'Voucher Fill Form');
                $this->db->select('expense_type, entry_id, decision, submitted_by,approved_amount,approved_by,being,secunitid,fund_name,expenditure_type,sanc_value,sanc_type')->from('bill_approval')->where('bill_no',$bill_no);
                $bill_r = $this->db->get();
                $bill_app = $bill_r->row();
		$user_name = $this->config->item('account_name');
		$decision;
		$bank_cash_account;
		$entry_id;
   //		print_r($bill_r);
		foreach($bill_r->result() as $row){
			$decision = $row->decision;
			//$bank_cash_account = $row->bank_cash_account;
			$entry_id = $row->entry_id;
			$expense = $row->expense_type;
			$expensetype = explode('.', $expense);
			$id = $this->Ledger_model->get_ledger_id($expensetype[0]);
			$approved_by =$row->approved_by;
			$secunitid =$row->secunitid;
			$fund =$row->fund_name;
			$sanc_type = $row->sanc_type;
			$sanc_value = $row->sanc_value;
			$exp_type = $row->expenditure_type;
		}
                 if($fund != 0){
		 $this->db->select('name')->from('ledgers')->where('id' ,$fund);
		$fund_r = $this->db->get();
             //   $fund_n = $fund_r->row();
		//$fund_name = $fund_n;
			foreach($fund_r->result() as $row){
				$fund_name = $row->name;
				}
		}
		else{
			$fund_name = "None";
			}
		$data['payment_mode_active']="Select";
		$data['approved_amount'] = array(
                        'name' => 'approved_amount',
                        'id' => 'approved_amount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->approved_amount,
                        'readonly'=>'true',
                );
		 $data['approved_by'] = array(
                        'name' => 'approved_by',
                        'id' => 'approved_by',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->approved_by,
                        'readonly'=>'true',
                );
		$data['being'] = array(
                        'name' => 'being',
                        'id' => 'being',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->being,
                        'readonly'=>'true',
                );
		$data['paid_to'] = array(
                        'name' => 'paid_to',
                        'id' => 'paid_to',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->submitted_by,
		
                );
		 $data['payment_mode'] = array(
                       "select" =>"Select",
			"cash" => "Cash",
                        "cheque"=> "Cheque",
			"bank transfer"=>"Bank Transfer",
			"dd"=>"Demand Draft",
                );
		$data['secunitid'] = array(
                        'name' => 'being',
                        'id' => 'being',
                        'maxlength' => '10',
                        'size' => '10',
                        'value' => $bill_app->secunitid,
                        'readonly'=>'true',
			);
                $data['fund'] = array(
                       'name' => 'fund',
                        'id' => 'fund',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $fund_name,
                        'readonly'=>'true',

                );
                $data['exp_type'] = array(
                        'name' => 'exp_type',
                        'id' => 'exp_type',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->expenditure_type,
                        'readonly'=>'true',
                        );
		$data['sanc_value'] = array(
                        'name' => 'sanc_value',
                        'id' => 'sanc_value',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->sanc_value,
                        'readonly'=>'true',
                        );
		$data['sanc_type'] = array(
                        'name' => 'sanc_type',
                        'id' => 'sanc_type',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->sanc_type,
                        'readonly'=>'true',
                        );

      /*          $data['check_no']=array(
                        'name' => 'check_no',
                        'id' => 'check_no',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );*/
              /* $data['pan_no']=array(
                        'name' => 'pan_no',
                        'id' => 'pan_no',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
*/
		$data['bank_cash']= $this->Ledger_model->get_all_ledgers_bankcash1();
                $data['bankcash_active']= '0';
		$data['bill_no']=$bill_no;
		/* Form validations */
		$this->form_validation->set_rules('bank_cash','BANK AND CASH ACCOUNT','trim|required'.$bill_no);
		$this->form_validation->set_rules('payment_mode', 'METHOD OF PAYMENT', 'trim|required');
             //   $this->form_validation->set_rules('pan_no','PAN NO', 'trim');
                $this->form_validation->set_rules('check_no','CHECK NO', 'trim');
		/* Re-populating form */
		if ($_POST)
                {
                        $data['approved_amount']['value'] = $this->input->post('approved_amount', TRUE);
                        $data['approved_by']['value'] = $this->input->post('approved_by', TRUE);
                        $data['being']['value'] = $this->input->post('being', TRUE);
                        $data['payment_mode_active'] = $this->input->post('payment_mode', TRUE);
			$data['bankcash_active'] = $this->input->post('bank_cash',TRUE);
                        $data['paid_to']['value'] = $this->input->post('paid_to', TRUE);
                        $data['check_no']['value'] = $this->input->post('check_no', TRUE);
			$data['secunitid']['value'] = $this->input->post('secunitid', TRUE);
			$data['fund_name']['value'] = $this->input->post('fund_name', TRUE);
			$data['exp_type']['value'] = $this->input->post('exp_type', TRUE);
			$data['sanc_value']['value'] = $this->input->post('sanc_value', TRUE);
                        $data['sanc_type']['value'] = $this->input->post('sanc_type', TRUE);
                     //   $data['pan_no']['value'] = $this->input->post('pan_no', TRUE);
                }
//form validation
		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'payment/voucherfilling', $data);
                        return;

                }
		else
                {
			$approved_amount = $data['approved_amount']['value'];
                        $billno = $bill_no;
			$bank_cash=$this->input->post('bank_cash',TRUE);
                        $payment_mode=$this->input->post('payment_mode', TRUE);
                        $paid_to=$this->input->post('paid_to', TRUE);
                      //  $pan_no=$this->input->post('pan_no', TRUE);
			if($payment_mode == 'select')
                        {
                                $this->messages->add('Please Select Mode of Payment', 'error');
                                $this->template->load('template', 'payment/voucherfilling', $data);
                                return;
                        }
			if($bank_cash == '0')
                        {
                                $this->messages->add('Please Select Bank Cash Acoount', 'error');
                                $this->template->load('template', 'payment/voucherfilling', $data);
                                return;
                        }


			$paymentmode=$payment_mode;

	/*		 if($payment_mode=="check"){
                        	$checkno=$this->input->post('check_no', TRUE);
                        	$paymentmode=$payment_mode.'-'.$checkno;
                        }
                        else{
                        	$paymentmode=$payment_mode;
			}*/

        // megha           
//code for entry in entries @megha
		                if($decision == "Approved"){
                                $today = date("Y/m/d H:i:s");
                                $date = explode('/', $today);
                                $data_date = $date[0] .'-' . $date[1] .'-' .$date[2];
                                $value = explode('-', $bank_cash);
				$code_ledg_val = $this->Ledger_model->get_code($value[0]);
                                $code_ledg_exp = $this->Ledger_model->get_code($id);
                                $code_ledg_fnd = $this->Ledger_model->get_code($fund);


				$this->db->select_max('number')->from('entries')->where('entry_type', '2');
                                $bill_r = $this->db->get();
                                $number;
                                foreach($bill_r->result() as $row) {
                                        $number = $row->number;
                                        $number = $number + 1;
                                }
                                        $this->db->trans_start();
                                        $insert_data=array(
                                        'entry_type' =>'2',
                                        'date'       => $data_date,
                                        'update_date' => $data_date,
                                        'forward_refrence_id'  => '0',
                                        'backward_refrence_id' => '0',
                                        'submitted_by' => $approved_by,
                                        'cr_total' => $approved_amount,
                                        'dr_total' => $approved_amount,
                                        'number' => $number,
					'sanc_type' => $sanc_type,
					'sanc_value' => $sanc_value,
					'secunitid' => $secunitid,
                                        );
                                        if ( ! $this->db->insert('entries', $insert_data))
                                        {
                                                $this->db->trans_rollback();
                                                $this->messages->add('Error addding Entry.', 'error');
                                                $this->template->load('template', 'payment/billapproval', $data);
                                                return;
                                        } else {
						$entry_id = $this->db->insert_id();
						$this->logger->write_message("success", "Added journal Bill/Voucher No.".$number."[id :".$entry_id."].By".$user_name);
                                              //  $entry_id = $this->db->insert_id();
                                        }
//for entry_items & fund management 
                                   if(($fund == '0') && ($exp_type == 'Select')){
				        $insert_ledger_data = array(
                                        'entry_id' => $entry_id,
                                        'ledger_id' =>$value[0],
                                        'amount' => $approved_amount,
                                        'dc' => 'C',
                                        'update_date' => $data_date,
                                        'forward_refrence_id'  => '0',
                                        'backward_refrence_id' => '0',
					'secunitid' => $secunitid,
					'ledger_code' => $code_ledg_val,
                               		 );
                                	if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                               		 {
                                        $this->db->trans_rollback();
                                        $this->messages->add('Error adding Ledger account - ' . $value[0] . ' to Entry.', 'error');
                                        $this->template->load('template', 'payment/voucherfilling', $data);
                                        return;
                                	}
                                	$insert_ledger_data = array(
                                        'entry_id' => $entry_id,
                                        'ledger_id' => $id,
                                        'amount' => $approved_amount,
                                        'dc' => 'D',
                                        'update_date' => $data_date,
                                        'forward_refrence_id'  => '0',
                                        'backward_refrence_id' => '0',
					'secunitid' => $secunitid,
					'ledger_code' => $code_ledg_exp,
                               		 );
                                	if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                               		 {
                                        $this->db->trans_rollback();
                                        $this->messages->add('Error adding Ledger account - ' . $id . ' to Entry.', 'error');
                                        $this->template->load('template', 'payment/voucherfilling', $data);
                                        return;
                               		 }	
				}
	                          else{
					if($exp_type == "Revenue" && $fund != '0'){
                                              $insert_ledger_data = array(
                     		                   'entry_id' => $entry_id,
                                	           'ledger_id' =>$value[0],
                                        	   'amount' => $approved_amount,
                                        	   'dc' => 'C',
                                        	   'update_date' => $data_date,
                                       		   'forward_refrence_id'  => '0',
                                       		   'backward_refrence_id' => '0',
						   'secunitid' => $secunitid,
						   'ledger_code' => $code_ledg_val,
                                        	    );
                                        	if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                        	 {
                                        		$this->db->trans_rollback();
                                        		$this->messages->add('Error adding Ledger account - ' . $value[0] . ' to Entry.', 'error');
                                        		$this->template->load('template', 'payment/voucherfilling', $data);
                                        		return;
                                       		  }

                                        	$insert_ledger_data = array(
                                        		'entry_id' => $entry_id,
                                        		'ledger_id' => $id,
                                        		'amount' => $approved_amount,
                                        		'dc' => 'D',
                                        		'update_date' => $data_date,
                                        		'forward_refrence_id'  => '0',
                                        		'backward_refrence_id' => '0',
							'secunitid' => $secunitid,
							'ledger_code' => $code_ledg_exp,
                                            		 );
                                       		 if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                        	 {
                                        		$this->db->trans_rollback();
                                        		$this->messages->add('Error adding Ledger account - ' . $id . ' to Entry.', 'error');

						   } else {
                                                $entry_items_id = $this->db->insert_id();
                                      
                                                           }

	                                        $insert_fund_data = array(
                                                        'entry_id' => $entry_id,
                                                        'ledger_id' => $fund,
                                                        'amount' => $approved_amount,
                                                        'dc' => 'D',
                                                        'update_date' => $data_date,
                                                        'forward_refrence_id'  => '0',
                                                        'backward_refrence_id' => '0',
                                                        'secunitid' => $secunitid,
							'ledger_code' => $code_ledg_fnd,
                                                         );
                                                 if ( ! $this->db->insert('entry_items', $insert_fund_data))
                                                 {
                                                        $this->db->trans_rollback();
                                                       $this->logger->write_message("error", "Error adding fund id:" . $fund);
							}else {
                                                        $entry_fund_id = $this->db->insert_id();

                                                  }
                                       			$this->db->select('id');
                                        		$this->db->from('ledgers')->where('name', 'Transit Income');
                                        		$query = $this->db->get();
                                        		$income = $query->row();
                                        		$income_id = $income->id;
                                			$code_ledg_inc = $this->Ledger_model->get_code($income_id);

                                        	  $insert_income_data = array(
                                                	 'entry_id' => $entry_id,
                                                	 'ledger_id' => $income_id,
                                                	 'amount' => $approved_amount,
                                                	 'dc' => 'C',
                                                	 'update_date' => $data_date,
                                                	 'forward_refrence_id' => '0',
                                                	 'backward_refrence_id' => '0',
                                                	 'secunitid' => $secunitid,
							'ledger_code' => $code_ledg_inc,
                                        		 );

                                        	 if ( ! $this->db->insert('entry_items', $insert_income_data))
                                        	  {
                                                          $this->db->trans_rollback();
                                                          $this->logger->write_message("error", "Error adding transit income");
                                       		   }
		                            		$this->db->select('name');
                                                	$this->db->from('ledgers')->where('id', $fund);
                                                	$query = $this->db->get();
                                                	$ledger = $query->row();
                                                	$ledger_name = $ledger->name;

                                                   $insert_expense_data = array(
                                                        'fund_id' => $fund,
                                                        'fund_name' => $ledger_name,
                                                        'amount' => $approved_amount,
                                                        'date' => $data_date,
                                                        'type' => $exp_type,
                                                        'entry_items_id' => $entry_items_id,
                                               		 );

                                                if ( ! $this->db->insert('fund_management', $insert_expense_data))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->logger->write_message("error", "Error adding expenditure details for fund :" . $fund);
                                                }
					      }
                                          elseif($exp_type == "Capital" && $fund != '0')
					       {	
                                           $insert_ledger_data = array(
                                                   'entry_id' => $entry_id,
                                                   'ledger_id' =>$value[0],
                                                   'amount' => $approved_amount,
                                                   'dc' => 'C',
                                                   'update_date' => $data_date,
                                                   'forward_refrence_id'  => '0',
                                                   'backward_refrence_id' => '0',
                                                   'secunitid' => $secunitid,
						   'ledger_code' => $code_ledg_val,
                                                    );
                                                if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                                 {
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error adding Ledger account - ' . $value[0] . ' to Entry.', 'error');
                                                        $this->template->load('template', 'payment/voucherfilling', $data);
                                                        return;
                                                  }
                                                $insert_ledger_data = array(
                                                        'entry_id' => $entry_id,
                                                        'ledger_id' => $id,
                                                        'amount' => $approved_amount,
                                                        'dc' => 'D',
                                                        'update_date' => $data_date,
                                                        'forward_refrence_id'  => '0',
                                                        'backward_refrence_id' => '0',
                                                        'secunitid' => $secunitid,
							'ledger_code' => $code_ledg_exp,
                                                         );
                                                 if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                                 {
                                                        $this->db->trans_rollback();
                                                       $this->logger->write_message("error", "Error adding fund id:" . $id);
                                                        }else {
                                                        $entry_fund_id = $this->db->insert_id();
						  }
	                                                $this->db->select('name');
                                                        $this->db->from('ledgers')->where('id', $fund);
                                                        $query = $this->db->get();
                                                        $ledger = $query->row();
                                                        $ledger_name = $ledger->name;

                                                   $insert_fund_data = array(
                                                        'fund_id' => $fund,
                                                        'fund_name' => $ledger_name,
                                                        'amount' => $approved_amount,
                                                        'date' => $data_date,
                                                        'type' => $exp_type,
                                                        'entry_items_id' => $entry_fund_id,
                                                         );

                                                if ( ! $this->db->insert('fund_management', $insert_fund_data))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->logger->write_message("error", "Error adding expenditure details for fund :" . $fund);
                                                }
                                        }
     			} 
		}
/*before			if($decision == "Approve"){
				$today = date("Y/m/d H:i:s");
				$date = explode('/', $today);
				$data_date = $date[0] .'-' . $date[1] .'-' .$date[2];
				$value = explode('-', $bank_cash);
				$this->db->trans_start();
					/*$insert_data=array(
					'entry_type' =>'2',
					'date' 	     => $today,
					);
					if ( ! $this->db->insert('entries', $insert_data))
                        		{
                                		$this->db->trans_rollback();
                                		$this->messages->add('Error addding Entry.', 'error');
                                		$this->template->load('template', 'payment/billapproval', $data);
                                		return;
                        		} else {
						$entry_id = $this->db->insert_id();
                        		}*/
/*before					$insert_ledger_data = array(
                                        'entry_id' => $entry_id,
                                        'ledger_id' => $id,
                                        'amount' => $approved_amount,
                                        'dc' => 'C',
                                        'update_date' => $data_date,
					'forward_refrence_id'  => '0',
					'backward_refrence_id' => '0',
                                );
                                if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                {
                                        $this->db->trans_rollback();
                                        $this->messages->add('Error adding Ledger account - ' . $value[1] . ' to Entry.', 'error');
                                        $this->template->load('template', 'payment/billapproval', $data);
                                        return;
                                }
				$insert_ledger_data = array(
                                        'entry_id' => $entry_id,
                                        'ledger_id' =>$value[0],
                                        'amount' => $approved_amount,
                                        'dc' => 'D',
                                        'update_date' => $data_date,
					'forward_refrence_id'  => '0',
					'backward_refrence_id' => '0',
                                );
                                if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                {
                                        $this->db->trans_rollback();
                                        $this->messages->add('Error adding Ledger account - ' . $data_ledger_id . ' to Entry.', 'error');
                                        $this->template->load('template', 'payment/billapproval', $data);
                                        return;
                                }
			}*/
//for print cheque
				if($payment_mode !='cash'){
					$this->db->trans_start();
                                        $insert_data=array(
                                        'ledger_id' =>$id,
					'entry_no' =>$entry_id,
					'update_cheque_no'=>'1',
					'paymentreceiptby' => $payment_mode,
                                        );
                                        if ( ! $this->db->insert('cheque_print', $insert_data))
                                        {
                                                $this->db->trans_rollback();
                                                $this->messages->add('Error addding cheque entry.', 'error');
                                                $this->template->load('template', 'payment/voucherfilling', $data);
                                                return;
                                        } else {
                                                $this->db->trans_complete();
                                        }
			       	}
//...........end

			$vc_date=date("Y-m-d H:i:s");
                        $this->db->trans_start();
                                $update_data = array(
                                        'vc_date'        =>$vc_date,
					'bank_cash_account' =>$bank_cash,
                                        'mode_of_payment' =>$paymentmode,
					'payment_status'   =>'DONE',
					'payment_date'   =>$vc_date,
                                );
			if ( ! $this->db->where('bill_no', $billno)->update('bill_approval', $update_data))
                                {
                                        $this->db->trans_rollback();
                                        $this->template->load('template', 'payment/voucherfilling', $data);
                                        return;
                                }
                                else
                                {
                                        $this->db->trans_complete();
					$this->logger->write_message("success", "Added Payment Bill/Voucher No.".$number."By".$user_name);

					$this->bill_printpreview($billno);
                                }
                	}
                	return;
        	}

	function bill_printpreview($bill_no)
        {
		$p_name = "";
		$p_add = "";
		$this->load->library('session');
	//	$this->load->library('Number');
		 $data['user_name'] = $this->config->item('account_email');
                if ( ! $show = $this->Payment_model->bill_printvalue($bill_no))
                {
                        $this->messages->add('Invalid Entry.', 'error');
                        return;
                }
		$this->db->select('secunitid')->from('bill_approval')->where('bill_no',$bill_no);
		$sec_id = $this->db->get();
                $sec_q = $sec_id->row();
		$this->secunitid = $sec_q->secunitid;
		$secunitid = $this->secunitid;
		$this->db->select('partyname,address')->from('addsecondparty')->where('sacunit',$secunitid);
		$p_data = $this->db->get();
		$fund = $show->fund_name;
		foreach($p_data->result() as $row)
                {
                        $p_name= $row->partyname;
			$p_add = $row->address;
		}
		 if($fund != 0){
                 $this->db->select('name')->from('ledgers')->where('id' ,$fund);
                 $fund_r = $this->db->get();
             //   $fund_n = $fund_r->row();
                //$fund_name = $fund_n;
                        foreach($fund_r->result() as $row){
                                $fund_name = $row->name;
                                }
                }       
                else{
                        $fund_name = "None";
                        }

		$data['left_width'] = "800";
                $data['right_width'] = "800";
                $data['bill_no']=$bill_no;
		$data['p_name']=$p_name;
		$data['p_add']=$p_add;
                $data['approved_amount'] =  $show->approved_amount;
                $data['approved_by'] =  $show->approved_by;
                $data['being']=  $show->being;
		$data['vc_date']=$show->vc_date;
		$data['submitter_id']=$show->submitter_id;
                $data['mode_of_payment']=  $show->mode_of_payment;
		$data['expense_type']=$show->expense_type;
		$data['bank_cash_account']=$show->bank_cash_account;
                $data['paid_to']=  $show->submitted_by;
                $data['fund_name']=  $fund_name;
                $data['exp_type']=$show->expenditure_type;
                $data['sanc_value']=$show->sanc_value;
                $data['sanc_type']=  $show->sanc_type;

	//	$data['approved_amount'] = $number;
	//	$data['number_string'] = $res;
                $this->load->view('payment/bill_printpreview',$data);
                 return;
        }

        function reject_printpreview($bill_no)
        {

                        $this->template->set('page_title', 'Rejected Bill');
                        $this->db->select('bill_no,submit_date,submitted_by,bill_name,total_amount,expense_type,being,decision')->from('bill_approval')->where('bill_no',$bill_no);
                        $bill_r = $this->db->get();
                        $bill_app = $bill_r->row();

                        $data['bill_no'] = array(
                        'name' => 'bill_no',
                        'id' => 'bill_no',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->bill_no,
                        'readonly'=>'true',
                        );

                        $data['bill_name'] = array(
                        'name' => 'bill_name',
                        'id' => 'bill_name',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->bill_name,
                        'readonly'=>'true',
                        );
                        $data['submitted_by'] = array(
                        'name' => 'submitted_by',
                        'id' => 'submitted_by',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->submitted_by,
                        'readonly'=>'true',
                        );
                        $data['submit_date'] = array(
                        'name' => 'submit_date',
                        'id' => 'submit_date',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->submit_date,
                        'readonly' => 'true',
                        );
                        $data['total_amount'] = array(
                        'name' => 'total_amount',
                        'id' => 'total_amount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->total_amount,
                        'readonly' => 'true',
                        );

                        $data['expensestype'] = array(
                        'name' => 'expensestype',
                        'id' => 'expensestype',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$bill_app->expense_type,
                        'readonly'=>'true',
                        );
                        $data['decision']=array(
                        'name' => 'decision',
                        'id' => 'decision',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $bill_app->decision,
                        'readonly' =>'true'
                        );
			$data['being'] = array(
                        'name' => 'being',
                        'id' => 'being',
                        'cols' => '50',
                        'rows' => '4',
                        'value' => $bill_app->being,
                        'readonly' => 'true'
                        );

                       $datal['bill_no'] = $bill_no;
                       $data['date'] = date("Y-m-d H:i:s");
                        /* Re-populating form */
                        if ($_POST)
                        {
                        $data['bill_no']['value'] = $this->input->post('bill_no', TRUE);
                        $data['bill_name']['value'] = $this->input->post('bill_name', TRUE);
                        $data['submitted_by']['value'] = $this->input->post('submitted_by', TRUE);
                        $data['total_amount']['value'] = $this->input->post('total_amount', TRUE);
                        $data['expensestype']['value']=$this->input->post('expensestype', TRUE);
                        $data['decision']['value']=$this->input->post('decision',TRUE);
                        $data['being']['value'] = $this->input->post('being', TRUE);

                        }
                $this->template->load('template', 'payment/reject_printpreview', $data);
                return;

        }


	function startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

          
	function ledger_budget($name)
        {
		$code='';
		$no_rows='';
		$avail_budget='';
		$this->db->select('code')->from('ledgers')->where('name', $name);
		$ledg_code = $this->db->get();
		foreach($ledg_code->result() as $row)
                {
                	$code = $row->code;
			$this->db->from('budgets')->where('code', $code);
			$query=$this->db->get();
			$query_l = $query->row();
			$no_rows=$query->num_rows();
			if($no_rows > 0 ){
				$this->bdbalance = $query_l->bd_balance;
                   		$this->consumeamt = $query_l->consume_amount;
				$budgetamt = $this->bdbalance;
                   		$consumeamt= $this->consumeamt;
                   		$avail_budget = $budgetamt - $consumeamt;	
			}elseif($no_rows == NULL){
				$length = strlen($code);
				    while(($no_rows == 0 || $no_rows == NULL ) && $length >="6"){ 
                        	    $code = substr($code,0,$length-2);
			//	    echo"sub_code==>$code";
                        	    $length = strlen($code);
                        	    $this->db->from('budgets')->where('code', $code);
                        	    $val=$this->db->get();
				    $no_rows=$val->num_rows();
			//	    echo"num_row==> $no_rows";
				}
				        if($length == 4 && $no_rows == 0){
						$avail_budget= "0.00";
				        }
				        else {
					      foreach($val->result() as $row){
				              $this->bdbalance = $row->bd_balance;
                                              $this->consumeamt = $row->consume_amount;
                                	      $budgetamt = $this->bdbalance;
                                              $consumeamt= $this->consumeamt;
                                              $avail_budget = $budgetamt - $consumeamt;
		                               }
 					     }     
				}
                      }   

			echo"$avail_budget";
			return ;
        }//fun.bugdt

        function init1l($id,$groupid,$data_amount,$data,$parent_id)
        {
			$this->load->library('GetParentlist');
              /*$parent_id =0;
		if ($id == 0)
                {

                        $id= 0;
		
                        $this->messages->add('Please Add atleast one parent group for this ledger entry for Payment','error');
                        return;

                } else {*/
                        $this->db->from('groups')->where('id', $groupid);
                        $group_q = $this->db->get();
                        $group = $group_q->row();
                        $this->parent_id = $group->parent_id;
                        $this->code = $group->code;
			$parent_id = $this->parent_id;
                        $this->db->from('budgets')->where('code', $this->code);
                        $query_l = $this->db->get();
                        $query_l = $query_l->num_rows();
                        if($query_l>0)
                        {

                                $budgetamt = 0;
                                $useamt = 0;
                                $allow = 0;
                                $ledg_code=$this->code;
                                $this->db->from('budgets')->where('code', $this->code);
                                $query_l = $this->db->get();
                                $query_l = $query_l->row();
                                $this->amt = $query_l->bd_balance;
                                $this->useamt = $query_l->consume_amount;
                                $this->allow=$query_l->allowedover;
                                $budgetamt=$this->amt;
                                $useamt=$this->useamt;
                                $allow=$this->allow;

                                /* if alloted budget amount is more than consume amount*/

                                if($budgetamt > $useamt)

                                {//if1
                                        $available_amount=$budgetamt - $useamt ;//its wrong

                                        /**  payment amount is greater than or equal to available amount **/
                                      if($data_amount > $available_amount)
                                        {
                                                /* check for allowed over expense*/
                                                if(($allow == -1) || ($allow == 0))
                                                {
                                                        $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                        //$this->template->load('template','entry/add',$data);
                                                        redirect('payment/billapproval');
                                                        //redirect
                                                        return ;
                                                 }

                                                else
                                                 {
                                                        /* check for payment amount by adding allowd over amount + consume amount */
                                                      $available_amount = $budgetamt - $useamt + $allow;
                                                        if($data_amount >= $available_amount)
                                                        {
                                                                $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                                //$this->template->load('template', 'entry/add',$data);
                                                                redirect('payment/billapproval');
                                                                return ;

                                                         }
                                                         else
                                                         {
                                                                /* Update budget table */
                                                                $sumamt=$data_amount + $useamt;
                                                                $allow_left = $available_amount - $data_amount ;
                                                                $update_data1 = array('consume_amount' => $sumamt, 'allowedover' => $allow_left);
                                                                if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                                {
                                                                        $this->db->trans_rollback();
                                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                        //$this->template->load('template', 'entry/add', $data);
                                                                        redirect('payment/billapproval');
                                                                        return ;
                                                                }
                                                                $parents = new GetParentlist();
                                                                $parents->init($groupid,$data_amount);
                                                                //$this->db->from('budgets')->where('code', '50');
                                                                $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                                $query_ll = $this->db->get();
                                                                $query_ll = $query_ll->row();
                                                                $this->amt1 = $query_ll->bd_balance;
                                                                $this->useamt1 = $query_ll->consume_amount;
                                                                $update_data2 = $this->useamt1 + $data_amount;
                                                                $update_data3 = array('consume_amount' => $update_data2);
                                                                //if ( ! $this->db->where('code', '50')->update('budgets', $update_data3))
                                                                if ( ! $this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                                {
                                                                        $this->db->trans_rollback();
                                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                                        $this->template->load('template', 'payment/billapproval', $data);
                                                                        redirect('payment/billapproval');
                                                                        return;
                                                                 }
                                                                return;
                                                        }
                                                }

                                        }
                                        else
                                        {
                                                $sumamt=$data_amount + $useamt;
                                                $update_data1 = array('consume_amount' => $sumamt );
                                               /* if ( ! $this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                {
                                                        $this->messages->add("Test in Getparent 7==>");
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
                                                        redirect('entry/add');
                                                        return;
                                                }*/
                                                //$this->messages->add('Line 1786 ===>'.$groupid.'===>'.$data_amount);
                                                //echo "1789";
                                                $parents = new GetParentlist();
                                                $parents->init($groupid,$data_amount);

                                                //$this->db->from('budgets')->where('code', '50');
                                                $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                $query_ll = $this->db->get();
                                                $query_ll = $query_ll->row();
                                                //$this->id = $query_l->id;
                                                $this->amt1 = $query_ll->bd_balance;
                                                $this->useamt1 = $query_ll->consume_amount;
                                                $update_data2 = $this->useamt1 + $data_amount;
                                                $update_data3 = array('consume_amount' => $update_data2);
                                                //echo "$update_data2"; 
                                                //if (!$this->db->where('code', '50')->update('budgets', $update_data3))
                                                if (!$this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                {
                                                        //$this->messages->add("Test in Getparent 8==>");
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
                                                        redirect('payment/billapproval');
                                                        return;
                                                }

                                        }
                                        //$this->template->load('template', 'entry/add', $data);
                                        //return $id;
                                }//1    


                                /* consume amount is greater than allocated budget amount*/
                              if($useamt >= $budgetamt)
                                {//2
                                        /* check for allowed over expenses */
                                      if(($allow == -1) || ($allow == 0))
                                        {
                                                $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                //$this->template->load('template', 'entry/add',$data);
                                                redirect('payment/billapproval');
                                                return;
                                        }
                                        /** get over consume amount and check with allowed left **/


                                      $overconsume_amount = $useamt - $budgetamt ;
                                        /* payment amount is greater than allowed over amount*/
                                      if($data_amount > $allow)
                                        {


                                                $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                //$this->template->load('template', 'entry/add',$data);
                                                redirect('payment/billapproval');
                                                return;
                                        }
                                        /* payment amount is less than allowed over amount*/
                                        if($data_amount <= $allow)
                                        {

                                                $overconsume_amount = $useamt - $budgetamt ;
                                                $available_amount = $allow ;
                                                $allowed_left = $allow - $data_amount;
                                                $consume_amount = $useamt + $data_amount;
                                                $update_data1 = array('consume_amount' => $consume_amount, 'allowedover' => $allowed_left);
                                                if (!$this->db->where('code', $ledg_code)->update('budgets', $update_data1))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
                                                        redirect('payment/billapproval');
                                                        return;
                                                }
                                                $parents = new GetParentlist();
                                                $parents->init($groupid,$data_amount);
                                                //$this->db->from('budgets')->where('code', '50');
                                                $this->db->from('budgets')->where('budgetname', 'Main Budget');
                                                $query_ll = $this->db->get();
                                                $query_ll = $query_ll->row();
                                                //$this->id = $query_l->id;
                                                $this->amt1 = $query_ll->bd_balance;
                                                $this->useamt1 = $query_ll->consume_amount;
                                                $update_data2 = $this->useamt1 + $data_amount;
                                                $update_data3 = array('consume_amount' => $update_data2);


                                                //if(!$this->db->where('code', '50')->update('budgets', $update_data3))
                                                if(!$this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error updating total expenses amount in budget.', 'error');
                                                        //$this->template->load('template', 'entry/add', $data);
                                                        redirect('payment/billapproval');
                                                        return;
                                                }


                                        }

                                }//2*/
                //      return $id;
                        }
                        else{
				//$parent_id = $id2;
                                $this->get_parent_groups($data_amount,$data,$parent_id);

                        }
              //  return $id;
               // }
        }//function




        function get_parent_groups($data_amount,$data,$parent_id)
        {

                $parent_groups = array();
                $this->db->from('groups')->where('id', $parent_id);
                $parent_group_q = $this->db->get();


                foreach ($parent_group_q->result() as $row)
                {
                       $parent_id= $row->parent_id;
			$groupid=$row->id;
                       $code=  $row->code;
			if ($parent_id == 0 )
				{ 
				 $this->messages->add('Please Add atleast one parent group for this ledger entry for Payment','error');
                        	redirect('payment/showupload_bill');
				return;
				}
			else {
                                $this->init1l($id,$groupid,$data_amount,$data,$parent_id);
			     }
                }
              //  return $parent_id;
        }




}
?>
