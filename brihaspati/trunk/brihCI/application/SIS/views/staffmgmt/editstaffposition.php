<!--@name editstaffposition.php
    @author Om Prakash(omprakashkgp@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
   <head>    
        <?php $this->load->view('template/header'); ?>
           
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
   </head>
   <body>

	<script>
           function goBack() {
        	window.history.back();
        }
	</script>
	<script>
        function getsstypevalue(val){
                var p = $('#p').val();
                var type = $('#type').val();
                var ss=val;
                var combid = p+","+type+","+ss;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/staffmgmt/getsstype",
                data: {"sstype" : combid},
                dataType:"html",
                success: function(data){
        //      alert(data);
                var ssdata=data;
                var positiondata=ssdata.split(',');
                        $('#p').val(positiondata[0].replace(/\"|"/g,''));
                        $('#v').val(positiondata[1].replace(/^"|"$/g, ''));
                        $('#ssper').val(positiondata[2].replace(/^"|"$/g, ''));
                        $('#pper').val(positiondata[3].replace(/^"|"$/g, ''));
                        $('#vper').val(positiondata[4].replace(/^"|"$/g, ''));
                        $('#sstem').val(positiondata[5].replace(/^"|"$/g, ''));
                        $('#ptem').val(positiondata[6].replace(/^"|"$/g, ''));
                        $('#vtem').val(positiondata[7].replace(/^"|"$/g, ''));
                }
             });
        }
	function getptypevalue(val){
                var ss = $('#ss').val();
                var type = $('#type').val();
                var p=val;
                var combid = p+","+type+","+ss;
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>sisindex.php/staffmgmt/getsstype",
                data: {"sstype" : combid},
                dataType:"html",
                success: function(data){
        //      alert(data);
                var ssdata=data;
                var positiondata=ssdata.split(',');
                        $('#p').val(positiondata[0].replace(/\"|"/g,''));
                        $('#v').val(positiondata[1].replace(/^"|"$/g, ''));
                        $('#ssper').val(positiondata[2].replace(/^"|"$/g, ''));
                        $('#pper').val(positiondata[3].replace(/^"|"$/g, ''));
                        $('#vper').val(positiondata[4].replace(/^"|"$/g, ''));
                        $('#sstem').val(positiondata[5].replace(/^"|"$/g, ''));
                        $('#ptem').val(positiondata[6].replace(/^"|"$/g, ''));
                        $('#vtem').val(positiondata[7].replace(/^"|"$/g, ''));
                }
             });
        }

   </script>
   <table width="100%">
   <!--<table style="padding: 8px 8px 8px 20px;">-->
     <tr colspan="2"><td>
     <div>
         <?php
            echo anchor('staffmgmt/staffposition', 'View Staff Position', array('class' => 'top_parent'));
         ?>
      </div>
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
      <!-- <table style="margin-left:30px;">-->
      <table style="width:100%; border:1px solid gray;" align="center" class="TFtable">
      <form action="<?php echo site_url('staffmgmt/editstaffposition/' . $sp_id);?>" method="POST" class="form-inline">
              <tr><thead><th  style="background-color:#2a8fcf;text-align:left;height:40px;" colspan="3">&nbsp;&nbsp;Staff Position Form</th></thead></tr>   
      <tr>
             <td><label for="campus" style="font-size:15px;"> Campus Name </label>
             <div>
                 <?php echo form_input($campus); ?>
             </div>
             </td>
             <td><label for="uo" style="font-size:15px;"> University Officers Control </label>
             <div>
                <?php echo form_input($uo); ?>
	<!--    <select name="uo" id="uo" class="my_dropdown" style="width:300px;">
                <option value="<?php echo $this->lgnmodel->get_listspfic1('authorities', 'id', 'name', $uo["value"])->id; ?>"><?php echo $uo["value"]; ?></option>
                <?php foreach($this->authorty as $uo): ?>
                    <option value="<?php echo $uo->id; ?>"><?php echo $uo->name; ?></option>
                <?php endforeach; ?></td>
		</select>-->
             </div>
	     </td>
             <td><label for="dept" style="font-size:15px;"> Department Name</label>
             <div>
                 <?php echo form_input($dept); ?>
             </div>
	     </td>	
        </tr>
        <tr>
             <td><label for="schemecode" style="font-size:15px;"> Scheme Name </label>
             <div>
                 <?php echo form_input($schemecode); ?>
             </div>
	     </td>
             <td><label for="group" style="font-size:15px;"> Group </label>
             <div>
                 <?php echo form_input($group); ?>
             </div>
	     </td>
             <td><label for="emppost" style="font-size:15px;"> Employee Post </label>
             <div>
		<?php echo form_input($emppost); ?>
             </div>
	    </td>
        </tr>
        <tr>
             <td><label for="tnt" style="font-size:15px;"> Working Type </label>
             <div>
                 <?php echo form_input($tnt); ?>
             </div>
	    </td> 
             <td><label for="ss" style="font-size:15px;"> Group Post </label>
             <div>
                 <?php echo form_input($grouppost); ?>
             </div>
	     </td>
             <td><label for="ss" style="font-size:15px;"> Employee Type </label>
             <div>
                 <?php echo form_input($type); ?>
             </div>
	    </td>	
        </tr>
        <tr>
             <td><label for="ss" style="font-size:15px;"> Plan / Non Plan </label>
             <div>
                 <?php echo form_input($pnp); ?>
             </div>
	     </td>
             <td><label for="ss" style="font-size:15px;"> Pay Band </label>
             <div>
                 <?php echo form_input($scale); ?>
             </div>
	     </td>
             <td><label for="ss" style="font-size:15px;"> Method of Recruitment </label>
             <div>
                 <?php echo form_input($methodrect); ?>
            </div>
	   </td>
        </tr>
	<tr>	
          <td><label for="ss" style="font-size:15px;"> Position Sanction Strength </label>
             <div>
                <!-- <?php //echo form_input($ss); ?>-->
		<input type="text" name="ss" id="ss" class="keyup-numeric" size="30" value="<?php echo $ss['value']; ?>" placeholder="Position Sanction Strength" required="required" oninput="getsstypevalue(this.value)" /><br>
             </div>
	     </td>
             <td><label for="p" style="font-size:15px;"> Position Present </label>
             <div>
               <!--  <?php //echo form_input($p); ?> -->
		<input type="text" name="p" id="p" class="keyup-numeric" size="30" value="<?php echo $p['value']; ?>" placeholder="Position" required="required" oninput="getptypevalue(this.value)" /><br>
             </div>
	     </td>
             <td><label for="v" style="font-size:15px;"> Position Vacant </label>
             <div>
                 <?php echo form_input($v); ?>
             </div>
	    </td>
	</tr>
	<tr>
             <td><label for="ssper" style="font-size:15px;"> Sanction Strength Permanent </label>
             <div>
                <?php echo form_input($ssper); ?>
             </div>
	     </td>
             <td><label for="pper" style="font-size:15px;"> Position Permanent </label>
             <div>
                 <?php echo form_input($pper); ?>
             </div>
	     </td>
             <td><label for="vper" style="font-size:15px;"> Vacancy Permanent </label>
             <div>
                 <?php echo form_input($vper); ?>
             </div>
	    </td>	
	</tr>
	<tr>
             <td><label for="sstem" style="font-size:15px;"> Sanction Strength Temporary </label>
             <div>
                 <?php echo form_input($sstem); ?>
             </div>
	     </td>
             <td><label for="ptem" style="font-size:15px;"> Position Temporary </label>
             <div>
                  <?php echo form_input($ptem); ?>
             </div>
	     </td>
             <td><label for="vtem" style="font-size:15px;"> Vacancy Temporary </label>
             <div>
                 <?php echo form_input($vtem); ?>
             </div>
	    </td>
        </tr>
        <tr>
             <td><label for="address1" style="font-size:15px;"> Address </label>
             <div>
                 <?php echo form_input($address1); ?>
             </div>
	     </td>
             <td><label for="ssdetail" style="font-size:15px;"> Sanction Strength Detail </label>
             <div>
                 <?php echo form_input($ssdetail); ?>
             </div>
	     </td>
             <td><label for="remarks" style="font-size:15px;"> Remarks </label>
             <div>
                <?php echo form_input($remarks); ?>
             </div>
	     </td>		
	</tr>
	<tr style="background-color:#2a8fcf;text-align:left;height:40px;">
	<td colspan="3">
	     <button name "submit" >Update</button>
	</form>
	     <?php echo "<button onclick=\"goBack()\" >Back</button>";?>
	</td>	
	</tr>
	     <?php echo form_hidden( 'sp_id', $sp_id );?>
     </table>
   </body>
   <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

