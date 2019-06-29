
<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name: Display Financial Power
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */
?>

<html>
<title>Purchase Type | Details</title>
  <head>
   <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
   <?php $this->load->view('template/header'); ?>
    
  </head>
 <body>

        <table width="100%">

          <tr><td>
            <?php
              echo anchor('picosetup/purchasetype/',"Add Purchase Type", array('title' =>'Purchase Type Form', 'class'=>'top_parent' ));
            ?>
          </td></tr>

          <tr>
            <div>
                <?php echo validation_errors('<div class="isa_warning">','</div>');?>
                <?php if(isset($_SESSION['success'])){?>
                <div class="isa_success"><?php echo $_SESSION['success'];?></div>
                <?php
                };
                ?>
                <?php if(isset($_SESSION['err_message'])){?>
                    <div class="isa_error"><?php echo $_SESSION['err_message'];?></div>
                <?php
                };
               ?>
            </div>
          </tr>
      </table>
      <div class="scroller_sub_page">
      <table class="TFtable" >
      <tr>
<thead><th>Sr.No</th><th>Type of Purchase</th><th>Sub Purchase Type</th><th>Amount Above<br>(Rs)</th><th>Amount Below<br>(Rs)</th><th>Item Description</th><th>Action</th></tr></thead>
<tbody>
   <?php
        $count =0;
        //foreach ($query->result() as $row)
        foreach ($result as $row)
        {  
         ?>
             <tr>
            <td> <?php echo ++$count; ?> </td> 
            <td> <?php echo $row->purch_type ?></td>
            <td> <?php echo $row->sub_purch_type ?></td>
            <td> <?php echo $row->amt_above ?></td>
            <td> <?php echo $row->amt_below ?></td>
            <td> <?php echo $row->pt_desc ?></td>

      <td>
         <?php  
    if ($row->pt_id > 0){
          
          echo "&nbsp; ";
                echo anchor('picosetup/deletepurchasetype/' . $row->pt_id , "Delete", array('title' => 'Delete' , 'class' => 'red-link')) . " ";
               // echo anchor('picosetup/editfinancialpower/' . $row->pt_id , "Modify", array('title' => 'Modify' , 'class' => 'red-link')) . " ";
        }
            echo "</td>";
            echo "</tr>";
          
        }
          ?>  
</tbody>
</table>
<br><br>
</div><!------scroller div------>
</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>



