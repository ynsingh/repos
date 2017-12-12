<!--@name editseatsetting.php
  @author Abhay(kumar.abhay.4187@gmail.com)
 -->
<html>
	<head>    
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){																			
//	var totseat=$this->totalseat;
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
</script>
			
	
	    <?php $this->load->view('template/header'); ?>
            <!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
	    <?php $this->load->view('template/menu');?>
      
         
    </head>
    <body>
	<script>
	function goBack() {
		window.history.back();
	}
	</script>
	<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
	<table width= "100%"> 
	<tr>
		<?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update Seat Reservation Details</b>";
                    echo "</td>";
            ?>
        </tr>
</table>
	<table width="100%">
               <tr><td>
                 <div>
            <?php  echo validation_errors('<div  class="isa_warning">','</div>');?>
            <?php  echo form_error('<div class="isa_error">','</div>');?>

            <?php if(isset($_SESSION['success'])){?>
            <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

             <?php
            };
            ?>
            </div> 
            </td></tr>  
      </table>    
	<table>  
            <?php
		echo form_open('setup/editseatsetting/' . $id);

            	echo "<tr>";
            	echo "<td>";
            	echo form_label('University', 'org_name');
		echo "</td>";
                echo "<td>";
                echo form_input($org_code);
                echo "</td>";
		echo "</tr>";

                echo "<tr>";
                echo "<td>";
                echo form_label('Category', '$cate_name');
                echo "</td>";
		echo "<td>";
		echo form_input($category);
        //        echo "<select name='category' style=\"width: 272px;\">";
	//	echo "<option value=$category[value]>$category[value]</option>";  
//		foreach($this->catresult as $datas):
  //              echo "<option value= $datas->cat_id > $datas->cat_name </option>";
    //            endforeach; 
      //          echo "</select>";
                echo "</td>";
		echo "</tr>";

		echo "<tr>";
		echo "<td>";
		echo form_label('Percentage', 'percentage');
		echo "</td>";
		echo "<td>";
		$perv=$percentage['value'];
		echo "<input id=\"percent\" type=\"text\" name=\"percentage\" size=\"40\"  class=\"form-control\" onkeyup=\"function();\" value=$perv />";
		echo "</td>";
		echo "</tr>";

                echo "<tr>";
		echo "<td>";
		echo form_label('Number of Seat', 'noofseat');
		echo "</td>";
		echo "<td>";
		$seatv=$numberofseat['value'];
		echo "<input type=\"text\" id=\"noofseat\" name=\"numberofseat\" var=\'calp\' size=\"40\" class=\"from-control\" onkeyup=\"function();\" value=$seatv />";
		echo "</td>";
		echo "</tr>";

		echo "<tr>";
		echo "<td>";
		echo "</td>";
		echo "<td>";
		echo "<input id=\"totseat\" type =\"hidden\" value=$this->totalseat >";
                echo form_hidden('id', $id);
                echo form_submit('submit', 'Update');
		echo form_close();
		echo "<button onclick =\"goBack()\">Back</button>";
		echo "</td>";
		echo "</tr>";
        ?>
        </table>   
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



