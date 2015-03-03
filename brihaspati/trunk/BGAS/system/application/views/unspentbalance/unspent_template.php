<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/printreport.css">


	<?php 
			$this->load->view($report); 
	?>
	<form><input class="hide-print" type="button" onClick="window.print()" value="Print Statement"></form>
