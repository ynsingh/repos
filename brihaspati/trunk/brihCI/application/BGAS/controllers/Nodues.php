<?php 
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Nodues extends CI_Controller {

	function __construct() {
		parent::__construct();
		$this->load->model('Secunit_model');
                $this->load->model('BGAS_model');
		$this->load->library('session');
        	if(empty($this->session->userdata('id_user'))) {
	        	$this->session->set_flashdata('flash_data', 'You don\'t have access!');
            		redirect('user/login');
        	}
    	}
	function noduesnote($sacunitid,$bal){
		//$this->template->set('nav_links', array('report2/download/negative_trans_report/'  => 'Download CSV', 'report2/printpreview/negative_trans_report/' => 'Print Preview', 'report2/pdf/negative_trans_report/' => 'Download PDF'));
		$this->template->set('nav_links', array('nodues/printpreview/noduesnote/'.$sacunitid.'/'.$bal => 'Print Preview', 'nodues/pdf/noduesnote/'.$sacunitid.'/'.$bal => 'Download PDF'));
		$fdata['print_preview'] =FALSE;
		$fdata['rdata']=$this->getNoduesdata($sacunitid,$bal);
		$balance =$this->getBalance($sacunitid);
//		print_r($fdata); die();
                if($balance >0){
	               // $data['balence']=$balance;
                        //$data['heading']="NO DUES NOTE";
                        $this->template->load('template', 'nodues/noduesnote', $fdata);
                }else{
                //	$data['balence']=$balance;
                  //      $data['heading']="NO DUES CERTIFICATE";
                        $this->template->load('template', 'nodues/noduescert', $fdata);
		}
		/*
		$data['partynme']=$this->BGAS_model->get_listspfic1('addsecondparty','partyname','sacunit',$sacunitid)->partyname;
		$data['balence']=$bal;
		$data['heading']="NO DUES NOTE";
		$this->db->select('id, name, ins_name,dept_name,address')->from('settings');
        	$ins_id = $this->db->get();
	        foreach( $ins_id->result() as $row)
        	{
                	$ins_name = $row->ins_name;
	                $ins_address = $row->address;
			$row1 = $row->name;
			$deptname = $row->dept_name;
        	}
		$data['ins_name']=$ins_name;
		$data['ins_address']=$ins_address;
		$data['row1']=$row1;
		$data['dept_name']=$deptname;
		$data['pfno']=$this->BGAS_model->get_listspfic1('addsecondparty','pfnumber','sacunit',$sacunitid)->pfnumber;
		$data['emailadd']=$this->BGAS_model->get_listspfic1('addsecondparty','email','sacunit',$sacunitid)->email;
		$data['paddress']=$this->BGAS_model->get_listspfic1('addsecondparty','permanentaddress','sacunit',$sacunitid)->permanentaddress;
		$this->template->load('template', 'nodues/noduesnote', $data);
		 */
                return;
	}

	 function noduescert($sacunitid,$bal){
                $this->template->set('nav_links', array('nodues/printpreview/noduescert/'.$sacunitid.'/'.$bal => 'Print Preview', 'nodues/pdf/noduescert/'.$sacunitid.'/'.$bal => 'Download PDF'));
		$fdata['print_preview'] =FALSE;
		$fdata['rdata']=$this->getNoduesdata($sacunitid,$bal);
		
                $balance =$this->getBalance($sacunitid);
                if($balance >0){
	               // $data['balence']=$balance;
                       // $data['heading']="NO DUES NOTE";
                        $this->template->load('template', 'nodues/noduesnote', $fdata);
                }else{
                //	$data['balence']=$balance;
                  //      $data['heading']="NO DUES CERTIFICATE";
                        $this->template->load('template', 'nodues/noduescert', $fdata);
		}
           /*     $data['partynme']=$this->BGAS_model->get_listspfic1('addsecondparty','partyname','sacunit',$sacunitid)->partyname;
                $data['balence']=$bal;
                $this->db->select('id, name, ins_name,dept_name,address')->from('settings');
                $ins_id = $this->db->get();
                foreach( $ins_id->result() as $row)
                {
                        $ins_name = $row->ins_name;
                        $ins_address = $row->address;
                        $row1 = $row->name;
                        $deptname = $row->dept_name;
                }
                $data['ins_name']=$ins_name;
                $data['ins_address']=$ins_address;
                $data['row1']=$row1;
                $data['dept_name']=$deptname;
                $data['pfno']=$this->BGAS_model->get_listspfic1('addsecondparty','pfnumber','sacunit',$sacunitid)->pfnumber;
                $data['emailadd']=$this->BGAS_model->get_listspfic1('addsecondparty','email','sacunit',$sacunitid)->email;
		$data['paddress']=$this->BGAS_model->get_listspfic1('addsecondparty','permanentaddress','sacunit',$sacunitid)->permanentaddress;
		//check the balance for this id
		$balance =$this->getBalance($sacunitid);
		if($balance >0){
			$data['heading']="NO DUES NOTE";
			$this->template->load('template', 'nodues/noduesnote', $data);
		}else{
	                $data['heading']="NO DUES CERTIFICATE";
			$this->template->load('template', 'nodues/noduescert', $data);
		}
	    */
                return;
	 }

	function getNoduesdata($sacunitid,$bal){
		$data['partynme']=$this->BGAS_model->get_listspfic1('addsecondparty','partyname','sacunit',$sacunitid)->partyname;
                $data['balence']=$bal;
                $this->db->select('id, name, ins_name,dept_name,address')->from('settings');
                $ins_id = $this->db->get();
                foreach( $ins_id->result() as $row)
                {
                        $ins_name = $row->ins_name;
                        $ins_address = $row->address;
                        $row1 = $row->name;
                        $deptname = $row->dept_name;
                }
                $data['ins_name']=$ins_name;
                $data['ins_address']=$ins_address;
                $data['row1']=$row1;
                $data['dept_name']=$deptname;
                $data['pfno']=$this->BGAS_model->get_listspfic1('addsecondparty','pfnumber','sacunit',$sacunitid)->pfnumber;
                $data['emailadd']=$this->BGAS_model->get_listspfic1('addsecondparty','email','sacunit',$sacunitid)->email;
                $data['paddress']=$this->BGAS_model->get_listspfic1('addsecondparty','permanentaddress','sacunit',$sacunitid)->permanentaddress;
                //check the balance for this id
                $balance =$this->getBalance($sacunitid);
                if($balance >0){
	                $data['balence']=$balance;
                        $data['heading']="NO DUES NOTE";
                      //  $this->template->load('template', 'nodues/noduesnote', $data);
                }else{
                	$data['balence']=$balance;
                        $data['heading']="NO DUES CERTIFICATE";
                       // $this->template->load('template', 'nodues/noduescert', $data);
		}
		return $data;
	}

	function getBalance($secid){
		$rec=$this->BGAS_model->get_listspfic1('ledgers','id','name','Fees Receivable');
		$bal=0;
                if(!empty($rec)){
                        $legid=$rec->id;
                        $whdata=array('ledger_id' => $legid,'secunitid' =>$secid, 'dc' =>'D');
                        $sumdb=$this->BGAS_model->get_orderlistspficemore('entry_items','sum(amount) as totdb',$whdata,'');
                        foreach($sumdb as $row2){
                                $dbamt = $row2->totdb;
                        }
                        $whdata=array('ledger_id' => $legid,'secunitid' =>$secid, 'dc' =>'C');
                        $sumcr=$this->BGAS_model->get_orderlistspficemore('entry_items','sum(amount) as totcr',$whdata,'');
                        foreach($sumcr as $row3){
                                $cramt = $row3->totcr;
                        }
                        $bal=$dbamt -$cramt;
                }
                return $bal;
	}

	function printpreview($statement, $sacunitid,$bal)
        {
//                $this->load->library('session');
		$this->load->model('Ledger_model');
                $date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');
		$data['fund'] = $this->Ledger_model->get_fund_ledgers();
		$data['print_preview'] =FALSE;
                $data['save_report'] =FALSE;
		$data['make_txt'] =TRUE;		
		// insert record in nodues table to maintain taken print
 		$idata = array(
                    'sacunitno'=>$sacunitid,
                    'type'=>$statement,
                    'date'=>date('y-m-d'),
	    	);
		$existflag = $this->BGAS_model->isduplicatemore('nodues', $idata);
		$idata['creatorid']=$this->session->userdata('user_name');
                if($existflag != 1 ){
			$iflag=$this->BGAS_model->insertrec('nodues', $idata) ;
		}
                /********************** Plan Report *************************/
                if ($statement == "noduesnote")
		{

                        $fdata['report'] = "nodues/noduesnote";
                        $fdata['title'] = "";
                        $fdata['width'] = "70%";
                        $fdata['print_preview'] = TRUE;
                        $fdata['entry_date1'] = $date1;
                        $fdata['entry_date2'] = $date2;
			$fdata['rdata']=$this->getNoduesdata($sacunitid,$bal);
			$this->load->view('report/report_template', $fdata);
//                        $this->load->view('nodues/noduesnote', $fdata);
                        return;
                }
		/********************** Non Plan Report *************************/
                if ($statement == "noduescert")
                {
                        $fdata['report'] = "nodues/noduescert";
                        $fdata['title'] = "";
                        $fdata['width'] = "70%";
                        $fdata['print_preview'] = TRUE;
                        $fdata['entry_date1'] = $date1;
			$fdata['entry_date2'] = $date2;

			$fdata['rdata']=$this->getNoduesdata($sacunitid,$bal);
			$this->load->view('report/report_template', $fdata);
                        //$this->load->view('nodues/noduescert', $fdata);
                        return;
                }
		/********************** Summry Report *************************/
                if ($statement == "summary_report")
                {
                        $data['report'] = "unspentbalance/summaryreport";
                        $data['title'] = "Summary Report";
                        $data['width'] = "70%";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('unspentbalance/unspent_template', $data);
                        return;
                }
		return;
	}
	function pdf($statement, $sacunitid,$bal)
        {
                $date1 = $this->session->userdata('date1');
                $date2 = $this->session->userdata('date2');
                $this->load->helper('pdf_helper');
                $this->load->library('pdf');
                $this->load->library('pagination');
                $this->load->helper('text');
		if($statement == "noduesnote")
                {
                        $data['width'] = "100%";
                        $page_count = 0;
                        /* Pagination setup */
                        $data['page_count'] = $page_count;
                        $data['report'] = "nodues/noduesnote";
                        $data['statement'] = "No Dues Note";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
			$data['rdata']=$this->getNoduesdata($sacunitid,$bal);
                        $this->pdf->load_view('report/pdfreport',$data);
                        $this->pdf->render();
                        $this->pdf->stream("noduesnote".$sacunitid.".pdf");
                        return;
              }
		if($statement == "noduescert")
                {
                        $data['width'] = "100%";
                        $page_count = 0;
                        /* Pagination setup */
                        $data['page_count'] = $page_count;
                        $data['report'] = "nodues/noduescert";
                        $data['statement'] = "No Dues Certificate";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
			$data['rdata']=$this->getNoduesdata($sacunitid,$bal);
                        $this->pdf->load_view('report/pdfreport',$data);
                        $this->pdf->render();
                        $this->pdf->stream("noduescert".$sacunitid.".pdf");
                        return;
              }
	}
	
	function delete($filename, $report_type)
        {
		$report_name=' 2015'.$report_type.".txt";
		$full_name=$filename.','. $report_name;
                //set path where we want to delete file...
                $path=$this->upload_path= realpath(BASEPATH.'../docs/BGAS');
		$file_list = get_filenames($path);
		$arr_len=count($file_list);
		for($i=0; $i<$arr_len; $i++){
			$exp_date=explode(",",$file_list[$i]);
			if($full_name == $file_list[$i]){
                		@unlink($path.'/'.$file_list[$i]);
			}
		}
		if($report_type == 'nonplan_report'){
		$this->nonplanreport();
		}elseif($report_type == 'summary_report'){
		$this->summaryreport();
		}else{
                $this->planreport();
		}
                return;
        }
	
	function view_file($filename, $report_type)
        {
//		$report_name=' 2015'.$report_type.".txt";
               // $full_name=$filename.','. $report_name;
                $full_name=$filename.$report_type;
		$this->load->helper('file');
		$this->load->helper('url'); //You should autoload this one ;)
	   	$path=$this->upload_path= realpath(BASEPATH.'../docs/BGAS/');
                $file_list = get_filenames($path);
                $arr_len=count($file_list);
                for($i=0; $i<$arr_len; $i++){
                        $exp_date=explode(",",$file_list[$i]);
                        if($full_name == $file_list[$i]){
				$data['file_name']=$file_list[$i];
                        }
                }
	
	        $this->load->view('unspentbalance/view_file', $data);

        }


	
}
?>
