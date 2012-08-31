/*
 * @(#) ModuleForExistingProgram.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
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

package in.ac.dei.edrp.client.EdeiAdmission;

import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.TextFieldListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
/**
 * @version 1.0 1 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 *
 */
public class ModuleForExistingProgram {
	EDeiSummaryServiceAsync summaryServiceAsync = GWT.create(EDeiSummaryService.class);
	messages msgs = GWT.create(messages.class);
	constants constants = GWT.create(constants.class);

	String universityId;
	String sessionStartDate;
	String sessionEndDate;
	String userEmail;
	List<EDEIStudentBean>availableList;
	private List<String> selectedCourseId=new ArrayList<String>();
	private List<String> selectedCourseName=new ArrayList<String>();

	public List<EDEIStudentBean> selectedModuleDetails=new ArrayList<EDEIStudentBean>(); 
	List<EDEIStudentBean> courseModule=new ArrayList<EDEIStudentBean>();

	ToolbarButton addButtonModule;
	ToolbarButton addButton;
	String pgId;

	public ModuleForExistingProgram(List<EDEIStudentBean>availableList,String universityId, String sessionStartDate,String sessionEndDate,String userEmail, String pgId) {
		this.availableList=availableList;
		this.universityId=universityId;
		this.sessionStartDate=sessionStartDate;
		this.sessionEndDate=sessionEndDate;
		this.userEmail=userEmail;
		this.pgId=pgId;
	}

	public Panel onModule(String action){
		Panel main=new Panel();	
		
		if(action.equalsIgnoreCase("CRS")){
			selectedCourseId.clear();
			selectedCourseName.clear();

			final FlexTable courseFT=new FlexTable();
			final TextField searchCourse=new TextField();

			FlexTable searchAdd=new FlexTable();
			searchAdd.setWidget(0, 0,new Label(constants.searchCourses()));
			searchAdd.setWidget(0, 1, searchCourse);
			courseFT.setWidget(0, 0, searchAdd);
			main.add(courseFT);
			final List<EDEIStudentBean>courseList=new ArrayList<EDEIStudentBean>();
			List<String>tempCourseList=new ArrayList<String>();
			for(int i=0;i<availableList.size();i++){
				if(tempCourseList.indexOf(availableList.get(i).getCoursesId())<0){
					tempCourseList.add(availableList.get(i).getCoursesId());
					courseList.add(availableList.get(i));
				}
			}
			
			if(courseList.size()<1){
				MessageBox.alert(msgs.courseNotFound());
			}
			createGrid(courseList,courseFT);			
			searchCourse.addListener(new TextFieldListenerAdapter() {
				public void onChange(Field field, Object newVal, Object oldVal) {
					List<EDEIStudentBean> filtered=filterList(courseList, searchCourse.getValueAsString());
					createGrid(filtered,courseFT);
				}
				public void onFocus(Field field) {
					onChange(field, "", "");
				}

				@SuppressWarnings("static-access")
				public void onSpecialKey(Field field, EventObject e) {
					if(e.getKey()==e.ENTER){
						List<EDEIStudentBean> filtered=filterList(courseList, searchCourse.getValueAsString());
						createGrid(filtered,courseFT);

					}
				}
			});			
		
		}
		else if(action.equalsIgnoreCase("MOD")){
			selectedModuleDetails.clear();
			final FlexTable moduleFT=new FlexTable();
			final TextField searchModule=new TextField();
			FlexTable searchAdd=new FlexTable();
			searchAdd.setWidget(0, 0, new Label(constants.searchModule()));
			searchAdd.setWidget(0, 1, searchModule);
			moduleFT.setWidget(0, 0, searchAdd);
			main.add(moduleFT);
			final List<EDEIStudentBean>moduleList=new ArrayList<EDEIStudentBean>();
			List<String>tempModuleList=new ArrayList<String>();
			for(int i=0;i<availableList.size();i++){
				if(tempModuleList.indexOf(availableList.get(i).getModuleId())<0){
					tempModuleList.add(availableList.get(i).getModuleId());
					moduleList.add(availableList.get(i));					
				}
			}
			if(moduleList.size()<1){
				MessageBox.alert(msgs.moduleNotFound());
			}
			createModuleGrid(moduleList,moduleFT);

			searchModule.addListener(new TextFieldListenerAdapter() {
				public void onChange(Field field, Object newVal, Object oldVal) {
					List<EDEIStudentBean> filtered=filterListModule(moduleList, searchModule.getValueAsString());
					createModuleGrid(filtered,moduleFT);
				}

				public void onFocus(Field field) {
					onChange(field, "", "");
				}

				@SuppressWarnings("static-access")
				public void onSpecialKey(Field field, EventObject e) {
					if(e.getKey()==e.ENTER){
						List<EDEIStudentBean> filtered=filterListModule(moduleList, searchModule.getValueAsString());
						createModuleGrid(filtered,moduleFT);
					}
				}

			});
		}
		return main;
	}

	void createGrid(List<EDEIStudentBean> resultDb, final FlexTable courseFT){
		final GridPanel grid = new GridPanel();
		grid.setLoadMask(true);
		grid.setMaskDisabled(false);
		Object[][] data = new Object[resultDb.size()][2];
		for (int i = 0; i < resultDb.size(); i++) {
			data[i][0] = resultDb.get(i).getCoursesName();
			data[i][1]= resultDb.get(i).getCoursesId();
		}
		final CheckboxSelectionModel cbSelectionModel=new CheckboxSelectionModel();
		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("courseName"),
				new StringFieldDef("courseId")
		});

		MemoryProxy proxy = new MemoryProxy(data);
		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(proxy, reader);
		store.load();
		grid.setStore(store);
		BaseColumnConfig[] columns = new BaseColumnConfig[] {
				new CheckboxColumnConfig(cbSelectionModel),
				new ColumnConfig(constants.courseCode(),"courseId", 80, true, null,"courseId"),		
				new ColumnConfig(constants.course(),"courseName", 80, true, null,"courseName") ,
		};
		ColumnModel columnModel = new ColumnModel(columns);
		grid.setColumnModel(columnModel);
		grid.setFrame(true);
		grid.setAutoExpandColumn("courseName");
		grid.setSelectionModel(cbSelectionModel);
		grid.setWidth(500);
		grid.setHeight(300);
		grid.setTitle(constants.selectCourseSession()+" "+sessionStartDate+" : "+sessionEndDate);
		grid.setStripeRows(true);
		Toolbar toolbar = new Toolbar();
		toolbar.addFill();
		grid.setTopToolbar(toolbar);
		addButton = new ToolbarButton(constants.select());
		final ToolbarButton editButton = new ToolbarButton(constants.addMore());
		editButton.setDisabled(true);
		addButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e){
				selectedCourseId.clear();
				selectedCourseName.clear();
				final Record records[]=cbSelectionModel.getSelections();

				if(records.length>0){
					final List<String> courseCheck=new ArrayList<String>();
					for(int l=0;l<records.length;l++){
						courseCheck.add(records[l].getAsString("courseId"));
					}
					summaryServiceAsync.getStudentCourses(userEmail,sessionStartDate,sessionEndDate,new AsyncCallback<List<EDEIStudentBean>>(){

						public void onFailure(Throwable arg0) {
							MessageBox.alert(""+arg0.getMessage());

						}
						public void onSuccess(List<EDEIStudentBean> studentCourse) {
							String addedCourse = "";
							for(int i=0;i<studentCourse.size();i++){
								if(courseCheck.contains(studentCourse.get(i).getCoursesId())){
									addedCourse=addedCourse+" "+studentCourse.get(i).getCoursesId();
								}
							}
							if(addedCourse.length()>0){
								MessageBox.alert(msgs.appliedCourse()+" "+addedCourse);
							}
							else{
								if(records.length<5){
									addButton.setDisabled(true);
									cbSelectionModel.lock();
									editButton.setDisabled(false);

									for(int m=0;m<records.length;m++){
										selectedCourseId.add(records[m].getAsString("courseId"));
										selectedCourseName.add(records[m].getAsString("courseName"));
									}
									List<EDEIStudentBean>moduleList=new ArrayList<EDEIStudentBean>();
									for(int ii=0;ii<selectedCourseId.size();ii++){
										for(int jj=0;jj<availableList.size();jj++){
											if(selectedCourseId.get(ii).equals(availableList.get(jj).getCoursesId())){
												moduleList.add(availableList.get(jj));
											}
										}
									}
									courseModule.clear();
									for(int a=0;a<moduleList.size();a++){
										courseModule.add(moduleList.get(a));
									}
									Panel panel = new Panel();
									panel.setBorder(false);  
									panel.setPaddings(15);
									panel.setAutoScroll(true);

									final TreePanel treePanel = new SampleTree(courseModule);  
									treePanel.setTitle(constants.selectionMade()+" "+selectedCourseId.size());/*+"    \t    "+"Session: "+sessionStartDate+"  "+sessionEndDate*/
									treePanel.setBorder(true);
									treePanel.setStyleName("panelColorMy");
									treePanel.setAutoScroll(true);
									treePanel.setWidth(500);  
									treePanel.setHeight(300);
									panel.add(treePanel);
									courseFT.setWidget(1, 2, panel);									
								}
								else MessageBox.alert(msgs.course4());
							}
						}
					});
				}else MessageBox.alert(msgs.selectCourse());
			}
		});

		editButton.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e){
				addButton.setDisabled(false);
				cbSelectionModel.unlock();
				editButton.setDisabled(true);
			}
		});
		toolbar.addButton(editButton);
		toolbar.addButton(addButton);
		courseFT.setWidget(1, 0, grid);
	}
	
	protected void createModuleGrid(final List<EDEIStudentBean> resultDb, final FlexTable moduleFT){
		final GridPanel moduleGrid = new GridPanel();
		Object[][] data = new Object[resultDb.size()][11];
		for (int i = 0; i < resultDb.size(); i++) {
			data[i][0] = resultDb.get(i).getModuleName();
			data[i][1]= resultDb.get(i).getModuleId();
			data[i][2]=resultDb.get(i).getModuleStartDate();
			data[i][3]=resultDb.get(i).getModuleEndDate();
			data[i][4]=resultDb.get(i).getProgCourseKey();
			data[i][5]=resultDb.get(i).getEntityId();
			data[i][6]=resultDb.get(i).getCoursesId();
			data[i][7]=resultDb.get(i).getCoursesName();
			data[i][8]=resultDb.get(i).getCredit();
			data[i][9]=resultDb.get(i).getSemesterStartDate();
			data[i][10]=resultDb.get(i).getSemesterEndDate();
		}
		final CheckboxSelectionModel cbSelectionModelModule=new CheckboxSelectionModel();
		RecordDef recordDef = new RecordDef(new FieldDef[] {
				new StringFieldDef("moduleName"),
				new StringFieldDef("moduleId"),
				new StringFieldDef("startDate"),
				new StringFieldDef("endDate"),
				new StringFieldDef("progCourseKey"),
				new StringFieldDef("entity"),
				new StringFieldDef("courseId"),
				new StringFieldDef("courseName"),
				new StringFieldDef("credit"),
				new StringFieldDef("semesterStartDate"),
				new StringFieldDef("semesterEndDate")
		});
		MemoryProxy proxy = new MemoryProxy(data);
		ArrayReader reader = new ArrayReader(recordDef);
		Store store = new Store(proxy, reader);
		store.load();
		moduleGrid.setStore(store);
		BaseColumnConfig[] columns = new BaseColumnConfig[] {
				new CheckboxColumnConfig(cbSelectionModelModule),
				new ColumnConfig(constants.moduleCode(),"moduleId", 80, true, null,"moduleId"),		
				new ColumnConfig(constants.module(),"moduleName", 80, true, null,"moduleName"),
				new ColumnConfig(constants.courseCode(),"courseId", 80, true, null,"courseId"),		
				new ColumnConfig(constants.course(),"courseName", 80, true, null,"courseName"),
				new ColumnConfig(constants.startDate(),"startDate", 80, true, null,"startDate"),		
				new ColumnConfig(constants.endDate(),"endDate", 80, true, null,"endDate"),
				new ColumnConfig(constants.credits(),"credit", 80, true, null,"credit"),
		};

		ColumnModel columnModel = new ColumnModel(columns);
		moduleGrid.setColumnModel(columnModel);
		moduleGrid.setFrame(true);
		moduleGrid.setAutoExpandColumn("moduleName");
		moduleGrid.setSelectionModel(cbSelectionModelModule);
		moduleGrid.setWidth(500);
		moduleGrid.setHeight(300);
		moduleGrid.setTitle(constants.selectModuleSession()+" "+sessionStartDate+" : "+sessionEndDate);
		moduleGrid.setStripeRows(true);

		Toolbar toolbar = new Toolbar();
		toolbar.addFill();
		moduleGrid.setTopToolbar(toolbar);
		addButtonModule = new ToolbarButton(constants.select());
		final ToolbarButton editButtonModule = new ToolbarButton(constants.addMore());
		editButtonModule.setDisabled(true);

		addButtonModule.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e){
				final Record records[]=cbSelectionModelModule.getSelections();
				selectedModuleDetails.clear();
				if(records.length>0){
					final List<String> moduleCheck=new ArrayList<String>();
					for(int l=0;l<records.length;l++){
						moduleCheck.add(records[l].getAsString("moduleId"));
					}
					summaryServiceAsync.checkstudentModules(userEmail,sessionStartDate,sessionEndDate,new AsyncCallback<List<EDEIStudentBean>>() {

						public void onFailure(Throwable arg0) {
							MessageBox.alert(arg0.getMessage());
						}

						public void onSuccess(List<EDEIStudentBean> moduleList) {
							String addedModule = "";
							for(int i=0;i<moduleList.size();i++){
								if(moduleCheck.contains(moduleList.get(i).getModuleId())){
									addedModule=addedModule+" "+moduleList.get(i).getModuleId();
								}
							}
							if(addedModule.length()>0){
								MessageBox.alert(msgs.appliedModule()+" "+addedModule);
							}
							else{
								int selectedcredits=0;
								for(int p=0;p<records.length;p++){
									selectedcredits=selectedcredits+records[p].getAsInteger("credit");
								}
								final int moduleTotalCredit=selectedcredits;
								summaryServiceAsync.getModuleCredit(new AsyncCallback<Integer>() {
									public void onFailure(Throwable arg0) {
										MessageBox.alert(arg0.toString());
									}

									public void onSuccess(Integer count) {

										if(count>=moduleTotalCredit){
											addButtonModule.setDisabled(true);
											cbSelectionModelModule.lock();
											editButtonModule.setDisabled(false);

											for(int m=0;m<records.length;m++){
												EDEIStudentBean moduleData= new EDEIStudentBean();
												moduleData.setModuleId(records[m].getAsString("moduleId"));
												moduleData.setModuleName(records[m].getAsString("moduleName"));
												moduleData.setCoursesId(records[m].getAsString("courseId"));
												moduleData.setCoursesName(records[m].getAsString("courseName"));
												moduleData.setModuleStartDate(records[m].getAsString("startDate"));
												moduleData.setModuleEndDate(records[m].getAsString("endDate"));
												moduleData.setProgCourseKey(records[m].getAsString("progCourseKey"));
												moduleData.setEntityId(records[m].getAsString("entity"));
												moduleData.setSemesterStartDate(records[m].getAsString("semesterStartDate"));
												moduleData.setSemesterEndDate(records[m].getAsString("semesterEndDate"));
												selectedModuleDetails.add(moduleData);
											}
											Panel panel = new Panel();
											panel.setBorder(false);  
											panel.setPaddings(15);
											panel.setAutoScroll(true);

											final TreePanel treePanel = new SampleTree(selectedModuleDetails,"1");  
											treePanel.setTitle(constants.selectionModule()+" "+selectedModuleDetails.size());
											treePanel.setBorder(true);
											treePanel.setStyleName("panelColorMy");
											treePanel.setAutoScroll(true);
											treePanel.setWidth(500);  
											treePanel.setHeight(300);
											panel.add(treePanel);
											moduleFT.setWidget(1, 2, panel);
										}
										else MessageBox.alert(msgs.creditlimit()+" "+count);
									}
								});
							}

						}
					});
				}else MessageBox.alert(msgs.selectModule());
			}
		});
		editButtonModule.addListener(new ButtonListenerAdapter(){
			public void onClick(Button button, EventObject e){
				addButtonModule.setDisabled(false);
				cbSelectionModelModule.unlock();
				editButtonModule.setDisabled(true);
			}
		});
		toolbar.addButton(editButtonModule);
		toolbar.addButton(addButtonModule);
		moduleFT.setWidget(1, 0, moduleGrid);
	}

	class SampleTree extends TreePanel {  
		public SampleTree(List<EDEIStudentBean> modules) {
			TreeNode root = new TreeNode(constants.coursesModules());  
			TreeNode ceo ;  	
			for(int i=0;i<selectedCourseId.size();i++){
				ceo=new TreeNode(new HTML("<B>"+selectedCourseId.get(i)+" "+selectedCourseName.get(i)+"</B>")+"");
				ceo.setExpandable(true);
				ceo.setExpanded(true);
				for(int k=0;k<modules.size();k++){
					if(selectedCourseId.get(i).equalsIgnoreCase(modules.get(k).getCoursesId())){
						ceo.appendChild(new TreeNode(modules.get(k).getModuleName()+" "+modules.get(k).getModuleId()+" ,Duration "+modules.get(k).getModuleStartDate()+"-"+modules.get(k).getModuleEndDate()));
					}
				}
				root.appendChild(ceo);  
			}
			setRootVisible(false);  
			setTitle(constants.course());  
			setWidth(500);  
			setHeight(300);  
			setRootNode(root);  
			root.setExpanded(true);  
		} 
		public SampleTree(List<EDEIStudentBean> modules,String flag) {
			TreeNode rootModule = new TreeNode(constants.coursesModules());  
			TreeNode moduleNode ;
			for(int i=0;i<modules.size();i++){
				moduleNode=new TreeNode(new HTML("<B>"+modules.get(i).getCoursesId()+" "+modules.get(i).getCoursesName()+"</B>")+"");
				moduleNode.setExpandable(true);
				moduleNode.setExpanded(true);
				moduleNode.appendChild(new TreeNode(modules.get(i).getModuleName()+" "+modules.get(i).getModuleId()+" ,Duration "+modules.get(i).getModuleStartDate()+"-"+modules.get(i).getModuleEndDate()));
				rootModule.appendChild(moduleNode);  
			}
			setRootVisible(false);  
			setTitle(constants.course());  
			setWidth(500);  
			setHeight(300);  
			setRootNode(rootModule);  
			rootModule.setExpanded(true); 
		}
	}

	List<EDEIStudentBean> filterList(List<EDEIStudentBean> resultDb, String filterTxt) {  
		List<EDEIStudentBean> filteredList = new ArrayList<EDEIStudentBean>();
		List<EDEIStudentBean> result=new ArrayList<EDEIStudentBean>();
		for(EDEIStudentBean course: resultDb) {  
			if((course.getCoursesId().toLowerCase().contains(filterTxt.toLowerCase()))||(course.getCoursesName().toLowerCase().contains(filterTxt.toLowerCase()))){
				filteredList.add(course);
			}
			else result.add(course);
		}
		filteredList.addAll(result);
		return filteredList;  
	}

	List<EDEIStudentBean> filterListModule(List<EDEIStudentBean> resultDb, String filterTxt) {

		List<EDEIStudentBean> filteredList = new ArrayList<EDEIStudentBean>();
		List<EDEIStudentBean> result=new ArrayList<EDEIStudentBean>();

		for(EDEIStudentBean course: resultDb) {  

			if((course.getCoursesId().toLowerCase().contains(filterTxt.toLowerCase()))||(course.getCoursesName().toLowerCase().contains(filterTxt.toLowerCase()))
					||(course.getModuleId().toLowerCase().contains(filterTxt.toLowerCase()))||(course.getModuleName().toLowerCase().contains(filterTxt.toLowerCase())))
			{
				filteredList.add(course);
			}
			else result.add(course);
		}
		filteredList.addAll(result);

		return filteredList;  
	}
}
