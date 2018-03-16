<!--@name mapscprgseat.php  @author Manorama Pal(palseema30@gmail.com)  -->

 <?php defined('BASEPATH') OR exit('No direct script access allowed');?>

<html>
    <head>    
       <?php $this->load->view('template/header'); ?>
       <?php //$this->load->view('template/menu');?> 
        <style>
                .abc{
                    width:100%;
                    
                }
            </style>
    </head>    
    <body>
       
        <!--<//?php
           echo "<table width=\"100%\" border=\"1\" style=\"color: black;  border-collapse:collapse; border:1px solid #BBBBBB;\">";
            echo "<tr style=\"text-align:left; font-weight:bold; background-color:#66C1E6;\">";
            echo "<td style=\"padding: 8px 8px 8px 20px;color:white;\">";
            echo "Map";
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            
            echo anchor('map/viewscprgseat/', "View Study Center Program Seat ", array('title' => 'Add Detail' , 'class' => 'top_parent'));
            echo "<span  style='padding: 8px 8px 8px 20px;'> ";
            echo "|";
            echo "<span  style='padding: 8px 8px 8px 20px;'>";
            echo "Map Study Center Program Seat";
            echo "</span>";
            echo "</td>";
            echo "</tr>";
            echo "</table>";
        ?>-->

<!--<table id="uname"><tr><td align=center>Welcome <?//= $this->session->userdata('username') ?>  </td></tr></table>-->

             
        <table width="100%"> 
            
            <?php echo form_error('<div class="isa_error">','</div>');?>
 
            <tr><td>  
                <div>    
                <?php echo anchor('map/viewscprgseat/', "Campus Program Seat List ", array('title' => 'View Detail' , 'class' => 'top_parent'));
                echo "<td align=\"right\">";
		$help_uri = site_url()."/help/helpdoc#MapStudyCenterandProgramwithSeat";
		echo "<a style=\"text-decoration:none\" target=\"_blank\" href=$help_uri><b>Click for Help</b></a>"
		?>
                </td>
                </div>
                <div>
                 
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>

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

<div>
        <table>
            
           <form action="<?php echo site_url('map/mapscprgseat');?>" method="POST" >
            <tr>
                <td><?php echo form_label('Campus Name', 'campus');?>
                </td>
                <td>
                    <?php echo form_dropdown('campus', $campus, '', 'class="abc" style="width:90%;"');?>

                </td>
            </tr>
            <tr>
                <td><?php echo form_label('Program Name', 'program');?></td>
                <td>
                 <?php echo form_dropdown('program',$program,'', 'class="abc" style="width:90%;"');?> 
                </td>
            </tr>
            <tr>
                <td>Number of Seat :</td>
                <td>
                   <input type="text" name="seatno" value="" size="34" style="width:90%;">
                </td>
                <td><?php //echo form_error('seatno')?> <span style= "text-align; left" align=left>Example:30</span></td>                
            </tr>
            <tr>
                <td>Gender :</td>
                <td>
                    <select name="gender"class="my_dropdown" style="width:90%;">
                    <option value="" disabled selected>------Select Gender------</option>  
                    <option value="All" class="dropdown-item">All</option>
                    <option value="Male" class="dropdown-item">Male</option>
                    <option value="Female" class="dropdown-item">Female</option>
                    <option value="Transgender" class="dropdown-item" >Transgender</option>
                    </select>
                </td> 
            </tr>
            <tr>
                <td>Academic Year :</td>
                <td>
                    <select name="academicyear" class="my_dropdown" style="width:90%;">
                    <option value="" disabled selected >------Select Academic year------</option>  
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
                    
                    <select name="semester" id="" class="my_dropdown" style="width:90%;">
                    <option value="" disabled selected >------Select Semester------</option>    
                    <option value="ODD" class="dropdown-item">ODD</option>
                    <option value="EVEN" class="dropdown-item">EVEN</option>
		    </select>
<!--
                        <select name="semester" id="" class="my_dropdown" style="width:100%;">
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
    -->
                </td>
            </tr>
            <tr>
               <td></td>
                <td colspan="2">   
                <button name="mapscprgseat" >Submit</button>
                <button name="reset" >Clear</button>
                </td>
               
            </tr>
        </form>    
        </table></div>
    </body> 
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>
