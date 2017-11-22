<!---@name editdesignation.php                                                                                                                                                               
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
    @author Om Prakash(omprakashkgp@gmail.com)  payscale drop down bug fix 
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Designation</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
            <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>

<script>
         $(document).ready(function(){
         $('#tnt').on('change',function(){
                var worktype = $(this).val();
                //alert(worktype);
                if(worktype == ''){
                    $('#grouppost').prop('disabled',true);
                   
                }
                else{
                    $('#grouppost').prop('disabled',false);
                    $.ajax({
                     // url: "<?php echo base_url();?>slcmsindex.php/setup2/getworkingtype",
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getworkingtype", 
			type: "POST",
                        data: {"groupp" : worktype},
                        dataType:"html",
                        success:function(data){
                            //alert("data==1="+data);
                            $('#grouppost').html(data.replace(/^"|"$/g, ''));
                                                 
                        },
                        error:function(data){
                            alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                    });
                }
            }); 
        });
</script>

    </head>
    <body>
 <script>
        function goBack() {
        window.history.back();
        }
    </script>


        <!--<//?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            //echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo anchor('setup/displayrole/', "View Role Details", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Edit role";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>--!>
        <table width="100%">
                <tr><td>
                        <div margin="2%">
                        <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                        <?php echo form_error('<div class="isa_error">','</div>');?>
                        <?php if(isset($_SESSION['success'])){?>
                                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                        <?php }; ?>
                        </div> </br>
                </td></tr>
        </table>
        <table style="padding: 8px 8px 8px 30px;">
        <?php
                echo form_open('setup2/editdesignation/'. $desig_id);
                
                echo "<tr>";
                echo "<td>";
                echo form_label('Designation Code', 'desig_code');
                echo "</td>";
                echo "<td>";
                echo form_input($desig_code);
                echo "</td>";
                echo "<td>";
                echo "Example: 10, 8,6 etc ";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Designation Type', '$desig_type');
                echo "</td>";
               echo "<td>";
                    echo "<select name=\"tnt\"id=\"tnt\" class=\"my_dropdown\" style=\"width:100%;\">";
                    echo "<option value=\"$desig_type[value]\" >$desig_type[value]</option>";
                    echo "<option value=\"disabled selected\">------Select Type------</option>";
                    echo "<option value=\"Teaching\" class=\"dropdown-item\">Teaching</option>";
                    echo "<option value=\"Non Teaching\" class=\"dropdown-item\">Non Teaching</option>";
                    echo "</select>";
                echo "</td>";
                 echo"<td>";
                  echo "Example: ";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Designation Sub Type', '$desig_subtype');
                echo "</td>";
                echo "<td>";
                $desigsubtype=$desig_subtype['value'];
                    echo "<select name=\"grouppost\"id=\"grouppost\" class=\"my_dropdown\" style=\"width:100%;\">";
                    echo "<option value=$desigsubtype class=\"dropdown-item\">$desigsubtype</option>";
                    echo "<option value=\"disabled selected\">------Select Sub Type------</option>";
                    echo "</select>";
                    echo "</td>";
                    echo"<td>";
                    echo "Example: ";
                    echo "</td>";
                    echo "</tr>";
               ?>
               <?php

	        echo "<tr>";
                echo "<td>Designation Payscale</td>";
                echo "<td><select name=\"desig_payscale\" class=\"my_dropdown\" style=\"width:100%;\">";
          
                echo "<option value=\"$desig_payscale[value]\">$desig_payscale[value]</option>"; ?>
		<?php foreach($this->payresult as $datas): ?>
		<option><?php echo set_select('desig_payscale', $datas->sgm_name."(". $datas->sgm_min."-".$datas->sgm_max.")".$datas->sgm_gradepay);?><?php echo $datas->sgm_name."(". $datas->sgm_min."-".$datas->sgm_max.")".$datas->sgm_gradepay; ?>
                          </option>
                  <?php endforeach; ?>
                </select></td>
                <td><?php echo form_error('desig_payscale')?></td>
               </td>
               </tr>
        
              <?php                
                echo "<tr>";
                echo "<td>";
                echo form_label('Designation Name', 'desig_name');
                echo "</td>";
                echo "<td>";
                echo form_input($desig_name);
                echo "</td>";
                echo "<td>";
                echo "Example: Faculty, Administrator, etc ";
                echo "</td>";
                echo "</tr>";
              
                echo "<tr>";
                echo "<td>";
                echo form_label('Designation Group', 'desig_group');
                echo "</td>";
               echo "<td>";
               $des=$desig_group['value'];
                    echo "<select name=\"desig_group\"class=\"my_dropdown\" style=\"width:100%;\">";
                    echo "<option value=$des class=\"dropdown-item\">$des</option>";
                    echo "<option value=\"disabled selected\">------Select Group------</option>";
                    echo "<option value=\"A\" class=\"dropdown-item\">A</option>";
                    echo "<option value=\"B\" class=\"dropdown-item\">B</option>";
                    echo "<option value=\"C\" class=\"dropdown-item\" >C</option>";
                    echo "<option value=\"D\" class=\"dropdown-item\" >D</option>";
                    echo "</select>";
                echo "</td>";
                 echo"<td>";
                  echo "Example: A, B, C, D etc ";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Designation Short', 'desig_short');
                echo "</td>";
                echo "<td>";
                echo form_input($desig_short);
                echo "</td>";
                echo "<td>";
                echo "Example: Good, Fair  etc ";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Designation Description', 'desig_desc');
                echo "</td>";
                echo "<td>";
                echo form_input($desig_desc);
                echo "</td>";
                echo "<td>";
                echo "</td>";
                echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo "</td>";
                echo "<td>";
                echo form_hidden('desig_id', $desig_id);
                echo form_submit('submit', 'Update');
                echo " ";
                echo form_close();
                echo "<button onclick=\"goBack()\" >Back</button>";
                echo "</td>";
                echo "</tr>";
 ?>
       </table>
</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>                                                                                                                                                                                                                                   
