<!--@name seatsetting.php
   @author Abhay(kumar.abhay.4187@gmail.com)
 -->
<html>
<head>
<script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
<script>
	$(document).ready(function(){
		$("#totseat, #percent").keyup(function() {
  			var totseat1 = parseInt($("#totseat").val());
	  		var percent1 = parseInt($("#percent").val());
  			var noofseat = parseInt($("#noofseat").val());
  			if ($("#totseat").val() && $("#percent").val()){     
  				$('#noofseat').val(((percent1 * totseat1) /  100).toFixed());
			}  
		});

		$("#noofseat").keyup(function() {
			var totseat1 = parseInt($("#totseat").val());
			var noofseat = parseInt($("#noofseat").val());
 			if ($("#noofseat").val() && $("#totseat").val() && $("#percent").val()){
 				$('#percent').val(((percentage * totalseat)/ 100 + totseat1).toFixed(2));
			}
		});
	})
	</script>
        <?php $this->load->view('template/header'); ?>
        <?php// $this->load->view('template/menu');?>
</head>    
<body>
<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table> -->	
<table width= "100%"> <tr>
            <?php
                        echo "<td align=\"left\" width=\"33%\">";
  			echo anchor('setup/dispseatsetting', "Seat Reservation List", array('title' => 'Add Detail' , 'class' => 'top_parent'));
                        echo "</td>";

                    	echo "<td align=\"center\" width=\"34%\">";
                        echo "<b>Add Seat Reservation Details</b>";
                        echo "</td>";
      
                        echo "<td align=\"right\" width=\"33%\">";
                        $help_uri = site_url()."/help/helpdoc#SeatReservation";
                        echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                        echo "</td>";           
             ?>
           </tr>
           </table>
           <table width="100%">
           <tr><td>
            <div>
   
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div  class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                    <?php
                    };
                    ?>
               
                </div>
	</td></tr>  
         </table>  
                      
                    <form action="<?php echo site_url('setup/seatsetting');?>" method="POST" class="form-inline">
                    <table>
		                <div>
				<span> </span><input id="totseat" type ="hidden" value= <?php echo $this->totalseat; ?>>  
				</div>
                                
                           <tr>
                                       <td><label for="org_profile" class="control-label">University:</label></td>
   			        <td>   
                                        <select name="org_profile" style="width:100%;">
					<option value=""disabled selected>----------Select university---------</option>
                    			 <?php foreach($this->uresult as $datas):?> 
					<option value ="<?php echo $datas->org_code;?>"><?php echo $datas->org_name;?></option>
                    			 <?php endforeach;?>
					</select>
                 	      </td>
                          </tr>
                              <tr>
                          		    <td><label for="category" class="control-label">Category:</label></td>
                              <td>
                   			 <select name="category" style="width:100%;">
                    			 <option value=""disabled selected>----------Select Category---------</option>
			      		 <?php foreach($this->catresult as $datas): ?>
					<option value="<?php echo $datas->cat_id;?>"><?php echo $datas->cat_name; ?></option>
					<?php endforeach; ?>
                    			</select>
                    	    </td>
                            </tr>
                          
                     	   <tr>
                    			  <td><label for="percentage" class="control-label">Percentage:</label></td>
                    			  <td><input id="percent" type="text" name="percentage" size="37"  class="form-control" onkeyup="function();"/></td>
					  <td><?php echo form_error('persentage')?></td>
                   	  </tr>
                      		<tr>
                  		          <td><label for="numberofseat" class="control-label">Number of Seat:</label></td>
					  <td><input type="text" id="noofseat" name="numberofseat" var='calp' size="37" class="from-control" onkeyup="function();" /></td>
					  <td><?php echo form_error('number of seat')?></td>
								
											
                               </tr>
                            
                       <tr>
                          <td>
		              <td>
                     <button name="seatsetting" >Submit</button>
                     <button name="reset" >Clear</button>
                              </td>
			</td>
                     </tr>
                     </table>
                    </form>
    </body>
    <div align="center"> <?php $this->load->view('template/footer');?></div>
</html>
      
  
