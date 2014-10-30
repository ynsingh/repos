<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Report2 extends Controller {
	var $acc_array;
	var $account_counter;
	var $logndb;
	var $ledger_data;
	var $id;
	var $name;
	var $code;
	var $children_groups = array();
	var $counter = 1;

	function Report2()
	{
		parent::Controller();
		$this->load->model('Tag_model');
		$this->load->model('Ledger_model');
		$this->load->model('Budget_model');
		$this->load->model('Group_model');
		$this->load->model('Secunit_model');
		/* Check access */
		if ( ! check_access('view reports'))
		{
			$this->messages->add('Permission denied.', 'error');
			redirect('');
			return;
		}

		return;
	}
	
	function index()
	{
		$this->template->set('page_title', 'Other Reports');
		$this->template->load('template', 'report2/index');
		return;
	}	
	function tag($ledger_id = 0)
	{
		$this->load->library('session');
		$this->load->helper('text');
		/* Pagination setup */
		$this->load->library('pagination');

		$this->template->set('page_title', 'Tag Statement');
		$this->template->set('nav_links', array('report2/download/tag/' . $ledger_id  => 'Download CSV', 'report2/printpreview/tag/' . $ledger_id => 'Print Preview', 'report2/pdf/tag/' . $ledger_id => 'Download PDF'));
		$data['width'] = "70%";

		if ($_POST)
		{
			$ledger_id = $this->input->post('ledger_id', TRUE);
		}
		$data['print_preview'] = FALSE;
		$data['ledger_id'] = $ledger_id;

		/* Checking for valid ledger id */
		if ($data['ledger_id'] > 0)
		{
			$this->db->from('ledgers')->where('id', $data['ledger_id'])->limit(1);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Tag.', 'error');
				redirect('report2/tag');
				return;
			}
		} else if ($data['ledger_id'] < 0) {
			$this->messages->add('Invalid Ledger account.', 'error');
			redirect('report2/tag');
			return;
		}
		$default_end_date;

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
		}
		
		$data['entry_date1'] = array(
			'name' => 'entry_date1',
			'id' => 'entry_date1',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_start,
		);
		$data['entry_date2'] = array(
			'name' => 'entry_date2',
			'id' => 'entry_date2',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_end_date,
		);
		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

                $date=explode("/",$data_date1);
                $start_date=$date[2]."-".$date[1]."-".$date[0];
                $date=explode("/",$data_date2);
                $end_date=$date[2]."-".$date[1]."-".$date[0];

                $newrange = array(
                      'startdate'  => $start_date,
                      'enddate'  => $end_date
                     );
                $this->session->set_userdata($newrange);
		/* Repopulating form */
 
		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);
		} 
		
		/* Form validations */ 

                $this->form_validation->set_rules('entry_date1', 'Entry Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Entry Date', 'trim|required|is_date|is_date_within_range');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'report2/tag', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			$date=explode("/",$data_date1);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$data_date2);
			$date2=$date[2]."-".$date[1]."-".$date[0];
			
			$newdata = array(
	                   'date1'  => $date1,
        	           'date2'  => $date2,
	                );
			$this->session->set_userdata($newdata);
			redirect('report2/tag/' . $ledger_id);
		}
		$this->template->load('template', 'report2/tag/' . $ledger_id, $data);
		return;
	}

	function fundlist()
	{
                $this->template->set('page_title', 'Fund List');
		$this->load->library('session');
		$this->load->model('Ledger_model');

		// code for search 
		$data['search'] = '';
		$data['search_by'] = array(
			"Select" => "Select",
                        "code" => "Account Code",
                        "name"=> "Fund Name",
			"op_balance"=> "O/P Balance",
			//"cl_balance"=> "C/L Balance",
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
			$this->template->load('template', 'report2/fundlist', $data);
			return;
		}
		else
		{
			$data_search_by = $this->input->post('search_by', TRUE);
			$data_text = $this->input->post('text', TRUE);
		}
		if(gmp_sign($data_text) == -1) {
			$this->messages->add('Text should be a positive value.', 'error');
			redirect('report2/fundlist');
		}
		if($data_search_by == "code")
		{
			$text = '';
			if($data_search_by == "code")
			{
				$text = 'Account Code';
			}
			if(! ctype_alnum($data_text)) {
				$this->messages->add($text . ' should be alphanumeric.', 'error');
				redirect('report2/fundlist');
			}
		}
		if($data_search_by == "op_balance" || $data_search_by == "cl_balance") {
			$balance=explode(',', $data_text);
			$data_text = implode("",$balance);
			$data['text']['value'] = $data_text;
			$text = '';
			if($data_search_by == "op_balance") {
				$text = "O/P Balance";
			}
			if($data_search_by == "cl_balance") {
				$text = "C/L Balance";
			}
			if(! is_numeric($data_text)) {
				$this->messages->add($text . ' should be numeric.', 'error');
				redirect('report2/fundlist');
			}
		}
		$data['search'] = $data_search_by;          
                $this->template->load('template', 'report2/fundlist', $data);

	}

	function printpreview($statement, $id = NULL)
        {
                $this->load->library('session');
		$this->load->model('Tag_model');
                $date1 = $this->session->userdata('date1');
                $date2 = $this->session->userdata('date2');
		if ($statement == "tag")
                {
                        $this->load->helper('text');
                        $data['width'] = "70%";
                        $page_count = 0;
                        /* Pagination setup */
                        $this->load->library('pagination');
                        $data['ledger_id'] = $this->uri->segment(4);
                        $data['page_count'] = $page_count;
                        /* Checking for valid ledger id */
                        if ($data['ledger_id'] < 1)
                        {
                                $this->messages->add('Invalid Ledger account.', 'error');
                                redirect('report2/tag');
                                return;
                        }
                        $this->db->from('ledgers')->where('id', $data['ledger_id'])->limit(1);
                        if ($this->db->get()->num_rows() < 1)
                        {
                                $this->messages->add('Invalid Ledger account.', 'error');
                                redirect('report2/tag');
                                return;
                        }
                        $data['report'] = "report2/tag";
                        $data['title'] = "Tag Statement for '" . $this->Tag_model->tag_name($data['ledger_id']) . "'";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
                        $this->session->unset_userdata('date1');
                        $this->session->unset_userdata('date2');
                        return;
                }

		if ($statement == "sec_report")
                {
                        $this->load->helper('text');
                        $data['width'] = "70%";
                        $page_count = 0;
                        /* Pagination setup */
                        $this->load->library('pagination');
                        $data['sec_uni_id'] = $this->uri->segment(4);
                        $data['page_count'] = $page_count;
                        $data['report'] = "report2/sec_report";
                        $data['title'] =  "Secondary unit Statement for '" . $this->Secunit_model->get_secunitname($data['sec_uni_id']) . "'";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
			$this->load->view('report/report_template', $data);
                        $this->session->unset_userdata('date1');
                        $this->session->unset_userdata('date2');
                        return;
                }
	}
	function pdf($statement, $id = NULL)
        {
                $this->load->helper('pdf_helper');
                $this->load->library('session');
		if($statement == "tag")
                {
                 $this->load->helper('text');
                        $data['width'] = "100%";
                        $page_count = 0;
                        /* Pagination setup */
                        $this->load->library('pagination');
                        $data['ledger_id'] = $this->uri->segment(4);
                        $data['page_count'] = $page_count;
                        /* Checking for valid ledger id */
                        if ($data['ledger_id'] < 1)
                        {
                                $this->messages->add('Invalid Ledger account.', 'error');
                                redirect('report2/tag');
                                return;
                        }
                        $this->db->from('ledgers')->where('id', $data['ledger_id'])->limit(1);
                        if ($this->db->get()->num_rows() < 1)
                        {
                                $this->messages->add('Invalid Ledger account.', 'error');
                                redirect('report2/tag');
                                return;
                        }
                        $data['report'] = "report2/tag";
                        $data['statement'] = "Tag Statement for '" . $this->Tag_model->tag_name($data['ledger_id']) . "'";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/pdfreport', $data);
                        return;
                }
		
		if($statement == "sec_report")
                {
                 $this->load->helper('text');
                        $data['width'] = "100%";
                        $page_count = 0;
                        /* Pagination setup */
                        $this->load->library('pagination');
                        $data['sec_uni_id'] = $this->uri->segment(4);
                        $data['page_count'] = $page_count;
                        $data['report'] = "report2/sec_report";
                        $data['statement'] = "Secondary unit Statement for '" . $this->Secunit_model->get_secunitname($data['sec_uni_id']) . "'";
                        $data['print_preview'] = TRUE;
                        $data['entry_date1'] = $date1;
                        $data['entry_date2'] = $date2;
                        $this->load->view('report/pdfreport', $data);
                        return;
                }

	}
	function fund_ledgerst($ledger_id = 0)
	{
		$this->load->library('session');
		$this->load->helper('text');
		/* Pagination setup */
		$this->load->library('pagination');

		$this->template->set('page_title', 'Ledger Statement');
		$this->template->set('nav_links', array('report/download/ledgerst/' . $ledger_id  => 'Download CSV', 'report/printpreview/ledgerst/' . $ledger_id => 'Print Preview', 'report/pdf/ledgerst/' . $ledger_id => 'Download PDF'));
		$data['width'] = "70%";

		if ($_POST)
		{
			$ledger_id = $this->input->post('ledger_id', TRUE);
		}
		$data['print_preview'] = FALSE;
		$data['ledger_id'] = $ledger_id;

		/* Checking for valid ledger id */
		if ($data['ledger_id'] > 0)
		{
			$this->db->from('ledgers')->where('id', $data['ledger_id'])->limit(1);
			if ($this->db->get()->num_rows() < 1)
			{
				$this->messages->add('Invalid Ledger account.', 'error');
				redirect('report2/fund_ledgerst');
				return;
			}
		} else if ($data['ledger_id'] < 0) {
			$this->messages->add('Invalid Ledger account.', 'error');
			redirect('report2/fund_ledgerst');
			return;
		}
		$default_end_date;

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
		}
		
		$data['entry_date1'] = array(
			'name' => 'entry_date1',
			'id' => 'entry_date1',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_start,
		);
		$data['entry_date2'] = array(
			'name' => 'entry_date2',
			'id' => 'entry_date2',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_end_date,
		);
		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

                $date=explode("/",$data_date1);
                $start_date=$date[2]."-".$date[1]."-".$date[0];
                $date=explode("/",$data_date2);
                $end_date=$date[2]."-".$date[1]."-".$date[0];

                $newrange = array(
                      'startdate'  => $start_date,
                      'enddate'  => $end_date
                     );
                $this->session->set_userdata($newrange);
		/* Repopulating form */
 
		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);
		} 
		
		/* Form validations */ 

                $this->form_validation->set_rules('entry_date1', 'Entry Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Entry Date', 'trim|required|is_date|is_date_within_range');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'report2/fund_ledgerst', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			$date=explode("/",$data_date1);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$data_date2);
			$date2=$date[2]."-".$date[1]."-".$date[0];
			
			$newdata = array(
	                   'date1'  => $date1,
        	           'date2'  => $date2,
	                );
			$this->session->set_userdata($newdata);
			redirect('report2/fund_ledgerst/' . $ledger_id);
		}
		$this->template->load('template', 'report2/fund_ledgerst', $data);
		return;
	}

	function download($statement, $id = NULL)
	{
		if($id != '0')
		{
			$this->load->library('session');
			$date1 = $this->session->userdata('date1');
			$date2 = $this->session->userdata('date2');
			/********************** TAG STATEMENT **********************/
			if ($statement == "tag")
			{
				$this->load->helper('text');
				$ledger_id = (int)$this->uri->segment(4);
				if ($ledger_id < 1)
				return;

				$this->load->model('Ledger_model');

				$cur_balance = 0;
				$counter = 0;
				$fund_ledgerst = array();

				$fund_ledgerst[$counter] = array ("", "", "Tag STATEMENT FOR " . strtoupper($this->Tag_model->tag_name($ledger_id)), "", "", "", "", "", "", "", "");
				$counter++;

				$fund_ledgerst[$counter][0]= "Date";
				$fund_ledgerst[$counter][1]= "Number";
				$fund_ledgerst[$counter][2]= "Ledger Name";
				$fund_ledgerst[$counter][3]= "Type";
				$fund_ledgerst[$counter][4]= "Dr Amount";
				$fund_ledgerst[$counter][5]= "Cr Amount";
				$counter++;

				$this->db->from('entries')->where('tag_id', $ledger_id);
				$this->db->where('date >=', $date1);
				$this->db->where('date <=', $date2);
				$fund_ledgerst_q = $this->db->get();
				foreach ($fund_ledgerst_q->result() as $row)
				{
					/* Entry Type */
					$current_entry_type = entry_type_info($row->entry_type);
					$fund_ledgerst[$counter][0] = date_mysql_to_php_display($row->date);
					$fund_ledgerst[$counter][1] = $row->number;
					/* Opposite entry name */
					$tag_name = $this->Ledger_model->get_opp_tag_entry_name($row->id, $row->entry_type);
					$fund_ledgerst[$counter][2] = $tag_name;
					$fund_ledgerst[$counter][3] =  $current_entry_type['name'];
					$fund_ledgerst[$counter][4] = $row->dr_total;
					$fund_ledgerst[$counter][5] = $row->cr_total;
					$counter++;
				}
					$this->load->helper('csv');
					echo array_to_csv($fund_ledgerst, "Tag.csv");
					return;
			}

			/********************** SECONDARY UNIT STATEMENT **********************/
			if ($statement == "sec_report")
			{
				$this->load->helper('text');
				$sec_uni_id = (int)$this->uri->segment(4);
				if ($sec_uni_id < 1)
				return;

				$this->load->model('Secunit_model');

				$cur_balance = 0;
				$counter = 0;
				$fund_ledgerst = array();

				$fund_ledgerst[$counter] = array ("", "", "SECONDARY UNIT STATEMENT FOR " . strtoupper($this->Secunit_model->get_secunitname($sec_uni_id)), "", "", "", "", "", "", "", "");
				$counter++;

				$fund_ledgerst[$counter][0]= "Date";
				$fund_ledgerst[$counter][1]= "Number";
				$fund_ledgerst[$counter][2]= "Ledger Name";
				$fund_ledgerst[$counter][3]= "Type";
				$fund_ledgerst[$counter][4]= "Dr Amount";
				$fund_ledgerst[$counter][5]= "Cr Amount";
				$counter++;

				$this->db->from('entries')->where('secunitid', $sec_uni_id);
				$this->db->where('date >=', $date1);
				$this->db->where('date <=', $date2);
				$fund_ledgerst_q = $this->db->get();
				foreach ($fund_ledgerst_q->result() as $row)
				{
					/* Entry Type */
					$current_entry_type = entry_type_info($row->entry_type);
					$fund_ledgerst[$counter][0] = date_mysql_to_php_display($row->date);
					$fund_ledgerst[$counter][1] = $row->number;
					/* Opposite entry name */
					$tag_name = $this->Ledger_model->get_opp_tag_entry_name($row->id, $row->entry_type);
					$fund_ledgerst[$counter][2] = $tag_name;
					$fund_ledgerst[$counter][3] =  $current_entry_type['name'];
					$fund_ledgerst[$counter][4] = $row->dr_total;
					$fund_ledgerst[$counter][5] = $row->cr_total;
					$counter++;
				}
					$this->load->helper('csv');
					echo array_to_csv($fund_ledgerst, "Secondary Unit report.csv");
					return;
			}
		
		
		}else{
                        redirect('report2/tag');
                        return;
		}
		return;
	}
	
	function sec_report($sec_uni_id = 0)
	{
		$this->load->library('session');
		$this->load->helper('text');
		/* Pagination setup */
		$this->load->library('pagination');

		$this->template->set('page_title', 'Secondary Unit Statement');
		$this->template->set('nav_links', array('report2/download/sec_report/' . $sec_uni_id  => 'Download CSV', 'report2/printpreview/sec_report/' . $sec_uni_id => 'Print Preview', 'report2/pdf/sec_report/' . $sec_uni_id => 'Download PDF'));
		$data['width'] = "70%";

		if ($_POST)
		{
			$sec_uni_id = $this->input->post('sec_uni_id', TRUE);
		}
		$data['print_preview'] = FALSE;
		$data['sec_uni_id'] = $sec_uni_id;

		$default_end_date;

		/* Form fields */ 
		$this->db->from('settings');
		$detail = $this->db->get();
		foreach ($detail->result() as $row)
		{
			$date1 = $row->fy_start;
			$date2 = $row->fy_end;
		}
		$date=explode("-",$date1);
		$date2 = explode("-", $row->fy_end);
		$default_start = '01/04/'.$date[0];
		$default_end = '31/03/'.$date2[0];
		
		$curr_date = date_today_php();
		if($curr_date >= $default_end) {
			$default_end_date = $default_end;
		}
		else {
			$default_end_date = $curr_date;
		}
		
		$data['entry_date1'] = array(
			'name' => 'entry_date1',
			'id' => 'entry_date1',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_start,
		);
		$data['entry_date2'] = array(
			'name' => 'entry_date2',
			'id' => 'entry_date2',
			'maxlength' => '11',
			'size' => '11',
			'value' => $default_end_date,
		);
		$data_date1 = $default_start;
                $data_date2 = $default_end_date;

                $date=explode("/",$data_date1);
                $start_date=$date[2]."-".$date[1]."-".$date[0];
                $date=explode("/",$data_date2);
                $end_date=$date[2]."-".$date[1]."-".$date[0];

                $newrange = array(
                      'startdate'  => $start_date,
                      'enddate'  => $end_date
                     );
                $this->session->set_userdata($newrange);
		/* Repopulating form */
 
		if ($_POST)
		{
			$data['entry_date1']['value'] = $this->input->post('entry_date1', TRUE);
			$data['entry_date2']['value'] = $this->input->post('entry_date2', TRUE);
		} 
		
		/* Form validations */ 

                $this->form_validation->set_rules('entry_date1', 'Entry Date From', 'trim|required|is_date|is_date_within_range');
                $this->form_validation->set_rules('entry_date2', 'To Entry Date', 'trim|required|is_date|is_date_within_range');

		/* Validating form */
		if ($this->form_validation->run() == FALSE)
		{
			$this->messages->add(validation_errors(), 'error');
			$this->template->load('template', 'report2/sec_report', $data);
			return;
		}
		else
		{
			$data_date1 = $this->input->post('entry_date1', TRUE);
			$data_date2 = $this->input->post('entry_date2', TRUE);

			$date=explode("/",$data_date1);
			$date1=$date[2]."-".$date[1]."-".$date[0];
			$date=explode("/",$data_date2);
			$date2=$date[2]."-".$date[1]."-".$date[0];
			
			$newdata = array(
	                   'date1'  => $date1,
        	           'date2'  => $date2,
	                );
			$this->session->set_userdata($newdata);
			redirect('report2/sec_report/' . $sec_uni_id);
		}
		$this->template->load('template', 'report2/sec_report/' . $sec_uni_id, $data);
		return;
	}
		
}
?>
