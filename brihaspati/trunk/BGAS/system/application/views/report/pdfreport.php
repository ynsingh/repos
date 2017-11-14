<?php
	//start code for generating pdf..........
	require_once(APPPATH.'libraries/dompdf/dompdf_config.inc.php');
	$dom_pdf = new DOMPDF();
	// (Optional) Setup the paper size and orientation
        //$dom_pdf->setPaper('A4', 'landscape');
	

	//code for view ..........
	$this->load->model('Ledger_model');
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
	$this->load->library('pagination');
        $ledger_id = $this->uri->segment(4);
	$start_date = $this->session->userdata('startdate');
        $end_date = $this->session->userdata('enddate');
        $from_date = '';
        $to_date = '';
        if($date1 == '' && $date2 == '')
        {
                $from_date = $start_date;
                $to_date = $end_date;
        }
        else {
                $from_date = $date1;
                $to_date = $date2;
        }
	
	echo "<br/><br/><br/>";
	echo"<table border='' cellpadding='5'class=\"simple-table report-table\" width=\"100%\" align=\"center\" bgcolor=\"#0099CC\">";
	echo"<tr>";
	echo"<td align=\"center\">";
		$this->db->select('id, name')->from('settings');
                $ins_id = $this->db->get();
                foreach( $ins_id->result() as $row)
                {
                        $row1 = $row->name;
                }
                $this->upload_path= realpath(BASEPATH.'../uploads/logo');
                $file_list = get_filenames($this->upload_path);
		if ($file_list)
        	{
                	foreach ($file_list as $row2)
                	{
                        	$ext = substr(strrchr($row2, '.'), 1);
                        	$my_values = explode('.',$row2);
                        	if($my_values[0] == $row1)
                        	{
                                	//echo "<td align=\"center\">";
                                	echo img(array('src' => base_url() . "uploads/logo/" . $row1.'.'.$ext));
                        	}
                        	$newrep_lace = str_replace('_', ' ', $my_values[0]);
                        	if(($newrep_lace == $row1) && ($my_values[0] != $row1))
                        	{
                                	//echo "<td align=\"center\">";
                                	echo img(array('src' => base_url() . "uploads/logo/" . $my_values[0].'.'.$ext));
                        	}
                	}
        	}

	echo"<br>";
	echo $this->config->item('account_name');echo "<br>"; ?><?php echo $this->config->item('account_address') . "</td>"; ?><?php echo "<td align=\"center\" class=\"bold\" >" . "<h2>" . $statement . "</h2><br>";
	if(($statement != "Balance Sheet")&&($statement != "Balance Sheet MHRD Format")){

	echo	 "For the period " . $from_date . " to " . $to_date ;
	}
	echo"</td>";
	
	echo "<td align=\"center\">" . 'Financial year' . '<br>' . date_mysql_to_php_display($this->config->item('account_fy_start'));
	echo" - " ;
	echo date_mysql_to_php_display($this->config->item('account_fy_end')); 
	
	echo"</td>";

	echo"</tr>";

	echo"</table>";
	?>
	<?php
		if(isset($isSchedule))
                        $this->load->view($report, $arr);
                else
                        $this->load->view($report);

	
	?>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printreport.css">


