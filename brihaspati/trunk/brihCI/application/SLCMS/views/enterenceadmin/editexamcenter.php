<!--@name editexamcenter.php
    @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
<title>Edit Exam Center</title>
    <head>
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
<script>
function calculate() {
                var myBox1 = document.getElementById('box1').value;     
                var myBox2 = document.getElementById('box2').value;
                var result = document.getElementById('result'); 
                var myResult = myBox1 * myBox2;
                result.value = myResult;
        
        }
</script>
 <table width="100%">
            <tr><td>
              <div align="left">
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?></div>

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

            echo form_open('enterenceadmin/editexamcenter/' . $id);


            echo "<tr>";
                echo "<td>";
                echo form_label('Enterance Exam Center Code', 'eec_code');
                echo "</td>";
                echo "<td>";
                echo form_input($eec_code);
                echo "</td>";
            	echo "</tr>";
		echo "<tr>";
                echo "<td>";
                echo form_label('Enterance Exam Center Name', 'eec_name');
                echo "</td>";
                echo "<td>";
                echo form_input($eec_name);
                echo "</td>";
            	echo "</tr>";
		echo "<tr>";
                echo "<td>";
                echo form_label('Enterance Exam Center Address', 'eec_address');
                echo "</td>";
                echo "<td>";
                echo form_input($eec_address);
                echo "</td>";
            	echo "</tr>";
		?>
		<tr><td>State: </td><td>
                <select name="eec_state"  id="state_id" disabled="">
               <option value="<?php echo $eec_state['value'];?>"><?php echo$this->commodel->get_listspfic1('states','name','id',$eec_state['value'])->name ;?></option>;
                <?php foreach($this->cresult as $datas): ?>
                <option value="<?php echo $datas->id; ?>"><?php echo $datas->name; ?></option>
                <?php endforeach; ?>
                </select>
                </tr></td>
                <tr><td>City: </td><td>
                <select style="height:35px;" name="eec_city" id="citname" disabled="">
                 <option value="<?php echo $eec_city['value'];?>"><?php echo$this->commodel->get_listspfic1('cities','name','id',$eec_city['value'])->name ;?></option>;
                </select>
                </tr></td>
		<?php
                echo "<td>";
                echo form_label('Enterance Exam Center Incharge', 'eec_incharge');
                echo "</td>";
                echo "<td>";
                echo form_input($eec_incharge);
                echo "</td>";
            	echo "</tr>";
		echo "<tr>";
                echo "<td>";
                echo form_label('Enterance Exam Center Number of Room', 'eec_noofroom');
                echo "</td>";
                echo "<td>";
		$numroom=$eec_noofroom['value'];
 		echo "<input id=\"box1\" type=\"text\" name=\"eec_noofroom\" size=\"37\"  class=\"form-control\" oninput=\"calculate();\" value=$numroom />";
                echo "</td>";
            	echo "</tr>";
		echo "<tr>";
                echo "<td>";
                echo form_label('Enterance Exam Center Capacity in Room', 'eec_capacityinroom');
                echo "</td>";
                echo "<td>";
		$totalroom=$eec_capacityinroom['value'];
		echo "<input id=\"box2\" type=\"text\" name=\"eec_capacityinroom\" size=\"37\"  class=\"form-control\" oninput=\"calculate();\" value=$totalroom />";
                echo "</td>";
                echo "</tr>";
		echo "<tr>";
                echo "<td>";
                echo form_label('Enterance Exam Center Total Capacity', 'eec_totalcapacity');
                echo "</td>";
                echo "<td>";
		$totalcapacity=$eec_totalcapacity['value'];
                echo "<input id=\"result\" type=\"text\" name=\"eec_totalcapacity\" size=\"37\"  class=\"form-control\" value=$totalcapacity readonly />";
                echo "</td>";
                echo "</tr>";
		echo "<tr>";
                echo "<td>";
                echo form_label('Enterance Exam Center Contact No', 'eec_contactno');
                echo "</td>";
                echo "<td>";
                echo form_input($eec_contactno);
                echo "</td>";
                echo "</tr>";
		echo "<tr>";
                echo "<td>";
                echo form_label('Enterance Exam Center Contact Email', 'eec_contactemail');
                echo "</td>";
                echo "<td>";
                echo form_input($eec_contactemail);
                echo "</td>";
            	echo "</tr>";
		echo "<td>";
                    echo form_hidden('exty_id', $id);
                   echo"<td>";
                    echo form_submit('submit', 'Update');
                   echo " ";

            echo form_close();
            echo "<button onclick=\"goBack()\" >Back</button>";
            echo "</td>";
            echo "</tr>";
            echo"</td>";
 ?>

       </table>
</body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
                              
