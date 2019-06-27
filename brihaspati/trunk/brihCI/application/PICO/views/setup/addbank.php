<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<!--@name addbank.php 
  @author Neha Khullar(@gmail.com)
 -->


<html>
<title>Add Bank</title>
 <head>    
        <?php $this->load->view('template/header'); ?>
	 <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
<script>
            $(document).ready(function(){
/*******uoc on the basis of campus*****************************************************************************/
            
            /*
            In future this code may be replace when either campusid added in the 
            autority or authority added in campus.*/
            $('#camp').on('change',function(){
                var sc_code = $(this).val();
                //alert(sc_code);
                if(sc_code == ''){
                    $('#uocid').prop('disabled',true);
                   
                }
                else{
                    $('#uocid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/empmgmt/getuoclist",
                        type: "POST",
                        data: {"campusname" : sc_code},
                        dataType:"html",
                        success:function(data){
                            //alert("data==1="+data);
                            $('#uocid').html(data.replace(/^"|"$/g, ''));
                                                 
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                    });
                }
            }); 
/*****end of uoc***************************************************************************/
            /************************select department on basis of uoc and campus*******************/           
             $('#uocid').on('change',function(){
                var sc_code = $('#camp').val();
                var uoc_id = $('#uocid').val();
                var combid = sc_code+","+uoc_id;
               //alert("combid=="+combid);
                if(uoc_id == ''){
                    $('#scid').prop('disabled',true);
                }
                else{
             
                    $('#scid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/empmgmt/getnewdeptlist",
                        type: "POST",
                        data: {"campuoc" : combid},
                        dataType:"html",
                        success:function(data){
                            
                            $('#scid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 

/************************select schemes on the basis of department*******************/
                       
             $('#scid').on('change',function(){
                //var sc_code = $('#camp').val();
                //var uoc_id = $('#uocid').val();
                var dept_id = $('#scid').val();
                //var campuocdept = sc_code+","+uoc_id+","+dept_id;
                //alert("seema==="+sc_code+'uoc==='+uoc_id+"dept=="+dept_id+"comb=="+campuocdept);
                if(dept_id == ''){
                    $('#schmid').prop('disabled',true);
                }
                else{
             
                    $('#schmid').prop('disabled',false);
                    $.ajax({
                        url: "<?php echo base_url();?>sisindex.php/staffmgmt/getnewdeptschemelist",
                        type: "POST",
                       // data: {"combthree" : campuocdept},
                         data: {"combdept" : dept_id},
                        dataType:"html",
                        success:function(data){
                            //alert("ok===="); 
                            //alert("data==1="+data);
                            $('#schmid').html(data.replace(/^"|"$/g, ''));
                       
                        },
                        error:function(data){
                            //alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                                            
                    });
                }
            }); 
        /*********************closer of scheme**************************************************/    
      });
</script> 
        
 </head>
   <body>

     <table width="100%">
       <tr><td>
        <div>
        <?php
           echo anchor('setup/displaybankdetails', 'Bank Details', array('class' => 'top_parent'));
            echo "<td align=\"right\">";
           $help_uri = site_url()."/help/helpdoc#Bank";
          // echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
           echo "</td>";
        ?>
        </div>
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
    <div>
        <form action="<?php echo site_url('setup/addbank');?>" method="POST" class="form-inline">
          <table>
<!--            <tr>

            <?php
                        echo "<td>";
                        echo form_label('Organization', 'org_id');
                        echo "</td>";
                        echo "<td>";
                        echo "<select name=\"org_id\" class=\"my_dropdown\" style=\"width:100%;\">";
                        echo "<option selected='true'disabled>------Organization------</option>";
                        echo "<option value='Indira Gandhi National Tribal University'>Indira Gandhi National Tribal University </option>";
                        echo "</select>";
                        ?>

	</tr>
-->
	 <tr>
                    <td>Campus Name <font color='Red'>*</font></td>
                    <td colspan=2>
                        <select id="camp" style="width:350px;" name="campus" required>
                            <option selected="selected" disabled selected>--------Campus Name-----</option>
                            <?php foreach($this->campus as $camdata): ?>
                            <option class="test" value="<?php echo $camdata->sc_id; ?>"><?php echo $camdata->sc_name; ?></option>
                            <?php endforeach; ?>
                        </select>
                    </td>
                </tr>
                <tr>
                <td>University Officer Control<font color='Red'>*</font></td>
                <td colspan=2><select name="uocontrol" style="width:350px;"id="uocid" required>
                <option selected="selected" disabled selected>--------University Officer Control -----</option>
                </select>
                </td>
                </tr>
                <tr>
                <td>Department<font color='Red'>*</font></td>
                <td colspan=2><select name="department" style="width:350px;"id="scid" required>
                <option selected="selected" disabled selected>--------Department-----</option>
                </select>
                </td>
        </tr>
<tr>
                <td>Scheme Name<font color='Red'></font></td>
                <td colspan=2><select name="schemecode" style="width:350px;"id="schmid" >
                <option selected="selected" disabled selected>--------Scheme Name-----</option>
                </select>
                </td>
        </tr>

            <tr>       
                <td><label for="name" class="control-label">Bank Name :</label></td>
                <td>                                                                                                                                   
                <input type="text" name="bank_name" class="form-control" size="40" value= "<?php echo isset($_POST["bank_name"]) ? $_POST["bank_name"] : ''; ?>" /><br>
                </td>              
                <td>
                 <!-- <?php echo form_error('name')?></td>-->
                </td>
               <td>
                   Example : State Bank of India
                </td>

 
            </tr>
            <tr>
                <td><label for="branch" class="control-label">Branch Name :</label></td>
                <td>
                <input type="text" name="branch_name" class="form-control" size="40" value= "<?php echo isset($_POST["branch_name"]) ? $_POST["branch_name"] : ''; ?>" /><br>
 
                </td>
                <td>
                  <!-- <?php echo form_error('branch')?></td>-->
                </td>
               <td>
                    Example : IIT Kanpur 
                 </td>


            </tr>
            <tr>
                <td><label for="address" class="control-label">Bank Address :</label></td>
                <td>
                <input type="text" name="bank_address" class="form-control" size="40" value= "<?php echo isset($_POST["bank_address"]) ? $_POST["bank_address"] : ''; ?>" /><br>

                </td>
                <td>
                  <!-- <?php echo form_error('address')?></td>-->
                </td> 
               <td>
                    Example : Near Shopping Center, ITT Kanpur Campus, U. P., Pin 208016
                 </td>



            </tr>
            <tr>
                <td><label for="ifsc code" class="control-label">IFSC Code :</label></td>
                <td>
                <input type="text" MaxLength="11" name="ifsc_code" class="form-control"  size="40" value= "<?php echo isset($_POST["ifsc_code"]) ? $_POST["ifsc_code"] : ''; ?>" /><br>

                </td>
                <td>
                  <!-- <?php echo form_error('ifsc code')?></td>-->
                </td>
               <td>
                    Example : SBIN0001161
                 </td>


               
            </tr>
            <tr>
                <td><label for="account number" class="control-label">Account Number:</label></td>
                <td>
                <input type="text" MaxLength="16" name="account_number" class="form-control" size="40" value= "<?php echo isset($_POST["account_number"]) ? $_POST["account_number"] : ''; ?>" /><br>

                </td>
                <td>
                  <!-- <?php echo form_error('account number')?></td>-->
                </td>
               <td>
                    Example : 30281294639
                 </td>
            </tr>
            <tr>
                <td><label for="account type" class="control-label">Account Type :</label></td>
                <td>
                <input type="text" name="account_type" class="form-control"  size="40" value= "<?php echo isset($_POST["account_type"]) ? $_POST["account_type"] : ''; ?>" /><br>

                </td>
                <td>
                  <!-- <?php echo form_error('account type')?></td>-->
                </td>
               <td>
                    Example : Savings
                 </td>

               

            </tr>
            <tr>
                <td><label for="account name" class="control-label">Account Name :</label></td>
                <td>
                <input type="text" name="account_name" class="form-control"  size="40" value= "<?php echo isset($_POST["account_name"]) ? $_POST["account_name"] : ''; ?>" /><br>

                </td>
                <td>
                   <!--<?php echo form_error('account name')?></td>-->
                </td>
               <td>
                    Example : Aakriti Shukla
                 </td>



            </tr>
            <tr>
                <td><label for="pan number" class="control-label">PAN Number :</label></td>
                <td>
                <input type="text" MaxLength="10" name="pan_number" class="form-control"  size="40" value= "<?php echo isset($_POST["pan_number"]) ? $_POST["pan_number"] : ''; ?>" /><br>

                </td>
                <td>
                   <!--<?php echo form_error('pan number')?></td>-->
                </td>
               <td>
                    Example : AAACS8577K 
                 </td>

                                           

            </tr>
            <tr>
                <td><label for="tan number" class="control-label">TAN Number :</label></td>
                <td>
                <input type="text" name="tan_number" class="form-control"  size="40" /><br>
                </td>
                <td>
                   <!--<?php echo form_error('tan number')?></td>-->
                </td>
               <td>
                    Example : DELA02603G
                 </td>



            </tr>
            <tr>
                <td><label for="gst number" class="control-label">GST Number :</label></td>
                <td>
                <input type="text" name="gst_number" class="form-control"  size="40" /><br>
                </td>
                <td>
                  <!-- <?php echo form_error('gst number')?></td>-->
                </td>
               <td>
                    Example : 22(State Code) AAAAA0000A(PAN) 1Z5(1-Entity no) (Z-Alphabet 'Z' by default) (5-Check sum digit)
                 </td>




            </tr>
            <tr>
                <td><label for="aadhar number" class="control-label">Aadhar Number:</label></td>
                <td>
                <input type="text" MaxLength="12" name="aadhar_number" class="form-control"  size="40" /><br>
                </td>
                <td>
                  <!-- <?php echo form_error('aadhar number')?></td>-->
                </td>
                <td>
                    Example : 499118665246
                 </td>



            </tr>
            <tr>
                <td><label for="remark" class="control-label">Remark :</label></td>
                <td>
                <input type="text" name="remark" class="form-control"  size="40" /><br>
                </td>
                <td>
                  <!-- <?php echo form_error('remark')?></td>-->                  
                </td>
               <td>
                    Example : Fair, Good, Need to be improved
                 </td>
            </tr>
            <tr>
              <td></td>
              <td>
              <button name="addbank">Add Bank</button>
	      <input type="reset" name="Reset" value="Clear"/>
                </td>
            </tr>
            </table>
    </form>
   <p><br></p>
    </div>
</body>
<p>&nbsp;</p>
    <div align=left> <?php $this->load->view('template/footer');?></div>
    </html>

              
            
          
