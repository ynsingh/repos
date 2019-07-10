<?php
    
/* 
 * @filename Pdfgen.php
 * @author Manorama Pal (palseema30@gmail.com)// Pdf Report for Profile Completeness List, Retired Employee List Disicipline wise.
 * (Department wise,Designation wise,Staff vacancy post wise, Staff  Vacancy uo wise,Staff position with Name UO wise,
 *  Staff position with Name and Post wise, Staff senrority List, Position-summary Professorlist,Hod List, UO List.) 
 * @author Akash Rathi(akash92y@gmail.com)  
 */

    class Pdfgen extends CI_Controller
    {

        function __construct() {
            parent::__construct();
            $this->load->model('Common_model',"commodel");
            $this->load->model('Login_model',"lgnmodel"); 
            $this->load->model('SIS_model',"sismodel");
            $this->load->helper('download');
              
            if(empty($this->session->userdata('id_user'))) {
                $this->session->set_flashdata('flash_data', 'You don\'t have access!');
                redirect('welcome');
            }
        }
    

        function ssl()
        {
            $selectfield ="sp_uo, sp_dept,sp_emppost, sp_schemecode,sp_sancstrenght , sp_position , sp_vacant,sp_type";
            $whorder = "sp_uo asc, sp_dept  asc, sp_schemecode  asc";
            $whdata= '';
            //$whdata = $this->getwhdata();

            $wid=$this->uri->segment(3,0);
            $uolt1=$this->uri->segment(4,0);
            $dept=  $this->uri->segment(5,0);

            if(($wid !=0) || ($uolt1 !=0)|| ($dept !=0)){
                $whdata['sp_tnt'] = $wid;
                if(($dept != "null") && ($dept != "All")){
                    $whdata['sp_dept']= $dept;
                }
                if(($uolt1 != "null") && ($uolt1 != "All")){	
                    $whdata['sp_uo'] = $uolt1;
                }
                $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield,$whdata,$whorder);
       
                //$this->load->view('report/temp1',$data);
            }
            else{
                $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield,$whdata,$whorder);    
            }
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->load_view('report/tempstaffstrengthlist',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }

        function svp()
        {
            $selectfield ="sp_uo, sp_dept,sp_schemecode, sp_emppost,sp_sancstrenght , sp_position , sp_vacant, sp_remarks";
            $whorder = "sp_emppost asc, sp_uo asc, sp_dept asc";
            $whdata = '';
        
            $wid=$this->uri->segment(3,0);
            $uolt1=$this->uri->segment(4,0);
            $desig=  $this->uri->segment(5,0);

            if(($wid !=0) || ($uolt1 !=0)|| ($desig !=0)){
                $whdata['sp_tnt'] = $wid;
                if($desig != "null" && $desig != "All"){
                    $whdata['sp_emppost']= $desig;
                }
                if(($uolt1 != "null") && ($uolt1 != "All")){
                    $whdata['sp_uo'] = $uolt1;
                }
                $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield,$whdata,$whorder);
            }
            else{
                $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield,$whdata,$whorder);
            }
    
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->load_view('report/tempstaffvacancy',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }

        function pv()
        {
            $selectfield ="sp_emppost";
            $whdata ='';

            $wid=$this->uri->segment(3,0);
            $desig=  $this->uri->segment(4,0);

            if($wid !=0 || $desig!=0){
                $whdata['sp_tnt']=$wid;
                if(!empty($post) && ($post!="All")){
                    $whdata['sp_emppost']=$desig;
                }
                $data['allpost']=$this->sismodel->get_distinctrecord('staff_position',$selectfield, $whdata);
            }
            else{
                $data['allpost']=$this->sismodel->get_distinctrecord('staff_position',$selectfield,$whdata);
            }
                
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->set_option('enable_html5_parser', true);
            $this->pdf->load_view('report/temppositionvancy',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }


        function lsf()
        {
            $selectfield ="sp_uo,sp_dept,sp_schemecode,sp_emppost,sp_sancstrenght,sp_position,sp_vacant";
            $whorder = "sp_uo asc, sp_dept asc, sp_schemecode  asc,sp_emppost asc";
            //	$whorder = "sp_uo asc, sp_dept asc";
            $whdata = '';
            $data['tnttype']='';
            $data['seldept']='';
            
            $wid=$this->uri->segment(3,0);
            $uolt1=$this->uri->segment(4,0);
            $dept=$this->uri->segment(5,0);
            
            if(($wid !=0) || ($uolt1 !=0)|| ($dept !=0)){
                $data['tnttype'] =$wid;
                
                $whdata['sp_tnt'] = $wid;
                if(($dept != "null") && ($dept != "All")){
                    $whdata['sp_dept']= $dept;
                }
                if(($uolt1 != "null") && ($uolt1 != "All")){
                    $whdata['sp_uo'] = $uolt1;
                }
                $data['records'] = $this->sismodel->get_distinctrecord('staff_position',$selectfield, $whdata);
            }
            else{
                $data['records'] = $this->sismodel->get_distinctrecord('staff_position',$selectfield, $whdata);
            }
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->set_option('enable_html5_parser', true);
            $this->pdf->load_view('report/templistofstaffposition',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }


        function del()
        {
            $selectfield ="emp_id,emp_code,emp_uocid, emp_dept_code,emp_name,emp_head, emp_post,emp_desig_code,emp_schemeid";
            $whorder = "emp_uocid asc, emp_dept_code  asc, emp_desig_code asc, emp_post asc";
            $cdate = date('Y-m-d');
            
            $whdata = $this->getprofilefilerdata();
            $whdata['emp_leaving'] = NULL;
            $whdata['emp_dor>=']=$cdate;
        
            echo $wid=$this->uri->segment(3,0);
            echo $uolt1=$this->uri->segment(4,0);
            echo $dept[]=  $this->uri->segment(5,0);
            
            if(($wid !=0) || ($uolt1 !=0)|| ($dept !=0)){

                $whdata['emp_worktype']=$wid;
                if($uolt1 !="All"){
                    $whdata['emp_uocid']=$uolt1;
                }
            
                $i=0;
                if((!empty($dept))&&($dept != "null")){
                    foreach($dept as $row){
                        $this->deptmt = $row[$i];
                        $names = $row;
                        $i++;
                    }
                }
	
                if(!empty($names)){
                    $data['records']= $this->sismodel->get_orderlistspficemoreorwh('employee_master',$selectfield,$whdata,'emp_dept_code',$names,$whorder);
	
                }
                else{
                    $data['records']= $this->sismodel->get_orderlistspficemoreorwh('employee_master',$selectfield,$whdata,'','',$whorder);
		}
                
            }
            else{
              // echo "in else loop";
              $data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
            }
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->load_view('report/tempdeptemplist',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }


        function dwel()
        {
            $this->sc=$this->commodel->get_orderlistspficemore('study_center','sc_id,sc_name,sc_code','','sc_name asc');
            $this->sub=$this->commodel->get_orderlistspficemore('subject','sub_id,sub_name,sub_code','','sub_name asc');

            $cdate = date('Y-m-d');
            $selectfield ="emp_dept_code, emp_id,emp_code,emp_name,emp_head, emp_desig_code,emp_specialisationid";
            $whorder = "emp_specialisationid asc, emp_desig_code asc ";
            
            $whdata = $this->getprofilefilerdata();
            $whdata['emp_leaving'] = NULL;
            $whdata['emp_dor>=']=$cdate;;
            $whdata['emp_worktype'] = 'Teaching';
            
            //$this->camp ='';
            //$this->subj='';
            $camp=$this->uri->segment(3,0);
            $subj[]=$this->uri->segment(4,0); 
            //echo"===".$camp;
           // die();
            if($camp !=0 || $subj !=0){
                if(!empty($camp))
                $whdata['emp_scid']=$camp;
                $i=0;
		if(!empty($subj)){
                    foreach($subj as $row){
			$this->subj = $row[$i];	
			$names = $row;
			$i++;
                    }
		}
		if(empty($names))
                $whdata['emp_specialisationid >'] = 0;
                $this->result = $this->sismodel->get_orderlistspficemoreorwh('employee_master',$selectfield,$whdata,'emp_specialisationid',$names,$whorder);
               // print_r("seema".$this->result);
                //die();
   
            }
            else{
                $whdata['emp_specialisationid >'] = 0;
        	$this->result = $this->sismodel->get_orderlistspficemoreorwh('employee_master',$selectfield,$whdata,'','',$whorder);
            
                // print_r("seema".$this->result);
                // die();
            }
        
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->set_option('enable_html5_parser', true);
            $this->pdf->load_view('report/tempdisciplinewiselist',$this->result);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }

        function proflist()
        {
            $cdate = date('Y-m-d');
            $selectfield ="emp_id,emp_code,emp_name,emp_dor,emp_specialisationid,emp_dept_code,emp_doj";        
            $whorder = "emp_doj asc";
            $desig=null;
            $whdata=array('emp_leaving' => NULL,'emp_dor>='=>$cdate);
           
            $wid=$this->uri->segment(3,0);
            $desig=$this->uri->segment(4,0);
            if($wid !=0 || $desig !=0)
            {    
                $whdata['emp_worktype'] = $wid;
                if(!empty($desig))
                    $whdata['emp_desig_code'] = $desig;
		$data['emplist'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
                $this->desig=$desig;        
            }
            else{
                $this->desig='ALL'; 
                $data['emplist'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
                
            }
            
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->set_option('enable_html5_parser', true);
            $this->pdf->load_view('report/tempproflist',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }


        function rpc(){
            $selectfield ="emp_id,emp_code,emp_uocid, emp_dept_code,emp_name,emp_head, emp_post,emp_desig_code";
            $whorder = "emp_uocid asc, emp_dept_code  asc, emp_desig_code asc, emp_post asc";
            $cdate = date('Y-m-d');
            // add doris geater than current date and reason is null  in whdata
            $whdata = $this->getprofilefilerdata();
            $whdata['emp_leaving'] = NULL;
            $whdata['emp_dor>=']=$cdate;
               
            $wtyp=$this->uri->segment(3,0);
            $uoff=$this->uri->segment(4,0);        
            $dept= $this->uri->segment(5,0);
                //echo "wtyp==".$wtyp.$uoff. $dept;
                //die();
               
            if($wtyp !=0 || $uoff !=0 || $dept!=0){
                    
                if((!empty($dept))&&($dept != "null")){
                    $data['deptmt'] = $dept;
                }else{
                    $data['deptmt']= "All";
                }

                if(!empty($wtype)){
                    $whdata['emp_worktype']=$wtype;
                    $data['wtyp'] = $wtype;
                }
                if(($dept != "null") && ($dept != "All") && (!empty($dept))){
                    $whdata['emp_dept_code']= $dept;
                }
                if(($uoff != "null") && ($uoff != "All")&& (!empty($uoff))){
                    $whdata['emp_uocid'] = $uoff;
                    $data['uolt'] = $uoff;
                }
                $data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
            }
            else{
                $data['records'] ='';
            }
              
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->set_option('enable_html5_parser', true);
            $this->pdf->load_view('report/tempreportprofile',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }


        function hodlist()
        {
            $data['uoc']=$this->lgnmodel->get_orderlistspficemore('authorities','priority,name,code','','name ASC');
            $today= date("Y-m-d H:i:s"); 
            $whdata=array('hl_status'=>'Fulltime','hl_dateto'=> '0000-00-00 00:00:00');
        
            $selectfield ="hl_userid,hl_empcode,hl_deptid,hl_scid,hl_uopid";
            $whorder = "hl_uopid asc";
                //$data['allsc']=$this->sismodel->get_orderlistspficemore('hod_list',$selectfield,$whdata,$whorder);
    
            $uoff=$this->uri->segment(3); 
            
            if(!empty($uoff)){
                $whdata['hl_uopid']=$uoff;
                $data['uolt'] = $uoff;
		    
            }    
            $data['allsc']=$this->sismodel->get_orderlistspficemore('hod_list',$selectfield,$whdata,$whorder);                           
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->set_option('enable_html5_parser', true);
            $this->pdf->load_view('report/temphodlist',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }

        function uolist()
        {
            $today= date("Y-m-d H:i:s");
            $selectfield ="ul_authuoid,ul_userid,ul_empcode, ul_uocode,ul_uoname,ul_id,  ul_modifydate";
            $whorder="ul_id asc,ul_authuoid ASC,  ul_modifydate DESC";
                //$whdata=array('ul_status'=>'Fulltime','ul_dateto'=> '0000-00-00 00:00:00');
            $whdata=array('ul_status'=>'Fulltime','ul_dateto >='=>$today);
            $data['allsc']=$this->sismodel->get_orderdistinctrecord('uo_list',$selectfield,$whdata,$whorder);
        
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->set_option('enable_html5_parser', true);
            $this->pdf->load_view('report/tempuolist',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }


        function rel(){
            $selectfield ="emp_id,emp_code,emp_uocid, emp_dept_code,emp_name,emp_head, emp_post,emp_desig_code,emp_schemeid,emp_email,emp_doj,emp_dor,emp_dob";
            $whorder = "emp_dor desc,emp_uocid asc, emp_dept_code  asc, emp_desig_code asc, emp_post asc";
            $cdate = date('Y-m-d');
            //$whdata = array ('emp_leaving ' =>NULL ,'emp_dor>=' =>$cdate); 
            $whdata = array ('emp_leaving !=' =>NULL); 
                    
            $wtyp=$this->uri->segment(3,0);
            $uoff=$this->uri->segment(4,0);        
            $dept[]= $this->uri->segment(5,0);
            $year=$this->uri->segment(6,0);
            $month=$this->uri->segment(7,0);
                
            if($wtyp !=0 || $uoff !=0 || $dept !=0 || $year !=0 || $month !=0){
                    
                $whdata['emp_worktype']=$wtyp;
                if($uoff !="All"){
                   $whdata['emp_uocid']=$uoff;
                }
            
                $i=0;
                if((!empty($dept))&&($dept != "null")){
                    foreach($dept as $row){
                        $this->deptmt = $row[$i];
                        $names = $row;
                        $i++;
                  //  print_r(array_values($names));
                    }
                }
            
                if(!empty($year)&&($year != "null")){
                    if(!empty($month)&&($month != "null")){
                        $whdata['SUBSTRING(emp_dor,1,7)  LIKE']=$year.'-'.$month.'%';
                    }
                    else{       
                        $whdata['SUBSTRING(emp_dor,1,4)  LIKE']=$year.'%';
                    }   
            
                }
            
                if(!empty($names)){
                    $data['records']= $this->sismodel->get_orderlistspficemoreorwh('employee_master',$selectfield,$whdata,'emp_dept_code',$names,$whorder);
                }else{
                    $data['records']= $this->sismodel->get_orderlistspficemoreorwh('employee_master',$selectfield,$whdata,'','',$whorder);
                }
            }
            else{
                //$names = array('Dismissed','Expired','Resigned','VRS');	
                $names = array('superannuation');	
                $whdata = array ('emp_dor <='=>$cdate, 'emp_leaving IS NULL'=> null);
                $data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
					
            }
            $this->load->library('pdf');
            $this->pdf->set_paper("A4", "portrait");
            $this->pdf->set_option('enable_html5_parser', true);
            $this->pdf->load_view('report/tempretired',$data);
            $this->pdf->render();
            $this->pdf->stream("report.pdf");
        }
        public 	function getprofilefilerdata(){
            $whdata ='';
            $rlid=$this->session->userdata('id_role');
            if ($rlid == 5){
                $usrid=$this->session->userdata('id_user');
                $deptid = '';
                $whdatad = array('userid' => $usrid,'roleid' => $rlid);
                $resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
                foreach($resu as $rw){
                    $deptid=$rw->deptid;
                }
                $whdata=array('emp_dept_code'=> $deptid);
            }
            if ($rlid == 10){
                $usrname=$this->session->userdata('username');
                if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')||($usrname === 'admin')||($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
                }else{
                    $uoid=$this->lgnmodel->get_listspfic1('authorities','id','authority_email',$usrname)->id;
                    $whdata=array('emp_uocid' => $uoid);
                }
            }
            return $whdata;
	}
    }
?>
