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
package in.ac.dei.edrp.client.Shared;


/**
 * @author Manpreet Kaur
 */

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.Function;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;


//import org.apache.commons.logging.*;  
//import org.apache.log4j.Logger;

/**
 * This class creates Home tab for institute administrator
 */

//import org.apache.commons.logging.*;  
//import org.apache.log4j.Logger;
@SuppressWarnings("deprecation")
public class CM_InstituteHome {
    //	private final CM_connectAsync connectService = GWT.create(CM_connect.class);	

    //	private static final Log log = LogFactory.getLog(CM_InstituteHome.class); 

    //	static Logger log = Logger.getLogger(CM_InstituteHome.class);
    public String userid;

    //	 	final FormPanel formPanel = new FormPanel(Position.LEFT); 
//    CM_tryUpload CMTU = new CM_tryUpload();
    HTMLPanel Text = new HTMLPanel("Welcome <BR> Institute Admin");
    final Image imgHome = new Image("images/home.png");
    final Image imgHome1 = new Image("images/home.png");
    HorizontalPanel mainHorizontalPanel = new HorizontalPanel();
    HorizontalPanel browseImgHpanel = new HorizontalPanel();
    VerticalPanel vpanel = new VerticalPanel();
    VerticalPanel vimgpanel = new VerticalPanel();
    DecoratorPanel docpanel = new DecoratorPanel();
    DecoratorPanel docFormpanel = new DecoratorPanel();
    Panel imgHorizontalPanel = new Panel();
    FormPanel imgFormPanel = new FormPanel();
    Button changeImageButton = new Button("Change image");
    Button browseButton = new Button("Browse");
    TextField imgpath = new TextField();

    //Constructor for setting the Value of User ID 
    public CM_InstituteHome(String Uid) {
        this.userid = Uid;
    }

    //Main method of class that displays welcome message for institute Administrator.
    public void Home() {
        Text.addStyleName("Heading");

        final Panel myPanel = new Panel();
        final Panel calenderPanel = new Panel();
        final Hyperlink news = new Hyperlink("News", null);
        final Hyperlink cal = new Hyperlink("Calender", null);

        mainHorizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        mainHorizontalPanel.setWidth("1000px");

        final CM_calender calendar = new CM_calender();

        calendar.addChangeListener(new ChangeListener() {
                public void onChange(Widget arg0) {
                    MessageBox.alert("Date selected: " + calendar.getDate());
                }
            });

        myPanel.setWidth(280);
        myPanel.setHeight(200);
        myPanel.setFrame(true);
        myPanel.setTitle("News");
        myPanel.setCollapsible(true);
        myPanel.setAnimCollapse(true);
        myPanel.addTool(new Tool(Tool.CLOSE,
                new Function() {
                public void execute() {
                    myPanel.hide();
                    vpanel.add(news);
                }
            }));

        news.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent arg0) {
                    vpanel.remove(news);
                    myPanel.show();
                }
            });

        calenderPanel.setWidth(280);
        calenderPanel.setHeight(225);
        calenderPanel.setFrame(true);
        calenderPanel.setTitle("Calender");
        calenderPanel.setCollapsible(true);
        calenderPanel.setAnimCollapse(true);
        calenderPanel.add(calendar);
        calenderPanel.addTool(new Tool(Tool.CLOSE,
                new Function() {
                public void execute() {
                    calenderPanel.hide();
                    vpanel.add(cal);
                }
            }));

        cal.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent arg0) {
                    vpanel.remove(cal);
                    calenderPanel.show();
                }
            });

        //	changeImageButton.setImage("a","/images/advertisement.png");
        
        
        
//        changeImageButton.addListener(new ButtonListenerAdapter() {
//                public void onClick(Button ProgrammeEditButton, EventObject e) {
//                    //	vimgpanel.add(browseImgHpanel);
//                    CMTU.tryupload();
//
//                    vimgpanel.add(CMTU.hpanel);
//                }
//            });

        
        
           browseImgHpanel.setSpacing(10);
        browseImgHpanel.add(imgpath);
        browseImgHpanel.add(browseButton);

        vpanel.setSpacing(20);
        vpanel.add(calenderPanel);
        vpanel.add(myPanel);

        docpanel.add(vpanel);

        vimgpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

        //	
        //	Image img=new Image();	
        //	
        //	  RecordDef recordDef = new RecordDef(new FieldDef[]{  
        // 		new ObjectFieldDef("image","image")  
        //	  
        //	  });  
        //
        //final XmlReader reader = new XmlReader("contact", recordDef);  
        //reader.setSuccess("@success");  
        //imgFormPanel.setReader(reader);
        //
        //FieldSet fieldSet = new FieldSet("Welcome");  
        //fieldSet.add(new Image());  

        //img.setSize("275px","270px");
        //        imgHorizontalPanel.setAutoHeight(true);
        //        imgHorizontalPanel.setAutoWidth(true);
        imgHorizontalPanel.setSize(275, 270);
        //imgHorizontalPanel.add(img);
        //imgFormPanel.add(imgHorizontalPanel);
        //imgFormPanel.add(fieldSet);
        //imgFormPanel.getForm().load("loginImage.xml", null, Connection.GET, "Loading...");
        vimgpanel.add(imgHorizontalPanel);
        /*
         * Temporarily removed button
         */

        //	vimgpanel.add(changeImageButton);
        docFormpanel.add(vimgpanel);

        //	execute();

        //	void com.gwtext.client.widgets.Panel.load(String url,Urlparam[] param,UrlLoadcallback callback,boolean loadscript)
        imgHorizontalPanel.load("loginImage.xml", null, null, false);

        mainHorizontalPanel.setSpacing(20);
        mainHorizontalPanel.add(docFormpanel);
        mainHorizontalPanel.add(Text);
        mainHorizontalPanel.add(docpanel);

        //			    log.trace("Hello World!");  
        //			    log.debug("How are you today?");  
        //			    log.info("I am fine.");  
        //			    log.error("I am programming.");  
        //			    log.warn("I love programming.");  
        //			    log.fatal("I am now dead. I should have been a movie star.");  
    }

    public void execute() {
        //	if (log.isTraceEnabled()) {
        //		log.trace("Test: TRACE level message.");
        //	}
        //	if (log.isDebugEnabled()) {
        //		log.debug("Test: DEBUG level message.");
        //	}
        //	if (log.isInfoEnabled()) {
        //		log.info("Test: INFO level message.");
        //	}

        //	if (log.isEnabledFor(Level.WARN)) {
        //		log.warn("Test: WARN level message.");
        //	}
        //	if (log.isEnabledFor(Level.ERROR)) {
        //		log.error("Test: ERROR level message.");
        //	}
        //	if (log.isEnabledFor(Level.FATAL)) {
        //		log.fatal("Test: FATAL level message.");
        //	}
    }
}
