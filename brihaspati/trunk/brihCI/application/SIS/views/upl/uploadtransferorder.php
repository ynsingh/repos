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
            <!--h1>Welcome <?= $this->session->userdata('username') ?>  </h1-->
            <?php $this->load->view('template/menu'); ?>
        </div> 
<table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
        <br/><br/><br/>
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
        <!--<table  align="center" style="margin-left:5%;width:70%;" border="1">-->
        <table  width="70%" style="font-size:13px;" >
            <tr><td align="left"><b>Note : The file extension should be in csv. The format of Staff transfer order file is --</b>
                </td>
            </tr>
                <tr><td align="left"><br/><b>Employee Name &nbsp; Registrar Name &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;Designation &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; USO No &nbsp; RC No &nbsp; Reference No &nbsp;&nbsp; Employee Type &nbsp;  Subject &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </b><br/>
                                   Manorama Pal &nbsp;&nbsp;&nbsp;&nbsp;  Dr. A B SINGH &nbsp; &nbsp; Registrar Incharge &nbsp;&nbsp&nbsp;&nbsp;34567 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; BC8776 &nbsp;&nbsp; C898000 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Teaching &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Transfer order &nbsp;&nbsp; 
                                   <br/><b>Order Content&nbsp; &nbsp; UO from &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; UO To </b><br/>
                                   order content&nbsp;&nbsp;&nbsp; Finance Officer &nbsp;Controller of examinations
                                   <br/><b>Deptartment From &nbsp;&nbsp;&nbsp;&nbsp; Deptartment To &nbsp;&nbsp;&nbsp;&nbsp; Designation From &nbsp;&nbsp; Designation To &nbsp; Post From  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Post To &nbsp;&nbsp;&nbsp;&nbsp; Date of relieve </b><br/>
                                   Animal Biotechnology&nbsp;  Animal Husbandry &nbsp;  AssistantProfessor &nbsp;&nbsp; Professor &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Assistant Professor &nbsp; Professor &nbsp;&nbsp; 2017-10-25 00:00:00
                                   <br/><b>Date of Joining &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; TTA Detail &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email Sent To</b><br/>
                                   2017-11-25 00:00:00&nbsp;&nbsp;&nbsp;transfer order Details &nbsp;&nbsp;&nbsp;&nbsp;palseema30@gmail.com
                </td>
            </tr>
        </table>
        <br/><table>
            <?php echo form_open_multipart('upl/uploadtransferorder');?>
                <tr>
                    <td align="left">
                        <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                        <div><label for="file" style="font-size:15px;"><b>Select File</b></label>
                        <input type='file'  id="fileUpload" name='userfile' size='20' accept=".csv, text/csv" /></div>
                        <div><input type='submit' name='importdata' id="btnUpload"  value='upload'/></div>
                    </td> 
                                 
                </tr>
             </form>
        </table>
        <div><?php $this->load->view('template/footer'); ?></div> 
    </body>    
</html>    
