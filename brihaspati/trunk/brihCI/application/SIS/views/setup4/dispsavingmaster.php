<!--@name dispsavingmaster.php
  @author Deepika Chaudhary(chaudharydeepika88@gmail.com)
 -->
<html>
 <title>View Master Saving</title>
    <head>    
        <?php $this->load->view('template/header'); ?>
        
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">    
    </head>
    <body>

      <table width="100%;">
            <tr>
            <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo anchor('setup4/savingmaster/', "Add Saving Master" ,array('title' => 'Add Saving Master ' , 'class' => 'top_parent'));
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\">";
                    echo "<b>Saving Master Details</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\">";
                    //$help_uri = site_url()."/help/helpdoc#ViewEmailSetting";
                   // echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
            ?>
 <div>

                <?php echo validation_errors('<div class="isa_warning>','</div>');?>

                <?php if(isset($_SESSION['success'])){?>
                    <div  class="isa_success"><?php echo $_SESSION['success'];?></div>

                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>

                <?php
                };
                ?>

            </div>
</td></tr>
        </table>
<div class="scroller_sub_page">
        <table class="TFtable" >
            <thead>
                <tr>
                    <th>Financial Year</th>
                    <th>U/S 80C</th>
                    <th>U/S 80CCD (1-B) </th>
                    <th>U/S 80D</th>
                    <th>U/S 80DD</th>
                    <th>U/S 80E</th>
                    <th>U/S 80G</th>
                    <th>U/S 80GGA</th>
                    <th>U/S 80U</th>
		    <th>U/S 24B</th>
		    <th>Other</th>
                    <th></th>
                </tr>
            </thead>
 <tbody>
              <?php if( count($this->savresult) ): ?>
                    <?php foreach($this->savresult as $row){ ?>
                        <tr>
                            <td><?php echo $row->sm_fyear; ?></td>
                            <td><?php echo $row->sm_80C; ?></td>
                            <td><?php echo $row->sm_80CCD; ?></td>
                            <td><?php echo $row->sm_80D;?></td>
                            <td><?php echo $row->sm_80DD ; ?></td>
                            <td><?php echo $row->sm_80E; ?></td>
                            <td><?php echo $row->sm_80G; ?></td>
                            <td><?php echo $row->sm_80GGA; ?></td>
                            <td><?php echo $row->sm_80U; ?></td>
                            <td><?php echo $row->sm_24B; ?></td> 
			    <td><?php echo $row->sm_other;?></td> 
                            <td> 
                          
			&nbsp;<?php echo anchor('setup4/editsavingmaster/' . $row->sm_id  , "Edit", array('title' => 'Details' , 'class' => 'red-link')) . " ";
			?>
                         </td>
                        </tr>
                    <?php }; ?>
                <?php else : ?>
                    <td colspan= "7" align="center"> No Records found...!</td>
                <?php endif;?>

            </tbody>
        </table>
        </div><!------scroller div------>
    </body>
<p>&nbsp;</p>
    <div align="center">  <?php $this->load->view('template/footer');?></div>
</html>

