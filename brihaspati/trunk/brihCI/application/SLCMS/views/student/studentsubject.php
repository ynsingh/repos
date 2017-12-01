<!--
    @name subject.php
    @author Sharad Singh(sharad23nov.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');

echo "<html>";
echo"<title>Add Subject</title>";
echo "<head>";

    $this->load->view('template/header');
    echo "<h1>"; 
    echo "Welcome "; echo$this->session->userdata('username'); 
    echo"</h1>";
    $this->load->view('template/stumenu'); 
    //$this->load->view('template/menu');
?>
<?php
echo "</head>";
echo "<body>";
?>
<br>
<table style="margin-left:9px;">
<tr colspan="2"><td>
<div>
</td></tr>
</table>
</div>

    <table>
    <tr colspan=2><td>
    <div  style="margin-left:30px;width:1700px;">

    <?php echo validation_errors('<div style="margin-left:30px;" class="isa_warning>','</div>');?>
    <?php echo form_error('<div style="margin-left:30px;" class="isa_error">','</div>');?>

     <?php if(isset($_SESSION['success'])){?>
       <div style="margin-left:30px" class="isa_success"><?php echo $_SESSION['success'];?></div>
<?php    }
    if(isset($_SESSION['error']))
    {
?>        <div style="margin-left:30px"; class="isa_error">"<?php echo $_SESSION['error'];?> </div>
<?php
    }
    if(isset($_SESSION['warning']))
    {
?>        <div style="margin-left:30px"; class="isa_warning">"<?php echo $_SESSION['warning'];?> </div>
<?php
    }

    echo "</td></tr>";
    echo "</table>";
    echo "<div style=\"margin-left:30px;\">";

    /* Form */
/*
echo $stid;
echo $acadyear;
echo $semester;
*/
    //print_r($subjectsem);
    $compsubject = array();
    $elecsubject = array();
    for($i=0; $i<sizeof($subjectsem); $i++)
    {
        $subdata = $subjectsem[$i];
        $sub_data=explode('#',$subdata);
        $subid = $sub_data[0];
        $subtype = $sub_data[1];
        if($subtype == "Compulsory")
            $compsubject[] = $subid;
        else
            $elecsubject[] = $subid;
            
    }
    echo "<table>";
    echo form_open('studenthome/studentsubject/');
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Student Name', 'studname');
    echo"</td><td>";
    echo $compname;
    echo "</td><td>";echo form_error('compname');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Name', 'prgname');
    echo"</td><td>";
    echo $prg_name;
    echo "</td><td>";
    echo form_error('prgname');
    echo"</td><td>";
    echo "</p>";


    echo "<p>";
    echo "<tr><td>";
    echo form_label('Enrollment No', 'enrollno');
    echo"</td><td>";
    echo $enrollno;
    echo "</td><td>";
    echo form_error('enrollno');  
    echo"</td><td>";
    echo "</p>";
/*
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Roll No', 'rollno');
    echo"</td><td>";
    echo $rollno;
    echo "</td><td>";echo form_error('rollno');echo"</td></tr>";
    echo "</p>";
*/
    echo "<p>";
    echo "<tr><td>";
    echo form_label('Academic Year', 'acadyear');
    echo"</td><td>";
    echo $acadyear;
    echo "</td><td>";echo form_error('rollno');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Semester', 'semester');
    echo"</td><td>";
    echo $semester;
    echo "</td><td>";echo form_error('rollno');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Minimum Credit : ', 'semester');
    echo"</td><td>";
    echo $semmincredit;
    echo "</td><td>";echo form_error('rollno');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Maximum Credit : ', 'semester');
    echo"</td><td>";
    echo $semmaxcredit;
    echo "</td><td>";echo form_error('rollno');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Semester CPI : ', 'semester');
    echo"</td><td>";
    echo $semcpi;
    echo "</td><td>";echo form_error('rollno');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Compulsory Subject', 'subjectlist1');
    echo"</td><td>";
    $totalcr = 0;
    for($i = 0; $i<sizeof($compsubject); $i++)
    {
        echo "<tr>";
        //echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;". $compsubject[$i];
        $subjectdata = $this->commodel->get_listrow('subject','sub_id',$compsubject[$i]);
        
        echo"<td><i>";echo "&nbsp;&nbsp;&nbsp;".$subjectdata->row()->sub_name;echo"</i></td>"; 
        echo"<td>";echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;".$subjectdata->row()->sub_ext1;echo"</td>"; 
        $totalcr = $totalcr + $subjectdata->row()->sub_ext1;
        
    echo "<tr>";
    }
    echo "<tr><td>";echo "&nbsp;&nbsp;&nbsp;Total";echo "</td><td>";echo"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;". $totalcr ;echo "</td></tr>";  
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Elective Subject', 'subjectlist1');
    echo"</td><td>";
    $totalcr_el = 0;
    for($i = 0; $i<sizeof($elecsubject); $i++)
    {
        echo "<tr>";
        $elecsubjectdata = $this->commodel->get_listrow('subject','sub_id',$elecsubject[$i]);
        echo"<td>"; 
?>
        <input type="checkbox" name="elecsubject[]" value="<?= $elecsubject[$i]?>">
<?php
        echo "<i>&nbsp;&nbsp;&nbsp;".$elecsubjectdata->row()->sub_name;echo"</i></td>";
        echo"<td>";echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;".$elecsubjectdata->row()->sub_ext1;echo"</td>";

        $totalcr_el = $totalcr_el + $elecsubjectdata->row()->sub_ext1;

        echo "</tr>";
    }
    echo "<tr><td>";echo "&nbsp;&nbsp;&nbsp;Total";echo "</td><td>";echo"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;". $totalcr_el ;echo "</td></tr>";
    echo "</td></tr>";
    echo "</p>";



    echo "<p>";
    echo "<tr><td>";
    echo form_hidden('ctotal', $totalcr);
    echo form_submit('submit', 'Submit');
    echo"</td></tr>";
    echo "</p>";

    echo form_close();
    echo "</table>";
    /* Form */
echo"</body>";
echo "<p><br><br><br></p>";
echo "<div align=\"center\">";  
    $this->load->view('template/footer');
echo "</div>";
echo "</html>";
?>
