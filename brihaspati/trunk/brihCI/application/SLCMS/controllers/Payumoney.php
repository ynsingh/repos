<?php 
defined('BASEPATH') OR exit('No direct script access allowed');

class Payumoney extends CI_Controller {
public function __construct() {
        parent::__construct();
        $this->load->helper('url');       
    }
 public function index(){
    //$this->load->view('form1');
   		 
    $this->check();
 }
 public function check(){
     
     // all values are required
    $amount =  $this->input->post('payble_amount');  // here amount
    $product_info = $this->input->post('product_info');  //fees type
    $customer_name = $this->input->post('customer_name'); //name of student
    $customer_emial = $this->input->post('customer_email'); //email of student
    $customer_mobile = $this->input->post('mobile_number'); // mobile number of student
    $customer_address = $this->input->post('customer_address');// roll number and program code with branch
    
    //payumoney details
    
    // these details taken form constant file  under config
        $MERCHANT_KEY = "SYMBk2HQ"; //change  merchant with yours 
        $SALT = "dxmk9SZZ9y";  //change salt with yours 

        $txnid = substr(hash('sha256', mt_rand() . microtime()), 0, 20);
        //optional udf values 
        $udf1 = '';
        $udf2 = '';
        $udf3 = '';
        $udf4 = '';
        $udf5 = '';
        
         $hashstring = $MERCHANT_KEY . '|' . $txnid . '|' . $amount . '|' . $product_info . '|' . $customer_name . '|' . $customer_emial . '|' . $udf1 . '|' . $udf2 . '|' . $udf3 . '|' . $udf4 . '|' . $udf5 . '||||||' . $SALT;
         $hash = strtolower(hash('sha512', $hashstring));
         
        $success = base_url() . 'Status'; //return to payustatus function   
        $fail = base_url() . 'Status'; //return to payustatus function
        $cancel = base_url() . 'Status';//return to payustatus function
        
        
         $data = array(
            'mkey' => $MERCHANT_KEY,
            'tid' => $txnid,
            'hash' => $hash,
            'amount' => $amount,           
            'name' => $customer_name,
            'productinfo' => $product_info,
            'mailid' => $customer_emial,
            'phoneno' => $customer_mobile,
            'address' => $customer_address,
            'action' => "https://test.payu.in", //for live change action  https://secure.payu.in
            'sucess' => $success,
            'failure' => $fail,
            'cancel' => $cancel            
        );

        $this->load->view('payumoney/confirmation', $data);   
     }
     
     
   public function payustatus() {
       $status = $this->input->post('status');
      if (empty($status)) {
            redirect('Payumoney');
        }
       
        $firstname = $this->input->post('firstname');
        $amount = $this->input->post('amount');
        $txnid = $this->input->post('txnid');
        $posted_hash = $this->input->post('hash');
        $key = $this->input->post('key');
        $productinfo = $this->input->post('productinfo');
        $email = $this->input->post('email');
	$ftype = $this->input->post('address1');
	$cdate = date('Y-m-d');	
        $SALT = "eCwWELxi";	 //  Your salt
        $add = $this->input->post('additionalCharges');
        If (isset($add)) {
            $additionalCharges = $this->input->post('additionalCharges');
            $retHashSeq = $additionalCharges . '|' . $salt . '|' . $status . '|||||||||||' . $email . '|' . $firstname . '|' . $productinfo . '|' . $amount . '|' . $txnid . '|' . $key;
        } else {

            $retHashSeq = $salt . '|' . $status . '|||||||||||' . $email . '|' . $firstname . '|' . $productinfo . '|' . $amount . '|' . $txnid . '|' . $key;
        }
          $data['hash'] = hash("sha512", $retHashSeq);
          $data['amount'] = $amount;
          $data['txnid'] = $txnid;
          $data['posted_hash'] = $posted_hash;
          $data['status'] = $status;
          if($status == 'success'){
	 	// update pg table
		$pgdata = array(
			'aspg_asmid' 	=> $Sid,	
			'aspg_txnid' 	=> $txnid,	
			'aspg_pinfo' 	=> $productinfo,	
			'aspg_amount' 	=> $amount,	
			'aspg_ftype'	=> $ftype,	
			'aspg_date' 	=> $cdate,	
			//'aspg_gw'	=> ,	
			'aspg_status'	=> $status,	
			//'aspg_txncode' 	=> ,
			//'aspg_reason' 	=>	
		);
		$pgupdate=$this->commodel->updaterec('admissionstudent_pg', $pgdata,'ca_asmid',$Sid);
		$this->logger->write_logmessage("update", "Update in admissionstudent_pg table.");
                $this->logger->write_dblogmessage("update", "Update in admissionstudent_pg table." );
	 		//call payment function for update record
		 $pmathod = 'Online';
		 $pay = $this->payment($txnid,$bank,$amount,$ftype,$pmathod);
			//set the proper message
		$message = '<h3>Online fees submit successfully.</h3>';
                $this->session->set_flashdata('msg',$message);
                $this->load->view('enterence/step_five', $data);   //this should go to step five for printing application page  with suitable message
         }
         else{
		// update pg table
		$pgdata = array(
			'aspg_asmid' 	=> $Sid,	
			'aspg_txnid' 	=> $txnid,	
			'aspg_pinfo' 	=> $productinfo,	
			'aspg_amount' 	=> $amount,	
			'aspg_ftype'	=> $ftype,	
			'aspg_date' 	=> $cdate,	
			//'aspg_gw'	=> ,	
			'aspg_status'	=> $status,	
			//'aspg_txncode' 	=> ,
			//'aspg_reason' 	=>	
		);
		$pgupdate=$this->commodel->updaterec('admissionstudent_pg', $pgdata,'ca_asmid',$Sid);
		$this->logger->write_logmessage("update", "not update in admissionstudent_pg table.");
                $this->logger->write_dblogmessage("update", "not update in admissionstudent_pg table.");

		// set the status message
              $this->load->view('enterence/step_four', $data); //this should go to confirmation page for retry to make payment with suitable message
         }
     
    }
    
   }
