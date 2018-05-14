<!--@name editscholar.php
    @author Krishna Sahu(krishnasahu2406@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Scholarship</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/datepicker/jquery-ui.css">
        <script type="text/javascript" src="<//?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-ui.js" ></script>
<script>

function goBack() {
        window.history.back();
        }
    </script>
</head>

    <body>
<!--table id="uname" border="2"><tr><td align=center>WELCOME <?= $this->session->userdata('username') ?>  </td></tr></table-->
        <table width="100%">
            <tr><td>
              <div>
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?></div>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div>
        </td></tr>
        </table>
		  <table  style="width:100%; border:1px solid gray;text-align:left;" >
		  <tr><thead><?php
					 echo "<td align=\"center\" style=\"background-color:#2a8fcf;text-align:left;height:40px;font-size:20px;\" colspan=\"10\">";
					 echo "<b>Scholarship Verification</b>";
					 echo "</td>";
					?>
		  </thead></tr>
		  
 <?php
	echo form_open ('scholarship/schvarify/'.$id);
	

		 echo "<tr>";
            echo "<td>";
                    echo form_label('Student Name', 'sm_fname');
               // echo "</td>";
		echo "<br>";
		//echo "</tr>";
		//echo "<tr>";
               // echo "<td>";
                    echo form_input($sm_fname);
                echo "</td>";
            //echo "</tr>";

	   // echo "<tr>";
                echo "<td>";
                    echo form_label('Mother Name', 'spar_mothername');
		echo "<br>";
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
               // echo "<td>";
                    echo form_input($spar_mothername);
                echo "</td>";
            //echo "</tr>";

	   //echo "<tr>";
                echo "<td>";
                    echo form_label('Father Name', 'spar_fathername');
                //echo "</td>";
	//	echo "</tr>";
	//	echo "<tr>";
          //      echo "<td>";
		echo "<br>";
                    echo form_input($spar_fathername);
                echo "</td>";
           // echo "</tr>";
	
	echo "<tr>";
                echo "<td>";
                    echo form_label('Gender', 'sm_gender');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($sm_gender);
                echo "</td>";
           // echo "</tr>";

	//echo "<tr>";
                echo "<td>";
                    echo form_label('Date of Birth', 'sm_dob');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($sm_dob);
                echo "</td>";
           // echo "</tr>";

	  // echo "<tr>";
                echo "<td>";
                    echo form_label('Category', 'cat_name');
               // echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($cat_name);
                echo "</td>";
          echo "</tr>";
	
	echo "<tr>";
                echo "<td>";
                    echo form_label('Semester', 'sp_semester');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($sp_semester);
                echo "</td>";
            //echo "</tr>";

	//echo "<tr>";
                echo "<td>";
                    echo form_label('Address', 'spar_caddress');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
		echo "<br>";
                //echo "<td>";
                    echo form_input($spar_caddress);
                echo "</td>";
           // echo "</tr>";
	
	//echo "<tr>";
                echo "<td>";
                    echo form_label('City', 'spar_ccity');
               // echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($spar_ccity);
                echo "</td>";
            echo "</tr>";

	echo "<tr>";
                echo "<td>";
                    echo form_label('State', 'spar_cstate');
               // echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($spar_cstate);
                echo "</td>";
            //echo "</tr>";

	//echo "<tr>";
                echo "<td>";
                    echo form_label('Country', 'spar_ccountry');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
               // echo "<td>";
		echo "<br>";
                    echo form_input($spar_ccountry);
                echo "</td>";
            //echo "</tr>";

	//echo "<tr>";
                echo "<td>";
                    echo form_label('Pincode', 'spar_cpincode');
               // echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
               // echo "<td>";
		echo "<br>";
                    echo form_input($spar_cpincode);
                echo "</td>";
            echo "</tr>";
	
	echo "<tr>";
                echo "<td>";
                    echo form_label('Mobile', 'sm_mobile');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($sm_mobile);
                echo "</td>";
            //echo "</tr>";

	//echo "<tr>";
                echo "<td>";
                    echo form_label('Aadhaar Number', 'sm_uid');
               // echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($sm_uid);
                echo "</td>";
            //echo "</tr>";
	
	//echo "<tr>";
                echo "<td>";
                    echo form_label('Email-Id', 'sm_email');
               // echo "</td>";
	//	echo "</tr>";
	//	echo "<tr>";
          //      echo "<td>";
		echo "<br>";
                    echo form_input($sm_email);
                echo "</td>";
            echo "</tr>";

	echo "<tr>";
                echo "<td>";
                    echo form_label('Department Name', 'dept_name');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($dept_name);
                echo "</td>";
            //echo "</tr>";

	//echo "<tr>";
                echo "<td>";
                    echo form_label('Religion', 'sm_religion');
               // echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($sm_religion);
                echo "</td>";
           // echo "</tr>";
	//echo "<tr>";
                echo "<td>";
                    echo form_label('Branch Name', 'prg_branch');
               // echo "</td>";
	//	echo "</tr>";
	//	echo "<tr>";
          //      echo "<td>";
		echo "<br>";
                    echo form_input($prg_branch);
                echo "</td>";
            echo "</tr>";

	echo "<tr>";
                echo "<td>";
                    echo form_label('Program Name', 'prg_name');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";
                    echo form_input($prg_name);
                echo "</td>";
//            echo "</tr>";

	    //  echo "<tr>";
                //echo "<td>";
                  //  echo form_label('Last Year Result', 'sa_lastyerres');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		//echo "<br>";
                  //  echo form_input($sa_lastyerres);
                //echo "</td>";
//            echo "</tr>";

//	      echo "<tr>";
                echo "<td>";
                    echo form_label('Scholarship Name', 'sa_name');
  //              echo "</td>";
	//	echo "</tr>";
	//	echo "<tr>";
          //      echo "<td>";
		echo "<br>";         
          	 echo form_input($sa_name);
                echo "</td>";
           // echo "</tr>";


         // echo "<tr>";
                echo "<td>";
                    echo form_label('Bank Name', 'sa_bname');
              //echo "</td>";
	      //echo "</tr>";
	      //echo "<tr>";
              //echo "<td>";
		echo "<br>";            
       		   echo form_input($sa_bname);
                echo "</td>";
           // echo "</tr>";


          echo "<tr>";
		echo "<td>";
                    echo form_label('Account Number', 'sa_accno');
                    //echo "<br />";
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";                
		    echo form_input($sa_accno);
                echo "</td>";
                
            //echo "</tr>";
		
              //  echo "<tr>";
                echo "<td>";
                    echo form_label('IFSC Number', 'sa_ifscno');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";                
		    echo form_input($sa_ifscno);
                echo "</td>";
          //  echo "</tr>";
       		
            //echo "<tr>";
                echo "<td>";
                    echo form_label('Bank Branch Name', 'sa_branch');
                //echo "</td>";
		//echo "</tr>";
		//echo "<tr>";
                //echo "<td>";
		echo "<br>";                  
		  echo form_input($sa_branch);
                echo "</td>";
            echo "</tr>";
	?>	
   </td>
<?php
		echo "<tr>";
                echo "<td>";
                echo "</td>";
                echo "<td>";
                echo form_hidden('sa_id', $id);?>
   		<tr style="background-color:#2a8fcf;text-align:left;height:40px;">
	            <td colspan="10">
		<input type="Submit" name="approved" value="Approve" onclick="return confirm('Are you sure you want to approve Scholarship request')" />

		<input type="Submit" name="rej" value="Reject" />
   		 <?php echo form_close();
                echo "<button onclick=\"goBack()\" >Back</button>";
                echo "</td>";
                echo "</tr>";
?>
  </tr> 
 


       </table>

</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

                                                                                                                                                                        
