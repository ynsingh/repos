
<!--@filename uploadphoto.php  @author Manorama Pal(palseema30@gmail.com)-->

<?php defined('BASEPATH') OR exit('No direct script access allowed');?>
<html>
    <head>
        <title>Welcome to TANUVAS</title>
        <!--<link rel="stylesheet" type="text/css" href="<//?php echo base_url(); ?>assets/css/tablestyle.css">-->
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#btnUpload").on('click',function(){
                    var allowedFiles = [".zip"];
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
        <table width="100%;">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b>Upload Staff Photo</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#UploadStaffPhoto";
                    /*echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";*/
                    echo "</td>";
                 
                ;?>
            </tr>
        </table>
        
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
            <tr><td align="left"><b>Note : The file extension should be in zip.</b>
                    <br/><b> zip folder contains employees photo and photo name should be employee PF No.</b> 
                </td>
            </tr>

            	 <?php echo form_open_multipart('upl/uplstaffphoto');?>
                <tr>
                    <td align="left">
                        <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                        <div><label for="file" style="font-size:15px;"><b>Choose a zip file to upload:</b></label>
                            <input type='file'  id="fileUpload" name='userfile' size='20' accept=".zip" />
                            <input type='submit' name="staffphotoupl"  value='upload'/>
                        </div>
                    </td> 
                                 
                </tr>
		</form>
        </table>    
        <p> &nbsp; </p>
        <div align="center">  <?php $this->load->view('template/footer');?></div>
    </body>    
</html>    
