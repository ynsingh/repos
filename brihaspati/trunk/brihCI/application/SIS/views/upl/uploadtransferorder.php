<!DOCTYPE html>
<!--@name uploadtransferorder.php 
  @author Manorama Pal(palseema30@gmail.com)
   -->
   
<html>
    <head>
        <title>Upload Staff List</title>
        <script type="text/javascript" src="<?php echo base_url();?>assets/js/1.12.4jquery.min.js" ></script>
        <!--<script type="text/javascript" src="<//?php echo base_url();?>assets/js/bootstrap.min.js" ></script>-->
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
        <div>
             <?php $this->load->view('template/header'); ?>
           
        </div> 

        <div>
<table width="100%;">
            <tr>
<?php
                    echo "<td align=\"center\" width=\"100%\" style=\"font-size:16px\">";
                    echo "<b>Upload Transfer Orders</b>";
                    echo "</td>";
		    echo "</tr>";
                    echo "</table>";
?>
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
        <!--<table  align="center" style="margin-left:5%;width:70%;" border="1">-->
        <table   style="font-size:13px;" >
            <tr><td align="left" ><b>Note : The file extension should be in csv. The format of Staff transfer order file is --</b>
                </td>
            </tr>
                <tr><td align="left"><br/><b>Registrar Name &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;Designation &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; USO No &nbsp; RC No &nbsp; Reference No &nbsp;&nbsp;</b>
                          <br/> Dr. A B SINGH &nbsp; &nbsp;Registrar Incharge &nbsp;&nbsp;&nbsp;34567 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BC8776 &nbsp;&nbsp; C898000 &nbsp;&nbsp;
                    <br/><br/>
                    <tr><td align="left"><b>Employee Transfer To Details:</b> </td></tr>    
                    <tr><td align="left"><b>Employee PF No. &nbsp;&nbsp;&nbsp;&nbsp &nbsp;&nbsp;Campus Code &nbsp;&nbsp; Authority(UCO)Code &nbsp;&nbsp; Department Code &nbsp;&nbsp;  Scheme Code &nbsp;&nbsp; DDO Code &nbsp;&nbsp; AGP Code &nbsp;&nbsp; Working Type &nbsp;&nbsp; Group &nbsp;&nbsp;Designation Code&nbsp;&nbsp;Shown against Post Code&nbsp;&nbsp; Employee Type </b> <br/>
                                V1234&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CU001&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MVC-AGB
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;1007&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D149
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;16 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Teaching
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;A&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Permanent<br/>
                    <b>Date of relieve &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date of Joining&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Subject
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Order Content&nbsp; &nbsp;&nbsp; &nbsp;TTA Detail &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email Sent To</b>
                    <br/>2018-05-30 00:00:00 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2018-06-30 00:00:00 &nbsp;&nbsp;&nbsp;TRANSFER
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;order content&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TTA Detail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;palseema30@gmail.com
                    </td></tr>
                                                                                   
                </td>
            </tr>
           
        </table>
        <br/><table>
            <?php echo form_open_multipart('upl/uploadtransferorder');?>
                <tr>
                    <td align="left">
                        <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                        <div><label for="file" style="font-size:15px;"><b>Select File</b></label>
                        <input type='file'  id="fileUpload" name='userfile' size='20' accept=".csv, text/csv" />
                        <input type='submit' name='importdata' id="btnUpload"  value='upload'/></div>
                    </td> 
                                 
                </tr>
             </form>
        </table>
        <div><?php $this->load->view('template/footer'); ?></div> 
    </body>    
</html>    
