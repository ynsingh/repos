<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<!--@name viewprofile.php 
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<html>
<head>
 <title>View Profile</title> 
 <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/profile.css">
</head>
<body>
                <?php $this->load->view('template/header'); ?>
		<!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
                <?php 
			if($this->session->userdata('id_role') == 1){
				$this->load->view('template/menu');
			}
			if($this->session->userdata('id_role') == 2){
				$this->load->view('template/facultymenu');
			}
			if($this->session->userdata('id_role') == 3){
				$this->load->view('template/stumenu');
			}

		?>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
                    <!--?php
                    echo "<table style=\"padding: 20px 8px 8px 20px;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    $help_uri = site_url()."/help/helpdoc#ViewProfile";
                    echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;font-size:17px;margin-left:54%;position:absolute;margin-top:-1%\">Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?-->
 <table width="100%">
            <tr colspan="2"><td>
<?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b>View Profile</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#ViewProfile";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
		    echo "</tr>";
		    echo "</table>";
?>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                   <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>
                    <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                    <?php
                    };
                ?>
                </div>
            </td>
     </tr>
</table>

<table>
      <tr>
           <td>   
                 <div class="profile-main"><b>Welcome, User</b></div>
                 <div class="container2">
                 <div class="heading1">
                 <p>Login Information</p>
                 </div>
	         <div class="profile-content">
                 <div class="left-content">
                 <tr>
		     <td style="padding: 8px 8px 8px 334px;">Currently Logged In As :</td> 
 		      <td>
		      <?php  
                      echo $this->currentlog;
                      echo "&nbsp;";
		      echo "("; 
                      echo anchor('home/logout', 'Logout', array('title' => 'Logout'));
		      echo ")";
		      ?>                                         
		     </td>
	         </tr>
	         <tr>
                    <td style="padding: 8px 8px 8px 334px;">Current Role :</td> 
                    <td>
		    <?php  echo $this->currentrole->role_name ;?>
	            </td>
                 </tr>  
		 <tr>
                    <td style="padding: 8px 8px 8px 334px;">University Name :</td>
                    <td><?php  echo $this->orgname->org_name ;?></td>
                 </tr>
                 <tr>
                     <td style="padding: 8px 8px 8px 334px;">Campus Name :</td>
                     <td><?php  echo $this->campusname->sc_name ;?></td>
                 </tr>
	         </div>
                 </div>
                 </div>
	  </td>
     </tr>
</table>
<table>
        <tr>
             <td>
		<div class="heading2">
                <p>Other Information</p>
		<div class="heading-col2"><a href="editprofile" title="Edit Profile"><img src="<?php echo base_url(); ?>uploads/icons/edit.png" width="20" height="20"></a></div>
                </div>
		<div class="profile-content">
                <div class="left-content">
		<tr>
                   <td style="padding: 8px 8px 8px 334px;">Name :</td> 
                   <td style="padding: 8px 8px 8px 75px">
                   <?php  echo $this->name->firstname ;?>&nbsp;&nbsp;<?php echo  $this->lastn->lastname ;?></td>
                </tr>
		<tr>
                   <td style="padding: 8px 8px 8px 334px;">Address :</td>
                   <td style="padding: 8px 8px 8px 75px">
                   <?php  echo $this->address->address ;?></td>
                </tr>
	        <tr>
                   <td style="padding: 8px 8px 8px 334px;">Mobile :</td>
                   <td style="padding: 8px 8px 8px 75px">
                   <?php  echo $this->mobile->mobile ;?></td>
               </tr>
               <tr>
                   <td style="padding: 8px 8px 8px 334px;">Email :</td> 
                   <td style="padding: 8px 8px 8px 75px"><?php  echo $this->email->email ;?></td>
               </tr>
	       <tr>
                   <td style="padding: 8px 8px 8px 334px;">Secondary Email :</td>
                   <td style="padding: 8px 8px 8px 75px"><?php  echo $this->secmail->secmail ;?></td>
               </tr>
               </div>
               </div> 
               </div>
          </td>
      </tr>
</table>
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
