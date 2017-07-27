<!--@name mapsubpre.php  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)  -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
<title>Subject Semester Program with Department List</title>
    <head>    
       	<?php $this->load->view('template/header'); ?>
        <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	<?php $this->load->view('template/menu');?> 
	<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
	<script>
	function getbranchname(branch){
                var branch = branch;
                //alert(branch);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/map/branchlist",
                data: {"programname" : branch},
                dataType:"html",
                success: function(data){
                //alert(data);
                $('#spreq_prgid').html(data.replace(/^"|"$/g, ''));
                }
            }); 
	}

	function getsubject(subj){
            var subj = subj;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/map/subjectlist",
                data: {"spreq_prgid" : subj},
                dataType:"html",
                success: function(data){
                $('#spreq_subid').html(data.replace(/^"|"$/g, ''));
                }
             });
        }

        function getpapername(paper){
                var paper = paper;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/map/paperlist",
                data: {"spreq_subid" : paper},
                dataType:"html",
                success: function(data){
                $('#spreq_subpid').html(data.replace(/^"|"$/g, ''));
                }
            });
	}

	</script>
    </head>    
    <body>
       
        <!--<//?php
           echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Map";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            
            echo anchor('map/viewscprgseat/', "View Study Center Program Seat ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Map Study Center Program Seat";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>-->
         </br>    
        <table width="100%"> 
            
            <?php echo form_error('<div style="margin-left:2%;" class="isa_error">','</div>');?>
 
            <tr><td>  
                <div style="margin-left:2%;">    
                <?php echo anchor('map/prerequisite/', "Subject With Prerequisite List ", array('title' => 'View Detail' , 'class' => 'top_parent'));
		$help_uri = site_url()."/help/helpdoc#";
		echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:65%\">Click for Help</b></a>";
		?>
                </div>
                <div align="left" style="margin-left:2%;">
                 
                <?php echo validation_errors('<div style="margin-left:2%;" class="isa_warning">','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div style="margin-left:2%;" class="isa_success"><?php echo $_SESSION['success'];?></div>

                <?php
                };
                ?>
                 <?php if(isset($_SESSION['err_message'])){?>

                    <div style="margin-left:2%;"  class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                <?php
                };
                ?>    
               
            </div>
        </td></tr>  
        </table>  
        <br/>
            
	<form action="<?php echo site_url('map/mapsubpre');?>" method="POST" class="form-inline">
	<table style="margin-left:2%;">
		<tr>
           	<td>Program Name :</td>
           	<td>
                <select name="programname" id="programname" class="my_dropdown" style="width:100%;" onchange="getbranchname(this.value)" >
                <option value="" disabled selected >------Select Program Name--------------</option>
                <?php foreach($prgresult as $dataspt): ?>
                <option value="<?php echo $dataspt->prg_name ?>"><?php echo $dataspt->prg_name; ?></option>
                <?php endforeach; ?>
           	</td>
		</tr>

        	<tr>
           	<td>Branch Name :</td>
           	<td>
                <select name="spreq_prgid" id="spreq_prgid" class="my_dropdown" style="width:100%;" onchange="getsubject(this.value)">
                <option value="" disabled selected >------Select Branch Name--------------</option>
           	</td>
		</tr>

		<tr>
                <td>Subject Name :</td>
                <td>
                <select name="spreq_subid" id="spreq_subid" class="my_dropdown" style="width:100%;" onchange="getpapername(this.value)" >
                <option value="" disabled selected >------Select Subject Name--------------</option>
                <?php //foreach($subres as $dataspt): ?>
<!--                <option value="<?php //echo $dataspt->sub_id ?>"><?php //echo $dataspt->sub_name; ?></option> -->
                <?php //endforeach; ?>
                </td>
                </tr>

		<tr>
                <td>Prerequisite Subject Name :</td>
                <td>
                <select name="spreq_subdepid" id="spreq_subdepid" class="my_dropdown" style="width:100%;" >
                <option value="" disabled selected >------Select Subject Prerequisite Name--------------</option>
                <?php foreach($subres as $dataspt): ?>
                <option value="<?php echo $dataspt->sub_id ?>"><?php echo $dataspt->sub_name; ?></option>
                <?php endforeach; ?>
                </td>
                </tr>

		<tr>
                <td>Subject Paper Name :</td>
                <td>
                <select name="spreq_subpid" id="spreq_subpid" class="my_dropdown" style="width:100%;" >
                <option value="" disabled selected >------Select Subject Paper Name--------------</option>
                <?php //foreach($subpres as $dataspt): ?>
            <!--    <option value="<?php //echo $dataspt->subp_id ?>"><?php //echo $dataspt->subp_name; ?></option> -->
                <?php //endforeach; ?>
                </td>
                </tr>

                <tr>
                <td>Prerequisite Subject Paper Name :</td>
                <td>
                <select name="spreq_subpdepid" id="spreq_subpdepid" class="my_dropdown" style="width:100%;" >
                <option value="" disabled selected >------Select Subject Prerequisite Paper Name--------------</option>
                <?php foreach($subpres as $dataspt): ?>
                <option value="<?php echo $dataspt->subp_id ?>"><?php echo $dataspt->subp_name; ?></option>
                <?php endforeach; ?>
                </td>
                </tr>

            	<tr>
               	<td></td>
                <td colspan="2">   
                <button name="mapsubpre" >Submit</button>
                <button name="reset" >Clear</button>
                </td>
            	</tr>
        </form>    
        </table>
    </body> 
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
