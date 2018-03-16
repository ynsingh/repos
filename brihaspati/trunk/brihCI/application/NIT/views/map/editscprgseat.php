<!--@name` editscprgseat.php  @author Manorama Pal(palseema30@gmail.com) -->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
        <?php $this->load->view('template/header'); ?>
            <!--h1>Welcome <?//= $this->session->userdata('username') ?>  </h1-->
        <?php //$this->load->view('template/menu');?>
       
    </head>
    <body>
   <script>
        function goBack() {
        window.history.back();
        }
    </script>

    <!--  <//?php
            echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Setup";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
           
            echo anchor('map/viewscprgseat/', "View Study Center Program Seat ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Edit Study Center Program Seat";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>  -->
<!--<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>-->
        <table width="100%"> 
            <tr>    
		<?php
                    echo "<td align=\"center\" width=\"100%\">";
                    echo "<b>Update Study Center and Program with Seat Details</b>";
                    echo "</td>";
            	?>
	</tr>
</table>
		<table width="100%">
                <tr><td>
                <div>
                    <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?>

                    <?php if(isset($_SESSION['success'])){?>
                        <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                    <?php if(isset($_SESSION['err_message'])){?>
                    <div  class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                    <?php
                    };
                    ?>        
                </div> 
            </td></tr>
        </table> 
    <!--<table style="margin-left:50px;">
    <tr><td align="left"> Edit Campus Program seat</td></tr> 
    </table><br/>-->
   
    <table>
          <form action="<?php echo site_url('map/editscprgseat/' .$id );?>" method="POST" class="form-inline">
            
              <tr>
                <td>Campus Name</td>
                <td>
                    <?php echo form_input($campus); ?>
                                                                     

                </td>
            </tr>
            <tr>
                <td>Program Name</td>
                <td>
                 <?php echo form_input($program); ?>
                </td>
            </tr>
            <tr>
                <td>Number of Seat :</td>
                <td>
                  <?php echo form_input($seatno); ?>
                 
                </td>
                <td><?php echo form_error('seatno')?></td>
                <td>Example : 30</td>
            </tr>
            <tr>
                <td>Gender :</td>
                <td>
                 
                  <select name="gender" class="my_dropdown" style="width:100%;">
                    <option value="<?php echo $gender['value'];?>" style="display:none"><?php echo $gender['value'];?></option>                 
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                     <option value="Transgender">Transgender</option>
                    </select>
                </td> 
            </tr>
            <tr>
                <td>Academic Year :</td>
                <td>
                    <select name="academicyear" class="my_dropdown" style="width:100%;">
                        <option value="<?php echo $academicyear['value'];?>" style="display:none"><?php echo $academicyear['value'];?></option>                 
                    <?php
                                                                           
                      for($i = 2016; $i < date("Y")+10; $i++){
                        $j=$i+1;                   
                        echo '<option value="'.$i.' - '.$j.'">'.$i.' - '.$j.'</option>';
                                                                             
                        }
                        ?>
                    </select>
                  
                </td>
            </tr>
            <tr>
                <td>Semester :</td>
                <td>
                    <select name="semester" class="my_dropdown" style="width:100%;">
                    <option value="<?php echo $semester['value'];?>" style="display:none"><?php echo $semester['value'];?></option>                 
                    <option value="ODD">ODD</option>
                    <option value="Even">Even</option>
                    </select>
                </td>
            </tr>
            <tr>
                
               <td></td>
                <td colspan="2">   
                <button name="editscprgseat" >Update</button>
            <?php echo form_hidden('id', $id);?>
        </form>
       		<?php echo "<button onclick=\"goBack()\" >Back</button>";?>
                <//?php echo anchor('map/viewscprgseat', 'Back', 'Back to ViewPage');?>
                </td>
               
            </tr>
      </table>
    </body>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
