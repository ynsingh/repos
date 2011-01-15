/**********************************************************************************
 * $URL:
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      .............
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/
package in.ac.dei.edrp.client.Applications;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_StudentInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.Shared.*;

import java.util.Date;

/**
 * <code> CreateSummarySheet </code>
 * CreateSummarySheet is used to generate a Application Form.
 * @author Ankit
 */
public class CreateSummarySheet{
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
    String progID;
    String Program;
    SummarySheet summaryObj;
    String NewRegNo;
    String ProgRegValue;
    String FormNo;
    String entity_id;
    String entity_name;
    String stu_value;
    boolean flag = false;
    String branchName = "";
    String branchId = "";
    boolean checkProgReg = false;
    String[] student = null;
    String RegFlag;
    String catCode;
    String Stu_Cos;
    int branchCount = 0;


    String entity_DefaultType = "";
    String entity_DefaultDescription = "";


    boolean entityViewIs = false;
     CM_ComboBoxes entityTypeCB;
     CM_ComboBoxes entityNameCB;
     CM_ComboBoxes programCB;
     CM_ComboBoxes branchCB;
    VerticalPanel vp;
    ScrollPanel mainVerticalPanel = new ScrollPanel();
    Validator valid = new Validator();
    String creatorid;
    messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    String dbException = cons.dbError();
    String errorMsg = msg.error();
    String confirm = msg.confirm();
    String alertSave = msg.alert_confirmentries();
    String correctEntriesMsg = msg.checkFields();
    String msgCheckFormNo = msg.checkFormNo(8);
    String msgCheckRegNo = msg.checkRegNo(8);
    String duplicateForm = msg.duplicateFormNoAlert();
    String duplicateRegNo = msg.duplicateRegNoAlert();
    String xCOS = cons.xCOS();
    Button submitButton;
    Button okButton;
    FormPanel mainForm;

    /**
     * Constructor Definition
     * @param userid
     */
    public CreateSummarySheet(String userid) {
        this.creatorid=userid;
    }


    /**
     * method generate First portion of Application form
     * for select the Entity, program, branch.
     * @return ScrollPanel
     */
    public ScrollPanel onModuleLoad1() {
    	summaryObj = new SummarySheet();
    	mainVerticalPanel.clear();
        mainVerticalPanel.setWidth("100%");
        mainVerticalPanel.setHeight("100%");

        vp = new VerticalPanel();

        okButton = new Button(cons.okButton());
         submitButton = new Button(cons.submit());
         entityTypeCB = new CM_ComboBoxes();
         entityNameCB = new CM_ComboBoxes();
         programCB = new CM_ComboBoxes();
         branchCB = new CM_ComboBoxes();
         mainForm = new FormPanel();

        entityTypeCB.onModuleLoad();
        programCB.onModuleLoad();
        entityNameCB.onModuleLoad();
        branchCB.onModuleLoad();
        programCB.progCombo.disable();
        branchCB.branchCombo.disable();
        entityNameCB.entityCombo.disable();

        /*
         * method check the Entity Default View
         */
        connectTemp.checkForDefaultView(new AsyncCallback<String>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert(dbException, caught.getMessage());
                }

                public void onSuccess(String arg0) {
                    if (arg0.equalsIgnoreCase("y")) {
                        entityViewIs = true;
                    }
					
                    furhterExecution();
					
                }
            });

        /*
         * Button for Submitting the Student Information
         */
        submitButton.addListener(new ButtonListenerAdapter() {
                @SuppressWarnings({"deprecation"
                })
                public void onClick(Button button, EventObject e) {
                	student = summaryObj.studentInfo();

                    progID = programCB.progCombo.getValue();
                    FormNo = student[12];
                    student[15] = creatorid;
                    int check = 0;

                    if (summaryObj.checkChars() == 1) {
                        System.out.println("error in chars" + check);

                        summaryObj.markIVChars(summaryObj.fname);
                        summaryObj.markIVChars(summaryObj.mname);
                        summaryObj.markIVChars(summaryObj.lname);
                        summaryObj.markIVChars(summaryObj.fname1);
                        summaryObj.markIVChars(summaryObj.mname1);
                        summaryObj.markIVChars(summaryObj.lname1);
                        summaryObj.markIVChars(summaryObj.fname2);
                        summaryObj.markIVChars(summaryObj.mname2);
                        summaryObj.markIVChars(summaryObj.lname2);
                        summaryObj.markIVChars(summaryObj.cityText);

                        check++;
                        System.out.println("error in chars" + check);
                    }

                    try {
						if (summaryObj.checkDate() > 0) {
						    check++;
						}
					} catch (Exception e1) {
						check++;
						//MessageBox.alert("Enter a valid Date");
					}

                    if (summaryObj.checkPersonal() > 0) {
                        summaryObj.markIV(summaryObj.fname);
                        //summaryObj.markIV(summaryObj.dateofbirthDateField);
                        summaryObj.markIV(summaryObj.newCat);
                        summaryObj.markIV(summaryObj.fname1);
                        summaryObj.markIV(summaryObj.fname2);
                        summaryObj.markIV(summaryObj.newGender);

                        check++;
                        System.out.println("error in pd" + check);
                    }

                    if (summaryObj.checkAddress() > 0) {
                        summaryObj.markIV(summaryObj.street1Text);
                        summaryObj.markIV(summaryObj.cityText);
                        summaryObj.markIV(summaryObj.newState);
                        summaryObj.markIV(summaryObj.pinNumber1);

                        check++;
                        System.out.println("error in add" + check);
                    }

                    if (summaryObj.checkAcad() > 0) {
                        check++;
                        System.out.println("error in acad" + check);
                    }

                    if (summaryObj.checkAcad() == 0) {
                        if (summaryObj.marksCheck() > 0) {
                            check++;
                            System.out.println("error in markscheck" + check);
                        }
                    }

                    if (summaryObj.hpPaper.isVisible()) {
                        if (summaryObj.checkpaperCode() > 0) {
                            check++;
                            System.out.println("error in paper" + check);
                        }
                    }

                    if (summaryObj.checkUG() > 0) {
                        summaryObj.markIV(summaryObj.ug.firstDegCodeCB);
                        check++;
                        System.out.println("error in ug" + check);
                    }

                    if (summaryObj.checkPG() > 0) {
                        summaryObj.markIV(summaryObj.pg.firstDegCodeCB);
                        check++;
                        System.out.println("error in pg" + check);
                    }

                    if (summaryObj.CheckBoard(summaryObj.ocb1) > 0) {
                        check++;
                        System.out.println("error in board" + check);
                    }

                    if (check == 0) {
                        /*
                         * method Checking the Registration Number
                         */
                        connectTemp.checkRegNo(NewRegNo, FormNo,
                            new AsyncCallback<boolean[]>() {
                                public void onFailure(Throwable caught) {
                                    MessageBox.alert(dbException,
                                        caught.getMessage());
                                }

                                public void onSuccess(boolean[] arg0) {
                                    checkProgReg = arg0[0];

                                    //checkForm=arg0[1];

                                    //if(checkProgReg==false && checkForm==false){
                                    if (checkProgReg == false) {
                                        String[] dupCheck = summaryObj.DuplicacyCheck(NewRegNo);

                                        /*
                                         * method to check Duplicate Student Information.
                                         */
                                        connectTemp.checkDuplicacy(dupCheck,
                                            new AsyncCallback<CM_StudentInfoGetter>() {
                                                public void onFailure(
                                                    Throwable caught) {
                                                    MessageBox.alert(dbException,
                                                        caught.getMessage());
                                                }

                                                public void onSuccess(
                                                    CM_StudentInfoGetter arg0) {
                                                    if (arg0 == null) {
                                                        flag = false;
                                                    } else {
                                                        flag = arg0.getFlag();
                                                        RegFlag = arg0.getRegistrationNumber();
                                                    }

                                                    if (flag == false) {
                                                        //	if(check==0)

                                                        //	{
                                                    	
                                                    	/*
                                                         * method to update Program Registration Number
                                                         */
                                                    	connectTemp.updateProgRegNumber(creatorid, entity_id,
                                                                progID, branchId, ProgRegValue,
                                                                new AsyncCallback<String>() {
                                                                    @Override
                                                                    public void onFailure(Throwable caught) {
                                                                        MessageBox.alert(dbException,
                                                                            caught.getMessage());
                                                                    }

                                                                    @Override
                                                                    public void onSuccess(String result) {
                                                                    }
                                                                });

                                                    	
                                                        MessageBox.confirm(confirm,
                                                            alertSave,
                                                            new MessageBox.ConfirmCallback() {
                                                                public void execute(
                                                                    String btnID) {
                                                                    if (btnID.matches(
                                                                                "yes")) {
                                                                        connectTemp.getStudentValue(creatorid,
                                                                            new AsyncCallback<CM_entityInfoGetter[]>() {
                                                                                public void onFailure(
                                                                                    Throwable caught) {
                                                                                    MessageBox.alert(dbException,
                                                                                        caught.getMessage());
                                                                                }

                                                                                public void onSuccess(
                                                                                    CM_entityInfoGetter[] arg0) {
                                                                                    stu_value = arg0[0].getvalue();

                                                                                    if (valid.nullValidator(
                                                                                                branchCB.branchCombo.getValue()) == false) {
                                                                                        branchId = branchCB.branchCombo.getValue();
                                                                                    }

                                                                                    student[13] = NewRegNo;
                                                                                    student[14] = "S" +
                                                                                        entity_id +
                                                                                        stu_value;

                                                                                    /*
                                                                                     * Method to Store the Student Personal information.
                                                                                     */
                                                                                    connectTemp.PersonalInfo(student,
                                                                                        creatorid,
                                                                                        new AsyncCallback<String>() {
                                                                                            public void onFailure(
                                                                                                Throwable caught) {
                                                                                                MessageBox.alert(dbException,
                                                                                                    caught.getMessage());
                                                                                            }

                                                                                            public void onSuccess(
                                                                                                String arg0) {
                                                                                            }
                                                                                        });

                                                                                    final String[] address =
                                                                                        summaryObj.addressInfo(student[14]);

                                                                                    /*
                                                                                     * Method to Store the Student Address information.
                                                                                     */
                                                                                    connectTemp.addressInfo(address,
                                                                                        creatorid,
                                                                                        new AsyncCallback<String>() {
                                                                                            public void onFailure(
                                                                                                Throwable caught) {
                                                                                                MessageBox.alert(dbException,
                                                                                                    caught.getMessage());
                                                                                            }

                                                                                            public void onSuccess(
                                                                                                String arg0) {
                                                                                            }
                                                                                        });

                                                                                    for (int i =
                                                                                            0;
                                                                                            i < summaryObj.cb1.length;
                                                                                            i++) {
                                                                                        if (summaryObj.cb1[i].isChecked() == true) {
                                                                                            String spwt =
                                                                                                summaryObj.cb1[i].getName();

                                                                                            /*
                                                                                             * Method to Store the special weightage Information.
                                                                                             */
                                                                                            connectTemp.studentSpWt(progID,
                                                                                                NewRegNo,
                                                                                                spwt,
                                                                                                entity_id,
                                                                                                creatorid,
                                                                                                new AsyncCallback<String>() {
                                                                                                    public void onFailure(
                                                                                                        Throwable caught) {
                                                                                                        MessageBox.alert(dbException,
                                                                                                            caught.getMessage());
                                                                                                    }

                                                                                                    public void onSuccess(
                                                                                                        String arg0) {
                                                                                                    }
                                                                                                });
                                                                                        }
                                                                                    }

                                                                                    if (summaryObj.radioSW.isChecked() == true) {
                                                                                        connectTemp.studentSpWt(progID,
                                                                                            NewRegNo,
                                                                                            "SW",
                                                                                            entity_id,
                                                                                            creatorid,
                                                                                            new AsyncCallback<String>() {
                                                                                                public void onFailure(
                                                                                                    Throwable caught) {
                                                                                                    MessageBox.alert(dbException,
                                                                                                        caught.getMessage());
                                                                                                }

                                                                                                public void onSuccess(
                                                                                                    String arg0) {
                                                                                                }
                                                                                            });
                                                                                    }

                                                                                    final String[][] hello =
                                                                                        summaryObj.returnString();

                                                                                    for (int i =
                                                                                            0;
                                                                                            i < hello.length;
                                                                                            i++) {
                                                                                    	 /*
                                                                                         * Method to Store the Student Call List information.
                                                                                         */
                                                                                    	connectTemp.StudentcallListInfo(entity_id,
                                                                                            progID,
                                                                                            branchId,
                                                                                            NewRegNo,
                                                                                            hello[i],
                                                                                            creatorid,
                                                                                            new AsyncCallback<String>() {
                                                                                                public void onFailure(
                                                                                                    Throwable caught) {
                                                                                                    MessageBox.alert(dbException,
                                                                                                        caught.getMessage());
                                                                                                }

                                                                                                public void onSuccess(
                                                                                                    String arg0) {
                                                                                                }
                                                                                            });
                                                                                    }

                                                                                    if (summaryObj.hpPaper.isVisible()) {
                                                                                        if (summaryObj.checkpaperCode() == 0) {
                                                                                            final String[][] paperCodes =
                                                                                                summaryObj.StudentpaperCode();

                                                                                            for (int i =
                                                                                                    0;
                                                                                                    i < paperCodes.length;
                                                                                                    i++) {
                                                                                                final String PaperCode =
                                                                                                    paperCodes[i][0];
                                                                                                final String PaperGroup =
                                                                                                    paperCodes[i][1];

                                                                                                progID = programCB.progCombo.getValue();

                                                                                                /*
                                                                                                 * Method to Store the paper Code information.
                                                                                                 */
                                                                                                connectTemp.paperCodeInfo(progID,
                                                                                                    NewRegNo,
                                                                                                    FormNo,
                                                                                                    PaperCode,
                                                                                                    entity_id,
                                                                                                    creatorid,
                                                                                                    PaperGroup,
                                                                                                    new AsyncCallback<String>() {
                                                                                                        public void onFailure(
                                                                                                            Throwable caught) {
                                                                                                            MessageBox.alert(dbException,
                                                                                                                caught.getMessage());
                                                                                                        }

                                                                                                        public void onSuccess(
                                                                                                            String arg0) {
                                                                                                        }
                                                                                                    });
                                                                                            }
                                                                                        }
                                                                                    }

                                                                                    if (summaryObj.ug.firstDegCodeCB.isVisible()) {
                                                                                        String FirstDeg =
                                                                                            summaryObj.StudentFD(summaryObj.ug.firstDegCodeCB);

                                                                                        if (valid.nullValidator(
                                                                                                    FirstDeg) == false) {
                                                                                        	 /*
                                                                                             * Method to Store the Student First Degree information.
                                                                                             */
                                                                                        	connectTemp.StudentFD(progID,
                                                                                                NewRegNo,
                                                                                                FormNo,
                                                                                                FirstDeg,
                                                                                                creatorid,
                                                                                                new AsyncCallback<String>() {
                                                                                                    public void onFailure(
                                                                                                        Throwable caught) {
                                                                                                        MessageBox.alert(dbException,
                                                                                                            caught.getMessage());
                                                                                                    }

                                                                                                    public void onSuccess(
                                                                                                        String arg0) {
                                                                                                    }
                                                                                                });
                                                                                        }
                                                                                    }

                                                                                    if (summaryObj.pg.firstDegCodeCB.isVisible()) {
                                                                                        String PDeg =
                                                                                            summaryObj.StudentFD(summaryObj.pg.firstDegCodeCB);

                                                                                        if (valid.nullValidator(
                                                                                                    PDeg) == false) {
                                                                                            connectTemp.StudentFD(progID,
                                                                                                NewRegNo,
                                                                                                FormNo,
                                                                                                PDeg,
                                                                                                creatorid,
                                                                                                new AsyncCallback<String>() {
                                                                                                    public void onFailure(
                                                                                                        Throwable caught) {
                                                                                                        MessageBox.alert(dbException,
                                                                                                            caught.getMessage());
                                                                                                    }

                                                                                                    public void onSuccess(
                                                                                                        String arg0) {
                                                                                                    }
                                                                                                });
                                                                                        }
                                                                                    }

                                                                                    catCode = summaryObj.newCat.getValue() +
                                                                                        "%";

                                                                                    /*
                                                                                     * Method to Get the COS value.
                                                                                     */
                                                                                    connectTemp.getcos_value(programCB.progCombo.getValue(),
                                                                                        branchId,
                                                                                        entity_id,
                                                                                        catCode,
                                                                                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                            public void onFailure(
                                                                                                Throwable caught) {
                                                                                                MessageBox.alert(dbException,
                                                                                                    caught.getMessage());
                                                                                            }

                                                                                            public void onSuccess(
                                                                                                CM_ProgramInfoGetter[] arg0) {
                                                                                                if (arg0.length == 0) {
                                                                                                    Stu_Cos = xCOS;
                                                                                                } else {
                                                                                                    String COS =
                                                                                                        arg0[0].getcos_value();

                                                                                                    if (COS.charAt(
                                                                                                                2) == 'X') {
                                                                                                        Stu_Cos = COS;
                                                                                                    } else if (COS.charAt(
                                                                                                                2) == student[3].charAt(
                                                                                                                0)) {
                                                                                                        Stu_Cos = COS;
                                                                                                    } else {
                                                                                                        Stu_Cos = xCOS;
                                                                                                    }
                                                                                                }

                                                                                                /*
                                                                                                 * Method to Store the Student Registration information.
                                                                                                 */
                                                                                                connectTemp.studentReg(student[14],
                                                                                                    NewRegNo,
                                                                                                    FormNo,
                                                                                                    Stu_Cos,
                                                                                                    entity_id,
                                                                                                    creatorid,
                                                                                                    new AsyncCallback<String>() {
                                                                                                        public void onFailure(
                                                                                                            Throwable caught) {
                                                                                                            MessageBox.alert(dbException,
                                                                                                                caught.getMessage());
                                                                                                        }

                                                                                                        public void onSuccess(
                                                                                                            String arg0) {
                                                                                                        }
                                                                                                    });
                                                                                            }
                                                                                        });

                                                                                    MessageBox.show(new MessageBoxConfig() {

                                                                                            {
                                                                                                setMsg(msg.alert_successfulentry() +
                                                                                                    " " +
                                                                                                    msg.yourRegNo() +
                                                                                                    " " +
                                                                                                    NewRegNo);

                                                                                                setButtons(MessageBox.OK);
                                                                                                setCallback(new MessageBox.PromptCallback() {
                                                                                                        public void execute(
                                                                                                            String btnID,
                                                                                                            String text) {
                                                                                                            try {
                                                                                                                mainVerticalPanel.clear();

                                                                                                                onModuleLoad1();

                                                                                                              //  vp.add(summaryObj.mainScroll);
                                                                                                               // vp.add(submitButton);
                                                                                                            } catch (Exception e) {
                                                                                                            }
                                                                                                        }
                                                                                                    });
                                                                                            }
                                                                                        });
                                                                                }
                                                                            });
                                                                    }
                                                                }
                                                            });

                                                        //}
                                                    } else {
                                                        branchId = branchCB.branchCombo.getValue();

                                                        if(entityViewIs==false){
                                                        entity_id = entityNameCB.entityCombo.getValue();
                                                        }

                                                        catCode = summaryObj.newCat.getValue() +
                                                            "%";

                                                        /*
                                                         * Method to check the already registered student.
                                                         */
                                                        connectTemp.checkProgIdRegNo(programCB.progCombo.getValue(),
                                                            branchId, NewRegNo,
                                                            RegFlag,
                                                            new AsyncCallback<CM_StudentInfoGetter>() {
                                                                public void onFailure(
                                                                    Throwable caught) {
                                                                    MessageBox.alert(dbException,
                                                                        caught.getMessage());
                                                                }

                                                                public void onSuccess(
                                                                    CM_StudentInfoGetter arg0) {
                                                                    final String stuId =
                                                                        arg0.getUser_id();

                                                                    if ((arg0.getFlag() == true)) {
                                                                        MessageBox.confirm(confirm,
                                                                            msg.userExistsAlert(),
                                                                            new MessageBox.ConfirmCallback() {
                                                                                public void execute(
                                                                                    String btnID) {
                                                                                    if (btnID.matches(
                                                                                                "yes")) {
                                                                                    	
                                                                                    	/*
                                                                                         * Method to Update program Registration Number.
                                                                                         */
                                                                                        connectTemp.updateProgRegNumber(creatorid,
                                                                                            entity_id,
                                                                                            progID,
                                                                                            branchId,
                                                                                            ProgRegValue,
                                                                                            new AsyncCallback<String>() {
                                                                                                @Override
                                                                                                public void onFailure(
                                                                                                    Throwable caught) {
                                                                                                    MessageBox.alert(dbException,
                                                                                                        caught.getMessage());
                                                                                                }

                                                                                                @Override
                                                                                                public void onSuccess(
                                                                                                    String result) {
                                                                                                }
                                                                                            });
                                                                                        
                                                                                        connectTemp.getcos_value(programCB.progCombo.getValue(),
                                                                                            branchId,
                                                                                            entity_id,
                                                                                            catCode,
                                                                                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                                                                public void onFailure(
                                                                                                    Throwable caught) {
                                                                                                    MessageBox.alert(dbException,
                                                                                                        caught.getMessage());
                                                                                                }

                                                                                                public void onSuccess(
                                                                                                    CM_ProgramInfoGetter[] arg0) {
                                                                                                    if (arg0.length == 0) {
                                                                                                        Stu_Cos = xCOS;
                                                                                                    } else {
                                                                                                        String COS =
                                                                                                            arg0[0].getcos_value();

                                                                                                        if (COS.charAt(
                                                                                                                    2) == 'X') {
                                                                                                            Stu_Cos = COS;
                                                                                                        } else if (COS.charAt(
                                                                                                                    2) == student[3].charAt(
                                                                                                                    0)) {
                                                                                                            Stu_Cos = COS;
                                                                                                        } else {
                                                                                                            Stu_Cos = xCOS;
                                                                                                        }
                                                                                                    }

                                                                                                    connectTemp.studentReg(stuId,
                                                                                                        NewRegNo,
                                                                                                        FormNo,
                                                                                                        Stu_Cos,
                                                                                                        entity_id,
                                                                                                        creatorid,
                                                                                                        new AsyncCallback<String>() {
                                                                                                            public void onFailure(
                                                                                                                Throwable caught) {
                                                                                                                MessageBox.alert(dbException,
                                                                                                                    caught.getMessage());
                                                                                                            }

                                                                                                            public void onSuccess(
                                                                                                                String arg0) {
                                                                                                            }
                                                                                                        });
                                                                                                }
                                                                                            });

                                                                                        final String[][] hello =
                                                                                            summaryObj.returnString();

                                                                                        for (int i =
                                                                                                0;
                                                                                                i < hello.length;
                                                                                                i++) {
                                                                                            connectTemp.StudentcallListInfo(entity_id,
                                                                                                progID,
                                                                                                branchId,
                                                                                                NewRegNo,
                                                                                                hello[i],
                                                                                                creatorid,
                                                                                                new AsyncCallback<String>() {
                                                                                                    public void onFailure(
                                                                                                        Throwable caught) {
                                                                                                        MessageBox.alert(dbException,
                                                                                                            caught.getMessage());
                                                                                                    }

                                                                                                    public void onSuccess(
                                                                                                        String arg0) {
                                                                                                    }
                                                                                                });
                                                                                        }

                                                                                        if (summaryObj.hpPaper.isVisible()) {
                                                                                            if (summaryObj.checkpaperCode() == 0) {
                                                                                                final String[][] paperCodes =
                                                                                                    summaryObj.StudentpaperCode();

                                                                                                for (int i =
                                                                                                        0;
                                                                                                        i < paperCodes.length;
                                                                                                        i++) {
                                                                                                    final String PaperCode =
                                                                                                        paperCodes[i][0];
                                                                                                    final String PaperGroup =
                                                                                                        paperCodes[i][1];

                                                                                                    connectTemp.paperCodeInfo(programCB.progCombo.getValue(),
                                                                                                        NewRegNo,
                                                                                                        FormNo,
                                                                                                        PaperCode,
                                                                                                        entity_id,
                                                                                                        creatorid,
                                                                                                        PaperGroup,
                                                                                                        new AsyncCallback<String>() {
                                                                                                            public void onFailure(
                                                                                                                Throwable caught) {
                                                                                                                MessageBox.alert(dbException,
                                                                                                                    caught.getMessage());
                                                                                                            }

                                                                                                            public void onSuccess(
                                                                                                                String arg0) {
                                                                                                            }
                                                                                                        });
                                                                                                }
                                                                                            }
                                                                                        }

                                                                                        if (summaryObj.ug.firstDegCodeCB.isVisible()) {
                                                                                            String FirstDeg =
                                                                                                summaryObj.StudentFD(summaryObj.ug.firstDegCodeCB);

                                                                                            if (valid.nullValidator(
                                                                                                        FirstDeg) == false) {
                                                                                                connectTemp.StudentFD(progID,
                                                                                                    NewRegNo,
                                                                                                    FormNo,
                                                                                                    FirstDeg,
                                                                                                    creatorid,
                                                                                                    new AsyncCallback<String>() {
                                                                                                        public void onFailure(
                                                                                                            Throwable caught) {
                                                                                                            MessageBox.alert(dbException,
                                                                                                                caught.getMessage());
                                                                                                        }

                                                                                                        public void onSuccess(
                                                                                                            String arg0) {
                                                                                                        }
                                                                                                    });
                                                                                            }
                                                                                        }

                                                                                        if (summaryObj.pg.firstDegCodeCB.isVisible()) {
                                                                                            String PDeg =
                                                                                                summaryObj.StudentFD(summaryObj.pg.firstDegCodeCB);

                                                                                            if (valid.nullValidator(
                                                                                                        PDeg) == false) {
                                                                                                connectTemp.StudentFD(progID,
                                                                                                    NewRegNo,
                                                                                                    FormNo,
                                                                                                    PDeg,
                                                                                                    creatorid,
                                                                                                    new AsyncCallback<String>() {
                                                                                                        public void onFailure(
                                                                                                            Throwable caught) {
                                                                                                            MessageBox.alert(dbException,
                                                                                                                caught.getMessage());
                                                                                                        }

                                                                                                        public void onSuccess(
                                                                                                            String arg0) {
                                                                                                        }
                                                                                                    });
                                                                                            }
                                                                                        }

                                                                                        MessageBox.show(new MessageBoxConfig() {

                                                                                                {
                                                                                                    setMsg(msg.alert_successfulentry() +
                                                                                                        " " +
                                                                                                        "Your reg no is" +
                                                                                                        " " +
                                                                                                        NewRegNo);

                                                                                                    setButtons(MessageBox.OK);
                                                                                                    setCallback(new MessageBox.PromptCallback() {
                                                                                                            public void execute(
                                                                                                                String btnID,
                                                                                                                String text) {
                                                                                                                try {
                                                                                                                    mainVerticalPanel.clear();

                                                                                                                    onModuleLoad1();

                                                                                                                    vp.add(summaryObj.mainScroll);
                                                                                                                    vp.add(submitButton);
                                                                                                                } catch (Exception e) {
                                                                                                                }
                                                                                                            }
                                                                                                        });
                                                                                                }
                                                                                            });
                                                                                    }
                                                                                }
                                                                            });
                                                                    			} else {
                                                                    				MessageBox.alert(msg.alreadyRegAlert(
                                                                    						Program,
                                                                    						branchCB.branchCombo.getRawValue()));
                                                                    			}
                                                                }
                                                            });
                                                    }
                                                }
                                            });
                                    } else {
                                        if (checkProgReg == true) {
                                            MessageBox.alert(duplicateRegNo);
                                        }

                                        //if(checkForm==true)
                                        //	MessageBox.alert(duplicateForm);
                                        //if(checkProgReg==true && checkForm==true)
                                        //	MessageBox.alert(duplicateForm+ "&" +duplicateRegNo);
                                    }
                                }
                            });
                    } else {
                        MessageBox.alert(errorMsg, correctEntriesMsg);
                    }
                }
            }); 

        return mainVerticalPanel;
       
    }

    void furhterExecution(){

        FlexTable flex3 = new FlexTable();
        FlexTable flex4 = new FlexTable();

        Label selectEntityName = new Label(cons.entityName());
        Label selectEntity = new Label(cons.entityType());

        final Label entityLabel = new Label();
        final Label entityNameLabel = new Label();
        final CheckBox enrollCheck = new CheckBox();
        enrollCheck.setChecked(false);

        final Label enrollmentNumberLabel = new Label(cons.enrollmentNumber());
        final TextBox enrollmentNumber = new TextBox();
        enrollmentNumberLabel.setVisible(false);
        enrollmentNumber.setMaxLength(10);
        enrollmentNumber.setVisible(false);


        enrollCheck.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent arg0) {
                    if (enrollCheck.isChecked()) {
                        enrollmentNumberLabel.setVisible(true);
                        enrollmentNumber.setEnabled(true);
                        enrollmentNumber.setVisible(true);
                    } else {
                        enrollmentNumberLabel.setVisible(false);
                        enrollmentNumber.setValue("");
                        enrollmentNumber.setVisible(false);
                    }
                }
            });

        if (entityViewIs == true) {

        	//default view flextable
            flex4.setCellSpacing(5);
            flex4.setWidget(0, 0, selectEntity);
            flex4.setWidget(0, 1, entityLabel);
            flex4.setWidget(0, 2, selectEntityName);
            flex4.setWidget(0, 3, entityNameLabel);
            flex4.setWidget(1, 0, new Label(cons.label_programname()));
            flex4.setWidget(1, 1, programCB.progCombo);
            flex4.setWidget(1, 2, new Label(cons.label_branchname()));
            flex4.setWidget(1, 3, branchCB.branchCombo);
            flex4.setWidget(2, 0, new Label(cons.enrolledStudent()));
            flex4.setWidget(2, 1, enrollCheck);
            flex4.setWidget(2, 2, enrollmentNumberLabel);
            flex4.setWidget(2, 3, enrollmentNumber);
            flex4.setWidget(4, 2, okButton);

        } else {

        	flex3.setCellSpacing(5);
            flex3.setWidget(0, 0, selectEntity);
            flex3.setWidget(0, 1, entityTypeCB.entityDescCB);
            flex3.setWidget(0, 2, selectEntityName);
            flex3.setWidget(0, 3, entityNameCB.entityCombo);
            flex3.setWidget(1, 0, new Label(cons.label_programname()));
            flex3.setWidget(1, 1, programCB.progCombo);
            flex3.setWidget(1, 2, new Label(cons.label_branchname()));
            flex3.setWidget(1, 3, branchCB.branchCombo);
            flex3.setWidget(2, 0, new Label(cons.enrolledStudent()));
            flex3.setWidget(2, 1, enrollCheck);
            flex3.setWidget(2, 2, enrollmentNumberLabel);
            flex3.setWidget(2, 3, enrollmentNumber);
            flex3.setWidget(4, 2, okButton);
        }

        mainForm.setLabelAlign(Position.TOP);
        mainForm.setTitle(cons.summarySheet());
        mainForm.setPaddings(5);
        mainForm.setWidth("100%");
        mainForm.setFrame(true);


        
        if (entityViewIs == true) {
        	
        	/*
             * Method to get the Entity Type for the Default View.
             */
        	connectTemp.defaultEntityType(creatorid,
                new AsyncCallback<CM_entityInfoGetter[]>() {
                    public void onFailure(Throwable caught) {
                        MessageBox.alert(dbException, caught.getMessage());
                    }

                    public void onSuccess(CM_entityInfoGetter[] arg0) {
                        entity_DefaultType = arg0[0].getEntity_type();
                        entity_DefaultDescription = arg0[0].getEntity_description();
                        entityLabel.setText(entity_DefaultDescription);
                       
                        /*
                         * Method to get the Entity Name for the Default View.
                         */
                        connectTemp.defaultEntityName(creatorid, entity_DefaultType, new AsyncCallback<CM_entityInfoGetter[]>() {

                                    public void onFailure(Throwable caught) {
                                        MessageBox.alert(dbException,
                                            caught.getMessage());
                                    }

                                    public void onSuccess(
                                    		          CM_entityInfoGetter[] arg0) {
                                  
                                        entity_name = arg0[0].getEntity_name();
                                        entity_id = arg0[0].getEntity_id();
                                        entityNameLabel.setText(entity_name);
                                  
                                        connectTemp.getProgrammeOff(entity_name,
                                               new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                                   public void onFailure(
                                                       Throwable caught) {
                                                       MessageBox.alert(dbException,
                                                           caught.getMessage());
                                                   }

                                                   public void onSuccess(
                                                       CM_ProgramInfoGetter[] arg0) {
                                                       RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                                   new StringFieldDef("Prog"),
                                                                   new StringFieldDef("ProgCode")
                                                               });

                                                       if (arg0.length > 0) {
                                                           final String[][] object2;

                                                           object2 = new String[arg0.length][2];

                                                           String str = null;

                                                           try {
                                                               for (int i = 0;
                                                                       i < arg0.length;
                                                                       i++) {
                                                                   for (int k = 0;
                                                                           k < 2;
                                                                           k++) {
                                                                       if (k == 0) {
                                                                           str = arg0[i].getprogram_name();
                                                                       } else if (k == 1) {
                                                                           str = arg0[i].getprogram_id();
                                                                       }

                                                                       object2[i][k] = str;
                                                                   }
                                                               }
                                                           } catch (Exception e2) {
                                                               System.out.println(
                                                                   "e2     " + e2);
                                                           }

                                                           Object[][] data = object2;

                                                           MemoryProxy proxy = new MemoryProxy(data);

                                                           ArrayReader reader = new ArrayReader(recordDef);
                                                           Store store = new Store(proxy,
                                                                   reader);
                                                           store.load();

                                                           programCB.progCombo.setStore(store);
                                                           programCB.progCombo.enable();
                              
                                                       } else {
                                                           MessageBox.alert(msg.emptyComboBox(
                                                                   "programmes",
                                                                   entity_name));
                                                       }

                                                     }
                                               });

                                      }
                                });
                        }
                   });

        	            
        } else { 
            entityTypeCB.entityDescCB.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox comboBox, Record record,
                        int index) {
                        entityNameCB.entityCombo.clearValue();
                        entityNameCB.entityCombo.disable();

                        programCB.progCombo.clearValue();
                        programCB.progCombo.disable();

                        branchCB.branchCombo.clearValue();
                        branchCB.branchCombo.disable();
                        
                        enrollmentNumberLabel.setVisible(false);
                        enrollmentNumber.setVisible(false);

                        vp.clear();

                        String entity_type = entityTypeCB.entityDescCB.getValue();

                        /*
                         * Method to Populate Entity ComboBox with Entity Name.
                         */
                        connectTemp.Entity_Name(creatorid, entity_type,
                            new AsyncCallback<CM_entityInfoGetter[]>() {
                                public void onFailure(Throwable caught) {
                                    MessageBox.alert(dbException,
                                        caught.getMessage());
                                }

                                public void onSuccess(
                                    CM_entityInfoGetter[] arg0) {
                                   
                                	RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                new StringFieldDef("EntityName"),
                                                new StringFieldDef("Entitycode")
                                            });
                                    final String[][] object2;

                                    object2 = new String[arg0.length][2];

                                    String str = null;

                                    try {
                                        for (int i = 0; i < arg0.length; i++) {
                                            for (int k = 0; k < 2; k++) {
                                                if (k == 0) {
                                                    str = arg0[i].getEntity_name();
                                                } else if (k == 1) {
                                                    str = arg0[i].getEntity_id();
                                                }

                                                object2[i][k] = str;
                                            }
                                        }
                                    } catch (Exception e2) {
                                        System.out.println("e2     " + e2);
                                    }

                                    Object[][] data = object2;

                                    MemoryProxy proxy = new MemoryProxy(data);

                                    ArrayReader reader = new ArrayReader(recordDef);
                                    Store store = new Store(proxy, reader);
                                    store.load();

                                    entityNameCB.entityCombo.setStore(store);
                                    entityNameCB.entityCombo.enable();
                                }
                            });
                    }
                });

            entityNameCB.entityCombo.addListener(new ComboBoxListenerAdapter() {
                    public void onSelect(ComboBox comboBox, Record record,
                        int index) {
                        
                    	programCB.progCombo.clearValue();
                        programCB.progCombo.disable();

                        branchCB.branchCombo.clearValue();
                        branchCB.branchCombo.disable();
                        
                        enrollmentNumberLabel.setVisible(false);
                        enrollmentNumber.setVisible(false);
                        vp.clear();

                        if (index >= 0) {
                            entity_name = entityNameCB.entityCombo.getRawValue();
                            entity_id = entityNameCB.entityCombo.getValue();
                         
                            /*
                             * Method to Populate Program ComboBox with Program Name.
                             */
                            connectTemp.getProgrammeOff(entity_name,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable caught) {
                                        MessageBox.alert(dbException,
                                            caught.getMessage());
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] arg0) {
                                        RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                    new StringFieldDef("Prog"),
                                                    new StringFieldDef("ProgCode")
                                                });

                                        if (arg0.length > 0) {
                                            final String[][] object2;

                                            object2 = new String[arg0.length][2];

                                            String str = null;

                                            try {
                                                for (int i = 0;
                                                        i < arg0.length; i++) {
                                                    for (int k = 0; k < 2;
                                                            k++) {
                                                        if (k == 0) {
                                                            str = arg0[i].getprogram_name();
                                                        } else if (k == 1) {
                                                            str = arg0[i].getprogram_id();
                                                        }

                                                        object2[i][k] = str;
                                                    }
                                                }
                                            } catch (Exception e2) {
                                                System.out.println("e2     " +
                                                    e2);
                                            }

                                            Object[][] data = object2;

                                            MemoryProxy proxy = new MemoryProxy(data);

                                            ArrayReader reader = new ArrayReader(recordDef);
                                            Store store = new Store(proxy,
                                                    reader);
                                            store.load();

                                            programCB.progCombo.setStore(store);
                                            programCB.progCombo.enable();
                                        } else {
                                            MessageBox.alert(msg.emptyComboBox(
                                                    "programmes", entity_name));
                                        }
                                    }
                                });
                        }
                    }
                });

        } 
      
            programCB.progCombo.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    enrollmentNumberLabel.setVisible(false);
                    enrollmentNumber.setVisible(false);
                	
                	branchCB.branchCombo.clearValue();
                    branchCB.branchCombo.disable();
                    vp.clear();

                    Program = programCB.progCombo.getRawValue();
                    progID = programCB.progCombo.getValue();
                   
                    /*
                     * Method to Populate Branch ComboBox with Branch Name.
                     */
                    connectTemp.getBranch(progID, entity_id,
                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                            public void onFailure(Throwable caught) {
                                MessageBox.alert(dbException,
                                    caught.getMessage());
                            }

                            public void onSuccess(CM_ProgramInfoGetter[] arg0) {
                                RecordDef recordDef = new RecordDef(new FieldDef[] {
                                            new StringFieldDef("BranchName"),
                                            new StringFieldDef("BranchCode")
                                        });
                                final String[][] object2;

                                object2 = new String[arg0.length][2];

                                String str = null;

                                try {
                                    for (int i = 0; i < arg0.length; i++) {
                                        for (int k = 0; k < 2; k++) {
                                            if (k == 0) {
                                                str = arg0[i].getBranch_name();
                                            } else if (k == 1) {
                                                str = arg0[i].getBranch();
                                            }

                                            object2[i][k] = str;
                                        }
                                    }
                                } catch (Exception e2) {
                                    System.out.println("e2     " + e2);
                                }

                                Object[][] data = object2;

                                MemoryProxy proxy = new MemoryProxy(data);

                                ArrayReader reader = new ArrayReader(recordDef);
                                Store store = new Store(proxy, reader);
                                store.load();

                                branchCB.branchCombo.setStore(store);
                                branchCB.branchCombo.enable();
                               
                            }
                        });
                }
            });
          
        branchCB.branchCombo.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                	enrollmentNumberLabel.setVisible(false);
                    enrollmentNumber.setVisible(false);
                	
                    branchName = branchCB.branchCombo.getRawValue();
                    branchId = branchCB.branchCombo.getValue();

                    vp.clear();
                }
            });

       

        if (entityViewIs == true) {
        	mainForm.add(flex4);
        } else {
            mainForm.add(flex3);
        }

        
        mainForm.add(vp);
        mainVerticalPanel.add(mainForm);

        /*
         * button for generating second part of Application form.
         */
        okButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    int check = 0;

                    if (entityViewIs == false) {
                    
                        if (entityTypeCB.entityDescCB.getValue() == null) {
                            check++;

                            if (entityTypeCB.entityDescCB.isDisabled() == false) {
                                summaryObj.markIV(entityTypeCB.entityDescCB);
                            }
                        }

                        if (entityNameCB.entityCombo.getValue() == null) {
                            check++;

                            if (entityNameCB.entityCombo.isDisabled() == false) {
                                summaryObj.markIV(entityNameCB.entityCombo);
                            }
                        }
                    }//end of if

                        if (programCB.progCombo.getValue() == null) {
                            check++;

                            if (programCB.progCombo.isDisabled() == false) {
                                summaryObj.markIV(programCB.progCombo);
                            }
                        }

                        if (branchCB.branchCombo.getValue() == null) {
                            check++;

                            if (branchCB.branchCombo.isDisabled() == false) {
                                summaryObj.markIV(branchCB.branchCombo);
                            }
                        }




                    if (check == 0) {
                        vp.clear();

                        SummarySheet d1 = new SummarySheet();
                        summaryObj = d1;
                       
                        /*
                         * Method to check set components
                         */
                        connectTemp.CheckComponents(entity_id, progID,
                            branchId,
                            new AsyncCallback<Boolean>() {
                                public void onFailure(Throwable caught) {
                                    MessageBox.alert(dbException,
                                        caught.getMessage());
                                }

                                public void onSuccess(Boolean result) {
                                    if (result == false) {
                                        MessageBox.alert(errorMsg,
                                            msg.noComponentSpecified(Program,
                                                branchName));
                                    } else {
                                    	/*
                                         * Method to get new Registration Number for a new Application.
                                         */
                                    	connectTemp.getProgRegNumber(creatorid,
                                            entity_id, progID, branchId,
                                            new AsyncCallback<String[]>() {
                                                public void onFailure(
                                                    Throwable caught) {
                                                    MessageBox.alert(dbException,
                                                        caught.getMessage());
                                                }

                                                public void onSuccess(
                                                    String[] result) {
                                                    if (result == null) {
                                                        MessageBox.alert(msg.initializeRegNo());
                                                    } else {
                                                        ProgRegValue = result[0];
                                                        NewRegNo = result[1] +
                                                            entity_id.substring(6,
                                                                8) + "" +
                                                            progID.substring(5,
                                                                7) + "" +
                                                            branchId.substring(1,
                                                                3) + "" +
                                                            result[0];
                                                       

                                                        if (enrollCheck.isChecked()) {
                                                        	enrollmentNumber.setEnabled(false);
                                                            if (enrollmentNumber.getValue() == "") {
                                                                MessageBox.alert(msg.enterEnrollNumber());
                                                            } else {
                                                                // MessageBox.alert("create a rpc and get all details according to enrollment number");
                                                                final String enrollNumber =
                                                                    enrollmentNumber.getValue();

                                                                /*
                                                                 * Method to get the Personal information of the enrolled student.
                                                                 */
                                                                connectTemp.getEnrolledStudentPersonalInfo(enrollNumber,
                                                                    new AsyncCallback<CM_StudentInfoGetter>() {
                                                                        public void onFailure(
                                                                            Throwable caught) {
                                                                            MessageBox.alert(dbException,
                                                                                caught.getMessage());
                                                                        }

                                                                        public void onSuccess(
                                                                            CM_StudentInfoGetter arg0) {
                                                                            
                                                                            if(arg0==null){
                                                                            	MessageBox.alert("Please Enter Correct Enrollment Number");
                                                                            	enrollmentNumber.setEnabled(true);
                                                                            }
                                                                            else{
                                                                            	summaryObj.generateSummarySheet(progID,
                                                                            			branchId,
                                                                            			entity_id);

                                                                            	summaryObj.fname.setValue(arg0.getfirst_name());
                                                                            	summaryObj.mname.setValue(arg0.getmiddle_name());
                                                                            	summaryObj.lname.setValue(arg0.getlast_name());
                                                                            	summaryObj.fname1.setValue(arg0.getfather_Fname());
                                                                            	summaryObj.mname1.setValue(arg0.getfather_Mname());
                                                                            	summaryObj.lname1.setValue(arg0.getfather_Lname());
                                                                            	summaryObj.fname2.setValue(arg0.getmother_Fname());
                                                                            	summaryObj.mname2.setValue(arg0.getmother_Mname());
                                                                            	summaryObj.lname2.setValue(arg0.getmother_Lname());
                                                                            	summaryObj.newGender.setValue(arg0.getgender());
                                                                            	summaryObj.dateofbirthDateField.setValue(arg0.getdate_of_birth());

                                                                            	/*
                                                                                 * Method to get the Category of the enrolled Student.
                                                                                 */
                                                                            	connectTemp.getEnrolledStuCategory( arg0.getCategory_code(), new AsyncCallback<CM_StudentInfoGetter[]>() {
                                                                                        public void onFailure(
                                                                                            Throwable caught) {
                                                                                            MessageBox.alert(dbException,
                                                                                                caught.getMessage());
                                                                                        }

                                                                                        public void onSuccess(
                                                                                            CM_StudentInfoGetter[] arg0) {
                                                                                        	//arg
                                                                                        	summaryObj.newCat.setRawValue(arg0[0].getcategory());
                                                                                        	summaryObj.newCat.setValue(arg0[0].getCategory_code());
                                                                                        }
                                                                                    });


                                                                            		summaryObj.fname.setDisabled(true);
                                                                            		summaryObj.mname.setDisabled(true);
                                                                            		summaryObj.lname.setDisabled(true);
                                                                            		summaryObj.fname1.setDisabled(true);
                                                                            		summaryObj.mname1.setDisabled(true);
                                                                            		summaryObj.lname1.setDisabled(true);
                                                                            		summaryObj.fname2.setDisabled(true);
                                                                            		summaryObj.mname2.setDisabled(true);
                                                                            		summaryObj.lname2.setDisabled(true);
                                                                            		summaryObj.newGender.setDisabled(true);
                                                                            		summaryObj.newCat.setDisabled(true);
                                                                            		summaryObj.dateofbirthDateField.setDisabled(true);

                                                                            		/*
                                                                                     * Method to get the Address Information of the enrolled Student.
                                                                                     */
                                                                            		connectTemp.getEnrolledStudentAddressInfo(arg0.getUser_id(), new AsyncCallback<CM_entityInfoGetter[]>(){

                                                                            				public void onFailure(Throwable  caught) {
                                                                            					MessageBox.alert(dbException,caught.getMessage());
                                                                            				}
                                                                            				public void onSuccess(CM_entityInfoGetter[] arg0) {
                                                                            					summaryObj.street1Text.setValue(arg0[0].getAddress_line1());
                                                                            					summaryObj.street2Text.setValue(arg0[0].getAddress_line2());
                                                                            					summaryObj.cityText.setValue(arg0[0].getcity());
                                                                            					summaryObj.pinNumber1.setValue(arg0[0].getpincode());
                                                                            					summaryObj.newState.setValue(arg0[0].getstate());
                                                                            				}
                                                                            		});

                                                                            		vp.add(summaryObj.mainScroll);
                                                                            		vp.add(submitButton);
                                                                            }//end of else
                                                                        }
                                                                    });
                                                            }
                                                        } else {
                                                           
                                                            summaryObj.generateSummarySheet(progID,
                                                                branchId,
                                                                entity_id);

                                                            vp.add(summaryObj.mainScroll);
                                                            vp.add(submitButton);
                                                        }

                                                        //
                                                    }
                                                }
                                            });
                                    }
                                }
                            });
                    } else {
                        MessageBox.alert(errorMsg, msg.fieldsReqd());
                    }
                }
            }); 
        
    } 

}


class SummarySheet {
    String dbException;
    String errorMsg;
    String confirm;
    String alertSave;
    String correctEntriesMsg;
    messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    RadioButton radioSW = new RadioButton("S", cons.applicable(), true);
    RadioButton radioNSW = new RadioButton("S", cons.not_Applicable(), true);
    String fName = cons.firstName();
    String mName = cons.middleName();
    String lName = cons.lastName();
    String maxMarks = cons.label_maxmarks();
    String marksObtd = cons.label_marksobtd();
    String percent = cons.percent();
    String chooseDeg = cons.chooseDegree();
    String chooseBoard = cons.chooseBoard();
    String score = cons.score();
    FieldSet fieldSet1 = new FieldSet(cons.personal_details());
    FieldSet fieldSet2 = new FieldSet(cons.addCorrespondence());
    FieldSet fieldSet3 = new FieldSet(cons.if_staffWard());
    FieldSet fieldSet4 = new FieldSet(cons.acad_perform());
    FieldSet fieldSet6 = new FieldSet(cons.test_options());
    FieldSet fieldSet7 = new FieldSet(cons.formNo_regNo());
    Validator valid = new Validator();
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
    int count = 0;
    int count1 = 0;
    int count2 = 0;
    int x1;
    int b = 0;
    int c = 0;
    int boardCount = 0;
    int spWtCount = 0;
    int newc = 0;
    int m1 = 0;
    int papercodeGroupCount = 0;
    ComboBox[] groupCB;
    NumberField[] t1 = new NumberField[c];
    CM_ComboBoxes[] ocb1 = new CM_ComboBoxes[boardCount];
    CheckBox[] cb1 = new CheckBox[spWtCount];
    FlexTable listTable = new FlexTable();
    VerticalPanel listVPanel = new VerticalPanel();
    FormPanel mainForm = new FormPanel();
    String Course;
    String[] complabels;
    int k1 = 0;
    String[][] x;
    String[][] newcArr;
    String[][] newS;
    boolean flag = false;

    //TextField appNo = new TextField();
    //TextField regNo = new TextField();
    TextField fname = new TextField();
    TextField mname = new TextField();
    TextField lname = new TextField();
    TextField fname1 = new TextField();
    TextField mname1 = new TextField();
    TextField lname1 = new TextField();
    TextField fname2 = new TextField();
    TextField mname2 = new TextField();
    TextField lname2 = new TextField();
    TextField street1Text = new TextField();
    TextField street2Text = new TextField();
    TextField cityText = new TextField();
    NumberField pinNumber1 = new NumberField();
    ComboBox newGender = new ComboBox();
    ComboBox newCat = new ComboBox();
    ComboBox newState = new ComboBox();
    //DateField dateofbirthDateField = new DateField("", "", 190);
    DateItem dateofbirthDateField = new DateItem();
    VerticalPanel mainScroll = new VerticalPanel();
    final VerticalPanel vmain = new VerticalPanel();
    final VerticalPanel vpRadioSW = new VerticalPanel();
    HorizontalPanel hpCall = new HorizontalPanel();
    HorizontalPanel hpPaper = new HorizontalPanel();
    final CM_ComboBoxes ug = new CM_ComboBoxes();
    final CM_ComboBoxes pg = new CM_ComboBoxes();
    String[][] object2;
    int checkCos = 0;

    /*
     * Method to Generate a Second portion of the Application form
     */
    @SuppressWarnings({"deprecation"
    })
    void generateSummarySheet(final String progID, final String branchID,
        final String entity_id) {
        dbException = cons.dbError();
        errorMsg = msg.error();
        confirm = msg.confirm();
        alertSave = msg.alert_confirmentries();
        correctEntriesMsg = msg.checkFields();

        mainScroll.clear();
        hpPaper.setVisible(false);

        connectTemp.papercodeGroupCount(progID,
            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert(dbException, caught.getMessage());
                }

                public void onSuccess(CM_ProgramInfoGetter[] result) {
                    papercodeGroupCount = result.length;

                    if (papercodeGroupCount == 0) {
                        hpPaper.setVisible(false);
                    } else {
                        groupCB = new ComboBox[papercodeGroupCount];

                        for (int i = 0; i < papercodeGroupCount; i++) {
                            String group = result[i].getGrouping();

                            final ComboBox paperCodeCB = new ComboBox();

                            paperCodeCB.setName(result[i].getGrouping());
                            paperCodeCB.setForceSelection(true);
                            paperCodeCB.setMinChars(1);
                            paperCodeCB.setDisplayField("paperDescription");
                            paperCodeCB.setValueField("paperCode");
                            paperCodeCB.setMode(ComboBox.LOCAL);
                            paperCodeCB.setTriggerAction(ComboBox.ALL);
                            paperCodeCB.setEmptyText("Select");
                            paperCodeCB.setLoadingText("Searching...");
                            paperCodeCB.setTypeAhead(true);
                            paperCodeCB.setSelectOnFocus(true);
                            paperCodeCB.setWidth(130);
                            paperCodeCB.setHideTrigger(false);
                            paperCodeCB.setReadOnly(true);

                            connectTemp.DistinctPaperGroupingCount(progID,
                                group,
                                new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                    public void onFailure(Throwable caught) {
                                        MessageBox.alert(dbException,
                                            caught.getMessage());
                                    }

                                    public void onSuccess(
                                        CM_ProgramInfoGetter[] arg0) {
                                        if (arg0.length > 0) {
                                            RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                        new StringFieldDef("paperDescription"),
                                                        new StringFieldDef("paperCode")
                                                    });

                                            object2 = new String[arg0.length][2];

                                            String str = null;

                                            try {
                                                for (int i = 0;
                                                        i < arg0.length; i++) {
                                                    for (int k = 0; k < 2;
                                                            k++) {
                                                        if (k == 0) {
                                                            str = arg0[i].getPaper_description();
                                                        } else if (k == 1) {
                                                            str = arg0[i].getPaper_code();
                                                        }

                                                        object2[i][k] = str;
                                                    }
                                                }
                                            } catch (Exception e2) {
                                                System.out.println("e2     " +
                                                    e2);
                                            }

                                            Object[][] data = object2;

                                            MemoryProxy proxy = new MemoryProxy(data);

                                            ArrayReader reader = new ArrayReader(recordDef);
                                            Store store = new Store(proxy,
                                                    reader);
                                            store.load();

                                            paperCodeCB.setStore(store);
                                        } else {
                                            paperCodeCB.disable();
                                        }
                                    }
                                });
                            groupCB[i] = paperCodeCB;

                            hpPaper.setVisible(true);
                            hpPaper.add(new Label(cons.group() + " " +
                                    groupCB[i].getName() + "*"));
                            hpPaper.add(groupCB[i]);
                        }
                    }
                }
            });

        OA_ComboBoxes gender_obj1 = new OA_ComboBoxes();
        CM_ComboBoxes category_obj1 = new CM_ComboBoxes();
        OA_ComboBoxes state_obj1 = new OA_ComboBoxes();

        NumberField pinNumber = new NumberField();

        Label nameLabel = new Label(cons.name());
        Label fnameLabel = new Label(fName);
        Label mnameLabel = new Label(mName);
        Label lnameLabel = new Label(lName);

        Label fathernameLabel = new Label(cons.fatherName());
        Label fnameLabel1 = new Label(fName);
        Label mnameLabel1 = new Label(mName);
        Label lnameLabel1 = new Label(lName);

        Label mothernameLabel = new Label(cons.motherName());
        Label fnameLabel2 = new Label(fName);
        Label mnameLabel2 = new Label(mName);
        Label lnameLabel2 = new Label(lName);

        Label streetlabel1 = new Label(cons.line1());
        Label streetlabel2 = new Label(cons.line2());
        Label citylabel = new Label(cons.city());
        Label statelabel = new Label(cons.state());
        Label pinlabel = new Label(cons.pincode());

        FlexTable flex1 = new FlexTable();
        FlexTable flex2 = new FlexTable();
        //FlexTable flex3 = new FlexTable();

        //flex3.setWidget(2, 0,new Label(cons.formNo()));
        //flex3.setWidget(2, 1,appNo);
        //flex3.setWidget(2, 0,new Label(cons.regNo()));
        //flex3.setWidget(2, 1,regNo);
        gender_obj1.onModuleLoad();
        category_obj1.onModuleLoad();
        state_obj1.onModuleLoad();

        newGender = gender_obj1.genderCB;
        newCat = category_obj1.categoryCB;
        newState = state_obj1.statesCB;

        //appNo.setMaxLength(8);
        //appNo.setMinLength(8);
        //regNo.setMaxLength(8);
        //regNo.setMinLength(8);
        fieldSet1.setFrame(true);

        fieldSet2.setFrame(true);

        fieldSet3.setFrame(true);

        fieldSet4.setFrame(true);

        fieldSet6.setFrame(true);

        fieldSet7.setFrame(true);

        hpPaper.setSpacing(10);

        fname.setWidth(190);
        mname.setWidth(190);
        lname.setWidth(190);
        // appNo.setWidth(130);
        // regNo.setWidth(130);
        fname.setAllowBlank(false);
        fname1.setAllowBlank(false);
        fname2.setAllowBlank(false);
        // appNo.setAllowBlank(false);
        //regNo.setAllowBlank(false);
        newGender.setAllowBlank(false);
        newCat.setAllowBlank(false);
        //dateofbirthDateField.setAllowBlank(false);
        
        cityText.setWidth(190);
        street1Text.setWidth(190);
        street2Text.setWidth(190);
        pinNumber.setWidth(190);
        pinNumber.setAllowDecimals(false);
        pinNumber.setAllowNegative(false);
        pinNumber.setMaxLength(6);
        pinNumber.setMinLength(6);

        cityText.setAllowBlank(false);
        street1Text.setAllowBlank(false);
        street2Text.setWidth(190);
        pinNumber.setAllowBlank(false);
        newState.setAllowBlank(false);
        
        DynamicForm container=new DynamicForm();
        dateofbirthDateField.setTitle("");
        dateofbirthDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
        dateofbirthDateField.setUseTextField(true);
        dateofbirthDateField.setValidateOnChange(true);
        dateofbirthDateField.setRequired(true);
        dateofbirthDateField.setEnforceDate(true);
        dateofbirthDateField.setShowErrorIcon(true);
        dateofbirthDateField.setInvalidDateStringMessage("Invalid Date");
        container.setItems(dateofbirthDateField);
        
        pinNumber1 = pinNumber;

        flex1.setCellSpacing(5);
        flex1.setWidget(0, 0, nameLabel);
        flex1.setWidget(0, 2, fnameLabel);
        flex1.setWidget(0, 4, mnameLabel);
        flex1.setWidget(0, 6, lnameLabel);
        flex1.setWidget(1, 2, fname);
        flex1.setWidget(1, 4, mname);
        flex1.setWidget(1, 6, lname);
        flex1.setWidget(2, 2, new Label(cons.gender()));
        flex1.setWidget(3, 2, newGender);
        flex1.setWidget(2, 4, new Label(cons.dob()));
        flex1.setWidget(3, 4, container);
        flex1.setWidget(2, 6, new Label(cons.category()));
        flex1.setWidget(3, 6, newCat);
        flex1.setWidget(4, 0, fathernameLabel);
        flex1.setWidget(4, 2, fnameLabel1);
        flex1.setWidget(4, 4, mnameLabel1);
        flex1.setWidget(4, 6, lnameLabel1);
        flex1.setWidget(5, 2, fname1);
        flex1.setWidget(5, 4, mname1);
        flex1.setWidget(5, 6, lname1);
        flex1.setWidget(6, 0, mothernameLabel);
        flex1.setWidget(6, 2, fnameLabel2);
        flex1.setWidget(6, 4, mnameLabel2);
        flex1.setWidget(6, 6, lnameLabel2);
        flex1.setWidget(7, 2, fname2);
        flex1.setWidget(7, 4, mname2);
        flex1.setWidget(7, 6, lname2);
        fieldSet1.add(flex1);

        flex2.setCellSpacing(5);
        flex2.setWidget(0, 0, streetlabel1);
        flex2.setWidget(0, 1, street1Text);
        flex2.setWidget(0, 2, streetlabel2);
        flex2.setWidget(0, 3, street2Text);
        flex2.setWidget(1, 0, citylabel);
        flex2.setWidget(1, 1, cityText);
        flex2.setWidget(1, 2, statelabel);
        flex2.setWidget(1, 3, newState);
        flex2.setWidget(2, 0, pinlabel);
        flex2.setWidget(2, 1, pinNumber1);
        fieldSet2.add(flex2);

        HorizontalPanel hp1 = new HorizontalPanel();
        hp1.setSpacing(20);

        HorizontalPanel hp2 = new HorizontalPanel();
        hp2.setSpacing(10);

        radioNSW.setChecked(true);
        hp2.add(radioSW);
        hp2.add(radioNSW);

        vpRadioSW.add(hp2);
        vpRadioSW.setHeight("60px");

        fieldSet3.add(vpRadioSW);

        fieldSet6.add(hpPaper);

        //fieldSet7.add(flex3);
        final VerticalPanel vp = new VerticalPanel();
        vp.setHeight("100%");
        fieldSet4.add(vp);

        FlexTable newFlex = display(progID, branchID);

        vp.add(newFlex);

        vmain.setSpacing(10);
        //vmain.add(fieldSet7);
        vmain.add(fieldSet1);
        vmain.add(fieldSet2);
        vmain.add(fieldSet3);
        vmain.add(fieldSet4);
        vmain.add(fieldSet6);

        mainScroll.add(vmain);

        newCat.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    connectTemp.getcos_value(progID, branchID, entity_id,
                        newCat.getValue() + "%",
                        new AsyncCallback<CM_ProgramInfoGetter[]>() {
                            public void onFailure(Throwable caught) {
                                MessageBox.alert(dbException,
                                    caught.getMessage());
                            }

                            public void onSuccess(CM_ProgramInfoGetter[] arg0) {
                                checkCos = arg0.length;

                                if (arg0.length == 0) {
                                    MessageBox.show(new MessageBoxConfig() {

                                            {
                                                setMsg(msg.noCOSspecified());

                                                setButtons(MessageBox.OK);
                                                setCallback(new MessageBox.PromptCallback() {
                                                        public void execute(
                                                            String btnID,
                                                            String text) {
                                                            try {
                                                                newCat.clearValue();
                                                            } catch (Exception e) {
                                                            }
                                                        }
                                                    });
                                            }
                                        });
                                }
                            }
                        });
                }
            });
    }

    /*
     * Method to get the Board Information.
     */
    public String[] returnBoard() {
        final String[] BoardList = new String[c];

        for (int k = 0; k < boardCount; k++) {
            for (int i = 0; i < c; i++) {
                if (listTable.getText(i + 1, 3).equals(cons.chooseBoard())) {
                    BoardList[i] = ocb1[k].boardCB.getValue();

                    k++;
                }
            }
        }

        return BoardList;
    }

    /*
     * Method to get the special Weightage Information.
     */
    @SuppressWarnings("deprecation")
    public String[] returnSpwtCB() {
        final String[] checklist = new String[c];

        for (int k = 0; k < boardCount; k++) {
            for (int i = 0; i < c; i++) {
                if (cb1[k].isChecked() == true) {
                    checklist[i] = cb1[k].getName();

                    k++;
                }
            }
        }

        return checklist;
    }

    /*
     * Method to get Academic Details.
     */
    public String[][] returnString() {
        final String[][] data1 = new String[c][5];

        final String[] AppCoaList = new String[c];

        for (int k = 1; k < (c + 1); k++) {
            AppCoaList[k - 1] = t1[k - 1].getRawValue();

            final int s1 = k - 1;

            final String[] data = new String[5];

            String Component = listTable.getText(k, 0);

            for (int i = 0; i < newS.length; i++) {
                if (newS[i][0].equals(Component)) {
                    Component = newS[i][5];
                }
            }

            final String Component1 = listTable.getText(k, 1);

            data[0] = Component;

            if (Component1.equals("(" + percent + ")")) {
                data[1] = AppCoaList[s1];
            } else {
                data[1] = "0";
            }

            if (Component1.equals("(" + marksObtd + ")") ||
                    Component1.equals("(Marks)")) {
                data[2] = AppCoaList[s1];
            } else {
                data[2] = "0";
            }

            if (Component1.equals("(" + maxMarks + ")")) {
                data[3] = AppCoaList[s1];
            } else {
                data[3] = "0";
            }

            if (Component1.equals("(" + score + ")")) {
                data[4] = AppCoaList[s1];
            } else {
                data[4] = "0";
            }

            data1[s1] = data;
        }

        return newFunc(data1);
    }

    public String[][] newFunc(String[][] data1) {
        String[][] data11 = new String[c][5];

        data11 = data1;

        String[] newBoard = returnBoard();

        for (int i = 0; i < (c - 1); i++) {
            if (data11[i][0].equalsIgnoreCase(data11[i + 1][0])) {
                newc++;
            }
        }

        newcArr = new String[c - newc][6];

        for (int i = 0; i < (c - 1); i++) {
            if (data11[i][0].equalsIgnoreCase(data11[i + 1][0])) {
                k1++;

                newcArr[k1 - 1][0] = data1[i][0];

                newcArr[k1 - 1][5] = newBoard[i];

                for (int j1 = 1; j1 < 5; j1++) {
                    float val = Float.parseFloat(data11[i][j1]) +
                        Float.parseFloat(data11[i + 1][j1]);
                    newcArr[k1 - 1][j1] = "" + val;
                }
            }
        }

        int newk = k1 * 2;

        int k2 = k1;

        for (int i = newk; i < c; i++) {
            k2++;

            newcArr[k2 - 1][0] = data1[i][0];
            newcArr[k2 - 1][5] = newBoard[i];

            for (int j1 = 1; j1 < 5; j1++) {
                newcArr[k2 - 1][j1] = data11[i][j1];
            }
        }

        for (int i = 0; i < (c - newc); i++)
            for (int j1 = 0; j1 < 6; j1++) {
                float x = Float.parseFloat(newcArr[i][2]);
                float y = Float.parseFloat(newcArr[i][3]);

                if ((x > 0) && (y > 0)) {
                    float z = (x / y) * 100;
                    newcArr[i][1] = "" + z;
                }
            }

        return newcArr;
    }

    
    /*
     * Method to generate Academic Performance area.
     * @return FlexTable
     */
    public FlexTable display(final String progID, final String branchID) {
        Label[] headings = new Label[6];

        headings[0] = new Label(cons.component());
        headings[1] = new Label(cons.criteria());
        headings[2] = new Label(cons.value());
        headings[3] = new Label(cons.board_degree());
        headings[4] = new Label(cons.value());
        headings[5] = new Label(cons.special_weightage());

        listTable.clear();
        listTable.setBorderWidth(0);
        listTable.setCellSpacing(10);
        listTable.setWidget(0, 0, headings[0]);
        listTable.setWidget(0, 1, headings[1]);
        listTable.setWidget(0, 2, headings[2]);
        listTable.setWidget(0, 3, headings[3]);
        listTable.setWidget(0, 4, headings[4]);
        listTable.setWidget(0, 5, headings[5]);

        /*
         * Method to get the Program Component
         */
        connectTemp.getProgramComponents(progID, branchID,
            new AsyncCallback<String[][]>() {
                public void onFailure(Throwable caught) {
                    MessageBox.alert(dbException, caught.getMessage());
                }

                public void onSuccess(String[][] result) {
                    if (result.length == 0) {
                    } else {
                        count = result.length;

                        ug.FirstDegree(progID, "U");
                        pg.FirstDegree(progID, "P");

                        for (int i = 0; i < count; i++) {
                            if (result[i][3].equals("Y")) {
                                result[i][3] = chooseBoard;
                                boardCount = boardCount + 1;
                            } else {
                                result[i][3] = "";
                            }
                        }

                        for (int i = 0; i < count; i++) {
                            if (result[i][1].equals("M")) {
                                b = b + 1;
                            }
                        }

                        for (int i = 0; i < count; i++)
                            if (result[i][6].equals("Y")) {
                                result[i][6] = result[i][5];
                                spWtCount = spWtCount + 1;
                            } else {
                                result[i][6] = "";
                            }

                        x = new String[2 * b][7];

                        int bt = 0;

                        for (int i = 0; i < count; i++) {
                            if (result[i][1].equals("M")) {
                                x[bt][0] = result[i][0];
                                x[bt][1] = marksObtd;
                                x[bt + 1][0] = result[i][0];
                                x[bt + 1][1] = maxMarks;
                                x[bt][2] = result[i][2];
                                x[bt][3] = result[i][3];
                                x[bt][4] = result[i][4];
                                x[bt][5] = result[i][5];
                                x[bt][6] = result[i][6];
                                bt = bt + 2;
                            }
                        }

                        c = count + b;

                        newS = new String[c][7];

                        for (int b1 = 0; b1 < bt; b1++) {
                            newS[b1][0] = x[b1][0];
                            newS[b1][1] = x[b1][1];
                            newS[b1][2] = x[b1][2];
                            newS[b1][3] = x[b1][3];
                            newS[b1][4] = x[b1][4];
                            newS[b1][5] = x[b1][5];
                            newS[b1][6] = x[b1][6];
                        }

                        for (int j = bt; j < c; j++) {
                            for (int i = 0; i < count; i++) {
                                if (result[i][1].equals("P")) {
                                    newS[j][0] = result[i][0];
                                    newS[j][1] = percent;
                                    newS[j][2] = result[i][2];
                                    newS[j][3] = result[i][3];
                                    newS[j][4] = result[i][4];
                                    newS[j][5] = result[i][5];
                                    newS[j][6] = result[i][6];
                                    j++;
                                }

                                if (result[i][1].equals("S")) {
                                    newS[j][0] = result[i][0];
                                    newS[j][1] = score;
                                    newS[j][2] = result[i][2];
                                    newS[j][3] = result[i][3];
                                    newS[j][4] = result[i][4];
                                    newS[j][5] = result[i][5];
                                    newS[j][6] = result[i][6];
                                    j++;
                                }
                            }
                        }

                        for (int i = 0; i < c; i++) {
                            listTable.setWidget(i + 1, 0, new Label(newS[i][0]));

                            listTable.setWidget(i + 1, 1,
                                new Label("(" + newS[i][1] + ")"));

                            listTable.setWidget(i + 1, 3, new Label(newS[i][3]));

                            listTable.setWidget(i + 1, 5, new Label(newS[i][6]));
                        }

                        CM_ComboBoxes[] ocb = new CM_ComboBoxes[boardCount];

                        for (int k = 0; k < boardCount; k++) {
                            for (int i = 0; i < c; i++)
                                if (listTable.getText(i + 1, 3)
                                                 .equals(chooseBoard)) {
                                    ocb[k] = new CM_ComboBoxes();
                                    ocb[k].BoardName();

                                    ocb[k].boardCB.setName(listTable.getText(i +
                                            1, 0));

                                    listTable.setWidget(i + 1, 4, ocb[k].boardCB);
                                    k++;
                                }
                        }

                        ocb1 = ocb;

                        CheckBox[] cb2 = new CheckBox[spWtCount];

                        for (int k = 0; k < spWtCount; k++) {
                            for (int i = 0; i < c; i++)

                                if (listTable.getText(i + 1, 5).equals("") == false) {
                                    cb2[k] = new CheckBox();
                                    cb2[k].setName(listTable.getText(i + 1, 5));
                                    listTable.setWidget(i + 1, 5, cb2[k]);

                                    k++;
                                }
                        }

                        cb1 = cb2;

                        NumberField[] t = new NumberField[c];

                        t1 = t;

                        for (int k = 0; k < c; k++) {
                            t[k] = new NumberField();

                            t1[k] = new NumberField();
                            t[k].setAllowNegative(false);

                            t[k].setMaxLength(7);

                            t1[k] = t[k];
                            t[k].setWidth(130);
                            t[k].setAllowBlank(false);

                            listTable.setWidget(k + 1, 2, t[k]);
                        }

                        for (int i = 0; i < (c - 1); i++) {
                            if (listTable.getText(i + 1, 0)
                                             .contains("Under Graduate")) {
                                if (listTable.getText(i + 2, 0)
                                                 .contains("Under Graduate")) {
                                    listTable.setWidget(i + 1, 3,
                                        (new Label(chooseDeg)));
                                    listTable.setWidget(i + 1, 4,
                                        (ug.firstDegCodeCB));
                                }
                            }

                            if (listTable.getText(i + 1, 0)
                                             .contains("Post Graduate")) {
                                if (listTable.getText(i + 2, 0)
                                                 .contains("Post Graduate")) {
                                    listTable.setWidget(i + 1, 3,
                                        (new Label(chooseDeg)));
                                    listTable.setWidget(i + 1, 4,
                                        (pg.firstDegCodeCB));
                                }
                            }
                        }
                    }
                }
            });

        return listTable;
    }

    /*
     * Method to Validate Board Information.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int CheckBoard(CM_ComboBoxes[] basicComboBoxSamples) {
        int check = 0;

        for (int i = 0; i < basicComboBoxSamples.length; i++)
            if (valid.nullValidator(
                        basicComboBoxSamples[i].boardCB.getRawValue())) {
                try {
                    check++;

                    basicComboBoxSamples[i].boardCB.markInvalid("");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        return check;
    }

    /*
     * Method to Validate the academic information.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int checkAcad() {
        int check = 0;

        for (int i = 1; i < listTable.getRowCount(); i++) {
            try {
                t1[i - 1].validate();
            } catch (Exception e) {
                check++;
                System.out.println(check);
            }

            try {
                if (Float.parseFloat(t1[i - 1].getRawValue()) <= 0.0) {
                    check++;
                    t1[i - 1].markInvalid("");
                }
            } catch (Exception e) {
            }
        }

        return check;
    }

    /*
     * Method to get the student information.
     * @return String[]
     */
    @SuppressWarnings("static-access")
    public String[] studentInfo() {
        final String[] student = new String[17];

        student[0] = fname.getRawValue();
        student[1] = mname.getRawValue();
        student[2] = lname.getRawValue();
        student[3] = newGender.getRawValue();
        student[4] = newCat.getValue();
       
        //DateUtil d = new DateUtil();
        //student[5] = d.format(dateofbirthDateField.getValue(), "Y-m-d");
        student[5] = dateofbirthDateField.getDisplayValue();
        student[6] = fname1.getRawValue();
        student[7] = mname1.getRawValue();
        student[8] = lname1.getRawValue();
        student[9] = fname2.getRawValue();
        student[10] = mname2.getRawValue();
        student[11] = lname2.getRawValue();
        //student[12]= appNo.getRawValue();
        student[12] = null;
        //	student[13]= regNo.getRawValue();
        student[13] = null;
        student[16] = newCat.getRawValue();

        return student;
    }

    /*
     * Method to Validate Address information.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int checkAddress() {
        int check = 0;

        if (valid.nullValidator(cityText.getRawValue()) ||
                valid.nullValidator(newState.getRawValue()) ||
                valid.nullValidator(pinNumber1.getRawValue())) {
            check = 1;
        } else {
            check = 0;
        }

        try {
            pinNumber1.validate();
        } catch (Exception e) {
            check = 1;
        }

        return check;
    }

    /*
     * Method to get the student Address information.
     * @return String[]
     */
    public String[] addressInfo(String StuId) {
        String[] address = new String[8];

        address[0] = StuId;
        address[1] = street1Text.getRawValue();
        address[5] = street2Text.getRawValue();
        address[2] = cityText.getRawValue();
        address[3] = newState.getRawValue();
        address[4] = pinNumber1.getRawValue();

        return address;
    }

    /*
     * Method to Validate Paper code.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int checkpaperCode() {
        int check = 0;

        for (int i = 0; i < groupCB.length; i++) {
            if (valid.nullValidator(groupCB[i].getRawValue())) {
                try {
                    check++;
                    groupCB[i].markInvalid("");
                } catch (Exception e) {
                }
            }
        }

        return check;
    }

    /*
     * This method is used to get the Paper Code information.
     * @return String[][]
     */
    public String[][] StudentpaperCode() {
        String[][] paperCode = null;

        paperCode = new String[groupCB.length][2];

        for (int i = 0; i < groupCB.length; i++) {
            paperCode[i][0] = groupCB[i].getValue();
            paperCode[i][1] = groupCB[i].getName();
        }

        return paperCode;
    }

    public String StudentFD(ComboBox c) {
        return c.getValue();
    }

    /*
     * Method to Validate Under graduate Information.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int checkUG() {
        int check = 0;

        if (ug.firstDegCodeCB.isVisible() &&
                (valid.nullValidator(ug.firstDegCodeCB.getRawValue()))) {
            check = 1;
        } else {
            check = 0;
        }

        return check;
    }

    /*
     * Method to Validate Post graduate information.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int checkPG() {
        int check = 0;

        if (pg.firstDegCodeCB.isVisible() &&
                (valid.nullValidator(pg.firstDegCodeCB.getRawValue()))) {
            check = 1;
        } else {
            check = 0;
        }

        return check;
    }

    /*
     * Method to Validate personal information.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int checkPersonal() {
        int check = 0;

        if (valid.nullValidator(fname.getText()) ||
                valid.nullValidator2(dateofbirthDateField.getDisplayValue()) ||
                valid.nullValidator(newCat.getRawValue()) ||
                valid.nullValidator(fname1.getText()) ||
                valid.nullValidator(fname2.getText())) {
            check = 1;
        } else {
            check = 0;
        }

        return check;
    }

    /*
     * Method to Validate the Date.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int checkDate() throws Exception {
        Date currentDate = new Date();
        Date dateofbirth=null;
        int check = 0;
        try{
         dateofbirth = (Date) dateofbirthDateField.getValue();
        }
        catch(Exception e){
        	throw new Exception(e);
        }
        
        if (valid.nullValidator2(dateofbirthDateField.getDisplayValue())) {
        	try {
                check++;
                //dateofbirthDateField.markInvalid("");
                dateofbirthDateField.getInvalidDateStringMessage();
            } catch (Exception e) {
            }
        } else {
        	if (valid.datechecker1(dateofbirth, currentDate) == true) {
                try {
                    check++;
                  dateofbirthDateField.getInvalidDateStringMessage();
                  
                } catch (Exception e) {
                }
            }
        }
	
        return check;
    }

    /*
     * Method to Validate whether information is in character or not.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int checkChars() {
        int check = 0;

        if ((valid.checkText(fname) > 0) || (valid.checkText(fname) > 0) ||
                (valid.checkText(mname) > 0) || (valid.checkText(lname) > 0) ||
                (valid.checkText(fname1) > 0) || (valid.checkText(mname1) > 0) ||
                (valid.checkText(lname1) > 0) || (valid.checkText(fname2) > 0) ||
                (valid.checkText(mname2) > 0) || (valid.checkText(lname2) > 0) ||
                (valid.checkText(cityText) > 0)) {
            check = 1;
        } else {
            check = 0;
        }

        return check;
    }

    /*
     * Method to get the data for Duplicacy Check.
     * @return String[]
     */
    public String[] DuplicacyCheck(String Reg) {
        String[] checkData = new String[6];

        checkData[0] = Reg;
        checkData[1] = fname.getText();
        checkData[2] = lname.getText();
        checkData[3] = dateofbirthDateField.getDisplayValue();
        checkData[4] = fname1.getText();
        checkData[5] = newCat.getRawValue();

        return checkData;
    }

    /*
     * Method to Validate null value
     * @return 0 if successfully validated otherwise return 1.
     */
    public int markIV(TextField t) {
        int check = 0;

        if ((valid.nullValidator(t.getValueAsString()) == true)) {
            try {
                check++;
                t.markInvalid("");
            } catch (Exception e) {
            }
        }

        return check;
    }

    /*
     * Method to Validate character value
     * @return 0 if successfully validated otherwise return 1.
     */
    public void markIVChars(TextField t) {
        if (valid.checkText(t) == 1) {
            try {
                t.markInvalid("");
            } catch (Exception e) {
            }
        }
    }

    /*
     * Method to Validate the marks information.
     * @return 0 if successfully validated otherwise return 1.
     */
    public int marksCheck() {
        int check = 0;

        for (int i = 1; i < listTable.getRowCount(); i++) {
            if (listTable.getText(i, 1).equals("(" + marksObtd + ")")) {
                float x = Float.parseFloat(t1[i - 1].getRawValue());
                float y = Float.parseFloat(t1[i].getRawValue());

                if ((x > 0) && (y > 0) && ((x / y) > 1)) {
                    try {
                        check++;
                        t1[i - 1].markInvalid("");
                        t1[i].markInvalid("");
                    } catch (Exception e) {
                    }
                }
            }

            if (listTable.getText(i, 1).equals("(" + percent + ")")) {
                float x = Float.parseFloat(t1[i - 1].getRawValue());

                if ((x > 0) && (x > 100.00)) {
                    try {
                        check++;
                        t1[i - 1].markInvalid("");
                    } catch (Exception e) {
                    }
                }
            }
        }

        return check;
    }
}
