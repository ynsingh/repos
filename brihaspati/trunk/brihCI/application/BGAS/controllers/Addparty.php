<?php

class Addparty extends CI_Controller {
function __construct() {
        parent::__construct();
		$this->load->model('secunit_model');
                $this->load->library('session');
                $this->load->model('upload_model');
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('user/login');
        }
    }

	function Addparty()
	{
		parent::Controller();
/*
		$this->load->model('secunit_model');
		$this->load->library('session');
		$this->load->model('upload_model');
*/
		/* Check access */

		if ( ! check_access('create entry'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}

		return;
	}
		
	function index()
        {
                redirect('addparty/show');
                return;
        }	
	

	function show()
	{
		//$this->load->library('session');
        	$asc_order = $this->session->userdata('order_change');
		$asc_id_order = $this->session->userdata('order_id_change');
		$this->template->set('nav_links', array('addparty/add' => 'Add Party','addparty/mulparty' => 'Upload Party'));
		$this->template->set('page_title', 'Show Party List ');
		if($asc_order == '1'){
		$this->db->from('addsecondparty')->order_by('partyname', 'asc');
                $pdetail = $this->db->get();
                $data['party_detail'] = $pdetail;
		}else{
		$this->db->from('addsecondparty')->order_by('id', 'asc');
		$pdetail = $this->db->get();
		$data['party_detail'] = $pdetail;
		}
		if($asc_id_order == '1'){
                $this->db->from('addsecondparty')->order_by('sacunit', 'asc');
                $pdetail = $this->db->get();
                $data['party_detail'] = $pdetail;
                }
		$data['sbal']=$this->secunit_model->get_all_secclsbal();
		// code for searching a given text
		$text = '';
		$data['search'] = '';
		$data['search_by'] = array(
			"Select" => "Select",
                        "sacunit" => "Party ID",
                        "partyname"=> "Party Name",
			"sacunit#1"=> "Party Type",
			"mobnum"=> "Mobile NO.",
			"email"=> "Email Id",
			"bancacnum"=> "Bank A/C No.",
			"bankname"=> "Bank Name",
			"branchname"=> "Branch Name",
			"ifsccode"=> "IFSC Code",
			"pan"=> "PAN No.",
			"u_id" => "UID No.",
			"tan"=> "TAN No.",
			"staxnum"=> "Service Tax No.",
			"vat"=> "VAT No.",
			"gst"=> "GST No",
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

		$this->form_validation->set_rules('search_by', 'Search By', 'trim|required');
		$this->form_validation->set_rules('text', 'Text', 'trim|required');
		/* Validating form */

		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'addparty/index', $data);
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
			redirect('addparty/index');
		}
		else {
			if($data_search_by == "partyname" || $data_search_by == "mobnum" || $data_search_by == "bancacnum" || $data_search_by == "ifsccode" || $data_search_by == "pan" ||  $data_search_by == "u_id" || $data_search_by == "tan" || $data_search_by == "staxnum" || $data_search_by == "vat" || $data_search_by == "gst" )
			{
				if(! ctype_alnum($data_text)) {
					$this->messages->add('Please enter alphanumeric value.', 'error');
					redirect('addparty/index');
				}
			}
		}
		$field = $data_search_by . '      ' . 'LIKE';
		$text = $data_text;
		if($data_search_by == 'sacunit#1'){
			$field_name=explode("#",$data_search_by);
			$field = $field_name[0] . '      ' . 'LIKE';
			if ($text == 'student') {
				$text='01';
			} elseif ($text == 'clerical staff') {
		  		$text='02';
			} elseif($text == 'technical staff') {
				$text='03';
			} elseif($text == 'supplier') {
        			$text='04';
			} elseif($text == 'admin staff') {
        			$text='05';
			} elseif($text == 'contractor') {
        			$text='06';
			} elseif($text == 'service provider') {
        			$text='07';
			} elseif($text == 'alumni/Doner') {
   				$text='08';
			} elseif($text == 'author') {
   				$text='09';
			} elseif($text == 'illustrator') {
   				$text='10';
			} elseif($text == 'participant') {
   				$text='11';
			} elseif($text == 'invites/Speaker') {
   				$text='12';
			} elseif($text == 'officers') {
   				$text='13';
			} elseif($text == 'others') {
   				$text='14';
			} else{
				 $this->messages->add('Search proper name of Party Type.', 'error');
			}

		}
		$this->db->from('addsecondparty')->where($field, '%' . $text . '%');
		$pdetail = $this->db->get();
		$data['party_detail'] = $pdetail;
		if( $pdetail->num_rows() < 1 )
		{
			$this->messages->add($text . ' is not found.', 'error');
			redirect('addparty/index');
		}
		//$data['abc']=$abc;
		$data['search'] = $data_search_by;
		$this->template->load('template', 'addparty/index', $data);
		$this->session->unset_userdata('order_change');
		return;
	}

//function is used for upload multiple party at a time
	function mulparty()
	{
		$this->load->library('form_validation');
		$this->template->set('nav_links', array('addparty/add' => 'Add Party','addparty/show' => 'Show Party'));
		$this->template->set('page_title', 'Upload Party');
		$logdata['Sr']="";
		if (isset($_FILES['userfile'])) {
			if ($_FILES["userfile"]["error"] > 0) {
        	 		echo "Return Code: " . $_FILES["userfile"]["error"] . "<br />";
        		}
			$file_name = $_FILES["userfile"]["name"];
			$ext = substr(strrchr($file_name, '.'), 1);

	//	if (file_exists($file_name)) {
			if (file_exists($_FILES["userfile"]["tmp_name"])) {
				if ($ext == 'txt') {
				//if (is_readable($file_name)) {
					if (is_readable($_FILES["userfile"]["tmp_name"])) {
						$i=1;
				//	$h = fopen($file_name,"r");
						$h = fopen($_FILES["userfile"]["tmp_name"],"r");
				//		$data[] = array();
						while (false !== ($line = fgets($h)))
        				        {
			//				$data[0]="";$data[1]="";$data[2]="";$data[3]="";$data[4]="";$data[5]="";$data[6]="";$data[7]="";$data[8]="";$data[9]="";$data[10]="";$data[11]="";$data[12]="";$data[13]="";$data[14]="";$data[15]="";$data[16]="";$data[17]="";
	
						        $data = explode( ";", $line );
							if(($data[0]=="") || ($data[1]=="")){
								$msg1=" The Party name or party role is missing";
							}
							else{
								$msg1= $this->addoneparty($data[0],$data[1],$data[2],$data[3],$data[4],$data[5],$data[6],$data[7],$data[8],$data[9],$data[10],$data[11],$data[12],$data[13],$data[14],$data[15],$data[16],$data[17]);
								//$msg1= addoneparty($data_pname,$data_partyrole,$data_address,$data_accountemail,$data_mnumber,$data_bankname,$data_branchname,$data_bankaddress,$data_bacnumber,$data_ifsccode,$data_pannum,$data_uidnum,$data_tannum,$data_stnm,$data_vatnum,$data_gstnum,$data_op_balance_dc,$data_opbal);
							}
			//				print_r($msg1);
				//			print_r ($data);
							$logdata['Sr'][$i]=$i.": ".$msg1;
		        				$i++;
							
	        	        		}
				//		print_r($_FILES["userfile"]["tmp_name"]);
			 			$this->messages->add(" File has been uploaded successfully. ", 'success');
						fclose($h);
					}
					else {
				 		$this->messages->add(" File does not readable. ", 'error');
					}
				}
				else {
					$this->messages->add(" File not having txt extension. ", 'error');
				}
			}
			else {
				$this->messages->add($file_name." File does not exist. ", 'error');
			}
		}
		$this->template->load('template', 'addparty/mulparty', $logdata);
                return ;
	}

	function add()
	{
		$this->load->library('form_validation');
		$this->template->set('page_title', 'Add Party');
           
               // $this->load->library('image_lib');
		
		/* Form fields */
		$data['pname'] = array(
			'name' => 'pname',
			'id' => 'pname',
			'maxlength' => '255',
			'size' => '40',
			'value' => '',
		);

/*		$data['sacunitid'] = array(
			'name' => 'sacunitid',
			'id' => 'sacunitid',
			'maxlength' => '10',
			'size' => '25',
			'value' => '',
		); */
		
		$data['partyrole'] = array(
                        "faculty" => "Faculty",
                        "student" => "Student",
                        "staff clerical"=> "Staff Clerical",
                        "technical staff" =>"Technical Staff",
                        "supplier" => "Supplier",
                        "admin staff" => "Admin Staff",
                        "contractor" => "Contractor",
                        "service provider" => "Service Provider",
                        "author" => "Author",
                        "illustrator" => "Illustrator",
                        "participant" => "Participant",
                        "invites/Speaker" => "Invites/Speaker",
                        "officers" => "Officers",
                        "others" => "Others",
                );

		$data['mnumber'] = array(
                        'name' => 'mnumber',
                        'id' => 'mnumber',
                        'maxlength' => '13',
                        'size' => '40',
                        'value' => '',
                );

		$data['accountemail'] = array(
                        'name' => 'accountemail',
                        'id' => 'accountemail',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => '',
                );

		$data['address'] = array(
			'name' => 'address',
			'id' => 'address',
			'rows' => '5',
			'cols' => '51',
			'value' => '',
		);
		$data['bacnumber'] = array(
                        'name' => 'bacnumber',
                        'id' => 'bacnumber',
                        'maxlength' => '20',
                        'size' => '40',
                        'value' => '',
                );
		$data['bankname'] = array(
                        'name' => 'bankname',
                        'id' => 'bankname',
                        'maxlength' => '250',
                        'size' => '40',
                        'value' => '',
                );
		$data['branchname'] = array(
                        'name' => 'branchname',
                        'id' => 'branchname',
                        'maxlength' => '200',
                        'size' => '40',
                        'value' => '',
                );
		$data['ifsccode'] = array(
                        'name' => 'ifsccode',
                        'id' => 'ifsccode',
                        'maxlength' => '25',
                        'size' => '40',
                        'value' => '',
                );
		$data['bankaddress'] = array(
			'name' => 'bankaddress',
			'id' => 'bankaddress',
			'rows' => '5',
			'cols' => '51',
			'value' => '',
		);
		$data['pannum'] = array(
                        'name' => 'pannum',
                        'id' => 'pannum',
                        'maxlength' => '10',
                        'size' => '40',
                        'value' => '',
                );


		$data['uidnum'] = array(
                        'name' => 'uidnum',
                        'id' => 'uidnum',
                        'maxlength' => '12',
                        'size' => '40',
                        'value' => '',
                );
		$data['tannum'] = array(
                        'name' => 'tannum',
                        'id' => 'tannum',
                        'maxlength' => '15',
                        'size' => '40',
                        'value' => '',
                );
		$data['stnum'] = array(
                        'name' => 'stnum',
                        'id' => 'stnum',
                        'maxlength' => '25',
                        'size' => '40',
                        'value' => '',
                );
		$data['vatnum'] = array(
                        'name' => 'vatnum',
                        'id' => 'vatnum',
                        'maxlength' => '25',
                        'size' => '40',
                        'value' => '',
                );
		$data['gstnum'] = array(
                        'name' => 'gstnum',
                        'id' => 'gstnum',
                        'maxlength' => '25',
                        'size' => '40',
                        'value' => '',
                );
		 $data['opbal'] = array(
                        'name' => 'opbal',
                        'id' => 'opbal',
                        'maxlength' => '15',
                        'size' => '35',
                        'value' => '',
                );

		$data['op_balance_dc'] = "D";
		/* Form validations */
		$this->form_validation->set_rules('pname', 'Party Name', 'trim|required|min_length[2]|max_length[255]');
		$this->form_validation->set_rules('partyrole', 'Party Category', 'trim|required');

		//$this->form_validation->set_rules('sacunitid', 'Secondary Accounting Unit', 'trim|required|max_length[10]');
		$this->form_validation->set_rules('mnumber', 'Mobile Number','trim|max_length[13]');
		$this->form_validation->set_rules('accountemail', 'Account Email', 'trim|valid_email');
		$this->form_validation->set_rules('address', 'Address', 'trim');
		$this->form_validation->set_rules('bacnumber', 'Bank Account Number', 'trim');
		$this->form_validation->set_rules('bankname', 'Bank Name','trim');
		$this->form_validation->set_rules('branchname', 'Branch Name','trim');
		$this->form_validation->set_rules('ifsccode', 'IFSC Code','trim');
		$this->form_validation->set_rules('bankaddress', 'Bank Address','trim');
		$this->form_validation->set_rules('pannum', 'PAN Number','trim|max_length[10]');
		$this->form_validation->set_rules('uidnum', 'UID Number','trim|max_length[12]');
		$this->form_validation->set_rules('tannum', 'TAN Number','trim');
		$this->form_validation->set_rules('stnum', 'Service Tax Number','trim');
		$this->form_validation->set_rules('vatnum', 'VAT Number','trim');
		$this->form_validation->set_rules('gstnum', 'GST Number','trim');
		$this->form_validation->set_rules('opbal', 'Opening Balance','trim');
		$this->form_validation->set_rules('op_balance_dc', 'Opening balance type', 'trim|required|is_dc');	
		/* Repopulating form */
		if ($_POST)
		{
			$data['pname']['value'] = $this->input->post('pname', TRUE);
			//$data['sacunitid']['value'] = $this->input->post('sacunitid', TRUE);
			$data['mnumber']['value'] = $this->input->post('mnumber', TRUE);
			$data['accountemail']['value'] = $this->input->post('accountemail', TRUE);
			$data['address']['value'] = $this->input->post('address', TRUE);
			$data['bacnumber']['value'] = $this->input->post('bacnumber', TRUE);
			$data['bankname']['value'] = $this->input->post('bankname', TRUE);
			$data['branchname']['value'] = $this->input->post('branchname', TRUE);
			$data['ifsccode']['value'] = $this->input->post('ifsccode', TRUE);
			$data['bankaddress']['value'] = $this->input->post('bankaddress', TRUE);
			$data['pannum']['value'] = $this->input->post('pannum', TRUE);
			$data['uidnum']['value'] = $this->input->post('uidnum', TRUE);
			$data['tannum']['value'] = $this->input->post('tannum', TRUE);
			$data['stnum']['value'] = $this->input->post('stnum', TRUE);
			$data['vatnum']['value'] = $this->input->post('vatnum', TRUE);
			$data['gstnum']['value'] = $this->input->post('gstnum', TRUE);
			$data['opbal']['value'] = $this->input->post('opbal', TRUE);
			$data['op_balance_dc'] = $this->input->post('op_balance_dc', TRUE);
		}
		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'addparty/add', $data);
			return;
		}
		else
		{
			$data_pname = $this->input->post('pname', TRUE);
                        $data_mnumber = $this->input->post('mnumber', TRUE);
                        $data_accountemail = $this->input->post('accountemail', TRUE);
                        $data_address = $this->input->post('address', TRUE);
                        $data_bacnumber = $this->input->post('bacnumber',TRUE);
                        $data_bankname = $this->input->post('bankname', TRUE);
                        $data_branchname = $this->input->post('branchname', TRUE);
                        $data_ifsccode = $this->input->post('ifsccode', TRUE);
                        $data_bankaddress = $this->input->post('bankaddress', TRUE);
                        $data_pannum = $this->input->post('pannum', TRUE);
			$data_uidnum = $this->input->post('uidnum', TRUE);
                        $data_tannum = $this->input->post('tannum', TRUE);
                        $data_stnm = $this->input->post('stnum', TRUE);
                        $data_vatnum = $this->input->post('vatnum', TRUE);
                        $data_gstnum = $this->input->post('gstnum', TRUE);
                        $data_opbal = $this->input->post('opbal', TRUE);
			$data_op_balance_dc = $this->input->post('op_balance_dc', TRUE);
			$data_partyrole = $this->input->post('partyrole', TRUE);

			$msg1= $this->addoneparty($data_pname,$data_partyrole,$data_address,$data_accountemail,$data_mnumber,$data_bankname,$data_branchname,$data_bankaddress,$data_bacnumber,$data_ifsccode,$data_pannum,$data_uidnum,$data_tannum,$data_stnm,$data_vatnum,$data_gstnum,$data_op_balance_dc,$data_opbal);

//add in new method
/*
                        if($data_partyrole == "faculty"){
                        	$secunit_id = "00";
                        }
                        if($data_partyrole == "student"){
                       		$secunit_id = "01";
                        }
                       	if($data_partyrole == "staff clerical"){
                       		$secunit_id = "02";
                        }
                        if($data_partyrole == "technical staff"){
                      		$secunit_id = "03";
                       	}
                   	if($data_partyrole == "supplier"){
                       		$secunit_id = "04";
                        }
                        if($data_partyrole == "admin staff"){
                      		$secunit_id = "05";
                       	}
                       	if($data_partyrole == "contractor"){
                        	$secunit_id = "06";
                        }
                      	if($data_partyrole == "service provider"){
                        	$secunit_id = "07";
                        }
                        if($data_partyrole == "alumni/doner"){
                        	$secunit_id = "08";
			} 
			if($data_partyrole == "author") {
                                $secunit_id = '09';
                        } 
			if($data_partyrole == 'illustrator') {
                                $secunit_id = '10';
                        } 
			if($data_partyrole == 'participant') {
                                $secunit_id = '11';
                        } 
			if($data_partyrole == 'invites/Speaker') {
                                $secunit_id = '12';
                        } 
			if($data_partyrole == 'officers') {
                                $secunit_id = '13';
                        }

                        $number = $this->upload_model->get_count_num($data_partyrole);
                        $secondary_id = $this->upload_model->get_random_secunitid($secunit_id,$number); 
 
			
			if($data_mnumber !=""){
			if(strlen($data_mnumber) < 10){
                                $this->messages->add('Mobile Number should be 10 or 13 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_bacnumber !=""){
			if((strlen($data_bacnumber) < 13)|| (strlen($data_bacnumber) > 20)){
                                $this->messages->add('Bank A/C number should be between 13 and 20 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			
			if($data_ifsccode !=""){
			if(strlen($data_ifsccode) < 11){
                                $this->messages->add('IFSC code should be 11 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_pannum !=""){
			if(strlen($data_pannum) < 10){
                                $this->messages->add('PAN should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_uidnum != ""){
			if(strlen($data_uidnum) < 12){
                                $this->messages->add('UID should be 12 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
                        }
			}
			if($data_tannum !=""){
			if(strlen($data_tannum) < 10){
                                $this->messages->add('TAN should be 10 to 15 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_stnm !=""){
			if(strlen($data_stnm) < 15){
                                $this->messages->add('Service tax number should be 15 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_vatnum !=""){
			if(strlen($data_vatnum) < 20){
                                $this->messages->add('VAT number should be 20 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_gstnum !=""){
			if(strlen($data_gstnum) < 20){
                                $this->messages->add('GST number should be 20 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			$this->db->from('addsecondparty');
	                $pdetail = $this->db->get();
			foreach ($pdetail->result() as $row)
	                {
				//$sacunit=$row->sacunit;
				$emailid=$row->email;
				$acnum=$row->bancacnum;
				$pan=$row->pan;
				$uid=$row->u_id;
				$tan=$row->tan;
				$stnumber=$row->staxnum;
				$vat=$row->vat;
				$gst=$row->gst;*/
		/*		if($sacunit == $data_sacunitid)
				{
                        		$this->messages->add(' Party with sunitid ' .$data_sacunitid. ' already exist. ', 'error');
                			$this->template->load('template', 'addparty/add', $data);
                        		return;
				}*/
		/*		if($data_accountemail !=""){
				if($emailid == $data_accountemail)
				{
                        		$this->messages->add(' Party with email ' .$data_accountemail. ' already exist. ', 'error');
                			$this->template->load('template', 'addparty/add', $data);
                        		return;
				}
				}
				if($data_bacnumber !="" ){
				if($acnum == $data_bacnumber)
				{
                        		$this->messages->add(' Party with A/C ' .$data_bacnumber. ' already exist. ', 'error');
                			$this->template->load('template', 'addparty/add', $data);
                        		return;
				}
				}
				if($data_pannum !=""){
				if($pan == $data_pannum)
				{
                        		$this->messages->add(' Party with Pan Number ' .$data_pannum. ' already exist. ', 'error');
                			$this->template->load('template', 'addparty/add', $data);
                        		return;
				}
				}
				if($data_uidnum !=""){
                                if($uid == $data_uidnum)
                                {
                                        $this->messages->add(' Party with UID Number ' .$data_uidnum. ' already exist. ', 'error');
                                        $this->template->load('template', 'addparty/add', $data);
                                        return;
                                }
				}
				if($data_tannum !=""){
				if($tan == $data_tannum)
				{
                        		$this->messages->add(' Party with Tan Number ' .$data_tannum. ' already exist. ', 'error');
                			$this->template->load('template', 'addparty/add', $data);
                        		return;
				}
				}
				if($data_stnm !=""){
				if($stnumber == $data_stnm)
				{
                        		$this->messages->add(' Party with Service Tax Number ' .$data_stnm. ' already exist. ', 'error');
                			$this->template->load('template', 'addparty/add', $data);
                        		return;
				}
				}
				if($data_vatnum !=""){
				if($vat == $data_vatnum)
				{
                        		$this->messages->add(' Party with Vat Number ' .$data_vatnum. ' already exist. ', 'error');
                			$this->template->load('template', 'addparty/add', $data);
                        		return;
				}
				}
				if($data_gstnum !=""){
				if($gst == $data_gstnum)
				{
                        		$this->messages->add(' Party with GST Number ' .$data_gstnum. ' already exist. ', 'error');
                			$this->template->load('template', 'addparty/add', $data);
                        		return;
				}
				}
                	}
*/
			if(strpos($msg1,'successfully') !== false){
 				//For Create Folder Automatically Under The BGAS Folder.  
	       			$this->upload_path= realpath(BASEPATH.'../');
        			if (!is_dir($this->upload_path . "/uploads/scopy")) {
                			$result = mkdir($this->upload_path . "/uploads/scopy",0777,true);
        			}//end
				$this->upload_path= realpath(BASEPATH.'../uploads/scopy');
			
				if (($_FILES['panfile']['name']!='')&&($data_pannum !='')){
					$this->upload_model->scancopy_fileupload('panfile',$data_pannum,$secondary_id);
				}
				if (($_FILES['uidfile']['name']!='')&&($data_uidnum !=''))
					$this->upload_model->scancopy_fileupload('uidfile',$data_uidnum,$secondary_id);
				if (($_FILES['bankfile']['name']!='')&&($data_bacnumber !=''))
					$this->upload_model->scancopy_fileupload('bankfile',$data_bacnumber,$secondary_id);

				$this->messages->add($msg1,'success');
				redirect('addparty/add');
                                return;
			}
			else{
				$this->messages->add($msg1,'error');
				$this->template->load('template', 'addparty/add', $data);
				return;
	

			}
/*
			$this->db->trans_start();
			$insert_data=array(
				'sacunit' =>$secondary_id,
				'partyname' =>$data_pname,
				'mobnum' =>$data_mnumber,
				'email' =>$data_accountemail, 
				'address' =>$data_address,
				'permanentaddress' =>$data_address, 
				'bancacnum' =>$data_bacnumber,
				'bankname' =>$data_bankname,
				'branchname' =>$data_branchname,
				'ifsccode' =>$data_ifsccode,
				'bankaddress' =>$data_bankaddress,
				'pan' =>$data_pannum,
				'u_id' =>$data_uidnum,
				'tan' =>$data_tannum,
				'staxnum' =>$data_stnm,
				'partyrole' =>$data_partyrole,
				'vat' =>$data_vatnum,
				'gst' =>$data_gstnum,
				'opbal' =>$data_opbal,
				'dc'=>$data_op_balance_dc,
//				'scopy_pan' =>$filename,
			);
			
			$sunitid = $insert_data['sacunit'];

			if ( ! $this->db->insert('addsecondparty', $insert_data))
                        {
                                $this->db->trans_rollback();
                                $this->messages->add('Error addding Secondary Unit.', 'error');
                                $this->logger->write_message("error", "Error adding second party");
                                $this->template->load('template', 'addparty/add', $data);
                                return;
                        }
			else {
                                        $this->db->trans_complete();
					$this->messages->add(' Party Name ' . $data_pname . ' added successfully. ');
                                        $this->logger->write_message(' Party ' . $data_pname . ' added successfully. ');		
                			redirect('addparty/add');
                			return;
                        }
*/
		}

                $this->template->load('template', 'addparty/add', $data);
                return;

	}


	function addoneparty($data_pname,$data_partyrole,$data_address,$data_accountemail,$data_mnumber,$data_bankname,$data_branchname,$data_bankaddress,$data_bacnumber,$data_ifsccode,$data_pannum,$data_uidnum,$data_tannum,$data_stnm,$data_vatnum,$data_gstnum,$data_op_balance_dc,$data_opbal){
	
			$secunit_id="";
			if($data_partyrole == "faculty"){
                                $secunit_id = "00";
                        }
                        if($data_partyrole == "student"){
                                $secunit_id = "01";
                        }
                        if($data_partyrole == "staff clerical"){
                                $secunit_id = "02";
                        }
                        if($data_partyrole == "technical staff"){
                                $secunit_id = "03";
                        }
                        if($data_partyrole == "supplier"){
                                $secunit_id = "04";
                        }
                        if($data_partyrole == "admin staff"){
                                $secunit_id = "05";
                        }
                        if($data_partyrole == "contractor"){
                                $secunit_id = "06";
                        }
                        if($data_partyrole == "service provider"){
                                $secunit_id = "07";
                        }
                        if($data_partyrole == "alumni/doner"){
                                $secunit_id = "08";
                        }
                        if($data_partyrole == "author") {
                                $secunit_id = '09';
                        }
                        if($data_partyrole == 'illustrator') {
                                $secunit_id = '10';
                        }
                        if($data_partyrole == 'participant') {
                                $secunit_id = '11';
                        }
                        if($data_partyrole == 'invites/Speaker') {
                                $secunit_id = '12';
                        }
                        if($data_partyrole == 'officers') {
                                $secunit_id = '13';
                        }
                        if($data_partyrole == 'others') {
                                $secunit_id = '14';
                        }
			if($secunit_id == ""){
				$msg1= 'The party role is invalid.';
				return $msg1;
			}
				

                        $number = $this->upload_model->get_count_num($data_partyrole);
                        $secondary_id = $this->upload_model->get_random_secunitid($secunit_id,$number);

			if($data_mnumber !=""){
                        if(strlen($data_mnumber) < 10){
                                $msg1= 'Mobile Number should be 10 or 13 digits.';
				return $msg1;
                        }
                        }
                        if($data_bacnumber !=""){
                        if((strlen($data_bacnumber) < 13)|| (strlen($data_bacnumber) > 20)){
                                $msg1= 'Bank A/C number should be between 13 and 20 digits.';
				return $msg1;
                        }
                        }

                        if($data_ifsccode !=""){
                        if(strlen($data_ifsccode) < 11){
                                $msg1= 'IFSC code should be 11 digits.';
				return $msg1;
                        }
                        }
                        if($data_pannum !=""){
                        if(strlen($data_pannum) < 10){
                                $msg1= 'PAN should be 10 digits.';
				return $msg1;
                        }
                        }
                        if($data_uidnum != ""){
                        if(strlen($data_uidnum) < 12){
                                $msg1= 'UID should be 12 digits.';
				return $msg1;
                        }
                        }
                        if($data_tannum !=""){
                        if(strlen($data_tannum) < 10){
                                $msg1= 'TAN should be 10 to 15 digits.';
				return $msg1;
                        }
                        }
			if($data_stnm !=""){
                        if(strlen($data_stnm) < 15){
                                $msg1= 'Service tax number should be 15 digits.';
				return $msg1;
                        }
                        }
                        if($data_vatnum !=""){
                        if(strlen($data_vatnum) < 20){
                                $msg1= 'VAT number should be 20 digits.';
				return $msg1;
                        }
                        }
                        if($data_gstnum !=""){
                        if(strlen($data_gstnum) < 20){
                                $msg1= 'GST number should be 20 digits.';
				return $msg1;
                        }
                        }
                        $this->db->from('addsecondparty');
                        $pdetail = $this->db->get();
                        foreach ($pdetail->result() as $row)
                        {
                                //$sacunit=$row->sacunit;
                                $emailid=$row->email;
                                $acnum=$row->bancacnum;
                                $pan=$row->pan;
                                $uid=$row->u_id;
                                $tan=$row->tan;
                                $stnumber=$row->staxnum;
                                $vat=$row->vat;
                                $gst=$row->gst;
				if($data_accountemail !=""){
                                if($emailid == $data_accountemail)
                                {
                                        $msg1= ' Party with email ' .$data_accountemail. ' already exist. ';
					return $msg1;
                                }
                                }
                                if($data_bacnumber !="" ){
                                if($acnum == $data_bacnumber)
                                {
                                        $msg1= ' Party with A/C ' .$data_bacnumber. ' already exist. ';
					return $msg1;
                                }
                                }
                                if($data_pannum !=""){
                                if($pan == $data_pannum)
                                {
                                        $msg1= ' Party with Pan Number ' .$data_pannum. ' already exist. ';
					return $msg1;
                                }
                                }
                                if($data_uidnum !=""){
                                if($uid == $data_uidnum)
                                {
                                        $msg1= ' Party with UID Number ' .$data_uidnum. ' already exist. ';
					return $msg1;
                                }
                                }
                                if($data_tannum !=""){
                                if($tan == $data_tannum)
                                {
                                        $msg1= ' Party with Tan Number ' .$data_tannum. ' already exist. ';
					return $msg1;
                                }
                                }
                                if($data_stnm !=""){
                                if($stnumber == $data_stnm)
                                {
                                        $msg1= ' Party with Service Tax Number ' .$data_stnm. ' already exist. ';
					return $msg1;
                                }
                                }
				if($data_vatnum !=""){
                                if($vat == $data_vatnum)
                                {
                                        $msg1= ' Party with Vat Number ' .$data_vatnum. ' already exist. ';
					return $msg1;
                                }
                                }
                                if($data_gstnum !=""){
                                if($gst == $data_gstnum)
                                {
                                       $msg1= ' Party with GST Number ' .$data_gstnum. ' already exist. ';
				       return $msg1;
                                }
                                }
                        }
			if($data_op_balance_dc == ""){
				$data_op_balance_dc='D';
			}
			
			if(empty($data_opbal)){
				$data_opbal=0.0;
			}
			$this->db->trans_start();
                        $insert_data=array(
                                'sacunit' =>$secondary_id,
                                'partyname' =>$data_pname,
                                'mobnum' =>$data_mnumber,
                                'email' =>$data_accountemail,
                                'address' =>$data_address,
                                'permanentaddress' =>$data_address,
                                'bancacnum' =>$data_bacnumber,
                                'bankname' =>$data_bankname,
                                'branchname' =>$data_branchname,
                                'ifsccode' =>$data_ifsccode,
                                'bankaddress' =>$data_bankaddress,
                                'pan' =>$data_pannum,
                                'u_id' =>$data_uidnum,
                                'tan' =>$data_tannum,
                                'staxnum' =>$data_stnm,
                                'partyrole' =>$data_partyrole,
                                'vat' =>$data_vatnum,
                                'gst' =>$data_gstnum,
                                'opbal' =>$data_opbal,
                                'dc'=>$data_op_balance_dc,
                        );

                        $sunitid = $insert_data['sacunit'];
//			print_r($insert_data);die;
                        if ( ! $this->db->insert('addsecondparty', $insert_data))
			{
				$msg11=	$this->db->error();

                                $this->db->trans_rollback();
				$this->logger->write_message("error", "Error adding second party");
//				print_r($this->db->error()); die;
                                $msg1= 'Error addding Secondary Unit.';
				return $msg1. "==".$msg11['message'];
                        }
                        else {
                                $this->db->trans_complete();
                                $this->logger->write_message("success"," Party " . $data_pname . " added successfully. ");
                                $msg1= ' Party Name ' . $data_pname . ' added successfully. ';
				return $msg1;
                        }
	}
	function edit($sunitid)
	{
		$this->template->set('page_title', 'Edit Party ');
		$this->db->from('addsecondparty')->where('sacunit', $sunitid);
                $detail = $this->db->get();
		$update_detail = $detail->row();

		/* Form fields */
                $data['pname'] = array(
                        'name' => 'pname',
                        'id' => 'pname',
                        'maxlength' => '255',
                        'size' => '40',
                        'value' => $update_detail->partyname,
                );

		$data['partyrole'] = array(
                        'value' => $update_detail->partyrole,

                );

		$data['sacunit'] = array(
			'name' => 'sacunit',
			'id' => 'sacunit',
			'maxlength' => '10',
			'size' => '40',
			'readonly'=>'true',
			'value' => $update_detail->sacunit,
		);

                $data['mnumber'] = array(
                        'name' => 'mnumber',
                        'id' => 'mnumber',
                        'maxlength' => '13',
                        'size' => '40',
                        'value' => $update_detail->mobnum,
                );
				
		$data['accountemail'] = array(
                        'name' => 'accountemail',
                        'id' => 'accountemail',
                        'maxlength' => '100',
                        'size' => '40',
                        'value' => $update_detail->email,
                );

                $data['address'] = array(
                        'name' => 'address',
                        'id' => 'address',
                        'rows' => '5',
                        'cols' => '51',
                        'value' => $update_detail->address,
                );
                $data['bacnumber'] = array(
                        'name' => 'bacnumber',
                        'id' => 'bacnumber',
                        'maxlength' => '20',
                        'size' => '40',
                        'value' => $update_detail->bancacnum ,
                );
                $data['bankname'] = array(
                        'name' => 'bankname',
                        'id' => 'bankname',
                        'maxlength' => '250',
                        'size' => '40',
                        'value' => $update_detail->bankname,
                );
		
                $data['branchname'] = array(
                        'name' => 'branchname',
                        'id' => 'branchname',
                        'maxlength' => '200',
                        'size' => '40',
                        'value' => $update_detail->branchname,
                );
                $data['ifsccode'] = array(
                        'name' => 'ifsccode',
                        'id' => 'ifsccode',
                        'maxlength' => '25',
                        'size' => '40',
                        'value' => $update_detail->ifsccode,
                );
                $data['bankaddress'] = array(
                        'name' => 'bankaddress',
                        'id' => 'bankaddress',
                        'rows' => '5',
                        'cols' => '51',
                        'value' => $update_detail->bankaddress,
                );
                $data['pannum'] = array(
                        'name' => 'pannum',
                        'id' => 'pannum',
                        'maxlength' => '10',
                        'size' => '40',
                        'value' => $update_detail->pan,
                );

		$data['uidnum'] = array(
                        'name' => 'uidnum',
                        'id' => 'uidnum',
                        'maxlength' => '12',
                        'size' => '40',
                        'value' => $update_detail->u_id,
                );

                $data['tannum'] = array(
                        'name' => 'tannum',
                        'id' => 'tannum',
                        'maxlength' => '15',
                        'size' => '40',
                        'value' => $update_detail->tan,
                );
                $data['stnum'] = array(
                        'name' => 'stnum',
                        'id' => 'stnum',
                        'maxlength' => '25',
                        'size' => '40',
                        'value' => $update_detail->staxnum,
                );
                $data['vatnum'] = array(
                        'name' => 'vatnum',
                        'id' => 'vatnum',
                        'maxlength' => '25',
                        'size' => '40',
                        'value' => $update_detail->vat,
                );
                $data['gstnum'] = array(
                        'name' => 'gstnum',
                        'id' => 'gstnum',
                        'maxlength' => '25',
                        'size' => '40',
                        'value' => $update_detail->gst,
                );
		 $data['opbal'] = array(
                        'name' => 'opbal',
                        'id' => 'opbal',
                        'maxlength' => '15',
                        'size' => '35',
                        'value' => $update_detail->opbal,
                );
		$data['sunitid'] = $sunitid;
		$data['op_balance_dc'] = $update_detail->dc;
		/* Form validations */
		$this->form_validation->set_rules('pname', 'Party Name', 'trim|required|min_length[2]|max_length[30]');
		$this->form_validation->set_rules('partyrole', 'Party Category', 'trim|required');
		$this->form_validation->set_rules('mnumber', 'Mobile Number');
		$this->form_validation->set_rules('accountemail', 'Account Email', 'trim|valid_email');
		$this->form_validation->set_rules('address', 'Address', 'trim');
		$this->form_validation->set_rules('bacnumber', 'Bank Account Number', 'trim');
		$this->form_validation->set_rules('bankname', 'Bank Name','trim');
		$this->form_validation->set_rules('branchname', 'Branch Name','trim');
		$this->form_validation->set_rules('ifsccode', 'IFSC Code','trim');
		$this->form_validation->set_rules('bankaddress', 'Bank Address','trim');
		$this->form_validation->set_rules('pannum', 'PAN Number','trim');
		$this->form_validation->set_rules('uidnum', 'UID Number','trim');
		$this->form_validation->set_rules('tannum', 'TAN Number','trim');
		$this->form_validation->set_rules('stnum', 'Service Tax Number','trim');
		$this->form_validation->set_rules('vatnum', 'VAT Number','trim');
		$this->form_validation->set_rules('gstnum', 'GST Number','trim');
		$this->form_validation->set_rules('opbal', 'Opening Balance','trim');
		$this->form_validation->set_rules('op_balance_dc', 'Opening balance type', 'trim|required|is_dc');
		/* Repopulating form */
		if ($_POST)
		{
			$data['pname']['value'] = $this->input->post('pname', TRUE);
			$data['mnumber']['value'] = $this->input->post('mnumber', TRUE);
			$data['accountemail']['value'] = $this->input->post('accountemail', TRUE);
			$data['address']['value'] = $this->input->post('address', TRUE);
			$data['bacnumber']['value'] = $this->input->post('bacnumber', TRUE);
			$data['bankname']['value'] = $this->input->post('bankname', TRUE);
			$data['branchname']['value'] = $this->input->post('branchname', TRUE);
			$data['ifsccode']['value'] = $this->input->post('ifsccode', TRUE);
			$data['bankaddress']['value'] = $this->input->post('bankaddress', TRUE);
			$data['pannum']['value'] = $this->input->post('pannum', TRUE);
			$data['uidnum']['value'] = $this->input->post('uidnum', TRUE);
			$data['tannum']['value'] = $this->input->post('tannum', TRUE);
			$data['stnum']['value'] = $this->input->post('stnum', TRUE);
			$data['vatnum']['value'] = $this->input->post('vatnum', TRUE);
			$data['gstnum']['value'] = $this->input->post('gstnum', TRUE);
			$data['opbal']['value'] = $this->input->post('opbal', TRUE);
			$data['op_balance_dc'] = $this->input->post('op_balance_dc', TRUE);
		}

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'addparty/edit', $data);
			return;
		}
		else
		{
			$data_pname = $this->input->post('pname', TRUE);
                        $data_mnumber = $this->input->post('mnumber', TRUE);
                        $data_accountemail = $this->input->post('accountemail', TRUE);
                        $data_address = $this->input->post('address', TRUE);
                        $data_bacnumber = $this->input->post('bacnumber',TRUE);
                        $data_bankname = $this->input->post('bankname', TRUE);
                        $data_branchname = $this->input->post('branchname', TRUE);
                        $data_ifsccode = $this->input->post('ifsccode', TRUE);
                        $data_bankaddress = $this->input->post('bankaddress', TRUE);
                        $data_pannum = $this->input->post('pannum', TRUE);
			$data_uidnum = $this->input->post('uidnum', TRUE);
                        $data_tannum = $this->input->post('tannum', TRUE);
                        $data_stnm = $this->input->post('stnum', TRUE);
                        $data_vatnum = $this->input->post('vatnum', TRUE);
                        $data_gstnum = $this->input->post('gstnum', TRUE);
                        $data_opbal = $this->input->post('opbal', TRUE);
			$data_op_balance_dc = $this->input->post('op_balance_dc', TRUE);
			$data_sunitid = $sunitid;
			if($data_mnumber !=""){
			if(strlen($data_mnumber) < 10){
                                $this->messages->add('Mobile Number should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			if($data_bacnumber !=""){
			if((strlen($data_bacnumber) < 13) || (strlen($data_bacnumber) > 20)){
                                $this->messages->add('Bank A/C number should be between 13 and 20 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
		
			if($data_ifsccode !=""){
			if(strlen($data_ifsccode) < 10){
                                $this->messages->add('IFSC code should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			if($data_pannum !=""){
			if(strlen($data_pannum) < 10){
                                $this->messages->add('PAN should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}

			if($data_uidnum != ""){
			if(strlen($data_uidnum) < 12){
                                $this->messages->add('UID should be 12 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
                        }
			}
			if($data_tannum !=""){
			if(strlen($data_tannum) < 10){
                                $this->messages->add('TAN should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			if($data_stnm !=""){
			if(strlen($data_stnm) < 15){
                                $this->messages->add('Service tax number should be 15 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			if($data_vatnum !=""){
			if(strlen($data_vatnum) < 20){
                                $this->messages->add('VAT number should be 20 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			if($data_gstnum !=""){
			if(strlen($data_gstnum) < 20){
                                $this->messages->add('GST number should be 20 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			$this->db->trans_start();
			$update_data=array(
				'partyname' =>$data_pname,
				'mobnum' =>$data_mnumber,
				'email' =>$data_accountemail, 
				'address' =>$data_address,
				'permanentaddress' =>$data_address, 
				'bancacnum' =>$data_bacnumber,
				'bankname' =>$data_bankname,
				'branchname' =>$data_branchname,
				'ifsccode' =>$data_ifsccode,
				'bankaddress' =>$data_bankaddress,
				'pan' =>$data_pannum,
				'u_id' =>$data_uidnum,
				'tan' =>$data_tannum,
				'staxnum' =>$data_stnm,
				'vat' =>$data_vatnum,
				'gst' =>$data_gstnum,
				'opbal' =>$data_opbal,
				'dc'=>$data_op_balance_dc
			);
			if ( ! $this->db->where('sacunit', $data_sunitid)->update('addsecondparty', $update_data))
			{
				$this->db->trans_rollback();
				$this->logger->write_message("error", "Error updating Secondary Unit ".$data_sunitid);
				$this->messages->add('Error updating Secondary Unit.', 'error');
				$this->template->load('template', 'addparty/edit', $data);
				return;
			}
			else{
                        	$this->db->trans_complete();
				 $this->logger->write_message("success"," Party " . $data_sunitid . " updated successfully. ");
                                $this->messages->add('Updated Secondary Unit Id ' . $data_sunitid . '.', 'success');
				redirect('addparty/show');
				return;
                        }
		}
                return;

	}

	 function set_group_id(){

                //$this->load->library('session');
		$this->session->unset_userdata('order_id_change');
                $this->session->unset_userdata('order_change');
                $this->session->set_userdata('order_change', '1');
        }

        function change_order(){

               // $this->load->library('session');
		$this->session->unset_userdata('order_id_change');
                $this->session->unset_userdata('order_change');
                $this->session->set_userdata('order_change', '0');
        }
	
	function change_id_order(){

               // $this->load->library('session');
                $this->session->set_userdata('order_id_change', '1');
        }
}

/* End of file create.php */
/* Location: ./system/application/controllers/admin/create.php */
