<?php

class Addparty extends Controller {

	function Addparty()
	{
		parent::Controller();
		$this->load->model('secunit_model');

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
		$this->load->library('session');
        	$asc_order = $this->session->userdata('order_change');
		$asc_id_order = $this->session->userdata('order_id_change');
		$this->template->set('nav_links', array('addparty/add' => 'Add Party'));
		$this->template->set('page_title', 'Add Party ');
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
                        "sacunit" => "Secondary Unit ID",
                        "partyname"=> "Party Name",
			"sacunit#1"=> "Party Type",
			"mobnum"=> "Mobile NO.",
			"email"=> "Email Id",
			"bancacnum"=> "Bank A/C No.",
			"bankname"=> "Bank Name",
			"branchname"=> "Branch Name",
			"ifsccode"=> "IFSC Code",
			"pan"=> "PAN No.",
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
			if($data_search_by == "partyname" || $data_search_by == "mobnum" || $data_search_by == "bancacnum" || $data_search_by == "ifsccode" || $data_search_by == "pan" || $data_search_by == "tan" || $data_search_by == "staxnum" || $data_search_by == "vat" || $data_search_by == "gst" )
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
			if ($text=='student') {
				$text='01';
			} elseif ($text == 'clerical staff') {
		  		$text='02';
			} elseif($text = 'Technical staff') {
				$text='03';
			} elseif($text = 'Supplier') {
        			$text='04';
			} elseif($text = 'Admin staff') {
        			$text='05';
			} elseif($text = 'Contractor') {
        			$text='06';
			} elseif($text = 'Service provider') {
        			$text='07';
			} elseif($text = 'Alumni/Doner') {
   				$text='08';
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
	function add()
	{
		$this->load->library('form_validation');
		$this->template->set('page_title', 'Add Party');

		/* Form fields */
		$data['pname'] = array(
			'name' => 'pname',
			'id' => 'pname',
			'maxlength' => '30',
			'size' => '25',
			'value' => '',
		);

		$data['sacunitid'] = array(
			'name' => 'sacunitid',
			'id' => 'sacunitid',
			'maxlength' => '10',
			'size' => '25',
			'value' => '',
		);

		$data['mnumber'] = array(
                        'name' => 'mnumber',
                        'id' => 'mnumber',
                        'maxlength' => '12',
                        'size' => '25',
                        'value' => '',
                );

		$data['accountemail'] = array(
                        'name' => 'accountemail',
                        'id' => 'accountemail',
                        'maxlength' => '100',
                        'size' => '25',
                        'value' => '',
                );

		$data['address'] = array(
			'name' => 'address',
			'id' => 'address',
			'rows' => '5',
			'cols' => '40',
			'value' => '',
		);
		$data['bacnumber'] = array(
                        'name' => 'bacnumber',
                        'id' => 'bacnumber',
                        'maxlength' => '17',
                        'size' => '25',
                        'value' => '',
                );
		$data['bankname'] = array(
                        'name' => 'bankname',
                        'id' => 'bankname',
                        'maxlength' => '200',
                        'size' => '25',
                        'value' => '',
                );
		$data['branchname'] = array(
                        'name' => 'branchname',
                        'id' => 'branchname',
                        'maxlength' => '200',
                        'size' => '25',
                        'value' => '',
                );
		$data['ifsccode'] = array(
                        'name' => 'ifsccode',
                        'id' => 'ifsccode',
                        'maxlength' => '200',
                        'size' => '25',
                        'value' => '',
                );
		$data['bankaddress'] = array(
			'name' => 'bankaddress',
			'id' => 'bankaddress',
			'rows' => '5',
			'cols' => '40',
			'value' => '',
		);
		$data['pannum'] = array(
                        'name' => 'pannum',
                        'id' => 'pannum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => '',
                );
		$data['tannum'] = array(
                        'name' => 'tannum',
                        'id' => 'tannum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => '',
                );
		$data['stnum'] = array(
                        'name' => 'stnum',
                        'id' => 'stnum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => '',
                );
		$data['vatnum'] = array(
                        'name' => 'vatnum',
                        'id' => 'vatnum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => '',
                );
		$data['gstnum'] = array(
                        'name' => 'gstnum',
                        'id' => 'gstnum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => '',
                );
		 $data['opbal'] = array(
                        'name' => 'opbal',
                        'id' => 'opbal',
                        'maxlength' => '15',
                        'size' => '25',
                        'value' => '',
                );
		$data['op_balance_dc'] = "D";
		/* Form validations */
		$this->form_validation->set_rules('pname', 'Party Name', 'trim|required|min_length[2]|max_length[30]');
		$this->form_validation->set_rules('sacunitid', 'Secondary Accounting Unit', 'trim|required|max_length[10]');
		$this->form_validation->set_rules('mnumber', 'Mobile Number');
		$this->form_validation->set_rules('accountemail', 'Account Email', 'trim|valid_email');
		$this->form_validation->set_rules('address', 'Address', 'trim');
		$this->form_validation->set_rules('bacnumber', 'Bank Account Number', 'trim');
		$this->form_validation->set_rules('bankname', 'Bank Name','trim');
		$this->form_validation->set_rules('branchname', 'Branch Name','trim');
		$this->form_validation->set_rules('ifsccode', 'IFSC Code','trim');
		$this->form_validation->set_rules('bankaddress', 'Bank Address','trim');
		$this->form_validation->set_rules('pannum', 'PAN Number','trim');
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
			$data['sacunitid']['value'] = $this->input->post('sacunitid', TRUE);
			$data['mnumber']['value'] = $this->input->post('mnumber', TRUE);
			$data['accountemail']['value'] = $this->input->post('accountemail', TRUE);
			$data['address']['value'] = $this->input->post('address', TRUE);
			$data['bacnumber']['value'] = $this->input->post('bacnumber', TRUE);
			$data['bankname']['value'] = $this->input->post('bankname', TRUE);
			$data['branchname']['value'] = $this->input->post('branchname', TRUE);
			$data['ifsccode']['value'] = $this->input->post('ifsccode', TRUE);
			$data['bankaddress']['value'] = $this->input->post('bankaddress', TRUE);
			$data['pannum']['value'] = $this->input->post('pannum', TRUE);
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
			$prole="";
			$data_sacunitid= $this->input->post('sacunitid', TRUE);
			$code = substr($data_sacunitid, 0, 2);
			if($code == "00"){
			$prole="faculty";	
			}
			if($code == "01"){
			$prole="student";	
			}
			if($code == "02"){
			$prole="clerical staff";	
			}
			if($code == "03"){
			$prole="technical staff";	
			}
			if($code == "04"){
			$prole="supplier";	
			}
			if($code == "05"){
			$prole="admin staff";	
			}
			if($code == "06"){
			$prole="contractor";	
			}
			if($code == "07"){
			$prole="service provider";	
			}
			if($code == "08"){
			$prole="alumni/doner";	
			}
			if(($code !="00")&&($code !="01")&&($code !="02")&&($code !="03")&&($code !="04")&&($code !="05")&&($code !="06")&&($code !="07")&&($code !="08"))
			{
                                $this->messages->add('Please insert the second unit id with given code.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
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
                        $data_tannum = $this->input->post('tannum', TRUE);
                        $data_stnm = $this->input->post('stnum', TRUE);
                        $data_vatnum = $this->input->post('vatnum', TRUE);
                        $data_gstnum = $this->input->post('gstnum', TRUE);
                        $data_opbal = $this->input->post('opbal', TRUE);
			$data_op_balance_dc = $this->input->post('op_balance_dc', TRUE);
			if(strlen($data_sacunitid) < 10){
                                $this->messages->add('second account unit should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			if($data_mnumber !=""){
			if(strlen($data_mnumber) < 10){
                                $this->messages->add('Mobile Number should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_bacnumber !=""){
			if(strlen($data_bacnumber) < 13){
                                $this->messages->add('Bank A/C number should be between 13 and 17 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_bacnumber !=""){
			if(strlen($data_bacnumber) > 17){
                                $this->messages->add('Bank A/C number should be between 13 and 17 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_ifsccode !=""){
			if(strlen($data_ifsccode) < 10){
                                $this->messages->add('IFSC code should be 10 digits.', 'error');
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
			if($data_tannum !=""){
			if(strlen($data_tannum) < 10){
                                $this->messages->add('TAN should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_stnm !=""){
			if(strlen($data_stnm) < 10){
                                $this->messages->add('Service tax number should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_vatnum !=""){
			if(strlen($data_vatnum) < 10){
                                $this->messages->add('VAT number should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			if($data_gstnum !=""){
			if(strlen($data_gstnum) < 10){
                                $this->messages->add('GST number should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/add', $data);
                                return;
			}
			}
			$this->db->from('addsecondparty');
	                $pdetail = $this->db->get();
			foreach ($pdetail->result() as $row)
	                {
				$sacunit=$row->sacunit;
				$emailid=$row->email;
				$acnum=$row->bancacnum;
				$pan=$row->pan;
				$tan=$row->tan;
				$stnumber=$row->staxnum;
				$vat=$row->vat;
				$gst=$row->gst;
				if($sacunit == $data_sacunitid)
				{
                        		$this->messages->add(' Party with sunitid ' .$data_sacunitid. ' already exist. ', 'error');
                			$this->template->load('template', 'addparty/add', $data);
                        		return;
				}
				if($data_accountemail !=""){
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
			$this->db->trans_start();
			$insert_data=array(
				'sacunit' =>$data_sacunitid,
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
				'tan' =>$data_tannum,
				'staxnum' =>$data_stnm,
				'partyrole' =>$prole,
				'vat' =>$data_vatnum,
				'gst' =>$data_gstnum,
				'opbal' =>$data_opbal,
				'dc'=>$data_op_balance_dc
			);

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
                			redirect('addparty/show');
                			return;
                        }

		}
                $this->template->load('template', 'addparty/add', $data);
                return;
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
                        'maxlength' => '30',
                        'size' => '25',
                        'value' => $update_detail->partyname,
                );

                $data['mnumber'] = array(
                        'name' => 'mnumber',
                        'id' => 'mnumber',
                        'maxlength' => '12',
                        'size' => '25',
                        'value' => $update_detail->mobnum,
                );
				
		$data['accountemail'] = array(
                        'name' => 'accountemail',
                        'id' => 'accountemail',
                        'maxlength' => '100',
                        'size' => '25',
                        'value' => $update_detail->email,
                );

                $data['address'] = array(
                        'name' => 'address',
                        'id' => 'address',
                        'rows' => '5',
                        'cols' => '40',
                        'value' => $update_detail->address,
                );
                $data['bacnumber'] = array(
                        'name' => 'bacnumber',
                        'id' => 'bacnumber',
                        'maxlength' => '17',
                        'size' => '25',
                        'value' => $update_detail->bancacnum ,
                );
                $data['bankname'] = array(
                        'name' => 'bankname',
                        'id' => 'bankname',
                        'maxlength' => '200',
                        'size' => '25',
                        'value' => $update_detail->bankname,
                );
		
                $data['branchname'] = array(
                        'name' => 'branchname',
                        'id' => 'branchname',
                        'maxlength' => '200',
                        'size' => '25',
                        'value' => $update_detail->branchname,
                );
                $data['ifsccode'] = array(
                        'name' => 'ifsccode',
                        'id' => 'ifsccode',
                        'maxlength' => '200',
                        'size' => '25',
                        'value' => $update_detail->ifsccode,
                );
                $data['bankaddress'] = array(
                        'name' => 'bankaddress',
                        'id' => 'bankaddress',
                        'rows' => '5',
                        'cols' => '40',
                        'value' => $update_detail->bankaddress,
                );
                $data['pannum'] = array(
                        'name' => 'pannum',
                        'id' => 'pannum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => $update_detail->pan,
                );
                $data['tannum'] = array(
                        'name' => 'tannum',
                        'id' => 'tannum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => $update_detail->tan,
                );
                $data['stnum'] = array(
                        'name' => 'stnum',
                        'id' => 'stnum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => $update_detail->staxnum,
                );
                $data['vatnum'] = array(
                        'name' => 'vatnum',
                        'id' => 'vatnum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => $update_detail->vat,
                );
                $data['gstnum'] = array(
                        'name' => 'gstnum',
                        'id' => 'gstnum',
                        'maxlength' => '10',
                        'size' => '25',
                        'value' => $update_detail->gst,
                );
		 $data['opbal'] = array(
                        'name' => 'opbal',
                        'id' => 'opbal',
                        'maxlength' => '15',
                        'size' => '25',
                        'value' => $update_detail->opbal,
                );
		$data['sunitid'] = $sunitid;
		$data['op_balance_dc'] = $update_detail->dc;
		/* Form validations */
		$this->form_validation->set_rules('pname', 'Party Name', 'trim|required|min_length[2]|max_length[30]');
		$this->form_validation->set_rules('mnumber', 'Mobile Number');
		$this->form_validation->set_rules('accountemail', 'Account Email', 'trim|valid_email');
		$this->form_validation->set_rules('address', 'Address', 'trim');
		$this->form_validation->set_rules('bacnumber', 'Bank Account Number', 'trim');
		$this->form_validation->set_rules('bankname', 'Bank Name','trim');
		$this->form_validation->set_rules('branchname', 'Branch Name','trim');
		$this->form_validation->set_rules('ifsccode', 'IFSC Code','trim');
		$this->form_validation->set_rules('bankaddress', 'Bank Address','trim');
		$this->form_validation->set_rules('pannum', 'PAN Number','trim');
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
			if(strlen($data_bacnumber) < 13){
                                $this->messages->add('Bank A/C number should be between 13 and 17 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			if($data_bacnumber !=""){
			if(strlen($data_bacnumber) > 17){
                                $this->messages->add('Bank A/C number should be between 13 and 17 digits.', 'error');
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
			if($data_tannum !=""){
			if(strlen($data_tannum) < 10){
                                $this->messages->add('TAN should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			if($data_stnm !=""){
			if(strlen($data_stnm) < 10){
                                $this->messages->add('Service tax number should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			if($data_vatnum !=""){
			if(strlen($data_vatnum) < 10){
                                $this->messages->add('VAT number should be 10 digits.', 'error');
                                $this->template->load('template', 'addparty/edit', $data);
                                return;
			}
			}
			if($data_gstnum !=""){
			if(strlen($data_gstnum) < 10){
                                $this->messages->add('GST number should be 10 digits.', 'error');
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
				$this->messages->add('Error updating Secondary Unit.', 'error');
				$this->template->load('template', 'addparty/edit', $data);
				return;
			}
			else{
                        	$this->db->trans_complete();
                                $this->messages->add('Updated Secondary Unit Id ' . $data_sunitid . '.', 'success');
				redirect('addparty/show');
				return;
                        }
		}
                return;

	}

	 function set_group_id(){

                $this->load->library('session');
		$this->session->unset_userdata('order_id_change');
                $this->session->unset_userdata('order_change');
                $this->session->set_userdata('order_change', '1');
        }

        function change_order(){

                $this->load->library('session');
		$this->session->unset_userdata('order_id_change');
                $this->session->unset_userdata('order_change');
                $this->session->set_userdata('order_change', '0');
        }
	
	function change_id_order(){

                $this->load->library('session');
                $this->session->set_userdata('order_id_change', '1');
        }


}

/* End of file create.php */
/* Location: ./system/application/controllers/admin/create.php */
