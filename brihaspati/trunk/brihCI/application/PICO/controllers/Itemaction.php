<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Itemaction
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 */

class Itemaction extends CI_Controller
{
    function __construct() {
	parent::__construct();
	
        $this->load->model('login_model','lgnmodel'); 
	$this->load->model('common_model','commodel'); 
        $this->load->model('PICO_model','picomodel');  
        $this->load->model('SIS_model','sismodel'); 
	$this->load->model("Mailsend_model","mailmodel");

        if(empty($this->session->userdata('id_user'))) {
        	$this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }

    /********************************** Item Details ********************************************************************************************/

    public function openitemtype(){
	    $sel='mt_id,mt_name';
	    $data['mtlist']=$this->picomodel->get_orderlistspficemore('material_type',$sel,'','');
            $this->load->view('itemaction/itemreceiveform',$data);
    }

    /**** This function Insert Item type *********************/

    public function insertitemtype(){
    	if(isset($_POST['item_type'])){
        	//$this->form_validation->set_rules('item_mtid','Material ID','trim|xss_clean|required|callback_isMaterialIdExist');
        	$this->form_validation->set_rules('mtid','Material ID','trim|xss_clean|required');
        	$this->form_validation->set_rules('item_pono','Item Purchase Order Number','trim|xss_clean|required');
        	$this->form_validation->set_rules('item_podate','Item Purchase Order Date','trim|xss_clean|required');
        	$this->form_validation->set_rules('item_challan','Item Challan Number','trim|xss_clean|required');
        	$this->form_validation->set_rules('item_challandate','Item Challan Date','trim|xss_clean|required');
            	$this->form_validation->set_rules('item_name','Item Name','trim|xss_clean|required');
            	$this->form_validation->set_rules('item_price','Item Price','trim|xss_clean|required');
            	$this->form_validation->set_rules('item_stock','Item Quantity','trim|xss_clean|required|alpha_numeric_spaces');
            	$this->form_validation->set_rules('item_desc','Item Description','trim|xss_clean');
            	$this->form_validation->set_rules('item_transport','Item Transport By','trim|xss_clean');
            	$this->form_validation->set_rules('item_recevied','Item Received By','trim|xss_clean|required');
        	if($this->form_validation->run() ==TRUE){
                	$data = array(
                    		'item_mtid'=>$this->input->post('mtid'),
                    		'item_name'=>strtolower($this->input->post('item_name')),
                    		'item_price'=>$this->input->post('item_price'),
				'item_qty'=>$this->input->post('item_stock'),
				'item_balqty'=>$this->input->post('item_stock'),
				'item_pono'=>$this->input->post('item_pono'),
				'item_podate'=>$this->input->post('item_podate'),
				'item_challanno'=>$this->input->post('item_challan'),
				'item_challandate'=>$this->input->post('item_challandate'),
				'item_desc'=>$this->input->post('item_desc'),
				'item_transport'=>$this->input->post('item_transport'),
				'item_receivername'=>$this->input->post('item_recevied'),
				'item_creatorname'=>$this->session->userdata('username'),
				'item_creatordate'=>date('Y-m-d'),
				'item_modifiername'=>$this->session->userdata('username'),
				'item_modifierdate'=>date('Y-m-d'),
                	);

                	$rflag= $this->picomodel->insertrec('items',$data);
                	if(!$rflag){
                    		$this->logger->write_logmessage("insert","Trying to Add New Item", "New Item is not added ".$_POST['item_name']);
                    		$this->session->set_flashdata('err_message','Error in adding Item details...'  , 'error');
                    		redirect('itemaction/openitemtype');
                	}
			else{

				//this entry must go to stock
				$smtid=$this->input->post('mtid');
				$stnme=strtolower($this->input->post('item_name'));
				$nqty=$this->input->post('item_stock');
				$pono=$this->input->post('item_pono');
				// get the item id from item table
				// $gdata=array('item_mtid'=>$smtid,'item_name'=>$stnme, 'item_qty'=>$nqty,'item_pono'=>$pono);
				// $resid=$this->picomodel->get_orderlistspficemore('items','item_id',$gdata,'');
				// foreach($resid as $row){
				// 	$itemid=$row->item_id;
				// }
				//put the entry in inspection report
				//take data form items, purchase order and purpase proposal			
				//$datair=array(
				//	'inr_itemid'=>$itemid,
				//	'inr_no'=>,
				//	'inr_nodate'=>,
				//	'inr_pono'=>$pono,
				//	'inr_podate'=>$this->input->post('item_podate'),
				//	'inr_challanno'=>$this->input->post('item_challan'),
				//	'inr_challandate'=>$this->input->post('item_challandate'),
				//	'inr_indentno'=>,
				//	'inr_indentname'=>,
				//	'inr_indentdept'=>,
				//	'inr_suppliername'=>,
				//	'inr_itemname'=>$stnme,
				//	'inr_itemqty'=>$nqty,
				//);	
				//
				//inr_id	inr_itemid	inr_no	inr_pono	inr_podate	inr_challanno	inr_challandate	
				//inr_nodate	inr_indentno	inr_indentname	inr_indentdept	inr_suppliername	inr_itemname	inr_itemqty

				// get the max row in item index
	//				$norw=0;
				// $sdata=array('itemmtid'=>$smtid);
				// $norw = $this->picomodel->getnoofrows('item_index',$sdata)
				// put the entry in item index
	//			foreach ($i=1;$i<=$nqty;$i++){
	//				$norw++;
	//				$itmno=$smtid.$norw;
				//	$dataindex=array(
				//	'itemmtid=>$smtid,
	//				'itemid'=>$itemid,
	//				'itemno'=>$itmno,
	//				'itemstore'=>'Store',
	//				);
	//				$iindexflag= $this->picomodel->insertrec('item_index',$dataindex);
	//			}
				$dupsdata=array('stock_mtid'=>$smtid,'stock_name'=>$stnme);
				$flag=$this->picomodel->isduplicatemore("stock_items",$dupsdata);
				//if exist then go to stock archive
				if($flag){
					$oqty=0;
					$res=$this->picomodel->get_orderlistspficemore('stock_items','stock_id,stock_qty',$dupsdata,'');
					foreach($res as $row){
						$oqty=$row->stock_qty;
						$stid=$row->stock_id;
					}
					$fqty=$oqty + $nqty;
					$usdata=array('stock_qty'=>$fqty);
					$uflag=$this->picomodel->updaterec('stock_items', $usdata,'stock_id', $stid);
					//add log in db
					$siadata=array(
						'stocka_stockid'=>$stid,
						'stocka_mtid'=>$smtid,
						'stocka_name'=>$stnme,
						'stocka_qty'=>$oqty,
						'stocka_desc'=>$this->input->post('item_desc'),
						'stocka_upstatus'=>"Added",
						'stocka_creatorname'=>$this->session->userdata('username'),
						'stocka_creatordate'=>date('Y-m-d'),
					);
					$siaflag=$this->picomodel->insertrec('stock_items_archive',$siadata);
					// add log in db
				}else{
					$usfdata = array(
	                                'stock_mtid'=>$smtid,
        	                        'stock_name'=>$stnme,
                	                'stock_qty'=>$nqty,
                        	        'stock_desc'=>$this->input->post('item_desc'),
	                        	);
					$usflag=$this->picomodel->insertrec('stock_items',$usfdata);
					$res=$this->picomodel->get_orderlistspficemore('stock_items','stock_id,stock_qty',$dupsdata,'');
                                        foreach($res as $row){
                                                $oqty=$row->stock_qty;
                                                $stid=$row->stock_id;
					}
					$siadata=array(
                                                'stocka_stockid'=>$stid,
                                                'stocka_mtid'=>$smtid,
                                                'stocka_name'=>$stnme,
                                                'stocka_qty'=>$oqty,
                                                'stocka_desc'=>$this->input->post('item_desc'),
                                                'stocka_upstatus'=>"Added",
                                                'stocka_creatorname'=>$this->session->userdata('username'),
                                                'stocka_creatordate'=>date('Y-m-d'),
                                        );
                                        $siaflag=$this->picomodel->insertrec('stock_items_archive',$siadata);

				}
                    		$this->logger->write_logmessage("insert","Added new item", "New Item ".$_POST['item_name']." added  successfully...");
                    		$this->logger->write_dblogmessage("insert","Added new item", "New Item".$_POST['item_name']." added  successfully...");
                    		$this->session->set_flashdata("success", "Item added successfully...");
                    		redirect("itemaction/itemtypedetails"); 
                	}
            	}//end validation
        }//end isset
        $this->load->view('itemaction/itemreceiveform');
    }

    /** This function check for duplicate Material ID entry
     * @return type
    */
/*    public function isMaterialIdExist($item_mtid) {

        $is_exist = $this->picomodel->isduplicate('items','item_mtid',$item_mtid);
       if ($is_exist)
        {
            $this->form_validation->set_message('isMaterialIdExist', 'This Material ID already exist.');

            return false;
        }
        else 
        {
            return true;
        }
    }
 */
    /** This function is used to modify Item detail entries
     * @return type
    */
    public function itemtypedetails(){
	$sel='item_id,item_mtid,item_name,item_price,item_qty,item_desc,item_pono,item_challanno,item_challandate,item_transport,item_receivername';
	$whorder='item_mtid asc,item_pono asc';
	$data['result'] = $this->picomodel->get_orderlistspficemore('items',$sel,'',$whorder);
        $this->logger->write_logmessage("view"," View Item List setting", "Item List setting details...");
	$this->logger->write_dblogmessage("view"," View Item List setting", "Item List setting details...");
        $this->load->view('itemaction/displayitemdetails',$data);
    }

    /*This function modify the Item Details*/
    public function edititemdetails($id) {

        $this->db4->from('items')->where('item_id',$id);
        $eset_data_q = $this->db4->get();
        if ($eset_data_q->num_rows() < 1)
        {
            redirect('picosetup/openitemtype');
        }
        $editeset_data = $eset_data_q->row();
        /* Form fields */

                $data['item_mtid'] = array(
                'name' => 'item_mtid',
                'id' => 'item_mtid',
                'size' => '40',
                'value' => $editeset_data->item_mtid,
                );

                $data['item_name'] = array(
                'name' => 'item_name',
                'id' => 'item_name',
                'size' => '40',
                'value' => $editeset_data->item_name,
                );

                $data['item_price'] = array(
                'name' => 'item_price',
                'id' => 'item_price',
                'size' => '40',
                'value' => $editeset_data->item_price,
                );

                $data['item_stock'] = array(
                'name' => 'item_stock',
                'id' => 'item_stock',
                'size' => '40',
                'value' => $editeset_data->item_stock,
                );
        
        $data['id'] = $id;
        /*Form Validation*/
            $this->form_validation->set_rules('item_mtid','Material ID','trim|xss_clean|required');
            $this->form_validation->set_rules('item_name','Item Name','trim|xss_clean|required');
            $this->form_validation->set_rules('item_price','Item Price','trim|xss_clean|required');
            $this->form_validation->set_rules('item_stock','Item Stock','trim|xss_clean|required|alpha_numeric_spaces');

        /* Re-populating form */
        if ($_POST)
        {
            $data['item_mtid']['value'] = $this->input->post('item_mtid', TRUE);
            $data['item_name']['value'] = $this->input->post('item_name', TRUE);
            $data['item_price']['value'] = $this->input->post('item_price', TRUE);
            $data['item_stock']['value'] = $this->input->post('item_stock', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('setup/edititemdetails',$data);
            return;
        }

        else{

            $data_item_mtid = $this->input->post('item_mtid', TRUE);
            $data_item_name = $this->input->post('item_name', TRUE);
            $data_item_price = $this->input->post('item_price', TRUE);
            $data_item_stock = $this->input->post('item_stock', TRUE);
            $data_eid = $id;

            $logmessage = "";
            if($editeset_data->item_mtid != $data_item_mtid)
                $logmessage = "Material ID" .$editeset_data->item_mtid. " changed by " .$data_item_mtid;

            if($editeset_data->item_name!= $data_item_name)
                $logmessage = "Item Name" .$editeset_data->item_name. " changed by " .$data_item_name;

            if($editeset_data->item_price != $data_item_price)
                $logmessage = "Item Price" .$editeset_data->item_price. " changed by " .$data_item_price;

            if($editeset_data->item_stock != $data_item_stock)
                $logmessage = "Item Stock" .$editeset_data->item_stock. " changed by " .$data_item_stock;

            $update_data = array(
               'item_mtid' => $data_item_mtid,
               'item_name'=> $data_item_name,
               'item_price' => $data_item_price,
               'item_stock' => $data_item_stock,
            );

            $duplicate= $this->PICO_model->isduplicatemore('items',$update_data);
            if(!$duplicate){
		    $roledflag=$this->PICO_model->updaterec('items', $update_data,'item_id', $data_eid);
                if(!$roledflag)
                {
                $this->logger->write_logmessage("error","Edit Item details error", "Edit Item details . $logmessage ");
                $this->logger->write_dblogmessage("error","Edit Item details error", "Edit Item details. $logmessage ");
                $this->session->set_flashdata('err_message','Error updating Item details' . $logmessage . '.', 'error');
                $this->load->view('setup/displayitemdetails', $data);
                }
                else{
		    //also update in stock item 
		    //also add in stock item archives
                $this->logger->write_logmessage("update","Edit Item details  Setting", "Edit Item details  Setting details. $logmessage ");
                $this->logger->write_dblogmessage("update","Edit Item details  Setting", "Edit Item details  Setting details. $logmessage ");
                $this->session->set_flashdata('success','Selected Item details updated successfully..');
                redirect('picosetup/itemtypedetails');
                }
            }
            else{
                $this->logger->write_logmessage("update", "duplicate   Item details  record " . "[item_id:" .$id. "] not updated successfully.. ");
                $this->logger->write_dblogmessage("update", "duplicate Item details  record" ." [item_id:" .$id. "] not updated successfully.. " );
                $this->session->set_flashdata("success", "Selected Item details already exists..." );
                redirect('picosetup/itemtypedetails');

            }
            
        }

    }

     /* This function is used to delete item record */
    public function deleteitemtype($id) { 
	$whdata = array ('item_id'=> $id);
        $mt_data=$this->picomodel->get_orderlistspficemore('items','item_id,item_mtid,item_name,item_qty',$whdata,'');
        if (empty($mt_data))
        {
		redirect('itemaction/itemtypedetails');
		return;
	}
	$sname=''; $smtid=''; $nqty=0;
	foreach($mt_data as $row){
		$stnme=strtolower($row->item_name);
		$smtid=$row->item_mtid;
		$nqty=$row->item_qty;
	}
        $fmdflag=$this->picomodel->deleterow('items','item_id', $id);
        if(!$fmdflag)
        {
                $this->logger->write_message("error", "Error  in Deleting Selected Item " ."[item_id:" . $id . "]");
                    $this->logger->write_dbmessage("error", "Error  in Deleting Selected Item "." [item_id:" . $id . "]");
                    $this->session->set_flashdata('err_message', 'Error in Deleting Selected Item - ', 'error');
                    redirect('itemaction/itemtypedetails');
                //return;
              }
              else{
		      //also update in stock item 
		    //also add in stock item archives
		      $oqty=0;
		      $dupsdata=array('stock_mtid'=>$smtid,'stock_name'=>$stnme);
                      $res=$this->picomodel->get_orderlistspficemore('stock_items','stock_id,stock_qty',$dupsdata,'');
                      foreach($res as $row){
                      	$oqty=$row->stock_qty;
                        $stid=$row->stock_id;
                      }
                      $fqty=$oqty - $nqty;
                      $usdata=array('stock_qty'=>$fqty);
                      $uflag=$this->picomodel->updaterec('stock_items', $usdata,'stock_id', $stid);
                      //add log in db
                      $siadata=array(
                      	'stocka_stockid'=>$stid,
                          'stocka_mtid'=>$smtid,
                          'stocka_name'=>$stnme,
                          'stocka_qty'=>$oqty,
                          'stocka_desc'=>$this->input->post('item_desc'),
                          'stocka_upstatus'=>"Deleted",
                          'stocka_creatorname'=>$this->session->userdata('username'),
                          'stocka_creatordate'=>date('Y-m-d'),
                    );
                    $siaflag=$this->picomodel->insertrec('stock_items_archive',$siadata);
                    // add log in db

                    $this->logger->write_logmessage("delete", "Deleted   Item record " . "[item_id:" . $id . "] deleted successfully. By ".$this->session->userdata('username') );
                    $this->logger->write_dblogmessage("delete", "Deleted Item record" ." [item_id:" . $id . "] deleted successfully. By ".$this->session->userdata('username') );
                    $this->session->set_flashdata("success", "Specific Item deleted successfully. By ".$this->session->userdata('username') );
                    redirect('itemaction/itemtypedetails');
            }
          $this->load->view('itemaction/displayitemdetails',$data);
    }

//item issue
    public function issueitem(){
	    $sel='mt_id,mt_name';
	    $data['mtlist']=$this->picomodel->get_orderlistspficemore('material_type',$sel,'','');
	    $data['pflist']=$this->sismodel->get_orderlistspficemore('employee_master','emp_id,emp_name,emp_code','','emp_name asc');
            $this->load->view('itemaction/itemissueform',$data);
    }

    /**** This function Insert Item type *********************/

    public function insertitemissued(){
    	if(isset($_POST['item_issue'])){
        	//$this->form_validation->set_rules('item_mtid','Material ID','trim|xss_clean|required|callback_isMaterialIdExist');
        	$this->form_validation->set_rules('mtid','Material ID','trim|xss_clean|required');
        	$this->form_validation->set_rules('item_name','Item Name','trim|xss_clean|required');
        	$this->form_validation->set_rules('item_qty','Item Quantity','trim|xss_clean|required');
            	$this->form_validation->set_rules('item_desc','Item Description','trim|xss_clean');
            	$this->form_validation->set_rules('empid','Item Name','trim|xss_clean|required');
            	$this->form_validation->set_rules('deptname','Item Price','trim|xss_clean|required');
            	$this->form_validation->set_rules('item_recevied','Item Received By','trim|xss_clean|required');
		if($this->form_validation->run() ==TRUE){
			$empid=$this->input->post('empid');
			$emppf=$this->sismodel->get_listspfic1('employee_master', 'emp_code', 'emp_id',$empid)->emp_code;
			$empname=$this->sismodel->get_listspfic1('employee_master', 'emp_name', 'emp_id',$empid)->emp_name;
			$itemid=$this->input->post('item_name');
			$itemname=$this->picomodel->get_listspfic1('items', 'item_name', 'item_id',$itemid)->item_name;
			$itembal=$this->picomodel->get_listspfic1('items', 'item_balqty', 'item_id',$itemid)->item_balqty;
			$nqty=$this->input->post('item_qty');
			$fitembal=$itembal - $nqty;

                	$data = array(
                    		'ii_itemid'=>$itemid,
                    		'ii_mtid'=>$this->input->post('mtid'),
                    		'ii_name'=>strtolower($itemname),
				'ii_qty'=>$nqty,
				'ii_desc'=>$this->input->post('item_desc'),
				'ii_staffpfno'=>$emppf,
				'ii_staffname'=>$empname,
				'ii_dept'=>$this->input->post('deptname'),
				'ii_receivername'=>$this->input->post('item_recevied'),
				'ii_creatorname'=>$this->session->userdata('username'),
				'ii_creatordate'=>date('Y-m-d'),
				'ii_modifiername'=>$this->session->userdata('username'),
				'ii_modifierdate'=>date('Y-m-d'),
                	);

                	$rflag= $this->picomodel->insertrec('items_issued',$data);
                	if(!$rflag){
                    		$this->logger->write_logmessage("insert","Trying to Add New Item", "New Item is not added ".$_POST['item_name']);
                    		$this->session->set_flashdata('err_message','Error in adding Item details...'  , 'error');
                    		redirect('itemaction/issueitem');
                	}
			else{
				//update items table
				$upitembal=array('item_balqty'=>$fitembal);
				$uiflag=$this->picomodel->updaterec('items',$upitembal,'item_id',$itemid);
				// add log in db
				//this entry must go to stock
				$smtid=$this->input->post('mtid');
				$stnme=strtolower($itemname);

				$dupsdata=array('sii_mtid'=>$smtid,'sii_name'=>$stnme);
				$flag=$this->picomodel->isduplicatemore("stock_items_issued",$dupsdata);
				//if exist then go to stock archive
				if($flag){
					$oqty=0;
					$res=$this->picomodel->get_orderlistspficemore('stock_items_issued','sii_id,sii_qty',$dupsdata,'');
					foreach($res as $row){
						$oqty=$row->sii_qty;
						$stid=$row->sii_id;
					}
					$fqty=$oqty + $nqty;
					$usdata=array('sii_qty'=>$fqty);
					$uflag=$this->picomodel->updaterec('stock_items_issued', $usdata,'sii_id', $stid);
					//add log in db
					$siadata=array(
						'siia_stockid'=>$stid,
						'siia_mtid'=>$smtid,
						'siia_name'=>$stnme,
						'siia_qty'=>$oqty,
						'siia_desc'=>$this->input->post('item_desc'),
						'siia_upstatus'=>"Issued",
						'siia_creatorname'=>$this->session->userdata('username'),
						'siia_creatordate'=>date('Y-m-d'),
					);
					$siaflag=$this->picomodel->insertrec('stock_items_issued_archive',$siadata);
					// add log in db
				}else{
					$usfdata = array(
	                                'sii_mtid'=>$smtid,
        	                        'sii_name'=>$stnme,
                	                'sii_qty'=>$nqty,
                        	        'sii_desc'=>$this->input->post('item_desc'),
	                        	);
					$usflag=$this->picomodel->insertrec('stock_items_issued',$usfdata);
					$res=$this->picomodel->get_orderlistspficemore('stock_items_issued','sii_id,sii_qty',$dupsdata,'');
					// add log in db
                                        foreach($res as $row){
                                                $oqty=$row->sii_qty;
                                                $stid=$row->sii_id;
                                        }
                                        $siadata=array(
                                                'siia_stockid'=>$stid,
                                                'siia_mtid'=>$smtid,
                                                'siia_name'=>$stnme,
                                                'siia_qty'=>$oqty,
                                                'siia_desc'=>$this->input->post('item_desc'),
                                                'siia_upstatus'=>"Issued",
                                                'siia_creatorname'=>$this->session->userdata('username'),
                                                'siia_creatordate'=>date('Y-m-d'),
                                        );
					$siaflag=$this->picomodel->insertrec('stock_items_issued_archive',$siadata);
					// add log in db
				}
                    		$this->logger->write_logmessage("insert","Added new item", "New Item ".$_POST['item_name']." added  successfully...");
                    		$this->logger->write_dblogmessage("insert","Added new item", "New Item".$_POST['item_name']." added  successfully...");
                    		$this->session->set_flashdata("success", "Item added successfully...");
                    		redirect("itemaction/itemissuedetails"); 
                	}
            	}//end validation
        }//end isset
        $this->load->view('itemaction/itemissueform');
    }

    public function getitemlist(){
	$mtyp = $this->input->post('mtype');
        $datawh=array('item_mtid' => $mtyp,'item_balqty >'=>0);
        $rlid=$this->session->userdata('id_role');
        $usrid=$this->session->userdata('id_user');

        $whorder = "item_name asc";
	$ilist_data = $this->picomodel->get_orderlistspficemore('items','item_id,item_name,item_balqty',$datawh,$whorder);
	$item_select_box ='';
                $item_select_box.='<option value=null>--Select Item--';
              //  $item_select_box.='<option value='.All.'>'.All. ' ';
                if(count($ilist_data)>0){
                        foreach($ilist_data as $grprecord){
                                $item_select_box.='<option value='.$grprecord->item_id.'>'.$grprecord->item_name.'('. $grprecord->item_balqty .')'.' ';
                        }
		}
                echo json_encode($item_select_box);
    }

    /** This function is used to issue Item detail entries
     * @return type
    */
    public function itemissuedetails(){
	$sel='ii_id,ii_itemid,ii_mtid,ii_name,ii_qty,ii_desc,ii_staffpfno,ii_staffname,ii_dept,ii_receivername,ii_creatordate';
	$whorder='ii_mtid asc,ii_itemid asc';
	$data['result'] = $this->picomodel->get_orderlistspficemore('items_issued',$sel,'',$whorder);
        $this->logger->write_logmessage("view"," View Item List setting", "Item List setting details...");
	$this->logger->write_dblogmessage("view"," View Item List setting", "Item List setting details...");
        $this->load->view('itemaction/displayissueitem',$data);
    }

//item return
    public function returnitem($pfno=''){
	$pfno = $this->input->post('emppfno');
	if(!empty($pfno)){
		$sel='ii_id,ii_itemid,ii_mtid,ii_name,ii_qty,ii_irqty,ii_desc,ii_staffpfno,ii_staffname,ii_dept,ii_receivername,ii_creatordate';
		$whorder='ii_mtid asc,ii_staffpfno asc,ii_dept asc';
		$whdata=array('ii_staffpfno'=>$pfno);
		$data['result']=$this->picomodel->get_orderlistspficemore('items_issued',$sel,$whdata,$whorder);
	        $this->load->view('itemaction/displayissueitemfr',$data);
	  //  $this->load->view('itemaction/itemreturnform',$data);
	}else{
		$sel='ii_id,ii_itemid,ii_mtid,ii_name,ii_qty,ii_irqty,ii_desc,ii_staffpfno,ii_staffname,ii_dept,ii_receivername,ii_creatordate';
		$whorder='ii_mtid asc,ii_staffpfno asc,ii_dept asc';
	    	$data['result']=$this->picomodel->get_orderlistspficemore('items_issued',$sel,'',$whorder);
        	$this->load->view('itemaction/displayissueitemfr',$data);
	  //  $this->load->view('itemaction/itemreturnform',$data);
	}
    }
    public function returnitemtype($iiid){
	    $sel='ii_itemid,ii_mtid,ii_name,ii_qty,ii_desc,ii_staffpfno,ii_staffname,ii_dept,ii_receivername';
	    $whdata=array('ii_id'=>$iiid);
	    $result=$this->picomodel->get_orderlistspficemore('items_issued',$sel,$whdata,'');
	    $ridata=array();
//	    $emppf="";
//	    $iiqty=0;
	    foreach ($result as $res){
		    $ridata['mtname']=$this->picomodel->get_listspfic1('material_type','mt_name','mt_id',$res->ii_mtid)->mt_name;
		    $ridata['itemname']=$res->ii_name;
		    $ridata['itemqty']=$res->ii_qty;
		    $ridata['desc']=$res->ii_desc;
		    $ridata['empname']=$res->ii_staffname;
		    $ridata['emppf']=$res->ii_staffpfno;
		    $ridata['dept']=$res->ii_dept;
//		    $emppf=$res->ii_staffpfno;
//		    $iiqty=$res->ii_qty;
	    }
	    $ridata['iiid']=$iiid;
/*	    $flg=false;
	    $irqty=0;
	    $whdata1=array('ir_iiid'=>$iiid,'ir_staffpfno'=>$emppf);
	    $qtyres=$this->picomodel->get_sumofvalue('items_return','ir_qty',$whdata1);
	    print_r($qtyres); die();
	    foreach($qtyres as $rsqt){
		$irqty=$rsqt->ir_qty;
	    }
	    if($iiqty >$irqty)
		    $flg=true;
	    $ridata['retflag']=$flg;
*/
	    $this->load->view('itemaction/itemreturnform',$ridata);
    }
  /*  public function getpfitemdata(){
	$pfno = $this->input->post('emppfno');
	$sel='ii_itemid,ii_mtid,ii_name,ii_qty,ii_desc,ii_staffpfno,ii_staffname,ii_dept,ii_receivername,ii_creatordate';
        $whorder='ii_mtid asc,ii_staffpfno asc,ii_dept asc';
        $whdata=array('ii_staffpfno'=>$pfno);
	$result=$this->picomodel->get_orderlistspficemore('items_issued',$sel,$whdata,$whorder);
	if(empty($result)){
		$result="No item issued with this PF Number";
	}
	echo json_encode($result);
    }
*/
    /**** This function Insert Item type *********************/

    public function insertitemreturned(){
    	if(isset($_POST['item_return'])){
//        	$this->form_validation->set_rules('mtid','Material ID','trim|xss_clean|required');
  //      	$this->form_validation->set_rules('item_name','Item Name','trim|xss_clean|required');
        	$this->form_validation->set_rules('item_qty','Item Quantity','trim|xss_clean|required');
    //        	$this->form_validation->set_rules('item_desc','Item Description','trim|xss_clean');
      //      	$this->form_validation->set_rules('empid','Item Name','trim|xss_clean|required');
        //    	$this->form_validation->set_rules('deptname','Item Price','trim|xss_clean|required');
            	$this->form_validation->set_rules('item_recevied','Item Received By','trim|xss_clean|required');
		if($this->form_validation->run() ==TRUE){
			$recid=$this->input->post('iiid');
			$nqty=$this->input->post('item_qty');
			$receviedby=$this->input->post('item_recevied');
			
			$sel='ii_itemid,ii_mtid,ii_name,ii_qty,ii_desc,ii_staffpfno,ii_staffname,ii_dept';
		        $whdata=array('ii_id'=>$recid);
		        $result=$this->picomodel->get_orderlistspficemore('items_issued',$sel,$whdata,'');
	                foreach ($result as $res){
				$itemid=$res->ii_itemid;
				$mtid =$res->ii_mtid;
				$itemname =$res->ii_name;
				$desc =$res->ii_desc;
				$emppf =$res->ii_staffpfno;
				$empname =$res->ii_staffname;
				$dept =$res->ii_dept;
			}
			$itembal=$this->picomodel->get_listspfic1('items', 'item_balqty', 'item_id',$itemid)->item_balqty;
			$fitembal=$itembal + $nqty;

			$data = array(
				'ir_iiid'=>$recid,
                    		'ir_itemid'=>$itemid,
                    		'ir_mtid'=>$mtid,
                    		'ir_name'=>strtolower($itemname),
				'ir_qty'=>$nqty,
				'ir_desc'=>$desc,
				'ir_staffpfno'=>$emppf,
				'ir_staffname'=>$empname,
				'ir_dept'=>$dept,
				'ir_receivername'=>$receviedby,
				'ir_creatorname'=>$this->session->userdata('username'),
				'ir_creatordate'=>date('Y-m-d'),
				'ir_modifiername'=>$this->session->userdata('username'),
				'ir_modifierdate'=>date('Y-m-d'),
                	);

                	$rflag= $this->picomodel->insertrec('items_return',$data);
                	if(!$rflag){
                    		$this->logger->write_logmessage("insert","Trying to Add return Item", "Return Item is not added ".$itemname);
                    		$this->session->set_flashdata('err_message','Error in adding return Item details...'  , 'error');
                    		redirect('itemaction/returnitem');
                	}
			else{
				//update items table
				$upitembal=array('item_balqty'=>$fitembal);
				$uiflag=$this->picomodel->updaterec('items',$upitembal,'item_id',$itemid);
				$this->logger->write_logmessage("update"," update Returned Item in item table", "Returned Item List details...".'item_balqty='.$fitembal.'=item_id='.$itemid."=uiflag=".$uiflag);
				// add log in db
				$irqty=0;
				$whdata1=array('ir_iiid'=>$recid,'ir_staffpfno'=>$emppf);
			        $qtyres=$this->picomodel->get_sumofvalue('items_return','ir_qty',$whdata1);
				foreach($qtyres as $rsqt){
			                $irqty=$rsqt->ir_qty;
            			}				
				$upiibal=array('ii_irqty'=>$irqty);
				$uiiflag=$this->picomodel->updaterec('items_issued',$upiibal,'ii_id',$recid);
				$this->logger->write_logmessage("update"," update Returned Item in item_issued table", "Returned Item List details...".'itemi_balqty='.$irqty.'=iitem_id='.$recid."=uiiflag=".$uiiflag);
				// add log in db
				//this entry must go to stock
				$smtid=$mtid;
				$stnme=strtolower($itemname);

				$dupsdata=array('sii_mtid'=>$smtid,'sii_name'=>$stnme);
				$flag=$this->picomodel->isduplicatemore("stock_items_issued",$dupsdata);
				//if exist then go to stock archive
				if($flag){
					$oqty=0;
					$res=$this->picomodel->get_orderlistspficemore('stock_items_issued','sii_id,sii_qty',$dupsdata,'');
					foreach($res as $row){
						$oqty=$row->sii_qty;
						$stid=$row->sii_id;
					}
					$fqty=$oqty - $nqty;
					$usdata=array('sii_qty'=>$fqty);
					$uflag=$this->picomodel->updaterec('stock_items_issued', $usdata,'sii_id', $stid);
					//add log in db
					$siadata=array(
						'siia_stockid'=>$stid,
						'siia_mtid'=>$smtid,
						'siia_name'=>$stnme,
						'siia_qty'=>$oqty,
						'siia_desc'=>$this->input->post('item_desc'),
						'siia_upstatus'=>"Returned",
						'siia_creatorname'=>$this->session->userdata('username'),
						'siia_creatordate'=>date('Y-m-d'),
					);
					$siaflag=$this->picomodel->insertrec('stock_items_issued_archive',$siadata);
					// add log in db
				}
                    		$this->logger->write_logmessage("insert","Returned item", "New Item ".$_POST['item_name']." added  successfully...");
                    		$this->logger->write_dblogmessage("insert","Returned  item", "New Item".$_POST['item_name']." added  successfully...");
                    		$this->session->set_flashdata("success", "Item Returned successfully...");
                    		redirect("itemaction/itemreturndetails"); 
                	}
            	}//end validation
        }//end isset
        $this->load->view('itemaction/returnitem');
    }

    /** This function is used to issue Item detail entries
     * @return type
    */
    public function itemreturndetails(){
	$sel='ir_id,ir_itemid,ir_mtid,ir_name,ir_qty,ir_desc,ir_staffpfno,ir_staffname,ir_dept,ir_receivername,ir_creatordate';
	$whorder='ir_mtid asc,ir_itemid asc';
	$data['result'] = $this->picomodel->get_orderlistspficemore('items_return',$sel,'',$whorder);
        $this->logger->write_logmessage("view"," View Returned Item List ", "Returned Item List details...");
	$this->logger->write_dblogmessage("view"," View Returned Item List ", "Returned Item List details...");
        $this->load->view('itemaction/displayreturnitem',$data);
    }

}
