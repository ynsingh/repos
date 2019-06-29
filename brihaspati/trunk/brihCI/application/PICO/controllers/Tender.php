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
  /* public function tenderform2()
    {
     		$dataa['tid']=$tid;
        $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
        
        $this->load->view('tender/step2',$dataa);
    }	 
   /*   public function tenderform3($id)
    {
        $data['tid']=$id;
        $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
        
        $this->load->view('tender/step3',$data);
    }	
    public function tenderform4($id)
    {
       $data['tid']=$id;
        $this->logger->write_logmessage("view"," View tender form ", " tender form details...");
        
        $this->load->view('tender/step4',$data);
    }	
 */   
  public function tenderbasic()   
   
   {


                 if(isset($_POST['bd'])) {
                 	
               
                $this->form_validation->set_rules('bd_trn','Tender Reference No','required|alpha_numeric');
                $this->form_validation->set_rules('bd_tt','Tender Type ','required');
                $this->form_validation->set_rules('bd_foc','Form of Contract','required');
                $this->form_validation->set_rules('bd_noc','No. of Covers','required');
                $this->form_validation->set_rules('bd_tc','Tender Category','required');
                $this->form_validation->set_rules('bd_ars','Allow Re-submission','required');
                $this->form_validation->set_rules('bd_aw','Allow Withdrawal','required');
                $this->form_validation->set_rules('bd_aos','Allow Offline Submission','required');
                $this->form_validation->set_rules('bd_pm','Payment Mode','required');
         //       $this->form_validation->set_rules('bd_offline','Offline','');
       //         $this->form_validation->set_rules('bd_online','Online','');
                $this->form_validation->set_rules('bd_covcon','Cover Content','required|alpha_numeric');
                
                
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
                //$b=$_POST['file_a'];
                //$a['file_a']=$b;
                //$upflag=$this->tenderupload($a,$entryid,$_POST['bd_trn']);
                
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
                   
                    //redirect("tender/tenderform2/".$data);
                     return;
                } } }
      $this->load->view('tender/step1');
}
              
              
              public function tenderwork()   
					{
				   $data['tid']=$_POST['tid'];	
						
               if(isset($_POST['wd'])) {
                 	
               
                 $this->form_validation->set_rules('wid_wit','Work item Details..','required|alpha_dash');
                 $this->form_validation->set_rules('wid_wd',' Work Description..','required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_pd','Prequal Details..','required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_pc','Product category..','required');
                 $this->form_validation->set_rules('wid_psc','Product Sub Category..','required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_ct','Contract Type..','required');
                 $this->form_validation->set_rules('wid_tv','Tender Value..','required');
                 $this->form_validation->set_rules('wid_bvd','Bid valid days..','required');
                 $this->form_validation->set_rules('wid_cpm','Completion Period (Months)','required|numeric');
                 $this->form_validation->set_rules('wid_l',' Location..','required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_p','Pincode..','required|numeric|exact_length[6]');
                $this->form_validation->set_rules('wid_pbm','Pre Bid Meeting..','required');
                $this->form_validation->set_rules('wid_pbmp','Pre Bid Meeting place..','required');
                $this->form_validation->set_rules('wid_pbma','Pre Bid Meeting address..','required');
                 $this->form_validation->set_rules('wid_bop','Bid Open place','required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_tc','Tenderer Class','required');
                 $this->form_validation->set_rules('wid_io','Inviting Officer','required|alpha');
                 $this->form_validation->set_rules('wid_ioa','Inviting Officer Address ','required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('wid_iop','Inviting Officer Phone','required');
                 $this->form_validation->set_rules('wid_ioe','Inviting Officer Email','required');
                  		
                if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
					  //get tender id
					  $id=$_POST['tid'];
					  $data['tid']=$id;
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
                'tc_prebidmeeting'=>$_POST['wid_pbm'],
                'tc_bidopenplace'=>$_POST['wid_bop'],
                'tc_tenderclass'=>$_POST['wid_tc'],
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
                 	
               
                $this->form_validation->set_rules('fd_tf','Tender Fee','required|numeric');
                $this->form_validation->set_rules('fd_tfpt','','required');
                $this->form_validation->set_rules('fd_tfpa','field 3 ','required');
                $this->form_validation->set_rules('fd_pf','field 4 ','required');
                $this->form_validation->set_rules('fd_s','field 5  ','required');
                $this->form_validation->set_rules('fd_oc','field 6 ','required');
                $this->form_validation->set_rules('fd_ef','field 7  ','required');
                $this->form_validation->set_rules('fd_a','field 8 ','required');
                $this->form_validation->set_rules('fd_p','field 9 ','required');
                $this->form_validation->set_rules('fd_eea','field 10','required');
                $this->form_validation->set_rules('fd_eep','field 11','required');
                  	  
                if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                 $id=$_POST['tid'];
                 $data['tid']=$id;
                 $tdata=array(
                'tc_tenderfees'=>$_POST['fd_tf'],
                'tc_tenderfeespayble'=>$_POST['fd_tfpt'],
                'tc_tenderfeespaybleat'=>$_POST['fd_tfpa'],
                'tc_processingfees'=>$_POST['fd_pf'],
                'tc_surcharge'=>$_POST['fd_s'],
                'tc_othercharge'=>$_POST['fd_oc'],
                'tc_emdfeesmode'=>$_POST['fd_ef'],
                'tc_emdamount'=>$_POST['fd_a'],
                'tc_emdpercentage'=>$_POST['fd_p'],
                'tc_emdexemption'=>$_POST['fd_eea'],
                'tc_emdexemptionper'=>$_POST['fd_eep'],
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
        $this->load->view('setup/step3',$data);


}

public function tendercritical()   
   
   {

                 $data['tid']=$_POST['tid'];
                 if(isset($_POST['cd'])) {
                 	
               
                $this->form_validation->set_rules('cd_pd','.....','required');
                $this->form_validation->set_rules('cd_dssd','Critical dates  ','required');
                $this->form_validation->set_rules('cd_dsed','Critical dates ','required');
                $this->form_validation->set_rules('cd_scsd','Critical dates ','required');
                $this->form_validation->set_rules('cd_sced','Critical dates  ','required');
                $this->form_validation->set_rules('cd_pbmd','Critical dates ','required');
                $this->form_validation->set_rules('cd_bssd','Critical dates  ','required');
                $this->form_validation->set_rules('cd_bsed','Critical dates ','required');
                $this->form_validation->set_rules('cd_bod','Critical dates ','required');
              
                  	  
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
        $this->load->view('setup/step4',$data);


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

		   // Check if image file is a actual image or fake image
			if(isset($_POST["submit"])) 
			{
				
   	    $check = getimagesize($_FILES["file_a"]["tmp_name"]);
   	    if($check !== false) {
     	    // echo "File is an image - " . $check["mime"] . ".";
          //$this->session->set_flashdata("success", "File is an image - " . $check["mime"] . ".");//on same page
          $uploadOk = 1;
   			                  } 
  				                  
  		     else {
       						// echo "File is not an image.";
       						$this->session->set_flashdata("success", "File is not an image.");
       						$this->load->view('tender/step5',$data);
                        $uploadOk = 0;
                        return;
                }
      	 }  


		    /*/ Check if file already exists
		       if (file_exists($target_file)) {
   	       // echo "Sorry, file already exists.";
   	       $this->session->set_flashdata("success", "Sorry, file already exists.");
   	       $uploadOk = 0;
																	}

          */
	       // Check file size
		    if ($_FILES["file_a"]["size"] > 500000) //500kb
		    
		                                          { 
  	 		 
  	 	                                         //echo "Sorry, your file is too large.";
  	                                         	  $this->session->set_flashdata("success", "Sorry, your file is too large.");
  	 	                                         $this->load->view('tender/step5',$data);
                                               $uploadOk = 0;
                                               return;
				                                      
				                                       
				                                    }


          // Allow certain file formats

		    if($imageFileType != "jpg" && $imageFileType != "pdf" ) // && $imageFileType != "jpeg" && $imageFileType != "gif" ) 
		                                        {
  				                                    // echo "Sorry, only JPG, JPEG, PNG & GIF files are allowed.";
  				                                    $this->session->set_flashdata("success",  "Sorry, only JPG & pdf files are allowed.");
  				                                    $this->load->view('tender/step5',$data);
                                               
 				                                    $uploadOk = 0;
 				                                    return;
											             }


		    // Check if $uploadOk is set to 0 by an error
			
		    if ($uploadOk == 0)
			                                    {
   		                                       //echo "Sorry, your file was not uploaded.";
                                               $this->session->set_flashdata("success", "Sorry, your file was not uploaded.");
                                               $this->load->view('tender/step5',$data);
                                               return;
    
                                             }
                         
                                            else // if everything is ok, try to upload file
     
                                            {
                                                if (move_uploaded_file($_FILES["file_a"]["tmp_name"], $target_file)) 
                                                 {
                    	
                                                   // echo "The file ". basename( $_FILES["file_a"]["name"]). " has been uploaded.";
             		                                 $this->session->set_flashdata("success", "The file ". basename( $_FILES["file_a"]["name"]). " has been uploaded....");
                                                   $this->load->view('tender/step6',$data);
                                                   return;
                                                  }
                                                else 
                                                 {
                                                   // echo "Sorry, there was an error uploading your file.";
                                                   $this->session->set_flashdata("success", "Sorry, there was an error uploading your file.");
          			                                 $this->load->view('tender/step5',$data);
           				                              return;
                                                  }
                                           }
}
 
 
 
 

public function tenderbiddoc() 

{
	
      $this->load->view('tender/step6');

}


public function tenderbid() 

{
					$tid=$_POST['tid']; // row id     
               $data['tid']=$tid;
               if(isset($_POST['submit']))
               //echo $tid;
               //die();
               {
                 	
              
                $this->form_validation->set_rules('b1',' ','required');
                $this->form_validation->set_rules('n1','Name Field 1','required');
                $this->form_validation->set_rules('d1','Designation Field 1 ','required');
                $this->form_validation->set_rules('e1','Email Field 1 ','required');
                $this->form_validation->set_rules('s1',' ','required');
                $this->form_validation->set_rules('f1','File Name 1','required');
                $this->form_validation->set_rules('de1','Desp. of file 1 ','required');
                $this->form_validation->set_rules('ty1','Type of file 1 ','required');
                $this->form_validation->set_rules('si1','Size of file 1 ','required');
                
                $this->form_validation->set_rules('b2',' ','required');
                $this->form_validation->set_rules('n2','Name Field 2','required');
                $this->form_validation->set_rules('d2','Designation Field 2','required');
                $this->form_validation->set_rules('e2','Email Field 2 ','required');
                $this->form_validation->set_rules('s2',' ','required');
                $this->form_validation->set_rules('f2',' ','');
                $this->form_validation->set_rules('de2',' ','');
                $this->form_validation->set_rules('ty2',' ','');
                $this->form_validation->set_rules('si2',' ','');
                
                $this->form_validation->set_rules('b3',' ','required');
                $this->form_validation->set_rules('n3',' ','');
                $this->form_validation->set_rules('d3',' ','');
                $this->form_validation->set_rules('e3',' ','');
                $this->form_validation->set_rules('s3',' ','required');
                $this->form_validation->set_rules('f3',' ','');
                $this->form_validation->set_rules('de3',' ','');
                $this->form_validation->set_rules('ty3',' ','');
                $this->form_validation->set_rules('si3',' ','');
                
                $this->form_validation->set_rules('b4',' ','required');
                $this->form_validation->set_rules('n4',' ','');
                $this->form_validation->set_rules('d4',' ','');
                $this->form_validation->set_rules('e4',' ','');
                $this->form_validation->set_rules('s4',' ','required');
                $this->form_validation->set_rules('f4',' ','');
                $this->form_validation->set_rules('de4',' ','');
                $this->form_validation->set_rules('ty4',' ','');
                $this->form_validation->set_rules('si4',' ','');
                
                $this->form_validation->set_rules('b5',' ','required');
                $this->form_validation->set_rules('n5',' ','');
                $this->form_validation->set_rules('d5',' ','');
                $this->form_validation->set_rules('e5',' ','');
                $this->form_validation->set_rules('s5',' ','required');
                $this->form_validation->set_rules('f5',' ','');
                $this->form_validation->set_rules('de5',' ','');
                $this->form_validation->set_rules('ty5',' ','');
                $this->form_validation->set_rules('si5',' ','');
                  
                $this->form_validation->set_rules('tp','Tender Prepared ','required');
                  
                $this->form_validation->set_rules('tpd','Designation Of Tender Maker ','required');
                  
                $this->form_validation->set_rules('ta','Tender Approved ','required');
                
                $this->form_validation->set_rules('tad','Designation.','required');
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
                         'tbos_modifierid'=>$this->session->userdata('username'),
                         'tbos_modifierdate'=>date('Y-m-d'),
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
                         'tud_modifierid'=>$this->session->userdata('username'),
                         'tud_modifierdate'=>date('Y-m-d'),
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
                          'tc_approvedbyname'=>$_POST['ta'],
                          'tc_approvedbydesig'=>$_POST['tad'],
                          'tc_creatorid'=>$this->session->userdata('username'),
                          'tc_creationdate'=>date('Y-m-d'),
                          'tc_modifierid'=>$this->session->userdata('username'),
                          'tc_modifierdate'=>date('Y-m-d'),
                                                                                      
                                      );
                	    
                           //$rflag=$this->PICO_model->insertrec('tender_create',$datay);           	
                           $rflag=$this->PICO_model->updaterec('tender_create', $dataz,'tc_id',$tid);
                               	      
                	      
                	     
                       




                $this->load->view('tender/step6',$data);   //success
                return;
                }
                
                $this->load->view('tender/step6',$data); //validation fail case
                return;
}


$this->load->view('tender/step6',$data); //clear button
return;



}



public function tenderapply() 



{












}
   } 
    ?>