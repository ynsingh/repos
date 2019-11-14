<!--@filename hra_pacegradelist.php  @author Manorama Pal(palseema30@gmail.com)-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>HRA Place Grade List</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/multiselect/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/multiselect/bootstrap-multiselect.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap3.3.6/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap3.3.6/bootstrap-multiselect.js" ></script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        
        <form action="<?php echo site_url('report/retiredemplist');?>" id="myForm" method="POST" class="form-inline">
             <table width="100%"><tr >   
                <td align="center"><b>HRA Place(Grade)</b> </td>  
                </tr></table>
                <div class="scroller_sub_page">
                    <table class="TFtable" >
                        <thead><tr>
                            <th>Sr.No</th>
                            <th>Grade Name</th>
                            <th>Places</th>
                            <th>Distance</th>
                            <th>Description</th>
                            
                   
                        </tr></thead>
                        <tbody>
                            <?php $serial_no = 1;?>
                            <?php if( count($hrarecord) ):  ?>
                                <?php foreach($hrarecord as $record){ ?>
                                <tr>
                                    <td><?php echo $serial_no++; ?></td>
                                    <td><?php echo $record->hgc_gradename; ?></td>
                                    <td><?php $str= $record->hgc_place; 
                                            echo wordwrap($str,80,"<br>\n");
                                        ?>
                                    </td>
                                    <td><?php echo $record->hgc_distancecover; ?></td>
                                    <td><?php $str1=$record->hgc_description;
                                            echo wordwrap($str1,60,"<br>\n");
                                        ?>
                                    </td>
                                    
                                    
                                
                                </tr>
                                <?php }; ?>
                            <?php else : ?>
                                <td colspan= "13" align="center"> No Records found...!</td>
                            <?php endif;?> 
                        </tbody>
                    </table> 
                </div><!------scroller div------>        
        </form>
        <p> &nbsp; </p>
            <div align="center">  <?php $this->load->view('template/footer');?></div>    
    </body>        
</html>        
