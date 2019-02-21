 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Edit HRA Grade</title>
    <head>
       <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <?php $this->load->view('template/header'); ?>
        
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
        <table width="100%">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup3/rentfreehra/', "View Rent Free HRA Grade" ,array('title' => 'View rent Free HRA Grade' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    //echo "<b></b>";
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
        <form action="<?php echo site_url('setup3/edit_rfhragrade/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<?php echo  $id ; ?>">
            <table>
			<tr>
                        <td><label for="paycomm" class="control-label">Pay Commission:</label></td>
                        <td>
                            <select name="paycomm" id="paycomm" class="my_dropdown" style="width:100%;" onchange="rangeofpay(this.value)">
                                <?php if(!empty($rfhragradedata->rfh_paycomm)):;?>
                                <option value="<?php echo $rfhragradedata->rfh_paycomm;?>"><?php echo $rfhragradedata->rfh_paycomm;?></option>
                                <?php else:?>
                                <option value="" disabled selected >------ Select Pay Commission----------</option>
                                 <?php endif;?>
                                <option value="6th">6th</option>
                                <option value="7th">7th</option>
                            </select>
                        </td>
                </tr>
                <tr>
                	<td><label for="hragrade" class="control-label">Rent Free HRA Grade:</label></td>
                        <td>
                            <select name="hragrade" id="hragrade" class="my_dropdown" style="width:100%;">
                                 <?php if(!empty($rfhragradedata->rfh_gradeid)):;?>
                                <option value="<?php echo $rfhragradedata->rfh_gradeid;?>"><?php 
                                    $hragradename=$this->sismodel->get_listspfic1('hra_grade_city','hgc_gradename','hgc_id',$rfhragradedata->rfh_gradeid)->hgc_gradename;
                                    echo $hragradename;?></option>      
                                <?php else:?>
                		<option value="" disabled selected >------Select Rent Free HRA Grade -------</option>
                                 <?php endif;?>
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
					 <option value="<?php echo $rfhragradedata->rfh_payrange; ?>"><?php echo $rfhragradedata->rfh_payrange; ?></option>
                                        <option >------Select----------------</option>
                                </select>
                                </div>
                    </td>
                </tr>
                <tr>
                    <td><label for="amount" class="control-label">Rent Free HRA Amount:</label></td>
                    <td><input type="text" name="amount" value="<?php echo $rfhragradedata->rfh_amount; ?>" placeholder="Amount In Rupees..." size="30" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button name="editrfhragrade">Update</button>
                        <button type="button" onclick="history.back();">Back</button>
                    </td>
                </tr>
            </table>    
        </form>
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>    
</html>    
