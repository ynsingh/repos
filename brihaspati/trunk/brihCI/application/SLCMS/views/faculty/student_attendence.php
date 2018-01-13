<!--@filename student_attendence.php  @author sumit saxena(sumitsesaxena@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to IGNTU :Attendence</title>
      	
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">

	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.min.css">
  	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
	
	<script>
	function getsemester(sem){
	        var sem = sem;
		//alert(sem);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/facultyhome/semester_get",
                data: {"program_branch" : sem},
                dataType:"html",
                success: function(data){
                	$('#semester').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	function getsubject(combid){
		var prgid = $('#program_branch').val();
                var semid = $('#semester').val();
                var combid = prgid+","+semid;
		//alert(combid);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/facultyhome/subject_get",
                data: {"semprg" : combid},
                dataType:"html",
                success: function(data){
                	$('#subjectname').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	function getpaper(prgsemsubid){
	        var prgid = $('#program_branch').val();
                var semid = $('#semester').val();
		var subid = $('#subjectname').val();
                var prgsemsubid = prgid+","+semid+","+subid;
		//alert(prgsemsubid);
                $.ajax({
                type: "POST",
		url: "<?php echo base_url();?>slcmsindex.php/facultyhome/paper_get",
                data: {"prg_sem_sub" : prgsemsubid},
                dataType:"html",
                success: function(data){
                	$('#papername').html(data.replace(/^"|"$/g, ''));
		}
             });
        }

	</script>
    </head>
    <body>
        <div>
            <?php $this->load->view('template/header'); ?>
        </div>
		<?php
                    echo "<table style=\"width:100%;\">";
                    echo "<tr valign=\"top\">";
		    echo "<td align=left>";
                    $help_uri = site_url()."/facultyhome/student_attendence_view";
                    echo "<a href=$help_uri><b>View Student Attendence</b></a>";
		    echo "<td>";
		    echo "<b>Student Attendence</b>";	 
		    echo "</td>";
                    echo "</td>";
                    echo "<td align=right>";
                    $help_uri = site_url()."/help/helpdocfaculty#StudentList";
                    echo "<a href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
                    echo "</tr>";
                    echo "</table>";
                 ?>
	<?php 
	    if(!empty($_SESSION['success'])){	
		if(isset($_SESSION['success'])){?>
         <div class="isa_success" style="font-size:18px;"><?php echo $_SESSION['success'];?></div>
         <?php
          } };
         ?>
	
        <?php if(isset($_SESSION['err_message'])){?>
             <div class="isa_error"><div ><?php echo $_SESSION['err_message'];?></div></div>
        <?php
        };
	?> 	

      </div>
        
	
	<form action="<?php echo site_url('facultyhome/student_attendence');?>" method="POST">
        <table style="width:100%;" >
	
            <tr style="font-weight:bold; background-color:lightslategray;">

                <td> <span style="color:white;">Select Program :</span>
                    <select name="program_branch" id="program_branch" onchange="getsemester(this.value);" required>
			<option selected="true" disabled>Select Program</option>
			 <?php foreach($prgsublist as $prgdata): ?>	
                            <option value="<?php echo $prgdata->pstp_prgid; ?>">
				<?php echo $this->cmodel->get_listspfic1('program','prg_name','prg_id',$prgdata->pstp_prgid)->prg_name."&nbsp;"."(".
                                $this->cmodel->get_listspfic1('program','prg_branch','prg_id',$prgdata->pstp_prgid)->prg_branch.")" ;?></option> 
 			<?php endforeach; ?>
		
		    </select>
                </td>
                <td><span style="color:white;">Semester :</span>
			<select name="semester" id="semester" onchange="getsubject(this.value);" required>
				<option selected="true" disabled>semester</option>
		
		   	</select>		
                </td>
                <td><span style="color:white;">Subject Name :</span>
                    <select name="subjectname" id="subjectname" onchange="getpaper(this.value);" required> 
                        <option selected="selected" disabled selected>subject name</option>
		
                    </select>
                </td>
		<td><span style="color:white;">Subject Paper Name :</span>
                    <select name="papername" id="papername" onchange="" required> 
                        <option selected="selected" disabled selected>subject paper name</option>
		
                    </select>
                </td>
		

		<td>
			  <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/jquery-ui.min.css">
  			  <script type="text/javascript" src="<?php echo base_url();?>assets/js/jquery-ui.min.js" ></script>

		<input id="adate" type="text" name="adate" value="<?php echo isset($_POST["adate"]) ? $_POST["adate"] : ''; ?>"  placeholder="Select Today Date" readonly required/>

<script>
$('#adate').datepicker({
    onSelect: function(value, ui) {
        console.log(ui.selectedYear)
        var today = new Date(), 
            dob = new Date(value), 
            age = 2017-ui.selectedYear;
		
   // $("#age").text(age);
    },
   //  maxDate: '+0d',
     changeMonth: true,
     changeYear: true,
     dateFormat: "yy-mm-dd",
     defaultDate: '1yr',
     yearRange: 'c-37:c+30',

});
$("#adate").datepicker().datepicker("setDate", new Date());
</script>
		<td>
                </td>
		<td><input type="submit" name="search" value="Search" /></td>
            </tr>
	<tr>
		 <td colspan=12><span style="color:black;float:right;font-weight:bold;">Academic Year:&nbsp;&nbsp <?php echo $academicyear;?></span></td>
	</tr>
	

        </table><br/>
<?php $i=0;?>	
	<table style="width:100%;">
	<tr>
	<td style="float:right;font-size:20px;">
		<span>Select Class :</span>
                    <select name="classtype" id="cltype" onchange=""> 
                        <option selected="selected" disabled selected>Select Class Type</option>
			<option value="R">Regular</option>
			<option value="E">Extra</option>	
                    </select>
	
	
	<form name="tfrm" method="POST" >
		Toggle
			<select name="attendence<?php echo $i;?>" id="first_box">
				<option value='P'>P</option>
				<option value='A'>A</option>
				<option value='L'>L</option>
			<select>
		<script>
		$('#first_box').change(function(){
    			$(".second_box").not(this).val(this.value);
		});
	</script>
		
	</form>
	</td></tr></table>
	</br>
        <table style="width:100%" class="TFtable" id='getrecord'>
            <thead>
                <tr style="text-align: center;">
                     <th>Sr.No</th>
                    <th>Student Name</th>
                    <th>Enrollment No</th>
                    <th>Roll No</th>
		    <th style="width:20%;">Attendence </br>P(present) A(absent) L(leave)</th>
				
                </tr>
            </thead>
             <tbody align=center>
		               
		<?php $count=1;
		 if(!empty($getdata)){
		 foreach($getdata as $getname):
			$studname = $this->cmodel->get_listspfic1('student_master','sm_fname','sm_id',$getname->sp_smid)->sm_fname;
			$studenrollno = $this->cmodel->get_listspfic1('student_master','sm_enrollmentno','sm_id',$getname->sp_smid)->sm_enrollmentno;
			$studrollno = $this->cmodel->get_listspfic1('student_entry_exit','senex_rollno','senex_smid',$getname->sp_smid)->senex_rollno;
		?>
		<tr>
            	<td><?php echo $count++;?></td>
		<td><?php echo $studname; ?></td>
		<td><?php echo $studenrollno;?></td>
		<td><?php echo $studrollno;?></td>
		
		<td> 
	<!--<form action="<?php echo site_url('Studenthome/student_attendence');?>" method="POST">--->
			<select name="attendence<?php echo $i;?>" class="second_box">
				<option value='P'>P</option>
				<option value='A'>A</option>
				<option value='L'>L</option>
			<select>
		</tr>
		
	<?php $i++; endforeach; }  

		else{  	
		?>
		
		 <tr><td colspan= "5" align="center"> No Records found...!</td></tr>
		<?php }  ?>
		
		<tr>
			<?php  $i=0; if(!empty($getdata)){
				foreach($getdata as $getname):
				$sccode = $this->cmodel->get_listspfic1('student_program','sp_sccode','sp_smid',$getname->sp_smid)->sp_sccode;
				$studdepart = $this->cmodel->get_listspfic1('student_program','sp_deptid','sp_smid',$getname->sp_smid)->sp_deptid;
			?>	
				<input type="hidden" name="stu_master_id<?php echo $i;?>" value="<?php echo $getname->sp_smid;?>">
				<input type="hidden" name="studycenter" value="<?php echo $sccode;?>">
				<input type="hidden" name="department" value="<?php echo $studdepart;?>">
				
			<?php $i++; endforeach; ?>
				<input type="hidden" name="count" value="<?php echo $i;?>">
				<input type="hidden" name="semester" value="<?php echo $this->sem;?>">
				<input type="hidden" name="program_branch" value="<?php echo $this->prgid;?>">
				<input type="hidden" name="subjectname" value="<?php echo $this->subjectid?>">
				<input type="hidden" name="papername" value="<?php echo $this->paperid;?>">
			<?php } ?>	
			<td  colspan=5 ><input type="submit" name="Submit" value="Submit" style="float:right;margin-right:120px;"/></td>	
			
		</tr>
		
            </tbody>  
        </table> 
	
	 </form> 
     
        <!--?= $this->pagination->create_links();?-->
        <div><?php $this->load->view('template/footer'); ?></div>  
    </body>
</html>
