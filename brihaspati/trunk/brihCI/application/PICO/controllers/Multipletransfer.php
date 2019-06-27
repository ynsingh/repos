
<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Multipletransfer.php
 * @author Manorama Pal (palseema30@gmail.com)  Staff transfer and posting
 */

class Multipletransfer extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
        $this->load->model('Common_model',"commodel");
        $this->load->model('Login_model',"lgnmodel"); 
        $this->load->model('SIS_model',"sismodel");
        $this->load->model("Mailsend_model","mailmodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 
    public function index(){
        
    //$this->load->view('staffmgmt/staffprofile');    
    }
    public function multitransfer(){
        
        $array_items = array('success' => '', 'error' => '', 'warning' =>'');
       	$this->session->set_flashdata($array_items);
	$error =array();
        $transtype='multipletransfer';
        $datatype['ttype']=$transtype;
        if(isset($_POST['importdata']))
        {
            $ferror='';
            $file_name = $_FILES['userfile']['name'];
            $file_ext=strtolower(end((explode('.',$file_name))));

            $expensions= array("txt","csv");

            if(in_array($file_ext,$expensions)=== false){
                $ferror="extension not allowed, please choose a txt or csv file.";
                $this->session->set_flashdata('err_message', $ferror);
                $this->load->view('staffmgmt/stafftransfer',$datatype);
               // echo "seema".$transtype;
                return;
            }
            else{
                $selectuitid=array();
                $ferror='';
                $filename=$_FILES['userfile']['tmp_name'];
                $filesize=$_FILES['userfile']['size'];
                $flag=true;
                $file = fopen($filename, "r");
                fgetcsv($file);
                $i=1;
               
                while (false !== ($line = fgets($file)))
                {
                    $getData = explode(",", $line);
                    $flag=false;
                    if(count($getData) >= 23){
                        $empid=$this->sismodel->get_listspfic1('employee_master', 'emp_id', 'emp_code', $getData[5])->emp_id;
                        $scid=$this->sismodel->get_listspfic1('employee_master', 'emp_scid', 'emp_id',  $empid)->emp_scid;
                        $uoid=$this->sismodel->get_listspfic1('employee_master', 'emp_uocid', 'emp_id',  $empid)->emp_uocid;
                        $deptid=$this->sismodel->get_listspfic1('employee_master', 'emp_dept_code', 'emp_id',  $empid)->emp_dept_code;
                        $schmid=$this->sismodel->get_listspfic1('employee_master', 'emp_schemeid', 'emp_id',  $empid)->emp_schemeid;
                        $wtype=$this->sismodel->get_listspfic1('employee_master', 'emp_worktype', 'emp_id',  $empid)->emp_worktype;
                        $desigid=$this->sismodel->get_listspfic1('employee_master', 'emp_desig_code', 'emp_id',  $empid)->emp_desig_code;
                        $sap=$this->sismodel->get_listspfic1('employee_master', 'emp_post', 'emp_id', $empid)->emp_post;
                                              
                        $regnameto=trim($getData[0]);
                        $regdisgto=trim($getData[1]);
                        $usono=trim($getData[2]);
                        $rcno=trim($getData[3]);
                        $refno=trim($getData[4]);
                        $scidto=$this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_code', trim($getData[6]))->sc_id;
                        $uocto=$this->lgnmodel->get_listspfic1('authorities', 'id', 'code',trim($getData[7]))->id;
                        $deptto=$this->commodel->get_listspfic1('Department', 'dept_id', 'dept_code',trim($getData[8]))->dept_id;
                        $schto=$this->sismodel->get_listspfic1('scheme_department', 'sd_id', 'sd_code',trim($getData[9]))->sd_id;
                        $ddoto=$this->sismodel->get_listspfic1('ddo', 'ddo_id', 'ddo_code',trim($getData[10]))->ddo_id;
                        $agpto=trim($getData[11]);
                        $wtyto=trim($getData[12]);
                        $groupto=trim($getData[13]);
                        $desigto=$this->commodel->get_listspfic1('designation', 'desig_id', 'desig_code',trim($getData[14]))->desig_id;
                        $sapto=$this->commodel->get_listspfic1('designation', 'desig_id', 'desig_code',trim($getData[15]))->desig_id;
                        $emptyto=trim($getData[16]);
                        $dorel=trim($getData[17]);
                        $doj=trim($getData[18]);
                        $sub=trim($getData[19]);
                        $orcontent=trim($getData[20]);
                        $tta=trim($getData[21]);
                        $emailto=trim($getData[22]);
                        
                        $datuit = array(
                            //'uit_staffname'         => $getData[0], //get id
                           
                            'uit_registrarname'     => $regnameto,
                            'uit_desig'             => $regdisgto,
                            'uit_uso_no'            => $usono,
                            'uit_date'              => date('y-m-d'), 
                            'uit_rc_no'             => $rcno,
                            'uit_subject'           => $sub, 
                            'uit_referenceno'       => $refno,
                           
                            'uit_ordercontent'      => $orcontent,
                            'uit_emptype'           => $wtype,
                            'uit_emptypeto'         => $wtyto,
                            'uit_uoc_from'          => $uoid,
                            'uit_workdept_from'     => $deptid,
                            
                            'uit_desig_from'        => $desigid,
                            'uit_staffname'         => $empid, 
                            'uit_workingpost_from'  => $sap,
                            'uit_scid_from'         => $scid,
                            'uit_scid_to'           => $scidto,
                            
                            'uit_uoc_to'            => $uocto, //getid
                            'uit_dept_to'           => $deptto, //getid
                            /********************************************/
                            'uit_desig_to'          => $desigto, ///getid
                            'uit_post_to'           => $sapto,
                                                       
                            'uit_schm_from'         => $schmid,
                            'uit_schm_to'           => $schto, 
                            'uit_ddoid_to'          => $ddoto,
                            'uit_group_to'          => $groupto,
                            'uit_paybandid_to'      => $agpto,
                            'uit_vacanttype_to'     => $wtyto,
                            
                            'uit_tta_detail'        => $tta,
                            'uit_dateofrelief'      => $dorel,
                            'uit_dateofjoining'     => $doj,
                            'uit_email_sentto '     => $emailto,
                            'uit_transfertype'      =>$transtype,
                            
                            
                        );
                        $usrinputtfr_flag=$this->sismodel->insertdata('user_input_transfer', $datuit);
                        array_push($selectuitid, $usrinputtfr_flag);
                        if($usrinputtfr_flag){
                            /****update in employee master**********/
                            $post=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$sapto)->desig_name;
                            $empdata = array(
                                'emp_dept_code'    => $deptto,
                                'emp_desig_code'   => $desigto,
                                'emp_post'         => $post,
                                'emp_worktype'     => $wtyto,
                                'emp_salary_grade' => $agpto,
                                'emp_schemeid'     => $schto,
                                'emp_scid'         => $scidto ,
                                'emp_uocid'        => $uocto,
                                'emp_uocuserid'    => $uocto,
                                'emp_ddouserid'    => $ddoto,
                                'emp_ddoid'        => $ddoto,
                                'emp_group'        => $groupto,
                    
                            );
                            $upempdata_flag=$this->sismodel->updaterec('employee_master', $empdata,'emp_id',$empid);
                            
                            /******* insert in service detail ************/ 
                            $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$desigto)->desig_code;
                            // $shownap=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['emppost'])->desig_id;
                            $this->sismodel->insertsdetail($empid,$scidto,$uocto,$deptto,$desigcode,$schto,$ddoto,$groupto,$agpto,'',$sapto,
                            date('y-m-d'),'','',$usono);
                            
                            /**************update staff position record also *****************/
                            $postfrom=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$sap)->desig_id;
                            $empptfrom=$this->sismodel->get_listspfic1('employee_master', 'emp_type_code', 'emp_id',  $empid)->emp_type_code;
                            //descrease position and increase vacancy from old data(means from )
                            $this->sismodel->updatestaffposition2($scid,$uoid,$deptid,$postfrom,$wtype,$empptfrom, $schmid);
                            //increase in position and decrease vacancy from new data(means to)
                            $this->sismodel->updatestaffposition($scidto,$uocto, $deptto,$sapto,$wtyto,$emptyto);
                                                        
                            /****************************************************************/
                            
                            $emppfno=$getData[5];
                            $empname=$this->sismodel->get_listspfic1('employee_master', 'emp_name', 'emp_id', $empid)->emp_name;
                            $deptname=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$deptto)->dept_name; 
                            $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_id',1)->org_name;
                            $sub='Employee Transfer And Posting - Letter  ' ;
                            $mess='OFFICE ORDER<br/> Dear'.$empname.'This is to inform you that you will be transferred at'.$deptname.'with immediate effect.<br/>
                            Please find the attachment of transfer order copy<br/> Wish you all the best<br/>'.$this->orgname.'<br/>
                            '.$getData[0].'<br/>'.$getData[1];
                           // $this->load->library('../controllers/staffmgmt');
                           // $attachment=$this->sismodel->gentransferordertpdf($empid);
                           // $this->mailstoperson=$this->mailmodel->mailsnd($emailto, $sub, $mess,$attachment,'');
                            $this->mailstoperson='';
                            if($this->mailstoperson){
                                //echo "in if part mail";
                                $error[] ="At row".$i."sufficient data and mail sent sucessfully";
                                $mailmsg='Transfer and Promotion order ....Mail send successfully';
                                $this->logger->write_logmessage("insert"," Transfer and Promotion order ",'mail send successfully  to '.$emailto);
                                $this->logger->write_dblogmessage("insert"," Transfer and Promotion order",'mail send successfully  to '.$emailto);
                            }
                            else{
                                //echo "in else part";
                                $mailmsg='Mail does not sent';
                                $this->logger->write_logmessage("insert"," Transfer and Promotion order", "Mail does not sent to ".$emailto);
                                $this->logger->write_dblogmessage("insert"," Transfer and Promotion order", "Mail does not sent to ".$emailto);
                            }//else close   
                            
                            $this->logger->write_logmessage("insert","Staff Transfer and Posting", " Employee transfer record insert successfully ");
                            $this->logger->write_dblogmessage("insert","Staff Transfer and Posting", "Employee transfer record insert successfully");
                           // $this->session->set_flashdata('success', 'Employee transfer record insert successfully ......');
                        }//ifclose $usrinputtfr_flag
                        else{
                            $this->logger->write_logmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting ".$empname );
                            $this->logger->write_dblogmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting".$empname);
                        }
                       $i++; 
                    }//if count
                    else{
                        //	insufficient data
                            $error[] ="At row".$i."insufficient data";
                            $this->logger->write_logmessage("insert"," Error in adding user input transfer in payroll ", "At row".$i."insufficient data"  );
                            $this->logger->write_dblogmessage("insert"," Error in adding user input transfer in payroll ", "At row".$i."insufficient data" );
                           // $this->session->set_flashdata('error', "At row".$i."insufficient data");
			    $i++;
                    }
                                      
                }//while close
                if($flag){
                    $this->session->set_flashdata('err_message', ' File without data');
                    $this->load->view('staffmgmt/stafftransfer',$datatype);
                    return;
                }else{
                    $attachment=$this->sismodel->multipleorder($selectuitid);
                    foreach ($error as $item => $value):
                        $ferror = $ferror ."</br>". $item .":". $value;
                    endforeach;
                    //display error of array
                    //put ferror in log file.
                    //print_r($selectuitid);die;
                    
                    $this->session->set_flashdata('success', $ferror);
                    redirect('staffmgmt/stafftransfer/multipletransfer');
                  
                }
                
		fclose($file);	
            } // else csv extension check            
        }//$post close    
        $this->load->view('staffmgmt/stafftransfer');
    }
}    