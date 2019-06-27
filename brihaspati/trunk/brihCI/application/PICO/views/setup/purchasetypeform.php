<?php defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Purchase Type
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Shivam Kumar Singh  (shivam.iitk1@gmail.com)
 */

?>
<html>
<title>Purchase Type | Form</title>
 <head>    
        <?php $this->load->view('template/header'); 
              

        ?>

        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/bootstrap.min.js" ></script>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>

   <!--  <script>
        function goBack() {
        window.history.back();
        }
    </script>  -->  
</head>
 

<script>
    $(document).ready(function(){
     $('#purchtype').on('change',function(){
                var worktype = $(this).val();
                //alert(worktype);
                if(worktype == ''){
                    $('#subpurchtype').prop('disabled',true);
                   
                }
                else{
                    $('#subpurchtype').prop('disabled',false);
                    $.ajax({

                        url: "<?php echo base_url();?>picoindex.php/picosetup/getsubpurchase",
                        type: "POST",
                        data: {"purchase" : worktype},
                        dataType:"html",
                        success:function(data){
                            //alert("data==1="+data);
                            $('#subpurchtype').html(data.replace(/^"|"$/g, ''));
                                                 
                        },
                        error:function(data){
                            alert("data in error==="+data);
                            alert("error occur..!!");
                 
                        }
                    });
                }
            }); 
    });

</script>

<table width="100%"> 
            <tr>
                <td>
                <?php echo anchor('picosetup/purchasetypedetails/', "View Purchase Types", array('title' => 'View Purchase Types' ,'class' =>'top_parent'));
                echo "<td align=\"right\">";
                $help_uri = site_url()."/help/helpdoc#Designation";
                echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                echo "</td>";
                ?>
            </td>
        </tr>    
            <tr><td>   
              <div align="left">
                    <?php echo validation_errors('<div  class="isa_warning">','</div>');?>
                    <?php echo form_error('<div class="isa_error">','</div>');?></div>

                    <?php if(isset($_SESSION['success'])){?>
                        <div class="isa_success"><?php echo $_SESSION['success'];?></div>

                    <?php
                    };
                    ?>
                </div> </br> 
        </td></tr>  
        </table>
        
<body>  

    <form action="<?php echo site_url('picosetup/addpurchasetype');?>" method="POST" class="form-inline">
        <table> 
            <tr>
                <td><label>Purchase Type : &nbsp;</label></td>
                <td>
                <select name="purchtype" id="purchtype" class="my_dropdown" style="width:100%;" >
                    <option value="" selected disabled>------Select-------</option>
                    <option value="Minor">Minor Purchase</option>
                    <option value="Medium">Medium Purchase</option>
                    <option value="Major">Major Purchase</option>                    
                </select>
                </td>    
            </tr>

            <tr>
                <td><label for="subpurchtype" class="control-label">Sub Purchase Type: &nbsp;</label></td>
                
                <td>
                    <select name="subpurchtype" id="subpurchtype" style="width:100%;" class="my_dropdown">
                        <option value="" disabled selected >------Select-------</option>
                    </select>
                </td>
        
            </tr>

            <tr>
                <td><label for="amt" class="control-label">Amount: &nbsp;</label></td>

                <td>
                    <input type="text" name="amt_above" placeholder="Above">
                    <input type="text" name="amt_below" placeholder="Below">
                </td>

            </tr> 

            <tr>
                <td><label for="desc" class="control-label">Description: &nbsp;</label></td>

                <td>
                    
                    <textarea name="desc" placeholder="Give Description Here..." style="width: 100%;"></textarea>
                </td>

            </tr>

            <tr>
                <td></td>
                <td>
                    <button name="submit_purch"> Submit </button>
                   <!--  <//?php
                        
                        echo "<button onclick=\"goBack()\" >Back</button>";
                    ?> -->
                </td>

            </tr>     

            
        </table>
    </form>

</body>
<p>&nbsp;</p>
<div align="center">  <?php $this->load->view('template/footer');?></div>
</html>