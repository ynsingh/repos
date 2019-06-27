
<!--@filename servicedata.php  @author Manorama Pal(palseema30@gmail.com)-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/tablestyle.css">
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#btnUpload").on('click',function(){
                    var allowedFiles = [".csv", "text/csv"];
                    var fileUpload = $("#fileUpload");
                    var lblError = $("#lblError");
                    var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+(" + allowedFiles.join('|') + ")$");
                    if (!regex.test(fileUpload.val().toLowerCase())) {
                        lblError.html("Please upload files having extensions: <b>" + allowedFiles.join(', ') + "</b> only.");
                        return false;
                    }
                    lblError.html('');
                    return true;
                });
            });
        </script>
    </head>
    <body>
        <?php $this->load->view('template/header'); ?>
        
       
         <div> 
            <?php
               if((isset($_SESSION['success'])) && ($_SESSION['success'])!=''){
                echo "<div  class=\"isa_success\">";
                echo $_SESSION['success'];
                echo "</div>";
                }
                if((isset($_SESSION['error'])) && (($_SESSION['error'])!='')){
                echo "<div  class=\"isa_error\">";
                echo $_SESSION['error'];
                echo "</div>";
                }
            ;?> 
                               
        </div>
         <table   style="font-size:13px;" >
            <tr><td align="left"><b>Note : The file extension should be in csv. The format of staff service detail file is --</b>
                </td>
            </tr>
            <tr><td align="left"><br/><b>Employee Code &nbsp; Place of working(campus code) &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;Designation Code &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; AGP Code &nbsp;&nbsp;&nbsp;&nbsp;Date of AGP &nbsp;&nbsp;&nbsp;&nbsp; Date From &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date To&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
                                    <br/>C1234&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MVC01&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;AP01&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;12&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;2017-10-25&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2017-10-25&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2017-11-25 
                                      
                </td>
            </tr>
            <!--<//?php echo form_open_multipart('upl/servicedata/' .$dataid);?>-->
            <?php echo form_open_multipart('upl/servicedata');?>
                <tr>
                    <td align="left">
                        <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                        <div><label for="file" style="font-size:15px;"><b>Select File</b></label>
                        <input type='file'  id="fileUpload" name='userfile' size='20' accept=".csv, text/csv" />
                        <input type='submit' name="servicerecord" id="btnUpload"  value='upload'/></div>
                    </td> 
                                 
                </tr>
            <!--<?php echo form_hidden('id', $dataid);?>-->
            <?php echo form_close();?>
        </table>    
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>    
</html>    