<?php 
if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Unspentbalance extends CI_Controller {

function __construct() {
        parent::__construct();
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('user/login');
        }
    }
        function planreport()
        {
		$this->load->model('Ledger_model');	
                $this->load->helper('text');
		$data['print_preview'] =FALSE;
		$data['save_report'] =FALSE;
		$data['make_txt'] =TRUE;
		if ($_POST){
                	if ($_POST['submit'] == 'Save'){
                        	$data['make_txt'] =FALSE;
				$this->messages->add('File Saved Successfully', 'success');
                	}
		}
		$data['fund'] = $this->Ledger_model->get_fund_ledgers();
		$this->template->load('template', 'unspentbalance/index', $data);

                return;
	}        
	
	function nonplanreport()
        {
		$this->load->model('Ledger_model');
                $this->load->helper('text');
		$data['print_preview'] =FALSE;
		$data['save_report'] =FALSE;
		$data['make_txt'] =TRUE;
		if ($_POST){
                        if ($_POST['submit'] == 'Save'){
                                $data['make_txt'] =FALSE;
				$this->messages->add('File Saved Successfully', 'success');
                        }
                }
		$data['fund'] = $this->Ledger_model->get_fund_ledgers();
		$this->template->load('template', 'unspentbalance/nonplan', $data);

                return;
        }

	function summaryreport()
        {
		$this->load->model('Ledger_model');
                $this->load->helper('text');
		$data['print_preview'] =FALSE;
		$data['save_report'] =FALSE;
		$data['make_txt'] =TRUE;
		if ($_POST){
                        if ($_POST['submit'] == 'Save'){
                                $data['make_txt'] =FALSE;
				$this->messages->add('File Saved Successfully', 'success');
                        }
                }
		$data['fund'] = $this->Ledger_model->get_fund_ledgers();
		$this->template->load('template', 'unspentbalance/summaryreport', $data);

                return;
        }

	function printpreview($statement, $id = NULL)
        {
                $this->load->library('session');
		$this->load->model('Ledger_model');
                $date1 = $this->session->userdata('date1');
		$date2 = $this->session->userdata('date2');
		$data['fund'] = $this->Ledger_model->get_fund_ledgers();
		$data['print_preview'] =FALSE;
                $data['save_report'] =FALSE;
                $data['make_txt'] =TRUE;		

                /********************** Plan Report *************************/
                if ($statement == "plan_report")
                {
                        $data['report'] = "unspentbalance/index";
                        $data['title'] = "Normal Plan";
                        $data['width'] = "70%";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('unspentbalance/unspent_template', $data);
                        return;
                }
		/********************** Non Plan Report *************************/
                if ($statement == "nonplan_report")
                {
                        $data['report'] = "unspentbalance/nonplan";
                        $data['title'] = "Non Plan";
                        $data['width'] = "70%";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('unspentbalance/unspent_template', $data);
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
		$report_name=' 2015'.$report_type.".txt";
                $full_name=$filename.','. $report_name;
		$this->load->helper('file');
		$this->load->helper('url'); //You should autoload this one ;)
	   	$path=$this->upload_path= realpath(BASEPATH.'../docs/BGAS');
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
