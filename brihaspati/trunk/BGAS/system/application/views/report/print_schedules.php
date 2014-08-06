<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printreport.css">

        <?php
                echo "<tr valign=\"top\">";
                echo "<table border='0' cellpadding='3'class=\"simple-table report-table\" width=\"70%\">";

                echo "<tr><td align=\"left\">";
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
                                echo "<br/>";
                                echo"&nbsp;&nbsp;&nbsp;&nbsp;";
                                echo $this->config->item('account_ins_name');
                                }
                        }
                }
                else{
                echo "<br/>";
                echo "<br/>";
                echo "<br/>";
                echo "<br/>";
                echo "<p align=\"justify\">" . "&nbsp;" . $this->config->item('account_ins_name') . "</p>";
                }

        ?>
        <br>
        <?php
        $date1 = $this->session->userdata('date1');
        $date2 = $this->session->userdata('date2');
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
        echo $this->config->item('account_name'); ?><br><?php echo $this->config->item('account_address') . "</td>"; ?><?php echo "<td align=\"center\" class=\"bold\" >" . "<h2>Schedules </h2><br>" . "For the period " . $from_date . " to " . $to_date . "</td>"; echo "<td align=\"right\">" . 'Financial year' . '<br>' . date_mysql_to_php_display($this->config->item('account_fy_start')); ?> - <?php echo date_mysql_to_php_display($this->config->item('account_fy_end')); ?><?php echo "</td></tr>";?>

        <?php echo"</table>";?>

        <?php
		$count = 1;
	        foreach($arr as $data)
        	{
                	echo "<h2> Schedule - ".$count." " .$data['name']."</h2>";
	                //echo "<br>";
        	        if($data['name'] == 'General Funds' || $data['name'] == 'Reserves and Surplus')
                	        $this->load->view('report/schedule_template_1', $data);
	                else
        	                $this->load->view('report/schedule_template',$data);

                	echo "<br><br>";
	                $count++;
        	}
		
        ?>
                <br>
        <form><input class="hide-print" type="button" onClick="window.print()" value="Print Statement"></form>

