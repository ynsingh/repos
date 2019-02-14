
<!--@name edit_ccaallowance.php  @author Manorama Pal(palseema30@gmail.com) -->
 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>
 <html>
    <title>Edit HRA Grade</title>
    <head>
       <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <?php $this->load->view('template/header'); ?>
        
        <script>
                function ccarangeofpay(val){
                         var pcom= $('#paycomm').val();
//                      alert(pcom);
//                       var val=val;
                         $.ajax({
                                type: "POST",
                                url: "<?php echo base_url();?>sisindex.php/jslist/getccapayrange",
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
                    echo anchor('setup3/cca_allowance/', "View City Compensatory Allowance(CCA)" ,array('title' => 'View City Compensatory Allowance(CCA)' , 'class' => 'top_parent'));
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
        <form action="<?php echo site_url('setup3/edit_ccaallowance/'.$id);?>" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<?php echo  $id ; ?>">
            <table>
<!--                <tr>
                	<td><label for="worktype" class="control-label">Working Type:</label></td>
                        <td>
                            <select name="worktype" id="worktype" class="my_dropdown" style="width:100%;">
                                <?php //if(!empty($ccadata->cca_workingtype)):;?>
                                <option value="<?php //echo $ccadata->cca_workingtype;?>"><?php //echo $ccadata->cca_workingtype;?></option>      
                                <?php //else:?>
                		<option value="" disabled selected >------ Select Working Type -----------</option>
                                 <?php //endif;?>
                		<option value="Teaching">Teaching</option>
                		<option value="Non Teaching">Non Teaching</option>
			    </select>
                        </td>
	     	</tr>-->
		<tr>
                        <td><label for="paycomm" class="control-label">Pay Commission:</label></td>
                        <td>
                            <select name="paycomm" id="paycomm" class="my_dropdown" style="width:100%;" onchange="ccarangeofpay(this.value)">
                                <?php if(!empty($ccadata->cca_paycomm)):;?>
                                <option value="<?php echo $ccadata->cca_paycomm;?>"><?php echo $ccadata->cca_paycomm;?></option>
                                <?php else:?>
                                <option value="" disabled selected >------ Select  -----------</option>
                                 <?php endif;?>
                                <option value="6th">6th</option>
                                <option value="7th">7th</option>
                            </select>
                        </td>
                </tr>
<!--
                <tr>
                	<td><label for="payscale" class="control-label">Pay Scale:</label></td>
                        <td>
                            <select name="payscale" id="payscale" class="my_dropdown" style="width:100%;">
                                <?php //if(!empty($ccadata->cca_payscaleid)):;?>
                                <option value="<?php //echo $ccadata->cca_payscaleid;?>"><?php 
//                                    $pname=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$ccadata->cca_payscaleid)->sgm_name;
  //                                  $min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$ccadata->cca_payscaleid)->sgm_min;
    //                                $max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$ccadata->cca_payscaleid)->sgm_max;
      //                              $gp=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$ccadata->cca_payscaleid)->sgm_gradepay;
        //                            $fullstr=$pname."( ".$min." - ".$max." )".$gp;
          ///                          echo $fullstr;?></option>      
                                <?php //else:?>
                		<option value="" disabled selected >------Select Pay Scale ---------------</option>
                                 <?php //endif;?>
                                <?php //foreach($this->salgrade as $sgdata): ?>	
   				<option value="<?php //echo $sgdata->sgm_id; ?>"><?php //echo $sgdata->sgm_name."( ".$sgdata->sgm_min." - ".$sgdata->sgm_max." )".$sgdata->sgm_gradepay; ?></option> 
                                <?php //endforeach; ?>
                            </select>
			    </select>
                        </td>
	     	</tr> 
-->
                <tr>
                	<td><label for="hragrade" class="control-label">CCA Grade:</label></td>
                        <td>
                            <select name="hragrade" id="hragrade" class="my_dropdown" style="width:100%;">
                                 <?php if(!empty($ccadata->cca_gradeid)):;?>
                                <option value="<?php echo $ccadata->cca_gradeid;?>"><?php 
					echo $ccadata->cca_gradeid;
				?></option>      
                                 <?php endif;?>
                		<option value=""  >------Select CCA Grade -------</option>
				 <option value="CCA-Type-I">CCA-Type-I</option>
                                <option value="CCA-Type-II">CCA-Type-II</option>
                                <option value="CCA-No">CCA-No</option>

				
			    </select>
                        </td>
	     	</tr>
		<tr>
                    <td><label for="payrange" class="control-label">Pay Range:</label></td>
			<td>
			 <div>
                                <select name="payrange" id="payrange" style="width:100%;">
                                         <option value="<?php echo $ccadata->cca_payrange; ?>"><?php echo $ccadata->cca_payrange; ?></option>
                                        <option >------Select----------------</option>
                                </select>
                                </div>
			</td>
                </tr>
                <tr>
                    <td><label for="amount" class="control-label">Amount:</label></td>
                    <td><input type="text" name="amount" value="<?php echo $ccadata->cca_amount; ?>" placeholder=" Amount..." size="30" /></td>
                </tr>
<!--                <tr>
                    <td><label for="description" class="control-label">Description</label></td>
                    <td><input type="text" name="Description" value="<?php echo $ccadata->cca_description; ?>" placeholder="Description..." size="30" /></td>
                </tr> -->
                <tr>
                    <td></td>
                    <td>
                        <button name="edit_cca">Update</button>
                        <button type="button" onclick="history.back();">Back</button>
                    </td>
                </tr>
            </table>    
        </form>
        <p> &nbsp; </p>
        <div align="center"> <?php $this->load->view('template/footer');?></div>
    </body>    
</html>    
