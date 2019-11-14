<!--@name subjectteacher.php
    @author Om Prakash(omprakashkgp@gmail.com)
 -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>  
    <title>Add Teacher List</title>    
        <?php $this->load->view('template/header'); ?>
       
        <?php //$this->load->view('template/menu');?>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
    </head>
    <body>
<!-- <table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
    <script>
	function getdepartment(val){
		var val=val;
	   	$.ajax({
		type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/map/getdeptlist",
		data: {"campusname" : val},
		dataType:"html",
		success: function(data){
		$('#deptname').html(data.replace(/^"|"$/g, ''));
		}
	     });
	   }
 
	function getteacherlist(tl){
	        var tl=tl;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/map/teacherlist",
                data: {"deptname" : tl},
                dataType:"html",
                success: function(data){
                $('#teachername').html(data.replace(/^"|"$/g, ''));
                }
             });
        }
     
	  /*function getsubject(subj){
            var subj = subj;
	   	$.ajax({
	    	type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/map/subjectlist",
                data: {"branchname" : subj},
		dataType:"html",
		success: function(data){
                $('#subjectname').html(data.replace(/^"|"$/g, ''));
		}
	     });	
        }
      
	function getpapername(paper){
           	var paper = paper;
	   	$.ajax({
	    	type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/map/paperlist",
                data: {"subjectname" : paper},
		dataType:"html",
		success: function(data){
                $('#papername').html(data.replace(/^"|"$/g, ''));
		}
	    });	
        }

    function getbranchname(branch){
                var branch = branch;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/map/branchlist",
                data: {"programname" : branch},
                dataType:"html",
                success: function(data){
                $('#branchname').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }*/

 function getdegreename(branch){
		var branch = branch;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/map/degreelist",
                data: {"subjecttype" : branch},
                dataType:"html",
                success: function(data){
                $('#degree').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }

function getsubj(combid){
	        var sem = $('#subsem_semester').val();
                var degree = $('#programname').val();
                var combid = sem+","+degree;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/map/subList",
                data: {"sem_degree" : combid},
                dataType:"html",
                success: function(data){
                $('#subid').html(data.replace(/^"|"$/g, ''));
                }
             });
        }

function getpaperName(idcomb){
	        var sem = $('#subsem_semester').val();
                var degree = $('#programname').val();
		var sub = $('#subid').val();
                var idcomb = sem+","+degree+","+sub;
		$.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/map/Listofpapers",
                data: {"sem_degree_sub" : idcomb},
                dataType:"html",
                success: function(data){
                $('#papername').html(data.replace(/^"|"$/g, ''));
                }
             });
        }
   </script>	

   <table style="width:100%;">
     <tr>
      <div>
         <?php
            echo "<td align=\"left\" width=\"33%\">";
            echo anchor('map/listsubjectteacher/', "List of Subject and Paper With Teacher" ,array('title' => 'List of Subject and Paper With Teacher' , 'class' => 'top_parent'));
            echo "</td>";

            echo "<td align=\"center\" width=\"34%\">";
            echo "<b>Add Subject and Paper With Teacher Details</b>";
            echo "</td>";

                echo "<td align=\"right\" width=\"33%\">";
	    $help_uri = site_url()."/help/helpdoc#MapSubjectandPaperwithTeacher";
            echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;\">Click for Help</b></a>";
         ?>
      </div>
      </tr>
           </table>
           <table width="100%">
          <tr><td>
      <div>
          <?php echo validation_errors('<div class="isa_warning">','</div>');?>
          <?php echo form_error('<div class="isa_error">','</div>');?>
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
     </td></tr>
   </table>
   <div> 
   <form id="myform" action="<?php echo site_url('map/subjectteacher');?>" method="POST" class="form-inline">
   <table>
  	<tr>
            <td>Campus Name :</td>
            <td>
            	<select name="campusname" id="campusname" class="my_dropdown" style="width:300px;" onchange="getdepartment(this.value)">
                <option value="" disabled selected >------Select Campus Name---------------</option>
		<?php foreach($scresult as $dataspt): ?>
		<!--<option value="<?php //echo $dataspt->sc_id; ?>" ><?php //echo $dataspt->sc_name; ?></option> -->
    <option value="<?php echo $dataspt->org_code; ?>" ><?php echo $dataspt->org_name; ?></option>
                <?php endforeach; ?>
           </td>
      </tr>
      <tr>
           <td>Department Name :</td>
           <td>
           	<select name="deptname" id="deptname" class="my_dropdown" style="width:300px;" onchange="getteacherlist(this.value)" >
                <option value="" disabled selected >------Select Department Name---------------</option>
           </td>
      </tr>
      <tr>
      	  <td>Academic Year :</td>
          <td>
        	<select name="academicyear" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select Academic year-------------</option>
                <?php
                for($i = date("Y")-2; $i < date("Y")+3; $i++){
                    $j=$i+1;
                    echo '<option value="'.$i.'-'.$j.'">'.$i.' - '.$j.'</option>';
                    }
                    ?>
                </select>
        </td>
       </tr>
       <tr>
           <td>Program Name :</td>
           <td>
           	<select name="programname" id="programname" class="my_dropdown" style="width:300px;" onchange="getbranchname(this.value)" >
                <option value="" disabled selected >------Select Program Name--------------</option>
                <?php foreach($pnresult as $dataspt): ?>
                <option value="<?php echo $dataspt->prg_id; ?>"><?php echo $dataspt->prg_name.'('.$dataspt->prg_branch.')';; ?></option>
		<?php endforeach; ?>
	   </td>
       </tr>
       <!--- <tr>
           <td>Branch Name :</td>
           <td>
                <select name="branchname" id="branchname" class="my_dropdown" style="width:300px;" onchange="getsubject(this.value)">
                <option value="" disabled selected >------Select Subject Name--------------</option>
           </td>
        </tr>--->
       <tr>
           <td>Semester :</td>
           <td>
          	<select name="semester" id="subsem_semester" class="my_dropdown" style="width:300px;" onchange="getsubj(this.value)">
                <option value="" disabled selected >------Select Semester----------------</option>
                <option value="1" class="dropdown-item"> 1 </option>
                <option value="2" class="dropdown-item"> 2 </option>
                <option value="3" class="dropdown-item"> 3 </option>
                <option value="4" class="dropdown-item"> 4 </option>
                <option value="5" class="dropdown-item"> 5 </option>
                <option value="6" class="dropdown-item"> 6 </option>
                <option value="7" class="dropdown-item"> 7 </option>
                <option value="8" class="dropdown-item"> 8 </option>
                <option value="9" class="dropdown-item"> 9 </option>
                <option value="10" class="dropdown-item"> 10 </option>
                </select>
           </td>
        </tr>
        <tr>
	   <td>Subject Name :</td>
           <td>
        	<select name="subjectname" id="subid" class="my_dropdown" style="width:300px;" onchange="getpaperName(this.value)">
                <option value="" disabled selected >------Select Subject Name--------------</option>
           </td>
        </tr>
        <tr>
           <td>Paper Name :</td>
           <td>
           	<select name="papername" id="papername" class="my_dropdown" style="width:300px;" >
                <option value="" disabled selected >------Select Paper Name---------------</option>
		</td>
        </tr>
	<tr>
	   <td>Teacher Name :</td>
	   <td>
           	<select name="teachername" id="teachername" class="my_dropdown" style="width:300px;">
                <option value="" disabled selected >------Select Teacher Name-------------</option>
	   </td>
	</tr>
        <tr>
	    <td></td>
            <td>
                <button name="subjectteacher" >Submit</button>
                <button name="clear" >Clear</button>
            </td>
	    <td></td>
          </tr>
        </table>
      </form>
     </div>		
   </body>
  <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
 
