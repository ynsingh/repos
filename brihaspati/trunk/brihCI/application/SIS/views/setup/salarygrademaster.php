<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name salarygrademaster.php
  @author Rekha Devi Pal(rekha20july@gmail.com)
  @author: Om Prakash (omprakashkgp@gmail.com), Dec-2017 Modification
 -->

<html>
 <head>    
<title>Salary Grade Master</title>
<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
 <script>
        $(document).ready(function(){
                $("#sgname").hide();
//                $("#slevel" ).hide();
                $("#sgpay").hide();
	
		 $('#paycomm').on('change',function(){
                        var pc= $('#paycomm').val();
                        if(pc == '6th'){
                                $("#sgname").show();
                                $("#sgpay").show();
                 //               $("#slevel").hide();
                        }
                        else{
                            //    $("#slevel").show();
                                $("#sgname").hide();
                                $("#sgpay").hide();
                        }
                  });
		

 	});
		function levelofpay(val){
			 var wt= $('#worktypeid').val();
//			alert(wt);
//	                 var val=val;
	                 $.ajax({
                		type: "POST",
		                url: "<?php echo base_url();?>sisindex.php/jslist/getlevelpayi",
                		data: {"wt" : wt},
		                dataType:"html",
                		success: function(data){
//              alert(data);
			                $('#sgmlevel').html(data.replace(/^"|"$/g, ''));
                		}
             		});
           	}

		function payband(val){
			var pc= $('#paycomm').val();
	                var wt=$('#worktypeid').val();
			var val = wt+","+pc;
//	alert(val);
        	        $.ajax({
                		type: "POST",
		                url: "<?php echo base_url();?>sisindex.php/jslist/getpaybandi",
                		data: {"pcwt" : val},
		                dataType:"html",
                		success: function(data){
  //            alert(data);
		                	$('#sgmname').html(data.replace(/^"|"$/g, ''));
                		}
             		});
           	}

 </script>	
 </head>    
   <body>
	<?php $this->load->view('template/header'); ?>

     <table width="100%"> 
       <tr><td>
       	<?php
           echo anchor('setup/displaysalarygrademaster', 'Salary Grade List', array('class' => 'top_parent'));
	  // $help_uri = site_url()."/help/helpdoc#Scheme";
	   //echo "<a target=\"_blank\" href=$help_uri><b style=\"float:right;position:absolute;margin-left:73%\">Click for Help</b></a>";
       	?>
        <div>
        <?php echo validation_errors('<div class="isa_warning">','</div>');?>
        <?php echo form_error('<div class="isa_error">','</div>');?>
        <?php if(isset($_SESSION['success'])){?>
        <div class="alert alert-success"><?php echo $_SESSION['success'];?></div>
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
        <form action="<?php echo site_url('setup/salarygrademaster');?>" method="POST" class="form-inline">
          <table>
		<tr>
		<td><label for="workingtype" class="control-label">Working Type</label>
		</td><td>
                        <div><select id="worktypeid" name="workingtype" required style="width:100%;" onchange="levelofpay(this.value)">
                        <option selected="selected" disabled selected>-------- Working Type ---------</option>
                        <option value="Teaching">Teaching</option>
                        <option value="Non Teaching">Non Teaching</option>
                    </select></div>
                </td>
                </tr>
		<tr>
                        <td><label for="paycomm" class="control-label">Pay Commission:</label></td>
                        <td>
                            <select name="paycomm" id="paycomm" style="width:100%;" onchange="payband(this.value)">
                                <option value="" disabled selected >------Select ---------------</option>
                                <option value="6th">6th</option>
                                <option value="7th">7th</option>
                            </select>
                        </td>
                </tr>
		<tr>
                <td><label for="group" class="control-label" >Group</label>
		</td><td>
                       <div><select name="group" id="grpid" required style="width:100%;">
                        <option selected="selected" disabled selected>------- Select Group ---------</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                    </select></div>
                </td>

                </tr>
            <tr id="sgname">  
                <td><label for="sgmname" class="control-label">Salary Grade Name(Pay Band) :</label></td>
                <td>
			<div>
			<select name="sgmname" id="sgmname" style="width:100%;" >
                        <option disabled selected >------Select----------------</option>
			</select>
                        </div>

<!--                <input type="text" name="sgmname" id="sgmname"  size="40" value="<?php echo isset($_POST["sgmname"]) ? $_POST["sgmname"] : ''; ?>"  class="form-control"  placeholder="Salary Grade Name.."/><br> -->
                </td>
 		<!--<td><?php //echo form_error('sgmname')?></td>--> 
            </tr>
	 <tr id="slevel">
                <td> 
                <label for="sgmlevel" class="control-label">Salary Level of Pay :</label>
		</td>
                <td>
			<div>
		<select name="sgmlevel" id="sgmlevel" style="width:100%;" >           
                <option selected="selected" disabled selected>------ Select Level------</option>
                </select>
                        </div>
                </td>
                      <!--  <option value="Level-1">Level-1</option>
                        <option value="Level-2">Level-2</option>
                        <option value="Level-3">Level-3</option>
                        <option value="Level-4">Level-4</option>
                        <option value="Level-5">Level-5</option>
                        <option value="Level-6">Level-6</option>
                        <option value="Level-7">Level-7</option>
                        <option value="Level-8">Level-8</option>
                        <option value="Level-9">Level-9</option>
                        <option value="Level-10">Level-10</option>
                        <option value="Level-11">Level-11</option>
                        <option value="Level-12">Level-12</option>
                        <option value="Level-13">Level-13</option>
                        <option value="Level-13A">Level-13A</option>
                        <option value="Level-14">Level-14</option>
                        <option value="Level-15">Level-15</option>
                        <option value="Level-16">Level-16</option>
                        <option value="Level-17">Level-17</option>
                        <option value="Level-18">Level-18</option> -->
                 </tr>
            <tr>
                <td>   
                    <label for="sgmmin" class="control-label">Scale of Pay Min :</label>
                </td>
                <td>
                    <input type="text" name="sgmmin" size="40"  value="<?php echo isset($_POST["sgmmin"]) ? $_POST["sgmmin"] : ''; ?>" class="form-control" placeholder="Salary Grade Min.."/> <br>
                </td>
		 <!--<td><?php //echo form_error('sgmmin')?></td>-->
            </tr>
            <tr> 
                <td>    
                <label for="sgmmax" class="control-label">Scale of Pay Max :</label>
                </td>
                <td>
                    <input type="text" name="sgmmax" size="40"  value="<?php echo isset($_POST["sgmmax"]) ? $_POST["sgmmax"] : ''; ?>" class="form-control" placeholder="Salary Grade Max.."/> <br>
                </td>
 		<!--<td><?php //echo form_error('sgmmax')?></td>-->
            </tr>
            <tr id="sgpay">
                <td>   
                <label for="sgmgradepay" class="control-label">Salary Grade Pay :</label>
                </td>
                <td>
                    <input type="text" name="sgmgradepay" id="sgmgradepay" size="40" value="<?php echo isset($_POST["sgmgradepay"]) ? $_POST["sgmgradepay"] : ''; ?>" placeholder="Salary Grade Pay .."/> <br>
                </td>
 		<!--<td><?php //echo form_error('sgmgradepay')?></td>-->
            </tr>
                <tr><td></td>
                <td colspan="2">
                <button name="salarygrademaster" >Add Salary Grade </button>
                <input type="reset" name="Reset" value="Clear"/>
                </td>
            </tr>
           </table>
        </form>
     </tr>
 <p><br></p>
  </body>
<p>&nbsp;</p>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
