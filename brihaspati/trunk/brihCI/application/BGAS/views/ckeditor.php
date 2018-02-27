<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed'); ?>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>BGAS | Edit Notes on Account</title>

	<?php echo link_tag(asset_url() . 'assets/bgas/images/favicon.ico', 'shortcut icon', 'image/ico'); ?>

	<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>assets/bgas/css/style.css">
	<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>assets/bgas/css/tables.css">
	<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>assets/bgas/css/custom.css">
	<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>assets/bgas/css/menu.css">
	<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>assets/bgas/css/jquery.datepick.css">
	<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>assets/bgas/css/thickbox.css">
<?php
/* Dynamically adding css files from controllers */
if (isset($add_css))
{
        foreach ($add_css as $id => $row)
        {
                echo "<link type=\"text/css\" rel=\"stylesheet\" href=\"" . asset_url() . $row ."\">";
        }
}
?>

<script type="text/javascript">
        var jsSiteUrl = '<?php echo asset_url(); ?>';
</script>

<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/jquery.min.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/jquery.datepick.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/custom.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/hoverIntent.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/superfish.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/supersubs.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/thickbox-compressed.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/ezpz_tooltip.min.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/shortcutslibrary.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>assets/bgas/js/shortcuts.js"></script>
<script type="text/javascript">var BASE_URI = "<?php echo asset_url(); ?>";</script>
<?php
/* Dynamically adding javascript files from controllers */
if (isset($add_javascript))
{
        foreach ($add_javascript as $id => $row)
        {
                echo "<script type=\"text/javascript\" src=\"" . asset_url() . $row ."\"></script>";
        }
}
?>

<script type="text/javascript">
/* Loading JQuery Superfish menu */
$(document).ready(function() {
        $("ul.sf-menu").supersubs({ 
                minWidth:12,
                maxWidth:27,
                extraWidth: 1
        }).superfish(); // call supersubs first, then superfish, so that subs are       
        $('.datepicker').datepick({
                dateFormat: '<?php echo $this->config->item('account_date_format'); ?>',
        });
        $('.datepicker-restrict').datepick({
                dateFormat: '<?php echo $this->config->item('account_date_format'); ?>',
                minDate: '<?php echo date_mysql_to_php($this->config->item('account_fy_start')); ?>',
                maxDate: '<?php echo date_mysql_to_php($this->config->item('account_fy_end')); ?>',
        });
        $(".loading").click( function() {
                var overlay = $('<div id="overlay"></div>');
                overlay.show();
                overlay.appendTo(document.body);
                $('.popup').show();
                history.go(0);
        });
});
</script>
</head>

<body>
<div id="container">
        <div id="header">


                <div id="logo">
                <?php echo anchor('', 'Brihaspati General Accounting System', array('class' => 'anchor-link-b'));?>

                </div>
		
		<?php
		echo "<div id=\"admin\">";
                        if ($this->session->userdata('user_name')) {
                                echo anchor('', 'Accounts', array('title' => "Accounts", 'class' => 'anchor-link-b'));
                                echo " | ";
                                /* Check if allowed administer rights */
                                if (check_access('administer')) {
                                        echo anchor('admin', 'Administer', array('title' => "Administer", 'class' => 'anchor-link-b'));
                                        echo " | ";
                                }
                                echo anchor('user/profile', 'Profile', array('title' => "Profile", 'class' => 'anchor-link-b'));
                                echo " | ";
                                echo anchor('user/logout', 'Logout', array('title' => "Logout", 'class' => 'anchor-link-b'));
                        }
                        echo "</div>";
		?>

		<?php
                $this->db->select('id, name')->from('settings');
                $ins_id = $this->db->get();
                foreach( $ins_id->result() as $row)
                {
                        $row1 = $row->name;
                }
                $this->upload_path= realpath(BASEPATH.'../uploads/BGAS/logo');
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
                                echo"&nbsp;&nbsp;&nbsp;&nbsp;";
                                echo"&nbsp;&nbsp;&nbsp;&nbsp;";
                                echo"&nbsp;&nbsp;&nbsp;&nbsp;";
                                echo"&nbsp;&nbsp;&nbsp;&nbsp;";
                                echo "<span style=\"margin-left:964px\">" . $this->config->item('account_ins_name') . "</span>";
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

		<div id="info">
		        <?php
                                echo $this->config->item('account_name');
                                echo " (";
                                echo anchor('user/account', 'change', array('title' => 'Change active account', 'class' => 'anchor-link-a'));
                                echo ")<br />";
                                echo "Acc. Name : ";
                                $current_active_account = $this->session->userdata('active_account');
                                echo ($current_active_account) ? $current_active_account : "(None)";
                                echo "<br/>";
                                echo "FY : ";
                                echo date_mysql_to_php_display($this->config->item('account_fy_start'));
                                echo " - ";
                                echo date_mysql_to_php_display($this->config->item('account_fy_end'));
                                echo "<br/>";
                                echo "Date : ";
                                echo Date("l, d F Y");
                        ?>
                </div>
	</div>

	<br><br><br><br><br>
	<br><br>

	<u><b>EDIT NOTES TO ACCOUNT</b></u>

		<?php 
			echo form_open('ckeditor/addToFile');
		?>

		<div>
        	<?php echo form_textarea($textarea);?>
		</div>

		<div>
        	<?php echo display_ckeditor($ckeditor); ?>
	        </div>

		<br>
		<div>
		<?php
			echo form_submit('submit', 'Save');
			echo " ";
			echo anchor('notes/display_notes', 'Back', array('title' => 'Notes To Account'));
		?>
		</div>	
	
		<?php echo form_close();?>
	<div id="footer">
        <?php if (isset($page_footer)) echo $page_footer ?>
		<a href="/~brihaspati/BGAS/ListOfDocument.html" target="_blank">Importants Links</a> Based on <a href="http://webzash.org" target="_blank"> Webzash<a/> and licensed is <a href="/~brihaspati/BGAS/brihaspati-license.txt" target="_blank">BGAS License</a> and <a href="/~brihaspati/BGAS/acknowledgement.txt" target="_blank">BGAS Acknowledgement</a>
	</div>

</body>

</html>
