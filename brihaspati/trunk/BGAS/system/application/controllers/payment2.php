<?php	if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Payment2 extends Controller {

        function Payment2()
        {
                parent::Controller();
                $this->load->model('Entry_model');
                $this->load->model('Ledger_model');
                $this->load->model('Tag_model');
		$this->load->model('Payment2_model');
		$this->load->model('Budget_model');
		$this->load->model('Secunit_model');
                $this->load->model('Authority_model');
		$this->load->model('User_model');
		$this->load->library('GetParentlist');
		$this->load->library('session');
		$this->load->library('form_validation');
		$this->load->library('validation');
		$this->load->library('paymentreceipt');
                return;
        }

        function index()
        {
                redirect('entry/show/payment2');
                return;
        }

	/**
		*Code for uploading of File.
		*This function saves the uploaded file in Uploads folder.
		*This function returns the name of file wherever we call this function.
	**/
	function p2fileupload()
        {
		$filename="";	
		$path=getcwd().'/uploads/Bills';
		if (!is_dir('uploads/Bills')) 
		{
                	mkdir('./uploads/Bills');
			chmod('./uplaods/Bills',0777);
		}
		$config['upload_path'] = $path;
                $config['allowed_types'] = 'gif|jpg|jpeg|png|pdf|';
                $config['max_size'] = '1000';
                $config['max_width'] = '1920';
                $config['max_height'] = '1280';
                $this->load->library('upload', $config);
		if(!$this->upload->do_upload()) 
			$this->upload->display_errors();
                else 
		{
                	$fInfo = $this->upload->data();
                        $filename=$fInfo['file_name'];
              	}
                return $filename;
        }
	
	/**
		*Code for index page of Bill upload/Voucher Creation with Multiple Verification
		*This function shows some information related with uploaded bill on index page.
		*This function display only 10 Bills per page.
	**/
	function showupload_bill_approval()
	{	
		$this->template->set('page_title', 'View Bill');	
		$this->template->set('nav_links', array( 'payment2/p2bill_upload/' =>'Bill Upload'));
		$this->load->helper("url");
                $this->load->library('pagination');
		$page_count = (int)$this->uri->segment(4);
                $page_count = $this->input->xss_clean($page_count);
                if ( ! $page_count)
                	$page_count = "0";
                $config['base_url'] = site_url('payment2/showupload_bill_approval/all');
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
		$data["bill_data"] = $this->Payment2_model->p2bill_uploadvalues($pagination_counter, $page_count);
                $config['total_rows'] = $this->Payment2_model->p2record_count();

                $this->pagination->initialize($config);
		$this->template->load('template', 'payment2/showupload_bill_approval', $data);
                return;

        }

	/**
		*Code for uploading of Bill.
		*This function will be used to upload bill.
		*It checks whether file of bill is uploaded or not and whether expense is selected or not.
		*In case any condition fails then this function will not forward user to next page.
	**/                
	function p2bill_upload()
	{
		if ( ! check_access('bill upload'))
		{
			$this->messages->add('Permission denied.', 'error');
                        redirect('payment2/showupload_bill_approval');
                        return;
                }
		$this->template->set('page_title', 'Bill Upload');
		
		$data['submitted_by'] = array(
                        'name' => 'submitted_by',
                        'id' => 'submitted_by',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
		
		$data['supplier_bill_no'] = array(
                        'name' => 'supplier_bill_no',
                        'id' => 'supplier_bill_no',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );
                
		$data['purchase_order_no'] = array(
                        'name' => 'purchase_order_no',
                        'id' => 'purchase_order_no',
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

                $data['secunit']=$this->Secunit_model->get_all_secunitid();
                $data['secunit_active']=0;
		
		$data['expenses']= $this->Ledger_model->get_codewise_ledgers();
                $data['expenses_active']=0;

                $data['forward_to']= $this->Authority_model->get_all_authorities_map_with_email();
                $data['forward_to_active']=0;
		
		$this->form_validation->set_rules('submitted_by', 'Submitter Email Id', 'trim|valid_emails|required');
                $this->form_validation->set_rules('supplier_bill_no', 'Supplier Bill No', 'trim|required||min_length[1]|max_length[100]');
                $this->form_validation->set_rules('purchase_order_no', 'Purchase Order No', 'trim|required||min_length[1]|max_length[100]');
                $this->form_validation->set_rules('totalamount', 'Total Amount', 'trim|required||min_length[1]|max_length[100]');
                $this->form_validation->set_rules('expenses', 'Expenses', 'trim|required');
		$this->form_validation->set_rules('secunit', 'Party Name', 'trim|required');
                $this->form_validation->set_rules('forward_to', 'Forward To', 'trim|required');
		
		if ($_POST)
                {
                        $data['submitted_by']['value'] = $this->input->post('submitted_by', TRUE);
                        $data['supplier_bill_no']['value'] = $this->input->post('supplier_bill_no', TRUE);
                        $data['purchase_order_no']['value'] = $this->input->post('purchase_order_no', TRUE);
                        $data['totalamount']['value'] = $this->input->post('totalamount', TRUE);
                        $data['expenses_active'] = $this->input->post('expenses', TRUE);
		        $data['secunit_active'] = $this->input->post('secunit', TRUE);
                        $data['forward_to_active'] = $this->input->post('forward_to', TRUE);
                }
		
		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'payment2/p2bill_upload', $data);
                        return;
                }
		else
                {
                        $expenses=$this->input->post('expenses',TRUE);
			$submitted_by = $this->input->post('submitted_by', TRUE);
                        $supplier_bill_no = $this->input->post('supplier_bill_no',TRUE);
                        $purchase_order_no = $this->input->post('purchase_order_no',TRUE);
                        $total_amount = $this->input->post('totalamount',TRUE);
                        $today = date("Y-m-d H:i:s");
                        $username=$this->session->userdata['user_name'];
			$filename=$this->p2fileupload();
			$secunit = $this->input->post('secunit', TRUE);
                        $forward_to = $this->input->post('forward_to', TRUE);
			$forward_to1 = explode('`',$forward_to);
        		$forward_to2 = $forward_to1[0];
        		$forward_to3 = $forward_to1[1];
		  	if($filename == "")
			{
				$this->messages->add('Please select a File having extension gif | jpg | jpeg | png | pdf |', 'error');
                                $this->template->load('template', 'payment2/p2bill_upload', $data);
                                return;
			}
                  	if($expenses == 0)
                        {
                                $this->messages->add('Please Select Expense Type From Dropdown List.', 'error');
                                $this->template->load('template', 'payment2/p2bill_upload', $data);
                                return;
                        }

			$this->db->trans_start();
                        $insert_data = array(
				'purchase_order_no' => $purchase_order_no,
				'supplier_bill_no' => $supplier_bill_no,
				'submit_date' => $today,
                                'submitted_by' => $submitted_by,
                                'submitter_id' => $username,
                                'bill_name'    => $filename,
                                'expense_type' => $expenses,
                                'total_amount' => $total_amount,
				'party_id' => $secunit,
				'current_location' => $forward_to2,
			);
				
			if ( ! $this->db->insert('bill_voucher_create', $insert_data))
                      	{
	                        $this->db->trans_rollback();
				$this->messages->add('Error upload bill. '.$username.$total_amount.'error');
                                $this->logger->write_message("error",'Error upload bill'.$username.$total_amount.'error');
                                $this->template->load('template', 'payment2/p2bill_upload', $data);
                                return;
                        } 
			else 
			{
                                $billid=$this->db->insert_id();

                        	$insert_data1 = array( 
                                	'bill_no' => $billid,
                                	'forward_from' => $this->session->userdata('user_name'),
                                	'forward_to'    => $forward_to2,
					'authority_name' => $forward_to3,
                                	'forward_date' => $today,
                                );
                        	if ( ! $this->db->insert('bill_approval_status', $insert_data1))
                        	{
                                	$this->db->trans_rollback();
                                	$this->messages->add('Error upload bill. '.$username.'error');
                                	$this->logger->write_message("error",'Error bill approval status'.$username.'error');
                                	$this->template->load('template', 'payment2/p2bill_upload', $data);
                                	return;
				}	
				else
				{
                                 	$this->db->trans_complete();
				}
                        }
			$message2 = base_url()."index.php/user/login";
 			$message1 = "<b>Please login to your account in Brihaspati General Accounting System and take actions on Bill Number $billid</b>";
			$message = $message1."<br>".$message2;
                        $subject = 'Bill Approval in BGAS ';
                        if($this->paymentreceipt->send_mail($forward_to2, $subject, $message))
                       	$this->messages->add('Bill is Uploaded Successfully');
			$this->logger->write_message("success",'Bill Is Uploaded Successfully. ');
                        redirect('payment2/showupload_bill_approval');
                        return;
		}
		$this->template->load('template', 'payment2/p2bill_upload',$data);
                return;
	}

	/**
		*Code for Multiple Bill Approval.
		*This function performs various actions according to selected decision.
		*Here parameter $bill_no is Bill Number.
	**/	
	function p2billapproval($bill_no)
	{
                if ( ! check_access('approve/reject'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('payment2/showupload_bill_approval');
                        return;
                }

                $this->template->set('page_title', 'Bill Approval');
                $this->db->select('id,purchase_order_no,supplier_bill_no,submitter_id,submit_date,submitted_by,bill_name,total_amount,expense_type,party_id,expenditure_type,decision,sanc_type,sanc_value,fund_id,narration')->from('bill_voucher_create')->where('id',$bill_no);
		$username = $this->config->item('account_name');
                $bill_r = $this->db->get();
                $bill_app = $bill_r->row();
		
		$val1 = ''; $val2 = ''; $val3 = ''; $val4 = ''; $val5 = ''; 
                $val1 = $bill_app->expenditure_type;
                $val2 = $bill_app->sanc_type;
                $val3 = $bill_app->sanc_value;
                $val4 = $bill_app->fund_id;
                $val5 = $bill_app->narration;
		$decision1 = $bill_app->decision;

		$budg = $bill_app->expense_type;
                $my_budg = explode('.',$budg);
                $budg_val = $my_budg[0];
		
		$inc=0;
		$this->db->select('id,comments,approval_amount')->from('bill_approval_status')->where('bill_no',$bill_no)->order_by('id','desc');
                $maxapprovalid = $this->db->get();
                foreach($maxapprovalid->result() as $row_2)
                {
			$inc++;
			$appro_amnt = $row_2->approval_amount;
			if ($inc==2)
			break;
                }
		
                $narr = $val5." ";
                $this->db->select('comments')->from('bill_approval_status')->where('bill_no',$bill_no)->order_by('id','asc');
                $bill_commnt = $this->db->get();
                foreach($bill_commnt->result() as $row)
                {
                        $narr = $narr ."  ". $row->comments;
                }

                $data['bill_no'] = array(
                	'name' => 'bill_no',
                	'id' => 'bill_no',
                	'maxlength' => '100',
                	'size' => '40',
                	'value' => $bill_app->id,
                	'readonly'=>'true',
                );
                        
		$data['supplier_bill_no'] = array(
                	'name' => 'supplier_bill_no',
                	'id' => 'supplier_bill_no',
                	'maxlength' => '100',
                	'size' => '40',
                	'value' => $bill_app->supplier_bill_no,
                	'readonly'=>'true',
                );  
                        
		$data['purchase_order_no'] = array(
                	'name' => 'purchase_order_no',
                	'id' => 'purchase_order_no',
                	'maxlength' => '100',
                	'size' => '40',
                	'value' => $bill_app->purchase_order_no,
                	'readonly'=>'true',
                );
                        
		$data['bill_name']= $bill_app->bill_name;
                        
		$data['submitted_by'] = array(
                	'name' => 'submitted_by',
                	'id' => 'submitted_by',
                	'maxlength' => '100',
                	'size' => '40',
                	'value' => $bill_app->submitted_by,
                	'readonly'=>'true',
		);

		$data['submitdate'] = array(
                        'name' => 'expensestype',
                        'id' => 'expensestype',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$bill_app->submit_date,
                        'readonly'=>'true',
                );

		$data['forward_from'] = array(
                	'name' => 'forward_from',
                	'id' => 'forward_from',
                	'maxlength' => '100',
                	'size' => '40',
                	'value' => $bill_app->submitter_id,
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

		$data['exp_budget'] = array(
			'name' => 'exp_budget',
                        'id' => 'exp_budget',
                        'maxlength' => '100',
                        'size' => '20',
                        'value' =>$this->p2ledger_budget('1.'.$budg_val),
                        'readonly'=>'true',
		);

		$data['secunitid'] = array(
                        'name' => 'secunitid',
                        'id' => 'secunitid',
                        'maxlength' => '10',
                        'size' => '40',
                        'value' =>$this->Secunit_model->get_secunitname($bill_app->party_id),
                        'readonly'=>'true',
                );
	
		$data['forward_to']= $this->Authority_model->get_all_authorities_map_with_email();
                $data['forward_to_active']=0;	

		if($val4 =='')
                {
                        $data['fund_active']="0";
                }
                else
                {
                        $data['fund_active']=$val4;
                }
                $data['fund'] = $this->Ledger_model->get_fund_ledgers();

		$data['fund_bal'] = array(
                        'name' => 'fund_bal',
                        'id' => 'fund_bal',
                        'maxlength' => '100',
                        'size' => '20',
                        'value' =>$this->Ledger_model->get_ledger_balance($bill_app->fund_id),
                        'readonly'=>'true',
                );
	
		if($val1 =='')
                {
                        $data['exp_type_active']='Select';
                }
                else
                {
                        $data['exp_type_active']=$val1;
                }
                $data['exp_type'] = array(
                        'Select' =>'Select',
                        'Capital' => 'Capital Expenditure',
                        'Revenue' => 'Revenue Expenditure',
                );

		if($val2 =='')
                {
                        $data['active_sanc_type']='non_plan';
                }
                else
                {
                        $data['active_sanc_type']= $val2;
                }
                $data['sanc_type'] = array(
                        'select' => 'Select',
                        'plan' => 'Plan',
                        'non_plan' => 'Non Plan',
                        'plan_sfc_scheme' => 'Plan - Specific Schemes',
                        'plan_other_scheme' => 'Other Schemes'
                );

		if($val3 =='')
                {
                        $data['active_plan']='Select';
                }
                else
                {
                        $data['active_plan']= $val3;
                }
                $data['plan'] = array(
                        'select' => 'Select',
                        'General OH:35' => 'General OH:35',
                        'General OH:31' => 'General OH:31',
                        'SCSP OH:35' => 'SCSP OH:35',
                        'SCSP OH:31' => 'SCSP OH:31',
                        'TSP OH:35' => 'TSP OH:35',
                        'TSP OH:31' => 'TSP OH:31'
                );

                if($val3 =='')
                {
                        $data['active_non_plan']='Non Salary OH:31';
                }
                else
                {
                        $data['active_non_plan']= $val3;
                }
                $data['non_plan'] = array(
                        'select' => 'Select',
                        'Salary OH:36' => 'Salary OH:36',
                        'Pension And Pensionary Benefit OH:31' => 'Pension And Pensionary Benefit OH:31',
                        'Non Salary OH:31' => 'Non Salary OH:31'
                );
	
		$data['decision_active']='Select';
		$data['decision']=array(
			'Approved' =>'Approved',
			'Rejected' =>'Rejected',
			'Hold' =>'Hold',
			'vchrcrn' =>'Voucher Creation',
			'Select' =>'Select',
		);
		
		$data['approved_amount'] = array(
                        'name' => 'approved_amount',
                        'id' => 'approved_amount',
                        'maxlength' => '100',
                        'size' => '20',
                        'value' => '',
                );

		$data['prev_approved_amount'] = array(
                     	'name' => 'prev_approved_amount',
                        'id' => 'prev_approved_amount',
                        'maxlength' => '100',
                        'size' => '20',
                        'value' =>$appro_amnt,
                        'readonly'=>'true',
                );
 
		if($val5 =='')
                {
			$data['being'] = array(
                        	'name' => 'being',
                        	'id' => 'being',
                        	'cols' => '50',
                        	'rows' => '4',
                        	'value' => '',
                	);
                }
                else
                {
			$data['being'] = array(
                       	 	'name' => 'being',
                        	'id' => 'being',
                        	'cols' => '50',
                        	'rows' => '4',
                        	'value' =>$narr,
                        	'readonly'=>'true',
                	);
                }

		$data['being1'] = array(
                        'name' => 'being1',
                        'id' => 'being1',
                        'cols' => '50',
                        'rows' => '4',
                        'value' => '',
                );
                
                $this->form_validation->set_rules('forward_to', 'Forward TO', 'trim');
                $this->form_validation->set_rules('fund', 'Select Fund', 'trim');
                $this->form_validation->set_rules('exp_type', 'Expenditure Type', 'trim');
                $this->form_validation->set_rules('sanc_type', 'Sanction Type', 'trim');
		$this->form_validation->set_rules('decision', 'Approve/Reject', 'trim'.$bill_no);            
                $this->form_validation->set_rules('approved_amount', 'Approved Amount', 'trim');
                $this->form_validation->set_rules('being', 'Narration', 'trim');
                $this->form_validation->set_rules('being1', 'Comments', 'trim');
                if ($_POST)
                {
                	$data['bill_no']['value'] = $this->input->post('bill_no', TRUE);
                	$data['supplier_bill_no']['value'] = $this->input->post('supplier_bill_no', TRUE);
                	$data['purchase_order_no']['value'] = $this->input->post('purchase_order_no', TRUE);
                        $data['submitted_by']['value'] = $this->input->post('submitted_by', TRUE);
                        $data['forward_from']['value'] = $this->input->post('forward_from', TRUE);
                        $data['total_amount']['value'] = $this->input->post('total_amount', TRUE);
                        $data['expensestype']['value']=$this->input->post('expensestype', TRUE);
                        $data['exp_budget']['value']=$this->input->post('exp_budget', TRUE);
                        $data['decision_active']=$this->input->post('decision', TRUE);
                        $data['approved_amount']['value'] = $this->input->post('approved_amount', TRUE);
                        $data['prev_approved_amount']['value'] = $this->input->post('prev_approved_amount', TRUE);
                        $data['being']['value'] = $this->input->post('being', TRUE);
                        $data['being1']['value'] = $this->input->post('being1', TRUE);
			$data['secunitid']['value'] = $this->input->post('secunitid', TRUE);
                        $data['forward_to_active'] = $this->input->post('forward_to', TRUE);
                        $data['fund_active'] = $this->input->post('fund', TRUE);
                        $data['exp_type_active'] = $this->input->post('exp_type', TRUE);
                        $data['active_sanc_type'] = $this->input->post('sanc_type', TRUE);
		        if($data['active_sanc_type'] != 'select')
                        {
                         	if($data['active_sanc_type'] == 'plan')
                            	$data['active_plan'] = $this->input->post('plan', TRUE);
                         	else
                            	$data['active_non_plan'] = $this->input->post('non_plan', TRUE);
                        }
		}
                if ($this->form_validation->run() == FALSE)
                {
                	$this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'payment2/p2billapproval', $data);
                        return;
                }
		else
		{
			$bill_no = $this->input->post('bill_no', TRUE);
			$supplier_bill_no = $this->input->post('supplier_bill_no', TRUE);
			$purchase_order_no = $this->input->post('purchase_order_no', TRUE);
                	$bill_name = $this->input->post('bill_name', TRUE);
                        $submitted_by = $this->input->post('submitted_by', TRUE);
                        $submitdate = $this->input->post('submitdate', TRUE);
                        $forward_from = $this->input->post('forward_from', TRUE);
                        $total_amount = $this->input->post('total_amount', TRUE);
                        $expenses = $this->input->post('expensestype',TRUE);
                        $my_values = explode('.',$expenses);
                        $ids = $my_values[0];
		        $secunitid = $this->input->post('secunitid', TRUE);
                        $forward_to = $this->input->post('forward_to', TRUE);
			$forward_to_2 = '';
			$forward_to_3 = '';
			if($forward_to != '0')
			{
				$forward_to_1 = explode('`',$forward_to);
                        	$forward_to2 = $forward_to_1[0];
                        	$forward_to3 = $forward_to_1[1];
			}

			$this->db->select_max('id')->from('bill_approval_status')->where('bill_no',$bill_no);
                	$maxauth = $this->db->get();
                	foreach($maxauth->result() as $row)
			{
                        	$max_auth = $row->id;
                        	$this->db->select('forward_to,authority_name')->from('bill_approval_status')->where('id',$max_auth);
                        	$maxim_auth = $this->db->get();
				$maxim_auth1 = $maxim_auth->row();
				$maxim_auth2 = $maxim_auth1->forward_to;
				$maxim_auth3 = $maxim_auth1->authority_name;
			}
			$comp_name = $this->session->userdata('user_name');
			if ($maxim_auth2 == $comp_name)
			{
				$auth_desig = $maxim_auth3;
			}
			else
			{
				$admin_name = $this->User_model->get_user_name_femail($comp_name);
				$auth_desig = $admin_name."(Administrator/)";
			}


			$fund = $this->input->post('fund', TRUE);
			$exp_type = $this->input->post('exp_type', TRUE);
                        $sanc_value = '';
                        $data_sanc_type = $this->input->post('sanc_type', TRUE);
                        if($data_sanc_type != 'select')
                        {
                        	if($data_sanc_type == 'plan')
                                	$sanc_value = $this->input->post('plan', TRUE);
                                else
                                	$sanc_value = $this->input->post('non_plan', TRUE);
                        }
		        $decision = $this->input->post('decision', TRUE);
			$approved_amount = $this->input->post('approved_amount', TRUE);
			$prev_approved_amount = $this->input->post('prev_approved_amount', TRUE);
		        $data_amount = $approved_amount;
                        $data_narration = $this->input->post('being', TRUE);
                        $data_narration1 = $this->input->post('being1', TRUE);
			
			if($decision == "Approved")
			{
				if($forward_to == "0")
                                {
                                        $this->messages->add('Please select Forward To From Dropdown List.' , 'error');
                                        $this->template->load('template', 'payment2/p2billapproval', $data);
                                        return;
                                }
			}
			if($fund != "0")
			{
				if($exp_type == "Select")
				{
					$this->messages->add('Please Select Expenditure Type From Dropdown List.','error');
					$this->template->load('template','payment2/p2billapproval',$data);
					return;
				}
			}
			if(($decision != "Hold") && ($decision != "Rejected"))
			{	
				if($data_sanc_type == "select")
                        	{
                                	$this->messages->add('Please Select Sanction Type From Dropdown List.', 'error');
                                	$this->template->load('template', 'payment2/p2billapproval', $data);
                                	return;
                        	}
                        	else
                        	{
                                	if($data_sanc_type == "plan" && $sanc_value == "select")
                                	{
                                        	$this->messages->add('Please Select Plan Sanction From Dropdown List.', 'error');
                                        	$this->template->load('template', 'payment2/p2billapproval', $data);
                                        	return;
                                	}
                                	if($data_sanc_type == "non_plan" && $sanc_value == "select")
                                	{
                                        	$this->messages->add('Please Select Plan Sanction From Dropdown List.', 'error');
                                        	$this->template->load('template', 'payment2/p2billapproval', $data);
                                        	return;
                                	}
                        	}

				if($approved_amount == '')
                        	{
                                	$this->messages->add('Please write Approved amount' , 'error');
                                	$this->template->load('template', 'payment2/p2billapproval', $data);
                                	return;
                        	}

				if($data_narration == '')
                        	{
                                	$this->messages->add('Please write narration in Narration box' , 'error');
                                	$this->template->load('template', 'payment2/p2billapproval', $data);
                                	return;
                        	}

			}
			if($decision != "Hold")
			{			
				if($data_narration1 == '')
                        	{
                                	$this->messages->add('Please write comments in Comments box' , 'error');
                                	$this->template->load('template', 'payment2/p2billapproval', $data);
                                	return;
                        	}
			}

			if($decision== "Select")
                        {
                                $this->messages->add('Please Select Decision From Dropdown List.', 'error');
                                $this->template->load('template', 'payment2/p2billapproval', $data);
                                return;
                        }
  	
			if($decision == 'Hold')
			{
				$this->messages->add('Bill is on HOLD. ');
                                $this->logger->write_message("success",'Bill is put on HOLD. By user '.$username);
                                redirect('payment2/showupload_bill_approval');
                                return;
			}
			
			$pay2_maxid = $this->Payment2_model->p2_approv_maxid($bill_no);
			$pay2maxid = explode(' ',$pay2_maxid);
			$maxbill_id = $pay2maxid[0];
			$for_wa_to = $pay2maxid[1];

			if($decision == 'Rejected')
                        {
				$this->db->trans_start();
				$today = date("Y/m/d H:i:s");
				$update_data_no_1 = array(
                                        'decision' => $decision,
                         	);
				if ( ! $this->db->where('id', $bill_no)->update('bill_voucher_create', $update_data_no_1))
                                {
                                        $this->db->trans_rollback();
                                        $this->messages->add('Error updating entries in bill voucher. '.$username.$submitter_id.$total_amount.'error');             
                                        $this->logger->write_message("error",'Error updating entries in bill voucher'.$username.$submitter_id.$total_amount.'error');
                                        $this->template->load('template', 'payment2/p2billapproval', $data);
                                        return;
                                }
				else
				{
					$update_data_no_2 = array(
						'approval_date' => $today,
						'approval_amount' => '',
						'approved_by' => $this->session->userdata('user_name'),
						'authority_name' => $auth_desig,
						'status' => $decision,
						'comments' => $data_narration1,
					);
					if ( ! $this->db->where('id', $maxbill_id)->update('bill_approval_status', $update_data_no_2))
                                	{
                                		$this->db->trans_rollback();
                                        	$this->messages->add('Error updating entries in bill approval status. '.$username.$submitter_id.$total_amount.'error');
                                        	$this->logger->write_message("error",'Error updating entries in bill approval status'.$username.$submitter_id.$total_amount.'error');
                                        	$this->template->load('template', 'payment2/p2billapproval', $data);
                                        	return;
					}
					else
					{
						$this->db->trans_complete();
					}
				}
				$message2 = base_url()."index.php/user/login";
				$message1 = "<b>Your bill is Rejected.</b>";
                        	$message = $message1."<br>".$message2;
                        	$subject = 'Bill Rejection in BGAS ';
                        	if($this->paymentreceipt->send_mail($bill_app->submitted_by, $subject, $message))
                        	$this->messages->add('Bill is Rejected Successfully');
                        	$this->logger->write_message("success",'Bill Is Rejected Successfully. By user '.$username);
                        	redirect('payment2/showupload_bill_approval');
                        	return;

			}
			if($decision=='Approved')
			{
				$this->db->select('id,code,group_id')->from('ledgers')->where('code',$ids);
                                $quer_yq = $this->db->get();
                                $quer_yn = $quer_yq->row();
                                $ledg_code1 = $quer_yn->code;
                                $groupid1 = $quer_yn->group_id;
                                $id1 = $quer_yn->id;
 				$account_code1 = $this->Budget_model->get_account_code('Expenses');
                                $temp1 = $this->p2startsWith($ledg_code1, $account_code1);
                                if($temp1)
                                {
                                        $parents1;
                                        $query_1 = $this->db->from('budgets')->where('code', $ledg_code1)->get();
                                        if($query_1->num_rows() > 0)
                                        {
                                                $this->db->from('budgets')->where('code', $ledg_code1);
                                                $quer_yl = $this->db->get();
                                                $quer_yl = $quer_yl->row();
                                                $budgetamt1 = $quer_yl->bd_balance;
                                                $useamt1 = $quer_yl->approval_amount;
                                                $consumeamt1 = $quer_yl->consume_amount;
                                                $allow1 = $quer_yl->allowedover;
                                                if($budgetamt1 > $useamt1)
                                                {
                                                        $available_amount1 = $budgetamt1 - $useamt1;  
                                                        if($data_amount > $available_amount1)
                                                        {
                                                                if(($allow1 == -1) || ($allow1 == 0))
                                                                {
                                                                        $this->messages->add('Budget is not sufficient to make this payment.','error');
                                                                        $this->template->load('template', 'payment2/p2billapproval',$data);
                                                                        return;
                                                                }
                                                          	$available_amount1 = $budgetamt1 - $useamt1 + $allow1;
                                                        	if($data_amount >= $available_amount1)
                                                         	{
                                                          		$this->messages->add('Budget is not sufficient to make this payment because approved amount exceeds budget limit.','error');
                                                                	$this->template->load('template', 'payment2/p2billapproval',$data);
                                                                	return;
                                                       		}
							}
						}
 						if($useamt1 >= $budgetamt1)
                                                {   
                                                        if(($allow1 == -1) || ($allow1 == 0))
                                                        {
                                                                $this->messages->add('Budget is not sufficient to make this payment because bd balance is less.','error');
                                                                $this->template->load('template', 'payment2/p2billapproval', $data);
                                                                return;
                                                        }
                                                        $overconsume_amount1 = $useamt1 - $budgetamt1 ;
                                                        if($data_amount > $allow1)
                                                        {
                                                                $this->messages->add('Approved amount is greater than allowedover.','error');
                                                                $this->template->load('template','payment2/p2billapproval' ,$data);
                                                                return;
                                                        }
						}
					}
				}
                                $this->db->trans_start();
				if($val5 == '')
				{
					$update_data_na1 = array(
						'narration' => $data_narration,
					);
					if(!$this->db->where('id',$bill_no)->update('bill_voucher_create',$update_data_na1))
					{
						$this->db->trans_rollback();
						$this->messages->add('Error updating narration.'.$username.$submitter_id.'error');
						$this->logger->write_message("error",'Error updating narration in bill voucher'.$username.$submitter_id.'error');
						$this->template->load('template','payment2/p2billapproval',$data);
						return;
					}
				}
				$this->db->trans_start();
				{
                                	$update_data_n1 = array(
                                        	'decision' => $decision,
                                        	'fund_id' => $fund,
                                        	'expenditure_type' => $exp_type,
                                        	'sanc_type' => $data_sanc_type,
                                        	'sanc_value' => $sanc_value,
                                        	'current_location' => $forward_to2,
                                        );
					if ( ! $this->db->where('id', $bill_no)->update('bill_voucher_create', $update_data_n1))
                                	{
                                        	$this->db->trans_rollback();
                                        	$this->messages->add('Error updating entries in bill voucher. '.$username.$submitter_id.$total_amount.'error');
                                        	$this->logger->write_message("error",'Error updating entries in bill voucher'.$username.$submitter_id.$total_amount.'error');
                                        	$this->template->load('template', 'payment2/p2billapproval', $data);
                                        	return;
                                	}	
                                	else
                                	{
                                        	$today = date("Y/m/d H:i:s");
                                        	$update_data_n2 = array(
                                        		'approval_date' => $today,
                                        		'approval_amount' => $approved_amount,
                                        		'approved_by' => $this->session->userdata('user_name'),
							'authority_name' => $auth_desig,
                                        		'status' => $decision,
                                        		'comments' => $data_narration1,
						);
						if ( ! $this->db->where('id', $maxbill_id)->update('bill_approval_status', $update_data_n2))
                                        	{
                                               	 	$this->db->trans_rollback();
                                                	$this->messages->add('Error updating entries in bill approval status. '.$username.$submitter_id.$total_amount.'error');
                                                	$this->logger->write_message("error",'Error updating entries in bill approval status'.$username.$submitter_id.$total_amount.'error');
                                                	$this->template->load('template', 'payment2/p2billapproval', $data);
                                                	return;
                                        	}
                                        	else
                                        	{
                                                	$insert_approval_data_n1 = array(
                                                		'bill_no' => $bill_no,
                                                		'forward_from' => $this->session->userdata('user_name'),
								'forward_to' => $forward_to2,
								'authority_name' => $forward_to3,
                                        			'forward_date' => $today,
                                                	);
                                                	if ( ! $this->db->insert('bill_approval_status', $insert_approval_data_n1))
                                                	{
                                                        	$this->db->trans_rollback();
                                                        	$this->messages->add('Error inserting new values. '.$username.'error');
                                                        	$this->logger->write_message("error",'Error inserting new values'.$username.'error');
                                                        	$this->template->load('template', 'payment2/p2billapproval', $data);
                                                        	return;
                                                	}
							else
							{
								$this->db->trans_complete();
							}
                                        	}
					}
				}
				$message2 = base_url()."index.php/user/login";
                        	$message1 = "<b>Please login to your account in Brihaspati General Accounting System and take actions on Bill Number $billid</b>";
                        	$message = $message1."<br>".$message2;
                        	$subject = 'Bill Approval in BGAS ';
                        	if($this->paymentreceipt->send_mail($forward_to2, $subject, $message))
                                $this->messages->add('Bill is approved successfully. ');
                                $this->logger->write_message("success",'Bill is approved successfully. By user '.$username);
                        	redirect('payment2/showupload_bill_approval');
                        	return;
//}
			}
			if($decision == 'vchrcrn')
			{
				$this->db->select('id,code,group_id')->from('ledgers')->where('code',$ids);
				$query_q = $this->db->get();
				$query_n = $query_q->row();
				$ledg_code = $query_n->code;
				$groupid = $query_n->group_id;
				$id = $query_n->id;
				/**
                                	* To identify "expense" entry, code of the selected ledger head
                            		* is being fetched. If the ledger head has "Expenses" as its 
                          		* super parent and the account is being debited, then it must
                               		* be a "expense" entry.
                             		* Then, the corresponding budget amount is updated. Since,
                            		* the budget amount is set for "expense" only.
				**/
                                $account_code = $this->Budget_model->get_account_code('Expenses');
				$temp = $this->p2startsWith($ledg_code, $account_code);
				if($temp)
				{
					$parents;
					$query1 = $this->db->from('budgets')->where('code', $ledg_code)->get();
					/**
                                        	* code for if particular head is not in budget,
                                        	* then made payment from parent which is present
                                        	* in budget.
                                        **/
					if($query1->num_rows() > 0)
			       		{
				 		$this->db->from('budgets')->where('code', $ledg_code);
				 		$query_l = $this->db->get();
				 		$query_l = $query_l->row();
						$budgetamt = $query_l->bd_balance;
		 				$useamt = $query_l->approval_amount;
				  		$consumeamt = $query_l->consume_amount;
				  		$allow = $query_l->allowedover;
						if($budgetamt > $useamt)
						{
				  			$available_amount = $budgetamt - $useamt;  //its wrong
							/**  payment amount is greater than or equal to available amount **/
							if($data_amount > $available_amount)
							{
								/* check for allowed over expense*/
								if(($allow == -1) || ($allow == 0))
		                				{
		                  					$this->messages->add('Budget is not sufficient to make this payment.','error');
		                  					$this->template->load('template', 'payment2/p2billapproval',$data);
		                  					return;
		                				}
				  				else
								{
									/* check for payment amount by adding allowd over amount + consume amount */
				  					$available_amount = $budgetamt - $useamt + $allow;
									if($data_amount >= $available_amount)
									{
			          						$this->messages->add('Budget is not sufficient to make this payment because approved amount exceeds budget limit.','error');
			          						$this->template->load('template', 'payment2/p2billapproval',$data);
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
		                  							$this->template->load('template', 'payment2/p2billapproval', $data);
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
		        								$this->template->load('template', 'payment2/p2billapproval', $data);
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
		        						$this->template->load('template', 'payment2/p2billapproval', $data);
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
		      							$this->template->load('template', 'payment2/p2billapproval', $data);
	              							return;
		   						}
							}
						}
 						/* consume amount is greater than allocated budget amount*/
						if($useamt >= $budgetamt)
						{ 	/* check for allowed over expenses */
							if(($allow == -1) || ($allow == 0))
			 				{
		           					$this->messages->add('Budget is not sufficient to make this payment because bd balance is less.','error');
								$this->template->load('template', 'payment2/p2billapproval', $data);
		           					return;
			 				}
							/** get over consume amount and check with allowed left **/
							$overconsume_amount = $useamt - $budgetamt ;
							/* payment amount is greater than allowed over amount*/
							if($data_amount > $allow)
		         				{
			   					$this->template->load('template','payment2/p2billapproval' ,$data);
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
		           						$this->template->load('template','payment2/p2billapproval' , $data);
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
		           						$this->template->load('template','payment2/p2billapproval' , $data);
		           						return;
		         					}
		       					}	
						}
					} 
					else
					{   
			  			$this->db->from('groups')->where('id', $groupid);
                          			$group_q = $this->db->get();
                          			$group = $group_q->row();
                       	  			$this->parent_id = $group->parent_id;
			  			$parent_id = $this->parent_id;
						$parents_get=$this->p2init1l($id,$groupid,$data_amount,$parent_id);

					}
                                	$bill_no = $this->input->post('bill_no', TRUE);
                                	$data_narration = $this->input->post('being', TRUE);
                                	$data_narration1 = $this->input->post('being1', TRUE);
                                	$data_narration2 = $data_narration.$data_narration1;
                                	$this->db->trans_start();
                                	$update_data_n1 = array(
                                        	'decision' => $decision,
                                        	'fund_id' => $fund,
                                        	'expenditure_type' => $exp_type,
                                        	'sanc_type' => $data_sanc_type,
                                        	'sanc_value' => $sanc_value,
                                        );
                                	if ( ! $this->db->where('id', $bill_no)->update('bill_voucher_create', $update_data_n1))
                                	{
                                        	$this->db->trans_rollback();
                                        	$this->messages->add('Error updating entries in bill voucher. '.$username.$submitter_id.$total_amount.'error');
                                        	$this->logger->write_message("error",'Error updating entries in bill voucher'.$username.$submitter_id.$total_amount.'error');
                                        	$this->template->load('template', 'payment2/p2billapproval', $data);
                                        	return;
                                	}
                                	else
                                	{
						$maxbi_llid = $maxbill_id;
                                        	$today = date("Y/m/d H:i:s");
                                        	$update_data_n2 = array(
                                        		'approval_date' => $today,
                                        		'approval_amount' => $approved_amount,
                                        		'approved_by' => $this->session->userdata('user_name'),
							'authority_name' => $auth_desig,
                                        		'status' => $decision,
                                        		'comments' => $data_narration1,
                                        	);
                                        	if ( ! $this->db->where('id', $maxbill_id)->update('bill_approval_status', $update_data_n2))
                                        	{
                                                	$this->db->trans_rollback();
                                                	$this->messages->add('Error updating entries in bill approval status. '.$username.$submitter_id.$total_amount.'error');
                                                	$this->logger->write_message("error",'Error updating entries in bill approval status'.$username.$submitter_id.$total_amount.'error');
                                                	$this->template->load('template', 'payment2/p2billapproval', $data);
                                                	return;
                                        	}
                                	}
                                	$this->db->trans_complete();
                               		$this->messages->add('Bill is approved successfully for Cheque Printing. ');
                                	$this->logger->write_message("success",'Bill is approved successfully. By user '.$username);
                                	redirect('payment2/showupload_bill_approval');
                                	return;
		      		}
			}
		}		
		$this->template->load('template','payment2/p2billapproval' , $data);
               	return;
	}

	/**
		*Code for voucher filling.
		*Here the parameter $bill_no is Bill Number.
		*This function can be accessed only when bill is approved and forwarded for voucher creation.
	**/
	function p2voucherfilling($bill_no)
        {
                if ( ! check_access('vouchercreation'))
                {
                        $this->messages->add('Permission denied.', 'error');
                        redirect('payment2/showupload_bill_approval');
                        return;
                }
                $this->template->set('page_title', 'Voucher Fill Form');
		$this->db->select('id, purchase_order_no, supplier_bill_no, submit_date, submitted_by, submitter_id, fund_id, total_amount, narration, expense_type, party_id, expenditure_type, sanc_type, sanc_value, decision')->from('bill_voucher_create')->where('id',$bill_no);
                $username = $this->config->item('account_name');
		$bill_vouch = $this->db->get();
		$vouch_fill = $bill_vouch->row();
		$narr1 = $vouch_fill->narration." ";
                $typexpense = explode('.', $vouch_fill->expense_type);
                $idexpen = $typexpense[0];
                $idexpen1 = $this->Ledger_model->get_ledger_id($idexpen);
		
		if ($vouch_fill->fund_id != '0'){	
		$this->db->select('id, name')->from('ledgers')->where('id' ,$vouch_fill->fund_id);
                $fund_r = $this->db->get();
                foreach($fund_r->result() as $row1)
		{
			$fu_nd = $row1->name;
		}
		}else{
			$fu_nd = "None";
		}

		if ($vouch_fill->expenditure_type != 'Select'){
			$vouch_expenditur_tp = $vouch_fill->expenditure_type;
		}else{
			$vouch_expenditur_tp = "None";
		}
		
		$this->db->select('comments')->from('bill_approval_status')->where('bill_no',$bill_no)->order_by('id','asc');
		$nar_comn = $this->db->get();
		foreach($nar_comn->result() as $row6)
		{
			$narr1 = $narr1." ".$row6->comments;
		}
		
		$this->db->select_max('id')->from('bill_approval_status')->where('bill_no',$bill_no);
		$maxvoucherid = $this->db->get();
		foreach($maxvoucherid->result() as $row2)
		{
			$maxvoucher_id = $row2->id;
			$this->db->select('forward_to, approval_amount, approved_by')->from('bill_approval_status')->where('id',$maxvoucher_id);
			$max_vouch = $this->db->get();
			foreach($max_vouch->result() as $row3)
			{
				$for_wardto = $row3->forward_to;
				$approv_alamount = $row3->approval_amount;
				$app_by = $row3->approved_by;
			}
		}
               
		$data['bill_no'] = array(
                        'name' => 'bill_no',
                        'id' => 'bill_no',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $vouch_fill->id, 
                        'readonly'=>'true',
                );
		
		$data['supplier_bill_no'] = array(
                        'name' => 'supplier_bill_no',
                        'id' => 'supplier_bill_no',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_fill->supplier_bill_no, 
                        'readonly'=>'true',
                );

		$data['purchase_order_no'] = array(
                        'name' => 'purchase_order_no',
                        'id' => 'purchase_order_no',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_fill->purchase_order_no,
                        'readonly'=>'true',
                );

		$data['forward_from'] = array(
                        'name' => 'forward_from',
                        'id' => 'forward_from',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_fill->submitter_id,
                        'readonly'=>'true',
                );

		$data['total_amount'] = array(
                        'name' => 'total_amount',
                        'id' => 'total_amount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_fill->total_amount,
                        'readonly'=>'true',
                );
		
		$data['expensestype'] = array(
                        'name' => 'expensestype',
                        'id' => 'expensestype',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_fill->expense_type,
                        'readonly'=>'true',
                );

		$data['approved_amount'] = array(
                        'name' => 'approved_amount',
                        'id' => 'approved_amount',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$approv_alamount,
                        'readonly'=>'true',
                );
		
		$data['approved_by'] = array(
                        'name' => 'approved_by',
                        'id' => 'approved_by',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$for_wardto, 
                        'readonly'=>'true',
                );
		
		$data['paid_to'] = array(
                        'name' => 'paid_to',
                        'id' => 'paid_to',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_fill->submitted_by, 
 			'readonly'=>'true',
		
                );

		$data['submitdate'] = array(
                        'name' => 'expensestype',
                        'id' => 'expensestype',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_fill->submit_date,
                        'readonly'=>'true',
                );
		
		$data['payment_mode_active']="Select";
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
			'value' =>$this->Secunit_model->get_secunitname($vouch_fill->party_id),
                        'readonly'=>'true',
		);
                
		$data['fund'] = array(
                       'name' => 'fund',
                        'id' => 'fund',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$fu_nd,
                        'readonly'=>'true',

                );
                
		$data['exp_type'] = array(
                        'name' => 'exp_type',
                        'id' => 'exp_type',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_expenditur_tp,
                        'readonly'=>'true',
                );
		
		$data['sanc_value'] = array(
                        'name' => 'sanc_value',
                        'id' => 'sanc_value',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_fill->sanc_value,
                        'readonly'=>'true',
                );
		
		$data['sanc_type'] = array(
                        'name' => 'sanc_type',
                        'id' => 'sanc_type',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' =>$vouch_fill->sanc_type, 
                        'readonly'=>'true',
                );
		
		$data['bank_cash']= $this->Ledger_model->get_all_ledgers_bankcash1();
                $data['bankcash_active']= '0';

		$data['narrate'] = array(
                        'name' => 'narrate',
                        'id' => 'narrate',
                        'cols' => '50',
                        'rows' => '4',
                        'value' => $narr1,
                        'readonly'=>'true',
                );
		
		$this->form_validation->set_rules('bank_cash','BANK AND CASH ACCOUNT','trim|required'.$bill_no);
		$this->form_validation->set_rules('payment_mode', 'METHOD OF PAYMENT', 'trim|required');
		
		if ($_POST)
                {
			$data['bill_no']['value'] = $this->input->post('bill_no', TRUE);
			$data['supplier_bill_no']['value'] = $this->input->post('supplier_bill_no', TRUE);
			$data['purchase_order_no']['value'] = $this->input->post('purchase_order_no', TRUE);
			$data['forward_from']['value'] = $this->input->post('forward_from', TRUE);
			$data['total_amount']['value'] = $this->input->post('total_amount', TRUE);
			$data['expensestype']['value'] = $this->input->post('expensestype', TRUE);
                        $data['approved_amount']['value'] = $this->input->post('approved_amount', TRUE);
                        $data['paid_to']['value'] = $this->input->post('paid_to', TRUE);
                        $data['approved_by']['value'] = $this->input->post('approved_by', TRUE);
                        $data['payment_mode_active'] = $this->input->post('payment_mode', TRUE);
			$data['fund']['value'] = $this->input->post('fund', TRUE);
			$data['exp_type']['value'] = $this->input->post('exp_type', TRUE);
			$data['sanc_value']['value'] = $this->input->post('sanc_value', TRUE);
                        $data['sanc_type']['value'] = $this->input->post('sanc_type', TRUE);
			$data['bankcash_active'] = $this->input->post('bank_cash',TRUE);
			$data['narrate']['value'] = $this->input->post('narrate', TRUE);
                }
		
		if ($this->form_validation->run() == FALSE)
                {
                        $this->messages->add(validation_errors(), 'error');
                        $this->template->load('template', 'payment2/p2voucherfilling', $data);
                        return;

                }
		else
                {
			$approved_amount = $data['approved_amount']['value'];
                        $billno = $bill_no;
                        $decision_1 = $vouch_fill->decision;
			$bank_cash=$this->input->post('bank_cash',TRUE);
                        $payment_mode=$this->input->post('payment_mode', TRUE);
                        $paid_to=$this->input->post('paid_to', TRUE);
			if($payment_mode == 'select')
                        {
                                $this->messages->add('Please Select Mode of Payment', 'error');
                                $this->template->load('template', 'payment2/p2voucherfilling', $data);
                                return;
                        }
			if($bank_cash == '0')
                        {
                                $this->messages->add('Please Select Bank Cash Acoount', 'error');
                                $this->template->load('template', 'payment2/p2voucherfilling', $data);
                                return;
                        }
		        
			if($decision_1 == "vchrcrn")
			{
                                $today = date("Y/m/d H:i:s");
                                $date = explode('/', $today);
                                $data_date = $date[0] .'-' . $date[1] .'-' .$date[2];
                                $value = explode('-', $bank_cash);
                                $app_roved_by = $app_by;
                                $sanc_ty_pe = $vouch_fill->sanc_type; 
                                $sanc_val_ue = $vouch_fill->sanc_value; 
                                $secunit_id = $vouch_fill->party_id; 
                                $exp_ty_pe = $vouch_expenditur_tp;
				$f_und = $data['fund']['value']; 
				$entry_type_id = 2;
				$number = $this->Entry_model->next_entry_number($entry_type_id);
                                $this->db->trans_start();
                                $insert_data=array(
                                        'entry_type' =>'2',
                                        'number' => $number,
                                        'date'       => $data_date,
                                        'dr_total' => $approved_amount,
                                        'cr_total' => $approved_amount,
                                        'narration' => $narr1,
                                        'update_date' => $data_date,
                                        'submitted_by' => $app_roved_by,
                                        'forward_refrence_id'  => '0',
                                        'backward_refrence_id' => '0',
					'secunitid' => $secunit_id,
					'sanc_letter_date' => '',
					'sanc_type' => $sanc_ty_pe,
					'sanc_value' => $sanc_val_ue,
					'vendor_voucher_number' =>$vouch_fill->supplier_bill_no,
                       		);
                                if ( ! $this->db->insert('entries', $insert_data))
                                {
                                	$this->db->trans_rollback();
                                 	$this->messages->add('Error addding Entry.', 'error');
					$this->logger->write_message("error", "Added journal Bill/Voucher No.".$number."By".$username);
                                 	$this->template->load('template', 'payment2/p2voucherfilling', $data);
                                 	return;
                                 } 
				 else 
				 {
				 	$entry_id = $this->db->insert_id();
					$this->logger->write_message("success", "Added journal Bill/Voucher No.".$number."[id :".$entry_id."].By".$username);
                                 }
                                 if(($f_und == '0') && ($exp_ty_pe == 'Select'))
				 {
				 	$insert_ledger_data = array(
                                        	'entry_id' => $entry_id,
                                        	'ledger_id' =>$value[0],
                                        	'amount' => $approved_amount,
                                        	'dc' => 'C',
                                        	'update_date' => $data_date,
                                        	'forward_refrence_id'  => '0',
                                        	'backward_refrence_id' => '0',
                               		 );
                                	 if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                               		 {
                                         	$this->db->trans_rollback();
                                         	$this->messages->add('Error adding Ledger account - ' . $value[0] . ' to Entry.', 'error');
                                         	$this->template->load('template', 'payment2/p2voucherfilling', $data);
                                        	return;
                                	 } 
                                	 $insert_ledger_data = array(
                                         	'entry_id' => $entry_id,
                                         	'ledger_id' => $idexpen1,
                                         	'amount' => $approved_amount,
                                         	'dc' => 'D',
                                         	'update_date' => $data_date,
                                         	'forward_refrence_id'  => '0',
                                         	'backward_refrence_id' => '0',
					 	'secunitid' => $secunit_id,
                               		 );
                                	 if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                               		 {
                                         	$this->db->trans_rollback();
                                         	$this->messages->add('Error adding Ledger account - ' . $idexpen . ' to Entry.', 'error');
                                         	$this->template->load('template', 'payment2/p2voucherfilling', $data);
                                        	return;
                               		 }	
				}
	                        else
				{
					if(($exp_ty_pe == "Revenue") && ($f_und != '0'))
					{
                                        	$insert_ledger_data = array(
                     		                	'entry_id' => $entry_id,
                                	                'ledger_id' =>$value[0],
                                        	        'amount' => $approved_amount,
                                        	        'dc' => 'C',
                                        	        'update_date' => $data_date,
                                       		        'forward_refrence_id'  => '0',
                                       		        'backward_refrence_id' => '0',
                                        	);
                                        	if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                        	{
							$this->db->trans_rollback();
                                        		$this->messages->add('Error adding Ledger account - ' . $value[0] . ' to Entry.', 'error');
                                        		$this->template->load('template', 'payment2/p2voucherfilling', $data);
                                        		return;
                                       		}
						$insert_fund_data = array(
                                                   	'entry_id' => $entry_id,
                                                        'ledger_id' => $vouch_fill->fund_id,
                                                        'amount' => $approved_amount,
                                                        'dc' => 'D',
                                                        'update_date' => $data_date,
                                                        'forward_refrence_id'  => '0',
                                                        'backward_refrence_id' => '0',
                                                        'secunitid' => $secunit_id,
                                                );
                                                if ( ! $this->db->insert('entry_items', $insert_fund_data))
                                                {
                                                    	$this->db->trans_rollback();
                                                        $this->logger->write_message("error", "Error adding fund id:" . $vouch_fill->fund_id);
						}
						else 
						{
                                                    	$entry_items_id = $this->db->insert_id();
						}
                                       		$this->db->select('id')->from('ledgers')->where('name', 'Transit Income');
                                        	$query = $this->db->get();
                                        	$income = $query->row();
                                        	$income_id = $income->id;
						$insert_income_data = array(
                                                	'entry_id' => $entry_id,
                                                	'ledger_id' => $income_id,
                                                	'amount' => $approved_amount,
                                                	'dc' => 'C',
                                                	'update_date' => $data_date,
                                                	'forward_refrence_id' => '0',
                                                	'backward_refrence_id' => '0',
                                                	'secunitid' => $secunit_id,
                                        	);
						if ( ! $this->db->insert('entry_items', $insert_income_data))
                                        	{
                                                	$this->db->trans_rollback();
                                                        $this->logger->write_message("error", "Error adding transit income");
                                       		}	
		                            	$this->db->select('name')->from('ledgers')->where('id', $vouch_fill->fund_id);;
                                                $quer_y = $this->db->get();
                                                $ledger = $quer_y->row();
                                                $ledger_name = $ledger->name;
						$insert_expense_data = array(
                                                	'fund_id' => $vouch_fill->fund_id,
                                                        'fund_name' => $ledger_name,
                                                        'amount' => $approved_amount,
                                                        'date' => $data_date,
                                                        'type' => $exp_ty_pe,
                                                        'entry_items_id' => $entry_items_id,
                                               	);
					        if ( ! $this->db->insert('fund_management', $insert_expense_data))
                                                {	
                                                	$this->db->trans_rollback();
                                                        $this->logger->write_message("error", "Error adding expenditure details for fund :" . $vouch_fill->fund_id);
                                                }
					}
                                        elseif(($exp_ty_pe == "Capital") && ($f_und != '0'))
					{	
                                        	$insert_ledger_data = array(
                                                	'entry_id' => $entry_id,
                                                        'ledger_id' =>$value[0],
                                                   	'amount' => $approved_amount,
                                                   	'dc' => 'C',
                                                   	'update_date' => $data_date,
                                                   	'forward_refrence_id'  => '0',
                                                   	'backward_refrence_id' => '0',
                                                );
                                                if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->messages->add('Error adding Ledger account - ' . $value[0] . ' to Entry.', 'error');
                                                        $this->template->load('template', 'payment2/p2voucherfilling', $data);
                                                        return;
                                                }
                                                $insert_ledger_data = array(
                                                        'entry_id' => $entry_id,
                                                        'ledger_id' => $idexpen1,
                                                        'amount' => $approved_amount,
                                                        'dc' => 'D',
                                                        'update_date' => $data_date,
                                                        'forward_refrence_id'  => '0',
                                                        'backward_refrence_id' => '0',
                                                        'secunitid' => $secunit_id,
                                                );
                                                if ( ! $this->db->insert('entry_items', $insert_ledger_data))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->logger->write_message("error", "Error adding fund id:" . $idexpen);
                                                }
						else 
						{
                                                        $entry_fund_id = $this->db->insert_id();
						}
	                                        $this->db->select('name')->from('ledgers')->where('id', $vouch_fill->fund_id);
                                                $qu_ery = $this->db->get();
                                                $led_ger = $qu_ery->row();
                                                $ledge_r_name = $led_ger->name;
						$insert_fund_data = array(
                                                        'fund_id' => $vouch_fill->fund_id,
                                                        'fund_name' => $ledge_r_name,
                                                        'amount' => $approved_amount,
                                                        'date' => $data_date,
                                                        'type' => $exp_ty_pe,
                                                        'entry_items_id' => $entry_fund_id,
                                                );
						if ( ! $this->db->insert('fund_management', $insert_fund_data))
                                                {
                                                        $this->db->trans_rollback();
                                                        $this->logger->write_message("error", "Error adding expenditure details for fund :" . $vouch_fill->fund_id);
                                                }
					}
				} 
			}
			if($payment_mode !='cash')
			{
				$this->db->trans_start();
                        	$insert_data=array(
                        		'ledger_id' =>$idexpen1,
			 		'entry_no' =>$entry_id,
					'update_cheque_no'=>'1',
                        	);
                        	if ( ! $this->db->insert('cheque_print', $insert_data))
                        	{
                        		$this->db->trans_rollback();
                                	$this->messages->add('Error addding cheque entry.', 'error');
                                	$this->template->load('template', 'payment2/p2voucherfilling', $data);
                                	return;
                        	} 
				else 
				{
                        		$this->db->trans_complete();
                        	}
			}
				
			$today = date("Y/m/d H:i:s");
                        $this->db->trans_start();
			$vc_date=date("Y-m-d H:i:s");
                        $update_new_data_2 = array(
				'entry_id' => $entry_id,
                                'vc_date' => $vc_date,
                                'bank_cash_account' => $bank_cash,
                                'mode_of_payment' => $payment_mode,
                                'payment_status' => 'DONE',
                                'payment_date' => $vc_date,
			);
			if ( ! $this->db->where('id', $bill_no)->update('bill_voucher_create', $update_new_data_2))
                        {
				$this->db->trans_rollback();
                                $this->messages->add('Error updating entries in bill voucher create. '.$username.$submitter_id.$total_amount.'error');
                                $this->logger->write_message("error",'Error updating entries in bill voucher create'.$username.$submitter_id.$total_amount.'error');
				$this->template->load('template', 'payment2/p2voucherfilling', $data);
                                return;
			}
                        else
                        {
				$this->db->trans_complete();
			}
			$message2 = base_url()."index.php/user/login";
			$message1 = "<b>Your bill is approved and voucher is created successfully.</b>";
                        $message = $message1."<br>".$message2;
                       	$subject = 'Bill Voucher Creation in BGAS ';
                       	if($this->paymentreceipt->send_mail($vouch_fill->submitted_by, $subject, $message))
                      	$this->messages->add('Payment Voucher is created successfully. ');
                      	$this->logger->write_message("success","Added Payment Bill/Voucher No.".$number."By".$username);
                      	redirect('payment2/showupload_bill_approval');
                      	return;
		}
                return;
	}

	/**
                *Code for Bill Print preview.
                *Here the parameter $bill_no is Bill Number.
                *This function generates the printpreview of approved bill.
        **/
	function p2bill_printpreview($bill_no)
        {
		$this->load->model('Setting_model');
		$p_name = "";
		$p_add = "";
		$data['user_name'] = $this->config->item('account_email');
                if ( ! $show = $this->Payment2_model->p2bill_printvalue($bill_no))
                {
                        $this->messages->add('Unable to get the information for bill create.', 'error');
                        return;
                }
		if ( ! $show1 = $this->Payment2_model->p2bill_pri_ntvalue($bill_no))
                {
                        $this->messages->add('Unable to get the information for bill status.', 'error');
                        return;
                }
		$for_opt = $this->Payment2_model->p2_forward_to($bill_no);
		$subm_id = $this->User_model->get_user_name_femail($show->submitter_id);
		$by_appro = $this->User_model->get_user_name_femail($show1->approved_by);
		$this->db->select('party_id')->from('bill_voucher_create')->where('id',$bill_no);
		$sec_id = $this->db->get();
                $sec_q = $sec_id->row();
		$this->party_id = $sec_q->party_id;
		$party_id = $this->party_id;
		$this->db->select('partyname,address')->from('addsecondparty')->where('sacunit',$party_id);
		$p_data = $this->db->get();
		foreach($p_data->result() as $row)
                {
                        $p_name= $row->partyname;
			$p_add = $row->address;
		}
		if($p_add == ''){
			$p_add1 = "None";}
		else{
			$p_add1 = $p_add;}
		$fu_nd = $show->fund_id;
		if($fu_nd == 0){
			$fund_id1 = "None";}       
                else{
                	$this->db->select('name')->from('ledgers')->where('id' ,$fu_nd);
                	$fund_r = $this->db->get();
                	foreach($fund_r->result() as $row1){
                        	$fund_id1 = $row1->name;}
                }
		if(($show->expenditure_type == "Select") || ($show->expenditure_type == "")){
			$prnexpnd_type = "None";}
		else{
			$prnexpnd_type = $show->expenditure_type;}
		if($show->sanc_type == "plan"){
			$prnsanc_type = "Plan";}
		elseif($show->sanc_type == "non_plan"){
			$prnsanc_type = "Non Plan";}
		elseif($show->sanc_type == "plan_sfc_scheme"){
			$prnsanc_type = "Plan-Specific Schemes";}
		elseif($show->sanc_type == "plan_other_scheme"){
			$prnsanc_type = "Other Schemes";}

		$narr1 = $show->narration." ";
		$this->db->select('comments')->from('bill_approval_status')->where('bill_no',$bill_no)->order_by('id','asc');
                $nar_comn = $this->db->get();
                foreach($nar_comn->result() as $row6)
                {
                        $narr1 = $narr1." ".$row6->comments;
                }

		$this->db->select('approved_by,authority_name')->from('bill_approval_status')->where('bill_no',$bill_no)->where('status','vchrcrn');
		$appname = $this->db->get();
		$appname1 = $appname->row();
		$appname2 = $appname1->approved_by;
		$appname3 = $appname1->authority_name;
               	$name1 = explode('/',$appname3);
              	$name4 = $name1[0].")";

		$data['us_name'] = $name4;
                $data['bill_no']=$bill_no;
		$data['p_name']=$p_name;
		$data['p_add']=$p_add1;
                $data['approved_amount'] = $show1->approval_amount;
                $data['approved_by'] = $for_opt;
                $data['fun_d_id']= $fund_id1;
                $data['narrate']= $narr1;
		$data['vc_date']=$show->vc_date;
		$data['submitter_id']=$subm_id;
                $data['mode_of_payment']=  $show->mode_of_payment;
		$data['expense_type']=$show->expense_type;
		$data['bank_cash_account']=$show->bank_cash_account;
                $data['paid_to']=  $show->submitted_by;
                $data['exp_type']=$prnexpnd_type;
                $data['sanc_value']=$show->sanc_value;
                $data['sanc_type']=$prnsanc_type;

                $this->load->view('payment2/p2bill_printpreview',$data);
                return;
        }

	/**
                *Code for Rejected Bill Print preview.
                *Here the parameter $bill_no is Bill Number.
                *This function generates the printpreview of Rejected bill.
        **/
	function p2reject_printpreview($bill_no)
        {
		$p_name = "";
		$p_add = "";
		$data['user_name'] = $this->config->item('account_email');
                if ( ! $show = $this->Payment2_model->p2bill_printvalue($bill_no))
                {
                        $this->messages->add('Unable to get the information for bill create.', 'error');
                        return;
                }
		if ( ! $show1 = $this->Payment2_model->p2bill_pri_ntvalue($bill_no))
                {
                        $this->messages->add('Unable to get the information for bill status.', 'error');
                        return;
                }
		$for_opt = $this->Payment2_model->p2_forward_to($bill_no);
		$for_opt1 = $this->Payment2_model->p2_reject_by($bill_no);
                $subm_id = $this->User_model->get_user_name_femail($show->submitter_id);
                $by_appro = $this->User_model->get_user_name_femail($show1->approved_by);
		$this->db->select('party_id')->from('bill_voucher_create')->where('id',$bill_no);
		$sec_id = $this->db->get();
                $sec_q = $sec_id->row();
		$this->party_id = $sec_q->party_id;
		$party_id = $this->party_id;
		$this->db->select('partyname,address')->from('addsecondparty')->where('sacunit',$party_id);
		$p_data = $this->db->get();
		foreach($p_data->result() as $row)
                {
                        $p_name= $row->partyname;
			$p_add = $row->address;
		}
		if($p_add == ''){
                        $p_add1 = "None";}
                else{
                        $p_add1 = $p_add;}
		$fu_nd = $show->fund_id;
		if($fu_nd != 0){
                	$this->db->select('name')->from('ledgers')->where('id' ,$fu_nd);
                	$fund_r = $this->db->get();
                	foreach($fund_r->result() as $row){
                        	$fund_id = $row->name;}
                }       
                else{
			$fund_id = "None";}
		if(($show->expenditure_type == "Select") || ($show->expenditure_type == "")){
                        $prnexpnd_type = "None";}
                else{
                        $prnexpnd_type = $show->expenditure_type;}

		if($show->bank_cash_account == "NO"){
			$prntbcaccn = "None";}
		else{
			$prntbcaccn = $show->bank_cash_account;}
		if($show->mode_of_payment == "NO"){
                        $prntmodpym = "None";}
                else{
                        $prntmodpym = $show->mode_of_payment;}
		if($show->sanc_type == "plan"){
                        $prnsanc_type = "Plan";}
                elseif($show->sanc_type == "non_plan"){
                        $prnsanc_type = "Non Plan";}
                elseif($show->sanc_type == "plan_sfc_scheme"){
                        $prnsanc_type = "Plan-Specific Schemes";}
                elseif($show->sanc_type == "plan_other_scheme"){
                        $prnsanc_type = "Other Schemes";}
                elseif($show->sanc_type == ""){
                        $prnsanc_type = "None";}
		if($show->sanc_value == ""){
			$prnsanc_value = "None";
		}
		else{
			$prnsanc_value = $show->sanc_value;}


		$narr1 = $show->narration." ";
                $this->db->select('comments')->from('bill_approval_status')->where('bill_no',$bill_no)->order_by('id','asc');
                $nar_comn = $this->db->get();
                foreach($nar_comn->result() as $row6)
                {
                        $narr1 = $narr1." ".$row6->comments;
                }

 		$this->db->select('approved_by,authority_name')->from('bill_approval_status')->where('bill_no',$bill_no)->where('status','Rejected');
                $appname = $this->db->get();
                $appname1 = $appname->row();
                $appname2 = $appname1->approved_by;
                $appname3 = $appname1->authority_name;
                $name1 = explode('/',$appname3);
                $name4 = $name1[0].")";

                $data['us_name'] = $name4;
                $data['bill_no']=$bill_no;
		$data['p_name']=$p_name;
		$data['p_add']=$p_add1;
                $data['approved_amount'] = $show1->approval_amount;
                $data['approved_by'] = $for_opt;
                $data['rejected_by'] = $for_opt1;
                $data['narrate']= $narr1;
		$data['vc_date']=$show1->approval_date;
		$data['submitter_id']=$subm_id;
                $data['mode_of_payment']=$prntmodpym;
		$data['expense_type']=$show->expense_type;
		$data['bank_cash_account']=$prntbcaccn;
                $data['paid_to']= $show->submitted_by;
                $data['fun_d_id']= $fund_id;
                $data['exp_type']=$prnexpnd_type;
                $data['sanc_value']=$prnsanc_value;
                $data['sanc_type']=$prnsanc_type;

                $this->load->view('payment2/p2reject_printpreview',$data);
                return;
        }
	
	/**
		*Code for string comparison.
		*This function compares length of two strings.
		*After that it first calculates length of string2 and then select string1 according to length of string2 and then compares both strings.
		*It returns either TRUE or FALSE.
	**/
	function p2startsWith($str1, $str2)
        {
                return !strncmp($str1, $str2, strlen($str2));
        }

	/**
		*Code to display Fund Balance.
		*This function Available Balance according to selected fund.
	**/
	function p2fund_balance($name)
	{
		$leid = $name;
                $oneno = "1";
                $zerono = "0";
                $bill_no=$this->session->userdata('bill_no');
                $this->db->select('fund_id')->from('bill_voucher_create')->where('id',$bill_no);
                $fun_dr = $this->db->get();
                $fun_dapp = $fun_dr->row();
                $ol_did = $fun_dapp->fund_id;
		$legr_bal = $this->Ledger_model->get_ledger_balance($leid);
		$o_ne = $legr_bal." ".$oneno;
		$z_ero = $legr_bal." ".$zerono;
                if($leid == $ol_did)
                {
                        echo "$o_ne";
                        return;
                }
                else
                {
                        echo "$z_ero";
                        return;
                }
	}

	/**
		*Code to display balance Fund balance in read only mode.
		*This function displays Available Balance of Fund if it was already selected by the previous authority who approved bill.
		*If in case Fund will be changed then it hides the Fund balance of previously selected fund and displays balance of currently selected Fund.
	**/	
	function p2prev_fund($name)
	{
		$new_id = $name;
		$one = "1";
		$zero = "0";
 		$bill_no=$this->session->userdata('bill_no');
		$this->db->select('fund_id')->from('bill_voucher_create')->where('id',$bill_no);
		$fund_r = $this->db->get();
                $fund_app = $fund_r->row();
		$old_id = $fund_app->fund_id;
		if($new_id == $old_id)
		{
			echo "$one";
			return;
		}
		else
		{
			echo "$zero";
			return;
		}
	}

	/**
		*Code to display balance in selected Expense.
		*Here the parameter $name contains code and name of selected Expense.
		*This functions displays balance of selected expense at the time Bill upload and Bill approval.
	**/
	function p2ledger_budget($name)
      	{
		$split = $name;
                $spl_it = explode('.',$split);
                $flagm = $spl_it[0];	
                $na_me = $spl_it[1];
			
		$this->db->from('budgets')->where('code', $na_me);
		$query=$this->db->get();
		$query_l = $query->row();
		$no_rows=$query->num_rows();
		if($no_rows > 0 )
		{
			$this->bdbalance = $query_l->bd_balance;
                   	$this->consumeamt = $query_l->consume_amount;
			$budgetamt = $this->bdbalance;
                   	$consumeamt= $this->consumeamt;
                   	$avail_budget = $budgetamt - $consumeamt;	
		}
		elseif($no_rows == NULL)
		{
			$length = strlen($na_me);
			while(($no_rows == 0 || $no_rows == NULL ) && $length >="6")
			{ 
				$na_me = substr($na_me,0,$length-2);
                        	$length = strlen($na_me);
                        	$this->db->from('budgets')->where('code', $na_me);
                        	$val=$this->db->get();
				$no_rows=$val->num_rows();
		//	}
				if($length == 4 && $no_rows == 0)
				{
					$avail_budget= "0.00";
				}
				else 
				{
					foreach($val->result() as $row)
					{
					/*	$this->bdbalance = $row->bd_balance;
                                        	$this->consumeamt = $row->consume_amount;
                                		$budgetamt = $this->bdbalance;
                                        	$consumeamt= $this->consumeamt;*/
						$budgetamt = $row->bd_balance;
                                        	$consumeamt = $row->consume_amount;
                                        	$avail_budget = $budgetamt - $consumeamt;
		               		}
				}    
			} 
		}
		if($flagm)
		{
                	return $avail_budget;
		}
		else
		{
			echo "$avail_budget";
			return ;
		}
        }

	function p2init1l($id,$groupid,$data_amount,$parent_id)
        {
		$this->load->library('GetParentlist');
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
                     	{
                   		$available_amount=$budgetamt - $useamt ;//its wrong
                                        /**  payment amount is greater than or equal to available amount **/
                     		if($data_amount > $available_amount)
                              	{
                                                /* check for allowed over expense*/
                              		if(($allow == -1) || ($allow == 0))
                                	{
                                     		$this->messages->add('Budget is not sufficient to make this payment.','error');
                                          	redirect('payment2/p2billapproval');
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
                                                    	redirect('payment2/p2billapproval');
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
                                                             	redirect('payment2/p2billapproval');
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
                                                               	$this->template->load('template', 'payment2/p2billapproval', $data);
                                                              	redirect('payment2/p2billapproval');
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
                                     	$parents = new GetParentlist();
                                    	$parents->init($groupid,$data_amount);
                                    	$this->db->from('budgets')->where('budgetname', 'Main Budget');
                                   	$query_ll = $this->db->get();
                                     	$query_ll = $query_ll->row();
                                   	$this->amt1 = $query_ll->bd_balance;
                                    	$this->useamt1 = $query_ll->consume_amount;
                                       	$update_data2 = $this->useamt1 + $data_amount;
                                     	$update_data3 = array('consume_amount' => $update_data2);
                                       	if (!$this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                     	{
                                  		$this->db->trans_rollback();
                                         	$this->messages->add('Error updating total expenses amount in budget.', 'error');
                                           	redirect('payment2/p2billapproval');
                                           	return;
                                   	}
                  		}
          		}    
                                /* consume amount is greater than allocated budget amount*/
                     	if($useamt >= $budgetamt)
                      	{
                                        /* check for allowed over expenses */
                       		if(($allow == -1) || ($allow == 0))
                        	{
                        		$this->messages->add('Budget is not sufficient to make this payment.','error');
                                       	redirect('payment2/p2billapproval');
                                   	return;
                           	}
                                        /** get over consume amount and check with allowed left **/
                              	$overconsume_amount = $useamt - $budgetamt ;
                                        /* payment amount is greater than allowed over amount*/
                                if($data_amount > $allow)
                           	{
                            		$this->messages->add('Budget is not sufficient to make this payment.','error');
                                     	redirect('payment2/p2billapproval');
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
                                               	redirect('payment2/p2billapproval');
                                            	return;
                                      	}
                                      	$parents = new GetParentlist();
                                    	$parents->init($groupid,$data_amount);
                                    	$this->db->from('budgets')->where('budgetname', 'Main Budget');
                                     	$query_ll = $this->db->get();
                                       	$query_ll = $query_ll->row();
                                     	$this->amt1 = $query_ll->bd_balance;
                                      	$this->useamt1 = $query_ll->consume_amount;
                                   	$update_data2 = $this->useamt1 + $data_amount;
                                       	$update_data3 = array('consume_amount' => $update_data2);
                                      	if(!$this->db->where('budgetname', 'Main Budget')->update('budgets', $update_data3))
                                    	{
                                     		$this->db->trans_rollback();
                                          	$this->messages->add('Error updating total expenses amount in budget.', 'error');
                                            	redirect('payment2/p2billapproval');
                                             	return;
                                       	}
                        	}
             		}
		}
       		else
		{
                	$this->p2get_parent_groups($id,$data_amount,$parent_id);
           	}
       	}

	function p2get_parent_groups($id,$data_amount,$parent_id)
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
                        	redirect('payment2/p2showupload_bill_approval');
				return;
			}
			else 
			{ 
                        	$this->p2init1l($id,$groupid,$data_amount,$parent_id);
			}
                }
        }
}

?>
