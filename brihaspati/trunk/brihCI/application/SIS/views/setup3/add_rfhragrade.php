 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>HRA GRADE</title>
    <head>
        <script type="text/javascript" src="<?php echo base_url();?>assets/datepicker/jquery-1.12.4.js" ></script>
        <script> 
	function rangeofpay(val){
                         var pcom= $('#paycomm').val();
                         $.ajax({
                                type: "POST",
                                url: "<?php echo base_url();?>sisindex.php/jslist/getpayrange",
                                data: {"pcom" : pcom},
                                dataType:"html",
                                success: function(data){
  //            alert(data);
                                        $('#payrange').html(data.replace(/^"|"$/g, ''));
                                }
                        });
                }

        </script>

    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup3/rentfreehra/', "View Rent Free HRA Grade" ,array('title' => 'View Rent Free HRA Grade' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Add Rent Free HRA Grade</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";

                ?>
            </tr>
        </table>
        <table width="100%">
           <tr><td>
           <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php echo form_error('<div class="isa_error">','</div>');?>
               <?php
                if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                    echo "<div  class=\"isa_success\">";
                    echo $_SESSION['success'];
                    echo "</div>";
                }
                if((isset($_SESSION['err_message'])) && (($_SESSION['err_message'])!='')){
                    echo "<div  class=\"isa_error\">";
                    echo $_SESSION['err_message'];
                    echo "</div>";
                }
                ;?>
                
            </div>
            </td></tr>
        </table>
        <form action="<?php echo site_url('setup3/add_rfhragrade');?>" method="POST" id="myForm" class="form-inline">
            <table>
		<tr>
                        <td><label for="paycomm" class="control-label">Pay Commission:</label></td>
                        <td>
                            <select name="paycomm" id="paycomm" style="width:100%;" onchange="rangeofpay(this.value)">
                                <option value="" disabled selected >------Select ---------------</option>
                                <option value="6th">6th</option>
                                <option value="7th">7th</option>
                            </select>
                        </td>
                </tr>
                <tr>
                	<td><label for="hragrade" class="control-label">Rent Free HRA Grade:</label></td>
                        <td>
                            <select name="hragrade" id="hragrade" class="my_dropdown" style="width:100%;">
                		<option value="" disabled selected >------Select Rent Free HRA Grade -------</option>
                                <?php foreach($this->hragrade as $hgcdata): ?>	
   				<option value="<?php echo $hgcdata->hgc_id; ?>"><?php echo $hgcdata->hgc_gradename;?></option> 
                                <?php endforeach; ?>
                            </select>
			    </select>
                        </td>
	     	</tr>
		 <tr>
                    <td><label for="payrange" class="control-label">Pay Range:</label></td>
                    <td>
				<div>
				<select name="payrange" id="payrange" style="width:100%;">
					<option disabled selected >------Select----------------</option>
				</select>
				</div>
		    </td>
                </tr>

                <tr>
                    <td><label for="amount" class="control-label">Rent Free HRA Amount:</label></td>
                    <td><input type="text" name="amount" value="<?php echo isset($_POST["amount"]) ? $_POST["amount"] : ''; ?>" placeholder="Amount In Rupees..." size="30" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                    <button name="add_rfhragrade">Submit</button>
                    <input type="reset" name="Reset" value="Clear"/>
                    </td>
                </tr>
            </table>    
        </form>
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>    
</html>    
