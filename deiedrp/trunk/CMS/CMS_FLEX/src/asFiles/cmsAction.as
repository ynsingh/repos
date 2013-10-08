/**
 * @(#) cmsAction.as
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
 
 [Embed(source="/images/infoIcon.png")]private var infoIcon:Class;
 
			import AddDropCourses.AddDropCourse;
			
			import MouCourse.MouCourse;
			
			import ProgramCourse.mxmlFile.ManageProgramCourseSetup;
			import ProgramCourse.mxmlFile.ProgramCourseSetup;
			
			import ProgramRegistration.BuildProgramRegistration;
			import ProgramRegistration.ManageProgramRegistration;
			import ProgramRegistration.ProgramRegistration;
			
			import RegistrationForInsAndAdmin.RegistrationForInsAndAdmin;
			
			import activityMaster.ActivityMaster;
			import activityMaster.ManageActivityMaster;
			
			import activityMasterControl.ActivityMasterControl;
			
			import admissionIntegration.admissionIntegration;
			
			import advanceParentProgram.AdvanceParentProgram;
			
			import associateInstructorCourse.BuildInstructorCourse;
			import associateInstructorCourse.addInstructorCourse;
			import associateInstructorCourse.manageInstructorCourse;
			
			import awardBlankForCollation.awardBlankForCollation;
			
			import awardBlankSheet.CreateAwardBlank;
			
			import buildActivityMaster.BuildActivityMaster;
			import buildActivityMaster.BuildActivityViaProgram;
			
			import buildNextSession.BuildNextSession;
			
			import buildinstructorcourse.InstructorCourseBuild;
			
			import buildprogramregistration.ProgramRegistrationBuild;
			
			import buildsemesterendprocess.ClearTemporaryTables;
			import buildsemesterendprocess.ReadyForRegistration;
			import buildsemesterendprocess.ReadyForSemesterEnd;
			
			import cancelCourseGroup.CancelCourseGroup;
			
			import cancelFinalRegistration.*;
			
			import cancelRegistration.CancelRegistration;
			
			import cgpaDivision.cgpaDivision;
			
			import clearStagingTables.ClearStagingTables;
			
			import common.InitializeUniversity;
			import common.ResetPassword;
			
			import correctionInAwardBlank.AwardBlankCorrection;
			
			import correctionInRegistration.correctionInRegistration;
			
			import courseEvaluationComponnent.courseEvaluationCompnent;
			
			import courseGroup.courseGroup;
			
			import courseMarksApproval.CourseMarksApproval;
			import courseMarksApproval.ManageCourseMarksApproval;
			
			import courseMaster.CourseMaster;
			import courseMaster.ManageCourseMaster;
			
			import coursegradelimit.*;
			
			import delayInComponentMarks.DelayInComponentMarks;
			
			import employeeMaster.DeleteEmployeeRole;
			import employeeMaster.ManageEmployeeMaster;
			import employeeMaster.employeeAuthoritySetup;
			import employeeMaster.employeeMaster;
			import employeeMaster.employeeRole;
			import employeeMaster.manageEmployeeAuthority;
			
			import entityMaster.EntityMaster;
			import entityMaster.ManageEntityMaster;
			
			import eodControl.EodControl;
			
			import eodMasterBuild.BuildEodMaster;
			
			import evaluationComponent.evaluationComponent;
			
			import instructorCourse.InstructorCourse;
            import externalExaminarCourse.externalExaminarCourse;
			import externalExaminarCourse.externalExaminarDetail;
			import externalExaminarCourse.manageExternalExaminarDetail;
			
			import instituteAdminApproval.ApprovalforInstAdmin;
			
			import logoUpload.LogoUpload;
			
			import mailConfiguration.MailConfiguration;
			
			import manualProcess.ManualProcess;
			import manualProcess.ResultFileNameUpload;
			
			import mx.collections.*;
			import mx.controls.Alert;
			import mx.events.MenuEvent;
			
			import nameCorrection.NameCorrection;
			
			import prestagingDataUpload.PrestagingDataSheetUpload;
			import prestagingDataUpload.PrestagingDataUploadForAdmin;
			
			import prestagingValidation.PrestagingActivity;
			
			import process.SendEmail;
			
			import programCourseType.ProgramCourseType;
			
			import programGroup.GroupRule;
			import programGroup.ProgramGroup;
			
			import programMaster.ManageBasicProgramDetails;
			import programMaster.ManageProgramBranchSpecialization;
			import programMaster.ManageProgramDuration;
			import programMaster.ProgramMaster;
			
			import programOfferedBy.ManageEntityProgram;
			import programOfferedBy.ProgramOfferedBy;
			
			import programSwitch.CreateProgramSwitch;
			import programSwitch.ManageProgramSwitch;
			
			import programTermDetails.ManageProgramTermDetails;
			import programTermDetails.ProgramTermDetails;
			
			import remark.Remark;
			import reportgeneration.*;
			
			import resetSystemValues.resetSystemValues;
			
			import rollEnrollFormat.rollEnrollFormat;
			
			import rspBackUtility.RevertResultProcess;
			
			import studentEnrollment.EnrollmentPhotoUpload;
			import studentEnrollment.StudentEnrollment;
			
			import studentInformation.studentInfoMain;
			
			import studentIssue.studentIssue;
			
			import studentMaster.StudentTrackingControl;
			import studentMaster.studentMaster;
			
			import studentRegistration1.LoginForm1;
			
			import studentRemedial.studentRemedials;
			
			import studentVerificationCheckList.StudentCheckList;
			
			import studentprogramswitch.programswitchmain;
			
			import switchrule.switchRule;
			
			import systemTableTwo.SystemTableTwo;
			
			import transcript.Transcript;
			
			import universityReservation.UniversitySeatReservation;
			
			import universityWithMouUniversity.universityWithMou;
			
			import universitymaster.CreateDefaultUser;
			import universitymaster.ManageUniversityMaster;
			import universitymaster.UniversityMaster;
			import universitymaster.createUniversityRoles;
			import universitymaster.manageUniversityRoles;
			
			import updatePrestagingTable.UpdatePrestagingTable;
			
			import userDataInterface.UserDataInterface;
			
			import withdrawMarksTransfer.WithdrawMarksTransfer;
			import insertStudentToPrestaging.insertToPrestaging;
			import withdrawstudent.WithdrawStudent;
			import programCourseHeaderCredits.programCourseHeaderCredit;
			
			protected var menuBarCollection:ArrayCollection=new ArrayCollection();
			
			protected var usersXML:XML;
			[Bindable]protected var menuData:XMLListCollection;
			
			protected function menuHttpServiceResultHandler(event:ResultEvent):void{
			    Mask.close();
				var menuString:String=event.result as XML;
				if(menuString.localeCompare("errorMenu")==1){
					Alert.show(commonFunction.getMessages('menuError')+" ( "+menuString.substring(10, menuString.length)+" ) "+ commonFunction.getMessages('checkLogFile'), 
						commonFunction.getMessages('error'),4,null,null,errorIcon);
				}
				else{
					usersXML= new XML(menuString);
					menuData =  new XMLListCollection(usersXML.*);
					vStack.selectedIndex=1;
				}
			}
						
			protected function menuHttpServiceFaultHandler(event:FaultEvent):void{
                Mask.close();
				Alert.show(commonFunction.getMessages('problemInService'),(commonFunction.getMessages('error')),4,null,null,errorIcon);	
			}
						
			// Event handler for the MenuBar control's itemClick event.
			private function menuHandler(event:MenuEvent):void  {
				var menuId:String=event.item.@menuId;
				//Alert.show("Clicked Menu id is: "+event.item.@menuId);					
				firstView(menuId);				
			}
			
			protected function firstView(menuId:String):void{
				switch(menuId)
				{

			case "MEB":
					{					
						var activityMasterControl:ActivityMasterControl=new ActivityMasterControl();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(activityMasterControl);
						break;

					}	
                   case "MBAA":
					{					
						var prestagingActivity:PrestagingActivity=new PrestagingActivity();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(prestagingActivity);
						break;

					}		
						
					
					case "MABA":
					{
						var entityMaster:EntityMaster=new EntityMaster();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(entityMaster);							
						break;
					}
										
					case "MAFA":
					{
						var activityMaster:ActivityMaster=new ActivityMaster();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(activityMaster);
						break;
					}
					case "MAFB":
					{
						var manageActivityMaster:ManageActivityMaster=new ManageActivityMaster();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(manageActivityMaster);
						break;
					}
					case "MADEA":
					{
						var createProgramCourse:ProgramCourseSetup=new ProgramCourseSetup();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(createProgramCourse);
						break;
					}
					case "MADEB":
					{
						var manageProgramCourse:ManageProgramCourseSetup=new ManageProgramCourseSetup();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(manageProgramCourse);
						break;
					}
					case "MCJA":
					{
						var createprogramOfferedBy:ProgramOfferedBy=new ProgramOfferedBy();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(createprogramOfferedBy);
						break;
					}
					case "MABB":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new ManageEntityMaster());
						break;
					}
					case "MCMA":
					{
						var programTermDetails:ProgramTermDetails=new ProgramTermDetails();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(programTermDetails);
						break;
					}
					case "MCMB":
					{
						var manageProgramTermDetails:ManageProgramTermDetails=new ManageProgramTermDetails();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(manageProgramTermDetails);
						break;
					}
					
					case "MCNA":
					{
						var createProgramRegistration:ProgramRegistration=new ProgramRegistration();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(createProgramRegistration);
						break;
					}
					case "MCNB":
					{
						var manageProgramRegistration:ManageProgramRegistration=new ManageProgramRegistration();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(manageProgramRegistration);
						break;
					}

//case "MACCA":
//					{					
//					var createEmpAuthority:employeeAuthoritySetup = new employeeAuthoritySetup();
//					loaderCanvas.removeAllChildren();
//					loaderCanvas.addChild(createEmpAuthority);
//					break;
//					}

					case "MACBA":
					{
						var createInstructorCourse:addInstructorCourse=new addInstructorCourse();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(createInstructorCourse);
						break;
					}
					case "MACBB":
					{
						var manageInstructorCourseObject:manageInstructorCourse=new manageInstructorCourse();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(manageInstructorCourseObject);
						break;
					}
						
					case "MAFC":
					{
						var buildActivityMaster:BuildActivityMaster=new BuildActivityMaster();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(buildActivityMaster);
						break;
					}
						
					case "MAADB":
					{
					var MouCourseObj:MouCourse=new MouCourse();
		 			loaderCanvas.removeAllChildren();
		 			loaderCanvas.addChild(MouCourseObj);
					break;
					}
					
					case "MADH":
					{
		 			var advanceParentProgramObj:AdvanceParentProgram=new AdvanceParentProgram();
		 			loaderCanvas.removeAllChildren();
		 			loaderCanvas.addChild(advanceParentProgramObj);
						break;
					}
					
					case "MADG":
					{
					 var programCourseTypeObj:ProgramCourseType=new ProgramCourseType();
					 loaderCanvas.removeAllChildren();
					 loaderCanvas.addChild(programCourseTypeObj);
					break;
					}
					

					case "MACAA":
					{
		 			var employeeMasterObj:employeeMaster=new employeeMaster();
		 			loaderCanvas.removeAllChildren();
		 			loaderCanvas.addChild(employeeMasterObj);
					break;
					}
					
					case "MACAB":
					{
		 			var ManageEmployeeMasterObj:ManageEmployeeMaster=new ManageEmployeeMaster();
		 			loaderCanvas.removeAllChildren();
		 			loaderCanvas.addChild(ManageEmployeeMasterObj);
					break;
					}
					
					
					case "MFB":
					{
					var switchRuleObj:switchRule=new switchRule();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(switchRuleObj);
					break;
					}
					
					case "MFD":
					{
					var cgpaDivisionObj:cgpaDivision=new cgpaDivision();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(cgpaDivisionObj);
					break;
					}
					
					case "MFA":
					{
					var evaluationComponentObj:evaluationComponent=new evaluationComponent();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(evaluationComponentObj);
					break;
					}
					
					case "MAGC":
					{
					var externalExaminarCourseObj:externalExaminarCourse=new externalExaminarCourse();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(externalExaminarCourseObj);
					break;
					}

					case "MBE":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new StudentCheckList());
					break;
					}
					
					case "MAAE":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new InitializeUniversity());
					break;
					}
					
					case "MADFA":
					{
					var CreateProgramSwitchObj:CreateProgramSwitch=new CreateProgramSwitch();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(CreateProgramSwitchObj);
					break;
					}
					
					case "MADFB":
					{
					var ManageProgramSwitchObj:ManageProgramSwitch=new ManageProgramSwitch();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(ManageProgramSwitchObj);
					break;
					}
					case "MAABA":
					{
					var universityMasterObjct:createUniversityRoles = new createUniversityRoles();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(universityMasterObjct);
					break;
					}
					case "MAABB":
					{
					var manageUniversityRole:manageUniversityRoles = new manageUniversityRoles();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(manageUniversityRole);
					break;
					}
					case "MAAAA":
					{
					var createUniversity:UniversityMaster = new UniversityMaster();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(createUniversity);
					break;
					}
					case "MAAAB":
					{
					var manageUniversity:ManageUniversityMaster = new ManageUniversityMaster();
					loaderCanvas.removeAllChildren();
					manageUniversity.loginUniversity=universityName;
					loaderCanvas.addChild(manageUniversity);
					break;
					}
					
					case "MAAAC":
					{
					var createDefaultUser:CreateDefaultUser = new CreateDefaultUser();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(createDefaultUser);
					break;
					}
					
					case "MACCA":
					{
					var employeeRoleSetup:employeeRole=new employeeRole();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(employeeRoleSetup);
					break;
					}
					
					case "MACCB":
					{
						var deleteEmployeeRole:DeleteEmployeeRole=new DeleteEmployeeRole();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(deleteEmployeeRole);
						break;
					}
					
					case "MACDA":
					{					
					var createEmpAuthority:employeeAuthoritySetup = new employeeAuthoritySetup();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(createEmpAuthority);
					break;
					}
					
					case "MACDB":
					{
					var manageEmpAuthority:manageEmployeeAuthority = new manageEmployeeAuthority();
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(manageEmpAuthority);
					break;
					}
//					case "MCD":
//					{
//					var progressCard:ProgressCard = new ProgressCard();
//					loaderCanvas.removeAllChildren();
//					loaderCanvas.addChild(progressCard);
//					break;
//					}
					
					case "MCE":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ReportAuthority);
					break;
					}
					
					case "MAACA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new UniversitySeatReservation);
					break;
					}
					
					case "MAEAB":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ManageCourseMaster);
					break;
					}
					
					case "MAEAA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new CourseMaster);
					break;
					}
						
					case "MFC":
					{
						var systemTableTwo:SystemTableTwo=new SystemTableTwo();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(systemTableTwo);
						break;
					}
					
					case "MAEB":
					{
		 			loaderCanvas.removeAllChildren();
		 			loaderCanvas.addChild(new courseGroup());
					break;
					}
					
					case "MDB":
					{
					var studentMasterObj:studentMaster=new studentMaster();
		 			loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(studentMasterObj);
					break;
					}
					
					case "MAECA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new CourseMarksApproval);
					break;
					}
					
					case "MAECB":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ManageCourseMarksApproval);
					break;
					}
					
					case "MAFB":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new StudentEnrollment);
					break;
					}
					
					case "MDC":
					{						
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new StudentTrackingControl);
					break;
											
					}
					
					case "MAADA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new universityWithMou);
					break;
					}
					
					case "MAEE":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new courseEvaluationCompnent);
					break;
					}
					
					case "SAH":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new PrestagingDataSheetUpload);
					break;
					}
					
					case "MAI":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new ResetPassword);
						break;
					}
					
					case "MCH":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new awardBlankForCollation);//added by ashish
						break;
					}
					
//					case "MCP":{						
//						loaderCanvas.removeAllChildren();
//						loaderCanvas.addChild(new ResultReport);
//						break;						
//					}
//					case "MCT":{						
//						loaderCanvas.removeAllChildren();
//						loaderCanvas.addChild(new MedalListReport);
//						break;						
//					}
					
					case "MBDA":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new PrestagingDataSheetUpload());
						break;
					}
					
					case "MBDB":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new PrestagingDataUploadForAdmin());
						break;
					}
					
					case "MEA":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new SendEmail());
						break;
					}
					
				//	case "MBDC":
				//	{
				//		loaderCanvas.removeAllChildren();
				//		loaderCanvas.addChild(new Process());
				//		break;
				//	}
					
					case "MAED":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new AddDropCourse());
					break;
					}
					
					case "MFE":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new EnrollmentPhotoUpload());
					break;
					}
					
//					case "MCO":
//					{
//					loaderCanvas.removeAllChildren();
//					loaderCanvas.addChild(new ConsolidatedChart());
//					break;
//					}
					
//					case "MCS":
//					{
//					loaderCanvas.removeAllChildren();
//					loaderCanvas.addChild(new ResultStatistics());
//					break;
//					}
					
					case "MADK":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new programCourseHeaderCredit());
					break;
					}
					
//					case "MCQ":
//					{
//					loaderCanvas.removeAllChildren();
//					loaderCanvas.addChild(new RegistrationStatistics);
//					break;
//					}
					
					//Amir ends here...

									
					case "MADAA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ProgramMaster());
					break;
					}
					
					case "MADABA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ManageBasicProgramDetails());
					break;
					}
					
					case "MADABB":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ManageProgramDuration());
					break;
					}
					
					case "MADABC":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ManageProgramBranchSpecialization());
					break;
					}
					
					case "MADCA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ProgramTermDetails());
					break;
					}
					
					case "MADCB":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ManageProgramTermDetails());
					break;
					}
					
						case "MADBA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ProgramOfferedBy());
					break;
					}
					
						case "MADBB":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ManageEntityProgram());
					break;
					}
					
						case "MADDA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ProgramRegistration());
					break;
					}
					
						case "MADDB":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ManageProgramRegistration());
					break;
					}

				
			
					//Ashutosh starts
					
//					case "RAA":
//					{
//						var progressCardObj:ProgressCard=new ProgressCard();
//						loaderCanvas.removeAllChildren();
//						loaderCanvas.addChild(progressCardObj);
//						break;
//					}
					
//					case "RAB":
//					{
//						var semesterWiseMarksObj:SemesterWiseMarks=new SemesterWiseMarks();
//						loaderCanvas.removeAllChildren();
//						loaderCanvas.addChild(semesterWiseMarksObj);
//						break;
//					}
                                        case "MCF":
						{							
							var print:PrintReports = new PrintReports();
							loaderCanvas.removeAllChildren();
							loaderCanvas.addChild(print);
							break;
						}

					//Ashutosh menu ends here
					
					case "MADI":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new ProgramGroup());
						break;
					}
					
					case "MADJ":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new GroupRule());
						break;
					}
					
					case "MCA":
					{
						var internalAwardBlank:CreateAwardBlank=new CreateAwardBlank();
						internalAwardBlank.displayType="I";
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(internalAwardBlank);
						break;
					}
					
					case "MCB":
					{
						var externalAwardBlank:CreateAwardBlank=new CreateAwardBlank();
						externalAwardBlank.displayType="E";
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(externalAwardBlank);
						break;
					}
					
					case "MCC":
					{
						var remedialAwardBlank:CreateAwardBlank=new CreateAwardBlank();
						remedialAwardBlank.displayType="R";
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(remedialAwardBlank);
						break;
					}

					case "MDA":
					{
						var studentRemedialsObj:studentRemedials=new studentRemedials();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(studentRemedialsObj);
						break;
					}
					
					case "MAFD":
					{
						var buildActivityViaProgram:BuildActivityViaProgram=new BuildActivityViaProgram();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(buildActivityViaProgram);
						break;
					}
					
					case "MBC":
					{
						var cancelRegistration:CancelRegistration=new CancelRegistration();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(cancelRegistration);
						break;
					}
					
					case "MCG":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new AwardBlankReports());
						break;
					}
					
//					case "MCK":
//					{
//						var finalSemesterResultStatistics:FinalSemesterResultStatistics = 
//						new FinalSemesterResultStatistics();
//						finalSemesterResultStatistics.universityName = universityName;
//						loaderCanvas.removeAllChildren();
//						loaderCanvas.addChild(finalSemesterResultStatistics);
//						break;
//					}	
								
//					case "MCR":
//					{
//						var finalSemesterResultStatisticsCategoryWise:FinalSemesterResultStatisticsCategoryWise = 
//						new FinalSemesterResultStatisticsCategoryWise();
//						finalSemesterResultStatisticsCategoryWise.universityName = universityName;						
//						loaderCanvas.removeAllChildren();
//						loaderCanvas.addChild(finalSemesterResultStatisticsCategoryWise);
//						break;
//					}
                    
                    case "MCR":
					{ 
                        var transcript1:Transcript = new Transcript();
						transcript1.universityName = universityName;						
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(transcript1);
						break;
					}
                    
					case "MCM":
					{
						loaderCanvas.removeAllChildren();
						var majorGroupWiseMeritList:MajorGroupWiseMeritList = new MajorGroupWiseMeritList();
						majorGroupWiseMeritList.UName=universityName;
						loaderCanvas.addChild(majorGroupWiseMeritList);
						break;
					}

						case "MBAB":
					{
						var UpdatePrestagingTableObj:UpdatePrestagingTable=new UpdatePrestagingTable();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(UpdatePrestagingTableObj);
						break;
					}
                        case "MBBA":
					{
						var NameCorrectionObj:NameCorrection=new NameCorrection();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(NameCorrectionObj);
						break;
					}
					
						case "MBBB":
					{
						var correctionInRegistrationObj:correctionInRegistration=new correctionInRegistration();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(correctionInRegistrationObj);
						break;
					}
					
					case "MAHA":
					 {
					  loaderCanvas.removeAllChildren();
					  loaderCanvas.addChild(new EodControl());
					  break;
					 }
					 
					 case "MAHB":
					 {
					  loaderCanvas.removeAllChildren();
					  loaderCanvas.addChild(new BuildEodMaster());
					  break;
					 }
					 
					  case "MADDC":
					 {
					  loaderCanvas.removeAllChildren();
					  loaderCanvas.addChild(new BuildProgramRegistration());
					  break;
					 }
					 
					 case "MACBC":
					 {
					  loaderCanvas.removeAllChildren();
					  loaderCanvas.addChild(new BuildInstructorCourse());
					  break;
					 }
                     
//                     case "MCN":
//					 {
//					  loaderCanvas.removeAllChildren();
//					  loaderCanvas.addChild(new gradeReportConsolidated());
//					  break;
//					 }
                     
                     case "MDD":
					 {
					  loaderCanvas.removeAllChildren();
					  loaderCanvas.addChild(new studentIssue());
					  break;
					 }
//Nupur code starts here                     
                     case "MEC":
					{
					var mm:ManualProcess=new ManualProcess();
					mm.Prefix="studentUpload";
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(mm);
					break;
					}
                     case "MFH":
					{
                    loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ResultFileNameUpload());
					break;
					}
                    case "MED":
					{
						var mm:ManualProcess=new ManualProcess();
						mm.Prefix="studentUploadFirstSemester";
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(mm);
					break;
					}
                    case "MDE":
					{
						var stuMarks:studentInfoMain=new studentInfoMain();
						stuMarks.universityName=universityName;
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(stuMarks);
					break;
					}
//nupur code ends here	
//Ashish Mohan code starts here                     
                    
					 case "MDG":
					{			
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new LoginForm1);
					break;
					}
					case "MBFA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new Cancel);
					break;
					}
					
					case "MBFB":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new Terminate);
					break;
					}
					
					case "MBFC":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new Withdraw());
					break;
					}
					
					case "MBG":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new insertToPrestaging);
					break;
					}
					
					case "MAEF":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new GradeLimit);
						break;
					}
					
					case "MAEG":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new WithdrawCourses);
						break;
					}
					
					case "MAEI":
					{	
						var internalGrade:InternalGradeLimit=new InternalGradeLimit;
						internalGrade.displayType="I";
						internalGrade.title="Grade Limit For Internal";
						internalGrade.user=userName;
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(internalGrade);
						break;
					}
					
					case "MAEJ":
					{	
						var remGrade:ExternalGradeLimit=new ExternalGradeLimit;
						remGrade.displayType="R";
						remGrade.title="Grade Limit For Remedial";
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(remGrade);
						break;
					}
					
					
					case "MAEK":
					{	
						var extGrade:ExternalGradeLimit=new ExternalGradeLimit;
						extGrade.displayType="E";
						extGrade.title="Grade Limit For External";
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(extGrade);
						break;
					}
					
					case "MAEL":
					{	
						var core:GradeLimitForCore=new GradeLimitForCore;
						core.displayType="I";
						core.title="GRADE LIMIT FOR CORE COURSES";
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(core);
						break;
					}

					case "MCI":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new MeritListGroup);
					break;
					}
					
					case "MCJ":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new DelayInDisplayMarks);
					break;
					}


//					case "MCX":
//					{
//					loaderCanvas.removeAllChildren();
//					loaderCanvas.addChild(new UnsatisfactoryPerformance());
//					break;
//					}



//					case "MCU":
//					{
//					loaderCanvas.removeAllChildren();
//					loaderCanvas.addChild(new TempCourses());
//					break;
//					}
					
//					case "MCV":
//					{
//					loaderCanvas.removeAllChildren();
//					loaderCanvas.addChild(new FinalCourses());
//					break;
//					}
//Ashish Mohan code ends here 	

//Devendra code starts here	
					 case "MFFA":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new BuildNextSession());
					break;
					}
					case "MFFB":
					{
					 loaderCanvas.removeAllChildren();
					 loaderCanvas.addChild(new ProgramRegistrationBuild());
					 break;
					}	
					case "MFFC":
					{
						var buildActivityMaster:BuildActivityMaster=new BuildActivityMaster();
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(buildActivityMaster);
						break;
					}
					case "MFFD":
					{
					  loaderCanvas.removeAllChildren();
					  loaderCanvas.addChild(new InstructorCourseBuild());
					  break;
					}
					case "MFFE":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new resetSystemValues());
						break;
					}	
					case "MFFF":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new ClearStagingTables());
						break;
					}
					case "MFGA":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new ClearTemporaryTables());
						break;
					}
					case "MFGB":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new ReadyForRegistration());
						break;
					}
					case "MFGC":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new ReadyForSemesterEnd());
						break;
					}
					case "MCS":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new ProvisionalCertificate());
						break;
					}
					case "MAEH":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new CancelCourseGroup());
						break;
					}
					case "MDH":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new WithdrawStudent());
						break;
					}
					case "MDI":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new Remark());
						break;
					}
					case "MDJ":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new UserDataInterface());
						break;
					}
					case "MAJ":	
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new rollEnrollFormat());
						break;
					}
//Devendra code ends here	
//Mandeep code starts here			
					case "MAGA":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new externalExaminarDetail());
						break;
					}
					case "MAGB":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new manageExternalExaminarDetail );
						break;
					}
					case "MCQ":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new DelayInComponentMarks);
						break;
					}					
					
					case "MCK":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new CourseWisePanelOfExaminers);
						break;
					}
//Mandeep code ends here					
					case "MDF":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new programswitchmain);
						break;
					}
//Mandeep code ends here		
					
					//"MCD" Menu Item Added By Dheeraj
					
					case "MCD":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new AwardBlankCorrection);
						break;
					}
					
					case "MEE":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new admissionIntegration);
						break;
					}
			
					case "MEF":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new WithdrawMarksTransfer);
						break;
					}
					case "MFJ":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new RevertResultProcess);
						break;
					}
					
					case "MFM":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new MailConfiguration);
						break;
					}
					
					case "MFN":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new LogoUpload);
						break;
					}
                    //*********staging table report interface added by NUPUR
					case "MCW":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new StagingTableReport());
						break;
					}
					
//					case "MAAF":
//					{
//					loaderCanvas.removeAllChildren();
//					loaderCanvas.addChild(new RegistrationForInsAndAdmin());
//					break;
//					}
					case "MAAG":
					{
					loaderCanvas.removeAllChildren();
					loaderCanvas.addChild(new ApprovalforInstAdmin());
					break;
					}
                     case "MACE":
					{
						loaderCanvas.removeAllChildren();
						loaderCanvas.addChild(new InstructorCourse());
						break;
					}
					//*******************************************************
					default:
					{
						Alert.show(commonFunction.getMessages('interfaceNotAdded'),(commonFunction.getMessages('info')),4,null,null,infoIcon);
			
						break;
					}
				}
			}
			
