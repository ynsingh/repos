 <?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Picosetup
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Abhay Singh (abhay831877@gmail.com)
**/
class Tender extends CI_Controller
{
        function __construct() {
        parent::__construct();
        $this->load->model('login_model'); 
  		  $this->load->model('common_model'); 
        $this->load->model('PICO_model');   //changed to PICO insted of 
        $this->load->model('dependrop_model','depmodel');
        $this->load->model('university_model','unimodel');
        $this->db4=$this->load->database('pico', TRUE);
        if(empty($this->session->userdata('id_user'))) {
        $this->session->set_flashdata('flash_data', 'You don\'t have access!');
        
		redirect('welcome');
        }
    }
    
    
    
    
   public function tenderform()
    {
     
        $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
        
        $this->load->view('tender/step1');
    }	  
   public function step2($id)
    {
     		$data['tid']=$id;
         $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
         $this->load->view('tender/step2',$data);
    }	 
   public function step3($id)
    {
        $data['tid']=$id;
        $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
        $this->load->view('tender/step3',$data);
    }	
   public function step4($id)
    {
       $data['tid']=$id;
       $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
       $this->load->view('tender/step4',$data);
    }	
   public function step5($id)
    {
       $data['tid']=$id;
       $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
       $this->load->view('tender/step5',$data);
    }	
   public function step6($id)
    {
       $data['tid']=$id;
       $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
       $this->load->view('tender/step6',$data);
    }	
  
  public function isAuthorityExist($f) {
  //|callback_isAuthorityExist
        $is_exist = $this->PICO_model->isduplicate('tender_create','tc_refno',$f);
        if ($is_exist)
        {
            $this->form_validation->set_message('isAuthorityExist', 'This data already exist.');

            return false;
        }
        else {
            return true;
        }
    }
    
  public function tender_re($id)
    {
       $data['tid']=$id;
       $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
       $this->load->view('tender/reupload',$data);
    }	
  
  public function tenderbasic()   
   
   {
   				
                 if(isset($_POST['bd'])) {
                 	
               
                $this->form_validation->set_rules('bd_trn','Tender Reference No','trim|xss_clean|required|alpha_numeric');
                $this->form_validation->set_rules('bd_tt','Tender Type ','trim|xss_clean|required');
                $this->form_validation->set_rules('bd_foc','Form of Contract','trim|xss_clean|required');
                $this->form_validation->set_rules('bd_noc','No. of Covers','trim|xss_clean|required');
                $this->form_validation->set_rules('bd_tc','Tender Category','trim|xss_clean|required');
                $this->form_validation->set_rules('bd_ars','Allow Re-submission','trim|xss_clean|required');
                $this->form_validation->set_rules('bd_aw','Allow Withdrawal','trim|xss_clean|required');
                $this->form_validation->set_rules('bd_aos','Allow Offline Submission','trim|xss_clean|required');
                $this->form_validation->set_rules('bd_pm','Payment Mode','trim|xss_clean|required');
                $this->form_validation->set_rules('bd_covcon','Cover Content','trim|xss_clean|required|alpha_numeric_spaces');
                
                
                if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';

                 $tdata = array(
                'tc_refno'=>$_POST['bd_trn'],
                'tc_tentype'=>$_POST['bd_tt'],
                'tc_contractform'=>$_POST['bd_foc'],
                'tc_coverid'=>$_POST['bd_noc'],
                'tc_category'=>$_POST['bd_tc'],
                'tc_allowresub'=>$_POST['bd_ars'],
                'tc_allowwithdra'=>$_POST['bd_aw'],
                'tc_allowoffline'=>$_POST['bd_aos'],
                'tc_paymode'=>$_POST['bd_pm'],
                'tc_offlineinstrumentid'=>$_POST['bd_offline'],
                'tc_onlinebankid'=>$_POST['bd_online'],
                'tc_covercontent'=>$_POST['bd_covcon'],
                 );
                 
                $entryid=$this->PICO_model->insertdata('tender_create', $tdata);
                
                $data['tid']=$entryid;
              
                
                if(!$entryid){
                	
                	
						$rflag=false;                
                }else{
						$rflag=true;                
                }
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add Tender basic details", "Tender basic details is not added ".$bd_trn);
                    $this->logger->write_dblogmessage("insert","Trying to add Tender basic details", "Tender basic details is not added ".$bd_trn);
                    $this->session->set_flashdata('err_message','Error in adding Tender basic details setting - '  , 'error');
                    redirect('tender/tenderform');

                }
                else{
                    $this->logger->write_logmessage("insert","Add Tender basic details Setting", "Tender basic details".$_POST['bd_trn']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Tender basic details Setting", "Tender basic details".$_POST['bd_trn']."added  successfully...");
                    $this->session->set_flashdata("success", "Tender basic details add successfully...");
                  
                    $this->load->view('tender/step2',$data);
                   
                  
                     return;
                } } }
                
              
            $this->load->view('tender/step1');
}
              
              
              public function tenderwork()   
					{
				   $data['tid']=$_POST['tid'];	
						
               if(isset($_POST['wd'])) {
                 	
               
                 $this->form_validation->set_rules('wid_wit','Work item Details..','trim|xss_clean|required|alpha_dash');
                 $this->form_validation->set_rules('wid_wd',' Work Description..','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_pd','Prequal Details..','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_pc','Product category..','trim|xss_clean|required');
                 $this->form_validation->set_rules('wid_psc','Product Sub Category..','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_ct','Contract Type..','trim|xss_clean|required');
                 $this->form_validation->set_rules('wid_tv','Tender Value..','trim|xss_clean|required');
                 $this->form_validation->set_rules('wid_bvd','Bid valid days..','trim|xss_clean|required');
                 $this->form_validation->set_rules('wid_cpm','Completion Period (Months)','trim|xss_clean|required|numeric');
                 $this->form_validation->set_rules('wid_l',' Location..','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_p','Pincode..','trim|xss_clean|required|numeric|exact_length[6]');
                 $this->form_validation->set_rules('wid_pbm','Pre Bid Meeting..','trim|xss_clean|required');
                 $this->form_validation->set_rules('wid_pbmp','Pre Bid Meeting place..','trim|xss_clean');
                 $this->form_validation->set_rules('wid_pbma','Pre Bid Meeting address..','trim|xss_clean');
                 $this->form_validation->set_rules('wid_bop','Bid Open place','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_tc','Tenderer Class','trim|xss_clean|required');
                 $this->form_validation->set_rules('wid_tsc','Tenderer Sub-Class','trim|xss_clean|required');
                 $this->form_validation->set_rules('wid_io','Inviting Officer','trim|xss_clean|required|alpha');
                 $this->form_validation->set_rules('wid_ioa','Inviting Officer Address ','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_iop','Inviting Officer Phone','trim|xss_clean|required');
                 $this->form_validation->set_rules('wid_ioe','Inviting Officer Email','trim|xss_clean|required');
                  		
                if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                  $id=$_POST['tid'];
                  $data['tid']=$id;					 
					  //get tender id
					   $pbm=$_POST['wid_pbm'];
                  $pbmp=$_POST['wid_pbmp'];
                  $pbma=$_POST['wid_pbma'];  
					   $wid_wit=$_POST['wid_wit'];
					    
					    
                if($pbm=='yes') {
                	                if(empty($pbmp) && empty($pbma) ) 
                	               {
                	                
                	               
                	    
                	               $this->logger->write_logmessage("insert","Trying to add Work item details", "Work item details is not added ".$wid_wit);
                                 $this->logger->write_dblogmessage("insert","Trying to add Work item details", "Work item details is not added ".$wid_wit);
                                 $this->session->set_flashdata('err_message','Please Fill Pre-Bid Meeting Detail As You Selected \'Yes\' '  , 'error');
                                 $this->load->view('tender/step2',$data);
                                 return;
                	    
                	    
                	               }
                	               
                	             
                	               }   					  
					  
					  
					 
				     //echo $id; die();
                 $tdata= array(
                'tc_workitemtitle'=>$_POST['wid_wit'],
                'tc_workdesc'=>$_POST['wid_wd'],
                'tc_prequaldetails'=>$_POST['wid_pd'],
                'tc_prodcatid'=>$_POST['wid_pc'],
                'tc_prodsubcat'=>$_POST['wid_psc'],
                'tc_contracttype'=>$_POST['wid_ct'],
                'tc_tendervalue'=>$_POST['wid_tv'],
                'tc_bidvaliddays'=>$_POST['wid_bvd'],
                'tc_completionm'=>$_POST['wid_cpm'],
                'tc_location'=>$_POST['wid_l'],
                'tc_pincode'=>$_POST['wid_p'],
                  
                'tc_prebidmeeting'=>$pbm,
                'tc_prebidmeetplace'=>$pbmp,
                'tc_prebidmeetadd'=>$pbma,                    
                               
                //'tc_prebidmeeting'=>$_POST['wid_pbm'],
                //'tc_prebidmeetplace'=>$_POST['wid_pbmp'],
                //'tc_prebidmeetadd'=>$_POST['wid_pbma'],
                
                
                'tc_bidopenplace'=>$_POST['wid_bop'],
                'tc_tenderclass'=>$_POST['wid_tc'],
                'tc_tendersubclass'=>$_POST['wid_tsc'],
                'tc_invitngofficer'=>$_POST['wid_io'],
                'tc_invitngoffadd'=>$_POST['wid_ioa'],
                'tc_invitngoffphone'=>$_POST['wid_iop'],
                'tc_invitngoffemail'=>$_POST['wid_ioe'],
                
                 );
                $rflag=$this->PICO_model->updaterec('tender_create', $tdata,'tc_id',$id);
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add Work item details", "Work item details is not added ".$wid_wit);
                    $this->logger->write_dblogmessage("insert","Trying to add Work item details", "Work item details is not added ".$wid_wit);
                    $this->session->set_flashdata('err_message','Error in adding Work item details setting - '  , 'error');
                    $this->load->view('tender/step2',$data);
                     return;
                    //redirect('tender/tenderform2/'.$id);

                }
                else{
                    $this->logger->write_logmessage("insert","Add Work item details Setting", "Work item details".$_POST['wid_wit']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Work item details Setting", "Work item details".$_POST['wid_wit']."added  successfully...");
                    $this->session->set_flashdata("success", "Work item details add successfully...");
                    $this->load->view('tender/step3',$data);
                     return;
                   //redirect("tender/tenderform3/".$id);
                }

            }
        }
        $this->load->view('tender/step2',$data);


}

public function tenderfee()   
   
   {
               $data['tid']=$_POST['tid'];

                 if(isset($_POST['fd'])) {
                 	
               
                $this->form_validation->set_rules('fd_tf','Tender Fee','trim|xss_clean|required|numeric');
                $this->form_validation->set_rules('fd_tfpt','Payble at','trim|xss_clean|required');
                $this->form_validation->set_rules('fd_tfpa','Payble to','trim|xss_clean|required');
                $this->form_validation->set_rules('fd_pf','Processing fee','trim|xss_clean|required');
                $this->form_validation->set_rules('fd_s','Surcharge','trim|xss_clean|required');
                $this->form_validation->set_rules('fd_oc','Other charges','trim|xss_clean|required');
                $this->form_validation->set_rules('fd_ef','EMD fee','trim|xss_clean|required');
                $this->form_validation->set_rules('fd_a','EMD Amount','trim|xss_clean');
                $this->form_validation->set_rules('fd_p','EMD Percentage','trim|xss_clean');
                $this->form_validation->set_rules('fd_eea','EMD Exemption Allowed','trim|xss_clean|required');
                $this->form_validation->set_rules('fd_eep','EMD Exemption Percentage','trim|xss_clean');
                  	  
                if($this->form_validation->run()==TRUE){
                 $id=$_POST['tid'];
                 $data['tid']=$id;	
                	
                $m  =$_POST['fd_ef'];
                $a  =$_POST['fd_a'];
                $p  =$_POST['fd_p'];
                
                $e  =$_POST['fd_eea'];
                $per=$_POST['fd_eep'];
                 
                   
                if($m=='fixed') {
                	                if(empty($a) ) 
                	               {
                	                
                	               
                	    
                	               $this->logger->write_logmessage("insert","Trying to add fee details", "fee details is not added ");
                                 $this->logger->write_dblogmessage("insert","Trying to add fee details", "fee details is not added ");
                                 $this->session->set_flashdata('err_message','Please Fill EMD Amount'  , 'error');
                                 $this->load->view('tender/step3',$data);
                                 return;
                	    
                	    
                	               }
                	               
                	               }   		 
                if($m=='percentage') {
                	                if(empty($p) ) 
                	               {
                	                
                	               
                	    
                	               $this->logger->write_logmessage("insert","Trying to add fee details", "fee details is not added ");
                                 $this->logger->write_dblogmessage("insert","Trying to add fee details", "fee details is not added ");
                                 $this->session->set_flashdata('err_message','Please Fill EMD Percentage'  , 'error');
                                 $this->load->view('tender/step3',$data);
                                 return;
                	    
                	    
                	               }
                	               
                	               }  
                 if($e=='partial') {
                	                if(empty($per) ) 
                	               {
                	                
                	               
                	    
                	               $this->logger->write_logmessage("insert","Trying to add fee details", "fee details is not added ");
                                 $this->logger->write_dblogmessage("insert","Trying to add fee details", "fee details is not added ");
                                 $this->session->set_flashdata('err_message','Please Fill EMD Exemption Percentage'  , 'error');
                                 $this->load->view('tender/step3',$data);
                                 return;
                	    
                	    
                	               }
                	               
                	               } 
                 
                 
                 //echo 'form-validated';
                
                 $tdata=array(
                'tc_tenderfees'=>$_POST['fd_tf'],
                'tc_tenderfeespayble'=>$_POST['fd_tfpt'],
                'tc_tenderfeespaybleat'=>$_POST['fd_tfpa'],
                'tc_processingfees'=>$_POST['fd_pf'],
                'tc_surcharge'=>$_POST['fd_s'],
                'tc_othercharge'=>$_POST['fd_oc'],
                'tc_emdfeesmode'=>$m,
                'tc_emdamount'=>$a,
                'tc_emdpercentage'=>$p,
                
                //'tc_emdfeesmode'=>$_POST['fd_ef'],
                //'tc_emdamount'=>$_POST['fd_a'],
                //'tc_emdpercentage'=>$_POST['fd_p'],
                
                'tc_emdexemption'=>$e,
                'tc_emdexemptionper'=>$per,
                
                 // 'tc_emdexemption'=>$_POST['fd_eea'],
                //'tc_emdexemptionper'=>$_POST['fd_eep'],
                 );
                $rflag=$this->PICO_model->updaterec('tender_create',$tdata,'tc_id',$id);
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add fee details", "fee details is not added ".$fd_tf);
                    $this->logger->write_dblogmessage("insert","Trying to add fee details", "fee details is not added ".$fd_tf);
                    $this->session->set_flashdata('err_message','Error in adding fee details setting - '  , 'error');
                    $this->load->view('tender/step3',$data);
                     return;
                    //redirect('tender/tenderform3');
                    

                }
                else{
                    $this->logger->write_logmessage("insert","Add fee details Setting", "fee details".$_POST['fd_tf']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add fee details Setting", "fee details".$_POST['fd_tf']."added  successfully...");
                    $this->session->set_flashdata("success", "Fee details add successfully...");
                    $this->load->view('tender/step4',$data);
                     return;
                    //redirect("tender/tenderform4");
                }

            }
        }
        $this->load->view('tender/step3',$data);


}

public function tendercritical()   
   
   {

                 $data['tid']=$_POST['tid'];
                 
                 if(isset($_POST['cd'])) {
                 	
               
                $this->form_validation->set_rules('cd_pd','Publishing Date','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_dssd','Sale Start Date ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_dsed','Sale End Date ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_scsd','Seek Clarification Start Date','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_sced','Seek Clarification End Date ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_pbmd','Pre Bid Meeting Date','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_bssd','Bid Submission Start Date ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_bsed','Bid Submission end Date ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_bod','Bid Opening Date','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_pdt',' Publish Time','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_dssdt','Sale Start Time  ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_dsedt','Sale End Time ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_scsdt','Clarification Start Time ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_scedt','Clarification End Time','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_pbmdt','Pre bid Time ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_bssdt','Bid Submission Start Time  ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_bsedt','Bid Submission End Time ','trim|xss_clean|required');
                $this->form_validation->set_rules('cd_bodt','Bid Opening Time ','trim|xss_clean|required');
              
                  	  
                if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
 						$id=$_POST['tid'];
                 $data['tid']=$id;
                 $tdata = array(
                'tc_publishingdate'=>$_POST['cd_pd'],
                'tc_docsalestartdate'=>$_POST['cd_dssd'],
                'tc_docsaleenddate'=>$_POST['cd_dsed'],
                'tc_seekclailstartdate'=>$_POST['cd_scsd'],
                'tc_seekclailenddate'=>$_POST['cd_sced'],
                'tc_prebidmeetingdate'=>$_POST['cd_pbmd'],
                'tc_bidsubstartdate'=>$_POST['cd_bssd'],
                'tc_bidsubenddate'=>$_POST['cd_bsed'],
                'tc_bidopeningdate'=>$_POST['cd_bod'],
                
                'tc_publishingdatet'=>$_POST['cd_pdt'],
                'tc_docsalestartdatet'=>$_POST['cd_dssdt'],
                'tc_docsaleenddatet'=>$_POST['cd_dsedt'],
                'tc_seekclailstartdatet'=>$_POST['cd_scsdt'],
                'tc_seekclailenddatet'=>$_POST['cd_scedt'],
                'tc_prebidmeetingdatet'=>$_POST['cd_pbmdt'],
                'tc_bidsubstartdatet'=>$_POST['cd_bssdt'],
                'tc_bidsubenddatet'=>$_POST['cd_bsedt'],
                'tc_bidopeningdatet'=>$_POST['cd_bodt'],
               
                 );
                $rflag=$this->PICO_model->updaterec('tender_create', $tdata,'tc_id',$id);
                
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add Critical dates", "Critical dates is not added ".$cd_pd);
                    $this->logger->write_dblogmessage("insert","Trying to add Critical dates", "Critical dates is not added ".$cd_pd);
                    $this->session->set_flashdata('err_message','Error in adding Critical dates setting - '  , 'error');
                    $this->load->view('tender/step4',$data);
                     return;
                    //redirect('tender/tenderform4');

                }
                else{
                    $this->logger->write_logmessage("insert","Add Critical dates Setting", "Critical dates".$_POST['cd_pd']." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Critical dates Setting", "Critical dates".$_POST['cd_pd']."added  successfully...");
                    $this->session->set_flashdata("success", "Critical dates add successfully...");
                    $this->load->view('tender/step5',$data);
                     return;
                    //redirect("tender/tenderform4");
                }

            }
        }
        $this->load->view('tender/step4',$data);


}




public function tenderupload()        //($a,$tid,$refno)
            {
        
            $tid=$_POST['tid'];//row id  
            $data['tid']=$tid;
            //echo $tid;
            // die();
            //$tid=5;
            $n=$this->PICO_model->get_listspfic1('tender_create','tc_refno','tc_id',$tid)->tc_refno;
            //$n=$refno; 
  
                       $desired_dir = './uploads/PICO/tender/';
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                           mkdir("$desired_dir",0777);
                        }
                      $desired_dir1 = './uploads/PICO/tender/'.$n;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir1)==false){
                              mkdir("$desired_dir1",0777);
                        }
                      $desired_dir2 = './uploads/PICO/tender/'.$n.'/'.$tid.'/';
                        // Create directory if it does not exist
                        if(is_dir($desired_dir2)==false){
                              mkdir("$desired_dir2",0777);
                        }  

          
			               $target_dir = $desired_dir2;    // path computer/opt/lamppp/htdocs/brihCI/uploads/tender folder

			               $target_file = $target_dir . basename($_FILES["file_a"]["name"]);
			               
			               $uploadOk = 1;
			               $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));

		   
			if(isset($_POST["submit"])) 
			
		    if ($_FILES["file_a"]["size"] > 500000) //500kb
		    
		                                       { $this->session->set_flashdata("success", "Sorry, your file is too large.");
  	 	                                         $this->load->view('tender/step5',$data);
                                               $uploadOk = 0;
                                               return;
				                                 }
          if($imageFileType != "jpg" && $imageFileType != "pdf" ) // && $imageFileType != "jpeg" && $imageFileType != "gif" ) 
		                                       { $this->session->set_flashdata("success",  "Sorry, only JPG & pdf files are allowed.");
  				                                   $this->load->view('tender/step5',$data);
                                               $uploadOk = 0;
 				                                   return;
											            }

          if ($uploadOk == 0)
			                                    { $this->session->set_flashdata("success", "Sorry, your file was not uploaded.");
                                               $this->load->view('tender/step5',$data);
                                               return;
                                             } 
                                      else 
                                          {
                                          	$name = $target_dir.'nit.'.$imageFileType;
                                            if (move_uploaded_file($_FILES["file_a"]["tmp_name"], $name)) 
                                              {
                                              	
                                              	$file_name=basename( $_FILES["file_a"]["name"]);
                                              	$file_size=$_FILES["file_a"]["size"];
                                              	$d=array(   'tc_nitdocfilesize'=>$file_size,
                                              	            'tc_nitdocfilename'=>$file_name,
                                              	            'tc_nitdoctype'=>$imageFileType,
                                                         	);
                                              	$rflag=$this->PICO_model->updaterec('tender_create', $d,'tc_id',$tid);
                                              	
                    	                           $this->session->set_flashdata("success", "The file ". basename( $_FILES["file_a"]["name"]). " has been uploaded....");
                                                $this->load->view('tender/step6',$data);
                                                return;
                                              }
                                                else 
                                              { $this->session->set_flashdata("success", "Sorry, there was an error uploading your file.");
          			                              $this->load->view('tender/step5',$data);
           				                           return;
                                              }
                                           }
}
 
 

public function tenderbid() 

{
					$tid=$_POST['tid']; // row id     
               $data['tid']=$tid;
               if(isset($_POST['submit']))
               //echo $tid;
               //die();
               {
                 	
              
                $this->form_validation->set_rules('b1',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('n1','Name Field 1','trim|xss_clean|required');
                $this->form_validation->set_rules('d1','Designation Field 1 ','trim|xss_clean|required');
                $this->form_validation->set_rules('e1','Email Field 1 ','trim|xss_clean|required');
                $this->form_validation->set_rules('s1',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('f1','File Name 1','trim|xss_clean|required');
                $this->form_validation->set_rules('de1','Desp. of file 1 ','trim|xss_clean|required');
                $this->form_validation->set_rules('ty1','Type of file 1 ','trim|xss_clean|required');
                $this->form_validation->set_rules('si1','Size of file 1 ','trim|xss_clean|required');
                
                $this->form_validation->set_rules('b2',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('n2','Name Field 2','trim|xss_clean|required');
                $this->form_validation->set_rules('d2','Designation Field 2','trim|xss_clean|required');
                $this->form_validation->set_rules('e2','Email Field 2 ','trim|xss_clean|required');
                $this->form_validation->set_rules('s2',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('f2',' ','trim|xss_clean');
                $this->form_validation->set_rules('de2',' ','trim|xss_clean');
                $this->form_validation->set_rules('ty2',' ','trim|xss_clean');
                $this->form_validation->set_rules('si2',' ','trim|xss_clean');
                
                $this->form_validation->set_rules('b3',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('n3',' ','trim|xss_clean');
                $this->form_validation->set_rules('d3',' ','trim|xss_clean');
                $this->form_validation->set_rules('e3',' ','trim|xss_clean');
                $this->form_validation->set_rules('s3',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('f3',' ','trim|xss_clean');
                $this->form_validation->set_rules('de3',' ','trim|xss_clean');
                $this->form_validation->set_rules('ty3',' ','trim|xss_clean');
                $this->form_validation->set_rules('si3',' ','trim|xss_clean');
                
                $this->form_validation->set_rules('b4',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('n4',' ','trim|xss_clean');
                $this->form_validation->set_rules('d4',' ','trim|xss_clean');
                $this->form_validation->set_rules('e4',' ','trim|xss_clean');
                $this->form_validation->set_rules('s4',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('f4',' ','trim|xss_clean');
                $this->form_validation->set_rules('de4',' ','trim|xss_clean');
                $this->form_validation->set_rules('ty4',' ','trim|xss_clean');
                $this->form_validation->set_rules('si4',' ','trim|xss_clean');
                
                $this->form_validation->set_rules('b5',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('n5',' ','trim|xss_clean');
                $this->form_validation->set_rules('d5',' ','trim|xss_clean');
                $this->form_validation->set_rules('e5',' ','trim|xss_clean');
                $this->form_validation->set_rules('s5',' ','trim|xss_clean|required');
                $this->form_validation->set_rules('f5',' ','trim|xss_clean');
                $this->form_validation->set_rules('de5',' ','trim|xss_clean');
                $this->form_validation->set_rules('ty5',' ','trim|xss_clean');
                $this->form_validation->set_rules('si5',' ','trim|xss_clean');
                  
                $this->form_validation->set_rules('tp','Tender Prepared ','required');
                  
                $this->form_validation->set_rules('tpd','Tender Maker ','required');
                  
              
     //*/
               if($this->form_validation->run()==TRUE)
               {
                   
                	       
                	       
                	      for($i=1;$i<6;$i++)
                	      {
                	     // $n.$i=$_POST['n'.$i];	
                	     $n= $this->input->post('n'.$i, TRUE);
                       // $n.$i=$_POST[$n.$i];	               	       
                	       if(!empty($n)) 
                	       {
                              
                              
                        $datax= array(
                        
                         'tbos_tcid'=>$tid,
                     
                         'tbos_bono'=>$this->input->post('b'.$i, TRUE),
                         'tbos_boname'=>$n,
                         'tbos_bodesig'=>$this->input->post('d'.$i, TRUE),
                         'tbos_boemail'=>$this->input->post('e'.$i, TRUE),
                         'tbos_creatorid'=>$this->session->userdata('username'),
                         'tbos_creationdate'=>date('Y-m-d'),
                         //'tbos_modifierid'=>$this->session->userdata('username'),
                         //'tbos_modifierdate'=>date('Y-m-d'),
                                      );
                        $rflag=$this->PICO_model->insertrec('tender_bid_openers_selection', $datax);           	
                          
                        }                	
                	      else {
                	      	break;
								
								  }                	
                      	
                      	}
                      	
                    
                      	
                      	for($i=1;$i<6;$i++)
                	      {
                	       // $f.$i=$_POST['f'.$i];	
                	       $f=$this->input->post('f'.$i, TRUE);	
                	       if(!empty($f)) 
                	       {
                              
                              
                         $datay= array(
                         'tud_tcid'=>$tid,
                         'tud_filename'=>$f,
                         'tud_desc'=>$this->input->post('de'.$i, TRUE),
                         'tud_filetype'=>$this->input->post('ty'.$i, TRUE),
                         'tud_filesize'=>$this->input->post('si'.$i, TRUE),
                         'tud_creatorid'=>$this->session->userdata('username'),
                         'tud_creationdate'=>date('Y-m-d'),
                         //'tud_modifierid'=>$this->session->userdata('username'),
                         //'tud_modifierdate'=>date('Y-m-d'),
                          );
                          $rflag=$this->PICO_model->insertrec('tender_upload_doc',$datay);           	
                          
                          }                	
                	        else {
                	        break;
								
								  }                	
                      	
                      	  }
                      	
                      
                      	  $dataz = array(
                          'tc_tenderprepby'=>$_POST['tp'],
                          'tc_prepbydesig'=>$_POST['tpd'],
                          //'tc_approvedbyname'=>$_POST['ta'],
                         // 'tc_approvedbydesig'=>$_POST['tad'],
                          'tc_creatorid'=>$this->session->userdata('username'),
                          'tc_creationdate'=>date('Y-m-d'),
                          //'tc_modifierid'=>$this->session->userdata('username'),
                          //'tc_modifierdate'=>date('Y-m-d'),
                                                                                      
                                      );
                	    
                           	
                           $rflag=$this->PICO_model->updaterec('tender_create', $dataz,'tc_id',$tid);
                               	      
                	      
                	     
                       




                $this->load->view('tender/tenderdisplay');   //success
                return;
                }
                
                $this->load->view('tender/step6',$data); //validation fail case
                return;
}


$this->load->view('tender/step6',$data); //clear button
return;

}





public function tenderdisplay() 
       {
	   	
	     $selectfield='tc_id,tc_refno,tc_tentype,tc_contractform,tc_category,tc_workitemtitle,tc_workdesc,tc_prodcatid,tc_prodsubcat,tc_tenderfees,tc_processingfees,tc_surcharge
	     ,tc_emdfeesmode,tc_emdamount,tc_emdpercentage,tc_emdexemption,tc_publishingdate,tc_docsalestartdate ,tc_seekclailstartdate,tc_bidsubstartdate,tc_approvedstatus,tc_approvedbyname
	     ,tc_bidsubenddate,tc_bidopeningdate,tc_nitdocfilename,tc_nitdocfilesize,tc_nitdoctype ,tbos_boname ,tbos_bodesig,tbos_boemail  ';
	     $joincond='tender_bid_openers_selection.tbos_tcid=tender_create.tc_id';
        $data['result'] = $this->PICO_model->get_jointbrecord('tender_create',$selectfield,'tender_bid_openers_selection',$joincond,'LEFT',''); //,$whdata   get_list('tender_create');
        $this->logger->write_logmessage("view"," View tenders", "tender  details...");
        $this->logger->write_dblogmessage("view"," View tenders", "tender  details...");
        $this->load->view('tender/tenderdisplay',$data);
       }

public function tenderview($i)
{	
			$id=$i;
			$whdata = array('tc_id'=>$id);
			$whdata1 = array('tud_tcid'=>$id);
			$whdata2 = array('tbos_tcid'=>$id);
         $data['tcresult']=$this->PICO_model->get_orderlistspficemore('tender_create','*',$whdata,'');  //$tbname ,$selectfield ,get_list('tender_create'); 
			$data['tudresult']=$this->PICO_model->get_orderlistspficemore('tender_upload_doc','*',$whdata1,''); 
			$data['tbosresult']=$this->PICO_model->get_orderlistspficemore('tender_bid_openers_selection','*',$whdata2,''); 
        $this->logger->write_logmessage("view"," View specific  tender", "tender  detail...");
        $this->logger->write_dblogmessage("view"," View specific tender", "tender  detail...");
     
        $this->load->view('tender/tenderview',$data);



}

public function tenderedit($id)

{
     
     $suname=$this->session->userdata['username'];
	  if((strcasecmp($suname,"admin"))==0)  
        {    
            
      //  $this->db4->from('tender_create')->where('tc_id', $id);
     //   $tender_data = $this->db4->get();
       
        $whdata1 = array('tud_tcid'=>$id);
		  $whdata2 = array('tbos_tcid'=>$id);
		  
		  $data['upldtender']=$this->PICO_model->get_orderlistspficemore('tender_upload_doc','*',$whdata1,'');
     	  $data['bidoptender']=$this->PICO_model->get_orderlistspficemore('tender_bid_openers_selection','*',$whdata2,'');
     	  
     	  $whdata = array('tc_id'=> $id);
        $tender_data=$this->PICO_model->get_orderlistspficemore('tender_create','*',$whdata,'');
      //   print_r($tender_data);
      //  $tender = $tender_data->row();
         $tender = $tender_data[0];
	//	print_r($tender);
		$data['tc_refno'] = array('name' => 'tc_refno','id' => 'tc_refno','size' => '40','value' => $tender->tc_refno,);
		$data['tc_tentype'] = array('name' => 'tc_tentype','id' => 'tc_tentype','size' => '40','value' => $tender->tc_tentype,);
		$data['tc_contractform'] = array('name' => 'tc_contractform','id' => 'tc_contractform','size' => '40','value' => $tender->tc_contractform,);
		$data['tc_coverid'] = array('name' => 'tc_coverid','id' => 'tc_coverid','size' => '40','value' => $tender->tc_coverid,);
		$data['tc_category'] = array('name' => 'tc_category','id' => 'tc_category','size' => '40','value' => $tender->tc_category,);
		$data['tc_allowresub'] = array('name' => 'tc_allowresub','id' => 'tc_allowresub','size' => '40','value' => $tender->tc_allowresub,);
		$data['tc_allowwithdra'] = array('name' => 'tc_allowwithdra','id' => 'tc_allowwithdra','size' => '40','value' => $tender->tc_allowwithdra,);
		$data['tc_allowoffline'] = array('name' => 'tc_allowoffline','id' => 'tc_allowoffline','size' => '40','value' => $tender->tc_allowoffline,);
		$data['tc_paymode'] = array('name' => 'tc_paymode','id' => 'tc_paymode','size' => '40','value' => $tender->tc_paymode,);
      $data['tc_covercontent'] = array('name' => 'tc_covercontent','id' => 'tc_covercontent','size' => '40','value' => $tender->tc_covercontent,);
		$data['tc_offlineinstrumentid'] = array('name' => 'tc_offlineinstrumentid','id' => 'tc_offlineinstrumentid','size' => '40','value' => $tender->tc_offlineinstrumentid,);
		$data['tc_onlinebankid'] = array('name' => 'tc_onlinebankid','id' => 'tc_onlinebankid','size' => '40','value' => $tender->tc_onlinebankid,);
		
		$data['tc_nitdocfilename'] = array('name' => 'tc_nitdocfilename','id' => 'tc_nitdocfilename','size' => '40','value' => $tender->tc_nitdocfilename,);
		$data['tc_nitdocfilesize'] = array('name' => 'tc_nitdocfilesize','id' => 'tc_nitdocfilesize','size' => '40','value' => $tender->tc_nitdocfilesize,);
		$data['tc_nitdoctype'] = array('name' => 'tc_nitdoctype','id' => 'tc_nitdoctype','size' => '40','value' => $tender->tc_nitdoctype,);
		
		$data['tc_workitemtitle'] = array('name' => 'tc_workitemtitle','id' => 'tc_workitemtitle','size' => '40','value' => $tender->tc_workitemtitle,);
		$data['tc_workdesc'] = array('name' => 'tc_workdesc','id' => 'tc_workdesc','size' => '40','value' => $tender->tc_workdesc,);
		$data['tc_prequaldetails'] = array('name' => 'tc_prequaldetails','id' => 'tc_prequaldetails','size' => '40','value' => $tender->tc_prequaldetails,);
		$data['tc_prodcatid'] = array('name' => 'tc_prodcatid','id' => 'tc_prodcatid','size' => '40','value' => $tender->tc_prodcatid,);
		$data['tc_prodsubcat'] = array('name' => 'tc_prodsubcat','id' => 'tc_prodsubcat','size' => '40','value' => $tender->tc_prodsubcat,);
		$data['tc_contracttype'] = array('name' => 'tc_contracttype','id' => 'tc_contracttype','size' => '40','value' => $tender->tc_contracttype,);
		$data['tc_tendervalue'] = array('name' => 'tc_tendervalue','id' => 'tc_tendervalue','size' => '40','value' => $tender->tc_tendervalue,);
		$data['tc_bidvaliddays'] = array('name' => 'tc_bidvaliddays','id' => 'tc_bidvaliddays','size' => '40','value' => $tender->tc_bidvaliddays,);
		$data['tc_completionm'] = array('name' => 'tc_completionm','id' => 'tc_completionm','size' => '40','value' => $tender->tc_completionm,);
		$data['tc_location'] = array('name' => 'tc_location','id' => 'tc_location','size' => '40','value' => $tender->tc_location,);
		$data['tc_pincode'] = array('name' => 'tc_pincode','id' => 'tc_pincode','size' => '40','value' => $tender->tc_pincode,);
		$data['tc_prebidmeeting'] = array('name' => 'tc_prebidmeeting','id' => 'tc_prebidmeeting','size' => '40','value' => $tender->tc_prebidmeeting,);
		$data['tc_prebidmeetplace'] = array('name' => 'tc_prebidmeetplace','id' => 'tc_prebidmeetplace','size' => '40','value' => $tender->tc_prebidmeetplace,);
		$data['tc_prebidmeetadd'] = array('name' => 'tc_prebidmeetadd','id' => 'tc_prebidmeetadd','size' => '40','value' => $tender->tc_prebidmeetadd,);
		$data['tc_bidopenplace'] = array('name' => 'tc_bidopenplace','id' => 'tc_bidopenplace','size' => '40','value' => $tender->tc_bidopenplace,);  
		$data['tc_invitngofficer'] = array('name' => 'tc_invitngofficer','id' => 'tc_invitngofficer','size' => '40','value' => $tender->tc_invitngofficer,);
		$data['tc_invitngoffadd'] = array('name' => 'tc_invitngoffadd','id' => 'tc_invitngoffadd','size' => '40','value' => $tender->tc_invitngoffadd,);
		$data['tc_invitngoffphone'] = array('name' => 'tc_invitngoffphone','id' => 'tc_invitngoffphone','size' => '40','value' => $tender->tc_invitngoffphone,);
		$data['tc_invitngoffemail'] = array('name' => 'tc_invitngoffemail','id' => 'tc_invitngoffemail','size' => '40','value' => $tender->tc_invitngoffemail,);
		$data['tc_tenderclass'] = array('name' => 'tc_tenderclass','id' => 'tc_tenderclass','size' => '40','value' => $tender->tc_tenderclass,);
		$data['tc_tendersubclass'] = array('name' => 'tc_tendersubclass','id' => 'tc_tendersubclass','size' => '40','value' => $tender->tc_tendersubclass,);
		
		$data['tc_tenderfees'] = array('name' => 'tc_tenderfees','id' => 'tc_tenderfees','size' => '40','value' => $tender->tc_tenderfees,);
		$data['tc_tenderfeespayble'] = array('name' => 'tc_tenderfeespayble','id' => 'tc_tenderfeespayble','size' => '40','value' => $tender->tc_tenderfeespayble,);
		$data['tc_tenderfeespaybleat'] = array('name' => 'tc_tenderfeespaybleat','id' => 'tc_tenderfeespaybleat','size' => '40','value' => $tender->tc_tenderfeespaybleat,);
		$data['tc_processingfees'] = array('name' => 'tc_processingfees','id' => 'tc_processingfees','size' => '40','value' => $tender->tc_processingfees,);
		$data['tc_surcharge'] = array('name' => 'tc_surcharge','id' => 'tc_surcharge','size' => '40','value' => $tender->tc_surcharge,);
		$data['tc_othercharge'] = array('name' => 'tc_othercharge','id' => 'tc_othercharge','size' => '40','value' => $tender->tc_othercharge,);
		$data['tc_emdfeesmode'] = array('name' => 'tc_emdfeesmode','id' => 'tc_emdfeesmode','size' => '40','value' => $tender->tc_emdfeesmode,);
		$data['tc_emdamount'] = array('name' => 'tc_emdamount','id' => 'tc_emdamount','size' => '40','value' => $tender->tc_emdamount,);
		$data['tc_emdpercentage'] = array('name' => 'tc_emdpercentage','id' => 'tc_emdpercentage','size' => '40','value' => $tender->tc_emdpercentage,);
		$data['tc_emdexemption'] = array('name' => 'tc_emdexemption','id' => 'tc_emdexemption','size' => '40','value' => $tender->tc_emdexemption,);
		$data['tc_emdexemptionper'] = array('name' => 'tc_emdexemptionper','id' => 'tc_emdexemptionper','size' => '40','value' => $tender->tc_emdexemptionper,);
		
		$data['tc_publishingdate'] = array('name' => 'tc_publishingdate','id' => 'tc_publishingdate','size' => '40','value' => $tender->tc_publishingdate,);
		$data['tc_publishingdatet'] = array('name' => 'tc_publishingdatet','id' => 'tc_publishingdatet','size' => '40','value' => $tender->tc_publishingdatet,);
		$data['tc_prebidmeetingdate'] = array('name' => 'tc_prebidmeetingdate','id' => 'tc_prebidmeetingdate','size' => '40','value' => $tender->tc_prebidmeetingdate,);
		$data['tc_prebidmeetingdatet'] = array('name' => 'tc_prebidmeetingdatet','id' => 'tc_prebidmeetingdatet','size' => '40','value' => $tender->tc_prebidmeetingdatet,);
		$data['tc_docsalestartdate'] = array('name' => 'tc_docsalestartdate','id' => 'tc_docsalestartdate','size' => '40','value' => $tender->tc_docsalestartdate,);
		$data['tc_docsalestartdatet'] = array('name' => 'tc_docsalestartdatet','id' => 'tc_docsalestartdatet','size' => '40','value' => $tender->tc_docsalestartdatet,);
		$data['tc_docsaleenddate'] = array('name' => 'tc_docsaleenddate','id' => 'tc_docsaleenddate','size' => '40','value' => $tender->tc_docsaleenddate,);
		$data['tc_docsaleenddatet'] = array('name' => 'tc_docsaleenddatet','id' => 'tc_docsaleenddatet','size' => '40','value' => $tender->tc_docsaleenddatet,);
		$data['tc_seekclailstartdate'] = array('name' => 'tc_seekclailstartdate','id' => 'tc_seekclailstartdate','size' => '40','value' => $tender->tc_seekclailstartdate,);
		$data['tc_seekclailstartdatet'] = array('name' => 'tc_seekclailstartdatet','id' => 'tc_seekclailstartdatet','size' => '40','value' => $tender->tc_seekclailstartdatet,);
		$data['tc_seekclailenddate'] = array('name' => 'tc_seekclailenddate','id' => 'tc_seekclailenddate','size' => '40','value' => $tender->tc_seekclailenddate,);  
		$data['tc_seekclailenddatet'] = array('name' => 'tc_seekclailenddatet','id' => 'tc_seekclailenddatet','size' => '40','value' => $tender->tc_seekclailenddatet,);
		$data['tc_bidsubstartdate'] = array('name' => 'tc_bidsubstartdate','id' => 'tc_bidsubstartdate','size' => '40','value' => $tender->tc_bidsubstartdate,);
		$data['tc_bidsubstartdatet'] = array('name' => 'tc_bidsubstartdatet','id' => 'tc_bidsubstartdatet','size' => '40','value' => $tender->tc_bidsubstartdatet,);
		$data['tc_bidsubenddate'] = array('name' => 'tc_bidsubenddate','id' => 'tc_bidsubenddate','size' => '40','value' => $tender->tc_bidsubenddate,);
		$data['tc_bidsubenddatet'] = array('name' => 'tc_bidsubenddatet','id' => 'tc_bidsubenddatet','size' => '40','value' => $tender->tc_bidsubenddatet,);
		$data['tc_bidopeningdate'] = array('name' => 'tc_bidopeningdate','id' => 'tc_bidopeningdate','size' => '40','value' => $tender->tc_bidopeningdate,);
		$data['tc_bidopeningdatet'] = array('name' => 'tc_bidopeningdatet','id' => 'tc_bidopeningdatet','size' => '40','value' => $tender->tc_bidopeningdatet,);
		
		
    $data['id']=$id;
       //form validation
 	          
	 $this->form_validation->set_rules('tc_refno','Tender Reference No','trim|xss_clean|required|alpha_numeric');
	 $this->form_validation->set_rules('tc_tentype','Tender Type ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_contractform','Form of Contract','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_coverid','No. of Covers','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_category','Tender Category','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_allowresub','Allow Re-submission','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_allowwithdra','Allow Withdrawal','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_allowoffline','Allow Offline Submission','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_paymode','Payment Mode','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_covercontent','Cover Content','trim|xss_clean|required|alpha_numeric_spaces');
	 
	  $this->form_validation->set_rules('tc_workitemtitle','Work item Details..','trim|xss_clean|required|alpha_dash');
	  $this->form_validation->set_rules('tc_workdesc',' Work Description..','trim|xss_clean|required|alpha_numeric_spaces');
	  $this->form_validation->set_rules('tc_prequaldetails','Prequal Details..','trim|xss_clean|required|alpha_numeric_spaces');
	  $this->form_validation->set_rules('tc_prodcatid','Product category..','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_prodsubcat','Product Sub Category..','trim|xss_clean|required|alpha_numeric_spaces');
	  $this->form_validation->set_rules('tc_contracttype','Contract Type..','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_tendervalue','Tender Value..','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_bidvaliddays','Bid valid days..','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_completionm','Completion Period (Months)','trim|xss_clean|required|numeric');
	  $this->form_validation->set_rules('tc_location',' Location..','trim|xss_clean|required|alpha_numeric_spaces');
	  $this->form_validation->set_rules('tc_pincode','Pincode..','trim|xss_clean|required|numeric|exact_length[6]');
	  $this->form_validation->set_rules('tc_prebidmeeting','Pre Bid Meeting..','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_prebidmeetplace','Pre Bid Meeting place..','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_prebidmeetadd','Pre Bid Meeting address..','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_bidopenplace','Bid Open place','trim|xss_clean|required|alpha_numeric_spaces');
	  $this->form_validation->set_rules('tc_tenderclass','Tenderer Class','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_tendersubclass','Tenderer Sub-Class','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_invitngofficer','Inviting Officer','trim|xss_clean|required|alpha');
	  $this->form_validation->set_rules('tc_invitngoffadd','Inviting Officer Address ','trim|xss_clean|required|alpha_numeric_spaces');
	  $this->form_validation->set_rules('tc_invitngoffphone','Inviting Officer Phone','trim|xss_clean|required');
	  $this->form_validation->set_rules('tc_invitngoffemail','Inviting Officer Email','trim|xss_clean|required');
	 
	 $this->form_validation->set_rules('tc_tenderfees','Tender Fee','trim|xss_clean|required|numeric');
	 $this->form_validation->set_rules('tc_tenderfeespayble','Payble at','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_tenderfeespaybleat','Payble to','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_processingfees','Processing fee','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_surcharge','Surcharge','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_othercharge','Other charges','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_emdfeesmode','EMD fee','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_emdamount','EMD Amount','trim|xss_clean');
	 $this->form_validation->set_rules('tc_emdpercentage','EMD Percentage','trim|xss_clean');
	 $this->form_validation->set_rules('tc_emdexemption','EMD Exemption Allowed','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_emdexemptionper','EMD Exemption Percentage','trim|xss_clean');	 
	 
	 $this->form_validation->set_rules('tc_publishingdate','Publishing Date','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_docsalestartdate','Sale Start Date ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_docsaleenddate','Sale End Date ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_seekclailstartdate','Seek Clarification Start Date','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_seekclailenddate','Seek Clarification End Date ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_prebidmeetingdate','Pre Bid Meeting Date','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_bidsubstartdate','Bid Submission Start Date ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_bidsubenddate','Bid Submission end Date ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_bidopeningdate','Bid Opening Date','trim|xss_clean|required');
	 
	 $this->form_validation->set_rules('tc_publishingdatet',' Publish Time','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_docsalestartdatet','Sale Start Time  ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_docsaleenddatet','Sale End Time ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_seekclailstartdatet','Clarification Start Time ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_seekclailenddatet','Clarification End Time','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_prebidmeetingdatet','Pre bid Time ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_bidsubstartdatet','Bid Submission Start Time  ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_bidsubenddatet','Bid Submission End Time ','trim|xss_clean|required');
	 $this->form_validation->set_rules('tc_bidopeningdatet','Bid Opening Time ','trim|xss_clean|required');
	 
	 
       $this->form_validation->set_rules('b1',' ','trim|xss_clean|required');
       $this->form_validation->set_rules('n1','Name Field 1','trim|xss_clean|required');
       $this->form_validation->set_rules('d1','Designation Field 1 ','trim|xss_clean|required');
       $this->form_validation->set_rules('e1','Email Field 1 ','trim|xss_clean|required');
      
       $this->form_validation->set_rules('f1','File Name 1','trim|xss_clean|required');
       $this->form_validation->set_rules('de1','Desp. of file 1 ','trim|xss_clean|required');
       $this->form_validation->set_rules('ty1','Type of file 1 ','trim|xss_clean|required');
       $this->form_validation->set_rules('si1','Size of file 1 ','trim|xss_clean|required');
       
       $this->form_validation->set_rules('b2',' ','trim|xss_clean|required');
       $this->form_validation->set_rules('n2','Name Field 2','trim|xss_clean|required');
       $this->form_validation->set_rules('d2','Designation Field 2','trim|xss_clean|required');
       $this->form_validation->set_rules('e2','Email Field 2 ','trim|xss_clean|required');
       
       $this->form_validation->set_rules('f2',' ','trim|xss_clean');
       $this->form_validation->set_rules('de2',' ','trim|xss_clean');
       $this->form_validation->set_rules('ty2',' ','trim|xss_clean');
       $this->form_validation->set_rules('si2',' ','trim|xss_clean');
       
       $this->form_validation->set_rules('b3',' ','trim|xss_clean');
       $this->form_validation->set_rules('n3',' ','trim|xss_clean');
       $this->form_validation->set_rules('d3',' ','trim|xss_clean');
       $this->form_validation->set_rules('e3',' ','trim|xss_clean');
     
       $this->form_validation->set_rules('f3',' ','trim|xss_clean');
       $this->form_validation->set_rules('de3',' ','trim|xss_clean');
       $this->form_validation->set_rules('ty3',' ','trim|xss_clean');
       $this->form_validation->set_rules('si3',' ','trim|xss_clean');
       
       $this->form_validation->set_rules('b4',' ','trim|xss_clean');
       $this->form_validation->set_rules('n4',' ','trim|xss_clean');
       $this->form_validation->set_rules('d4',' ','trim|xss_clean');
       $this->form_validation->set_rules('e4',' ','trim|xss_clean');
     
       $this->form_validation->set_rules('f4',' ','trim|xss_clean');
       $this->form_validation->set_rules('de4',' ','trim|xss_clean');
       $this->form_validation->set_rules('ty4',' ','trim|xss_clean');
       $this->form_validation->set_rules('si4',' ','trim|xss_clean');
       
       $this->form_validation->set_rules('b5',' ','trim|xss_clean');
       $this->form_validation->set_rules('n5',' ','trim|xss_clean');
       $this->form_validation->set_rules('d5',' ','trim|xss_clean');
       $this->form_validation->set_rules('e5',' ','trim|xss_clean');
   
       $this->form_validation->set_rules('f5',' ','trim|xss_clean');
       $this->form_validation->set_rules('de5',' ','trim|xss_clean');
       $this->form_validation->set_rules('ty5',' ','trim|xss_clean');
       $this->form_validation->set_rules('si5',' ','trim|xss_clean');  
	  
				
			if($_POST)	
			{
				
		$tc_refno = $_POST[ 'tc_refno']; 
		$tc_tentype = $_POST[ 'tc_tentype']; 
		$tc_contractform = $_POST[ 'tc_contractform']; 
		$tc_coverid = $_POST[ 'tc_coverid'];
		$tc_category = $_POST[ 'tc_category'];
		$tc_allowresub = $_POST[ 'tc_allowresub'];
		$tc_allowwithdra = $_POST[ 'tc_allowwithdra'];
		$tc_allowoffline = $_POST[ 'tc_allowoffline'];
		$tc_paymode = $_POST[ 'tc_paymode'];
      $tc_covercontent = $_POST[ 'tc_covercontent'];
		$tc_offlineinstrumentid = $_POST[ 'tc_offlineinstrumentid'];
		$tc_onlinebankid = $_POST[ 'tc_onlinebankid'];
		
		$tc_workitemtitle = $_POST[ 'tc_workitemtitle'];
		$tc_workdesc = $_POST[ 'tc_workdesc'];
		$tc_prequaldetails = $_POST[ 'tc_prequaldetails']; 
		$tc_prodcatid = $_POST[ 'tc_prodcatid'];
		$tc_prodsubcat = $_POST[ 'tc_prodsubcat'];
		$tc_contracttype = $_POST[ 'tc_contracttype'];
		$tc_tendervalue = $_POST[ 'tc_tendervalue'];
		$tc_bidvaliddays = $_POST[ 'tc_bidvaliddays'];
		$tc_completionm = $_POST[ 'tc_completionm'];
		$tc_location = $_POST[ 'tc_location'];
		$tc_pincode = $_POST[ 'tc_pincode'];
		$tc_prebidmeeting = $_POST[ 'tc_prebidmeeting']; 
		$tc_prebidmeetplace = $_POST[ 'tc_prebidmeetplace'];
		$tc_prebidmeetadd = $_POST[ 'tc_prebidmeetadd']; 
		$tc_bidopenplace = $_POST[ 'tc_bidopenplace'];
		$tc_invitngofficer = $_POST[ 'tc_invitngofficer'];
		$tc_invitngoffadd = $_POST[ 'tc_invitngoffadd'];
		$tc_invitngoffphone = $_POST[ 'tc_invitngoffphone'];
		$tc_invitngoffemail = $_POST[ 'tc_invitngoffemail'];
		$tc_tenderclass = $_POST[ 'tc_tenderclass'];
		$tc_tendersubclass = $_POST[ 'tc_tendersubclass'];
		
		$tc_tenderfees = $_POST[ 'tc_tenderfees'];
		$tc_tenderfeespayble = $_POST[ 'tc_tenderfeespayble'];
		$tc_tenderfeespaybleat = $_POST[ 'tc_tenderfeespaybleat'];
		$tc_processingfees = $_POST[ 'tc_processingfees'];
		$tc_surcharge = $_POST[ 'tc_surcharge'];
		$tc_othercharge = $_POST[ 'tc_othercharge'];
		$tc_emdfeesmode = $_POST[ 'tc_emdfeesmode'];
		$tc_emdamount = $_POST[ 'tc_emdamount'];
		$tc_emdpercentage = $_POST[ 'tc_emdpercentage'];
		$tc_emdexemption = $_POST[ 'tc_emdexemption'];
		$tc_emdexemptionper = $_POST[ 'tc_emdexemptionper'];
		
		$tc_publishingdate = $_POST[ 'tc_publishingdate'];
		$tc_publishingdatet = $_POST[ 'tc_publishingdatet'];
		$tc_prebidmeetingdate = $_POST[ 'tc_prebidmeetingdate'];
		$tc_prebidmeetingdatet = $_POST[ 'tc_prebidmeetingdatet'];
		$tc_docsalestartdate = $_POST[ 'tc_docsalestartdate'];
		$tc_docsalestartdatet = $_POST[ 'tc_docsalestartdatet'];
		$tc_docsaleenddate = $_POST[ 'tc_docsaleenddate'];
		$tc_docsaleenddatet = $_POST[ 'tc_docsaleenddatet'];
		$tc_seekclailstartdate = $_POST[ 'tc_seekclailstartdate'];
		$tc_seekclailstartdatet = $_POST[ 'tc_seekclailstartdatet'];
		$tc_seekclailenddate = $_POST[ 'tc_seekclailenddate'];
		$tc_seekclailenddatet = $_POST[ 'tc_seekclailenddatet'];
		$tc_bidsubstartdate = $_POST[ 'tc_bidsubstartdate'];
		$tc_bidsubstartdatet = $_POST[ 'tc_bidsubstartdatet'];
		$tc_bidsubenddate = $_POST[ 'tc_bidsubenddate'];
		$tc_bidsubenddatet = $_POST[ 'tc_bidsubenddatet'];
		$tc_bidopeningdate = $_POST[ 'tc_bidopeningdate'];
		$tc_bidopeningdatet = $_POST[ 'tc_bidopeningdatet'];	
				
				
				
				
				
     
			}	
				
				

       if($this->form_validation->run()== FALSE)
       {

          $this->load->view('tender/tenderedit',$data);
          return;

       }
       else {  
        if(isset($_POST['submit']))
                 {
                   $eid=$id; 
    



      $data_a= $tc_refno; 
		$data_b= $tc_tentype; 
		$data_c= $tc_contractform; 
		$data_d= $tc_coverid;
		$data_e= $tc_category;
		$data_f= $tc_allowresub;
		$data_g= $tc_allowwithdra;
		$data_h= $tc_allowoffline;
		$data_i= $tc_paymode;
      $data_j= $tc_covercontent;
		$data_k= $tc_offlineinstrumentid;
		$data_l= $tc_onlinebankid;
		
		$data_m = $tc_workitemtitle;
		$data_n = $tc_workdesc;
		$data_o = $tc_prequaldetails; 
		$data_p = $tc_prodcatid;
		$data_q = $tc_prodsubcat;
		$data_r = $tc_contracttype;
		$data_s = $tc_tendervalue;
		$data_t = $tc_bidvaliddays;
		$data_u = $tc_completionm;
		$data_v = $tc_location;
		$data_w = $tc_pincode;
		$data_x = $tc_prebidmeeting; 
		$data_y = $tc_prebidmeetplace;
		
		$dataa_a = $tc_prebidmeetadd; 
		$dataa_b = $tc_bidopenplace;
		$dataa_c = $tc_invitngofficer;
		$dataa_d = $tc_invitngoffadd;
		$dataa_e = $tc_invitngoffphone;
		$dataa_f = $tc_invitngoffemail;
		$dataa_g = $tc_tenderclass;
		$dataa_h = $tc_tendersubclass;
		
		$dataa_i = $tc_tenderfees;
		$dataa_j = $tc_tenderfeespayble;
		$dataa_k = $tc_tenderfeespaybleat;
		$dataa_l = $tc_processingfees;
		$dataa_m = $tc_surcharge;
		$dataa_n = $tc_othercharge;
		$dataa_o = $tc_emdfeesmode;
		$dataa_p = $tc_emdamount;
		$dataa_q = $tc_emdpercentage;
		$dataa_r = $tc_emdexemption;
		$dataa_s = $tc_emdexemptionper;
		
		$dataa_t = $tc_publishingdate;
		$dataa_u = $tc_publishingdatet;
		$dataa_v = $tc_prebidmeetingdate;
		$dataa_w = $tc_prebidmeetingdatet;
		$dataa_x = $tc_docsalestartdate;
		$dataa_y = $tc_docsalestartdatet;
		$dataa_z = $tc_docsaleenddate;
		
		$dataaa_a = $tc_docsaleenddatet;
		$dataaa_b = $tc_seekclailstartdate;
		$dataaa_c = $tc_seekclailstartdatet;
		$dataaa_d = $tc_seekclailenddate;
		$dataaa_e = $tc_seekclailenddatet;
		$dataaa_f = $tc_bidsubstartdate;
		$dataaa_g = $tc_bidsubstartdatet;
		$dataaa_h = $tc_bidsubenddate;
		$dataaa_i = $tc_bidsubenddatet;
		$dataaa_j = $tc_bidopeningdate;
		$dataaa_k = $tc_bidopeningdatet;



   
       
       //logger
        $logmessage = "";
           
            if($tender->tc_refno != $data_a)
                $logmessage = "Update Tender " .$tender->tc_refno. " changed by " .$data_a;
            if($tender->tc_tentype != $data_b)
                $logmessage = "Update Tender " .$tender->tc_tentype. " changed by " .$data_b;
            if($tender->tc_contractform != $data_c)
                $logmessage = "Update Tender " .$tender->tc_contractform. " changed by " .$data_c;
            if($tender->tc_coverid != $data_d)
                $logmessage = "Update Tender " .$tender->tc_coverid. " changed by " .$data_d;
            if($tender->tc_category != $data_e)
                $logmessage = "Update Tender " .$tender->tc_category. " changed by " .$data_e;
            if($tender->tc_allowresub != $data_f)
                $logmessage = "Update Tender " .$tender->tc_allowresub. " changed by " .$data_f;
            if($tender->tc_allowwithdra != $data_g)
                $logmessage = "Update Tender " .$tender->tc_allowwithdra. " changed by " .$data_g;
            if($tender->tc_allowoffline != $data_h)
                $logmessage = "Update Tender " .$tender->tc_allowoffline. " changed by " .$data_h;
            if($tender->tc_paymode != $data_i)
                $logmessage = "Update Tender " .$tender->tc_paymode. " changed by " .$data_i;
            if($tender->tc_covercontent != $data_j)
                $logmessage = "Update Tender " .$tender->tc_covercontent. " changed by " .$data_j;
             if($tender->tc_offlineinstrumentid != $data_k)
                $logmessage = "Update Tender " .$tender->tc_offlineinstrumentid. " changed by " .$data_k;
            if($tender->tc_onlinebankid != $data_l)
                $logmessage = "Update Tender " .$tender->tc_onlinebankid. " changed by " .$data_l;
            if($tender->tc_workitemtitle != $data_m)
                $logmessage = "Update Tender " .$tender->tc_workitemtitle. " changed by " .$data_m;
             if($tender->tc_workdesc != $data_n)
                $logmessage = "Update Tender " .$tender->tc_workdesc. " changed by " .$data_n;
            if($tender->tc_prequaldetails != $data_o)
                $logmessage = "Update Tender " .$tender->tc_prequaldetails. " changed by " .$data_o;
             if($tender->tc_prodcatid != $data_p)
                $logmessage = "Update Tender " .$tender->tc_prodcatid. " changed by " .$data_p;
            if($tender->tc_prodsubcat != $data_q)
                $logmessage = "Update Tender " .$tender->tc_prodsubcat. " changed by " .$data_q;
             if($tender->tc_contracttype != $data_r)
                $logmessage = "Update Tender " .$tender->tc_contracttype. " changed by " .$data_r;
            if($tender->tc_tendervalue != $data_s)
                $logmessage = "Update Tender " .$tender->tc_tendervalue. " changed by " .$data_s;
            if($tender->tc_bidvaliddays != $data_t)
                $logmessage = "Update Tender " .$tender->tc_bidvaliddays. " changed by " .$data_t;
            if($tender->tc_completionm != $data_u)
                $logmessage = "Update Tender " .$tender->tc_completionm. " changed by " .$data_u;
            if($tender->tc_location != $data_v)
                $logmessage = "Update Tender " .$tender->tc_location. " changed by " .$data_v;
            if($tender->tc_pincode != $data_w)
                $logmessage = "Update Tender " .$tender->tc_pincode. " changed by " .$data_w;
            if($tender->tc_prebidmeeting != $data_x)
                $logmessage = "Update Tender " .$tender->tc_prebidmeeting. " changed by " .$data_x;
            if($tender->tc_prebidmeetplace != $data_y)
                $logmessage = "Update Tender " .$tender->tc_prebidmeetplace. " changed by " .$data_y;
           
     
       
           if($tender->tc_prebidmeetadd != $dataa_a)
                $logmessage = "Update Tender " .$tender->tc_prebidmeetadd. " changed by " .$dataa_a;
            if($tender->tc_bidopenplace != $dataa_b)
                $logmessage = "Update Tender " .$tender->tc_bidopenplace. " changed by " .$dataa_b;
            if($tender->tc_invitngofficer != $dataa_c)
                $logmessage = "Update Tender " .$tender->tc_invitngofficer. " changed by " .$dataa_c;
            if($tender->tc_invitngoffadd != $dataa_d)
                $logmessage = "Update Tender " .$tender->tc_invitngoffadd. " changed by " .$dataa_d;
            if($tender->tc_invitngoffphone != $dataa_e)
                $logmessage = "Update Tender " .$tender->tc_invitngoffphone. " changed by " .$dataa_e;
            if($tender->tc_invitngoffemail != $dataa_f)
                $logmessage = "Update Tender " .$tender->tc_invitngoffemail. " changed by " .$dataa_f;
            if($tender->tc_tenderclass != $dataa_g)
                $logmessage = "Update Tender " .$tender->tc_tenderclass. " changed by " .$dataa_g;
            if($tender->tc_tendersubclass != $dataa_h)
                $logmessage = "Update Tender " .$tender->tc_tendersubclass. " changed by " .$dataa_h;
            if($tender->tc_tenderfees != $dataa_i)
                $logmessage = "Update Tender " .$tender->tc_tenderfees. " changed by " .$dataa_i;
            if($tender->tc_tenderfeespayble != $dataa_j)
                $logmessage = "Update Tender " .$tender->tc_tenderfeespayble. " changed by " .$dataa_j;
             if($tender->tc_tenderfeespaybleat != $dataa_k)
                $logmessage = "Update Tender " .$tender->tc_tenderfeespaybleat. " changed by " .$dataa_k;
            if($tender->tc_processingfees != $dataa_l)
                $logmessage = "Update Tender " .$tender->tc_processingfees. " changed by " .$dataa_l;
            if($tender->tc_surcharge != $dataa_m)
                $logmessage = "Update Tender " .$tender->tc_surcharge. " changed by " .$dataa_m;
             if($tender->tc_othercharge != $dataa_n)
                $logmessage = "Update Tender " .$tender->tc_othercharge. " changed by " .$dataa_n;
            if($tender->tc_emdfeesmode != $dataa_o)
                $logmessage = "Update Tender " .$tender->tc_emdfeesmode. " changed by " .$dataa_o;
             if($tender->tc_emdamount != $dataa_p)
                $logmessage = "Update Tender " .$tender->tc_emdamount. " changed by " .$dataa_p;
            if($tender->tc_emdpercentage != $dataa_q)
                $logmessage = "Update Tender " .$tender->tc_emdpercentage. " changed by " .$dataa_q;
             if($tender->tc_emdexemption != $dataa_r)
                $logmessage = "Update Tender " .$tender->tc_emdexemption. " changed by " .$dataa_r;
            if($tender->tc_emdexemptionper != $dataa_s)
                $logmessage = "Update Tender " .$tender->tc_emdexemptionper. " changed by " .$dataa_s;
            if($tender->tc_publishingdate != $dataa_t)
                $logmessage = "Update Tender " .$tender->tc_publishingdate. " changed by " .$dataa_t;
            if($tender->tc_publishingdatet != $dataa_u)
                $logmessage = "Update Tender " .$tender->tc_publishingdatet. " changed by " .$dataa_u;
            if($tender->tc_prebidmeetingdate != $dataa_v)
                $logmessage = "Update Tender " .$tender->tc_prebidmeetingdate. " changed by " .$dataa_v;
            if($tender->tc_prebidmeetingdatet != $dataa_w)
                $logmessage = "Update Tender " .$tender->tc_prebidmeetingdatet. " changed by " .$dataa_w;
            if($tender->tc_docsalestartdate != $dataa_x)
                $logmessage = "Update Tender " .$tender->tc_docsalestartdate. " changed by " .$dataa_x;
            if($tender->tc_docsalestartdatet != $dataa_y)
                $logmessage = "Update Tender " .$tender->tc_docsalestartdatet. " changed by " .$dataa_y;
            if($tender->tc_docsaleenddate != $dataa_z)
                $logmessage = "Update Tender " .$tender->tc_docsaleenddate. " changed by " .$dataa_z;
                
                
                
                
            if($tender->tc_docsaleenddatet != $dataaa_a)
                $logmessage = "Update Tender " .$tender->tc_docsaleenddatet. " changed by " .$dataaa_a;
            if($tender->tc_seekclailstartdate != $dataaa_b)
                $logmessage = "Update Tender " .$tender->tc_seekclailstartdate. " changed by " .$dataaa_b;
            if($tender->tc_seekclailstartdatet != $dataaa_c)
                $logmessage = "Update Tender " .$tender->tc_seekclailstartdatet. " changed by " .$dataaa_c;
            if($tender->tc_seekclailenddate != $dataaa_d)
                $logmessage = "Update Tender " .$tender->tc_seekclailenddate. " changed by " .$dataaa_d;
            if($tender->tc_seekclailenddatet != $dataaa_e)
                $logmessage = "Update Tender " .$tender->tc_seekclailenddatet. " changed by " .$dataaa_e;
            if($tender->tc_bidsubstartdate != $dataaa_f)
                $logmessage = "Update Tender " .$tender->tc_bidsubstartdate. " changed by " .$dataaa_f;
            if($tender->tc_bidsubstartdatet != $dataaa_g)
                $logmessage = "Update Tender " .$tender->tc_bidsubstartdatet. " changed by " .$dataaa_g;
            if($tender->tc_bidsubenddate != $dataaa_h)
                $logmessage = "Update Tender " .$tender->tc_bidsubenddate. " changed by " .$dataaa_h;
            if($tender->tc_bidsubenddatet != $dataaa_i)
                $logmessage = "Update Tender " .$tender->tc_bidsubenddatet. " changed by " .$dataaa_i;
            if($tender->tc_bidopeningdate != $dataaa_j)
                $logmessage = "Update Tender " .$tender->tc_bidopeningdate. " changed by " .$dataaa_j;
            if($tender->tc_bidopeningdatet != $dataaa_k)
                $logmessage = "Update Tender " .$tender->tc_bidopeningdatet. " changed by " .$dataaa_k;
       
            
            
            $update_data= array(
                  'tc_refno'=>$data_a, 
						'tc_tentype'=>$data_b, 
						'tc_contractform'=>$data_c ,
						'tc_coverid'=>$data_d,
						'tc_category'=>$data_e,
						'tc_allowresub'=>$data_f,
						'tc_allowwithdra'=>$data_g,
						'tc_allowoffline'=>$data_h,
						'tc_paymode'=>$data_i,
						'tc_covercontent'=>$data_j,
						'tc_offlineinstrumentid'=>$data_k,
						'tc_onlinebankid'=>$data_l,
						
						'tc_workitemtitle'=>$data_m,
						'tc_workdesc'=>$data_n,
						'tc_prequaldetails'=>$data_o ,
						'tc_prodcatid'=>$data_p,
						'tc_prodsubcat'=>$data_q,
						'tc_contracttype'=>$data_r,
						'tc_tendervalue'=>$data_s,
						'tc_bidvaliddays'=>$data_t,
						'tc_completionm'=>$data_u,
						'tc_location'=>$data_v,
						'tc_pincode'=>$data_w,
						'tc_prebidmeeting'=>$data_x ,
						'tc_prebidmeetplace'=>$data_y,
						
						'tc_prebidmeetadd'=>$dataa_a ,
						'tc_bidopenplace'=>$dataa_b,
						'tc_invitngofficer'=>$dataa_c,
						'tc_invitngoffadd'=>$dataa_d,
						'tc_invitngoffphone'=>$dataa_e,
						'tc_invitngoffemail'=>$dataa_f,
						'tc_tenderclass'=>$dataa_g,
						'tc_tendersubclass'=>$dataa_h,
						
						'tc_tenderfees'=>$dataa_i,
						'tc_tenderfeespayble'=>$dataa_j,
						'tc_tenderfeespaybleat'=>$dataa_k,
						'tc_processingfees'=>$dataa_l,
						'tc_surcharge'=>$dataa_m,
						'tc_othercharge'=>$dataa_n,
						'tc_emdfeesmode'=>$dataa_o,
						'tc_emdamount'=>$dataa_p,
						'tc_emdpercentage'=>$dataa_q,
						'tc_emdexemption'=>$dataa_r,
						'tc_emdexemptionper'=>$dataa_s,
						
						'tc_publishingdate'=>$dataa_t,
						'tc_publishingdatet'=>$dataa_u,
						'tc_prebidmeetingdate'=>$dataa_v,
						'tc_prebidmeetingdatet'=>$dataa_w,
						'tc_docsalestartdate'=>$dataa_x,
						'tc_docsalestartdatet'=>$dataa_y,
						'tc_docsaleenddate'=>$dataa_z,
						
						'tc_docsaleenddatet'=>$dataaa_a,
						'tc_seekclailstartdate'=>$dataaa_b,
						'tc_seekclailstartdatet'=>$dataaa_c,
						'tc_seekclailenddate'=>$dataaa_d,
						'tc_seekclailenddatet'=>$dataaa_e,
						'tc_bidsubstartdate'=>$dataaa_f,
						'tc_bidsubstartdatet'=>$dataaa_g,
						'tc_bidsubenddate'=>$dataaa_h,
						'tc_bidsubenddatet'=>$dataaa_i,
						'tc_bidopeningdate'=>$dataaa_j,
						'tc_bidopeningdatet'=>$dataaa_k,
						 
						'tc_modifierid'=>$this->session->userdata('username'),
                  'tc_modifierdate'=>date('Y-m-d'),
						 );
						 
						   
                	      for($i=1;$i<6;$i++)
                	      {
                	   
                	       $n= $this->input->post('n'.$i, TRUE);
                                  	       
                	       if(!empty($n)) 
                	       {
                              
                         $idb=$this->input->post('tbos'.$i, TRUE);      
                        $datax= array(
                        
                         'tbos_tcid'=>$this->input->post('tc'.$i, TRUE),
                     
                         'tbos_bono'=>$this->input->post('b'.$i, TRUE),
                         'tbos_boname'=>$n,
                         'tbos_bodesig'=>$this->input->post('d'.$i, TRUE),
                         'tbos_boemail'=>$this->input->post('e'.$i, TRUE),
                         
                         'tbos_modifierid'=>$this->session->userdata('username'),
                        
                                      );
                        $rflag=$this->PICO_model->updaterec('tender_bid_openers_selection', $datax,'tbos_id',$idb);           	
                          
                        }                	
                	      else {
                	      	break;
								
								  }                	
                      	
                      	}
                      	
                    
                      	
                      	for($i=1;$i<6;$i++)
                	      {
                	     
                	       $f=$this->input->post('f'.$i, TRUE);	
                	       if(!empty($f)) 
                	       {
                              
                          $ida=$this->input->post('tud'.$i, TRUE); 
                          
                         $datay= array(
                         'tud_tcid'=>$this->input->post('tcc'.$i, TRUE),
                         'tud_filename'=>$f,
                         'tud_desc'=>$this->input->post('de'.$i, TRUE),
                         'tud_filetype'=>$this->input->post('ty'.$i, TRUE),
                         'tud_filesize'=>$this->input->post('si'.$i, TRUE),
                         
                         'tud_modifierid'=>$this->session->userdata('username'),
                        
                          );
                          $rflag=$this->PICO_model->updaterec('tender_upload_doc',$datay,'tud_id',$ida);           	
                          
                          }                	
                	        else {
                	        break;
								
								  }                	
                      	
                      	  }
                      	
						 
                   
             $roledflag=$this->PICO_model->updaterec('tender_create', $update_data,'tc_id',$eid);
             if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit  Setting error", "Edit vendor Setting details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit  Setting error", "Edit vendor Setting details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating  - ' . $logmessage . '.', 'error');
                $this->load->view('tender/tenderedit', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit  Setting", "Edit Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit  Setting", "Edit  Setting details. $logmessage ");
                $this->session->set_flashdata('success','Updated Successfully..');
                redirect('tender/tenderdisplay/');
              
                }
						 
						 
						 
            }
            redirect('tender/tenderdisplay');
            }

//admin close

}
else 
redirect('tender/tenderdisplay');
}

  public function tenderdelete($id) {
          $suname=$this->session->userdata['username'];
	       if((strcasecmp($suname,"admin"))==0)	
          {
          	
          $roledflag=$this->PICO_model->deleterow('tender_create','tc_id', $id);
          if(!$roledflag)
          {
          	$this->logger->write_message("error", "Error  in deleting tender " ."[tc_id:" . $id . "]");
            $this->logger->write_dbmessage("error", "Error  in deleting tender "." [tc_id:" . $id . "]");
            $this->session->set_flashdata('err_message', 'Error In Deleting Tender  - ', 'error');
            redirect('tender/tenderdisplay');
           return;
          }
          else{
          $this->logger->write_logmessage("delete", "Deleted   tender  " . "[tc_id:" . $id . "] deleted successfully.. " );
           $this->logger->write_dblogmessage("delete", "Deleted tender " ." [tc_id:" . $id . "] deleted successfully.. " );
            $this->session->set_flashdata("success", 'Tender Deleted Successfully...' );
            redirect('tender/tenderdisplay');
          }       
 
          $this->load->view('tender/tenderdisplay');
          }
          else  redirect('tender/tenderdisplay');
    
    
    }

 public function tenderapprove($id)
 {
   $suname=$this->session->userdata['username'];
	       if((strcasecmp($suname,"admin"))==0)	
           {
           	$tid=$id;
           	$n=$this->PICO_model->get_listspfic1('tender_create','tc_refno','tc_id',$tid)->tc_refno;
           	$status='Approved';
           	$update_data= array(
              'tc_approvedstatus'=>$status,         	 
           	  'tc_approvedbyname'=>$this->session->userdata('username'),
              'tc_approvedbydesig'=>$this->session->userdata('username'),
           	 );
           	 $update_archive= array(
           	  'tc_id'=>$tid,
           	  'tc_refno'=>$n,
              'tc_approvedstatus'=>$status,         	 
           	  'tc_byname'=>$this->session->userdata('username'),
              'tc_bydesig'=>$this->session->userdata('username'),
           	 );
            $entry_archive_id=$this->PICO_model->insertdata('tender_create_archive', $update_archive); 
           	$roledflag=$this->PICO_model->updaterec('tender_create', $update_data,'tc_id',$tid);
            if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->session->set_flashdata('err_message','Error Approving Tender- ' . $logmessage . '.', 'error');
                $this->load->view('tender/tenderdisplay');
            }
            else{
                $this->logger->write_logmessage("update","Edit  Setting", "Edit Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit  Setting", "Edit  Setting details. $logmessage ");
                $this->session->set_flashdata('success','Updated and Approved..');
                redirect('tender/tenderdisplay/');
              
                }  
           	
            return;
           }
         $this->session->set_flashdata("err_message", 'Tender can\'t be approved by you.....' );
         redirect('tender/tenderdisplay');
 }

public function tenderdismiss($id)
 {
   $suname=$this->session->userdata['username'];
	       if((strcasecmp($suname,"admin"))==0)	
           {
           	$tid=$id;
           	$n=$this->PICO_model->get_listspfic1('tender_create','tc_refno','tc_id',$tid)->tc_refno;
           	$status='Dismissed';
           	$update_data= array(
              'tc_approvedstatus'=>$status,         	 
           	  'tc_approvedbyname'=>$this->session->userdata('username'),
              'tc_approvedbydesig'=>$this->session->userdata('username'),
           	 );
           	 $update_archive= array(
           	  'tc_id'=>$tid,
           	  'tc_refno'=>$n,
              'tc_approvedstatus'=>$status,         	 
           	  'tc_byname'=>$this->session->userdata('username'),
              'tc_bydesig'=>$this->session->userdata('username'),
           	 );
           	$entry_archive_id=$this->PICO_model->insertdata('tender_create_archive', $update_archive);
           	$roledflag=$this->PICO_model->updaterec('tender_create', $update_data,'tc_id',$tid);
            if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->session->set_flashdata('err_message','Error Dis-Approving Tender- ' . $logmessage . '.', 'error');
                $this->load->view('tender/tenderdisplay');
            }
            else{
                $this->logger->write_logmessage("update","Edit  Setting", "Edit Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit  Setting", "Edit  Setting details. $logmessage ");
                $this->session->set_flashdata('success','Updated and Dis-Approved..');
                redirect('tender/tenderdisplay/');
              
                }  
           	
            return;
           }
         $this->session->set_flashdata("err_message", 'Tender can\'t be Dis-Approved by you.....' );
         redirect('tender/tenderdisplay');
 }

public function tender_re_upload()

{
   $tid=$_POST['tid'];
   
   $n=$this->PICO_model->get_listspfic1('tender_create','tc_refno','tc_id',$tid)->tc_refno;
           
  
                     
          
			               $target_dir = './uploads/PICO/tender/'.$n.'/'.$tid.'/';

			               $target_file = $target_dir . basename($_FILES["file_a"]["name"]);
			               
			               $uploadOk = 1;
			               $imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));

		   
			if(isset($_POST["submit"])) 
			
		    if ($_FILES["file_a"]["size"] > 500000) //500kb
		    
		                                       { $this->session->set_flashdata("success", "Sorry, your file is too large.");
  	 	                                         redirect('tender/tenderdisplay');//$this->load->view('tender/tenderdisplay');
                                               $uploadOk = 0;
                                               return;
				                                 }
          if($imageFileType != "jpg" && $imageFileType != "pdf" ) // && $imageFileType != "jpeg" && $imageFileType != "gif" ) 
		                                       { $this->session->set_flashdata("success",  "Sorry, only JPG & pdf files are allowed.");
  				                                   redirect('tender/tenderdisplay');//$this->load->view('tender/tenderdisplay');
                                               $uploadOk = 0;
 				                                   return;
											            }

          if ($uploadOk == 0)
			                                    { $this->session->set_flashdata("success", "Sorry, your file was not uploaded.");
                                               redirect('tender/tenderdisplay');//$this->load->view('tender/tenderdisplay');
                                               return;
                                             } 
                                      else 
                                          {
                                          	$name = $target_dir.'nit.'.$imageFileType;
                                            if (move_uploaded_file($_FILES["file_a"]["tmp_name"], $name)) 
                                              {
                                              	
                                              	$file_name=basename( $_FILES["file_a"]["name"]);
                                              	$file_size=$_FILES["file_a"]["size"];
                                              	$d=array(   'tc_nitdocfilesize'=>$file_size,
                                              	            'tc_nitdocfilename'=>$file_name,
                                              	            'tc_nitdoctype'=>$imageFileType,
                                                         	);
                                              	$rflag=$this->PICO_model->updaterec('tender_create', $d,'tc_id',$tid);
                                              	
                    	                           $this->session->set_flashdata("success", "The file ". basename( $_FILES["file_a"]["name"]). " has been uploaded....");
                                                redirect('tender/tenderdisplay');//$this->load->view('tender/tenderdisplay');
                                                return;
                                              }
                                                else 
                                              { $this->session->set_flashdata("success", "Sorry, there was an error uploading your file.");
          			                              redirect('tender/tenderdisplay');//$this->load->view('tender/tenderdisplay');
           				                           return;
                                              }
                                           }

}

public function question()
{

     $this->load->view('tender/query');


}

public function tender_question()
{
    if(isset($_POST['submit']))  {
              
                 $this->form_validation->set_rules('q_email','Email   ','trim|xss_clean|required');
                 $this->form_validation->set_rules('q_subject','Subject ','trim|xss_clean|required');
                  $this->form_validation->set_rules('q_desc','Description ','trim|xss_clean|required');
                 if($this->form_validation->run()==TRUE){
               

                 $data = array(
                'tq_email'=>$_POST['q_email'],
                'tq_subject'=>$_POST['q_subject'],
                'tq_desc'=>$_POST['q_desc'],
                 );
               
                $rflag=$this->PICO_model->insertrec('tender_query', $data);
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to send", "message not sent by".$q_email);
                    $this->logger->write_dblogmessage("insert","Trying to send", "message not sent by".$q_email);
                    $this->session->set_flashdata('err_message','Error In Sending message- '  , 'error');
                    redirect('tender/tender_apply_list');

                }
                else{
                    $this->logger->write_logmessage("insert","query sent from", "tender_query".$_POST['q_email']." successfully...");
                    $this->logger->write_dblogmessage("insert","query sent from", "tender_query".$_POST['q_email']."  successfully...");
                    $this->session->set_flashdata("success", "Message Sent Successfully...");
                    redirect("tender/tender_apply_list");
        }
        }
       
    }
    $this->load->view('tender/query');
}


public function tender_apply_list() 
       {
	   	
	     $selectfield='tc_id,tc_refno,tc_tenderfees,tc_processingfees,tc_surcharge
	     ,tc_emdfeesmode,tc_emdamount,tc_emdpercentage,tc_emdexemption,tc_publishingdate,tc_bidsubstartdate,tc_approvedstatus
	     ,tc_bidsubenddate,tc_bidopeningdate';
	     $joincond='tender_bid_openers_selection.tbos_tcid=tender_create.tc_id';
        $data['result'] = $this->PICO_model->get_jointbrecord('tender_create',$selectfield,'tender_bid_openers_selection',$joincond,'LEFT',''); //,$whdata   get_list('tender_create');
        $this->logger->write_logmessage("view"," View tenders for apply", "tender  details...");
        $this->logger->write_dblogmessage("view"," View tenders for apply", "tender  details...");
        $this->load->view('tender/tender_apply_list',$data);
       }

public function tenderapply($id)

{              $data['tc_id']=$id;	
               $data['tc_refno']=$this->PICO_model->get_listspfic1('tender_create','tc_refno','tc_id',$id)->tc_refno;
              $fieldems="tc_tenderfees,tc_processingfees,tc_surcharge,tc_emdfeesmode,tc_emdamount,tc_emdpercentage,tc_emdexemption,tc_othercharge ";
              $whdataems = array ('tc_id' => $id);
              $whorderems = '';
              $data['result'] = $this->PICO_model->get_orderlistspficemore('tender_create',$fieldems,$whdataems,$whorderems);   
              $whdata1 = array('tud_tcid'=>$id); 
              $data['upload']=$this->PICO_model->get_orderlistspficemore('tender_upload_doc','*',$whdata1,''); 
              $this->load->view('tender/tender_apply',$data);              
             	
}



public function tender_apply()

{              if(isset($_POST['submit']))  { 
               echo 'working here line 1732'; 	
               die();      	
                                             }
          
          
}

} 
    ?>
       