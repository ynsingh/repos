<!--@name staffposition.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <head>    
        <?php $this->load->view('template/header'); ?>
      <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>       
      <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css"> 
    </head>

<script>
 $(document).ready(function(){

 /****************************************** start of uofficer********************************/
                $('#wtype').on('change',function(){
                    var workt = $(this).val();
                    //alert(sc_code);
                    if(workt == ''){
                        $('#uoff').prop('disabled',true);
                   
                    }
                    else{
                        $('#uoff').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getuolist_sp",
                            type: "POST",
                            data: {"worktype" : workt},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#uoff').html(data.replace(/^"|"$/g, ''));
                                                 
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of uofficer********************************/

 /****************************************** start of deptarment********************************/
                $('#uoff').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    //alert(sc_code);
                    var wrktypeuo = wtcode+","+uoid;
                    if(wtcode == ''){
                        $('#dept').prop('disabled',true);
                   
                    }
                    else{
                        $('#dept').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getdeptlist_sp",
                            type: "POST",
                            data: {"worktypeuo" : wrktypeuo},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#dept').html(data.replace(/^"|"$/g, ' '));
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 
                /******************************************end of department********************************/

 /****************************************** start of designation*******************************/
                $('#dept').on('change',function(){
                    var wtcode = $('#wtype').val();
                    var uoid = $('#uoff').val();
                    var dept = $('#dept').val();
                    //alert(sc_code);
                    var wtuodept = wtcode+","+uoid+","+dept;
                    // alert(wtuodept);
                    if(dept == ''){
                        $('#post').prop('disabled',true);
                   
                    }
                    else{
                        $('#post').prop('disabled',false);
                        $.ajax({
                           // url: "<?php echo base_url();?>sisindex.php/staffmgmt/getcombdesiglist",
                            url: "<?php echo base_url();?>sisindex.php/report/getuodeptpostlist_sp",
                            type: "POST",
                            data: {"wtuodept" : wtuodept},
                            dataType:"html",
                            success:function(data){
                            //alert("data==1="+data);
                                $('#post').html(data.replace(/^"|"$/g, ' '));
                            },
                            error:function(data){
                                //alert("data in error==="+data);
                                alert("error occur..!!");
                 
                            }
                        });
                    }
                }); 

                /****************************************** start post********************************/
             /*   $('#wtype').on('change',function(){
                    var workt = $(this).val();
                   //alert("post====="+workt);
                    if(workt == ''){
                        $('#post').prop('disabled',true);

                    }
                    else{
                        $('#post').prop('disabled',false);
                        $.ajax({
                            url: "<?php echo base_url();?>sisindex.php/report/getpostlist_sp",
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
                }); */
            });

            function verify(){
                var x=document.getElementById("wtype").value;
		var y=document.getElementById("uoff").value;
		var z=document.getElementById("dept").value;
                var w=document.getElementById("post").value;
                if((x == 'null' && y == 'null') || (x == '' && y == '')||(y == 'null')||(x == 'null')){
                    alert("please select UO option for search !!");
                    return false;
                };


            }
</script>

    <body>

<table width="100%">
            <tr colspan="2">
         <?php
	    echo "<td align=\"left\" width=\"33%\">";
		$roleid=$this->session->userdata('id_role');
                if(($roleid == 1)){
	            echo anchor('staffmgmt/newstaffposition/', ' Staff Position ', array('class' => 'top_parent'));
		}
            echo "</td>";
            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Staff Position Details</b>";
            echo "</td>";
            echo "<td align=\"right\" width=\"33%\">";
	    $help_uri = site_url()."/help/helpdoc#ViewStaffPosition";
            echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
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
        <?php if  (isset($_SESSION['err_message'])){?>
	     <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
	<?php
	};
	?>
 </div>
 </td></tr>
 </table>
<form action="<?php echo site_url('staffmgmt/staffposition');?>" id="myForm" method="POST" class="form-inline">
          <table width="100%" border="0">
            <tr style="font-weight:bold;width:100%;">
                <td>  Select Working Type<br>
                    <select name="wtype" id="wtype" style="width:200px;">
			<?php if  (!empty($this->wtyp)){ ?>
                        <option value="<?php echo $this->wtyp; ?>" > <?php echo $this->wtyp; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>- Select Working Type -</option>
                          <?php  } ?>
                      <option value="Teaching">Teaching</option>
                      <option value="Non Teaching"> Non Teaching</option>
                    </select>
 		</td>
               <td>  Select UO<br>
                    <select name="uoff" id="uoff" style="width:200px;">
                      <option value="" disabled selected>-- Select University officer--</option>
                    </select>
                </td>
                <td>  Select Department<br>
                    <select name="dept" id="dept" style="width:200px;">
                      <option value="" disabled selected>-- Select Department --</option>
                    </select>

                </td>
           <!--     </tr>
                <tr style="font-weight:bold;">-->
                <td> Select Designation<br>
                    <select name="post" id="post" style="width:200px;">
                      <option  value="" disabled selected>-- Select Designation --</option>
                    </select>

                </td>

		 <?php
              //  echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp";
                ?>
                <!--</td>
                <td> --> <!-- Select Post
                    <select name="post" id="post">
			<?php  if((!empty($this->desigm))&&($this->desigm != 'All')){ ?>
                        <option value="<?php echo $this->desigm; ?>" > <?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$this->desigm)->desig_name ." ( ". $this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$this->desigm)->desig_code ." )"; ?></option>
                        <?php  }else{ ?>
                      <option value="" disabled selected>---------- Select Post -----------------</option>
                         <?php  } ?>
                     <!-- <option value="All" >All</option> -->
                    </select>
               <!-- </td>-->
                <td>
                    <input type="submit" name="filter" id="crits" value="Search"  onClick="return verify()"/>
                </td>
            </tr>
        </table><br>
        <div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
		<th> Sr.No </th>
		<th> Campus Name </th>
		<th> U O Control </th>
		<th> Department Name </th>
		<th> Scheme Name (Scheme Code) </th>
		<th> Working Type </th>
		<th> Employee Type </th>
		<th> Employee Post </th>
		<th> Pay Band </th>
		<th> Method of Recruitment </th>
		<th> Sanction Strength Position </th>
		<th> Present Position </th>
		<th> Vacant Position </th>
		<th> Action </th>
	</thead>
	<tbody>
	<?php $count = 0;
		 if( count($records) ) {
                foreach ($records as $row)
                {
//		print_r($row);die;
		?>    
			<tr>
			 <td><?php echo ++$count; ?> </td>
                         <td><?php echo $this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $row->sp_campusid)->sc_name; ?> </td>
			 <td><?php echo $this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $row->sp_uo)->name ?> </td>
			 <td><?php echo $this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $row->sp_dept)->dept_name; ?> </td>
			 <td><?php echo $this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id', $row->sp_schemecode)->sd_name ."<br>( ".$this->sismodel->get_listspfic1('scheme_department', 'sd_code', 'sd_id', $row->sp_schemecode)->sd_code . " )";  ?> </td>
			 <td><?php echo $row->sp_tnt ?> </td>
			 <td><?php echo $row->sp_type ?> </td>
			 <td><?php echo $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $row->sp_emppost)->desig_name ?> </td>
			 <td><?php echo $row->sp_scale ?> </td>
			 <td><?php echo $row->sp_methodRect ?> </td>
			 <td><?php echo $row->sp_sancstrenght ?> </td>
			 <td><?php echo $row->sp_position ?> </td>
			 <td><?php echo $row->sp_vacant ?> </td>
		        <td><?php 
		//		$roleid=$this->session->userdata('id_role');
				if($roleid == 1){	
					echo anchor('staffmgmt/editstaffposition/' . $row->sp_id , "Edit", array('title' => 'Edit Details' , 'class' => 'red-link')); 
				}
			?> </td> 	
		      </tr>
	<?php  } 
	}else{
  	?>  
        <tr><td colspan= "13" align="center"> No Records found...!</td></tr>
       <?php }?>
	</tbody>
        </table>
        </div><!------scroller div------> 
   </body>
<p> &nbsp; </p>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
