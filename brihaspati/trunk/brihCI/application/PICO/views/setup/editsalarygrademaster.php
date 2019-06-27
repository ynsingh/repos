<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name editsalarygrademaster.php 
  @author Om Prakash(omprakashkgp@gmail.com)
 -->

<html>
<title>editsalarygrademaster</title>
    <head>    
      <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
 <script>

        $(document).ready(function(){
                $("#sgname").hide();
     //           $("#slevel" ).hide();
                $("#sgpay").hide();
        
                 $('#paycomm').on('change',function(){
                        var pc= $('#paycomm').val();
                        if(pc == '6th'){
                                $("#sgname").show();
                                $("#sgpay").show();
                              //  $("#slevel").hide();
                        }
                        else{
                               // $("#slevel").show();
                                $("#sgname").hide();
                                $("#sgpay").hide();
                        }
                  });


        });

	 function levelofpay(val){
                         var wt= $('#worktypeid').val();
//                      alert(wt);
//                       var val=val;
                         $.ajax({
                                type: "POST",
                                url: "<?php echo base_url();?>sisindex.php/jslist/getlevelpay",
                                data: {"wt" : wt},
                                dataType:"html",
                                success: function(data){
  //            alert(data);
                                        $('#sgm_level').html(data.replace(/^"|"$/g, ''));
                                }
                        });
                }

                function payband(val){
                        var pc= $('#paycomm').val();
                        var wt=$('#worktypeid').val();
                        var val = wt+","+pc;
//      alert(val);
                        $.ajax({
                                type: "POST",
                                url: "<?php echo base_url();?>sisindex.php/jslist/getpayband",
                                data: {"pcwt" : val},
                                dataType:"html",
                                success: function(data){
  //            alert(data);
                                        $('#sgm_name').html(data.replace(/^"|"$/g, ''));
                                }
                        });
                }

 </script>   
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>

<script>
        function goBack() {
        window.history.back();
        }
    </script>

      <table width="100%">
            <tr><td>
		<?php
        	   echo anchor('setup/displaysalarygrademaster', 'Salary Grade List', array('class' => 'top_parent'));
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
                          <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                        <?php
                    };
		?>	  

                </div> 
            </td></tr>
        </table>
        <table>  
        <?php
            echo form_open('setup/editsalarygrademaster/'.$sgm_id);
		echo "<tr>";
                echo "<td><label for=\"workingtype\" class=\"control-label\">Working Type:</label></td>";
                echo "<td>";
                echo "<select name=\"workingtype\" id='worktypeid' style=\"width:100%;\" onchange=\"levelofpay(this.value)\">";
                echo "<option value=".$sgm_wt[value].">".$sgm_wt[value]."</option>";
                echo "<option value=\"\" disabled  >------Select ---------------</option>";
                echo "<option value='Teaching'>Teaching</option>";
                echo "<option value='Non Teaching'>Non Teaching</option>";
                echo "</select>";
                echo "</td>";
                echo "</tr>";

		echo "<tr>";
                echo "<td><label for=\"paycomm\" class=\"control-label\">Pay Commission:</label></td>";
                echo "<td>";
                echo "<select name=\"paycomm\" id='paycomm' style=\"width:100%;\" onchange=\"payband(this.value)\">";
                echo "<option value=".$sgm_pc[value].">".$sgm_pc[value]."</option>";
                echo "<option value=\"\" disabled  >------Select ---------------</option>";
                echo "<option value='6th'>6th</option>";
                echo "<option value='7th'>7th</option>";
                echo "</select>";
                echo "</td>";
                echo "</tr>";

       
		echo "<tr>";
                echo "<td><label for=\"group\" class=\"control-label\">Group:</label></td>";
                echo "<td>";
                echo "<select name=\"group\" id=\"group\" style=\"width:100%;\">";
                echo "<option value=".$sgm_group[value].">".$sgm_group[value]."</option>";
                echo "<option value=\"\" disabled >------Select ---------------</option>";
                echo "<option value=\"A\">A</option>";
                echo "<option value=\"B\">B</option>";
                echo "<option value=\"C\">C</option>";
                echo "<option value=\"D\">D</option>";
                echo "</select>";
                echo "</td>";
                echo "</tr>";

            	echo "<tr id=sgname>";
                echo "<td>";
                echo form_label('Salary Grade Name(Pay Band)', 'sgm_name');
                echo "</td>";
                echo "<td>";
?>		<div>
                        <select name="sgm_name" id="sgm_name" style="width:100%;" >
<?php			echo "<option value=".$sgm_name[value].">".$sgm_name[value]."</option>"; ?>
                        <option disabled  >------Select----------------</option>
                        </select>
                        </div>

<?php   //                echo form_input($sgm_name);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            	echo "</tr>";

		echo "<tr id='slevel'>";
               	echo "<td>";
               	echo form_label('Salary Level of Pay ','sgm_level');
                echo "</td>";
                echo "<td>";
                $level=$sgm_level['value'];

               // echo "<select name=\"sgm_level\"class=\"my_dropdown\" style=\"width:100%;\">";
               // echo "<option value=".$level.">".$level."</option>";
?>
 <div>
                <select name="sgm_level" id="sgm_level" style="width:100%;" >
              <?php   echo "<option value=".$level.">".$level."</option>"; ?>
                <option  disabled >------ Select Level------</option>
                </select>
                        </div>

  <?php //                   echo "<option value=selected disabled >------select------</option>";

                echo "</td>";
                echo "</tr>";


            	echo "<tr>";
                echo "<td>";
                echo form_label('Scale of Pay Min', 'sgm_min');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                echo form_input($sgm_min);
                echo "</td>";
                echo "<td>";
                echo "</td>";
	        echo "</tr>";

 		echo "<tr>";
                echo "<td>";
                echo form_label('Scale of Pay Max', 'sgm_max');
                //echo "<br />";
                echo "</td>";
                echo "<td>";
                echo form_input($sgm_max);
                echo "</td>";
                echo "<td>";
                echo "</td>";
            	echo "</tr>";
        
            
            	echo "<tr id='sgpay'>";
                echo "<td>";
                echo form_label('Salary Grade Pay ', 'sgm_gradepay');
                echo "</td>";
                echo "<td>";
                //echo "<br />";
                echo form_input($sgm_gradepay);
                echo "</td>";
                echo "<td>";                    
                echo "</td>";
            	echo "</tr>";

        
            	echo "<tr>";
		echo "<td></td>";
                echo "<td>";
                echo form_hidden('sgm_id', $sgm_id);
                echo form_submit('submit', 'Update');
            	echo form_close();
            	echo "<button onclick=\"goBack()\" >Back</button>";
            	echo "</td>";
            	echo "</tr>";
       ?>
        </table>   
    </body>
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

