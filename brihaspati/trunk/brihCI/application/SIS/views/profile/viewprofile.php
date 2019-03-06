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

            <div>
                <?php $this->load->view('template/header'); ?>

            </div> 		    
                    <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "<table style=\"width: 100%;\">";
                    echo "<tr valign=\"top\">";
                    echo "<td>";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>View Profile Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                    $help_uri = site_url()."/help/helpdoc#ViewProfile";
                    echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                    ?>
 <table>
            <tr colspan="2">
                <td>
                    <div>
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
           
    
</table>

<table style="width:100%;" class="heading1" border=0>
      <tr>
           <td>   
                 <div class="container2">
                <tr>
                 <td class="heading1" colspan=2><p><b>Welcome, User</b></p></td>
                 </tr><tr>
                  <td class="heading1" colspan=2><p>Login Information</p></td></tr>
		</div>
                  <div class="profile-content">
                 <div class="left-content">
                 <tr>
		     <td >Currently Logged In As :</td> 
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
                    <td >Current Role :</td> 
                    <td>
		    <?php  echo $this->currentrole->role_name ;?>
	            </td>
                 </tr>  
		 <tr>
                    <td >University Name :</td>
                    <td><?php if(!empty($this->orgname->org_name)){
				echo $this->orgname->org_name ;
				}else{
				echo $orgname;
				}
			?></td>
                 </tr>
                 <tr>
                     <td >Campus Name :</td>
                     <td><?php  
			if(!empty($this->campusname->sc_name)){
				echo $this->campusname->sc_name ;
			}else if (!empty($campusname)){
				echo $campusname;
			}
			else{
				echo $scname;
			}
		?></td>
                 </tr>
	         </div>
                 </div>
                <!-- </div> -->
	  </td>
     </tr>
</table>
<?php $empid=$this->session->userdata('id_emp'); ?>
<table style="width:100%;" class="heading1" border=0>
		<tr>
		<td>
		<div class="container2">
		<tr>
                  <td class="heading1" colspan=2>Other Information
		<!--	<a href="editprofile" title="Edit Profile" ><img src="<?php //echo base_url(); ?>uploads/icons/edit.png" width="20" height="20"></a>-->
			</td>
		 </tr>
		</div>
		<div class="profile-content">
                <div class="left-content">
		<tr>
                   <td> Name :</td> 
                   <td >
                   <?php  
				$dexist=$this->sismodel->isduplicate('employee_master','emp_id',$this->session->userdata('id_emp'));
				if($dexist){
					echo $this->sismodel->get_listspfic1('employee_master','emp_name','emp_id',$this->session->userdata('id_emp'))->emp_name; 
				}
				else{
					echo $name;
				}
//				else get userid
//	 get empcode from uolist
// get empdetails from employee master
		?>
	<?php		//	$this->name->firstname ;?>&nbsp;&nbsp;<?php //echo  $this->lastn->lastname ;
//			echo $this->session->userdata('id_dept');
//			 echo "( ". $this->commodel->get_listspfic1('Department','dept_name','dept_id' ,$this->session->userdata('id_dept'))->dept_name ." )";
		?></td>
                </tr>
		<tr>
		<td> Designation : </td>
		<td>
		<?php  
			 if($dexist){
			 $desigid=$this->sismodel->get_listspfic1('employee_master','emp_desig_code','emp_id' ,$empid)->emp_desig_code; 
			echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$desigid)->desig_name;
			}else{
				echo $designame;
			}
		?>	
		</td>
		</tr>
		<tr>
                   <td>Address :</td>
                   <td >
                   <?php  
			if(!empty($this->address->address)){
				echo $this->address->address ;
			}else if(!empty($address)){
				echo $address;
			}
			else{
				echo $deptname;
			}
		?></td>
                </tr>
	        <tr>
                   <td>Mobile :</td>
                   <td>
                   <?php  
			if(!empty($this->mobile->mobile)){
			echo $this->mobile->mobile ;
			}
			else{
				echo $mobile;
			}
		?></td>
               </tr>
               <tr>
                   <td>Email :</td> 
                   <td ><?php  //echo //$this->currentlog; 

			if(!empty($this->email->email)){
			echo $this->email->email ;
			}else{
			echo $email;
			}
		?></td>
               </tr>
	    <!--   <tr>
                   <td>Secondary Email :</td>
                   <td><?php  //echo $this->email->email ;//$this->secmail->secmail ;?></td>
               </tr> -->
               </div>
               </div> 
      <!--         </div>-->
          </td>
      </tr>
</table>
<!--</div>-->
</body>
<div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
