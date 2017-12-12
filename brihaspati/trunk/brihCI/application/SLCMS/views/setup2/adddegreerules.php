<!--@name adddegreerules.php
    @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 -->
<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html> 
<title>Add Degree Rules</title>

 <head>
      <?php $this->load->view('template/header'); ?>
      <?php $this->load->view('template/menu');?>
      <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
<script>
        function getbranchname(branch){
                var branch = branch;
                //alert(branch);
                $.ajax({
                type: "POST",
                url: "<?php echo base_url();?>slcmsindex.php/setup2/branchlist",
                data: {"programname" : branch},
                dataType:"html",
                success: function(data){
               // alert(data);
                $('#dr_prgid').html(data.replace(/^"|"$/g, ''));
                }
            }); 
        }
</script>   
</head>
<body>
<!--<//?php
        echo "<table border=\"0\" align=\"left\" style=\"color: black;  border-collapse:collapse; border:1px;\">";
        echo "<tr style=\"text-align:left; \">";
                echo "<td style=\"padding: 8px 8px 8px 20px; decoration:none;\">";
        echo anchor('setup/adddegreerules/', "View degree rule ", array('title' => 'Add Rule'));
        echo "</td>";
        echo "</tr>";
        echo "</table>";
        ?>-->
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>

<table width="100%">
            <tr>
                <div>
                <?php
                echo "<td align=\"left\" width=\"33%\">";
                echo anchor('setup2/degreerules/', "View Degree Rules ", array('title' => 'View Degree rules' ,'class' =>'top_parent'));  
                echo "</td>";

                echo "<td align=\"center\" width=\"34%\">";
                echo "<b>Add Degree Rules Details</b>";
                echo "</td>";

                echo "<td align=\"right\" width=\"33%\">";
                $help_uri = site_url()."/help/helpdoc#Degree Rules";
                echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                ?>
                </div>
               </tr>
           </table>
           <table width="100%">
           <tr><td>
               <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                 <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };

                if(isset($_SESSION['err_message'])){?>
                <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
               ?>
              </div>
         </td></tr>
    </table>
    <div> 
    <form action="<?php echo site_url('setup2/adddegreerules');?>" method="POST" class="form-inline">
                <table>
    		<tr><td><label for="programname" class="control-label"> Choose Programme:</label></td><td>
             <select name="programname" id="programname" class="my_dropdown" style="width:100%;" onchange="getbranchname(this.value)" >
             <option value="" disabled  selected >------Select Program Name--------------</option>
             <?php foreach($this->prgresult as $dataspt): ?>
             <option value="<?php echo $dataspt->prg_name; ?>"><?php echo $dataspt->prg_name; ?></option>
             <?php endforeach; ?>
             </td>
      </tr>
           <tr>
           <td><label for="dr_prgid" class="control-label">Choose Branch:</label></td>
           <td>
                <select name="dr_prgid" id="dr_prgid" class="my_dropdown" style="width:100%;">
                <option value="" disabled  selected >------Select Branch Name--------------</option>
            </td>
           </tr>
            </select>
              <tr>
               <td><label for="dr_mincredit" class="control-label">Degree Minimum Credit:</label></td>
               <td>
               <input type="text" name="dr_mincredit"  class="form-control" size="33" />
               </td>
            </tr>
            <tr>
                <td><label for="dr_minsubcredit" class="control-label">Minimum Subject Credit:</label></td>
                <td>
                <input type="text" name="dr_minsubcredit" class="form-control" size="33" />
                </td>
            </tr>
              <tr>
                <td><label for="dr_minthesiscredit" class="control-label">Minimum Thesis Credit:</label></td>
                <td>
                <input type="text" name="dr_minthesiscredit" class="form-control" size="33" />
                </td>
            </tr>
              <tr>
                <td><label for="dr_minsub" class="control-label">Minimum Subjects:</label></td>
                <td>
                <input type="text" name="dr_minsub" class="form-control" size="33" />
                </td>
            </tr>
              <tr>
                <td><label for="dr_minsemester" class="control-label">Minimum Semesters:</label></td>
                <td>
                <input type="text" name="dr_minsemester" class="form-control" size="33" />
                </td>
            </tr>
              <tr>
                <td><label for="dr_mincpi" class="control-label">Minimum CPI:</label></td>
                <td>
                <input type="text" name="dr_mincpi" class="form-control" size="33" />
                </td>
            </tr>
              <tr>
                <td><label for="dr_maxcredit" class="control-label">Maximum Credit:</label></td>
                <td>
                <input type="text" name="dr_maxcredit" class="form-control" size="33" />
                </td>
            </tr>
              <tr>
                <td><label for=" dr_maxsemeter" class="control-label">Maximum Semesters:</label></td>
                <td>
                <input type="text" name="dr_maxsemeter" class="form-control" size="33" />
                </td>
            </tr>
            <tr>
                <td></td><td>
		<button name="adddegreerules" >Add Rule</button>
                <button name="reset" >Clear</button>

                </td>
            </tr>
            </table></div>
   </form>
    <div align="left"> <?php $this->load->view('template/footer');?></div>
    </html>







        


                      
                                                            
    

