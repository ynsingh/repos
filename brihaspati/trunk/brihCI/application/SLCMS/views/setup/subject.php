<!--
    @name subject.php
    @author Sharad Singh(sharad23nov.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');

echo "<html>";
echo"<title>Add Subject</title>";
echo "<head>";

    $this->load->view('template/header');
    $this->load->view('template/menu');
?>
<?php
echo "</head>";
echo "<body>";
/*
    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Map";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo anchor('setup/viewsubject/', "View Subject", array('title' => 'Subject Detail' , 'class' => 'top_parent'));
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\">";
    echo "Add Subject";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
    echo"</br>";
*/
?>
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
<table width="100%;">
<tr><td>
<div>
	<?php echo anchor('setup/viewsubject/', "Subject List" ,array('title' => 'Subject List' , 'class' => 'top_parent'));
	 echo "<td align=\"right\">";
	$help_uri = site_url()."/help/helpdoc#Subject";
	echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
	echo"</td>"
	?>
</td></tr>
</table></div>
<table><tr>
    <div>
<?php echo validation_errors('<div class="isa_warning">','</div>');?>
    <?php echo form_error('<div  class="isa_error">','</div>');?>

     <?php if(isset($_SESSION['success'])){?>
       <div  class="isa_success"><?php echo $_SESSION['success'];?></div>
<?php    }
    if(isset($_SESSION['error']))
    {
?>        <div  class="isa_success">"<?php echo $_SESSION['error'];?> </div>
</div>
<?php
    }
    echo "</tr>";
    echo "</table>";
    echo "</div>";

    /* Form */
    echo "<table>";
    echo form_open('setup/subject');
    
    echo "<p>";
    echo "<tr><td>"; 
    echo form_label('Program Name', 'prgname');
?>
</td><td>
     <select name="program" style="width:100%;">
                        <option value=""disabled selected>---------Select program ---------</option>
                        <?php foreach($this->prgresult as $datas): ?>
                        <option value="<?php echo $datas->prg_id; ?>"><?php echo $datas->prg_name."(".$this->common_model->get_listspfic1('program','prg_branch','prg_id',$datas->prg_id)->prg_branch.")"; ?></option>
                        <?php endforeach; ?>
                        </select>
                        </td></tr>
<?php
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Semester/Year', 'semester');
?>
                        </td><td>
                        <select name="sub_semester" id="" class="my_dropdown" style="width:100%;">
                        <option value="" disabled selected >------Select Semester------</option>
                        <option value="1" class="dropdown-item">1</option>
                        <option value="2" class="dropdown-item">2</option>
                        <option value="3" class="dropdown-item">3</option>
                        <option value="4" class="dropdown-item">4</option>
                        <option value="5" class="dropdown-item">5</option>
                        <option value="6" class="dropdown-item">6</option>
                        <option value="7" class="dropdown-item">7</option>
                        <option value="8" class="dropdown-item">8</option>
                        </select>
                        </td></tr>
<?php
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Type','subtype'); ?>
	</td><td>
                <select name="sub_subtype"class="my_dropdown" style="width:100%;">
                <option value="" disabled selected>------Select Subject Type------</option>
                <option value="Compulsory" class="dropdown-item">Compulsory</option>
                <option value="Elective" class="dropdown-item">Elective</option>
                </select>
                </td>
                </tr>
<?php
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Name', 'subname');
    echo"</td><td>";
    echo form_input($subname);
    echo "</td><td>";echo form_error('subname');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Credits', 'subext1');
    echo"</td><td>";
    echo form_input($subext1);
    echo "</td><td>";echo form_error('subext1');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Code', 'subcode');
    echo"</td><td>";
    echo form_input($subcode);
    echo "</td><td>";
    echo form_error('subcode');  
    echo"</td><td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Short', 'subshort');
    echo"</td><td>";
    echo form_input($subshort);
    echo "</td><td>";echo form_error('subshort');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Description', 'subdesc');
    echo"</td><td>";
    echo form_input($subdesc);
    echo "</td><td>";echo form_error('subdesc');echo"</td></tr>";
    echo "</p>";


    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Ext1', 'subext2');
    echo"</td><td>";
    echo form_input($subext2);
    echo "</td><td>";echo form_error('subext2');echo"</td></tr>";
    echo "</p>";

  //  echo "<p>";
    echo "<tr><td>";
    echo"<td>";
    echo form_submit('submit', 'Submit');
    echo form_submit('reset', 'Clear');
    //echo form_button('submit', 'Submit');
    echo"</td></tr>";
//    echo "</p>";
    echo form_close();
    echo "</table>";
    
    /* Form */
echo"</body>";
echo "<div>";  
    $this->load->view('template/footer');
echo "</div>";
echo "</html>";
?>
