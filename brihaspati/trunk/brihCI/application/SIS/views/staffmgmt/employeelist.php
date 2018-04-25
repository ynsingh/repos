<!--@filename employeelist.php  @author Manorama Pal(palseema30@gmail.com) 
    modification in gui - 16 OCT 2017 
-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
	<script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">   
    </head>
<script>
$(document).ready(function(){
                /****************************************** start post********************************/
                $('#wtype').on('change',function(){
                    var workt = $(this).val();
                   //alert("post====="+workt);
                    if(workt == ''){
                        $('#post').prop('disabled',true);

                    }
                    else{
                        $('#post').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getdesiglist",
                            type: "POST",
                            data: {"worktype" : workt},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#post').html(data.replace(/^"|"$/g, ''));

                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");

                            }
                        });
                    }
                });
            });

            function verify(){
                var x=document.getElementById("wtype").value;
                var y=document.getElementById("post").value;
                if((x == 'null' && y == 'null') || (x == '' && y == '')||(y == 'null')||(x == 'null')){
                    alert("please select option for search !!");
                    return false;
                };


            }
</script>
    <body>
            <?php $this->load->view('template/header'); ?>
           
        <table width="100%"><tr colspan="2">
        <?php 
        echo "<td align=\"left\" width=\"33%\">";
	$roleid=$this->session->userdata('id_role');
        if(($roleid == 1)||($roleid == 5)){
		echo anchor('staffmgmt/staffprofile/', "Add Profile" ,array('title' => 'Add staff profile ' , 'class' => 'top_parent'));
	}
        echo "</td>";
        echo "<td align=\"center\" width=\"34%\">";
        echo "<b>Staff Profile Details</b>";
        echo "</td>";
        echo "<td align=\"right\" width=\"33%\">";
	$help_uri = site_url()."/help/helpdoc#ViewEmployeeList";
        echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
        echo "</td>";
        ?>
        <div>     
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
                
	        <?php if(isset($_SESSION['success'])){?>
                    <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
                ?>    
                  
        </div>
</tr>
        </table>
<form action="<?php echo site_url('staffmgmt/employeelist');?>" id="myForm" method="POST" class="form-inline">
          <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
                <td>  Select Working Type
                    <select name="wtype" id="wtype">
				<?php if  (!empty($this->wtyp)){ ?>
                        <option value="<?php echo $this->wtyp; ?>" > <?php echo $this->wtyp; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>------- Select Working Type -------</option>
                          <?php  } ?>
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>
                    </select>
		<?php
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp";
		?>
                <!--</td>
                <td> --> Select Post
                    <select name="post" id="post">
			<?php if  ((!empty($this->desigm))&&($this->desigm != 'All')){ ?>
                        <option value="<?php echo $this->desigm; ?>" > <?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$this->desigm)->desig_name ." ( ". $this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$this->desigm)->desig_code ." )"; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>---------- Select Post -----------------</option>
			 <?php  } ?>
                     <!-- <option value="All" >All</option> -->
                    </select>
               <!-- </td>
                <td>-->
                    <input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/>
                </td>
            </tr>
        </table><br>
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Sr.No</th>
                    <th></th>
                    <th>Employee Name</th>
                    <th>Campus Name</th>
                    <th>University Officer Control</th>
                    <th>Department Name</th>
                    <th>Scheme Name</th>
                    <th>Specialisation(Major Subject)</th>
                    <th>Designation</th>
                    <!--<th>Employee Post</th>-->
                   <!-- <th>Pay Band</th>-->
                    <th>E-Mail ID</th>
                    <th>Contact No</th>
                    <th>Aadhaar No</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <?php $serial_no = 1;?>
              <?php if( count($records) ):  ?>
                    <?php foreach($records as $record){ ?>
                        <tr>
                            <td><?php echo $serial_no++; ?></td>
                            <?php //$img=$record->emp_code;?>
                            <?php if(!empty($record->emp_photoname)):?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/'.$record->emp_photoname);?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php else :?>
                            <td><p><img src="<?php echo base_url('uploads/SIS/empphoto/empdemopic.png');?>"  alt="" v:shapes="_x0000_i1025" width="78" height="94"></p></td>
                            <?php endif;?>
			    <td><?php echo anchor("report/viewfull_profile/{$record->emp_id}",$record->emp_name." ( "."PF No:".$record->emp_code." )" ,array('title' => 'View Employee Profile' , 'class' => 'red-link'));?></td>
                           <!-- <td><?php //echo $record->emp_name."<br/>" ."("."PF No:".$record->emp_code.")"; ?></td> -->
                            <td><?php echo $this->commodel->get_listspfic1('study_center','sc_name','sc_id',$record->emp_scid)->sc_name; ?></td>
                            <td><?php echo $this->lgnmodel->get_listspfic1('authorities','name','id' ,$record->emp_uocid)->name; ?></td>
                            <td><?php echo $this->commodel->get_listspfic1('Department','dept_name','dept_id',$record->emp_dept_code)->dept_name; ?></td>
                            <?php if(!empty($record->emp_schemeid)):?>
                            <td><?php echo $this->sismodel->get_listspfic1('scheme_department','sd_name','sd_id',$record->emp_schemeid)->sd_name; ?></td>
                            <?php else : ?>
                            <td></td>
                            <?php endif;?>
                            <?php if(!empty($record->emp_specialisationid)) :?>
                            <td><?php echo $this->commodel->get_listspfic1('subject','sub_name','sub_id',$record->emp_specialisationid)->sub_name; ?></td>
                            <?php else : ?>
                            <td></td>
                            <?php endif;?>
                            <td><?php echo $this->commodel->get_listspfic1('designation','desig_name','desig_id',$record->emp_desig_code)->desig_name; ?></td>
			   <!-- <td><?php //echo $record->emp_post; ?></td>-->
                           <!-- <td></td>-->
                            <td><?php echo $record->emp_email; ?></td>
                            <td><?php echo $record->emp_phone; ?></td>
                            <td><?php echo $record->emp_aadhaar_no; ?></td>
                            <td> <?php 
		//		$roleid=$this->session->userdata('id_role');
                                if(($roleid == 1)||($roleid == 5)){
					echo anchor("staffmgmt/editempprofile/{$record->emp_id}","View/Edit",array('title' => 'View/Edit Details' , 'class' => 'red-link')); 
				}
				?></td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "13" align="center"> No Records found...!</td>
                <?php endif;?>
		</tbody>
        </table>
        </div><!------scroller div------>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
        
    </body>    
</html>   
