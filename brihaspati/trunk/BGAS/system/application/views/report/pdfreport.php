<?php
	//start code for generating pdf..........
	tcpdf();
	$obj_pdf = new TCPDF('P', PDF_UNIT, PDF_PAGE_FORMAT, true, 'UTF-8', false);
	$obj_pdf->SetCreator(PDF_CREATOR);
	$title = "PDF Report";
	$obj_pdf->SetTitle($title);
	$obj_pdf->setHeaderFont(Array(PDF_FONT_NAME_MAIN, '', PDF_FONT_SIZE_MAIN));
	$obj_pdf->setFooterFont(Array(PDF_FONT_NAME_DATA, '', PDF_FONT_SIZE_DATA));
	$obj_pdf->SetDefaultMonospacedFont('helvetica');
	$obj_pdf->SetHeaderMargin(PDF_MARGIN_HEADER);
	$obj_pdf->setPrintHeader(false);
	$obj_pdf->SetFooterMargin(PDF_MARGIN_FOOTER);
	$obj_pdf->setPrintFooter(false);
	$obj_pdf->SetMargins(PDF_MARGIN_LEFT, PDF_MARGIN_TOP, PDF_MARGIN_RIGHT);
	$obj_pdf->SetAutoPageBreak(TRUE, PDF_MARGIN_BOTTOM);
	$obj_pdf->SetFont('helvetica', '', 9);
	$obj_pdf->setFontSubsetting(false);
	$obj_pdf->AddPage();
	ob_start();
	

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
	echo"<table border='' cellpadding='5'class=\"simple-table report-table\" width=\"100%\" align=\"center\">";
	echo"<tr>";
	echo"<td align=\"left\">";
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
                                        echo img(array('src' => base_url() . "uploads/logo/" . $row1.'.'.$ext));
                                }
                        }
                }
	echo"<br>";
	echo $this->config->item('account_name');echo "<br>"; ?><?php echo $this->config->item('account_address') . "</td>"; ?><?php echo "<td align=\"center\" class=\"bold\" >" . "<h2>" . $statement . "</h2><br>";
	if(($statement != "Balance Sheet")&&($statement != "Balance Sheet MHRD Format")){

	echo	 "For the period " . $from_date . " to " . $to_date ;
	}
	echo"</td>";
	
	echo "<td align=\"right\">" . 'Financial year' . '<br>' . date_mysql_to_php_display($this->config->item('account_fy_start'));
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


<?php
	$content = ob_get_contents();
	ob_end_clean();
	$obj_pdf->writeHTML($content, true, false, true, false, '');
	$obj_pdf->Output($statement.'.pdf', 'I');

?>