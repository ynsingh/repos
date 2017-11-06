<!--@name editstaffposition.php
    @author Om Prakash(omprakashkgp@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
   <head>    
        <?php $this->load->view('template/header'); ?>
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
        <?php $this->load->view('template/menu');?>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
   </head>
   <body>
	<script>
           function goBack() {
        	window.history.back();
        }

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


	</script>
   </script>
   <table style="padding: 8px 8px 8px 20px;">
     <tr colspan="2"><td>
       <div style="margin-left:10px;width:1700px;">
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
              </div></br>
           </td></tr>
       </table>
       <table style="margin-left:30px;">
       <form action="<?php echo site_url('staffmgmt/editstaffposition/' . $sp_id);?>" method="POST" class="form-inline">
       <tr>
             <td> Campus Name </td>
             <td>
                 <?php echo form_input($campus); ?>
             </td>
             <td>&nbsp;</td>
             <td> University Officers Control </td>
             <td>
                <?php echo form_input($uo); ?>
	<!--    <select name="uo" id="uo" class="my_dropdown" style="width:300px;">
                <option value="<?php echo $this->lgnmodel->get_listspfic1('authorities', 'id', 'name', $uo["value"])->id; ?>"><?php echo $uo["value"]; ?></option>
                <?php foreach($this->authorty as $uo): ?>
                    <option value="<?php echo $uo->id; ?>"><?php echo $uo->name; ?></option>
                <?php endforeach; ?></td>
		</select>-->
             </td>
	     <td>&nbsp;</td>
             <td> Department Name</td>
             <td>
                 <?php echo form_input($dept); ?>
             </td>
        </tr>
        <tr>
             <td> Scheme Name </td>
             <td>
                 <?php echo form_input($schemecode); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Group </td>
             <td>
                 <?php echo form_input($group); ?>
             </td>
	     <td>&nbsp;</td>
             <td>Employee post </td>
             <td>
		<?php echo form_input($emppost); ?>
             </td>
        </tr>
        <tr>
             <td> Working Type </td>
             <td>
                 <?php echo form_input($tnt); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Group Post </td>
             <td>
                 <?php echo form_input($grouppost); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Employee Type </td>
             <td>
                 <?php echo form_input($type); ?>
             </td>
        </tr>
        <tr>
             <td> Plan / Non Plan </td>
             <td>
                 <?php echo form_input($pnp); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Pay Band </td>
             <td>
                 <?php echo form_input($scale); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Method of Recruitment </td>
             <td>
                 <?php echo form_input($methodrect); ?>
             </td>
        </tr>
	<tr>	
             <td> Position Sanction Strength </td>
             <td>
                <!-- <?php echo form_input($ss); ?>-->
		<input type="text" name="ss" id="ss" class="keyup-numeric" size="26" value="<?php echo $ss['value']; ?>" placeholder="Position Sanction Strength..." required="required" oninput="getsstypevalue(this.value)" /><br>
             </td>
	     <td>&nbsp;</td>
             <td> Position Present </td>
             <td>
                 <?php echo form_input($p); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Position Vacant </td>
             <td>
                 <?php echo form_input($v); ?>
             </td>
	</tr>
	<tr>
             <td> Sanction Strength Permanent </td>
             <td>
                <?php echo form_input($ssper); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Position Permanent </td>
             <td>
                 <?php echo form_input($pper); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Vacancy Permanent </td>
             <td>
                 <?php echo form_input($vper); ?>
             </td>
	</tr>
	<tr>
             <td> Sanction Strength Temporary </td>
             <td>
                 <?php echo form_input($sstem); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Position Temporary </td>
             <td>
                  <?php echo form_input($ptem); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Vacancy Temporary </td>
             <td>
                 <?php echo form_input($vtem); ?>
             </td>
        </tr>
        <tr>
             <td>Address </td>
             <td>
                 <?php echo form_input($address1); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Sanction Strength Detail </td>
             <td>
                 <?php echo form_input($ssdetail); ?>
             </td>
	     <td>&nbsp;</td>
             <td> Remarks </td>
             <td>
                <?php echo form_input($remarks); ?>
             </td>
	</tr>
	<tr><td></td>
	<td>
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
 
