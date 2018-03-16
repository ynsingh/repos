<!--
    @name editsubject.php
    @author Sharad Singh(sharad23nov.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');

echo "<html>";
echo"<title>Edit Subject</title>";
?>
<!--<link rel="shortcut icon" href="<?php //echo base_url('assets/images'); ?>/index.jpg">-->
	<link rel="icon" href="<?php echo base_url('uploads/logo'); ?>/nitsindex.png" type="image/png">	
<?php
echo "<head>";

    $this->load->view('template/header');
    //echo "<h1>"; 
    //echo "Welcome "; echo$this->session->userdata('username'); 
    //echo"</h1>";
//    $this->load->view('template/menu');
?>
<?php
echo "</head>";
echo "<body>";
/*    echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
    echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
    echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
    echo "Map";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo anchor('setup/viewsubject/', "View Subject", array('title' => 'Subject Detail' , 'class' => 'top_parent'));
    echo "<span  style=\"padding: 8px 8px 8px 20px;\"> ";
    echo "|";
    echo "<span  style=\"padding: 8px 8px 8px 20px;\">";
    echo "Subject List";
    echo "</span>";
    echo "</td>";
    echo "</tr>";
    echo "</table>";
    echo"</br>";
*/
?>
<body>
<script>
        function goBack() {
        window.history.back();
        }
    </script>

<!--	<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->
	<table width="100%">
	<tr>
	            <?php //echo anchor('setup/viewsubject/', "Subject List" ,array('title' => 'Subject List' , 'class' => 'top_parent'));
		    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update Subject Details</b>";
                    echo "</td>";
		    ?>
		</tr>
	</table>
		   <table width="100%">
		   <tr><td>
		    <div>
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['error'])){?>
                    <div class="isa_error"><?php echo $_SESSION['error'];?></div>

                    <?php
                    };
                    ?>
                </div>
              </td>
            </tr>
        </table>

<?php
    echo "<table>";
    echo form_open('setup/editsubject/'.$subid);

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Program Name', 'subprg');
    echo"</td><td>";
    $prgid = $subprg['value'];
    $prgname = $this->common_model->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name;
    $prgbranch = $this->common_model->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch;
    echo  $prgname ." ( ".$prgbranch ." )";
   // echo form_input($degree);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Semester/Year', 'subsem');
    echo"</td><td>";
    echo form_input($subsem);
    echo "</td><td>";echo form_error('subsem');echo"</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Type', 'subtype');
    echo"</td><td>";
    echo form_input($subtype);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Name', 'subname');
    echo"</td><td>";
    echo form_input($subname);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Code', 'subcode');
    echo"</td><td>";
    echo form_input($subcode);
    echo "</td><td>";
  //  echo form_error('subcode');  
    echo"</td><td>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Short', 'subshort');
    echo"</td><td>";
    echo form_input($subshort);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Description', 'subdesc');
    echo"</td><td>";
    echo form_input($subdesc);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Credit', 'subext1');
    echo"</td><td>";
    echo form_input($subext1);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo form_label('Subject Ext1', 'subext2');
    echo"</td><td>";
    echo form_input($subext2);
    echo "</td></tr>";
    echo "</p>";

    echo "<p>";
    echo "<tr><td>";
    echo"<td>";
    echo form_submit('submit', 'Update');
    //echo form_button('submit', 'Submit');
    echo"";
//    echo anchor('setup/viewsubject', 'Back', array('class' => 'top_parent'));
  //  echo"</td></tr>";
 //   echo "</p>";
    echo form_close();
    echo "<button onclick=\"goBack()\" >Back</button>";
    echo"</td>";
    echo "</table>";
    /* Form */

echo"</body>";
echo "<div align=\"center\">";  
    $this->load->view('template/footer');
echo "</div>";
echo "</html>";
?>
