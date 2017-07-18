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
            <h1>Welcome <?= $this->session->userdata('username') ?>  </h1>
	    <?php $this->load->view('template/menu');?>
      
         
    </head>
    <body>
	<script>
	function goBack() {
		window.history.back();
	}
	</script>
	<table style="margin-left:30px;"> 
	<font size=3pt>
	<div style="margin-left: 10px; width:200px;">
	<?php echo anchor('setup/dispseatsetting/', "View Seat Reservation", array('title' => 'Add Detail' , 'class' => 'top_parent')) . " "; ?>
	</font>
            <tr colspan="2"><td>    
            <div style="margin-left:30px;width:1000px;">
            <?php  echo validation_errors('<div style="margin-left:30px;" class="isa_warning">','</div>');?>
            <?php  echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

            <?php if(isset($_SESSION['success'])){?>
            <div style="margin-left:30px;" class="isa_success"><?php echo $_SESSION['success'];?></div>

             <?php
            };
            ?>
            </div> </br> 
            </td></tr>  
      </table>    
	<table style="padding: 8px 8px 8px 30px;">  
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
		echo "<input id=\"percent\" type=\"text\" name=\"percentage\" size=\"37\"  class=\"form-control\" onkeyup=\"function();\" value=$perv />";
		echo "</td>";
		echo "</tr>";

                echo "<tr>";
		echo "<td>";
		echo form_label('Number of Seat', 'noofseat');
		echo "</td>";
		echo "<td>";
		$seatv=$numberofseat['value'];
		echo "<input type=\"text\" id=\"noofseat\" name=\"numberofseat\" var=\'calp\' size=\"37\" class=\"from-control\" onkeyup=\"function();\" value=$seatv />";
		echo "</td>";
		echo "</tr>";

		echo "<tr>";
		echo "<td>";
		echo "</td>";
		echo "<td>";
		echo "<input id=\"totseat\" type =\"hidden\" value=$this->totalseat >";
                echo form_hidden('id', $id);
                echo form_submit('submit', 'Update');
		echo "<button onclick =\"goBack()\">Back</button>";
		echo "</td>";
		echo "</tr>";
		echo form_close();
        ?>
        </table>   
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



