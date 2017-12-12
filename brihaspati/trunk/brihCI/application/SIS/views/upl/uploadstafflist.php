<!DOCTYPE html>
<!--@name liststaff.php 
  @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
  @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
  @author Malvika Upadhyay (malvikaupadhyay644@gmail.com)
  @author Om Prakash (omprakashkgp@gmail.com)  upload csv file for staff profile registration in SIS  
  @author Manorama Pal (palseema30@gmail.com) 06 Dec 2017 modification in upload staff profile csv file
 -->
<html>
    <head>
        <title>Upload Staff List</title>
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
        <?php $this->load->view('template/menu'); ?>     
        <table id="uname"><tr><td align=center>Welcome <?= $this->session->userdata('username') ?>  </td></tr></table>
                  
        <div>
        <table width="100%;">
            <tr>
                <?php
                    echo "<td align=\"left\" width=\"33%\">";
                    echo "</td>";
                    echo "<td align=\"center\" width=\"34%\" style=\"font-size:16px\">";
                    echo "<b>Upload Staff List</b>";
                    echo "</td>";
                    echo "<td align=\"right\" width=\"33%\" style=\"font-size:16px\">";
                    $help_uri = site_url()."/help/helpdoc#UploadStaffList";
                    echo "<a style=\"text-decoration:none\"target=\"_blank\" href=$help_uri><b>Click for Help</b></a>";
                    echo "</td>";
                 
                ;?>
            </tr>
        </table>
        <div>  
            <?php
                // display user message area
                /*if(isset($_SESSION)) {
                    echo $this->session->flashdata('flash_data');
                }*/
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
            <!-- OP: instruction for upload csv file for staff profile  ragistration -->
            <tr><td align="left"><b>Note : The file extension should be in csv. The format of Staff list file is </b>
                </td>
            </tr>
            <tr><td align="left"><br/><b>Employee PF NO &nbsp; Employee Name &nbsp; Campus Code &nbsp;&nbsp; Authority(UCO)Code &nbsp;&nbsp; Department Code &nbsp; Scheme Code &nbsp;&nbsp;&nbsp;&nbsp;DDO Code&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Working Type 
                        &nbsp;&nbsp;&nbsp;Group&nbsp;&nbsp;&nbsp;Designation Code &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Shown against Post</b><br/>
                        V1418&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;VIJAYARANI,K&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CU002
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DEAN-FBS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MVC-ABT
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;12042&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D115&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Teaching
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;14&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;14<br/>
                        <b>Employee Type&nbsp;&nbsp;&nbsp;AGP Code &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date of Birth &nbsp;&nbsp;&nbsp;&nbsp Date of Appointment &nbsp;&nbsp;&nbsp;&nbsp;  Email Id
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bank Acc No &nbsp; Aadhar No</b><br/>
                        Temporary&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;32&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1965-05-24&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1991-07-22
                        &nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp; sms429329@gmail.com  &nbsp;&nbsp;&nbsp;&nbsp;22222222222 &nbsp;&nbsp;&nbsp;&nbsp;333333333333
                        <!--20041201 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| Gautam b &nbsp;&nbsp;&nbsp;&nbsp;| Atmospheric Sc &nbsp;| Dev &nbsp;&nbsp;&nbsp;| 1 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| Electrical &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| UPS-Embryo &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| Project Associate &nbsp;| PB1 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 2017/08/14 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 1975/01/10 &nbsp;&nbsp;&nbsp;&nbsp;| 20040183598 &nbsp;| 632004183598 &nbsp;| op@gmail.com ";--->
                </td>
            </tr>
            <?php echo form_open_multipart('upl/uploadslist');?>
             <tr>
                    <td align="left">
                        <span id="lblError" style="color: red;font-size:15px;"></span><br/>
                        <div><label for="file" style="font-size:15px;"><b>Select File</b></label>
                        <input type='file'  id="fileUpload" name='userfile' size='20' accept=".csv, text/csv" />
                        <input type='submit' name="uploadslist" id="btnUpload"  value='upload'/></div>
                    </td> 
                                 
                </tr>
            
            <?php echo form_close();?>
        </table>    
        <p> &nbsp; </p>
        <?php $this->load->view('template/footer'); ?>

    </body>
</html>

               

