<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Intender
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Abhay Singh (abhay831877@gmail.com)
**/
class Intender extends CI_Controller
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
    
public function a_1()
{
            
        $selectfield='po_id,po_ppid,po_tcid' ;
	   
	     $joincond='items.item_pono=purchase_order.po_no';
		  $whdata = array('item_irstatus'=>'');
		  $whorder='';
        $typeofmat['result'] = $this->PICO_model->get_jointbrecord('items',$selectfield,'purchase_order',$joincond,'LEFT',$whdata,$whorder); 
	
           

       
                          
           $this->load->view('intender/intenderlist',$typeofmat);

}


public function receipt()
{
	$po_id=$_POST['id'];
	
		$id=$this->PICO_model->get_listspfic1('purchase_order','po_ppid','po_id',$po_id)->po_ppid;
           
		      $ii=$this->PICO_model->get_listspfic1('purchase_order','po_taid','po_id',$po_id)->po_taid;
            $ta_id=$ii;

             $n=$this->PICO_model->get_listspfic1('tender_apply','ta_vendorid','ta_id',$ta_id)->ta_vendorid;
				 
				 $whdata = array('vendor_id' => $n);
				 $fieldems="vendor_id,vendor_companyname,vendor_address";
				 $whorderems = '';
				 $typeofmat['result'] = $this->PICO_model->get_orderlistspficemore('vendor',$fieldems,$whdata,$whorderems); 
				 
				 $typeofmat['material']=  $this->PICO_model->get_list('material_type');
				 $typeofmat['dept']= $this->common_model->get_list('Department');
   
             $k=$this->PICO_model->get_listspfic1('tender_apply','ta_tcid','ta_id',$ta_id)->ta_tcid;
				 $f=$this->PICO_model->get_listspfic1('tender_create','tc_refno','tc_id',$k)->tc_refno;
				 $whdata = array('tc_id' => $k);
				 $fieldems="tc_id,tc_refno,tc_workitemtitle,tc_workdesc,tc_quantity ";
				 $whorderems = '';
				 $typeofmat['tcresult'] = $this->PICO_model->get_orderlistspficemore('tender_create',$fieldems,$whdata,$whorderems);
           
             $whdata = array('ta_id' => $ta_id);
				 $fieldems="ta_id,ta_baseprice,ta_gsttax,ta_totalprice,ta_warranty,ta_guarantee,ta_payment,ta_delivery";
				 $whorderems = '';
				 $typeofmat['taresult'] = $this->PICO_model->get_orderlistspficemore('tender_apply',$fieldems,$whdata,$whorderems);
				 
				 $whdata = array('pp_id' => $id);
				 $fieldems="pp_budgethead,pp_budgetdept,pp_budgetprojno,pp_budgetamt,pp_deptindentno,pp_indenterid,pp_deptid,pp_indentdate,pp_materialtypeid,pp_indentername,pp_id";
				 $whorderems = '';
				 $typeofmat['proposal'] = $this->PICO_model->get_orderlistspficemore('purchase_proposal',$fieldems,$whdata,$whorderems);
				 
				  $typeofmat['from']=$this->PICO_model->get_listspfic1('purchase_proposal','pp_deliveryperiodfrom','pp_id',$id)->pp_deliveryperiodfrom;
				  $typeofmat['to']=$this->PICO_model->get_listspfic1('purchase_proposal','pp_deliveryperiodto','pp_id',$id)->pp_deliveryperiodto;
				
           
            $entryid=$this->PICO_model->get_listspfic1('l1_details','ld_id','ld_tcid',$k)->ld_id;            
            $typeofmat['ld_id']=$entryid;
            
    
	         
            $this->load->view('intender/intender_receipt',$typeofmat);
	
}


  public function receipt_entry()
        {
            if(isset($_POST['press'])) 
            {
            $this->form_validation->set_rules('ie','Reason For Rejection','trim|xss_clean|required');
            $this->form_validation->set_rules('if','Replacement field','trim|xss_clean|required');
            $this->form_validation->set_rules('ic','Approve quantity','required');
            $this->form_validation->set_rules('id','Reject quantity','required');
            $this->form_validation->set_rules('ig','remark','trim|xss_clean|required');
                
                if($this->form_validation->run()==TRUE)
             
             {
              
                 $y=date('Y');
                 $m=date('m');
                 if($m>3) { $y1=$y-1;
                            $fy=$y.'-'.$y1;
                          }
                 else     { $y1=$y-1;
                            $fy=$y1.'-'.$y;
                          }          
                
                 $data = array(   
                'inr_itemid'=>$_POST['i1'],
                'inr_pono'=>$_POST['i2'],
                'inr_podate'=>$_POST['i3'],
                'inr_challanno'=>$_POST['i4'],
                'inr_challandate'=>$_POST['i5'],
                'inr_indentno'=>$_POST['i6'],
                'inr_indentname'=>$_POST['i7'],
                'inr_indentdept'=>$_POST['i8'],
                'inr_suppliername'=>$_POST['i9'],
                'inr_itemname'=>$_POST['ia'],
                'inr_itemqty'=>$_POST['ib'],
                'inr_qtyapprov'=>$_POST['ic'],
                'inr_qtyrej'=>$_POST['id'],
                'inr_reasonforrej'=>$_POST['ie'],
                'inr_replacement'=>$_POST['if'],
                'inr_remark'=>$_POST['ig'],
                'inr_irstatus'=>'Completed',
                'inr_nodate'=>date('Y-m-d'),
                'inr_creatorname'=> $this->session->userdata('username'),
                'inr_creatordate'=>date('Y-m-d')
                 );

                
                 $duplicate= $this->PICO_model->isduplicatemore('inspection_report',$data);
                 if(!$duplicate)
                               {              
                               $entryid=$this->PICO_model->insertdata('inspection_report',$data);
                               
                               }
                 else { $entryid=$this->PICO_model->get_listspfic1('inspection_report','inr_id','inr_pono',$_POST['i2'])->inr_id; }       
                 
                 $update_data = array(
                   'inr_no' => 'I/'.$fy.'/'.$entryid,
                                     );
                    $r=$this->PICO_model->updaterec('inspection_report',$update_data,'inr_id', $entryid);
                 $uupdate_data = array(
                   'item_irstatus' => 'Completed',
                                     );                    
                    $w=$this->PICO_model->updaterec('items',$uupdate_data,'item_id', $_POST['i1']);
                    $this->logger->write_logmessage("insert","Add Inspection Report Setting", "Inspection Report added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Inspection Report Setting", "Inspection Report Description added  successfully...");
                    $this->session->set_flashdata("success", "Inspection Report added successfully...");
                    redirect('intender/a_1');
            
                
                
            }
            
          }
          
     
    }


public function inspection_report_display() 
       {
	   	
	     $selectfield='*';
	     
	     $joincond='tender_bid_openers_selection.tbos_tcid=tender_create.tc_id';

        $data['result'] = $this->PICO_model->get_jointbrecord('tender_create',$selectfield,'tender_bid_openers_selection',$joincond,'LEFT',''); //,$whdata   get_list('tender_create');
        $data['proposal'] = $this->PICO_model->get_orderlistspficemore('inspection_report',$selectfield,'','');
				
        $this->logger->write_logmessage("view"," View inspection report", "Report  details...");
        $this->logger->write_dblogmessage("view"," View inspection report", "Report  details...");
    
       $this->load->view('intender/inspection_display',$data);
       }

public function gemview()
{
  $typeofmat['material']=  $this->PICO_model->get_list('material_type');
  $typeofmat['dept']= $this->common_model->get_list('Department');
  $typeofmat['ven']=  $this->PICO_model->get_list('vendor');
  $this->load->view('intender/gem_proposal',$typeofmat);

}   



public function proposal_gem_entry() 
{
            if(isset($_POST['press'])) {

				
				
				
                $this->form_validation->set_rules('pp_ddate','ddate ','required');
                $this->form_validation->set_rules('pp_deptindentno',' deptindentno','trim|xss_clean|required');
                $this->form_validation->set_rules('pp_deptid','deptid ','trim|xss_clean|required');
               $this->form_validation->set_rules('pp_indentername','indentername ','trim|xss_clean|required');
               $this->form_validation->set_rules('pp_indenteremail',' indenteremail','trim|xss_clean|required');
                $this->form_validation->set_rules('pp_indentdate',' indentdate','required');
                $this->form_validation->set_rules('pp_indenterid','indenterid ','trim|xss_clean|required');
                $this->form_validation->set_rules('pp_typeofmaterial','type of material','required');
                
              $this->form_validation->set_rules('pp_total','Total ','required');
              $this->form_validation->set_rules('pp_budgetprojno[]','budgetprojno','required');
               $this->form_validation->set_rules('pp_budgethead[]',' budgethead','required');
               $this->form_validation->set_rules('pp_budgetamount[]',' budgetamount','required');
                $this->form_validation->set_rules('pp_Date',' period','required');
                $this->form_validation->set_rules('pp_DateFrom',' DateFrom','required');
                $this->form_validation->set_rules('pp_DateTo','DateTo ','required');
                $this->form_validation->set_rules('pp_warranty',' warranty','trim|xss_clean|required');
                $this->form_validation->set_rules('pp_guarantee','guarantee ','trim|xss_clean|required');
                $this->form_validation->set_rules('pp_payterm','payterm','required');
                $this->form_validation->set_rules('pp_gemrefno','proposal ref no ','required');
                  
              
     //*/
               if($this->form_validation->run()==TRUE)
               {
               	
				   $data=array(
				   'pp_tcid'=>'NIL',	
					'pp_taid'=>'NIL',  
				   'pp_purchasefrom'=>'Gem',
				   'pp_gemrefno'=>$_POST['pp_gemrefno'],	
					'pp_tenrefno'=>'NIL',	
					'pp_dddate'=>$_POST['pp_ddate'],	
					'pp_deptindentno'=>$_POST['pp_deptindentno'],	
					'pp_deptid'=>$_POST['pp_deptid'],	
					'pp_indentername'=>$_POST['pp_indentername'],	
					'pp_indenteremail'=>$_POST['pp_indenteremail'],	
					'pp_indentdate'=>$_POST['pp_indentdate'],	
					'pp_indenterid'=>$_POST['pp_indenterid'],	
					'pp_materialtypeid'=>$_POST['pp_typeofmaterial'],	
				
				   'pp_vendorid'=>$_POST['pp_vid'],	
					'pp_deliveryperiod'=>$_POST['pp_Date'],	
					'pp_deliveryperiodfrom'=>$_POST['pp_DateFrom'],	
					'pp_deliveryperiodto'=>$_POST['pp_DateTo'],	
					'pp_warranty'=>$_POST['pp_warranty'],	
					'pp_guarantee'=>$_POST['pp_guarantee'],	
					'pp_payterm'=>$_POST['pp_payterm'],
						
					'pp_hodapproved'=>$this->session->userdata('username'),
					'pp_hodapproveddate'=>date('Y-m-d'),
					
					//'pp_budgetcomment'=>$_POST['pp_'],	
					//'pp_budgetapproved'=>$_POST['pp_'],	
					//'pp_budgetapprovedby'=>$_POST['pp_'],	
					//'pp_budgetapproveddate'=>$_POST['pp_'],	
					//'pp_auditobservation'=>$_POST['pp_'],	
					//'pp_auditapproved'=>$_POST['pp_'],	
					//'pp_auditapprovedby'=>$_POST['pp_'],	
					//'pp_auditapproveddate'=>$_POST['pp_'],	
				   //'pp_expsanctionstatus'=>$_POST['pp_'],	
					//'pp_expsanctionby'=>$_POST['pp_'],	
					//'pp_expsanctiondate'=>$_POST['pp_'],	
					//'pp_updatedate'=>$_POST['pp_'],	
					//'pp_updateby'=>$_POST['pp_'],   ....done
					
					//'pp_dordstatus'=>$_POST['pp_'],	
					//'pp_dorddate'=>$_POST['pp_'],	
					//'pp_ddstatus'=>$_POST['pp_'],	
					//'pp_dstatus'=>$_POST['pp_'],	
					//'pp_ddate'=>$_POST['pp_'],	
					//'pp_reqcreationdate'=>$_POST['pp_'],	
					//'pp_reqcreationby'=>$_POST['pp_'],	...left
					
					   );
				   
				   $entryid=$this->PICO_model->insertdata('purchase_proposal', $data);
				  
              
                
                if(!$entryid){ $rflag=false;  }
                 else{ $rflag=true; 
                     }
                
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add Tender basic details", "Tender basic details is not added ".$bd_trn);
                    $this->logger->write_dblogmessage("insert","Trying to add Tender basic details", "Tender basic details is not added ".$bd_trn);
                    $this->session->set_flashdata('err_message','Error in adding  details setting - '  , 'error');
                    redirect('intender/gemview');
                }
                else{ //single data uploaded... now multiple data row
                $flag=false;
                /************************************************/
                $tot=0;
                $desc = $this->input->post('desc', TRUE);
                $stock = $this->input->post('stock', TRUE);
                $quantity = $this->input->post('quantity', TRUE);
                $price = $this->input->post('price', TRUE);
                $gst = $this->input->post('gst', TRUE);
                $total = $this->input->post('total', TRUE);
                	   
                //$desc =$_POST['desc'];
                // $stock=$_POST['stock'];
                //$quantity=$_POST['quantity'];
                //  $price=$_POST['price'];
                //  $gst =$_POST['gst'];
                //  $total=$_POST['total'];
                
                foreach($desc as $a => $b){
          
                    $data = array(
                        'rid_ppid'=>$entryid,
                        'rid_itemdes'   =>$desc[$a],
                        'rid_itemstock' =>$stock[$a],
                        'rid_itemqtyreq'=>$quantity[$a],
                        'rid_itemunitp' =>$price[$a],
                        'rid_gst'       =>$gst[$a],
                        'rid_itemgstapply' =>$gst[$a],
                        'rid_itemtotcost'  =>$total[$a],
                             
                                  );
                    $tot=$tot+$total[$a];
                    if(!empty($desc[$a])){
                        $dupcheck = array(
                        'rid_ppid' =>$entryid,
                        'rid_itemdes' =>$desc[$a],
                                          ); 
                        $stddup = $this->PICO_model->isduplicatemore('required_item_details',$dupcheck);
                        if(!$stddup){
                          $flag=$this->PICO_model->insertdata('required_item_details', $data);
                                    }    
                    } }
                    
                   $update_data= array('pp_itemtotcost'=>$tot,);
 	                $roledflag=$this->PICO_model->updaterec('purchase_proposal',$update_data,'pp_id',$entryid); 
                    
                   $budget=0; 
                   $budgetprojno=$_POST['pp_budgetprojno'];
                   $budgethead  =$_POST['pp_budgethead'];
                   $budgetamount=$_POST['pp_budgetamount'];
                
                   foreach($budgetprojno as $a => $b){
          
                   $data = array(
                        'bd_ppid'=>$entryid,
                        'bd_budgetprojno'   =>$budgetprojno[$a],
                        'bd_budgethead' =>$budgethead[$a],
                        'bd_budgetamount'=>$budgetamount[$a],
                        'bd_budgetdept' =>$budgetprojno[$a],
                                 );
                    $budget=$budget+$budgetamount[$a];
                    if(!empty($budgetprojno[$a])){
                        $dupcheck = array(
                        'bd_ppid' =>$entryid,
                        'bd_budgetprojno' =>$budgetprojno[$a],
                                          ); 
                        $stddup = $this->PICO_model->isduplicatemore('budget_details',$dupcheck);
                        if(!$stddup){
                          $flag=$this->PICO_model->insertdata('budget_details', $data);
                                    }    
                    } }
                
                    $update_data= array('pp_budgetapproved'=>$budget,);
 	                 $roledflag=$this->PICO_model->updaterec('purchase_proposal',$update_data,'pp_id',$entryid); 
                    
                    
                    $this->logger->write_logmessage("insert","Add Proposals details Setting","Proposals details".$entryid." added  successfully...");
                    $this->logger->write_dblogmessage("insert","Add Proposals details Setting","Proposals details".$entryid."added  successfully...");
                    $this->session->set_flashdata("success", "Proposals details added successfully...");
                    redirect('tender/purchaseproposals');
                    return;  }echo 'not added details try again!!!!';
                			     } echo 'validation failed';
                    return;
                    }
                 
                    echo 'try again with ALL field filled';
}

 
 
public function gem_proposals()
{
$whd4= array('pp_purchasefrom'=>'Gem');
$whorder='pp_id desc';
$data['result']=$this->PICO_model->get_orderlistspficemore('purchase_proposal','*',$whd4,$whorder);		
$this->load->view('intender/gem_proposals',$data);

}
 public function gem_budget_unit($id)
 {       
          $data['data']=$id;
     
          $whdata = array('pp_id' => $id);
		    $fieldems="pp_itemtotcost,pp_budgetapproved";
		    $whorderems = '';
			 $data['proposal']=$this->PICO_model->get_orderlistspficemore('purchase_proposal',$fieldems,$whdata,$whorderems);
    
    $this->load->view('intender/gem_budget_unit',$data);
 
 }
 
 public function unit_upload()
 {
 	          if(isset($_POST['press'])) {
 	          $pp_id=$_POST['pp_id'];
 	      
 	       
 	      	$update_data= array(	'pp_budgetcomment'=>$_POST['bu_comment'],	
					'pp_budgetapprovedby'=>$this->session->userdata('username'),
					'pp_budgetapproveddate'=>date('Y-m-d'),
					'pp_updateby'=>$this->session->userdata('username'),
					'pp_updatedate'=>date('Y-m-d'),
					  
					);
 	         
 	         	$roledflag=$this->PICO_model->updaterec('purchase_proposal',$update_data,'pp_id',$pp_id);
           
            $total=$_POST['bu_committed'];                   
            $a=0;
            $whdata = array('bd_ppid' => $pp_id);
		      $fieldems="bd_budgetamount";
		      $whorderems = '';
			   $budget=$this->PICO_model->get_orderlistspficemore('budget_details',$fieldems,$whdata,$whorderems);
            sort($budget); 
            $arrlength = count($budget); 
             for($x = 0; $x < $arrlength; $x++) 
             { 
             $a=$budget[$x]-$total; 
             
             if($a==0){
             	        break;
                      }
             } 
           
            if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->session->set_flashdata('err_message','Error Approving proposal- ' . $logmessage . '.', 'error');
                redirect('intender/gem_proposals');
            }
            else{
                $this->logger->write_logmessage("update","Edit  Setting", "Edit Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit  Setting", "Edit  Setting details. $logmessage ");
                $this->session->set_flashdata('success','Updated and Approved..');
                redirect('intender/gem_proposals');
              
                }  
           	
            return;
      }
       redirect('intender/gem_proposals');
 }
public function gem_expenditure($id)
 {
          $suname=$this->session->userdata['username'];
	       if((strcasecmp($suname,"admin"))==0)	
           {
           	$pp_id=$id;
         
           	$status='Approved';
           	$update_data= array(
              'pp_expsanctionstatus'=>$status,         	 
           	  'pp_expsanctionby'=>$this->session->userdata('username'),
              'pp_expsanctiondate'=>date('Y-m-d'),
              'pp_updateby'=>$this->session->userdata('username'),
					'pp_updatedate'=>date('Y-m-d'),
           	 );
           
            	$roledflag=$this->PICO_model->updaterec('purchase_proposal',$update_data,'pp_id',$pp_id);
            if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->session->set_flashdata('err_message','Error Approving proposal- ' . $logmessage . '.', 'error');
                redirect('intender/intender_proposals');
            }
            else{
                $this->logger->write_logmessage("update","Edit  Setting", "Edit Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit  Setting", "Edit  Setting details. $logmessage ");
                $this->session->set_flashdata('success','Updated and Approved..');
                redirect('intender/gem_proposals');
              
                }  
           	
            return;
           }
         $this->session->set_flashdata("err_message", 'Proposal can\'t be approved by you.....' );
         redirect('intender/gem_proposals');
 } 
 
 public function gem_audit($id)
 {
 	
 	          $data['data']=$id;
 	          
 	          $this->load->view('intender/gem_audit',$data); 
 	           
 	         
 	         
      
       //  redirect('intender/gem_proposals');
 } 
 
 public function gem_audit_upload()
  {
 	
             if(isset($_POST['press'])) {
  	          $pp_id=$_POST['pp_id'];
 	      
 	       
 	      	$update_data= array(
 	      		'pp_auditobservation'=>$_POST['bu_comment'],	
					'pp_auditapproved'=>'Approved',	
					'pp_auditapprovedby'=>$this->session->userdata('username'),
					'pp_auditapproveddate'=>date('Y-m-d'),
					'pp_updateby'=>$this->session->userdata('username'),
					'pp_updatedate'=>date('Y-m-d'),
					);
 	         
 	         	$roledflag=$this->PICO_model->updaterec('purchase_proposal',$update_data,'pp_id',$pp_id);
            if(!$roledflag)
            {
                $this->logger->write_logmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->logger->write_dblogmessage("error","Edit  Setting error", "Edit Approve details. $logmessage ");
                $this->session->set_flashdata('err_message','Error Approving proposal- ' . $logmessage . '.', 'error');
                redirect('intender/gem_proposals');
            }
            else{
                $this->logger->write_logmessage("update","Edit  Setting", "Edit Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit  Setting", "Edit  Setting details. $logmessage ");
                $this->session->set_flashdata('success','Updated and Approved..');
                redirect('intender/gem_proposals');
              
                }  
           	
            return;
      }
       redirect('intender/gem_proposals');
 }
 
 
public function gem_proposal_view($i)
{	
			$id=$i;
		   $this->logger->write_logmessage("view"," View specific  proposal id=$id", "proposal  detail...");
         $this->logger->write_dblogmessage("view"," View specific proposal id=$id", "proposal  detail...");
       

             $n=$this->PICO_model->get_listspfic1('purchase_proposal','pp_vendorid','pp_id',$id)->pp_vendorid;
				 
				 $whdata = array('vendor_id' => $n);
				 $fieldems="vendor_id,vendor_companyname,vendor_address";
				 $whorderems = '';
				 $typeofmat['result'] = $this->PICO_model->get_orderlistspficemore('vendor',$fieldems,$whdata,$whorderems); 
				 
				 //$typeofmat['material']=  $this->PICO_model->get_list('material_type');
				 //$typeofmat['dept']= $this->common_model->get_list('Department');
   
            
           
           
				 
				 $whdata = array('pp_id' => $id);
				 $fieldems="pp_budgethead,pp_budgetdept,pp_budgetprojno,pp_budgetamt,pp_deptindentno,pp_indenterid,pp_deptid,pp_indentdate,pp_materialtypeid,pp_indenteremail,pp_indentername,pp_deliveryperiod,pp_warranty,pp_payterm,
				  pp_guarantee,pp_gemrefno,pp_budgetcomment,pp_budgetapproved,pp_budgetapprovedby,pp_budgetapproveddate,pp_auditobservation,pp_auditapproved,pp_auditapprovedby,pp_auditapproveddate,pp_expsanctionstatus,
				  pp_expsanctionby,pp_expsanctiondate,pp_itemtotcost";
				 $whorderems = '';
				 $typeofmat['proposal'] = $this->PICO_model->get_orderlistspficemore('purchase_proposal',$fieldems,$whdata,$whorderems);
				 
				 $whdata = array('bd_ppid' => $id);
				 $fieldems="bd_budgethead,bd_budgetdept,bd_budgetprojno,bd_budgetamount";
				 $whorderems = '';
				 $typeofmat['budget'] = $this->PICO_model->get_orderlistspficemore('budget_details',$fieldems,$whdata,$whorderems);
				 
				 $whdata = array('rid_ppid' => $id);
				 $fieldems="rid_itemdes,rid_itemstock,rid_itemqtyreq,rid_itemtotcost,rid_itemgstapply,rid_itemunitp";
				 $whorderems = '';
				 $typeofmat['required'] = $this->PICO_model->get_orderlistspficemore('required_item_details',$fieldems,$whdata,$whorderems);
				 
				 $typeofmat['from']=$this->PICO_model->get_listspfic1('purchase_proposal','pp_deliveryperiodfrom','pp_id',$id)->pp_deliveryperiodfrom;
				 $typeofmat['to']=$this->PICO_model->get_listspfic1('purchase_proposal','pp_deliveryperiodto','pp_id',$id)->pp_deliveryperiodto;
				
           
           
            
     
     
     
        $this->load->view('intender/gem_view',$typeofmat);
}
 
 }