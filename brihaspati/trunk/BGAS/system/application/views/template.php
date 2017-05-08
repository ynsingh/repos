<?php  if ( ! defined('BASEPATH')) exit('No direct script access allowed');?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>BGAS<?php if (isset($page_title)) echo ' | ' . $page_title; ?></title>

<?php echo link_tag(asset_url() . 'images/favicon.ico', 'shortcut icon', 'image/ico'); ?>

<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/style.css">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/tables.css">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/custom.css">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/menu.css">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/jquery.datepick.css">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/thickbox.css">
<link type="text/css" rel="stylesheet" href="<?php echo asset_url(); ?>css/loading.css">
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
	var jsSiteUrl = '<?php echo base_url(); ?>';
</script>

<script type="text/javascript" src="<?php echo asset_url(); ?>js/jquery.min.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>js/jquery.datepick.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>js/custom.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>js/hoverIntent.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>js/superfish.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>js/supersubs.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>js/thickbox-compressed.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>js/ezpz_tooltip.min.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>js/shortcutslibrary.js"></script>
<script type="text/javascript" src="<?php echo asset_url(); ?>js/shortcuts.js"></script>
<script type="text/javascript">var BASE_URI = "<?php echo base_url(); ?>";</script>
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
<script>
   $(document).ready(function(){
	   $(window).bind('scroll', function() {
	   //var navHeight = $( window ).height() - 35;
	   var navHeight =125 - 35;
			 if ($(window).scrollTop() > navHeight) {
				 $('nav').addClass('fixed');
			 }
			 else {
				 $('nav').removeClass('fixed');
			 }
		});
	});
</script>
<style>
.chcol1{
        animation-name: blink;
        animation-duration: 4s;
        animation-iteration-count: infinite;
        animation-direction: alternate;
        text-decoration: none;
}

@keyframes blink {
        0%   {color:Turquoise;}
        10%   {color:SteelBlue;}
        20%   {color:SlateBlue;}
        30%   {color:SkyBlue;}
        40%   {color:RoyalBlue;}
        50%   {color:PowderBlue;}
        60%   {color:MediumTurquoise;}
        70%   {color:MediumSlateBlue;}
        80%   {color:MediumBlue;}
        90%   {color:DodgerBlue;}
        100%  {color:DeepSkyBlue;}
}
</style>

</head>
<body>
<div id="container">
	<div id="header1">
		<div id="admin">
		<?php
			if(isset($schedule) || isset($help))
			{
			}
			else
			{
				if ($this->session->userdata('user_name')) 
				{
					echo anchor('', 'Accounts', array('title' => "Accounts", 'class' => 'anchor-link-b'));
					echo " | ";
					/* Check if allowed administer rights */
					if (check_access('administer')) {
						echo anchor('admin', 'Administer', array('title' => "Administer", 'class' => 'anchor-link-b'));
						echo " | ";
					}
                                	if (check_access('manager')) 
					{
                                        	echo anchor('user/aggregator', 'Aggregater', array('title' => "Aggregater", 'class' => 'anchor-link-b'));
                                        	echo " | ";
                                	}
					echo anchor('user/profile', 'Profile', array('title' => "Profile", 'class' => 'anchor-link-b'));
					echo " | ";
					echo anchor('user/logout', 'Logout', array('title' => "Logout", 'class' => 'anchor-link-b'));
				}
			}
		?>
		</div>

		<div id="logo">
		<?php
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
				$newrep_lace = str_replace('_', ' ', $my_values[0]);
                                if(($newrep_lace == $row1) && ($my_values[0] != $row1))
                                {
                                	echo img(array('src' => base_url() . "uploads/logo/" . $my_values[0].'.'.$ext));
                                	echo "<br/>";
                                	echo"&nbsp;&nbsp;&nbsp;&nbsp;";
                                	echo $this->config->item('account_ins_name');
                                }
                        }
                }
                else
		{
                	echo "<br/>";
                	echo "<br/>";
                	echo "<br/>";
                	//echo "<br/>";
                	echo "<p align=\"justify\">" . "&nbsp;" . $this->config->item('account_ins_name') . "</p>";
                }
		?>
		</div>

		<div id="logo1">
		<?php $end_uri = site_url();?>
		<?php //echo anchor('', 'Brihaspati General Accounting System', array('class' => 'anchor-link-b'));?>
		<?php echo "<a class=\"chcol1\" href=$end_uri>Brihaspati General Accounting System</a>";?>
		</div>

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


	<div id="header">
	<?php 
	//Check added by Priyanka
	if(isset($schedule) || isset($help)){}
	else{?> 
	<nav>
	<?php
		echo "<ul class=\"sf-menu\">";
			echo "<li class=\"current\">";
				//echo "<a href=" . base_url() . "title=\"Dashboard\">Dashboard</a>";
				echo anchor(base_url(), 'Dashboard', array('title' => 'Dashboard'));
			echo "</li>";
			echo "<li>";
				echo anchor('budgetl', 'Budgets', array('title' => 'Budget accounts'));
			echo "</li>";
			echo "<li>";
				echo anchor('account', 'Chart Of Accounts', array('title' => 'Chart of accounts')); 
			echo "</li>";
			echo "<li>";
					/* Showing Entry Type sub-menu */
					$entry_type_all = $this->config->item('account_entry_types');
					$entry_type_count = count($entry_type_all);
					if ($entry_type_count < 1)
					{
						echo "";
					} else if ($entry_type_count == 1) {
						foreach ($entry_type_all as $id => $row)
						{
							echo anchor('entry/show/' . $row['label'], $row['name'], array('title' => $row['name'] . ' Entries'));
						}
					} else {
						echo anchor('entry', 'Vouchers', array('title' => 'Entries'));
						echo "<ul>";
						echo "<li>"; 
							 echo anchor('entry/show/all', 'View All Vouchers', array('title' => 'All Entries'));
						echo "</li>";
						foreach ($entry_type_all as $id => $row)
						{
							// line added by Priyanka
							if($row['name'] == 'Journal'){
								echo "<li>";
								echo anchor('entry/add/journal/' . $row['label'], 'Create ' . $row['name'] . ' Voucher', array('title' => $row['name'] . ' Entries'));
								echo  "</li>";
							}
						}
						  echo "<li>";
                                                        echo anchor('payment/showupload_bill', 'Bill Upload / Voucher Creation', array('title' => 'Bill/Voucher Creation'));
                                                echo "</li>";
						//Link of multiple verification added by @RAHUL 
                        			echo "<li>";
                        			echo anchor('payment2/showupload_bill_approval', 'Bill Upload / Voucher Creation With Multiple Varification', array('title' => 'Bill/Voucher Creation'));
                        			echo "</li>";

						echo "</ul>";
					}
				
			echo "</li>";
			echo "<li>";
				$chart_account = $this->config->item('chart_account');
				echo anchor('report', 'Reports', array('title' => 'Reports')); 
				echo "<ul>";
					echo "<li>";
						//echo  anchor('report/balancesheet', 'Balance Sheet', array('title' => 'Balance Sheet', ));
						echo  anchor('','Balance Sheet');
						 echo "<ul>";
							echo "<li>";
								//echo  anchor('report/balancesheet', 'Corporate Format', array('title' => 'Balance Sheet', 'class' => 'loading'));
								echo anchor(prep_url( base_url().'index.php/report/balancesheet'), 'Corporate Format', 'target="_blank"');
							echo "</li>";
							echo "<li>";
								if($chart_account == "mhrd"){
								 //echo anchor('report/new_balancesheet', 'MHRD Format', array('title' => 'Balance Sheet MHRD Format', 'class' => 'loading'));
								
								echo anchor(prep_url( base_url().'index.php/report/new_balancesheet'), 'MHRD Format','target="_blank"');
								}
								if($chart_account == "mhrd2015"){							
							//echo "</li>";
							//echo "<li>";
                                //echo anchor('report/new_mhrd', 'New MHRD Format', array('title' => 'Balance Sheet MHRD Format-2015', 'class' => 'loading'));
                                echo anchor(prep_url( base_url().'index.php/report/new_mhrd'), 'MHRD Format 2015','target="_blank"');  
                                                        }
                                                        echo "</li>";

						 echo "</ul>";
					echo "</li>";
				//	echo "<li>"; 
				//		echo anchor('report/new_balancesheet', 'Balance Sheet MHRD Format', array('title' => 'Balance Sheet MHRD Format', 'class' => 'loading')); 
				//	echo "</li>";
					echo "<li>";
                                                echo  anchor('','Income & Expenditure');
                                                 echo "<ul>";
                                                        echo "<li>";
                                                             //echo anchor('report/profitandloss', 'Corporate Format', array('title' => 'Income & Expenditure', 'class' => 'loading'));
                                                        	echo anchor(prep_url( base_url().'index.php/report/profitandloss'), 'Corporate Format', 'target="_blank"');
                                                        echo "</li>";
                                                        echo "<li>";
                                                        if($chart_account == "mhrd"){
                                                               // echo anchor('report2/profitandloss_mhrd', 'MHRD Format', array('title' => 'Income & Expenditure', 'class' => 'loading'));
                                                        		echo anchor(prep_url( base_url().'index.php/report2/profitandloss_mhrd'), 'MHRD Format', 'target="_blank"');
                                                        }
                                                        //echo "</li>";
                                                        //echo "<li>";
                                                        if($chart_account == "mhrd2015"){
                                                               // echo anchor('report2/profitandloss_mhrd', 'MHRD Format', array('title' => 'Income & Expenditure', 'class' => 'loading'));
                                                        		echo anchor(prep_url( base_url().'index.php/report2/profitandloss_mhrdnew'), 'MHRD Format 2015', 'target="_blank"');
                                                        }
                                                        echo "</li>";
                                                  echo "</ul>";
                                         echo "</li>";
					echo "<li>";
						//echo anchor('report/paymentreceipt', 'Payment & Receipt', array('title' => 'Payment & Receipt', 'class' => 'loading'));
						echo anchor(prep_url( base_url().'index.php/report/paymentreceipt'), 'Payment & Receipt', 'target="_blank"');
					echo "</li>";
					echo "<li>";
						echo anchor('report/trialbalance', 'Trial Balance', array('title' => 'Trial Balance')); 
					echo "</li>";
					echo "<li>";
						echo anchor('report/ledgerst', 'Ledger Statement', array('title' => 'Ledger Statement'));
					echo "</li>";
					echo "<li>";
						echo anchor('report/reconciliation/pending', 'Reconciliation', array('title' => 'Reconciliation'));
					echo "</li>";
					echo "<li>";
						echo anchor('report/depreciation', 'Depreciation as today', array('title' => 'Depreciation as today'));
					echo "</li>";
					echo "<li>";
						echo anchor('report/dayst', 'Day Book', array('title' => 'Day Book'));
					echo "</li>";
					echo "<li>";
                                                echo anchor('report/cashst', 'Cash/Bank Book', array('title' => 'Cash /Bank Book'));
                                        echo "</li>";
					echo "<li>";
						echo anchor('','Unspent Balance');
						echo "<ul>";
							echo "<li>";
								//echo anchor('unspentbalance/planreport', 'Plan Report', array('title' => 'Plan Report', 'class' => 'loading'));
								 echo anchor(prep_url( base_url().'index.php/unspentbalance/planreport'), 'Plan Report', 'target="_blank"');
							echo "</li>";
							echo "<li>";
								//echo anchor('unspentbalance/nonplanreport', 'Non-Plan Report', array('title' => 'Non-Plan Report', 'class' => 'loading'));
								echo anchor(prep_url( base_url().'index.php/unspentbalance/nonplanreport'), 'Non-Plan Report', 'target="_blank"');
							echo "</li>";
							echo "<li>";
								//echo anchor('unspentbalance/summaryreport', 'Summary Report', array('title' => 'Summary Report', 'class' => 'loading'));
								echo anchor(prep_url( base_url().'index.php/unspentbalance/summaryreport'), 'Summary Report', 'target="_blank"');
							echo "</li>";
						echo "</ul>";
					echo "</li>"; 
				echo "</ul>";
			echo "</li>";
			echo"<li>";

				echo anchor('report2','Other Report',array('title'=>'Other Report'));
				echo"<ul>";
					echo"<li>";
						echo anchor('report2/fundlist','Fund List', array('title' =>'Fund List'));

					echo"</li>";
                                        echo"<li>";

						echo anchor('report2/tag','Tag Report', array('title'=>'Tag Report'));
					echo"</li>";
					 echo"<li>";
                                                echo anchor('report2/sec_report','Party Report', array('title'=>'Party Report'));
                                        echo"</li>";
					echo "<li>";
                                                echo anchor('','Log Report');
                                                echo "<ul>";
                                                        echo "<li>";
                                                                echo anchor('log/LogReport/COA', 'Chart Of Account', array('title' => 'Chart Of Account', 'class' => 'anchor-link-a'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                                echo anchor('log/LogReport/TrnsLog', 'Transaction Log', array('title' => 'Transaction Log', 'class' => 'anchor-link-a'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                                echo anchor('log/LogReport/BugtLog', 'Budget Log', array('title' => 'Budget Log', 'class' => 'anchor-link-a'));
                                                        echo "</li>";
                                                        echo "<li>";
                                                                echo anchor('log/LogReport/OtherLog', 'Other Log', array('title' => 'Other Log', 'class' => 'anchor-link-a'));
                                                        echo "</li>";
                                                echo "</ul>";
                                        echo "</li>";
					echo"<li>";
                                                echo anchor('report2/tds_report','TDS Report', array('title'=>'TDS Report'));
                                        echo"</li>";
					echo"<li>";
                                                echo anchor('report2/sundry_credit_report','Sundry Creditors Report', array('title'=>'Sundry Creditors Report'));
                                        echo"</li>";
					echo"<li>";
                                                echo anchor('report2/sundry_debit_report','Sundry Debtors Report', array('title'=>'Sundry Debtors Report'));
                                        echo"</li>";

					echo"<li>";
                                                echo anchor('report2/negative_trans_report','Negative Transaction Report', array('title'=>'Negative Transaction Report'));
                                        echo"</li>";

				echo"</ul>";

			echo "<li>";
				echo anchor('setting', 'Settings', array('title' => 'Settings')); 
			echo "</li>";

			echo "<li>";
                               echo anchor('payrollsetup', 'Payroll Setup', array('title' => 'PayrollSetup',)); 
                        echo "</li>";
			echo "<li>";
				//echo anchor('addparty', 'Secondary Accounting System', array('title' => 'Secondary Accounting System'));
				echo anchor('addparty', 'Party', array('title' => 'Party Id'));
				echo "<ul>";
					echo "<li>";
					echo anchor('addparty/show', 'View Party', array('title' => 'ViewParty'));
					echo "</li>";
					echo "<li>";
					echo anchor('addparty/add', 'Add Party', array('title' => 'AddParty'));
					echo "</li>";
				echo "</ul>";
                        echo "</li>";

			echo "<li>";
				echo anchor('help', 'Help', array('title' => 'Help',)); 
				echo "<ul>";
				echo "<li>";
					echo anchor('help/FAQ', 'FAQ', array('title' => 'FAQ',));
				echo "</li>";
					echo "<li>" . anchor('help/helpdoc', 'User Mannual', array('title' => 'User Mannual',)) . "</li>";
				echo "</ul>";
			echo "</li>";

			echo "<li>";
                                echo anchor('changepassword', 'Change Password', array('title' => 'changepassword', 'class' => 'last')); 
                        echo "</li>";
		echo "</ul>";
		?>
	</nav>
	<?php
	}
	?>

	<?php if(isset($schedule) || isset($help)){ 
	echo "<div id=\"content\" style=\"margin-top:20px\">";
	}
	else{
	echo "<div id=\"content\">";
	} ?>
		<div id="sidebar">
			<?php if (isset($page_sidebar)) echo $page_sidebar; ?>
			</div>
		<div id="main">
			<div id="main-title">
				<?php if (isset($page_title)) echo $page_title; ?>
			</div>
			<div class='popup'>
				<div class='cnt223'>
					<p>
						<img src="<?php echo base_url(); ?>images/loadingAnimation.gif" alt="loading...">
					</p>
				</div>
			</div>
			<?php if (isset($nav_links)) {
				echo "<div id=\"main-links\">";
				echo "<ul id=\"main-links-nav\">";
				foreach ($nav_links as $link => $title) {
					if ($title == "Print Preview")
						echo "<li>" . anchor_popup($link, $title, array('title' => $title, 'class' => 'nav-links-item', 'style' => 'background-image:url(\'' . asset_url() . 'images/buttons/navlink.png\');', 'width' => '1024')) . "</li>";
					else
						echo "<li>" . anchor($link, $title, array('title' => $title, 'class' => 'nav-links-item', 'style' => 'background-image:url(\'' . asset_url() . 'images/buttons/navlink.png\');')) . "</li>";
				}
				echo "</ul>";
				echo "</div>";
			} ?>
			<div class="clear">
			</div>
			<div id="main-content">
				<?php
				$messages = $this->messages->get();
				if (is_array($messages))
				{
					if (count($messages['success']) > 0)
					{
						echo "<div id=\"success-box\">";
						echo "<ul>";
						foreach ($messages['success'] as $message) {
							echo ('<li>' . $message . '</li>');
						}
						echo "</ul>";
						echo "</div>";
					}
					if (count($messages['error']) > 0)
					{
						echo "<div id=\"error-box\">";
						echo "<ul>";
						foreach ($messages['error'] as $message) {
							if (substr($message, 0, 4) == "<li>")
								echo ($message);
							else
								echo ('<li>' . $message . '</li>');
						}
						echo "</ul>";
						echo "</div>";
					}
					if (count($messages['message']) > 0)
					{
						echo "<div id=\"message-box\">";
						echo "<ul>";
						foreach ($messages['message'] as $message) {
							echo ('<li>' . $message . '</li>');
						}
						echo "</ul>";
						echo "</div>";
					}
				}
				?>
				<?php echo $contents; ?>
			</div>
		</div>
	</div>
<div id="footer">
	<?php if (isset($page_footer)) echo $page_footer ?>
<a href="/~brihaspati/BGAS/ListOfDocument.html" target="_blank">Importants Links</a> Based on <a href="http://webzash.org" target="_blank"> Webzash<a/> and licensed is <a href="/~brihaspati/BGAS/brihaspati-license.txt" target="_blank">BGAS License</a> and <a href="/~brihaspati/BGAS/acknowledgement.txt" target="_blank">BGAS Acknowledgement</a>
</div>
</body>
</html>
