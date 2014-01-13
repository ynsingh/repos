<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printreport.css">

	<?php 
	echo "<tr valign=\"top\">";
	echo "<table border='0' cellpadding='3'class=\"simple-table report-table\" width=\"70%\">";

		echo "<tr><td align=\"left\">";
		$this->upload_path= realpath(BASEPATH.'../uploads/logo');
		$file_list = get_filenames($this->upload_path);
                if ($file_list)
                {
                        foreach ($file_list as $row)
                        {
				echo img(array('src' => base_url() . "uploads/logo/" . $row)); 
			}
		}
		?>
		<br>
		<?php echo $this->config->item('account_name'); ?><br><?php echo $this->config->item('account_address') . "</td>"; ?><?php echo "<td align=\"center\" class=\"bold\" >" . "<h2>" . $title . "</h2><br>" . "For the period " . $this->session->userdata('date1') . " to " . $this->session->userdata('date2') . "</td>" ; echo "<td align=\"right\">" . 'Financial year' . '<br>' . date_mysql_to_php_display($this->config->item('account_fy_start')); ?> - <?php echo date_mysql_to_php_display($this->config->item('account_fy_end')); ?><?php echo "</td></tr>";?>

	<?php echo"</table>";?>
		<?php $this->load->view($report); ?>
		<br>
	<form><input class="hide-print" type="button" onClick="window.print()" value="Print Statement"></form>
